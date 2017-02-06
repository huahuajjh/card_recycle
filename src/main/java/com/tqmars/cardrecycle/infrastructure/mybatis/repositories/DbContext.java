package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jjh on 1/11/17.
 */
public class DbContext {
    private static final String CONFIG_FILE = "conf/mybatis-config.xml";
    private static SqlSessionFactory FACTORY = null;
    private static DbContext ourInstance = new DbContext();
    private static ThreadLocal<SqlSession> sessionHolder = new ThreadLocal<SqlSession>();

    public static DbContext getInstance() {
        return ourInstance;
    }

    private DbContext() {
    }

    static {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(CONFIG_FILE);
            FACTORY = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            LoggerFactory.getLogger().error("DbContext:static:init mybatis error:" + e.getMessage());
            try {
                in.close();
            } catch (IOException ex) {
                LoggerFactory.getLogger().error("DbContext:static:init io error:" + ex.getMessage());
            }
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LoggerFactory.getLogger().error("DbContext:static:init io error:" + e.getMessage());
            }
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }

    public SqlSession getSession()
    {
        if(sessionHolder.get() == null)
        {
            LoggerFactory.getLogger().info(DbContext.class.getName()+"-init SqlSession");
            sessionHolder.set(FACTORY.openSession());
            sessionHolder.get();
        }
        return sessionHolder.get();
    }

    void commitSession(){
        if(sessionHolder.get() == null)
        {
            return;
        }

        sessionHolder.get().commit();
        sessionHolder.get().close();
        sessionHolder.set(null);
        LoggerFactory.getLogger().info(DbContext.class.getName()+"-session closed");
    }

    void rollbackSession() {
        if (sessionHolder.get() == null) {
            return;
        }
        sessionHolder.get().rollback(true);
        sessionHolder.get().close();
        sessionHolder.set(null);
        LoggerFactory.getLogger().info(DbContext.class.getName()+"-session rollback and closed");
    }

    void closeSession()
    {
        if (sessionHolder.get() == null) {
            return;
        }
        sessionHolder.get().close();
        sessionHolder.set(null);
        LoggerFactory.getLogger().info(DbContext.class.getName()+"-session closed");
    }

}
