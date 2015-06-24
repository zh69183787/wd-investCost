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

package com.wonders.ic.corporation.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wonders.ic.corporation.dao.PsCorporationDao;
import com.wonders.ic.corporation.service.PsCorporationService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

public class PsCorporationServiceImpl implements PsCorporationService {
	private PsCorporationDao psCorporationDao;

	public void setPsCorporationDao(PsCorporationDao psCorporationDao) {
		this.psCorporationDao = psCorporationDao;
	}
	
	public List<PsCorporation> findPsCorporationByCompNameWithFuzzySearch(String compName){
		return psCorporationDao.findPsCorporationByCompNameWithFuzzySearch(compName);
	}

	@Override
	public Page findByCompanyNameChn(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return psCorporationDao.findByCompanyNameChn(filter, pageNo, pageSize);
	}
}
