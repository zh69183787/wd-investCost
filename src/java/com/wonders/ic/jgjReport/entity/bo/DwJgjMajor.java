package com.wonders.ic.jgjReport.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


@Entity
@Table(name = "DW_JGJ_MAJOR")
@SuppressWarnings("serial")
public class DwJgjMajor implements Serializable, BusinessObject {

	private BigDecimal allActualCount; // allActualCount

	private BigDecimal allActualGov; // allActualGov

	private BigDecimal allPlanCount; // allPlanCount

	private BigDecimal allPlanGov; // allPlanGov

	private BigDecimal carActualCount; // carActualCount

	private BigDecimal carActualGov; // carActualGov

	private BigDecimal carPlanCount; // carPlanCount

	private BigDecimal carPlanGov; // carPlanGov

	private Date createTime; // createTime

	private BigDecimal eleActualCount; // eleActualCount

	private BigDecimal eleActualGov; // eleActualGov

	private BigDecimal elePlanCount; // elePlanCount

	private BigDecimal elePlanGov; // elePlanGov

	private String id; // id

	private BigDecimal numActualCount; // numActualCount

	private BigDecimal numActualGov; // numActualGov

	private BigDecimal numPlanCount; // numPlanCount

	private BigDecimal numPlanGov; // numPlanGov

	private BigDecimal otherActualCount; // otherActualCount

	private BigDecimal otherActualGov; // otherActualGov

	private BigDecimal otherPlanCount; // otherPlanCount

	private BigDecimal otherPlanGov; // otherPlanGov

	private BigDecimal stationActualCount; // stationActualCount

	private BigDecimal stationActualGov; // stationActualGov

	private BigDecimal stationPlanCount; // stationPlanCount

	private BigDecimal stationPlanGov; // stationPlanGov

	private String typename; // type

	private BigDecimal workActualCount; // workActualCount

	private BigDecimal workActualGov; // workActualGov

	private BigDecimal workPlanCount; // workPlanCount

	private BigDecimal workPlanGov; // workPlanGov

	private String year; // year
	
	private String lineName;
	
	private BigDecimal basePlanCount;
	private BigDecimal basePlanGov;
	private BigDecimal baseActualCount;
	private BigDecimal baseActualGov;
	
	private String order_;
	

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

	public void setCarActualCount(BigDecimal carActualCount) {
		if(carActualCount != null) this.carActualCount = carActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.carActualCount = carActualCount;
	}

	@Column(name = "CAR_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getCarActualCount() {
		return carActualCount;
	}

	public void setCarActualGov(BigDecimal carActualGov) {
		if(carActualGov != null) this.carActualGov = carActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.carActualGov = carActualGov;
	}

	@Column(name = "CAR_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getCarActualGov() {
		return carActualGov;
	}

	public void setCarPlanCount(BigDecimal carPlanCount) {
		if(carPlanCount != null) this.carPlanCount = carPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.carPlanCount = carPlanCount;
	}

	@Column(name = "CAR_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getCarPlanCount() {
		return carPlanCount;
	}

	public void setCarPlanGov(BigDecimal carPlanGov) {
		if(carPlanGov != null) this.carPlanGov = carPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.carPlanGov = carPlanGov;
	}

