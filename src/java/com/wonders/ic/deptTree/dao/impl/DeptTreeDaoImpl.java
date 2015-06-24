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

package com.wonders.ic.deptTree.dao.impl;

import java.util.List;

import com.wonders.ic.deptTree.dao.DeptTreeDao;
import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DeptTreeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DeptTreeDaoImpl extends AbstractHibernateDaoImpl<DeptTree> implements DeptTreeDao {

	@Override
	public List<DeptTree> findAllByName(String name) {
		String hql = "from DeptTree t where t.name like '%"+name+"%' order by to_number(t.ordernum) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DeptTree> findByPid(String pid) {
		String hql ="from DeptTree t where t.pid ='"+pid+"' order by to_number(t.ordernum) ASC";
		return getHibernateTemplate().find(hql);
	}
	
	
}
