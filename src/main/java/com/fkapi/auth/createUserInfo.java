package com.fkapi.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fkapi.service.*;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.RiskMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.utils.ExcelUtils;

public class createUserInfo {

	Map<String, String> map ;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, String> create(Integer userInfoNo, Boolean delHistoryOrder, SqlSession session) {
		p2p_customerService pcService;
		p2p_base_customerService pbcService ;
		p2p_cert_authService pcaService ;
		p2p_student_custService pscService ;
		p2p_student_infoService psiService ;
		ex_StudentCreditInfoService esService ;
		p2p_customer_educationService pceService ;
		p2p_customer_education_logService pcelService ;
		py_educationService peService ;
		p2p_linkface_authService plaService ;
		p2p_linkface_msgService plmService ;
		p2p_customer_contactorService pccService ;
		p2p_cust_top_contactorService pctcService ;
		p2p_juxinli_mobile_authService pjmaService ;
		p2p_juxinli_mobile_logService pjmlService ;
		p2p_cust_accountService pcacService ;
		p2p_account_bankcardService pabService ;
		p2p_base_accountService pbaService ;
		p2p_account_bankcard_logService pablService ;
		p2p_third_accountService ptaService ;
		p2p_account_checkService pacService ;
		p2p_cust_credit_infoService pcciService ;
		jxl_primary_infoService jpiService ;
		p2p_loan_claimService plcService ;
		p2p_repay_planService prpService ;
		risk_education_whiteListService rewService ;
		p2p_cooperation_companyService pccompanyService ;
		p2p_cooperation_employeeService pcemployeeService ;
		JSONObject json ;

		SqlSession riskSession = RiskMybatisUtils.getFactory().openSession(true);
		setMap(getUserInfoMap(CommonUtils.excelPath, CommonUtils.userInfoSheetName, userInfoNo));
		pcService = new p2p_customerService();
		pbcService = new p2p_base_customerService();
		if (delHistoryOrder){
			map.put("oldCustId",String.valueOf(pcService.getCustID(map.get("loginName"), session)));
		}else {
			map.put("oldCustId","");
		}
		// 添加p2p_customer表
		pcService.addCustomer(map.get("loginName"), map.get("platForm"), session);
		map.put("custId", pcService.getCustID(map.get("loginName"), session).toString());

		// 添加用户基础信息
		pbcService.addBaseCustomer(map.get("oldCustId"), map.get("custId"), session);

		// 添加身份认证
		if (!map.get("certAuth").trim().isEmpty()) {
			map.put("certAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("certAuth")), 1));
			pbcService = new p2p_base_customerService();
			pcacService = new p2p_cust_accountService();
			pabService = new p2p_account_bankcardService();
			pbaService = new p2p_base_accountService();
			pablService = new p2p_account_bankcard_logService();
			ptaService = new p2p_third_accountService();
			pacService = new p2p_account_checkService();
			pcciService = new p2p_cust_credit_infoService();
			json = new JSONObject(map.get("certAuth"));
			if (json.getString("certAuthStatus") != null
					&& json.getString("certAuthStatus").equals("AS")) {
				pcaService = new p2p_cert_authService();
				pcaService.addCertAuth(map.get("oldCustId"), map.get("custId"), json, session);
				pcacService.addCustAccout(map.get("oldCustId"), map.get("custId"), json, session);
				pabService.addAccountBankCard(map.get("custId"), json,
						session);
				pbaService.addBaseAccount(map.get("custId"), json, session);
				pablService.addAccountBankcarLog(map.get("custId"), json,
						session);
				ptaService.addThirdAccount(map.get("oldCustId"), map.get("custId"), json, session);
				pacService
						.addAccountCheckService(map.get("custId"), json, session);
				pcciService.addCustCreditInfo(map.get("oldCustId"), map.get("custId"), session);
				pbcService.update(map.get("custId"), json, "certAuth",
						session);
			}
		}
		// 手机号码认证
		if (!map.get("phoneAuth").trim().isEmpty()) {
			map.put("phoneAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("phoneAuth")), 1));
			json = new JSONObject(map.get("phoneAuth"));
			pbcService = new p2p_base_customerService();
			if (json.getString("phoneAuthStatus") != null
					&& json.getString("phoneAuthStatus").equals("AS")) {
				pcService = new p2p_customerService();
				pbcService.update(map.get("custId"), json, "phoneAuth",
						session);
				pcService.update(map.get("loginName"), json, "phoneAuth",
						session);
			}
		}
		// 添加学籍认证（学生）
		if (!map.get("schoolRollAuth").trim().isEmpty()) {
			map.put("schoolRollAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("schoolRollAuth")), 1));
			pbcService = new p2p_base_customerService();
			json = new JSONObject(map.get("schoolRollAuth"));
			if (json.get("schoolRollAuthStatus") != null
					&& json.getString("schoolRollAuthStatus").equals("AS")) {
				pscService = new p2p_student_custService();
				pscService.addStudentCust(map.get("custId"), json, session);
				psiService = new p2p_student_infoService();
				psiService.addStudentInfo(map.get("oldCustId"), map.get("custId"), json, session);
				esService = new ex_StudentCreditInfoService();
				esService.addExStudentInfo(map.get("oldCustId"), map.get("custId"),
						new JSONObject(map.get("certAuth")).getString("idNo"),
						json, session);
				pbcService.update(map.get("custId"), json, "schoolRollAuth",
						session);
			} else if (json.getString("schoolRollAuthStatus").equals("AF")) {
				pbcService.update(map.get("custId"), json, "schoolRollAuth",
						session);
			}
		}

		// 添加学历认证（成人）
		if (!map.get("educationAuth").trim().isEmpty()) {
			map.put("educationAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("educationAuth")), 1));
			pbcService = new p2p_base_customerService();
			json = new JSONObject(map.get("educationAuth"));
			if (json.get("educationAuthStatus") != null
					&& json.get("educationAuthStatus").equals("AS")) {
				pceService = new p2p_customer_educationService();
				pceService.addCustomerEducation(map.get("oldCustId"), map.get("custId"), json,
						session);
				pcelService = new p2p_customer_education_logService();
				pcelService.addCustomerEducationLog(map.get("oldCustId"), map.get("custId"), json,
						session);
				peService = new py_educationService();
				peService.addPyEducation(map.get("oldCustId"), map.get("custId"), new JSONObject(
						map.get("certAuth")).getString("custName"),
						new JSONObject(map.get("certAuth")).getString("idNo"),
						json, session);
				pbcService.update(map.get("custId"), json, "educationAuth",
						session);
			} else if (json.get("educationAuthStatus") != null
					&& json.get("educationAuthStatus").equals("AF")) {
				pcelService = new p2p_customer_education_logService();
				pcelService.addCustomerEducationLog(map.get("oldCustId"), map.get("custId"), json,
						session);
				pbcService.update(map.get("custId"), json, "educationAuth",
						session);
			}
		}

		// 添加头像认证
		if (!map.get("photoAuth").trim().isEmpty()) {
			map.put("photoAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("photoAuth")), 1));
			pbcService = new p2p_base_customerService();
			json = new JSONObject(map.get("photoAuth"));
			if (json.getString("photoAuthStatus") != null
					&& json.getString("photoAuthStatus").equals("AS")) {
				plaService = new p2p_linkface_authService();
				plaService.addLinkFaceAuth(map.get("oldCustId"), map.get("custId"), json, session);
				plmService = new p2p_linkface_msgService();
				plmService.addLinkFaceMsg(map.get("oldCustId"), map.get("custId"), new JSONObject(
						map.get("certAuth")).getString("custName"),
						new JSONObject(map.get("certAuth")).getString("idNo"),
						session);

				pbcService.update(map.get("custId"), json, "photoAuth",
						session);
			} else if (json.getString("photoAuthStatus") != null
					&& json.getString("photoAuthStatus").equals("AF")) {
				plaService = new p2p_linkface_authService();
				plaService.addLinkFaceAuth(map.get("oldCustId"), map.get("custId"), json, session);
				pbcService.update(map.get("custId"), json, "photoAuth",
						session);
			}
		}

		// 添加常用联系人认证
		if (!map.get("contractor").isEmpty()) {
			map.put("contractor", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("contractor")), 1));
			json = new JSONObject(map.get("contractor"));
			pccService = new p2p_customer_contactorService();
			pccService.addCustomerContactor(map.get("oldCustId"), map.get("custId"),
					json, session);
			pctcService = new p2p_cust_top_contactorService();
			pctcService.addCustTopContactor(map.get("oldCustId"), map.get("custId"),
					json, session);
		}

		// 添加通讯认证
		if (!map.get("jxlMobileAuth").trim().isEmpty()) {
			map.put("jxlMobileAuth", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("jxlMobileAuth")), 1));
			json = new JSONObject(map.get("jxlMobileAuth"));
			if (json.getString("jxlMobileAuthStatus").equals("AS")) {
				pjmaService = new p2p_juxinli_mobile_authService();
				pjmaService.addJuxinliMobileAuth(map.get("custId"), json,
						session);
				pjmlService = new p2p_juxinli_mobile_logService();
				pjmlService.addJuxinliMobileLog(map.get("oldCustId"), map.get("custId"),
						new JSONObject(map.get("phoneAuth"))
								.getString("mobile"), json, session);
				jpiService = new jxl_primary_infoService();
				jpiService.addPrimaryInfo(map.get("oldCustId"), map.get("custId"), json, session);
				//通讯认证已迁移至新风控系统
				risk_cust_mobile_authSerivce rcmaService  = new risk_cust_mobile_authSerivce();
				rcmaService.addCustMobileAuth(map, json);
			}
		}

		// 认证用户住宅地址
		if (!map.get("nowAddress").trim().isEmpty()) {
			map.put("nowAddress", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("nowAddress")), 1));
			json = new JSONObject(map.get("nowAddress"));
			pbcService = new p2p_base_customerService();
			if (json.getString("addrAuthStatus").equals("AS")) {
				pbcService.update(map.get("custId"), json, "addrAuth",
						session);
			}
		}

