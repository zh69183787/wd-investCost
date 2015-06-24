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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;
import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wonders.ic.dwProject.dao.DwProjectCoverDao;
import com.wonders.ic.dwProject.entity.bo.DwProjectCover;
import com.wonders.ic.dwProject.service.DwProjectCoverService;
import com.wonders.ic.project.dao.ProjectDao;
import com.wonders.ic.project.service.ProjectService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectCoverServiceImpl implements DwProjectCoverService {
	private DwProjectCoverDao dwProjectCoverDao;
	private ProjectDao projectDao;
	private ProjectService projectService;
	private DeptTreeService deptTreeService;
	
	private final String type1 = "99991"; //项目公司
	private final String type2 = "99990"; //运营公司id
	private final String type3 = "2545"; //维保保障中心id
	
	private final String yg = "2540";	//运管中心id
	private final String edu = "2546";		//教育培训中心,863：2902,现场2546

	public void setDwProjectCoverDao(DwProjectCoverDao dwProjectCoverDao) {
		this.dwProjectCoverDao = dwProjectCoverDao;
	}
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public void setDeptTreeService(DeptTreeService deptTreeService) {
		this.deptTreeService = deptTreeService;
	}
	@Override
	public void updateDwProjectData() {
		List<String> controlAndMaintain =  new ArrayList<String>();
		//List<String> projectCompany = new ArrayList<String>();
		Date addDate = new Date();
		/**1.先删除数据库中的数据**/
		dwProjectCoverDao.deleteAll();
		
		/**2.增加全部数据**/
		Long all = projectDao.findSumOfProject();
		dwProjectCoverDao.save(getDwProjectCoverInstance("全部项目", null, null, "0", addDate, new BigDecimal(all)));
		
		/**3.内部项目**/
		//1.全部
		Long innerAll = dwProjectCoverDao.findProjectSumByTypeAndAdddeptId("0","");
		dwProjectCoverDao.save(getDwProjectCoverInstance("内部项目-全部", null, null, "2", addDate, new BigDecimal(innerAll)));
		
		//2.运营中心
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<String> type2IdList= new ArrayList<String>();
		dept2TreeList.addAll(dept2TreeList.size(), deptTreeService.findByPid(type2));
		int type2CountAll = 0;
		List<String[]> resultList2 = new ArrayList<String[]>();
		for(int i=0; i<dept2TreeList.size(); i++){
			type2IdList.add(dept2TreeList.get(i).getId());
		}
		controlAndMaintain.addAll(type2IdList);
		Map<String, String> type2Map = projectService.findCountGroupByAddDeptIds(type2IdList,"0");
		if(type2Map!=null && type2Map.size()>0){
			for(int i=0; i<dept2TreeList.size(); i++){
				if(type2Map.get(dept2TreeList.get(i).getId())!=null){
					resultList2.add(new String[]{dept2TreeList.get(i).getName(),type2Map.get(dept2TreeList.get(i).getId()),dept2TreeList.get(i).getId()});
					type2CountAll += Integer.valueOf(type2Map.get(dept2TreeList.get(i).getId()));
				}else{
					resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
				}
			}
		}else{
			for(int i=0; i<dept2TreeList.size(); i++){
				resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
			}
		}
		List<DwProjectCover> type2List = null;
		if(resultList2!=null && resultList2.size()>0){
			type2List = new ArrayList<DwProjectCover>();
			for(int i=0; i<resultList2.size(); i++){
				String shortCompanyName = resultList2.get(i)[0];
				if(shortCompanyName.equals("第一运营公司")){
					shortCompanyName = "运一公司";
				}else if(shortCompanyName.equals("第二运营公司")){
					shortCompanyName = "运二公司";
				}else if(shortCompanyName.equals("第三运营公司")){
					shortCompanyName = "运三公司";
				}else if(shortCompanyName.equals("第四运营公司")){
					shortCompanyName = "运四公司";
				}
				type2List.add(getDwProjectCoverInstance(shortCompanyName,resultList2.get(i)[2],"2","2",addDate,new BigDecimal(resultList2.get(i)[1])));
			}
			dwProjectCoverDao.saveOrUpdateAll(type2List);
		}
		//3.维保中心
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> type3IdList= new ArrayList<String>();
		dept3TreeList.addAll(dept3TreeList.size(), deptTreeService.findByPid(type3));
		dept3TreeList.add(deptTreeService.findById(type3));
		List<String[]> resultList3 = new ArrayList<String[]>();
		int type3CountAll = 0;
		for(int i=0; i<dept3TreeList.size(); i++){
			type3IdList.add(dept3TreeList.get(i).getId());
		}
		controlAndMaintain.addAll(type3IdList);
		Map<String, String> type3Map = projectService.findCountGroupByAddDeptIds(type3IdList,"0");
		if(type3Map!=null && type3Map.size()>0){
			for(int i=0; i<dept3TreeList.size(); i++){
				if(type3Map.get(dept3TreeList.get(i).getId())!=null){
					resultList3.add(new String[]{dept3TreeList.get(i).getName(),type3Map.get(dept3TreeList.get(i).getId()),dept3TreeList.get(i).getId()});
					type3CountAll += Integer.valueOf(type3Map.get(dept3TreeList.get(i).getId()));
				}else{
					resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
				}
			}
		}else{
			for(int i=0; i<dept3TreeList.size(); i++){
				resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
			}
		}
		List<DwProjectCover> type3List = null;
		if(resultList3!=null && resultList3.size()>0){
			type3List = new ArrayList<DwProjectCover>();
			for(int i=0; i<resultList3.size(); i++){
				String shortCompanyName = resultList3.get(i)[0];
				if(shortCompanyName.contains("车辆分公司")){
					shortCompanyName = "车辆公司";
				}else if(shortCompanyName.contains("供电分公司")){
					shortCompanyName = "供电公司";
				}else if(shortCompanyName.contains("通号分公司")){
					shortCompanyName = "通号公司";
				}else if(shortCompanyName.contains("工务分公司")){
					shortCompanyName = "工务公司";
				}else if(shortCompanyName.contains("物资后勤分公司")){
					shortCompanyName = "物资公司";
				}else if(shortCompanyName.contains("维护保障中心")){
					shortCompanyName = "维保中心本部";
				}
				type3List.add(getDwProjectCoverInstance(shortCompanyName,resultList3.get(i)[2],"3","2",addDate,new BigDecimal(resultList3.get(i)[1])));
			}
			dwProjectCoverDao.saveOrUpdateAll(type3List);
		}
		//4.内部项目-其他
		controlAndMaintain.add(yg);		//运管中心
		controlAndMaintain.add(edu);  //维护保障中心
		Long other = dwProjectCoverDao.findProjectSumByTypeAndWithoutAdddeptId("0",controlAndMaintain);
		dwProjectCoverDao.save(getDwProjectCoverInstance("内部项目-其他", null, "-1", "2", addDate, new BigDecimal(other)));
		//5.其他-运管中心
		/*Long controlCenter = dwProjectCoverDao.findProjectSumByTypeAndAdddeptId("0","2540");
		dwProjectCoverDao.save(getDwProjectCoverInstance("运管中心", "2540", "4", "2", addDate, new BigDecimal(controlCenter)));*/
		
		//5.新增需求
		//5.1新增了运管中新2540,教培中心
		List<String> idList = new ArrayList<String>();
		idList.add(yg);
		idList.add(edu);
		Map<String, String> resultnew = projectService.findCountGroupByAddDeptIds(idList,"0");
		List<DwProjectCover> addListNew = new ArrayList<DwProjectCover>();
		if(resultnew.get(yg)!=null){
			addListNew.add(getDwProjectCoverInstance("运管中心", yg, "4", "2",addDate, new BigDecimal(resultnew.get(yg))));
		}else{
			addListNew.add(getDwProjectCoverInstance("运管中心", yg, "4", "2",addDate, new BigDecimal(0)));
		}
		if(resultnew.get(edu)!=null){
			addListNew.add(getDwProjectCoverInstance("教培中心", edu, "5", "2",addDate, new BigDecimal(resultnew.get(edu))));
		}else{
			addListNew.add(getDwProjectCoverInstance("教培中心", edu, "5", "2",addDate, new BigDecimal(0)));
		}
		dwProjectCoverDao.saveOrUpdateAll(addListNew);
		
		
		
		
		
		
		
		
		
		
		
		
		/**3.外部项目**/
		//1.全部
		Long outAll = dwProjectCoverDao.findProjectSumByTypeAndAdddeptId("1","");
		dwProjectCoverDao.save(getDwProjectCoverInstance("外部项目-全部", null, null, "1", addDate, new BigDecimal(outAll)));
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<String> type1IdList= new ArrayList<String>();
		dept1TreeList.addAll(dept1TreeList.size(), deptTreeService.findByPid(type1));
		int type1CountAll = 0;
		List<String[]> resultList1 = new ArrayList<String[]>();
		for(int i=0; i<dept1TreeList.size(); i++){
			type1IdList.add(dept1TreeList.get(i).getId());
		}
		Map<String, String> type1Map = projectService.findCountGroupByAddDeptIds(type1IdList,"1");
		if(type1Map!=null && type1Map.size()>0){
			for(int i=0; i<dept1TreeList.size(); i++){
				if(type1Map.get(dept1TreeList.get(i).getId())!=null){
					resultList1.add(new String[]{dept1TreeList.get(i).getName(),type1Map.get(dept1TreeList.get(i).getId()),dept1TreeList.get(i).getId()});
					type1CountAll += Integer.valueOf(type1Map.get(dept1TreeList.get(i).getId()));
				}else{
					resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
				}
			}
		}else{
			for(int i=0; i<dept1TreeList.size(); i++){
				resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
			}
		}
		List<DwProjectCover> type1List = null;
		if(resultList1!=null && resultList1.size()>0){
			type1List = new ArrayList<DwProjectCover>();
			for(int i=0; i<resultList1.size(); i++){
				if(resultList1.get(i)[0].contains("项目公司")){
					type1List.add(getDwProjectCoverInstance(resultList1.get(i)[0].replace("项目公司", ""),resultList1.get(i)[2],"1","1",addDate,new BigDecimal(resultList1.get(i)[1])));
				}else{
					type1List.add(getDwProjectCoverInstance(resultList1.get(i)[0],resultList1.get(i)[2],"1","1",addDate,new BigDecimal(resultList1.get(i)[1])));
				}
			}
			dwProjectCoverDao.saveOrUpdateAll(type1List);
		}
		//2.其他
		Long other2 = dwProjectCoverDao.findProjectSumByTypeAndWithoutAdddeptId("1",type1IdList);
		dwProjectCoverDao.save(getDwProjectCoverInstance("外部项目-其他", null, "-1", "1", addDate, new BigDecimal(other2)));
		
		
		/**3.其他项目**/
		//1.全部
		Long otherAll = dwProjectCoverDao.findProjectSumByTypeAndAdddeptId("5","");
		dwProjectCoverDao.save(getDwProjectCoverInstance("其他项目-全部", null, null, "3", addDate, new BigDecimal(otherAll)));
	}

	
	public DwProjectCover getDwProjectCoverInstance(String companyName,String companyId,String companyType,String projectType,Date createDate,BigDecimal numbers){
		DwProjectCover projectCover = new DwProjectCover();
		projectCover.setCompanyName(companyName);
		projectCover.setCompanyId(companyId);
		projectCover.setCompanyType(companyType);
		projectCover.setProjectType(projectType);
		projectCover.setCreateDate(createDate);
		projectCover.setNumbers(numbers);
		return projectCover;
	}
	@Override
	public List<DwProjectCover> findByProjectTypeAndCompanyType(String projectType,String companyType) {
		
		return dwProjectCoverDao.findByProjectTypeAndCompanyType(projectType, companyType);
	}
	
	
	
	
}
