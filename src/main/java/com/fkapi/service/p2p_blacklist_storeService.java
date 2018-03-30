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
import com.fkapi.database.dao.p2p_blacklist_storeMapper;
import com.fkapi.database.domain.p2p_blacklist_store;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_blacklist_storeService {
	Log log = new Log(p2p_blacklist_storeService.class);
	p2p_blacklist_storeMapper pbsMapper = null;
	
	public void addBlackListStore(JSONObject json, SqlSession session){
		delBlackListStore(json, session);
		pbsMapper = session.getMapper(p2p_blacklist_storeMapper.class);
		p2p_blacklist_store pbs = new p2p_blacklist_store();
		List<p2p_blacklist_store> list = new ArrayList<>();
		
		pbs.setName(json.getString("custName"));
		pbs.setCertNo(json.getString("idNo"));
		pbs.setUpdateTime(CommonUtils.getCurDate("day"));
		pbs.setOverdueDate(CommonUtils.getCurDate("day"));
		list.add(pbs);
		try {
			pbsMapper.insert(list);
			Reporter.log("添加外部黑名单数据成功，添加的用户为：" + json.getString("custName") +","+ json.getString("idNo"));
		} catch (Exception e) {
			Reporter.log("添加外部黑名单数据时出现异常，添加失败！ 参数为： " + json.getString("custName") +","+ json.getString("idNo"));
		}
	}
	
	public void delBlackListStore(JSONObject json, SqlSession session){
		pbsMapper = session.getMapper(p2p_blacklist_storeMapper.class);
		p2p_blacklist_store pbs = new p2p_blacklist_store();
		List<p2p_blacklist_store> list = new ArrayList<>();
		
		pbs.setName(json.getString("custName"));
		String idNo = json.getString("idNo");
		pbs.setCertNo(idNo.substring(0, 6)+"%"+idNo.substring(idNo.length()-6));
		list.add(pbs);
		try {
			pbsMapper.deleteByCustNameOrIdNo(list);
			Reporter.log("删除外部黑名单数据成功！ 参数为： " + json.getString("custName") +","+ json.getString("idNo"));
		} catch (Exception e) {
			Reporter.log("删除外部黑名单数据时出现异常，删除失败！ 参数为： " + json.getString("custName") +","+ json.getString("idNo"));
		}
	}
	
	@Test
	public void test(){
	}
}
