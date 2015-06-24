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

package com.wonders.ic.dwContract.service;

import java.util.List;

import com.wonders.ic.dwContract.entity.bo.DwContractCover;


/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwContractCoverService {
	/**
	 * 计算合同封面
	 */
	public void updateContractCoverData(); 
	
	/**
	 * 根据合同类型查询，不模糊
	 * @param contractType
	 * @return
	 */
	public DwContractCover findByContractType(String contractType); 
	
	/**
	 * 根据合同类型查询，项目公司不为空
	 * @param contractType
	 * @return
	 */
	public List<DwContractCover> findByCompanyType(String conpanyType);
	
	/**
	 * 根据合同类型模糊搜索
	 * @param contractType
	 * @return
	 */
	public List<DwContractCover> findByContractTypeWithFuzzySearch(String contractType);
}
