/**
 * 
 */
package com.fkapi.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.ex_collegesMapper;
import com.fkapi.database.domain.ex_colleges;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 * 
 */
public class ex_collegesService {
	Log log = new Log(ex_collegesService.class);
	ex_collegesMapper ecMapper = null;
	
	/**
	 * 根据学校名称获取学校的id,省份,城市信息
	 * @param collegeName： 学校名称
	 * @return
	 */
	public Map<Object, Object> getCollegesDate(String collegeName, SqlSession session) {
		ecMapper = session.getMapper(ex_collegesMapper.class);
		Map<Object, Object> map = new HashMap<>();
		try {
			ex_colleges ec = ecMapper.selectByCollegeName(collegeName);
			if (ec == null) {
				map.put("id", null);
				map.put("province", null);
				map.put("city", null);
				Reporter.log("根据collegeName:" + collegeName +"未获取到学校信息");
				log.info("根据collegeName:" + collegeName +"未获取到学校信息");
			} else {
				map.put("id", ec.getId());
				map.put("province", ec.getProvince());
				map.put("city", ec.getCity());
			}
		} catch (Exception e) {
			Reporter.log("根据collegeName:" + collegeName +"获取学校信息是发生异常，获取信息失败"+ e.getMessage());
			log.info("根据collegeName:" + collegeName +"获取学校信息是发生异常，获取信息失败"+ e.getMessage());
			e.printStackTrace();
			
		}
		return map;
	}
	
	@Test
	public void test(){
	}
}
