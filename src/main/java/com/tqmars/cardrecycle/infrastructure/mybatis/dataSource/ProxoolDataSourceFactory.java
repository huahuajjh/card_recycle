package com.tqmars.cardrecycle.infrastructure.mybatis.dataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jjh on 2/9/17.
 */
public class ProxoolDataSourceFactory implements DataSourceFactory{
    public ProxoolDataSourceFactory(){
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
