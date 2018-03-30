/**
 * 
 */
package com.fkapi.yc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.auth.createUserInfo;
import com.fkapi.service.p2p_customer_companyService;
import com.fkapi.service.p2p_loan_claimService;
import com.fkapi.service.p2p_loan_claim_relative_appService;
import com.fkapi.service.risk_auth_jobService;
import com.fkapi.service.risk_auth_job_logService;
import com.fkapi.service.risk_data_yicai_user_infoService;
import com.fkapi.utils.ExcelUtils;

/**
 * @author Administrator
 * 
 */
public class YcAuthInfo {

	private String excelPath = System.getProperty("user.dir")
			+ "\\testcase.xlsx";
	private String sheetName = "易才认证";

	public Map<String, String> creditYCAuth(Integer ycAuthNo, String userInfoNo,SqlSession session) {
		CreateYcTestData cytd ;
		p2p_loan_claimService plcService ;
		p2p_customer_companyService pccService ;
		risk_auth_jobService rajService ;
		risk_auth_job_logService rajlService ;
		risk_data_yicai_user_infoService rdyService ;
		p2p_loan_claim_relative_appService plcraService = new p2p_loan_claim_relative_appService();

		JSONObject json ;
		int allColNum = 0;
		Map<String, String> userInfoMap = null;
		Map<String, String> ycAuthMap ;
		try {
			allColNum = ExcelUtils.getAllColNum(System.getProperty("user.dir")
					+ "\\testcase.xlsx", "易才认证", 0);
		} catch (IOException e) {
			// log.error(e.getMessage());
		}
		ycAuthMap = new HashMap<>();
		for (int j = 0; j < allColNum; j++) {
			ycAuthMap.put(ExcelUtils.getCellDate(excelPath, sheetName, 0, j),
					ExcelUtils.getCellDate(excelPath, sheetName, ycAuthNo, j));
		}
		cytd = new CreateYcTestData();
		// 创建用户，并获取所创建用户的信息
		try {
			userInfoMap = cytd.getUserInfoMap();
		} catch (Exception e) {
			Reporter.log("根据userInfoNo： " + userInfoNo + "获取基础用户信息失败 "
					+ e.getMessage());
		}
		plcService = new p2p_loan_claimService();
		
		// 认证用户公司信息
		if (ycAuthMap.get("工作认证") != null) {
			json = new JSONObject(ycAuthMap.get("工作认证"));
			pccService = new p2p_customer_companyService();
			rajService = new risk_auth_jobService();
			rajlService = new risk_auth_job_logService();
			rdyService = new risk_data_yicai_user_infoService();
			if (json.getString("jobAuth").equals("AS")) {
				rajService.addRiskAuthJob(userInfoMap.get("oldCustId"), userInfoMap.get("custId"),
						json.getString("jobAuth"),session);
				pccService.addCustomerCompany(userInfoMap.get("oldCustId"), userInfoMap.get("custId"),
						json,session);
				rajlService.addRiskAuthJobLog(userInfoMap.get("oldCustId"), userInfoMap.get("custId"),
						json,session);
				rdyService.addRiskDataYCUserInfo(userInfoMap.get("oldCustId"), userInfoMap.get("custId"),
						new JSONObject(userInfoMap.get("custId")), json, session);
			}
		}
		// 创建用户的借款订单
		if (ycAuthMap.get("借款订单") != null) {
			json = new JSONObject(ycAuthMap.get("借款订单"));
			plcService.addProject(userInfoMap, json, true, true, session);
			ycAuthMap.put("projectNo", plcService.getProjectNo());
			//添加借款设备，若不添加借款设备则风控审批时会报异常
			plcraService.addLoanDevice(plcService.getProjectNo(), json.getString("deviceCode"), session);
		}
		return ycAuthMap;
	}
	
	/**
	 * 返回ycauth的参数信息
	 * @param ycAuthNo
	 * @return
	 */
	public Map<String, String> getYcAuthMap(Integer ycAuthNo) {
		Map<String, String> ycAuthMap ;
		int allColNum = 0;
		try {
			allColNum = ExcelUtils.getAllColNum(System.getProperty("user.dir")
					+ "\\testcase.xlsx", "易才认证", 0);
		} catch (IOException e) {
			// log.error(e.getMessage());
		}
		ycAuthMap = new HashMap<>();
		for (int j = 0; j < allColNum; j++) {
			ycAuthMap.put(ExcelUtils.getCellDate(excelPath, sheetName, 0, j),
					ExcelUtils.getCellDate(excelPath, sheetName, ycAuthNo, j));
		}

		return ycAuthMap;
	}

	@Test
	public void test() {
		getYcAuthMap(1);
	}
}
