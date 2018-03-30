package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_customer;

public interface p2p_customerMapper {
    int deleteByLoginName(String loginName);

    int insert(List<p2p_customer> list);

    int insertSelective(p2p_customer record);

    p2p_customer selectByLoginName(String loginName);

    int updateByLoginName(p2p_customer record);

    int updateByPrimaryKey(p2p_customer record);
}