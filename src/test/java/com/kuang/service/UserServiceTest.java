package com.kuang.service;

import com.kuang.pojo.User;
import com.kuang.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        userService.getAllUsers();
    }

    @Test
    void testGetUserById() {
        userService.getUserById(1);
    }

    @Test
    void testAddUser() {
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
        userService.addUser(user);
    }

    @Test
    void testUpdateUserById() {
        User user = new User();
        user.setId(20);
        user.setUserCode("test3343");
        user.setUserName("测试www");
        user.setUserPassword("1234567");
        user.setGender(1);
        user.setBirthday(new Date());
        user.setPhone("15800731945");
        user.setAddress("长沙市dddd");
        user.setUserRole(3);
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        userService.updateUserById(user);
    }

    @Test
    void testDeleteUserById() {
        userService.deleteUserById(20);
    }

    @Test
    void testGetUsersByCondition() {
        Integer currentPage = 1;
        Integer pageSize = 5;
        Map<String, String> map1 = new HashMap<>();
        map1.put("type", "like");
        map1.put("field", "userName");
        map1.put("value", "李");

        Map<String, String> map2 = new HashMap<>();
        map2.put("type", "equal");
        map2.put("field", "userRole");
        map2.put("value", "2");

        List<Map> conditions = new ArrayList<>();
        conditions.add(map1);
        conditions.add(map2);

        userService.getUsersByCondition(currentPage, pageSize, conditions);
    }

    @Test
    public void testGetUserByCode() {
        userService.getUserByCode("admin");
    }
}
