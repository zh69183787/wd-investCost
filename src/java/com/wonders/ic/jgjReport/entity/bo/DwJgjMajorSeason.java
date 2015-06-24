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

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "DW_JGJ_MAJOR_SEASON")
public class DwJgjMajorSeason implements Serializable, BusinessObject {

	private String id; // id

	private BigDecimal allActualCount; // allActualCount

	private BigDecimal allActualGov; // allActualGov

	private BigDecimal allPlanCount; // allPlanCount

	private BigDecimal allPlanGov; // allPlanGov

	private Date createTime; // createTime

	private String lineName; // lineName

	private String order_; // order

	private BigDecimal overhaulActualCount; // overhaulActualCount

	private BigDecimal overhaulActualGov; // overhaulActualGov

	private BigDecimal overhaulPlanCount; // overhaulPlanCount

	private BigDecimal overhaulPlanGov; // overhaulPlanGov

	private BigDecimal updateActualCount; // updateActualCount

	private BigDecimal updateActualGov; // updateActualGov

	private BigDecimal updatePlanCount; // updatePlanCount

	private BigDecimal updatePlanGov; // updatePlanGov

	private String year; // year
	private Long season;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAllActualCount(BigDecimal allActualCount) {
		if(allActualCount != null) this.allActualCount = allActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.allActualCount = allActualCount;
	}

	@Column(name = "ALL_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getAllActualCount() {
		return allActualCount;
	}

	public void setAllActualGov(BigDecimal allActualGov) {
		if(allActualGov != null) this.allActualGov = allActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.allActualGov = allActualGov;
	}

	@Column(name = "ALL_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getAllActualGov() {
		return allActualGov;
	}

	public void setAllPlanCount(BigDecimal allPlanCount) {
		if(allPlanCount != null) this.allPlanCount = allPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.allPlanCount = allPlanCount;
	}

	@Column(name = "ALL_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getAllPlanCount() {
		return allPlanCount;
	}

	public void setAllPlanGov(BigDecimal allPlanGov) {
		if(allPlanGov != null) this.allPlanGov = allPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.allPlanGov = allPlanGov;
	}

	@Column(name = "ALL_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getAllPlanGov() {
		return allPlanGov;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "LINE_NAME", nullable = true, length = 100)
	public String getLineName() {
		return lineName;
	}

	public void setOverhaulActualCount(BigDecimal overhaulActualCount) {
		if(overhaulActualCount != null) this.overhaulActualCount = overhaulActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.overhaulActualCount = overhaulActualCount;
	}

	@Column(name = "OVERHAUL_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getOverhaulActualCount() {
		return overhaulActualCount;
	}

	public void setOverhaulActualGov(BigDecimal overhaulActualGov) {
		if(overhaulActualGov != null) this.overhaulActualGov = overhaulActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.overhaulActualGov = overhaulActualGov;
	}

	@Column(name = "OVERHAUL_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getOverhaulActualGov() {
		return overhaulActualGov;
	}

	public void setOverhaulPlanCount(BigDecimal overhaulPlanCount) {
		if(overhaulPlanCount != null) this.overhaulPlanCount = overhaulPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.overhaulPlanCount = overhaulPlanCount;
	}

	@Column(name = "OVERHAUL_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getOverhaulPlanCount() {
		return overhaulPlanCount;
	}

	public void setOverhaulPlanGov(BigDecimal overhaulPlanGov) {
		if(overhaulPlanGov != null) this.overhaulPlanGov = overhaulPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.overhaulPlanGov = overhaulPlanGov;
	}

	@Column(name = "OVERHAUL_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getOverhaulPlanGov() {
		return overhaulPlanGov;
	}

	public void setUpdateActualCount(BigDecimal updateActualCount) {
		if(updateActualCount != null) this.updateActualCount = updateActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.updateActualCount = updateActualCount;
	}

	@Column(name = "UPDATE_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getUpdateActualCount() {
		return updateActualCount;
	}

	public void setUpdateActualGov(BigDecimal updateActualGov) {
		if(updateActualGov != null) this.updateActualGov = updateActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.updateActualGov = updateActualGov;
	}

	@Column(name = "UPDATE_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getUpdateActualGov() {
		return updateActualGov;
	}

	public void setUpdatePlanCount(BigDecimal updatePlanCount) {
		if(updatePlanCount != null) this.updatePlanCount = updatePlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.updatePlanCount = updatePlanCount;
	}

	@Column(name = "UPDATE_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getUpdatePlanCount() {
		return updatePlanCount;
	}

	public void setUpdatePlanGov(BigDecimal updatePlanGov) {
		if(updatePlanGov != null) this.updatePlanGov = updatePlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.updatePlanGov = updatePlanGov;
	}

	@Column(name = "UPDATE_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getUpdatePlanGov() {
		return updatePlanGov;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}

	@Column(name = "SEASON", nullable = true, length = 1)
	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	@Column(name = "ORDER_", length = 10)
	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order) {
		order_ = order;
	}

}
