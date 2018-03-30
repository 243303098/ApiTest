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

import com.fkapi.database.dao.p2p_cert_authMapper;
import com.fkapi.database.domain.p2p_cert_auth;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 * 
 */
public class p2p_cert_authService {
	Log log = new Log(p2p_cert_authService.class);
	p2p_cert_authMapper pcaMapper = null;
	p2p_customerService pcService = null;

	public void addCertAuth(String oldCustId, String custId,JSONObject json, SqlSession session) {
		delCertAuth(oldCustId, session);
		session = MybatisUtils.getFactory().openSession(true);
		pcaMapper = session.getMapper(p2p_cert_authMapper.class);
		pcService = new p2p_customerService();
		List<p2p_cert_auth> list = new ArrayList<>();
		p2p_cert_auth pca = new p2p_cert_auth();
		pca.setCustId(Long.valueOf(custId));
		pca.setCustName(json.getString("custName"));
		pca.setCertNo(json.getString("idNo"));
		pca.setAuthStatus(json.getString("certAuthStatus"));
		pca.setCreateTime(CommonUtils.getCurDate("second"));
		pca.setAuthTime(CommonUtils.getCurDate("second"));
		list.add(pca);
		Reporter.log("添加p2p_cert_auth表的参数为： " + json.toString());
		log.info("添加p2p_cert_auth表的参数为： " + json.toString());
		try {
			pcaMapper.insert(list);
			Reporter.log("添加custId为："+ custId + "的p2p_cert_auth表的数据成功");
			log.info("添加custId为："+ custId + "的p2p_cert_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为："+ custId + "的p2p_cert_auth表的数据发生异常，添加失败" + e.getMessage());
			log.error("添加custId为："+ custId + "的p2p_cert_auth表的数据发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delCertAuth(String oldCustId, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		pcaMapper = session.getMapper(p2p_cert_authMapper.class);
		pcService = new p2p_customerService();
		try {
			pcaMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为："+ pcService.getCustID(loginName) + "的p2p_cert_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为："+ oldCustId + "的p2p_cert_auth表的数据发生异常，删除失败" + e.getMessage());
			log.error("删除custId为："+ oldCustId + "的p2p_cert_auth表的数据发生异常，删除失败" + e.getMessage());
		}
	}
	
	public void update(String custId,String certNo, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		pcaMapper = session.getMapper(p2p_cert_authMapper.class);
		pcService = new p2p_customerService();
		p2p_cert_auth pca ;
		pca = pcaMapper.selectByCustId(Long.valueOf(custId));
		pca.setCertNo(certNo);
		try {
			pcaMapper.updateByCustId(pca);
			Reporter.log("更新custId为： " + custId + "的p2p_cert_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("更新custId为： " + custId + "的p2p_cert_auth表的数据时发生异常，更新失败" + e.getMessage());
		}
	}
	
	/**
	 * 根据loginname获取idcard
	 * @param custId
	 * @return
	 */
	public String getIdcard(String custId, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		pcaMapper = session.getMapper(p2p_cert_authMapper.class);
		String idCard ;
		pcService = new p2p_customerService();
		try {
			p2p_cert_auth pca = pcaMapper.selectByCustId(Long.valueOf(custId));
			if(pca == null){
				Reporter.log("根据custId为： " + custId + "未获取到Idcard");
				return null;
			}else{
				idCard = pca.getCertNo();
				Reporter.log("根据custId为： " + custId + "获取Idcard成功");
				return idCard;
			}
		} catch (Exception e) {
			Reporter.log("根据custId为： " + custId + "获取Idcard时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}
	
	@Test
	public void test(){
		
	}
	
}
