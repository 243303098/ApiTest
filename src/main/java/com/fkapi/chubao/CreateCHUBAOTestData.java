package com.fkapi.chubao;

import com.fkapi.auth.createUserInfo;
import com.fkapi.rule.rule;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public class CreateCHUBAOTestData {
    private Map<String, String> userInfoMap ;

    public Map<String, String> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, String> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public void create(String dataName, String userInfoNo, SqlSession session){
        SqlSession vccSession = null;
        rule rule = new rule();
        createUserInfo cui = new createUserInfo();
        //创建用户数据信息并保存
        setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.userInfoSheetName,
                userInfoNo), true, session));

        if (!dataName.trim().isEmpty()) {
            Map<String, String> map = getCHUBAOTestData(ExcelUtils.getRowNoByValue(CommonUtils.chubaoExcelPath, CommonUtils.chubaoDataSheetName, dataName));
            //添加风控审批订单
            rule.createLastOrder(userInfoMap, map.get("风控审批订单"), true, session);
            //添加用户的上笔订单
            rule.createLastOrder(userInfoMap, map.get("上笔订单"), false, session);
            //更新用户身份信息及联系人信息
            rule.createCertCity(userInfoMap, map.get("F003"), session);
            //添加用户累计借款信息
            rule.createVcc_VA_F009(userInfoMap, map.get("F004/F005"), session, vccSession, false);
            //更新用户身份证信息
            rule.updateIdCardNo(userInfoMap, map.get("F007"), session);
            //更新用户年龄
            rule.createAge(userInfoMap, map.get("年龄"), session);
            //添加外部黑名单
            rule.createOutBlackList(userInfoMap, map.get("外部黑名单"), session);
            //添加内部黑名单
            rule.createInBlackList(userInfoMap, map.get("内部黑名单"), session);
            //添加设备黑名单
            rule.createDeviceBlackList(userInfoMap, map.get("设备黑名单"), map.get("风控审批订单"), session);
            //更新用户的账户状态
            rule.createCustStatus(userInfoMap, map.get("账户状态"), session);

        }
    }

    /**
     * 获取触宝测试数据生成表中的数据
     * @param dataNo
     * @return
     */
    public Map<String, String> getCHUBAOTestData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.chubaoExcelPath, CommonUtils.chubaoDataSheetName, dataNo);
        return map;
    }
}
