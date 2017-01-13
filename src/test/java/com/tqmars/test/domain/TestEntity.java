package com.tqmars.test.domain;

import com.tqmars.cardrecycle.domain.entities.data.Course;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by jjh on 1/13/17.
 */
public class TestEntity {
    private IRepository<Course, Integer> _courseRepository;

    @Before
    public void reposirotyInit() {
        _courseRepository = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(Course.class);
    }

    @Test
    public void testTime(){
        Course c = _courseRepository.get(18);
        System.out.println(c.getTime());
    }

    @Test
    public void testInsert(){
        Course c = new Course();
        c.setName("ls");
        c.setTime(Date.valueOf("2017-01-13"));
        c.setAmount(new BigDecimal("12.12"));

        for (int i = 0; i <1000; i++) {
            c.setName("zs"+i);
            _courseRepository.insert(c);
        }
        _courseRepository.commit();
    }
}
