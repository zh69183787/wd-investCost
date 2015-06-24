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

package com.wonders.ic.investEstimateSubject.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.ic.investEstimateSubject.dao.InvestEstimateSubjectDao;
import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * InvestEstimateSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateSubjectDaoImpl extends AbstractHibernateDaoImpl<InvestEstimateSubject> implements InvestEstimateSubjectDao {
	public Page findInvestEstimateSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from InvestEstimateSubject t ";
		String countHql = "select count(*) from InvestEstimateSubject t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("subjectName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else {
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.wbsId ASC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public boolean findHierarchy(String type, String projectId, String wbsId) {
		String queryFilter ="" ;
		if("2".equals(type)){	//下级目录
			queryFilter  = " and t.wbsResolveId like '"+(wbsId+".")+"%'";
		}
		
		if("4".equals(type)){	//同级向下
			queryFilter  = " and t.wbsResolveId like '"+wbsId.substring(0,wbsId.length()-1)+"%' and t.wbsResolveId >= '"+wbsId+"'";
		}
		
		String hql ="select t from InvestEstimateSubject t where t.projectId='"+projectId+"'";
		hql += queryFilter;
		List<InvestEstimateSubject> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean findLowerHierarchy(String projectId, String wbsId) {
		String hql ="select t from InvestEstimateSubject t where t.projectId='"+projectId+"' and t.wbsResolveId like '"+(wbsId+".")+"%'";
		List<InvestEstimateSubject> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<InvestEstimateSubject> findAllHierarchyByLevel(String projectId, String level) {
		String hql ="select t from InvestEstimateSubject t where t.projectId='"+projectId+"' and t.level = '"+level+"' order by t.order ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteByIdLogical(String[] ids) {
		String idsString = "";
		if(ids!=null && ids.length>0){
			for(int i=0; i<ids.length; i++){
				idsString += "'"+ids[i]+"',";
			}
			idsString = idsString.substring(0,idsString.length()-1);
		}
		String hql = "update InvestEstimateSubject t set t.removed='1' where t.id in ("+idsString+")";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<InvestEstimateSubject> findByIds(String[] ids) {
		String idsString = "";
		if(ids!=null && ids.length>0){
			for(int i=0; i<ids.length; i++){
				idsString += "'"+ids[i]+"',";
			}
			idsString = idsString.substring(0,idsString.length()-1);
		}
		String hql = "select t from InvestEstimateSubject t where t.removed='0' and t.id in ("+idsString+")";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Object[] findEstimateDecide(String projectId,String[] idArray) {
		if(idArray==null || idArray.length<1)
			return null;
		String idFilter ="";
		for(int i=0; i<idArray.length; i++){
			idFilter += "'"+idArray[i]+"',";
		}
		idFilter = idFilter.substring(0,idFilter.length()-1);
		
		String sql = "select sum(ESTIMATE_DECIDE_FIRST),sum(ESTIMATE_DECIDE_SECOND),sum(ESTIMATE_DECIDE_THIRD),sum(ESTIMATE_DECIDE_OTHER), sum(PROJECT_AMOUNT) " +
				"from C_INVEST_ESTIMATE_SUBJECT where REMOVED='0' and PROJECT_ID='"+projectId+"' and ID in ("+idFilter+")";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		List<Object[]> list = query.list();
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void deleteByIdLogical(String id) {
		this.deleteById(id);
	}

	@Override
	public void deleteAllOnLogically(String[] ids) {
		if(ids==null || ids.length<1) return;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<ids.length; i++){
			sb.append("'"+ids[i]+"',");
		}
		String idsFilter = sb.substring(0, sb.length()-1);
		String hql ="update InvestEstimateSubject t set t.removed='1' where t.id in("+idsFilter+") and t.removed='0'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<InvestEstimateSubject> findAllByIdList(List<String> ids) {
		if(ids==null || ids.size()<1) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<ids.size(); i++){
			sb.append("'").append(ids.get(i)).append("'");
		}
		String hql ="from InvestEstimateSubject t where t.id in ("+sb.substring(0,sb.length()-1)+") and t.removed ='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<InvestEstimateSubject> findAllByProjectIdAndSubjectNameWithFuzzySearch(String projectId,String subjectName) {
		String hql ="from InvestEstimateSubject t where t.projectId='"+projectId+"' and t.subjectName like '%"+subjectName+"%' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findSumOfAmoutAndDecideByIdList(List<String> idList) {
		if(idList==null || idList.size()<1) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<idList.size(); i++){
			sb.append("'").append(idList.get(i)).append("',");
		}
		String hql ="select sum(t.projectAmount),sum(t.estimateDecideFirst),sum(t.estimateDecideSecond),sum(estimateDecideThird),sum(estimateDecideOther) from InvestEstimateSubject t where t.id in ("+sb.substring(0,sb.length()-1)+") and t.removed ='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public InvestEstimateSubject findByWbsId(String wbsId) {
		String hql ="from InvestEstimateSubject a where a.removed='0' and a.id = (select b.nodeId from Wbs b where b.removed='0' and b.id='"+wbsId+"') order by a.createDate DESC";
		List<InvestEstimateSubject> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	@Override
	public void updateList(List<InvestEstimateSubject> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	
	
	
	
	
	
}
