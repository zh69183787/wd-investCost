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

package com.wonders.ic.contractPlan.action;

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

import com.wonders.ic.contractPlan.entity.bo.ContractPlan;
import com.wonders.ic.contractPlan.service.ContractPlanService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractPlanAction extends BaseAjaxAction {
	private ContractPlan contractPlan = new ContractPlan();
	private ContractPlanService contractPlanService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int size = 20;

	@Override
	public Object getModel() {
		return contractPlan;
	}
	
	public ContractPlan getContractPlan() {
		return contractPlan;
	}
	public void setContractPlan(ContractPlan contractPlan) {
		this.contractPlan = contractPlan;
	}

	public void setContractPlanService(ContractPlanService contractPlanService) {
		this.contractPlanService = contractPlanService;
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
	 * 显示list页面
	 */
	public String findContractPlanByPage() {
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(contractPlan);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(contractPlan, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = contractPlanService.findContractPlanByPage(filter, Integer.valueOf(pageNo), size);
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
		contractPlan.setCreateDate(sdf.format(new Date()));
		contractPlan.setUpdateDate(sdf.format(new Date()));
		contractPlan.setRemoved("0");
		contractPlanService.saveContractPlan(contractPlan);
		return SUCCESS;
	}
	
	
	/**
	 * 显示详细页面
	 */
	public String showView(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contractPlan = contractPlanService.findById(id);
		return SUCCESS;
	}
	
	/**
	 * 显示修改页面
	 */
	public String showEdit(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contractPlan = contractPlanService.findById(id);
		return SUCCESS;
	}
	
	/**
	 * 保存修改
	 */
	public String saveEdit(){
		
		ContractPlan tempContractPlan = contractPlanService.findById(contractPlan.getId());
		BeanUtils.copyProperties(tempContractPlan, contractPlan);
		tempContractPlan.setUpdateDate(sdf.format(new Date()));
		contractPlanService.update(tempContractPlan);
		return SUCCESS;
	}


	/**
	 * 删除
	 */
	public String delete(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contractPlanService.deleteById(id);
		
		return AJAX;
	}
	
	
	
	



	
	
}
