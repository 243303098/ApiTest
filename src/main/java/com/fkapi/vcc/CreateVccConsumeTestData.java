package com.fkapi.vcc;

import com.fkapi.auth.createUserInfo;
import com.fkapi.auth.createVccCustomer;
import com.fkapi.service.*;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.rule.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/19.
 */
public class CreateVccConsumeTestData {
    vcc_orderService voService;
    vcc_user_card_mapService vucmService;
    vcc_loan_infoService vliService;
    p2p_black_deviceService pbdService;
    p2p_loan_claimService plcService;
    vcc_repay_planService vrpService ;
    p2p_repay_planService prpService ;

    rule rule = new rule();

    Map<String, String> userInfoMap;

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, String> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public void create(String dataName, String userInfoNo, SqlSession session, SqlSession vccSession, SqlSession riskSession) {
        JSONObject json = null;
        //创建基础用户信息，并将基础用户信息保存到userInfoMap中
        createUserInfo cui = new createUserInfo();
        setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, "userInfo",
                userInfoNo), true, session));

        //添加虚拟信用卡用户表信息
        createVccCustomer cvc = new createVccCustomer();
        cvc.create(userInfoMap, true, vccSession);

        vucmService = new vcc_user_card_mapService();
        vucmService.addVccUserCardMap(userInfoMap, vccSession);

        if (!dataName.trim().isEmpty()) {
            Map<String, String> map = getVccConsumeData(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.consumeSheetname, dataName));
            if (!map.get("VPT_F001_F002").trim().isEmpty()) {
                json = new JSONObject(map.get("VPT_F001_F002"));
                JSONObject vccJson ;
                vliService = new vcc_loan_infoService();
                voService = new vcc_orderService();
                vrpService = new vcc_repay_planService();
                if (!map.get("VCC订单").trim().isEmpty()){
                    vccJson = new JSONObject(map.get("VCC订单"));
                    if(vccJson.getString("repayStatus").equals("OVERDUE_NO_REPAY")){
                        vliService.addVccLoanInfo(userInfoMap, json, "Y", vccSession);
                    }else {
                        vliService.addVccLoanInfo(userInfoMap, json, "N", vccSession);
                    }
                    voService.addVccOrder(userInfoMap, json, vccJson, vccSession);
                    vrpService.addVccRepalyPlan(userInfoMap, vccJson, false, vccSession);
                }else {
                    vliService.addVccLoanInfo(userInfoMap, json, "N", vccSession);
                    vccJson = new JSONObject();
                    vccJson.put("repayStatus","WAIT_REPAY");
                    vccJson.put("billOutFlag","N");
                    vccJson.put("delFlag","N");
                    voService.addVccOrder(userInfoMap, json, vccJson, vccSession);
                    vrpService.addVccRepalyPlan(userInfoMap, vccJson, true, vccSession);
                }
            }

            //添加历史的现金贷订单
            rule.createLastOrder(userInfoMap, map.get("现金贷订单"), false, session);

            if (!map.get("VPT_U005现金贷明细").trim().isEmpty()){
                prpService = new p2p_repay_planService();
                JSONObject repayPlanJson = new JSONObject(map.get("VPT_U005现金贷明细"));
                //添加还款明细表，每笔明细均为100元
                prpService.addRepayPlan(userInfoMap, repayPlanJson, session);
            }

            // 根据输入的信息判断是否将用户添加进黑名单
            rule.createInBlackList(userInfoMap, map.get("内部黑名单"), session);

            // 将用户输入的身份信息加入到黑名单中
            rule.createOutBlackList(userInfoMap, map.get("外部黑名单"), session);

            //rule.createDeviceBlackList();
            // 根据输入的信息判断是否将设备加入到黑名单
            if (!map.get("设备黑名单").trim().isEmpty()) {
                pbdService = new p2p_black_deviceService();
                json = new JSONObject();
                //虚拟信用卡中的设备编号为当前用户的mobileSign
                json.put("deviceCode", new JSONObject(userInfoMap.get("phoneAuth")).getString("mobileSign"));
                json.put("loanSubSrc", "VCC");
                pbdService.delBlackDevice(json, session);
                if (map.get("设备黑名单").trim().toUpperCase().equals("Y")) {
                    pbdService.addBlackDevice(json, session);
                }
            }
        }
    }

    /**
     * 获取虚拟信用卡消费数据生成表中的信息
     *
     * @param dataNo
     * @return
     */
    public Map<String, String> getVccConsumeData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.excelPath, CommonUtils.consumeSheetname ,dataNo);
        return map;
    }

}
