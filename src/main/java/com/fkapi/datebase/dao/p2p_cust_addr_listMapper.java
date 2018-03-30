package com.fkapi.datebase.dao;

import java.util.List;

import com.fkapi.datebase.domain.p2p_cust_addr_list;

public interface p2p_cust_addr_listMapper {
    int deleteByCustId(Long custId);

    int insert(List<p2p_cust_addr_list> list);

    List<p2p_cust_addr_list> selectByCustId(Long custId);

    int updateByCustId(p2p_cust_addr_list record);

}