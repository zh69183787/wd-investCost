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

package com.wonders.ic.contractStatus.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface ContractStatusDao extends AbstractHibernateDao<ContractStatus> {
	public Page findContractStatusByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	public List<ContractStatus> findStatusList(String id,String type);
	
	/**
	 * 查询总价
	 * @param contractId
	 * @param tyupe
	 * @return
	 */
	public double findSumOfContractStatusByContractIdAndType(String contractId,String tyupe);
	
	public void saveAll(List<ContractStatus> list);
	
	public List<ContractStatus> findByYearAndContractId(String year,String contractId,String type);
	
	public List<Object[]> findByTypeAndDateRange(String type,String startDate,String endDate);
	
	/**
	 * 根据项目的类型查询
	 * @param year 年份
	 * @param onlyThisYear 仅当年
	 * @param professionalType 专业类型
	 * @param typeIds 项目类型ids
	 * @param season 季度
	 * @return
	 */
	public List<ContractStatus> findByYear(String year, boolean onlyThisYear, String professionalType, List<String> typeIds, Long season);
}


