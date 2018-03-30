package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_customer_contactor;

public interface p2p_customer_contactorMapper {
	int deleteByCustId(Long custId);

    int insert(List<p2p_customer_contactor> list);

    int insertSelective(p2p_customer_contactor record);

    p2p_customer_contactor selectByCustId(Long custId);

    int updateByPrimaryKeySelective(p2p_customer_contactor record);

    int updateByPrimaryKey(p2p_customer_contactor record);
}