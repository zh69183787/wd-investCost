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

package com.wonders.ic.dwProject.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.dwProject.entity.bo.DwProjectInvestPay;
import com.wonders.ic.project.entity.bo.Project;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectInvestPayDao extends
		AbstractHibernateDao<DwProjectInvestPay> {
	public Page findDwProjectInvestPayByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<DwProjectInvestPay> findByName8(String projectNo,String projectName,String sDate,String eDate);
	
	/**
	 * 查询所有已被合同绑定过的项目
	 * @return
	 */
	public List<Project> findAllProjectBindedWithContracts();
	
	/**
	 * 查询合同
	 * @param projectId 项目id
	 * @return
	 */
	public List<ContractStatus> findAllContractStatusByProjectIdAndType(String projectId,String type);
	
	/**
	 * 保存或更新
	 */
	public void saveOrUpdateAll(List<DwProjectInvestPay> dwPayList);
	
}
