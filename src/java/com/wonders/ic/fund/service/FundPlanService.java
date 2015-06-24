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

package com.wonders.ic.fund.service;

import java.util.List;

import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wondersgroup.framework.core.bo.Page;

public interface FundPlanService {
	
	public Page findFundPlanByPage(FundPlan fundPlan, String pageNo, String pageSize);
	
	public void saveFundPlan(FundPlan fundPlan);
	
	public FundPlan findFundPlanById(String id);
	
	/**
	 * 批量保存(导入)
	 * @param list
	 */
	public void saveAll(List<FundPlan> list);
	
	public void deleteByIdOnLogical(String id);
	
	public boolean exists(FundPlan fundPlan);

}
