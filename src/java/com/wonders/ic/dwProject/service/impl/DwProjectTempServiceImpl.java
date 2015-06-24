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

import java.util.Date;
import java.util.List;

import com.wonders.ic.dwProject.dao.DwProjectTempDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectTemp;
import com.wonders.ic.dwProject.service.DwProjectTempService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectTempServiceImpl implements DwProjectTempService {
	private DwProjectTempDao dwProjectTempDao;

	public void setDwProjectTempDao(DwProjectTempDao dwProjectTempDao) {
		this.dwProjectTempDao = dwProjectTempDao;
	}

	@Override
	public List<DwProjectTemp> findByDate(Date sDate,Date eDate) {
		return dwProjectTempDao.findByDate(sDate,eDate);
	}

	
}
