/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.ic.contract.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.entity.bo.HtBa;
import com.wonders.ic.contract.entity.bo.HtXx;
import com.wonders.ic.contract.entity.bo.KpiClear;
import com.wonders.ic.contract.entity.bo.KpiClearStatus;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface ContractDao extends AbstractHibernateDao<Contract> {
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize);
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList);
	
	public Page findContractByPageWithoutExecuteId(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList);
	
	public Page findByContractName(Map<String, Object> filter, int pageNo,int pageSize);
	/**
	 * 保存合同
	 * @param contract 合同
	 */
	public void saveContract(Contract contract);
	
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
	 * @param 主键
	 */
	public void deleteById(String id);
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractNo 合同编号
	 * @return 合同
	 */
	public List<Contract> findByContractNoWithFuzzySearch(String contractNo); 
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractNo 合同名称
	 * @return 合同
	 */
	public List<Contract> findByContractNameWithFuzzySearch(String contractName); 
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteByIdOnLogical(String id);
	
	/**
	 * 查询总数，根据合同类型分类
	 * @return
	 */
	public List<Object[]> findCountGroupByContractType();
	
	/**
	 * 查询甲方执行的总数
	 * @return
	 */
	public List<Object[]> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList);
	
	/**
	 * 查询甲方执行的总数,根据合同类型查询
	 * @return
	 */
	public List<Object[]> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList,String contractType);
	
	
	/**
	 * 根据projectId,合同对应的工程量清单绑定了的概算科目
	 * 概算科目-->查出 工程量条目-->contractId
	 * @return
	 */
	public List<Object[]> findAllByProjectIdAndQuantitiesInEstimateSubject(String projectId,String estimateSubjectId);
	
	/**
	 * 
	 * @param idList
	 * @return
	 */
	public List<Contract> findAllByContractIdList(List<String> idList);
	
	/**
	 * 
	 * @return
	 */
	public List<Object[]> findCountByContractType(List<String> typeList,Map<String, Object> filter,List<String> addDeptIdList);
	
	/**
	 * 
	 * @param typeList
	 * @param filter
	 * @param addDeptIdList
	 * @return
	 */
	public List<Object[]> findCountByContractTypeWithoutExecuteId(List<String> typeList,Map<String, Object> filter,List<String> addDeptIdList);
	
	/**
	 * 查询合同总价
	 * @param investEstimateId 概算科目id
	 * @return
	 */
	public String findSumOfContractPriceByInvestEstimateId(String investEstimateId);
	
	/**
	 * 更新合同里面绑定的项目名称
	 * @param projectId
	 */
	public void updateProjectNameByProjectId(String projectId,String projectName);
	
	public void updateProjectNoAndProjectNameByProjectId(String projectId,String projectNo,String projectName);
	
	/**
	 * 批量修改甲方（执行），合同分类
	 * @param ids 合同id
	 * @param executeName	甲方执行名称
	 * @param executeId		甲方执行id
	 * @param type			合同类型
	 */
	public void updateBatchEdit(String[] ids,String executeName,String executeId,String type);
	
	/**
	 * 查询合同总数
	 * @return
	 */
	public Long findContractsSum();
	
	/**
	 * 根据合同类型，查询总数
	 * @return
	 */
	public Long findContractsSumByType(String type);
	
	/**
	 * 根据合同类型，查询总数，没有甲方执行
	 * @return
	 */
	public Long findContractsSumByTypeWithoutOwnerExecuteId(String type);
	
	/**
	 * 查询合同总数，没有甲方（执行）
	 * @param type
	 * @return
	 */
	public Long findSumOfContractByTypeWithoutExecuteId(String type);
	
	/**
	 * 查询合同总数，没有甲方（执行）
	 * @param type
	 * @return
	 */
	public Long findSumOfContractByTypeWithoutExecuteId(String type,List<String> executeIdList);
	
	
	/**
	 * 将HtXx表中的数据导入C_contract表中，用完之后删除本方法
	 * start******************************************
	 */
	
	public List<HtXx> findAllHtXx();
	public List<Contract> findAllByContractNo(String contractNo);
	public HtBa findHtBaByHtXxId(long htXxId);
	public void saveAll(List<Contract> contractList);
	/**
	 * end******************************************
	 */
	
	/**
	 * 根据合同编号查询
	 */
	public List<Contract> findByContractNoLowerCase(String contractNo);
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractNo 合同编号
	 * @return 合同
	 */
	public List<Contract> findByContractNoWithFuzzySearch(String contractNo,String type); 
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractNo 合同名称
	 * @return 合同
	 */
	public List<Contract> findByContractNameWithFuzzySearch(String contractName,String type); 
	
	/**
	 * 
	 * @param contractType 合同类型
	 * @param signedDate	签约时间，yyyy-MM
	 * @return
	 */
	public List<Contract> findByContractType(String contractType);
	
	/**
	 * 
	 * @param filter 查询条件 只接收7个参数
	 * @return
	 */
	public List<Contract> findByFilter(Map<String, Object> filter);
	
	/**
	 * 执行sql语句，只查询一个值
	 * @param sql
	 * @return
	 */
	public String executeSQLReturnOneData(String sql);
	
	public List<Contract> findContractBySelfNoWithFuzzySearch(String selfNo);
	
	/**
	 * 根据selfNo查询，忽视大小写
	 * @param selfNo
	 * @return
	 */
	public List<Contract> findBySelfNoIgnoreCase(String selfNo);
	
	/**
	 * 查询公司线路表
	 * @param name
	 * @return
	 */
	public List<CompanyRoute> findCompanyByNameAndType(String name,String type);
	
	public List<CompanyRoute> findByPidAndType(String id,String type);
	
	public List<CompanyRoute> findAllLine();
	
	public List<Contract> findContractByHQL(String hql);
	
	public List<Object[]> findContractBySQL(String sql);
	
	public List<Contract> findAllContractByUpdateDate(String startDate,String endDate);
	
	public void saveKpiClear(KpiClear kpiClear);
	
	public void saveKpiClearStatus(KpiClearStatus kpiClearStatus);
	
	public void updateKpiClearStatus(KpiClearStatus kpiClearStatus);
	
	public List<Object[]> findKpiContractByType(String type, Map<String, Object> filter);
	
	public List findKpiClearByContractId(String id);
	
	public List<KpiClearStatus> findKpiClearStatusByContractId(String id);
	
	public List<Object[]> findUserGroupByName(String name);
	
	public String findQueryFilterByName(String name);
	
	public String findNameByGroup(String executeName, String contractGroup);
	
	public void sendMessage(String receiver,String content,String sendLoginName,String sendUserName);
	
	public List<Object[]> getKpiClearCount();
	
	public List<Object[]> findKpiByContractId(String contractId);

	boolean isLoginNameInGroup(String filter, String contractId);

	List<String> findUserGroup();

	List findNameByQueryFilter(String filter);

	public Page findContractChangeItems(Map<String, Object> filter,String loc, int pageNo,
			int pageSize);

}
