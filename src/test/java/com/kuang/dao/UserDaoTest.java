package com.kuang.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.pojo.User;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void testSelectUserById() {
        userDao.selectById(1);
    }

    @Test
    void testInsertUser() {
        User user = new User();
        user.setUserCode("test");
        user.setUserName("测试");
        user.setUserPassword("1234567");
        user.setGender(1);
        user.setBirthday(new Date());
        user.setPhone("15800731945");
        user.setAddress("长沙市");
        user.setUserRole(3);
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        userDao.insert(user);
    }

    @Test
    void testUpdateUserById() {
        User user = new User();
        user.setId(20);
        user.setUserCode("test111");
        user.setUserName("测试222");
        user.setUserPassword("1234567");
        user.setGender(1);
        user.setBirthday(new Date());
        user.setPhone("15800731945");
        user.setAddress("长沙市eeee");
        user.setUserRole(3);
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        userDao.updateById(user);
    }

    @Test
    void testDeleteUserById() {
        userDao.deleteById(20);
    }

    @Test
    void testGetAllUsers() {
        userDao.selectList(null);
    }

    @Test
    void testGetPageUsers() {
        Page<User> page = new Page<>(1, 5);
        userDao.selectPage(page, null);
    }

    @Test
    void testGetUsersByCondition() {
        String userName = "李";
        String userRole = "2";
        IPage<User> page = new Page<>(1, 5);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(userName), User::getUserName, userName);
        lambdaQueryWrapper.eq(!"0".equals(userRole), User::getUserRole, userRole);
        userDao.selectPage(page, lambdaQueryWrapper);
    }

    @Test
    public void test() {
        userDao.getUserById(1);
    }

    @Test
    public void testGetUserByCode() {
        userDao.getUserByCode("admin");
    }
}
