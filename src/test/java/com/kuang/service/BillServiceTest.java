package com.kuang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.pojo.Bill;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class BillServiceTest {
    @Autowired
    private BillService billService;

    @Test
    public void testGetAllBills() {
        billService.list();
    }

    @Test
    public void testGetBillById() {
        billService.getById(2);
    }

    @Test
    public void testAddBill() {
        Bill bill = new Bill();
        bill.setBillCode("test");
        bill.setProductName("测试");
        bill.setProductDesc("日用品");
        bill.setProductUnit("箱");
        bill.setProductCount(100);
        bill.setTotalPrice(1000.00);
        bill.setIsPayment(2);
        bill.setCreatedBy(1);
        bill.setCreationDate(new Date());
        bill.setProviderId(13);
        billService.save(bill);
    }

    @Test
    public void testUpdateBillById() {
        Bill bill = new Bill();
        bill.setId(23);
        bill.setBillCode("test111");
        bill.setProductName("测试22");
        bill.setProductDesc("日用品");
        bill.setProductUnit("箱");
        bill.setProductCount(100);
        bill.setTotalPrice(1000.00);
        bill.setIsPayment(2);
        bill.setCreatedBy(1);
        bill.setCreationDate(new Date());
        bill.setProviderId(13);
        billService.updateById(bill);
    }

    @Test
    public void testDeleteBillById() {
        billService.removeById(23);
    }

    public void testGetBillsByCondition_d() {
        String productName = "牛奶";
        String providerId = "13";
        String isPayment = "2";
        IPage<Bill> page = new Page<>(1, 5);
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(productName), "productName", productName);
        queryWrapper.eq(Strings.isNotEmpty(providerId), "providerId", providerId);
        queryWrapper.eq(Strings.isNotEmpty(isPayment), "isPayment", isPayment);
        billService.page(page, queryWrapper);
    }

    @Test
    void testGetBillsByCondition() {
        Integer currentPage = 1;
        Integer pageSize = 5;
        Map<String, String> map1 = new HashMap<>();
        map1.put("type", "like");
        map1.put("field", "productName");
        map1.put("value", "牛奶");

        Map<String, String> map2 = new HashMap<>();
        map2.put("type", "equal");
        map2.put("field", "providerId");
        map2.put("value", "13");

        Map<String, String> map3 = new HashMap<>();
        map3.put("type", "equal");
        map3.put("field", "isPayment");
        map3.put("value", "2");

        List<Map> conditions = new ArrayList<>();
        conditions.add(map1);
        conditions.add(map2);

        billService.getBillByCondition(currentPage, pageSize, conditions);
    }

    @Test
    public void testGetBillById1() {
        billService.getBillById(2);
    }
}
