
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
 
package com.wonders.ic.dwProject.action;

import com.wonders.ic.dwProject.service.DwProjectCoverService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2013-3-6
 * @author modify by $Author$
 * @since 1.0
 */
 
public class DwProjectCoverAction extends BaseAjaxAction { 
	private DwProjectCoverService dwProjectCoverService;
	
	public void setDwProjectCoverService(DwProjectCoverService dwProjectCoverService) {
		this.dwProjectCoverService = dwProjectCoverService;
	}

	/**
	 * 计算显示合同封面所要用的数据
	 * @return
	 */
	public String calculateDwProjectCover(){
		
		dwProjectCoverService.updateDwProjectData();
		return SUCCESS;
	}
	
	/**
	 * 计算显示合同封面所要用的数据
	 * @return
	 */
	public String calculateDwProjectCoverOnSchedule(){
		
		dwProjectCoverService.updateDwProjectData();
		return null;
	}

}
	
