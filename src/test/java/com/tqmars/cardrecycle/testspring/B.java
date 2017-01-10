package com.tqmars.cardrecycle.testspring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jjh on 1/10/17.
 */
public class B {
    ApplicationContext context;

    public B()
    {
        context = new ClassPathXmlApplicationContext("conf/spring.xml");
    }

    private A a;

    @Test
    public void testBead()
    {
        A a = context.getBean("a",A.class);
        System.out.println(a.hi());

    }
}
