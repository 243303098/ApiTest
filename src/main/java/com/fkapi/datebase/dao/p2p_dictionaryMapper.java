package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.p2p_dictionary;

import java.util.List;
import java.util.Map;

public interface p2p_dictionaryMapper {
    int deleteByDictName(String dictName);

    int insert(p2p_dictionary record);

    int insertSelective(p2p_dictionary record);

    p2p_dictionary selectByDictName(String dictName);

    p2p_dictionary selectOtherDictCode(List<String> list);

    int updateByPrimaryKeySelective(p2p_dictionary record);

    int updateByPrimaryKey(p2p_dictionary record);
}