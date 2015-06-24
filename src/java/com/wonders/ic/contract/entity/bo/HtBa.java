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
 * HtBaʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-5
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "HT_BA")
public class HtBa implements Serializable, BusinessObject {

	private String addPerson; // addPerson

	private String addTime; // addTime

	private String attr1; // attr1

	private Date attr10; // attr10

	private String attr2; // attr2

	private String attr3; // attr3

	private String attr4; // attr4

	private String attr5; // attr5

	private String attr6; // attr6

	private String attr7; // attr7

	private Date attr8; // attr8

	private Date attr9; // attr9

	private String contractSignTime; // contractSignTime

	private String examinePassTime; // examinePassTime

	private Long htId; // htId

	private Long id; // id

	private String modelId; // modelId

	private String performTimelimit; // performTimelimit

	private String pinstanceId; // pinstanceId

	private String remark; // remark

	private String signCop; // signCop

	private String userId; // userId

	private String workitemId; // workitemId

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

	public void setAttr10(Date attr10) {
		this.attr10 = attr10;
	}

	@Column(name = "ATTR10", nullable = true, length = 7)
	public Date getAttr10() {
		return attr10;
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

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	@Column(name = "ATTR6", nullable = true, length = 200)
	public String getAttr6() {
		return attr6;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	@Column(name = "ATTR7", nullable = true, length = 200)
	public String getAttr7() {
		return attr7;
	}

	public void setAttr8(Date attr8) {
		this.attr8 = attr8;
	}

	@Column(name = "ATTR8", nullable = true, length = 7)
	public Date getAttr8() {
		return attr8;
	}

	public void setAttr9(Date attr9) {
		this.attr9 = attr9;
	}

	@Column(name = "ATTR9", nullable = true, length = 7)
	public Date getAttr9() {
		return attr9;
	}

	public void setContractSignTime(String contractSignTime) {
		this.contractSignTime = contractSignTime;
	}

	@Column(name = "CONTRACT_SIGN_TIME", nullable = true, length = 200)
	public String getContractSignTime() {
		return contractSignTime;
	}

	public void setExaminePassTime(String examinePassTime) {
		this.examinePassTime = examinePassTime;
	}

	@Column(name = "EXAMINE_PASS_TIME", nullable = true, length = 200)
	public String getExaminePassTime() {
		return examinePassTime;
	}

	public void setHtId(Long htId) {
		this.htId = htId;
	}

	@Column(name = "HT_ID", nullable = true, length = 22)
	public Long getHtId() {
		return htId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 22)
	public Long getId() {
		return id;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Column(name = "MODEL_ID", nullable = true, length = 200)
	public String getModelId() {
		return modelId;
	}

	public void setPerformTimelimit(String performTimelimit) {
		this.performTimelimit = performTimelimit;
	}

	@Column(name = "PERFORM_TIMELIMIT", nullable = true, length = 200)
	public String getPerformTimelimit() {
		return performTimelimit;
	}

	public void setPinstanceId(String pinstanceId) {
		this.pinstanceId = pinstanceId;
	}

	@Column(name = "PINSTANCE_ID", nullable = true, length = 200)
	public String getPinstanceId() {
		return pinstanceId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 4000)
	public String getRemark() {
		return remark;
	}

	public void setSignCop(String signCop) {
		this.signCop = signCop;
	}

	@Column(name = "SIGN_COP", nullable = true, length = 200)
	public String getSignCop() {
		return signCop;
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
