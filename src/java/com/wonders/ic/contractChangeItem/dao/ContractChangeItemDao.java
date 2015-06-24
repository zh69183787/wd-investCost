package com.wonders.ic.contractChangeItem.dao;

import java.util.Date;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface ContractChangeItemDao extends AbstractHibernateDao<Contract>{
	
	/**
	 * 保存合同
	 * @param contractChangeItem 合同
	 */
	public void saveContractChangeItem(ContractChangeItem contractChangeItem);
	
	/**
	 * 查询
	 * @param id 主键
	 */
	public Contract findContractById(String id);
	
	/**
	 * 保存修改
	 * @param contract 合同
	 */
	public void update(Contract contract);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
	

	public Page findItemByPage(Map<String, Object> filter, int pageNo,
			int pageSize);



	public void deleteOnLogical(String id);

	public ContractChangeItem findById(String id);

	public void update(ContractChangeItem contractChangeItem);

    public Integer getMaxNo(String contractId);

    Map countNum(Map<String, Object> filter);

    void syncUpdate(Date startDate,Date endDate);

    void syncInsert(Date startDate,Date endDate);
}
