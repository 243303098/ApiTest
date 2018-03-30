package com.fkapi.vcc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fkapi.auth.createVccCustomer;
import com.fkapi.rule.rule;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import com.fkapi.auth.createUserInfo;
import com.fkapi.utils.ExcelUtils;

/**
 * @author Administrator
 */
public class CreateVccApplyTestData {

    private Map<String, String> userInfoMap ;

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, String> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public void create(String dataName, String userInfoNo, SqlSession session, SqlSession vccSession) {

        rule rule = new rule();
        createUserInfo cui = new createUserInfo();

        //创建基础用户信息并获取用户基础信息，并转成map形式存储（userinfo表）
        setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.userInfoSheetName,
                userInfoNo), true, session));

        //添加虚拟信用卡用户表信息
        createVccCustomer cvc = new createVccCustomer();
        cvc.create(userInfoMap, true, vccSession);

        if (!dataName.trim().isEmpty()) {
            Map<String, String> map = getVccApplyData(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.applyDataSheetName, dataName));

            //添加用户的上笔订单数据
            rule.createLastOrder(userInfoMap, map.get("上笔订单"), false, session);

            //根据通讯录联系人中的信息判断，添加相应的数据
            rule.createCustList(userInfoMap, map.get("通讯录联系人"), session);

            //根据是否全部为手机号中的信息判断，添加相应的数据
            rule.createMobileNo(userInfoMap, map.get("是否全部为手机号"), session);

            //根据住宅所在地中的信息判断，添加相应的数据
            rule.createHomeCity(userInfoMap, map.get("是否有住宅所在地"), session);

            //根据是否有户籍所在地中的信息判断，添加相应的数据
            rule.createCertCity(userInfoMap, map.get("是否有户籍所在地"), session);

            //根据是否有父母所在地中的信息判断，添加相应的数据
            rule.createParentCity(userInfoMap, map.get("是否有父母所在地"), session);

            //根据是否有学校号码段中的信息判断，添加相应的数据
            rule.createSchoolCity(userInfoNo, userInfoMap, map.get("是否有学校号码段"), session);

            //根据VA_F006中的信息判断，添加相应的数据
            rule.createVcc_VA_F006(userInfoNo, userInfoMap, map.get("VA_F006"), session);

            rule.createVcc_VA_F007(userInfoMap, map.get("VA_F007"), session);

            rule.createVcc_VA_F008(userInfoMap, map.get("VA_F008"), session);

            rule.createVcc_VA_F009(userInfoMap, map.get("VA_F009/VA_F010"), session, vccSession, true);

            rule.createVcc_VA_F011(userInfoMap, map.get("VA_F011"), session, vccSession, true);

            rule.createVcc_VA_F012(userInfoMap, map.get("VA_F012"), session);

            rule.createVcc_VA_F013(userInfoMap, map.get("VA_F013_用户名字"), session);

            rule.createVcc_VA_F014(userInfoMap, map.get("VA_F014"), session);

            rule.createVcc_VA_F015(userInfoMap, map.get("VA_F015"), session, vccSession);

            // 根据用户输入的年龄，更新对应的身份证信息
            rule.createAge(userInfoMap, map.get("年龄"), session);

            // 根据输入的信息判断是否将用户添加进黑名单
            rule.createInBlackList(userInfoMap, map.get("内部黑名单"), session);

            // 将用户输入的身份信息加入到黑名单中
            rule.createOutBlackList(userInfoMap, map.get("外部黑名单"), session);

            // 根据输入的信息判断是否将设备加入到黑名单
            rule.createDeviceBlackList(userInfoMap, map.get("设备黑名单"), userInfoMap.get("phoneAuth"), session);
        }
    }

    /**
     * 获取虚拟信用卡数据生成表的信息
     *
     * @param dataNo
     * @return 返回Excel的二维数据给dataprovider
     */
    public Map<String, String> getVccApplyData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.excelPath, CommonUtils.applyDataSheetName, dataNo);
        return map;
    }
}
