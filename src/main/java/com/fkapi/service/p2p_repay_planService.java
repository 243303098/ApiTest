/**
 *
 */
package com.fkapi.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fkapi.database.dao.p2p_repay_planMapper;
import com.fkapi.database.domain.p2p_repay_plan;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 */
public class p2p_repay_planService {

    p2p_repay_planMapper prpMapper = null;

    /**
     * 根据custId删除还款计划表
     */
    public void delRepayPlan(String custId, SqlSession session) {
        prpMapper = session.getMapper(p2p_repay_planMapper.class);
        try {
            prpMapper.deleteByCustID(Long.valueOf(custId));
        } catch (Exception e) {
            Reporter.log("删除custId为： " + custId + "的p2p_repay_plan表中的数据时发生异常，删除失败" + e.getMessage());
        }
    }

    /**
     * 添加还款计划表
     *
     * @throws ParseException
     */
    public void addRepayPlan(Map<String, String> userinfoMap, JSONObject json, SqlSession session) {
        delRepayPlan(userinfoMap.get("oldCustId"), session);
        prpMapper = session.getMapper(p2p_repay_planMapper.class);
        p2p_repay_plan prp = new p2p_repay_plan();
        List<p2p_repay_plan> prpList;
        JSONArray repayPlanArray = json.getJSONArray("repayPlan");
        JSONObject repayPlanJson;
        for (int i = 0; i < json.getInt("allTerm"); i++) {
            repayPlanJson = repayPlanArray.getJSONObject(i);
            prpList = new ArrayList<>();
            prp.setProjectNo(userinfoMap.get("projectNo"));
            prp.setRepaymentId(Long.valueOf(userinfoMap.get("custId")));
            prp.setTermNo(i + 1);
            prp.setRepayDate(CommonUtils.subMonth(CommonUtils.getCurDate("second"), -(i + 1)));
            if (json.has("overDueTime")) {
                if (!json.getString("overDueTime").isEmpty()) {
                    prp.setActualPayTime(CommonUtils.subDay(CommonUtils.subMonth(CommonUtils.getCurDate("second"), -(i + 1)), -json.getInt("overDueTime")));
                    prp.setRepayStatus("OVERDUE_REPAID");
                }
            } else {
                prp.setRepayStatus("OVERDUE_NO_REPAY");
            }

            prpList.add(prp);
            try {
                prpMapper.insert(prpList);
                Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的p2p_repay_plan表中的数据成功");
            } catch (Exception e) {
                Reporter.log("添加custId为： " + userinfoMap.get("custId") + "的p2p_repay_plan表中的数据时发生异常，添加失败" + e.getMessage());
            }
        }
    }

    @Test
    public void test() throws ParseException {
        //addRepayPlan();
    }

}
