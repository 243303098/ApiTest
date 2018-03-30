package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_blacklist_store;

public interface p2p_blacklist_storeMapper {
    int deleteByCustNameOrIdNo(List<p2p_blacklist_store> list);

    int insert(List<p2p_blacklist_store> list);

    int insertSelective(p2p_blacklist_store record);

    p2p_blacklist_store selectByCustNameAndIdNo(List<p2p_blacklist_store> list);

    int updateByPrimaryKeySelective(p2p_blacklist_store record);

    int updateByPrimaryKey(p2p_blacklist_store record);
}