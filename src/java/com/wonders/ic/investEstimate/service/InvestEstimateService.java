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

package com.wonders.ic.investEstimate.service;

import java.util.Map;

import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
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

public interface InvestEstimateService {
	
	public Page findInvestEstimateByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	/**
	 * 根据项目id查询
	 * @param projectId
	 * @return
	 */
	public InvestEstimate findByProjectId(String projectId);
	
	/**
	 * 更新
	 * @param investEstimate
	 */
	public void update(InvestEstimate investEstimate);
	
	
	/**
	 * 保存
	 * @param investEstimate
	 */
	public void saveAdd(InvestEstimate investEstimate);
	
	
	
	/**
	 * 查询
	 */
	public InvestEstimate findById(String id );
	
	/**
	 * 保存或更新
	 * @param investEstimate
	 */
	public void saveOrUpdate(InvestEstimate investEstimate);
	
	/**
	 * 保存
	 * @param investEstimate
	 */
	public void save(InvestEstimate investEstimate);
	
	
	
	/**
	 * 更新概算表，将概算科目的顶层的值相加
	 * @param object
	 * @param objectId
	 */
	public void updateByFirstLevel(String object,String objectId);
	
	
	
	
	
	
	
	
	
	
	
	
}
