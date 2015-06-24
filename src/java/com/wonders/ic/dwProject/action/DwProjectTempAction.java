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

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwProject.entity.bo.DwProjectTemp;
import com.wonders.ic.dwProject.service.DwProjectTempService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectTempAction extends BaseAjaxAction {
	private DwProjectTempService dwProjectTempService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setDwProjectTempService(DwProjectTempService dwProjectTempService) {
		this.dwProjectTempService = dwProjectTempService;
	}

	//显示报表1'
	public String showReport(){
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(sDate==null || "".equals(sDate)){
			sDate = "2012-01-01";
		}
		List<DwProjectTemp> list =null;
		try {
			Date nowDate = sdf.parse(sDate);
			Calendar calendar = Calendar.getInstance(),calendar2=Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.set(Calendar.MONTH, calendar.getMinimum(Calendar.MONTH));
			calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
			calendar2.setTime(nowDate);
			calendar2.set(Calendar.MONTH, calendar2.getMaximum(Calendar.MONTH));
			calendar2.set(Calendar.DATE, calendar2.getMaximum(Calendar.DATE));
			Date date1 = calendar.getTime();
			Date date2 = calendar2.getTime();
			list = dwProjectTempService.findByDate(calendar.getTime(),calendar2.getTime());
		} catch (ParseException e) {
			System.out.println("日期转化错误");
		}
		
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		ServletActionContext.getRequest().setAttribute("sDate", sDate);
		return SUCCESS;
	}
	
	public String findAllData(){
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		if(sDate==null || "".equals(sDate)){
			sDate = "2012-01-01";
		}
		List<DwProjectTemp> list =null;
		try {
			Date nowDate = sdf.parse(sDate);
			Calendar calendar = Calendar.getInstance(),calendar2=Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.set(Calendar.MONTH, calendar.getMinimum(Calendar.MONTH));
			calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
			calendar2.setTime(nowDate);
			calendar2.set(Calendar.MONTH, calendar2.getMaximum(Calendar.MONTH));
			calendar2.set(Calendar.DATE, calendar2.getMaximum(Calendar.DATE));
			list = dwProjectTempService.findByDate(calendar.getTime(),calendar2.getTime());
		} catch (ParseException e) {
			System.out.println("日期转化错误");
		}
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	
}
