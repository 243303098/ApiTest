package com.fkapi.service;

import com.fkapi.database.dao.risk_education_whitelistMapper;
import com.fkapi.database.domain.risk_education_whitelist;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.RiskMybatisUtils;
import com.fkapi.utils.VCCMybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/26.
 */
public class risk_education_whiteListService {

    risk_education_whitelistMapper rewMapper = null;

    public void addEducationWhiteList(Map<String, String> userinfoMap, SqlSession riskSession){
        riskSession = RiskMybatisUtils.getFactory().openSession(true);
        rewMapper = riskSession.getMapper(risk_education_whitelistMapper.class);
        risk_education_whitelist rew = new risk_education_whitelist();
        rew.setCustId(Long.valueOf(userinfoMap.get("custId")));
        rew.setCreateTime(CommonUtils.getCurDate("second"));
        try {
            rewMapper.insert(rew);
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的学历白名单用户成功");
        }catch (Exception e){
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的学历白名单用户时发生异常，添加失败" + e.getMessage());
        }
    }

    public void delEducationWhiteList(Map<String, String> userinfoMap, SqlSession riskSession){
        riskSession = VCCMybatisUtils.getFactory().openSession(true);
        rewMapper = riskSession.getMapper(risk_education_whitelistMapper.class);
        try {
            rewMapper.deleteByCustId(Long.valueOf(userinfoMap.get("custId")));
        }catch (Exception e){
            Reporter.log("删除custId为： " + userinfoMap.get("custId") + "的学历白名单用户时发生异常，删除失败" + e.getMessage());
        }
    }

    @Test
    public void test(){
        SqlSession riskSession = RiskMybatisUtils.getFactory().openSession(true);
        Map<String, String> userinfoMap = new HashMap<>();
        userinfoMap.put("custId", "330002477");
        addEducationWhiteList(userinfoMap, riskSession);
    }
}
