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

package com.wonders.ic.progress.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.ic.progress.dao.ProgressDao;
import com.wonders.ic.progress.entity.bo.Progress;
import com.wonders.ic.progress.service.ProgressService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

public class ProgressServiceImpl implements ProgressService {
	private ProgressDao progressDao;

	public void setProgressDao(ProgressDao progressDao) {
		this.progressDao = progressDao;
	}

	public Page findProgressByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return progressDao.findProgressByPage(filter, pageNo, pageSize);
	}

	@Override
	public void save(Progress progress) {
		progressDao.save(progress);
	}

	@Override
	public List<Progress> findAllByObjectId(String contractId,String objectType) {
		return progressDao.findAllByObjectId(contractId,objectType);
	}

	@Override
	public Progress findById(String id) {
		return progressDao.load(id);
	}

	@Override
	public void update(Progress progress) {
		progressDao.update(progress);
	}

	@Override
	public void deleteById(String id) {
		progressDao.deleteById(id);
	}
	
	
}
