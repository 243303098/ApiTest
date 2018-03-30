package com.fkapi.rule;

import com.fkapi.auth.createUserInfo;
import com.fkapi.auth.createVccCustomer;
import com.fkapi.service.*;
import com.fkapi.utils.Assertion;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/21.
 */
public class rule {

    p2p_base_customerService pbcService;
    jxl_phone_call_recordsService jxlRecordsService;
    p2p_cust_location_logService pcllService;
    p2p_cert_authService pcaService;
    p2p_nw_blacklistService pnbService;
    p2p_blacklist_storeService pbsService;
    p2p_black_deviceService pbdService;
    vcc_user_card_mapService vucmService;
    p2p_cust_addr_listService pcalService;
    p2p_mobile_addrService pmaService;
    p2p_student_custService pscService;
    p2p_customer_contactorService pccService;
    p2p_customer_educationService pceService;
    p2p_loan_claimService plcService;
    p2p_dictionaryService pdService;
    risk_pingan_grayscale_statService rpgsService ;
    p2p_customerService pcService ;
    p2p_cust_credit_infoService pcciService ;
    p2p_cooperation_companyService pccompanyService ;

    createVccCustomer cvc = new createVccCustomer();
    createUserInfo cui = new createUserInfo();

    /**
     * 添加Excel中的上笔订单的数据
     *
     * @param userInfoMap
     * @param data
     * @param isCreditOrder
     * @param session
     */
    public void createLastOrder(Map<String, String> userInfoMap, String data, Boolean isCreditOrder, SqlSession session) {
        if (!data.trim().isEmpty()) {
            JSONObject json = new JSONObject(data);
            plcService = new p2p_loan_claimService();
            plcService.addProject(userInfoMap, json, isCreditOrder, false, session);
        }
    }

