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

package com.wonders.ic.quantitiesSubject.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * QuantitiesSubjectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_QUANTITIES_SUBJECT")
public class QuantitiesSubject implements Serializable, BusinessObject {

	private String id; // id

	private String acceptCondition; // acceptCondition

	private String contractId; // contractId

	private String createDate; // createDate

	private String amount;
	
	private String investEstimateId; // investEstimateId

	private String investEstimateName; // investEstimateName

	private String operateDate; // operateDate

	private String remark; // remark

	private String removed; // removed

	private String subjectName; // subjectName

	private String subjectStatus; // subjectStatus

	private String unitPrice; // unitPrice
	
	private String subjectNo;
	
	private String dataType;


	private String contractNo;
	
	private String totalPrice;
	
	private String subjectType;

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

	public void setAcceptCondition(String acceptCondition) {
		this.acceptCondition = acceptCondition;
	}

	@Column(name = "ACCEPT_CONDITION", nullable = true, length = 400)
	public String getAcceptCondition() {
		return acceptCondition;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "CONTRACT_ID", nullable = true, length = 40)
	public String getContractId() {
		return contractId;
	}
	
	@Column(name="CONTRACT_NO",nullable = true,length=100)
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 40)
	public String getCreateDate() {
		return createDate;
	}

	@Column(name="AMOUNT",nullable = true,length=40)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setInvestEstimateId(String investEstimateId) {
		this.investEstimateId = investEstimateId;
	}

	@Column(name = "INVEST_ESTIMATE_ID", nullable = true, length = 40)
	public String getInvestEstimateId() {
		return investEstimateId;
	}

	public void setInvestEstimateName(String investEstimateName) {
		this.investEstimateName = investEstimateName;
	}

	@Column(name = "INVEST_ESTIMATE_NAME", nullable = true, length = 40)
	public String getInvestEstimateName() {
		return investEstimateName;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 40)
	public String getOperateDate() {
		return operateDate;
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

	@Column(name = "SUBJECT_STATUS", nullable = true, length = 40)
	public String getSubjectStatus() {
		return subjectStatus;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "UNIT_PRICE", nullable = true, length = 40)
	public String getUnitPrice() {
		return unitPrice;
	}

	@Column(name="TOTAL_PRICE",nullable = true,length = 40)
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name="SUBJECT_NO",nullable = true,length = 40)
	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	@Column(name="DATA_TYPE",nullable = true,length = 10)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name="SUBJECT_TYPE",nullable = true,length = 10)
	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	

}
