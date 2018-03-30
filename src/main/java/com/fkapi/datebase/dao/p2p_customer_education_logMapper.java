package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_customer_education_log;

public interface p2p_customer_education_logMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_customer_education_log> list);

    int insertSelective(p2p_customer_education_log record);

    p2p_customer_education_log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_customer_education_log record);

    int updateByPrimaryKey(p2p_customer_education_log record);
}