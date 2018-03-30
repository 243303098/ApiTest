package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_customer_company;

public interface p2p_customer_companyMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_customer_company> list);

    int insertSelective(p2p_customer_company record);

    p2p_customer_company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_customer_company record);

    int updateByPrimaryKey(p2p_customer_company record);
}