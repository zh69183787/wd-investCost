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

package com.wonders.ic.corporation.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wonders.ic.corporation.service.PsCorporationService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

public class PsCorporationAction extends BaseAjaxAction {
	private PsCorporation psCorporation = new PsCorporation();
	private PsCorporationService psCorporationService;

	/**
	 * ajax查询,根据compName查询
	 */
	public String findPsCorporationByCompNameWithFuzzySearch(){
		String compName = ServletActionContext.getRequest().getParameter("compName");
		List<PsCorporation> list = psCorporationService.findPsCorporationByCompNameWithFuzzySearch(compName);
        if(list!=null && list.size()>0){
            String jsonData = VOUtils.getJsonDataFromCollection(list);
            createJSonData(jsonData);
        }
		
		return AJAX;
		
	}


	public void setPsCorporationService(
			PsCorporationService psCorporationService) {
		this.psCorporationService = psCorporationService;
	}

	public PsCorporation getPsCorporation() {
		return psCorporation;
	}

	public void setPsCorporation(PsCorporation psCorporation) {
		this.psCorporation = psCorporation;
	}
	
}
