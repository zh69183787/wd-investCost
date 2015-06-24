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

package com.wonders.ic.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.api.dto.ContractDto;
import com.wonders.ic.project.dao.ProjectDao;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Page findProjectByPage(Map<String, Object> filter, int pageNo,
			int pageSize,List<String> addDeptIdList) {
		return projectDao.findProjectByPage(filter, pageNo, pageSize,addDeptIdList);
	}

    @Override
    public Page getProjects(Project project, int pageNo, int pageSize) {
        return projectDao.findProject(project,pageNo,pageSize);
    }

    @Override
	public Page findProjectByPageWithoutAdddeptList(Map<String, Object> filter,
			int pageNo, int pageSize, List<String> addDeptIdList) {
		return projectDao.findProjectByPageWithoutAdddeptList(filter, pageNo, pageSize, addDeptIdList);
	}

	public void saveProject(Project project){
		projectDao.saveProject(project);
	}
	public void update(Project project){
		projectDao.update(project);
	}
	public void deleteById(String id){
		projectDao.deleteById(id);
	}
	public Project findProjectById(String id) {
		return projectDao.findProjectById(id);
	}

	@Override
	public List<Project> findByProjectNoWithFuzzySearch(String projectNo) {
		
		return projectDao.findByProjetNBoWithFuzzySearch(projectNo);
	}

	@Override
	public List<Project> findByProjectNameWithFuzzySearch(String projectName) {
		return projectDao.findByProjectNameWithFuzzySearch(projectName);
	}

	@Override
	public List<Project> findByDispatchNoWithFuzzySearch(String dispatchNo) {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("dispatchNo", dispatchNo);
		Page page = projectDao.findByPage(filter, 1, 10);
		return page.getResult();
	}
	
	@Override
	public Map<String, String> findCountGroupByAddDeptIds(List<String> addDeptIds) {
		Map<String, String> resultMap = null;
		List<Object[]> list = projectDao.findCountGroupByAddDeptIds(addDeptIds);
		if(list!=null && list.size()>0){
			resultMap = new HashMap<String, String>();
			for(int i=0; i<list.size(); i++){
				resultMap.put(list.get(i)[0].toString(), list.get(i)[1].toString());
			}
		}
		return resultMap;
	}
	
	@Override
	public Map<String, String> findCountGroupByAddDeptIds(List<String> addDeptIds,String projectType) {
		Map<String, String> resultMap = null;
		List<Object[]> list = projectDao.findCountGroupByAddDeptIds(addDeptIds,projectType);
		if(list!=null && list.size()>0){
			resultMap = new HashMap<String, String>();
			for(int i=0; i<list.size(); i++){
				resultMap.put(list.get(i)[0].toString(), list.get(i)[1].toString());
			}
			for(int i=0; i<addDeptIds.size(); i++){
				if(resultMap.get(addDeptIds.get(i))==null || resultMap.get(addDeptIds.get(i)).equals("")){
					resultMap.put(addDeptIds.get(i), "0");
				}
			}
		}
		return resultMap;
	}
	
	public String getLineByCompanyId(String id){
		return projectDao.getLineByCompanyId(id);
	}

	@Override
	public boolean isProjectNameRepeated(String projectName,String projectId) {
		List<Project> list = projectDao.findAllByName(projectName,projectId);
		if(list==null || list.size()<1) return false;
		return true;
	}

	@Override
	public boolean isProjectNoRepeated(String projectNo,String projectId) {
		List<Project> list = projectDao.findAllByNo(projectNo,projectId);
		if(list==null || list.size()<1) return false;
		return true;
	}

	@Override
	public Long findSumOfProjectByAddDeptIdAndType(String addDeptId,String type) {
		return projectDao.findSumOfProjectByAddDeptIdAndType(addDeptId,type);
	}

	@Override
	public Long findSumOfProjectByTypeWithoutAdddeptId(String type,
			List<String> adddeptId) {
		
		return projectDao.findSumOfProjectByTypeWithoutAdddeptId(type, adddeptId);
	}
	
	@Override
	public List<Project> findByProjectNoWithFuzzySearch(String projectNo,String type) {
		
		return projectDao.findByProjetNBoWithFuzzySearch(projectNo,type);
	}

	@Override
	public List<Project> findByProjectNameWithFuzzySearch(String projectName,String type) {
		return projectDao.findByProjectNameWithFuzzySearch(projectName,type);
	}

	@Override
	public Page findByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		
		return projectDao.findByPage(filter, pageNo, pageSize);
	}

	@Override
	public List<Project> findByProjectName(String projectName) {
		
		return projectDao.findByProjectName(projectName);
	}
	
	@Override
	public List<Project> findByDispatchNo(String dispatchNo) {
		return projectDao.findByDispatchNo(dispatchNo);
	}

	@Override
	public List<Project> findByProjectTypeAndProjectAddeptId(String projectType,String projectAdddeptId) {
		return projectDao.findByProjectTypeAndProjectAddeptId(projectType,projectAdddeptId);
	}

	
	
	@Override
	public List<Project> findByFilter(Map<String, Object> filter) {
		return projectDao.findByFilter(filter);
	}

	@Override
	public List<Project> findByCondition(String condition,String projectAddeptId,Map<String, Object> filter) {
		if(condition!=null && !"".equals(condition)){
			if("condition1".equals(condition) || "condition2".equals(condition)){
				return projectDao.findByCondition(condition,projectAddeptId,filter);
			}
		}
		return null;
	}

	@Override
	public List<Project> findByConditonAndFilter(String condition,
			Map<String, Object> filter) {
		if(condition!=null && !"".equals(condition)){
			if("condition1".equals(condition) || "condition2".equals(condition)){
				return projectDao.findByConditonAndFilter(condition,filter);
			}
		}
		return null;
	}

	@Override
	public String findOneData(String sql) {
		return projectDao.executeSQLReturnOneData(sql);
	}

	@Override
	public List<Project> findProjectByHql(String hql, int begin, int dataSize) {
		
		return projectDao.findProjectByHql(hql,begin,dataSize);
	}

	@Override
	public int findCountBySql(String sql) {
		
		return projectDao.findCountBySql(sql);
	}

	@Override
	public List<Object[]> findBySQL(String sql) {
		
		return projectDao.findBySQL(sql);
	}

	@Override
	public Page findProjectByPage(Map<String, Object> filter, Integer pageNo, Integer pageSize) {
		return projectDao.findProjectByPage(filter, pageNo, pageSize);
	}

	@Override
	public List<ContractDto> findContractsByProjectId(String projectId) {
		return projectDao.findContractsByProjectId(projectId);
	}
	
	
}
