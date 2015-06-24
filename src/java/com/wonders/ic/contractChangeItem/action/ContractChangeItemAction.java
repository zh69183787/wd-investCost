package com.wonders.ic.contractChangeItem.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.wonders.ic.JsonUtils;
import org.apache.commons.lang.StringUtils;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wonders.ic.contractChangeItem.service.ContractChangeItemService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class ContractChangeItemAction extends BaseAjaxAction {


    private ContractChangeItem contractChangeItem = new ContractChangeItem();

    private ContractChangeItemService contractChangeItemService;
    private ContractService contractService;


    private Integer pageSize = 10;
    private Integer pageNo = 1;
    private String contractId;
    private String notSelected;
    private String format;
    private Page page;

    //查出"变更协议"的数据列表
    public String changeItems() {
        Assert.notNull(contractId, "合同ID不能为空!");

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("contract.id", contractId);
        filter.put("contractChangeProtocolId", contractChangeItem.getContractChangeProtocolId());
        filter.put("_notSelected", notSelected);
        page = contractChangeItemService.findItemByPage(filter, Integer.valueOf(pageNo), pageSize);

        if (StringUtils.isNotBlank(format) && format.equals("json")) {
            createJSonData(JsonUtils.getJsonDataFromPage(page, ContractChangeItem.class, new String[]{"contractChangeProtocols", "kpiClearStatus"}));
            return AJAX;
        }

        return SUCCESS;
    }

    public String uniqueNo() {
        if (StringUtils.isNotBlank(contractChangeItem.getId())) {
            ContractChangeItem changeItem = contractChangeItemService.findById(contractChangeItem.getId());
            contractId = changeItem.getContract().getId();
            if (contractChangeItem.getChangeSerialNo().equals(changeItem.getChangeSerialNo())) {
                createJSonData("1");
                return AJAX;
            }
        }

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("contract.id", contractId);
        filter.put("changeSerialNo", contractChangeItem.getChangeSerialNo());
        page = contractChangeItemService.findItemByPage(filter, 1, pageSize);
        Integer total = page.getTotalSize();

        createJSonData(total > 0 ? "0" : "1");
        return AJAX;
    }

    /**
     * 显示新增或修改页面
     */
    public String changeItem() throws InvocationTargetException, IllegalAccessException {


        if (!StringUtils.isBlank(contractChangeItem.getId())) {
            ContractChangeItem oldContractChangeItem = contractChangeItemService.findById(contractChangeItem.getId());
            Contract contract = contractService.findContractById(oldContractChangeItem.getContract().getId());
            BeanUtils.copyProperties(oldContractChangeItem, contractChangeItem);
            contractChangeItem.setContract(contract);

        } else {
            Assert.notNull(contractId, "合同ID不能为空!");
            Integer maxSq = contractChangeItemService.getMaxContractSq(contractId);
            Contract contract = contractService.findContractById(contractId);
            contractChangeItem.setContract(contract);
            contractChangeItem.setChangeSerialNo(String.valueOf(maxSq));

        }

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {
        contractChangeItemService.delete(contractChangeItem.getId());
        return "changeItemsAction";
    }


    /**
     * 保存修改
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String save() throws IllegalAccessException, InvocationTargetException {
        if(StringUtils.isNotBlank(contractChangeItem.getChangeContentType()))
        contractChangeItem.setChangeContentType(contractChangeItem.getChangeContentType().replaceAll(" ", ""));
        if(StringUtils.isNotBlank(contractChangeItem.getChangeItemType()))
        contractChangeItem.setChangeItemType(contractChangeItem.getChangeItemType().replaceAll(" ", ""));
        if(StringUtils.isNotBlank(contractChangeItem.getChangePriceType()))
        contractChangeItem.setChangePriceType(contractChangeItem.getChangePriceType().replaceAll(" ", ""));

        contractChangeItemService.saveContractChangeItem(contractChangeItem);

        return "changeItemAction";
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

    public ContractChangeItem getContractChangeItem() {
        return contractChangeItem;
    }

    public void setContractChangeItem(ContractChangeItem contractChangeItem) {
        this.contractChangeItem = contractChangeItem;
    }


    public ContractService getContractService() {
        return contractService;
    }


    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }


    @Override
    public Object getModel() {
        return contractChangeItem;
    }


    public ContractChangeItemService getContractChangeItemService() {
        return contractChangeItemService;
    }


    public void setContractChangeItemService(
            ContractChangeItemService contractChangeItemService) {
        this.contractChangeItemService = contractChangeItemService;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNotSelected() {
        return notSelected;
    }

    public void setNotSelected(String notSelected) {
        this.notSelected = notSelected;
    }
}
