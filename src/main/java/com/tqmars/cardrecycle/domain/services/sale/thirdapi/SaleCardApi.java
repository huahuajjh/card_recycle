package com.tqmars.cardrecycle.domain.services.sale.thirdapi;

import com.tqmars.cardrecycle.application.net.HttpClientTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Des;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jjh on 1/21/17.
 */
public class SaleCardApi {
    private static String notifyUrl = PropertiesFileTool.readByKey("backUrl");

    public static ApiResult sale1Card(String _cardCode,String _par,String _cardNo,String _cardPwd,String orderNo) {
        String merchId = PropertiesFileTool.readByKey("businessId");
        String merchSecret = PropertiesFileTool.readByKey("businessPwd");
        String cardCode = _cardCode;
        String cardNo = _cardNo;
        String cardKey = null;
        try {
            cardKey = Des.toDes3(_cardPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String par = _par;
        String merchOrderNo = orderNo;
        String sign = Md5.md5(merchId+merchOrderNo+cardCode+par+cardNo+cardKey+notifyUrl+merchSecret);

        String url = "http://api.139card.com/card/sell";

        Map<String,String> map = new HashMap<>();
        map.put("MerchId",merchId);
        map.put("CardCode",cardCode);
        map.put("CardNo",cardNo);
        map.put("CardKey",cardKey);
        map.put("Par",par);
        map.put("MerchOrderNo",merchOrderNo);
        map.put("NotifyUrl",notifyUrl);
        map.put("Sign",sign);

        String result = HttpClientTool.get(url,map);
        ApiResult apiResult = new ApiResult();
        try {
            apiResult = Serialization.toObject(result,ApiResult.class);
            if(null == apiResult || null == apiResult.getResultCode() || apiResult.getResultCode().equals(""))
            {
                apiResult.setResultCode("-9");
                apiResult.setMessage("第三方接口错误");
                return apiResult;
            }
            return apiResult;
        }catch (Exception ex){
            if(result.contains("<html>")){
                apiResult.setResultCode("-1");
                apiResult.setMessage("第三方接口异常");
                LoggerFactory.getLogger().error("error when request third card api,cannot deserialization the result,404 error:"+ex.getMessage());
                return apiResult;
            }
            LoggerFactory.getLogger().error("error when request third card api,cannot deserialization the result:"+ex.getMessage());
            return apiResult;
        }
    }

}
