package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.vcc_repay_plan;

import java.util.List;

public interface vcc_repay_planMapper {
    int deleteByCustId(Long id);

    int insert(List<vcc_repay_plan> list);

    int insertSelective(vcc_repay_plan record);

    vcc_repay_plan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(vcc_repay_plan record);

    int updateByPrimaryKey(vcc_repay_plan record);
}