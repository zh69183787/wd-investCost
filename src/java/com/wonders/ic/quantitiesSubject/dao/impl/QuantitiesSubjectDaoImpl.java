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

package com.wonders.ic.quantitiesSubject.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.ic.quantitiesSubject.dao.QuantitiesSubjectDao;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * QuantitiesSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesSubjectDaoImpl extends AbstractHibernateDaoImpl<QuantitiesSubject> implements QuantitiesSubjectDao {
	public Page findQuantitiesSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from QuantitiesSubject t ";
		String countHql = "select count(*) from QuantitiesSubject t ";
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
		filterPart += " order by t.wbsId asc";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public List<QuantitiesSubject> findByContractId(String contractId) {
		String hql ="from QuantitiesSubject t where t.removed='0' and t.contractId='"+contractId+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteAllByIdsOnLogical(String[] ids) {
		if(ids==null || ids.length<1) return ;
		String queryFilter = "";
		for(int i=0; i<ids.length; i++){
			queryFilter += "'"+ids[i] +"',";
		}
		queryFilter = queryFilter.substring(0,queryFilter.length()-1);
		
		String hql ="update QuantitiesSubject t set t.removed='1' where t.id in("+queryFilter+")";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		
	}

	@Override
	public BigDecimal findSumOfAllPriceByContractId(String contractId) {
		String sql ="select sum(TOTAL_PRICE) from C_QUANTITIES_SUBJECT where CONTRACT_ID ='"+contractId+"' and REMOVED='0'";
		List<BigDecimal> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	@Override
	public List<Object[]> findSumOfAmoutAndDecideByIdList(List<String> idList) {
		if(idList==null || idList.size()<1) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<idList.size(); i++){
			sb.append("'").append(idList.get(i)).append("',");
		}
		String hql ="select sum(t.amount),sum(t.totalPrice) from QuantitiesSubject t where t.id in ("+sb.substring(0,sb.length()-1)+") and t.removed ='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public QuantitiesSubject findByWbsId(String wbsId) {
		String hql ="from QuantitiesSubject a where a.removed='0' and a.id = (select b.nodeId from Wbs b where b.removed='0' and b.id='"+wbsId+"') order by a.createDate DESC";
		List<QuantitiesSubject> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(list==null || list.size()<1) return null;
		return list.get(0);
		
	}

	@Override
	public void updateList(List<QuantitiesSubject> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public List<String> findSumOfTotalPriceByContractIdAndEstimateSubjectId(List<String> contractIdList, String estimateSubjectId) {
		StringBuilder contractIdFilter = null,orderIdStr = null;
		if(contractIdList==null || contractIdList.size()<1)
			return null;
		contractIdFilter = new StringBuilder();
		orderIdStr = new StringBuilder();
		for(int i=0; i<contractIdList.size(); i++){
			contractIdFilter.append("'").append(contractIdList.get(i)).append("',");
			orderIdStr.append(contractIdList.get(i)).append(",");
		}
		
		String hql ="select sum(t.totalPrice) from QuantitiesSubject t where t.removed='0' and t.contractId in ("+contractIdFilter.deleteCharAt(contractIdFilter.length()-1)+") " +
				"and t.investEstimateId='"+estimateSubjectId+"' group by t.contractId order by instr('"+orderIdStr.deleteCharAt(orderIdStr.length()-1)+"',t.contractId) ";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void saveAll(List<QuantitiesSubject> qsList) {
		getHibernateTemplate().saveOrUpdateAll(qsList);
	}

	@Override
	public QuantitiesSubject findById(String id) {
		String hql ="from QuantitiesSubject t where t.id='"+id+"' and t.removed='0'";
		List<QuantitiesSubject> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	@Override
	public void updateInvestEstimateIdAndNameNull(String investEstimateId) {
		String hql ="update QuantitiesSubject t set t.investEstimateId='',t.investEstimateName='' where t.investEstimateId='"+investEstimateId+"' and t.removed='0'";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public void updateInvestEstimateName(String investEstimateId,String investEstimateName) {
		String hql ="update QuantitiesSubject t set t.investEstimateName='"+investEstimateName+"' where t.investEstimateId='"+investEstimateId+"' and t.removed='0'";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public String findSumOfTotalPriceByInvestEstimateId(String investEstimateId) {
		String hql ="select sum(t.totalPrice) from QuantitiesSubject t where t.investEstimateId='"+investEstimateId+"' and t.removed='0'";
		List<String> result = getHibernateTemplate().find(hql);
		if(result!=null && result.size()>0) return result.get(0);
		return null;
	}

	@Override
	public void updateContractNameByContractId(String contractId,String contractNo) {
		String hql="update QuantitiesSubject t set t.contractNo='"+contractNo+"' where t.removed='0' and t.contractId='"+contractId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void deleteAllByContractIdOnLogical(String contractId) {
		String hql="update QuantitiesSubject t set t.removed='1' where t.removed='0' and t.contractId='"+contractId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}



	
	
	
	
	
}
