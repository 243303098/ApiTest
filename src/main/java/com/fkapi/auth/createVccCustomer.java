package com.fkapi.auth;

import com.fkapi.service.*;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
public class createVccCustomer {
    createUserInfo cui ;
    p2p_customerService pcService ;
    vcc_customerService VccCustomerService ;
    public void create(Map<String, String> userinfoMap, Boolean isDelMobileSign, SqlSession vccSession){
        cui = new createUserInfo();
        pcService = new p2p_customerService();
        VccCustomerService = new vcc_customerService();
        //添加虚拟信用卡的用户表
        VccCustomerService.addVccCustomerService(userinfoMap, vccSession);
    }
}
