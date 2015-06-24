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

package com.wonders.ic.investEstimateSubject.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.investEstimate.dao.InvestEstimateDao;
import com.wonders.ic.investEstimateSubject.dao.InvestEstimateSubjectDao;
import com.wonders.ic.investEstimateSubject.entity.bo.InvestEstimateSubject;
import com.wonders.ic.investEstimateSubject.service.InvestEstimateSubjectService;
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

public class InvestEstimateSubjectServiceImpl implements InvestEstimateSubjectService {
	private InvestEstimateSubjectDao investEstimateSubjectDao;
	private WbsDao wbsDao;
	private InvestEstimateDao investEstimateDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void setWbsDao(WbsDao wbsDao) {
		this.wbsDao = wbsDao;
	}

	public void setInvestEstimateDao(InvestEstimateDao investEstimateDao) {
		this.investEstimateDao = investEstimateDao;
	}

	public void setInvestEstimateSubjectDao(
			InvestEstimateSubjectDao investEstimateSubjectDao) {
		this.investEstimateSubjectDao = investEstimateSubjectDao;
	}

	public Page findInvestEstimateSubjectByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		return investEstimateSubjectDao.findInvestEstimateSubjectByPage(filter,pageNo, pageSize);
	}

	@Override
	public void saveEstimateSubject(InvestEstimateSubject estimateSubject) {
		investEstimateSubjectDao.save(estimateSubject);
	}

	@Override
	public InvestEstimateSubject findEstimateSubjectById(String id) {
		return investEstimateSubjectDao.load(id);
	}

	@Override
	public void update(InvestEstimateSubject investEstimateSubject) {
		investEstimateSubjectDao.update(investEstimateSubject);
	}

	@Override
	public void deleteById(String id) {
		investEstimateSubjectDao.deleteById(id);
	}

	@Override
	public InvestEstimateSubject findLastHierarchyByLevel(String projectId,String level) {
		List<InvestEstimateSubject> list = investEstimateSubjectDao.findAllHierarchyByLevel(projectId, level);
		if(list==null || list.size()==0){
			return null;
		}else{
			return list.get(list.size()-1);
		}
	}

	@Override
	public void deleteByIdsOnLogically(String[] ids) {
		investEstimateSubjectDao.deleteAllOnLogically(ids);
	}

	@Override
	public void deleteByIdLogical(String id) {
		String ids[] = {id};
		investEstimateSubjectDao.deleteByIdLogical(ids);
	}

	@Override
	public List<InvestEstimateSubject> findByIds(String[] ids) {

		return investEstimateSubjectDao.findByIds(ids);
	}

	@Override
	public InvestEstimateSubject findById(String id) {
		String[] ids = new String[]{id}; 
		List<InvestEstimateSubject> list = investEstimateSubjectDao.findByIds(ids);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	@Override
	public List<String> findEstimateDecide(String projectId,List<String> idList) {
		if(idList==null && idList.size()<1){
			return null;
		}
		String[] idArray = new String[idList.size()];
		for(int i=0; i<idList.size(); i++){
			idArray[i] = idList.get(i);
		}
		
		Object[] list = investEstimateSubjectDao.findEstimateDecide(projectId,idArray);
		List<String> resultList = null;
		if(list!=null && list.length>0){
			resultList = new ArrayList<String>();
			for(int i=0; i<list.length; i++){
				if(list[i]!=null){
					BigDecimal bd = (BigDecimal) list[i];
					resultList.add(bd.toString());
				}else{
					resultList.add("0");
				}
			}
		}
		return resultList;
	}

	@Override
	public Wbs saveAddOnPage(InvestEstimateSubject investEstimateSubject,String parentId, String wbsId,String level,String order,String ifHasSpecial,String specialOrder) {
		Wbs wbs = null;
		
		
		if(parentId==null || "".equals(parentId)){
			parentId = "0";
		}
		
		if(investEstimateSubject.getProjectId()!=null && !"".equals(investEstimateSubject.getProjectId())
				&& investEstimateSubject.getId()!=null && !"".equals(investEstimateSubject.getId())){
			
			wbs = new Wbs();
			wbs.setWbsId(wbsId);
			
			wbs.setParentId(parentId);
			wbs.setNodeId(investEstimateSubject.getId());
			wbs.setWbsObjectId(investEstimateSubject.getProjectId());
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
			wbs.setWbsObject("1");		//1:挂在项目下,2:挂在合同下
			wbs.setCreateTime(sdf.format(new Date()));
			wbs.setType("1");
			wbsDao.updateWbsOrderByIncrease("1",wbs.getWbsObjectId(), wbs.getParentId(), wbs.getWbsLevel(), wbs.getWbsOrder(),ifHasSpecial,specialOrder);
			wbsDao.save(wbs);
		}
		
		
		return wbs;
	}

	@Override
	public void deleteAllSubjectAndWbsLogical(List<Wbs> wbsList) {
		if(wbsList==null || wbsList.size()<1) return;
		String[] subjectIds = new String[wbsList.size()];
		for(int i=0; i<wbsList.size(); i++){
			subjectIds[i] = wbsList.get(i).getNodeId();
		}
		Wbs top = wbsList.get(0);
		deleteByIdsOnLogically(subjectIds);
		wbsDao.deleteAllOnLogically(wbsList);
		wbsDao.updateWbsOrderByDecrease(top.getWbsObject(), top.getWbsObjectId(), top.getParentId(), top.getWbsLevel(), top.getWbsOrder());
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

	
	@Override
	public String saveAddOnPageSpecial(InvestEstimateSubject investEstimateSubject,String parentId,String wbsId,String level,String order,String specialOrder) {
		
		investEstimateSubject.setRemoved("0");
		investEstimateSubject.setCreateDate(sdf.format(new Date()));
		investEstimateSubject.setUpdateDate(sdf.format(new Date()));
		
		saveEstimateSubject(investEstimateSubject); 	//保存科目
		
		
		if(investEstimateSubject.getProjectId()!=null && !"".equals(investEstimateSubject.getProjectId())
				&& investEstimateSubject.getId()!=null && !"".equals(investEstimateSubject.getId())){
			
			Wbs wbs = new Wbs();
			wbs.setWbsId(wbsId);
			wbs.setParentId(parentId);
			wbs.setNodeId(investEstimateSubject.getId());
			wbs.setWbsObjectId(investEstimateSubject.getProjectId());
			
			try {
				wbs.setWbsLevel(Long.valueOf(level));
				wbs.setWbsOrder(Long.valueOf(order));
				wbs.setSpecialOrder(Long.valueOf(specialOrder)+1);
			} catch (NumberFormatException e) {
				return "fail";
			}
			wbs.setRemoved("0");
			wbs.setWbsObject("1");		//1:挂在项目下,2:挂在合同下
			wbs.setCreateTime(sdf.format(new Date()));
			wbs.setType("2");//2为特殊行
			wbsDao.updateWbsSpecialOrder("1",wbs.getWbsObjectId(), wbs.getParentId(), wbs.getWbsLevel(), wbs.getWbsOrder(),Long.valueOf(specialOrder));
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
	public String compute(String object, String objectId) {
		List<InvestEstimateSubject> updateList  = null;
		List<String> firstLevelIdList = new ArrayList<String>();		//第一层的概算科目id
		
		Wbs lastLevelAndOrderWbs = wbsDao.findLastLevelByObjectAndObjectId(object, objectId);	//最后一层,最后一个节点
		if(lastLevelAndOrderWbs==null) return "fail";
		
		for(long i=lastLevelAndOrderWbs.getWbsLevel(); i>=1; i--){								//循环level
			List<Wbs> wbsList = wbsDao.findAllByObjectAndObjectIdAndLevel("1", objectId, i);		//指定层的所有数据
			updateList = new ArrayList<InvestEstimateSubject>();
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
						List<Object[]> sumResult = investEstimateSubjectDao.findSumOfAmoutAndDecideByIdList(wbsMap.get(key));
						if(sumResult==null || sumResult.size()<1) continue;		//1.总数；,2-5投资批复
						InvestEstimateSubject ies = investEstimateSubjectDao.findByWbsId(key);
						Double projectSum;
						Double a1,a2,a3,a4,decideSum;
						projectSum = Double.valueOf((String.valueOf(sumResult.get(0)[0])));
						a1 = Double.valueOf(sumResult.get(0)[1].toString());
						a2 = Double.valueOf(sumResult.get(0)[2].toString());
						a3 = Double.valueOf(sumResult.get(0)[3].toString());
						a4 = Double.valueOf(sumResult.get(0)[4].toString());
						decideSum = a1 + a2 + a3 + a4;
						ies.setProjectAmount(projectSum.toString());
						ies.setEstimateDecideFirst(getFormattedMoney(a1.toString()));
						ies.setEstimateDecideSecond(getFormattedMoney(a2.toString()));
						ies.setEstimateDecideThird(getFormattedMoney(a3.toString()));
						ies.setEstimateDecideOther(getFormattedMoney(a4.toString()));
						ies.setEstimateDecideSum(getFormattedMoney(decideSum.toString()));
						if(projectSum!=0){
							ies.setEconomicIndicator(getFormattedMoney(String.valueOf((decideSum/projectSum))));
						}else{
							ies.setEconomicIndicator(getFormattedMoney(decideSum.toString()));
						}
						ies.setUpdateDate(sdf.format(new Date()));
						updateList.add(ies);
					}
					investEstimateSubjectDao.updateList(updateList);
					//updateList = null;
				}
			}else{
				if(wbsList!=null && wbsList.size()>0){
					for(int j=0; j<wbsList.size(); j++){
						firstLevelIdList.add(wbsList.get(j).getNodeId());
					}
				}
			}
		}
		
		investEstimateDao.updateFirstLevelAllDecide("1",objectId,firstLevelIdList);	//计算工程量表
		
		if(updateList!=null && updateList.size()>0){
			investEstimateSubjectDao.updateList(updateList);				//普通行已算好
		}
		return "";
	}
	

	@Override
	public List<InvestEstimateSubject> findAllBySubjectNameWithFuzzySearch(String projectId,String subjectName) {
		return investEstimateSubjectDao.findAllByProjectIdAndSubjectNameWithFuzzySearch(projectId, subjectName);
	}

	@Override
	public void updateSpecilData(String[] ids, String[] decides) {
		List<InvestEstimateSubject> updateList = null;
		if(ids!=null && ids.length>0){
			updateList = new ArrayList<InvestEstimateSubject>();
			for(int i=0; i<ids.length; i++){
				String[] decide = decides[i].split(",");
 				InvestEstimateSubject ies = investEstimateSubjectDao.load(ids[i]);
				if(ies!=null && decide!=null && decide.length>0){
					ies.setEstimateDecideFirst(getFormattedMoney(decide[0]));
					ies.setEstimateDecideSecond(getFormattedMoney(decide[1]));
					ies.setEstimateDecideThird(getFormattedMoney(decide[2]));
					ies.setEstimateDecideOther(getFormattedMoney(decide[3]));
					ies.setEstimateDecideSum(getFormattedMoney(decide[4]));
					ies.setUpdateDate(sdf.format(new Date()));
					updateList.add(ies);
				}
			}
		}
		if(updateList!=null && updateList.size()>0){
			investEstimateSubjectDao.updateList(updateList);
		}
	}

	
	
}
