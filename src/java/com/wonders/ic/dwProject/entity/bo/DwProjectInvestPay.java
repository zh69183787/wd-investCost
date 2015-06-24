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

package com.wonders.ic.dwProject.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwProjectInvestPay实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_PROJECT_INVEST_PAY")
public class DwProjectInvestPay implements Serializable, BusinessObject {

	private String id; // id

	private String actualPay; // actualPay

	private Date actualPayDate; // actualPayDate

	private Date createDate; // createDate

	private String planPay; // planPay

	private Date planPayDate; // planPayDate
	
	private String projectPlanPay; // planPay
	
	private Date projectPlanDate; // planPayDate

	private String projectId; // projectId

	private String projectName; // projectName

	private String projectNo; // projectNo

	private Date updateDate; // updateDate
	
	private String monthPay;
	
	private String planMonthPay;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setActualPay(String actualPay) {
		this.actualPay = actualPay;
	}

	@Column(name = "ACTUAL_PAY", nullable = true, length = 40)
	public String getActualPay() {
		return actualPay;
	}

	public void setActualPayDate(Date actualPayDate) {
		this.actualPayDate = actualPayDate;
	}

	@Column(name = "ACTUAL_PAY_DATE", nullable = true, length = 11)
	public Date getActualPayDate() {
		return actualPayDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 11)
	public Date getCreateDate() {
		return createDate;
	}

	public void setPlanPay(String planPay) {
		this.planPay = planPay;
	}

	@Column(name = "PLAN_PAY", nullable = true, length = 40)
	public String getPlanPay() {
		return planPay;
	}

	public void setPlanPayDate(Date planPayDate) {
		this.planPayDate = planPayDate;
	}

	@Column(name = "PLAN_PAY_DATE", nullable = true, length = 11)
	public Date getPlanPayDate() {
		return planPayDate;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_ID", nullable = true, length = 40)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_NAME", nullable = true, length = 400)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 400)
	public String getProjectNo() {
		return projectNo;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 11)
	public Date getUpdateDate() {
		return updateDate;
	}

	@Column(name = "PROJECT_PLAN_PAY", nullable = true, length = 40)
	public String getProjectPlanPay() {
		return projectPlanPay;
	}

	public void setProjectPlanPay(String projectPlanPay) {
		this.projectPlanPay = projectPlanPay;
	}

	@Column(name = "PROJECT_PLAN_DATE", nullable = true, length = 11)
	public Date getProjectPlanDate() {
		return projectPlanDate;
	}

	public void setProjectPlanDate(Date projectPlanDate) {
		this.projectPlanDate = projectPlanDate;
	}
	
	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	@Column(name = "MONTH_PAY", nullable = true, length = 40)
	public String getMonthPay() {
		return monthPay;
	}
	
	public void setPlanMonthPay(String planMonthPay) {
		this.planMonthPay = planMonthPay;
	}

	@Column(name = "PLAN_MONTH_PAY", nullable = true, length = 40)
	public String getPlanMonthPay() {
		return planMonthPay;
	}
	

}
