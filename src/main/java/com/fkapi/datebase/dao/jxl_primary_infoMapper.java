package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.jxl_primary_info;

public interface jxl_primary_infoMapper {
    int deleteByCustId(Long custId);

    int insert(List<jxl_primary_info> list);

    int insertSelective(jxl_primary_info record);

    jxl_primary_info selectByCustId(Long custId);

    int updateByPrimaryKeySelective(jxl_primary_info record);

    int updateByPrimaryKey(jxl_primary_info record);
}