package com.fkapi.service;

import com.fkapi.auth.createUserInfo;
import com.fkapi.auth.createVccCustomer;
import com.fkapi.database.dao.vcc_cust_bizMapper;
import com.fkapi.database.domain.vcc_cust_biz;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.ExcelUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
public class vcc_cust_bizService {
    vcc_cust_bizMapper vcbMapper ;
    createUserInfo cui ;
    vcc_customerService vcService ;
    createVccCustomer cvc = new createVccCustomer();

    public void addVccCustBiz(Map<String,String> userinfoMap, JSONObject json, SqlSession session, SqlSession vccSession){
        String excelPath = System.getProperty("user.dir") + "\\testcase.xlsx";
        vcbMapper = vccSession.getMapper(vcc_cust_bizMapper.class);
        cui = new createUserInfo();
        List<vcc_cust_biz> list ;
        vcc_cust_biz vcb = new vcc_cust_biz();
        //删除之前添加的数据
        delVccCustBiz(new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign"), vccSession);
        for (int i = 0; i < json.getInt("allCount"); i++) {
            JSONArray jsonArray = json.getJSONArray("orders");
            JSONObject orderStatusJson = jsonArray.getJSONObject(i);
            list = new ArrayList<>();
            Map<String,String> map = cui.create(ExcelUtils.getRowNoByValue(
                    excelPath, "userInfo", orderStatusJson.getString("userinfoNo")), false, session);
            cvc.create(map, false, vccSession);
            vcb.setCustId(Long.valueOf(map.get("custId")));
            vcb.setStatus(orderStatusJson.getString("status"));
            vcb.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"),orderStatusJson.getInt("time")));
            vcb.setUpdateTime(CommonUtils.getCurDate("second"));
            list.add(vcb);
            try{
                vcbMapper.insert(list);
                Reporter.log("添加custId为： " + map.get("custId") + "的vcc_cust_biz表数据成功");
            }catch (Exception e){
                Reporter.log("添加custId为： " + map.get("custId") + "的vcc_cust_biz表数据时发生异常，添加失败");
            }
        }
    }
    public void delVccCustBiz(String mobileSign, SqlSession vccSession){
        vcbMapper = vccSession.getMapper(vcc_cust_bizMapper.class);
        vcService = new vcc_customerService();
        try{
            vcbMapper.deleteByCustId(vcService.getCustId(mobileSign, vccSession));
        }catch (Exception e){
            Reporter.log("删除mobileSign为： " + vcService.getCustId(mobileSign, vccSession) + "的vcc_cust_biz表中的数据时发生异常，删除失败");
        }
    }
    @Test
    public void test(){
        SqlSession session = VCCMybatisUtils.getFactory().openSession(true);
        delVccCustBiz("999999", session);
    }
}
