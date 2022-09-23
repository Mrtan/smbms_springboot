package com.kuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuang.dao.ProviderDao;
import com.kuang.pojo.Provider;
import com.kuang.pojo.User;
import com.kuang.service.ProviderService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderDao, Provider> implements ProviderService {
    @Autowired
    private ProviderDao providerDao;
    @Override
    public IPage getProvidersByCondition(Integer currentPage, Integer pageSize, List<Map> conditions) {
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
        return providerDao.selectPage(page, queryWrapper);
    }
}
