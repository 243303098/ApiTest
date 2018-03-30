package com.fkapi.service;

import com.fkapi.database.dao.vcc_repay_planMapper;
import com.fkapi.database.domain.vcc_loan_info;
import com.fkapi.database.domain.vcc_repay_plan;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
public class vcc_repay_planService {
    vcc_repay_planMapper vrpMapper ;
    vcc_loan_infoService vliService ;

    public void addVccRepalyPlan(Map<String,String> userinfoMap, JSONObject json, Boolean isCreditOrder, SqlSession vccSession){
        vrpMapper = vccSession.getMapper(vcc_repay_planMapper.class);
        vliService = new vcc_loan_infoService();
        List<vcc_repay_plan> list ;
        vcc_repay_plan vrp = new vcc_repay_plan();
        delVccRepalyPlan(userinfoMap.get("oldCustId"), vccSession);
        List<vcc_loan_info> successOrderNoList = vliService.getVccLonaInfo(userinfoMap.get("custId"), vccSession);
        if (!isCreditOrder){
            for (int i = 0; i < successOrderNoList.size(); i++) {
                list = new ArrayList<>();
                vrp.setLoanNo(successOrderNoList.get(i).getLoanNo());
                vrp.setCustId(Long.valueOf(userinfoMap.get("custId")));
                vrp.setRepayDate(CommonUtils.subMonth(CommonUtils.getCurDate("second"),1));
                vrp.setRepayStatus(json.getString("repayStatus"));
                vrp.setBillOutFlag(json.getString("billOutFlag"));
                vrp.setDelFlag(json.getString("delFlag"));
                vrp.setCreateTime(CommonUtils.getCurDate("second"));
                vrp.setUpdateTime(CommonUtils.getCurDate("second"));
                list.add(vrp);
                try{
                    vrpMapper.insert(list);
                    Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的vcc_repay_plan表中的数据成功");
                }catch (Exception e){
                    Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的vcc_repay_plan表中的数据时发生异常" + e.getMessage());
                }
            }
        }
    }

    public void delVccRepalyPlan(String custId, SqlSession vccSession){
        vrpMapper = vccSession.getMapper(vcc_repay_planMapper.class);
        try{
            vrpMapper.deleteByCustId(Long.valueOf(custId));
        }catch (Exception e){
            Reporter.log("删除custId为： " + custId + "的vcc_repay_plan表中的数据时发生异常" + e.getMessage());
        }
    }
}
