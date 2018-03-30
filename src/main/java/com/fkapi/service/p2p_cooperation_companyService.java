package com.fkapi.service;

import com.fkapi.database.dao.p2p_cooperation_companyMapper;
import com.fkapi.database.domain.p2p_cooperation_company;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */
public class p2p_cooperation_companyService {
    p2p_cooperation_companyMapper pccMapper ;
    p2p_dictionaryService pdService ;

    public void addCustCompany(JSONObject json, SqlSession session){
        delCustCompany("测试公司", session);
        session = MybatisUtils.getFactory().openSession(true);
        pccMapper = session.getMapper(p2p_cooperation_companyMapper.class);
        List<p2p_cooperation_company> list = new ArrayList<>();
        p2p_cooperation_company pcc  = new p2p_cooperation_company();
        pdService = new p2p_dictionaryService();
        pcc.setCompanyId(json.getLong("companyId"));
        pcc.setCompanyName(json.getString("companyName"));
        pcc.setCompanyScale(json.getString("companyScaleMin") + "-" + json.getString("companyScaleMax"));
        pcc.setCompanyScaleMax(json.getInt("companyScaleMax"));
        pcc.setCompanyScaleMin(json.getInt("companyScaleMin"));
        pcc.setCompanyProvince(json.getString("companyProvince"));
        pcc.setCompanyCity(json.getString("companyCity"));
        pcc.setCompanyProvinceCode(pdService.getDictCode(json.getString("companyProvince"), session));
        pcc.setCompanyCityCode(pdService.getDictCode(json.getString("companyCity"), session));
        pcc.setUpdateTime(CommonUtils.getCurDate("second"));
        pcc.setCreateTime(CommonUtils.getCurDate("second"));
        list.add(pcc);
        try {
            pccMapper.insert(list);
            Reporter.log("添加p2p_cooperation_company表中的数据成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            Reporter.log("添加p2p_cooperation_company表中的数据失败" + e.getMessage());
        }
    }

    public void delCustCompany(String companyName, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        pccMapper = session.getMapper(p2p_cooperation_companyMapper.class);
        try {
            pccMapper.deleteByCompanyName(companyName);
        }catch (Exception e){
            Reporter.log("删除p2p_cooperation_company表中的数据失败" + e.getMessage());
        }
    }

    public p2p_cooperation_company getCompanyInfo(Long companyId, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        pccMapper = session.getMapper(p2p_cooperation_companyMapper.class);
        p2p_cooperation_company pcc ;
        pcc = pccMapper.selectByCompanyId(companyId);
        if (pcc != null){
            return pcc;
        }else {
            return null;
        }
    }

    public void updateCooperationCompany(Map<String, String> userInfoMap, String data, SqlSession session, String option){
        session = MybatisUtils.getFactory().openSession(true);
        pccMapper = session.getMapper(p2p_cooperation_companyMapper.class);
        p2p_cooperation_company pcc ;
        JSONObject compantInfoJson = new JSONObject(userInfoMap.get("工作认证"));
        pcc = pccMapper.selectByCompanyId(compantInfoJson.getLong("companyId"));
        if (option.equals("companyScaleMax")){
            pcc.setCompanyScaleMax(Integer.valueOf(data));
        }
        pccMapper.updateByCompanyId(pcc);
    }

    @Test
    public void test(){
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        //getCompanyInfo(1L, session);
        JSONObject jsonObject = new JSONObject("{ \"companyId\": \"999\", \"companyName\": \"测试公司\", \"companyScaleMax\": \"100\", \"companyScaleMin\": \"0\", \"companyProvince\": \"江苏省\", \"companyCity\": \"苏州市\", \"entryDate\": \"2017-11-01\", \n" +
                "\"laborRelationsType\": \"正式\", \"workStatus\": \"正常\", \"monthlySalaryMax\": \"10000\", \"monthlySalaryMin\": \"8001\" }");
        addCustCompany(jsonObject, session);
    }
}
