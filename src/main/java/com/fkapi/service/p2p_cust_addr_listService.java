/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fkapi.database.dao.p2p_cust_addr_listMapper;
import com.fkapi.database.domain.p2p_cust_addr_list;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;

/**
 * @author Administrator
 *
 */
public class p2p_cust_addr_listService {
	p2p_customerService pcService = null;
	p2p_cust_addr_listMapper pcalMapper = null;
	int allNum,disNameNum,disMobileNum;

	/**
	 * 根据json参数添加特定的数据
	 * @param custId
	 * @param userMobile
	 * @param json
	 * @param session
	 */
	public void addCustAddrList(String oldCustId, String custId, String userMobile, JSONObject json, SqlSession session){
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		List<p2p_cust_addr_list> list ;
		p2p_cust_addr_list pcal ;
		pcService = new p2p_customerService();
		Random ranom = new Random();
		allNum = json.getInt("num");
		disNameNum = json.getInt("dinstinctName");
		disMobileNum = json.getInt("distinctMobile");

		delCustAddrList(oldCustId, session);

		if(allNum > 0){
			if(allNum >= disMobileNum && allNum >= disNameNum){
				//判断去重后姓名、手机号码个数，取最小值进行添加，disMobileNum、disNameNum最少要达到总数的一半，否则会出现问题
				if(disNameNum > disMobileNum){
					for(int i=0;i<disMobileNum;i++){
						list = new ArrayList<>();
						pcal = new p2p_cust_addr_list();
						pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
						pcal.setCustId(Long.valueOf(custId));
						pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + i + 1));
						pcal.setMobileName("测试i" + i);
						pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + i + 1));
						pcal.setCreateTime(CommonUtils.getCurDate("second"));
						pcal.setUpdateTime(CommonUtils.getCurDate("second"));
						list.add(pcal);
						pcalMapper.insert(list);
					}
					//判断如果去重后的姓名与总数相等，则添加去重后姓名与号码的差，此时所有数据添加完毕
					if(disNameNum == allNum){
						for(int j=0;j<disNameNum - disMobileNum;j++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + j + 1));
							pcal.setMobileName("测试j" + j);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + j + 1));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
					}else{
						//若去重后两者最大值小于allnum则添加剩余姓名重复部分
						for(int k=0;k<allNum - disNameNum;k++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + k + 1));
							pcal.setMobileName("测试i" + k);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + k + 1));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
						//添加剩余手机号重复部分
						for(int i=0;i<disNameNum - disMobileNum;i++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + i + 1));
							pcal.setMobileName("测试m" + i);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + i + 1));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
					}
					
				}else if(disNameNum < disMobileNum){
					for(int i=0;i<disNameNum;i++){
						list = new ArrayList<>();
						pcal = new p2p_cust_addr_list();
						pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
						pcal.setCustId(Long.valueOf(custId));
						pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + i + 1));
						pcal.setMobileName("测试i" + i);
						pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + i + 1));
						pcal.setCreateTime(CommonUtils.getCurDate("second"));
						pcal.setUpdateTime(CommonUtils.getCurDate("second"));
						list.add(pcal);
						pcalMapper.insert(list);
					}
					if(disMobileNum == allNum){
						for(int j=0;j<disMobileNum - disNameNum;j++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + j + 1000));
							pcal.setMobileName("测试i" + j);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + j + 1000));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
					}else{
						for(int k=0;k<allNum - disMobileNum;k++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + k + 1000));
							pcal.setMobileName("测试i" + k);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + k + 1000));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
						for(int i=0;i<disMobileNum - disNameNum;i++){
							list = new ArrayList<>();
							pcal = new p2p_cust_addr_list();
							pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
							pcal.setCustId(Long.valueOf(custId));
							pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + i + 1000));
							pcal.setMobileName("测试i" + i);
							pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + i + 1000));
							pcal.setCreateTime(CommonUtils.getCurDate("second"));
							pcal.setUpdateTime(CommonUtils.getCurDate("second"));
							list.add(pcal);
							pcalMapper.insert(list);
						}
					}
				}else{
					for(int j=0;j<disMobileNum;j++){
						list = new ArrayList<>();
						pcal = new p2p_cust_addr_list();
						pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
						pcal.setCustId(Long.valueOf(custId));
						pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + j + 1));
						pcal.setMobileName("测试i" + j);
						pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + j + 1));
						pcal.setCreateTime(CommonUtils.getCurDate("second"));
						pcal.setUpdateTime(CommonUtils.getCurDate("second"));
						list.add(pcal);
						pcalMapper.insert(list);
					}
					for(int k=0;k<allNum - disMobileNum;k++){
						list = new ArrayList<>();
						pcal = new p2p_cust_addr_list();
						pcal.setId(String.valueOf(System.currentTimeMillis())+ranom.nextInt(1000));
						pcal.setCustId(Long.valueOf(custId));
						pcal.setMobile(String.valueOf(Long.valueOf(userMobile) + k + 1));
						pcal.setMobileName("测试i" + k);
						pcal.setMobileNo(String.valueOf(Long.valueOf(userMobile) + k + 1));
						pcal.setCreateTime(CommonUtils.getCurDate("second"));
						pcal.setUpdateTime(CommonUtils.getCurDate("second"));
						list.add(pcal);
						pcalMapper.insert(list);
					}
				}
			}
		}
	}

	public void addCustAddrListForAddress(String oldCustId, String custId, String mobile, SqlSession session){
		delCustAddrList(oldCustId, session);
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		List<p2p_cust_addr_list> list = new ArrayList<>();
		p2p_cust_addr_list pcal = new p2p_cust_addr_list();
		pcService = new p2p_customerService();
		Random random = new Random();
		pcal.setId(String.valueOf(System.currentTimeMillis())+random.nextInt(1000));
		pcal.setCustId(Long.valueOf(custId));
		pcal.setMobile(mobile);
		pcal.setMobileNo(mobile);
		pcal.setMobileName("测试");
		pcal.setCreateTime(CommonUtils.getCurDate("second"));
		pcal.setUpdateTime(CommonUtils.getCurDate("second"));
		list.add(pcal);
		try{
			pcalMapper.insert(list);
			Reporter.log("添加mobile为： " + mobile + "的p2p_cust_addr_list表数据成功");
		}catch (Exception e){
			Reporter.log("添加mobile为： " + mobile + "的p2p_cust_addr_list表数据时发生异常" + e.getMessage());
		}
	}

	
	public void delCustAddrList(String oldCustId, SqlSession session){
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		pcService = new p2p_customerService();
		try {
			pcalMapper.deleteByCustId(Long.valueOf(oldCustId));
			Reporter.log("删除custId为： " + oldCustId + "的p2p_cust_addr_list表数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： " + oldCustId + "的p2p_cust_addr_list表数据时发生异常，删除失败" + e.getMessage());
		}
	}

	/**
	 * 根据标识更新手机号
	 * @param custId
	 * @param session
	 * @param option 参数为非标准手机号或非手机号
	 */
	public void updateCustAddrList(String custId, SqlSession session, String option){
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		pcService = new p2p_customerService();
		p2p_cust_addr_list pcal ;
		List<p2p_cust_addr_list> list ;
		list = getCustAddrListMobile(custId, session);
		if(list != null){
			pcal = list.get(0);
			if(option.equals("非标准手机号")){
				pcal.setMobile("86"+ pcal.getMobile());
				pcal.setMobileNo("86"+ pcal.getMobileNo());
			}
			if(option.equals("非手机号")){
				pcal.setMobile("10086");
				pcal.setMobileNo("10086");
			}
			try{
				pcalMapper.updateByCustId(pcal);
				Reporter.log("更新手机号码成功");
			}catch (Exception e){
				Reporter.log("更新手机号码时发生异常：" + e.getMessage());
			}
		}else {
			Reporter.log("根据custId:" + custId + "未获取到p2p_cust_addr_list表数据");
		}
	}

	/**
	 * 更新关键字是否含有“中介”，“办卡”等
	 */
	public void updateMobileName(String custId, String hasKeywords, SqlSession session){
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		pcService = new p2p_customerService();
		p2p_cust_addr_list pcal ;
		List<p2p_cust_addr_list> list = getCustAddrListMobile(custId, session);
		if(list.size() > 0){
			pcal = list.get(0);
			if (hasKeywords.toUpperCase().equals("Y")){
				pcal.setMobileName("中介");
				try{
					pcalMapper.updateByCustId(pcal);
					Reporter.log("更新手机号码成功");
				}catch (Exception e){
					Reporter.log("更新手机号码时发生异常：" + e.getMessage());
				}
			}
		}
	}

	public List<p2p_cust_addr_list> getCustAddrListMobile(String custId, SqlSession session){
		pcalMapper = session.getMapper(p2p_cust_addr_listMapper.class);
		pcService = new p2p_customerService();
		List<p2p_cust_addr_list> list = pcalMapper.selectByCustId(Long.valueOf(custId));
		return list;
	}

	
	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);
		JSONObject json = new JSONObject("{\"num\":\"10\",\"dinstinctName\":\"5\",\"distinctMobile\":\"5\"}");
		//addCustAddrList("ceshi01", "15221527941", json, session);
		updateCustAddrList("ceshi01",session,"非标准手机号");
	}
}
