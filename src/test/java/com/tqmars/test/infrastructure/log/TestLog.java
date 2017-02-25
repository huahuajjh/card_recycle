package com.tqmars.test.infrastructure.log;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jjh on 1/10/17.
 */
public class TestLog {
    @Test
    public void testLog() throws Exception {
        LoggerFactory.getLogger().error("log done");
    }
    
}

