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

package com.wonders.ic.dwContract.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * DwContractCoverʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-2-28
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_CONTRACT_COVER")
public class DwContractCover implements Serializable, BusinessObject {

	private String id; // id

	private String companyName; // companyName

	private String contractType; // contractType

	private Date createDate; // createDate
	
	private String companyType;
	
	private String companyId ;
	
	private BigDecimal numbers;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "COMPANY_NAME", nullable = true, length = 200)
	public String getCompanyName() {
		return companyName;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "CONTRACT_TYPE", nullable = true, length = 20)
	public String getContractType() {
		return contractType;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 11)
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name = "COMPANY_TYPE", nullable = true, length = 20)
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name = "COMPANY_ID", nullable = true, length = 40)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "NUMBERS", nullable = true, length = 22)
	public BigDecimal getNumbers() {
		return numbers;
	}

	public void setNumbers(BigDecimal numbers) {
		this.numbers = numbers;
	}
	
	

}
