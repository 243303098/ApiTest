package com.fkapi.datebase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fkapi.datebase.domain.p2p_loan_claim;

public interface p2p_loan_claimMapper {
    int deleteByCustID(List<p2p_loan_claim> list);

    int insert(List<p2p_loan_claim> plcList);
    
    int insert_10(@Param("PROJECT_NO") String projectNo,@Param("CUST_ID") Long custId,@Param("BORROWER_NAME") String name);

    int insertSelective(p2p_loan_claim record);

    p2p_loan_claim selectByCustID(Long custId);

    int updateByPrimaryKeySelective(p2p_loan_claim record);

    int updateByPrimaryKey(p2p_loan_claim record);
}