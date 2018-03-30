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
import com.fkapi.database.dao.p2p_linkface_authMapper;
import com.fkapi.database.domain.p2p_linkface_auth;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_linkface_authService {
	p2p_linkface_authMapper plaMapper = null;
	p2p_customerService pcService = null;
	p2p_linkface_auth pla = null;
	
	public void addLinkFaceAuth(String oldCustId, String custId,JSONObject photoAuth, SqlSession session){
		delLinkFaceAuth(oldCustId, session);
		plaMapper = session.getMapper(p2p_linkface_authMapper.class);
		List<p2p_linkface_auth> list = new ArrayList<>();
		pcService = new p2p_customerService();
		pla = new p2p_linkface_auth();
		pla.setCustId(Long.valueOf(custId));
		pla.setStatus(photoAuth.getString("photoAuthStatus"));
		pla.setCreateTime(CommonUtils.getCurDate("day"));
		list.add(pla);
		Reporter.log("添加p2p_linkface_auth表的参数为： " + photoAuth.toString());
		try {
			plaMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_linkface_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_linkface_auth表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delLinkFaceAuth(String oldCustId, SqlSession session){
		plaMapper = session.getMapper(p2p_linkface_authMapper.class);
		pcService = new p2p_customerService();
		try {
			plaMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的p2p_linkface_auth表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_linkface_auth表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		//addLinkFaceAuth("160051908", "AS");
	}
}
