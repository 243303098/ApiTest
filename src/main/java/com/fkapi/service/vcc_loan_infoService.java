package com.fkapi.service;

import com.fkapi.database.dao.vcc_loan_infoMapper;
import com.fkapi.database.domain.vcc_loan_info;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/20.
 */
public class vcc_loan_infoService {

    vcc_loan_infoMapper vliMapper ;

    public void addVccLoanInfo(Map<String, String> userInfoMap, JSONObject json, String isOverDue, SqlSession vccSession){
        vliMapper = vccSession.getMapper(vcc_loan_infoMapper.class);
        List<vcc_loan_info> list ;
        vcc_loan_info vli = new vcc_loan_info();
        delVccLoanInfo(userInfoMap.get("oldCustId"), vccSession);
        for (int i = 0; i < json.getInt("sucessOrders"); i++) {
            list = new ArrayList<>();
            vli.setLoanNo(UUID.randomUUID().toString().replace("-", ""));
            vli.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
            vli.setCardNo(new JSONObject(userInfoMap.get("certAuth")).getString("cardNo"));
            vli.setCustId(Long.valueOf(userInfoMap.get("custId")));
            vli.setStatus("1");
            if(isOverDue.trim().toUpperCase().equals("Y")){
                vli.setOverdueFlag("Y");
            }
            if (json.getString("isSameTime").toUpperCase().equals("Y")) {
                vli.setLoanDate(CommonUtils.getCurDate("second"));
                vli.setCreateTime(CommonUtils.getCurDate("second"));
                vli.setUpdateTime(CommonUtils.getCurDate("second"));
            } else {
                if (json.getString("timeType").toUpperCase().equals("D")) {
                    vli.setLoanDate(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vli.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vli.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                }
                if (json.getString("timeType").toUpperCase().equals("M")) {
                    vli.setLoanDate(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vli.setCreateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vli.setUpdateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                }
            }
            list.add(vli);
            try{
                vliMapper.insert(list);
                Reporter.log("根据custId: " + userInfoMap.get("custId") + "添加vcc_loan_info表中的数据成功");
            }catch (Exception e){
                Reporter.log("根据custId: " + userInfoMap.get("custId") + "添加vcc_loan_info表中的数据时发生异常，添加失败" + e.getMessage());
            }
        }
    }

    public void delVccLoanInfo(String custId, SqlSession vccSession){
        vliMapper = vccSession.getMapper(vcc_loan_infoMapper.class);
        try{
            vliMapper.deleteByCustId(Long.valueOf(custId));
        }catch (Exception e){
            Reporter.log("根据custId： " + custId + "删除vcc_loan_info表中的数据是发生异常，删除失败" + e.getMessage());
        }
    }

    public List<vcc_loan_info> getVccLonaInfo(String custId, SqlSession vccSession){
        vliMapper = vccSession.getMapper(vcc_loan_infoMapper.class);
        vcc_loan_info vli = new vcc_loan_info();
        vli.setCustId(Long.valueOf(custId));
        List<vcc_loan_info> list = vliMapper.selectByCustId(vli);
        return list;
    }

    @Test
    public void test(){
        SqlSession session = VCCMybatisUtils.getFactory().openSession(true);
        getVccLonaInfo("1", session);
    }
}
