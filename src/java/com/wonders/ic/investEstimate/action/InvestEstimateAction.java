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

package com.wonders.ic.investEstimate.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
import com.wonders.ic.investEstimate.service.InvestEstimateService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateAction extends BaseAjaxAction {
	private InvestEstimate investEstimate = new InvestEstimate();
	private InvestEstimateService investEstimateService;
	private int size = 20;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Object getModel() {
		return investEstimate;
	}

	public InvestEstimate getInvestEstimate() {
		return investEstimate;
	}

	public void setInvestEstimate(InvestEstimate investEstimate) {
		this.investEstimate = investEstimate;
	}

	public void setInvestEstimateService(InvestEstimateService investEstimateService) {
		this.investEstimateService = investEstimateService;
	}


	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}

	/**
	 *	 显示列表页面 
	 */
	public String findInvestEstimateByPage() {
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.investEstimate);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.investEstimate,key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		filter.put("removed", "0");

		Page page = investEstimateService.findInvestEstimateByPage(filter, Integer.valueOf(pageNo), size);
		ServletActionContext.getRequest().setAttribute("page", page);
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		return SUCCESS;
	}
	
	
	/**
	 * 显示新增页面
	 */
	public String showAdd(){
		
		return SUCCESS;
	}

	/**
	 * 保存新增
	 */
	public String saveAdd(){
		investEstimate.setCreateDate(sdf.format(new Date()));
		investEstimate.setOperateDate(sdf.format(new Date()));
		investEstimate.setRemoved("0");
		investEstimateService.saveAdd(investEstimate);
		
		return SUCCESS;
	}

	/**
	 * 显示编辑页面
	 */
	public String showEdit(){
		String id = ServletActionContext.getRequest().getParameter("id");
		investEstimate = investEstimateService.findById(id);
		return SUCCESS;
	}

	/**
	 * 保存编辑
	 */
	public String saveEdit(){
		InvestEstimate ie = investEstimateService.findById(investEstimate.getId());
		
		BeanUtils.copyProperties(investEstimate, ie);
		ie.setRemoved("0");
		ie.setOperateDate(sdf.format(new Date()));
		
		investEstimateService.update(ie);
		
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细
	 */
	public String showView(){
		String id = ServletActionContext.getRequest().getParameter("id");
		investEstimate = investEstimateService.findById(id);
		
		
		return SUCCESS;
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		
		return SUCCESS;
	}
	
	
	/**
	 * 在list页面新增或保存
	 */
	public String saveOrUpdateOnPage(){
		String id = ServletActionContext.getRequest().getParameter("id");
		InvestEstimate ie = investEstimateService.findById(id);
		boolean saveOrUpdate = false;	//true:save, false:update
		if(ie==null){
			ie = new InvestEstimate();
			ie.setCreateDate(sdf.format(new Date()));
			ie.setRemoved("0");
			ie.setProjectId(ServletActionContext.getRequest().getParameter("projectId"));
			ie.setProjectName(ServletActionContext.getRequest().getParameter("projectName"));
			saveOrUpdate = true;
		}
		ie.setProjectUnit(ServletActionContext.getRequest().getParameter("projectUnit"));
		ie.setProjectCount(ServletActionContext.getRequest().getParameter("projectCount"));
		ie.setOperateDate(sdf.format(new Date()));
		if(saveOrUpdate){
			investEstimateService.save(ie);
		}else{
			investEstimateService.saveOrUpdate(ie);
		}
		return AJAX;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
}
