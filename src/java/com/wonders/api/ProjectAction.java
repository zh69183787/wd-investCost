package com.wonders.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * Created by Administrator on 2014/10/31.
 */
public class ProjectAction extends BaseAction implements ModelDriven<Project> {

    private ProjectService projectService;
    private Project project = new Project();
    
    private String dispatchNo;
    private String projectName;
    private String excludeCancelStatus;

    public String projects() throws Exception{
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		
//		System.out.println("dispatchNo:" + project.getDispatchNo());
//		System.out.println("projectName:" + projectName);
		if(null == excludeCancelStatus || "".equals(excludeCancelStatus)){
			excludeCancelStatus = "0";
		}
		if("1".equals(excludeCancelStatus.trim())){
			filter.put("projectState", "1");
		}
		if (null != project.getDispatchNo() && !"".equals(project.getDispatchNo())) {
			filter.put("dispatchNo", project.getDispatchNo());
		}
		if (null != project.getProjectName() && !"".equals(project.getProjectName())) {
			filter.put("projectName", project.getProjectName());
		}
//		filter.put("dispatchNo", "沪地铁（2010）144号");
		Page page = projectService.findProjectByPage(filter, getCurrentPageNo(), getPageSize());
		
        Map result = new HashMap();
        result.put("projects",page.getResult());
        result.put("page",page);

        outputJson(result,getCallback());
        return Action.NONE;
    }
    
    public String cancellations() throws Exception{
        Page page = projectService.getProjects(project,getCurrentPageNo(),getPageSize());
        Map result = new HashMap();
        result.put("projects",page.getResult());
        result.put("page",page);
        outputJson(result,getCallback());
        return Action.NONE;
    }
    
    public String updateProjectState() throws Exception{
    	Map result = new HashMap();
    	Project p = projectService.findProjectById(project.getId());
    	if(p!=null){
    		p.setProjectState(project.getProjectState());
    		p.setProjectDestoryDate(project.getProjectDestoryDate());
    		p.setSettlementPrice(project.getSettlementPrice());
    	    try {
    	    	this.projectService.saveProject(p);
    	    	result.put("errorCode", "1000");
    	    	result.put("errorMsg", "更新成功");
      	    } catch (Exception e) {
      	    	result.put("errorCode", "1001");
      	    	result.put("errorMsg", "更新失败");
      	    }    		
    	}else{
    		result.put("errorCode", "1001");
    		result.put("errorMsg", "无该ID项目");    		
    	}

    	this.getHttpServletResponse().setContentType("text/html;charset=utf-8");
    	this.getHttpServletResponse().getWriter().print(JSONObject.fromObject(result));
        return Action.NONE;
    }
    
    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Project getModel() {
        return project;
    }

    public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getExcludeCancelStatus() {
		return excludeCancelStatus;
	}

	public void setExcludeCancelStatus(String excludeCancelStatus) {
		this.excludeCancelStatus = excludeCancelStatus;
	}

	public static void main(String[] args) {
//        ProjectAction p = new ProjectAction();
//        Map map = new HashMap();
//        ProjectDto dto = new ProjectDto();
//        dto.setContracts(new ArrayList<ContractDto>());
//        ContractDto contractDto = new ContractDto();
//contractDto.setContractName("2232");
//        dto.getContracts().add(contractDto);
//        map.put("project",dto);
//        try {
//            p.outputJson(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
