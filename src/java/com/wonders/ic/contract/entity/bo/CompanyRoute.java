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

package com.wonders.ic.contract.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * CompanyRouteʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-9-11
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@IdClass(CompanyRoutePK.class)
@Table(name = "C_COMPANY_ROUTE")
public class CompanyRoute implements Serializable, BusinessObject {

	private String description; // description

	private String id; // id

	private String objectName; // objectName

	private Long order; // order

	private String pid; // pid

	private String type; // type
	private String removed;

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION", nullable = true, length = 2000)
	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Column(name = "OBJECT_NAME", nullable = true, length = 500)
	public String getObjectName() {
		return objectName;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	@Column(name = "ORDER_", nullable = true, length = 10)
	public Long getOrder() {
		return order;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	//@Id
	@Column(name = "PID", nullable = true, length = 40)
	public String getPid() {
		return pid;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Id
	@Column(name = "TYPE", nullable = false, length = 2)
	public String getType() {
		return type;
	}

	@Column(name = "REMOVED", nullable = false, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	
}
