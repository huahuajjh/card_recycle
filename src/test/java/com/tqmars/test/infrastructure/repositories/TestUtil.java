package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Student;
import com.tqmars.cardrecycle.infrastructure.mybatis.repositories.DbContext;
import com.tqmars.cardrecycle.infrastructure.mybatis.util.MapToEntityTool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by jjh on 1/13/17.
 */
public class TestUtil {
    @Test
    public void testToEntity(){

    }

    @Test
    public void testToEntityList() throws InstantiationException, IllegalAccessException {
        SqlSessionFactory factory = DbContext.getInstance().getSqlSessionFactory();
        SqlSession session = factory.openSession();
        String value = "select * from student";
        List<Map<String,Object>> list = session.selectList("com.tqmars.cardrecycle.domain.entities.data.Course.getAllWithCondition",value);

        List<Student> list1 = MapToEntityTool.toEntityList(Student.class,list);

        list1.forEach(c->System.out.println(c.toString()));

    }

}
