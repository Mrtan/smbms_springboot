package com.kuang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.pojo.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderService extends IService<Provider> {
    IPage getProvidersByCondition(Integer currentPage, Integer pageSize, List<Map> conditions);
}
