package com.wonders.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/10/31.
 */
public class ProjectDto implements Serializable {

    private String id;
    private String projectAdddept;
    private String projectAddperson;
    private String projectBudgetAll;
    private String projectMoneysource;
    private String projectName;
    private String projectNo;
    private String dispatchNo;
    private String projectType;
    private String primaryDesignBudget;
    private String settlementPrice;
    private String payType;

    private List<ContractDto>  contracts;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectAdddept() {
        return projectAdddept;
    }

    public void setProjectAdddept(String projectAdddept) {
        this.projectAdddept = projectAdddept;
    }

    public String getProjectAddperson() {
        return projectAddperson;
    }

    public void setProjectAddperson(String projectAddperson) {
        this.projectAddperson = projectAddperson;
    }

    public String getProjectBudgetAll() {
        return projectBudgetAll;
    }

    public void setProjectBudgetAll(String projectBudgetAll) {
        this.projectBudgetAll = projectBudgetAll;
    }

    public String getProjectMoneysource() {
        return projectMoneysource;
    }

    public void setProjectMoneysource(String projectMoneysource) {
        this.projectMoneysource = projectMoneysource;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getPrimaryDesignBudget() {
        return primaryDesignBudget;
    }

    public void setPrimaryDesignBudget(String primaryDesignBudget) {
        this.primaryDesignBudget = primaryDesignBudget;
    }

    public String getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(String settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
    
}
