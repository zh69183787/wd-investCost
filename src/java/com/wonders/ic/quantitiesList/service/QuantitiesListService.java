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

package com.wonders.ic.quantitiesList.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
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

public interface QuantitiesListService {
	/**
	 * 分页查询
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findQuantitiesListByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	
	/**
	 * 保存新增
	 * @param quantitiesList
	 */
	public void saveQuantitiesList(QuantitiesList quantitiesList);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 工程量清单
	 */
	public QuantitiesList findById(String id);
	
	/**
	 * 更新
	 * @param quantitiesList 工程量清单
	 */
	public void update(QuantitiesList quantitiesList); 
	
	/**
	 * 删除
	 * @param quantitiesList 工程量清单
	 */
	public void delete(QuantitiesList quantitiesList);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
	
	/**
	 * 保存新增或更新
	 * @param quantitiesList
	 */
	public void saveOrUpdate(QuantitiesList quantitiesList);
	
	/**
	 * 根据合同id查询
	 * @param contractId
	 * @return
	 */
	public QuantitiesList findAllByContractId(String contractId);
	
	
	
}
