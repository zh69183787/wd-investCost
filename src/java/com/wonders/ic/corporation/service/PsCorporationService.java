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

package com.wonders.ic.corporation.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

public interface PsCorporationService {
	/**
	 * 
	 * @function:模糊匹配
	 * @data:2013-1-25下午12:48:57
	 * @author Xuxiao
	 * @param compName
	 * @return
	 *
	 */
	public List<PsCorporation> findPsCorporationByCompNameWithFuzzySearch(String compName);
	
	/**
	 * 根据供应商名称查询供应商，仅用于接口
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page findByCompanyNameChn(Map<String, Object> filter, int pageNo, int pageSize);
}
