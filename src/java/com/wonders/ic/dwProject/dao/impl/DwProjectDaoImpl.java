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

import java.text.SimpleDateFormat;
import java.util.List;

import com.wonders.ic.dwProject.dao.DwProjectDao;
import com.wonders.ic.dwProject.entity.bo.DwProject;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwContractʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectDaoImpl extends AbstractHibernateDaoImpl<DwProject> implements DwProjectDao {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 
	@Override
	public List<DwProject> findByYear(String year) {
		String hql="from DwProject t where t.year = '"+year+"' order by t.order asc";
		return getHibernateTemplate().find(hql);
	}

	
	
}
