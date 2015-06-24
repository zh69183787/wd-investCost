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

package com.wonders.ic.deptTree.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DeptTreeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_DEPT_TREE")
public class DeptTree implements Serializable, BusinessObject {

	private String description; // description

	private String id; // id

	private String name; // name

	private String pid; // pid
	
	private String ordernum;

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION", nullable = true, length = 100)
	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "PID", nullable = true, length = 40)
	public String getPid() {
		return pid;
	}

	@Column(name = "ORDERNUM", nullable = true, length = 10)
	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	
	
}
