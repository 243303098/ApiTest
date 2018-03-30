package com.fkapi.service;

import com.fkapi.database.dao.risk_audit_item_logMapper;
import com.fkapi.database.domain.risk_audit_item_log;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */
public class risk_audit_item_logService {

    risk_audit_item_logMapper railMapper ;

    public String getAuditResult(Long custId, String item, SqlSession session){
        railMapper = session.getMapper(risk_audit_item_logMapper.class);
        risk_audit_item_log rail = new risk_audit_item_log();
        List<risk_audit_item_log> list = new ArrayList();
        rail.setCustId(custId);
        rail.setItem(item);
        try{
            list = railMapper.selectByCustIdAndItem(rail);
            if(list.isEmpty()){
                Reporter.log("根据custId: " + custId + "remark: " + item + "未获取到风控审批记录");
                return null;
            }else {
                Reporter.log("根据custId: " + custId + "remark: " + item + "获取的风控审批记录为 " + list.get(0).getAuditResult());
                return list.get(0).getAuditResult();
            }
        }catch (Exception e){
            Reporter.log("根据custId: " + custId + "remark: " + item + "获取风控审批记录时发生异常，获取失败" + e.getMessage());
            return null;
        }
    }

    public void delAuditResult(Long custId, SqlSession session){
        railMapper = session.getMapper(risk_audit_item_logMapper.class);
        try{
            railMapper.deleteByCustId(custId);
        }catch (Exception e){
            Reporter.log("根据custId： " + custId + "删除风控审批记录时发生异常，删除失败" + e.getMessage());
        }
    }
}
