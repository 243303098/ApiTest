package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.risk_credit_yicai_tiqianhua_log;

public interface risk_credit_yicai_tiqianhua_logMapper {
    int deleteByCustId(Long id);

    int insert(risk_credit_yicai_tiqianhua_log record);

    int insertSelective(risk_credit_yicai_tiqianhua_log record);

    risk_credit_yicai_tiqianhua_log selectByCustId(Long id);

    int updateByPrimaryKeySelective(risk_credit_yicai_tiqianhua_log record);

    int updateByPrimaryKey(risk_credit_yicai_tiqianhua_log record);
}