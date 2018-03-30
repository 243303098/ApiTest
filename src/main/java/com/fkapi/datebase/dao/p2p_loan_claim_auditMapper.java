package com.fkapi.datebase.dao;

import java.util.List;
import java.util.Map;
import com.fkapi.datebase.domain.p2p_loan_claim_audit;

public interface p2p_loan_claim_auditMapper {
    int deleteByOperator(Long id);

    int insert(p2p_loan_claim_audit record);

    int insertSelective(p2p_loan_claim_audit record);

    List<p2p_loan_claim_audit> selectAuditReByProAndCust(p2p_loan_claim_audit record);

    int updateByPrimaryKeySelective(p2p_loan_claim_audit record);

    int updateByPrimaryKey(p2p_loan_claim_audit record);
}