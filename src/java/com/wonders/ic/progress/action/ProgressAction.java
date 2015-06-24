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

package com.wonders.ic.progress.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONObject;

import com.wonders.ic.progress.entity.bo.Progress;
import com.wonders.ic.progress.service.ProgressService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

public class ProgressAction extends BaseAjaxAction {
	private Progress progress = new Progress();
	private ProgressService progressService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void setProgressService(ProgressService progressService) {
		this.progressService = progressService;
	}
	
	@Override
	public Object getModel() {
		return progress;
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

	public String findProgressByPage() {
		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.progress);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.progress, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = progressService.findProgressByPage(filter,
				start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page, Progress.class);
		createJSonData(json);
		return AJAX;
	}

	/**
	 * 保存
	 */
	public String saveAddProgress(){
		
		progress.setCreateDate(sdf.format(new Date()));
		progress.setRemoved("0");
		progressService.save(progress);
		return AJAX;
	}
	
	/**
	 * 保存修改
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String saveEditProgress() throws IllegalAccessException, InvocationTargetException{
		String id = progress.getId();
		Progress originalProgress = progressService.findById(id);
		BeanUtils.copyProperties(originalProgress, progress);
		originalProgress.setUpdateDate(sdf.format(new Date()));
		originalProgress.setRemoved("0");
		progressService.update(originalProgress);
		
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteProgress(){
		String id = progress.getId();
		progressService.deleteById(id);
		
		return AJAX;
	}

	
}
