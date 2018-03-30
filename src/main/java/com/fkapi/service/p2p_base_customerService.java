/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_base_customerMapper;
import com.fkapi.database.domain.p2p_base_customer;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 * 
 */
public class p2p_base_customerService {
	Log log = new Log(p2p_base_customerService.class);
	p2p_base_customerMapper pbcMapper = null;
	p2p_customerService pcService = null;
	p2p_mobile_addrService pmaService = null;
	p2p_dictionaryService pdService = null;
	JSONObject json = null;

	public void addBaseCustomer(String oldCustId, String custId, SqlSession session) {
		delBaseCustomer(oldCustId, session);
		pbcMapper = session.getMapper(p2p_base_customerMapper.class);
		List<p2p_base_customer> list = new ArrayList<>();
		p2p_base_customer pbc ;
		pcService = new p2p_customerService();
		pmaService = new p2p_mobile_addrService();
		pbc = new p2p_base_customer();
		pbc.setCustId(Long.valueOf(custId));
		pbc.setCreateTime(CommonUtils.getCurDate("second"));
		pbc.setUpdateTime(CommonUtils.getCurDate("second"));
		list.add(pbc);
		try {
			pbcMapper.insert(list);
			Reporter.log("添加custId为：" + custId + "的p2p_base_customer表的数据成功");
			log.info("添加custId为：" + custId + "的p2p_base_customer表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为：" + custId + "的p2p_base_customer表的数据时发生异常，添加失败" + e.getMessage());
			log.error("添加custId为：" + custId + "的p2p_base_customer表的数据时发生异常，添加失败" + e.getMessage());
		}
	}

	public void delBaseCustomer(String oldCustId, SqlSession session) {
		pbcMapper = session.getMapper(p2p_base_customerMapper.class);
		pcService = new p2p_customerService();
		try {
			pbcMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除loginName为：" + loginName + "的p2p_base_customer表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为：" + oldCustId + "的p2p_base_customer表的数据时发生异常，删除失败" + e.getMessage());
			log.error("删除custId为：" + oldCustId + "的p2p_base_customer表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
	
	//更新p2p_base_customer表信息
	public void update(String custId, JSONObject json, String option, SqlSession session){
		pbcMapper = session.getMapper(p2p_base_customerMapper.class);
		pcService = new p2p_customerService();
		pmaService = new p2p_mobile_addrService();
		pdService = new p2p_dictionaryService();
		p2p_base_customer pbc = pbcMapper.selectByCustId(Long.valueOf(custId));
		if(option.equals("certAuth")){
			if (json.has("custName")){
				pbc.setCustName(json.getString("custName"));
			}
			if (json.has("idNo")){
				pbc.setCertNo(json.getString("idNo"));
			}
			if (json.has("certAuthStatus")){
				pbc.setCertAuth(json.getString("certAuthStatus"));
			}

			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新certAuth为： "+ json.toString() + "的数据成功");
				log.info("更新certAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新certAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新certAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("educationAuth")){
			pbc.setSchoolRoll(json.getString("clogLevel"));
			pbc.setEducationAuth(json.getString("educationAuthStatus"));
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新educationAuth为： "+ json.toString() + "的数据成功");
				log.info("更新educationAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新educationAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新educationAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("phoneAuth")){
			pbc.setTel(json.getString("mobile"));
			pbc.setPhoneAuth(json.getString("phoneAuthStatus"));
			if (pmaService.getMobileTable(json.getString("mobile"), session) != null){
				pbc.setMobileProvince(pmaService.getMobileTable(json.getString("mobile"), session).getProvinceCode());
				pbc.setMobileCity(pmaService.getMobileTable(json.getString("mobile"), session).getCityCode());
			}else {
				pbc.setMobileProvince(null);
				pbc.setMobileCity(null);
			}
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新phoneAuth为： "+ json.toString() + "的数据成功");
				log.info("更新phoneAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新phoneAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新phoneAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("photoAuth")){
			pbc.setPhotoAuth(json.getString("photoAuthStatus"));
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新photoAuth为： "+ json.toString() + "的数据成功");
				log.info("更新photoAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新photoAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新photoAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("schoolRollAuth")){
			pbc.setSchoolRoll(json.getString("clogLevel"));
			pbc.setSchoolRollAuth(json.getString("schoolRollAuthStatus"));
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新schoolRollAuth为： "+ json.toString() + "的数据成功");
				log.info("更新schoolRollAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新schoolRollAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新schoolRollAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("addrAuth")){
			pbc.setNowFullAddr(json.getString("fullAddr"));
			pbc.setNowAddrProvince(pdService.getDictCode(json.getString("nowProvince"),session));
			pbc.setNowAddrCity(pdService.getDictCode(json.getString("nowCity"),session));
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新addrAuth为： "+ json.toString() + "的数据成功");
				log.info("更新addrAuth为： "+ json.toString() + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新addrAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
				log.error("更新addrAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
		if(option.equals("age")){
			pbc.setCertNo(json.getString("certNo"));
			try {
				pbcMapper.updateByCustId(pbc);
				Reporter.log("更新certNo为：" + json.getString("certNo") + "的数据成功");
				log.info("更新certNo为：" + json.getString("certNo") + "的数据成功");
			} catch (Exception e) {
				Reporter.log("更新certNo为：" + json.getString("certNo") + "的数据时发生异常，更新失败" + e.getMessage()); 
				log.error("更新addrAuth为： "+ json.toString() + "的数据时发生异常，更新失败" + e.getMessage());
			}
		}
	}

}
