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
import com.fkapi.database.dao.p2p_cust_accountMapper;
import com.fkapi.database.domain.p2p_cust_account;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_cust_accountService {
	Log log = new Log(p2p_cust_accountService.class);
	p2p_cust_accountMapper pcaMapper = null;
	p2p_customerService pcService = null;
	
	/**
	 * 添加用户账户信息
	 * @param custId
	 * @param certAuth
	 */
	public void addCustAccout(String oldCustId, String custId,JSONObject certAuth, SqlSession session){
		delCustAccout(oldCustId, certAuth, session);
		pcaMapper = session.getMapper(p2p_cust_accountMapper.class);
		List<p2p_cust_account> list = new ArrayList<>();
		p2p_cust_account pca = new p2p_cust_account();
		pcService = new p2p_customerService();
		pca.setAccountNo(certAuth.getString("cardNo"));
		pca.setCustId(Long.valueOf(custId));
		list.add(pca);
		Reporter.log("添加p2p_cust_account表的参数为： " + certAuth.toString());
		log.info("添加p2p_cust_account表的参数为： " + certAuth.toString());
		try {
			pcaMapper.insert(list);
			Reporter.log("添加certAuth为： "+ certAuth.toString() +"的p2p_cust_account表数据成功");
			log.info("添加certAuth为： "+ certAuth.toString() +"的p2p_cust_account表数据成功");
		} catch (Exception e) {
			Reporter.log("添加certAuth为： "+ certAuth.toString() +"的p2p_cust_account表数据时发生异常，添加失败" + e.getMessage());
			log.error("添加certAuth为： "+ certAuth.toString() +"的p2p_cust_account表数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	/**
	 * 根据acctNo或者custId删除用户账户信息
	 * @param oldCustId
	 * @param certAuth
	 */
	public void delCustAccout(String oldCustId, JSONObject certAuth, SqlSession session){
		pcaMapper = session.getMapper(p2p_cust_accountMapper.class);
		try {
			pcaMapper.deleteByAcctNo(certAuth.getString("cardNo"));
		} catch (Exception e) {
			Reporter.log("删除custId为： "+ oldCustId +"或者acctNo为："+ certAuth.getString("cardNo") +"的p2p_cust_account表数据时发生异常，删除失败" + e.getMessage());
			log.error("删除custId为： "+ oldCustId +"或者acctNo为："+ certAuth.getString("cardNo") +"的p2p_cust_account表数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	/**
	 * 根据用户loginName获取acctNo
	 * @param custId
	 * @return
	 */
	public String getAccountNo(String custId, SqlSession session){
		pcaMapper = session.getMapper(p2p_cust_accountMapper.class);
		pcService = new p2p_customerService();
		try {
			p2p_cust_account pca = pcaMapper.selectByCustId(Long.valueOf(custId));
			if(pca == null){
				Reporter.log("根据custId为： "+ custId +"未获取到用户AccountNo信息");
				log.error("根据custId为： "+ custId +"未获取到用户AccountNo信息");
				return null;
			}else{
				return pca.getAccountNo();
			}
		} catch (Exception e) {
			Reporter.log("根据custId为： "+ custId +"获取用户AccountNo信息时发生异常，获取失败" + e.getMessage());
			log.info("根据custId为： "+ custId +"获取用户AccountNo信息时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}
	
	@Test
	public void test(){
		
	}
}
