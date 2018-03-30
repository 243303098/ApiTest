package com.fkapi.ndk;

import com.fkapi.service.p2p_loan_claim_auditService;
import com.fkapi.service.p2p_loan_claim_relative_appService;
import com.fkapi.service.risk_audit_item_logService;
import com.fkapi.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/21.
 */
@Listeners({ AssertionListener.class })
public class ExcuteNDKCase {
    @Test(dataProvider = "getData")
    public void excute(String caseName, String isRun, String userInfoNo, String dataName,
                       String remark, String expect) {

        Map<String, String> userInfoMap;
        p2p_loan_claim_auditService plcaService;
        risk_audit_item_logService railService ;
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        SqlSession riskSession = RiskMybatisUtils.getFactory().openSession(true);
        Reporter.log("               ");
        Reporter.log("************** 当前执行的caseNo为： " + caseName + " **************");
        Reporter.log("               ");
        if (!userInfoNo.isEmpty()) {
            // 创建用户信息并获取userinfo表的基础信息
            CreateNDKTestData cndktd = new CreateNDKTestData();
            cndktd.create(dataName, userInfoNo, session, riskSession);
            userInfoMap = cndktd.getUserInfoMap();

            if (remark != null && expect != null) {
                // 清除风控审批数据,新风控审批记录在risk中,原风控审批记录在opr中,牛大咖中暂时抽出
                //plcaService = new p2p_loan_claim_auditService();
                //plcaService.delAuditRe(userInfoMap.get("oldCustId"), session);
                railService = new risk_audit_item_logService();
                railService.delAuditResult(Long.valueOf(userInfoMap.get("oldCustId")), riskSession);
                //请求牛大咖风控审批接口
                //Post.postNDK(
                        //userInfoMap.get("custId"), userInfoMap.get("projectNo"), "NDK_LOAN_AUDIT");
                //请求牛大咖1.9.5版本风控审批接口
                Post.postNDK_195(userInfoMap, "NDK_LOAN_AUDIT");
                //获取最终的审批结果
                //String result = plcaService.getAuditRe(userInfoMap.get("custId"), remark, session);
                String result = railService.getAuditResult(Long.valueOf(userInfoMap.get("custId")), remark, riskSession);
                // 向Excel中写入实际结果
                try {
                    ExcelUtils.setCellData(result,
                            ExcelUtils.getRowNoByValue(CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName, caseName),
                            ExcelUtils.getColNoByValue(CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName, "实际结果"),
                            CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName);
                    Reporter.log("期望结果为：" + expect);
                    Reporter.log("实际结果为：" + result);
                } catch (Exception e1) {
                    Reporter.log("插入实际结果时发生异常，插入失败");
                }

                String finalResult = result.equals(expect) ? "Passed"
                        : "Failed";
                // //向Excel中写入是否通过；
                try {
                    ExcelUtils.setCellData(finalResult,
                            ExcelUtils.getRowNoByValue(CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName, caseName),
                            ExcelUtils.getColNoByValue(CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName, "是否通过"),
                            CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName);
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
            }else {
                Reporter.log("请选择要创建的用户信息");
                Assertion.verifyTure(false);
            }
        }
    }

    @BeforeMethod
    public void clearTestData(){
        //清楚redis中的缓存
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.clearRedis();
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        p2p_loan_claim_relative_appService plcraService = new p2p_loan_claim_relative_appService();
        plcraService.delLoanDevice("999999999", session);
        plcraService.delLoanDevice("888888888", session);
    }

    @DataProvider
    public Object[][] getData() throws Exception {
        return ExcelUtils.excelToDateMap(CommonUtils.ndkExcelPath, CommonUtils.ndkCaseSheetName);
    }
}