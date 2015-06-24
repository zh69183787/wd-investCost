package com.wonders.ic.contract.entity.bo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Contractʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_CONTRACT")
public class Contract implements Serializable, BusinessObject {

	private String id; // id

	private String buildSupplierId; // buildSupplierId

	private String buildSupplierName; // buildSupplierName

	private String contractAttachment; // contractAttachment

	private String contractContent; // contractContent

	private String contractName; // contractName

	private String contractNo; // contractNo

	private String contractOwnerId; // contractOwnerId

	private String contractOwnerName; // contractOwnerName

	private String contractPassedDate; // contractPassedDate

	private String contractPlanId; // contractPlanId

	private String contractPlanNo; // contractPlanNo

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
	
	private String contractEndDate; // contractEndDate
	
	private String contractStartDate;
	
	
	private String contractOwnerExecuteId;
	private String contractOwnerExecuteName;
	
	private String verifyPrice;
	private String finalPrice;
	private String inviteBidType;
	private String nationVerifyPrice;
	
	private String selfNo; //自用编号
	private String createType;	//创建类型
	private String contractDestoryDate;	//合同销号时间
	
	private String registerPersonName;		//合同登记人名称
	private String registerPersonLoginname;		//合同登记人id
	private String contractGrouping;		//合同分组
	
	private String contractAttribute;    //(建设类)合同分类
	private String contractAttributeId;    //(建设类)合同分类ID
	
	private String budgetType;     //预算类型
	private String budgetTypeCode;   //预算类型编码
	private String statType;   //统计类型
	
	
	private List<KpiClearStatus> kpiClearStatus;
	private List<ContractChangeProtocol> contractChangeProtocols;  //合同变更协议
	
	
	
	public Long evaluateNum;			//评估次数
	
	@Column(name = "CONTRACT_ATTRIBUTE_ID", nullable = true, length = 100)
	public String getContractAttributeId() {
		return contractAttributeId;
	}
	
	public void setContractAttributeId(String contractAttributeId){
		this.contractAttributeId = contractAttributeId;
	}
	@Column(name = "BUDGET_TYPE", nullable = true, length = 200)
	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}
	@Column(name = "BUDGET_TYPE_CODE", nullable = true, length = 50)
	public String getBudgetTypeCode() {
		return budgetTypeCode;
	}

	public void setBudgetTypeCode(String budgetTypeCode) {
		this.budgetTypeCode = budgetTypeCode;
	}
	@Column(name = "STAT_TYPE", nullable = true, length = 50)
	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}
					
	@Column(name = "CONTRACT_ATTRIBUTE", nullable = true, length = 200)
	public String getContractAttribute() {
		return contractAttribute;
	}

	public void setContractAttribute(String contractAttribute) {
		this.contractAttribute = contractAttribute;
	}

	@Column(name = "CONTRACT_DESTORY_DATE", nullable = true, length = 40)
	public String getContractDestoryDate() {
		return contractDestoryDate;
	}

	public void setContractDestoryDate(String contractDestoryDate) {
		this.contractDestoryDate = contractDestoryDate;
	}

	@Column(name = "CREATE_TYPE", nullable = true, length = 200)
	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}

	@Column(name = "SELF_NO", nullable = true, length = 200)	
	public String getSelfNo() {
		return selfNo;
	}

	public void setSelfNo(String selfNo) {
		this.selfNo = selfNo;
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

	@Column(name = "CONTRACT_NAME", nullable = true, length = 140)
	public String getContractName() {
		return contractName;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_NO", nullable = true, length = 100)
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

	@Column(name = "CONTRACT_OWNER_NAME", nullable = true, length = 1000)
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

	public void setContractPlanId(String contractPlanId) {
		this.contractPlanId = contractPlanId;
	}

	@Column(name = "CONTRACT_PLAN_ID", nullable = true, length = 40)
	public String getContractPlanId() {
		return contractPlanId;
	}

	public void setContractPlanNo(String contractPlanNo) {
		this.contractPlanNo = contractPlanNo;
	}

	@Column(name = "CONTRACT_PLAN_NO", nullable = true, length = 40)
	public String getContractPlanNo() {
		return contractPlanNo;
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

	@Column(name = "CONTRACT_TYPE", nullable = true, length = 40)
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

	@Column(name = "PAY_TYPE", nullable = true, length = 400)
	public String getPayType() {
		return payType;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_ID", nullable = true, length = 100)
	public String getProjectId() {
		return projectId;
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

	@Column(name = "CONTRACT_START_DATE", nullable = true, length = 40)
	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	@Column(name = "CONTRACT_OWNER_EXECUTE_ID", nullable = true, length = 40)
	public String getContractOwnerExecuteId() {
		return contractOwnerExecuteId;
	}

	public void setContractOwnerExecuteId(String contractOwnerExecuteId) {
		this.contractOwnerExecuteId = contractOwnerExecuteId;
	}

	@Column(name = "CONTRACT_OWNER_EXECUTE_NAME", nullable = true, length = 40)
	public String getContractOwnerExecuteName() {
		return contractOwnerExecuteName;
	}

	public void setContractOwnerExecuteName(String contractOwnerExecuteName) {
		this.contractOwnerExecuteName = contractOwnerExecuteName;
	}

	@Column(name = "VERIFY_PRICE", nullable = true, length = 100)
	public String getVerifyPrice() {
		return verifyPrice;
	}

	public void setVerifyPrice(String verifyPrice) {
		this.verifyPrice = verifyPrice;
	}

	@Column(name = "FINAL_PRICE", nullable = true, length = 100)
	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Column(name = "INVITE_BID_TYPE", nullable = true, length = 20)
	public String getInviteBidType() {
		return inviteBidType;
	}

	public void setInviteBidType(String inviteBidType) {
		this.inviteBidType = inviteBidType;
	}

	@Column(name = "NATION_VERIFY_PRICE", nullable = true, length = 100)
	public String getNationVerifyPrice() {
		return nationVerifyPrice;
	}

	public void setNationVerifyPrice(String nationVerifyPrice) {
		this.nationVerifyPrice = nationVerifyPrice;
	}

	@Column(name = "REGISTER_PERSON_NAME", nullable = true, length = 200)
	public String getRegisterPersonName() {
		return registerPersonName;
	}

	public void setRegisterPersonName(String registerPersonName) {
		this.registerPersonName = registerPersonName;
	}

	@Column(name = "REGISTER_PERSON_LOGINNAME", nullable = true, length = 100)
	public String getRegisterPersonLoginname() {
		return registerPersonLoginname;
	}

	public void setRegisterPersonLoginname(String registerPersonLoginname) {
		this.registerPersonLoginname = registerPersonLoginname;
	}

	@Column(name = "CONTRACT_GROUPING", nullable = true, length = 1)
	public String getContractGrouping() {
		return contractGrouping;
	}

	public void setContractGrouping(String contractGrouping) {
		this.contractGrouping = contractGrouping;
	}

	@Column(name = "EVALUATE_NUM", nullable = true)
	public Long getEvaluateNum() {
		return evaluateNum;
	}

	public void setEvaluateNum(Long evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	public List<KpiClearStatus> getKpiClearStatus() {
		return kpiClearStatus;
	}

	public void setKpiClearStatus(List<KpiClearStatus> kpiClearStatus) {
		this.kpiClearStatus = kpiClearStatus;
	}

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	public List<ContractChangeProtocol> getContractChangeProtocols() {
		return contractChangeProtocols;
	}

	public void setContractChangeProtocols(
			List<ContractChangeProtocol> contractChangeProtocols) {
		this.contractChangeProtocols = contractChangeProtocols;
	}

}