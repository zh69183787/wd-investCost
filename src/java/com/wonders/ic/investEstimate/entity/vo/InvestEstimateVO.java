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

package com.wonders.ic.investEstimate.entity.vo;

import java.util.Date;

import javax.persistence.Column;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * InvestEstimateʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateVO implements ValueObject {

	private String id;
	private String createDate;
	private String estimateDecideAmount;
	private String estimateDescribe;
	private String estimateStatus;
	private String estimateTemplate;
	private String fillPerson;
	private String fillUnit;
	private String imageProgress;
	private String investControlAmount;
	private String lineDistance;
	private String operateDate;
	private String pay;
	private String projectCount;
	private String projectId;
	private String projectName;
	private String remark;
	private String removed;
	private String estimateDecideFirst;
	
	private String estimateDecideSecond;
	
	private String estimateDecideThird;
	
	private String estimateDecideOther;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setEstimateDescideAmount(String estimateDecideAmount) {
		this.estimateDecideAmount = estimateDecideAmount;
	}

	public String getEstimateDecideAmount() {
		return estimateDecideAmount;
	}

	public void setEstimateDescribe(String estimateDescribe) {
		this.estimateDescribe = estimateDescribe;
	}

	public String getEstimateDescribe() {
		return estimateDescribe;
	}

	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	public String getEstimateStatus() {
		return estimateStatus;
	}

	public void setEstimateTemplate(String estimateTemplate) {
		this.estimateTemplate = estimateTemplate;
	}

	public String getEstimateTemplate() {
		return estimateTemplate;
	}

	public void setFillPerson(String fillPerson) {
		this.fillPerson = fillPerson;
	}

	public String getFillPerson() {
		return fillPerson;
	}

	public void setFillUnit(String fillUnit) {
		this.fillUnit = fillUnit;
	}

	public String getFillUnit() {
		return fillUnit;
	}

	public void setImageProgress(String imageProgress) {
		this.imageProgress = imageProgress;
	}

	public String getImageProgress() {
		return imageProgress;
	}

	public void setInvestControlAmount(String investControlAmount) {
		this.investControlAmount = investControlAmount;
	}

	public String getInvestControlAmount() {
		return investControlAmount;
	}

	public void setLineDistance(String lineDistance) {
		this.lineDistance = lineDistance;
	}

	public String getLineDistance() {
		return lineDistance;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPay() {
		return pay;
	}

	public void setProjectCount(String projectCount) {
		this.projectCount = projectCount;
	}

	public String getProjectCount() {
		return projectCount;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public String getEstimateDecideFirst() {
		return estimateDecideFirst;
	}

	public void setEstimateDecideFirst(String estimateDecideFirst) {
		this.estimateDecideFirst = estimateDecideFirst;
	}
	
	public String getEstimateDecideSecond() {
		return estimateDecideSecond;
	}

	public void setEstimateDecideSecond(String estimateDecideSecond) {
		this.estimateDecideSecond = estimateDecideSecond;
	}
	
	public String getEstimateDecideThird() {
		return estimateDecideThird;
	}

	public void setEstimateDecideThird(String estimateDecideThird) {
		this.estimateDecideThird = estimateDecideThird;
	}
	
	public String getEstimateDecideOther() {
		return estimateDecideOther;
	}

	public void setEstimateDecideOther(String estimateDecideOther) {
		this.estimateDecideOther = estimateDecideOther;
	}
}
