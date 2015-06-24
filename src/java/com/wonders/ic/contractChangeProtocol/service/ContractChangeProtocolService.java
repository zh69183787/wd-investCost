package com.wonders.ic.contractChangeProtocol.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wondersgroup.framework.core.bo.Page;

public interface ContractChangeProtocolService {

	public  Page findItemByPage(Map<String, Object> filter, int pageNo,int pageSize);

	public void saveContractChangeProtocol(ContractChangeProtocol contractChangeProtocol);

	public ContractChangeProtocol findById(String id);

	public void delete(String id);

    Map getProtocolNumAndPrice(Map<String, Object> filter);

    public void syncData(Date startDate,Date endDate);
}
