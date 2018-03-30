package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_third_account;

public interface p2p_third_accountMapper {
    int deleteByCustIdAndAcctNo(List<p2p_third_account> list);

    int insert(List<p2p_third_account> list);

    int insertSelective(p2p_third_account record);

    p2p_third_account selectByPrimaryKey(String acctNo);

    int updateByPrimaryKeySelective(p2p_third_account record);

    int updateByPrimaryKey(p2p_third_account record);
}