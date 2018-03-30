package com.fkapi.vcc;

import com.fkapi.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */
@Listeners({AssertionListener.class})
public class ExcuteVccLimitCase {
    static String excelPath = System.getProperty("user.dir")
            + "\\testcase.xlsx";
    static String sheetName = "虚拟信用卡额度case";

    @Test(dataProvider = "getData")
    public void excute(String caseName, String isRun, String userInfoNo, String dataName,
                       String expect) {
        Map<String, String> userInfoMap;
        CreateVccApplyTestData cvtd = new CreateVccApplyTestData();
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        SqlSession vccSession = VCCMybatisUtils.getFactory().openSession(true);
        Reporter.log("               ");
        Reporter.log("************** 当前执行的caseNo为： " + caseName + " **************");
        Reporter.log("               ");
        if (!userInfoNo.isEmpty()) {
            // 创建用户信息
            cvtd.create(dataName, userInfoNo, session, vccSession);

            // 获取userinfo表的基础信息
            userInfoMap = cvtd.getUserInfoMap();

            if (expect != null) {
                //请求风控审批接口，并获取额度
                JSONObject responseJson = new JSONObject(Post.postVccLimit(userInfoMap.get("custId")));
                String resultLimit = responseJson.getJSONObject("body").getString("credit_line");
                // 向Excel中写入实际结果
                try {
                    ExcelUtils.setCellData(resultLimit, ExcelUtils.getRowNoByValue(
                            excelPath, sheetName, caseName), ExcelUtils
                                    .getColNoByValue(excelPath, sheetName, "实际结果"),
                            excelPath, sheetName);
                    Reporter.log("期望结果为：" + expect);
                    Reporter.log("实际结果为：" + resultLimit);
                } catch (Exception e1) {
                    Reporter.log("插入实际结果时发生异常，插入失败");
                }

                String finalResult = resultLimit.equals(expect) ? "Passed"
                        : "Failed";
                // //向Excel中写入是否通过
                try {
                    ExcelUtils.setCellData(finalResult, ExcelUtils
                                    .getRowNoByValue(excelPath, sheetName, caseName),
                            ExcelUtils.getColNoByValue(excelPath, sheetName,
                                    "是否通过"), excelPath, sheetName);
                    Reporter.log("用例执行结果为：" + finalResult);
                } catch (Exception e) {
                    Reporter.log("插入执行结果时发生异常，插入失败");
                }

                // 断言，判断实际结果与预期结果是否一致
                Assertion.verifyEquals(resultLimit, expect);
                Reporter.log("               ");
                Reporter.log("************** caseNo： " + caseName + " 执行完毕 **************");
                Reporter.log("               ");
                Reporter.log("               ");
                session.close();
            }
        } else {
            Reporter.log("请选择要创建的用户信息");
            Assertion.verifyTure(false);
        }
    }

    @DataProvider
    public Object[][] getData() throws Exception {
        return ExcelUtils.excelToDateMap(excelPath, sheetName);
    }
}
