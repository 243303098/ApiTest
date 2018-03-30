package com.fkapi.database.dao;

import com.fkapi.database.domain.risk_audit_item_log;

import java.util.List;

public interface risk_audit_item_logMapper {
    int deleteByCustId(Long custId);

    List<risk_audit_item_log> selectByCustIdAndItem(risk_audit_item_log rail);

}