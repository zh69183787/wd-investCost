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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwProject.entity.bo.DwProjectApprove;
import com.wonders.ic.dwProject.service.DwProjectApproveService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectApproveAction extends BaseAjaxAction {
	private DwProjectApprove dwProjectApprove = new DwProjectApprove();
	private DwProjectApproveService dwProjectApproveService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setDwProjectApproveService(
			DwProjectApproveService dwProjectApproveService) {
		this.dwProjectApproveService = dwProjectApproveService;
	}
	
	//显示报表4
	public String showReport4(){
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String eDate = ServletActionContext.getRequest().getParameter("eDate");
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		
		if((projectName==null || "".equals(projectName)) && (projectNo==null || "".equals(projectNo))){
			projectName = "轨道交通4号线28列AC05型电动列车架修项目";
			projectNo = "2011-维保中心-004-大修项目";
		}
		List<DwProjectApprove> list = dwProjectApproveService.findAllByNameAndNo(projectName, projectNo);
		
		/*
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
			list = dwProjectApproveService.findByName(projectNo, projectName, date1);
		} catch (ParseException e) {
			System.out.println("日期转化错误！");
		}
		*/
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		ServletActionContext.getRequest().setAttribute("sDate", sDate);
		ServletActionContext.getRequest().setAttribute("projectName", projectName);
		ServletActionContext.getRequest().setAttribute("projectNo", projectNo);
		ServletActionContext.getRequest().setAttribute("eDate", eDate);
		return SUCCESS;
	}
}
