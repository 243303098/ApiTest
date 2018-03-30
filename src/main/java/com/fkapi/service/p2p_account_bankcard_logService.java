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
import com.fkapi.database.dao.p2p_account_bankcard_logMapper;
import com.fkapi.database.domain.p2p_account_bankcard_log;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_account_bankcard_logService {
	Log log = new Log(p2p_account_bankcard_logService.class);
	p2p_account_bankcard_logMapper pablMapper = null;
	p2p_cust_accountService pcaService  = null;
	
	/**
	 * 添加用户银行卡的log数据
	 * @param custId
	 * @param certAuth
	 */
	public void addAccountBankcarLog(String custId,JSONObject certAuth, SqlSession session){
		delAccountBankcarLog(certAuth, session);
		pablMapper = session.getMapper(p2p_account_bankcard_logMapper.class);
		List<p2p_account_bankcard_log> list = new ArrayList<>();
		p2p_account_bankcard_log pabl = new p2p_account_bankcard_log();
		pcaService = new p2p_cust_accountService();
		pabl.setId(pcaService.getAccountNo(custId, session));
		pabl.setAcctNo(pcaService.getAccountNo(custId, session));
		pabl.setCardNo(certAuth.getString("cardNo"));
		pabl.setBindTime(CommonUtils.getCurDate("second"));
		pabl.setRealName(certAuth.getString("custName"));
		pabl.setCertNo(certAuth.getString("idNo"));
		pabl.setCreateTime(CommonUtils.getCurDate("second"));
		list.add(pabl);
		Reporter.log("添加p2p_account_bankcard_log表的参数为： " + certAuth.toString());
		log.info("添加p2p_account_bankcard_log表的参数为： " + certAuth.toString());
		try {
			pablMapper.insert(list);
			Reporter.log("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard_log表的信息成功");
			log.info("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard_log表的信息成功");
		} catch (Exception e) {
			Reporter.log("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard_log表的信息发生异常，添加失败" + e.getMessage());
			log.error("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard_log表的信息发生异常，添加失败" + e.getMessage());
		}
	}
	
	/**
	 * 删除用户银行卡的log数据
	 * @param json
	 */
	public void delAccountBankcarLog(JSONObject json, SqlSession session){
		pablMapper = session.getMapper(p2p_account_bankcard_logMapper.class);
		List<p2p_account_bankcard_log> list = new ArrayList<>();
		p2p_account_bankcard_log pabl = new p2p_account_bankcard_log();
		pcaService = new p2p_cust_accountService();
		pabl.setAcctNo(json.getString("cardNo"));
		pabl.setCardNo(json.getString("cardNo"));
		list.add(pabl);
		try {
			pablMapper.deleteByAcctNoAndCardNo(list);
			//Reporter.log("删除acctNo或者cardNo为："+json.getString("cardNo") + "的p2p_account_bankcard_log表的信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("删除acctNo或者cardNo为："+json.getString("cardNo") + "的p2p_account_bankcard_log表的信息发生异常，删除失败" + e.getMessage());
			log.error("删除acctNo或者cardNo为："+json.getString("cardNo") + "的p2p_account_bankcard_log表的信息发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		//JSONObject json = new JSONObject("{\"cardNo\":\"6217001000000000201\"}");
		//delAccountBankcarLog(json);
	}
}
