package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.risk_pingan_grayscale_stat;

import java.util.List;

public interface risk_pingan_grayscale_statMapper {
    int deleteByCustId(Long custId);

    int insert(List<risk_pingan_grayscale_stat> list);

    int insertSelective(risk_pingan_grayscale_stat record);

    risk_pingan_grayscale_stat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(risk_pingan_grayscale_stat record);

    int updateByPrimaryKey(risk_pingan_grayscale_stat record);
}