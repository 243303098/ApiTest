/**
 * 
 */
package com.fkapi.service;

import com.fkapi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.fkapi.database.dao.p2p_dictionaryMapper;
import com.fkapi.database.domain.p2p_dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class p2p_dictionaryService {
	p2p_dictionaryMapper dcMapper = null;
	
	//根据省份或者城市返回对应的code
	public String getDictCode(String dictName, SqlSession session){
		dcMapper = session.getMapper(p2p_dictionaryMapper.class);
		String dictCode = null;
		try {
			p2p_dictionary pd = dcMapper.selectByDictName(dictName);
			if(pd == null){
				return null;
			}else{
				//获取的城市编码有可能为6位，如果大于4位则截取前四位
				if(pd.getDictCode().length() > 4){
					dictCode = pd.getDictCode().substring(0,4);
				}else {
					dictCode = pd.getDictCode();
				}
			}
		} catch (Exception e) {
			Reporter.log("根据省份或城市名称： "+ dictName + "获取编码时发生异常，获取失败" + e.getMessage());
		}
		return dictCode;
	}

	public String getOtherDictCode(List<String> list, SqlSession session){
		dcMapper = session.getMapper(p2p_dictionaryMapper.class);
		String dictCode = null;
		try {
			p2p_dictionary pd = dcMapper.selectOtherDictCode(list);
			if(pd == null){
				return null;
			}else{
				//获取的城市编码有可能为6位，如果大于4位则截取前四位
				if(pd.getDictCode().length() > 4){
					dictCode = pd.getDictCode().substring(0,4);
				}else {
					dictCode = pd.getDictCode();
				}
			}
		} catch (Exception e) {
			Reporter.log("根据省份或城市名称获取编码时发生异常，获取失败" + e.getMessage());
		}
		return dictCode;
	}
	
	@Test
	public void test(){
		SqlSession session = MybatisUtils.getFactory().openSession(true);

		List<String> list = new ArrayList<>();
		list.add("3205");
		list.add("3206");
		System.out.print(getOtherDictCode(list, session));
	}
}
