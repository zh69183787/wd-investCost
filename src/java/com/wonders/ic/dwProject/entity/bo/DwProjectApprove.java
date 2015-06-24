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

package com.wonders.ic.dwProject.entity.bo;

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

/**
 * DwProjectApproveʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-3-11
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_PROJECT_APPROVE")
public class DwProjectApprove implements Serializable, BusinessObject {

	private String id; // id

	private BigDecimal actualPay; // actualPay

	private Date addDate; // addDate

	private BigDecimal april; // april

	private BigDecimal august; // august

	private String contractName; // contractName

	private BigDecimal contractPrice; // contractPrice

	private String contractStatus; // contractStatus

	private Date createDate; // createDate

	private BigDecimal december; // december

	private BigDecimal february; // february

	private BigDecimal finalPrice; // finalPrice

	private String groupNumber; // groupNumber

	private BigDecimal january; // january

	private BigDecimal july; // july

	private BigDecimal june; // june

	private BigDecimal lastyearTotalPay; // lastyearTotalPay

	private BigDecimal march; // march

	private BigDecimal may; // may

	private BigDecimal november; // november

	private BigDecimal october; // october

	private BigDecimal projectEstimatePrice; // projectEstimatePrice

	private String projectName; // projectName

	private String projectNo; // projectNo

	private String projectType; // projectType

	private BigDecimal september; // september

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	@Column(name = "ACTUAL_PAY", nullable = true, length = 22)
	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Column(name = "ADD_DATE", nullable = true, length = 11)
	public Date getAddDate() {
		return addDate;
	}

	public void setApril(BigDecimal april) {
		this.april = april;
	}

	@Column(name = "APRIL", nullable = true, length = 22)
	public BigDecimal getApril() {
		return april;
	}

	public void setAugust(BigDecimal august) {
		this.august = august;
	}

	@Column(name = "AUGUST", nullable = true, length = 22)
	public BigDecimal getAugust() {
		return august;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NAME", nullable = true, length = 200)
	public String getContractName() {
		return contractName;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PRICE", nullable = true, length = 22)
	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	@Column(name = "CONTRACT_STATUS", nullable = true, length = 2)
	public String getContractStatus() {
		return contractStatus;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 11)
	public Date getCreateDate() {
		return createDate;
	}

	public void setDecember(BigDecimal december) {
		this.december = december;
	}

	@Column(name = "DECEMBER", nullable = true, length = 22)
	public BigDecimal getDecember() {
		return december;
	}

	public void setFebruary(BigDecimal february) {
		this.february = february;
	}

	@Column(name = "FEBRUARY", nullable = true, length = 22)
	public BigDecimal getFebruary() {
		return february;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Column(name = "FINAL_PRICE", nullable = true, length = 22)
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	@Column(name = "GROUP_NUMBER", nullable = true, length = 200)
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setJanuary(BigDecimal january) {
		this.january = january;
	}

	@Column(name = "JANUARY", nullable = true, length = 22)
	public BigDecimal getJanuary() {
		return january;
	}

	public void setJuly(BigDecimal july) {
		this.july = july;
	}

	@Column(name = "JULY", nullable = true, length = 22)
	public BigDecimal getJuly() {
		return july;
	}

	public void setJune(BigDecimal june) {
		this.june = june;
	}

	@Column(name = "JUNE", nullable = true, length = 22)
	public BigDecimal getJune() {
		return june;
	}

	public void setLastyearTotalPay(BigDecimal lastyearTotalPay) {
		this.lastyearTotalPay = lastyearTotalPay;
	}

	@Column(name = "LASTYEAR_TOTAL_PAY", nullable = true, length = 22)
	public BigDecimal getLastyearTotalPay() {
		return lastyearTotalPay;
	}

	public void setMarch(BigDecimal march) {
		this.march = march;
	}

	@Column(name = "MARCH", nullable = true, length = 22)
	public BigDecimal getMarch() {
		return march;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	@Column(name = "MAY", nullable = true, length = 22)
	public BigDecimal getMay() {
		return may;
	}

	public void setNovember(BigDecimal november) {
		this.november = november;
	}

	@Column(name = "NOVEMBER", nullable = true, length = 22)
	public BigDecimal getNovember() {
		return november;
	}

	public void setOctober(BigDecimal october) {
		this.october = october;
	}

	@Column(name = "OCTOBER", nullable = true, length = 22)
	public BigDecimal getOctober() {
		return october;
	}

	public void setProjectEstimatePrice(BigDecimal projectEstimatePrice) {
		this.projectEstimatePrice = projectEstimatePrice;
	}

	@Column(name = "PROJECT_ESTIMATE_PRICE", nullable = true, length = 22)
	public BigDecimal getProjectEstimatePrice() {
		return projectEstimatePrice;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_NAME", nullable = true, length = 200)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 200)
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PROJECT_TYPE", nullable = true, length = 40)
	public String getProjectType() {
		return projectType;
	}

	public void setSeptember(BigDecimal september) {
		this.september = september;
	}

	@Column(name = "SEPTEMBER", nullable = true, length = 22)
	public BigDecimal getSeptember() {
		return september;
	}

}
