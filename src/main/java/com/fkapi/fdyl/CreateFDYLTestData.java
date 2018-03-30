package com.fkapi.fdyl;

import com.fkapi.auth.createUserInfo;
import com.fkapi.rule.rule;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/1.
 */
public class CreateFDYLTestData {

    private Map<String, String> userInfoMap ;

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
            Map<String, String> map = getFDYLTestData(ExcelUtils.getRowNoByValue(CommonUtils.fdylExcelPath, CommonUtils.fdylDataSheetName, dataName));
            //添加风控审批订单
            rule.createLastOrder(userInfoMap, map.get("风控审批订单"), true, session);
            //更新手机号使用时长
            rule.updateCallRecordsforFDYL_F001(userInfoMap, map.get("F001"), session);
            //更新每月联系人数据
            rule.updateCallRecordsforFDYL_F002(userInfoMap, map.get("F002"), session);
            //更新每月通话时长
            rule.updateCallRecordsforFDYL_F003(userInfoMap, map.get("F003"), session);
            //更新每月主叫天数
            rule.updateCallRecordsforFDYL_F004(userInfoMap, map.get("F004"), session);
            //更新F005/F006
            rule.updateCallRecordsforFDYL_F005(userInfoMap, map.get("F005/F006"), session);
            //更新F007
            rule.updateCallRecordsforFDYL_F007(userInfoMap, map.get("F007"), session);
            //更新F008
            rule.updateCustMobile(userInfoMap, map.get("F008"), session);
            //更新F009/F010
            rule.createVcc_VA_F009(userInfoMap, map.get("F009/F010"), session, vccSession, false);

            rule.updateCompanyInfo(userInfoMap, map.get("F014"), session, "companyScaleMax");
            //更新F015
            rule.updateCallRecordsforFDYL_F015(userInfoMap, map.get("F015"), session);
            //更新年龄
            rule.createAge(userInfoMap, map.get("U001"), session);
            //更新内部黑名单
            rule.createInBlackList(userInfoMap, map.get("U002"), session);
            //更新外部黑名单
            rule.createOutBlackList(userInfoMap, map.get("U003"), session);
            //更新工作入职日期
            rule.updateWorkInfo(userInfoMap, map.get("U005"), session, "entryDate");
            //更新劳动关系
            rule.updateWorkInfo(userInfoMap, map.get("U006"), session, "laborRelationsType");
            //添加上笔订单
            rule.createLastOrder(userInfoMap, map.get("U008"), false, session);
            //更新工作状态
            rule.updateWorkInfo(userInfoMap, map.get("U009"), session, "workStatus");
            //更新最大薪资
            rule.updateWorkInfo(userInfoMap, map.get("U010"), session, "monthlySalaryMax");
        }
    }

    /**
     * 获取FDYL数据生成表中的数据
     * @param dataNo
     * @return
     */
    public Map<String, String> getFDYLTestData(Integer dataNo) {
        CommonUtils commonUtils = new CommonUtils();
        Map<String, String> map = commonUtils.getTestData(CommonUtils.fdylExcelPath, CommonUtils.fdylDataSheetName, dataNo);
        return map;
    }
}
