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

package com.wonders.ic.dwContract.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.dao.ContractDao;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;
import com.wonders.ic.dwContract.dao.DwContractCoverDao;
import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wonders.ic.dwContract.service.DwContractCoverService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractCoverServiceImpl implements DwContractCoverService {
	private DwContractCoverDao dwContractCoverDao;
	private ContractDao contractDao;
	private ContractService contractService;
	private DeptTreeService deptTreeService;
	private final String type1 = "99991"; //项目公司
	private final String type2 = "99990"; //运营公司id
	private final String type3 = "2545"; //维保保障中心id
	private final String yg = "2540";	//运管中心id
	private final String edu = "2546";		//教育培训中心,863：2902,现场2546

	
	
	public void setDeptTreeService(DeptTreeService deptTreeService) {
		this.deptTreeService = deptTreeService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}
	public void setDwContractCoverDao(DwContractCoverDao dwContractCoverDao) {
		this.dwContractCoverDao = dwContractCoverDao;
	}

	@Override
	public void updateContractCoverData() {
		Date addDate = new Date();
		/**1.先删除所有数据*******************/
		dwContractCoverDao.deleteAll();
		
		/**2.增加全部数据*********************/
		Long allNumber = contractDao.findContractsSum();
		dwContractCoverDao.save(getContractCoverInstance(null,null,null,"0",addDate,new BigDecimal(allNumber)));
		
		/** 3.增加建设类合同 start***********************/
			//1.建设类全部
		Long type1AllNumber = contractDao.findContractsSumByType("1");	
		dwContractCoverDao.save(getContractCoverInstance(null,null,null,"1",addDate,new BigDecimal(type1AllNumber)));
			//2.建设类-项目公司
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<String> type1IdList = new ArrayList<String>(),typeAllIdList = new ArrayList<String>();
		int type1CountAll = 0;
		dept1TreeList.addAll(deptTreeService.findByPid(type1));
		List<String[]> resultList1 = new ArrayList<String[]>();
		for(int i=0; i<dept1TreeList.size(); i++){
			type1IdList.add(dept1TreeList.get(i).getId());
		}
		typeAllIdList.addAll(type1IdList);
		Map<String, String> type1Map = contractService.findCountGroupByAddDeptIds(type1IdList,"1");
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
		if(resultList1!=null && resultList1.size()>0){
			List<DwContractCover> list = new ArrayList<DwContractCover>();
			for(int i=0; i<resultList1.size(); i++){
				if(resultList1.get(i)[0].contains("项目公司")){
					list.add(getContractCoverInstance(resultList1.get(i)[0].replace("项目公司", ""), resultList1.get(i)[2], "1", "1", addDate, new BigDecimal(resultList1.get(i)[1])));
				}else{
					list.add(getContractCoverInstance(resultList1.get(i)[0], resultList1.get(i)[2], "1", "1", addDate, new BigDecimal(resultList1.get(i)[1])));
				}
				
			}
			dwContractCoverDao.saveOrUpdateAll(list);
		}
		//4.建设类-合同分类
		List<DwContractCover> type1List = new ArrayList<DwContractCover>();
		for(int i=1; i<=8; i++){
			type1List.add(getContractCoverInstance(null, null, null, "1,"+i, addDate, new BigDecimal(contractDao.findContractsSumByType("1,"+i))));
		}
		dwContractCoverDao.saveOrUpdateAll(type1List);
		/**建设类end************************************/
		
		
		
		/**运维类 start**************************/
			//1.运维类全部
		Long type2AllNumber = contractDao.findContractsSumByType("2");	
		dwContractCoverDao.save(getContractCoverInstance(null,null,null,"2",addDate,new BigDecimal(type2AllNumber)));
			//2.运维类-运营公司
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<String> type2IdList = new ArrayList<String>(),typeAl2IdList = new ArrayList<String>();
		int type2CountAll = 0;
		dept2TreeList.addAll(deptTreeService.findByPid(type2));
		List<String[]> resultList2 = new ArrayList<String[]>();
		for(int i=0; i<dept2TreeList.size(); i++){
			type2IdList.add(dept2TreeList.get(i).getId());
		}
		typeAl2IdList.addAll(type2IdList);
		Map<String, String> type2Map = contractService.findCountGroupByAddDeptIds(type2IdList,"2");
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
		if(resultList2!=null && resultList2.size()>0){
			List<DwContractCover> list = new ArrayList<DwContractCover>();
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
				list.add(getContractCoverInstance(shortCompanyName, resultList2.get(i)[2], "2", "2", addDate, new BigDecimal(resultList2.get(i)[1])));
			}
			dwContractCoverDao.saveOrUpdateAll(list);
		}
			//3.运维类-维保中心
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> type3IdList = new ArrayList<String>(),typeAl3IdList = new ArrayList<String>();
		int type3CountAll = 0;
		dept3TreeList.addAll(deptTreeService.findByPid(type3));
		dept3TreeList.add(deptTreeService.findById(type3));
		List<String[]> resultList3 = new ArrayList<String[]>();
		for(int i=0; i<dept3TreeList.size(); i++){
			type3IdList.add(dept3TreeList.get(i).getId());
		}
		typeAl3IdList.addAll(type3IdList);
		Map<String, String> type3Map = contractService.findCountGroupByAddDeptIds(type3IdList,"2");
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
		if(resultList3!=null && resultList3.size()>0){
			List<DwContractCover> list = new ArrayList<DwContractCover>();
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
					shortCompanyName ="维保中心本部";
				}
				list.add(getContractCoverInstance(shortCompanyName, resultList3.get(i)[2], "3", "2", addDate, new BigDecimal(resultList3.get(i)[1])));
			}
			dwContractCoverDao.saveOrUpdateAll(list);
		}
		//4.运维类-合同分类
		List<DwContractCover> type2List = new ArrayList<DwContractCover>();
		for(int i=1; i<=3; i++){
			type2List.add(getContractCoverInstance(null, null, null, "2,"+i, addDate, new BigDecimal(contractDao.findContractsSumByType("2,"+i))));
		}
		dwContractCoverDao.saveOrUpdateAll(type2List);
		
		//5.新增需求
		//5.1新增了运管中新2540,教培中心
		List<String> idList = new ArrayList<String>();
		idList.add(yg);
		idList.add(edu);
		Map<String, String> resultnew = contractService.findCountGroupByAddDeptIds(idList,"2");
		List<DwContractCover> addListNew = new ArrayList<DwContractCover>();
		if(resultnew.get(yg)!=null){
			addListNew.add(getContractCoverInstance("运管中心", yg, "4", "2",addDate, new BigDecimal(resultnew.get(yg))));
		}else{
			addListNew.add(getContractCoverInstance("运管中心", yg, "4", "2",addDate, new BigDecimal(0)));
		}
		if(resultnew.get(edu)!=null){
			addListNew.add(getContractCoverInstance("教培中心", edu, "5", "2",addDate, new BigDecimal(resultnew.get(edu))));
		}else{
			addListNew.add(getContractCoverInstance("教培中心", edu, "5", "2",addDate, new BigDecimal(0)));
		}
		dwContractCoverDao.saveOrUpdateAll(addListNew);
		
		//dwContractCoverDao.save(getContractCoverInstance("运管中心", yg, companyType, contractType, addDate, new BigDecimal(contractService.findOneData(sql))));
		
		/**运维类 end**************************/
		
		/**其他类***************/
		Long type3AllNumber = contractDao.findContractsSumByType("3");	
		dwContractCoverDao.save(getContractCoverInstance(null,null,null,"3",addDate,new BigDecimal(type3AllNumber))); 
	}
	
	//创建一个DwContractCover
	public DwContractCover getContractCoverInstance(String companyName,String companyId,String companyType,String contractType,Date createDate,BigDecimal numbers){
		DwContractCover contractCover = new DwContractCover();
		contractCover.setCompanyName(companyName);
		contractCover.setCompanyId(companyId);
		contractCover.setCompanyType(companyType);
		contractCover.setContractType(contractType);
		contractCover.setCreateDate(createDate);
		contractCover.setNumbers(numbers);
		return contractCover;
	}

	@Override
	public DwContractCover findByContractType(String contractType) {
		List<DwContractCover> list = dwContractCoverDao.findByContractType(contractType);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	@Override
	public List<DwContractCover> findByCompanyType(
			String conpanyType) {
		return dwContractCoverDao.findByCompanyType(conpanyType);
	}

	@Override
	public List<DwContractCover> findByContractTypeWithFuzzySearch(String contractType) {
		return dwContractCoverDao.findByContractTypeWithFuzzySearch(contractType);
	}
	
	
}
