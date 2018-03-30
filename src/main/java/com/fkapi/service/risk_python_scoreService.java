package com.fkapi.service;

import com.fkapi.database.dao.risk_python_scoreMapper;
import com.fkapi.database.domain.risk_python_score;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */
public class risk_python_scoreService {

    risk_python_scoreMapper riskPythonScoreMapper = null;

    risk_python_score rpc ;

    public void addRiskPythonScore(Map<String, String> userinfoMap, Double score, SqlSession vccSession){
        delRiskPythonScore(Long.valueOf(userinfoMap.get("custId")), vccSession);
        delRiskPythonScore(Long.valueOf(userinfoMap.get("oldCustId")), vccSession);
        riskPythonScoreMapper = vccSession.getMapper(risk_python_scoreMapper.class);
        List<risk_python_score> list = new ArrayList<>();
        rpc = new risk_python_score();
        rpc.setCustId(Long.valueOf(userinfoMap.get("custId")));
        rpc.setScore(BigDecimal.valueOf(score));
        rpc.setCreateTime(CommonUtils.getCurDate("second"));
        rpc.setUpdateTime(CommonUtils.getCurDate("second"));
        list.add(rpc);
        try {
            riskPythonScoreMapper.insert(list);
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的risk_python_score表中的数据成功");
        }catch (Exception e){
            Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的risk_python_score表中的数据时发生异常" + e.getMessage());
        }
    }

    public void delRiskPythonScore(Long custId, SqlSession vccSession){
        riskPythonScoreMapper = vccSession.getMapper(risk_python_scoreMapper.class);
        try {
            riskPythonScoreMapper.deleteByCustId(custId);
        }catch (Exception e){
            Reporter.log("删除custId为： " + custId + "的risk_python_score表中的数据时发生异常" + e.getMessage());
        }
    }

    public BigDecimal getPythonScore(Map<String, String> userinfoMap, SqlSession vccSession){
        riskPythonScoreMapper = vccSession.getMapper(risk_python_scoreMapper.class);
        rpc = riskPythonScoreMapper.selectByCustId(Long.valueOf(userinfoMap.get("custId")));
        if (rpc != null){
            return rpc.getScore();
        }else {
            return null ;
        }
    }
}
