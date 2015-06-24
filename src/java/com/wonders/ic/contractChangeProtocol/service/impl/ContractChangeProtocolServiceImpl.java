package com.wonders.ic.contractChangeProtocol.service.impl;

import java.util.Date;
import java.util.Map;

import com.wonders.ic.contractChangeProtocol.dao.ContractChangeProtocolDao;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wonders.ic.contractChangeProtocol.service.ContractChangeProtocolService;
import com.wondersgroup.framework.core.bo.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

public class ContractChangeProtocolServiceImpl implements ContractChangeProtocolService {
    private ContractChangeProtocolDao contractChangeProtocolDao;

    @Override
    public void delete(String id) {
        contractChangeProtocolDao.deleteOnLogical(id);
    }

    @Override
    public Map getProtocolNumAndPrice(Map<String, Object> filter) {
        return contractChangeProtocolDao.count(filter);
    }

    public ContractChangeProtocolDao getContractChangeProtocolDao() {
        return contractChangeProtocolDao;
    }


    public void setContractChangeProtocolDao(ContractChangeProtocolDao contractChangeProtocolDao) {
        this.contractChangeProtocolDao = contractChangeProtocolDao;
    }


    public Page findItemByPage(Map<String, Object> filter, int pageNo,int pageSize) {
        return contractChangeProtocolDao.findItemByPage(filter, pageNo, pageSize);
    }


    @Override
    public void saveContractChangeProtocol(ContractChangeProtocol contractChangeProtocol) {

        if(!StringUtils.isBlank(contractChangeProtocol.getId())){
            ContractChangeProtocol oldContractChangeProtocol = contractChangeProtocolDao.findById(contractChangeProtocol.getId());
            BeanUtils.copyProperties(contractChangeProtocol, oldContractChangeProtocol, new String[]{"contractChangeItemList",
                    "regLoginName","regTime","createTime","updateTime","removed","dept","deptId"});
            oldContractChangeProtocol.setContractChangeItemList(contractChangeProtocol.getContractChangeItemList());
            contractChangeProtocol = oldContractChangeProtocol;
        }else{
            contractChangeProtocol.setId(null);
        }
        contractChangeProtocolDao.saveContractChangeProtocol(contractChangeProtocol);
    }

    @Override
    public ContractChangeProtocol findById(String id) {
        return contractChangeProtocolDao.findById(id);
    }

    public void syncData(Date startDate,Date endDate){
        contractChangeProtocolDao.syncUpdateData(startDate,endDate);
        contractChangeProtocolDao.syncInsertData(startDate,endDate);
    }
}
