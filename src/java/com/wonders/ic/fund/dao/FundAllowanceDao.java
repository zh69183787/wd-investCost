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

package com.wonders.ic.fund.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.fund.entity.bo.FundAllowance;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface FundAllowanceDao extends AbstractHibernateDao<FundAllowance> {
	
	public Page findFundAllowanceByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	public void saveFundAllowance(FundAllowance fundAllowance);
	
	public FundAllowance findFundAllowanceById(String id);
	
	public void saveAll(List<FundAllowance> list);
	
	public void deleteByIdOnLogical(String id);
	
	public boolean exists(FundAllowance fundAllowance);
}


