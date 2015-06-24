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

package com.wonders.ic.dwContract.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwContractProgressʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-26
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_CONTRACT_PROGRESS")
public class DwContractProgress implements Serializable, BusinessObject {

	private String id; // id

	private BigDecimal actualPay; // actualPay

	private Date contractCreateDate; // contractCreateDate

	private BigDecimal contractPrice; // contractPrice

	private String costType; // costType

	private Date createDate; // createDate

	private String explanation; // explanation

	private BigDecimal firstSeasonPay; // firstSeasonPay

	private BigDecimal fourthSeasonPay; // fourthSeasonPay

	private String groupCompanyApprovalNo; // gproupCompanyApprovalNo

	private BigDecimal lastAccumulativeTotal; // lastAccumulativeTotal

	private String paidCompany; // paidCompany

	private String projectContractName; // projectContractName

	private String projectType; // projectType

	private String removed; // removed

	private BigDecimal secondSeasonPay; // secondSeasonPay

	private BigDecimal thirdSeasonPay; // thirdSeasonPay

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

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	@Column(name = "ACTUAL_PAY", nullable = true, length = 22)
	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setContractCreateDate(Date contractCreateDate) {
		this.contractCreateDate = contractCreateDate;
	}

	@Column(name = "CONTRACT_CREATE_DATE", nullable = true, length = 7)
	public Date getContractCreateDate() {
		return contractCreateDate;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 22)
	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@Column(name = "COST_TYPE", nullable = true, length = 40)
	public String getCostType() {
		return costType;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "EXPLANATION", nullable = true, length = 4000)
	public String getExplanation() {
		return explanation;
	}

	public void setFirstSeasonPay(BigDecimal firstSeasonPay) {
		this.firstSeasonPay = firstSeasonPay;
	}

	@Column(name = "FIRST_SEASON_PAY", nullable = true, length = 22)
	public BigDecimal getFirstSeasonPay() {
		return firstSeasonPay;
	}

	public void setFourthSeasonPay(BigDecimal fourthSeasonPay) {
		this.fourthSeasonPay = fourthSeasonPay;
	}

	@Column(name = "FOURTH_SEASON_PAY", nullable = true, length = 22)
	public BigDecimal getFourthSeasonPay() {
		return fourthSeasonPay;
	}

	public void setGroupCompanyApprovalNo(String groupCompanyApprovalNo) {
		this.groupCompanyApprovalNo = groupCompanyApprovalNo;
	}

	@Column(name = "GROUP_COMPANY_APPROVAL_NO", nullable = true, length = 200)
	public String getGroupCompanyApprovalNo() {
		return groupCompanyApprovalNo;
	}

	public void setLastAccumulativeTotal(BigDecimal lastAccumulativeTotal) {
		this.lastAccumulativeTotal = lastAccumulativeTotal;
	}

	@Column(name = "LAST_ACCUMULATIVE_TOTAL", nullable = true, length = 22)
	public BigDecimal getLastAccumulativeTotal() {
		return lastAccumulativeTotal;
	}

	public void setPaidCompany(String paidCompany) {
		this.paidCompany = paidCompany;
	}

	@Column(name = "PAID_COMPANY", nullable = true, length = 200)
	public String getPaidCompany() {
		return paidCompany;
	}

	public void setProjectContractName(String projectContractName) {
		this.projectContractName = projectContractName;
	}

	@Column(name = "PROJECT_CONTRACT_NAME", nullable = true, length = 200)
	public String getProjectContractName() {
		return projectContractName;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PROJECT_TYPE", nullable = true, length = 40)
	public String getProjectType() {
		return projectType;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setSecondSeasonPay(BigDecimal secondSeasonPay) {
		this.secondSeasonPay = secondSeasonPay;
	}

	@Column(name = "SECOND_SEASON_PAY", nullable = true, length = 22)
	public BigDecimal getSecondSeasonPay() {
		return secondSeasonPay;
	}

	public void setThirdSeasonPay(BigDecimal thirdSeasonPay) {
		this.thirdSeasonPay = thirdSeasonPay;
	}

	@Column(name = "THIRD_SEASON_PAY", nullable = true, length = 22)
	public BigDecimal getThirdSeasonPay() {
		return thirdSeasonPay;
	}

}
