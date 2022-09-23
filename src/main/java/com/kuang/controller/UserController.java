package com.kuang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public Boolean modifyUser(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    @DeleteMapping("{id}")
    public Boolean deleteUser(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    @GetMapping
    public IPage getUsersByCondition(@RequestParam Integer currentPage, @RequestParam Integer pageSize,
                                     @RequestParam(required = false) String userName, @RequestParam(required = false) String userRole,
                                     @RequestParam(required = false) String userCode) {
        List<Map> conditions = new ArrayList<>();
        if (Strings.isNotEmpty(userName)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "like");
            map.put("field", "userName");
            map.put("value", userName);
            conditions.add(map);
        }
        if (Strings.isNotEmpty(userRole) && !"0".equals(userRole)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "equal");
            map.put("field", "userRole");
            map.put("value", userRole);
            conditions.add(map);
        }
        if (Strings.isNotEmpty(userCode)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "equal");
            map.put("field", "userCode");
            map.put("value", userCode);
            conditions.add(map);
        }
        return userService.getUsersByCondition(currentPage, pageSize, conditions);
    }
}
