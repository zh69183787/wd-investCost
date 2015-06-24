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

package com.wonders.ic.dwContract.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.dwContract.dao.DwContractDestoryNumberDao;
import com.wonders.ic.dwContract.entity.bo.DwContractDestoryNumber;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwContractDestoryNumberʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-8
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractDestoryNumberDaoImpl extends
		AbstractHibernateDaoImpl<DwContractDestoryNumber> implements
		DwContractDestoryNumberDao {

	@Override
	public List<DwContractDestoryNumber> findAllDestoryNumber() {
		String hql ="from DwContractDestoryNumber t";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwContractDestoryNumber> findByFilter(Map<String, String> filter) {
		String hql = "from DwContractDestoryNumber t ";
		if(!filter.isEmpty()){
			hql += " where ";
			int count = 0;
			for(Iterator<String> i=filter.keySet().iterator(); i.hasNext();){
				if(count>0){
					hql += " and ";
				}
				String param = i.next();
				if("projectNo".equals(param) || "projectName".equals(param)){
					hql += " t."+param+" like '%"+filter.get(param).toLowerCase()+"%'";
				}
				if("projectType".equals(param)){
					if("1".equals(filter.get(param).toString())){		//外部项目
						hql += " t."+param+" = '1'";
					}else if("2".equals(filter.get(param).toString())){	//内部项目,2,3,4
						hql += " t."+param+" in ('2','3','4')";
					}
				}
				if("contractSignedStart".equals(param)){
					hql += " t.contractSignedDate >= '"+filter.get(param)+"'";
				}
				if("contractSignedEnd".equals(param)){
					hql += " t.contractSignedDate <= '"+filter.get(param)+"'";
				}
				if("contractStatus".equals(param)){
					hql +=" t.contractStatus='"+filter.get(param)+"'";
				}
				count++;
			}
		}
		hql +=" order by t.contractSignedDate DESC";
		return getHibernateTemplate().find(hql);
	}
	
	
}
