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

package com.wonders.ic.dwProject.service;

import java.util.Date;
import java.util.List;

import com.wonders.ic.dwProject.entity.bo.DwProjectApprove;

/**
 * 业务服务
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectApproveService {
	/**
	 * 根据时间查询
	 * @param sDate
	 * @return
	 */
	public List<DwProjectApprove> findByDate(Date sDate);
	public List<DwProjectApprove> findByName(String projectNo,String projectName,Date sDate);
	
	/**
	 * 根据名称和编号查询
	 * @param projectName
	 * @param projectNo
	 * @return
	 */
	public List<DwProjectApprove> findAllByNameAndNo(String projectName,String projectNo);
}
