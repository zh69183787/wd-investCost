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

package com.wonders.ic.progress.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.progress.entity.bo.Progress;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface ProgressService {
	
	public Page findProgressByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * 保存
	 * @param progress
	 */
	public void save(Progress progress);
	
	
	/**
	 * 查询
	 * @param id 合同主键
	 * @param objectType
	 * @return
	 */
	public List<Progress> findAllByObjectId(String contractId,String objectType);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return
	 */
	public Progress findById(String id);
	
	/**
	 * 保存或更新
	 * @param progress
	 */
	public void update(Progress progress);
	
	public void deleteById(String id);
}
