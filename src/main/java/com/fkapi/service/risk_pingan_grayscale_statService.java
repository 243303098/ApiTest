package com.fkapi.service;

import com.fkapi.database.dao.risk_pingan_grayscale_statMapper;
import com.fkapi.database.domain.risk_pingan_grayscale_stat;
import com.fkapi.utils.CommonUtils;
import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/25.
 */

public class risk_pingan_grayscale_statService {

    risk_pingan_grayscale_statMapper rpgsMapper = null ;

    public void addPinganGrayscaleStat(Map<String, String> userinfoMap, JSONObject json, SqlSession session){
        delPinganGrayscaleStat(Long.valueOf(userinfoMap.get("oldCustId")), session);
        rpgsMapper = session.getMapper(risk_pingan_grayscale_statMapper.class);
        List<risk_pingan_grayscale_stat> list = new ArrayList();
        risk_pingan_grayscale_stat rpgs = new risk_pingan_grayscale_stat();
        rpgs.setId(System.currentTimeMillis());
        rpgs.setCustId(Long.valueOf(userinfoMap.get("custId")));
        rpgs.setL1mwdcnTtimesout(json.getInt("l1mwdcnTtimesout"));
        rpgs.setL3mwdcnTnumsconF(json.getInt("l3mwdcnTnumsconF"));
        rpgs.setCreateTime(CommonUtils.getCurDate("second"));
        list.add(rpgs);
        try{
            rpgsMapper.insert(list);
            Reporter.log("添加custId为：" + userinfoMap.get("custId") + "参数为：" + json + "的risk_pingan_grayscale_stat表中的数据成功");
        }catch (Exception e){
            Reporter.log("添加custId为：" + userinfoMap.get("custId") + "参数为：" + json + "的risk_pingan_grayscale_stat表中的数据时发生异常" + e.getMessage());
        }
    }

    public void delPinganGrayscaleStat(Long oldCustId, SqlSession session){
        rpgsMapper = session.getMapper(risk_pingan_grayscale_statMapper.class);
        rpgsMapper.deleteByCustId(oldCustId);
    }

    @Test
    public void test(){
        Map<String, String> userinfoMap = new HashMap<>();
        userinfoMap.put("custId","330001335");
        SqlSession session = MybatisUtils.getFactory().openSession(true);
        JSONObject json = new JSONObject("{\"l1mwdcnTtimesout\":\"4\",\"l3mwdcnTnumsconF\":\"0\"}");
        addPinganGrayscaleStat(userinfoMap, json, session);
    }
}
