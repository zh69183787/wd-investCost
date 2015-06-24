
package com.wonders.ic.jgjReport.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

@Entity
@Table(name = "DW_JGJ_PROJECT_PROGRESS")
@SuppressWarnings("serial")
public class DwJgjProjectProgress implements Serializable, Cloneable, BusinessObject {

	private String id; // id

	private BigDecimal approvalBugget; // approvalBugget

	private BigDecimal contractPrice; // contractPrice

	private Date createTime; // createTime

	private String dispatchNo; // dispatchNo

	private String feeDepartment; // feeDepartment
	
	private String feeMoney;

	private BigDecimal finalPrice; // finalPrice

	private String maintainType; // maintainType

	private String mainExecute; // mainExecute

	private String professionalType; // professionalType

	private String projectName; // projectName

	private BigDecimal thisYearPlanCount; // thisYearPlanCount

	private BigDecimal thisYearPlanGov; // thisYearPlanGov

	private String year; // year
	
	private String projectProgress; // projectProgress
	
	private BigDecimal thisYearPayCount; // thisYearPayCount
	
	private BigDecimal thisYearPayGov; // thisYearPayGov
	
	private Long season; 
	
	private String projectId;
	private String contractId;
	private BigDecimal nationVerifyPrice;
	private BigDecimal verifyPrice;
	
	public Object clone() {
		Object o = null;
		try {
			o = (DwJgjProjectProgress) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setApprovalBugget(BigDecimal approvalBugget) {
		this.approvalBugget = approvalBugget;
		if(this.approvalBugget!=null){
			this.approvalBugget = this.approvalBugget.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "APPROVAL_BUGGET", nullable = true, length = 22)
	public BigDecimal getApprovalBugget() {
		return approvalBugget;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
		if(this.contractPrice!=null){
			this.contractPrice = this.contractPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 22)
	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	@Column(name = "DISPATCH_NO", nullable = true, length = 400)
	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setFeeDepartment(String feeDepartment) {
		this.feeDepartment = feeDepartment;
	}

	@Column(name = "FEE_DEPARTMENT", nullable = true, length = 2000)
	public String getFeeDepartment() {
		return feeDepartment;
	}
	
	@Column(name = "FEE_MONEY")
	public String getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(String feeMoney) {
		this.feeMoney = feeMoney;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
		if(this.finalPrice!=null){
			this.finalPrice = this.finalPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "FINAL_PRICE", nullable = true, length = 22)
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setMaintainType(String maintainType) {
		this.maintainType = maintainType;
	}

	@Column(name = "MAINTAIN_TYPE", nullable = true, length = 2)
	public String getMaintainType() {
		return maintainType;
	}

	public void setMainExecute(String mainExecute) {
		this.mainExecute = mainExecute;
	}

	@Column(name = "MAIN_EXECUTE", nullable = true, length = 400)
	public String getMainExecute() {
		return mainExecute;
	}

	public void setProfessionalType(String professionalType) {
		this.professionalType = professionalType;
	}

	@Column(name = "PROFESSIONAL_TYPE", nullable = true, length = 2)
	public String getProfessionalType() {
		return professionalType;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_NAME", nullable = true, length = 400)
	public String getProjectName() {
		return projectName;
	}

	public void setThisYearPlanCount(BigDecimal thisYearPlanCount) {
		this.thisYearPlanCount = thisYearPlanCount;
		if(this.thisYearPlanCount!=null){
			this.thisYearPlanCount = this.thisYearPlanCount.setScale(6,BigDecimal.ROUND_HALF_UP);
		}
		
	}

	@Column(name = "THIS_YEAR_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getThisYearPlanCount() {
		return thisYearPlanCount;
	}

	public void setThisYearPlanGov(BigDecimal thisYearPlanGov) {
		this.thisYearPlanGov = thisYearPlanGov;
		if(this.thisYearPlanGov!=null){
			this.thisYearPlanGov = this.thisYearPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "THIS_YEAR_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getThisYearPlanGov() {
		return thisYearPlanGov;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}
	
	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	@Column(name = "PROJECT_PROGRESS", nullable = true, length = 400)
	public String getProjectProgress() {
		return projectProgress;
	}

	public void setThisYearPayCount(BigDecimal thisYearPayCount) {
		this.thisYearPayCount = thisYearPayCount;
		if(this.thisYearPayCount!=null){
			this.thisYearPayCount = this.thisYearPayCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "THIS_YEAR_PAY_COUNT", nullable = true, length = 22)
	public BigDecimal getThisYearPayCount() {
		return thisYearPayCount;
	}
	
	public void setThisYearPayGov(BigDecimal thisYearPayGov) {
		this.thisYearPayGov = thisYearPayGov;
		if(this.thisYearPayGov!=null){
			this.thisYearPayGov = this.thisYearPayGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "THIS_YEAR_PAY_GOV", nullable = true, length = 22)
	public BigDecimal getThisYearPayGov() {
		return thisYearPayGov;
	}

	@Column(name = "SEASON", nullable = true, length = 22)
	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	@Transient
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Transient
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Transient
	public BigDecimal getNationVerifyPrice() {
		return nationVerifyPrice;
	}

	public void setNationVerifyPrice(BigDecimal nationVerifyPrice) {
		this.nationVerifyPrice = nationVerifyPrice;
		if(this.nationVerifyPrice!=null){
			this.nationVerifyPrice = this.nationVerifyPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Transient
	public BigDecimal getVerifyPrice() {
		return verifyPrice;
	}

	public void setVerifyPrice(BigDecimal verifyPrice) {
		this.verifyPrice = verifyPrice;
		if(this.verifyPrice!=null){
			this.verifyPrice = this.verifyPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}
	
}
