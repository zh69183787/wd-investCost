package com.wonders.ic.contractChangeProtocol.dao;

import java.util.Date;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface ContractChangeProtocolDao extends AbstractHibernateDao<Contract>{
	
	/**
	 * 保存合同
	 * @param contractChangeProtocol 合同
	 */
	public void saveContractChangeProtocol(ContractChangeProtocol contractChangeProtocol);
	
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

	public ContractChangeProtocol findById(String id);


    Map count(Map<String, Object> filter);

    public void syncInsertData(Date startDate,Date endDate);

    public void syncUpdateData(Date startDate,Date endDate);
}
