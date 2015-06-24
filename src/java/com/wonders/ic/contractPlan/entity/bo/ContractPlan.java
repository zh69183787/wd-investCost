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

package com.wonders.ic.contractPlan.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * ContractPlanʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_CONTRACT_PLAN")
public class ContractPlan implements Serializable, BusinessObject {

	private String id; // id

	private String buildSupplierId; // buildSupplierId

	private String buildSupplierName; // buildSupplierName

	private String contractAttachment; // contractAttachment

	private String contractContent; // contractContent

	private String contractEndDate; // contractEndDate

	private String contractName; // contractName

	private String contractNo; // contractNo

	private String contractOwnerId; // contractOwnerId

	private String contractOwnerName; // contractOwnerName

	private String contractPassedDate; // contractPassedDate

	private String contractPrice; // contractPrice

	private String contractSignedDate; // contractSignedDate

	private String contractStatus; // contractStatus

	private String contractType; // contractType

	private String createDate; // createDate

	private String payType; // payType

	private String projectId; // projectId

	private String projectName; // projectName

	private String projectNo; // projectNo

	private String remark; // remark

	private String removed; // removed

	private String updateDate; // updateDate

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

	public void setBuildSupplierId(String buildSupplierId) {
		this.buildSupplierId = buildSupplierId;
	}

	@Column(name = "BUILD_SUPPLIER_ID", nullable = true, length = 40)
	public String getBuildSupplierId() {
		return buildSupplierId;
	}

	public void setBuildSupplierName(String buildSupplierName) {
		this.buildSupplierName = buildSupplierName;
	}

	@Column(name = "BUILD_SUPPLIER_NAME", nullable = true, length = 40)
	public String getBuildSupplierName() {
		return buildSupplierName;
	}

	public void setContractAttachment(String contractAttachment) {
		this.contractAttachment = contractAttachment;
	}

	@Column(name = "CONTRACT_ATTACHMENT", nullable = true, length = 40)
	public String getContractAttachment() {
		return contractAttachment;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	@Column(name = "CONTRACT_CONTENT", nullable = true, length = 4000)
	public String getContractContent() {
		return contractContent;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	@Column(name = "CONTRACT_END_DATE", nullable = true, length = 40)
	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 40)
	public String getContractName() {
		return contractName;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_NO", nullable = true, length = 40)
	public String getContractNo() {
		return contractNo;
	}

	public void setContractOwnerId(String contractOwnerId) {
		this.contractOwnerId = contractOwnerId;
	}

	@Column(name = "CONTRACT_OWNER_ID", nullable = true, length = 40)
	public String getContractOwnerId() {
		return contractOwnerId;
	}

	public void setContractOwnerName(String contractOwnerName) {
		this.contractOwnerName = contractOwnerName;
	}

	@Column(name = "CONTRACT_OWNER_NAME", nullable = true, length = 40)
	public String getContractOwnerName() {
		return contractOwnerName;
	}

	public void setContractPassedDate(String contractPassedDate) {
		this.contractPassedDate = contractPassedDate;
	}

	@Column(name = "CONTRACT_PASSED_DATE", nullable = true, length = 40)
	public String getContractPassedDate() {
		return contractPassedDate;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 40)
	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractSignedDate(String contractSignedDate) {
		this.contractSignedDate = contractSignedDate;
	}

	@Column(name = "CONTRACT_SIGNED_DATE", nullable = true, length = 40)
	public String getContractSignedDate() {
		return contractSignedDate;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	@Column(name = "CONTRACT_STATUS", nullable = true, length = 1)
	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "CONTRACT_TYPE", nullable = true, length = 1)
	public String getContractType() {
		return contractType;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 40)
	public String getCreateDate() {
		return createDate;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "PAY_TYPE", nullable = true, length = 40)
	public String getPayType() {
		return payType;
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

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 40)
	public String getProjectNo() {
		return projectNo;
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

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 40)
	public String getUpdateDate() {
		return updateDate;
	}

}
