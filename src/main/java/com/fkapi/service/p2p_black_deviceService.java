/**
 * 
 */
package com.fkapi.service;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_black_deviceMapper;
import com.fkapi.database.domain.p2p_black_device;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.Log;

/**
 * @author Administrator
 *
 */
public class p2p_black_deviceService {
	Log log = new Log(p2p_black_deviceService.class);
	p2p_black_deviceMapper pbdMapper = null;
	
	public void addBlackDevice(JSONObject json, SqlSession session){
		delBlackDevice(json, session);
		pbdMapper = session.getMapper(p2p_black_deviceMapper.class);
		p2p_black_device pbd = new p2p_black_device();
		pbd.setDeviceCode(json.has("mobileSign") ? json.getString("mobileSign") : "999999999");
		pbd.setAddReason("测试添加黑名单");
		pbd.setAddSource("VCC");
		pbd.setCreateTime(CommonUtils.getCurDate("second"));
		try {
			pbdMapper.insert(pbd);
			Reporter.log("添加设备黑名单成功，设备号为： " + json.getString("mobileSign"));
		} catch (Exception e) {
			Reporter.log("添加设备黑名单时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delBlackDevice(JSONObject json, SqlSession session){
		pbdMapper = session.getMapper(p2p_black_deviceMapper.class);
		try {
			pbdMapper.deleteByDviceCode(json.has("mobileSign") ? json.getString("mobileSign") : "999999999");
			Reporter.log("删除设备黑名单成功，设备号为： " + json.getString("mobileSign"));
		} catch (Exception e) {
			Reporter.log("删除设备黑名单时发生异常，删除失败" + e.getMessage());
		}
	}
}
