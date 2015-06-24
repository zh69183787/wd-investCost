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
 * CUserGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "C_USER_GROUP")
public class UserGroup implements java.io.Serializable {

	// Fields

	private String userGroupId;
	private String userId;
	private String contractOwnerExecuteName;
	private String contractGrouping;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public UserGroup() {
	}

	/** full constructor */
	public UserGroup(String userId, String contractOwnerExecuteName,
			String contractGrouping, String creator, Date createTime,
			String updater, Date updateTime) {
		this.userId = userId;
		this.contractOwnerExecuteName = contractOwnerExecuteName;
		this.contractGrouping = contractGrouping;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "USER_GROUP_ID", unique = true, nullable = false, length = 32)
	public String getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	@Column(name = "USER_ID", length = 100)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CONTRACT_OWNER_EXECUTE_NAME", length = 200)
	public String getContractOwnerExecuteName() {
		return this.contractOwnerExecuteName;
	}

	public void setContractOwnerExecuteName(String contractOwnerExecuteName) {
		this.contractOwnerExecuteName = contractOwnerExecuteName;
	}

	@Column(name = "CONTRACT_GROUPING", length = 1)
	public String getContractGrouping() {
		return this.contractGrouping;
	}

	public void setContractGrouping(String contractGrouping) {
		this.contractGrouping = contractGrouping;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
