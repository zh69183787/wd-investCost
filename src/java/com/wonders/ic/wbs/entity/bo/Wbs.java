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

package com.wonders.ic.wbs.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Wbsʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "C_WBS")
public class Wbs implements Serializable, BusinessObject {

	private String id; // id

	private String createTime; // createTime

	private String nodeId; // nodeId

	private String parentId; // parentId

	private String remark; // remark

	private String removed; // removed

	private String updateTime; // updateTime

	private String wbsId; // wbsId

	private Long wbsLevel; // wbsLevel

	private String wbsObject; // wbsObject

	private String wbsObjectId; // wbsObjectId

	private Long wbsOrder; // wbsOrder
	
	private String type;
	
	private Long specialOrder;

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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME", nullable = true, length = 40)
	public String getCreateTime() {
		return createTime;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "NODE_ID", nullable = true, length = 40)
	public String getNodeId() {
		return nodeId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARENT_ID", nullable = true, length = 40)
	public String getParentId() {
		return parentId;
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

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME", nullable = true, length = 40)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setWbsId(String wbsId) {
		this.wbsId = wbsId;
	}

	@Column(name = "WBS_ID", nullable = true, length = 40)
	public String getWbsId() {
		return wbsId;
	}

	public void setWbsLevel(Long wbsLevel) {
		this.wbsLevel = wbsLevel;
	}

	@Column(name = "WBS_LEVEL", nullable = true, length = 22)
	public Long getWbsLevel() {
		return wbsLevel;
	}

	public void setWbsObject(String wbsObject) {
		this.wbsObject = wbsObject;
	}

	@Column(name = "WBS_OBJECT", nullable = true, length = 5)
	public String getWbsObject() {
		return wbsObject;
	}

	public void setWbsObjectId(String wbsObjectId) {
		this.wbsObjectId = wbsObjectId;
	}

	@Column(name = "WBS_OBJECT_ID", nullable = true, length = 40)
	public String getWbsObjectId() {
		return wbsObjectId;
	}

	public void setWbsOrder(Long wbsOrder) {
		this.wbsOrder = wbsOrder;
	}

	@Column(name = "WBS_ORDER", nullable = true, length = 22)
	public Long getWbsOrder() {
		return wbsOrder;
	}
	
	public void setSpecialOrder(Long specialOrder) {
		this.specialOrder = specialOrder;
	}

	@Column(name = "SPECIAL_ORDER", nullable = true, length = 22)
	public Long getSpecialOrder() {
		return specialOrder;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE", nullable = true, length = 1)
	public String getType() {
		return type;
	}

}
