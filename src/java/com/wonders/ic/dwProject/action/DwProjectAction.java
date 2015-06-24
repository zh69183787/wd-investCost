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

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwProject.entity.bo.DwProject;
import com.wonders.ic.dwProject.service.DwProjectService;
import com.wonders.ic.export.ExportExcel;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-1-4
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectAction extends BaseAjaxAction {
	private DwProject dwProject = new DwProject();
	private DwProjectService dwProjectService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setDwProjectService(DwProjectService dwProjectService) {
		this.dwProjectService = dwProjectService;
	}

	//显示报表1
	public String showReport() throws UnsupportedEncodingException{
		String year = ServletActionContext.getRequest().getParameter("year");
		servletRequest.setAttribute("year", year);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(year==null || "".equals(year)){
			year = "2012";
		}
		List<DwProject> list =null;
		list = dwProjectService.findByYear(year);
		
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		ServletActionContext.getRequest().setAttribute("sDate", year);
		
		
		String export = servletRequest.getParameter("export");
		if(export!=null && "yes".equals(export)){
			List<Object[]> dataset = new ArrayList<Object[]>();
			String filename = year+"年度线路分专业立项情况统计表.xls";
			
			this.getServletResponse().setCharacterEncoding("utf-8");
	        this.getServletResponse().reset();
	        this.servletResponse.setContentType("octets/stream");  
	        this.getServletResponse().addHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
			
			
			
			for (int i = 0; i < list.size(); i++) {
				DwProject temp = list.get(i);
				Object[] params = new Object[8];
				
				params[0] = temp.getLineName();		//线路名称
				double all = 0.0;
				if(temp.getType1()!=null && !"".equals(temp.getType1())){
					params[1] = getFormattedMoney(temp.getType1());
					all += Double.valueOf(getFormattedMoney(temp.getType1()));
				}
				if(temp.getType2()!=null && !"".equals(temp.getType2())){
					params[2] = getFormattedMoney(temp.getType2());
					all += Double.valueOf(getFormattedMoney(temp.getType2()));
				}
				if(temp.getType3()!=null && !"".equals(temp.getType3())){
					params[3] = getFormattedMoney(temp.getType3());
					all += Double.valueOf(getFormattedMoney(temp.getType3()));
				}
				if(temp.getType4()!=null && !"".equals(temp.getType4())){
					params[4] = getFormattedMoney(temp.getType4());
					all += Double.valueOf(getFormattedMoney(temp.getType4()));
				}
				if(temp.getType5()!=null && !"".equals(temp.getType5())){
					params[5] = getFormattedMoney(temp.getType5());
					all += Double.valueOf(getFormattedMoney(temp.getType5()));
				}
				if(temp.getType6()!=null && !"".equals(temp.getType6())){
					params[6] = getFormattedMoney(temp.getType6());
					all += Double.valueOf(getFormattedMoney(temp.getType6()));
				}
				params[7] = getFormattedMoney(all+"");
				dataset.add(params);
			}
			String[] headers = {"线路/专业","车辆(万元)","供电(万元)","通号(万元)","工务(万元)","基地(万元)","车站机电(万元)","合计(万元)"};
			short[] width = {10000,5000,5000,5000,5000,5000,5000,5000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel((year+"年度线路分专业立项情况统计表"),(year+"年度线路分专业立项情况统计表"), headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		
		return SUCCESS;
	}
	
	public String findAllData(){
		String year = ServletActionContext.getRequest().getParameter("year");
		if(year==null || "".equals(year)){
			year = "2012";
		}
		List<DwProject> list =null;
		list = dwProjectService.findByYear(year);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	//得到保留6位小数后的字符串
	public String getFormattedMoney(String money){
		if(money==null || "".equals(money)){
			return null;
		}
		Double result = 0d;
		try {
			result = Double.valueOf(money);
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.000000");
		return df.format(result);
	}
}
