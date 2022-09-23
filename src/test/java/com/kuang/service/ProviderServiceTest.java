package com.kuang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.pojo.Provider;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ProviderServiceTest {
    @Autowired
    private ProviderService providerService;

    @Test
    public void testGetAllProviders() {
        providerService.list();
    }

    @Test
    public void testGetProviderById() {
        providerService.getById(2);
    }

    @Test
    public void testAddProvider() {
        Provider provider = new Provider();
        provider.setProCode("CS_00011");
        provider.setProName("测试");
        provider.setProDesc("长期合作");
        provider.setProContact("大飞飞");
        provider.setProPhone("13566667777");
        provider.setProAddress("长沙");
        provider.setProFax("010-58858787");
        provider.setCreatedBy(1);
        provider.setCreationDate(new Date());
        providerService.save(provider);
    }

    @Test
    public void testUpdateProviderById() {
        Provider provider = new Provider();
        provider.setId(18);
        provider.setProCode("CS_00011");
        provider.setProName("测试222");
        provider.setProDesc("长期合作333");
        provider.setProContact("大飞飞");
        provider.setProPhone("13566667777");
        provider.setProAddress("长沙");
        provider.setProFax("010-58858787");
        provider.setCreatedBy(1);
        provider.setCreationDate(new Date());
        providerService.updateById(provider);
    }

    @Test
    public void testDeleteProviderById() {
        providerService.removeById(18);
    }

    public void testGetProvidersByCondition_d() {
        String proCode = "BJ_GYS001";
        String proName = "北京";
        IPage<Provider> page = new Page<>(1, 5);
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(proCode), "proCode", proCode);
        queryWrapper.like(Strings.isNotEmpty(proName), "proName", proName);
        providerService.page(page, queryWrapper);
    }

    @Test
    void testGetProvidersByCondition() {
        Integer currentPage = 1;
        Integer pageSize = 5;
        Map<String, String> map1 = new HashMap<>();
        map1.put("type", "like");
        map1.put("field", "proCode");
        map1.put("value", "BJ_GYS001");

        Map<String, String> map2 = new HashMap<>();
        map2.put("type", "like");
        map2.put("field", "proName");
        map2.put("value", "北京");

        List<Map> conditions = new ArrayList<>();
        conditions.add(map1);
        conditions.add(map2);

        providerService.getProvidersByCondition(currentPage, pageSize, conditions);
    }

}
