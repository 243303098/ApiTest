package com.fkapi.database.dao;

import com.fkapi.database.domain.risk_python_score;

import java.util.List;

public interface risk_python_scoreMapper {
    int deleteByCustId(Long id);

    int insert(List<risk_python_score> list);

    risk_python_score selectByCustId(Long id);

    int updateByPrimaryKey(risk_python_score record);
}