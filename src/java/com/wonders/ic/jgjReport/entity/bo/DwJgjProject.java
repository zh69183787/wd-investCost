package com.wonders.ic.jgjReport.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


@SuppressWarnings("serial")
@Entity
@Table(name = "DW_JGJ_PROJECT")
public class DwJgjProject implements Serializable, Cloneable, BusinessObject {

	private String id; // id

	private String appointMaintain; // appointMaintain

	private BigDecimal approvalBugget; // approvalBugget

	private String bidInvaiting; // bidInvaiting

	private String contractName; // contractName

	private BigDecimal contractPrice; // contractPrice

	private String dispatchNo; // dispatchNo

	private BigDecimal endYearPayCount; // endYearPayCount

	private BigDecimal endYearPayGov; // endYearPayGov

	private String feeDepartment; // feeDepartment
	
	private String feeMoney;

	private BigDecimal finalPrice; // finalPrice

	private BigDecimal lastYearCount; // lastYearCount

	private BigDecimal lastYearGov; // lastYearGov

	private String maintainType; // maintainType

	private String mainExecute; // mainExecute

	private String professionalType; // professionalType

	private String projectName; // projectName

	private String projectProgress; // projectProgress

	private BigDecimal thisYearPayCount; // thisYearPayCount

	private BigDecimal thisYearPayGov; // thisYearPayGov

	private BigDecimal thisYearPlanCount; // thisYearPlanCount

	private BigDecimal thisYearPlanGov; // thisYearPlanGov

	private String year; // year
	
	private Date createTime;
	
	private String contractId;
	private BigDecimal nationVerifyPrice;
	private BigDecimal verifyPrice;
	
	public Object clone() {
		Object o = null;
		try {
			o = (DwJgjProject) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
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

	public void setAppointMaintain(String appointMaintain) {
		this.appointMaintain = appointMaintain;
	}

	@Column(name = "APPOINT_MAINTAIN", nullable = true, length = 400)
	public String getAppointMaintain() {
		return appointMaintain;
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

	public void setBidInvaiting(String bidInvaiting) {
		this.bidInvaiting = bidInvaiting;
	}

	@Column(name = "BID_INVAITING", nullable = true, length = 400)
	public String getBidInvaiting() {
		return bidInvaiting;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 400)
	public String getContractName() {
		return contractName;
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

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	@Column(name = "DISPATCH_NO", nullable = true, length = 400)
	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setEndYearPayCount(BigDecimal endYearPayCount) {
		this.endYearPayCount = endYearPayCount;
		if(this.endYearPayCount!=null){
			this.endYearPayCount = this.endYearPayCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "END_YEAR_PAY_COUNT", nullable = true, length = 22)
	public BigDecimal getEndYearPayCount() {
		return endYearPayCount;
	}

	public void setEndYearPayGov(BigDecimal endYearPayGov) {
		this.endYearPayGov = endYearPayGov;
		if(this.endYearPayGov!=null){
			this.endYearPayGov = this.endYearPayGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "END_YEAR_PAY_GOV", nullable = true, length = 22)
	public BigDecimal getEndYearPayGov() {
		return endYearPayGov;
	}

	public void setFeeDepartment(String feeDepartment) {
		this.feeDepartment = feeDepartment;
	}

	@Column(name = "FEE_DEPARTMENT", nullable = true, length = 400)
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

	public void setLastYearCount(BigDecimal lastYearCount) {
		this.lastYearCount = lastYearCount;
		if(this.lastYearCount!=null){
			this.lastYearCount = this.lastYearCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "LAST_YEAR_COUNT", nullable = true, length = 22)
	public BigDecimal getLastYearCount() {
		return lastYearCount;
	}

	public void setLastYearGov(BigDecimal lastYearGov) {
		this.lastYearGov = lastYearGov;
		if(this.lastYearGov!=null){
			this.lastYearGov = this.lastYearGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Column(name = "LAST_YEAR_GOV", nullable = true, length = 22)
	public BigDecimal getLastYearGov() {
		return lastYearGov;
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

	public void setThisYearPlanCount(BigDecimal thisYearPlanCount) {
		this.thisYearPlanCount = thisYearPlanCount;
		if(this.thisYearPlanCount!=null){
			this.thisYearPlanCount = this.thisYearPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Transient
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

}
