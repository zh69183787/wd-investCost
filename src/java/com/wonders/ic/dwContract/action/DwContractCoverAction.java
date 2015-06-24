
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
 
package com.wonders.ic.dwContract.action;

import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wonders.ic.dwContract.service.DwContractCoverService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */
 
public class DwContractCoverAction extends BaseAjaxAction { 
	private DwContractCover dwContractCover = new DwContractCover();
	private DwContractCoverService dwContractCoverService;
	
	public void setDwContractCoverService(DwContractCoverService dwContractCoverService) {
		this.dwContractCoverService = dwContractCoverService;
	}
	/**
	 * 计算显示合同封面所要用的数据
	 * @return
	 */
	public String calculateDwContractCover(){
		
		dwContractCoverService.updateContractCoverData();
		return SUCCESS;
	}
	
	/**
	 * 计算显示合同封面所要用的数据
	 * @return
	 */
	public String calculateDwContractCoverOnSchedule(){
		
		dwContractCoverService.updateContractCoverData();
		return null;
	}
	
}
	
