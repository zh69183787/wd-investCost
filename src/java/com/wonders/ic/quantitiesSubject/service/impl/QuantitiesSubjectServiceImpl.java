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

package com.wonders.ic.quantitiesSubject.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.quantitiesList.dao.QuantitiesListDao;
import com.wonders.ic.quantitiesSubject.dao.QuantitiesSubjectDao;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wonders.ic.quantitiesSubject.service.QuantitiesSubjectService;
import com.wonders.ic.wbs.dao.WbsDao;
import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesSubjectServiceImpl implements QuantitiesSubjectService {
	private QuantitiesSubjectDao quantitiesSubjectDao;
	private QuantitiesListDao quantitiesListDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private WbsDao wbsDao;

	
	public void setQuantitiesListDao(QuantitiesListDao quantitiesListDao) {
		this.quantitiesListDao = quantitiesListDao;
	}

	public void setQuantitiesSubjectDao(
			QuantitiesSubjectDao quantitiesSubjectDao) {
		this.quantitiesSubjectDao = quantitiesSubjectDao;
	}

	public void setWbsDao(WbsDao wbsDao) {
		this.wbsDao = wbsDao;
	}

	public Page findQuantitiesSubjectByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return quantitiesSubjectDao.findQuantitiesSubjectByPage(filter, pageNo,
				pageSize);
	}

	@Override
	public List<QuantitiesSubject> findByContractId(String contractId) {
		return quantitiesSubjectDao.findByContractId(contractId);
	}

	@Override
	public void saveQuantitiesSubject(QuantitiesSubject quantitiesSubject) {
		quantitiesSubjectDao.save(quantitiesSubject);
	}

	@Override
	public QuantitiesSubject findById(String id) {
		return quantitiesSubjectDao.findById(id);
	}

	@Override
	public void update(QuantitiesSubject quantitiesSubject) {
		quantitiesSubjectDao.update(quantitiesSubject);
	}

	@Override
	public void deleteAllByIdsOnLogical(String[] ids) {
		quantitiesSubjectDao.deleteAllByIdsOnLogical(ids);
	}

	@Override
	public String findSumOfAllPriceByContractId(String contractId) {
		BigDecimal bd = quantitiesSubjectDao.findSumOfAllPriceByContractId(contractId);
		if(bd==null ) return null;
		return bd.toString();
	}
	
	@Override
	public Wbs saveAddOnPage(QuantitiesSubject quantitiesSubject,String parentId, String wbsId,String level,String order,String ifHasSpecial,String specialOrder) {
		Wbs wbs = null;
		if(parentId==null || "".equals(parentId)){
			parentId = "0";
		}
		
		if(quantitiesSubject.getContractId()!=null && !"".equals(quantitiesSubject.getContractId())
				&& quantitiesSubject.getId()!=null && !"".equals(quantitiesSubject.getId())){
			
			wbs = new Wbs();
			wbs.setWbsId(wbsId);
			
			wbs.setParentId(parentId);
			wbs.setNodeId(quantitiesSubject.getId());
			wbs.setWbsObjectId(quantitiesSubject.getContractId());
			if(level==null || "".equals(level)){
				level = "1";
			}
			if(order==null || "".equals(order)){
				order = "1";
			}
			try {
				wbs.setWbsLevel(Long.valueOf(level));
				wbs.setWbsOrder(Long.valueOf(order));
			} catch (NumberFormatException e) {
				return null;
			}
			wbs.setRemoved("0");
			wbs.setType("1");
			wbs.setWbsObject("2");		//1:挂在项目下,2:挂在合同下
			wbs.setCreateTime(sdf.format(new Date()));
			wbsDao.updateWbsOrderByIncrease("2",wbs.getWbsObjectId(), wbs.getParentId(), wbs.getWbsLevel(), wbs.getWbsOrder(),ifHasSpecial,specialOrder);
			wbsDao.save(wbs);
		}
		
		
		return wbs;
	}

	@Override
	public void deleteAllSubjectAndWbsLogically(List<Wbs> wbsList) {
		if(wbsList==null || wbsList.size()<1) return;
		String[] subjectIds = new String[wbsList.size()];
		for(int i=0; i<wbsList.size(); i++){
			subjectIds[i] = wbsList.get(i).getNodeId();
		}
		Wbs top = wbsList.get(0);
		deleteAllByIdsOnLogical(subjectIds);
		wbsDao.deleteAllOnLogically(wbsList);
		wbsDao.updateWbsOrderByDecrease(top.getWbsObject(), top.getWbsObjectId(), top.getParentId(), top.getWbsLevel(), top.getWbsOrder());
		
	}
	
	@Override
	public String saveAddOnPageSpecial(QuantitiesSubject quantitiesSubject,String parentId, String wbsId,String level,String order,String specialOrder) {
		
		
		if(parentId==null || "".equals(parentId)){
			parentId = "0";
		}
		
		if(quantitiesSubject.getContractId()!=null && !"".equals(quantitiesSubject.getContractId())
				&& quantitiesSubject.getId()!=null && !"".equals(quantitiesSubject.getId())){
			
			Wbs wbs = new Wbs();
			wbs.setWbsId(wbsId);
			
			wbs.setParentId(parentId);
			wbs.setNodeId(quantitiesSubject.getId());
			wbs.setWbsObjectId(quantitiesSubject.getContractId());
			if(level==null || "".equals(level)){
				level = "1";
			}
			if(order==null || "".equals(order)){
				order = "1";
			}
			try {
				wbs.setWbsLevel(Long.valueOf(level));
				wbs.setWbsOrder(Long.valueOf(order));
				wbs.setSpecialOrder(Long.valueOf(specialOrder)+1);
			} catch (NumberFormatException e) {
				return "fail";
			}
			wbs.setRemoved("0");
			wbs.setType("2");
			wbs.setWbsObject("2");		//1:挂在项目下,2:挂在合同下
			wbs.setCreateTime(sdf.format(new Date()));
			wbsDao.updateWbsSpecialOrder("2",wbs.getWbsObjectId(), wbs.getParentId(), wbs.getWbsLevel(), wbs.getWbsOrder(),Long.valueOf(specialOrder));
			wbsDao.save(wbs);
		}
		
		
		return "success";
	}
	
	@Override
	public void deleteWbsSpecial(List<Wbs> wbsList) {
		if(wbsList==null || wbsList.size()<1) return;
		wbsDao.deleteAllOnLogically(wbsList);
	}

	@Override
	public void updateCompute(String object, String objectId) {
		List<QuantitiesSubject> updateList  = new ArrayList<QuantitiesSubject>();
		List<String> firstLevelIdList = new ArrayList<String>();		//第一层的概算科目id
		
		Wbs lastLevelAndOrderWbs = wbsDao.findLastLevelByObjectAndObjectId(object, objectId);	//最后一层,最后一个节点
		if(lastLevelAndOrderWbs==null) return ;
		
		for(long i=lastLevelAndOrderWbs.getWbsLevel(); i>=1; i--){								//循环level
			List<Wbs> wbsList = wbsDao.findAllByObjectAndObjectIdAndLevel("2", objectId, i);		//指定层的所有数据
			if(i!=1){
				if(wbsList!=null && wbsList.size()>0){
					Map<String, List<String>> wbsMap = new HashMap<String, List<String>>();
					for(int j=0; j<wbsList.size(); j++){											//循环本层的list
						if(wbsMap.get(wbsList.get(j).getParentId())==null){
							List<String> tempList = new ArrayList<String>();
							tempList.add(wbsList.get(j).getNodeId());
							wbsMap.put(wbsList.get(j).getParentId(),tempList);
						}else{
							wbsMap.get(wbsList.get(j).getParentId()).add(wbsList.get(j).getNodeId());
						}
					}
					
					Iterator<String> it = wbsMap.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next();
						List<Object[]> resultList = quantitiesSubjectDao.findSumOfAmoutAndDecideByIdList(wbsMap.get(key));
						if(resultList==null || resultList.size()<1) continue;		//1.总数；,2-5投资批复
						QuantitiesSubject qs = quantitiesSubjectDao.findByWbsId(key);
						
						qs.setAmount(Double.valueOf(resultList.get(0)[0].toString()).toString());
						qs.setTotalPrice(getFormattedMoney(resultList.get(0)[1].toString()));
						qs.setOperateDate(sdf.format(new Date()));
					
						updateList.add(qs);
					}
				}
			}else{
				if(wbsList!=null && wbsList.size()>0){
					for(int j=0; j<wbsList.size(); j++){
						firstLevelIdList.add(wbsList.get(j).getNodeId());
					}
				}
			}
		}
		quantitiesListDao.updateFirstLevelAmountAndTotalPrice("1",objectId,firstLevelIdList);	//计算概算表
		
		if(updateList!=null && updateList.size()>0){
			quantitiesSubjectDao.updateList(updateList);				//普通行已算好
		}
		
		
	}
	
	public void updateSpecialData(String[] ids,String number[],String money[]){
		List<QuantitiesSubject> updateList = null;
		if(ids!=null && ids.length>0){
			updateList = new ArrayList<QuantitiesSubject>();
			for(int i=0; i<ids.length; i++){
 				QuantitiesSubject qs = quantitiesSubjectDao.load(ids[i]);
				if(qs!=null && number[i]!=null && money[i]!=null){
					qs.setOperateDate(sdf.format(new Date()));
					qs.setAmount(number[i]);
					qs.setTotalPrice(money[i]);
					updateList.add(qs);
				}
			}
		}
		if(updateList!=null && updateList.size()>0){
			quantitiesSubjectDao.updateList(updateList);
		}
	}
	
	@Override
	public List<String> findSumOfTotalPriceByContractIdAndEstimateSubjectId(List<String> contractIdList, String estimateSubjectId) {
		if(contractIdList==null || contractIdList.size()<1)
			return null;
		return quantitiesSubjectDao.findSumOfTotalPriceByContractIdAndEstimateSubjectId(contractIdList, estimateSubjectId);
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

	@Override
	public void updateInvestEstimateIdAndNameNull(String investEstimateId) {
		quantitiesSubjectDao.updateInvestEstimateIdAndNameNull(investEstimateId);
	}

	@Override
	public void updateInvestEstimateName(String investEstimateId,String investEstimateName) {
		quantitiesSubjectDao.updateInvestEstimateName(investEstimateId, investEstimateName);
	}

	@Override
	public void updateContractNameByContractId(String contractId,String contractNo) {
		quantitiesSubjectDao.updateContractNameByContractId(contractId, contractNo);
	}

	
	
	
	
}
