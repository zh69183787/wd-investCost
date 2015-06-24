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

package com.wonders.ic.wbs.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

public interface WbsDao extends AbstractHibernateDao<Wbs> {
	public Page findWbsByPage(Map<String, Object> filter, int pageNo,
			int pageSize);

	public List<Wbs> sortByWBS(String wbsObject,String projectId);
	
	/**
	 * 刷新wbsOrder
	 * @param projectId 项目id
	 * @param parentId	父节点
	 * @param level	层级 
	 * @param order	当前order 
	 */
	public void updateWbsOrderByIncrease(String object,String objectId,String parentId,long level,long order,String ifHasSpecial,String specialOrder);
	
	
	/**
	 * 刷新wbsOrder
	 * @param projectId 项目id
	 * @param parentId	父节点
	 * @param level	层级 
	 * @param order	当前order 
	 */
	public void updateWbsOrderByDecrease(String object,String objectId,String parentId,long level,long order);
	
	/**
	 * 查询
	 * @param wbsObjectId 关联WBS_OBJECT的主键
	 * @return
	 */
	public Wbs findByObjectAndObjectIdAndNodeId(String object,String objectId,String nodeId);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteByIdLogical(String id);
	
	/**
	 * 查询所有第一层的objectId
	 * @param projectId	项目id
	 * @return
	 */
	public List<Wbs> findAllFirstLevelIdByObjectAndId(String object,String objectId);
	
	/**
	 * 查询该目录以及其所有子目录
	 * @param object
	 * @param objectId
	 * @param id
	 * @return
	 */
	public List<Wbs> findAllWbsHierarchyByDown(String object,String objectId,String id);
	
	
	/**
	 * 逻辑删除
	 * @param wbsList
	 */
	public void deleteAllOnLogically(List<Wbs> wbsList);
	
	//找出特殊行
	public List<Wbs> findSpecialRows(String wbsObject,String projectId);
	

	/**
	 * 查询最后一个层级
	 * @param object
	 * @param objectId
	 * @return
	 */
	public Wbs findLastLevelByObjectAndObjectId(String object,String objectId);
	
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public Wbs findById(String id);

	
	/**
	 * 根据节点查询
	 * @param nodeIdList
	 * @return
	 */
	public List<Wbs> findAllByNodeIds(String object,String objectId,List<String> nodeIdList);

	
	public void updateWbsSpecialOrder(String object,String objectId, String parentId,long level, long order,long specialOrder);

	
	
	/**
	 * 根据level查询
	 * @param object
	 * @param obejctId
	 * @param level
	 * @return
	 */
	public List<Wbs> findAllByObjectAndObjectIdAndLevel(String object,String objectId,long level);
	
	/**
	 * 
	 */
	public void deleteAllByObjectIdOnLogical(String object,String objectId);

}
