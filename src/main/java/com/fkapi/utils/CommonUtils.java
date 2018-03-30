/**
 * 
 */
package com.fkapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fkapi.service.p2p_cert_authService;
/**
 * @author Administrator
 * 
 */
public class CommonUtils {

	public static String excelPath = System.getProperty("user.dir") + "\\testcase.xlsx";

	public static String configSheetName = "config";

	public static String applyDataSheetName = "虚拟信用卡准入数据生成";

	public static String consumeSheetname = "虚拟信用卡消费数据生成";

	public static String applyCaseSheetName = "虚拟信用卡准入case";

	public static String consumeCasesheetName = "虚拟信用卡消费case";

	public static String dataSheetName = "元数据表";

	public static String userInfoSheetName = "userInfo";

	public static String ndkExcelPath = System.getProperty("user.dir") + "\\NDK.xlsx";

	public static String ndkDataSheetName = "NDK数据生成";

	public static String ndkCaseSheetName = "NDKCase";

	public static String chubaoExcelPath = System.getProperty("user.dir") + "\\CHUBAO.xlsx";

	public static String chubaoDataSheetName = "CHUBAO数据生成";

	public static String chubaoCaseSheetName = "CHUBAOCase";

	public static String nxbExcelPath = System.getProperty("user.dir") + "\\NXB.xlsx";

	public static String nxbDataSheetName = "NXB数据生成";

	public static String nxbCaseSheetName = "NXBCase";

	public static String fdylExcelPath = System.getProperty("user.dir") + "\\FDYL.xlsx";

	public static String fdylDataSheetName = "复大医疗数据生成";

	public static String fdylCaseSheetName = "复大医疗Case";

	/**
	 * 获取properties中的数据信息
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(System.getProperty("user.dir")
					+ "\\config.properties");
			// 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中
			// in = propertiesTools.class.getResourceAsStream(fileNamePath);
			props.load(in);
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != in)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Reporter.log("读取文件出现异常" + e.getMessage());
				}
		}
	}
	
	/**
	 * 获取当前时间
	 * @param option：精确到秒传second,精确到天传day,精确到月传month
	 * @throws ParseException 
	 */
	
	public static Date getCurDate(String option) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = null;
		if (option.equals("second")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if(option.equals("day")){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else if(option.equals("month")){
			sdf = new SimpleDateFormat("yyyy-MM");
		}else if (option.equals("year")){
			sdf = new SimpleDateFormat("yyyy");
		}
		System.out.println(StringToDate(sdf.format(calendar.getTime()), option));
		return StringToDate(sdf.format(calendar.getTime()), option);
	}

	/**
	 * String转为日期类型——年月日时分秒
	 * 
	 * @param date: 传入的具体时间
	 * @param option：精确到秒传second,精确到天传day,精确到月传month
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String date, String option){
		SimpleDateFormat sdf = null;
		if (option.equals("second")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if(option.equals("day")){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else if(option.equals("month")){
			sdf = new SimpleDateFormat("yyyy-MM");
		}else if(option.equals("year")){
			sdf = new SimpleDateFormat("yyyy");
		}
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			//Reporter.log("日期转换失败，输入的日期格式为： " + date);
			return null;
		}
	}
	
	public static Short getAge(short time){
		Calendar ca = Calendar.getInstance();
		short curYear = (short) ca.get(Calendar.YEAR);
		return (short) (curYear - time);
	}

	/**
	 * 日期月份计算
	 * 
	 * @param date
	 * @param month：要加减的月份(会得到上个月当天的前一天)
	 * @return
	 * @throws ParseException
	 */
	public static Date subMonth(Date date, int month) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, -month);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 计算年份差
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date subYear(Date date, int year) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(sdf.format(getNextDay(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Reporter.log("出现异常" + e.getMessage());
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, -year);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 计算天数
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date subDay(Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Reporter.log("出现异常" + e.getMessage());
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) - day);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 分钟加减
	 * @param date
	 * @param min
	 * @return
	 */
	public static Date subMin(Date date, int min) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Reporter.log("出现异常" + e.getMessage());
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.set(Calendar.MINUTE, rightNow.get(Calendar.MINUTE) - min);
		Date dt1 = rightNow.getTime();
		return dt1;
	}
	
	/**
	 * 获取当前时间的前一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    }

	/**
	 * 获取当前月份的第一天
	 */
	public static Date getFirstDay(){
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		return c.getTime();
	}

	/**
	 * 获取指定日期的月份的最后一天
	 * @return
	 */
	public static Date getLastDay(Date date){
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		return c.getTime();
	}
	/**
	 * 根据身份证及年龄生成新的身份证号
	 * @param custId
	 * @param age
	 * @return
	 */
	public static String creteIdCardByAge(String custId, String age,SqlSession session){
		p2p_cert_authService pcaService = new p2p_cert_authService();
		String idNo ;
		String idCard ;
		idCard = pcaService.getIdcard(custId, session);
		String areaCode = idCard.substring(0, 6);
		String otherCode = idCard.substring(10);
		int year = Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(age);
		idNo = areaCode+String.valueOf(year)+otherCode;
		return idNo;
	}
	/**
	 * 生成projectNo,根据时间戳随机生成
	 * @return
	 */
	public static String getProjectNo(){
		String projectNo = "JKM";
		return projectNo + UUID.randomUUID().toString().replace("-","");
	}

	/**
	 * 获取要添加的测试数据信息
	 * @param excelPath
	 * @param sheetName
	 * @param dataNo
	 * @return
	 */
	public Map<String, String> getTestData(String excelPath, String sheetName, Integer dataNo) {
		Map<String, String> map;
		int allColNum = 0;
		try {
			allColNum = ExcelUtils.getAllColNum(excelPath, sheetName, 0);
		} catch (IOException e) {
			Reporter.log("获取Excel的信息失败");
		}
		map = new HashMap<>();
		for (int j = 0; j < allColNum; j++) {
			map.put(ExcelUtils.getCellDate(excelPath, sheetName, 0, j),
					ExcelUtils.getCellDate(excelPath, sheetName, dataNo, j));
		}
		return map;
	}
	
	@Test
	public void f() throws ParseException{
		System.out.println(getLastDay(getCurDate("second")));
	}

}
