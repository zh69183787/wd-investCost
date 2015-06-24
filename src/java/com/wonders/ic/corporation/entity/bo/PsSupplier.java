package com.wonders.ic.corporation.entity.bo;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Administrator on 2014/8/12.
 */
@Entity
@Table(name = "PS_SUPPLIER")
public class PsSupplier implements Serializable{


    private String id;

    @Id
    @javax.persistence.Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String chineseFullName;

    @Basic
    @javax.persistence.Column(name = "CHINESE_FULL_NAME")
    public String getChineseFullName() {
        return chineseFullName;
    }

    public void setChineseFullName(String chineseFullName) {
        this.chineseFullName = chineseFullName;
    }

    private String englishFullName;

    @Basic
    @javax.persistence.Column(name = "ENGLISH_FULL_NAME")
    public String getEnglishFullName() {
        return englishFullName;
    }

    public void setEnglishFullName(String englishFullName) {
        this.englishFullName = englishFullName;
    }

    private String companyType;

    @Basic
    @javax.persistence.Column(name = "COMPANY_TYPE")
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    private String cityCode;

    @Basic
    @javax.persistence.Column(name = "CITY_CODE")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    private String regCapital;

    @Basic
    @javax.persistence.Column(name = "REG_CAPITAL")
    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    private String regCapitalUnit;

    @Basic
    @javax.persistence.Column(name = "REG_CAPITAL_UNIT")
    public String getRegCapitalUnit() {
        return regCapitalUnit;
    }

    public void setRegCapitalUnit(String regCapitalUnit) {
        this.regCapitalUnit = regCapitalUnit;
    }

    private String taxNum;

    @Basic
    @javax.persistence.Column(name = "TAX_NUM")
    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    private Date taxStartDate;

    @Basic
    @javax.persistence.Column(name = "TAX_START_DATE")
    public Date getTaxStartDate() {
        return taxStartDate;
    }

    public void setTaxStartDate(Date taxStartDate) {
        this.taxStartDate = taxStartDate;
    }

    private Date taxEndDate;

    @Basic
    @javax.persistence.Column(name = "TAX_END_DATE")
    public Date getTaxEndDate() {
        return taxEndDate;
    }

    public void setTaxEndDate(Date taxEndDate) {
        this.taxEndDate = taxEndDate;
    }

    private Date busiStartDate;

    @Basic
    @javax.persistence.Column(name = "BUSI_START_DATE")
    public Date getBusiStartDate() {
        return busiStartDate;
    }

    public void setBusiStartDate(Date busiStartDate) {
        this.busiStartDate = busiStartDate;
    }

    private Date busiEndDate;

    @Basic
    @javax.persistence.Column(name = "BUSI_END_DATE")
    public Date getBusiEndDate() {
        return busiEndDate;
    }

    public void setBusiEndDate(Date busiEndDate) {
        this.busiEndDate = busiEndDate;
    }

    private String orgNum;

    @Basic
    @javax.persistence.Column(name = "ORG_NUM")
    public String getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(String orgNum) {
        this.orgNum = orgNum;
    }

    private Date orgStartDate;

    @Basic
    @javax.persistence.Column(name = "ORG_START_DATE")
    public Date getOrgStartDate() {
        return orgStartDate;
    }

    public void setOrgStartDate(Date orgStartDate) {
        this.orgStartDate = orgStartDate;
    }

    private Date orgEndDate;

    @Basic
    @javax.persistence.Column(name = "ORG_END_DATE")
    public Date getOrgEndDate() {
        return orgEndDate;
    }

    public void setOrgEndDate(Date orgEndDate) {
        this.orgEndDate = orgEndDate;
    }

    private Date establishmentTime;

    @Basic
    @javax.persistence.Column(name = "ESTABLISHMENT_TIME")
    public Date getEstablishmentTime() {
        return establishmentTime;
    }

    public void setEstablishmentTime(Date establishmentTime) {
        this.establishmentTime = establishmentTime;
    }

    private String employeeNum;

    @Basic
    @javax.persistence.Column(name = "EMPLOYEE_NUM")
    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    private String webSite;

    @Basic
    @javax.persistence.Column(name = "WEB_SITE")
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    private String tradeCode;

    @Basic
    @javax.persistence.Column(name = "TRADE_CODE")
    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    private String businessScope;

    @Basic
    @javax.persistence.Column(name = "BUSINESS_SCOPE")
    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    private String accountNum;

    @Basic
    @javax.persistence.Column(name = "ACCOUNT_NUM")
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    private String officeAddress;

    @Basic
    @javax.persistence.Column(name = "OFFICE_ADDRESS")
    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    private String registerAddress;

