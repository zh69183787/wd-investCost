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

package com.wonders.ic.investEstimateSubject.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * InvestEstimateSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_INVEST_ESTIMATE_SUBJECT")
public class InvestEstimateSubject implements Serializable, BusinessObject {

	private String id; // id

	private String createDate; // createDate

	private String economicIndicator; // economicIndicator

	private String estimateDecideFirst; // estimateDecideFirst

	private String estimateDecideOther; // estimateDecideOther

	private String estimateDecideSecond; // estimateDecideSecond

	private String estimateDecideSum; // estimateDecideSum

	private String estimateDecideThird; // estimateDecideThird

	private String investEstimateId; // investEstimateId

	private String projectAmount; // projectAmount

	private String projectAmountUnit; // projectAmountUnit

	private String projectId; // projectId

	private String remark; // remark

	private String removed; // removed

	private String subjectName; // subjectName

	private String subjectStatus; // subjectStatus

	private String updateDate; // updateDate
	
	private String dataType;
	
	private String objectControlValue;
	private String objectAdjustValue;

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

	public void setEconomicIndicator(String economicIndicator) {
		this.economicIndicator = economicIndicator;
	}

	@Column(name = "ECONOMIC_INDICATOR", nullable = true, length = 40)
	public String getEconomicIndicator() {
		return economicIndicator;
	}

	public void setEstimateDecideFirst(String estimateDecideFirst) {
		this.estimateDecideFirst = estimateDecideFirst;
	}

	@Column(name = "ESTIMATE_DECIDE_FIRST", nullable = true, length = 40)
	public String getEstimateDecideFirst() {
		return estimateDecideFirst;
	}

	public void setEstimateDecideOther(String estimateDecideOther) {
		this.estimateDecideOther = estimateDecideOther;
	}

	@Column(name = "ESTIMATE_DECIDE_OTHER", nullable = true, length = 40)
	public String getEstimateDecideOther() {
		return estimateDecideOther;
	}

	public void setEstimateDecideSecond(String estimateDecideSecond) {
		this.estimateDecideSecond = estimateDecideSecond;
	}

	@Column(name = "ESTIMATE_DECIDE_SECOND", nullable = true, length = 40)
	public String getEstimateDecideSecond() {
		return estimateDecideSecond;
	}

	public void setEstimateDecideSum(String estimateDecideSum) {
		this.estimateDecideSum = estimateDecideSum;
	}

	@Column(name = "ESTIMATE_DECIDE_SUM", nullable = true, length = 40)
	public String getEstimateDecideSum() {
		return estimateDecideSum;
	}

	public void setEstimateDecideThird(String estimateDecideThird) {
		this.estimateDecideThird = estimateDecideThird;
	}

	@Column(name = "ESTIMATE_DECIDE_THIRD", nullable = true, length = 40)
	public String getEstimateDecideThird() {
		return estimateDecideThird;
	}

	public void setInvestEstimateId(String investEstimateId) {
		this.investEstimateId = investEstimateId;
	}

	@Column(name = "INVEST_ESTIMATE_ID", nullable = true, length = 40)
	public String getInvestEstimateId() {
		return investEstimateId;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	@Column(name = "PROJECT_AMOUNT", nullable = true, length = 40)
	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmountUnit(String projectAmountUnit) {
		this.projectAmountUnit = projectAmountUnit;
	}

	@Column(name = "PROJECT_AMOUNT_UNIT", nullable = true, length = 40)
	public String getProjectAmountUnit() {
		return projectAmountUnit;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_ID", nullable = true, length = 40)
	public String getProjectId() {
		return projectId;
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

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Column(name = "SUBJECT_NAME", nullable = true, length = 200)
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}

	@Column(name = "SUBJECT_STATUS", nullable = true, length = 1)
	public String getSubjectStatus() {
		return subjectStatus;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 40)
	public String getUpdateDate() {
		return updateDate;
	}

	@Column(name = "DATA_TYPE", nullable = true, length = 10)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "OBJECT_CONTROL_VALUE", nullable = true, length = 100)
	public String getObjectControlValue() {
		return objectControlValue;
	}

	public void setObjectControlValue(String objectControlValue) {
		this.objectControlValue = objectControlValue;
	}

	@Column(name = "OBJECT_ADJUST_VALUE", nullable = true, length = 100)
	public String getObjectAdjustValue() {
		return objectAdjustValue;
	}

	public void setObjectAdjustValue(String objectAdjustValue) {
		this.objectAdjustValue = objectAdjustValue;
	}
	


	

}
