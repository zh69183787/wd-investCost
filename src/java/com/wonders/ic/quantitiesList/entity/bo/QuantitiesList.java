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

package com.wonders.ic.quantitiesList.entity.bo;

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
 * QuantitiesListʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_QUANTITIES_LIST")
public class QuantitiesList implements Serializable, BusinessObject {

	private String contractId; // contractId

	private String contractNo; // contractNo

	private String contractPrice; // contractPrice

	private String createDate; // createDate

	private String fillCompany; // fillCompany

	private String fillPerson; // fillPerson

	private String id; // id

	private String imageProgress; // imageProgress

	private String operateDate; // operateDate

	private String pay; // pay

	private String projectAmount; // projectAmount

	private String projectQuantitiesDescribe; // projectQuantitiesDescribe

	private String projectQuantitiesStatus; // projectQuantitiesStatus

	private String remark; // remark

	private String removed; // removed
	
	private String totalPrice;

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
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

	@Column(name = "CONTRACT_ID", nullable = true, length = 40)
	public String getContractId() {
		return contractId;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_NO", nullable = true, length = 40)
	public String getContractNo() {
		return contractNo;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 100)
	public String getContractPrice() {
		return contractPrice;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 40)
	public String getCreateDate() {
		return createDate;
	}

	public void setFillCompany(String fillCompany) {
		this.fillCompany = fillCompany;
	}

	@Column(name = "FILL_COMPANY", nullable = true, length = 400)
	public String getFillCompany() {
		return fillCompany;
	}

	public void setFillPerson(String fillPerson) {
		this.fillPerson = fillPerson;
	}

	@Column(name = "FILL_PERSON", nullable = true, length = 100)
	public String getFillPerson() {
		return fillPerson;
	}

	public void setImageProgress(String imageProgress) {
		this.imageProgress = imageProgress;
	}

	@Column(name = "IMAGE_PROGRESS", nullable = true, length = 100)
	public String getImageProgress() {
		return imageProgress;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 40)
	public String getOperateDate() {
		return operateDate;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Column(name = "PAY", nullable = true, length = 100)
	public String getPay() {
		return pay;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	@Column(name = "PROJECT_AMOUNT", nullable = true, length = 100)
	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectQuantitiesDescribe(String projectQuantitiesDescribe) {
		this.projectQuantitiesDescribe = projectQuantitiesDescribe;
	}

	@Column(name = "PROJECT_QUANTITIES_DESCRIBE", nullable = true, length = 4000)
	public String getProjectQuantitiesDescribe() {
		return projectQuantitiesDescribe;
	}

	public void setProjectQuantitiesStatus(String projectQuantitiesStatus) {
		this.projectQuantitiesStatus = projectQuantitiesStatus;
	}

	@Column(name = "PROJECT_QUANTITIES_STATUS", nullable = true, length = 1)
	public String getProjectQuantitiesStatus() {
		return projectQuantitiesStatus;
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

	@Column(name ="TOTAL_PRICE",nullable = true,length=100)
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
