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

package com.wonders.ic.project.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Projectʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_PROJECT")
public class Project implements Serializable, BusinessObject {

	private String id; // id

	private String createDate; // createDate

	private String operateDate; // operateDate

	private String projectAdddept; // projectAdddept

	private String projectAddperson; // projectAddperon

	private String projectAddtimeDate; // projectAddtimeDate

	private String projectAttachId; // projectAttachId

	private String projectBudgetAll; // projectBudgetAll

	private String projectCheckunit; // projectCheckunit

	private String projectCompany; // projectCompany

	private String projectCreateunit; // projectCreateunit

	private String projectEndtimePlanDate; // projectEndtimePlanDate

	private String projectEstimateAll; // projectEstimateAll

	private String projectExecuteunit; // projectExecuteunit

	private String projectExplain; // projectExplain

	private String projectMoneysource; // projectMoneysource

	private String projectName; // projectName

	private String projectNo; // projectNo

	private String projectStarttimePlanDate; // projectStarttimePlanDate

	private String projectState; // projectState

	private String projectType; // projectType
	
	private String payType; // 成本属性 1：资本性，2：费用性

	private String remark; // remark

	private String removed; // removed
	
	private String keyword;
	
	private String projectCompanyId;
	private String projectAddpersonId;
	private String projectAdddeptId;
	private String substituteConstruction;
	private String dispatchNo;
	
	private String projectType2;
	private String contractPerson;
	private String contractPhone;
	private String professionalType;
	
	private String moneySourceType;
	private String approvalDate;
	
	private String projectFeasibilityNo;
	private String projectFeasibilityDate;
	private String projectFeasibilityBudget;
	private String projectFeasibilityRemark;
	private String primaryDesignNo;
	private String primaryDesignDate;
	private String primaryDesignBudget;
	private String primaryDesignMoney;
	private String primaryDesignRemark;
	
	private String projectDestoryDate; 
	private String cityAllowance;
	
	private String settlementPrice;	//决算价，销项完成后赋值
	
	public Project() {
	}

	public Project(Project project) {
		BeanUtils.copyProperties(project, this);
	}

	@Column(name="PROJECT_DESTORY_DATE",nullable=true,length=40)
	public String getProjectDestoryDate() {
		return projectDestoryDate;
	}

	public void setProjectDestoryDate(String projectDestoryDate) {
		this.projectDestoryDate = projectDestoryDate;
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

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 40)
	public String getCreateDate() {
		return createDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 40)
	public String getOperateDate() {
		return operateDate;
	}

	public void setProjectAdddept(String projectAdddept) {
		this.projectAdddept = projectAdddept;
	}

	@Column(name = "PROJECT_ADDDEPT", nullable = true, length = 40)
	public String getProjectAdddept() {
		return projectAdddept;
	}

	public void setProjectAddtimeDate(String projectAddtimeDate) {
		this.projectAddtimeDate = projectAddtimeDate;
	}

	@Column(name = "PROJECT_ADDPERSON", nullable = true, length = 40)
	public String getProjectAddperson() {
		return projectAddperson;
	}

	public void setProjectAddperson(String projectAddperson) {
		this.projectAddperson = projectAddperson;
	}

	@Column(name = "PROJECT_ADDTIME_DATE", nullable = true, length = 40)
	public String getProjectAddtimeDate() {
		return projectAddtimeDate;
	}

	public void setProjectAttachId(String projectAttachId) {
		this.projectAttachId = projectAttachId;
	}

	@Column(name = "PROJECT_ATTACH_ID", nullable = true, length = 40)
	public String getProjectAttachId() {
		return projectAttachId;
	}

	public void setProjectBudgetAll(String projectBudgetAll) {
		this.projectBudgetAll = projectBudgetAll;
	}

	@Column(name = "PROJECT_BUDGET_ALL", nullable = true, length = 40)
	public String getProjectBudgetAll() {
		return projectBudgetAll;
	}

	public void setProjectCheckunit(String projectCheckunit) {
		this.projectCheckunit = projectCheckunit;
	}