	@Column(name = "CAR_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getCarPlanGov() {
		return carPlanGov;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setEleActualCount(BigDecimal eleActualCount) {
		if(eleActualCount != null) this.eleActualCount = eleActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.eleActualCount = eleActualCount;
	}

	@Column(name = "ELE_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getEleActualCount() {
		return eleActualCount;
	}

	public void setEleActualGov(BigDecimal eleActualGov) {
		if(eleActualGov != null) this.eleActualGov = eleActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.eleActualGov = eleActualGov;
	}

	@Column(name = "ELE_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getEleActualGov() {
		return eleActualGov;
	}

	public void setElePlanCount(BigDecimal elePlanCount) {
		if(elePlanCount != null) this.elePlanCount = elePlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.elePlanCount = elePlanCount;
	}

	@Column(name = "ELE_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getElePlanCount() {
		return elePlanCount;
	}

	public void setElePlanGov(BigDecimal elePlanGov) {
		if(elePlanGov != null) this.elePlanGov = elePlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.elePlanGov = elePlanGov;
	}

	@Column(name = "ELE_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getElePlanGov() {
		return elePlanGov;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="sys.uuid")
	@GenericGenerator(name="sys.uuid",strategy="uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}

	public void setNumActualCount(BigDecimal numActualCount) {
		if(numActualCount != null) this.numActualCount = numActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.numActualCount = numActualCount;
	}

	@Column(name = "NUM_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getNumActualCount() {
		return numActualCount;
	}

	public void setNumActualGov(BigDecimal numActualGov) {
		if(numActualGov != null) this.numActualGov = numActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.numActualGov = numActualGov;
	}

	@Column(name = "NUM_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getNumActualGov() {
		return numActualGov;
	}

	public void setNumPlanCount(BigDecimal numPlanCount) {
		if(numPlanCount != null) this.numPlanCount = numPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.numPlanCount = numPlanCount;
	}

	@Column(name = "NUM_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getNumPlanCount() {
		return numPlanCount;
	}

	public void setNumPlanGov(BigDecimal numPlanGov) {
		if(numPlanGov != null) this.numPlanGov = numPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.numPlanGov = numPlanGov;
	}

	@Column(name = "NUM_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getNumPlanGov() {
		return numPlanGov;
	}

	public void setOtherActualCount(BigDecimal otherActualCount) {
		if(otherActualCount != null) this.otherActualCount = otherActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.otherActualCount = otherActualCount;
	}

	@Column(name = "OTHER_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getOtherActualCount() {
		return otherActualCount;
	}

	public void setOtherActualGov(BigDecimal otherActualGov) {
		if(otherActualGov != null) this.otherActualGov = otherActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.otherActualGov = otherActualGov;
	}

	@Column(name = "OTHER_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getOtherActualGov() {
		return otherActualGov;
	}

	public void setOtherPlanCount(BigDecimal otherPlanCount) {
		if(otherPlanCount != null) this.otherPlanCount = otherPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.otherPlanCount = otherPlanCount;
	}

	@Column(name = "OTHER_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getOtherPlanCount() {
		return otherPlanCount;
	}

	public void setOtherPlanGov(BigDecimal otherPlanGov) {
		if(otherPlanGov != null) this.otherPlanGov = otherPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.otherPlanGov = otherPlanGov;
	}

	@Column(name = "OTHER_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getOtherPlanGov() {
		return otherPlanGov;
	}

	public void setStationActualCount(BigDecimal stationActualCount) {
		if(stationActualCount != null) this.stationActualCount = stationActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.stationActualCount = stationActualCount;
	}

	@Column(name = "STATION_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getStationActualCount() {
		return stationActualCount;
	}

	public void setStationActualGov(BigDecimal stationActualGov) {
		if(stationActualGov != null) this.stationActualGov = stationActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.stationActualGov = stationActualGov;
	}

	@Column(name = "STATION_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getStationActualGov() {
		return stationActualGov;
	}

	public void setStationPlanCount(BigDecimal stationPlanCount) {
		if(stationPlanCount != null) this.stationPlanCount = stationPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.stationPlanCount = stationPlanCount;
	}

	@Column(name = "STATION_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getStationPlanCount() {
		return stationPlanCount;
	}

	public void setStationPlanGov(BigDecimal stationPlanGov) {
		if(stationPlanGov != null) this.stationPlanGov = stationPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.stationPlanGov = stationPlanGov;
	}

	@Column(name = "STATION_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getStationPlanGov() {
		return stationPlanGov;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "TYPENAME", nullable = true, length = 40)
	public String getTypename() {
		return typename;
	}

	public void setWorkActualCount(BigDecimal workActualCount) {
		this.workActualCount = workActualCount;
	}

	@Column(name = "WORK_ACTUAL_COUNT", nullable = true, length = 22)
	public BigDecimal getWorkActualCount() {
		return workActualCount;
	}

	public void setWorkActualGov(BigDecimal workActualGov) {
		if(workActualGov != null) this.workActualGov = workActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.workActualGov = workActualGov;
	}

	@Column(name = "WORK_ACTUAL_GOV", nullable = true, length = 22)
	public BigDecimal getWorkActualGov() {
		return workActualGov;
	}

	public void setWorkPlanCount(BigDecimal workPlanCount) {
		if(workPlanCount != null) this.workPlanCount = workPlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.workPlanCount = workPlanCount;
	}

	@Column(name = "WORK_PLAN_COUNT", nullable = true, length = 22)
	public BigDecimal getWorkPlanCount() {
		return workPlanCount;
	}

	public void setWorkPlanGov(BigDecimal workPlanGov) {
		if(workPlanGov != null) this.workPlanGov = workPlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.workPlanGov = workPlanGov;
	}

	@Column(name = "WORK_PLAN_GOV", nullable = true, length = 22)
	public BigDecimal getWorkPlanGov() {
		return workPlanGov;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}

	@Column(name = "LINE_NAME", nullable = true, length = 40)
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "BASE_PLAN_COUNT", nullable = true, length = 40)
	public BigDecimal getBasePlanCount() {
		return basePlanCount;
	}

	public void setBasePlanCount(BigDecimal basePlanCount) {
		if(basePlanCount != null) this.basePlanCount = basePlanCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.basePlanCount = basePlanCount;
	}

	@Column(name = "BASE_PLAN_GOV", nullable = true, length = 40)
	public BigDecimal getBasePlanGov() {
		return basePlanGov;
	}

	public void setBasePlanGov(BigDecimal basePlanGov) {
		if(basePlanGov != null) this.basePlanGov = basePlanGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.basePlanGov = basePlanGov;
	}

	@Column(name = "BASE_ACTUAL_COUNT", nullable = true, length = 40)
	public BigDecimal getBaseActualCount() {
		return baseActualCount;
	}

	public void setBaseActualCount(BigDecimal baseActualCount) {
		if(baseActualCount != null) this.baseActualCount = baseActualCount.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.baseActualCount = baseActualCount;
	}

	@Column(name = "BASE_ACTUAL_GOV", nullable = true, length = 40)
	public BigDecimal getBaseActualGov() {
		return baseActualGov;
	}

	public void setBaseActualGov(BigDecimal baseActualGov) {
		if(baseActualGov != null) this.baseActualGov = baseActualGov.setScale(6, BigDecimal.ROUND_HALF_UP);
		else this.baseActualGov = baseActualGov;
	}

	@Column(name = "ORDER_", length = 10)
	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order) {
		order_ = order;
	}
	
}
