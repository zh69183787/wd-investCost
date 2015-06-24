/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.ic.attach.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.ic.attach.dao.AttachDao;
import com.wonders.ic.attach.entity.bo.Attach;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Attachʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

public class AttachDaoImpl extends AbstractHibernateDaoImpl<Attach> implements
		AttachDao {
	public Page findAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Attach t ";
		String countHql = "select count(*) from Attach t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				filterPart += "t." + paramName + "=:" + paramName;
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

	@Override
	public void deleteByIdOnLogical(String id) {
		String hql ="update Attach t set t.removed='1' where t.id='"+id+"'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<Attach> findByIds(String[] ids) {
		String idsString ="";
		if(ids==null || ids.length<1) return null;
		for(int i=0; i<ids.length; i++){
			try {
				Long.valueOf(ids[i]);
			} catch (NumberFormatException e) {
				return null;
			}
			idsString += ids[i] +",";
		}
		idsString = idsString.substring(0,idsString.length()-1);
		String hql ="from Attach t where t.removed='0' and t.id in ("+idsString+")";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Attach> findByGroupId(String groupId) {
		String hql = "from Attach t where t.removed='0' and t.groupid='"+groupId+"'";
		return getHibernateTemplate().find(hql);
	}
	
	
}
