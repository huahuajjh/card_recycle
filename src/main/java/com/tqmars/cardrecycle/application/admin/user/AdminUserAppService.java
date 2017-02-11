package com.tqmars.cardrecycle.application.admin.user;

import com.tqmars.cardrecycle.application.admin.user.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;
import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.repositories.IAdminRepository;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
public class AdminUserAppService extends BaseAppService implements IAdminUserAppService{
    IAdminRepository _adminRepository;

    public AdminUserAppService(IAdminRepository _adminRepository) {
        this._adminRepository = _adminRepository;
        this._adminRepository.setEntityClass(Admin.class);
    }

    @Override
    public String login(LoginInput input) {
        input.setPwd(Md5.md5WithSalt(input.getPwd()));
        String token = _adminRepository.login(AutoMapper.mapping(Admin.class,input));
        _adminRepository.commit();
        return token;
    }

    @Override
    public boolean changePwd(ChangePwdInput input) {
        Admin admin = _adminRepository.single("token='"+input.getToken()+"'");
        if(admin == null){
            return false;
        }
        try {
            admin.changePwd(input.getOldPwd(),input.getNewPwd());
        } catch (ApplicationServiceException e) {
            _adminRepository.rollBack();
            return false;
        }
        _adminRepository.update(admin);
        _adminRepository.commit();
        return true;
    }

    @Override
    public void logout(LogoutInput input) {
        Admin admin = _adminRepository.single("token='"+input.getToken()+"'");
        if(null == admin){
            _adminRepository.commit();
            return;
        }
        _adminRepository.logout(admin);
        _adminRepository.commit();
    }

    @Override
    public String createUser(CreateUserInput input) {
        boolean exists = _adminRepository.isExists("account='"+input.getAccount()+"'");
        if(exists){
            return toJsonWithFormatter(null,"用户名"+input.getAccount()+"已经存在",Code.ACCOUNT_EXISTS_ERR);
        }

        Admin admin = AutoMapper.mapping(Admin.class, input);
        admin.setLastLoginTime(DateTool.getInstance().getNowSqlTime());
        if(null != admin){
            admin.setPwd(Md5.md5WithSalt(input.getPwd()));
        }else
        {
            return toJsonWithFormatter(null,"创建用户失败,缺少参数",Code.FAIL);
        }

        try {
            _adminRepository.insert(admin);
            _adminRepository.commit();
            return toJsonWithFormatter(null,"success",Code.SUCCESS);
        }catch (Exception ex){
            _adminRepository.rollBack();
            return toJsonWithFormatter(null,"err",Code.FAIL);
        }
    }

    @Override
    public void delUser(Integer id) {
        _adminRepository.deleteById(id);
        _adminRepository.commit();
    }

    @Override
    public void modifyUser(ModifyUserInput input) {

    }

    @Override
    public List<QueryUserListOutput> queryUserList() {
        List<Admin> list = _adminRepository.getAll();
        _adminRepository.commit();
        return AutoMapper.mapping(QueryUserListOutput.class,list);
    }
}
