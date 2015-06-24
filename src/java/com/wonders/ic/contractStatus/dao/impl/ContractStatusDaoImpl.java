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

package com.wonders.ic.contractStatus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.wonders.ic.contractStatus.dao.ContractStatusDao;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * ContractStatusʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractStatusDaoImpl extends
		AbstractHibernateDaoImpl<ContractStatus> implements ContractStatusDao {
	public Page findContractStatusByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from ContractStatus t ";
		String countHql = "select count(*) from ContractStatus t ";
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
	
	public List<ContractStatus> findStatusList(String id,String type){
		String hql = "from ContractStatus t where t.removed='0' and t.type = '"+type+"' and t.contractId = '"+id+"' order by to_date(t.operateDate,'yyyy-mm-dd') desc,t.updateDate desc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public double findSumOfContractStatusByContractIdAndType(String contractId,String type) {
		String hql = "select sum(t.money) from ContractStatus t where t.contractId='"+contractId+"' and t.type='"+type+"'";
		List<String> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			if(list.get(0)!=null && !"".equals(list.get(0)) && !"0".equals(list.get(0)) ){
				return Double.valueOf(list.get(0));
			}else{
				return 0;
			}
			
		}
		return 0;
	}

	@Override
	public void saveAll(List<ContractStatus> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public List<ContractStatus> findByYearAndContractId(String year,String contractId,String type) {
		String hql ="from ContractStatus t where t.removed='0' and t.type='"+type+"' and t.operateDate like '"+year+"%' and t.contractId='"+contractId+"'";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Object[]> findByTypeAndDateRange(String type,String startDate,String endDate) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select p.DISPATCH_NO,p.PROJECT_NAME,c.CONTRACT_NO,c.CONTRACT_NAME,cs.LINE From C_Contract_Status Cs ");
		sql.append("Left Outer Join C_Contract C On C.Id = Cs.Contract_Id and C.Removed = 0 ");
		sql.append("Left Outer Join C_Project P On P.Id  = C.Project_Id And P.Removed = 0 ");
		sql.append("Where Cs.Removed = 0 ");
		if(StringUtils.isNotEmpty(startDate)) {
			sql.append("And Cs.type = :type ");
		}		
		if(StringUtils.isNotEmpty(startDate)) {
			sql.append("And Cs.operate_date >= :startDate ");
		}
		if(StringUtils.isNotEmpty(endDate)) {
			sql.append("And Cs.operate_date <= :endDate ");
		}		
		
		Query query = getSession().createSQLQuery(sql.toString());
		query.setParameter("type", type);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ContractStatus> findByYear(String year, boolean onlyThisYear, String professionalType, List<String> typeIds, Long season) {
		String firstDay = year + "-01-01";
		String lastDay = year + "-12-31";
		if(season != null){
			switch (season.intValue()) {
			case 1:
				lastDay = year + "03-31";
				break;
			case 2:
				firstDay = year + "-04-01";
				lastDay = year + "-06-30";
				break;
			case 3:
				firstDay = year + "-07-01";
				lastDay = year + "-09-30";
				break;
			case 4:
				firstDay = year + "-10-01";
				break;
			default:
				break;
			}
		}
		StringBuilder sql = new StringBuilder();
		sql.append("Select cs.* From C_Contract_Status Cs ");
		sql.append("Left Outer Join C_Contract C On C.Id = Cs.Contract_Id and C.Removed = 0 ");
		sql.append("Left Outer Join C_Project P On P.Id  = C.Project_Id And P.Removed = 0 ");
		sql.append("Where P.Professional_Type Is Not Null ");
		if(typeIds != null && typeIds.size() > 0){
			sql.append("and P.Project_Type in(:typeIds) ");
		}
		if(StringUtils.isNotEmpty(professionalType)) {
			sql.append("And P.Professional_Type = :professionalType ");
		}
		if(onlyThisYear){
			sql.append("And To_Date(Cs.Operate_Date,'yyyy-mm-dd') >= to_date(:firstDay,'yyyy-mm-dd') ");
		}
		sql.append("And To_Date(Cs.Operate_Date,'yyyy-mm-dd') <= to_date(:lastDay,'yyyy-mm-dd') ");
		
		Query query = getSession().createSQLQuery(sql.toString())
			.addEntity(ContractStatus.class);
		if(StringUtils.isNotEmpty(professionalType)){
			query.setParameter("professionalType", professionalType);
		}
		if(onlyThisYear){
			query.setParameter("firstDay", firstDay);
		}
		if(typeIds != null && typeIds.size() > 0){
			query.setParameterList("typeIds", typeIds);
		}
		query.setParameter("lastDay", lastDay);
		return query.list();
	}
	
	
}
