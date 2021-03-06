package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.ex_StudentCreditInfo;

public interface ex_StudentCreditInfoMapper {
    int deleteByCustId(String custId);

    int insert(List<ex_StudentCreditInfo> list);

    int insertSelective(ex_StudentCreditInfo record);

    ex_StudentCreditInfo selectByCustId(String custId);

    int updateByCustId(ex_StudentCreditInfo record);

}