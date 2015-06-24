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

package com.wonders.ic.project.dao;

import java.util.List;
import java.util.Map;

import com.wonders.api.dto.ContractDto;
import com.wonders.api.dto.ProjectDto;
import com.wonders.ic.project.entity.bo.Project;
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

public interface ProjectDao extends AbstractHibernateDao<Project> {

    Page findProject(Project project, int pageNo,int pageSize);

	/*public Page findProjectByPage(Map<String, Object> filter, int pageNo,
			int pageSize);*/
	
	/**
	 * 
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @param addDeptIdList
	 * @return
	 */
	public Page findProjectByPage(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList);
	
	public Page findProjectByPageWithoutAdddeptList(Map<String, Object> filter, int pageNo,
			int pageSize,List<String> addDeptIdList);
	/**
	 * 保存项目
	 * @param project 保存项目
	 * @return
	 */
	public void saveProject(Project project);
	/**
	 * 查询合同
	 * @param id 主键
	 * @return 合同
	 */
	public Project findProjectById(String id);
	
	/**
	 * 保存修改
	 * @param project
	 */
	public void update(Project project);
	
	/**
	 * 删除
	 * @param 主键
	 */
	public void deleteById(String id);
	
	/**
	 * 模糊搜索
	 * @param projectNo 项目编号
	 */
	public List<Project> findByProjetNBoWithFuzzySearch(String projectNo);
	
	/**
	 * 模糊搜索
	 * @param projectName 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNameWithFuzzySearch(String projectName);

	public List<Object[]> findCountGroupByAddDeptIds(List<String> addDeptIds) ;
	
	public List<Object[]> findCountGroupByAddDeptIds(List<String> addDeptIds,String projectType) ;
	
	
	/**
	 * 根据项目公司的id找出所对应的线路
	 * @param filter
	 * @return
	 */
	public String getLineByCompanyId(String id);
	
	/**
	 * 根据项目名称查询
	 * @param projectName
	 * @return
	 */
	public List<Project> findAllByName(String projectName,String projectId);
	
	/**
	 * 根据项目编号查询
	 * @param projectNo
	 * @return
	 */
	public List<Project> findAllByNo(String projectNo,String projectId);
	
	/**
	 * 查询项目总数
	 * @return
	 */
	public Long findSumOfProject();
	
	/**
	 * 根据addDeptId(项目公司)查询
	 * @param addDeptId
	 * @return
	 */
	public Long findSumOfProjectByAddDeptIdAndType(String type,String addDeptId);
	
	/**
	 * 根据addDeptId(项目公司)查询
	 * @param addDeptId
	 * @return
	 */
	public Long findSumOfProjectByTypeWithoutAdddeptId(String type,List<String> adddeptId);
	
	/**
	 * 模糊搜索
	 * @param projectNo,projectType 项目编号
	 */
	public List<Project> findByProjetNBoWithFuzzySearch(String projectNo,String type);
	
	/**
	 * 模糊搜索
	 * @param projectName,projectType 搜索条件
	 * @return 项目
	 */
	public List<Project> findByProjectNameWithFuzzySearch(String projectName,String type);
	
	/**
	 * 分页查询
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findByPage(Map<String,Object> filter,int pageNo,int pageSize);
	
	public List<Project> findByProjectName(String projectName);
	
	public List<Project> findByDispatchNo(String dispatchNo);
	
	/**
	 * 根据项目类型查询
	 * @param projectType  1:外部项目,2:内部项目（库中2,3,4都是内部项目）
	 * @return
	 */
	public List<Project> findByProjectTypeAndProjectAddeptId(String projectType,String projectAddeptId);
	
	public List<Project> findByFilter(Map<String, Object> filter);
	/**
	 * 根据条件类型查找
	 * @param condition 只接受condition1,condition2,其他返回null
	 * @param projectAddeptId 负责部门
	 * @return
	 */
	public List<Project> findByCondition(String condition,String projectAddeptId,Map<String, Object> filter);
	
	
	public List<Project> findByConditonAndFilter(String condition,Map<String, Object> filter);
	/**
	 * 执行sql语句，只查询一个值
	 * @param sql
	 * @return
	 */
	public String executeSQLReturnOneData(String sql);
	
	public List<Project> findProjectByHql(String hql,int begin,int dataSiez);
	
	public int findCountBySql(String sql);
	
	public List<Object[]> findBySQL(String sql);
	
	/**
	 * 根据dispatchNo, projectName, pageSize, currentPageNo查找项目
	 * @param filter
	 * @param page
	 * @return
	 */
	public Page findProjectByPage(Map<String, Object> filter, Integer pageNo, Integer pageSize);
	
	public List<ContractDto> findContractsByProjectId(String projectId);
}
