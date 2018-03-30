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
import com.fkapi.database.dao.p2p_customerMapper;
import com.fkapi.database.domain.p2p_customer;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 * 
 */
public class p2p_customerService {
	
	Log log = new Log(p2p_customerService.class);
	p2p_customerMapper pcMapper = null;
	p2p_customer pc = null;

	// 根据loginName创建数据
	public void addCustomer(String loginName,String platForm, SqlSession session) {
		delCustomer(loginName, session);
		pcMapper = session.getMapper(p2p_customerMapper.class);
		List<p2p_customer> list = new ArrayList<>();
		pcMapper.deleteByLoginName(loginName);
		pc = new p2p_customer();
		pc.setLoginName(loginName);
		pc.setCreateTime(CommonUtils.getCurDate("second"));
		//pc.setLastLoginTime(CommonUtils.getCurDate("second"));
		pc.setLastLoginTime(null);
		if(platForm.toUpperCase().equals("APP") || platForm.toUpperCase().equals("P2P")){
			pc.setBelongPlatform(platForm.toUpperCase());
			pc.setAutoPwd("N");
		}else {
			pc.setBelongPlatform(null);
			pc.setAutoPwd("Y");
		}
		//当用户填写了手机号码时更新该字段
		pc.setMobile(null);
		list.add(pc);
		try {
			pcMapper.insert(list);
			Reporter.log("新增loginName为：" + loginName + "的p2p_customer表的数据成功");
			log.info("新增loginName为：" + loginName + "的p2p_customer表的数据成功");
		} catch (Exception e) {
			Reporter.log("新增loginName为：" + loginName + "的p2p_customer表的数据时发生异常，新增失败" + e.getMessage());
			log.error("新增loginName为：" + loginName + "的p2p_customer表的数据时发生异常，新增失败" + e.getMessage());
		}
	}

	// 根据loginName删除数据
	public void delCustomer(String loginName, SqlSession session) {
		pcMapper = session.getMapper(p2p_customerMapper.class);
		try {
			pcMapper.deleteByLoginName(loginName);
			//Reporter.log("删除loginName为：" + loginName + "的p2p_customer表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除loginName为：" + loginName + "的p2p_customer表的数据时失败，删除失败" + e.getMessage());
			log.error("删除loginName为：" + loginName + "的p2p_customer表的数据时失败，删除失败" + e.getMessage());
		}
	}

	/**
	 * 更新手机号
	 * @param loginName
	 * @param json
	 * @param option
	 * @param session
	 */
	public void update(String loginName, JSONObject json, String option, SqlSession session){
		pcMapper = session.getMapper(p2p_customerMapper.class);
		pc = new p2p_customer();
		pc = pcMapper.selectByLoginName(loginName);
		if(option.equals("phoneAuth")){
			if(json.getString("mobile") != null){
				pc.setMobile(json.getString("mobile"));
				pcMapper.updateByLoginName(pc);
			}
		}
		if (option.equals("custStatus")){
			if(json.getString("status") != null){
				pc.setCustStatus(json.getString("status"));
				pcMapper.updateByLoginName(pc);
			}
		}
	}

	public Long getCustID(String loginName, SqlSession session) {
		pcMapper = session.getMapper(p2p_customerMapper.class);
		try {
			p2p_customer pc = pcMapper.selectByLoginName(loginName);
			if(pc == null){
				return null;
			}else {
				return pc.getId();
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Test
	public void test() {
		//updateMobile("hu_123", "15221527000");
	}
}
