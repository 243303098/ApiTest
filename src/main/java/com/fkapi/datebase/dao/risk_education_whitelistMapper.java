package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.risk_education_whitelist;

public interface risk_education_whitelistMapper {
    int deleteByCustId(Long custId);

    int insert(risk_education_whitelist record);

}