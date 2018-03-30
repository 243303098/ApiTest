package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.p2p_cust_account;

public interface p2p_cust_accountMapper {
    int deleteByAcctNo(String cardNo);

    int insert(List<p2p_cust_account> list);

    int insertSelective(p2p_cust_account record);

    p2p_cust_account selectByCustId(Long custId);

    int updateByPrimaryKeySelective(p2p_cust_account record);

    int updateByPrimaryKey(p2p_cust_account record);
}