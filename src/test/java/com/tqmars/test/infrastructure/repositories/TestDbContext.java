package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.infrastructure.mybatis.repositories.DbContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by jjh on 1/11/17.
 */
public class TestDbContext<T> extends HashMap<String, Integer> {
    @Test
    public void testDbcontext() {
//        SqlSession session = DbContext.getInstance().getSqlSessionFactory().openSession();
//        Repository<Course, Integer> repository = session.getMapper(Repository.class);
//        System.out.println(new RepositoryBase<Course,Integer>().setEntityClass(Course.class).getT());
    }

    @Test
    public void testMybatis() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        SqlSessionFactory factory = DbContext.getInstance().getSqlSessionFactory();
        SqlSession session = factory.openSession();
//        session.close();
//        int id = 2;
//        Course course = session.selectOne("com.tqmars.cardrecycle.domain.entities.data.Course.get",1);
//        System.out.println(course.getName());
//        String value = "select * from student where id=5";
//        List<Map<String,Object>> list = session.selectList("com.tqmars.cardrecycle.domain.entities.data.Course.getAllWithCondition",value);

//        HashMap<String,Object> c = DbContext.getInstance().getSession().selectOne("com.tqmars.cardrecycle.domain.entities.data.single",value);
//        System.out.println(c);
//        Field[] fields = clazz.getFields();

//        Class<Course> clazz = Course.class;
//        Table tb = clazz.getAnnotation(Table.class);
//        Field field = clazz.getField("id");
//        System.out.println(tb.name());
//        Column col = field.getAnnotation(Column.class);
//        System.out.println(col.name());
//        list.forEach(map->map.entrySet().forEach(
//                e->System.out.println(e.getKey())
//        ));
//        System.out.println(MapToEntityTool.toEntity(Course.class,c).getId());

        String sql = "insert into tb_admin(id,account,pwd) values(0,'a1','a')";
        int id = session.insert("com.tqmars.cardrecycle.domain.entities.data.insertAndGetId",sql);
        session.commit();
        System.out.println(id);

    }

}
