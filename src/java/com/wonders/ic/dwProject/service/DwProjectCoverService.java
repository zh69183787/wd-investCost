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

import java.util.List;

import com.wonders.ic.dwProject.entity.bo.DwProjectCover;


/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectCoverService {
	
	/**
	 * 计算项目封面
	 */
	public void updateDwProjectData();
	
	/**
	 * 根据项目类型、公司类型查询
	 * @param projectType
	 * @param companyType
	 * @return
	 */
	public List<DwProjectCover> findByProjectTypeAndCompanyType(String projectType,String companyType);
}
