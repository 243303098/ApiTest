package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.risk_data_yicai_user_info;

public interface risk_data_yicai_user_infoMapper {
    int deleteByCustId(Long custId);

    int insert(List<risk_data_yicai_user_info> list);

    int insertSelective(risk_data_yicai_user_info record);

    risk_data_yicai_user_info selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(risk_data_yicai_user_info record);

    int updateByPrimaryKey(risk_data_yicai_user_info record);
}