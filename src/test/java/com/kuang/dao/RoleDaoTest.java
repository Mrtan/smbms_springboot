package com.kuang.dao;

import com.kuang.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    void testSelectRoleById() {
        roleDao.selectById(1);
    }

    @Test
    void testInsertRole() {
        Role role = new Role();
        role.setRoleCode("SMBMS_TEST");
        role.setRoleName("系统测试员");
        role.setCreatedBy(1);
        role.setCreationDate(new Date());
        roleDao.insert(role);
    }

    @Test
    void testUpdateRoleById() {
        Role role = new Role();
        role.setId(4);
        role.setRoleCode("SMBMS_TEST...");
        role.setRoleName("系统测试员...");
        role.setCreatedBy(1);
        role.setCreationDate(new Date());
        roleDao.updateById(role);
    }

    @Test
    void testDeleteRoleById() {
        roleDao.deleteById(4);
    }

    @Test
    void testGetAllRoles() {
        roleDao.selectList(null);
    }
}
