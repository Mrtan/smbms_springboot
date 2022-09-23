package com.kuang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface BillService extends IService<Bill> {
    IPage getBillByCondition(Integer currentPage, Integer pageSize, List<Map> conditions);

    Bill getBillById(Integer id);
}
