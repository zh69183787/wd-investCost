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

package com.wonders.ic.fund.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.wonders.ic.fund.dao.FundPlanDao;
import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class FundPlanDaoImpl extends
		AbstractHibernateDaoImpl<FundPlan> implements FundPlanDao {
	
	public Page findFundPlanByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from FundPlan t ";
		String countHql = "select count(*) from FundPlan t ";
		String filterPart = " where removed = '0'";
		
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("dispatchNo") || paramName.equals("line") || paramName.equals("year")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
			}
		}
		String orderPart = " order by t.updateDate desc";
		return findByHQLWithPage(hql + filterPart + orderPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	@Override
	public void saveFundPlan(FundPlan fundPlan) {
		try {
			getHibernateTemplate().saveOrUpdate(fundPlan);
		} catch (DataAccessException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteByIdOnLogical(String id) {
		String hql ="update FundPlan t set t.removed='1' where t.id='"+id+"'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	@Override
	public FundPlan findFundPlanById(String id) {
		return (FundPlan) getHibernateTemplate().get(FundPlan.class, id);
	}
	
	@Override
	public boolean exists(FundPlan fundPlan) {
		String hql ="select t.id from FundPlan t where t.projectId='"+fundPlan.getProjectId()+"' and t.line = '"+fundPlan.getLine()+"' and t.year = '"+fundPlan.getYear()+"'";
		if(!StringUtils.isEmpty(fundPlan.getId())){
			hql += " and id != '"+fundPlan.getId()+"'";
		}
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list().size() > 0 ? true : false;
	}
	
	@Override
	public void saveAll(List<FundPlan> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}
}
