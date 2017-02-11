package com.tqmars.test.jbdc;

import com.tqmars.cardrecycle.domain.entities.data.BankAccount;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.jdbc.repository.DbContext;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jjh on 2/9/17.
 */
public class TestJdbc {
    @Test
    public void testConn() throws SQLException {
        Connection conn = DbContext.getInstance().getConnection();
//        Connection conn1 = DbContext.getInstance().getConnection();

//        Assert.assertEquals(conn,conn1);
//        System.out.print(conn.hashCode() == conn1.hashCode());

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select name from tb_bank_account");

        while (rs.next()){
            System.out.println(rs.getString("name"));
        }

    }

    @Test
    public void testTransaction(){
//        DbContext.getInstance().startTransaction();
        IRepository<BankAccount,Integer> repository = ServiceLocator.getInstance().getService("RepositoryBase",IRepository.class);
        repository.setEntityClass(BankAccount.class);
        repository.getAll().forEach(a->System.out.println(Serialization.toJson(a)));
    }
}
