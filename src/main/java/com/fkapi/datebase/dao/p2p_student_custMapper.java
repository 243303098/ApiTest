package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_student_cust;

public interface p2p_student_custMapper {
    int deleteBySchoolAndMajor(p2p_student_cust record);

    int insert(List<p2p_student_cust> list);

    int insertSelective(p2p_student_cust record);

    p2p_student_cust selectByCustId(Long custId);

    int updateByPrimaryKeySelective(p2p_student_cust record);

    int updateByPrimaryKey(p2p_student_cust record);
}