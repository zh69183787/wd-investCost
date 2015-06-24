package com.wonders.ic.contractChangeItem.entity.bo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.ic.contract.entity.bo.Contract;

@Entity
@Table(name = "C_CONTRACT_CHANGE_ITEM")
public class ContractChangeItem implements java.io.Serializable {

    // Fields

    private String id;
    private String changeContractNo;
    private String changeSerialNo;
    private String changePutForword;
    private String changeItemType;
    private String changeContentType;
    private String changeContentDescription;
    private BigDecimal changePrice;
    private String changePriceType;
    private String changePriceDescription;
    private String supervisionSuggest;


    private String leader;
    private String attach;
    private Date createTime;
    private Date updateTime;
    private String removed;
    private String regPerson;
    private String regLoginName;
    private Date regTime;
    private String dealPerson;
    private String dealDeptSuggest;
    private Date passTime;
    private String dept;
    private String deptId;
    private String contractType;
    private Integer contractSq;
    private Contract contract;
    private String contractChangeProtocolId;
    private String contractChangeAgreementId;
    private String changeSort;
    private String changeMemo;

    // Constructors

    /**
     * default constructor
     */
    public ContractChangeItem() {
        this.removed = "0";
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    @Column(name = "CONTRACT_CHANGE_AGREEMENT_ID", length = 32)
    public String getContractChangeAgreementId() {
        return contractChangeAgreementId;
    }

    public void setContractChangeAgreementId(String contractChangeAgreementId) {
        this.contractChangeAgreementId = contractChangeAgreementId;
    }

    @Column(name = "CONTRACT_CHANGE_PROTOCOL_ID", length = 32)
    public String getContractChangeProtocolId() {
        return contractChangeProtocolId;
    }

    public void setContractChangeProtocolId(String contractChangeProtocolId) {
        this.contractChangeProtocolId = contractChangeProtocolId;
    }

    // Property accessors
    @Id
    @GeneratedValue(generator = "system.uuid")
    @GenericGenerator(name = "system.uuid", strategy = "uuid")
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(name = "CHANGE_PRICE", precision = 20, scale = 6)
    public BigDecimal getChangePrice() {
        return this.changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }


    @Column(name = "CHANGE_CONTRACT_NO", length = 100)
    public String getChangeContractNo() {
        return this.changeContractNo;
    }

    public void setChangeContractNo(String changeContractNo) {
        this.changeContractNo = changeContractNo;
    }

    @Column(name = "CHANGE_SERIAL_NO", length = 200)
    public String getChangeSerialNo() {
        return this.changeSerialNo;
    }

    public void setChangeSerialNo(String changeSerialNo) {
        this.changeSerialNo = changeSerialNo;
    }

    @Column(name = "CHANGE_PUT_FORWORD", length = 1)
    public String getChangePutForword() {
        return this.changePutForword;
    }

    public void setChangePutForword(String changePutForword) {
        this.changePutForword = changePutForword;
    }


    @Column(name = "LEADER", length = 100)
    public String getLeader() {
        return this.leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Column(name = "ATTACH", length = 100)
    public String getAttach() {
        return this.attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7,updatable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TIME", length = 7)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "REMOVED", length = 1)
    public String getRemoved() {
        return this.removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    @Column(name = "CHANGE_ITEM_TYPE", length = 1)
    public String getChangeItemType() {
        return this.changeItemType;
    }

    public void setChangeItemType(String changeItemType) {
        this.changeItemType = changeItemType;
    }

    @Column(name = "REG_PERSON", length = 100)
    public String getRegPerson() {
        return this.regPerson;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    @Column(name = "REG_LOGIN_NAME", length = 100)
    public String getRegLoginName() {
        return this.regLoginName;
    }

    public void setRegLoginName(String regLoginName) {
        this.regLoginName = regLoginName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REG_TIME", length = 7)
    public Date getRegTime() {
        return this.regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Column(name = "DEAL_PERSON", length = 100)
    public String getDealPerson() {
        return this.dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    @Column(name = "DEAL_DEPT_SUGGEST", length = 500)
    public String getDealDeptSuggest() {
        return this.dealDeptSuggest;
    }

    public void setDealDeptSuggest(String dealDeptSuggest) {
        this.dealDeptSuggest = dealDeptSuggest;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PASS_TIME", length = 7)
    public Date getPassTime() {
        return this.passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    @Column(name = "DEPT", length = 200)
    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Column(name = "DEPT_ID", length = 100)
    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @ManyToOne(targetEntity = Contract.class)
    @JoinColumn(name = "CONTRACT_ID")
    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Column(name = "CHANGE_SORT", length = 100)
    public String getChangeSort() {
        return changeSort;
    }

    public void setChangeSort(String changeSort) {
        this.changeSort = changeSort;
    }

    @Column(name = "CHANGE_MEMO", length = 200)
    public String getChangeMemo() {
        return changeMemo;
    }

    public void setChangeMemo(String changeMemo) {
        this.changeMemo = changeMemo;
    }

    @Column(name = "CHANGE_CONTENT_TYPE", length = 1)
    public String getChangeContentType() {
        return changeContentType;
    }

    public void setChangeContentType(String changeContentType) {
        this.changeContentType = changeContentType;
    }

    @Column(name = "CHANGE_CONTENT_DESCRIPTION", length = 2000)
    public String getChangeContentDescription() {
        return changeContentDescription;
    }

    public void setChangeContentDescription(String changeContentDescription) {
        this.changeContentDescription = changeContentDescription;
    }

    @Column(name = "CHANGE_PRICE_TYPE", length = 1)
    public String getChangePriceType() {
        return changePriceType;
    }

    public void setChangePriceType(String changePriceType) {
        this.changePriceType = changePriceType;
    }

    @Column(name = "CHANGE_PRICE_DESCRIPTION", length = 2000)
    public String getChangePriceDescription() {
        return changePriceDescription;
    }

    public void setChangePriceDescription(String changePriceDescription) {
        this.changePriceDescription = changePriceDescription;
    }

    @Column(name = "SUPERVISION_SUGGEST", length = 2000)
    public String getSupervisionSuggest() {
        return supervisionSuggest;
    }

    public void setSupervisionSuggest(String supervisionSuggest) {
        this.supervisionSuggest = supervisionSuggest;
    }

    @Column(name = "CONTRACT_TYPE", length = 10)
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Column(name = "CONTRACT_SQ")
    public Integer getContractSq() {
        return contractSq;
    }

    public void setContractSq(Integer contractSq) {
        this.contractSq = contractSq;
    }
}