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

package com.wonders.ic.attach.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

public interface AttachService {
	
	public Page findAttachByPage(Map<String, Object> filter, int pageNo,int pageSize);
	
	/**
	 * 保存
	 * @param attach
	 */
	public void saveAttach(Attach attach);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteByIdOnLogical(String id);
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public Attach findById(String id);
	
	/**
	 * 查询
	 * @param ids
	 * @return
	 */
	public List<Attach> findByIds(String[] ids);
	
	public List<Attach> findByGroupId(String groupId);
}
