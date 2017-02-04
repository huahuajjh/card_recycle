package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;
import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_admin")
public class Admin extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "account")
    private String account;

    @Column(name = "pwd")
    private String pwd = "abcd123";

    @Column(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void changePwd(String oldPwd, String newPwd) throws ApplicationServiceException {
        if(this.pwd.equals(Md5.md5WithSalt(oldPwd))){
            this.pwd = Md5.md5WithSalt(newPwd);
        }else {
            throw new ApplicationServiceException("原始密码错误");
        }
    }
}
