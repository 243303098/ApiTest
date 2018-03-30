package com.fkapi.database.dao;

import java.util.List;

import com.fkapi.database.domain.p2p_account_check;
import com.fkapi.database.domain.p2p_account_checkKey;

public interface p2p_account_checkMapper {
    int deleteByAcctNo(String acctNo);

    int insert(List<p2p_account_check> list);

    int insertSelective(p2p_account_check record);

    p2p_account_check selectByPrimaryKey(p2p_account_checkKey key);

    int updateByPrimaryKeySelective(p2p_account_check record);

    int updateByPrimaryKey(p2p_account_check record);
}