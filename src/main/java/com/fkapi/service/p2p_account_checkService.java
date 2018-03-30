/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_account_checkMapper;
import com.fkapi.database.domain.p2p_account_check;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 * 
 */
public class p2p_account_checkService {
	Log log = new Log(p2p_account_checkService.class);
	p2p_account_checkMapper pacMapper = null;
	p2p_customerService pcService = null;
	p2p_cust_accountService pcatService = null;

	/**
	 * 添加账户检查表信息
	 * 
	 * @param custId
	 */
	public void addAccountCheckService(String custId, JSONObject json, SqlSession session) {
		delAccountCheckService(json, session);
		pacMapper = session.getMapper(p2p_account_checkMapper.class);
		List<p2p_account_check> list = new ArrayList<>();
		p2p_account_check pac = new p2p_account_check();
		pcService = new p2p_customerService();
		pcatService = new p2p_cust_accountService();
		pac.setAcctNo(pcatService.getAccountNo(custId, session));
		pac.setOpenAcctUserNo(custId);
		list.add(pac);
		try {
			pacMapper.insert(list);
			Reporter.log("添加acctNo为：" + pcatService.getAccountNo(custId, session)
					+ "的p2p_account_check表信息成功");
			log.info("添加acctNo为：" + pcatService.getAccountNo(custId, session)
					+ "的p2p_account_check表信息成功");
		} catch (Exception e) {
			Reporter.log("添加acctNo为：" + pcatService.getAccountNo(custId, session)
					+ "的p2p_account_check表信息时发生异常，添加失败" + e.getMessage());
			log.error("添加acctNo为：" + pcatService.getAccountNo(custId, session)
					+ "的p2p_account_check表信息时发生异常，添加失败" + e.getMessage());
		}
	}

	/**
	 * 根据custId或acctNo删除账户检查表信息
	 * 
	 * @param
	 * @param json
	 */
	public void delAccountCheckService(JSONObject json, SqlSession session) {
		pacMapper = session.getMapper(p2p_account_checkMapper.class);
		try {
			pacMapper.deleteByAcctNo(json.getString("cardNo"));
		} catch (Exception e) {
			Reporter.log("删除acctNo为：" + json.getString("cardNo")
					+ "的p2p_account_check表信息时发生异常，删除失败" + e.getMessage());
			log.error("删除acctNo为：" + json.getString("cardNo")
					+ "的p2p_account_check表信息时发生异常，删除失败" + e.getMessage());
		}
	}
}
