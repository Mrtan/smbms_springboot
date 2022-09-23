package com.kuang.service;

import com.kuang.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void testGetAllRoles() {
        roleService.list();
    }

    @Test
    void testGetRoleById() {
        roleService.getById(1);
    }

    @Test
    void testAddRole() {
        Role role = new Role();
        role.setRoleCode("SMBMS_TEST");
        role.setRoleName("系统测试员");
        role.setCreatedBy(1);
        role.setCreationDate(new Date());
        roleService.save(role);
    }

    @Test
    void testUpdateRoleById() {
        Role role = new Role();
        role.setId(5);
        role.setRoleCode("SMBMS_TEST...");
        role.setRoleName("系统测试员...");
        role.setCreatedBy(1);
        role.setCreationDate(new Date());
        roleService.updateById(role);
    }

    @Test
    void testDeleteRoleById() {
        roleService.removeById(5);
    }
}
