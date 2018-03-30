/**
 * 
 */
package com.fkapi.yc;

import java.io.IOException;
import java.util.Map;

import com.fkapi.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.fkapi.auth.createUserInfo;
import com.fkapi.service.p2p_loan_claim_auditService;
import com.fkapi.service.risk_credit_yicai_tiqianhuaService;

/**
 * @author Administrator String age, String isNwBlack, String isOutBlack, String
 *         isDeviceBlack, String isYCEm, String lastProject,
 */
@Listeners({ AssertionListener.class })
public class ExcuteYiCaiCase {
	private String excelPath = System.getProperty("user.dir")
			+ "\\testcase.xlsx";
	private String sheetName = "信用审批case";
	static String userInfoSheetName = "userInfo";
	private String result = null;

	@Test(dataProvider = "getData")
	public void excute(String caseName, String isRun, String userInfoNo, String ycAuthNo,
			String dataNo, String proName, String remark, String expect,
			String actual, String auditResult) throws IOException,
			InterruptedException {

		risk_credit_yicai_tiqianhuaService rtService ;
		p2p_loan_claim_auditService plcaService ;
		Map<String, String> userInfoMap = null;
		Map<String, String> ycAuthMap ;
		createUserInfo cui = new createUserInfo();
		// ycAuthInfo yac = new ycAuthInfo();
		CreateYcTestData ctd = new CreateYcTestData();
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		SqlSession vccSession = VCCMybatisUtils.getFactory().openSession(true);
		Reporter.log("              ");
		Reporter.log("----------------------------------当前执行的caseNo为： "
				+ dataNo + "--------------------------------------------");
		Reporter.log("               ");
		try {
			// 获取userinfo表的基础信息
			userInfoMap = cui.getUserInfoMap(excelPath, userInfoSheetName, ExcelUtils.getRowNoByValue(
					excelPath, "userInfo", userInfoNo));
		} catch (Exception e2) {
			Reporter.log("获取用户基础信息或易才认证信息时出现异常" + e2.getMessage());
		}

		// 创建易才测试case
		if (userInfoNo != null && ycAuthNo != null && dataNo != null) {
			ctd.creditYCTestData(ycAuthNo, userInfoNo,
					ExcelUtils.getRowNoByValue(excelPath, "易才数据生成", dataNo),
					session, vccSession);

			// 获取易才认证表的信息，新增了projectNo
			ycAuthMap = ctd.ycAuthMap;

			// 修改易才可借额度，若不修改额度则风控审批无法进行
			rtService = new risk_credit_yicai_tiqianhuaService();
			rtService.updateLimit(5000, userInfoMap, session);

			// 清除风控审批数据，并请求风控接口
			plcaService = new p2p_loan_claim_auditService();
			plcaService.delAuditRe(userInfoMap.get("oldCustId"), session);

			// 判断预期结果
			if (proName != null && remark != null && expect != null) {
				// 请求风控审批接口并根据remark获取最终的审批结果
				Post.postYcCredit(ycAuthMap.get("projectNo"), proName);
				try{
					result = plcaService.getAuditRe(
							userInfoMap.get("custId"), remark, session);
				}catch (NullPointerException e){
					Reporter.log("获取审批结果时发生异常，获取失败" + e.getMessage());
				}
				// 向Excel中写入实际结果
				try {
					ExcelUtils.setCellData(result, ExcelUtils.getRowNoByValue(
							excelPath, sheetName, caseName), ExcelUtils
							.getColNoByValue(excelPath, sheetName, "实际结果"),
							excelPath, sheetName);
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
							.getRowNoByValue(excelPath, sheetName, caseName),
							ExcelUtils.getColNoByValue(excelPath, sheetName,
									"是否通过"), excelPath, sheetName);
					Reporter.log("用例执行结果为：" + finalResult);
				} catch (Exception e) {
					Reporter.log("插入执行结果时发生异常，插入失败");
				}
				// 断言，判断实际结果与预期结果是否一致
				Assertion.verifyEquals(result, expect);
				Reporter.log("               ");
				Reporter.log("-----------------------------------caseNo： "
						+ dataNo
						+ "执行完毕---------------------------------------------");
				Reporter.log("               ");
				session.close();
			} else {
				Reporter.log("请填写预期结果！");
				Assertion.verifyTure(false);
			}
		}else {
			Reporter.log("请选择要创建的用户信息");
			Assertion.verifyTure(false);
		}
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		return ExcelUtils.excelToDateMap(excelPath, sheetName);
	}
}
