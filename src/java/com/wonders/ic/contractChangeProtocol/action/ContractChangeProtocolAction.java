package com.wonders.ic.contractChangeProtocol.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wonders.ic.contractChangeItem.service.ContractChangeItemService;
import com.wondersgroup.framework.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wonders.ic.contractChangeProtocol.service.ContractChangeProtocolService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class ContractChangeProtocolAction extends BaseAjaxAction {


    private ContractService contractService;
    private ContractChangeProtocolService contractChangeProtocolService;
    private ContractChangeItemService contractChangeItemService;

    private ContractChangeProtocol contractChangeProtocol;
    private Integer pageSize = 10;
    private Integer pageNo = 1;
    private String contractId;
    private String changeItemIds;
    private Page page;

    @Override
    public ContractChangeProtocol getModel() {
        if(contractChangeProtocol == null)
            contractChangeProtocol = new ContractChangeProtocol();
        return contractChangeProtocol;
    }


    //查出"变更协议"的数据列表
    public String protocols() {
        Assert.notNull(contractId, "合同ID不能为空!");

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("contract.id", contractId);
        page = contractChangeProtocolService.findItemByPage(filter, Integer.valueOf(pageNo), pageSize);
        return SUCCESS;
    }


    /**
     * 显示新增或修改页面
     */
    public String protocol() throws InvocationTargetException, IllegalAccessException {



        if(!StringUtils.isBlank(contractChangeProtocol.getId())){
            ContractChangeProtocol oldContractChangeProtocol = contractChangeProtocolService.findById(contractChangeProtocol.getId());
            Contract  contract = contractService.findContractById(oldContractChangeProtocol.getContract().getId());
            BeanUtils.copyProperties(oldContractChangeProtocol,contractChangeProtocol);
            contractChangeProtocol.setContract(contract);

        }else{
            Assert.notNull(contractId, "合同ID不能为空!");
            Contract contract  = contractService.findContractById(contractId);
            contractChangeProtocol.setOriginalContractName(contract.getContractName());
            contractChangeProtocol.setContractNo(contract.getContractNo());
            contractChangeProtocol.setContractSelfNum(contract.getSelfNo());
            contractChangeProtocol.setExecPeriodStart(DateUtils.getDateFromYYYY_MM_DD(contract.getContractStartDate()));
            contractChangeProtocol.setExecPeriodEnd(DateUtils.getDateFromYYYY_MM_DD(contract.getContractEndDate()));
            contractChangeProtocol.setContractPrice(Double.parseDouble(contract.getContractPrice()));
            contractChangeProtocol.setOppositeCompany(contract.getBuildSupplierName());
            contractChangeProtocol.setCorporationId(contract.getBuildSupplierId());
            contractChangeProtocol.setContract(contract);
        }

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {
        contractChangeProtocolService.delete(contractChangeProtocol.getId());
        return "protocolsAction";
    }


    /**
     * 保存修改
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String save() throws IllegalAccessException, InvocationTargetException {
        ArrayList<ContractChangeItem> changeItems = new ArrayList<ContractChangeItem>();
        if(StringUtils.isNotBlank(changeItemIds)){
           String[] changeItemIdArray =  changeItemIds.split(",");
            for (String itemId : changeItemIdArray) {
                ContractChangeItem contractChangeItem = new ContractChangeItem();
                contractChangeItem.setId(itemId);
                changeItems.add(contractChangeItem);
            }
            contractChangeProtocol.setContractChangeItemList(changeItems);
        }
        contractChangeProtocolService.saveContractChangeProtocol(contractChangeProtocol);

        return "protocolAction";
    }


    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ContractChangeProtocol getContractChangeProtocol() {
        return contractChangeProtocol;
    }

    public void setContractChangeProtocol(ContractChangeProtocol contractChangeProtocol) {
        this.contractChangeProtocol = contractChangeProtocol;
    }


    public ContractChangeProtocolService getContractChangeProtocolService() {
        return contractChangeProtocolService;
    }

    public void setContractChangeProtocolService(
            ContractChangeProtocolService contractChangeProtocolService) {
        this.contractChangeProtocolService = contractChangeProtocolService;
    }


    public ContractService getContractService() {
        return contractService;
    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }


    public ContractChangeItemService getContractChangeItemService() {
        return contractChangeItemService;
    }

    public void setContractChangeItemService(ContractChangeItemService contractChangeItemService) {
        this.contractChangeItemService = contractChangeItemService;
    }

    public String getChangeItemIds() {
        return changeItemIds;
    }

    public void setChangeItemIds(String changeItemIds) {
        this.changeItemIds = changeItemIds;
    }
}
