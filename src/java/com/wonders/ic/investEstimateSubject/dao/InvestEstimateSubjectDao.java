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

package com.wonders.ic.investEstimateSubject.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wonders.ic.wbs.entity.bo.Wbs;
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

public interface InvestEstimateSubjectDao extends AbstractHibernateDao<InvestEstimateSubject> {
	public Page findInvestEstimateSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	public boolean findHierarchy(String type,String projectId,String wbsId);
	
	/**
	 * 查询下一级目录
	 * @param projectId
	 * @param wbsId
	 * @return
	 */
	public boolean findLowerHierarchy(String projectId,String wbsId);
	
	
	/**
	 * 查询当前层级下的所有
	 * @param level 层级
	 * @return
	 */
	public List<InvestEstimateSubject> findAllHierarchyByLevel(String projectId,String level);
	
	/**
	 * 批量逻辑删除
	 * @param id
	 */
	public void deleteByIdLogical(String[] id);
	
	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	public List<InvestEstimateSubject> findByIds(String[] ids);
	
	/**
	 * 查询4项概算批复值
	 * @return
	 */
	public Object[] findEstimateDecide(String projectId,String[] idArray);
	
	public void deleteByIdLogical(String id);
	
	public void deleteAllOnLogically(String[] ids);
	
	public List<InvestEstimateSubject> findAllByIdList(List<String> ids);
	
	/**
	 * 模糊查询
	 * @param subjectName
	 * @return
	 */
	public List<InvestEstimateSubject> findAllByProjectIdAndSubjectNameWithFuzzySearch(String projectId,String subjectName);
	
	/**
	 * 根据idList，查询数量，和概算批复值
	 * @param idList
	 * @return
	 */
	public List<Object[]> findSumOfAmoutAndDecideByIdList(List<String> idList);
	
	
	/**
	 * 根据wbs的主键查询
	 * @param wbsParentId
	 * @return
	 */
	public InvestEstimateSubject findByWbsId(String wbsId);
	
	/**
	 * 更新list
	 * @param list
	 */
	public void updateList(List<InvestEstimateSubject> list);
	
	
	
}
