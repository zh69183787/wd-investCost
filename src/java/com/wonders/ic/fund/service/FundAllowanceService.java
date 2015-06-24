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

import com.wonders.ic.fund.entity.bo.FundAllowance;
import com.wondersgroup.framework.core.bo.Page;

public interface FundAllowanceService {
	
	public Page findFundAllowanceByPage(FundAllowance fundPlan, String pageNo, String pageSize);
	
	public void saveFundAllowance(FundAllowance fundPlan);
	
	public FundAllowance findFundAllowanceById(String id);
	
	/**
	 * 批量保存(导入)
	 * @param list
	 */
	public void saveAll(List<FundAllowance> list);
	
	public void deleteByIdOnLogical(String id);
	
	public boolean exists(FundAllowance fundAllowance);
	
}
