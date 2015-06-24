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

import java.util.List;

import com.wonders.ic.dwContract.dao.DwContractProgressDao;
import com.wonders.ic.dwContract.entity.bo.DwContractProgress;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwContractProgressʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-26
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractProgressDaoImpl extends
		AbstractHibernateDaoImpl<DwContractProgress> implements
		DwContractProgressDao {

	@Override
	public List<DwContractProgress> findByContractCreateDate(String sDate,String eDate) {
		if(sDate==null || eDate==null) return null;
		String hql ="from DwContractProgress t where to_char(t.contractCreateDate,'yyyy-mm-dd') > '"+sDate+"' " +
				"and to_char(t.contractCreateDate,'yyyy-mm-dd')< '"+eDate+"'"+
				" and t.removed='0' order by t.contractCreateDate ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
