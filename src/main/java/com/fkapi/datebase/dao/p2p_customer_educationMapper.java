package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_customer_education;

public interface p2p_customer_educationMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_customer_education> list);

    int insertSelective(p2p_customer_education record);

    p2p_customer_education selectByCustId(Long custId);

    int updateByCustId(p2p_customer_education record);

    int updateByPrimaryKey(p2p_customer_education record);
}