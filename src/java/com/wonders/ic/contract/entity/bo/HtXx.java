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

package com.wonders.ic.contract.entity.bo;

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
 * HtXxʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-5
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "HT_XX")
public class HtXx implements Serializable, BusinessObject {

	private Long id; // id

	private String addPerson; // addPerson

	private String addTime; // addTime

	private String attr1; // attr1

	private String attr2; // attr2

	private String attr3; // attr3

	private String attr4; // attr4

	private String attr5; // attr5

	private Date attr6; // attr6

	private Long autScanFlag; // autScanFlag

	private String budget; // budget

	private String contractMoney; // contractMoney

	private String contractMoneyType; // contractMoneyType

	private String contractName; // contractName

	private String contractName1; // contractName1

	private String contractNum; // contractNum

	private String contractStage; // contractStage

	private String contractType; // contractType

	private String currentDep; // currentDep

	private String currentLink; // currentLink

	private String currentPerson; // currentPerson

	private String currentSj; // currentSj

	private String currentSj2; // currentSj2

	private String dealDepSuggest; // dealDepSuggest

	private String dealPerson; // dealPerson

	private String dealState; // dealState

	private String fileNum; // fileNum

	private String flag; // flag

	private String flag1; // flag1

	private String flag2; // flag2

	private String flag3; // flag3

	private String flag4; // flag4

	private String htAttach; // htAttach

	private String modelId; // modelId

	private String nextDep; // nextDep

	private String nextPerson; // nextPerson

	private String pinstanceId; // pinstanceId

	private String planNum; // planNum

	private String projectCoName; // projectCoName

	private String projectNum; // projectNum

	private String remark; // remark

	private Long removed; // removed

	private String selfNum; // selfNum

	private String signCop; // signCop

	private String statusHt; // statusHt

	private String taskid; // taskid

	private String taskuser; // taskuser

	private String totalDate; // totalDate

	private String userId; // userId

