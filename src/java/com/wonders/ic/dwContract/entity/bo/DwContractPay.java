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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwContractPayʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-1-5
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_CONTRACT_PAY")
public class DwContractPay implements Serializable, BusinessObject {

	private String id; // id

	private Long contractDurationTime; // contractDurationTime

	private Date contractEndDate; // contractEndDate

	private String contractName; // contractName

	private String contractPrice; // contractPrice

	private Date contractStartDate; // contractStartDate

	private Date createDate; // createDate

	private String decidePrice; // decidePrice

	private String partyBUnit; // partyBUnit
	
	private String partyBUnitId; // partyBUnit

	private String progressInfo; // progressInfo

	private String projectName; // projectName
	
	private String projectNo; // projectNo

	private String totalPay; // totalPay

	private Date updateDate; // updateDate
	
	private String totalPayRate;

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

	public void setContractDurationTime(Long contractDurationTime) {
		this.contractDurationTime = contractDurationTime;
	}

	@Column(name = "CONTRACT_DURATION_TIME", nullable = true, length = 22)
	public Long getContractDurationTime() {
		return contractDurationTime;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	@Column(name = "CONTRACT_END_DATE", nullable = true, length = 11)
	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 400)
	public String getContractName() {
		return contractName;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 40)
	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	@Column(name = "CONTRACT_START_DATE", nullable = true, length = 11)
	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 11)
	public Date getCreateDate() {
		return createDate;
	}

	public void setDecidePrice(String decidePrice) {
		this.decidePrice = decidePrice;
	}

	@Column(name = "DECIDE_PRICE", nullable = true, length = 40)
	public String getDecidePrice() {
		return decidePrice;
	}

	public void setPartyBUnit(String partyBUnit) {
		this.partyBUnit = partyBUnit;
	}

	@Column(name = "PARTY_B_UNIT", nullable = true, length = 400)
	public String getPartyBUnit() {
		return partyBUnit;
	}

	public void setProgressInfo(String progressInfo) {
		this.progressInfo = progressInfo;
	}

	@Column(name = "PROGRESS_INFO", nullable = true, length = 40)
	public String getProgressInfo() {
		return progressInfo;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_NAME", nullable = true, length = 400)
	public String getProjectName() {
		return projectName;
	}

	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}

	@Column(name = "TOTAL_PAY", nullable = true, length = 40)
	public String getTotalPay() {
		return totalPay;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 11)
	public Date getUpdateDate() {
		return updateDate;
	}

	@Column(name = "PARTY_B_UNIT_ID", nullable = true, length = 40)
	public String getPartyBUnitId() {
		return partyBUnitId;
	}

	public void setPartyBUnitId(String partyBUnitId) {
		this.partyBUnitId = partyBUnitId;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 400)
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Transient
	public String getTotalPayRate() {
		if (totalPay == null || totalPay.equals("") || 
				contractPrice == null || contractPrice.equals("")) {
			return "0";
		}else{
			Double contractPrice2Double = Double.valueOf(contractPrice);
			Double totalPay2Double = Double.valueOf(totalPay);
			return"" + Math.round(totalPay2Double / contractPrice2Double * 100);
		}
	}


}
