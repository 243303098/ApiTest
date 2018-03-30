/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;

import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_cust_credit_infoMapper;
import com.fkapi.database.domain.p2p_cust_credit_info;
import org.testng.annotations.Test;

/**
 * @author Administrator
 *
 */
public class p2p_cust_credit_infoService {
	p2p_cust_credit_infoMapper pcciMapper = null;
	p2p_customerService pcService = null;
	
	public void addCustCreditInfo(String oldCustId, String custId, SqlSession session){
		delCustCreditInfo(oldCustId, session);
		pcciMapper = session.getMapper(p2p_cust_credit_infoMapper.class);
		List<p2p_cust_credit_info> list = new ArrayList<>();
		pcService = new p2p_customerService();
		p2p_cust_credit_info pcci = new p2p_cust_credit_info();
		pcci.setCustId(Long.valueOf(custId));
		list.add(pcci);
		try {
			pcciMapper.insert(list);
			Reporter.log("添加custId为： "+ custId +"的p2p_cust_credit_info表数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为： "+ custId +"的p2p_cust_credit_info表数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delCustCreditInfo(String oldCustId, SqlSession session){
		pcciMapper = session.getMapper(p2p_cust_credit_infoMapper.class);
		pcService = new p2p_customerService();
		try {
			pcciMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为： "+ custId +"的p2p_cust_credit_info表数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： "+ oldCustId +"的p2p_cust_credit_info表数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 更新用户的信用分
	 * @param custId
	 * @param score
	 * @param session
	 */
	public void updateCreditScore(String custId, Integer score, SqlSession session){
		pcciMapper = session.getMapper(p2p_cust_credit_infoMapper.class);
		p2p_cust_credit_info pcci = new p2p_cust_credit_info();
		pcci = pcciMapper.selectByCustId(Long.valueOf(custId));
		pcci.setCreditScore(score);
		try{
			pcciMapper.updateByCustId(pcci);
			Reporter.log("更新custId为： " + custId + "的信用分成功");
		}catch (Exception e){
			Reporter.log("更新custId为： " + custId + "的信用分失败" + e.getMessage());
		}
	}

	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		updateCreditScore("330002643",100, session);
	}
}
