package com.fkapi.service;

import com.fkapi.database.dao.p2p_cust_location_logMapper;
import com.fkapi.database.domain.p2p_cust_location_log;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */
public class p2p_cust_location_logService {
    p2p_cust_location_logMapper pcllMapper ;
    p2p_customerService pcService ;
    ex_collegesService ecService ;
    p2p_cust_location_log pcll ;
    p2p_dictionaryService pdService ;

    public void addCustLocationLog(Map<String, String> userInfoMap, JSONObject json, SqlSession session, String option){
        delCustLocationLog(userInfoMap.get("oldCustId"), session);
        pcllMapper = session.getMapper(p2p_cust_location_logMapper.class);
        pcService = new p2p_customerService();
        pcll = new p2p_cust_location_log();
        ecService = new ex_collegesService();
        pdService = new p2p_dictionaryService();
        List<p2p_cust_location_log> list ;

        if(option.equals("VA_F012")){
            if(json.get("isSame").equals("Y")){
                for(int i=0;i<json.getInt("loginNum");i++){
                    list = new ArrayList<>();
                    pcll.setCustId(Long.valueOf(userInfoMap.get("custId")));
                    pcll.setCreateTime(CommonUtils.getCurDate("second"));
                    pcll.setMobileSign("88888888");
                    pcll.setDeviceMac("88888888");
                    list.add(pcll);
                    try{
                        pcllMapper.insert(list);
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表数据成功");
                    }catch (Exception e){
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表时发生异常，添加失败" + e.getMessage());
                    }
                }
            }else {
                for(int i=0;i<json.getInt("loginNum");i++){
                    list = new ArrayList<>();
                    pcll.setCustId(Long.valueOf(userInfoMap.get("custId")));
                    pcll.setCreateTime(CommonUtils.subMin(CommonUtils.getCurDate("second"), 5));
                    pcll.setMobileSign("88888888" + i);
                    pcll.setDeviceMac("88888888" + i);
                    list.add(pcll);
                    try{
                        pcllMapper.insert(list);
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表数据成功");
                    }catch (Exception e){
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表时发生异常,添加失败" + e.getMessage());
                    }
                }
            }
        }

        if (option.equals("VA_F014")){
            List<String> cityList = new ArrayList<>();
            Map<Object, Object> schoolCityMap = null;
            if (!userInfoMap.get("educationAuth").isEmpty()){
                if (new JSONObject(userInfoMap.get("educationAuth")).getString("educationAuthStatus").equals("AS")){
                    schoolCityMap = ecService.getCollegesDate(new JSONObject(userInfoMap.get("educationAuth")).getString("collegeName"), session);
                }
            }
            if (!userInfoMap.get("schoolRollAuth").isEmpty()){
                if (new JSONObject(userInfoMap.get("schoolRollAuth")).getString("schoolRollAuthStatus").equals("AS")){
                    schoolCityMap = ecService.getCollegesDate(new JSONObject(userInfoMap.get("schoolRollAuth")).getString("collegeName"), session);
                }
            }
            String parentCity = pdService.getDictCode(new JSONObject(userInfoMap.get("contractor").toString()).getString("city"), session);
            if(json.getString("isSchool").toUpperCase().equals("Y") || json.getString("isParent").toUpperCase().equals("Y") || json.getString("isHome").toUpperCase().equals("Y")){
                for (int i=0;i<json.getInt("effectiveNum");i++){
                    list = new ArrayList<>();
                    pcll.setCustId(Long.valueOf(userInfoMap.get("custId")));
                    pcll.setCreateTime(CommonUtils.getCurDate("second"));
                    pcll.setLongitude("120");
                    pcll.setLatitude("31");
                    if(json.getString("isSchool").toUpperCase().equals("Y") && json.getString("isParent").toUpperCase().equals("Y")){
                        pcll.setCityCode(String.valueOf(schoolCityMap.get("city")));
                    }
                    if(json.getString("isSchool").toUpperCase().equals("Y") && !json.getString("isParent").toUpperCase().equals("Y")){
                        pcll.setCityCode(String.valueOf(schoolCityMap.get("city")));
                    }
                    if(!json.getString("isSchool").toUpperCase().equals("Y") && json.getString("isParent").toUpperCase().equals("Y")){
                        pcll.setCityCode(parentCity);
                    }
                    list.add(pcll);
                    try{
                        pcllMapper.insert(list);
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表数据成功");
                    }catch (Exception e){
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表时发生异常,添加失败" + e.getMessage());
                    }
                }
            }else {
                cityList.add(schoolCityMap.get("city").toString());
                cityList.add(parentCity);
                for (int i=0;i<json.getInt("effectiveNum");i++){
                    list = new ArrayList<>();
                    pcll.setCustId(Long.valueOf(userInfoMap.get("custId")));
                    pcll.setCreateTime(CommonUtils.getCurDate("second"));
                    pcll.setLongitude("120");
                    pcll.setLatitude("31");
                    pcll.setCityCode(pdService.getOtherDictCode(cityList, session));
                    list.add(pcll);
                    try{
                        pcllMapper.insert(list);
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表数据成功");
                    }catch (Exception e){
                        Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表时发生异常,添加失败" + e.getMessage());
                    }
                }
            }
            for (int j = 0; j < json.getInt("allNum") - json.getInt("effectiveNum"); j++) {
                list = new ArrayList<>();
                pcll.setCustId(Long.valueOf(userInfoMap.get("custId")));
                pcll.setCreateTime(CommonUtils.getCurDate("second"));
                pcll.setLongitude("0");
                pcll.setLatitude("0");
                pcll.setCityCode(null);
                list.add(pcll);
                try{
                    pcllMapper.insert(list);
                    Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表数据成功");
                }catch (Exception e){
                    Reporter.log("根据custId： " + userInfoMap.get("custId") + "添加p2p_cust_location_log表时发生异常,添加失败" + e.getMessage());
                }
            }
        }
    }

    public void delCustLocationLog(String oldCustId, SqlSession session){
        pcllMapper = session.getMapper(p2p_cust_location_logMapper.class);
        pcService = new p2p_customerService();
        try{
            pcllMapper.deleteByCustId(Long.valueOf(oldCustId));
        }catch (Exception e){
            Reporter.log("根据custId: " + oldCustId + "删除p2p_cust_location_log表中的数据时发生异常,删除失败" + e.getMessage());
        }
    }
}
