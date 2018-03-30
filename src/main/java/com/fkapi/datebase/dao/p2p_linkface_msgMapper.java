package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_linkface_msg;

public interface p2p_linkface_msgMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_linkface_msg> list);

    int insertSelective(p2p_linkface_msg record);

    p2p_linkface_msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_linkface_msg record);

    int updateByPrimaryKey(p2p_linkface_msg record);
}