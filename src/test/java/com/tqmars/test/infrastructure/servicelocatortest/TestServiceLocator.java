package com.tqmars.test.infrastructure.servicelocatortest;

import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import com.tqmars.test.testspring.A;
import org.junit.Test;

/**
 * Created by jjh on 1/10/17.
 */
public class TestServiceLocator {
    @Test
    public void testGetBead()
    {
        A a = ServiceLocator.getInstance().getService("a", A.class);
        System.out.println(a.hi());
    }
}
