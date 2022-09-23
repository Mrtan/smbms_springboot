package com.kuang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuang.dao.RoleDao;
import com.kuang.pojo.Role;
import com.kuang.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
}
