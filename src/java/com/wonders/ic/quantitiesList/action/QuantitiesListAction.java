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

package com.wonders.ic.quantitiesList.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
import com.wonders.ic.quantitiesList.service.QuantitiesListService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesListAction extends BaseAjaxAction {
	private QuantitiesList quantitiesList = new QuantitiesList();
	private QuantitiesListService quantitiesListService;
	private int size = 20;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	@Override
	public Object getModel() {
		return quantitiesList;
	}
	
	public QuantitiesList getQuantitiesList() {
		return quantitiesList;
	}

	public void setQuantitiesList(QuantitiesList quantitiesList) {
		this.quantitiesList = quantitiesList;
	}
	public void setQuantitiesListService(
			QuantitiesListService quantitiesListService) {
		this.quantitiesListService = quantitiesListService;
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
	 * 显示列表页面
	 * @return
	 */
	public String findQuantitiesListByPage() {
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(quantitiesList);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(quantitiesList,key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = quantitiesListService.findQuantitiesListByPage(filter, Integer.valueOf(pageNo), size);
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
		quantitiesList.setCreateDate(sdf.format(new Date()));
		quantitiesList.setOperateDate(sdf.format(new Date()));
		quantitiesList.setRemoved("0");
		quantitiesListService.saveQuantitiesList(quantitiesList);
		return SUCCESS;
	}
	
	/**
	 * 显示修改页面
	 */
	public String showEdit(){
		String id = ServletActionContext.getRequest().getParameter("id");
		quantitiesList = quantitiesListService.findById(id);
		return SUCCESS;
	}
	
	/**
	 * 保存新增
	 */
	public String saveEdit(){
		QuantitiesList tempQuantities = quantitiesListService.findById(quantitiesList.getId());
		if(tempQuantities!=null){
			try {
				BeanUtils.copyProperties(tempQuantities, quantitiesList);
				tempQuantities.setOperateDate(sdf.format(new Date()));
				quantitiesListService.update(tempQuantities);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细
	 */
	public String showView(){
		String id = ServletActionContext.getRequest().getParameter("id");
		quantitiesList = quantitiesListService.findById(id);
		
		return SUCCESS;
	}
	
	
	/**
	 * 删除
	 */
	public String delete(){
		String id = ServletActionContext.getRequest().getParameter("id");
		quantitiesListService.deleteById(id);
		return AJAX;
	}
	
	
	/**
	 * list页面保存新增
	 * @return
	 */
	public String saveOrUpdateOnPage(){
		String id = ServletActionContext.getRequest().getParameter("id");
		QuantitiesList ql = quantitiesListService.findById(id);
		if(ql==null){
			ql = new QuantitiesList();
			ql.setRemoved("0");
			ql.setCreateDate(sdf.format(new Date()));
			ql.setContractId(ServletActionContext.getRequest().getParameter("contractId"));
			ql.setContractNo(ServletActionContext.getRequest().getParameter("contractNo"));
		}
		long projectAmout;
		try {
			projectAmout = Long.valueOf(quantitiesList.getProjectAmount());
			ql.setProjectAmount(String.valueOf(projectAmout));
		} catch (NumberFormatException e) {
			return AJAX;
		}
		ql.setOperateDate(sdf.format(new Date()));
		quantitiesListService.update(ql);
		return AJAX;
	}
	

	
}
