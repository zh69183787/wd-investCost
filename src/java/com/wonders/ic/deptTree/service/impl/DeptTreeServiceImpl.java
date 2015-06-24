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

package com.wonders.ic.deptTree.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.ic.deptTree.dao.DeptTreeDao;
import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DeptTreeServiceImpl implements DeptTreeService {
	private DeptTreeDao deptTreeDao;

	public void setDeptTreeDao(DeptTreeDao deptTreeDao) {
		this.deptTreeDao = deptTreeDao;
	}

	@Override
	public List<DeptTree> findAllByName(String name) {
		
		return deptTreeDao.findAllByName(name);
	}

	@Override
	public List<DeptTree> findByPid(String pid) {
		return deptTreeDao.findByPid(pid);
	}

	/**
	 * 得到所有子节点的主键
	 * @param pid
	 * @return
	 */
	public List<String> getTreeIdByPid(String pid){
		List<DeptTree> list = findByPid(pid);
		List<String> idList = null;
		if(list!=null && list.size()>0){
			idList = new ArrayList<String>();
			for(int i=0; i<list.size(); i++){
				idList.add(list.get(i).getId());
			}
		}
		return idList;
	}

	@Override
	public DeptTree findById(String id) {
		return deptTreeDao.load(id);
	}
	
	
}
