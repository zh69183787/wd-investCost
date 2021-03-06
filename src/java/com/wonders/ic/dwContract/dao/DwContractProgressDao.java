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

package com.wonders.ic.dwContract.dao;

import java.util.List;

import com.wonders.ic.dwContract.entity.bo.DwContractProgress;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-26
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwContractProgressDao extends
		AbstractHibernateDao<DwContractProgress> {
	public List<DwContractProgress> findByContractCreateDate(String sDate,String eDate);
	
}
