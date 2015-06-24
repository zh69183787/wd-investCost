package com.wonders.api.dto;

/**
 * Created by Administrator on 2014/10/31.
 */
public class ContractDto {
    private String id;
    private String      contractNo;
    private String  selfNo;
    private String          contractName;
    private String  contractType;
    private String         contractOwnerExecuteName;
    private String  buildSupplierName;
    private String        contractSignedDate;
    private String contractPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractOwnerExecuteName() {
        return contractOwnerExecuteName;
    }

    public void setContractOwnerExecuteName(String contractOwnerExecuteName) {
        this.contractOwnerExecuteName = contractOwnerExecuteName;
    }

    public String getBuildSupplierName() {
        return buildSupplierName;
    }

    public void setBuildSupplierName(String buildSupplierName) {
        this.buildSupplierName = buildSupplierName;
    }

    public String getContractSignedDate() {
        return contractSignedDate;
    }

    public void setContractSignedDate(String contractSignedDate) {
        this.contractSignedDate = contractSignedDate;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }
}
