package com.fkapi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fkapi.database.dao.p2p_loan_claim_auditMapper;
import com.fkapi.database.domain.p2p_loan_claim_audit;
import com.fkapi.utils.MybatisUtils;

public class p2p_loan_claim_auditService {
	p2p_loan_claim_auditMapper plcauditMapper = null;

	/**
	 * 根据订单号，风控规则码，用户ID 获取对应的风控审批结果
	 * 
	 * @return 风控审批结果
	 */
	public String getAuditRe(String custId, String remark, SqlSession session) {
		plcauditMapper = session.getMapper(p2p_loan_claim_auditMapper.class);
		//p2p_customerService pcService = new p2p_customerService();
//		Map<String,Object> map=new HashMap<>();
//		map.put("operator", custId);
//		map.put("remark", remark);
		p2p_loan_claim_audit plca = new p2p_loan_claim_audit();
		plca.setOperator(Long.valueOf(custId));
		plca.setRemark(remark);

		try {
			List<p2p_loan_claim_audit> list = plcauditMapper.selectAuditReByProAndCust(plca);
			if(list.isEmpty()){
				Reporter.log("根据custId: " + custId + "remark: " + remark + "未获取到风控审批记录");
				return null;
			}else {
				Reporter.log("根据custId: " + custId + "remark: " + remark + "获取的风控审批记录为 " + list.get(0).getOperateType());
				return list.get(0).getOperateType();
			}
		} catch (Exception e) {
			Reporter.log("根据custId: " + custId + "remark: " + remark + "获取风控审批记录时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}

	/**
	 * 根据Operator即用户的cust_id删除风控审批记录
	 */
	public void delAuditRe(String oldCustId, SqlSession session) {
		plcauditMapper = session.getMapper(p2p_loan_claim_auditMapper.class);
		try {
			plcauditMapper.deleteByOperator(Long.valueOf(oldCustId));
			//Reporter.log("根据custId:" + custId + "删除风控审批记录成功");
		} catch (Exception e) {
			Reporter.log("根据custId:" + oldCustId + "删除风控审批记录时发生异常，删除失败" + e.getMessage());
		}
	}

	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		System.out.println(getAuditRe("ceshi01", "P001",session));
	}
}
