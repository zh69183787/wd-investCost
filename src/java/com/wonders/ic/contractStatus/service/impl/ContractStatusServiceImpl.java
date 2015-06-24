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

package com.wonders.ic.contractStatus.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.contractStatus.dao.ContractStatusDao;
import com.wonders.ic.contractStatus.service.ContractStatusService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractStatusServiceImpl implements ContractStatusService {
	private ContractStatusDao contractStatusDao;

	public void setContractStatusDao(ContractStatusDao contractStatusDao) {
		this.contractStatusDao = contractStatusDao;
	}

	public void addContractStatus(ContractStatus contractStatus) {
		contractStatusDao.save(contractStatus);
	}

	public void deleteContractStatus(ContractStatus contractStatus) {
		contractStatusDao.delete(contractStatus);
	}

	public ContractStatus findContractStatusById(String id) {
		return contractStatusDao.load(id);
	}

	public void updateContractStatus(ContractStatus contractStatus) {
		contractStatusDao.update(contractStatus);
	}

	public Page findContractStatusByPage(int pageNo, int pageSize) {
		Page page = contractStatusDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findContractStatusByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return contractStatusDao.findContractStatusByPage(filter, pageNo,
				pageSize);
	}
	
	public List<ContractStatus> findStatusList(String id ,String type){
		return contractStatusDao.findStatusList(id,type);
	}

	public List<Object[]> findByTypeAndDateRange(String type,String startDate,String endDate){
		return contractStatusDao.findByTypeAndDateRange(type,startDate,endDate);
	}
	
	@Override
	public double findSumOfContractStatusByContractIdAndType(String contractId,String type) {
		
		return contractStatusDao.findSumOfContractStatusByContractIdAndType(contractId, type);
	}

	@Override
	public void saveAll(List<ContractStatus> list) {
		contractStatusDao.saveAll(list);
	}

	@Override
	public boolean isResultExist(String year, String contractId,String type) {
		List<ContractStatus> list = contractStatusDao.findByYearAndContractId(year,contractId,type);
		if(list!=null && list.size()>0) return true;
		return false;
	}
	
	
}
