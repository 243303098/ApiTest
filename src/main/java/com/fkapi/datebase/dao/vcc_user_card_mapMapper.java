package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.vcc_user_card_map;

import java.util.List;

public interface vcc_user_card_mapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    int deleteByCardNo(String cardNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    int insert(List<vcc_user_card_map> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    int insertSelective(vcc_user_card_map record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    vcc_user_card_map selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(vcc_user_card_map record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcc_user_card_map
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(vcc_user_card_map record);
}