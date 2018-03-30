/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.risk_auth_job_logMapper;
import com.fkapi.database.domain.risk_auth_job_log;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class risk_auth_job_logService {
	risk_auth_job_logMapper rajlMapper = null;
	p2p_customerService pcService = null;
	
	public void addRiskAuthJobLog(String oldCustId, String custId,JSONObject json, SqlSession session){
		delRiskAuthJobLog(oldCustId, session);
		rajlMapper = session.getMapper(risk_auth_job_logMapper.class);
		List<risk_auth_job_log> list = new ArrayList<>();
		risk_auth_job_log rajl = new risk_auth_job_log();
		pcService = new p2p_customerService();
		rajl.setCustId(Long.valueOf(custId));
		rajl.setOldStatus(json.getString("jobAuth"));
		rajl.setNewStatus(json.getString("jobAuth"));
		rajl.setGmtCreate(CommonUtils.getCurDate("second"));
		rajl.setGmtModified(CommonUtils.getCurDate("second"));
		list.add(rajl);
		try {
			rajlMapper.insert(list);
			Reporter.log("添加custId为： " + custId + "的risk_auth_job_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为： " + custId + "的risk_auth_job_log表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delRiskAuthJobLog(String oldCustId, SqlSession session){
		rajlMapper = session.getMapper(risk_auth_job_logMapper.class);
		pcService = new p2p_customerService();
		try {
			rajlMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为： " + oldCustId + "的risk_auth_job_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： " + oldCustId + "的risk_auth_job_log表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
