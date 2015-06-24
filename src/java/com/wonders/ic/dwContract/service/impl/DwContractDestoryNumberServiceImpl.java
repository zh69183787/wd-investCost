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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.dwContract.dao.DwContractDestoryNumberDao;
import com.wonders.ic.dwContract.entity.bo.DwContractDestoryNumber;
import com.wonders.ic.dwContract.service.DwContractDestoryNumberService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-3-8
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractDestoryNumberServiceImpl implements
		DwContractDestoryNumberService {
	private DwContractDestoryNumberDao dwContractDestoryNumberDao;

	public void setDwContractDestoryNumberDao(
			DwContractDestoryNumberDao dwContractDestoryNumberDao) {
		this.dwContractDestoryNumberDao = dwContractDestoryNumberDao;
	}

	@Override
	public List<DwContractDestoryNumber> findAll() {
		
		return dwContractDestoryNumberDao.findAllDestoryNumber();
	}

	@Override
	public List<DwContractDestoryNumber> findByFilter(Map<String, String> filter) {
		
		return dwContractDestoryNumberDao.findByFilter(filter);
	}

	
}
