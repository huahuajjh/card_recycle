package com.tqmars.cardrecycle.application.admin.user;

import com.tqmars.cardrecycle.application.admin.user.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;
import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.repositories.IAdminRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

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
    public boolean changePwd(ChangePwdInput input) throws ApplicationServiceException {
        Admin admin = _adminRepository.get(input.getId());
        if(admin == null){
            return false;
        }
        admin.changePwd(input.getOldPwd(),input.getNewPwd());
        _adminRepository.update(admin);
        _adminRepository.commit();
        return true;
    }

    @Override
    public void logout(LogoutInput input) {
        _adminRepository.logout(AutoMapper.mapping(Admin.class,input));
        _adminRepository.commit();
    }

    @Override
    public void createUser(CreateUserInput input) {
        _adminRepository.insert(AutoMapper.mapping(Admin.class, input));
        _adminRepository.commit();
    }

    @Override
    public void delUser(Integer id) {

    }

    @Override
    public void modifyUser(ModifyUserInput input) {

    }

    @Override
    public List<QueryUserListOutput> queryUserList(QueryUserListInput input) {
        return null;
    }
}
