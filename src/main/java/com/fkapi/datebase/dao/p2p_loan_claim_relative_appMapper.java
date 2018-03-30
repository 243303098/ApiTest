package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_loan_claim_relative_app;

public interface p2p_loan_claim_relative_appMapper {
    int deleteByMobileSign(String mobileSign);

    int insert(List<p2p_loan_claim_relative_app> list);

    int insertSelective(p2p_loan_claim_relative_app record);

    p2p_loan_claim_relative_app selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_loan_claim_relative_app record);

    int updateByPrimaryKey(p2p_loan_claim_relative_app record);
}