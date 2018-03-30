package com.fkapi.service;

import com.fkapi.database.dao.p2p_loan_claim_snapshootMapper;
import com.fkapi.database.domain.p2p_loan_claim_snapshoot;
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
 * Created by Administrator on 2017/11/3.
 */
public class p2p_loan_claim_snapshootService {
    p2p_loan_claim_snapshootMapper plcsMapper ;

    public void addLoanClaimSnapshoot(Map<String,String> userInfoMap, String projectNo, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        plcsMapper = session.getMapper(p2p_loan_claim_snapshootMapper.class);
        List<p2p_loan_claim_snapshoot> list = new ArrayList<>();
        p2p_loan_claim_snapshoot plcs = new p2p_loan_claim_snapshoot();
        JSONObject phoneJson = new JSONObject(userInfoMap.get("phoneAuth"));
        JSONObject workJson = new JSONObject(userInfoMap.get("工作认证"));
        plcs.setProjectNo(projectNo);
        plcs.setCustId(Long.valueOf(userInfoMap.get("custId")));
        plcs.setCookie(phoneJson.getString("cookie"));
        plcs.setMobileSign(phoneJson.getString("mobileSign"));
        plcs.setPhoneTel(phoneJson.getString("mobile"));
        plcs.setCompanyId(workJson.getLong("companyId"));
        plcs.setProjectType("FD");
        plcs.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), Integer.valueOf(userInfoMap.get("time"))));
        list.add(plcs);
        try {
            plcsMapper.insert(list);
            Reporter.log("添加p2p_loan_claim_snapshoot表中的数据成功");
        }catch (Exception e){
            e.printStackTrace();
            Reporter.log("添加p2p_loan_claim_snapshoot表中的数据失败" + e.getMessage());
        }
    }

    public void delLoanClaimSnapshoot(String cookie, SqlSession session){
        session = MybatisUtils.getFactory().openSession(true);
        plcsMapper = session.getMapper(p2p_loan_claim_snapshootMapper.class);
        try {
            plcsMapper.deleteByCookie(cookie);
            Reporter.log("删除p2p_loan_claim_snapshoot表中的数据成功");
        }catch (Exception e){
            Reporter.log("删除p2p_loan_claim_snapshoot表中的数据失败" + e.getMessage());
        }
    }

    @Test
    public void test(){
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        delLoanClaimSnapshoot("999999999", session);
    }
}
