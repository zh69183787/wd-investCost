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

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.wonders.ic.fund.dao.FundAllowanceDao;
import com.wonders.ic.fund.entity.bo.FundAllowance;
import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wonders.ic.fund.service.FundAllowanceService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class FundAllowanceServiceImpl implements FundAllowanceService {
	private FundAllowanceDao fundAllowanceDao;

	public FundAllowanceDao getFundAllowanceDao() {
		return fundAllowanceDao;
	}

	public void setFundAllowanceDao(FundAllowanceDao fundAllowanceDao) {
		this.fundAllowanceDao = fundAllowanceDao;
	}

	public Page findFundAllowanceByPage(FundAllowance fundAllowance, String pageNo, String pageSize){
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
		JSONObject obj = JSONObject.fromObject(fundAllowance, jsonConfig);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = (String) obj.get(key);
			if (value != null && value.trim().length() > 0) {
				filter.put(key, value);
			}
		}
		
		return this.fundAllowanceDao.findFundAllowanceByPage(filter, ipageNo, ipageSize);
	}
	
	public void deleteByIdOnLogical(String id){
		this.fundAllowanceDao.deleteByIdOnLogical(id);
	}
	
	@Override
	public void saveFundAllowance(FundAllowance fundAllowance) {
		fundAllowanceDao.saveFundAllowance(fundAllowance);
	}
	
	@Override
	public FundAllowance findFundAllowanceById(String id) {
		return fundAllowanceDao.findFundAllowanceById(id);
	}
	
	@Override
	public void saveAll(List<FundAllowance> list) {
		fundAllowanceDao.saveAll(list);
	}
	
	public boolean exists(FundAllowance fundAllowance){
		if(StringUtils.isEmpty(fundAllowance.getLine())){
			return false;
		}
		return this.fundAllowanceDao.exists(fundAllowance);
	}
}
