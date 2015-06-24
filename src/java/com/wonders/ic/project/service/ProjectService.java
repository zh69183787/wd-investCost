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

package com.wonders.ic.project.service;

import java.util.List;
import java.util.Map;

import com.wonders.api.dto.ContractDto;
import com.wonders.ic.project.entity.bo.Project;
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

public interface ProjectService {

    public Page getProjects(Project project, int pageNo, int pageSize);

	public Page findProjectByPage(Map<String, Object> filter, int pageNo,
			int pageSize,List<String> addDeptIdList);
	
	public Page findProjectByPageWithoutAdddeptList(Map<String, Object> filter, int pageNo,
			int pageSize,List<String> addDeptIdList);
	
	/**
	 * 保存项目
	 * @param contract 保存项目
	 */
	public void saveProject(Project project);
	
	/**
	 * 查询项目
	 * @param id 主键
	 * @return 项目
	 */
	public Project findProjectById(String id);
	
	/**
	 * 保存修改
	 * @param contract
	 */
	public void update(Project project);
	
	/**
	 * 删除
	 * @param 主键
	 */
	public void deleteById(String id);
	
	
	/**
	 * 模糊搜索
	 * @param projectNo 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNoWithFuzzySearch(String projectNo);
	
	/**
	 * 模糊搜索
	 * @param projectName 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNameWithFuzzySearch(String projectName);
	
	/**
	 * 模糊搜索
	 * @param dispatchNo 搜索条件
	 * @return 项目
	 */
	public List<Project> findByDispatchNoWithFuzzySearch(String dispatchNo);
	
	/**
	 * 根据公司名称查询总数
	 * @param companyName
	 * @return
	 */
	public Map<String, String> findCountGroupByAddDeptIds(List<String> addDeptIds);
	
	/**
	 * 根据公司名称查询总数
	 * @param companyName
	 * @return
	 */
	public Map<String, String> findCountGroupByAddDeptIds(List<String> addDeptIds,String contracttType);
	
	
	/**
	 * 根据项目公司的id找出所对应的线路
	 * @param filter
	 * @return
	 */
	public String getLineByCompanyId(String id);
	
	/**
	 * 项目名称是否重复
	 * @return true:重复，false:不重复
	 */
	public boolean isProjectNameRepeated(String projectName,String projectId);
	
	/**
	 * 项目编号是否重复
	 * @param projectNo
	 * @return true:重复，false:不重复
	 */
	public boolean isProjectNoRepeated(String projectNo,String projectId);
	
	/**
	 * 根据addDeptId(项目公司)查询
	 * @param addDeptId
	 * @return
	 */
	public Long findSumOfProjectByAddDeptIdAndType(String adddeptId,String type);

	/**
	 * 根据addDeptId(项目公司)查询
	 * @param addDeptId
	 * @return
	 */
	public Long findSumOfProjectByTypeWithoutAdddeptId(String type,List<String> adddeptId);
	
	
	/**
	 * 模糊搜索
	 * @param projectNo,projectType 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNoWithFuzzySearch(String projectNo,String type);
	
	/**
	 * 模糊搜索
	 * @param projectName,projectType 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNameWithFuzzySearch(String projectName,String type);
	
	public Page findByPage(Map<String,Object> filter,int pageNo,int pageSize);
	
	public List<Project> findByProjectName(String projectName);
	
	public List<Project> findByDispatchNo(String dispatchNo);
	
	/**
	 * 根据项目类型查询
	 * @param projectType  1:外部项目,2:内部项目（库中2,3,4都是内部项目）
	 * @return
	 */
	public List<Project> findByProjectTypeAndProjectAddeptId(String projectType,String projectAdddeptId);
	
	public List<Project> findByFilter(Map<String, Object> filter);
	
	
	/**
	 * 根据条件类型查找
	 * @param condition 只接受condition1,condition2,其他返回null
	 * @return
	 */
	public List<Project> findByCondition(String condition,String projectAdddeptId,Map<String, Object> filter);
	
	public List<Project> findByConditonAndFilter(String condition,Map<String, Object> filter);
	
	
	
	/**
	 * 查询单一的值
	 * @param sql 一定要是sql语句,返回值是一个数值
	 * @return
	 */
	public String findOneData(String sql);
	
	public List<Project> findProjectByHql(String hql,int begin,int dataSize);
	
	public int findCountBySql(String sql);
	
	public List<Object[]> findBySQL(String sql);
	
	public Page findProjectByPage(Map<String, Object> filter, Integer pageNo, Integer pageSize);
	
	public List<ContractDto> findContractsByProjectId(String projectId);
}
