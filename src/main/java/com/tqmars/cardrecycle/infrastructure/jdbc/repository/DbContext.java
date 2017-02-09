package com.tqmars.cardrecycle.infrastructure.jdbc.repository;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;

import java.sql.*;

/**
 * Created by jjh on 2/9/17.
 */
public class DbContext {
    private String url = "jdbc:mysql://localhost:3306/card_recycle";
    private String username = "root";
    private String pwd = "123";
    private static DbContext INSTANCE = new DbContext();
    private static ThreadLocal<Connection> CONN = new ThreadLocal<>();

    private DbContext(){}

    public static DbContext getInstance(){
        if(null == INSTANCE){
            return new DbContext();
        }else{
            return INSTANCE;
        }
    }

    public Connection getConnection(){
        try {
            if(null == CONN.get()){
                CONN.set(DriverManager.getConnection(url,username,pwd));
                return CONN.get();
            }
            return CONN.get();
        } catch (SQLException e) {
            LoggerFactory.getLogger().error("connection loading err:",e);
            e.printStackTrace();
            throw new RuntimeException("get connection err");
        }
    }

    public void close(){
        try {
            getConnection().close();
            statement().close();
        } catch (SQLException e) {
            LoggerFactory.getLogger().error("dbcontext err",e);
            e.printStackTrace();
        }
    }

    private Statement statement(){
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            LoggerFactory.getLogger().error("dbcontext err",e);
            e.printStackTrace();
            throw new RuntimeException("execute statement fail");
        }
    }

    public void startTransaction(){
        try {
            Statement st = getConnection().createStatement();

            boolean r = st.execute("select version()");
            if(!r){
                throw new RuntimeException("start transaction fail");
            }
        } catch (SQLException e) {
            LoggerFactory.getLogger().error("dbcontext err",e);
            e.printStackTrace();
        }
    }

    public void commit(){
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            LoggerFactory.getLogger().error("dbcontext err",e);
            throw new RuntimeException("commit transaction fail");
        }
    }

    public void rollback(){
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            LoggerFactory.getLogger().error("dbcontext err",e);
            throw new RuntimeException("rollback fail");
        }
    }


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LoggerFactory.getLogger().error("jdbc loading err:",e);
            e.printStackTrace();
        }

    }


}
