package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_account_bankcard_log;

public interface p2p_account_bankcard_logMapper {
    int deleteByAcctNoAndCardNo(List<p2p_account_bankcard_log> list);

    int insert(List<p2p_account_bankcard_log> list);

    int insertSelective(p2p_account_bankcard_log record);

    p2p_account_bankcard_log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(p2p_account_bankcard_log record);

    int updateByPrimaryKey(p2p_account_bankcard_log record);
}