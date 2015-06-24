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

package com.wonders.ic.investEstimate.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * InvestEstimateʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_INVEST_ESTIMATE")
public class InvestEstimate implements Serializable, BusinessObject {

	private String id; // id

	private String createDate; // createDate

	private String estimateDecideAmount; // estimateDescideAmount

	private String estimateDescribe; // estimateDescribe

	private String estimateStatus; // estimateStatus

	private String estimateTemplate; // estimateTemplate

	private String fillPerson; // fillPerson

	private String fillCompany; // fillCompany

	private String imageProgress; // imageProgress

	private String investControlAmount; // investControlAmount

	private String lineDistance; // lineDistance

	private String operateDate; // operateDate

	private String pay; // pay

	private String projectCount; // projectCount

	private String projectId; // projectId

	private String projectName; // projectName

	private String remark; // remark

	private String removed; // removed
	
	private String projectUnit;
	
	private String estimateDecideFirst;
	
	private String estimateDecideSecond;
	
	private String estimateDecideThird;
	
	private String estimateDecideOther;

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

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 40)
	public String getCreateDate() {
		return createDate;
	}

	public void setEstimateDescribe(String estimateDescribe) {
		this.estimateDescribe = estimateDescribe;
	}

	@Column(name = "ESTIMATE_DECIDE_AMOUNT", nullable = true, length = 40)
	public String getEstimateDecideAmount() {
		return estimateDecideAmount;
	}

	public void setEstimateDecideAmount(String estimateDecideAmount) {
		this.estimateDecideAmount = estimateDecideAmount;
	}

	@Column(name = "ESTIMATE_DESCRIBE", nullable = true, length = 400)
	public String getEstimateDescribe() {
		return estimateDescribe;
	}

	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	@Column(name = "ESTIMATE_STATUS", nullable = true, length = 2)
	public String getEstimateStatus() {
		return estimateStatus;
	}

	public void setEstimateTemplate(String estimateTemplate) {
		this.estimateTemplate = estimateTemplate;
	}

	@Column(name = "ESTIMATE_TEMPLATE", nullable = true, length = 400)
	public String getEstimateTemplate() {
		return estimateTemplate;
	}

	public void setFillPerson(String fillPerson) {
		this.fillPerson = fillPerson;
	}

	@Column(name = "FILL_PERSON", nullable = true, length = 40)
	public String getFillPerson() {
		return fillPerson;
	}

	public void setImageProgress(String imageProgress) {
		this.imageProgress = imageProgress;
	}

	@Column(name = "FILL_COMPANY", nullable = true, length = 40)
	public String getFillCompany() {
		return fillCompany;
	}

	public void setFillCompany(String fillCompany) {
		this.fillCompany = fillCompany;
	}

	@Column(name = "IMAGE_PROGRESS", nullable = true, length = 40)
	public String getImageProgress() {
		return imageProgress;
	}

	public void setInvestControlAmount(String investControlAmount) {
		this.investControlAmount = investControlAmount;
	}

	@Column(name = "INVEST_CONTROL_AMOUNT", nullable = true, length = 40)
	public String getInvestControlAmount() {
		return investControlAmount;
	}

	public void setLineDistance(String lineDistance) {
		this.lineDistance = lineDistance;
	}

	@Column(name = "LINE_DISTANCE", nullable = true, length = 400)
	public String getLineDistance() {
		return lineDistance;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 40)
	public String getOperateDate() {
		return operateDate;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Column(name = "PAY", nullable = true, length = 40)
	public String getPay() {
		return pay;
	}

	public void setProjectCount(String projectCount) {
		this.projectCount = projectCount;
	}

	@Column(name = "PROJECT_COUNT", nullable = true, length = 40)
	public String getProjectCount() {
		return projectCount;
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

	@Column(name = "PROJECT_NAME", nullable = true, length = 40)
	public String getProjectName() {
		return projectName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 4000)
	public String getRemark() {
		return remark;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	@Column(name = "PROJECT_UNIT", nullable = true, length = 40)
	public String getProjectUnit() {
		return projectUnit;
	}

	public void setProjectUnit(String projectUnit) {
		this.projectUnit = projectUnit;
	}
	
	@Column(name = "ESTIMATE_DECIDE_FIRST", nullable = true, length = 40)
	public String getEstimateDecideFirst() {
		return estimateDecideFirst;
	}

	public void setEstimateDecideFirst(String estimateDecideFirst) {
		this.estimateDecideFirst = estimateDecideFirst;
	}
	
	@Column(name = "ESTIMATE_DECIDE_SECOND", nullable = true, length = 40)
	public String getEstimateDecideSecond() {
		return estimateDecideSecond;
	}

	public void setEstimateDecideSecond(String estimateDecideSecond) {
		this.estimateDecideSecond = estimateDecideSecond;
	}
	
	@Column(name = "ESTIMATE_DECIDE_THIRD", nullable = true, length = 40)
	public String getEstimateDecideThird() {
		return estimateDecideThird;
	}

	public void setEstimateDecideThird(String estimateDecideThird) {
		this.estimateDecideThird = estimateDecideThird;
	}
	
	@Column(name = "ESTIMATE_DECIDE_OTHER", nullable = true, length = 40)
	public String getEstimateDecideOther() {
		return estimateDecideOther;
	}

	public void setEstimateDecideOther(String estimateDecideOther) {
		this.estimateDecideOther = estimateDecideOther;
	}

}
