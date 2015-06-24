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

package com.wonders.ic.fund.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface FundPlanDao extends AbstractHibernateDao<FundPlan> {
	
	public Page findFundPlanByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	public void saveFundPlan(FundPlan fundPlan);
	
	public FundPlan findFundPlanById(String id);
	
	public void saveAll(List<FundPlan> list);
	
	public void deleteByIdOnLogical(String id);
	
	public boolean exists(FundPlan fundPlan);
}


