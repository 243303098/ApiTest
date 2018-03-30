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
import com.fkapi.database.dao.p2p_juxinli_mobile_authMapper;
import com.fkapi.database.domain.p2p_juxinli_mobile_auth;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_juxinli_mobile_authService {
	p2p_juxinli_mobile_authMapper pjmaMapper = null;
	p2p_customerService pcService = null;
	
	public void addJuxinliMobileAuth(String custId,JSONObject json, SqlSession session){
		delJuxinliMobileAuth(custId, session);
		pjmaMapper = session.getMapper(p2p_juxinli_mobile_authMapper.class);
		List<p2p_juxinli_mobile_auth> list ;
		pcService = new p2p_customerService();
		p2p_juxinli_mobile_auth pjma = new p2p_juxinli_mobile_auth();
		for(int i=0;i<2;i++){
			list = new ArrayList<>();
			pjma.setCustId(Long.valueOf(custId));
			pjma.setStatus(json.getString("jxlMobileAuthStatus"));
			if(json.getString("authTime").toUpperCase().equals("NOW")){
				pjma.setCreateTime(CommonUtils.getCurDate("second"));
				pjma.setUpdateTime(CommonUtils.getCurDate("second"));
			}else {
				pjma.setCreateTime(CommonUtils.StringToDate(json.getString("authTime"), "day"));
				pjma.setUpdateTime(CommonUtils.StringToDate(json.getString("authTime"), "day"));
			}
			pjma.setType(String.valueOf(i+1));
			list.add(pjma);
			Reporter.log("添加的通讯认证参数为 ： " + json.toString());
			try {
				pjmaMapper.insert(list);
				Reporter.log("添加custId为："+ custId + "的p2p_juxinli_mobile_auth表的数据成功");
			} catch (Exception e) {
				Reporter.log("添加custId为："+ custId + "的p2p_juxinli_mobile_auth表的数据时发生异常，添加失败" + e.getMessage());
			}
		}
	}
	
	public void delJuxinliMobileAuth(String custId, SqlSession session){
		pjmaMapper = session.getMapper(p2p_juxinli_mobile_authMapper.class);
		pcService = new p2p_customerService();
		try {
			pjmaMapper.deleteByCustId(Long.valueOf(custId));
			//Reporter.log("删除custId为：" + pcService.getCustID(loginName) + "的p2p_juxinli_mobile_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + custId + "的p2p_juxinli_mobile_auth表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 根据custId更新p2p_juxinli_mobile_auth表中的认证时间
	 * @param custId
	 * @param json
	 * @param session
	 */
	public void updateJuxinliMobileAuth(String custId, JSONObject json, SqlSession session){
		pjmaMapper = session.getMapper(p2p_juxinli_mobile_authMapper.class);
		pcService = new p2p_customerService();
		p2p_juxinli_mobile_auth pjma = pjmaMapper.selectByCustId(Long.valueOf(custId));
		pjma.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
		pjma.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("day")));
		try{
			pjmaMapper.updateByCustId(pjma);
		}catch (Exception e){
			Reporter.log("根据custId： " + custId + "更新p2p_juxinli_mobile_auth表时发生异常，更新失败" + e.getMessage());
		}
	}

	/**
	 * 根据custId获取p2p_juxinli_mobile_auth表的Id
	 * @param custId
	 * @return
	 */
	public Long getAuthId(String custId, SqlSession session){
		pjmaMapper = session.getMapper(p2p_juxinli_mobile_authMapper.class);
		pcService = new p2p_customerService();
		try {
			p2p_juxinli_mobile_auth pjma = pjmaMapper.selectByCustId(Long.valueOf(custId));
			if(pjma == null){
				Reporter.log("根据custId为： " + custId + "未获取到authId");
				return null;
			}else {
				return pjma.getId();
			}
		} catch (Exception e) {
			Reporter.log("根据custId为： " + custId + "获取authId时发生异常" + e.getMessage());
			return null;
		}
	}
	
	@Test
	public void test(){
		
	}
}
