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

package com.wonders.ic.progress.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * Progressʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-12-19
 * @author modify by $Author$
 * @since 1.0
 */

public class ProgressVO implements ValueObject {

	private String createDate;
	private String describe;
	private String id;
	private String objectType;
	private String progressType;
	private String remark;
	private String removed;
	private String reportDeptId;
	private String reportDeptName;
	private String reportPersonId;
	private String reportPersonName;
	private String time;
	private String updateDate;

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}

	public String getProgressType() {
		return progressType;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setReportDeptId(String reportDeptId) {
		this.reportDeptId = reportDeptId;
	}

	public String getReportDeptId() {
		return reportDeptId;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public String getReportDeptName() {
		return reportDeptName;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

}
