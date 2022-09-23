package com.kuang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.Provider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProviderDao extends BaseMapper<Provider> {
}
