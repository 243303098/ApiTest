/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fkapi.database.dao.risk_cust_mobile_authMapper;
import com.fkapi.database.domain.risk_cust_mobile_auth;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.RiskMybatisUtils;

/**
 * @author Administrator
 *
 */
public class risk_cust_mobile_authSerivce {
	
	risk_cust_mobile_authMapper rcmaMapper;
	risk_cust_mobile_auth rcma ;
	
	public void addCustMobileAuth(Map<String,String> userInfoMap, JSONObject json){
		SqlSession riskSession = RiskMybatisUtils.getFactory().openSession(true);
		rcmaMapper = riskSession.getMapper(risk_cust_mobile_authMapper.class);
		rcma = new risk_cust_mobile_auth();
		
		rcma.setCustId(Long.valueOf(userInfoMap.get("custId")));
		rcma.setStatus(json.getString("jxlMobileAuthStatus"));
		rcma.setUpdateTime(CommonUtils.getCurDate("second"));
		rcma.setExpireTime(CommonUtils.getCurDate("second"));
		rcma.setLastAuthTime(CommonUtils.getCurDate("second"));
		rcma.setLastAuthTime(CommonUtils.getCurDate("second"));
		
		List<risk_cust_mobile_auth> list = new ArrayList<>();
		list.add(rcma);
		rcmaMapper.insert(list);
	}
}
