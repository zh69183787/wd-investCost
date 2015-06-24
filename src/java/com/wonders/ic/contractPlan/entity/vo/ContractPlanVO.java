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

package com.wonders.ic.contractPlan.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * ContractPlanʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractPlanVO implements ValueObject {

	private String id;
	private String buildSupplierId;
	private String buildSupplierName;
	private String contractAttachment;
	private String contractContent;
	private String contractEndDate;
	private String contractName;
	private String contractNo;
	private String contractOwnerId;
	private String contractOwnerName;
	private String contractPassedDate;
	private String contractPrice;
	private String contractSignedDate;
	private String contractStatus;
	private String contractType;
	private String createDate;
	private String payType;
	private String projectId;
	private String projectName;
	private String projectNo;
	private String remark;
	private String removed;
	private String updateDate;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setBuildSupplierId(String buildSupplierId) {
		this.buildSupplierId = buildSupplierId;
	}

	public String getBuildSupplierId() {
		return buildSupplierId;
	}

	public void setBuildSupplierName(String buildSupplierName) {
		this.buildSupplierName = buildSupplierName;
	}

	public String getBuildSupplierName() {
		return buildSupplierName;
	}

	public void setContractAttachment(String contractAttachment) {
		this.contractAttachment = contractAttachment;
	}

	public String getContractAttachment() {
		return contractAttachment;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractOwnerId(String contractOwnerId) {
		this.contractOwnerId = contractOwnerId;
	}

	public String getContractOwnerId() {
		return contractOwnerId;
	}

	public void setContractOwnerName(String contractOwnerName) {
		this.contractOwnerName = contractOwnerName;
	}

	public String getContractOwnerName() {
		return contractOwnerName;
	}

	public void setContractPassedDate(String contractPassedDate) {
		this.contractPassedDate = contractPassedDate;
	}

	public String getContractPassedDate() {
		return contractPassedDate;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractSignedDate(String contractSignedDate) {
		this.contractSignedDate = contractSignedDate;
	}

	public String getContractSignedDate() {
		return contractSignedDate;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractType() {
		return contractType;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return payType;
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

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectNo() {
		return projectNo;
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

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

}
