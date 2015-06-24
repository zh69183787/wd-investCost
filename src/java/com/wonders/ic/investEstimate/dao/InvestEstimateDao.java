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

package com.wonders.ic.investEstimate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
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

public interface InvestEstimateDao extends AbstractHibernateDao<InvestEstimate> {
	public Page findInvestEstimateByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	/**
	 * 根据projectId查询
	 * @param projectId
	 * @return
	 */
	public List<InvestEstimate> findAllByProjectId(String projectId);
	
	/**
	 * 根据projectId查询(结果唯一)
	 * @param projectId
	 * @return
	 */
	public InvestEstimate findByProjectId(String projectId);
	
	
	/**
	 * 更新概算表，把概算科目的第一层的概算批复值相加
	 * @param projectId
	 * @param subjectIdList
	 */
	public void updateFirstLevelAllDecide(String object,String projectId,List<String> idList);
	
	
	
	
}