    @Basic
    @javax.persistence.Column(name = "REGISTER_ADDRESS")
    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    private String cdmFaxNumber;

    @Basic
    @javax.persistence.Column(name = "CDM_FAX_NUMBER")
    public String getCdmFaxNumber() {
        return cdmFaxNumber;
    }

    public void setCdmFaxNumber(String cdmFaxNumber) {
        this.cdmFaxNumber = cdmFaxNumber;
    }

    private String cdmMainTelephoneNumber;

    @Basic
    @javax.persistence.Column(name = "CDM_MAIN_TELEPHONE_NUMBER")
    public String getCdmMainTelephoneNumber() {
        return cdmMainTelephoneNumber;
    }

    public void setCdmMainTelephoneNumber(String cdmMainTelephoneNumber) {
        this.cdmMainTelephoneNumber = cdmMainTelephoneNumber;
    }

    private String motMess;

    @Basic
    @javax.persistence.Column(name = "MOT_MESS")
    public String getMotMess() {
        return motMess;
    }

    public void setMotMess(String motMess) {
        this.motMess = motMess;
    }

    private String lawer;

    @Basic
    @javax.persistence.Column(name = "LAWER")
    public String getLawer() {
        return lawer;
    }

    public void setLawer(String lawer) {
        this.lawer = lawer;
    }

    private String lawerIdType;

    @Basic
    @javax.persistence.Column(name = "LAWER_ID_TYPE")
    public String getLawerIdType() {
        return lawerIdType;
    }

    public void setLawerIdType(String lawerIdType) {
        this.lawerIdType = lawerIdType;
    }

    private String lawerIdCard;

    @Basic
    @javax.persistence.Column(name = "LAWER_ID_CARD")
    public String getLawerIdCard() {
        return lawerIdCard;
    }

    public void setLawerIdCard(String lawerIdCard) {
        this.lawerIdCard = lawerIdCard;
    }

    private String customField4;

    @Basic
    @javax.persistence.Column(name = "CUSTOM_FIELD_4")
    public String getCustomField4() {
        return customField4;
    }

    public void setCustomField4(String customField4) {
        this.customField4 = customField4;
    }

    private String busiNum;

    @Basic
    @javax.persistence.Column(name = "BUSI_NUM")
    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }

    private String countryCode;

    @Basic
    @javax.persistence.Column(name = "COUNTRY_CODE")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    private String provinceCode;

    @Basic
    @javax.persistence.Column(name = "PROVINCE_CODE")
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    private String linkmanName;

    @Basic
    @javax.persistence.Column(name = "LINKMAN_NAME")
    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    private String linkmanTelphone;

    @Basic
    @javax.persistence.Column(name = "LINKMAN_TELPHONE")
    public String getLinkmanTelphone() {
        return linkmanTelphone;
    }

    public void setLinkmanTelphone(String linkmanTelphone) {
        this.linkmanTelphone = linkmanTelphone;
    }

    private String busiLinkemanTel;

    @Basic
    @javax.persistence.Column(name = "BUSI_LINKEMAN_TEL")
    public String getBusiLinkemanTel() {
        return busiLinkemanTel;
    }

    public void setBusiLinkemanTel(String busiLinkemanTel) {
        this.busiLinkemanTel = busiLinkemanTel;
    }

    private String linkmanEmail;

    @Basic
    @javax.persistence.Column(name = "LINKMAN_EMAIL")
    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

    private String bankName;

    @Basic
    @javax.persistence.Column(name = "BANK_NAME")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    private String bankBranchName;

    @Basic
    @javax.persistence.Column(name = "BANK_BRANCH_NAME")
    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    private String taxNumAttach;

    @Basic
    @javax.persistence.Column(name = "TAX_NUM_ATTACH")
    public String getTaxNumAttach() {
        return taxNumAttach;
    }

    public void setTaxNumAttach(String taxNumAttach) {
        this.taxNumAttach = taxNumAttach;
    }

    private String busiNumAttach;

    @Basic
    @javax.persistence.Column(name = "BUSI_NUM_ATTACH")
    public String getBusiNumAttach() {
        return busiNumAttach;
    }

    public void setBusiNumAttach(String busiNumAttach) {
        this.busiNumAttach = busiNumAttach;
    }

    private String orgNumAttach;

    @Basic
    @javax.persistence.Column(name = "ORG_NUM_ATTACH")
    public String getOrgNumAttach() {
        return orgNumAttach;
    }

    public void setOrgNumAttach(String orgNumAttach) {
        this.orgNumAttach = orgNumAttach;
    }


}
