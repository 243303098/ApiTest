package com.fkapi.datebase.dao;

import java.util.List;
import com.fkapi.datebase.domain.p2p_account_bankcard;

public interface p2p_account_bankcardMapper {
    int deleteByAcctNoAndCardNo(List<p2p_account_bankcard> list);

    int insert(List<p2p_account_bankcard> list);

    int insertSelective(p2p_account_bankcard record);

    p2p_account_bankcard selectByAcctNo(String acctNo);

    int updateByPrimaryKeySelective(p2p_account_bankcard record);

    int updateByPrimaryKey(p2p_account_bankcard record);
}