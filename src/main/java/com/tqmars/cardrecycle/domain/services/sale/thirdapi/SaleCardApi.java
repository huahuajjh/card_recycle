package com.tqmars.cardrecycle.domain.services.sale.thirdapi;

import com.tqmars.cardrecycle.application.net.HttpClientTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Des;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;

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
        map.put("NotifyUrl",notifyUrl);
        map.put("MerchId",merchId);
        map.put("CardCode",cardCode);
        map.put("Par",par);
        map.put("CardNo",cardNo);
        map.put("CardKey",cardKey);
        map.put("MerchOrderNo",merchOrderNo);
        map.put("Sign",sign);

        String result = HttpClientTool.get(url,map);

        ApiResult apiResult = Serialization.toObject(result,ApiResult.class);

        return apiResult;

    }

}
