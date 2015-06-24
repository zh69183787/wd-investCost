package com.wonders.ic.contract.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CKpiClearStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "C_KPI_CLEAR_STATUS")
public class KpiClearStatus implements java.io.Serializable {

	// Fields

	private String kpiClearStatusId;
	private String contractId;
	private String kpiStatus;
	private String kpiType;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public KpiClearStatus() {
	}

	/** full constructor */
	public KpiClearStatus(String contractId, String kpiStatus, String kpiType,
			String creator, Date createTime, String updater, Date updateTime) {
		this.contractId = contractId;
		this.kpiStatus = kpiStatus;
		this.kpiType = kpiType;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "C_KPI_CLEAR_STATUS_ID", unique = true, nullable = false, length = 32)
	public String getKpiClearStatusId() {
		return this.kpiClearStatusId;
	}

	public void setKpiClearStatusId(String kpiClearStatusId) {
		this.kpiClearStatusId = kpiClearStatusId;
	}

	@Column(name = "CONTRACT_ID")
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "KPI_STATUS", length = 1)
	public String getKpiStatus() {
		return this.kpiStatus;
	}

	public void setKpiStatus(String kpiStatus) {
		this.kpiStatus = kpiStatus;
	}

	@Column(name = "KPI_TYPE", length = 2)
	public String getKpiType() {
		return this.kpiType;
	}

	public void setKpiType(String kpiType) {
		this.kpiType = kpiType;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATER", length = 100)
	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}