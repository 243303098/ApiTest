/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fkapi.database.dao.p2p_customer_education_logMapper;
import com.fkapi.database.domain.p2p_customer_education_log;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 *
 */
public class p2p_customer_education_logService {
	p2p_customer_education_logMapper pcelMapper = null;
	p2p_customerService pcService = null;
	ex_collegesService exService = null;
	Map<Object,Object> map = null;
	
	public void addCustomerEducationLog(String oldCustId, String custId,JSONObject educationAuth, SqlSession session){
		delCustomerEducationLog(oldCustId, session);
		session = MybatisUtils.getFactory().openSession(true);
		pcelMapper = session.getMapper(p2p_customer_education_logMapper.class);
		List<p2p_customer_education_log> list = new ArrayList<p2p_customer_education_log>();
		p2p_customer_education_log pcel = new p2p_customer_education_log();
		pcService = new p2p_customerService();
		exService = new ex_collegesService();
		map = new HashMap<>();
		map = exService.getCollegesDate(educationAuth.getString("collegeName"),session);
		pcel.setCreateTime(CommonUtils.getCurDate("second"));
		pcel.setCustId(Long.valueOf(custId));
		pcel.setSchoolName(educationAuth.getString("collegeName") == null?"":educationAuth.getString("collegeName"));
		pcel.setEducation(educationAuth.getString("clogLevel") == null?"":educationAuth.getString("clogLevel"));
		if(educationAuth.getString("clogLevel") != null && educationAuth.getString("clogLevel").equals("本科")){
			pcel.setEntranceTime(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")), 4));
			pcel.setGraduationTime(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")));
		}else if(educationAuth.getString("clogLevel") != null && educationAuth.getString("clogLevel").equals("专科")){
			pcel.setEntranceTime(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")), 3));
			pcel.setGraduationTime(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")));
		}else{
			pcel.setGraduationTime(null);
		}
		pcel.setAuthStatus(educationAuth.getString("educationAuthStatus"));
		pcel.setCity(String.valueOf(map.get("city")));
		list.add(pcel);
		Reporter.log("添加p2p_customer_education_log表的参数为： " + educationAuth.toString());
		try {
			pcelMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_customer_education_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_customer_education_log表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delCustomerEducationLog(String oldCustId, SqlSession session){
		pcelMapper = session.getMapper(p2p_customer_education_logMapper.class);
		pcService = new p2p_customerService();
		try {
			pcelMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + pcService.getCustID(loginName) + "的p2p_customer_education_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_customer_education_log表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		//delCustomerEducationLog("160051908");
		//addCustomerEducationLog("CB170000090", "");
	}
}

