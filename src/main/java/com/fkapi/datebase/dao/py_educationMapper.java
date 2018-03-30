package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.py_education;

public interface py_educationMapper {
    int deleteByCustId(Long custId);

    int insert(List<py_education> list);

    int insertSelective(py_education record);

    py_education selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(py_education record);

    int updateByPrimaryKey(py_education record);
}