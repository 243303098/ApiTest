package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.risk_auth_job_log;

public interface risk_auth_job_logMapper {
    int deleteByCustId(Long custId);

    int insert(List<risk_auth_job_log> list);

    int insertSelective(risk_auth_job_log record);

    risk_auth_job_log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(risk_auth_job_log record);

    int updateByPrimaryKey(risk_auth_job_log record);
}