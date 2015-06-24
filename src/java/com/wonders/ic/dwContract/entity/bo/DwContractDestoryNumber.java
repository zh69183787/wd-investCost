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

import java.util.Date;
import java.util.Set;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwContractDestoryNumberʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-8
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_CONTRACT_DESTORY_NUMBER")
public class DwContractDestoryNumber implements Serializable, BusinessObject {

	private String id; // id

	private Date addDate; // addDate

	private String contractName; // contractName

	private BigDecimal contractPrice; // contractPrice

	private String contractStatus; // contractStatus

	private Date createDate; // createDate

	private BigDecimal finalPrice; // finalPrice

	private String projectName; // projectName

	private String projectNo; // projectNo

	private String projectType; // projectType

	private String supplier; // supplier
	
	private String contractNo;

	private String contractId;
	private String projectId;
	private String contractSignedDate;
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Column(name = "ADD_DATE", nullable = true, length = 11)
	public Date getAddDate() {
		return addDate;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 200)
	public String getContractName() {
		return contractName;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 22)
	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	@Column(name = "CONTRACT_STATUS", nullable = true, length = 2)
	public String getContractStatus() {
		return contractStatus;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 11)
	public Date getCreateDate() {
		return createDate;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Column(name = "FINAL_PRICE", nullable = true, length = 22)
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_NAME", nullable = true, length = 200)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 200)
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PROJECT_TYPE", nullable = true, length = 2)
	public String getProjectType() {
		return projectType;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Column(name = "SUPPLIER", nullable = true, length = 200)
	public String getSupplier() {
		return supplier;
	}

	@Column(name = "CONTRACT_NO", nullable = true, length = 200)
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_ID", nullable = true, length = 40)
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "PROJECT_ID", nullable = true, length = 40)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CONTRACT_SIGNED_DATE", nullable = true, length = 20)
	public String getContractSignedDate() {
		return contractSignedDate;
	}

	public void setContractSignedDate(String contractSignedDate) {
		this.contractSignedDate = contractSignedDate;
	}

	
}
