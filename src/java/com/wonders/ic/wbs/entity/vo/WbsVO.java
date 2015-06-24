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

package com.wonders.ic.wbs.entity.vo;

import java.util.Date;

import javax.persistence.Column;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * Wbsʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

public class WbsVO implements ValueObject {

	private String id;
	private String createTime;
	private String nodeId;
	private String parentId;
	private String remark;
	private String removed;
	private String updateTime;
	private String wbsId;
	private Long wbsLevel;
	private String wbsObject;
	private String wbsObjectId;
	private Long wbsOrder;
	private String type;
	private Long specialOrder;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
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

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setWbsId(String wbsId) {
		this.wbsId = wbsId;
	}

	public String getWbsId() {
		return wbsId;
	}

	public void setWbsLevel(Long wbsLevel) {
		this.wbsLevel = wbsLevel;
	}

	public Long getWbsLevel() {
		return wbsLevel;
	}

	public void setWbsObject(String wbsObject) {
		this.wbsObject = wbsObject;
	}

	public String getWbsObject() {
		return wbsObject;
	}

	public void setWbsObjectId(String wbsObjectId) {
		this.wbsObjectId = wbsObjectId;
	}

	public String getWbsObjectId() {
		return wbsObjectId;
	}

	public void setWbsOrder(Long wbsOrder) {
		this.wbsOrder = wbsOrder;
	}

	public Long getWbsOrder() {
		return wbsOrder;
	}
	
	public void setSpecialOrder(Long specialOrder) {
		this.specialOrder = specialOrder;
	}

	public Long getSpecialOrder() {
		return specialOrder;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
