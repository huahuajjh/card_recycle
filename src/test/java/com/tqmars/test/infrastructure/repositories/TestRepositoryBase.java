package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jjh on 1/11/17.
 */
public class TestRepositoryBase {

    private IRepository<User, Integer> _userRepository;
    private IRepository<Admin, Integer> _adminRepository;

    @Before
    public void repositoryInit() {
        _userRepository = ServiceLocator.getInstance().getService("UserRepository", IRepository.class).setEntityClass(User.class);
        _adminRepository = ServiceLocator.getInstance().getService("AdminRepository", IRepository.class).setEntityClass(Admin.class);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAccount("ls");
        user.setBusinessId("a2d3b4b231");
        user.setBusinessPwd(UUID.randomUUID().toString());
        user.setPwd("huahuajjh3");
        user.setQq("7038250211");
        user.setTel("18817676232");
        user.setToken("asd");

        Admin a = new Admin();
        a.setPwd("asd");
        a.setAccount("zs");

        _userRepository.insert(user);

        User u = _userRepository.single("account='"+user.getAccount()+"' and pwd='"+user.getPwd()+"'");
        System.out.println(u.getId());
        _userRepository.commit();
    }

    @Test
    public void testInsertAndGetId(){
        Admin admin = new Admin();
        admin.setAccount("huahuajjh1");
        admin.setPwd("123");
        admin.setToken("sss");
        int id = _adminRepository.insertAndGetId(admin);
        _adminRepository.commit();
        System.out.println(id);
    }

    @Test
    public void testInsertAndReturnEntity(){
        Admin admin = new Admin();
        admin.setAccount("huahuajjh2");
        admin.setPwd("123");
        admin.setToken("sss2");
        Admin a = _adminRepository.insert(admin);
        _adminRepository.commit();
        System.out.println(a.toString());
    }

    @Test
    public void testUpdate() {
        User user = _userRepository.get(2);
        user.setToken("asdd");
        _userRepository.update(user);
        _userRepository.commit();
    }

    @Test
    public void testDelete() {
        _userRepository.deleteById(2);
        _userRepository.commit();
    }

    @Test
    public void testQueryByCondition() throws Exception {

    }

    @Test
    public void testGet() {
        User u = _userRepository.single("account='huahuajjh' and pwd='"+ Md5.md5WithSalt("huahuajjh3")+"'");
        System.out.println(u.getId());
    }

    @Test
    public void testSingle() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void testGetAllWithCondition() {
    }

    @Test
    public void testInsert1() {
    }

    @Test
    public void update1() {
    }

    @Test
    public void testDeleteById() {
    }

    @Test
    public void testCount() {
    }

    @Test
    public void testCountByCondition() {
    }

    @Test
    public void testIsExists() {
    }



}
