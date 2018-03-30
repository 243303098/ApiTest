/**
 * 
 */
package com.fkapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;

import com.fkapi.database.dao.risk_credit_yicai_tiqianhuaMapper;
import com.fkapi.database.domain.risk_credit_yicai_tiqianhua;
import com.fkapi.utils.MybatisUtils;
import com.fkapi.utils.Post;

/**
 * @author Administrator
 * 
 */
public class risk_credit_yicai_tiqianhuaService {

	risk_credit_yicai_tiqianhuaMapper rtMapper = null;

	/**
	 * 根据custId删除该用户的额度表
	 */
	public void delRiskT(String oldCustId, SqlSession session) {
		session = MybatisUtils.getFactory().openSession(true);
		rtMapper = session.getMapper(risk_credit_yicai_tiqianhuaMapper.class);
		try {
			rtMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为： " + custId + "的risk_credit_yicai_tiqianhua表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： " + oldCustId + "的risk_credit_yicai_tiqianhua表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 根据用户custId 查询用户的可用额度
	 */
	public String getLimit(String custId, SqlSession session) {
		rtMapper = session.getMapper(risk_credit_yicai_tiqianhuaMapper.class);
		try {
			risk_credit_yicai_tiqianhua rt = rtMapper.selectByCustId(Long.valueOf(custId));
			if(rt == null){
				Reporter.log("根据custId： " + custId + "未获取到可用额度");
				return null;
			}else {
				return String.valueOf(rt.getAvailableLimit());
			}
		} catch (Exception e) {
			Reporter.log("根据custId： " + custId + "获取可用额度时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}

	/**
	 * 修改易才可借额度
	 */
	public void updateLimit(int Limit, Map<String, String> map, SqlSession session) {
		rtMapper = session.getMapper(risk_credit_yicai_tiqianhuaMapper.class);
		risk_credit_yicai_tiqianhua rcyt ;
		JSONObject json = new JSONObject(map.get("certAuth"));
		//请求额度计算接口获取用户的额度
		Post.postYcLimit(
				map.get("custId"),
				json.getString("custName"), json.getString("idNo"));
		try {
			rcyt = rtMapper
					.selectByCustId(Long.valueOf(map.get("custId")));
			if(rcyt != null){
				rcyt.setAvailableLimit(new BigDecimal(Limit));
				rcyt.setMaxLimit(new BigDecimal(Limit));
				//更新用户的可借额度，使得用户可以通过风控审批
				rtMapper.updateByCustId(rcyt);
			}else{
				//如果请求额度接口失败，则手动新增数据
				List<risk_credit_yicai_tiqianhua> list = new ArrayList<>();
				risk_credit_yicai_tiqianhua rcytA = new risk_credit_yicai_tiqianhua();
				rcytA.setCustId(Long.valueOf(map.get("custId")));
				list.add(rcytA);
				rtMapper.insert(list);
			}
		} catch (Exception e) {
			Reporter.log("修改用户的可借额度时发生异常，修改失败" + e.getMessage());
		}
	}
}
