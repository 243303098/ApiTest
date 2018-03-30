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
import com.fkapi.database.dao.p2p_student_infoMapper;
import com.fkapi.database.domain.p2p_student_info;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 *
 */
public class p2p_student_infoService {
	p2p_student_infoMapper psiMapper = null;
	p2p_customerService pcService = new p2p_customerService();
	ex_professionService epService = new ex_professionService();
	ex_collegesService ecService = null;
	Map<Object, Object> map = new HashMap<>();
	
	public void addStudentInfo(String oldCustId, String custId,JSONObject schoolRollAuth, SqlSession session){
		delStudentInfo(oldCustId, session);
		session = MybatisUtils.getFactory().openSession(true);
		psiMapper = session.getMapper(p2p_student_infoMapper.class);
		List<p2p_student_info> list = new ArrayList<>();
		ecService = new ex_collegesService();
		map = ecService.getCollegesDate(schoolRollAuth.getString("collegeName"),session);
		p2p_student_info psi = new p2p_student_info();
		psi.setCustId(Long.valueOf(custId));
		psi.setCollegesId(epService.getCollogeCode(schoolRollAuth, session));
		psi.setEntranceTime(schoolRollAuth.getString("entranceTime") == null?null:CommonUtils.StringToDate(schoolRollAuth.getString("entranceTime"), "day"));
		psi.setProvince(String.valueOf(map.get("province")));
		psi.setCity(String.valueOf(map.get("city")));
		psi.setCreator(Long.valueOf(custId));
		psi.setModifier(Long.valueOf(custId));
		psi.setCreateTime(CommonUtils.getCurDate("second"));
		psi.setUpdateTime(CommonUtils.getCurDate("second"));
		psi.setCollege(schoolRollAuth.getString("professionName"));
		list.add(psi);
		Reporter.log("添加p2p_student_info表的参数为： " + schoolRollAuth.toString());
		try {
			psiMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_student_info表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_student_info表的数据时出现异常，添加失败" + e.getMessage());
		}
	}
	
	public void delStudentInfo(String oldCustId, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		psiMapper = session.getMapper(p2p_student_infoMapper.class);
		try {
			psiMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的p2p_student_info表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_student_info表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 更新用户的modality
	 * @param custId
	 * @param modality
	 * @param session
	 */
	public void updataModality(String custId, String modality, SqlSession session){
		psiMapper = session.getMapper(p2p_student_infoMapper.class);
		p2p_student_info psi = psiMapper.selectByCustId(Long.valueOf(custId));
		psi.setModality(modality);
		try {
			psiMapper.updateByCustId(psi);
			Reporter.log("更新custId为：" + custId + "的p2p_student_info表中的数据成功");
		}catch (Exception e){
			Reporter.log("更新custId为：" + custId + "的p2p_student_info表中的数据失败" + e.getMessage());
		}
	}
	@Test
	public void test(){
		
	}
}
