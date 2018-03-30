package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.risk_virtual_credit_card;

import java.util.List;

public interface risk_virtual_credit_cardMapper {
    int deleteByCustId(Long custId);

    int insert(List<risk_virtual_credit_card> list);

    int insertSelective(risk_virtual_credit_card record);

    risk_virtual_credit_card selectByCustId(Long custId);

    int updateByPrimaryKeySelective(risk_virtual_credit_card record);

    int updateByPrimaryKey(risk_virtual_credit_card record);
}