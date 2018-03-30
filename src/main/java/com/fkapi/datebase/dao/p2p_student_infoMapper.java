package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_student_info;

public interface p2p_student_infoMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_student_info> list);

    int insertSelective(p2p_student_info record);

    p2p_student_info selectByCustId(Long id);

    int updateByCustId(p2p_student_info record);
}