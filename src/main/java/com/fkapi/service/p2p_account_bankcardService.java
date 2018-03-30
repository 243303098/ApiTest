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
import com.fkapi.database.dao.p2p_account_bankcardMapper;
import com.fkapi.database.domain.p2p_account_bankcard;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_account_bankcardService {
	Log log = new Log(p2p_account_bankcardService.class);
	p2p_account_bankcardMapper pabMapper = null;
	p2p_customerService pcService = null;
	p2p_cust_accountService pcaService = null;
	
	/**
	 * 添加用户银行卡信息
	 * @param custId
	 * @param certAuth
	 */
	public void addAccountBankCard(String custId,JSONObject certAuth, SqlSession session){
		delAccountBankCard(certAuth, session);
		pabMapper = session.getMapper(p2p_account_bankcardMapper.class);
		List<p2p_account_bankcard> list = new ArrayList<>();
		pcaService = new p2p_cust_accountService();
		p2p_account_bankcard pab = new p2p_account_bankcard();
		pab.setAcctNo(pcaService.getAccountNo(custId, session));
		pab.setCardNo(certAuth.getString("cardNo") == null?"":certAuth.getString("cardNo"));
		pab.setBindTime(CommonUtils.getCurDate("second"));
		list.add(pab);
		Reporter.log("添加p2p_account_bankcard表的参数为： " + certAuth.toString());
		log.info("添加p2p_account_bankcard表的参数为： " + certAuth.toString());
		try {
			pabMapper.insert(list);
			Reporter.log("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard表信息成功");
			log.info("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard表信息成功");
		} catch (Exception e) {
			Reporter.log("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard表信息发生异常，添加失败" + e.getMessage());
			log.error("添加卡号为："+ certAuth.getString("cardNo") +"的p2p_account_bankcard表信息发生异常，添加失败" + e.getMessage());
		}
	}
	
	/**
	 * 通过acctNo或者CardNo删除用户银行卡信息
	 * @param certAuth
	 */
	public void delAccountBankCard(JSONObject certAuth, SqlSession session){
		pabMapper = session.getMapper(p2p_account_bankcardMapper.class);
		List<p2p_account_bankcard> list = new ArrayList<>();
		p2p_account_bankcard pab = new p2p_account_bankcard();
		pab.setAcctNo(certAuth.getString("cardNo"));
		pab.setCardNo(certAuth.getString("cardNo"));
		list.add(pab);
		try {
			pabMapper.deleteByAcctNoAndCardNo(list);
			//Reporter.log("删除acctNo或者cardNo为："+certAuth.getString("cardNo") + "的p2p_account_bankcard表信息成功");
		} catch (Exception e) {
			Reporter.log("删除acctNo或者cardNo为："+certAuth.getString("cardNo") + "的p2p_account_bankcard表信息时发生异常，删除失败" + e.getMessage());
			log.info("删除acctNo或者cardNo为："+certAuth.getString("cardNo") + "的p2p_account_bankcard表信息时发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		JSONObject json = new JSONObject("{\"cardNo\":\"6217001000000000201\"}");
		System.out.println(json.toString());
		//delAccountBankCard(json);
	}
}
