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
import com.fkapi.database.dao.p2p_student_custMapper;
import com.fkapi.database.domain.p2p_student_cust;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 *
 */
public class p2p_student_custService {
	p2p_student_custMapper pscMapper = null;
	p2p_customerService pcService = new p2p_customerService();
	ex_collegesService ecService;
	p2p_dictionaryService pdService ;
	Map<Object,Object> map = new HashMap<>();
	
	public void addStudentCust(String custId, JSONObject schoolRollAuth, SqlSession session){
		//delStudentCust(session);
		pscMapper = session.getMapper(p2p_student_custMapper.class);
		List<p2p_student_cust> list = new ArrayList<>();
		ecService = new ex_collegesService();
		pdService = new p2p_dictionaryService();
		p2p_student_cust psc = new p2p_student_cust();
		map = ecService.getCollegesDate(schoolRollAuth.getString("collegeName"),session);
		psc.setCustId(Long.valueOf(custId));
		psc.setSchoolName(schoolRollAuth.getString("collegeName") == null?"":schoolRollAuth.getString("collegeName"));
		psc.setEnterYear(schoolRollAuth.getString("entranceTime") == null?null:Integer.valueOf(schoolRollAuth.getString("entranceTime").substring(0, 4)));
		psc.setProvince(String.valueOf(map.get("province")));
		psc.setCity(String.valueOf(map.get("city")));
		psc.setLastUpdateTime(CommonUtils.getCurDate("second"));
		psc.setSchoolId((Long)map.get("id"));
		psc.setCollege(schoolRollAuth.getString("professionName"));
		psc.setMajor(schoolRollAuth.getString("professionName"));
		list.add(psc);
		Reporter.log("添加p2p_student_cust表的参数为：" + schoolRollAuth.toString());
		try {
			pscMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_student_cust表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_student_cust表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delStudentCust(SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		pscMapper = session.getMapper(p2p_student_custMapper.class);
		pcService = new p2p_customerService();
		p2p_student_cust psc = new p2p_student_cust();
		psc.setSchoolName("苏州大学");
		psc.setMajor("信息与计算科学");
		try {
			pscMapper.deleteBySchoolAndMajor(psc);
			//Reporter.log("删除custId为：" + custId + "的p2p_student_cust表的数据成功");
		} catch (Exception e) {
			Reporter.log("刪除schoolName：苏州大学 的p2p_student_cust表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	public String getSchoolCityCode(String custId, SqlSession session){
		pscMapper = session.getMapper(p2p_student_custMapper.class);
		try{
			p2p_student_cust psc = pscMapper.selectByCustId(Long.valueOf(custId));
			if(psc != null){
				return psc.getCity();
			}else {
				return null;
			}
		}catch (Exception e){
			Reporter.log("根据custId: " + custId + "获取学校城市编码时发生异常" + e.getMessage());
			return null;
		}
	}
	
	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		System.out.print(getSchoolCityCode("ceshi01", session));
	}
}
