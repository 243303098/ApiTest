package com.fkapi.database.dao;

import com.fkapi.database.domain.vcc_loan_info;

import java.util.List;

public interface vcc_loan_infoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    int deleteByCustId(Long custId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    int insert(List<vcc_loan_info> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    int insertSelective(vcc_loan_info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    List<vcc_loan_info> selectByCustId(vcc_loan_info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(vcc_loan_info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_loan_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(vcc_loan_info record);
}