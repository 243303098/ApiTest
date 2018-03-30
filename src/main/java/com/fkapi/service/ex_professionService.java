package com.fkapi.service;

import com.fkapi.database.dao.ex_professionMapper;
import com.fkapi.database.domain.ex_profession;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/7/13.
 */
public class ex_professionService {
    ex_professionMapper epMapper ;
    ex_profession ep ;
    public Long getCollogeCode(JSONObject json, SqlSession session){
        epMapper = session.getMapper(ex_professionMapper.class);
        ep = new ex_profession();
        if(json.getString("collegeName").length() > 0 && json.getString("professionName").length() > 0){
            ep.setCollegeName(json.getString("collegeName"));
            ep.setProfessionName(json.getString("professionName"));
            try{
                ep = epMapper.selectByCollegeNameAndProfessionName(ep);
                return ep.getId();
            }catch (Exception e){
                Reporter.log("根据collegeName和professionName：" + json.getString("professionName") + json.getString("") + "获取学院id时发生异常，获取失败" + e.getMessage());
                return null;
            }
        }else {
            return null;
        }
    }

    @Test
    public void test(){

    }
}
