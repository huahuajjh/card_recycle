package com.tqmars.test.infrastructure.log;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.junit.Test;

/**
 * Created by jjh on 1/10/17.
 */
public class TestLog {
    @Test
    public void testLog()
    {
        LoggerFactory.getLogger().info("log done");
    }
    
}

