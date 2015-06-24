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

package com.wonders.ic.corporation.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.api.dto.PsCorporationDto;
import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wonders.ic.corporation.dao.PsCorporationDao;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * PsCorporation实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

public class PsCorporationDaoImpl extends
		AbstractHibernateDaoImpl<PsCorporation> implements PsCorporationDao {
	
	public List<PsCorporation> findPsCorporationByCompNameWithFuzzySearch(String compName){
        String hql ="from PsCorporation t where t.companyNameChn like '%"+compName+"%' and removed='0'";
        System.out.println("----------------------");
        return getHibernateTemplate().find(hql);
	}
	
	@Override
	public Page findByCompanyNameChn(Map<String, Object> filter, int pageNo,int pageSize){
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from PsCorporation t ";
		String countHql = "select count(*) from PsCorporation t ";
		String filterPart = "";
		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				String paramName = i.next();
				if(paramName.equals("companyNameChn")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}
			}
		}
		
		Page page = findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
		List<PsCorporation> result = page.getResult();
		List<PsCorporationDto> finalResult = new ArrayList<PsCorporationDto>();
		for(PsCorporation cor : result){
			PsCorporationDto corDto = new PsCorporationDto();
			corDto.setId(cor.getCompId().toString());
			corDto.setCompanyNameChn(cor.getCompanyNameChn());
			corDto.setOrgNum(cor.getOrgCode());
			finalResult.add(corDto);
		}
		page.setResult(finalResult);
		return page;
	}
}
