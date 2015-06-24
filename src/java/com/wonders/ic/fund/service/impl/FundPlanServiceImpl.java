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

package com.wonders.ic.fund.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.fund.dao.FundPlanDao;
import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wonders.ic.fund.entity.vo.FundPlanVo;
import com.wonders.ic.fund.service.FundPlanService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class FundPlanServiceImpl implements FundPlanService {
	private FundPlanDao fundPlanDao;

	public FundPlanDao getFundPlanDao() {
		return fundPlanDao;
	}

	public void setFundPlanDao(FundPlanDao fundPlanDao) {
		this.fundPlanDao = fundPlanDao;
	}

	public Page findFundPlanByPage(FundPlan fundPlan, String pageNo, String pageSize){
		int ipageNo = 1;
		int ipageSize = 10;
		if(!StringUtils.isEmpty(pageNo)){
			ipageNo = Integer.valueOf(pageNo);
		}
		if(!StringUtils.isEmpty(pageSize)){
			ipageSize = Integer.valueOf(pageSize);
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		
	    JsonConfig jsonConfig = new JsonConfig();  
	    jsonConfig.setExcludes(new String[] {"money"});  
		JSONObject obj = JSONObject.fromObject(fundPlan, jsonConfig);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = (String)obj.get(key);
			if (value != null && value.trim().length() > 0) {
				filter.put(key, value);
			}
		}
		
		return this.fundPlanDao.findFundPlanByPage(filter, ipageNo, ipageSize);
	}
	
	public boolean exists(FundPlan fundPlan){
		return this.fundPlanDao.exists(fundPlan);
	}
	
	public void deleteByIdOnLogical(String id){
		this.fundPlanDao.deleteByIdOnLogical(id);
	}
	
	@Override
	public void saveFundPlan(FundPlan fundPlan) {
		fundPlanDao.saveFundPlan(fundPlan);
	}
	
	@Override
	public FundPlan findFundPlanById(String id) {
		return fundPlanDao.findFundPlanById(id);
	}
	
	@Override
	public void saveAll(List<FundPlan> list) {
		fundPlanDao.saveAll(list);
	}
}
