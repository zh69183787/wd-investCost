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

package com.wonders.ic.wbs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.ic.wbs.dao.WbsDao;
import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wonders.ic.wbs.service.WbsService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

public class WbsServiceImpl implements WbsService {
	private WbsDao wbsDao;
	private List<Wbs> wbsList = new ArrayList<Wbs>();

	public void setWbsDao(WbsDao wbsDao) {
		this.wbsDao = wbsDao;
	}

	public Page findWbsByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return wbsDao.findWbsByPage(filter, pageNo, pageSize);
	}

	
	public List<Wbs> sortByWBS(String wbsObject,String projectId){
		return wbsDao.sortByWBS(wbsObject, projectId);
	}


	@Override
	public void save(Wbs wbs) {
		wbsDao.save(wbs);
	}


	@Override
	public List<String> findAllFirstLevelIdByObjectAndObjectId(String object,String objectId) {
		List<Wbs> wbsList = wbsDao.findAllFirstLevelIdByObjectAndId(object, objectId);
		List<String> idList = null;
		if(wbsList!=null && wbsList.size()>0){
			idList = new ArrayList<String>();
			for(int i=0; i<wbsList.size(); i++){
				idList.add(wbsList.get(i).getNodeId());
			}
		}
		return idList;
	}

	@Override
	public List<Wbs> findAllWbsHierarchy(String object, String objectId,String id) {
		Wbs wbs = wbsDao.load(id);
		if(wbs==null)
			return null;
		this.wbsList.add(wbs);
		findWbsHierarchyByDown(wbs.getWbsObject(),wbs.getWbsObjectId(),wbs.getId());
		/*List<Wbs> list = wbsDao.findAllWbsHierarchyByDown(wbs.getWbsObject(),wbs.getWbsObjectId(),wbs.getId());
		if(list==null || list.size()<1)
			return wbsList;
		for(int i=0; i<list.size(); i++){
			List<Wbs> temp = wbsDao.findAllWbsHierarchyByDown(list.get(i).getWbsObject(), list.get(i).getWbsObject(), list.get(i).getId());
			wbsList.addAll(temp);
		}*/
		List<Wbs> resultList = new ArrayList<Wbs>();
		for(int i=0; i<wbsList.size();){
			resultList.add(wbsList.get(0));
			wbsList.remove(0);
		}
		return resultList;
	}
	
	public void findWbsHierarchyByDown(String object,String objectId,String id){
		List<Wbs> list = wbsDao.findAllWbsHierarchyByDown(object, objectId, id);
		if(list!=null && list.size()>0){
			this.wbsList.addAll(list);
			for(int i=0; i<list.size(); i++){
				findWbsHierarchyByDown(list.get(0).getWbsObject(), list.get(0).getWbsObjectId(), list.get(i).getId());
			}
		}
	}

	@Override
	public List<Wbs> findAllByNodeIds(String object,String objectId,List<String> nodeIdList) {
		
		return wbsDao.findAllByNodeIds(object, objectId, nodeIdList);
	}

	
	
	public List<Wbs> findSpecialRows(String wbsObject,String projectId){
		return wbsDao.findSpecialRows(wbsObject, projectId);
	}
	
	
	

}
