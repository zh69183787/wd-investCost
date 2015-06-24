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

package com.wonders.ic.dwContract.service.impl;

import java.util.Date;
import java.util.List;

import com.wonders.ic.dwContract.dao.DwContractPayDao;
import com.wonders.ic.dwContract.entity.bo.DwContractPay;
import com.wonders.ic.dwContract.service.DwContractPayService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-5
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractPayServiceImpl implements DwContractPayService {
	private DwContractPayDao dwContractPayDao;

	public void setDwContractPayDao(DwContractPayDao dwContractPayDao) {
		this.dwContractPayDao = dwContractPayDao;
	}

	@Override
	public List<DwContractPay> findByDate6(Date sDate){
		return dwContractPayDao.findByDate6(sDate);
	}
	
	@Override
	public List<DwContractPay> findByName6(String projectNo,String projectName,Date sDate){
		return dwContractPayDao.findByName6(projectNo,projectName,sDate);
	}
	
}
