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

package com.wonders.ic.corporation.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

public interface PsCorporationDao extends AbstractHibernateDao<PsCorporation> {
	public List<PsCorporation> findPsCorporationByCompNameWithFuzzySearch(String compName);

	Page findByCompanyNameChn(Map<String, Object> filter, int pageNo, int pageSize);
}
