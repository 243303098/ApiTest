/**
 * 
 */
package com.fkapi.service;

import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_mobile_addrMapper;
import com.fkapi.database.domain.p2p_mobile_addr;

/**
 * @author Administrator
 *
 */
public class p2p_mobile_addrService {
	p2p_mobile_addrMapper pmaMapper = null;
	p2p_mobile_addr pma = null;

	public p2p_mobile_addr getMobileTable(String mobile, SqlSession session) {
		pmaMapper = session.getMapper(p2p_mobile_addrMapper.class);
		pma = new p2p_mobile_addr();
		String m = mobile.substring(0, 7);
		try {
			pma = pmaMapper.selectByMobile(m);
			if (pma == null) {
				Reporter.log("根据截取后mobile: " + m + "未获取到手机号码城市编码");
				return null;
			} else {
				return pma;
			}
		} catch (Exception e) {
			Reporter.log("根据截取后mobile: " + m + "获取p2p_mobile_addr表的数据时发生异常，获取失败" + e.getMessage());
			return null;
		}
	}

	public String getMobileByAddr(String cityCode, SqlSession session) {
		pmaMapper = session.getMapper(p2p_mobile_addrMapper.class);
		pma = new p2p_mobile_addr();
		try {
			pma = pmaMapper.selectByCityCode(cityCode);
			if (pma == null) {
				Reporter.log("根据cityCode: " + cityCode + "未获取到手机号码段");
				return null;
			} else {
				return pma.getMobile();
			}
		} catch (Exception e) {
			Reporter.log("根据cityCode: " + cityCode + "获取手机号码段时发生异常,获取失败！" + e.getMessage());
			return null;
		}
	}

	/**
	 *	根据省份编码获取该省份的手机号段
	 * @param provinceCode
	 * @param session
	 * @return
	 */
	public String getMobileByProvince(String provinceCode, SqlSession session) {
		pmaMapper = session.getMapper(p2p_mobile_addrMapper.class);
		pma = new p2p_mobile_addr();
		try {
			pma = pmaMapper.selectByProvinceCode(provinceCode);
			if (pma == null) {
				Reporter.log("根据cityCode: " + provinceCode + "未获取到手机号码段");
				return null;
			} else {
				return pma.getMobile();
			}
		} catch (Exception e) {
			Reporter.log("根据cityCode: " + provinceCode + "获取手机号码段时发生异常,获取失败！" + e.getMessage());
			return null;
		}
	}
	/**
	 *	根据省份获取除该省份外的其他省份的手机码段
	 */
	public String getOtherMobileByProvince(String provinceCode, SqlSession session) {
		pmaMapper = session.getMapper(p2p_mobile_addrMapper.class);
		pma = new p2p_mobile_addr();
		try {
			pma = pmaMapper.selectByOtherProvinceCode(provinceCode);
			if (pma == null) {
				Reporter.log("provinceCode: " + provinceCode + "未获取到手机号码段");
				return null;
			} else {
				return pma.getMobile();
			}
		} catch (Exception e) {
			Reporter.log("provinceCode: " + provinceCode + "获取手机号码段时发生异常,获取失败！" + e.getMessage());
			return null;
		}
	}
	/**
	 * select * from p2p_mobile_addr where city_code != ?;
	 * 查询非输入城市的手机号码段
	 * @param cityCode
	 * @param session
	 * @return
	 */
	public String getOtherMobileByAddr(String cityCode, SqlSession session) {
		pmaMapper = session.getMapper(p2p_mobile_addrMapper.class);
		pma = new p2p_mobile_addr();
		try {
			pma = pmaMapper.selectByOtherCityCode(cityCode);
			if (pma == null) {
				Reporter.log("根据cityCode: " + cityCode + "未获取到手机号码段");
				return null;
			} else {
				return pma.getMobile();
			}
		} catch (Exception e) {
			Reporter.log("根据cityCode: " + cityCode + "获取手机号码段时发生异常,获取失败！" + e.getMessage());
			return null;
		}
	}

	@Test
	public void test(){
		System.out.println();
	}
}
