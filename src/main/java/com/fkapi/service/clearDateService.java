/**
 * 
 */
package com.fkapi.service;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * @author Administrator
 *
 */
public class clearDateService {
	
	/**
	 * 删除测试数据
	 */
	public void delProject(String loginName,String certAuth, SqlSession session){
		p2p_customerService pcService = new p2p_customerService();
		p2p_base_customerService pbcService = new p2p_base_customerService();
		p2p_cert_authService pcaService = new p2p_cert_authService();
		p2p_student_infoService psiService = new p2p_student_infoService();
		ex_StudentCreditInfoService esService = new ex_StudentCreditInfoService();
		p2p_customer_educationService pceService = new p2p_customer_educationService();
		p2p_customer_education_logService pcelService = new p2p_customer_education_logService();
		py_educationService peService = new py_educationService();
		p2p_linkface_authService plaService = new p2p_linkface_authService();
		p2p_linkface_msgService plmService = new p2p_linkface_msgService();
		p2p_customer_contactorService pccService = new p2p_customer_contactorService();
		p2p_cust_top_contactorService pctcService = new p2p_cust_top_contactorService();
		p2p_juxinli_mobile_authService pjmaService = new p2p_juxinli_mobile_authService();
		p2p_juxinli_mobile_logService pjmlService = new p2p_juxinli_mobile_logService();
		risk_auth_jobService rajService = new risk_auth_jobService();
		p2p_cust_accountService pcacService = new p2p_cust_accountService();
		p2p_account_bankcardService pabService = new p2p_account_bankcardService();
		p2p_base_accountService pbaService = new p2p_base_accountService();
		p2p_account_bankcard_logService pablService = new p2p_account_bankcard_logService();
		p2p_third_accountService ptaService = new p2p_third_accountService();
		p2p_account_checkService pacService = new p2p_account_checkService();
		p2p_cust_credit_infoService pcciService = new p2p_cust_credit_infoService();
		jxl_primary_infoService jpiService = new jxl_primary_infoService();
		risk_auth_job_logService rajlService = new risk_auth_job_logService();
		risk_data_yicai_user_infoService rdyService = new risk_data_yicai_user_infoService();
		p2p_loan_claimService plcService = new p2p_loan_claimService();
		p2p_loan_claim_auditService plcaService = new p2p_loan_claim_auditService();
		p2p_cust_addr_listService pcalService = new p2p_cust_addr_listService();
		jxl_phone_call_recordsService jxlRecords = new jxl_phone_call_recordsService();
		p2p_cust_location_logService pcllService = new p2p_cust_location_logService();

		pcllService.delCustLocationLog(loginName, session);
		jxlRecords.delCallRecords(loginName, session);
		pcalService.delCustAddrList(loginName, session);
		plcaService.delAuditRe(loginName, session);
		plcService.delClaim(loginName, session);
		jpiService.delPrimaryInfo(loginName, session);
		pcciService.delCustCreditInfo(loginName, session);
		pacService.delAccountCheckService(new JSONObject(certAuth), session);
		ptaService.delThirdAccount(loginName,new JSONObject(certAuth), session);
		pablService.delAccountBankcarLog(new JSONObject(certAuth), session);
		pabService.delAccountBankCard(new JSONObject(certAuth), session);
		pbaService.delBaseAccount(new JSONObject(certAuth), session);
		pcacService.delCustAccout(loginName,new JSONObject(certAuth), session);
		pcaService.delCertAuth(loginName, session);
		psiService.delStudentInfo(loginName, session);
		esService.delExStudentInfo(loginName, session);
		pceService.delCustomerEducation(loginName, session);
		pcelService.delCustomerEducationLog(loginName, session);
		peService.delPyEducation(loginName, session);
		plaService.delLinkFaceAuth(loginName, session);
		plmService.delLinkFaceMsg(loginName, session);
		pccService.delCustomerContactor(loginName, session);
		pctcService.delCustTopContactor(loginName, session);
		pjmaService.delJuxinliMobileAuth(loginName, session);
		pjmlService.delJuxinliMobileLog(loginName, session);
		rdyService.delRiskDataYCUserInfo(loginName, session);
		rajlService.delRiskAuthJobLog(loginName, session);
		rajService.delRiskAuthJob(loginName, session);
		pbcService.delBaseCustomer(loginName, session);
		pcService.delCustomer(loginName, session);
		
	}
	
	@Test
	public void test(){
		//delProject("nxbnowAddr");
	}

}
