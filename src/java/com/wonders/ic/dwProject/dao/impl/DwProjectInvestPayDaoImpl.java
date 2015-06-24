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

package com.wonders.ic.dwProject.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.dwProject.dao.DwProjectInvestPayDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectInvestPay;
import com.wonders.ic.project.entity.bo.Project;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwProjectInvestPay实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectInvestPayDaoImpl extends
		AbstractHibernateDaoImpl<DwProjectInvestPay> implements
		DwProjectInvestPayDao {
	public Page findDwProjectInvestPayByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from DwProjectInvestPay t ";
		String countHql = "select count(*) from DwProjectInvestPay t ";
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
	public List<DwProjectInvestPay> findByName8(String projectNo,String projectName,String sDate,String eDate){
		String hql="from DwProjectInvestPay t where ";
		if((projectNo==null && projectNo.equals("")) && (projectName==null && "".equals(projectName))){
			return null;		//项目名称和编号都为空，返回空
		}else if((projectNo!=null && !"".equals(projectNo)) && (projectName!=null && !"".equals(projectName))){		//都不为空
			hql +="t.projectName = '"+projectName+"' and t.projectNo = '"+projectNo+"'";
		}else{	//一个为空，一个不为空
			if(projectNo!=null && !"".equals(projectNo)){		//编号不为空
				hql +="t.projectNo = '"+projectNo+"'";
			}else{
				hql +="t.projectName = '"+projectName+"'";
			}
		}
		if(sDate!=null && !"".equals(sDate)){
			hql += " and to_char(t.actualPayDate,'yyyy-mm-dd') >= '"+sDate+"'";
		}
		if(eDate!=null && !"".equals(eDate)){
			hql += " and to_char(t.actualPayDate,'yyyy-mm-dd') <= '"+eDate+"'";
		}
		hql += " ORDER BY to_char(t.actualPayDate,'yyyy-mm-dd') ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Project> findAllProjectBindedWithContracts() {
		String hql= "from Project p where p.removed='0' and p.id in (select c.projectId from Contract c where c.removed='0') order by p.createDate DESC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<ContractStatus> findAllContractStatusByProjectIdAndType(String projectId,String type) {
		String hql ="from ContractStatus s where s.removed='0' and s.type='"+type+"' " +
				"and s.contractId in (select t.id from Contract t where t.removed='0' and t.projectId='"+projectId+"') order by s.operateDate ASC"; 
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void saveOrUpdateAll(List<DwProjectInvestPay> dwPayList) {
		getHibernateTemplate().saveOrUpdateAll(dwPayList);
	}
	
	
}
