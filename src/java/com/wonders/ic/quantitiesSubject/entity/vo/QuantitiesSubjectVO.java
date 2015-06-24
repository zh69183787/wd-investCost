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

package com.wonders.ic.quantitiesSubject.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * QuantitiesSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesSubjectVO implements ValueObject {

	private String id;
	private String acceptCondition;
	private String contractId;
	private String createDate;
	private String data;
	private String investEstimateId;
	private String investEstimateName;
	private String operateDate;
	private String remark;
	private String removed;
	private String subjectName;
	private String subjectStatus;
	private String unitPrice;
	private String wbsResolveId;
	private String wbsStructureId;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAcceptCondition(String acceptCondition) {
		this.acceptCondition = acceptCondition;
	}

	public String getAcceptCondition() {
		return acceptCondition;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setInvestEstimateId(String investEstimateId) {
		this.investEstimateId = investEstimateId;
	}

	public String getInvestEstimateId() {
		return investEstimateId;
	}

	public void setInvestEstimateName(String investEstimateName) {
		this.investEstimateName = investEstimateName;
	}

	public String getInvestEstimateName() {
		return investEstimateName;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateDate() {
		return operateDate;
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

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitPrice() {
		return unitPrice;
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

}
