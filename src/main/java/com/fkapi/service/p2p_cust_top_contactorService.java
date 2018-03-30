/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_cust_top_contactorMapper;
import com.fkapi.database.domain.p2p_cust_top_contactor;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_cust_top_contactorService {
	p2p_cust_top_contactorMapper pctcMapper = null;
	p2p_customerService pcService = null;
	
	public void addCustTopContactor(String oldCustId, String custId, JSONObject json, SqlSession session){
		delCustTopContactor(oldCustId, session);
		pctcMapper = session.getMapper(p2p_cust_top_contactorMapper.class);
		List<p2p_cust_top_contactor> list ;
		pcService = new p2p_customerService();
		p2p_cust_top_contactor pctc = new p2p_cust_top_contactor();
		String relation ;
		JSONArray jsonArray = (JSONArray) json.get("contractor");
		for(int i=0;i<jsonArray.length();i++){
			list = new ArrayList<>();
			pctc.setCustId(Long.valueOf(custId));
			pctc.setConName(String.valueOf(new JSONObject(jsonArray.get(i).toString()).get("name")));
			pctc.setMobile(String.valueOf(new JSONObject(jsonArray.get(i).toString()).get("mobile")));
			relation = (String) new JSONObject(jsonArray.get(i).toString()).get("relation");
			if(relation.equals("父母")){
				pctc.setRelationType("parents");
			}else if(relation.equals("亲戚")){
				pctc.setRelationType("relative");
			}else if(relation.equals("配偶")){
				pctc.setRelationType("spouse");
			}else if(relation.equals("同学")){
				pctc.setRelationType("classmate");
			}else if(relation.equals("朋友")){
				pctc.setRelationType("friend");
			}else if(relation.equals("同事")){
				pctc.setRelationType("colleague");
			}else if(relation.equals("其他")){
				pctc.setRelationType("other");
			}
			pctc.setCreateTime(CommonUtils.getCurDate("day"));
			pctc.setUpdateTime(CommonUtils.getCurDate("day"));
			list.add(pctc);
			Reporter.log("添加p2p_cust_top_contactor表的参数为： " + json);
			try {
				pctcMapper.insert(list);
				Reporter.log("添加custId为："+ custId + "的p2p_cust_top_contactor表的数据成功");
			} catch (Exception e) {
				Reporter.log("添加custId为："+ custId + "的p2p_cust_top_contactor表的数据发生异常，添加失败" + e.getMessage());
			}
		}
	}
	
	public void delCustTopContactor(String oldCustId, SqlSession session){
		pctcMapper = session.getMapper(p2p_cust_top_contactorMapper.class);
		pcService = new p2p_customerService();
		try {
			pctcMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的p2p_cust_top_contactor表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_cust_top_contactor表的数据发生异常，删除失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		
	}
}
