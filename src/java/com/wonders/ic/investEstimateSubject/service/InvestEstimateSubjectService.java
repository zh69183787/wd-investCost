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

package com.wonders.ic.investEstimateSubject.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
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

public interface InvestEstimateSubjectService {
	
	public Page findInvestEstimateSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	/**
	 * 保存
	 * @param estimateSubject 概算科目
	 */
	public void saveEstimateSubject(InvestEstimateSubject estimateSubject);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return	概算科目
	 */
	public InvestEstimateSubject findEstimateSubjectById(String id);
	
	/**
	 * 更新
	 * @param investEstimateSubject 概算科目
	 */
	public void update(InvestEstimateSubject investEstimateSubject);
	
	public void deleteById(String id);
	
	/**
	 * 批量删除（逻辑）
	 * @param id
	 */
	public void deleteByIdsOnLogically(String[] ids);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteByIdLogical(String id);
	
	
	/**
	 * 查询当前层级下最后一条数据
	 * @param level
	 * @param projectId
	 */
	public InvestEstimateSubject findLastHierarchyByLevel(String projectId,String level);
	
	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	public List<InvestEstimateSubject> findByIds(String[] ids);
	
	
	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	public InvestEstimateSubject findById(String id);
	
	/**
	 * 查询4项概算批复值
	 * @return
	 */
	public List<String> findEstimateDecide(String projectId,List<String> idList);
	
	
	/**
	 * 在页面上面保存概算科目
	 * @return
	 */
	public Wbs saveAddOnPage(InvestEstimateSubject investEstimateSubject,String parentId,String wbsId,String level,String order,String ifHasSpecial,String specialOrder);
	
	/**
	 * 删除所有list
	 * @param list
	 */
	public void deleteAllSubjectAndWbsLogical(List<Wbs> wbsList);
	

	//新增特殊行
	public String saveAddOnPageSpecial(InvestEstimateSubject investEstimateSubject,String parentId,String wbsId,String level,String order,String specialOrder);
	
	public void deleteWbsSpecial(List<Wbs> wbsList);

	
	public String compute(String object,String objectId);
	
	public List<InvestEstimateSubject> findAllBySubjectNameWithFuzzySearch(String projectId,String subjectName);
	
	/**
	 * 更新特殊行
	 * @param ids
	 * @param decides
	 */
	public void updateSpecilData(String[] ids,String[] decides); 
		
	
	

}
