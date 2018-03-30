/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_third_accountMapper;
import com.fkapi.database.domain.p2p_third_account;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_third_accountService {
	p2p_third_accountMapper ptaMapper = null;
	p2p_cust_accountService pcaService = null;
	p2p_customerService pcService =null;
	public void addThirdAccount(String oldCustId, String custId,JSONObject certAuth, SqlSession session){
		delThirdAccount(oldCustId, certAuth, session);
		ptaMapper = session.getMapper(p2p_third_accountMapper.class);
		List<p2p_third_account> list = new ArrayList<>();
		p2p_third_account pta = new p2p_third_account();
		pcaService = new p2p_cust_accountService();
		pcService = new p2p_customerService();
		pta.setAcctNo(pcaService.getAccountNo(custId, session));
		pta.setRefNo(custId);
		pta.setThirdAcctNo(pcaService.getAccountNo(custId, session));
		pta.setThirdUserId(custId);
		pta.setUserRole(certAuth.getString("userRole"));
		pta.setCreateTime(CommonUtils.getCurDate("second"));
		pta.setUpdateTime(CommonUtils.getCurDate("second"));
		list.add(pta);
		Reporter.log("添加p2p_third_account表的参数为： " + certAuth.toString());
		try {
			ptaMapper.insert(list);
			Reporter.log("添加custId为： " + custId + "的p2p_third_account表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为： " + custId + "的p2p_third_account表的数据时发生异常，添加失败");
		}
	}
	
	public void delThirdAccount(String oldCustId,JSONObject json, SqlSession session){
		ptaMapper = session.getMapper(p2p_third_accountMapper.class);
		List<p2p_third_account> list = new ArrayList<>();
		pcService = new p2p_customerService();
		p2p_third_account pta = new p2p_third_account();
		pta.setRefNo(oldCustId);
		pta.setAcctNo(json.getString("cardNo"));
		list.add(pta);
		try {
			ptaMapper.deleteByCustIdAndAcctNo(list);
			//Reporter.log("根据custId: " + oldCustId + "或者acctNo: " + json.getString("cardNo") + "删除p2p_third_account表的数据成功");
		} catch (Exception e) {
			Reporter.log("根据custId: " + oldCustId + "或者acctNo: " + json.getString("cardNo") + "删除p2p_third_account表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
