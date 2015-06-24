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

package com.wonders.ic.investEstimate.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimate.dao.InvestEstimateDao;
import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
import com.wonders.ic.investEstimate.service.InvestEstimateService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateServiceImpl implements InvestEstimateService {
	private InvestEstimateDao investEstimateDao;

	public void setInvestEstimateDao(InvestEstimateDao investEstimateDao) {
		this.investEstimateDao = investEstimateDao;
	}

	public Page findInvestEstimateByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		return investEstimateDao.findInvestEstimateByPage(filter, pageNo,pageSize);
	}

	@Override
	public InvestEstimate findByProjectId(String projectId) {
		List<InvestEstimate> list = investEstimateDao.findAllByProjectId(projectId);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void update(InvestEstimate investEstimate) {
		investEstimateDao.update(investEstimate);
	}

	@Override
	public void saveAdd(InvestEstimate investEstimate) {
		investEstimateDao.save(investEstimate);
	}

	@Override
	public InvestEstimate findById(String id) {
		return investEstimateDao.load(id);
	}

	@Override
	public void saveOrUpdate(InvestEstimate investEstimate) {
		investEstimateDao.saveOrUpdate(investEstimate);
	}

	@Override
	public void save(InvestEstimate investEstimate) {
		investEstimateDao.save(investEstimate);
	}

	@Override
	public void updateByFirstLevel(String object, String objectId) {
		InvestEstimate ie = investEstimateDao.findByProjectId(objectId);	//该项目下的概算表
		
		
		
		
	}

	
	
}
