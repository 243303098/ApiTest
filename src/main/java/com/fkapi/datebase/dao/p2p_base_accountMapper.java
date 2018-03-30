package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_base_account;

public interface p2p_base_accountMapper {
    int deleteByAcctNo(String acctNo);

    int insert(List<p2p_base_account> list);

    int insertSelective(p2p_base_account record);

    p2p_base_account selectByPrimaryKey(String acctNo);

    int updateByPrimaryKeySelective(p2p_base_account record);

    int updateByPrimaryKey(p2p_base_account record);
}