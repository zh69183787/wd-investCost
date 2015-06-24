
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wonders.ic.dwProject.entity.bo.DwProjectInvestPay;
import com.wonders.ic.dwProject.service.DwProjectInvestPayService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author Administrator
 * @version $Revision$ 
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */
 
public class DwProjectInvestPayAction extends BaseAjaxAction { 
	private DwProjectInvestPay dwProjectInvestPay = new DwProjectInvestPay();
	private DwProjectInvestPayService dwProjectInvestPayService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

	public void setDwProjectInvestPayService(DwProjectInvestPayService dwProjectInvestPayService) {
		this.dwProjectInvestPayService = dwProjectInvestPayService;
	}
	
	//显示报表8 
	public String showReport8(){
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		String funcType = ServletActionContext.getRequest().getParameter("funcType");
		if(funcType==null){
			funcType = "2";
		}
		if("1".equals(funcType)){   //外部项目
			projectName = "上海轨道交通10号线一期工程";	//先写死
			projectNo = "2007-十号线-001";	//先写死
		}else if("2".equals(funcType)){   //内部项目
			if(projectName==null&&projectNo==null){
				projectName = "2012年委托运营管理项目（维保中心）";	//先写死
				projectNo = "2012-维保中心-001-成本内项目";	//先写死
			}
		}
		
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String eDate = ServletActionContext.getRequest().getParameter("eDate");
		int idate1 = 0;
		int idate2 = 0;
		List<DwProjectInvestPay> list = dwProjectInvestPayService.findByName8(projectNo, projectName,sDate,eDate);
		if(list!=null && list.size()>0){
			if(sDate==null || "".equals(sDate)){
				if(list.get(0).getActualPayDate()==null&&list.get(0).getPlanPayDate()==null){
					sDate = "";
				}else if(list.get(0).getActualPayDate()==null){
					sDate = sdf.format(list.get(0).getPlanPayDate());
				}else if(list.get(0).getPlanPayDate()==null){
					sDate = sdf.format(list.get(0).getActualPayDate());
				}else{
					idate1 =  Integer.parseInt(sdf1.format(list.get(0).getActualPayDate()));
					idate2 =  Integer.parseInt(sdf1.format(list.get(0).getPlanPayDate()));
					if(idate1<idate2){
						sDate = sdf.format(list.get(0).getActualPayDate());
					}else{
						sDate = sdf.format(list.get(0).getPlanPayDate());
					}
				}
			}
			if(eDate==null || "".equals(eDate)){
				if(list.get(list.size()-1).getActualPayDate()==null&&list.get(list.size()-1).getPlanPayDate()==null){
					eDate = "";
				}else if(list.get(list.size()-1).getActualPayDate()==null){
					eDate = sdf.format(list.get(list.size()-1).getPlanPayDate());
				}else if(list.get(list.size()-1).getPlanPayDate()==null){
					eDate = sdf.format(list.get(list.size()-1).getActualPayDate());
				}else{
					idate1 =  Integer.parseInt(sdf1.format(list.get(list.size()-1).getActualPayDate()));
					idate2 =  Integer.parseInt(sdf1.format(list.get(list.size()-1).getPlanPayDate()));
					if(idate1>idate2){
						eDate = sdf.format(list.get(list.size()-1).getActualPayDate());
					}else{
						eDate = sdf.format(list.get(list.size()-1).getPlanPayDate());
					}
				}
			}
		}
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		//ServletActionContext.getRequest().setAttribute("projectName", projectName);
		ServletActionContext.getRequest().setAttribute("projectName", projectName);
		ServletActionContext.getRequest().setAttribute("projectNo", projectNo);
		ServletActionContext.getRequest().setAttribute("sDate", sDate);
		ServletActionContext.getRequest().setAttribute("eDate", eDate);
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("funcType", funcType);
		return SUCCESS;
	}
	
	public String findAllData8(){
		String funcType = ServletActionContext.getRequest().getParameter("funcType");
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		if(funcType==null){
			funcType = "2";
		}
		if("1".equals(funcType)){   //外部项目
			projectName = "上海轨道交通10号线一期工程";	//先写死
			projectNo = "2007-十号线-001";	//先写死
		}else if("2".equals(funcType)){   //内部项目
			if(projectName==null&&projectNo==null){
				projectName = "2012年委托运营管理项目（维保中心）";	//先写死
				projectNo = "2012-维保中心-001-成本内项目";	//先写死
			}
		}
		
		String sDate = ServletActionContext.getRequest().getParameter("sDate");
		String eDate = ServletActionContext.getRequest().getParameter("eDate");
		
		List<DwProjectInvestPay> list = dwProjectInvestPayService.findByName8(projectNo, projectName,sDate,eDate);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 计算所有项目（被合同绑定过的）下的合同实际支付金额,
	 * 并存放在DW_PROJECT_INVEST_PAY表中,作为报表8的数据
	 */
	public String countAllPaysForProject(){
		dwProjectInvestPayService.updateCountAllPaysForProject();
		return SUCCESS;
	}
	
//	private String getNullValue(Date date){
//		if(date==null){
//			return "0";
//		}else{
//			return str;
//		}
//	}
//	
	
	
}
	
