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

package com.wonders.ic.attach.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.ic.attach.dao.AttachDao;
import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

public class AttachServiceImpl implements AttachService {
	private AttachDao attachDao;

	public void setAttachDao(AttachDao attachDao) {
		this.attachDao = attachDao;
	}

	public Page findAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return attachDao.findAttachByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveAttach(Attach attach) {
		this.attachDao.save(attach);
	}

	@Override
	public void deleteByIdOnLogical(String id) {
		this.attachDao.deleteByIdOnLogical(id);
	}

	@Override
	public Attach findById(String id) {
		return this.attachDao.load(Long.valueOf(id));
	}

	@Override
	public List<Attach> findByIds(String[] ids) {
		
		return this.attachDao.findByIds(ids);
	}

	@Override
	public List<Attach> findByGroupId(String groupId) {
		
		return this.attachDao.findByGroupId(groupId);
	}
	
	
}
