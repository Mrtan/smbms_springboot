package com.kuang.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.pojo.Bill;
import com.kuang.pojo.Provider;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ProviderDaoTest {
    @Autowired
    private ProviderDao providerDao;

    @Test
    public void testGetAllProviders() {
        providerDao.selectList(null);
    }

    @Test
    public void testGetProviderById() {
        providerDao.selectById(13);
    }

    @Test
    public void testInsertProvider() {
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
        providerDao.insert(provider);
    }

    @Test
    public void testUpdateProviderById() {
        Provider provider = new Provider();
        provider.setId(17);
        provider.setProCode("CS_00011");
        provider.setProName("测试222");
        provider.setProDesc("长期合作333");
        provider.setProContact("大飞飞");
        provider.setProPhone("13566667777");
        provider.setProAddress("长沙");
        provider.setProFax("010-58858787");
        provider.setCreatedBy(1);
        provider.setCreationDate(new Date());
        providerDao.updateById(provider);
    }

    @Test
    public void testDeleteProviderById() {
        providerDao.deleteById(17);
    }

    @Test
    public void testGetProviderByCondition() {
        String proCode = "BJ_GYS001";
        String proName = "北京";
        IPage<Provider> page = new Page<>(1, 5);
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(proCode), "proCode", proCode);
        queryWrapper.like(Strings.isNotEmpty(proName), "proName", proName);
        providerDao.selectPage(page, queryWrapper);
    }

}
