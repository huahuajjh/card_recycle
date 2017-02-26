package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.repositories.IAdminRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

import java.util.Random;

/**
 * Created by jjh on 1/14/17.
 */
public class AdminRepository extends RepositoryBase<Admin,Integer> implements IAdminRepository {
    public AdminRepository(DbContext _context) {
        super(_context);
    }

    @Override
    public String login(Admin admin) {
        Admin _a = single("account='"+admin.getAccount()+"' and pwd='"+admin.getPwd()+"'");
        if(_a==null){
            return null;
        }

        String token = Md5.md5WithSalt(_a.getAccount()+_a.getPwd()+new Random().nextInt(1000));
        _a.setLastLoginTime(DateTool.getInstance().getNowSqlTime());
        _a.setToken(token);
        update(_a);
        return token;
    }

    @Override
    public void logout(Admin admin) {
        Admin _a = single("token='"+admin.getToken()+"'");
        if(_a == null){
            return;
        }

        _a.setToken(null);
        update(_a);

    }

    @Override
    public boolean auth(String token){
        Admin _a = single("token='"+token+"''");
        if(null == _a.getToken() || _a.getToken().equals("")){
            return false;
        }
        else if(_a.getToken().equals(token)){
            return true;
        }
        else{
            return false;
        }
    }
}