		//添加工作认证
		if (!map.get("工作认证").trim().isEmpty()){
			map.put("工作认证", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("工作认证")), 1));
			json = new JSONObject(map.get("工作认证"));
			pccompanyService = new p2p_cooperation_companyService();
			pcemployeeService = new p2p_cooperation_employeeService();
			pccompanyService.addCustCompany(json, session);
			pcemployeeService.addCooperationEmployee(map, json, session);
		}

		//添加用户的历史订单（是否存在逾期的订单）,如果为空则表示用户没有历史订单，用户为首次借款
		if (!map.get("historyOrder").trim().isEmpty()){
			map.put("historyOrder", ExcelUtils.getCellDate(CommonUtils.excelPath, CommonUtils.dataSheetName, ExcelUtils.getRowNoByValue(CommonUtils.excelPath, CommonUtils.dataSheetName, map.get("historyOrder")), 1));
			plcService = new p2p_loan_claimService();
			prpService = new p2p_repay_planService();
			JSONObject historyOrderJson ;
			//若状态为OVERDUE_REPAID则标识历史订单中有逾期的订单
			if(new JSONObject(map.get("historyOrder")).getString("repayStatus").equals("OVERDUE")){
				//构建历史订单数据
 				historyOrderJson = new JSONObject(map.get("historyOrder"));
				historyOrderJson.put("deviceCode",new JSONObject(map.get("phoneAuth")).getString("mobileSign"));
				plcService.addProject(map, historyOrderJson, false, delHistoryOrder, session);
				//构建repay_plan表中的数据
				historyOrderJson.put("allTerm", historyOrderJson.getInt("loanTerm"));
				historyOrderJson.put("repayPlan", new JSONArray("[ {\"time\": \""+ historyOrderJson.getInt("time") +"\" } ]"));
				prpService.addRepayPlan(map, historyOrderJson, session);
			}else if (new JSONObject(map.get("historyOrder")).getString("repayStatus").equals("WAIT_REPAY")){
				historyOrderJson = new JSONObject(map.get("historyOrder"));
				historyOrderJson.put("deviceCode", new JSONObject(map.get("phoneAuth")).getString("mobileSign"));
				plcService.addProject(map, historyOrderJson, false, delHistoryOrder, session);
				historyOrderJson.put("allTerm", historyOrderJson.getInt("loanTerm"));
				historyOrderJson.put("repayPlan", new JSONArray("[ { \"status\": \"WAIT_REPAY\", \"time\":\""+ historyOrderJson.getInt("time") +"\" } ]"));
				prpService.addRepayPlan(map, historyOrderJson, session);
			}else if (new JSONObject(map.get("historyOrder")).getString("repayStatus").length() < 1){
				historyOrderJson = new JSONObject(map.get("historyOrder"));
				historyOrderJson.put("status", "RETURN");
				historyOrderJson.put("deviceCode", new JSONObject(map.get("phoneAuth")).getString("mobileSign"));
				plcService.addProject(map, historyOrderJson, false, delHistoryOrder, session);
			}else if (new JSONObject(map.get("historyOrder")).getString("repayStatus").equals("SETTLED")){
				historyOrderJson = new JSONObject(map.get("historyOrder"));
				historyOrderJson.put("deviceCode", new JSONObject(map.get("phoneAuth")).getString("mobileSign"));
				plcService.addProject(map, historyOrderJson, false, delHistoryOrder, session);
				historyOrderJson.put("allTerm", historyOrderJson.getInt("loanTerm"));
				historyOrderJson.put("repayPlan", new JSONArray("[ { \"status\": \"NORMAL\", \"time\":\""+ historyOrderJson.getInt("time") +"\" } ]"));
				prpService.addRepayPlan(map, historyOrderJson, session);
			}
		}
		//判断用户是否为学历白名单用户，是则添加
		if (!map.get("isEducationWhite").trim().isEmpty()){
			rewService = new risk_education_whiteListService();
			if (map.get("isEducationWhite").toUpperCase().equals("Y")){
				rewService.addEducationWhiteList(map, riskSession);
			}
		}
		return map;
	}

	/**
	 * 根据userInfoNo获取userinfo表中的信息,并返回一个map类型
	 * 
	 * @param userInfoNo
	 *            ：userinfo表中的userInfoNo
	 * @return
	 */
	public Map<String, String> getUserInfoMap(String path, String sheetName, Integer userInfoNo) {
		Map<String, String> map ;
		int allColNum = 0;
		try {
			allColNum = ExcelUtils.getAllColNum(System.getProperty("user.dir")
					+ "\\testcase.xlsx", "userInfo", 0);
		} catch (IOException e) {
			Reporter.log("获取Excel的信息失败");
		}
		map = new HashMap<>();
		for (int j = 0; j < allColNum; j++) {
			map.put(ExcelUtils.getCellDate(path, sheetName, 0, j),
					ExcelUtils.getCellDate(path, sheetName, userInfoNo, j));
		}
		return map;
	}

	@Test
	public void test() {
	}
}
