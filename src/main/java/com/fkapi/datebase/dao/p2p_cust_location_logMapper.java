package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.p2p_cust_location_log;

import java.util.List;

public interface p2p_cust_location_logMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_cust_location_log> list);

    int insertSelective(p2p_cust_location_log record);

    p2p_cust_location_log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_cust_location_log record);

    int updateByPrimaryKey(p2p_cust_location_log record);
}