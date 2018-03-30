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
import com.fkapi.database.dao.p2p_juxinli_mobile_logMapper;
import com.fkapi.database.domain.p2p_juxinli_mobile_log;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_juxinli_mobile_logService {
	p2p_juxinli_mobile_logMapper pjmlMapper = null;
	p2p_customerService pcService = null;
	p2p_juxinli_mobile_authService pjmaService = null;
	
	public void addJuxinliMobileLog(String oldCustId, String custId,String mobile,JSONObject json, SqlSession session){
		delJuxinliMobileLog(oldCustId, session);
		pjmlMapper = session.getMapper(p2p_juxinli_mobile_logMapper.class);
		List<p2p_juxinli_mobile_log> list = new ArrayList<>();
		pcService = new p2p_customerService();
		pjmaService = new p2p_juxinli_mobile_authService();
		p2p_juxinli_mobile_log pjml = new p2p_juxinli_mobile_log();
		pjml.setCustId(Long.valueOf(custId));
		pjml.setStatus(json.getString("jxlMobileAuthStatus"));
		pjml.setMobile(mobile);
		pjml.setCreateTime(CommonUtils.StringToDate(json.getString("authTime"), "second"));
		pjml.setAuthId(pjmaService.getAuthId(custId, session));
		pjml.setUpdateTime(CommonUtils.StringToDate(json.getString("authTime"), "second"));
		list.add(pjml);
		try {
			pjmlMapper.insert(list);
			Reporter.log("添加custId为："+ custId + "的p2p_juxinli_mobile_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为："+ custId + "的p2p_juxinli_mobile_log表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delJuxinliMobileLog(String oldCustId, SqlSession session){
		pjmlMapper = session.getMapper(p2p_juxinli_mobile_logMapper.class);
		pcService = new p2p_customerService();
		try {
			pjmlMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + pcService.getCustID(loginName) + "的p2p_juxinli_mobile_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_juxinli_mobile_log表的数据发生异常，删除失败" + e.getMessage());
		}
	}
	
	public Long getAuthLogId(String custId, SqlSession session){
		pjmlMapper = session.getMapper(p2p_juxinli_mobile_logMapper.class);
		p2p_juxinli_mobile_log pjml ;
		pjmaService = new p2p_juxinli_mobile_authService();
		pcService = new p2p_customerService();
		try {
			pjml = pjmlMapper.selectByCustId(Long.valueOf(custId));
			if(pjml == null){
				Reporter.log("根据custId: " + custId + "未获取到authLogId");
				return null;
			}else {
				return pjml.getId();
			}
		} catch (Exception e) {
			Reporter.log("根据custId: " + custId + "获取authLogId时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}
	@Test
	public void test(){
		
	}
}
