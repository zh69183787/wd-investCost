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

package com.wonders.ic.quantitiesSubject.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
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

public interface QuantitiesSubjectDao extends AbstractHibernateDao<QuantitiesSubject> {
	public Page findQuantitiesSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	
	/**
	 * 查询
	 * @param contractId 合同id
	 * @return 
	 */
	public List<QuantitiesSubject> findByContractId(String contractId);
	
	/**
	 * 逻辑删除
	 * @param ids
	 */
	public void deleteAllByIdsOnLogical(String[] ids);
	
	/**
	 * 查询总价
	 * @param contractId
	 * @return
	 */
	public BigDecimal findSumOfAllPriceByContractId(String contractId);
	
	/**
	 * 查询数量、金额总和
	 * @param idList
	 * @return
	 */
	public List<Object[]> findSumOfAmoutAndDecideByIdList(List<String> idList);
	
	
	/**
	 * 根据关联的wbsId查询
	 * @param wbsId
	 * @return
	 */
	public QuantitiesSubject findByWbsId(String wbsId);
	
	
	public void updateList(List<QuantitiesSubject> list);
	
	/**
	 * 根据合同、概算科目查询工程量条目的总价
	 * @param contractId
	 * @param estimateSubjectId
	 * @return
	 */
	public List<String> findSumOfTotalPriceByContractIdAndEstimateSubjectId(List<String> contractIdList,String estimateSubjectId);
	
	/**
	 * 批量保存
	 * @param qsList
	 */
	public void saveAll(List<QuantitiesSubject> qsList);
	
	public QuantitiesSubject findById(String id);
	
	/**
	 * 设置概算科目id和name为空。用于删除概算科目后
	 * @param investEstimateId
	 */
	public void updateInvestEstimateIdAndNameNull(String investEstimateId);
	
	/**
	 * 根据概算科目id更新概算科目名称
	 * @param investEstimateId
	 */
	public void updateInvestEstimateName(String investEstimateId,String investEstimateName);
	
	/**
	 * 根据概算科目，查询工程量清单绑定在该概算科目下的总金额
	 * @param investEstimateId
	 * @return
	 */
	public String findSumOfTotalPriceByInvestEstimateId(String investEstimateId);
	
	/**
	 * 
	 * @param contractId
	 * @param contractName
	 */
	public void updateContractNameByContractId(String contractId,String contractNo);
	
	/**
	 * 根据contractId逻辑删除
	 * @param contractId
	 */
	public void deleteAllByContractIdOnLogical(String contractId);
}
