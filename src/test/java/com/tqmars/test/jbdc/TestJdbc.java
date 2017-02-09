package com.tqmars.test.jbdc;

import com.tqmars.cardrecycle.infrastructure.jdbc.repository.DbContext;
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

//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("select * from tb_user");
//
//        while (rs.next()){
//            System.out.println(rs.getString("name"));
//            System.out.println(rs.getString("id"));
//            System.out.println(rs.getString("account"));
//            System.out.println(rs.getString("pwd"));
//            System.out.println(rs.getString("token"));
//        }

    }

    @Test
    public void testTransaction(){
        DbContext.getInstance().startTransaction();
    }
}
