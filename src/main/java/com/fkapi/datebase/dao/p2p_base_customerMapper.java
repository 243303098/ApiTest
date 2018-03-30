package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_base_customer;

public interface p2p_base_customerMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_base_customer> list);

    int insertSelective(p2p_base_customer record);

    p2p_base_customer selectByCustId(Long custId);

    int updateByCustId(p2p_base_customer record);

    int updateByPrimaryKey(p2p_base_customer record);
}