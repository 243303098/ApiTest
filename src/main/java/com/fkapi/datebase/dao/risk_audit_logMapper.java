package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.risk_audit_log;

import java.util.List;

public interface risk_audit_logMapper {
    int deleteByCustId(Long custId);

    int insert(risk_audit_log record);

    int insertSelective(risk_audit_log record);

    List<risk_audit_log> selectByCustId(risk_audit_log ral);

    int updateByPrimaryKeySelective(risk_audit_log record);

    int updateByPrimaryKey(risk_audit_log record);
}