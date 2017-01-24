package com.tqmars.cardrecycle.infrastructure.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
        logger = Logger.getLogger("/conf/log4j.properties");
    }
}
