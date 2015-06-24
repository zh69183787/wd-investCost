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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwContractChangeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-1-6
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_CONTRACT_CHANGE")
public class DwContractChange implements Serializable, BusinessObject {

	private String id; // id

	private String changeMoney; // changeMoney

	private String changeReason; // changeReason

	private String contractId; // contractId

	private String contractName; // contractName

	private String contractPrice; // contractPrice

	private Date createDate; // createDate

	private String decidePrice; // decidePrice

	private String partyBCompany; // partyBCompany

	private String projectId; // projectId
	
	private String projectNo; // projectNo

	private String projectName; // projectName

	private Date updateDate; // updateDate
	
	private String signedDate;
	private String projectType;
	private Long changeCounts;

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

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	@Column(name = "CHANGE_MONEY", nullable = true, length = 40)
	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Column(name = "CHANGE_REASON", nullable = true, length = 4000)
	public String getChangeReason() {
		return changeReason;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "CONTRACT_ID", nullable = true, length = 40)
	public String getContractId() {
		return contractId;
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

	public void setPartyBCompany(String partyBCompany) {
		this.partyBCompany = partyBCompany;
	}

	@Column(name = "PARTY_B_COMPANY", nullable = true, length = 400)
	public String getPartyBCompany() {
		return partyBCompany;
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

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 11)
	public Date getUpdateDate() {
		return updateDate;
	}
	
	@Column(name = "PROJECT_NO", nullable = true, length = 400)
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "SIGNED_DATE", nullable = true, length = 10)
	public String getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}

	@Column(name = "PROJECT_TYPE", nullable = true, length = 2)
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "CHANGE_COUNTS", nullable = true)
	public Long getChangeCounts() {
		return changeCounts;
	}

	public void setChangeCounts(Long changeCounts) {
		this.changeCounts = changeCounts;
	}
	
}
