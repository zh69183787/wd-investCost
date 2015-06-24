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

package com.wonders.ic.investEstimateSubject.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * InvestEstimateSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class InvestEstimateSubjectVO implements ValueObject {

	private String id;
	private String createDate;
	private String economicIndicator;
	private String estimateDecideFirst;
	private String estimateDecideOther;
	private String estimateDecideSecond;
	private String estimateDecideSum;
	private String estimateDecideThird;
	private String investEstimateId;
	private String projectAmount;
	private String projectAmountUnit;
	private String projectId;
	private String remark;
	private String removed;
	private String subjectName;
	private String subjectStatus;
	private String updateDate;
	private String wbsResolveId;
	private String wbsStructureId;
	private String objectControlValue;

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

	public void setEconomicIndicator(String economicIndicator) {
		this.economicIndicator = economicIndicator;
	}

	public String getEconomicIndicator() {
		return economicIndicator;
	}

	public void setEstimateDecideFirst(String estimateDecideFirst) {
		this.estimateDecideFirst = estimateDecideFirst;
	}

	public String getEstimateDecideFirst() {
		return estimateDecideFirst;
	}

	public void setEstimateDecideOther(String estimateDecideOther) {
		this.estimateDecideOther = estimateDecideOther;
	}

	public String getEstimateDecideOther() {
		return estimateDecideOther;
	}

	public void setEstimateDecideSecond(String estimateDecideSecond) {
		this.estimateDecideSecond = estimateDecideSecond;
	}

	public String getEstimateDecideSecond() {
		return estimateDecideSecond;
	}

	public void setEstimateDecideSum(String estimateDecideSum) {
		this.estimateDecideSum = estimateDecideSum;
	}

	public String getEstimateDecideSum() {
		return estimateDecideSum;
	}

	public void setEstimateDecideThird(String estimateDecideThird) {
		this.estimateDecideThird = estimateDecideThird;
	}

	public String getEstimateDecideThird() {
		return estimateDecideThird;
	}

	public void setInvestEstimateId(String investEstimateId) {
		this.investEstimateId = investEstimateId;
	}

	public String getInvestEstimateId() {
		return investEstimateId;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmountUnit(String projectAmountUnit) {
		this.projectAmountUnit = projectAmountUnit;
	}

	public String getProjectAmountUnit() {
		return projectAmountUnit;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
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

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}

	public String getSubjectStatus() {
		return subjectStatus;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setWbsResolveId(String wbsResolveId) {
		this.wbsResolveId = wbsResolveId;
	}

	public String getWbsResolveId() {
		return wbsResolveId;
	}

	public void setWbsStructureId(String wbsStructureId) {
		this.wbsStructureId = wbsStructureId;
	}

	public String getWbsStructureId() {
		return wbsStructureId;
	}
	
	public void setObjectControlValue(String objectControlValue) {
		this.objectControlValue = objectControlValue;
	}

	public String getObjectControlValue() {
		return objectControlValue;
	}

}
