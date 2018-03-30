package com.fkapi.datebase.dao;

import java.util.List;
import com.fkapi.datebase.domain.p2p_repay_plan;

public interface p2p_repay_planMapper {
    int deleteByCustID(Long custId);

    int insert(List<p2p_repay_plan> prpList);
    
    int insert_10(List<p2p_repay_plan> prpList);
    
    int insertSelective(p2p_repay_plan record);

    p2p_repay_plan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(p2p_repay_plan record);

    int updateByPrimaryKey(p2p_repay_plan record);
}