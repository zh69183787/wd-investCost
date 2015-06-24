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

package com.wonders.ic.contractPlan.service;

import java.util.Map;
import java.util.Date;

import com.wonders.ic.contractPlan.entity.bo.ContractPlan;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface ContractPlanService {
	
	public Page findContractPlanByPage(Map<String, Object> filter, int pageNo,int pageSize);
	
	/**
	 * 保存新增
	 * @param contractPlan 合同计划
	 */
	public void saveContractPlan(ContractPlan contractPlan);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 合同计划
	 */
	public ContractPlan findById(String id);
	
	/**
	 * 更新
	 * @param contractPlan 合同计划 
	 */
	public void update(ContractPlan contractPlan);
	
	/**
	 * 删除
	 * @param contractPlan 合同计划
	 */
	public void deleteById(String id);
}
