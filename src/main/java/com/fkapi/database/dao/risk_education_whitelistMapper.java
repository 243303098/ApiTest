package com.fkapi.database.dao;

import com.fkapi.database.domain.risk_education_whitelist;

public interface risk_education_whitelistMapper {
    int deleteByCustId(Long custId);

    int insert(risk_education_whitelist record);

}