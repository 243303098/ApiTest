package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.p2p_loan_claim_ext;

import java.util.List;

public interface p2p_loan_claim_extMapper {
    int deleteByPrimaryKey(String projectNo);

    int insert(List<p2p_loan_claim_ext> list);

    int insertSelective(p2p_loan_claim_ext record);

    p2p_loan_claim_ext selectByPrimaryKey(String projectNo);

    int updateByPrimaryKeySelective(p2p_loan_claim_ext record);

    int updateByPrimaryKey(p2p_loan_claim_ext record);
}