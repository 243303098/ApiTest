/**
 * 
 */
package com.fkapi.yc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import com.fkapi.auth.createUserInfo;
import com.fkapi.service.p2p_base_customerService;
import com.fkapi.service.p2p_black_deviceService;
import com.fkapi.service.p2p_blacklist_storeService;
import com.fkapi.service.p2p_cert_authService;
import com.fkapi.service.p2p_loan_claimService;
import com.fkapi.service.p2p_nw_blacklistService;
import com.fkapi.service.risk_auth_jobService;
import com.fkapi.utils.Assertion;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;

/**
 * @author Administrator
 * 
 */
public class CreateYcTestData {

	String excelPath = System.getProperty("user.dir") + "\\testcase.xlsx";

	String ycDataSheetName = "易才数据生成";

	private String authSheetName = "易才认证";

	Map<String, String> userInfoMap ;
	Map<String, String> ycAuthMap ;

	public Map<String, String> getUserInfoMap() {
		return userInfoMap;
	}

	public void setUserInfoMap(Map<String, String> userInfoMap) {
		this.userInfoMap = userInfoMap;
	}

	public void creditYCTestData(String ycAuthNo, String userInfoNo,
								 Integer dataNo, SqlSession session, SqlSession vccSession) {


		p2p_base_customerService pbcService ;
		p2p_cert_authService pcaService ;
		p2p_nw_blacklistService pnbService ;
		p2p_blacklist_storeService pbsService ;
		p2p_black_deviceService pbdService ;
		risk_auth_jobService rajService ;
		p2p_loan_claimService plcService ;
		createUserInfo cui ;
		YcAuthInfo yai ;
		JSONObject json = null;
		cui = new createUserInfo();
		yai = new YcAuthInfo();

		Map<String, String> map = getYCData(dataNo);
		// 判断用户基础信息和易才认证信息是否有数据，有则根据对应的名称新增数据
		try {
			// 根据userInfoNo创建用户基础信息并获取用户基础信息，并转成map形式存储（userinfo表）
			setUserInfoMap(cui.create(ExcelUtils.getRowNoByValue(excelPath, "userInfo",
					userInfoNo), true, session));
			// 根据ycAuthNo创建用户的易才认证信息
			ycAuthMap = yai.creditYCAuth(
					ExcelUtils.getRowNoByValue(excelPath, authSheetName, ycAuthNo),
					userInfoNo, session);
		} catch (Exception e1) {
			Reporter.log("创建用户信息时出现异常" + e1.getMessage());
		}

		// 根据用户输入的年龄，更新对应的身份证信息
		if (!map.get("年龄").trim().isEmpty()) {
			pbcService = new p2p_base_customerService();
			pcaService = new p2p_cert_authService();
			json = new JSONObject();
			json.put("certNo", CommonUtils.creteIdCardByAge(
					userInfoMap.get("custId"), map.get("年龄"), session));
			pbcService.update(userInfoMap.get("custId"), json, "age",
					session);
			pcaService.update(userInfoMap.get("custId"), CommonUtils
					.creteIdCardByAge(userInfoMap.get("custId"),
							map.get("年龄"), session), session);
		}

		// 根据输入的信息判断是否将用户添加进黑名单
		if (map.get("内部黑名单") != null && map.get("内部黑名单").equals("Y")) {
			pnbService = new p2p_nw_blacklistService();
			pnbService.delNwBlackList(userInfoMap.get("oldCustId"), session);
			pnbService.addNwBlackList(userInfoMap.get("oldCustId"), userInfoMap.get("custId"), session);
		} else if (map.get("内部黑名单") != null && map.get("内部黑名单").equals("N")) {
			pnbService = new p2p_nw_blacklistService();
			pnbService.delNwBlackList(userInfoMap.get("oldCustId"), session);
		}

		// 将用户输入的身份信息加入到黑名单中
		if (!map.get("外部黑名单").trim().isEmpty()) {
			pbsService = new p2p_blacklist_storeService();
			JSONObject inputJson = null;
			try {
				inputJson = new JSONObject(map.get("外部黑名单"));
				json = new JSONObject(userInfoMap.get("certAuth"));
			} catch (Exception e) {
				Reporter.log("参数转json时出错，参数为：" + map.get("外部黑名单")
						+ userInfoMap.get("certAuth"));
				Assertion.verifyTure(false);
			}
			// 删除使用的基础用户的黑名单信息
			pbsService.delBlackListStore(json, session);
			// 删除case中用户输入的信息
			pbsService.delBlackListStore(inputJson, session);
			// 将用户输入的身份信息添加到外部黑名单中
			pbsService.addBlackListStore(inputJson, session);
		} else {
			// 如果为空则表示不添加用户进入黑名单
			pbsService = new p2p_blacklist_storeService();
			json = new JSONObject(userInfoMap.get("certAuth"));
			pbsService.delBlackListStore(json, session);
		}

		// 根据输入的信息判断是否将设备加入到黑名单
		if (!map.get("设备黑名单").trim().isEmpty()) {
			pbdService = new p2p_black_deviceService();
			try {
				json = new JSONObject(ycAuthMap.get("借款订单"));
			} catch (Exception e) {
				Reporter.log("参数转json时出错，参数为：" + ycAuthMap.get("借款订单"));
				Assertion.verifyTure(false);
			}
			pbdService.delBlackDevice(json, session);
			if (map.get("设备黑名单").equals("Y")) {
				pbdService.addBlackDevice(json, session);
			}
		}

		// 根据输入的信息判断是否为易才在职员工
		if (!map.get("易才员工").trim().isEmpty()) {
			rajService = new risk_auth_jobService();
			if (map.get("易才员工").equals("Y")) {
				rajService.updateAuthStatus(userInfoMap.get("custId"), "AS",
						session);
			}
			if (map.get("易才员工").equals("N")) {
				rajService.updateAuthStatus(userInfoMap.get("custId"), "AF",
						session);
			}
		}

		// 判断上笔订单信息
		if (!map.get("上笔订单").trim().isEmpty()) {
			plcService = new p2p_loan_claimService();
			json = new JSONObject(map.get("上笔订单"));
			plcService.addProject(userInfoMap, json, false, true, session);
		}
	}

	public Map<String, String> getYCData(Integer dataNo) {
		Map<String, String> map ;
		int allColNum = 0;
		try {
			allColNum = ExcelUtils.getAllColNum(System.getProperty("user.dir")
					+ "\\testcase.xlsx", "易才数据生成", 0);
		} catch (IOException e) {
			Reporter.log("获取Excel的信息失败");
		}
		map = new HashMap<>();
		for (int j = 0; j < allColNum; j++) {
			map.put(ExcelUtils.getCellDate(excelPath, ycDataSheetName, 0, j),
					ExcelUtils.getCellDate(excelPath, ycDataSheetName, dataNo, j));
		}
		return map;
	}

}
