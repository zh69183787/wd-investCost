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

package com.wonders.ic.deptTree.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DeptTreeAction extends BaseAjaxAction {
	private DeptTree deptTree = new DeptTree();
	private DeptTreeService deptTreeService;
	
	public DeptTree getDeptTree() {
		return deptTree;
	}

	public void setDeptTree(DeptTree deptTree) {
		this.deptTree = deptTree;
	}

	@Override
	public Object getModel() {
		return deptTree;
	}

	public DeptTreeService getDeptTreeService() {
		return deptTreeService;
	}

	public void setDeptTreeService(DeptTreeService deptTreeService) {
		this.deptTreeService = deptTreeService;
	}
	
	public String findDeptByName(){
		String deptName = ServletActionContext.getRequest().getParameter("deptName");
		List<DeptTree> treeList = deptTreeService.findAllByName(deptName);
		if(treeList!=null && treeList.size()>0){
			String jsonData = VOUtils.getJsonDataFromCollection(treeList);
			createJSonData(jsonData);
		}
		return AJAX;
	}

	
}
