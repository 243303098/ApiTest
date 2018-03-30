/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;

import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.jxl_primary_infoMapper;
import com.fkapi.database.domain.jxl_primary_info;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;
import org.testng.annotations.Test;

/**
 * @author Administrator
 * 
 */
public class jxl_primary_infoService {
	Log log = new Log(jxl_primary_infoService.class);
	jxl_primary_infoMapper jpiMapper = null;
	p2p_juxinli_mobile_logService pjmlService = null;
	p2p_customerService pcService = null;

	/**
	 * 添加聚信立通讯认证信息
	 * 
	 * @param custId
	 * @param json
	 *            : 通讯认证信息
	 */
	@SuppressWarnings("deprecation")
	public void addPrimaryInfo(String oldCustId, String custId, JSONObject json, SqlSession session) {
		delPrimaryInfo(oldCustId, session);
		jpiMapper = session.getMapper(jxl_primary_infoMapper.class);
		List<jxl_primary_info> list = new ArrayList<>();
		pjmlService = new p2p_juxinli_mobile_logService();
		pcService = new p2p_customerService();
		jxl_primary_info jpi = new jxl_primary_info();
		jpi.setCustId(Long.valueOf(custId));
		jpi.setAuthLogId(pjmlService.getAuthLogId(custId, session));
		jpi.setUpdateTime(CommonUtils.getCurDate("second").toGMTString());
		jpi.setCreateTime(CommonUtils.getCurDate("second"));
		list.add(jpi);
		Reporter.log("添加jxl_primary_info表的参数为： " + json.toString());
		log.info("添加jxl_primary_info表的参数为： " + json.toString());
		try {
			jpiMapper.insert(list);
			Reporter.log("添加通讯认证的信息为：" + json.toString() + "的jxl_primary_info表信息成功");
			log.info("添加通讯认证的信息为：" + json.toString() + "的jxl_primary_info表信息成功");
		} catch (Exception e) {
			Reporter.log("添加通讯认证的信息为：" + json.toString() + "的jxl_primary_info表信息时发生异常，添加失败"
					+ e.getMessage());
			log.error("添加通讯认证的信息为：" + json.toString() + "的jxl_primary_info表信息时发生异常，添加失败"
					+ e.getMessage());
		}
	}

	/**
	 * 删除聚信立通讯认证信息
	 * 
	 * @param oldCustId
	 */
	public void delPrimaryInfo(String oldCustId, SqlSession session) {
		jpiMapper = session.getMapper(jxl_primary_infoMapper.class);
		pcService = new p2p_customerService();
		try {
			jpiMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + pcService.getCustID(loginName)
				//	+ "的jxl_primary_info表的记录成功");
			
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId
					+ "的jxl_primary_info表的记录发生异常，删除失败" + e.getMessage());
			log.error("的jxl_primary_info表的记录发生异常，删除失败" + e.getMessage());
		}
	}

	public Long getPrimaryId(String custId, SqlSession session){
		jpiMapper = session.getMapper(jxl_primary_infoMapper.class);
		pcService = new p2p_customerService();
		try{
			jxl_primary_info jpi = jpiMapper.selectByCustId(Long.valueOf(custId));
			if(jpi != null){
				return jpi.getId();
			}else {
				return null;
			}
		}catch (Exception e){
			Reporter.log("根据custId： " + custId + "获取primarId时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}

	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		System.out.print(getPrimaryId("ceshi01", session));
	}
}
