package com.tqmars.cardrecycle.webapi.controller.admin.bank;

import com.tqmars.cardrecycle.application.admin.bank.IBankAppService;
import com.tqmars.cardrecycle.application.admin.bank.dto.AddBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.ModifyBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.QueryBankWithConditionInput;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 1/16/17.
 */
@RestController
@RequestMapping(value = "/admin/AdminBankController",method = RequestMethod.POST)
public class AdminBankController extends ControllerBase {
    private IBankAppService _bankAppService;

    public AdminBankController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._bankAppService = getService("BankAppService",IBankAppService.class);
    }


    /**
     * 查询银行
     * @param url -- /admin/AdminBankController/query
     * @param condition -- QueryBankWithConditionInput -- {index,count}
     * @return BankOutput -- [{id,name}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryBankWithConditionInput _condition = Serialization.toObject(condition, QueryBankWithConditionInput.class);
        return  _bankAppService.queryBankWithCondition(_condition);
    }

    /**
     * 修改银行类型信息
     * @param url -- /admin/AdminBankController/modify
     * @param input -- {id,name}
     * @return void
     */
    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "input") String input){
        ModifyBankInput _input = Serialization.toObject(input,ModifyBankInput.class);
        _bankAppService.modifyBank(_input);
        return toSucessMsg();
    }

    /**
     * 删除指定银行信息
     * @param url -- /admin/AdminBankController/del
     * @param id
     * @return void
     */
    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "input") String id){
        _bankAppService.delBank(Integer.valueOf(id));
        return toSucessMsg();
    }

    /**
     * 新增银行类型信息
     * @param url -- /admin/AdminBankController/add
     * @param input -- {name}
     * @return void
     */
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "input") String input){
        AddBankInput _input = Serialization.toObject(input, AddBankInput.class);
        _bankAppService.addBank(_input);
        return toSucessMsg();
    }

}
