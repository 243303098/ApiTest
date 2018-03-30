/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_customer_companyMapper;
import com.fkapi.database.domain.p2p_customer_company;

/**
 * @author Administrator
 *
 */
public class p2p_customer_companyService {
	p2p_customer_companyMapper pccMapper = null;
	p2p_customerService pcService = null;
	
	public void addCustomerCompany(String oldCustId, String custId,JSONObject json, SqlSession session){
		delCustomerCompany(oldCustId, session);
		pccMapper = session.getMapper(p2p_customer_companyMapper.class);
		List<p2p_customer_company> list = new ArrayList<>();
		pcService = new p2p_customerService();
		p2p_customer_company pcc = new p2p_customer_company();
		pcc.setCustId(Long.valueOf(custId));
		pcc.setCompName(json.getString("companyName"));
		pcc.setCompAddr(json.getString("companyAdd"));
		pcc.setCompAddrProvinceCode(json.getString("companyProvice"));
		pcc.setCompAddrCityCode(json.getString("companyCity"));
		list.add(pcc);
		Reporter.log("添加p2p_customer_company表的参数为： " + json.toString());
		try {
			pccMapper.insert(list);
			Reporter.log("添加公司信息为："+ json.toString() + "的p2p_customer_company表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加公司信息为："+ json.toString() + "的p2p_customer_company表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delCustomerCompany(String oldCustId, SqlSession session){
		pccMapper = session.getMapper(p2p_customer_companyMapper.class);
		pcService = new p2p_customerService();
		try {
			pccMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("添加custId为："+ custId + "的p2p_customer_company表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为："+ oldCustId + "的p2p_customer_company表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
