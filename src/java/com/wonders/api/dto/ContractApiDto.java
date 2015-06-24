package com.wonders.api.dto;

import java.util.List;

import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;

/**
 * 该Dto类用于api包下ContractAction类中的contracts方法
 * @author zhou
 *
 */
public class ContractApiDto {

	private String id;
	private String contractName;
	private String contractNo;
	private String selfNo;
	private String contractPrice;
	private String buildSupplierName;
	private String contractStartDate;
	private String contractEndDate;
    private String contractType;
	
	private List<ContractChangeProtocol> contractChangeProtocols;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getSelfNo() {
		return selfNo;
	}

	public void setSelfNo(String selfNo) {
		this.selfNo = selfNo;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getBuildSupplierName() {
		return buildSupplierName;
	}

	public void setBuildSupplierName(String buildSupplierName) {
		this.buildSupplierName = buildSupplierName;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public List<ContractChangeProtocol> getContractChangeProtocols() {
		return contractChangeProtocols;
	}

	public void setContractChangeProtocols(
			List<ContractChangeProtocol> contractChangeProtocols) {
		this.contractChangeProtocols = contractChangeProtocols;
	}

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
}
