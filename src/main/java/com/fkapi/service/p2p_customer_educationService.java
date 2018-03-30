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
import com.fkapi.database.dao.p2p_customer_educationMapper;
import com.fkapi.database.domain.p2p_customer_education;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_customer_educationService {
	p2p_customer_educationMapper pceMapper = null;
	ex_collegesService ecService = null;
	p2p_customerService pcService = null;
	Map<Object, Object> map = null;
	
	public void addCustomerEducation(String oldCustId, String custId,JSONObject educationAuth, SqlSession session){
		delCustomerEducation(oldCustId, session);
		pceMapper = session.getMapper(p2p_customer_educationMapper.class);
		List<p2p_customer_education> list = new ArrayList<>();
		pcService = new p2p_customerService();
		ecService = new ex_collegesService();
		map = new HashMap<>();
		map = ecService.getCollegesDate(educationAuth.getString("collegeName"),session);
		p2p_customer_education pce = new p2p_customer_education();
		pce.setCustId(Long.valueOf(custId));
		pce.setEducation(educationAuth.getString("clogLevel") == null?"":educationAuth.getString("clogLevel"));
		pce.setSchoolName(educationAuth.getString("collegeName") == null?"":educationAuth.getString("collegeName"));
		if(educationAuth.getString("clogLevel") != null && educationAuth.getString("clogLevel").equals("本科")){
			pce.setEntranceTime(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")), 4));
			pce.setGraduationTime(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")));
		}else if(educationAuth.getString("clogLevel") != null && educationAuth.getString("clogLevel").equals("专科")){
			pce.setEntranceTime(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")), 3));
			pce.setGraduationTime(CommonUtils.subYear(CommonUtils.getCurDate("day"), educationAuth.getInt("graduateTime")));
		}else{
			pce.setGraduationTime(null);
		}
		pce.setCreateTime(CommonUtils.getCurDate("second"));
		pce.setProvince(Long.valueOf((String) map.get("province")));
		pce.setCity(Long.valueOf((String) map.get("city")));
		list.add(pce);
		Reporter.log("添加p2p_customer_education表的参数为： " + educationAuth.toString());
		try {
			pceMapper.insert(list);
			Reporter.log("学历认证信息为： "+ educationAuth.toString());
			Reporter.log("添加custId为：" + custId + "的p2p_customer_education表的数据成功");
		} catch (Exception e) {
			Reporter.log("学历认证信息为： "+ educationAuth.toString());
			Reporter.log("添加custId为：" + custId + "的p2p_customer_education表的数据发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delCustomerEducation(String oldCustId, SqlSession session){
		pceMapper = session.getMapper(p2p_customer_educationMapper.class);
		pcService = new p2p_customerService();
		try {
			pceMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + pcService.getCustID(loginName) + "的p2p_customer_education表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_customer_education表的数据发生异常，删除失败" + e.getMessage());
		}
	}

	public String getSchoolCityCode(String custId, SqlSession session){
		pceMapper = session.getMapper(p2p_customer_educationMapper.class);
		pcService = new p2p_customerService();
		try{
			p2p_customer_education pce = pceMapper.selectByCustId(Long.valueOf(custId));
			if(pce != null){
				return String.valueOf(pce.getCity());
			}else {
				return null;
			}
		}catch (Exception e){
			Reporter.log("根据custId: " + custId + "获取学校城市编码时发生异常" + e.getMessage());
			return null;
		}
	}

	/**
	 * 更新用户学历信息
	 * @param custId
	 * @param session
	 * @param option
	 */
	public void update(Long custId, SqlSession session, String option){
		pceMapper = session.getMapper(p2p_customer_educationMapper.class);
		p2p_customer_education pce = pceMapper.selectByCustId(custId);
		if (option.equals("school")){
			pce.setSchoolName("测试");
			try {
				pceMapper.updateByCustId(pce);
			}catch (Exception e){
				Reporter.log("更新学历信息失败" + e.getMessage());
			}
		}
		if (option.equals("degree")){
			pce.setEducation("博士研究生");
			try {
				pceMapper.updateByCustId(pce);
			}catch (Exception e){
				Reporter.log("更新学历信息失败" + e.getMessage());
			}
		}
		if (option.equals("entertime")){
			pce.setEntranceTime(CommonUtils.StringToDate("1000-10-19", "day"));
			try {
				pceMapper.updateByCustId(pce);
			}catch (Exception e){
				Reporter.log("更新学历信息失败" + e.getMessage());
			}
		}
		if (option.equals("graduatetime")){
			pce.setGraduationTime(CommonUtils.StringToDate("1000-10-19", "day"));
			try {
				pceMapper.updateByCustId(pce);
			}catch (Exception e){
				Reporter.log("更新学历信息失败" + e.getMessage());
			}
		}
	}
	
	@Test
	public void test(){
		//delCustomerEducation("160051908");
		//addCustomerEducation("160051908", "{\"educationAuthStatus\":\"AS\",\"entranceTime\":\"2016-07-01\",\"collegeName\":\"苏州大学\",\"clogLevel\":\"本科\"}");
	}
}
