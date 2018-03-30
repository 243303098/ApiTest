/**
 * 
 */
package com.fkapi.service;

import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import com.fkapi.database.dao.risk_credit_yicai_tiqianhua_logMapper;

/**
 * @author Administrator
 *
 */
public class risk_credit_yicai_tiqianhua_logService {
	risk_credit_yicai_tiqianhua_logMapper rtlMapper = null;
	
	/**
	 * 根据custId删除该用户的额度表日志
	 */
	public void delRiskTLog(String oldCustId, SqlSession session){
		rtlMapper = session.getMapper(risk_credit_yicai_tiqianhua_logMapper.class);
		p2p_customerService pcService = new p2p_customerService();
		try {
			rtlMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为： " + custId + "的risk_credit_yicai_tiqianhua_log表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： " + oldCustId + "的risk_credit_yicai_tiqianhua_log表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
