package com.tqmars.test.infrastructure.serialization;

import com.tqmars.cardrecycle.domain.entities.data.Course;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jjh on 1/11/17.
 */
public class TestSerialization {
    private IRepository<Course, Integer> _courseRepository;

    @Before
    public void reposirotyInit() {
        _courseRepository = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(Course.class);
    }

    @Test
    public void testJson()
    {
        System.out.println(Serialization.toJson(_courseRepository.get(5)));
    }

    @Test
    public void testToObj()
    {
        System.out.println(Serialization.toObject("{\"name\":\"physic1\",\"id\":5}",Course.class).getName());
    }
}
