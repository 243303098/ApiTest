package com.fkapi.service;

import com.fkapi.database.domain.jxl_phone_call_records;
import com.fkapi.database.dao.jxl_phone_call_recordsMapper;
import com.fkapi.database.domain.p2p_cust_addr_list;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/7/10.
 */
public class jxl_phone_call_recordsService {
    jxl_phone_call_recordsMapper jxlRecordMapper ;
    jxl_primary_infoService jpiService ;
    p2p_cust_addr_listService pcalService ;
    p2p_mobile_addrService pmaService ;
    p2p_dictionaryService pdService ;
    p2p_cooperation_companyService pccService ;

    /**
     * 根据NXB_F014规则输入的信息添加通话记录
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addCallRecordsForNXB_F014(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        List<jxl_phone_call_records> list ;
        jxl_phone_call_records jxlRecords ;
        for (int i = 0; i < json.getInt("call"); i++) {
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(String.valueOf(System.currentTimeMillis()));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone("13333333333");
            jxlRecords.setCallPlace("江苏");
            jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("主叫");
            jxlRecords.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        for (int i = 0; i < json.getInt("called"); i++){
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(String.valueOf(System.currentTimeMillis()));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone("13333333333");
            jxlRecords.setOtherCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setCallPlace("江苏");
            jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("被叫");
            jxlRecords.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
    }

    /**
     *  根据VA_F008规则输入的信息添加通话记录
     * @param userInfoMap 用户基础信息表
     * @param json 要添加的通话记录信息（虚拟信用卡数据生成表中的json）
     * @param session
     */
    public void addCallRecordsForVA_F008(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        JSONArray jsonArray = (JSONArray) new JSONObject(userInfoMap.get("contractor")).get("contractor");
        List<jxl_phone_call_records> list = new ArrayList<>();
        jxl_phone_call_records jxlRecords = new jxl_phone_call_records();

        jxlRecords.setId(String.valueOf(System.currentTimeMillis()));
        jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
        jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
        if(json.getString("isContactor").toUpperCase().equals("Y")){
            jxlRecords.setOtherCellPhone(String.valueOf(new JSONObject(jsonArray.get(0).toString()).get("mobile")));
        }else {
            jxlRecords.setOtherCellPhone("13333333333");
        }
        jxlRecords.setCallPlace("江苏");
        jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
        jxlRecords.setUseTime(10L);
        jxlRecords.setInitType(json.getString("type"));
        jxlRecords.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
        list.add(jxlRecords);
        jxlRecordMapper.insertVA_F008(list);
    }

