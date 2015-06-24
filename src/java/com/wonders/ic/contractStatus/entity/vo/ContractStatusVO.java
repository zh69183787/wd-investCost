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

package com.wonders.ic.contractStatus.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * ContractStatusʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractStatusVO implements ValueObject {

	private String id;
	private String contractId;
	private String money;
	private String operateDate;
	private String persent;
	private String reason;
	private String remark;
	private String removed;
	private String type;
	private Date updateDate;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMoney() {
		return money;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setPersent(String persent) {
		this.persent = persent;
	}

	public String getPersent() {
		return persent;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}
