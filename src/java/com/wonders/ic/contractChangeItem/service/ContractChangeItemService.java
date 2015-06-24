package com.wonders.ic.contractChangeItem.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wondersgroup.framework.core.bo.Page;

public interface ContractChangeItemService {

	public  Page findItemByPage(Map<String, Object> filter, int valueOf,
			int size);

	public void saveContractChangeItem(ContractChangeItem contractChangeItem);

	public ContractChangeItem findById(String id);

	public void delete(String id);

	public void update(ContractChangeItem tempContractChangeItem);

    public Integer getMaxContractSq(String contractId);

    Map getChangeItemNum(Map<String, Object> filter);

    void syncData(Date startDate,Date endDate);
}
