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

package com.wonders.ic.dwContract.dao.impl;

import java.util.List;

import com.wonders.ic.dwContract.dao.DwContractCoverDao;
import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwContractCoverʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractCoverDaoImpl extends
		AbstractHibernateDaoImpl<DwContractCover> implements DwContractCoverDao {

	@Override
	public List<String> findAllContractByType(String type) {
		String hql ="select t.contractOwnerExecuteName,count(t.contractOwnerExecuteName) from Contract t where t.removed='0' " +
				"and t.contractType like '%"+type+"%' and t.contractOwnerExecuteName is not null group by t.contractOwnerExecuteName";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteAll() {
		String hql ="delete DwContractCover t";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void saveOrUpdateAll(List<DwContractCover> dwContractCoverList) {
		getHibernateTemplate().saveOrUpdateAll(dwContractCoverList);
	}

	@Override
	public List<DwContractCover> findByContractType(String contractType) {
		String hql = "from DwContractCover t where t.contractType='"+contractType+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwContractCover> findByCompanyType(String companyType) {
		String hql = "from DwContractCover t where t.companyType='"+companyType+"' order by t.companyId ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwContractCover> findByContractTypeWithFuzzySearch(String contractType) {
		String hql = "from DwContractCover t where t.contractType like '"+contractType+"%' order by t.contractType ASC";
		return getHibernateTemplate().find(hql);
	}
	
	
	
}
