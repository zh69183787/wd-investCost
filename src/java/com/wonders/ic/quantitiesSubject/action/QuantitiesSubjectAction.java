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

package com.wonders.ic.quantitiesSubject.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.export.ExportExcel;
import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wonders.ic.investEstimateSubject.service.InvestEstimateSubjectService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wonders.ic.quantitiesList.entity.bo.QuantitiesList;
import com.wonders.ic.quantitiesList.service.QuantitiesListService;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubjectWbsBo;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wonders.ic.quantitiesSubject.entity.bo.WbsEstimateSubject;
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

public class QuantitiesSubjectAction extends BaseAjaxAction {
	private QuantitiesSubject quantitiesSubject = new QuantitiesSubject();
	private QuantitiesSubjectService quantitiesSubjectService;
	private ContractService contractService;
	private ProjectService projectService;
	private QuantitiesListService quantitiesListService;
	private InvestEstimateSubjectService subjectService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private WbsService wbsService;
	
	public void setWbsService(WbsService wbsService) {
		this.wbsService = wbsService;
	}

	@Override
	public Object getModel() {
		return quantitiesSubject;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public void setQuantitiesSubjectService(QuantitiesSubjectService quantitiesSubjectService) {
		this.quantitiesSubjectService = quantitiesSubjectService;
	}
	
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setQuantitiesListService(QuantitiesListService quantitiesListService) {
		this.quantitiesListService = quantitiesListService;
	}

	public void setSubjectService(InvestEstimateSubjectService subjectService) {
		this.subjectService = subjectService;
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
	 * 	显示list页面
	 */
	public String findQuantitiesSubjectByPage() {
		if(quantitiesSubject.getContractId()==null || "".equals(quantitiesSubject.getContractId())){
			return SUCCESS;
		}
		String contractId = ServletActionContext.getRequest().getParameter("contractId");
		
		QuantitiesList quantitiesList = quantitiesListService.findAllByContractId(contractId);
		if(quantitiesList==null){
			quantitiesList = new QuantitiesList();
			quantitiesList.setRemoved("0");
			quantitiesList.setCreateDate(sdf.format(new Date()));
			quantitiesList.setContractId(contractId);
			quantitiesListService.saveOrUpdate(quantitiesList);
		}
		
		List<Wbs> listWBS = wbsService.sortByWBS("2", contractId);//"2"为工程量条目
		List<Wbs> specialListWBS = wbsService.findSpecialRows("2", contractId);//"2"为工程量条目
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
		List<QuantitiesSubject> list = new ArrayList<QuantitiesSubject>();
		if(listWBS!=null&&listWBS.size()>0){
			double sum1 = 0;
			double sum2 = 0;
			for(int i=0;i<listWBS.size();i++){
				list.add(quantitiesSubjectService.findById(listWBS.get(i).getNodeId()));
				if(list.get(i)!=null){
					if((long)listWBS.get(i).getWbsLevel()==(long)1&&"1".equals(listWBS.get(i).getType())){
						sum1 += list.get(i).getAmount()==null ? 0 : Double.parseDouble(list.get(i).getAmount());
						sum2 += list.get(i).getTotalPrice()==null ? 0 : Double.parseDouble(list.get(i).getTotalPrice());
					}
					if("3".equals(list.get(i).getDataType())){
						list.get(i).setAmount(String.valueOf(sum1));
						list.get(i).setTotalPrice(getFormattedMoney2(String.valueOf(sum2)));
					}
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("quantitiesSubjectList", list);
		ServletActionContext.getRequest().setAttribute("listWBS", listWBS);
		
		Contract contract = contractService.findContractById(quantitiesSubject.getContractId());
		ServletActionContext.getRequest().setAttribute("contract", contract);
		
		ServletActionContext.getRequest().setAttribute("quantitiesList", quantitiesList);
		
		String hideNodes = ServletActionContext.getRequest().getParameter("hideNodes");  
		ServletActionContext.getRequest().setAttribute("hideNodes",hideNodes);
		
		String targetNode = ServletActionContext.getRequest().getParameter("targetNode"); 
		ServletActionContext.getRequest().setAttribute("targetNode", targetNode);
		
		String ifExport = this.servletRequest.getParameter("ifExport");
		if ("yes".equals(ifExport)) {
			List<Object[]> datasets = new ArrayList<Object[]>();
			for (int i = 0; i < listWBS.size(); i++) {
					Object[] params = new Object[8];
					params[0] = listWBS.get(i).getWbsId();
					params[1] = list.get(i).getSubjectNo();
					params[2] = list.get(i).getSubjectName();
					String type = list.get(i).getSubjectType();
					if(type!=null){
						if(type.equals("1")){
							params[3] = "总价闭口类";
						}else if(type.equals("2")){
							params[3] = "单价核算类";
						}else if(type.equals("3")){
							params[3] = "里程碑支付类";
						}else{
							params[3] = "";
						}
					}
					params[4] = getFormattedMoney2(list.get(i).getUnitPrice());
					params[5] = list.get(i).getAmount();
					params[6] = getFormattedMoney2(list.get(i).getTotalPrice());
					params[7] = list.get(i).getInvestEstimateName();
					datasets.add(params);
			}
			
			String downloadFileName = contract.getContractName()+"-工程量条目-"+sdf2.format(new Date())+".xls";
			try {
				this.servletResponse.setContentType("octets/stream");  
				this.servletResponse.addHeader("Content-Disposition","attachment;filename="+new String(downloadFileName.getBytes("gb2312"), "ISO8859-1" ));
				String[] headers = {"WBS编码","条目编码","条目名称","条目类型","单价（元）","数量","金额（元）","关联概算科目"};
				short[] width = {5000,5000,10000,5000,3000,6000,10000};

				try {
					OutputStream out = this.servletResponse.getOutputStream();
					ExportExcel ee = new ExportExcel();
					ee.exportExcel("工程量条目导出", headers, datasets, out,width); 
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	
	/**
	 * 保存新增
	 * @return
	 */
	public String saveAddOnPage(){
		String parentId = ServletActionContext.getRequest().getParameter("parentId");	//父节点,需要前台传值,先手动传
		String wbsId = ServletActionContext.getRequest().getParameter("wbsId");			//wbsId
		String level = ServletActionContext.getRequest().getParameter("level");			//先手动传
		String order = ServletActionContext.getRequest().getParameter("order");			//先手动传
		String ifHasSpecial = ServletActionContext.getRequest().getParameter("ifHasSpecial");
		String specialOrder = ServletActionContext.getRequest().getParameter("specialOrder");
		String jsonData;
		if(wbsId!=null && !"".equals(wbsId) && level!=null && !"".equals(level) && order!=null && !"".equals(order)){
			quantitiesSubject.setRemoved("0");
			quantitiesSubject.setCreateDate(sdf.format(new Date()));
			quantitiesSubject.setOperateDate(sdf.format(new Date()));
			
			quantitiesSubject.setUnitPrice(getFormattedMoney(quantitiesSubject.getUnitPrice()));
			quantitiesSubject.setTotalPrice(getFormattedMoney(quantitiesSubject.getTotalPrice()));
			quantitiesSubject.setAmount(Double.valueOf(quantitiesSubject.getAmount()).toString());
			
			quantitiesSubjectService.saveQuantitiesSubject(quantitiesSubject);	//保存科目
			Wbs wbs = quantitiesSubjectService.saveAddOnPage(quantitiesSubject, parentId, wbsId, level, order,ifHasSpecial,specialOrder);
			//jsonData = "success";
			
			//解决页面保存后刷新问题
			QuantitiesSubjectWbsBo bo = new QuantitiesSubjectWbsBo();
			bo.setQuantitiesSubject(quantitiesSubject);
			bo.setWbs(wbs);
			jsonData = VOUtils.getJsonData(bo);
			
		}else{
			jsonData = null;
		}
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 保存修改
	 * @return
	 */
	public String saveEditOnPage(){
		QuantitiesSubject qs = quantitiesSubjectService.findById(quantitiesSubject.getId());
		if(qs==null)	return AJAX;
		
		qs.setSubjectNo(quantitiesSubject.getSubjectNo());
		qs.setSubjectName(quantitiesSubject.getSubjectName());
		qs.setSubjectType(quantitiesSubject.getSubjectType());
		qs.setUnitPrice(quantitiesSubject.getUnitPrice());
		qs.setAmount(Double.valueOf(quantitiesSubject.getAmount()).toString());
		qs.setTotalPrice(quantitiesSubject.getTotalPrice());
		qs.setAcceptCondition(quantitiesSubject.getAcceptCondition());
		qs.setSubjectStatus(quantitiesSubject.getSubjectStatus());
		qs.setOperateDate(sdf.format(new Date()));
		
		qs.setUnitPrice(getFormattedMoney(qs.getUnitPrice()));
		qs.setTotalPrice(getFormattedMoney(qs.getTotalPrice()));
		
		quantitiesSubjectService.update(qs);
		String jsonData = VOUtils.getJsonData(qs);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 逻辑删除
	 * @return
	 */
	public String deleteAll(){
		
		String id = ServletActionContext.getRequest().getParameter("id");
		String contractId = ServletActionContext.getRequest().getParameter("contractId");
		String parentId = ServletActionContext.getRequest().getParameter("parentId");
		
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(contractId)){
			createJSonData("删除失败,请稍后再试！");
			return AJAX;
		}
		List<Wbs> wbsList = wbsService.findAllWbsHierarchy("2", contractId, id);
		quantitiesSubjectService.deleteAllSubjectAndWbsLogically(wbsList);
		
		return AJAX;
		
	}
	
	/**
	 * 根据名称，模糊搜索概算科目
	 * @return
	 */
	public String findSubject(){
		List<Wbs> wbsList = null;
		List<InvestEstimateSubject> subjectList = null;
		String contractId  = ServletActionContext.getRequest().getParameter("contractId");
		String subjectName = ServletActionContext.getRequest().getParameter("subjectName");
		
		Contract contract = contractService.findContractById(contractId);
		if(contract==null || "".equals(contract.getProjectId())){
			createJSonData("该合同不存在");
		}else{
			Project project = projectService.findProjectById(contract.getProjectId());
			if(project==null){
				createJSonData("该项目不存在");
			}else{
				subjectList = subjectService.findAllBySubjectNameWithFuzzySearch(project.getId(),subjectName);
				if(subjectList==null || subjectList.size()<1){
					createJSonData("科目名称不存在");
				}else{
					List<String> nodeIdList = new ArrayList<String>();
					for(int i=0; i<subjectList.size(); i++){
						nodeIdList.add(subjectList.get(i).getId());
					}
					wbsList = wbsService.findAllByNodeIds("1", project.getId(), nodeIdList);		//没有下级的所有的wbs
				}
			}
		}
		
		//wbslist已找到，现在从subjectlist删掉 有下级结构的
		List<WbsEstimateSubject> resultList = null;
		if(wbsList!=null && subjectList!=null){
			resultList = new ArrayList<WbsEstimateSubject>();
			WbsEstimateSubject tempObject ;
			for(int i=0; i<wbsList.size(); i++){
				tempObject = new WbsEstimateSubject();
				for(int j=0; j<subjectList.size(); j++){
					if(wbsList.get(i).getNodeId().equals(subjectList.get(j).getId())){
						tempObject.setSubjectId(subjectList.get(i).getId());
						tempObject.setSubjectName(subjectList.get(i).getSubjectName());
						Double resultSum = Double.valueOf(subjectList.get(i).getEstimateDecideSum());
						if(resultSum==0){
							tempObject.setSum("0");
						}else{
							tempObject.setSum(getFormattedMoney(String.valueOf(resultSum)));
						}
						tempObject.setWbsId(wbsList.get(i).getWbsId());
						resultList.add(tempObject);
						//subjectList.remove(subjectList.get(i));		//从subjectlist中删除
						break;
					}
				}
			}
		}
		String jsonData = VOUtils.getJsonDataFromCollection(resultList);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 根据名称，模糊搜索概算科目
	 * @return
	 */
	public String findSubjectByProjectId(){
		List<Wbs> wbsList = null;
		List<InvestEstimateSubject> subjectList = null;
		String projectId  = ServletActionContext.getRequest().getParameter("projectId");
		String subjectName = ServletActionContext.getRequest().getParameter("subjectName");
		
		
		Project project = projectService.findProjectById(projectId);
		if(project==null){
			createJSonData("该项目不存在");
		}else{
			subjectList = subjectService.findAllBySubjectNameWithFuzzySearch(project.getId(),subjectName);
			if(subjectList==null || subjectList.size()<1){
				createJSonData("科目名称不存在");
			}else{
				List<String> nodeIdList = new ArrayList<String>();
				for(int i=0; i<subjectList.size(); i++){
					nodeIdList.add(subjectList.get(i).getId());
				}
				wbsList = wbsService.findAllByNodeIds("1", project.getId(), nodeIdList);		//没有下级的所有的wbs
			}
		}
		
		//wbslist已找到，现在从subjectlist删掉 有下级结构的
		List<WbsEstimateSubject> resultList = null;
		if(wbsList!=null && subjectList!=null){
			resultList = new ArrayList<WbsEstimateSubject>();
			WbsEstimateSubject tempObject ;
			for(int i=0; i<subjectList.size(); i++){
				tempObject = new WbsEstimateSubject();
				for(int j=0; j<wbsList.size(); j++){
					if(wbsList.get(j).getNodeId().equals(subjectList.get(i).getId())){
						tempObject.setSubjectId(subjectList.get(i).getId());
						tempObject.setSubjectName(subjectList.get(i).getSubjectName());
						Double resultSum = Double.valueOf(subjectList.get(i).getEstimateDecideSum());
						if(resultSum==0){
							tempObject.setSum("0");
						}else{
							tempObject.setSum(getFormattedMoney(String.valueOf(resultSum)));
						}
						tempObject.setWbsId(wbsList.get(j).getWbsId());
						resultList.add(tempObject);
						break;
					}
				}
			}
		}
		if(resultList!=null){
			String jsonData = VOUtils.getJsonDataFromCollection(resultList);
			createJSonData(jsonData);
		}
		return AJAX;
	}
	
	
	/**
	 * 保存关联的投资概算科目,就保存科目主键,和科目名称
	 * @return
	 */
	public String saveSubjectOnPage(){
		String quantitiesSubjectId = ServletActionContext.getRequest().getParameter("id");
		String ieId = ServletActionContext.getRequest().getParameter("ieId");
		String ieName = ServletActionContext.getRequest().getParameter("ieName");
		
		QuantitiesSubject qs = quantitiesSubjectService.findById(quantitiesSubjectId);
		if(qs!=null){
			qs.setOperateDate(sdf.format(new Date()));
			qs.setInvestEstimateId(ieId);
			qs.setInvestEstimateName(ieName);
			quantitiesSubjectService.update(qs);
		}
		return AJAX;
	}
	
	
	
	public String compute(){
		String contractId = ServletActionContext.getRequest().getParameter("contractId");
		
		quantitiesSubjectService.updateCompute("2",contractId);
		
		String specilDataIds = ServletActionContext.getRequest().getParameter("specilDataIds");
		String number = ServletActionContext.getRequest().getParameter("number");
		String money = ServletActionContext.getRequest().getParameter("money");
		String ids[] ,numbers[],moneys[];
		
		if(specilDataIds!=null && !"".equals(specilDataIds) && number!=null && !"".equals(number) && money!=null && !"".equals(money)){
			ids = specilDataIds.split(",");
			numbers = number.split(",");
			moneys = money.split(",");
			quantitiesSubjectService.updateSpecialData(ids, numbers,moneys);
		}

		return AJAX;
	}
	
	
	
	
	//得到保留4位小数后的字符串
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
		DecimalFormat df = new DecimalFormat("#0.0000");
		return df.format(result);
	}
	
	//得到保留2位小数后的字符串
	public String getFormattedMoney2(String money){
		if(money==null || "".equals(money)){
			money = "0";
		}
		Double result = 0d;
		try {
			result = Double.valueOf(money);
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(result);
	}
	
	//新增特殊行
	public void saveAddOnPageSpecial(){
		
		String parentId = ServletActionContext.getRequest().getParameter("parentId");	//父节点,需要前台传值,先手动传
		String level = ServletActionContext.getRequest().getParameter("level");			
		String order = ServletActionContext.getRequest().getParameter("order");	
		String wbsId = ServletActionContext.getRequest().getParameter("wbsId");	
		String specialOrder = ServletActionContext.getRequest().getParameter("specialOrder");	
		
		if( level!=null && !"".equals(level) && order!=null && !"".equals(order)){
			quantitiesSubject.setRemoved("0");
			quantitiesSubject.setCreateDate(sdf.format(new Date()));
			quantitiesSubject.setOperateDate(sdf.format(new Date()));
			
			quantitiesSubjectService.saveQuantitiesSubject(quantitiesSubject);	//保存科目
			quantitiesSubjectService.saveAddOnPageSpecial(quantitiesSubject, parentId,wbsId, level, order,specialOrder);
		}
	}
	
	//修改特殊行
	public void saveEditOnPageSpecial(){
		String id = ServletActionContext.getRequest().getParameter("id");
		String subjectName = ServletActionContext.getRequest().getParameter("subjectName");
		if(id!=null){
			QuantitiesSubject bo = quantitiesSubjectService.findById(id);
			bo.setSubjectName(subjectName);
			quantitiesSubjectService.saveQuantitiesSubject(bo);
		}
	}
	
	//删除特殊行
	public void deleteSpecial(){
		String id = ServletActionContext.getRequest().getParameter("id");
		String pro_id = ServletActionContext.getRequest().getParameter("pro_id");
		if(id!=null){
			QuantitiesSubject bo = quantitiesSubjectService.findById(pro_id);
			bo.setRemoved("1");
			quantitiesSubjectService.saveQuantitiesSubject(bo);
			List<Wbs> wbsList = wbsService.findAllWbsHierarchy("1", bo.getContractId(), id);
			quantitiesSubjectService.deleteWbsSpecial(wbsList);
		}
	}
	
	
}