    /**
     * 根据通讯录联系人中的信息判断,添加相应的数据,适用于虚拟信用卡VA_F001，牛大咖AF001规则
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createCustList(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            JSONObject json = new JSONObject(data);
            pcalService = new p2p_cust_addr_listService();
            pcalService.addCustAddrList(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"), json, session);
        }
    }

    /**
     * 判断用户的手机号类型（是否为标准手机号等），并添加相应的数据
     * 适用于虚拟信用卡VA_F001，牛大咖AF001规则中的手机号格式
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createMobileNo(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pcalService = new p2p_cust_addr_listService();
            if (data.equals("标准手机号")) {
                //为标准手机号时不做任何操作
            }
            if (data.equals("非标准手机号")) {
                //更新其中一条手机号将其改为非标准手机号
                pcalService.updateCustAddrList(userInfoMap.get("custId"), session, "非标准手机号");
            }
            if (data.equals("N")) {
                //若不是手机号，则更新手机号为非手机号
                pcalService.updateCustAddrList(userInfoMap.get("custId"), session, "非手机号");
            }
        }
    }

    /**
     * 根据是否有住宅所在地中的信息判断，添加相应的数据
     * 适用于虚拟信用卡VA_F002,牛大咖AF002
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createHomeCity(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pdService = new p2p_dictionaryService();
            pmaService = new p2p_mobile_addrService();
            pcalService = new p2p_cust_addr_listService();
            JSONObject json = new JSONObject(userInfoMap.get("nowAddress"));
            if (data.toUpperCase().equals("Y")) {
                String cityCode = pdService.getDictCode(json.getString("nowCity"), session);
                String mobileCode = pmaService.getMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
            if (data.toUpperCase().equals("N")) {
                String cityCode = pdService.getDictCode(json.getString("nowCity"), session);
                String mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
        }
    }

    /**
     * 根据是否有户籍所在地中的信息判断，添加相应的数据
     * 适用于虚拟信用卡VA_F003, 触宝F003
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createCertCity(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pmaService = new p2p_mobile_addrService();
            pcalService = new p2p_cust_addr_listService();
            //截取身份证前4位，即为户籍所在地编码
            String cityCode = new JSONObject(userInfoMap.get("certAuth")).getString("idNo").substring(0, 4);
            if (data.toUpperCase().equals("Y")) {
                String mobileCode = pmaService.getMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            } else if (data.toUpperCase().equals("N")) {
                String mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            } else {
                JSONObject json = new JSONObject(data);
                if (json.getString("IDHaseRelativeNum").equals("Y")){
                    if (json.getString("ContactsHaseRelativeNum").equals("Y")){
                        String mobileCode = pmaService.getMobileByAddr(cityCode, session);
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
                    }
                    if (json.getString("ContactsHaseRelativeNum").equals("N")){
                        String mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
                    }
                }
                if (json.getString("IDHaseRelativeNum").equals("N")){
                    if (json.getString("ContactsHaseRelativeNum").equals("Y")){
                        String mobileCode = pmaService.getMobileByProvince(cityCode.substring(0,2), session);
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
                    }
                    if (json.getString("ContactsHaseRelativeNum").equals("N")){
                        String mobileCode = pmaService.getOtherMobileByProvince(cityCode.substring(0,2), session);
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
                    }
                }
                if (json.getString("ContactsHaseRelativeNum").trim().isEmpty()){
                    pcalService.delCustAddrList(userInfoMap.get("oldCustId"), session);
                }
            }

        }
    }

    /**
     * 根据是否有父母所在地中的信息判断,并添加相应的数据
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createParentCity(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pccService = new p2p_customer_contactorService();
            pmaService = new p2p_mobile_addrService();
            pcalService = new p2p_cust_addr_listService();
            String cityCode = pccService.getCustomerContactor(userInfoMap.get("custId"), session);
            if (data.toUpperCase().equals("Y")) {
                String mobileCode = pmaService.getMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
            if (data.toUpperCase().equals("N")) {
                String mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
        }
    }

    /**
     * 根据是否有学校号码段中的信息判断，添加相应的数据
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createSchoolCity(String userInfoNo, Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            String schoolCityCode;
            pmaService = new p2p_mobile_addrService();
            pcalService = new p2p_cust_addr_listService();
            pscService = new p2p_student_custService();
            pceService = new p2p_customer_educationService();
            if (userInfoNo.length() > 0 && userInfoNo.contains("student")) {
                schoolCityCode = pscService.getSchoolCityCode(userInfoMap.get("custId"), session);
            } else {
                schoolCityCode = pceService.getSchoolCityCode(userInfoMap.get("custId"), session);
            }
            if (data.toUpperCase().equals("Y")) {
                String mobileCode = pmaService.getMobileByAddr(schoolCityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
            if (data.toUpperCase().equals("N")) {
                String mobileCode = pmaService.getOtherMobileByAddr(schoolCityCode, session);
                pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), mobileCode + "0000", session);
            }
        }
    }

    /**
     * 创建虚拟信用卡VA_F006的规则数据
     *
     * @param userInfoNo
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F006(String userInfoNo, Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            String mobileCode = null;
            String schoolCityCode;
            JSONObject mobileJson = new JSONObject();
            JSONObject json = new JSONObject(data);
            pccService = new p2p_customer_contactorService();
            pbcService = new p2p_base_customerService();
            pmaService = new p2p_mobile_addrService();
            pscService = new p2p_student_custService();
            pceService = new p2p_customer_educationService();
            //获取父母所在地城市编码
            String parentCityCode = pccService.getCustomerContactor(userInfoMap.get("custId"), session);
            //根据所输入的参数判断是否毕业，获取相应的学校所在地城市编码
            if (userInfoNo.length() > 0 && userInfoNo.contains("student")) {
                schoolCityCode = pscService.getSchoolCityCode(userInfoMap.get("custId"), session);
            } else {
                schoolCityCode = pceService.getSchoolCityCode(userInfoMap.get("custId"), session);
            }
            if (json.get("parentAddr").equals("Y") && json.get("schoolAddr").equals("Y")) {
                mobileCode = pmaService.getMobileByAddr(parentCityCode, session);
            }
            if (json.get("parentAddr").equals("Y") && json.get("schoolAddr").equals("N")) {
                mobileCode = pmaService.getMobileByAddr(parentCityCode, session);
            }
            if (json.get("parentAddr").equals("N") && json.get("schoolAddr").equals("Y")) {
                mobileCode = pmaService.getMobileByAddr(schoolCityCode, session);
            }
            if (json.get("parentAddr").equals("N") && json.get("schoolAddr").equals("N")) {
                mobileCode = pmaService.getOtherMobileByAddr(schoolCityCode, session);
            }
            mobileJson.put("phoneAuthStatus", "AS");
            mobileJson.put("mobile", mobileCode + "0000");
            pbcService.update(userInfoMap.get("custId"), mobileJson, "phoneAuth", session);
        }
    }

    /**
     * 以最近一次运营商认证为时间节点的过去三个自然月中，每个月的主叫次数排名前十的手机号码中（申请人呼出），
     * 有大于等于两个号码出现在通讯录中，否则拒绝；
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F007(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            jxlRecordsService = new jxl_phone_call_recordsService();
            JSONObject json = new JSONObject(data);
            jxlRecordsService.addRecordsForVA_F007(userInfoMap, json, session);
        }
    }

    /**
     * 对于首次申请客户，最近一次运营商认证成功的前90天（自然天），如果没有主叫过任一紧急联系人，则拒绝
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F008(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            jxlRecordsService = new jxl_phone_call_recordsService();
            JSONObject json = new JSONObject(data);
            jxlRecordsService.addCallRecordsForVA_F008(userInfoMap, json, session);
        }
    }

    /**
     * 1、同一个手机标识码1天内第二个及以上注册用户申请到Virtual Credit Card/牛大咖资格（无法抓取手机标志码算通过）
     * 2、同一个手机标识码累计第三个及以上注册用户申请到Virtual Credit Card资格（无法抓取手机标志码算通过）
     * 复大医疗：
     * F009（F, S, T）：同一个cookie 1天内第二个及以上注册用户申请到贷款（无法抓取cookie算通过）。 (跨同渠道产品)
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F009(Map<String, String> userInfoMap, String data, SqlSession session, SqlSession vccSession, Boolean isVccOrder) {
        if (isVccOrder) {
            if (!data.trim().isEmpty()) {
                vucmService = new vcc_user_card_mapService();
                JSONObject json = new JSONObject(data);
                if (json.getString("isApply").toUpperCase().equals("Y")) {
                    vucmService.addVccUserCardMap(userInfoMap, json, false, session, vccSession);
                } else {
                    vucmService.delVccUserCardMap(new JSONObject(userInfoMap.get("phoneAuth")).getString("mobileSign"), vccSession);
                }
            }
        }else {
            if (!data.trim().isEmpty()) {
                JSONObject json = new JSONObject(data);
                JSONArray orderArray = new JSONArray(json.get("orders").toString());
                //创建有成功历史订单的用户，并且手机标识码与借款人相同
                for (int i = 0; i < json.getInt("allCount"); i++) {
                    cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.userInfoSheetName,
                            orderArray.getJSONObject(i).getString("userinfoNo")), false, session);
                }
            }
        }
    }

    /**
     * 同一个手机标识码累计第二个及以上注册用户申请到Virtual Credit Card资格的用户，若与第一个使用这个手机标识码
     * 申请到Virtual Credit Card资格的用户，通讯录相似度大于等于90%
     * （通讯录一致号码/第一个申请到Virtual Credit Card资格用户的通讯录号码数量），则拒绝
     *
     * @param userInfoMap
     * @param data
     * @param session
     * @param vccSession
     * @param isVccOrder
     */
    public void createVcc_VA_F011(Map<String, String> userInfoMap, String data, SqlSession session, SqlSession vccSession, Boolean isVccOrder) {
        if (!data.trim().isEmpty()) {
            JSONObject json = new JSONObject(data);
            JSONArray jsonArray = json.getJSONArray("addrList");
            JSONObject addrListJson = jsonArray.getJSONObject(0);
            pcalService = new p2p_cust_addr_listService();
            Map<String, String> otherUserInfoMap = cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, "userInfo",
                    json.getString("userinfoNo")), false, session);
            pcalService.addCustAddrList(userInfoMap.get("oldCustId"), otherUserInfoMap.get("custId"), new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"), addrListJson, session);
            if (isVccOrder) {
                vucmService = new vcc_user_card_mapService();
                cvc.create(otherUserInfoMap, false, vccSession);
                vucmService.addVccUserCardMap(otherUserInfoMap, vccSession);
            }
        }
    }

    /**
     * 若手机在首次申请前在多个设备登录，则拒绝此申请
     *
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F012(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pcllService = new p2p_cust_location_logService();
            JSONObject json = new JSONObject(data);
            pcllService.addCustLocationLog(userInfoMap, json, session, "VA_F012");
        }
    }

    /**
     * 拒绝新疆客户的申请。新疆客户定义如下：本科及以上：姓名>=4个字且学校所在地在新疆省专科：姓名>=4个字或学校所在地在新疆省
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F013(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pbcService = new p2p_base_customerService();
            JSONObject json = new JSONObject();
            json.put("custName", data);
            json.put("idNo", new JSONObject(userInfoMap.get("certAuth")).getString("idNo"));
            json.put("certAuthStatus", "AS");
            pbcService.update(userInfoMap.get("custId"), json, "certAuth", session);
        }
    }

    /**
     * 触宝F007:用户填写身份证号/姓名与牛娃系统内已认证身份证号/姓名不完全匹配
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateIdCardNo(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()) {
            pbcService = new p2p_base_customerService();
            JSONObject json = new JSONObject(data);
            JSONObject dataJson = new JSONObject();
            if (json.getString("IDMatch").toUpperCase().equals("N")){
                dataJson.put("idNo", new JSONObject(userInfoMap.get("certAuth")).getString("idNo").replaceFirst("9","8"));
            }else {
                dataJson.put("idNo", new JSONObject(userInfoMap.get("certAuth")).getString("idNo"));
            }
            if (json.getString("NameMatch").toUpperCase().equals("N")){
                dataJson.put("custName", new JSONObject(userInfoMap.get("certAuth")).getString("custName") + "1");
            }else {
                dataJson.put("custName", new JSONObject(userInfoMap.get("certAuth")).getString("custName"));
            }
            pbcService.update(userInfoMap.get("custId"), dataJson, "certAuth", session);
        }
    }

    /**
     * 对于首次申请贷款的人，抓取申请前7天的位置信息，位置信息超过15次，且没有一次是在学校/父母所在地（/实习所在城市）。
     * （只有确实抓到经纬度才算一次有效位置信息，如果抓取经纬度为0则不算。）如果之前有成功放款不用判断
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createVcc_VA_F014(Map<String, String> userInfoMap, String data, SqlSession session) {
        if (!data.trim().isEmpty()) {
            pcllService = new p2p_cust_location_logService();
            JSONObject json = new JSONObject(data);
            pcllService.addCustLocationLog(userInfoMap, json, session, "VA_F014");
        }
    }

    /**
     * 若A和B为同学（同校同专业同一年入学）且同为牛呗用户时，A的通讯录里有B，但是B的通讯录里面没有A。则拒绝B。
     * @param userInfoMap
     * @param data
     * @param session
     * @param vccSession
     */
    public void createVcc_VA_F015(Map<String, String> userInfoMap, String data, SqlSession session, SqlSession vccSession) {
        if (!data.trim().isEmpty()) {
            JSONObject jsonObject = new JSONObject(data);
            int num = jsonObject.getInt("allNum");
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    pcalService = new p2p_cust_addr_listService();
                    JSONArray jsonArray = jsonObject.getJSONArray("userinfo");
                    JSONObject userJson = jsonArray.getJSONObject(i);
                    //创建用户信息
                    Map<String, String> classmateUserInfoMap = cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, "userInfo",
                            userJson.getString("userinfoNo")), false, session);
                    if (userJson.getString("inAddrListNum").equals("1")) {
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), classmateUserInfoMap.get("custId"), new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"), session);
                    }
                    if (userJson.getString("inAddrListNum").equals("2")) {
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), new JSONObject(classmateUserInfoMap.get("phoneAuth")).getString("mobile"), session);
                        pcalService.addCustAddrListForAddress(userInfoMap.get("oldCustId"), classmateUserInfoMap.get("custId"), new JSONObject(userInfoMap.get("phoneAuth")).getString("mobile"), session);
                    }
                }
            }
        }
    }

    /**
     * 适用于修改年龄
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createAge(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()) {
            pbcService = new p2p_base_customerService();
            pcaService = new p2p_cert_authService();
            JSONObject json = new JSONObject();
            json.put("certNo", CommonUtils.creteIdCardByAge(userInfoMap.get("custId"), data, session));
            pbcService.update(userInfoMap.get("custId"), json, "age", session);
            pcaService.update(userInfoMap.get("custId"), CommonUtils.creteIdCardByAge(userInfoMap.get("custId"), data, session), session);
        }
    }

    /**
     * 适用于修改内部黑名单
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createInBlackList(Map<String, String> userInfoMap, String data, SqlSession session){
        if (data != null && data.equals("Y")) {
            pnbService = new p2p_nw_blacklistService();
            pnbService.delNwBlackList(userInfoMap.get("oldCustId"), session);
            pnbService.addNwBlackList(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), session);
        } else if (data != null && data.equals("N")) {
            pnbService = new p2p_nw_blacklistService();
            pnbService.delNwBlackList(userInfoMap.get("oldCustId"), session);
        }
    }

    /**
     * 适用于修改外部黑名单
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createOutBlackList(Map<String, String> userInfoMap, String data, SqlSession session){
        JSONObject json = null;
        if (!data.trim().isEmpty()) {
            pbsService = new p2p_blacklist_storeService();
            JSONObject inputJson = null;
            try {
                inputJson = new JSONObject(data);
                json = new JSONObject(userInfoMap.get("certAuth"));
            } catch (Exception e) {
                Reporter.log("参数转json时出错，参数为：" + data
                        + userInfoMap.get("certAuth"));
                Assertion.verifyTure(false);
            }
            // 删除使用的基础用户的黑名单信息
            pbsService.delBlackListStore(json, session);
            // 删除case中用户输入的信息
            pbsService.delBlackListStore(inputJson, session);
            // 将用户输入的身份信息添加到外部黑名单中
            pbsService.addBlackListStore(inputJson, session);
        } else {
            // 如果为空则表示不添加用户进入黑名单
            pbsService = new p2p_blacklist_storeService();
            json = new JSONObject(userInfoMap.get("certAuth"));
            pbsService.delBlackListStore(json, session);
        }
    }

    /**
     * 适用于设备黑名单
     * @param userInfoMap
     * @param deviceData
     * @param orderData
     * @param session
     */
    public void createDeviceBlackList(Map<String, String> userInfoMap, String deviceData, String orderData, SqlSession session){
        JSONObject json = null;
        if (!deviceData.trim().isEmpty()) {
            pbdService = new p2p_black_deviceService();
            try {
                json = new JSONObject(orderData);
                pbdService.delBlackDevice(json, session);
            } catch (Exception e) {
                Reporter.log("参数转json时出错，参数为：" + orderData);
                Assertion.verifyTure(false);
            }
            if (deviceData.equals("Y")) {
                pbdService.addBlackDevice(json, session);
            }
        }
    }

    /**
     * 对于首次申请的用户，最近一次运营商认证成功的前三个月，
     * 被叫通话中对方号码的手机号段为申请人住宅所在地号段的被叫次数占总被叫次数的百分比<=20%，则拒绝
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createNDKAF013(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()){
            JSONObject json = new JSONObject(data);
            jxlRecordsService = new jxl_phone_call_recordsService();
            jxlRecordsService.addPhoneCallRecordsForAF013(userInfoMap, json, session);
        }
    }

    /**
     * 对于首次申请客户，近一个月主叫催收号码次数>=4次，则拒绝
     * 对于首次申请的用户，近三个月与几家委外催收机构号码有过联系>=1次，则拒绝
     * 此规则针对牛大咖用户的AF015和AF016
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createNDKAF014(Map<String, String> userInfoMap, String data, SqlSession session){
        JSONObject json ;
        if (!data.trim().isEmpty()){
            json = new JSONObject(data);
            rpgsService = new risk_pingan_grayscale_statService();
            rpgsService.addPinganGrayscaleStat(userInfoMap, json, session);
        }
    }

    /**
     * 更新用户的账户状态
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void createCustStatus(Map<String, String> userInfoMap, String data, SqlSession session){
        JSONObject json = new JSONObject();
        if (!data.trim().isEmpty()){
            json.put("status", data);
            pcService = new p2p_customerService();
            pcService.update(userInfoMap.get("loginName"), json, "custStatus", session);
        }
    }

    /**
     * NXB_F011(F): 对于通讯录含有关键字（如“中介”， “办卡”等）的用户
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateMobileName(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()){
            pcalService = new p2p_cust_addr_listService();
            pcalService.updateMobileName(userInfoMap.get("custId"), data, session);
        }
    }

    /**
     * NXB_F014/NXB_F015
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void addCallRecordsForNXB(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()){
            jxlRecordsService = new jxl_phone_call_recordsService();
            jxlRecordsService.addCallRecordsForNXB_F014(userInfoMap, new JSONObject(data), session);
        }
    }

    /**
     * NXB_U001：更新用户的信用分
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateCreditScore(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()){
            pcciService = new p2p_cust_credit_infoService();
            pcciService.updateCreditScore(userInfoMap.get("custId"), Integer.valueOf(data), session);
        }
    }

    /**
     * NXB_U005：更新用户的Modality
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateModality(Map<String, String> userInfoMap, String data, SqlSession session){
        if (!data.trim().isEmpty()) {
            ex_StudentCreditInfoService esService = new ex_StudentCreditInfoService();
            esService.updataModality(userInfoMap.get("custId"), data, session);
        }
    }

    /**
     * 更新用户学历信息
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateEducation(Map<String, String> userInfoMap, String data, SqlSession session){
        p2p_customer_educationService pceService = new p2p_customer_educationService();
        if (!data.trim().isEmpty()) {
            JSONObject json = new JSONObject(data);
            if (json.getString("hasEduInfo").toUpperCase().equals("N")){
                pceService.delCustomerEducation(userInfoMap.get("custId"), session);
            }
            if (json.getString("schoolSame").toUpperCase().equals("N")){
                pceService.update(Long.valueOf(userInfoMap.get("custId")), session, "school");
            }
            if (json.getString("degreeSame").toUpperCase().equals("N")){
                pceService.update(Long.valueOf(userInfoMap.get("custId")), session, "degree");
            }
            if (json.getString("enterTimeSame").toUpperCase().equals("N")){
                pceService.update(Long.valueOf(userInfoMap.get("custId")), session, "entertime");
            }
            if (json.getString("graduateTimeSame").toUpperCase().equals("N")){
                pceService.update(Long.valueOf(userInfoMap.get("custId")), session, "graduatetime");
            }
        }
    }

    /**
     * 复大医疗：
     * F001（F, S, T）：手机号使用时间>=3个自然月， 否则拒绝。
     * 即运营商认证时抓取到的最早一条通话记录的时间距离申请时间>=3个月，否则拒绝
     * 牛大咖：
     * AU009：手机状态为正常在用，且手机在网时间大于三个月，否则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F001(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            jpcrService.addPhoneCallRecordsForF001(userInfoMap, Integer.valueOf(date), session);
        }
    }

    /**
     * 复大医疗：
     * F002（F）：以最近一次通过运营商认证为时间节点的过去三个月中，每个月联系的distinct 手机号>=3个， 否则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F002(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF002(userInfoMap, json, session);
        }
    }


    /**
     * 复大医疗：
     * F003（F）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月主叫总时长必须>=1分钟，否则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F003(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF003(userInfoMap, json, session);
        }
    }

    /**
     * 复大医疗：
     * F004（F）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月至少有>=4天有主叫，否则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F004(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF004(userInfoMap, json, session);
        }
    }

    /**
     * 复大医疗：
     * F005（F, S, T）：以最近一次通过运营商认证为时间节点的过去三个月中， 每月的主叫号码中，
     * 至少有>=1个手机号码的手机号段为住宅所在地或者工作地点所在城市号段，否则拒绝；（精确到城市）
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F005(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF005(userInfoMap, json, session);
        }
    }

    /**
     * 复大医疗
     * F007（F, S, T）：以最近一次通过运营商认证为时间节点的过去三个月中，至少联系过一次紧急联系人（任一紧急联系人）；
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F007(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF007(userInfoMap, json, session);
        }
    }

    /**
     * 复大医疗
     * F008（F, S, T）：借款人手机号段必须为户籍所在城市或住宅所在城市或工作地点所在城市号段，否则拒绝；
     * 如果借款人手机号段未匹配到城市，则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCustMobile(Map<String, String> userInfoMap, String date, SqlSession session){
        if (date.length() > 0){
            String mobileCode = null ;
            JSONObject json = new JSONObject(date);
            pdService = new p2p_dictionaryService();
            pmaService = new p2p_mobile_addrService();
            pccompanyService = new p2p_cooperation_companyService();
            json.put("phoneAuthStatus","AS");
            JSONObject homeCityCodeJson = new JSONObject(userInfoMap.get("nowAddress"));
            JSONObject registerCityCodeJson = new JSONObject(userInfoMap.get("certAuth"));
            String cityCode = pdService.getDictCode(homeCityCodeJson.getString("nowCity"), session);
            String registerCode = registerCityCodeJson.getString("idNo").substring(0,4);
            String workCode = pccompanyService.getCompanyInfo(new JSONObject(userInfoMap.get("工作认证")).getLong("companyId"), session).getCompanyCityCode();
            pbcService = new p2p_base_customerService();
            if (json.getString("IsMatch").toUpperCase().equals("Y")){
                if (json.getString("IsRegister").toUpperCase().equals("Y")){
                    mobileCode = pmaService.getMobileByAddr(registerCode, session);
                }
                if (json.getString("IsRegister").toUpperCase().equals("N")){
                    mobileCode = pmaService.getOtherMobileByAddr(registerCode, session);
                }
                if (json.getString("IsHome").toUpperCase().equals("Y")){
                    mobileCode = pmaService.getMobileByAddr(cityCode, session);
                }
                if (json.getString("IsHome").toUpperCase().equals("N")){
                    mobileCode = pmaService.getOtherMobileByAddr(cityCode, session);
                }
                if (json.getString("IsWork").toUpperCase().equals("Y")){
                    mobileCode = pmaService.getMobileByAddr(workCode, session);
                }
                if (json.getString("IsWork").toUpperCase().equals("N")){
                    mobileCode = pmaService.getOtherMobileByAddr(workCode, session);
                }
                json.put("mobile", mobileCode + "0000");
                pbcService.update(userInfoMap.get("custId"), json, "phoneAuth", session);
            }else if (json.getString("IsMatch").toUpperCase().equals("N")){
                json.put("mobile", "10000000000");
                pbcService.update(userInfoMap.get("custId"), json, "phoneAuth", session);
            }
        }
    }

    /**
     * 复大医疗：
     * F015（F, S, T）：以最近一次通过运营商认证为时间节点的过去一个月中，被叫挂断（通话时间为0）
     * 的被叫次数占总被叫次数的百分比大于90%，则拒绝
     * @param userInfoMap
     * @param date
     * @param session
     */
    public void updateCallRecordsforFDYL_F015(Map<String, String> userInfoMap, String date, SqlSession session){
        jxl_phone_call_recordsService jpcrService = new jxl_phone_call_recordsService();
        if (date.length() > 0){
            JSONObject json = new JSONObject(date);
            jpcrService.addPhoneCallRecordsForF015(userInfoMap, json, session);
        }
    }

    /**
     * 复大医疗：
     * U005（F, S, T）：入职时间距申请时间>4个自然月，否则拒绝
     * U006（F, S, T）：劳动关系如果是临时，则拒绝
     * U009（F, S, T）：工作状态必须为正常或试用，否则拒绝
     * U010（F, S, T）：如果是正式员工，月薪范围的最高值（如果为10000+，则按15000计算）必须为月均还款额的2倍及以上，否则拒绝；如果是试用员工，
     *                 月薪范围的最高值*0.8（如果为10000+，则按15000*0.8计算）必须为月均还款额的2倍及以上，否则拒绝
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void updateWorkInfo(Map<String, String> userInfoMap, String data, SqlSession session, String option){
        if (data.length() > 0) {
            p2p_cooperation_employeeService pceService = new p2p_cooperation_employeeService();
            pceService.update(userInfoMap, data, session, option);
        }
    }

    /**
     * 复大医疗
     * F014（F, S, T）：同一个公司，如有10%*A个人还未还款，则拒绝。其中，A为公司规模抓取到的最大值。比如，抓取的公司规模为100-500人，
     * 则A=500，即，如果在申请时，同个公司有10%*500=50个人还未还款，则拒绝
     * @param userInfoMap
     * @param data
     * @param session
     * @param option
     */
    public void updateCompanyInfo(Map<String, String> userInfoMap, String data, SqlSession session, String option){
        if (data.length() > 0) {
            p2p_cooperation_companyService pccService = new p2p_cooperation_companyService();
            pccService.updateCooperationCompany(userInfoMap, data, session, option);
        }
    }

    /**
     * AU014：对于牛大咖首次放款用户（NDK_F），“牛大咖首次模型”分数〉0.383时,则拒绝。
     * 其中，“牛大咖首次模型”指宗帅组最近为牛大咖首次用户建的模型
     * @param userInfoMap
     * @param data
     * @param vccSession
     */
    public void updatePythonScore(Map<String, String> userInfoMap, String data, SqlSession vccSession){
        if (data.length() > 0){
            risk_python_scoreService rpcService = new risk_python_scoreService();
            rpcService.addRiskPythonScore(userInfoMap, Double.valueOf(data), vccSession);
        }
    }
}