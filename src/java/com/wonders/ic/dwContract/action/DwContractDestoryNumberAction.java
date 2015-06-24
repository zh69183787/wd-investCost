
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wonders.ic.dwContract.entity.bo.DwContractDestoryNumber;
import com.wonders.ic.dwContract.service.DwContractDestoryNumberService;
import com.wonders.ic.export.ExportExcel;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2013-3-8
 * @author modify by $Author$
 * @since 1.0
 */
 
public class DwContractDestoryNumberAction extends BaseAjaxAction { 
	private DwContractDestoryNumber dwContractDestoryNumber = new DwContractDestoryNumber();
	private DwContractDestoryNumberService dwContractDestoryNumberService;
	public void setDwContractDestoryNumberService(
			DwContractDestoryNumberService dwContractDestoryNumberService) {
		this.dwContractDestoryNumberService = dwContractDestoryNumberService;
	}
	
	public String showReport5() throws UnsupportedEncodingException{
		String projectName = servletRequest.getParameter("projectName");
		String projectNo = servletRequest.getParameter("projectNo");
		String projectType = servletRequest.getParameter("projectType");
		String contractSignedStart = servletRequest.getParameter("contractSignedStart");
		String contractSignedEnd = servletRequest.getParameter("contractSignedEnd");
		String contractStatus = servletRequest.getParameter("contractStatus");
		servletRequest.setAttribute("projectId", servletRequest.getParameter("projectId"));
		Map<String, String> filter = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(projectName)){
			filter.put("projectName", projectName);
			servletRequest.setAttribute("projectName", projectName);
		}
		if(StringUtils.isNotEmpty(projectNo)){
			filter.put("projectNo", projectNo);
			servletRequest.setAttribute("projectNo", projectNo);
		}
		if(StringUtils.isNotEmpty(projectType)){
			filter.put("projectType", projectType);
			servletRequest.setAttribute("projectType", projectType);
		}
		if(StringUtils.isNotEmpty(contractSignedStart)){
			filter.put("contractSignedStart", contractSignedStart);
			servletRequest.setAttribute("contractSignedStart", contractSignedStart);
		}
		if(StringUtils.isNotEmpty(contractSignedEnd)){
			filter.put("contractSignedEnd", contractSignedEnd);
			servletRequest.setAttribute("contractSignedEnd", contractSignedEnd);
		}
		if(StringUtils.isNotEmpty(contractStatus)){
			filter.put("contractStatus", contractStatus);
			servletRequest.setAttribute("contractStatus", contractStatus);
		}
		List<DwContractDestoryNumber> destoryNumberList = dwContractDestoryNumberService.findByFilter(filter); 
		servletRequest.setAttribute("destoryNumberList", destoryNumberList);
		
		
		
		
		String export = servletRequest.getParameter("export");
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			String filename = "运维合同销号情况统计表.xls";
			
			this.getServletResponse().setCharacterEncoding("utf-8");
	        this.getServletResponse().reset();
	        this.servletResponse.setContentType("octets/stream");  
	        this.getServletResponse().addHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
			
			
			
			for (int i = 0; i < destoryNumberList.size(); i++) {
				DwContractDestoryNumber temp = destoryNumberList.get(i);
				Object[] params = new Object[7];
				
				params[0] = temp.getContractNo();		//合同编号
				
				params[1] = temp.getContractName();		//合同名称
				params[2] = temp.getSupplier();			//承包商/供应商
				if(temp.getContractPrice()!=null){
					params[3] = getFormattedMoney(temp.getContractPrice()+"");	//合同价
				}else{
					params[3] = ""; 
				}
				if(temp.getFinalPrice()!=null){
					params[4] = getFormattedMoney(temp.getFinalPrice()+"");		//决算价
				}else{
					params[4] = "";
				}
				params[5] = temp.getContractSignedDate();	//合同签约时间
				if(temp.getContractStatus()!=null){
					if("1".equals(temp.getContractStatus())){
						params[6] = "实施中";				//合同状态
					}else if("3".equals(temp.getContractStatus())){
						params[6] = "已销号";
					}
				}
				dataset.add(params);
			}
			
			String[] headers = {"合同编号","合同名称","承包商/供应商","合同价(万元)","决算价(万元)","合同签约时间","合同状态"};
			short[] width = {10000,10000,10000,10000,10000,5000,5000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("运维合同销号情况统计表", headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		return SUCCESS;
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
	
