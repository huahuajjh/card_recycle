package com.tqmars.cardrecycle.webapi.controller.card;

import com.tqmars.cardrecycle.application.admin.card.dto.QueryCardTypeOutput;
import com.tqmars.cardrecycle.application.card.ICardTypeAppService;
import com.tqmars.cardrecycle.application.card.dto.QueryCardItemOutput;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */

@RestController
@RequestMapping(value = "/card",method = RequestMethod.POST)
public class CardController extends ControllerBase {
    private ICardTypeAppService _cardTypeAppService;

    public CardController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        this._cardTypeAppService = getService("CardTypeAppService",ICardTypeAppService.class);
    }

    /**
     * 查询充值卡类型集合
     * @param url -- /card/query
     * @return QueryCardTypeOutput -- [{id(卡id),name(卡名称),cardCode(卡代码),saleRatio(卡寄售比例)}]
     */
    @RequestMapping(value = "/query")
    public String query(){
        List<QueryCardTypeOutput> list = _cardTypeAppService.queryCardTypeList();
        return toJsonWithFormatter(list,"success", Code.SUCCESS);
    }

    /**
     * 根据卡id查询卡item集合
     * @param url -- /card/item/query
     * @param cardId -- 卡id
     * @return QueryCardItemOutput -- [{id(item id),supportAmount(支持金额),cardTypeId(卡类型id)}]
     */
    @RequestMapping(value = "/item/query")
    public String queryItem(@RequestParam(value = "cardId") String cardId){
        List<QueryCardItemOutput> list = _cardTypeAppService.queryCardItem(Integer.valueOf(cardId));
        return toJsonWithFormatter(list,"success",Code.SUCCESS);
    }

}
