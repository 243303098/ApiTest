package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.ex_colleges;

public interface ex_collegesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ex_colleges record);

    int insertSelective(ex_colleges record);

    ex_colleges selectByCollegeName(String collegeName);

    int updateByPrimaryKeySelective(ex_colleges record);

    int updateByPrimaryKey(ex_colleges record);
}