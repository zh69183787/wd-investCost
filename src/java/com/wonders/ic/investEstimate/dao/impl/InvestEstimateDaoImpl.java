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

package com.wonders.ic.investEstimate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimate.dao.InvestEstimateDao;
import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * InvestEstimateʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateDaoImpl extends AbstractHibernateDaoImpl<InvestEstimate> implements InvestEstimateDao {
	public Page findInvestEstimateByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from InvestEstimate t ";
		String countHql = "select count(*) from InvestEstimate t ";
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
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public List<InvestEstimate> findAllByProjectId(String projectId) {
		String hql = "select t from InvestEstimate t where t.projectId='"+projectId+"' and t.removed='0' order by t.operateDate";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public InvestEstimate findByProjectId(String projectId) {
		String hql = "select t from InvestEstimate t where t.projectId='"+projectId+"' and t.removed='0' order by t.operateDate";
		List<InvestEstimate> ieList = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(ieList==null || ieList.size()<1) return null;
		return ieList.get(0);
	}

	@Override
	public void updateFirstLevelAllDecide(String object,String projectId,List<String> subjectIdList) {
		StringBuilder sb = new StringBuilder(); 
		if(subjectIdList==null && subjectIdList.size()<1) return ;
		for(int i=0; i<subjectIdList.size(); i++){
			sb.append("'").append(subjectIdList.get(i)).append("',");
		}
		String hql ="update InvestEstimate t set t.estimateDecideAmount = "+
				" (select to_char(sum(b.estimateDecideSum),'999999999999.999999') from InvestEstimateSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+")),"+
				" t.estimateDecideFirst = "+
				" (select to_char(sum(b.estimateDecideFirst),'999999999999.999999') from InvestEstimateSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+")),"+
				" t.estimateDecideSecond = "+
				" (select to_char(sum(b.estimateDecideSecond),'999999999999.999999') from InvestEstimateSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+")),"+
				" t.estimateDecideThird = "+
				" (select to_char(sum(b.estimateDecideThird),'999999999999.999999') from InvestEstimateSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+")),"+
				" t.estimateDecideOther = "+
				" (select to_char(sum(b.estimateDecideOther),'999999999999.999999') from InvestEstimateSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+")) "+
				" where t.removed='0' and t.projectId='"+projectId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		
	}


	
}
