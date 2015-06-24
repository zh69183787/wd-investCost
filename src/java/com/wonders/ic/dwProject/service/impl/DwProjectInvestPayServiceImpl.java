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

package com.wonders.ic.dwProject.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.dwProject.dao.DwProjectInvestPayDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectInvestPay;
import com.wonders.ic.dwProject.service.DwProjectInvestPayService;
import com.wonders.ic.project.entity.bo.Project;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectInvestPayServiceImpl implements DwProjectInvestPayService {
	private DwProjectInvestPayDao dwProjectInvestPayDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setDwProjectInvestPayDao(
			DwProjectInvestPayDao dwProjectInvestPayDao) {
		this.dwProjectInvestPayDao = dwProjectInvestPayDao;
	}

	public void addDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay) {
		dwProjectInvestPayDao.save(dwProjectInvestPay);
	}

	public void deleteDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay) {
		dwProjectInvestPayDao.delete(dwProjectInvestPay);
	}

	public DwProjectInvestPay findDwProjectInvestPayById(String id) {
		return dwProjectInvestPayDao.load(id);
	}

	public void updateDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay) {
		dwProjectInvestPayDao.update(dwProjectInvestPay);
	}

	public Page findDwProjectInvestPayByPage(int pageNo, int pageSize) {
		Page page = dwProjectInvestPayDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findDwProjectInvestPayByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return dwProjectInvestPayDao.findDwProjectInvestPayByPage(filter,
				pageNo, pageSize);
	}
	
	public List<DwProjectInvestPay> findByName8(String projectNo,String projectName,String sDate,String eDate){
		return dwProjectInvestPayDao.findByName8(projectNo, projectName, sDate,eDate);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateCountAllPaysForProject() {
		List<Project> projectList = dwProjectInvestPayDao.findAllProjectBindedWithContracts();
		if(projectList!=null && projectList.size()>0){
			Date createDate = new Date();
			for(int i=0; i<projectList.size(); i++){
				Project project = projectList.get(i);
				List<ContractStatus> contractStatusList = dwProjectInvestPayDao.findAllContractStatusByProjectIdAndType(project.getId(), "3");//所有的实际支付值
				if(contractStatusList!=null && contractStatusList.size()>0){ 	//有实际支付数据
					Date tempDate = null;		//临时时间，用来月份递增相加
					float actualPayTotal = 0;			//单位:万元
					List<DwProjectInvestPay> addList = new ArrayList<DwProjectInvestPay>();	
					DwProjectInvestPay dwPay ;
					for(int m=0; m<contractStatusList.size(); m++){
						ContractStatus contractStatus = contractStatusList.get(m);
						try {
							Date contractDate = sdf.parse(contractStatus.getOperateDate());		//日期转化错误，后面的都不执行
							float actualPay = Float.valueOf(contractStatus.getMoney());		//支付值转化错误，后面的都不执行
							if(tempDate==null){			//当前月循环开始
								tempDate = contractDate;
								actualPayTotal = actualPay;
								if(m==contractStatusList.size()-1){			//是最后一条数据
									dwPay = new DwProjectInvestPay();
									dwPay.setProjectId(project.getId());
									dwPay.setProjectName(project.getProjectName());
									dwPay.setProjectNo(project.getProjectNo());
									dwPay.setActualPay(String.valueOf(actualPayTotal));
									dwPay.setCreateDate(new Date());
									dwPay.setActualPay(String.valueOf(actualPayTotal));
									contractDate.setDate(15);
									dwPay.setActualPayDate(contractDate);
									dwPay.setCreateDate(createDate);
									addList.add(dwPay);
								}
							}else{
//System.out.println(tempDate.getYear()+"="+contractDate.getYear()+","+tempDate.getMonth()+"="+contractDate.getMonth());								
								if(tempDate.getYear()==contractDate.getYear() && tempDate.getMonth()==contractDate.getMonth()){	//年月相同,支付值相加
									actualPayTotal += actualPay;
									if(m==contractStatusList.size()-1){	//最后一条数据	
										dwPay = new DwProjectInvestPay();
										dwPay.setProjectId(project.getId());
										dwPay.setProjectName(project.getProjectName());
										dwPay.setProjectNo(project.getProjectNo());
										dwPay.setActualPay(String.valueOf(actualPayTotal));
										dwPay.setCreateDate(new Date());
										dwPay.setActualPay(String.valueOf(actualPayTotal));
										contractDate.setDate(15);
										dwPay.setActualPayDate(contractDate);
										dwPay.setCreateDate(createDate);
										addList.add(dwPay);
									}
								}else{	//年月不同
									while (!(tempDate.getYear()+""+tempDate.getMonth()).equals(contractDate.getYear()+""+contractDate.getMonth())) {
										Date now = new Date();
										dwPay = new DwProjectInvestPay();
										dwPay.setProjectId(project.getId());
										dwPay.setProjectName(project.getProjectName());
										dwPay.setProjectNo(project.getProjectNo());
										dwPay.setActualPay(String.valueOf(actualPayTotal));
										dwPay.setCreateDate(new Date());
										tempDate.setDate(15);		//设置为当月的15号
										now.setTime(tempDate.getTime());
										dwPay.setActualPayDate(now);
										dwPay.setCreateDate(createDate);
										addList.add(dwPay);
										tempDate.setMonth(tempDate.getMonth()+1);		//月份+1
									}
									//年份相同,支付值相加
									actualPayTotal += actualPay;
									if(m==contractStatusList.size()-1){	//最后一条数据
										dwPay = new DwProjectInvestPay();
										dwPay.setProjectId(project.getId());
										dwPay.setProjectName(project.getProjectName());
										dwPay.setProjectNo(project.getProjectNo());
										dwPay.setActualPay(String.valueOf(actualPayTotal));
										dwPay.setCreateDate(new Date());
										dwPay.setActualPay(String.valueOf(actualPayTotal));
										contractDate.setDate(15);
										dwPay.setActualPayDate(contractDate);
										dwPay.setCreateDate(createDate);
										addList.add(dwPay);
									}
								}
							}
						} catch (ParseException e) {
							System.out.println("日期转换错误,C_CONTRACT_STATUS的id="+contractStatus.getId());
						} catch (NumberFormatException e2) {
							System.out.println("实际支付转化错误,C_CONTRACT_STATUS的id="+contractStatus.getId());
						}
					}
					dwProjectInvestPayDao.saveOrUpdateAll(addList);
				}
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
