package com.fkapi.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class VCCMybatisUtils {

    private final static SqlSessionFactory vccfactory;

    static{
        String CONFIG_PATH = "mybatis_config_vcc.xml";
        GetExcelConfig getExcelConfig = new GetExcelConfig();
        Map<String, String> map ;
        Reader reader = null;

        map = getExcelConfig.getConfigData(CommonUtils.excelPath, CommonUtils.configSheetName, "vcc");
        Properties properties = new Properties();
        properties.setProperty("jdbc.url", "jdbc:mysql://" + map.get("ip") + "/" + map.get("db") + "?useUnicode=true&characterEncoding=utf-8");
        properties.setProperty("jdbc.username", map.get("username"));
        properties.setProperty("jdbc.password", map.get("password"));

        try {
            reader = Resources.getResourceAsReader(CONFIG_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        vccfactory = new SqlSessionFactoryBuilder().build(reader,properties);
    }

    public static SqlSessionFactory getFactory(){

        return vccfactory;
    }
}