    /**
     * 根据VA_F007规则输入的信息添加通话记录
     * @param userInfoMap
     * @param json 要添加的通话记录信息（虚拟信用卡数据生成表中的json-VA_F007）
     * @param session
     */
    public void addRecordsForVA_F007(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        //首先生成基础的通话记录数据，认证前三个月的数据，主叫次数均为一次，通话时间一次增加
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        pcalService = new p2p_cust_addr_listService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords = new jxl_phone_call_records();
        for(int k=0;k<3;k++){
            for (int i=0;i<10;i++){
                for(int j=0;j<1;j++){
                    list = new ArrayList<>();
                    jxlRecords.setId(i + UUID.randomUUID().toString().replaceAll("-","").substring(1));
                    jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                    jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                    jxlRecords.setOtherCellPhone("1333333333" + i);
                    jxlRecords.setCallPlace("江苏");
                    jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), k+1));
                    jxlRecords.setUseTime(10L + i*2);
                    jxlRecords.setInitType("主叫");
                    jxlRecords.setUpdateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), k+1));
                    list.add(jxlRecords);
                    jxlRecordMapper.insertVA_F008(list);
                }
            }
        }
        //根据所填信息，新增相应的数据
        JSONArray jsonArray = json.getJSONArray("records");
        List<p2p_cust_addr_list> mobileList = pcalService.getCustAddrListMobile(userInfoMap.get("custId"), session);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int numTopTen = jsonObject.getInt("NumTopTen");
            if(jsonObject.getInt("NumTopTen") > 0){
                for(int k=0;k<numTopTen;k++){
                    list = new ArrayList<>();
                    jxlRecords.setId(k + UUID.randomUUID().toString().replaceAll("-","").substring(1));
                    jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                    jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                    jxlRecords.setOtherCellPhone(mobileList.get(k).getMobile());
                    jxlRecords.setCallPlace("江苏");
                    jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), i+1));
                    if(k == 0){
                        switch (jsonObject.getInt("MinRank") - numTopTen){
                            case 0 :
                                jxlRecords.setUseTime(99L);
                            case 1 :
                                jxlRecords.setUseTime(27L);
                                break;
                            case 2 :
                                jxlRecords.setUseTime(25L);
                                break;
                            case 3 :
                                jxlRecords.setUseTime(23L);
                                break;
                            case 4 :
                                jxlRecords.setUseTime(21L);
                                break;
                            case 5 :
                                jxlRecords.setUseTime(19L);
                                break;
                            case 6 :
                                jxlRecords.setUseTime(17L);
                                break;
                            case 7 :
                                jxlRecords.setUseTime(15L);
                                break;
                            case 8 :
                                jxlRecords.setUseTime(13L);
                                break;
                            case 9 :
                                jxlRecords.setUseTime(11L);
                                break;
                            case 10 :
                                jxlRecords.setUseTime(9L);
                                break;
                        }
                    }else {
                        jxlRecords.setUseTime(99L);
                    }
                    jxlRecords.setInitType(json.getString("type"));
                    jxlRecords.setUpdateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), i+1));
                    list.add(jxlRecords);
                    jxlRecordMapper.insertVA_F008(list);
                }
            }
        }
    }

    /**
     * 添加牛大咖AF013中的数据
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForAF013(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        pmaService = new p2p_mobile_addrService();
        pcalService = new p2p_cust_addr_listService();
        pdService = new p2p_dictionaryService() ;
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords ;
        String homeCityCode = pdService.getDictCode(new JSONObject(userInfoMap.get("nowAddress")).getString("nowCity"), session);
        //先添加主叫号码的记录
        JSONArray callingJsonArray = new JSONArray(json.get("calling").toString());
        JSONArray calledJsonArray = new JSONArray(json.get("called").toString());
        for (int i = 0; i < callingJsonArray.getJSONObject(0).getInt("homeRecordsNum"); i++) {
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone(pmaService.getMobileByAddr(homeCityCode, session) + "111" + i);
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("主叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        for (int i = 0; i < callingJsonArray.getJSONObject(0).getInt("otherRecordsNum"); i++) {
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone(pmaService.getOtherMobileByAddr(homeCityCode, session) + "111" + i);
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("主叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        //添加被叫号码
        for (int i = 0; i < calledJsonArray.getJSONObject(0).getInt("homeRecordsNum"); i++) {
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone(pmaService.getMobileByAddr(homeCityCode, session) + "111" + i);
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("被叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        for (int i = 0; i < calledJsonArray.getJSONObject(0).getInt("otherRecordsNum"); i++) {
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone(pmaService.getOtherMobileByAddr(homeCityCode, session) + "111" + i);
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("被叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
    }

    /**
     * 复大医疗：
     * F001（F, S, T）：手机号使用时间>=3个自然月， 否则拒绝。
     * 即运营商认证时抓取到的最早一条通话记录的时间距离申请时间>=3个月，否则拒绝
     * 牛大咖：
     * AU009：手机状态为正常在用，且手机在网时间大于三个月，否则拒绝
     * @param userInfoMap
     * @param month
     * @param session
     */
    public void addPhoneCallRecordsForF001(Map<String,String> userInfoMap, Integer month, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        List<jxl_phone_call_records> list = new ArrayList<>();
        jxl_phone_call_records jxlRecords = new jxl_phone_call_records();
        jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
        jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
        jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
        jxlRecords.setOtherCellPhone("18888888888");
        jxlRecords.setCallPlace(null);
        jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getFirstDay(), month));
        jxlRecords.setUseTime(10L);
        jxlRecords.setInitType("主叫");
        jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
        list.add(jxlRecords);
        jxlRecordMapper.insertVA_F008(list);
    }

    /**
     * 复大医疗：
     * F002（F）：以最近一次通过运营商认证为时间节点的过去三个月中，每个月联系的distinct 手机号>=3个， 否则拒绝
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF002(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        JSONArray jsonArray = json.getJSONArray("records");
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonObject.getInt("DistinctNum"); j++){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                if (jsonObject.getString("IsPhoneNo").toUpperCase().equals("N")){
                    jxlRecords.setOtherCellPhone("1008" + j);
                }else if (jsonObject.getString("IsPhoneNo").equals("标准手机号")){
                    jxlRecords.setOtherCellPhone("1888888888" + j);
                }else if (jsonObject.getString("IsPhoneNo").equals("非标准手机号")){
                    jxlRecords.setOtherCellPhone("861888888888" + j);
                }
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month")));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType("主叫");
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }
            for (int k = 0; k < jsonObject.getInt("Num") - jsonObject.getInt("DistinctNum"); k++){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                if (jsonObject.getString("IsPhoneNo").toUpperCase().equals("N")){
                    jxlRecords.setOtherCellPhone("1008" + i);
                }else if (jsonObject.getString("IsPhoneNo").equals("标准手机号")){
                    jxlRecords.setOtherCellPhone("1888888888" + i);
                }else if (jsonObject.getString("IsPhoneNo").equals("非标准手机号")){
                    jxlRecords.setOtherCellPhone("861888888888" + i);
                }
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month")));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType("主叫");
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }

        }
    }

    /**
     * 复大医疗：
     * F003（F）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月主叫总时长必须>=1分钟，否则拒绝
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF003(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        JSONArray jsonArray = json.getJSONArray("records");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            if (jsonObject.getString("CallType").equals("主叫")){
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                jxlRecords.setOtherCellPhone("18888888888");
            }else {
                jxlRecords.setOtherCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                jxlRecords.setCellPhone("18888888888");
            }
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month")));
            jxlRecords.setUseTime(jsonObject.getLong("Time"));
            jxlRecords.setInitType(jsonObject.getString("CallType"));
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
    }

    /**
     * 复大医疗：
     * F004（F）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月至少有>=4天有主叫，否则拒绝
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF004(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        JSONArray jsonArray = json.getJSONArray("records");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonObject.getInt("CallDays"); j++){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                jxlRecords.setOtherCellPhone("18888888888");
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month"))), j+1));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType("主叫");
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }
            for (int k = 0; k < jsonObject.getInt("CalledDays"); k++){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setOtherCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                jxlRecords.setCellPhone("18888888888");
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month"))), k+1));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType("被叫");
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }
        }
    }

    /**
     * F005（F, S, T）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月的主叫号码中，
     * 至少有>=1个手机号码的手机号段为住宅所在地或者工作地点所在城市号段，否则拒绝；（精确到城市）
     *
     * F006（F, S, T）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月的主叫号码中，
     * 至少有>=1个手机号码的手机号段为户籍所在城市号段，否则拒绝
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF005(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        pdService = new p2p_dictionaryService();
        pmaService = new p2p_mobile_addrService();
        pccService = new p2p_cooperation_companyService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        JSONObject homeCityCodeJson = new JSONObject(userInfoMap.get("nowAddress"));
        JSONObject registerCityCodeJson = new JSONObject(userInfoMap.get("certAuth"));
        JSONArray jsonArray = json.getJSONArray("records");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonObject.getInt("Num"); j++){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                if (jsonObject.getString("PhoneNo").startsWith("住宅")){
                    String cityCode = pdService.getDictCode(homeCityCodeJson.getString("nowCity"), session);
                    String mobileCode = pmaService.getMobileByAddr(cityCode, session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                if (jsonObject.getString("PhoneNo").startsWith("非住宅")){
                    String cityCode = pdService.getDictCode(homeCityCodeJson.getString("nowCity"), session);
                    String mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                if (jsonObject.getString("PhoneNo").startsWith("户籍")){
                    //String registerCode = pdService.getDictCode(registerCityCodeJson.getString("idNo").substring(0,4), session);
                    String mobileCode = pmaService.getMobileByAddr(registerCityCodeJson.getString("idNo").substring(0,4), session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                if (jsonObject.getString("PhoneNo").startsWith("非户籍")){
                    String registerCode = pdService.getDictCode(registerCityCodeJson.getString("idNo").substring(0,4), session);
                    String mobileCode = pmaService.getOtherMobileByAddr(registerCode, session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                if (jsonObject.getString("PhoneNo").startsWith("工作")){
                    String workCode = pccService.getCompanyInfo(new JSONObject(userInfoMap.get("工作认证")).getLong("companyId"), session).getCompanyCityCode();
                    String mobileCode = pmaService.getMobileByAddr(workCode, session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                if (jsonObject.getString("PhoneNo").startsWith("非工作")){
                    String workCode = pccService.getCompanyInfo(new JSONObject(userInfoMap.get("工作认证")).getLong("companyId"), session).getCompanyCityCode();
                    String mobileCode = pmaService.getOtherMobileByAddr(workCode, session);
                    jxlRecords.setOtherCellPhone(mobileCode + "0000");
                }
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month"))), j+1));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType(jsonObject.getString("CallType"));
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }
        }
    }

    /**
     * 复大医疗
     * F007（F, S, T）：以最近一次通过运营商认证为时间节点的过去三个月中，至少联系过一次紧急联系人（任一紧急联系人）；
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF007(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        pdService = new p2p_dictionaryService();
        pmaService = new p2p_mobile_addrService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        JSONArray jsonArray = json.getJSONArray("records");
        JSONArray contactJsonArray = (JSONArray) new JSONObject(userInfoMap.get("contractor")).get("contractor");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getString("IsContact").toUpperCase().equals("Y")){
                list = new ArrayList<>();
                jxlRecords = new jxl_phone_call_records();
                jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
                jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
                jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
                jxlRecords.setOtherCellPhone(String.valueOf(new JSONObject(contactJsonArray.get(0).toString()).get("mobile")));
                jxlRecords.setCallPlace(null);
                jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), jsonObject.getInt("Month"))), 1));
                jxlRecords.setUseTime(10L);
                jxlRecords.setInitType(jsonObject.getString("CallType"));
                jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(jxlRecords);
                jxlRecordMapper.insertVA_F008(list);
            }
        }
    }

    /**
     * 复大医疗：
     * F015（F, S, T）：以最近一次通过运营商认证为时间节点的过去一个月中，被叫挂断（通话时间为0）
     * 的被叫次数占总被叫次数的百分比大于90%，则拒绝
     * @param userInfoMap
     * @param json
     * @param session
     */
    public void addPhoneCallRecordsForF015(Map<String,String> userInfoMap, JSONObject json, SqlSession session) {
        delCallRecords(userInfoMap.get("oldCustId"), session);
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        pdService = new p2p_dictionaryService();
        pmaService = new p2p_mobile_addrService();
        List<jxl_phone_call_records> list;
        jxl_phone_call_records jxlRecords;
        for (int i = 0; i < json.getInt("CalledBreak"); i++){
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone("18888888888");
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1)), 1));
            jxlRecords.setUseTime(0L);
            jxlRecords.setInitType("被叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        for (int i = 0; i < json.getInt("CallBreak"); i++){
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone("18888888888");
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1)), 1));
            jxlRecords.setUseTime(0L);
            jxlRecords.setInitType("主叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
        for (int i = 0; i < json.getInt("AllRecords") - json.getInt("CallBreak") - json.getInt("CalledBreak"); i++){
            list = new ArrayList<>();
            jxlRecords = new jxl_phone_call_records();
            jxlRecords.setId(UUID.randomUUID().toString().replaceAll("-",""));
            jxlRecords.setPrimaryId(jpiService.getPrimaryId(userInfoMap.get("custId"), session));
            jxlRecords.setCellPhone(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"));
            jxlRecords.setOtherCellPhone("18888888888");
            jxlRecords.setCallPlace(null);
            jxlRecords.setStartTime(CommonUtils.subDay(CommonUtils.getLastDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), 1)), 1));
            jxlRecords.setUseTime(10L);
            jxlRecords.setInitType("被叫");
            jxlRecords.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(jxlRecords);
            jxlRecordMapper.insertVA_F008(list);
        }
    }
    /**
     * 根据primaryId删除删除jxl_phone_call_records表中的信息
     * @param oldCustId
     * @param session
     */
    public void delCallRecords(String oldCustId, SqlSession session){
        jxlRecordMapper = session.getMapper(jxl_phone_call_recordsMapper.class);
        jpiService = new jxl_primary_infoService();
        try {
            jxlRecordMapper.deleteByPrimaryId(jpiService.getPrimaryId(oldCustId, session));
        }catch (Exception e) {
            Reporter.log("根据primaryId: " + jpiService.getPrimaryId(oldCustId, session) +"删除jxl_phone_call_records表中的信息时发生异常" + e.getMessage());
        }
    }

    @Test
    public void test(){

    }
}
