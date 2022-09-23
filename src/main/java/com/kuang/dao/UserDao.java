package com.kuang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select u.*, r.roleName from smbms_user u, smbms_role r where u.userRole = r.id and u.id = #{id}")
    public User getUserById(Integer id);

    @Select("select u.* from smbms_user u, smbms_role r where u.userCode = #{userCode} and u.userRole = r.id")
    User getUserByCode(String userCode);
}
