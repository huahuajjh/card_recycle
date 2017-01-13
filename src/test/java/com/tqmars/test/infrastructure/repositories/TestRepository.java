package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Course;
import com.tqmars.cardrecycle.domain.repositories.IRepositoryOfIntPrimaryKey;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jjh on 1/11/17.
 */
public class TestRepository extends TestCase{

    public void testSelect(){
        IRepositoryOfIntPrimaryKey<Course> repository = ServiceLocator.getInstance().getService("Repository", IRepositoryOfIntPrimaryKey.class);
        repository.setEntityClass(Course.class);


        List<Course> list = null;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("value","select * from course");
//        list = repository.getAllWithCondition(map);
//        for (Course course : list) {
//            System.out.println(course.getName());
//        }
    }

}
