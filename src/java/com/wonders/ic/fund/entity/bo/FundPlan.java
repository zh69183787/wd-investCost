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

package com.wonders.ic.fund.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * FundPlan
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_FUND_PLAN")
public class FundPlan implements Serializable, BusinessObject {

	private String id; // id

	private BigDecimal money; // money
	
	private String dispatchNo;	// dispatchNo

	private String projectId; // projectId

	private String line; // 线路

	private String year; // year

	private String removed; // removed

	private String createDate; // createDate
	
	private String updateDate; // updateDate
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	@Column(name = "MONEY", nullable = true, length = 40)
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "DISPATCH_NO", nullable = true, length = 100)
	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	@Column(name = "PROJECT_ID", nullable = true, length = 40)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "LINE", nullable = true, length = 100)
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "YEAR", nullable = true, length = 10)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 20)
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 20)
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
