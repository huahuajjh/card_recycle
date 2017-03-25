package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

import java.util.Random;

/**
 * Created by jjh on 1/14/17.
 */
public class UserRepository extends RepositoryBase<User,Integer> implements IUserRepository {
    public UserRepository(DbContext _context) {
        super(_context);
    }

    @Override
    public String login(User user) {
        User _u = single("status=1 and account='"+user.getAccount()+"' and pwd='"+user.getPwd()+"'");
        if(_u==null){
            return null;
        }

        String token = Md5.md5WithSalt(_u.getAccount()+_u.getPwd()+new Random().nextInt(1000));

        _u.setLastLoginTime(DateTool.getInstance().getNowSqlTime());
        _u.setToken(token);
        update(_u);
        return token;
    }

    @Override
    public void logout(User user) {
        User _u = single("token='"+user.getToken()+"'");
        if(_u == null){
            return;
        }

        _u.setToken(null);
        update(_u);
    }

    @Override
    public boolean auth(String token) {
        User u = single("token='"+token+"'");
        if(null == u || null == u.getToken() || u.getToken().equals("")){
            return false;
        }
        return true;
    }

}
