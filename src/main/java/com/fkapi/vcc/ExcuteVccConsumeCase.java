package com.fkapi.vcc;

import com.fkapi.service.p2p_loan_claim_auditService;
import com.fkapi.service.risk_audit_item_logService;
import com.fkapi.service.risk_virtual_credit_cardService;
import com.fkapi.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/20.
 */
@Listeners({ AssertionListener.class })
public class ExcuteVccConsumeCase {

    risk_virtual_credit_cardService rvccService ;

    @Test(dataProvider = "getData")
    public void excute(String caseName, String isRun, String userInfoNo, String dataName,
                       String remark, String expect){
        Map<String, String> userInfoMap ;
        p2p_loan_claim_auditService plcaService ;
        risk_audit_item_logService railService ;
        CreateVccConsumeTestData cvctd = new CreateVccConsumeTestData();
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        SqlSession vccSession = VCCMybatisUtils.getFactory().openSession(true);
        SqlSession riskSession = RiskMybatisUtils.getFactory().openSession(true);
        Reporter.log("               ");
        Reporter.log("************** 当前执行的caseNo为： "	+ caseName + " **************");
        Reporter.log("               ");
        if (!userInfoNo.isEmpty()){
            cvctd.create(dataName, userInfoNo, session, vccSession, riskSession);
            userInfoMap = cvctd.getUserInfoMap();

            //新增的最大额度默认为200
            rvccService = new risk_virtual_credit_cardService();
            rvccService.addRiskVirtualCreditCard(userInfoMap, riskSession);

            if (remark != null && expect != null) {
                // 清除风控审批数据
                //plcaService = new p2p_loan_claim_auditService();
                //plcaService.delAuditRe(userInfoMap.get("oldCustId"), session);
                railService = new risk_audit_item_logService();
                railService.delAuditResult(Long.valueOf(userInfoMap.get("oldCustId")), riskSession);

                // 请求风控审批接口并根据remark获取最终的审批结果
                Post.postVccConsume(userInfoMap.get("custId"), "000000000");
                String result = railService.getAuditResult(Long.valueOf(userInfoMap.get("custId")), remark, riskSession);
                // 向Excel中写入实际结果
                try {
                    ExcelUtils.setCellData(result, ExcelUtils.getRowNoByValue(
                            CommonUtils.excelPath, CommonUtils.consumeCasesheetName, caseName), ExcelUtils
                                    .getColNoByValue(CommonUtils.excelPath, CommonUtils.consumeCasesheetName, "实际结果"),
                            CommonUtils.excelPath, CommonUtils.consumeCasesheetName);
                    Reporter.log("期望结果为：" + expect);
                    Reporter.log("实际结果为：" + result);
                } catch (Exception e1) {
                    Reporter.log("插入实际结果时发生异常，插入失败");
                }
                String finalResult = result.equals(expect) ? "Passed"
                        : "Failed";
                // //向Excel中写入是否通过
                try {
                    ExcelUtils.setCellData(finalResult, ExcelUtils
                                    .getRowNoByValue(CommonUtils.excelPath, CommonUtils.consumeCasesheetName, caseName),
                            ExcelUtils.getColNoByValue(CommonUtils.excelPath, CommonUtils.consumeCasesheetName,
                                    "是否通过"), CommonUtils.excelPath, CommonUtils.consumeCasesheetName);
                    Reporter.log("用例执行结果为：" + finalResult);
                } catch (Exception e) {
                    Reporter.log("插入执行结果时发生异常，插入失败");
                }

                // 断言，判断实际结果与预期结果是否一致
                Assertion.verifyEquals(result, expect);
                Reporter.log("               ");
                Reporter.log("************** caseNo： " + caseName + " 执行完毕 **************");
                Reporter.log("               ");
                Reporter.log("               ");
                session.close();
            }
        }else {
            Reporter.log("请选择要创建的用户信息");
            Assertion.verifyTure(false);
        }
    }

    @DataProvider
    public Object[][] getData() throws Exception {
        return ExcelUtils.excelToDateMap(CommonUtils.excelPath, CommonUtils.consumeCasesheetName);
    }
}
