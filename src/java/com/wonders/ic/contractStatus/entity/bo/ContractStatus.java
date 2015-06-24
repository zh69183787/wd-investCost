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

package com.wonders.ic.contractStatus.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * ContractStatusʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_CONTRACT_STATUS")
public class ContractStatus implements Serializable, BusinessObject {

	private String id; // id

	private String contractId; // contractId

	private String money; // money

	private String operateDate; // operateDate

	private String persent; // persent

	private String reason; // reason

	private String remark; // remark

	private String removed; // removed

	private String type; // type

	private Date updateDate; // updateDate
	
	private String changeFlowNo;
	private String changeRaisedCompany;
	private String attach;
	private String line; 
	
	
	@Column(name = "CHANGE_FLOW_NO", nullable = true, length = 200)
	public String getChangeFlowNo() {
		return changeFlowNo;
	}

	public void setChangeFlowNo(String changeFlowNo) {
		this.changeFlowNo = changeFlowNo;
	}
	@Column(name = "CHANGE_RAISED_COMPANY", nullable = true, length = 200)
	public String getChangeRaisedCompany() {
		return changeRaisedCompany;
	}

	public void setChangeRaisedCompany(String changeRaisedCompany) {
		this.changeRaisedCompany = changeRaisedCompany;
	}
	@Column(name = "ATTACH", nullable = true, length = 200)
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
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

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "CONTRACT_ID", nullable = true, length = 40)
	public String getContractId() {
		return contractId;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "MONEY", nullable = true, length = 40)
	public String getMoney() {
		return money;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 40)
	public String getOperateDate() {
		return operateDate;
	}

	public void setPersent(String persent) {
		this.persent = persent;
	}

	@Column(name = "PERSENT", nullable = true, length = 40)
	public String getPersent() {
		return persent;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "REASON", nullable = true, length = 1000)
	public String getReason() {
		return reason;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 1000)
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

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE", nullable = true, length = 1)
	public String getType() {
		return type;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 7)
	public Date getUpdateDate() {
		return updateDate;
	}

	@Column(name = "LINE", nullable = true, length = 100)
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
