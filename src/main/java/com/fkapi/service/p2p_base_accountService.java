/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_base_accountMapper;
import com.fkapi.database.domain.p2p_base_account;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_base_accountService {
	Log log = new Log(p2p_base_accountService.class);
	p2p_base_accountMapper pbaMapper = null;
	p2p_cust_accountService pcaService = null;
	
	/**
	 * 添加账户基础信息
	 * @param custId
	 * @param certAuth
	 */
	public void addBaseAccount(String custId,JSONObject certAuth, SqlSession session){
		delBaseAccount(certAuth, session);
		pbaMapper = session.getMapper(p2p_base_accountMapper.class);
		List<p2p_base_account> list = new ArrayList<>();
		p2p_base_account pba = new p2p_base_account();
		pcaService = new p2p_cust_accountService();
		pba.setAcctNo(pcaService.getAccountNo(custId, session));
		pba.setAcctName(certAuth.getString("custName"));
		pba.setOpenTime(CommonUtils.getCurDate("second"));
		pba.setCreateTime(CommonUtils.getCurDate("second"));
		list.add(pba);
		Reporter.log("添加p2p_base_account表的参数为： " + certAuth.toString());
		log.info("添加p2p_base_account表的参数为： " + certAuth.toString());
		try {
			pbaMapper.insert(list);
			Reporter.log("添加acctNo为："+ pcaService.getAccountNo(custId, session) +"的p2p_base_account表信息成功");
			log.info("添加acctNo为："+ pcaService.getAccountNo(custId, session) +"的p2p_base_account表信息成功");
		} catch (Exception e) {
			Reporter.log("添加acctNo为："+ pcaService.getAccountNo(custId, session) +"的p2p_base_account表信息时发生异常，添加失败" + e.getMessage());
			log.error("添加acctNo为："+ pcaService.getAccountNo(custId, session) +"的p2p_base_account表信息时发生异常，添加失败" + e.getMessage());
		}
	}
	
	/**
	 * 删除账户基础信息
	 * @param json
	 */
	
	public void delBaseAccount(JSONObject json, SqlSession session){
		pbaMapper = session.getMapper(p2p_base_accountMapper.class);
		pcaService = new p2p_cust_accountService();
		try {
			pbaMapper.deleteByAcctNo(json.getString("cardNo"));
			//Reporter.log("删除acctNo为："+ json.getString("cardNo") +"的p2p_base_account表信息成功");
		} catch (Exception e) {
			Reporter.log("删除acctNo为："+ json.getString("cardNo") +"的p2p_base_account表信息时发生异常，删除失败" + e.getMessage());
			log.error("删除acctNo为："+ json.getString("cardNo") +"的p2p_base_account表信息时发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
	}
}