	@Column(name = "PROJECT_CHECKUNIT", nullable = true, length = 40)
	public String getProjectCheckunit() {
		return projectCheckunit;
	}

	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}

	@Column(name = "PROJECT_COMPANY", nullable = true, length = 40)
	public String getProjectCompany() {
		return projectCompany;
	}

	public void setProjectCreateunit(String projectCreateunit) {
		this.projectCreateunit = projectCreateunit;
	}

	@Column(name = "PROJECT_CREATEUNIT", nullable = true, length = 40)
	public String getProjectCreateunit() {
		return projectCreateunit;
	}

	public void setProjectEndtimePlanDate(String projectEndtimePlanDate) {
		this.projectEndtimePlanDate = projectEndtimePlanDate;
	}

	@Column(name = "PROJECT_ENDTIME_PLAN_DATE", nullable = true, length = 40)
	public String getProjectEndtimePlanDate() {
		return projectEndtimePlanDate;
	}

	public void setProjectEstimateAll(String projectEstimateAll) {
		this.projectEstimateAll = projectEstimateAll;
	}

	@Column(name = "PROJECT_ESTIMATE_ALL", nullable = true, length = 40)
	public String getProjectEstimateAll() {
		return projectEstimateAll;
	}

	public void setProjectExecuteunit(String projectExecuteunit) {
		this.projectExecuteunit = projectExecuteunit;
	}

	@Column(name = "PROJECT_EXECUTEUNIT", nullable = true, length = 40)
	public String getProjectExecuteunit() {
		return projectExecuteunit;
	}

	public void setProjectExplain(String projectExplain) {
		this.projectExplain = projectExplain;
	}

	@Column(name = "PROJECT_EXPLAIN", nullable = true, length = 4000)
	public String getProjectExplain() {
		return projectExplain;
	}

	public void setProjectMoneysource(String projectMoneysource) {
		this.projectMoneysource = projectMoneysource;
	}

	@Column(name = "PROJECT_MONEYSOURCE", nullable = true, length = 4000)
	public String getProjectMoneysource() {
		return projectMoneysource;
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

	public void setProjectStarttimePlanDate(String projectStarttimePlanDate) {
		this.projectStarttimePlanDate = projectStarttimePlanDate;
	}

	@Column(name = "PROJECT_STARTTIME_PLAN_DATE", nullable = true, length = 40)
	public String getProjectStarttimePlanDate() {
		return projectStarttimePlanDate;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	@Column(name = "PROJECT_STATE", nullable = true, length = 1)
	public String getProjectState() {
		return projectState;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PROJECT_TYPE", nullable = true, length = 40)
	public String getProjectType() {
		return projectType;
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

	@Column(name = "KEYWORD", nullable = true, length = 40)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "PROJECT_COMPANY_ID", nullable = true, length = 40)
	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	@Column(name = "PROJECT_ADDPERSON_ID", nullable = true, length = 40)
	public String getProjectAddpersonId() {
		return projectAddpersonId;
	}

	public void setProjectAddpersonId(String projectAddpersonId) {
		this.projectAddpersonId = projectAddpersonId;
	}
	 
	@Column(name = "PROJECT_ADDDEPT_ID", nullable = true, length = 40)
	public String getProjectAdddeptId() {
		return projectAdddeptId;
	}

	public void setProjectAdddeptId(String projectAdddeptId) {
		this.projectAdddeptId = projectAdddeptId;
	}

	@Column(name = "SUBSTITUTE_CONSTRUCTION", nullable = true, length = 2)
	public String getSubstituteConstruction() {
		return substituteConstruction;
	}

	public void setSubstituteConstruction(String substituteConstruction) {
		this.substituteConstruction = substituteConstruction;
	}

	@Column(name = "DISPATCH_NO", nullable = true, length = 40)
	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	@Column(name = "PROJECT_TYPE2", nullable = true, length = 40)
	public String getProjectType2() {
		return projectType2;
	}

	public void setProjectType2(String projectType2) {
		this.projectType2 = projectType2;
	}

	@Column(name = "CONTRACT_PERSON", nullable = true, length = 40)
	public String getContractPerson() {
		return contractPerson;
	}

	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}

	@Column(name = "CONTRACT_PHONE", nullable = true, length = 40)
	public String getContractPhone() {
		return contractPhone;
	}

	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}

	@Column(name = "PROFESSIONAL_TYPE", nullable = true, length = 10)
	public String getProfessionalType() {
		return professionalType;
	}

	public void setProfessionalType(String professionalType) {
		this.professionalType = professionalType;
	}

	@Column(name = "PROJECT_FEASIBILITY_NO", nullable = true, length = 200)
	public String getProjectFeasibilityNo() {
		return projectFeasibilityNo;
	}

	public void setProjectFeasibilityNo(String projectFeasibilityNo) {
		this.projectFeasibilityNo = projectFeasibilityNo;
	}

	@Column(name = "PROJECT_FEASIBILITY_DATE", nullable = true, length = 40)
	public String getProjectFeasibilityDate() {
		return projectFeasibilityDate;
	}

	public void setProjectFeasibilityDate(String projectFeasibilityDate) {
		this.projectFeasibilityDate = projectFeasibilityDate;
	}

	@Column(name = "PROJECT_FEASIBILITY_BUDGET", nullable = true, length = 40)
	public String getProjectFeasibilityBudget() {
		return projectFeasibilityBudget;
	}

	public void setProjectFeasibilityBudget(String projectFeasibilityBudget) {
		this.projectFeasibilityBudget = projectFeasibilityBudget;
	}

	@Column(name = "PROJECT_FEASIBILITY_REMARK", nullable = true, length = 4000)
	public String getProjectFeasibilityRemark() {
		return projectFeasibilityRemark;
	}

	public void setProjectFeasibilityRemark(String projectFeasibilityRemark) {
		this.projectFeasibilityRemark = projectFeasibilityRemark;
	}

	@Column(name = "PRIMARY_DESIGN_NO", nullable = true, length = 200)
	public String getPrimaryDesignNo() {
		return primaryDesignNo;
	}

	public void setPrimaryDesignNo(String primaryDesignNo) {
		this.primaryDesignNo = primaryDesignNo;
	}

	@Column(name = "PRIMARY_DESIGN_DATE", nullable = true, length = 40)
	public String getPrimaryDesignDate() {
		return primaryDesignDate;
	}

	public void setPrimaryDesignDate(String primaryDesignDate) {
		this.primaryDesignDate = primaryDesignDate;
	}

	@Column(name = "PRIMARY_DESIGN_BUDGET", nullable = true, length = 40)
	public String getPrimaryDesignBudget() {
		return primaryDesignBudget;
	}

	public void setPrimaryDesignBudget(String primaryDesignBudget) {
		this.primaryDesignBudget = primaryDesignBudget;
	}

	@Column(name = "PRIMARY_DESIGN_MONEY", nullable = true, length = 40)
	public String getPrimaryDesignMoney() {
		return primaryDesignMoney;
	}

	public void setPrimaryDesignMoney(String primaryDesignMoney) {
		this.primaryDesignMoney = primaryDesignMoney;
	}

	@Column(name = "PRIMARY_DESIGN_REMARK", nullable = true, length = 4000)
	public String getPrimaryDesignRemark() {
		return primaryDesignRemark;
	}

	public void setPrimaryDesignRemark(String primaryDesignRemark) {
		this.primaryDesignRemark = primaryDesignRemark;
	}

	@Column(name = "APPROVAL_DATE", nullable = true, length = 40)
	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "MONEY_SOURCE_TYPE", nullable = true, length = 1)
	public String getMoneySourceType() {
		return moneySourceType;
	}

	public void setMoneySourceType(String moneySourceType) {
		this.moneySourceType = moneySourceType;
	}

	@Column(name = "CITY_ALLOWANCE", nullable = true, length = 2)
	public String getCityAllowance() {
		return cityAllowance;
	}

	public void setCityAllowance(String cityAllowance) {
		this.cityAllowance = cityAllowance;
	}

	@Column(name = "PAY_TYPE", nullable = true, length = 1)
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "SETTLEMENT_PRICE", nullable = true, length = 40)
	public String getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	
	
	
}
