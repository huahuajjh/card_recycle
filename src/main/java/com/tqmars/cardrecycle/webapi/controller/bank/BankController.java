package com.tqmars.cardrecycle.webapi.controller.bank;

import com.tqmars.cardrecycle.application.bank.IBankAppservice;
import com.tqmars.cardrecycle.application.bank.dto.AddBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.ModifyBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.QueryBankAccountOutput;
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
 * Created by jjh on 1/22/17.
 */
@RestController
@RequestMapping(value = "/bankAccount",method = RequestMethod.POST)
public class BankController extends ControllerBase {
    private IBankAppservice _bankAppService;

    public BankController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._bankAppService = getService("BankAccountAppService",IBankAppservice.class);
    }

    /**
     * 新增银行账户
     * @param url -- /bankAccount/add
     * @param bankAcc -- AddBankAccountInput -- {bankName(银行名称),name(用户姓名),cardNum(银行卡号),userId(用户id),bankId(银行类型id)}
     * @return void
     */
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "bankAcc") String bankAcc){
        AddBankAccountInput input = Serialization.toObject(bankAcc, AddBankAccountInput.class);
        _bankAppService.addBankAccount(input);
        return toSucessMsg();

    }

    /**
     * 修改银行账户信息
     * @param url -- /bankAccount/modify
     * @param bankAcc -- ModifyBankAccountInput -- {id,bankId(银行类型id),cardNum(银行帐号),name(姓名),bankName(银行名称)}
     * @return void
     */
    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "bankAcc") String bankAcc){
        ModifyBankAccountInput input = Serialization.toObject(bankAcc, ModifyBankAccountInput.class);
        _bankAppService.modifyBankAccount(input);
        return toSucessMsg();
    }

    /**
     * 删除银行账户
     * @param url -- /bankAccount/del
     * @param id -- id
     * @return void
     */
    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "id") String id){
        _bankAppService.delBankAccount(Integer.valueOf(id));
        return toSucessMsg();
    }

    /**
     * 查询银行类型列表
     * @param url -- /bankAccount/queryBankAccount
     * @return List<QueryBankAccountOutput> -- [{id,cardNum,name,userId,bankName,bankId}]
     */
    @RequestMapping(value = "/queryBankAccount")
    public String queryBankAccount(){
        List<QueryBankAccountOutput> list = _bankAppService.queryBankAccount();
        return toJsonWithFormatter(list,"success", Code.SUCCESS);
    }

    /**
     * 根据id查询银行账户信息
     * @param url -- /bankAccount/queryBankAccount/id
     * @param id -- id
     * @return QueryBankAccountOutput -- {id,cardNum,name,userId,bankName,bankId}
     */
    @RequestMapping(value = "/queryBankAccount/id")
    public String queryBankAccountById(@RequestParam(value = "id") String id){
        QueryBankAccountOutput r = _bankAppService.queryBankAccountById(Integer.valueOf(id));
        return toJsonWithFormatter(r,"success",Code.SUCCESS);
    }

}
