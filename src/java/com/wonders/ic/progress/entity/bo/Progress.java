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

package com.wonders.ic.progress.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Progressʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_PROGRESS")
public class Progress implements Serializable, BusinessObject {

	private String createDate; // createDate

	private String describe; // describe

	private String id; // id

	private String objectType; // objectType

	private String progressType; // progressType

	private String remark; // remark

	private String removed; // removed

	private String reportDeptId; // reportDeptId

	private String reportDeptName; // reportDeptName

	private String reportPersonId; // reportPersonId

	private String reportPersonName; // reportPersonName

	private String time; // time

	private String updateDate; // updateDate
	
	private String objectId;

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 100)
	public String getCreateDate() {
		return createDate;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "DESCRIBE", nullable = true, length = 1000)
	public String getDescribe() {
		return describe;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID", nullable = false, length = 100)
	public String getId() {
		return id;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Column(name = "OBJECT_TYPE", nullable = true, length = 2)
	public String getObjectType() {
		return objectType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}

	@Column(name = "PROGRESS_TYPE", nullable = true, length = 1000)
	public String getProgressType() {
		return progressType;
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

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setReportDeptId(String reportDeptId) {
		this.reportDeptId = reportDeptId;
	}

	@Column(name = "REPORT_DEPT_ID", nullable = true, length = 100)
	public String getReportDeptId() {
		return reportDeptId;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	@Column(name = "REPORT_DEPT_NAME", nullable = true, length = 100)
	public String getReportDeptName() {
		return reportDeptName;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	@Column(name = "REPORT_PERSON_ID", nullable = true, length = 100)
	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	@Column(name = "REPORT_PERSON_NAME", nullable = true, length = 100)
	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "TIME", nullable = true, length = 100)
	public String getTime() {
		return time;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 100)
	public String getUpdateDate() {
		return updateDate;
	}

	@Column(name = "OBJECT_ID", nullable = true, length = 100)
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}


}
