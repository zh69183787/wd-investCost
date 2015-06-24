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

package com.wonders.ic.investEstimateSubject.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.export.ExportExcel;
import com.wonders.ic.investEstimate.entity.bo.InvestEstimate;
import com.wonders.ic.investEstimate.service.InvestEstimateService;
import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubjectWbsBo;
import com.wonders.ic.investEstimateSubject.service.InvestEstimateSubjectService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wonders.ic.quantitiesSubject.service.QuantitiesSubjectService;
import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wonders.ic.wbs.service.WbsService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateSubjectAction extends BaseAjaxAction {
	private WbsService wbsService;
	private InvestEstimateSubject investEstimateSubject = new InvestEstimateSubject();
	private InvestEstimateSubjectService investEstimateSubjectService;
	private ProjectService projectService;
	private InvestEstimateService investEstimateService;
	private ContractService contractService;
	private QuantitiesSubjectService quantitiesSubjectService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	public Object getModel() {
		return investEstimateSubject;
	}
	public InvestEstimateSubject getInvestEstimateSubject() {
		return investEstimateSubject;
	}
	public void setInvestEstimateSubject(InvestEstimateSubject investEstimateSubject) {
		this.investEstimateSubject = investEstimateSubject;
	}
	public void setInvestEstimateSubjectService(InvestEstimateSubjectService investEstimateSubjectService) {
		this.investEstimateSubjectService = investEstimateSubjectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	public void setWbsService(WbsService wbsService) {
		this.wbsService = wbsService;
	}
	public void setQuantitiesSubjectService(
			QuantitiesSubjectService quantitiesSubjectService) {
		this.quantitiesSubjectService = quantitiesSubjectService;
	}
	public InvestEstimateService getInvestEstimateService() {
		return investEstimateService;
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
	 * 显示列表页面
	 */
	@SuppressWarnings("unchecked")
	public String findInvestEstimateSubjectByPage() {
		String type = ServletActionContext.getRequest().getParameter("type"); 
		String projectId = ServletActionContext.getRequest().getParameter("projectId");
		if(projectId==null || "".equals(projectId)){
			if("view".equals(type)){
				return "view";
			}
			return SUCCESS;
		}
		Project project = projectService.findProjectById(projectId);
		ServletActionContext.getRequest().setAttribute("project", project);
		if(project==null) return  SUCCESS;
		InvestEstimate ie = investEstimateService.findByProjectId(projectId);
		if(ie==null){
			ie = new InvestEstimate();
			ie.setCreateDate(sdf.format(new Date()));
			ie.setProjectId(projectId);
			ie.setRemoved("0");
			investEstimateService.save(ie);
		}
		
		List<Wbs> listWBS = wbsService.sortByWBS("1", projectId);//"1"为投资概算科目
		List<Wbs> specialListWBS = wbsService.findSpecialRows("1", projectId);//"1"为投资概算科目
		if(specialListWBS!=null&&specialListWBS.size()>0&&listWBS!=null&&listWBS.size()>0){
			for(int i=0;i<specialListWBS.size();i++){
				for(int j=0;j<listWBS.size();j++){
					if(specialListWBS.get(i).getParentId().equals(listWBS.get(j).getParentId())&&(long)specialListWBS.get(i).getWbsOrder()==(long)listWBS.get(j).getWbsOrder()&&"1".equals(listWBS.get(j).getType())){
						listWBS.add(j+1, specialListWBS.get(i));
						break;
					}
				}
			}
		}
		List<InvestEstimateSubject> list = new ArrayList<InvestEstimateSubject>();
		List<String> signedContractList = new ArrayList<String>();		//ycl添加，已签合同价,type=view时查询
		
		if("view".equals(type)){
			this.compute();
			if(listWBS!=null&&listWBS.size()>0){
				double sum1 = 0;
				double sum2 = 0;
				double sum3 = 0;
				double sum4 = 0;
				double sum5 = 0;
				for(int i=0;i<listWBS.size();i++){
					list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
					signedContractList.add(getShowedMoney(contractService.findSumOfContractPriceByInvestEstimateId(listWBS.get(i).getNodeId())));//已签合同价钱
					if((long)listWBS.get(i).getWbsLevel()==(long)1&&"1".equals(listWBS.get(i).getType())){
						sum1 += Double.parseDouble(list.get(i).getEstimateDecideFirst());
						sum2 += Double.parseDouble(list.get(i).getEstimateDecideSecond());
						sum3 += Double.parseDouble(list.get(i).getEstimateDecideThird());
						sum4 += Double.parseDouble(list.get(i).getEstimateDecideOther());
						sum5 += Double.parseDouble(list.get(i).getEstimateDecideSum());
					}
					if("3".equals(list.get(i).getDataType())){
						list.get(i).setEstimateDecideFirst(getShowedMoney(String.valueOf(sum1)));
						list.get(i).setEstimateDecideSecond(getShowedMoney(String.valueOf(sum2)));
						list.get(i).setEstimateDecideThird(getShowedMoney(String.valueOf(sum3)));
						list.get(i).setEstimateDecideOther(getShowedMoney(String.valueOf(sum4)));
						list.get(i).setEstimateDecideSum(getShowedMoney(String.valueOf(sum5)));
					}
				}
				/*for(int i=0;i<listWBS.size();i++){
					list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
					signedContractList.add(getFormattedMoney(contractService.findSumOfContractPriceByInvestEstimateId(listWBS.get(i).getNodeId())));
				}*/
			}
		}else{
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;
			double sum5 = 0;
			for(int i=0;i<listWBS.size();i++){
				list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
				if((long)listWBS.get(i).getWbsLevel()==(long)1&&"1".equals(listWBS.get(i).getType())){
					sum1 += Double.parseDouble(list.get(i).getEstimateDecideFirst());
					sum2 += Double.parseDouble(list.get(i).getEstimateDecideSecond());
					sum3 += Double.parseDouble(list.get(i).getEstimateDecideThird());
					sum4 += Double.parseDouble(list.get(i).getEstimateDecideOther());
					sum5 += Double.parseDouble(list.get(i).getEstimateDecideSum());
				}
				if("3".equals(list.get(i).getDataType())){
					list.get(i).setEstimateDecideFirst(getShowedMoney(String.valueOf(sum1)));
					list.get(i).setEstimateDecideSecond(getShowedMoney(String.valueOf(sum2)));
					list.get(i).setEstimateDecideThird(getShowedMoney(String.valueOf(sum3)));
					list.get(i).setEstimateDecideOther(getShowedMoney(String.valueOf(sum4)));
					list.get(i).setEstimateDecideSum(getShowedMoney(String.valueOf(sum5)));
				}
			}
		}
			
		String ifExport = this.servletRequest.getParameter("ifExport");
		if ("yes".equals(ifExport)) {
			
			List<Object[]> datasets = new ArrayList<Object[]>();
			//遍历List将需要导出的字段放入到datasets里面
			for (int i = 0; i < listWBS.size(); i++) {
					Object[] params = new Object[10];
					if ("1".equals(list.get(i).getDataType())) {
						params[0] = listWBS.get(i).getWbsId();
					}
					params[1] = list.get(i).getSubjectName();
					params[2] = list.get(i).getProjectAmountUnit();
					params[3] = list.get(i).getProjectAmount();
					params[4] = getShowedMoney(list.get(i).getEstimateDecideFirst());
					params[5] = getShowedMoney(list.get(i).getEstimateDecideSecond());
					params[6] = getShowedMoney(list.get(i).getEstimateDecideThird());
					params[7] = getShowedMoney(list.get(i).getEstimateDecideOther());
					params[8] = getShowedMoney(list.get(i).getEstimateDecideSum());
					params[9] = getShowedMoney(list.get(i).getEconomicIndicator());
					datasets.add(params);
			}
			this.servletResponse.setContentType("octets/stream");  
			try {
				String downloadFileName = project.getProjectName()+"-概算科目-"+sdf2.format(new Date())+".xls";
				this.servletResponse.addHeader("Content-Disposition","attachment;filename="+new String(downloadFileName.getBytes("gb2312"), "ISO8859-1" ));
				
				String[] headers = {"WBS编码","科目名称","单位","数量","建筑工程批复(万元)","安装工程批复(万元)","设备工器具批复(万元)","其他费用批复(万元)","概算批复值(合计)(万元)","经济指标(单价)(万元)"};
				short[] width = {10000,10000,7000,5000,5000,5000,5000,5000,5000,5000};

				try {
					OutputStream out = this.servletResponse.getOutputStream();
					ExportExcel ee = new ExportExcel();
					ee.exportExcel("投资概算科目导出", headers, datasets, out,width); 
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} 
		} 
		
		servletRequest.setAttribute("signedContractList", signedContractList);
		ServletActionContext.getRequest().setAttribute("InvestEstimateSubjectList", list);
		ServletActionContext.getRequest().setAttribute("listWBS", listWBS);
		
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		
		InvestEstimate investEstimate = investEstimateService.findByProjectId(projectId);
		ServletActionContext.getRequest().setAttribute("investEstimate", investEstimate);
		
		String hideNodes = ServletActionContext.getRequest().getParameter("hideNodes"); 
		ServletActionContext.getRequest().setAttribute("hideNodes", hideNodes);
		
		String targetNode = ServletActionContext.getRequest().getParameter("targetNode"); 
		ServletActionContext.getRequest().setAttribute("targetNode", targetNode);
		 
		if("view".equals(type)){
			return "view";
		}
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细页面
	 */
	public String showView(){
		String id = ServletActionContext.getRequest().getParameter("id");
		investEstimateSubject = investEstimateSubjectService.findEstimateSubjectById(id);
		return SUCCESS;
	}
	
	/**
	 * 批量删除
	 */
	public String deleteAll(){
		
		String id = ServletActionContext.getRequest().getParameter("id");
		String projectId = ServletActionContext.getRequest().getParameter("projectId");
		String parentId = ServletActionContext.getRequest().getParameter("parentId");
		
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(projectId)){
			createJSonData("删除失败,请稍后再试！");
			return AJAX;
		}
		
		List<Wbs> wbsList = wbsService.findAllWbsHierarchy("1", projectId, id);
		
		investEstimateSubjectService.deleteAllSubjectAndWbsLogical(wbsList);
		
		if(wbsList!=null && wbsList.size()>0){
			for(int i=0; i<wbsList.size(); i++){
				quantitiesSubjectService.updateInvestEstimateIdAndNameNull(wbsList.get(i).getNodeId());
			}
		}
		
		
		//investEstimateSubjectService.deleteByIdsOnLogical(idArray);	//删除科目

		return AJAX;
	}
	
	/**
	 * list页面上保存新增
	 * @return
	 */
	public String saveAddOnPage(){
		
		String parentId = ServletActionContext.getRequest().getParameter("parentId");	//父节点,需要前台传值,先手动传
		String wbsId = ServletActionContext.getRequest().getParameter("wbsId");			//wbsId
		String level = ServletActionContext.getRequest().getParameter("level");			
		String order = ServletActionContext.getRequest().getParameter("order");		
		String ifHasSpecial = ServletActionContext.getRequest().getParameter("ifHasSpecial");
		String specialOrder = ServletActionContext.getRequest().getParameter("specialOrder");	
		
		String jsonData;
		if(wbsId!=null && !"".equals(wbsId) && level!=null && !"".equals(level) && order!=null && !"".equals(order)){
			investEstimateSubject.setRemoved("0");
			investEstimateSubject.setCreateDate(sdf.format(new Date()));
			investEstimateSubject.setUpdateDate(sdf.format(new Date()));
			
			investEstimateSubject.setEstimateDecideFirst(getFormattedMoney(investEstimateSubject.getEstimateDecideFirst()));
			investEstimateSubject.setEstimateDecideSecond(getFormattedMoney(investEstimateSubject.getEstimateDecideSecond()));
			investEstimateSubject.setEstimateDecideThird(getFormattedMoney(investEstimateSubject.getEstimateDecideThird()));
			investEstimateSubject.setEstimateDecideOther(getFormattedMoney(investEstimateSubject.getEstimateDecideOther()));
			
			Double sum = getDoubleValue(investEstimateSubject.getEstimateDecideFirst())+getDoubleValue(investEstimateSubject.getEstimateDecideSecond())
			+getDoubleValue(investEstimateSubject.getEstimateDecideThird())+getDoubleValue(investEstimateSubject.getEstimateDecideOther());
			
			investEstimateSubject.setEstimateDecideSum(getFormattedMoney(sum.toString()));
			
			double amount = Double.valueOf(investEstimateSubject.getProjectAmount());
			investEstimateSubject.setProjectAmount(String.valueOf(amount));
			if(amount!=0){
				double result = sum / amount;
				investEstimateSubject.setEconomicIndicator(getFormattedMoney(String.valueOf(result)));
			}else{
				investEstimateSubject.setEconomicIndicator(getFormattedMoney(investEstimateSubject.getEstimateDecideSum()));
			}
			investEstimateSubjectService.saveEstimateSubject(investEstimateSubject); 	//保存科目
			Wbs wbs = investEstimateSubjectService.saveAddOnPage(investEstimateSubject, parentId, wbsId, level, order,ifHasSpecial,specialOrder);
			
			//解决页面保存后刷新问题
			InvestEstimateSubjectWbsBo bo = new InvestEstimateSubjectWbsBo();
			bo.setInvestEstimateSubject(investEstimateSubject);
			bo.setWbs(wbs);
			jsonData = VOUtils.getJsonData(bo);
		}else{
			jsonData = null;
		}
		
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * list页面上保存修改 
	 */
	public String saveEditOnPage(){
		InvestEstimateSubject original = investEstimateSubjectService.findById(investEstimateSubject.getId());
		
		if(original!=null){
			if(!original.getSubjectName().equals(investEstimateSubject.getSubjectName())){
				quantitiesSubjectService.updateInvestEstimateName(investEstimateSubject.getId(), investEstimateSubject.getSubjectName());
			}
		}
		if(original==null)	return AJAX;
		
		original.setUpdateDate(sdf.format(new Date()));
		original.setSubjectName(investEstimateSubject.getSubjectName());
		original.setProjectAmount(Double.valueOf(investEstimateSubject.getProjectAmount()).toString());
		original.setProjectAmountUnit(investEstimateSubject.getProjectAmountUnit());
		
		double decide1 ,decide2,decide3,decide4,sum;
		decide1 = getDoubleValue(investEstimateSubject.getEstimateDecideFirst());
		decide2 = getDoubleValue(investEstimateSubject.getEstimateDecideSecond());
		decide3 = getDoubleValue(investEstimateSubject.getEstimateDecideThird());
		decide4 = getDoubleValue(investEstimateSubject.getEstimateDecideOther());
		
		sum = decide1 + decide2 + decide3 + decide4;
		
		original.setEstimateDecideFirst(getFormattedMoney(String.valueOf(decide1)));
		original.setEstimateDecideSecond(getFormattedMoney(String.valueOf(decide2)));
		original.setEstimateDecideThird(getFormattedMoney(String.valueOf(decide3)));
		original.setEstimateDecideOther(getFormattedMoney(String.valueOf(decide4)));
		original.setEstimateDecideSum(getFormattedMoney(String.valueOf(sum)));
		double numberAll = getDoubleValue(investEstimateSubject.getProjectAmount());
		if(numberAll !=0){	//如果数量不为0
			original.setEconomicIndicator(getFormattedMoney(String.valueOf(sum/numberAll)));
		}else{
			original.setEconomicIndicator(getFormattedMoney(original.getEstimateDecideSum()));
		}
		investEstimateSubjectService.saveEstimateSubject(original);
		
		return AJAX;
	}
	
	/**
	 * 重新计算按钮
	 * @return
	 */
	public String compute(){
		String projectId = ServletActionContext.getRequest().getParameter("projectId");
		//Project project = projectService.findProjectById(projectId);
		String result= investEstimateSubjectService.compute("1", projectId);		//计算普通行
		
		String specilDataIds = ServletActionContext.getRequest().getParameter("specilDataIds");
		String specilDecides = ServletActionContext.getRequest().getParameter("decides");
		String[] ids ,decides;
		
		if(specilDataIds!=null && !"".equals(specilDataIds) && specilDecides!=null && !"".equals(specilDecides)){
			ids = specilDataIds.split(",");
			decides = specilDecides.split("-");
			investEstimateSubjectService.updateSpecilData(ids, decides);
		}
		
		return AJAX;
	}
	
	/**
	 * 查询相关联的合同
	 * @return
	 */
	public String showAllContract(){
		String estimateSubjectId = ServletActionContext.getRequest().getParameter("estimateSubjectId");
		String projectId = ServletActionContext.getRequest().getParameter("projectId");
		
		List<Object[]> contractList = contractService.findAllByProjectIdAndQuantitiesInEstimateSubject(projectId, estimateSubjectId);
		List<String> contractIdList = null;
		if(contractList!=null){
			contractIdList = new ArrayList<String>();
			for(int i=0; i<contractList.size(); i++){
				contractIdList.add(contractList.get(i)[0].toString());
			}
		}
		List<String> quantitiesTotalPriceList = quantitiesSubjectService.findSumOfTotalPriceByContractIdAndEstimateSubjectId(contractIdList, estimateSubjectId);
		
		List<String[]> result = null;
		if(contractList!=null && quantitiesTotalPriceList!=null &&contractIdList.size()==quantitiesTotalPriceList.size()){
			result = new ArrayList<String[]>();
			for(int i=0; i<contractList.size(); i++){
				double price = Double.valueOf(quantitiesTotalPriceList.get(i))/10000;		//得到的数据单位是(元)，除以10000,转化成(万元)
				result.add(
					new String[]{contractList.get(i)[0].toString(),contractList.get(i)[1].toString(),
							contractList.get(i)[2].toString(),contractList.get(i)[3].toString(),String.valueOf(price)});
			}
		}
		String jsonData = VOUtils.getJsonDataFromCollection(result);
		createJSonData(jsonData);
		return AJAX;
	}
	
	
	public String findInvestEstimateSubjectByPageForBind() {
		
		String type = ServletActionContext.getRequest().getParameter("type"); 
		String projectId = ServletActionContext.getRequest().getParameter("projectId");
		
		InvestEstimate ie = investEstimateService.findByProjectId(projectId);
		if(ie==null){
			ie = new InvestEstimate();
			ie.setCreateDate(sdf.format(new Date()));
			ie.setProjectId(projectId);
			ie.setRemoved("0");
			investEstimateService.save(ie);
		}
		
		
		List<Wbs> listWBS = wbsService.sortByWBS("1", projectId);//"1"为投资概算科目
		List<Wbs> specialListWBS = wbsService.findSpecialRows("1", projectId);//"1"为投资概算科目
		if(specialListWBS!=null&&specialListWBS.size()>0&&listWBS!=null&&listWBS.size()>0){
			for(int i=0;i<specialListWBS.size();i++){
				for(int j=0;j<listWBS.size();j++){
					if(specialListWBS.get(i).getParentId().equals(listWBS.get(j).getParentId())&&(long)specialListWBS.get(i).getWbsOrder()==(long)listWBS.get(j).getWbsOrder()&&"1".equals(listWBS.get(j).getType())){
						listWBS.add(j+1, specialListWBS.get(i));
						break;
					}
				}
			}
		}
		List<InvestEstimateSubject> list = new ArrayList<InvestEstimateSubject>();
		List<String> signedContractList = new ArrayList<String>();		//ycl添加，已签合同价,type=view时查询
		
		if("view".equals(type)){
			if(listWBS!=null&&listWBS.size()>0){
				double sum1 = 0;
				double sum2 = 0;
				double sum3 = 0;
				double sum4 = 0;
				double sum5 = 0;
				for(int i=0;i<listWBS.size();i++){
					list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
					signedContractList.add(getFormattedMoney(contractService.findSumOfContractPriceByInvestEstimateId(listWBS.get(i).getNodeId())));//已签合同价钱
					if((long)listWBS.get(i).getWbsLevel()==(long)1&&"1".equals(listWBS.get(i).getType())){
						sum1 += Double.parseDouble(list.get(i).getEstimateDecideFirst());
						sum2 += Double.parseDouble(list.get(i).getEstimateDecideSecond());
						sum3 += Double.parseDouble(list.get(i).getEstimateDecideThird());
						sum4 += Double.parseDouble(list.get(i).getEstimateDecideOther());
						sum5 += Double.parseDouble(list.get(i).getEstimateDecideSum());
					}
					if("3".equals(list.get(i).getDataType())){
						list.get(i).setEstimateDecideFirst(getFormattedMoney(String.valueOf(sum1)));
						list.get(i).setEstimateDecideSecond(getFormattedMoney(String.valueOf(sum2)));
						list.get(i).setEstimateDecideThird(getFormattedMoney(String.valueOf(sum3)));
						list.get(i).setEstimateDecideOther(getFormattedMoney(String.valueOf(sum4)));
						list.get(i).setEstimateDecideSum(getFormattedMoney(String.valueOf(sum5)));
					}
				}
				/*for(int i=0;i<listWBS.size();i++){
					list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
					signedContractList.add(getFormattedMoney(contractService.findSumOfContractPriceByInvestEstimateId(listWBS.get(i).getNodeId())));
				}*/
			}
		}else{
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;
			double sum5 = 0;
			for(int i=0;i<listWBS.size();i++){
				list.add(investEstimateSubjectService.findById(listWBS.get(i).getNodeId()));
				if((long)listWBS.get(i).getWbsLevel()==(long)1&&"1".equals(listWBS.get(i).getType())){
					sum1 += Double.parseDouble(list.get(i).getEstimateDecideFirst());
					sum2 += Double.parseDouble(list.get(i).getEstimateDecideSecond());
					sum3 += Double.parseDouble(list.get(i).getEstimateDecideThird());
					sum4 += Double.parseDouble(list.get(i).getEstimateDecideOther());
					sum5 += Double.parseDouble(list.get(i).getEstimateDecideSum());
				}
				if("3".equals(list.get(i).getDataType())){
					list.get(i).setEstimateDecideFirst(getFormattedMoney(String.valueOf(sum1)));
					list.get(i).setEstimateDecideSecond(getFormattedMoney(String.valueOf(sum2)));
					list.get(i).setEstimateDecideThird(getFormattedMoney(String.valueOf(sum3)));
					list.get(i).setEstimateDecideOther(getFormattedMoney(String.valueOf(sum4)));
					list.get(i).setEstimateDecideSum(getFormattedMoney(String.valueOf(sum5)));
				}
			}
		}
			
		String ifExport = this.servletRequest.getParameter("ifExport");
		if ("yes".equals(ifExport)) {
			
			List<Object[]> datasets = new ArrayList<Object[]>();
			//遍历List将需要导出的字段放入到datasets里面
			for (int i = 0; i < listWBS.size(); i++) {
					Object[] params = new Object[10];
					if ("1".equals(list.get(i).getDataType())) {
						params[0] = listWBS.get(i).getWbsId();
					}
					params[1] = list.get(i).getSubjectName();
					params[2] = list.get(i).getProjectAmountUnit();
					params[3] = list.get(i).getProjectAmount();
					params[4] = list.get(i).getEstimateDecideFirst();
					params[5] = list.get(i).getEstimateDecideSecond();
					params[6] = list.get(i).getEstimateDecideThird();
					params[7] = list.get(i).getEstimateDecideOther();
					params[8] = list.get(i).getEstimateDecideSum();
					params[9] = list.get(i).getEconomicIndicator();
					
					datasets.add(params);
			}
			
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=InvestEstimateSubject.xls"); 
			
			String[] headers = {"WBS编码","科目名称","单位","数量","建筑工程批复(万元)","安装工程批复(万元)","设备工器具批复(万元)","其他费用批复(万元)","概算批复值(合计)(万元)","经济指标(单价)(万元)"};
			short[] width = {10000,10000,7000,5000,5000,5000,5000,5000,5000,5000};

			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("投资概算科目导出", headers, datasets, out,width); 
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} 
		
		servletRequest.setAttribute("signedContractList", signedContractList);
		ServletActionContext.getRequest().setAttribute("InvestEstimateSubjectList", list);
		ServletActionContext.getRequest().setAttribute("listWBS", listWBS);
		
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		
		Project project = projectService.findProjectById(projectId);
		ServletActionContext.getRequest().setAttribute("project", project);
		
		InvestEstimate investEstimate = investEstimateService.findByProjectId(projectId);
		ServletActionContext.getRequest().setAttribute("investEstimate", investEstimate);
		
		String hideNodes = ServletActionContext.getRequest().getParameter("hideNodes"); 
		ServletActionContext.getRequest().setAttribute("hideNodes", hideNodes);
		
		String targetNode = ServletActionContext.getRequest().getParameter("targetNode"); 
		ServletActionContext.getRequest().setAttribute("targetNode", targetNode);
		 
		return SUCCESS;
	}
	
	
	
	
	public String getStringByFormatDouble(double number,int digit){
		if(digit<0) return String.valueOf(number);
		String formatter = "0.";
		for(int i=0; i<digit; i++){
			formatter += "0";
		}
		DecimalFormat df = new DecimalFormat(formatter);
		String result = df.format(number);
		while (result.endsWith("00")) {
			result = result.substring(0,result.length()-1);
		}
		return result;
	}	
	
	//转化为double
	public double getDoubleValue(Object object){
		if(object==null)	return 0;
		try {
			double d = Double.valueOf(object.toString());
			return d;
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	//得到保留6位小数后的字符串
	public String getFormattedMoney(String money){
		if(money==null || "".equals(money)){
			money = "0";
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
	
	public String getShowedMoney(String money){
		if(money==null || "".equals(money)){
			money = "0";
		}
		Double result = 0d;
		try {
			result = Double.valueOf(money);
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.0000");
		if(result==0) return "0";
		return df.format(result);
	}
	
	/**
	 * list页面上保存修改 
	 */
	public String saveObjectControlValue(){
		InvestEstimateSubject original = investEstimateSubjectService.findById(investEstimateSubject.getId());
		
		if(original==null)	return AJAX;
		
		original.setUpdateDate(sdf.format(new Date()));
		
		if("".equals(investEstimateSubject.getObjectControlValue())){
			original.setObjectControlValue("");
		}else{
			double decide1 ,adjust;
			decide1 = getDoubleValue(investEstimateSubject.getObjectControlValue());
			adjust = getDoubleValue(investEstimateSubject.getObjectAdjustValue());
			original.setObjectControlValue(getFormattedMoney(String.valueOf(decide1)));
			original.setObjectAdjustValue(getFormattedMoney(String.valueOf(adjust)));
		}
		
		investEstimateSubjectService.saveEstimateSubject(original);
		
		return AJAX;
	}
	
	//新增特殊行
	public void saveAddOnPageSpecial(){
		
		String parentId = ServletActionContext.getRequest().getParameter("parentId");	//父节点,需要前台传值,先手动传
		String level = ServletActionContext.getRequest().getParameter("level");			
		String order = ServletActionContext.getRequest().getParameter("order");	
		String wbsId = ServletActionContext.getRequest().getParameter("wbsId");	
		String specialOrder = ServletActionContext.getRequest().getParameter("specialOrder");	
		
		if( level!=null && !"".equals(level) && order!=null && !"".equals(order)){
			investEstimateSubjectService.saveAddOnPageSpecial(investEstimateSubject, parentId,wbsId, level, order,specialOrder);
		}
	}
	
	//修改特殊行
	public void saveEditOnPageSpecial(){
		String id = ServletActionContext.getRequest().getParameter("id");
		String subjectName = ServletActionContext.getRequest().getParameter("subjectName");
		if(id!=null){
			InvestEstimateSubject bo = investEstimateSubjectService.findById(id);
			bo.setSubjectName(subjectName);
			investEstimateSubjectService.saveEstimateSubject(bo);
		}
	}
	
	//删除特殊行
	public void deleteSpecial(){
		String id = ServletActionContext.getRequest().getParameter("id");
		String pro_id = ServletActionContext.getRequest().getParameter("pro_id");
		if(id!=null){
			InvestEstimateSubject bo = investEstimateSubjectService.findById(pro_id);
			bo.setRemoved("1");
			investEstimateSubjectService.saveEstimateSubject(bo);
			List<Wbs> wbsList = wbsService.findAllWbsHierarchy("1", bo.getProjectId(), id);
			investEstimateSubjectService.deleteWbsSpecial(wbsList);
		}
	}
	
	
	
	
	
	
	

	

}
