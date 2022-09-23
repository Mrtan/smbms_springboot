package com.kuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.dao.RoleDao;
import com.kuang.dao.UserDao;
import com.kuang.pojo.Role;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        user.setUserPassword(null);
        return user;
    }

    @Override
    public Boolean addUser(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public Boolean updateUserById(User user) {
        return userDao.updateById(user) > 0;
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        return userDao.deleteById(id) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.selectList(null);
    }

    @Override
    public IPage<User> getUsersByCondition(Integer currentPage, Integer pageSize, List<Map> conditions) {
        IPage page = new Page(currentPage, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper<User>();
        if (conditions != null) {
            for (Map condition : conditions) {
                String type = (String) condition.get("type");
                String field = (String) condition.get("field");
                String value = (String) condition.get("value");
                if ("like".equals(type)) queryWrapper.like(Strings.isNotEmpty(field), field, value);
                if ("equal".equals(type)) queryWrapper.eq(!"0".equals(field), field, value);
            }
        }
        IPage iPage = userDao.selectPage(page, queryWrapper);
        List<User> users = iPage.getRecords();
        for (User user : users) {
            user.setUserPassword(null);
        }
        return iPage;
    }

    @Override
    public User getUserByCode(String userCode) {
        return userDao.getUserByCode(userCode);
    }

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        User user = userDao.getUserByCode(userCode);
        if (user == null) {
            throw new UsernameNotFoundException("用户编号不存在");
        }
        Role role = roleDao.selectById(user.getUserRole());
        user.setRole(role);
        user.setRoleName(role.getRoleName());
        return user;
    }
}
