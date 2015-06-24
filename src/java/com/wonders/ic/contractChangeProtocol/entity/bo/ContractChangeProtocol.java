package com.wonders.ic.contractChangeProtocol.entity.bo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

//import org.hibernate.annotations.Entity;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import com.wonders.ic.contract.entity.bo.Contract;

/**
 * CContractChangeProtocol entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
@Table(name = "C_CONTRACT_CHANGE_PROTOCOL")
public class ContractChangeProtocol implements java.io.Serializable {

	// Fields

	private String id;
	private String originalContractName;
	private Double changePrice;
	private String changeItemType;

	private String dealPerson;
	private String regPerson;
	private Date signTime;
	private String dealDeptSuggest;
	private String memo;
	private String contractName;
	private String contractNo;
	private String contractSelfNum;
	private Double contractPrice;
	private String oppositeCompany;
	private Date execPeriodStart;
	private String regLoginName;
	private Date regTime;
	private Date passTime;
	private Date execPeriodEnd;
	private String attach;
	private Date createTime;
	private Date updateTime;
	private String removed;
	private String dept;
	private String deptId;
	private Contract contract;
	private String corporationId;

    private List<ContractChangeItem> contractChangeItemList;

    public ContractChangeProtocol(){
        this.removed = "0";
        this.createTime = new Date();
        this.updateTime = new Date();
    }


	// Property accessors
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ORIGINAL_CONTRACT_NAME", length = 300)
	public String getOriginalContractName() {
		return this.originalContractName;
	}

	public void setOriginalContractName(String originalContractName) {
		this.originalContractName = originalContractName;
	}

	@Column(name = "CHANGE_PRICE", precision = 20, scale = 6)
	public Double getChangePrice() {
		return this.changePrice;
	}

	public void setChangePrice(Double changePrice) {
		this.changePrice = changePrice;
	}

	@Column(name = "CHANGE_ITEM_TYPE", length = 1)
	public String getChangeItemType() {
		return this.changeItemType;
	}

	public void setChangeItemType(String changeItemType) {
		this.changeItemType = changeItemType;
	}

    @Column(name = "DEAL_PERSON", length = 100)
	public String getDealPerson() {
		return this.dealPerson;
	}

	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	@Column(name = "REG_PERSON", length = 100)
	public String getRegPerson() {
		return this.regPerson;
	}

	public void setRegPerson(String regPerson) {
		this.regPerson = regPerson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIGN_TIME", length = 7)
	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	@Column(name = "DEAL_DEPT_SUGGEST", length = 500)
	public String getDealDeptSuggest() {
		return this.dealDeptSuggest;
	}

	public void setDealDeptSuggest(String dealDeptSuggest) {
		this.dealDeptSuggest = dealDeptSuggest;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "CONTRACT_NAME", length = 300)
	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CONTRACT_NO", length = 200)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_SELF_NUM", length = 200)
	public String getContractSelfNum() {
		return this.contractSelfNum;
	}

	public void setContractSelfNum(String contractSelfNum) {
		this.contractSelfNum = contractSelfNum;
	}

	@Column(name = "CONTRACT_PRICE", precision = 20, scale = 6)
	public Double getContractPrice() {
		return this.contractPrice;
	}

	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "OPPOSITE_COMPANY", length = 200)
	public String getOppositeCompany() {
		return this.oppositeCompany;
	}

	public void setOppositeCompany(String oppositeCompany) {
		this.oppositeCompany = oppositeCompany;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXEC_PERIOD_START", length = 7)
	public Date getExecPeriodStart() {
		return this.execPeriodStart;
	}

	public void setExecPeriodStart(Date execPeriodStart) {
		this.execPeriodStart = execPeriodStart;
	}

	@Column(name = "REG_LOGIN_NAME", length = 100,updatable = false)
	public String getRegLoginName() {
		return this.regLoginName;
	}

	public void setRegLoginName(String regLoginName) {
		this.regLoginName = regLoginName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REG_TIME", length = 7,updatable = false)
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PASS_TIME", length = 7)
	public Date getPassTime() {
		return this.passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXEC_PERIOD_END", length = 7)
	public Date getExecPeriodEnd() {
		return this.execPeriodEnd;
	}

	public void setExecPeriodEnd(Date execPeriodEnd) {
		this.execPeriodEnd = execPeriodEnd;
	}

	@Column(name = "ATTACH", length = 100)
	public String getAttach() {
		return this.attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7,updatable = false )
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

	@Column(name = "DEPT", length = 200,updatable = false)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "DEPT_ID", length = 100,updatable = false)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@ManyToOne(targetEntity=Contract.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Column(name = "CORPORATION_ID", length = 100)
	public String getCorporationId() {
		return this.corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

    @OneToMany(fetch = FetchType.LAZY,targetEntity = ContractChangeItem.class,cascade =
            {
                    CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE,
            })
    @JoinColumns(value={@JoinColumn(name="CONTRACT_CHANGE_PROTOCOL_ID")})
    public List<ContractChangeItem> getContractChangeItemList() {
        return contractChangeItemList;
    }

    public void setContractChangeItemList(List<ContractChangeItem> contractChangeItemList) {
        this.contractChangeItemList = contractChangeItemList;
    }
}