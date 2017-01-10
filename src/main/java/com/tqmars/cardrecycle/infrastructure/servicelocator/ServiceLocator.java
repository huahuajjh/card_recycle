package com.tqmars.cardrecycle.infrastructure.servicelocator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jjh on 1/10/17.
 */
public final class ServiceLocator{
    private static ServiceLocator ourInstance = new ServiceLocator();
    private static ApplicationContext springContext = new ClassPathXmlApplicationContext("conf/spring.xml");

    public static ServiceLocator getInstance() {
        return ourInstance;
    }

    private ServiceLocator() {
    }

    public <TService> TService getService(String beanId,Class<TService> service)
    {
        return springContext.getBean(beanId,service);
    }
}
