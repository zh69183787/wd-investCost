
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwContract.entity.bo.DwContractProgress;
import com.wonders.ic.dwContract.service.DwContractProgressService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2013-2-26
 * @author modify by $Author$
 * @since 1.0
 */
 
public class DwContractProgressAction extends BaseAjaxAction { 
	private DwContractProgress dwContractProgress = new DwContractProgress();
	private DwContractProgressService dwContractProgressService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setDwContractProgressService(DwContractProgressService dwContractProgressService) {
		this.dwContractProgressService = dwContractProgressService;
	}
	
	//显示报表0
	public String showReport0(){
		String contractCreateDate = ServletActionContext.getRequest().getParameter("contractCreateDate");
		
		List<DwContractProgress> resultList = new ArrayList<DwContractProgress>();
		
		
		
		Date date = null;
		if(contractCreateDate==null || "".equals(contractCreateDate)){
			date = new Date();
		}else{
			try {
				date = sdf.parse(contractCreateDate);
			}catch (ParseException e) {
				System.out.println("日期转换错误");
			}
		}
		servletRequest.setAttribute("contractCreateDate", sdf.format(date));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar c1 = Calendar.getInstance();		//本年的开始时间
		c1.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
		c1.set(Calendar.MONTH, c1.getMinimum(Calendar.MONTH));					
		c1.set(Calendar.DATE, c1.getMinimum(Calendar.DATE));
		
		Calendar c2 = Calendar.getInstance();		//本年结束时间
		c2.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c2.getMaximum(Calendar.MONTH));					
		c2.set(Calendar.DATE, c2.getMaximum(Calendar.DATE));
		resultList = dwContractProgressService.findByContractCreateDate(sdf.format(c1.getTime()),sdf.format(c2.getTime()));
		
		servletRequest.setAttribute("resultList", resultList);
		
		
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		return SUCCESS;
	}
}
	
