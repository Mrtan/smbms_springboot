package com.kuang.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.pojo.Bill;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class BillDaoTest {
    @Autowired
    private BillDao billDao;

    @Test
    public void testGetAllBills() {
        billDao.selectList(null);
    }

    @Test
    public void testGetBillById() {
        billDao.selectById(2);
    }

    @Test
    public void testGetBillById1() {
        billDao.selectById(2);
    }

    @Test
    public void testInsertBill() {
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
        billDao.insert(bill);
    }

    @Test
    public void testUpdateBillById() {
        Bill bill = new Bill();
        bill.setId(22);
        bill.setBillCode("test111");
        bill.setProductName("测试222");
        bill.setProductDesc("日用品333");
        bill.setProductUnit("箱");
        bill.setProductCount(100);
        bill.setTotalPrice(1000.00);
        bill.setIsPayment(2);
        bill.setCreatedBy(1);
        bill.setCreationDate(new Date());
        bill.setProviderId(13);
        billDao.updateById(bill);
    }

    @Test
    public void testDeleteBillById() {
        billDao.deleteById(22);
    }

    @Test
    public void testGetBillsByCondition() {
        String productName = "牛奶";
        String providerId = "13";
        String isPayment = "2";
        IPage<Bill> page = new Page<>(1, 5);
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(productName), "productName", productName);
        queryWrapper.eq(Strings.isNotEmpty(providerId), "providerId", providerId);
        queryWrapper.eq(Strings.isNotEmpty(isPayment), "isPayment", isPayment);
        billDao.selectPage(page, queryWrapper);
    }
}
