package com.wonders.ic.jgjReport.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.wonders.ic.contractStatus.dao.ContractStatusDao;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.jgjReport.dao.JgjReportDao;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajor;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorSeason;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProject;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectProgress;
import com.wonders.ic.jgjReport.entity.bo.MoneySource;
import com.wonders.ic.jgjReport.service.JgjReportService;

public class JgjReportServiceImpl implements JgjReportService{

	private JgjReportDao jgjReportDao;
	private ContractStatusDao contractStatusDao;
	
	public JgjReportDao getJgjReportDao() {
		return jgjReportDao;
	}
	public void setJgjReportDao(JgjReportDao jgjReportDao) {
		this.jgjReportDao = jgjReportDao;
	}
	public ContractStatusDao getContractStatusDao() {
		return contractStatusDao;
	}
	public void setContractStatusDao(ContractStatusDao contractStatusDao) {
		this.contractStatusDao = contractStatusDao;
	}
	
	@Override
	public List<DwJgjMajor> findByYearAndTypeJgjMajor(String year, String type) {
		return jgjReportDao.findByYearAndType(year, type);
	}
	@Override
	public List<String> findAllYearJgjMajor() {
		
		return jgjReportDao.findAllYearJgjMajor();
	}
	@Override
	public List<String> findAllYearJgjProject() {
		
		return jgjReportDao.findAllYearJgjProject();
	}
	@Override
	public List<DwJgjProject> findByYearAndTypeJgjProject(String year) {
		
		return jgjReportDao.findByYearJgjProject(year);
	}
	
