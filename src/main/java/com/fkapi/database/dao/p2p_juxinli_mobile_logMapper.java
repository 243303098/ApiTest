package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.p2p_juxinli_mobile_log;

public interface p2p_juxinli_mobile_logMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_juxinli_mobile_log> list);

    int insertSelective(p2p_juxinli_mobile_log record);

    p2p_juxinli_mobile_log selectByCustId(Long custId);

    int updateByPrimaryKeySelective(p2p_juxinli_mobile_log record);

    int updateByPrimaryKey(p2p_juxinli_mobile_log record);
}