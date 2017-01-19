package com.tqmars.cardrecycle.webapi.controller.admin.user;

import com.tqmars.cardrecycle.application.admin.user.IAdminUserAppService;
import com.tqmars.cardrecycle.application.admin.user.dto.*;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.Const;
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
@RequestMapping(value = "/admin/user",method = RequestMethod.POST)
public class AdminUserController extends ControllerBase {
    private IAdminUserAppService _adminUserAppService;

    public AdminUserController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        _adminUserAppService = getService("AdminUserAppService",IAdminUserAppService.class);
    }

    /**
     * 后台管理账户登录,返回token
     * @param url -- /admin/user/login
     * @param input -- LoginInput -- {account,pwd}
     * @return token
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "input") String input){
        LoginInput loginInput = Serialization.toObject(input, LoginInput.class);
        String token = _adminUserAppService.login(loginInput);
        getSession().setAttribute(Const.ADMIN_TOKEN,token);
        return toJsonWithFormatter(token,"success", Code.SUCCESS);
    }

    /**
     * 后台管理账户退出
     * @param url -- /admin/user/logout
     * @param token -- token
     * @return void
     */
    @RequestMapping(value = "/logout")
    public String logout(@RequestParam(value = "token") String token){
        LogoutInput in = new LogoutInput();
        in.setToken(token);
        _adminUserAppService.logout(in);

        getSession().invalidate();
        return toSucessMsg();
    }

    /**
     * 分页查询用户
     * @param url -- /admin/user/query
     * @param condition -- QueryUserListInput -- {index,count}
     * @return List<QueryUserListOutput> -- [{id,account,pwd}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryUserListInput input = Serialization.toObject(condition, QueryUserListInput.class);
        List<QueryUserListOutput> list = _adminUserAppService.queryUserList(input);
        return toJsonWithFormatter(list,"success",Code.SUCCESS);
    }

    /**
     * 更新用户数据
     * @param url -- /admin/user/modify
     * @param input -- ModifyUserInput -- {id,account,pwd}
     * @return void
     */
    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "input") String input){
        ModifyUserInput user = Serialization.toObject(input, ModifyUserInput.class);
        _adminUserAppService.modifyUser(user);
        return toSucessMsg();
    }

    /**
     * 删除用户
     * @param url -- /admin/user/del
     * @param input -- id
     * @return void
     */
    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "input") String input){
        _adminUserAppService.delUser(Integer.valueOf(input));
        return toSucessMsg();
    }

    /**
     * 新增后台管理账户
     * @param url -- /admin/user/add
     * @param input -- CreateUserInput -- {account,pwd}
     * @return void
     */
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "input") String input){
        CreateUserInput user = Serialization.toObject(input, CreateUserInput.class);
        _adminUserAppService.createUser(user);
        return toSucessMsg();
    }

}
