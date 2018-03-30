package com.fkapi.service;

import com.fkapi.database.dao.vcc_customerMapper;
import com.fkapi.database.domain.vcc_customer;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
public class vcc_customerService {
    vcc_customerMapper vccCutomerMapper = null ;

    public void addVccCustomerService(Map<String, String> userinfoMap, SqlSession vccSession){
        vccSession = VCCMybatisUtils.getFactory().openSession(true);
        vccCutomerMapper = vccSession.getMapper(vcc_customerMapper.class);
        List<vcc_customer> list = new ArrayList<>();
        vcc_customer vc = new vcc_customer();
        vc.setCustId(Long.valueOf(userinfoMap.get("custId")));
        vc.setLoginName(userinfoMap.get("loginName"));
        vc.setCustName(new JSONObject(userinfoMap.get("certAuth")).getString("custName"));
        vc.setCertNo(new JSONObject(userinfoMap.get("certAuth")).getString("idNo"));
        vc.setMobile(new JSONObject(userinfoMap.get("phoneAuth")).getString("mobile"));
        vc.setMobileSign(new JSONObject(userinfoMap.get("phoneAuth")).getString("mobileSign"));
        vc.setCreateTime(CommonUtils.getCurDate("second"));
        vc.setUpdateTime(CommonUtils.getCurDate("second"));
        list.add(vc);
        try{
            vccCutomerMapper.insert(list);
            Reporter.log("添加loginName为：" +userinfoMap.get("loginName")+ "的vcc_customer表的数据成功");
        }catch (Exception e){
            Reporter.log("添加loginName为：" +userinfoMap.get("loginName")+ "的vcc_customer表的数据时发生异常，添加失败");
        }
    }

    public void delVccCustomerService(String loginName, SqlSession vccSession){
        vccSession = VCCMybatisUtils.getFactory().openSession(true);
        vccCutomerMapper = vccSession.getMapper(vcc_customerMapper.class);
        try{
            vccCutomerMapper.deleteByLoginName(loginName);
        }catch (Exception e){
            Reporter.log("根据loginName: " + loginName + "删除vcc_customer表数据时发生异常，删除失败");
        }
    }

    public void delVccCustomerByMobileSign(String mobileSign, SqlSession vccSession){
        vccSession = VCCMybatisUtils.getFactory().openSession(true);
        vccCutomerMapper = vccSession.getMapper(vcc_customerMapper.class);
        try{
            vccCutomerMapper.deleteByMobileSign(mobileSign);
        }catch (Exception e){
            Reporter.log("根据loginName: " + mobileSign + "删除vcc_customer表数据时发生异常，删除失败");
        }
    }

    public List<Long> getCustId(String mobileSign, SqlSession vccSession){
        vccSession = VCCMybatisUtils.getFactory().openSession(true);
        vccCutomerMapper = vccSession.getMapper(vcc_customerMapper.class);
        List<Long> list = vccCutomerMapper.selectByMobileSign(mobileSign);
        return list;
    }

    @Test
    public void test(){
        SqlSession session = VCCMybatisUtils.getFactory().openSession(true);
        getCustId("999999", session);
    }

}
