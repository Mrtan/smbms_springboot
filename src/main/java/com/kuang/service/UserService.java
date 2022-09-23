package com.kuang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(Integer id);

    Boolean addUser(User user);

    Boolean updateUserById(User user);

    Boolean deleteUserById(Integer id);

    List<User> getAllUsers();

    IPage<User> getUsersByCondition(Integer currentPage, Integer pageSize, List<Map> conditions);

    User getUserByCode(String userCode);
}
