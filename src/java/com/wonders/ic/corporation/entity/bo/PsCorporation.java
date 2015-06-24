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

package com.wonders.ic.corporation.entity.bo;

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
 * PsCorporation实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-25
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "PS_CORPORATION")

        public class PsCorporation implements Serializable, BusinessObject {

	private Long compId; // compId

	private String annualTurnover; // annualTurnover

	private Long appStatus; // appStatus

	private String atUnit; // atUnit

	private String businessLicenseCode; // businessLicenseCode

	private String code; // code

	private String companyNameChn; // companyNameChn

	private String companyNameEn; // companyNameEn

	private Long companyNature; // companyNature

	private String companyType; // companyType

	private Long currentAuditId; // currentAuditId

	private String depositAccount; // depositAccount

	private String depositBank; // depositBank

	private String email; // email

	private String ext1; // ext1

	private String ext10; // ext10

	private String ext11; // ext11

	private String ext12; // ext12

	private String ext13; // ext13

	private String ext14; // ext14

	private String ext15; // ext15

	private String ext16; // ext16

	private String ext17; // ext17

	private String ext18; // ext18

	private String ext19; // ext19

	private String ext2; // ext2

	private String ext20; // ext20

	private String ext3; // ext3

	private String ext4; // ext4

	private String ext5; // ext5

	private String ext6; // ext6

	private String ext7; // ext7

	private String ext8; // ext8

	private String ext9; // ext9

	private String fax; // fax

	private String industry; // industry

	private String legalName; // legalName

	private Long logoPic; // logoPic

	private String nationalCode; // nationalCode

	private String officeAddress; // officeAddress

	private String operateDept; // operateDept

	private Date operateTime; // operateTime

	private String operator; // operator

	private String orgCode; // orgCode

	private String phone; // phone

	private String profile; // profile

	private String rcUnit; // rcUnit

	private Double registeredFund; // registeredFund

	private String registeredFundUnit; // registeredFundUnit

	private String registerAddress; // registerAddress

	private String registerCapital; // registerCapital

	private String remarks; // remarks

	private String removed; // removed

	private String role; // role

	private String shortName; // shortName

	private String staffPopulation; // staffPopulation

	private String taxCode; // taxCode

	private String website; // website

    private String supplierId;

    private String cityCode;

    private Date taxStartDate;

    private Date taxEndDate;

    private Date busiStartDate;

    private Date busiEndDate;

    private Date orgStartDate;

    private Date orgEndDate;

    private Date establishmentTime;

    private String motMess;


    private String lawerIdCard;

    private String lawerIdType;

    private String customField;

    private String provinceCode;

    private String linkmanName;
    private String linkmanTelphone;

    private String busiLinkmanTel;
    private String linkmanEmail;
    private String bankBranchName;
    private String taxNumAttach;
    private String busiNumAttach;
    private String orgNumAttach;




	private PsCorporation psCorporation;

	public void setCompId(Long compId) {
		this.compId = compId;
	}

    @Id
    @SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_CORPORATION")
    @GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
    @Column(name = "COMP_ID")
	public Long getCompId() {
		return compId;
	}

	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	@Column(name = "ANNUAL_TURNOVER", nullable = true, length = 50)
	public String getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAppStatus(Long appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "APP_STATUS", nullable = true, length = 20)
	public Long getAppStatus() {
		return appStatus;
	}

	public void setAtUnit(String atUnit) {
		this.atUnit = atUnit;
	}

	@Column(name = "AT_UNIT", nullable = true, length = 10)
	public String getAtUnit() {
		return atUnit;
	}

	public void setBusinessLicenseCode(String businessLicenseCode) {
		this.businessLicenseCode = businessLicenseCode;
	}

	@Column(name = "BUSINESS_LICENSE_CODE", nullable = true, length = 50)
	public String getBusinessLicenseCode() {
		return businessLicenseCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CODE", nullable = true, length = 100)
	public String getCode() {
		return code;
	}

	public void setCompanyNameChn(String companyNameChn) {
		this.companyNameChn = companyNameChn;
	}

	@Column(name = "COMPANY_NAME_CHN", nullable = true, length = 100)
	public String getCompanyNameChn() {
		return companyNameChn;
	}

	public void setCompanyNameEn(String companyNameEn) {
		this.companyNameEn = companyNameEn;
	}

	@Column(name = "COMPANY_NAME_EN", nullable = true, length = 100)
	public String getCompanyNameEn() {
		return companyNameEn;
	}

	public void setCompanyNature(Long companyNature) {
		this.companyNature = companyNature;
	}

	@Column(name = "COMPANY_NATURE", nullable = true, length = 20)
	public Long getCompanyNature() {
		return companyNature;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name = "COMPANY_TYPE", nullable = true, length = 100)
	public String getCompanyType() {
		return companyType;
	}

	public void setCurrentAuditId(Long currentAuditId) {
		this.currentAuditId = currentAuditId;
	}

	@Column(name = "CURRENT_AUDIT_ID", nullable = true, length = 20)
	public Long getCurrentAuditId() {
		return currentAuditId;
	}

	public void setDepositAccount(String depositAccount) {
		this.depositAccount = depositAccount;
	}

	@Column(name = "DEPOSIT_ACCOUNT", nullable = true, length = 50)
	public String getDepositAccount() {
		return depositAccount;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	@Column(name = "DEPOSIT_BANK", nullable = true, length = 100)
	public String getDepositBank() {
		return depositBank;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT_1", nullable = true, length = 200)
	public String getExt1() {
		return ext1;
	}

	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}

	@Column(name = "EXT_10", nullable = true, length = 200)
	public String getExt10() {
		return ext10;
	}

	public void setExt11(String ext11) {
		this.ext11 = ext11;
	}

	@Column(name = "EXT_11", nullable = true, length = 200)
	public String getExt11() {
		return ext11;
	}

	public void setExt12(String ext12) {
		this.ext12 = ext12;
	}

	@Column(name = "EXT_12", nullable = true, length = 200)
	public String getExt12() {
		return ext12;
	}

	public void setExt13(String ext13) {
		this.ext13 = ext13;
	}

	@Column(name = "EXT_13", nullable = true, length = 200)
	public String getExt13() {
		return ext13;
	}

	public void setExt14(String ext14) {
		this.ext14 = ext14;
	}

	@Column(name = "EXT_14", nullable = true, length = 200)
	public String getExt14() {
		return ext14;
	}

	public void setExt15(String ext15) {
		this.ext15 = ext15;
	}

	@Column(name = "EXT_15", nullable = true, length = 200)
	public String getExt15() {
		return ext15;
	}

	public void setExt16(String ext16) {
		this.ext16 = ext16;
	}

	@Column(name = "EXT_16", nullable = true, length = 200)
	public String getExt16() {
		return ext16;
	}

	public void setExt17(String ext17) {
		this.ext17 = ext17;
	}

	@Column(name = "EXT_17", nullable = true, length = 200)
	public String getExt17() {
		return ext17;
	}

	public void setExt18(String ext18) {
		this.ext18 = ext18;
	}

	@Column(name = "EXT_18", nullable = true, length = 200)
	public String getExt18() {
		return ext18;
	}

	public void setExt19(String ext19) {
		this.ext19 = ext19;
	}

	@Column(name = "EXT_19", nullable = true, length = 200)
	public String getExt19() {
		return ext19;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT_2", nullable = true, length = 200)
	public String getExt2() {
		return ext2;
	}

	public void setExt20(String ext20) {
		this.ext20 = ext20;
	}

	@Column(name = "EXT_20", nullable = true, length = 200)
	public String getExt20() {
		return ext20;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT_3", nullable = true, length = 200)
	public String getExt3() {
		return ext3;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	@Column(name = "EXT_4", nullable = true, length = 200)
	public String getExt4() {
		return ext4;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	@Column(name = "EXT_5", nullable = true, length = 200)
	public String getExt5() {
		return ext5;
	}

	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}

	@Column(name = "EXT_6", nullable = true, length = 200)
	public String getExt6() {
		return ext6;
	}

	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	@Column(name = "EXT_7", nullable = true, length = 200)
	public String getExt7() {
		return ext7;
	}

	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}

	@Column(name = "EXT_8", nullable = true, length = 200)
	public String getExt8() {
		return ext8;
	}

	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}

	@Column(name = "EXT_9", nullable = true, length = 200)
	public String getExt9() {
		return ext9;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "FAX", nullable = true, length = 50)
	public String getFax() {
		return fax;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "INDUSTRY", nullable = true, length = 200)
	public String getIndustry() {
		return industry;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	@Column(name = "LEGAL_NAME", nullable = true, length = 100)
	public String getLegalName() {
		return legalName;
	}

	public void setLogoPic(Long logoPic) {
		this.logoPic = logoPic;
	}

	@Column(name = "LOGO_PIC", nullable = true, length = 20)
	public Long getLogoPic() {
		return logoPic;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	@Column(name = "NATIONAL_CODE", nullable = true, length = 50)
	public String getNationalCode() {
		return nationalCode;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Column(name = "OFFICE_ADDRESS", nullable = true, length = 100)
	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOperateDept(String operateDept) {
		this.operateDept = operateDept;
	}

	@Column(name = "OPERATE_DEPT", nullable = true, length = 50)
	public String getOperateDept() {
		return operateDept;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 7)
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_CODE", nullable = true, length = 50)
	public String getOrgCode() {
		return orgCode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "PHONE", nullable = true, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Column(name = "PROFILE", nullable = true, length = 1000)
	public String getProfile() {
		return profile;
	}

	public void setRcUnit(String rcUnit) {
		this.rcUnit = rcUnit;
	}

	@Column(name = "RC_UNIT", nullable = true, length = 10)
	public String getRcUnit() {
		return rcUnit;
	}

	public void setRegisteredFund(Double registeredFund) {
		this.registeredFund = registeredFund;
	}

	@Column(name = "REGISTERED_FUND", nullable = true, length = 20)
	public Double getRegisteredFund() {
		return registeredFund;
	}

	public void setRegisteredFundUnit(String registeredFundUnit) {
		this.registeredFundUnit = registeredFundUnit;
	}

	@Column(name = "REGISTERED_FUND_UNIT", nullable = true, length = 100)
	public String getRegisteredFundUnit() {
		return registeredFundUnit;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	@Column(name = "REGISTER_ADDRESS", nullable = true, length = 100)
	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	@Column(name = "REGISTER_CAPITAL", nullable = true, length = 50)
	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "REMARKS", nullable = true, length = 200)
	public String getRemarks() {
		return remarks;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public String getRemoved() {
		return removed;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "ROLE", nullable = true, length = 50)
	public String getRole() {
		return role;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "SHORT_NAME", nullable = true, length = 100)
	public String getShortName() {
		return shortName;
	}

	public void setStaffPopulation(String staffPopulation) {
		this.staffPopulation = staffPopulation;
	}

	@Column(name = "STAFF_POPULATION", nullable = true, length = 20)
	public String getStaffPopulation() {
		return staffPopulation;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Column(name = "TAX_CODE", nullable = true, length = 100)
	public String getTaxCode() {
		return taxCode;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "WEBSITE", nullable = true, length = 50)
	public String getWebsite() {
		return website;
	}

	@ManyToOne(targetEntity = PsCorporation.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_ID")
	public PsCorporation getPsCorporation() {
		return psCorporation;
	}

	public void setPsCorporation(PsCorporation psCorporation) {
		this.psCorporation = psCorporation;
	}

    @Column(name = "SUPPLIER_ID", nullable = true,length = 200)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    @Column(name = "CITY_CODE", nullable = true,length = 200)
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @Column(name = "TAX_START_DATE", nullable = true)
    public Date getTaxStartDate() {
        return taxStartDate;
    }

    public void setTaxStartDate(Date taxStartDate) {
        this.taxStartDate = taxStartDate;
    }
    @Column(name = "TAX_END_DATE", nullable = true)
    public Date getTaxEndDate() {
        return taxEndDate;
    }

    public void setTaxEndDate(Date taxEndDate) {
        this.taxEndDate = taxEndDate;
    }
    @Column(name = "BUSI_START_DATE", nullable = true)
    public Date getBusiStartDate() {
        return busiStartDate;
    }

    public void setBusiStartDate(Date busiStartDate) {
        this.busiStartDate = busiStartDate;
    }
    @Column(name = "BUSI_END_DATE", nullable = true)
    public Date getBusiEndDate() {
        return busiEndDate;
    }

    public void setBusiEndDate(Date busiEndDate) {
        this.busiEndDate = busiEndDate;
    }
    @Column(name = "ORG_START_DATE", nullable = true)
    public Date getOrgStartDate() {
        return orgStartDate;
    }

    public void setOrgStartDate(Date orgStartDate) {
        this.orgStartDate = orgStartDate;
    }
    @Column(name = "ORG_END_DATE", nullable = true)
    public Date getOrgEndDate() {
        return orgEndDate;
    }

    public void setOrgEndDate(Date orgEndDate) {
        this.orgEndDate = orgEndDate;
    }
    @Column(name = "ESTABLISHMENT_TIME", nullable = true)
    public Date getEstablishmentTime() {
        return establishmentTime;
    }

    public void setEstablishmentTime(Date establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    @Column(name = "MOT_MESS", nullable = true, length = 2000)
    public String getMotMess() {
        return motMess;
    }

    public void setMotMess(String motMess) {
        this.motMess = motMess;
    }

    @Column(name = "LAWER_ID_CARD", nullable = true, length = 50)
    public String getLawerIdCard() {
        return lawerIdCard;
    }

    public void setLawerIdCard(String lawerIdCard) {
        this.lawerIdCard = lawerIdCard;
    }

    @Column(name = "LAWER_ID_TYPE", nullable = true, length = 200)
    public String getLawerIdType() {
        return lawerIdType;
    }

    public void setLawerIdType(String lawerIdType) {
        this.lawerIdType = lawerIdType;
    }
    @Column(name = "CUSTOM_FIELD_4", nullable = true, length = 100)
    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }
    @Column(name = "PROVINCE_CODE", nullable = true, length = 100)
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    @Column(name = "LINKMAN_NAME", nullable = true, length = 100)
    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }
    @Column(name = "LINKMAN_TELPHONE", nullable = true, length = 100)
    public String getLinkmanTelphone() {
        return linkmanTelphone;
    }

    public void setLinkmanTelphone(String linkmanTelphone) {
        this.linkmanTelphone = linkmanTelphone;
    }
    @Column(name = "BUSI_LINKMAN_TEL", nullable = true, length = 100)
    public String getBusiLinkmanTel() {
        return busiLinkmanTel;
    }

    public void setBusiLinkmanTel(String busiLinkmanTel) {
        this.busiLinkmanTel = busiLinkmanTel;
    }

    @Column(name = "LINKMAN_EMAIL", nullable = true, length = 100)
    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }
    @Column(name = "BANK_BRANCH_NAME", nullable = true, length = 100)
    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    @Column(name = "TAX_NUM_ATTACH", nullable = true, length = 1000)
    public String getTaxNumAttach() {
        return taxNumAttach;
    }

    public void setTaxNumAttach(String taxNumAttach) {
        this.taxNumAttach = taxNumAttach;
    }

    @Column(name = "BUSI_NUM_ATTACH", nullable = true, length = 1000)
    public String getBusiNumAttach() {
        return busiNumAttach;
    }

    public void setBusiNumAttach(String busiNumAttach) {
        this.busiNumAttach = busiNumAttach;
    }

    @Column(name = "ORG_NUM_ATTACH", nullable = true, length = 1000)
    public String getOrgNumAttach() {
        return orgNumAttach;
    }

    public void setOrgNumAttach(String orgNumAttach) {
        this.orgNumAttach = orgNumAttach;
    }
}
