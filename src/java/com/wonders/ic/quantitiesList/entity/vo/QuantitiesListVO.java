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

package com.wonders.ic.quantitiesList.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * QuantitiesListʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class QuantitiesListVO implements ValueObject {

	private String contractId;
	private String contractNo;
	private String contractPrice;
	private String createDate;
	private String fillCompany;
	private String fillPerson;
	private String id;
	private String imageProgress;
	private String operateDate;
	private String pay;
	private String projectAmount;
	private String projectQuantitiesDescribe;
	private String projectQuantitiesStatus;
	private String remark;
	private String removed;

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setFillCompany(String fillCompany) {
		this.fillCompany = fillCompany;
	}

	public String getFillCompany() {
		return fillCompany;
	}

	public void setFillPerson(String fillPerson) {
		this.fillPerson = fillPerson;
	}

	public String getFillPerson() {
		return fillPerson;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setImageProgress(String imageProgress) {
		this.imageProgress = imageProgress;
	}

	public String getImageProgress() {
		return imageProgress;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPay() {
		return pay;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectQuantitiesDescribe(String projectQuantitiesDescribe) {
		this.projectQuantitiesDescribe = projectQuantitiesDescribe;
	}

	public String getProjectQuantitiesDescribe() {
		return projectQuantitiesDescribe;
	}

	public void setProjectQuantitiesStatus(String projectQuantitiesStatus) {
		this.projectQuantitiesStatus = projectQuantitiesStatus;
	}

	public String getProjectQuantitiesStatus() {
		return projectQuantitiesStatus;
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

}
