/**
 * 
 */
package com.fkapi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.py_educationMapper;
import com.fkapi.database.domain.py_education;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class py_educationService {
	py_educationMapper peMapper = null;
	p2p_customerService pcService = null;
	
	public void addPyEducation(String oldCustId, String custId,String reqName,String reqDocumentNo,JSONObject json, SqlSession session){
		delPyEducation(oldCustId, session);
		peMapper = session.getMapper(py_educationMapper.class);
		List<py_education> list = new ArrayList<>();
		pcService = new p2p_customerService();
		py_education pe = new py_education();
		pe.setCustId(Long.valueOf(custId));
		pe.setReqName(reqName == null?"":reqName);
		pe.setReqDocumentNo(reqDocumentNo == null?"":reqDocumentNo);
		pe.setCollege(json.getString("collegeName") == null?"":json.getString("collegeName"));
		System.out.println(CommonUtils.subYear(CommonUtils.getCurDate("day"), json.getInt("graduateTime")).toString());
		pe.setGraduateTime(new SimpleDateFormat("yyyy").format(new Date(CommonUtils.subYear(CommonUtils.getCurDate("day"), json.getInt("graduateTime")).toString())));
		if(json.getString("clogLevel") != null && json.getString("clogLevel").equals("本科")){
			pe.setStartTime(new SimpleDateFormat("yyyy").format(new Date(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), json.getInt("graduateTime")), 4).toString())));
		}else if(json.getString("clogLevel") != null && json.getString("clogLevel").equals("专科")){
			pe.setStartTime(new SimpleDateFormat("yyyy").format(new Date(CommonUtils.subYear(CommonUtils.subYear(CommonUtils.getCurDate("day"), json.getInt("graduateTime")), 3).toString())));
		}else{
			pe.setStartTime(null);
		}
		pe.setSpecialty(json.getString("professionName"));
		pe.setDegree(json.getString("clogLevel"));
		list.add(pe);
		Reporter.log("添加的py_education表的参数为： " + json.toString());
		try {
			peMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的py_education表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的py_education表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delPyEducation(String oldCustId, SqlSession session){
		peMapper = session.getMapper(py_educationMapper.class);
		pcService = new p2p_customerService();
		try {
			peMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的py_education表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的py_education表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		//delPyEducation("160051908");
		//addPyEducation("CB170000090", "1", "1", "北京大学", "2011-07-01", "本科");
	}
}
