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

package com.wonders.ic.quantitiesSubject.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wonders.ic.wbs.entity.bo.Wbs;
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

public interface QuantitiesSubjectService {
	
	public Page findQuantitiesSubjectByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	/**
	 * 查询
	 * @param contractId 合同id
	 * @return 
	 */
	public List<QuantitiesSubject> findByContractId(String contractId);
	
	/**
	 * 保存
	 * @param quantitiesSubject
	 */
	public void saveQuantitiesSubject(QuantitiesSubject quantitiesSubject);
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public QuantitiesSubject findById(String id);
	
	/**
	 * 更新
	 * @param quantitiesSubject
	 */
	public void update (QuantitiesSubject quantitiesSubject);
	
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
	public String findSumOfAllPriceByContractId(String contractId);
	
	public Wbs saveAddOnPage(QuantitiesSubject quantitiesSubject,String parentId, String wbsId,String level,String order,String ifHasSpecial,String specialOrder);
	
	/**
	 * 逻辑删除
	 * @param wbsList
	 */
	public void deleteAllSubjectAndWbsLogically(List<Wbs> wbsList);
	
	public String saveAddOnPageSpecial(QuantitiesSubject quantitiesSubject,String parentId, String wbsId,String level,String order,String specialOrder);
	
	public void deleteWbsSpecial(List<Wbs> wbsList) ;
	
	/**
	 * 重新计算
	 * @param object
	 * @param objectId
	 */
	public void updateCompute(String object, String objectId) ;
	
	/**
	 * 更新特殊行
	 * @param ids
	 * @param number
	 * @param money
	 */
	public void updateSpecialData(String[] ids,String number[],String money[]);
	
	/**
	 * 根据合同、概算科目查询工程量条目的总价
	 * @param contractId
	 * @param estimateSubjectId
	 * @return
	 */
	public List<String> findSumOfTotalPriceByContractIdAndEstimateSubjectId(List<String> contractId,String estimateSubjectId);
		
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
	 * 
	 * @param contractId
	 * @param contractNo
	 */
	public void updateContractNameByContractId(String contractId,String contractNo);
}
