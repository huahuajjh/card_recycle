package com.tqmars.cardrecycle.webapi.controller.admin.card;

import com.tqmars.cardrecycle.application.admin.card.IAdminCardTypeAppService;
import com.tqmars.cardrecycle.application.admin.card.dto.*;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
@RestController
@RequestMapping(value = "/admin/card",method = RequestMethod.POST)
public class CardTypeController extends ControllerBase {
    private IAdminCardTypeAppService _adminCardTypeAppService;

    public CardTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        this._adminCardTypeAppService = getService("AdminCardTypeAppService",IAdminCardTypeAppService.class);
    }

    /**
     * 查询充值卡类型列表
     * @param url -- /admin/card/query?condition=xxx
     * @param condition -- 查询条件,json,
     *     condition={index(页码),count(叶容量)}
     * @return QueryCardTypeAndItemOutput -- {id,name(卡类型名称),cardCode(卡代码),saleRatio(卡寄售比例),supportAmount(支持面值-10,11,12)}
     */
    @RequestMapping(value = "/query")
    public String query(){
        List<QueryCardTypeOutput> list = _adminCardTypeAppService.queryCardType();
        return toJsonWithFormatter(list,"success", Code.SUCCESS);
    }

    /**
     * 新增充值卡类信息
     * @param url -- /admin/card/add
     * @param input -- AddCardTypeInput -- {name(充值卡类型名称),cardCode(充值卡类型代码),saleRatio(寄售比例)}
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "input") String input){
        AddCardTypeInput card = Serialization.toObject(input, AddCardTypeInput.class);
        try {
            _adminCardTypeAppService.addCardType(card);
            return toSucessMsg();
        }catch (RuntimeException e){
            return toFailMsg(e.getMessage());
        }

    }

    /**
     * @param url -- /admin/card/modify
     * 更新充值卡类型
     * @param input -- ModifyCardTypeInput -- {id(id),name(充值卡类型名称),cardCode(充值卡类型代码),saleRatio(寄售比例)}
     * @return void
     */
    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "input") String input){
        ModifyCardTypeInput card = Serialization.toObject(input, ModifyCardTypeInput.class);
        try {
            _adminCardTypeAppService.modifyCardType(card);
            return toSucessMsg();
        }catch (RuntimeException e){
            return toFailMsg(e.getMessage());
        }
    }

    /**
     * 删除充值卡类型
     * @param url -- /admin/card/del
     * @param id -- 充值卡类型id
     * @return void
     */
    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "id") String id){
        int _id = Integer.valueOf(id);
        _adminCardTypeAppService.delCardType(_id);
        return toSucessMsg();
    }

    /**
     * 新增充值卡子项
     * @param url -- /admin/card/cardItem/add
     * @param input -- AddCardTypeItemInput -- {cardTypeId(所属充值卡类型id),supportAmount(支持面值)}
     * @return void
     */
    @RequestMapping(value = "/cardItem/add")
    public String addCardItem(@RequestParam(value = "input") String input){
        AddCardTypeItemInput item = Serialization.toObject(input, AddCardTypeItemInput.class);
        _adminCardTypeAppService.addCardTypeItem(item);
        return toSucessMsg();
    }

    /**
     * 更新充值卡子项
     * @param url -- /admin/card/cardItem/modify
     * @param input -- ModifyCardTypeItemInput -- {id(id),cardTypeId(所属充值卡类型id),supportAmount(支持面值)}
     * @return void
     */
    @RequestMapping(value = "/cardItem/modify")
    public String modifyCardItem(@RequestParam(value = "input") String input){
        ModifyCardTypeItemInput cardTypeItem = Serialization.toObject(input, ModifyCardTypeItemInput.class);
        _adminCardTypeAppService.modifyCardTypeItem(cardTypeItem);
        return toSucessMsg();
    }

    /**
     * 删除充值卡子项
     * @param url -- /admin/card/cardItem/del
     * @param id -- 充值卡子项所属充值卡卡id
     * @return void
     */
    @RequestMapping(value = "/cardItem/del")
    public String delCardItem(@RequestParam(value = "id") String id){
        _adminCardTypeAppService.delCardTypeItem(Integer.valueOf(id));
        return toSucessMsg();
    }

    /**
     * 根据卡类型id查询卡item集合
     * @param url -- /admin/card/cardItem/query
     * @param cardTypeId -- 充值卡子项所属充值卡卡id
     * @return List<QueryCardTypeItemOutput> -- [{id(id),cardTypeId(所属充值卡类型id),supportAmount(支持面值)}]
     */
    @RequestMapping(value = "/cardItem/query")
    public String queryCardItem(@RequestParam(value = "cardTypeId") String cardTypeId){
        List<QueryCardTypeItemOutput> list = _adminCardTypeAppService.queryCardTypeItem(Integer.valueOf(cardTypeId));
        return toJsonWithFormatter(list,"success",Code.SUCCESS);
    }

}
