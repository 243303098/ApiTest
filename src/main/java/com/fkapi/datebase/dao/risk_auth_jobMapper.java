package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.risk_auth_job;

public interface risk_auth_jobMapper {
    int deleteByCustId(Long custId);

    int insert(List<risk_auth_job> list);

    int insertSelective(risk_auth_job record);

    risk_auth_job selectByCustId(Long custId);

    int updateByCustId(risk_auth_job record);

    int updateByPrimaryKey(risk_auth_job record);
}