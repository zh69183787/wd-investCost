package com.wonders.ic.contract.entity.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * CKpiClear entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "C_KPI_CLEAR")
public class KpiClear implements java.io.Serializable {

	// Fields

	private String kpiClearId;
	private String contractAttachId;
	private Contract contractId;
	private String opinion;
	private String removed;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;
	private String type;	//判断不同人员的页面显示
	private String ownerType;		//判断该条数据的创建人类型

	// Constructors

	/** default constructor */
	public KpiClear() {
	}

	/** full constructor */
	public KpiClear(String contractAttach, Contract contractId, String opinion,
			String removed, String creator, Date createTime, String updater,
			Date updateTime) {
		this.contractAttachId = contractAttach;
		this.contractId = contractId;
		this.opinion = opinion;
		this.removed = removed;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "KPI_CLEAR_ID", unique = true, nullable = false, length = 32)
	public String getKpiClearId() {
		return this.kpiClearId;
	}

	public void setKpiClearId(String kpiClearId) {
		this.kpiClearId = kpiClearId;
	}

	@Column(name = "CONTRACT_ATTACH_ID", length = 200)
	public String getContractAttachId() {
		return this.contractAttachId;
	}

	public void setContractAttachId(String contractAttach) {
		this.contractAttachId = contractAttach;
	}

	@ManyToOne(targetEntity=Contract.class)
	@JoinColumn(name = "CONTRACT_ID")
	public Contract getContractId() {
		return this.contractId;
	}

	public void setContractId(Contract contract) {
		this.contractId = contract;
	}

	@Column(name = "OPINION", length = 300)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "TYPE", length = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "OWNER_TYPE", length = 1)
	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

}