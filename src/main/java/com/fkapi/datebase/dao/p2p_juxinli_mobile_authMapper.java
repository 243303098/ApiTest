package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_juxinli_mobile_auth;

public interface p2p_juxinli_mobile_authMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_juxinli_mobile_auth> list);

    int insertSelective(p2p_juxinli_mobile_auth record);

    p2p_juxinli_mobile_auth selectByCustId(Long custId);

    int updateByCustId(p2p_juxinli_mobile_auth record);

    int updateByPrimaryKey(p2p_juxinli_mobile_auth record);
}