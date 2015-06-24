package com.wonders.ic.contract.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.wonders.ic.contract.dao.ContractDao;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.wonders.ic.contract.dao.ShortMsgDao;

public class ShortMsgDaoImpl extends AbstractHibernateDaoImpl<Contract> implements ShortMsgDao {



	@Override
	public Map<String,String> getUserInfoByName(String loginName) {
		if(loginName==null || "".equals(loginName)){
			return null;
		}
		String sql = "select distinct MOBILE1, NAME from V_USERDEP where login_name=?";
		Session session = getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		query.setString(0, loginName);
		Map<String, String> map = new HashMap<String, String>();
		if(query.list()!=null && query.list().size()>0){
			Object[] obj = (Object[]) query.list().get(0);
			map.put("phone", (String)obj[0]);
			map.put("userName", (String)obj[1]);
		}
		return map;
	}
	
	@Override
	public void sendMessage(String mobile, String content){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String sql = "insert into T_SHORT_MSG (ID,STATUS,MOBILE,CONTENT) VALUES(?,?,?,?)";
		Session session = getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		query.setString(0, uuid);
		query.setString(1, "0");
		query.setString(2, mobile);
		query.setString(3, content);
		try{
			query.executeUpdate();
		}catch (Exception e) {
			
		}finally{
			session.close();
		}
	}

}