	private String workitemId; // workitemId

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
	}

	@Column(name = "ADD_PERSON", nullable = true, length = 200)
	public String getAddPerson() {
		return addPerson;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ADD_TIME", nullable = true, length = 200)
	public String getAddTime() {
		return addTime;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	@Column(name = "ATTR1", nullable = true, length = 200)
	public String getAttr1() {
		return attr1;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	@Column(name = "ATTR2", nullable = true, length = 200)
	public String getAttr2() {
		return attr2;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	@Column(name = "ATTR3", nullable = true, length = 200)
	public String getAttr3() {
		return attr3;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	@Column(name = "ATTR4", nullable = true, length = 200)
	public String getAttr4() {
		return attr4;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	@Column(name = "ATTR5", nullable = true, length = 200)
	public String getAttr5() {
		return attr5;
	}

	public void setAttr6(Date attr6) {
		this.attr6 = attr6;
	}

	@Column(name = "ATTR6", nullable = true, length = 7)
	public Date getAttr6() {
		return attr6;
	}

	public void setAutScanFlag(Long autScanFlag) {
		this.autScanFlag = autScanFlag;
	}

	@Column(name = "AUT_SCAN_FLAG", nullable = true, length = 22)
	public Long getAutScanFlag() {
		return autScanFlag;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	@Column(name = "BUDGET", nullable = true, length = 200)
	public String getBudget() {
		return budget;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	@Column(name = "CONTRACT_MONEY", nullable = true, length = 200)
	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoneyType(String contractMoneyType) {
		this.contractMoneyType = contractMoneyType;
	}

	@Column(name = "CONTRACT_MONEY_TYPE", nullable = true, length = 200)
	public String getContractMoneyType() {
		return contractMoneyType;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 200)
	public String getContractName() {
		return contractName;
	}

	public void setContractName1(String contractName1) {
		this.contractName1 = contractName1;
	}

	@Column(name = "CONTRACT_NAME1", nullable = true, length = 200)
	public String getContractName1() {
		return contractName1;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	@Column(name = "CONTRACT_NUM", nullable = true, length = 200)
	public String getContractNum() {
		return contractNum;
	}

	public void setContractStage(String contractStage) {
		this.contractStage = contractStage;
	}

	@Column(name = "CONTRACT_STAGE", nullable = true, length = 200)
	public String getContractStage() {
		return contractStage;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "CONTRACT_TYPE", nullable = true, length = 200)
	public String getContractType() {
		return contractType;
	}

	public void setCurrentDep(String currentDep) {
		this.currentDep = currentDep;
	}

	@Column(name = "CURRENT_DEP", nullable = true, length = 200)
	public String getCurrentDep() {
		return currentDep;
	}

	public void setCurrentLink(String currentLink) {
		this.currentLink = currentLink;
	}

	@Column(name = "CURRENT_LINK", nullable = true, length = 200)
	public String getCurrentLink() {
		return currentLink;
	}

	public void setCurrentPerson(String currentPerson) {
		this.currentPerson = currentPerson;
	}

	@Column(name = "CURRENT_PERSON", nullable = true, length = 200)
	public String getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentSj(String currentSj) {
		this.currentSj = currentSj;
	}

	@Column(name = "CURRENT_SJ", nullable = true, length = 200)
	public String getCurrentSj() {
		return currentSj;
	}

	public void setCurrentSj2(String currentSj2) {
		this.currentSj2 = currentSj2;
	}

	@Column(name = "CURRENT_SJ2", nullable = true, length = 200)
	public String getCurrentSj2() {
		return currentSj2;
	}

	public void setDealDepSuggest(String dealDepSuggest) {
		this.dealDepSuggest = dealDepSuggest;
	}

	@Column(name = "DEAL_DEP_SUGGEST", nullable = true, length = 2000)
	public String getDealDepSuggest() {
		return dealDepSuggest;
	}

	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	@Column(name = "DEAL_PERSON", nullable = true, length = 200)
	public String getDealPerson() {
		return dealPerson;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	@Column(name = "DEAL_STATE", nullable = true, length = 200)
	public String getDealState() {
		return dealState;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	@Column(name = "FILE_NUM", nullable = true, length = 200)
	public String getFileNum() {
		return fileNum;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	@Column(name = "FLAG1", nullable = true, length = 200)
	public String getFlag1() {
		return flag1;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	@Column(name = "FLAG2", nullable = true, length = 200)
	public String getFlag2() {
		return flag2;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	@Column(name = "FLAG3", nullable = true, length = 200)
	public String getFlag3() {
		return flag3;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	@Column(name = "FLAG4", nullable = true, length = 200)
	public String getFlag4() {
		return flag4;
	}

	public void setHtAttach(String htAttach) {
		this.htAttach = htAttach;
	}

	@Column(name = "HT_ATTACH", nullable = true, length = 100)
	public String getHtAttach() {
		return htAttach;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Column(name = "MODEL_ID", nullable = true, length = 200)
	public String getModelId() {
		return modelId;
	}

	public void setNextDep(String nextDep) {
		this.nextDep = nextDep;
	}

	@Column(name = "NEXT_DEP", nullable = true, length = 200)
	public String getNextDep() {
		return nextDep;
	}

	public void setNextPerson(String nextPerson) {
		this.nextPerson = nextPerson;
	}

	@Column(name = "NEXT_PERSON", nullable = true, length = 200)
	public String getNextPerson() {
		return nextPerson;
	}

	public void setPinstanceId(String pinstanceId) {
		this.pinstanceId = pinstanceId;
	}

	@Column(name = "PINSTANCE_ID", nullable = true, length = 200)
	public String getPinstanceId() {
		return pinstanceId;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	@Column(name = "PLAN_NUM", nullable = true, length = 200)
	public String getPlanNum() {
		return planNum;
	}

	public void setProjectCoName(String projectCoName) {
		this.projectCoName = projectCoName;
	}

	@Column(name = "PROJECT_CO_NAME", nullable = true, length = 200)
	public String getProjectCoName() {
		return projectCoName;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	@Column(name = "PROJECT_NUM", nullable = true, length = 200)
	public String getProjectNum() {
		return projectNum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 2000)
	public String getRemark() {
		return remark;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public Long getRemoved() {
		return removed;
	}

	public void setSelfNum(String selfNum) {
		this.selfNum = selfNum;
	}

	@Column(name = "SELF_NUM", nullable = true, length = 200)
	public String getSelfNum() {
		return selfNum;
	}

	public void setSignCop(String signCop) {
		this.signCop = signCop;
	}

	@Column(name = "SIGN_COP", nullable = true, length = 200)
	public String getSignCop() {
		return signCop;
	}

	public void setStatusHt(String statusHt) {
		this.statusHt = statusHt;
	}

	@Column(name = "STATUS_HT", nullable = true, length = 100)
	public String getStatusHt() {
		return statusHt;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	@Column(name = "TASKID", nullable = true, length = 200)
	public String getTaskid() {
		return taskid;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	@Column(name = "TASKUSER", nullable = true, length = 200)
	public String getTaskuser() {
		return taskuser;
	}

	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}

	@Column(name = "TOTAL_DATE", nullable = true, length = 200)
	public String getTotalDate() {
		return totalDate;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_ID", nullable = true, length = 200)
	public String getUserId() {
		return userId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	@Column(name = "WORKITEM_ID", nullable = true, length = 200)
	public String getWorkitemId() {
		return workitemId;
	}

}