	@Override
	public void calculateDwJgjProject(String year) {
		List<DwJgjProject> dwJgjProjects = jgjReportDao.findJgjProjectByYear(year);
		List<String> typeIds = new ArrayList<String>();
		typeIds.add("2");
		typeIds.add("3");
		List<ContractStatus> contractStatuss = contractStatusDao.findByYear(year, false, null, typeIds, null);
		Map<String, List<ContractStatus>> contractMap = new HashMap<String, List<ContractStatus>>();
		for (ContractStatus contractStatus : contractStatuss) {
			String contractId = contractStatus.getContractId();
			if(contractMap.get(contractId) == null){
				List<ContractStatus> list = new ArrayList<ContractStatus>();
				list.add(contractStatus);
				contractMap.put(contractId, list);
			}else {
				contractMap.get(contractId).add(contractStatus);
			}
		}
		
		Date startDate = String2Date(year + "-01-01");
		Date endDate = String2Date(year + "-12-31");
		for (DwJgjProject dwJgjProject : dwJgjProjects) {
			String contractId = dwJgjProject.getContractId();
			Double lastYearCount = null, thisYearPayCount = null, thisYearPlanCount = null;
			Double contractPrice = dwJgjProject.getContractPrice().doubleValue();
			List<ContractStatus> statusList = contractMap.get(contractId);
			if(statusList != null){
				for (ContractStatus contractStatus : statusList) {
					String type = contractStatus.getType();
					Date operateDate = String2Date(contractStatus.getOperateDate());
					if("3".equals(type)){	//实际支付
						if(operateDate.getTime() <= startDate.getTime()) { //至上年
							if (lastYearCount == null) lastYearCount = 0.0;
							lastYearCount += Double.valueOf(contractStatus.getMoney());
						}
						else if(operateDate.getTime() >= startDate.getTime() && operateDate.getTime() <= endDate.getTime()){//本年
							if (thisYearPayCount == null) thisYearPayCount = 0.0;
							thisYearPayCount += Double.valueOf(contractStatus.getMoney());	
						}
					}else if("4".equals(type)){	//用款计划
						if(operateDate.getTime() >= startDate.getTime() && operateDate.getTime() <= endDate.getTime()){//本年
							if (thisYearPlanCount == null) thisYearPlanCount = 0.0;
							thisYearPlanCount += Double.valueOf(contractStatus.getMoney());	
						}
					}else if("1".equals(type)){ //合同变更
						if (contractPrice == null) contractPrice = 0.0;
						contractPrice += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if(dwJgjProject.getNationVerifyPrice() != null)
				dwJgjProject.setFinalPrice(dwJgjProject.getNationVerifyPrice());
			else if(dwJgjProject.getVerifyPrice() != null)
				dwJgjProject.setFinalPrice(dwJgjProject.getVerifyPrice());
			dwJgjProject.setContractPrice(round(contractPrice,6));
			dwJgjProject.setLastYearCount(round(lastYearCount,6));
			dwJgjProject.setThisYearPlanCount(round(thisYearPlanCount,6));
			dwJgjProject.setThisYearPayCount(round(thisYearPayCount,6));
			if(lastYearCount != null || thisYearPayCount != null){
				if(lastYearCount == null) lastYearCount = 0.0;
				if(thisYearPayCount == null) thisYearPayCount = 0.0;
				dwJgjProject.setEndYearPayCount(round(lastYearCount + thisYearPayCount,6));
			}
			List<MoneySource> moneySources = parseMoneySource(dwJgjProject.getFeeDepartment());
			for(int i=0; i<moneySources.size(); i++){
				if(i == 0){
					dwJgjProject.setFeeDepartment(moneySources.get(0).getUnitName());
					dwJgjProject.setFeeMoney(moneySources.get(0).getMoney().toString());
				}else {
					DwJgjProject temp = (DwJgjProject)dwJgjProject.clone();
					temp.setFeeDepartment(moneySources.get(i).getUnitName());
					temp.setFeeMoney(moneySources.get(i).getMoney().toString());
					jgjReportDao.saveProject(temp);
				}
			}
			jgjReportDao.saveProject(dwJgjProject);
		}
	}
	
	@Override
	public void calculateDwJgjProjectCount(String year) {
		List<String> typeIds = new ArrayList<String>();
		typeIds.add("2");
		typeIds.add("3");
		List<DwJgjProjectCount> dwJgjProjectCounts = jgjReportDao.findJgjProjectCountByYear(year);
		List<ContractStatus> contractStatuss = contractStatusDao.findByYear(year, false, null, typeIds, null);
		Map<String, List<ContractStatus>> contractMap = new HashMap<String, List<ContractStatus>>();
		for (ContractStatus contractStatus : contractStatuss) {
			String contractId = contractStatus.getContractId();
			if(contractMap.get(contractId) == null){
				List<ContractStatus> list = new ArrayList<ContractStatus>();
				list.add(contractStatus);
				contractMap.put(contractId, list);
			}else {
				contractMap.get(contractId).add(contractStatus);
			}
		}
		
		Map<String, List<DwJgjProjectCount>> dwMap = new HashMap<String, List<DwJgjProjectCount>>();
		Date startDate = String2Date(year + "-01-01");
		Date endDate = String2Date(year + "-12-31");
		for (DwJgjProjectCount dwJgjProjectCount : dwJgjProjectCounts) {
			String contractId = dwJgjProjectCount.getContractId();
			String projectId = dwJgjProjectCount.getProjectId();
			Double lastYearPayCount = null, thisYearPlanCount = null;
			Double contractPrice = dwJgjProjectCount.getContractPrice().doubleValue();
			List<ContractStatus> statusList = contractMap.get(contractId);
			if(statusList != null){
				for (ContractStatus contractStatus : statusList) {
					Date operateDate = String2Date(contractStatus.getOperateDate());
					String type = contractStatus.getType();
					if("3".equals(type)){	//实际支付
						if(operateDate.getTime() <= startDate.getTime()) { //至上年
							if (lastYearPayCount == null) lastYearPayCount = 0.0;
							lastYearPayCount += Double.valueOf(contractStatus.getMoney());
						}
					}else if("4".equals(type)){	//用款计划
						if(operateDate.getTime() >= startDate.getTime() && operateDate.getTime() <= endDate.getTime()){//本年
							if (thisYearPlanCount == null) thisYearPlanCount = 0.0;
							thisYearPlanCount += Double.valueOf(contractStatus.getMoney());	
						}
					}else if("1".equals(type)){ //合同变更
						if (contractPrice == null) contractPrice = 0.0;
						contractPrice += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if(dwJgjProjectCount.getNationVerifyPrice() != null)
				dwJgjProjectCount.setFinalPrice(dwJgjProjectCount.getNationVerifyPrice());
			else if(dwJgjProjectCount.getVerifyPrice() != null)
				dwJgjProjectCount.setFinalPrice(dwJgjProjectCount.getVerifyPrice());
			dwJgjProjectCount.setContractPrice(contractPrice != null ? BigDecimal.valueOf(contractPrice) : null);
			dwJgjProjectCount.setThisYearPlanCount(thisYearPlanCount != null ? BigDecimal.valueOf(thisYearPlanCount) : null);
			dwJgjProjectCount.setLastYearPayCount(lastYearPayCount != null ? BigDecimal.valueOf(lastYearPayCount) : null);
			
			if(dwMap.get(projectId) == null){
				List<DwJgjProjectCount> list = new ArrayList<DwJgjProjectCount>();
				list.add(dwJgjProjectCount);
				dwMap.put(projectId, list);
			}else {
				dwMap.get(projectId).add(dwJgjProjectCount);
			}
		}
		
		for (Entry<String, List<DwJgjProjectCount>> entry : dwMap.entrySet()) {
			Double contractPrice = null, finalPrice = null, thisYearPlanCount = null, lastYearPayCount = null;
			for (DwJgjProjectCount dwJgjProjectCount : entry.getValue()) {
				if(dwJgjProjectCount.getContractPrice() != null){
					if(contractPrice == null) contractPrice = 0.0;
					contractPrice += dwJgjProjectCount.getContractPrice().doubleValue();
				}
				if(dwJgjProjectCount.getFinalPrice() != null){
					if(finalPrice == null) finalPrice = 0.0;
					finalPrice += dwJgjProjectCount.getFinalPrice().doubleValue();
				}
				if(dwJgjProjectCount.getLastYearPayCount() != null){
					if(lastYearPayCount == null) lastYearPayCount = 0.0;
					lastYearPayCount += dwJgjProjectCount.getLastYearPayCount().doubleValue();
				}
				if(dwJgjProjectCount.getThisYearPlanCount() != null){
					if(thisYearPlanCount == null) thisYearPlanCount = 0.0;
					thisYearPlanCount += dwJgjProjectCount.getThisYearPlanCount().doubleValue();
				}
			}
			DwJgjProjectCount dw = entry.getValue().get(0);
			dw.setContractPrice(round(contractPrice,6));
			dw.setFinalPrice(round(finalPrice,6));
			dw.setLastYearPayCount(round(lastYearPayCount,6));
			dw.setThisYearPlanCount(round(thisYearPlanCount,6));
			
			List<MoneySource> moneySources = parseMoneySource(dw.getFeeDepartment());
			for(int i=0; i<moneySources.size(); i++){
				if(i == 0){
					dw.setFeeDepartment(moneySources.get(0).getUnitName());
					dw.setFeeMoney(moneySources.get(0).getMoney().toString());
				}else {
					DwJgjProjectCount temp = (DwJgjProjectCount)dw.clone();
					temp.setFeeDepartment(moneySources.get(i).getUnitName());
					temp.setFeeMoney(moneySources.get(i).getMoney().toString());
					jgjReportDao.saveProjectCount(temp);
				}
			}
			jgjReportDao.saveProjectCount(dw);
		}
	}
	
	@Override
	public void calculateDwJgjProjectProgress(String year) {
		List<String> typeIds = new ArrayList<String>();
		typeIds.add("2");
		typeIds.add("3");
		for(long season=1; season<=4; season++){
			List<DwJgjProjectProgress> dwJgjProjectProgresses = jgjReportDao.findJgjProjectProgressByYearAndSeason(year, season);
			List<ContractStatus> contractStatuss = contractStatusDao.findByYear(year, true, null, typeIds, season);
			Map<String, List<ContractStatus>> contractMap = new HashMap<String, List<ContractStatus>>();
			for (ContractStatus contractStatus : contractStatuss) {
				String contractId = contractStatus.getContractId();
				if(contractMap.get(contractId) == null){
					List<ContractStatus> list = new ArrayList<ContractStatus>();
					list.add(contractStatus);
					contractMap.put(contractId, list);
				}else {
					contractMap.get(contractId).add(contractStatus);
				}
			}
			
			Map<String, List<DwJgjProjectProgress>> dwMap = new HashMap<String, List<DwJgjProjectProgress>>();
			for (DwJgjProjectProgress dwJgjProjectProgress : dwJgjProjectProgresses) {
				String contractId = dwJgjProjectProgress.getContractId();
				String projectId = dwJgjProjectProgress.getProjectId();
				Double thisYearPayCount = null, thisYearPlanCount = null;
				Double contractPrice = dwJgjProjectProgress.getContractPrice().doubleValue();
				List<ContractStatus> statusList = contractMap.get(contractId);
				if(statusList != null){
					for (ContractStatus contractStatus : statusList) {
						String type = contractStatus.getType();
						if("3".equals(type)){	//实际支付
							if (thisYearPayCount == null) thisYearPayCount = 0.0;
							thisYearPayCount += Double.valueOf(contractStatus.getMoney());	
						}else if("4".equals(type)){	//用款计划
							if (thisYearPlanCount == null) thisYearPlanCount = 0.0;
							thisYearPlanCount += Double.valueOf(contractStatus.getMoney());	
						}else if("1".equals(type)){ //合同变更
							if (contractPrice == null) contractPrice = 0.0;
							contractPrice += Double.valueOf(contractStatus.getMoney());
						}
					}
				}
				if(dwJgjProjectProgress.getNationVerifyPrice() != null)
					dwJgjProjectProgress.setFinalPrice(dwJgjProjectProgress.getNationVerifyPrice());
				else if(dwJgjProjectProgress.getVerifyPrice() != null)
					dwJgjProjectProgress.setFinalPrice(dwJgjProjectProgress.getVerifyPrice());
				dwJgjProjectProgress.setContractPrice(round(contractPrice, 6));
				dwJgjProjectProgress.setThisYearPlanCount(round(thisYearPlanCount, 6));
				dwJgjProjectProgress.setThisYearPayCount(round(thisYearPayCount,6));
				
				if(dwMap.get(projectId) == null){
					List<DwJgjProjectProgress> list = new ArrayList<DwJgjProjectProgress>();
					list.add(dwJgjProjectProgress);
					dwMap.put(projectId, list);
				}else {
					dwMap.get(projectId).add(dwJgjProjectProgress);
				}
			}
			
			for (Entry<String, List<DwJgjProjectProgress>> entry : dwMap.entrySet()) {
				Double contractPrice = null, finalPrice = null, thisYearPlanCount = null, thisYearPayCount = null;
				for (DwJgjProjectProgress dwJgjProjectProgress : entry.getValue()) {
					if(dwJgjProjectProgress.getContractPrice() != null){
						if(contractPrice == null) contractPrice = 0.0;
						contractPrice += dwJgjProjectProgress.getContractPrice().doubleValue();
					}
					if(dwJgjProjectProgress.getFinalPrice() != null){
						if(finalPrice == null) finalPrice = 0.0;
						finalPrice += dwJgjProjectProgress.getFinalPrice().doubleValue();
					}
					if(dwJgjProjectProgress.getThisYearPayCount() != null){
						if(thisYearPayCount == null) thisYearPayCount = 0.0;
						thisYearPayCount += dwJgjProjectProgress.getThisYearPayCount().doubleValue();
					}
					if(dwJgjProjectProgress.getThisYearPlanCount() != null){
						if(thisYearPlanCount == null) thisYearPlanCount = 0.0;
						thisYearPlanCount += dwJgjProjectProgress.getThisYearPlanCount().doubleValue();
					}
				}
				DwJgjProjectProgress dw = entry.getValue().get(0);
				dw.setContractPrice(round(contractPrice,6));
				dw.setFinalPrice(round(finalPrice,6));
				dw.setThisYearPayCount(round(thisYearPayCount,6));
				dw.setThisYearPlanCount(round(thisYearPlanCount,6));
				
				List<MoneySource> moneySources = parseMoneySource(dw.getFeeDepartment());
				for(int i=0; i<moneySources.size(); i++){
					if(i == 0){
						dw.setFeeDepartment(moneySources.get(0).getUnitName());
						dw.setFeeMoney(moneySources.get(0).getMoney().toString());
					}else {
						DwJgjProjectProgress temp = (DwJgjProjectProgress)dw.clone();
						temp.setFeeDepartment(moneySources.get(i).getUnitName());
						temp.setFeeMoney(moneySources.get(i).getMoney().toString());
						jgjReportDao.saveProjectProgress(temp);
					}
				}
				jgjReportDao.saveProjectProgress(dw);
			}
		}
		
	}

	@Override
	public void calculateDwJgjMajor(String year) {
		List<String> overhaulType = new ArrayList<String>();
		overhaulType.add("3");
		calculateDwJgjMajorByType(year, overhaulType);
		List<String> updateType = new ArrayList<String>();
		updateType.add("2");
		calculateDwJgjMajorByType(year, updateType);
	}
	
	public void calculateDwJgjMajorByType(String year, List<String> typeIds) {

		List<DwJgjMajor> dwJgjMajors = jgjReportDao.findJgjMajorByYear(year);
		Map<String, List<ContractStatus>> contractMap1 = switchContractStatus(year, "1", typeIds, null);
		Map<String, List<ContractStatus>> contractMap2 = switchContractStatus(year, "2", typeIds, null);
		Map<String, List<ContractStatus>> contractMap3 = switchContractStatus(year, "3", typeIds, null);
		Map<String, List<ContractStatus>> contractMap4 = switchContractStatus(year, "4", typeIds, null);
		Map<String, List<ContractStatus>> contractMap5 = switchContractStatus(year, "5", typeIds, null);
		Map<String, List<ContractStatus>> contractMap6 = switchContractStatus(year, "6", typeIds, null);
		for (DwJgjMajor dwJgjMajor : dwJgjMajors) {
			String line = dwJgjMajor.getLineName();
			Double carPlanCount = null, carActualCount = null, elePlanCount = null, eleActualCount = null;
			Double numPlanCount = null, numActualCount = null, workPlanCount = null, workActualCount = null;
			Double basePlanCount = null, baseActualCount = null, stationPlanCount = null, stationActualCount = null;
			
			List<ContractStatus> statusList1 = contractMap1.get(line);
			List<ContractStatus> statusList2 = contractMap2.get(line);
			List<ContractStatus> statusList3 = contractMap3.get(line);
			List<ContractStatus> statusList4 = contractMap4.get(line);
			List<ContractStatus> statusList5 = contractMap5.get(line);
			List<ContractStatus> statusList6 = contractMap6.get(line);
			if (statusList1 != null) {
				for (ContractStatus contractStatus : statusList1) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (carPlanCount == null) carPlanCount = 0.0;
						carPlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (carActualCount == null) carActualCount = 0.0;
						carActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList2 != null) {
				for (ContractStatus contractStatus : statusList2) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (elePlanCount == null) elePlanCount = 0.0;
						elePlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (eleActualCount == null) eleActualCount = 0.0;
						eleActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList3 != null) {
				for (ContractStatus contractStatus : statusList3) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (numPlanCount == null) numPlanCount = 0.0;
						numPlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (numActualCount == null) numActualCount = 0.0;
						numActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList4 != null) {
				for (ContractStatus contractStatus : statusList4) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (workPlanCount == null) workPlanCount = 0.0;
						workPlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (workActualCount == null) workActualCount = 0.0;
						workActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList5 != null) {
				for (ContractStatus contractStatus : statusList5) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (basePlanCount == null) basePlanCount = 0.0;
						basePlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (baseActualCount == null) baseActualCount = 0.0;
						baseActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList6 != null) {
				for (ContractStatus contractStatus : statusList6) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (stationPlanCount == null) stationPlanCount = 0.0;
						stationPlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (stationActualCount == null) stationActualCount = 0.0;
						stationActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			dwJgjMajor.setTypename(typeIds.get(0));
			dwJgjMajor.setCarPlanCount(round(carPlanCount, 6));
			dwJgjMajor.setCarActualCount(round(carActualCount, 6));
			dwJgjMajor.setElePlanCount(round(elePlanCount, 6));
			dwJgjMajor.setEleActualCount(round(eleActualCount, 6));
			dwJgjMajor.setNumPlanCount(round(numPlanCount, 6));
			dwJgjMajor.setNumActualCount(round(numActualCount, 6));
			dwJgjMajor.setWorkPlanCount(round(workPlanCount, 6));
			dwJgjMajor.setWorkActualCount(round(workActualCount, 6));
			dwJgjMajor.setStationPlanCount(round(stationPlanCount, 6));
			dwJgjMajor.setStationActualCount(round(stationActualCount, 6));
			dwJgjMajor.setBasePlanCount(round(basePlanCount, 6));
			dwJgjMajor.setBaseActualCount(round(baseActualCount, 6));
			if(carPlanCount != null || elePlanCount != null || numPlanCount != null 
					|| workPlanCount != null || stationPlanCount != null || basePlanCount != null){
				Double allPlanCount = 0.0;
				if (carPlanCount != null) allPlanCount += carPlanCount;
				if (elePlanCount != null) allPlanCount += elePlanCount;
				if (numPlanCount != null) allPlanCount += numPlanCount;
				if (workPlanCount != null) allPlanCount += workPlanCount;
				if (stationPlanCount != null) allPlanCount += stationPlanCount;
				if (basePlanCount != null) allPlanCount += basePlanCount;
				dwJgjMajor.setAllPlanCount(round(allPlanCount, 6));
			}
			if(carActualCount != null || eleActualCount != null || numActualCount != null 
					|| workActualCount != null || stationActualCount != null || baseActualCount != null){
				Double allActualCount = 0.0;
				if (carActualCount != null) allActualCount += carActualCount;
				if (eleActualCount != null) allActualCount += eleActualCount;
				if (numActualCount != null) allActualCount += numActualCount;
				if (workActualCount != null) allActualCount += workActualCount;
				if (stationActualCount != null) allActualCount += stationActualCount;
				if (baseActualCount != null) allActualCount += baseActualCount;
				dwJgjMajor.setAllActualCount(round(allActualCount, 6));
			}
			jgjReportDao.save(dwJgjMajor);
		}
	}
	
	@Override
	public void calculateDwJgjMajorCount(String year) {
		List<String> overhaulType = new ArrayList<String>();
		overhaulType.add("3");
		List<String> updateType = new ArrayList<String>();
		updateType.add("2");
		List<DwJgjMajorCount> dwJgjMajorCounts = jgjReportDao.findJgjMajorCountByYear(year);
		Map<String, List<ContractStatus>> contractMapByOverhaul = switchContractStatus(year, null, overhaulType, null);
		Map<String, List<ContractStatus>> contractMapByUpdate = switchContractStatus(year, null, updateType, null);
		for (DwJgjMajorCount dwJgjMajorCount : dwJgjMajorCounts) {
			String line = dwJgjMajorCount.getLineName();
			Double overhaulPlanCount = null, overhaulActualCount = null, updatePlanCount = null, updateActualCount = null;
			List<ContractStatus> statusList1 = contractMapByOverhaul.get(line);
			List<ContractStatus> statusList2 = contractMapByUpdate.get(line);
			if (statusList1 != null) {
				for (ContractStatus contractStatus : statusList1) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (overhaulPlanCount == null) overhaulPlanCount = 0.0;
						overhaulPlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (overhaulActualCount == null) overhaulActualCount = 0.0;
						overhaulActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			if (statusList2 != null) {
				for (ContractStatus contractStatus : statusList2) {
					if("4".equals(contractStatus.getType())){	//计划用款
						if (updatePlanCount == null) updatePlanCount = 0.0;
						updatePlanCount += Double.valueOf(contractStatus.getMoney());
					}else if ("3".equals(contractStatus.getType())){ //实际支付
						if (updateActualCount == null) updateActualCount = 0.0;
						updateActualCount += Double.valueOf(contractStatus.getMoney());
					}
				}
			}
			dwJgjMajorCount.setOverhaulPlanCount(round(overhaulPlanCount, 6));
			dwJgjMajorCount.setOverhaulActualCount(round(overhaulActualCount, 6));
			dwJgjMajorCount.setUpdatePlanCount(round(updatePlanCount, 6));
			dwJgjMajorCount.setUpdateActualCount(round(updateActualCount, 6));
			if(overhaulPlanCount != null || updatePlanCount != null ){
				Double allPlanCount = 0.0;
				if (overhaulPlanCount != null) allPlanCount += overhaulPlanCount;
				if (updatePlanCount != null) allPlanCount += updatePlanCount;
				dwJgjMajorCount.setAllPlanCount(round(allPlanCount, 6));
			}
			if(overhaulActualCount != null || updateActualCount != null ){
				Double allActualCount = 0.0;
				if (overhaulActualCount != null) allActualCount += overhaulActualCount;
				if (updateActualCount != null) allActualCount += updateActualCount;
				dwJgjMajorCount.setAllActualCount(round(allActualCount, 6));
			}
			jgjReportDao.saveDwJgjMajorCount(dwJgjMajorCount);
		}
	}
	
	@Override
	public void calculateDwJgjMajorSeason(String year) {
		List<String> overhaulType = new ArrayList<String>();
		overhaulType.add("3");
		List<String> updateType = new ArrayList<String>();
		updateType.add("2");
		for(long season=1; season<=4; season++){
			List<DwJgjMajorSeason> dwJgjMajorSeasons = jgjReportDao.findJgjMajorSeasonByYearAndSeason(year, season);
			Map<String, List<ContractStatus>> contractMapByOverhaul = switchContractStatus(year, null, overhaulType, season);
			Map<String, List<ContractStatus>> contractMapByUpdate = switchContractStatus(year, null, updateType, season);
			for (DwJgjMajorSeason dwJgjMajorSeason : dwJgjMajorSeasons) {
				String line = dwJgjMajorSeason.getLineName();
				Double overhaulPlanCount = null, overhaulActualCount = null, updatePlanCount = null, updateActualCount = null;
				List<ContractStatus> statusList1 = contractMapByOverhaul.get(line);
				List<ContractStatus> statusList2 = contractMapByUpdate.get(line);
				if (statusList1 != null) {
					for (ContractStatus contractStatus : statusList1) {
						if("4".equals(contractStatus.getType())){	//计划用款
							if (overhaulPlanCount == null) overhaulPlanCount = 0.0;
							overhaulPlanCount += Double.valueOf(contractStatus.getMoney());
						}else if ("3".equals(contractStatus.getType())){ //实际支付
							if (overhaulActualCount == null) overhaulActualCount = 0.0;
							overhaulActualCount += Double.valueOf(contractStatus.getMoney());
						}
					}
				}
				if (statusList2 != null) {
					for (ContractStatus contractStatus : statusList2) {
						if("4".equals(contractStatus.getType())){	//计划用款
							if (updatePlanCount == null) updatePlanCount = 0.0;
							updatePlanCount += Double.valueOf(contractStatus.getMoney());
						}else if ("3".equals(contractStatus.getType())){ //实际支付
							if (updateActualCount == null) updateActualCount = 0.0;
							updateActualCount += Double.valueOf(contractStatus.getMoney());
						}
					}
				}
				dwJgjMajorSeason.setOverhaulPlanCount(round(overhaulPlanCount, 6));
				dwJgjMajorSeason.setOverhaulActualCount(round(overhaulActualCount, 6));
				dwJgjMajorSeason.setUpdatePlanCount(round(updatePlanCount, 6));
				dwJgjMajorSeason.setUpdateActualCount(round(updateActualCount, 6));
				if(overhaulPlanCount != null || updatePlanCount != null ){
					Double allPlanCount = 0.0;
					if (overhaulPlanCount != null) allPlanCount += overhaulPlanCount;
					if (updatePlanCount != null) allPlanCount += updatePlanCount;
					dwJgjMajorSeason.setAllPlanCount(round(allPlanCount, 6));
				}
				if(overhaulActualCount != null || updateActualCount != null ){
					Double allActualCount = 0.0;
					if (overhaulActualCount != null) allActualCount += overhaulActualCount;
					if (updateActualCount != null) allActualCount += updateActualCount;
					dwJgjMajorSeason.setAllActualCount(round(allActualCount, 6));
				}
				jgjReportDao.saveDwJgjMajorSeason(dwJgjMajorSeason);
			}
		}
	}
	
	/**
	 * 查询CONTRACT_STATUS
	 * @param year 年份
	 * @param professionalType 专业类型
	 * @param typeIds 项目类型ids
	 * @param season 季度
	 * @return
	 */
	public Map<String, List<ContractStatus>> switchContractStatus(String year, String professionalType, List<String> typeIds, Long season){
		List<ContractStatus> contractStatuss = contractStatusDao.findByYear(year, true, professionalType, typeIds, season);
		Map<String, List<ContractStatus>> contractMap = new HashMap<String, List<ContractStatus>>();
		for (ContractStatus contractStatus : contractStatuss) {
			String line = contractStatus.getLine();
			if(contractMap.get(line) == null){
				List<ContractStatus> list = new ArrayList<ContractStatus>();
				list.add(contractStatus);
				contractMap.put(line, list);
			}else {
				contractMap.get(line).add(contractStatus);
			}
		}
		
		return contractMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoneySource> parseMoneySource(String moneySource){
		//StringBuilder feeDepartment = new StringBuilder();
		List<MoneySource> stats = new ArrayList<MoneySource>();
		if(StringUtils.isNotEmpty(moneySource)){
			JSONObject jsonObj  = JSONObject.fromObject(moneySource);
			JSONArray jsonArray = jsonObj.getJSONArray("moneySource");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setArrayMode( JsonConfig.MODE_LIST );
			jsonConfig.setRootClass(MoneySource.class );
			stats = (List<MoneySource>) JSONSerializer.toJava( jsonArray, jsonConfig );
//			for(MoneySource ms : stats){
//				if(feeDepartment.length() == 0)
//					feeDepartment.append(ms.getUnitName() + " : " + ms.getMoney());
//				else
//					feeDepartment.append(" \r\n" +ms.getUnitName() + " : " + ms.getMoney());
//			}
		}
		return stats;
	}

	public Date String2Date(String strDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date currentdate = null;
		try {
			currentdate = sdf.parse(strDate);
		} catch (ParseException e) {
		}
		return currentdate;
	}
	
	public BigDecimal round(Double value, int scale){
		if (value == null) return null;
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	@Override
	public List<DwJgjProjectCount> findByYearAndTypeJgjProjectCount(String year) {
		return jgjReportDao.findByYearJgjProjectCount(year);
	}
	@Override
	public List<String> findAllYearJgjProjectCount() {
		return jgjReportDao.findAllYearJgjProjectCount();
	}
	@Override
	public List<String> findAllYearJgjMajorCount() {
		return jgjReportDao.findAllYearJgjMajorCount();
	}
	@Override
	public List<DwJgjMajorCount> findByYearJgjMajorCount(String year) {
		return jgjReportDao.findByYearJgjMajorCount(year);
	}
	@Override
	public List<String> findAllYearJgjProjectProgress() {
		
		return jgjReportDao.findAllYearJgjProjectProgress();
	}
	@Override
	public List<DwJgjProjectProgress> findByYearJgjProjectProgress(String year,String season) {
		
		return jgjReportDao.findByYearJgjProjectProgress(year, season);
	}
	
	
	
	
	@Override
	public List<String> findAllYearJgjMajorSeason() {
		
		return jgjReportDao.findAllYearJgjMajorSeason();
	}
	@Override
	public List<DwJgjMajorSeason> findByYearJgjMajorSeason(String year,String season) {
		
		return jgjReportDao.findByYearJgjMajorSeason(year, season);
	}

	@Override
	public void deleteAllDwJgjProjectCountByYear(String year) {
		jgjReportDao.deleteAllDwJgjProjectCountByYear(year);
	}
	@Override
	public void deleteAllDwJgjMajorCountByYear(String year) {
		jgjReportDao.deleteAllDwJgjMajorCountByYear(year);
	}
	@Override
	public void deleteAllDwJgjMajorByYear(String year) {
		jgjReportDao.deleteAllDwJgjMajorByYear(year);
	}
	@Override
	public void deleteAllDwJgjProjectByYear(String year) {
		jgjReportDao.deleteAllDwJgjProjectByYear(year);
	}
	@Override
	public void deleteAllDwJgjMajorSeasonByYear(String year) {
		jgjReportDao.deleteAllDwJgjMajorSeasonByYear(year);
	}
	@Override
	public void deleteAllDwJgjProjectProgressByYear(String year) {
		jgjReportDao.deleteAllDwJgjProjectProgressByYear(year);
	}
	
}
