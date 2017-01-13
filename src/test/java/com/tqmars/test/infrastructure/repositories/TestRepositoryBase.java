package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Course;
import com.tqmars.cardrecycle.domain.entities.data.Student;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

import java.beans.Expression;
import java.util.List;

/**
 * Created by jjh on 1/11/17.
 */
public class TestRepositoryBase {

    private IRepository<Course, Integer> _courseRepository;
    private IRepository<Student, Integer> _studentRepository;

    @Before
    public void reposirotyInit() {
//        _courseRepository = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(Course.class);
        _studentRepository = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(Student.class);
    }

    @Test
    public void testRepositoryBase() {
        System.out.println(_courseRepository.get(3).getName());
    }

    @Test
    public void testInsert() {
        Course course = new Course();
        course.setName("ch");
        _courseRepository.insert(course);
        _courseRepository.commit();
    }

    @Test
    public void testUpdate() {
        Course course = _courseRepository.get(5);
        course.setName("physic1");
        _courseRepository.update(course);
        _courseRepository.commit();
    }

    @Test
    public void testDelete() {
        _courseRepository.deleteById(1);
        _courseRepository.commit();
    }

    @Test
    public void testQueryByCondition() throws Exception {
//        Map<String,Object> map = new HashMap<>();
//        map.put("value","select name from course where id=9");
//        List<Course> list = _courseRepository.getAllWithCondition(map);
//
//        for (Course course : list) {
//            System.out.println(course.getName());
//        }
//        Statement s = new Statement(new Course(),"setName",new Object[]{"zs"});
        Expression s = new Expression(new Course(),"setName",new Object[]{"zs"});
        System.out.println("getMethodName--"+s.getMethodName());
        System.out.println("getTarget--"+s.getTarget());
        System.out.println("getArguments--"+s.getArguments());
        System.out.println("getValue--"+s.getValue());

    }

    @Test
    public void testGet(){
        Course c = (Course) ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(Course.class).get(5);
        System.out.println(c.getId());
    }

    @Test
    public void testSingle(){
//        Course c = _courseRepository.single("id=9");
        System.out.println(_courseRepository.single("id=9"));
    }

    @Test
    public void testGetAll(){
        List<Student> list = _studentRepository.getAll();
        list.forEach(c->System.out.println(c));
    }

    @Test
    public void testGetAllWithCondition(){
        List<Student> list = _studentRepository.getAllWithCondition("id>5");
        list.forEach(c->System.out.println(c));
    }

    @Test
    public void testInsert1(){
        Course c = new Course();
        c.setName("insert test");
        _courseRepository.insert(c);
        _courseRepository.commit();
    }

    @Test
    public void update1(){
        Course c = new Course();
        c.setName("insert");
        c.setId(5);
        _courseRepository.update(c);
        _courseRepository.commit();
    }

    @Test
    public void testDeleteById(){
        _courseRepository.deleteById(13);
        _courseRepository.commit();
    }

    @Test
    public void testCount(){
        System.out.println(_courseRepository.count());
    }

    @Test
    public void testCountByCondition(){
//        System.out.println(_courseRepository.countWithCondition("id>7"));
        System.out.println(_courseRepository.countWithCondition("name='jjh'"));
    }

    @Test
    public void testIsExists(){
        System.out.println(_courseRepository.isExists("name='jjh'"));
    }

}
