package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_cust_top_contactor;

public interface p2p_cust_top_contactorMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_cust_top_contactor> list);

    int insertSelective(p2p_cust_top_contactor record);

    p2p_cust_top_contactor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_cust_top_contactor record);

    int updateByPrimaryKey(p2p_cust_top_contactor record);
}