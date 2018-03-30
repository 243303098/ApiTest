package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_loan_claimMapper;
import com.fkapi.database.domain.p2p_loan_claim;
import com.fkapi.utils.CommonUtils;

/**
 * 对p2p_loan_claim表进行增删改查等操作
 * 
 * @author Administrator
 * 
 */
public class p2p_loan_claimService {
	p2p_loan_claim_relative_appService plcraService ;
	p2p_loan_claimMapper plcMapper = null;
	p2p_customerService pcService = null;
	p2p_loan_claim_extSerivce plceService = null ;
	private String projectNo = null;
	
	public p2p_loan_claimService() {
		setProjectNo(CommonUtils.getProjectNo());
	}
	
	/**
	 * 根据用户的cust_id删除订单,及其他附属信息
	 */
	public void delClaim(String oldCustId, SqlSession session) {
		List<p2p_loan_claim> list = new ArrayList<>();
		plcMapper = session.getMapper(p2p_loan_claimMapper.class);
		pcService = new p2p_customerService();
		p2p_loan_claim plc = new p2p_loan_claim();
		plc.setCustId(Long.valueOf(oldCustId));
		list.add(plc);
		// 删除订单
		try {
			plcMapper.deleteByCustID(list);
		} catch (Exception e) {
			Reporter.log("根据custId： " + oldCustId + "删除p2p_loan_claim表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 添加订单信息
	 * @param userinfoMap
	 * @param json
	 * @param isCreditOrder 是否为风控审批订单如果是的话 则添加风控审批订单不是则添加历史订单
	 * @param session
	 */
	public void addProject(Map<String, String> userinfoMap, JSONObject json, Boolean isCreditOrder, Boolean delHistoryOrder, SqlSession session) {
		if (delHistoryOrder){
			delClaim(userinfoMap.get("oldCustId"), session);
		}
		plcMapper = session.getMapper(p2p_loan_claimMapper.class);
		List<p2p_loan_claim> list = new ArrayList<>();
		pcService = new p2p_customerService();
		p2p_loan_claim plc = new p2p_loan_claim();
		//this.setProjectNo(CommonUtils.getProjectNo());
		userinfoMap.put("time",json.getString("time"));
		userinfoMap.put("projectNo", CommonUtils.getProjectNo());
		//plc.setProjectNo(this.getProjectNo());
		plc.setProjectName(json.getString("projectName"));
		if(json.getString("loanSubSrc").equals("YICAI")){
			plc.setProjectChannel("易才");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").equals("CHUBAO")){
			plc.setProjectChannel("触宝");
			plc.setLoanSrc("BDF");
		}else if (json.getString("loanSubSrc").equals("LD")){
			plc.setProjectChannel("朗顿");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").contains("YHJ")){
			plc.setProjectChannel("壹加众达网络科技（北京）有限公司");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").contains("NDK")){
			plc.setProjectChannel("牛大咖");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").contains("NXB")){
			plc.setProjectChannel("牛小宝");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").contains("NKK")){
			plc.setProjectChannel("牛大款");
			plc.setLoanSrc("APP");
		}else if (json.getString("loanSubSrc").contains("FD")) {
			plc.setProjectChannel("测试复大医疗");
			plc.setLoanSrc("BDF");
		}
		plc.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
		plc.setLoanTerm(json.getInt("loanTerm"));
		if(json.getInt("loanTerm") == 1){
			plc.setTimeType("D");
		}else {
			plc.setTimeType("M");
		}
		//判断如果是风控审批订单，则借款日期为当天，如果是历史订单则添加相对于的借款时间

		if(isCreditOrder){
			plc.setRepayStatus(null);
			plc.setIsOverdue("N");
			plc.setOverdueDays(null);
			plc.setReleaseStatus("");
			plc.setAuditStatus("LOAN");
			plc.setProjectNo(userinfoMap.get("projectNo"));
			plc.setLoanDate(CommonUtils.subMin(CommonUtils.getCurDate("second"), 1));
			plc.setNextRepayDate(CommonUtils.subMonth(CommonUtils.getCurDate("second"),-1));
		}else {
			if(json.getString("auditStatus").toUpperCase().equals("LOANED")){
				plc.setReleaseStatus("MATCHED");
				plc.setAuditStatus("LOANED");
			}else if(json.getString("auditStatus").toUpperCase().equals("RETURN")){
				plc.setReleaseStatus("REJECT_AUDIT");
				plc.setAuditStatus("RETURN");
			}

			plc.setRepayStatus(json.getString("repayStatus"));
			if (json.getString("repayStatus").equals("OVERDUE")){
				plc.setIsOverdue("Y");
				plc.setOverdueDays(json.getInt("time"));
			}else {
				plc.setIsOverdue("N");
				plc.setOverdueDays(null);
			}
			//this.setProjectNo(CommonUtils.getProjectNo());
			plc.setProjectNo(userinfoMap.get("projectNo"));
			//借款当天也算一天，所以要减去一天
			plc.setLoanDate(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
			plc.setNextRepayDate(CommonUtils.subMonth(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")-1),1));
		}
		plc.setCustId(Long.valueOf(userinfoMap.get("custId")));
		plc.setBorrowerName("测试");
		//设置借款来源
		plc.setLoanSubSrc(json.getString("loanSubSrc"));
		plc.setPrjCfgType(json.getString("loanSubSrc"));
		list.add(plc);
		//添加订单
		Reporter.log("添加p2p_loan_claim表的参数为： " + json.toString());
		try {
			plcMapper.insert(list);
			Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的p2p_loan_claim表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的p2p_loan_claim表的数据时发生异常,添加失败" + e.getMessage());
		}
		if (json.getString("loanSubSrc").equals("CHUBAO")){
			plceService = new p2p_loan_claim_extSerivce();
			plceService.addLoanClaimExt(userinfoMap.get("projectNo"), "CHUBAO_S", session);
		}else if (json.getString("loanSubSrc").equals("FD")) {
			plceService = new p2p_loan_claim_extSerivce();
			plceService.addLoanClaimExt(userinfoMap.get("projectNo"), "FDD", session);
		}else {
			plceService = new p2p_loan_claim_extSerivce();
			plceService.addLoanClaimExt(userinfoMap.get("projectNo"), "NIUWA", session);
		}

		//添加借款订单相关的设备
		plcraService = new p2p_loan_claim_relative_appService();
		plcraService.addLoanDevice(userinfoMap.get("projectNo"), new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign"), session);
		//添加借款快照表
		if (json.getString("loanSubSrc").contains("FD")){
			p2p_loan_claim_snapshootService plcsService = new p2p_loan_claim_snapshootService();
			plcsService.addLoanClaimSnapshoot(userinfoMap, userinfoMap.get("projectNo"), session);
		}
	}
	

	@Test
	public void test() {
		
	}

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return this.projectNo;
	}

	/**
	 * @param projectNo the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

}
