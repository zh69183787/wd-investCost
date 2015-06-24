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

package com.wonders.ic.deptTree.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * DeptTreeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class DeptTreeVO implements ValueObject {

	private String description;
	private String id;
	private String name;
	private String pid;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

}
