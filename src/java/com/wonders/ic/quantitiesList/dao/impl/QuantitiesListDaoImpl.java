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

package com.wonders.ic.quantitiesList.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesList.dao.QuantitiesListDao;
import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * QuantitiesListʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesListDaoImpl extends AbstractHibernateDaoImpl<QuantitiesList> implements QuantitiesListDao {
	
	public Page findQuantitiesListByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from QuantitiesList t ";
		String countHql = "select count(*) from QuantitiesList t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("fillPerson") || paramName.equals("fillCompany")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.operateDate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public void saveQuantitiesList(QuantitiesList quantitiesList) {
		getHibernateTemplate().save(quantitiesList);
	}
	

	@Override
	public List<QuantitiesList> findAllByContractId(String contractId) {
		String hql ="from QuantitiesList t where t.removed='0' and t.contractId='"+contractId+"'";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public void updateFirstLevelAmountAndTotalPrice(String object,String objectId, List<String> subjectIdList) {
		StringBuilder sb = new StringBuilder(); 
		if(subjectIdList==null && subjectIdList.size()<1) return ;
		for(int i=0; i<subjectIdList.size(); i++){
			sb.append("'").append(subjectIdList.get(i)).append("',");
		}
		String hql ="update QuantitiesList t set t.projectAmount = " +
				"(select sum(b.amount) from QuantitiesSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+"))"+
				", t.totalPrice = "+
				"(select to_char(sum(b.totalPrice),'9999999999999999.99')  from QuantitiesSubject b where b.removed='0' and b.id in ("+sb.substring(0,sb.length()-1)+"))"+
				" where t.removed='0' and t.contractId='"+objectId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
}
