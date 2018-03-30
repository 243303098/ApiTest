package com.fkapi.service;

import com.fkapi.database.dao.vcc_orderMapper;
import com.fkapi.database.domain.vcc_loan_info;
import com.fkapi.database.domain.vcc_order;
import com.fkapi.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Reporter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/20.
 */
public class vcc_orderService {
    vcc_orderMapper voMapper;
    vcc_loan_infoService vliService ;

    public void addVccOrder(Map<String,String> userInfoMap, JSONObject json, JSONObject vccJson, SqlSession vccSession) {
        voMapper = vccSession.getMapper(vcc_orderMapper.class);
        List<vcc_order> list;
        vcc_order vo = new vcc_order();
        delVccOrder(new JSONObject(userInfoMap.get("certAuth")).getString("cardNo"), vccSession);
        vliService = new vcc_loan_infoService();
        List<vcc_loan_info> successOrderNoList = vliService.getVccLonaInfo(userInfoMap.get("custId"), vccSession);
        //添加成功的订单
        for (int i = 0; i < json.getInt("sucessOrders"); i++) {
            list = new ArrayList<>();
            if (json.getString("isSameStore").toUpperCase().equals("Y")) {
                vo.setMerchantNo("000000001");
            } else {
                vo.setMerchantNo("000000002");
            }
            vo.setOrderNo(successOrderNoList.get(i).getOrderNo());
            vo.setCardNo(new JSONObject(userInfoMap.get("certAuth")).getString("cardNo"));
            if (vccJson.getString("delFlag").toUpperCase().equals("N")){
                vo.setReturnAmount(BigDecimal.valueOf(0));
            }else {
                vo.setReturnAmount(BigDecimal.valueOf(100));
            }
            if (json.getString("isSameTime").toUpperCase().equals("Y")) {
                vo.setSubmitTime(CommonUtils.getCurDate("second"));
                vo.setTransTime(CommonUtils.subMin(CommonUtils.getCurDate("second"), 10));
                vo.setCreateTime(CommonUtils.getCurDate("second"));
                vo.setUpdateTime(CommonUtils.getCurDate("second"));
            } else {
                if (json.getString("timeType").toUpperCase().equals("D")) {
                    vo.setSubmitTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setTransTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                }
                if (json.getString("timeType").toUpperCase().equals("M")) {
                    vo.setSubmitTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setTransTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setCreateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    vo.setUpdateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                }
            }
            vo.setOrderStatus("1");
            list.add(vo);
            try {
                voMapper.insert(list);
                Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单成功");
            } catch (Exception e) {
                Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单时发生异常，添加失败" + e.getMessage());
            }
        }

        if (json.getInt("allOrders") - json.getInt("sucessOrders") > 0){
            for (int i = 0; i < json.getInt("allOrders") - json.getInt("sucessOrders"); i++) {
                list = new ArrayList<>();
                if (json.getString("isSameStore").toUpperCase().equals("Y")) {
                    vo.setMerchantNo("000000001");
                } else {
                    vo.setMerchantNo("000000002");
                }
                vo.setOrderNo(UUID.randomUUID().toString().replace("-",""));
                vo.setCardNo(new JSONObject(userInfoMap.get("certAuth")).getString("cardNo"));
                if (vccJson.getBoolean("delFlag")){
                    vo.setReturnAmount(BigDecimal.valueOf(0));
                }else {
                    vo.setReturnAmount(BigDecimal.valueOf(100));
                }
                if (json.getString("isSameTime").toUpperCase().equals("Y")) {
                    vo.setSubmitTime(CommonUtils.getCurDate("second"));
                    vo.setTransTime(CommonUtils.getCurDate("second"));
                    vo.setCreateTime(CommonUtils.getCurDate("second"));
                    vo.setUpdateTime(CommonUtils.getCurDate("second"));
                } else {
                    if (json.getString("timeType").toUpperCase().equals("D")) {
                        vo.setSubmitTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setTransTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setCreateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setUpdateTime(CommonUtils.subDay(CommonUtils.getCurDate("second"), json.getInt("time")));
                    }
                    if (json.getString("timeType").toUpperCase().equals("M")) {
                        vo.setSubmitTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setTransTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setCreateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                        vo.setUpdateTime(CommonUtils.subMonth(CommonUtils.getCurDate("second"), json.getInt("time")));
                    }
                }
                vo.setOrderStatus("2");
                list.add(vo);
                try {
                    voMapper.insert(list);
                    Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单成功");
                } catch (Exception e) {
                    Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单时发生异常，添加失败" + e.getMessage());
                }
            }
        }

        list = new ArrayList<>();
        vo.setMerchantNo("000000001");
        vo.setCardNo(new JSONObject(userInfoMap.get("certAuth")).getString("cardNo"));
        vo.setSubmitTime(CommonUtils.getCurDate("second"));
        vo.setTransTime(CommonUtils.getCurDate("second"));
        vo.setCreateTime(CommonUtils.getCurDate("second"));
        vo.setUpdateTime(CommonUtils.getCurDate("second"));
        vo.setOrderNo("000000000");
        vo.setOrderStatus("0");
        list.add(vo);
        try {
            voMapper.insert(list);
            Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单成功");
        } catch (Exception e) {
            Reporter.log("添加cardNo为：" + new JSONObject(userInfoMap.get("certAuth")).getString("cardNo") + "的vcc_order表中的订单时发生异常，添加失败" + e.getMessage());
        }
    }

    public void delVccOrder(String cardNo, SqlSession vccSession){
        voMapper = vccSession.getMapper(vcc_orderMapper.class);
        try{
            voMapper.deleteByCarNo(cardNo);
        }catch (Exception e){
            Reporter.log("删除cardNo为： " + cardNo + "的vcc_order表中的数据时发生异常，删除失败" + e.getMessage());
        }
    }
}
