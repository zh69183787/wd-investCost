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

package com.wonders.ic.attach.dao;

import java.util.List;
import java.util.Map;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

public interface AttachDao extends AbstractHibernateDao<Attach> {
	public Page findAttachByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	
	public void deleteByIdOnLogical(String id);
	
	/**
	 * 查询
	 * @param ids
	 * @return
	 */
	public List<Attach> findByIds(String[] ids);
	
	public List<Attach> findByGroupId(String groupId);
}
