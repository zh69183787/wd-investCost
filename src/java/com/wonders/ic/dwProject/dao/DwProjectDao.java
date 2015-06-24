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

package com.wonders.ic.dwProject.dao;

import java.util.List;

import com.wonders.ic.dwProject.entity.bo.DwProject;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectDao extends AbstractHibernateDao<DwProject> {
	
	/**
	 * 根据时间查询
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public List<DwProject> findByYear(String year);
	
	
}
