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

package com.wonders.ic.quantitiesList.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface QuantitiesListDao extends AbstractHibernateDao<QuantitiesList> {
	public Page findQuantitiesListByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	
	/**
	 * 保存新增
	 * @param quantitiesList
	 */
	public void saveQuantitiesList(QuantitiesList quantitiesList);
	

	/**
	 * 根据合同id查询
	 * @param contractId
	 * @return
	 */
	public List<QuantitiesList> findAllByContractId(String contractId);
	
	/**
	 * 
	 * @param object
	 * @param objectId
	 * @param idList
	 */
	public void updateFirstLevelAmountAndTotalPrice(String object,String objectId,List<String> idList);
}
