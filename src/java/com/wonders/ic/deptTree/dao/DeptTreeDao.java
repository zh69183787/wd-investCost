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

package com.wonders.ic.deptTree.dao;

import java.util.List;

import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface DeptTreeDao extends AbstractHibernateDao<DeptTree> {
	
	public List<DeptTree> findAllByName(String name);
	
	/**
	 * 根据父节点查询所有子节点
	 * @param pid
	 * @return
	 */
	public List<DeptTree> findByPid(String pid);
}
