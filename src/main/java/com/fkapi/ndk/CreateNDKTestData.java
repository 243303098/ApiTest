package com.fkapi.ndk;

import com.fkapi.auth.createUserInfo;
import com.fkapi.rule.rule;
import com.fkapi.service.p2p_loan_claimService;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/18.
 */
public class CreateNDKTestData {

    private Map<String, String> userInfoMap ;

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, String> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public void create(String dataName, String userInfoNo, SqlSession session, SqlSession riskSession){
        SqlSession vccSession = null;
        rule rule = new rule();
        createUserInfo cui = new createUserInfo();
        //创建用户数据信息并保存
        setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.userInfoSheetName,
                userInfoNo), true, session));

        if (!dataName.trim().isEmpty()) {
            Map<String, String> map = getNDKTestData(ExcelUtils.getRowNoByValue(CommonUtils.ndkExcelPath, CommonUtils.ndkDataSheetName, dataName));
            //添加风控审批订单
            rule.createLastOrder(userInfoMap, map.get("风控审批订单"), true, session);
            //添加用户的通讯录联系人信息
            rule.createCustList(this.getUserInfoMap(), map.get("通讯录联系人"), session);
            //根据是否全部为手机号中的信息判断，添加相应的数据
            rule.createMobileNo(userInfoMap, map.get("是否全部为手机号"), session);
            //根据住宅所在地中的信息添加相应的数据
            rule.createHomeCity(userInfoMap, map.get("是否有住宅所在地"), session);
            //添加AF004中对应的测试数据(此规则与虚拟信用卡中的VA_F007相同)
            rule.createVcc_VA_F007(userInfoMap, map.get("AF004"), session);
            //添加AF005/AF006中所需的测试数据
            rule.createVcc_VA_F009(userInfoMap, map.get("AF005/AF006"), session, vccSession, false);
            //添加AF007中所需的测试数据
            rule.createVcc_VA_F011(userInfoMap, map.get("AF007"), session, vccSession, false);
            //添加AF008中所需的测试数据
            rule.createVcc_VA_F012(userInfoMap, map.get("AF008"), session);
            //添加AF013中所需的测试数据
            rule.createNDKAF013(userInfoMap, map.get("AF013"), session);
            //添加AF014中所需的测试数据
            rule.createVcc_VA_F008(userInfoMap, map.get("AF014"), session);
            //添加AF015/AF016中所需的测试数据
            rule.createNDKAF014(userInfoMap, map.get("AF015/AF016"), session);
            //更新用户的年龄
            rule.createAge(userInfoMap, map.get("年龄"), session);
            //更新用户学历信息
            rule.updateEducation(userInfoMap, map.get("学历信息"), session);
            //操作外部黑名单
            rule.createOutBlackList(userInfoMap, map.get("外部黑名单"), session);
            //操作内部黑名单
            rule.createInBlackList(userInfoMap, map.get("内部黑名单"), session);
            //添加设备黑名单
            rule.createDeviceBlackList(userInfoMap, map.get("设备黑名单"), map.get("风控审批订单"), session);
            //添加Python模型分
            rule.updatePythonScore(userInfoMap, map.get("Python模型分"), riskSession);
        }
    }

    /**
     * 获取牛大咖数据生成表中的数据
     * @param dataNo
     * @return
     */
    public Map<String, String> getNDKTestData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.ndkExcelPath, CommonUtils.ndkDataSheetName, dataNo);
        return map;
    }
}
