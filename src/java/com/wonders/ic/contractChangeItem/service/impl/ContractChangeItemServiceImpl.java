package com.wonders.ic.contractChangeItem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeItem.dao.ContractChangeItemDao;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wonders.ic.contractChangeItem.service.ContractChangeItemService;
import com.wondersgroup.framework.core.bo.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

public class ContractChangeItemServiceImpl implements ContractChangeItemService {
    private ContractChangeItemDao contractChangeItemDao;


    public ContractChangeItemDao getContractChangeItemDao() {
        return contractChangeItemDao;
    }


    public void setContractChangeItemDao(ContractChangeItemDao contractChangeItemDao) {
        this.contractChangeItemDao = contractChangeItemDao;
    }


    public Page findItemByPage(Map<String, Object> filter, int pageNo,
                               int pageSize) {
        Page page = contractChangeItemDao.findItemByPage(filter, pageNo, pageSize);
        return page;
    }


    @Override
    public void saveContractChangeItem(ContractChangeItem contractChangeItem) {
        if (!StringUtils.isBlank(contractChangeItem.getId())) {
            ContractChangeItem oldContractChangeItem = contractChangeItemDao.findById(contractChangeItem.getId());
            BeanUtils.copyProperties(contractChangeItem, oldContractChangeItem, new String[]{"changeSort","regTime","contractChangeAgreementId","contractChangeProtocolId","dept","deptId","contractType",
                    "contractChangeItemList","leader","createTime","updateTime","removed","regLoginName"});


            contractChangeItem = oldContractChangeItem;
        } else {
            contractChangeItem.setId(null);
        }
        contractChangeItem.setContractSq(Integer.parseInt(contractChangeItem.getChangeSerialNo()));
        contractChangeItemDao.saveContractChangeItem(contractChangeItem);
    }

    @Override
    public Integer getMaxContractSq(String contractId) {
        return contractChangeItemDao.getMaxNo(contractId);
    }

    @Override
    public Map getChangeItemNum(Map<String, Object> filter) {
        return contractChangeItemDao.countNum(filter);
    }

    @Override
    public void syncData(Date startDate,Date endDate) {
        contractChangeItemDao.syncInsert( startDate, endDate);
        contractChangeItemDao.syncUpdate( startDate, endDate);
    }

    @Override
    public ContractChangeItem findById(String id) {
        return contractChangeItemDao.findById(id);
    }

    @Override
    public void delete(String id) {
        contractChangeItemDao.deleteOnLogical(id);
    }

    @Override
    public void update(ContractChangeItem contractChangeItem) {
        contractChangeItemDao.update(contractChangeItem);
    }
}
