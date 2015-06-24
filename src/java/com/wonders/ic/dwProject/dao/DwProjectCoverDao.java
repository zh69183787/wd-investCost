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

package com.wonders.ic.dwProject.dao;

import java.util.List;

import com.wonders.ic.dwProject.entity.bo.DwProjectCover;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectCoverDao extends AbstractHibernateDao<DwProjectCover> {
	/**
	 * 删除库中所有数据
	 */
	public void deleteAll();
	
	/**
	 * 根据类型查询总数
	 * @param type,内部项目：0，外部项目1，其他项目：5
	 * @return
	 */
	public Long findProjectSumByTypeAndAdddeptId(String type,String adddeptId);
	
	/**
	 * 根据类型查询
	 * @param type
	 * @param adddeptId
	 * @return
	 */
	public Long findProjectSumByTypeAndWithoutAdddeptId(String type,List<String>adddeptId);
	
	/**
	 * 保存list
	 */
	public void saveOrUpdateAll(List<DwProjectCover> list);
	
	/**
	 * 根据项目类型、公司类型查询
	 * @param projectType
	 * @param companyType
	 * @return
	 */
	public List<DwProjectCover> findByProjectTypeAndCompanyType(String projectType,String companyType);
}
