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

import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwContractCoverDao extends
		AbstractHibernateDao<DwContractCover> {
	
	/**
	 * 根据合同分类
	 */
	public List<String> findAllContractByType(String type);
	
	/**
	 * 删除所有数据
	 */
	public void deleteAll();
	
	/**
	 * 保存全部
	 * @param dwContractCoverList
	 */
	public void saveOrUpdateAll(List<DwContractCover> dwContractCoverList);
	
	/**
	 * 根据合同类型查询，不模糊
	 * @param contractType
	 * @return
	 */
	public List<DwContractCover> findByContractType(String contractType); 
	
	/**
	 * 根据合同类型查询，项目公司不为空
	 * @param contractType
	 * @return
	 */
	public List<DwContractCover> findByCompanyType(String conpanyType);
	
	/**
	 * 根据合同类型模糊搜索
	 * @param contractType
	 * @return
	 */
	public List<DwContractCover> findByContractTypeWithFuzzySearch(String contractType);
}
