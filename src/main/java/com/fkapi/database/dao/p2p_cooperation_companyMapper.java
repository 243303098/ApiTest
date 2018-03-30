package com.fkapi.database.dao;

import com.fkapi.database.domain.p2p_cooperation_company;

import java.util.List;

public interface p2p_cooperation_companyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    int deleteByCompanyName(String companyName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    int insert(List<p2p_cooperation_company> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    int insertSelective(p2p_cooperation_company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    p2p_cooperation_company selectByCompanyId(Long companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    int updateByCompanyId(p2p_cooperation_company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p2p_cooperation_company
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(p2p_cooperation_company record);
}