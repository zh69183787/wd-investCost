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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwContract.entity.bo.DwContractPay;
import com.wonders.ic.dwContract.service.DwContractPayService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-5
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractPayAction extends BaseAjaxAction {
	private DwContractPay dwContractPay = new DwContractPay();
	private DwContractPayService dwContractPayService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setDwContractPayService(
			DwContractPayService dwContractPayService) {
		this.dwContractPayService = dwContractPayService;
	}
	
	//显示报表6 
	public String showReport6(){
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String eDate = ServletActionContext.getRequest().getParameter("eDate");
		String projectName = "上海轨道交通10号线一期工程";
		String projectNo = "2007-十号线-001-资本性项目";
		List<DwContractPay> list = null;
		try {
			Date date1 = null;
			if(sDate!=null && !"".equals(sDate)){
				date1 = sdf.parse(sDate);
			}else{
				date1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				sDate = sdf.format(calendar.getTime());
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				eDate = sdf.format(calendar.getTime());
			}
			list = dwContractPayService.findByName6(projectNo,projectName,date1);
		} catch (ParseException e) {
			System.out.println("日期转化错误！");
		}
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		ServletActionContext.getRequest().setAttribute("projectName", projectName);
		ServletActionContext.getRequest().setAttribute("projectNo", projectNo);
		ServletActionContext.getRequest().setAttribute("sDate", sDate);
		ServletActionContext.getRequest().setAttribute("eDate", eDate);
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}
	
}
