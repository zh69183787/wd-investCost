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

package com.wonders.ic.attach.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import org.springframework.util.Assert;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

public class AttachAction extends BaseAjaxAction {
	private Attach attach = new Attach();
	private AttachService attachService;
    private String attachIdList;
	
	public void setAttachService(AttachService attachService) {
		this.attachService = attachService;
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

	public String getAttachList(){
        Assert.notNull(attachIdList,"附件ID不能为空!");
        List<Attach> attachList = attachService.findByIds(attachIdList.split(","));
        ServletActionContext.getRequest().setAttribute("attachList", attachList);
        if(attachList!=null && attachList.size() > 0) {
            String jsonData = VOUtils.getJsonDataFromCollection(attachList);
            createJSonData(jsonData);
        }
        return AJAX;
    }

	public String findAttachByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.attach);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.attach, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = attachService.findAttachByPage(filter, start / size + 1,
				size);
		String json = VOUtils.getJsonDataFromPage(page, Attach.class);
		createJSonData(json);
		return AJAX;
	}


	/**
	 * 删除(逻辑)
	 * @return
	 */
	public String deleteAttach(){
		String id = ServletActionContext.getRequest().getParameter("id");
		this.attachService.deleteByIdOnLogical(id);
		
		return AJAX;
	}
	
	/**
	 * 下载附件
	 * @return
	 */
	public String downloadAttach(){
		
		String id = ServletActionContext.getRequest().getParameter("attachId");
		attach = attachService.findById(id);
		if(attach==null) return null;
		String fileName = attach.getFilename()+"."+attach.getFileextname();
			
	 	String fullPath = attach.getSavefilename();  
        InputStream is = null;
		try {
			is = new FileInputStream(fullPath);
			
			 int len=0;  
		        byte []buffers=new byte[1024];  
		        this.getServletResponse().setCharacterEncoding("utf-8");
		        this.getServletResponse().reset();
		        this.getServletResponse().setContentType("application/x-msdownload");  
		        this.getServletResponse().addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));  
		        OutputStream os = this.getServletResponse().getOutputStream(); 
		        //把文件内容通过输出流打印到页面上供下载  
		        while((len=is.read(buffers))!=-1){  
		            os.write(buffers, 0, len);  
		            os.flush();
		        }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		return null;
	}

    public String getAttachIdList() {
        return attachIdList;
    }

    public void setAttachIdList(String attachIdList) {
        this.attachIdList = attachIdList;
    }
}
