/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.risk_auth_jobMapper;
import com.fkapi.database.domain.risk_auth_job;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 *
 */
public class risk_auth_jobService {
	Log log = new Log(risk_auth_jobService.class);
	risk_auth_jobMapper rajMapper = null;
	p2p_customerService pcService = null;
	
	public void addRiskAuthJob(String oldCustId, String custId,String authStatus, SqlSession session){
		delRiskAuthJob(oldCustId, session);
		rajMapper = session.getMapper(risk_auth_jobMapper.class);
		List<risk_auth_job> list = new ArrayList<>();
		pcService = new p2p_customerService();
		risk_auth_job raj = new risk_auth_job();
		raj.setCustId(Long.valueOf(custId));
		raj.setAuthStatus(authStatus);
		raj.setGmtSucceed(CommonUtils.getCurDate("second"));
		raj.setGmtCreate(CommonUtils.getCurDate("second"));
		raj.setGmtModified(CommonUtils.getCurDate("second"));
		list.add(raj);
		try {
			rajMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的risk_auth_job表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的risk_auth_job表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delRiskAuthJob(String oldCustId, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		rajMapper = session.getMapper(risk_auth_jobMapper.class);
		pcService = new p2p_customerService();
		try {
			rajMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的risk_auth_job表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的risk_auth_job表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	public void updateAuthStatus(String custId,String authStatus, SqlSession session){
		session = MybatisUtils.getFactory().openSession(true);
		rajMapper = session.getMapper(risk_auth_jobMapper.class);
		pcService = new p2p_customerService();
		risk_auth_job raj = rajMapper.selectByCustId(Long.valueOf(custId));
		raj.setAuthStatus(authStatus);
		try {
			rajMapper.updateByCustId(raj);
			Reporter.log("更新易才员工状态成功，在职状态为：" + authStatus);
			log.info("更新易才员工状态成功，在职状态为：" + authStatus);
		} catch (Exception e) {
			Reporter.log("更新易才员工状态时发生异常，更新失败" + e.getMessage());
			log.info("更新易才员工状态时发生异常，更新失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		
	}
}
