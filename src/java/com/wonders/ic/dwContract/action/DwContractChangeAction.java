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

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwContract.entity.bo.DwContractChange;
import com.wonders.ic.dwContract.service.DwContractChangeService;
import com.wonders.ic.export.ExportExcel;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-6
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractChangeAction extends BaseAjaxAction {
	private DwContractChange dwContractChange = new DwContractChange();
	private DwContractChangeService dwContractChangeService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setDwContractChangeService(
			DwContractChangeService dwContractChangeService) {
		this.dwContractChangeService = dwContractChangeService;
	}
	
	//显示报表7
	public String showReport7() throws UnsupportedEncodingException{
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String eDate = ServletActionContext.getRequest().getParameter("eDate");
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		String projectType = servletRequest.getParameter("projectType");
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		List<DwContractChange> list = dwContractChangeService.findByDate(projectNo,projectName,sDate,eDate,projectType);
		
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		ServletActionContext.getRequest().setAttribute("sDate", sDate);
		ServletActionContext.getRequest().setAttribute("eDate", eDate);
		ServletActionContext.getRequest().setAttribute("projectName", projectName);
		ServletActionContext.getRequest().setAttribute("projectNo", projectNo);
		ServletActionContext.getRequest().setAttribute("projectType", projectType);
		
		
		String export = servletRequest.getParameter("export"); 
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			String filename = "合同变更总体情况.xls";
			
			this.getServletResponse().setCharacterEncoding("utf-8");
	        this.getServletResponse().reset();
	        this.servletResponse.setContentType("octets/stream");  
	        this.getServletResponse().addHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
			
			
			
			for (int i = 0; i < list.size(); i++) {
				DwContractChange temp = list.get(i);
				Object[] params = new Object[7];
				
				params[0] = temp.getProjectName();		//项目名称
				params[1] = temp.getContractName();		//合同名称
				params[2] = temp.getPartyBCompany();	//乙方单位
				params[3] = temp.getContractPrice();	//合同金额
				params[4] = temp.getChangeMoney();		//变更金额
				params[5] = temp.getChangeCounts();		//变更次数
				params[6] = temp.getChangeReason();		//变更原因
				
				dataset.add(params);
			}
			
			String[] headers = {"项目名称","合同名称","乙方单位","合同金额(万元)","变更金额(万元)","变更次数","变更原因"};
			short[] width = {10000,10000,10000,10000,10000,5000,5000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同变更总体情况","合同变更总体情况", headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		
		return SUCCESS;
	}
}
