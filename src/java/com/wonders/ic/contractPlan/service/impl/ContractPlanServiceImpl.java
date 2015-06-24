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

package com.wonders.ic.contractPlan.service.impl;

import java.util.Map;

import com.wonders.ic.contractPlan.dao.ContractPlanDao;
import com.wonders.ic.contractPlan.entity.bo.ContractPlan;
import com.wonders.ic.contractPlan.service.ContractPlanService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractPlanServiceImpl implements ContractPlanService {
	private ContractPlanDao contractPlanDao;

	public void setContractPlanDao(ContractPlanDao contractPlanDao) {
		this.contractPlanDao = contractPlanDao;
	}

	public Page findContractPlanByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return contractPlanDao.findContractPlanByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveContractPlan(ContractPlan contractPlan) {
		contractPlanDao.save(contractPlan);
	}

	@Override
	public ContractPlan findById(String id) {
		return contractPlanDao.load(id);
	}

	@Override
	public void update(ContractPlan contractPlan) {
		contractPlanDao.update(contractPlan);
	}

	@Override
	public void deleteById(String id) {
		contractPlanDao.deleteById(id);
	}
}
