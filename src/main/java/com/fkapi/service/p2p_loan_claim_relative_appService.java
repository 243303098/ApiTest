/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;

import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_loan_claim_relative_appMapper;
import com.fkapi.database.domain.p2p_loan_claim_relative_app;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_loan_claim_relative_appService {
	Log log = new Log(p2p_loan_claim_relative_appService.class);
	p2p_loan_claim_relative_appMapper plcraMapper = null;
	
	public void addLoanDevice(String proJectNo, String deviceCode, SqlSession session){
		plcraMapper = session.getMapper(p2p_loan_claim_relative_appMapper.class);
		p2p_loan_claim_relative_app plcra = new p2p_loan_claim_relative_app();
		List<p2p_loan_claim_relative_app> list = new ArrayList<>();
		
		plcra.setProjectNo(proJectNo);
		plcra.setMobileSign(deviceCode);
		plcra.setCreateTime(CommonUtils.getCurDate("second"));
		list.add(plcra);
		try {
			plcraMapper.insert(list);
			Reporter.log("添加借款设备成功，设备号为： " + deviceCode);
			log.info("添加借款设备成功，设备号为： " + deviceCode);
		} catch (Exception e) {
			Reporter.log("添加借款设备时发生异常，添加失败" + e.getMessage());
			log.info("添加借款设备时发生异常，添加失败" + e.getMessage());
		}
	}

	public void delLoanDevice(String deviceCode, SqlSession session){
		plcraMapper = session.getMapper(p2p_loan_claim_relative_appMapper.class);
		try{
			plcraMapper.deleteByMobileSign(deviceCode);
		}catch (Exception e){
			Reporter.log("删除deviceCode为：" + deviceCode + "的借款设备时发生异常，添加失败" + e.getMessage());
		}
	}
	
	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		delLoanDevice("999999999", session);
	}
}
