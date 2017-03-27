package com.tqmars.test.infrastructure.email;

import org.junit.Test;

/**
 * Created by Administrator on 2017/3/15.
 */
public class TestEmail {
    @Test
    public void testSendEmail() throws Exception {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            System.out.println(e.getMessage());
        }
    }


}
