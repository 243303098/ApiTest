package com.fkapi.service;

import com.fkapi.database.dao.p2p_cooperation_employeeMapper;
import com.fkapi.database.domain.p2p_cooperation_employee;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/3.
 */
public class p2p_cooperation_employeeService {
    p2p_cooperation_employeeMapper pceMapper ;

    public void addCooperationEmployee(Map<String,String> userInfoMap, JSONObject json, SqlSession session){
        //delCooperationEmployee(new JSONObject(userInfoMap.get("工作认证")).getLong("companyId"), session);
        session = MybatisUtils.getFactory().openSession(true);
        pceMapper = session.getMapper(p2p_cooperation_employeeMapper.class);
        List<p2p_cooperation_employee> list = new ArrayList<>();
        p2p_cooperation_employee pce = new p2p_cooperation_employee();
        JSONObject certJson = new JSONObject(userInfoMap.get("certAuth"));
        JSONObject telJson = new JSONObject(userInfoMap.get("phoneAuth"));
        JSONObject addrJson = new JSONObject(userInfoMap.get("nowAddress"));
        pce.setCompanyId(json.getLong("companyId"));
        pce.setCompanyName(json.getString("companyName"));
        pce.setCustName(certJson.getString("custName"));
        pce.setCertNo(certJson.getString("idNo"));
        pce.setTel(telJson.getString("mobile"));
        pce.setFamilyAddr(addrJson.getString("fullAddr"));
        pce.setEntryDate(CommonUtils.getCurDate("second"));
        if (json.getString("laborRelationsType").startsWith("正式")){
            pce.setLaborRelationsType(1);
        }
        if (json.getString("laborRelationsType").startsWith("临时")){
            pce.setLaborRelationsType(2);
        }
        if (json.getString("workStatus").startsWith("试用")){
            pce.setWorkStatus(1);
        }
        if (json.getString("workStatus").startsWith("正常")){
            pce.setWorkStatus(2);
        }
        if (json.getString("workStatus").startsWith("申请")){
            pce.setWorkStatus(3);
        }
        if (json.getString("workStatus").startsWith("辞职")){
            pce.setWorkStatus(4);
        }
        pce.setMonthlySalaryRange(json.getString("monthlySalaryMin") + "-" + json.getString("monthlySalaryMax"));
        pce.setMonthlySalaryMax(json.getInt("monthlySalaryMax"));
        pce.setMonthlySalaryMin(json.getInt("monthlySalaryMin"));
        pce.setCreateTime(CommonUtils.getCurDate("second"));
        pce.setUpdateTime(CommonUtils.getCurDate("second"));
        list.add(pce);
        try {
            pceMapper.insert(list);
            Reporter.log("添加p2p_cooperation_employee表中的数据成功");
        }catch (Exception e){
            Reporter.log("添加p2p_cooperation_employee表中的数据失败" + e.getMessage());
        }
    }

    public void delCooperationEmployee(Long companyId, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        pceMapper = session.getMapper(p2p_cooperation_employeeMapper.class);
        try {
            pceMapper.deleteByCompanyId(companyId);
            Reporter.log("删除p2p_cooperation_employee表中的数据成功");
        }catch (Exception e){
            Reporter.log("删除p2p_cooperation_employee表中的数据失败" + e.getMessage());
        }
    }

    public p2p_cooperation_employee getCooperationEmployee(Map<String,String> userInfoMap, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        pceMapper = session.getMapper(p2p_cooperation_employeeMapper.class);
        JSONObject certJson = new JSONObject(userInfoMap.get("certAuth"));
        p2p_cooperation_employee pce ;
        pce = pceMapper.selectByCertNo(certJson.getString("idNo"));
        if (pce != null){
            return pce;
        }else {
            return null;
        }
    }

    /**
     * 更新工作信息
     * @param userInfoMap
     * @param data
     * @param session
     */
    public void update(Map<String,String> userInfoMap, String data, SqlSession session, String option){
        session = MybatisUtils.getFactory().openSession(true);
        pceMapper = session.getMapper(p2p_cooperation_employeeMapper.class);
        p2p_cooperation_employee pce = getCooperationEmployee(userInfoMap, session);

        if (option.equals("entryDate")){
            pce.setEntryDate(CommonUtils.subMonth(pce.getEntryDate(), Integer.valueOf(data)));
        }

        if (option.equals("laborRelationsType")){
            if (data.startsWith("正式")){
                pce.setLaborRelationsType(1);
            }
            if (data.startsWith("临时")){
                pce.setLaborRelationsType(2);
            }
        }

        if (option.equals("workStatus")){
            if (data.startsWith("试用")){
                pce.setWorkStatus(1);
            }
            if (data.startsWith("正常")){
                pce.setWorkStatus(2);
            }
            if (data.startsWith("申请")){
                pce.setWorkStatus(3);
            }
            if (data.startsWith("辞职")){
                pce.setWorkStatus(4);
            }
        }

        if (option.equals("monthlySalaryMax")){
            pce.setMonthlySalaryMax(Integer.valueOf(data));
        }
        try {
            pceMapper.updateByCertNo(pce);
        }catch (Exception e){
            Reporter.log("更新入职日期失败" + e.getMessage());
        }
    }
}
