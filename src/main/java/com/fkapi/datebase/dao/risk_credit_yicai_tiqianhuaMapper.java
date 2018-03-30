package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.risk_credit_yicai_tiqianhua;

public interface risk_credit_yicai_tiqianhuaMapper {
    int deleteByCustId(Long id);

    int insert(List<risk_credit_yicai_tiqianhua> list);

    int insertSelective(risk_credit_yicai_tiqianhua record);

    risk_credit_yicai_tiqianhua selectByCustId(Long id);

    int updateByCustId(risk_credit_yicai_tiqianhua record);

    int updateByPrimaryKey(risk_credit_yicai_tiqianhua record);
}