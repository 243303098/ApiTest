package com.fkapi.service;

import com.fkapi.auth.createUserInfo;
import com.fkapi.auth.createVccCustomer;
import com.fkapi.database.dao.vcc_user_card_mapMapper;
import com.fkapi.database.domain.vcc_user_card_map;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
public class vcc_user_card_mapService {
    String excelPath = System.getProperty("user.dir") + "\\testcase.xlsx";
    vcc_user_card_mapMapper vucmMapper ;
    createUserInfo cui ;
    vcc_customerService vcService ;
    createVccCustomer cvc = new createVccCustomer();

    public void addVccUserCardMap(Map<String, String> userinfoMap, JSONObject json, Boolean isDelMobileSign, SqlSession session, SqlSession vccSession){
        if (isDelMobileSign || new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign").equals("888888888")){
            vcService.delVccCustomerByMobileSign(new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign"), vccSession);
        }
        vucmMapper = vccSession.getMapper(vcc_user_card_mapMapper.class);
        cui = new createUserInfo();
        vcc_user_card_map vucm = new vcc_user_card_map();
        List<vcc_user_card_map> list ;
        delVccUserCardMap(new JSONObject(userinfoMap.get("certAuth")).getString("cardNo"), vccSession);
        for (int i = 0; i < json.getInt("allCount"); i++) {
            JSONArray jsonArray = json.getJSONArray("orders");
            JSONObject orderStatusJson = jsonArray.getJSONObject(i);
            list = new ArrayList<>();
            Map<String, String> map = cui.create(ExcelUtils.getRowNoByValue(
                    excelPath, "userInfo", orderStatusJson.getString("userinfoNo")), false, session);
            cvc.create(map, false, vccSession);
            vucm.setCustId(Long.valueOf(map.get("custId")));
            vucm.setCardNo(new JSONObject(map.get("certAuth")).getString("cardNo"));
            vucm.setRelativeTime(CommonUtils.subMin(CommonUtils.subDay(CommonUtils.getCurDate("second"),orderStatusJson.getInt("time")), 10));
            vucm.setCreateTime(CommonUtils.subMin(CommonUtils.subDay(CommonUtils.getCurDate("second"),orderStatusJson.getInt("time")), 10));
            vucm.setUpdateTime(CommonUtils.subMin(CommonUtils.subDay(CommonUtils.getCurDate("second"),orderStatusJson.getInt("time")), 10));
            list.add(vucm);
            try {
                vucmMapper.insert(list);
                Reporter.log("添加custId为： " + map.get("custId") + "的vcc_user_card_map表中的数据成功");
            }catch (Exception e){
                Reporter.log("添加custId为： " + map.get("custId") + "的vcc_user_card_map表中的数据时发生异常，添加失败");
            }
        }
    }

    public void addVccUserCardMap(Map<String, String> userinfoMap, SqlSession vccSession){
        delVccUserCardMap(new JSONObject(userinfoMap.get("certAuth")).getString("cardNo"), vccSession);
        vucmMapper = vccSession.getMapper(vcc_user_card_mapMapper.class);
        vcc_user_card_map vucm = new vcc_user_card_map();
        List<vcc_user_card_map> list = new ArrayList<>();
        delVccUserCardMap(new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign"), vccSession);
        vucm.setCustId(Long.valueOf(userinfoMap.get("custId")));
        vucm.setCardNo(new JSONObject(userinfoMap.get("certAuth")).getString("cardNo"));
        vucm.setRelativeTime(CommonUtils.getCurDate("second"));
        vucm.setCreateTime(CommonUtils.getCurDate("second"));
        vucm.setUpdateTime(CommonUtils.getCurDate("second"));
        list.add(vucm);
        try {
            vucmMapper.insert(list);
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的vcc_user_card_map表中的数据成功");
        }catch (Exception e){
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的vcc_user_card_map表中的数据时发生异常，添加失败");
        }
    }

    public void delVccUserCardMap(String cardNo, SqlSession vccSession){
        vucmMapper = vccSession.getMapper(vcc_user_card_mapMapper.class);
        try{
            vucmMapper.deleteByCardNo(cardNo);
        }catch (Exception e){
            Reporter.log("删除cardNo为： " + cardNo + "的vcc_cust_biz表中的数据时发生异常，删除失败");
        }
    }

    @Test
    public void test(){
        SqlSession vccSession = VCCMybatisUtils.getFactory().openSession(true);
        delVccUserCardMap("6217001000000000211", vccSession);
    }
}
