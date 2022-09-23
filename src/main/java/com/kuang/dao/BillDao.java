package com.kuang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BillDao extends BaseMapper<Bill> {

    @Select("select b.*, p.proName from smbms_bill b, smbms_provider p where b.providerId = p.id and b.id = #{id}")
    public Bill getBillById(Integer id);
}
