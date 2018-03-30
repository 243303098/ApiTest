package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.jxl_phone_call_records;

import java.util.List;

public interface jxl_phone_call_recordsMapper {
    int deleteByPrimaryId(Long primaryId);

    int insertVA_F008(List<jxl_phone_call_records> list);

    int insertSelective(jxl_phone_call_records record);

    jxl_phone_call_records selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(jxl_phone_call_records record);

    int updateByPrimaryKey(jxl_phone_call_records record);
}