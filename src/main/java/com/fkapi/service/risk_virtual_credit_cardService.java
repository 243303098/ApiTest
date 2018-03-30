package com.fkapi.service;

import com.fkapi.database.dao.risk_virtual_credit_cardMapper;
import com.fkapi.database.domain.risk_virtual_credit_card;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public class risk_virtual_credit_cardService {
    risk_virtual_credit_cardMapper rvccMapper ;

    public void addRiskVirtualCreditCard(Map<String, String> userinfoMap,  SqlSession riskSession){
        rvccMapper = riskSession.getMapper(risk_virtual_credit_cardMapper.class);
        delRiskVirtualCreditCard(userinfoMap.get("oldCustId"), riskSession);
        delRiskVirtualCreditCard(userinfoMap.get("custId"), riskSession);
        List<risk_virtual_credit_card> list = new ArrayList<>();
        risk_virtual_credit_card rvcc = new risk_virtual_credit_card();
        rvcc.setCustId(Long.valueOf(userinfoMap.get("custId")));
        rvcc.setAvailableLimit(BigDecimal.valueOf(200));
        rvcc.setMaxLimit(BigDecimal.valueOf(200));
        rvcc.setGmtCreate(CommonUtils.getCurDate("second"));
        rvcc.setGmtModified(CommonUtils.getCurDate("second"));
        list.add(rvcc);
        try{
            rvccMapper.insert(list);
            Reporter.log("新增custId为： " + userinfoMap.get("custId") + "的risk_virtual_credit_card表中的数据成功");
        }catch (Exception e){
            Reporter.log("新增custId为： " + userinfoMap.get("custId") + "的risk_virtual_credit_card表中的数据时发生异常，新增失败" + e.getMessage());
        }
    }

    public void delRiskVirtualCreditCard(String custId, SqlSession riskSession){
        rvccMapper = riskSession.getMapper(risk_virtual_credit_cardMapper.class);
        try{
            rvccMapper.deleteByCustId(Long.valueOf(custId));
        }catch (Exception e){
            Reporter.log("删除custId为： " + custId + "的risk_virtual_credit_card表中的数据时发生异常，删除失败" + e.getMessage());
        }
    }
}
