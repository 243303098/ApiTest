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
import com.fkapi.database.dao.p2p_customer_contactorMapper;
import com.fkapi.database.domain.p2p_customer_contactor;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_customer_contactorService {
	p2p_customer_contactorMapper pccMapper = null;
	p2p_customerService pcService = null;
	p2p_dictionaryService pdService = null;
	
	public void addCustomerContactor(String oldCustId, String custId, JSONObject json, SqlSession session){
		delCustomerContactor(oldCustId, session);
		pccMapper = session.getMapper(p2p_customer_contactorMapper.class);
		List<p2p_customer_contactor> list = new ArrayList<>();
		p2p_customer_contactor pcc = new p2p_customer_contactor();
		pcService = new p2p_customerService();
		pdService = new p2p_dictionaryService();
		pcc.setCustId(Long.valueOf(custId));
		pcc.setCreateTime(CommonUtils.getCurDate("day"));
		pcc.setProvince(pdService.getDictCode(json.getString("province"),session));
		pcc.setCity(pdService.getDictCode(json.getString("city"),session));
		list.add(pcc);
		Reporter.log("添加p2p_customer_contactor表的参数为： " + json.toString());
		try {
			pccMapper.insert(list);
			Reporter.log("添加的联系人信息为： "+ json);
			Reporter.log("添加custId为："+ custId + "的p2p_customer_contactor表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加的联系人信息为： "+ json);
			Reporter.log("添加custId为："+ custId + "的p2p_customer_contactor表的数据时出现异常，添加失败" + e.getMessage());
		}
	} 
	
	public void delCustomerContactor(String oldCustId, SqlSession session){
		pccMapper = session.getMapper(p2p_customer_contactorMapper.class);
		pcService = new p2p_customerService();
		try {
			pccMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为：" + custId + "的p2p_customer_contactor表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_customer_contactor表的数据时发生异常，删除失败" + e.getMessage());
		}
	}

	public String getCustomerContactor(String custId, SqlSession session){
		pccMapper = session.getMapper(p2p_customer_contactorMapper.class);
		pcService = new p2p_customerService();
		try{
			p2p_customer_contactor pcc = pccMapper.selectByCustId(Long.valueOf(custId));
			if(pcc != null){
				return pcc.getCity();
			}else {
				return null;
			}
		}catch (Exception e){
			Reporter.log("根据custId：" + custId + "获取城市编码时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}
	
	@Test
	public void test(){
		
	}
}
