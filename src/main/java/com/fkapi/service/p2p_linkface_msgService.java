/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_linkface_msgMapper;
import com.fkapi.database.domain.p2p_linkface_msg;

/**
 * @author Administrator
 *
 */
public class p2p_linkface_msgService {
	p2p_linkface_msgMapper plmMapper = null;
	p2p_customerService pcService = null;
	
	public void addLinkFaceMsg(String oldCustId, String custId,String realName,String idCard, SqlSession session){
		delLinkFaceMsg(oldCustId, session);
		plmMapper = session.getMapper(p2p_linkface_msgMapper.class);
		List<p2p_linkface_msg> list = new ArrayList<p2p_linkface_msg>();
		p2p_linkface_msg plm = new p2p_linkface_msg();
		pcService = new p2p_customerService();
		plm.setCustId(Long.valueOf(custId));
		plm.setRealName(realName == null?"":realName);
		plm.setIdCard(idCard == null?"":idCard);
		list.add(plm);
		try {
			plmMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_linkface_msg表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_linkface_msg表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delLinkFaceMsg(String oldCustId, SqlSession session){
		plmMapper = session.getMapper(p2p_linkface_msgMapper.class);
		pcService = new p2p_customerService();
		try {
			plmMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的p2p_linkface_msg表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_linkface_msg表的数据发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		
	}
}
