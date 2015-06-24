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

package com.wonders.ic.progress.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.progress.entity.bo.Progress;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface ProgressDao extends AbstractHibernateDao<Progress> {
	public Page findProgressByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * 查询
	 * @param id 合同主键
	 * @return
	 */
	public List<Progress> findAllByObjectId(String id,String objectType);
}
