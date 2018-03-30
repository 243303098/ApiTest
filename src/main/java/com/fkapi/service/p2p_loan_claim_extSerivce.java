package com.fkapi.service;

import com.fkapi.database.dao.p2p_loan_claim_extMapper;
import com.fkapi.database.domain.p2p_loan_claim_ext;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public class p2p_loan_claim_extSerivce {

    public void addLoanClaimExt(String projectNo, String strategyId, SqlSession session){
        p2p_loan_claim_extMapper plceMapper = session.getMapper(p2p_loan_claim_extMapper.class);
        List<p2p_loan_claim_ext> list = new ArrayList<>();
        p2p_loan_claim_ext plce = new p2p_loan_claim_ext();
        plce.setBdfPrjType(strategyId);
        plce.setProjectNo(projectNo);
        list.add(plce);
        try{
            plceMapper.insert(list);
            Reporter.log("添加projectNo为：" + projectNo + "的p2p_loan_claim_ext表中的数据成功");
        }catch (Exception e){
            Reporter.log("添加projectNo为：" + projectNo + "的p2p_loan_claim_ext表中的数据时发生异常，添加失败" + e.getMessage());
        }
    }
}
