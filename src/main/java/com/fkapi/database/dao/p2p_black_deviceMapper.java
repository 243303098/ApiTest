package com.fkapi.database.dao;

import com.fkapi.database.domain.p2p_black_device;

public interface p2p_black_deviceMapper {
    int deleteByDviceCode(String code);

    int insert(p2p_black_device record);

    int insertSelective(p2p_black_device record);

    p2p_black_device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_black_device record);

    int updateByPrimaryKey(p2p_black_device record);
}