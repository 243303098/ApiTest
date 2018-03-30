package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_linkface_auth;

public interface p2p_linkface_authMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_linkface_auth> list);

    int insertSelective(p2p_linkface_auth record);

    p2p_linkface_auth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_linkface_auth record);

    int updateByPrimaryKey(p2p_linkface_auth record);
}