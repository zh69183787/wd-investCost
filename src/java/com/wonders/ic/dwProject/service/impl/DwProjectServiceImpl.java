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

package com.wonders.ic.dwProject.service.impl;

import java.util.List;

import com.wonders.ic.dwProject.dao.DwProjectDao;
import com.wonders.ic.dwProject.entity.bo.DwProject;
import com.wonders.ic.dwProject.service.DwProjectService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectServiceImpl implements DwProjectService {
	private DwProjectDao dwProjectDao;

	public void setDwProjectDao(DwProjectDao dwProjectDao) {
		this.dwProjectDao = dwProjectDao;
	}

	@Override
	public List<DwProject> findByYear(String year) {
		return dwProjectDao.findByYear(year);
	}

	
}
