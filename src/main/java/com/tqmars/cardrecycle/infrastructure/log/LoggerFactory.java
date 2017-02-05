package com.tqmars.cardrecycle.infrastructure.log;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;

/**
 * Created by jjh on 1/10/17.
 */
public final class LoggerFactory {
    private static Logger logger;

    private LoggerFactory(){}
    public static Logger getLogger()
    {
        return logger;
    }

    static {
        InputStream is = LogFactory.class.getResourceAsStream("/conf/log4j.properties");
        PropertyConfigurator.configure(is);
        logger = Logger.getLogger(LoggerFactory.class);
    }
}
