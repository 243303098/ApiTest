/**
 * 
 */
package com.fkapi.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.testng.Reporter;
import com.fkapi.database.dao.p2p_nw_blacklistMapper;
import com.fkapi.database.domain.p2p_nw_blacklist;
import com.fkapi.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class p2p_nw_blacklistService {
	p2p_nw_blacklistMapper pnbMapper = null;
	p2p_customerService pcService = null;
	
	/**
	 * 将所给的用户添加到内部黑名单
	 * 只根据custId来关联用户
	 * @param custId
	 */
	public void addNwBlackList(String oldCustId, String custId, SqlSession session){
		delNwBlackList(oldCustId, session);
		pnbMapper = session.getMapper(p2p_nw_blacklistMapper.class);
		List<p2p_nw_blacklist> list = new ArrayList<>();
		pcService = new p2p_customerService();
		p2p_nw_blacklist pnb = new p2p_nw_blacklist();
		
		pnb.setCustId(Long.valueOf(custId));
		pnb.setProjectNo(CommonUtils.getProjectNo());
		list.add(pnb);
		try {
			pnbMapper.insert(list);
			Reporter.log("添加custId为： " + custId + "的p2p_nw_blacklist表的数据成功");
		} catch (Exception e) {
			Reporter.log("添加custId为： " + custId + "的p2p_nw_blacklist表的数据时发生异常，添加失败" + e.getMessage());
		}
	}
	
	public void delNwBlackList(String oldCustId, SqlSession session){
		pnbMapper = session.getMapper(p2p_nw_blacklistMapper.class);
		pcService = new p2p_customerService();
		try {
			pnbMapper.deleteByCustId(Long.valueOf(oldCustId));
			//Reporter.log("删除custId为： " + oldCustId + "的p2p_nw_blacklist表的数据成功");
		} catch (Exception e) {
			Reporter.log("删除custId为： " + oldCustId + "的p2p_nw_blacklist表的数据时发生异常，删除失败" + e.getMessage());
		}
	}
}
