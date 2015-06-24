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

package com.wonders.ic.wbs.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

public interface WbsService {
	
	public Page findWbsByPage(Map<String, Object> filter, int pageNo,int pageSize);
	

	
	public List<Wbs> sortByWBS(String wbsObject,String projectId);

	/**
	 * 保存
	 * @param wbs
	 */
	public void save(Wbs wbs);
	
	/**
	 * 查询所有第一层的objectId
	 * @param object	1:项目，2：合同
	 * @param object 	关联对象的主键
	 * @return
	 */
	public List<String> findAllFirstLevelIdByObjectAndObjectId(String object,String objectId);
	
	/**
	 * 查询该目录以及其所有子目录
	 * @param object
	 * @param objectId
	 * @param nodeId
	 * @return
	 */
	public List<Wbs> findAllWbsHierarchy(String object,String objectId,String id);
	

	public List<Wbs> findSpecialRows(String wbsObject,String projectId);

	/**
	 * 根据
	 * @param nodeIdList
	 * @return
	 */
	public List<Wbs> findAllByNodeIds(String object,String objectId,List<String> nodeIdList);


}
