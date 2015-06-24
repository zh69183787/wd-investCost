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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.ic.dwProject.dao.DwProjectCoverDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectCover;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwProjectCoverʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectCoverDaoImpl extends
		AbstractHibernateDaoImpl<DwProjectCover> implements DwProjectCoverDao {

	@Override
	public void deleteAll() {
		String hql ="delete DwProjectCover";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}

	@Override
	public Long findProjectSumByTypeAndAdddeptId(String type,String adddeptId) {
		String hql ="select count(*) from Project t where ";
		if(type.equals("0")){
			hql += " t.projectType in ('2','3','4')";
		}else{
			hql += " t.projectType = '"+type+"'";
		}
		if(adddeptId!=null && adddeptId.equals("-1")){
			hql += " and t.projectAdddeptId is null";
		}else if(adddeptId!=null && !"".equals(adddeptId)){
			hql += " and t.projectAdddeptId ='"+adddeptId+"'";
		}
		hql += " and t.removed='0'";
		List<Long> list = getHibernateTemplate().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	
	
	

	@Override
	public Long findProjectSumByTypeAndWithoutAdddeptId(String type,List<String> adddeptId) {
		String hql ="select count(*) from Project t where ";
		if(type.equals("0")){
			hql += " t.projectType in ('2','3','4')";
		}else{
			hql += " t.projectType = '"+type+"'";
		}
		if(adddeptId!=null && adddeptId.size()>0){
			String idFilter = "";
			for(int i=0; i<adddeptId.size(); i++){
				idFilter +="'" + adddeptId.get(i)+"',";
			}
			hql += " and (t.projectAdddeptId is null or t.projectAdddeptId not in("+idFilter.substring(0,idFilter.length()-1)+"))";
		}
		hql += " and t.removed='0'";
		List<Long> list = getHibernateTemplate().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	@Override
	public void saveOrUpdateAll(List<DwProjectCover> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}
	
	@Override
	public List<DwProjectCover> findByProjectTypeAndCompanyType(String projectType, String companyType) {
		String hql ="from DwProjectCover t where t.projectType='"+projectType+"'";
		if(companyType!=null && !"".equals(companyType)){
			hql += " and t.companyType='"+companyType+"'";
		}else if(companyType==null){
			hql += " and t.companyType is null";
		}
		hql += " order by t.companyId ASC";
		return getHibernateTemplate().find(hql);
	}
}
