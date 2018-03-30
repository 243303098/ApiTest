package com.fkapi.utils;

import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/28.
 */
public class RedisUtil {

    private  Jedis jedis ;

    public void clearRedis() {
        GetExcelConfig getExcelConfig = new GetExcelConfig();
        Map<String, String> map = getExcelConfig.getConfigData(CommonUtils.excelPath, CommonUtils.configSheetName, "common");
        //连接redis服务器，192.168.0.100:6379
        //jedis = new Jedis(map.get("ip"), 6379);
        jedis = new Jedis("192.168.101.203", 6379);
        jedis.auth("testredis");
        jedis.flushDB();
    }

    @Test
    public void test(){
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.clearRedis();
    }
}
