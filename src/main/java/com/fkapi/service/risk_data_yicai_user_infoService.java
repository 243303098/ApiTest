/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.risk_data_yicai_user_infoMapper;
import com.fkapi.database.domain.risk_data_yicai_user_info;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class risk_data_yicai_user_infoService {
	risk_data_yicai_user_infoMapper rdyMapper = null;
	
	public void addRiskDataYCUserInfo(String oldCustId, String custId, JSONObject userInfoJson, JSONObject json, SqlSession session){
		delRiskDataYCUserInfo(oldCustId, session);
		rdyMapper = session.getMapper(risk_data_yicai_user_infoMapper.class);
		List<risk_data_yicai_user_info> list = new ArrayList<>();
		risk_data_yicai_user_info rdy = new risk_data_yicai_user_info();
		rdy.setCustId(Long.valueOf(custId));
		rdy.setCertId(userInfoJson.getString("idNo"));
		rdy.setName(userInfoJson.getString("custName"));
		if(json.getString("jobAuth").equals("AS")){
			rdy.setCurrentStatus(1);
		}else {
			rdy.setCurrentStatus(0);
		}
		rdy.setEntryDate(CommonUtils.StringToDate(json.getString("entryDate"), "day"));
		rdy.setContractCompany(json.getString("companyName"));
		rdy.setGmtCreate(CommonUtils.getCurDate("second"));
		rdy.setGmtModified(CommonUtils.getCurDate("second"));
		list.add(rdy);
		Reporter.log("添加risk_data_yicai_user_info表的参数为： " + json.toString());
		try {
			rdyMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的risk_data_yicai_user_info表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的risk_data_yicai_user_info表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delRiskDataYCUserInfo(String oldCustId, SqlSession session){
		rdyMapper = session.getMapper(risk_data_yicai_user_infoMapper.class);
		try {
			rdyMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的risk_data_yicai_user_info表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的risk_data_yicai_user_info表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
