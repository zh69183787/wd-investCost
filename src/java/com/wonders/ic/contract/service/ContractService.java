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

package com.wonders.ic.contract.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.entity.bo.KpiClear;
import com.wonders.ic.contract.entity.bo.KpiClearStatus;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface ContractService {
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize);
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList);
	
	public Page findContractByPageWithoutExecuteId(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList);
	
	public Page findByContractName(Map<String, Object> filter, int pageNo,int pageSize);
	
	/**
	 * 保存合同
	 * @param contract 保存合同
	 */
	public void saveContract(Contract contract);
	
	/**
	 * 查询合同
	 * @param id 主键
	 * @return 合同
	 */
	public Contract findContractById(String id);
	
	/**
	 * 保存修改
	 * @param contract
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
	 * @param contractName 合同名称
	 * @return 合同
	 */
	public List<Contract> findByContractNameWithFuzzySearch(String contractNo);
	
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteByIdOnLogical(String id);
	
	/**
	 * 查询总数，根据合同类型分类
	 * @return
	 */
	public Map<String, String> findCountGroupByContractType();
	
	/**
	 * 查询甲方执行的总数
	 * @return
	 */
	public Map<String, String> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList);
	
	/**
	 * 查询甲方执行的总数,根据合同类型查询
	 * @return
	 */
	public Map<String, String> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList,String contractType);
	
	/**
	 * 根据projectId,合同对应的工程量清单绑定了的概算科目
	 * 概算科目-->查出 工程量条目-->contractId
	 * @return
	 */
	public List<Object[]> findAllByProjectIdAndQuantitiesInEstimateSubject(String projectId,String estimateSubjectId);
	
	/**
	 * 根据contractId数组查询
	 * @param contractIdArray 
	 * @return
	 */
	public List<Contract> findAllByContractIdArray(String[] contractIdArray);
	
	/**
	 * 绑定概算科目
	 * @param contractList,必须是没有添加过工程量条目的合同
	 * @param subjectId 概算科目id
	 * @param subjectName	概算科目名称
	 * @return
	 */
	public void updateToBindBatchEstimateSubject(List<Contract> contractList, String subjectId,String subjectName);
	
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
	 * 删除合同，连带删除工程量清单和wbs表
	 * @param contractId
	 */
	public void deleteContractAndWbsAndQuantitiesByContractId(String contractId);
	
	
	/**
	 * 将HtXx表中的数据导入C_contract表中，用完之后删除本方法
	 * @return
	 */
	public void importHtXxToContract();
	
	//批量修改甲方（执行），合同分类
	public void updateBatchEdit(String[] ids,String executeName,String executeId,String type);
	
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
	 * 根据合同编号查询
	 * @param contractNo
	 * @return
	 */
	public List<Contract> findByContractNoLowerCase(String contractNo);
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractNo,contractType 合同编号
	 * @return 合同
	 */
	public List<Contract> findByContractNoWithFuzzySearch(String contractNo,String type);
	
	
	/**
	 * 根据合同编号模糊查询
	 * @param contractName,contractType 合同名称
	 * @return 合同
	 */
	public List<Contract> findByContractNameWithFuzzySearch(String contractNo,String type);
	
	public void saveAll(List<Contract> contractList);
	
	/**
	 * 
	 * @param contractType 合同类型,1,:建设类 2,:运维类  3,:其他类
	 * @param signedDate	签约时间，yyyy-MM,按照签约时间升序排
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
	 * 查询单一的值
	 * @param sql 一定要是sql语句,返回值是一个数值
	 * @return
	 */
	public String findOneData(String sql);
	
	public List<Contract> findContractBySelfNoWithFuzzySearch(String selfNo);
	
	/**
	 * 判断自有编号是否存在，忽视大小写
	 * @param selfNo
	 */
	public boolean isSelfNoExistIgnoreCase(String selfNo);
	
	/**
	 * 查询项目线路表
	 * @param name
	 * @return
	 */
	public List<CompanyRoute> findCompanyByName(String name);
	
	public List<CompanyRoute> findLineByCompanyId(String id);
	
	/**
	 * 查询集团下面的所有线路
	 */
	public List<CompanyRoute> findAllLine();
	
	public List<Contract> findByHql(String hql);
	public List<Object[]> findBySQL(String sql);
	
	
	public void updateAllContract(List<Contract> list);
	
	public List<Contract> findAllContractByUpdateDate(String startDate,String endDate);
	
	public void saveKpiClear(KpiClear kpiClear);
	
	public void saveKpiClearStatus(KpiClearStatus kpiClearStatus);
	
	public void updateKpiClearStatus(KpiClearStatus kpiClearStatus);
	
	public List<Object[]> findKpiContractByType(String type, Map<String, Object> filter);

	/**
	 * 根据合同ID查找KPI消缺
	 * @param id
	 * @return
	 */
	public List<KpiClear> findKpiClearByContractId(String id);
	
	public List<KpiClearStatus> findKpiClearStatusByContractId(String id);
	
	/**
	 * 根据用户查找其对应的甲方执行单位和分组
	 * @param name
	 * @return
	 */
	public List<Map<String, Object>> findUserGroupByName(String name);
	
	public String findQueryFilterByName(String name);
	
	public String findNameByGroup(String executeName, String contractGroup);

	
	public List<Object[]> getKpiClearCount();
	
	public List<Map<String,Object>> findKpiByContractId(String contractId);

	List getNameByContractIdInGroup(String contractId);

	Page findContractChangeItems(Map<String, Object> filter,String loc, int pageNo,
			int pageSize);

}
