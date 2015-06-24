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

import com.wonders.ic.dwProject.dao.DwProjectApproveDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectApprove;
import com.wonders.ic.dwProject.service.DwProjectApproveService;

/**
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectApproveServiceImpl implements DwProjectApproveService {
	private DwProjectApproveDao dwProjectApproveDao;

	public void setDwProjectApproveDao(DwProjectApproveDao dwProjectApproveDao) {
		this.dwProjectApproveDao = dwProjectApproveDao;
	}
	public List<DwProjectApprove> findByDate(Date sDate){
		return dwProjectApproveDao.findByDate(sDate);
	}
	@Override
	public List<DwProjectApprove> findByName(String projectNo,
			String projectName, Date sDate) {
		// TODO Auto-generated method stub
		return dwProjectApproveDao.findByName(projectNo, projectName, sDate);
	}
	@Override
	public List<DwProjectApprove> findAllByNameAndNo(String projectName,String projectNo) {

		return dwProjectApproveDao.findAllByNameAndNo(projectName, projectNo);
	}
	
	
}