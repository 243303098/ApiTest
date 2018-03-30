package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.p2p_nw_blacklist;

public interface p2p_nw_blacklistMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_nw_blacklist> list);

    int insertSelective(p2p_nw_blacklist record);

    p2p_nw_blacklist selectByCustId(Long custId);

    int updateByPrimaryKeySelective(p2p_nw_blacklist record);

    int updateByPrimaryKey(p2p_nw_blacklist record);
}