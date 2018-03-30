package com.fkapi.nxb;

import com.fkapi.auth.createUserInfo;
import com.fkapi.rule.rule;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.
 */
public class CreateNXBTestData {
    private Map<String, String> userInfoMap;

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, String> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public void create(String dataName, String userInfoNo, SqlSession session) {
        SqlSession vccSession = null;
        rule rule = new rule();
        createUserInfo cui = new createUserInfo();
        //创建用户数据信息并保存
        setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.userInfoSheetName,
                userInfoNo), true, session));

        if (!dataName.trim().isEmpty()) {
            Map<String, String> map = getNXBTestData(ExcelUtils.getRowNoByValue(CommonUtils.nxbExcelPath, CommonUtils.nxbDataSheetName, dataName));
            //添加风控审批订单
            rule.createLastOrder(userInfoMap, map.get("风控审批订单"), true, session);

            rule.createVcc_VA_F006(userInfoNo, userInfoMap, map.get("NXB_F001"), session);

            rule.createCustList(userInfoMap, map.get("通讯录联系人"), session);

            rule.createMobileNo(userInfoMap, map.get("是否全部为手机号"), session);

            rule.createParentCity(userInfoMap, map.get("是否有父母所在地"), session);

            rule.createSchoolCity(userInfoNo, userInfoMap, map.get("是否有学校号码段"), session);

            rule.createVcc_VA_F009(userInfoMap, map.get("NXB_F005/F006"), session, vccSession, false);

            rule.createVcc_VA_F014(userInfoMap, map.get("NXB_F008"), session);

            rule.createVcc_VA_F011(userInfoMap, map.get("NXB_F009"), session, vccSession, false);

            rule.createVcc_VA_F012(userInfoMap, map.get("NXB_F010"), session);

            rule.updateMobileName(userInfoMap, map.get("NXB_F011"), session);

            rule.createVcc_VA_F015(userInfoMap, map.get("NXB_F012"), session, vccSession);

            rule.createVcc_VA_F013(userInfoMap, map.get("NXB_F013"), session);

            rule.addCallRecordsForNXB(userInfoMap, map.get("NXB_F014"), session);

            rule.updateCreditScore(userInfoMap, map.get("NXB_U001"), session);

            rule.createAge(userInfoMap, map.get("年龄"), session);

            rule.updateModality(userInfoMap, map.get("NXB_U005"), session);

            rule.createOutBlackList(userInfoMap, map.get("NXB_U007"), session);

            rule.createDeviceBlackList(userInfoMap, map.get("NXB_U010"), map.get("风控审批订单"),session);

            rule.createInBlackList(userInfoMap, map.get("NXB_U016"), session);
        }
    }

    /**
     * 获取牛小宝数据生成表中的数据
     * @param dataNo
     * @return
     */
    public Map<String, String> getNXBTestData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.nxbExcelPath, CommonUtils.nxbDataSheetName, dataNo);
        return map;
    }
}
