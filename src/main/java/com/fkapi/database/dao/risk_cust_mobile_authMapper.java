package com.fkapi.database.dao;

import com.fkapi.database.domain.risk_cust_mobile_auth;

import java.util.List;


public interface risk_cust_mobile_authMapper {
    int deleteByPrimaryKey(Long custId);

    int insert(List<risk_cust_mobile_auth> list);

    int insertSelective(risk_cust_mobile_auth record);

    risk_cust_mobile_auth selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(risk_cust_mobile_auth record);

    int updateByPrimaryKey(risk_cust_mobile_auth record);
}