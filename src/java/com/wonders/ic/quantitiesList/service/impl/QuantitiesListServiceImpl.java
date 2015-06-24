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

package com.wonders.ic.quantitiesList.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wonders.ic.quantitiesList.dao.QuantitiesListDao;
import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
import com.wonders.ic.quantitiesList.service.QuantitiesListService;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesListServiceImpl implements QuantitiesListService {
	private QuantitiesListDao quantitiesListDao;
	
	public void setQuantitiesListDao(QuantitiesListDao quantitiesListDao) {
		this.quantitiesListDao = quantitiesListDao;
	}

	public Page findQuantitiesListByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return quantitiesListDao.findQuantitiesListByPage(filter, pageNo,
				pageSize);
	}

	@Override
	public void saveQuantitiesList(QuantitiesList quantitiesList) {
		quantitiesListDao.saveQuantitiesList(quantitiesList);
	}

	@Override
	public QuantitiesList findById(String id) {
		return quantitiesListDao.load(id);
	}

	@Override
	public void update(QuantitiesList quantitiesList) {
		quantitiesListDao.update(quantitiesList);
	}

	@Override
	public void delete(QuantitiesList quantitiesList) {
		quantitiesListDao.delete(quantitiesList);
	}

	@Override
	public void deleteById(String id) {
		quantitiesListDao.deleteById(id);
	}

	@Override
	public void saveOrUpdate(QuantitiesList quantitiesList) {
		quantitiesListDao.saveOrUpdate(quantitiesList);
	}

	@Override
	public QuantitiesList findAllByContractId(String contractId) {
		List<QuantitiesList> list = quantitiesListDao.findAllByContractId(contractId);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	
	
	
}
