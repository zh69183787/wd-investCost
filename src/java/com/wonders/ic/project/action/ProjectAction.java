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

package com.wonders.ic.project.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.contractStatus.service.ContractStatusService;
import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;
import com.wonders.ic.dwProject.entity.bo.DwProjectCover;
import com.wonders.ic.dwProject.service.DwProjectCoverService;
import com.wonders.ic.export.ExportExcel;
import com.wonders.ic.progress.entity.bo.Progress;
import com.wonders.ic.progress.service.ProgressService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ProjectAction extends BaseAjaxAction {
	private Project project = new Project();
	private ContractService contractService;
	private ProjectService projectService;
	private AttachService attachService;
	private DeptTreeService deptTreeService;
	private ProgressService progressService;
	private DwProjectCoverService dwProjectCoverService;
	private ContractStatusService contractStatusService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private int size = 20;
	private final String type1 = "99991"; //项目公司
	private final String type2 = "99990"; //运营公司id
	private final String type3 = "2545"; //维保保障中心id
	private final String yg = "2540";		//运营管理中心
	private final String edu = "2546";	//教育培训中心
	
	private Map<String, String> companyMap = new HashMap<String, String>();
	
	public ProjectAction() {
		companyMap.put("2541", "运一公司");
		companyMap.put("2542", "运二公司");
		companyMap.put("2543", "运三公司");
		companyMap.put("2544", "运四公司");
		companyMap.put("2719", "维保中心-供电公司");
		companyMap.put("2721", "维保中心-工务公司");
		companyMap.put("2720", "维保中心-通号公司");
		companyMap.put("2722", "维保中心-物资和后勤公司");
		companyMap.put("2718", "维保中心-车辆公司");
		companyMap.put("2545", "上海轨道交通维护保障中心");
	}
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public void setContractStatusService(ContractStatusService contractStatusService) {
		this.contractStatusService = contractStatusService;
	}

	public void setAttachService(AttachService attachService) {
		this.attachService = attachService;
	}

	public void setDeptTreeService(DeptTreeService deptTreeService) {
		this.deptTreeService = deptTreeService;
	}

	public void setProgressService(ProgressService progressService) {
		this.progressService = progressService;
	}
	
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public void setDwProjectCoverService(DwProjectCoverService dwProjectCoverService) {
		this.dwProjectCoverService = dwProjectCoverService;
	}

	@Override
	public Object getModel() {
		return project;
	}

	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}

	
	
/**
 	* 查询列表页面
 * @throws DocumentException 
 * @throws UnsupportedEncodingException 
 */
	public String findProjectByPage() throws DocumentException, UnsupportedEncodingException {
		String projectMoneysource = servletRequest.getParameter("projectMoneysource");
		String current = servletRequest.getParameter("current");
		servletRequest.setAttribute("current", current);
		if(projectMoneysource!=null && !"".equals(projectMoneysource)){
			projectMoneysource = URLDecoder.decode(projectMoneysource,"utf-8");
			project.setProjectMoneysource(projectMoneysource);
		}
		
		
		String onLineProjectAdddeptId = "";
		Cookie[] cookies = servletRequest.getCookies();
		String userId="",token="",appName="",secret="";
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("userId")){
				userId = cookies[i].getValue();
				continue;
			}
			if(cookies[i].getName().equals("token")){
				token = cookies[i].getValue();
				continue;
			}
			
		}
		Properties properties = loadProperties("config.properties");
		secret = properties.getProperty("secret");
		appName = properties.getProperty("appName");
		String dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><userId>" + userId + "</userId></params>";
		
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getMatchedDeptUsersById",secret,"xml",dataParams);	//
		if(usePersonXMl!=null && !"".equals(usePersonXMl)){
			Document document = DocumentHelper.parseText(usePersonXMl);
			Element rootElem = document.getRootElement();
			
			Node userIdNode = rootElem.selectSingleNode("//deptId");
			if(userIdNode!=null){
				String dataParams2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + userIdNode.getText() + "</id></params>";
				String pidXml = getUserInfoThroughCA(appName,token,"getNodesInfo",secret,"xml",dataParams2);
				document = DocumentHelper.parseText(pidXml);
				rootElem = document.getRootElement();
				Node deptNode = rootElem.selectSingleNode("//pid");
				String companyId = deptNode.getText();
				String companyName = companyMap.get(companyId);
				if(companyName!=null && !"".equals(companyName)){	//在该公司下
					onLineProjectAdddeptId = companyId;
					servletRequest.setAttribute("onLineProjectAdddeptId", onLineProjectAdddeptId);
				}
			}
		}
		
		String nomodify = servletRequest.getParameter("nomodify");
		servletRequest.setAttribute("nomodify", nomodify);
		
		String projectEndtimePlanDateStart = servletRequest.getParameter("projectEndtimePlanDateStart");
		String projectStarttimePlanDateEnd = servletRequest.getParameter("projectStarttimePlanDateEnd");
		String approvalDateEnd = servletRequest.getParameter("approvalDateEnd");
		servletRequest.setAttribute("approvalDateEnd", approvalDateEnd);
		servletRequest.setAttribute("projectEndtimePlanDateStart", projectEndtimePlanDateStart);
		servletRequest.setAttribute("projectStarttimePlanDateEnd", projectStarttimePlanDateEnd);
		String pageNo = servletRequest.getParameter("pageNo");
		String companyId = ServletActionContext.getRequest().getParameter("projectAdddeptId");
		String pType = servletRequest.getParameter("pType");
		servletRequest.setAttribute("pType", pType);
		if(companyId==null || "".equals(companyId)){
			project.setProjectAdddeptId(companyId);
		}
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		
		String companyType = ServletActionContext.getRequest().getParameter("companyType");
		ServletActionContext.getRequest().setAttribute("companyType", companyType);
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> typeAllIdList = new ArrayList<String>();
		
		List<String> type1IdList = new ArrayList<String>();
		List<String> type2IdList= new ArrayList<String>();
		List<String> type3IdList= new ArrayList<String>();
		
		dept1TreeList.add(deptTreeService.findById(type1));
		dept2TreeList.add(deptTreeService.findById(type2));
		dept3TreeList.add(deptTreeService.findById(type3));
		
		//1.先根据父节点得到所有子节点的id(deptTree)
		dept1TreeList.addAll(dept1TreeList.size(), deptTreeService.findByPid(type1));
		dept2TreeList.addAll(dept2TreeList.size(), deptTreeService.findByPid(type2));
		dept3TreeList.addAll(dept3TreeList.size(), deptTreeService.findByPid(type3));
		
		
		if(pType!=null && !"".equals(pType)){
			List<String> type1List = new ArrayList<String>();
			List<String> type2List = new ArrayList<String>();
			List<String> type3List = new ArrayList<String>();
			/**项目公司数据**/
			if(dept1TreeList!=null && dept1TreeList.size()>0){
				int type1CountAll = 0;
				
				List<String[]> resultList1 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					for(int i=0; i<dept1TreeList.size(); i++){
						type1List.add(dept1TreeList.get(i).getId());
					}
					type1IdList.add(onLineProjectAdddeptId);
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						type1IdList.add(dept1TreeList.get(i).getId());
						type1List.add(dept1TreeList.get(i).getId());
					}
				}
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type1IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept1TreeList.size(); i++){
						if(typeMap.get(dept1TreeList.get(i).getId())!=null){
							resultList1.add(new String[]{dept1TreeList.get(i).getName(),typeMap.get(dept1TreeList.get(i).getId()),dept1TreeList.get(i).getId()});
							type1CountAll += Integer.valueOf(typeMap.get(dept1TreeList.get(i).getId()));
						}else{
							resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
					}
				}
				ServletActionContext.getRequest().setAttribute("type1CountAll", type1CountAll);
				ServletActionContext.getRequest().setAttribute("resultList1", resultList1);
			}
			/**运营公司数据**/
			if(dept2TreeList!=null && dept2TreeList.size()>0){
				int type2CountAll = 0;
				List<String[]> resultList2 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					type2IdList.add(onLineProjectAdddeptId);
					for(int i=0; i<dept2TreeList.size(); i++){
						type2List.add(dept2TreeList.get(i).getId());
					}
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						type2IdList.add(dept2TreeList.get(i).getId());
						type2List.add(dept2TreeList.get(i).getId());
					}
				}
				
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type2IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept2TreeList.size(); i++){
						if(typeMap.get(dept2TreeList.get(i).getId())!=null){
							resultList2.add(new String[]{dept2TreeList.get(i).getName(),typeMap.get(dept2TreeList.get(i).getId()),dept2TreeList.get(i).getId()});
							type2CountAll += Integer.valueOf(typeMap.get(dept2TreeList.get(i).getId()));
						}else{
							resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
					}
				}
				
				ServletActionContext.getRequest().setAttribute("type2CountAll", type2CountAll);
				ServletActionContext.getRequest().setAttribute("resultList2", resultList2);
			}
			/**维保中心数据**/
			if(dept3TreeList!=null && dept3TreeList.size()>0){
				int type3CountAll = 0;
				List<String[]> resultList3 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					for(int i=0; i<dept3TreeList.size(); i++){
						type3List.add(dept3TreeList.get(i).getId());
					}
					type3IdList.add(onLineProjectAdddeptId);
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						type3IdList.add(dept3TreeList.get(i).getId());
						type3List.add(dept3TreeList.get(i).getId());
					}
				}
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type3IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept3TreeList.size(); i++){
						if(typeMap.get(dept3TreeList.get(i).getId())!=null){
							resultList3.add(new String[]{dept3TreeList.get(i).getName(),typeMap.get(dept3TreeList.get(i).getId()),dept3TreeList.get(i).getId()});
							type3CountAll += Integer.valueOf(typeMap.get(dept3TreeList.get(i).getId()));
						}else{
							resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
					}
				}
				ServletActionContext.getRequest().setAttribute("type3CountAll", type3CountAll);
				ServletActionContext.getRequest().setAttribute("resultList3", resultList3);
			}
			
			
			Long ygAll = projectService.findSumOfProjectByAddDeptIdAndType(yg,"0"); //运营管理中心,id=2540
			Long eduAll = projectService.findSumOfProjectByAddDeptIdAndType(edu,"0"); 
			String [] ygCompany = {"运管中心",ygAll.toString(),yg};
			String [] eduCompany = {"教培中心",eduAll.toString(),edu};
			servletRequest.setAttribute("ygCompany", ygCompany);
			servletRequest.setAttribute("eduCompany", eduCompany);
			
			//其他项目数据
			Long othterProject = 0l;
			if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
				othterProject = projectService.findSumOfProjectByAddDeptIdAndType(onLineProjectAdddeptId, "5");
			}else{
				othterProject = projectService.findSumOfProjectByAddDeptIdAndType(null, "5");
			}
			servletRequest.setAttribute("othterProject", othterProject);
			
			//内部项目其他数据
			List<String> controlAndMaintainIdList = new ArrayList<String>();
			controlAndMaintainIdList.addAll(type2List);
			controlAndMaintainIdList.addAll(type3List);
			controlAndMaintainIdList.add(yg);	//运营中心
			controlAndMaintainIdList.add("2545");	//维护保障中心
			controlAndMaintainIdList.add(edu);	//维护保障中心
			Long innerOther = 0l;
			if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
				innerOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("0", controlAndMaintainIdList);
			}else{
				innerOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("0", controlAndMaintainIdList);
			}
			servletRequest.setAttribute("innerOther", innerOther );
			//外部项目其他数据
			Long outOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("1", type1List);
			servletRequest.setAttribute("outOther", outOther );
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		
		JSONObject obj = JSONObject.fromObject(this.project);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.project, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		filter.put("removed", "0");
		if(pType!=null && !"".equals(pType) && (project.getProjectType()==null || "".equals(project.getProjectType()))){
			filter.put("projectType", pType);
		}
		if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
			filter.put("projectAdddeptId", onLineProjectAdddeptId);
		}
		if(filter.get("projectAdddeptId")!=null && filter.get("projectAdddeptId").equals("2540")){ //点击查询的是运营管理中心
			typeAllIdList = null;
		}
		String findType = servletRequest.getParameter("findType");
		servletRequest.setAttribute("findType", findType);
		if(projectEndtimePlanDateStart!=null && !"".equals(projectEndtimePlanDateStart)){
			filter.put("projectEndtimePlanDateStart", projectEndtimePlanDateStart);
		}
		if(projectStarttimePlanDateEnd!=null && !"".equals(projectStarttimePlanDateEnd)){
			filter.put("projectStarttimePlanDateEnd", projectStarttimePlanDateEnd);
		}
		if(approvalDateEnd!=null && !"".equals(approvalDateEnd)){
			filter.put("approvalDateEnd", approvalDateEnd);
		}
		if(current!=null && !"".equals(current)){
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf2.parse(current));
			} catch (ParseException e) {
				System.out.println("error");
				e.printStackTrace();
			}
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			filter.put("approvalDate",sdf2.format(c.getTime()));
			filter.put("approvalDateEnd",current);
			
			project.setApprovalDate(sdf2.format(c.getTime()));
			//servletRequest.setAttribute("approvalDate", sdf2.format(c.getTime()));
			servletRequest.setAttribute("approvalDateEnd", current);
			ServletActionContext.getRequest().setAttribute("showOrHide", "show");
		}
		Page page = null;
		if(findType!=null && "notin".equals(findType)){
			List<String> controlAndMaintainIdList = new ArrayList<String>(); 
			if(pType!=null && pType.equals("0")){  //内部项目
				controlAndMaintainIdList.addAll(type2IdList);
				controlAndMaintainIdList.addAll(type3IdList);
				controlAndMaintainIdList.add(yg);
				controlAndMaintainIdList.add(edu);
				page = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), size,controlAndMaintainIdList);
			}else if(pType!=null && pType.equals("1")){
				controlAndMaintainIdList.addAll(type1IdList);
			}
			typeAllIdList.add("2540");
			page = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), size,controlAndMaintainIdList);
		}else{
			if(filter.get("projectType")!=null && filter.get("projectType").equals("5")){
				page = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), size,null);
			}else{
				page = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), size,typeAllIdList);
			}
		}

		
		//数据送到前台
		ServletActionContext.getRequest().setAttribute("page", page);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		String ifExport = this.servletRequest.getParameter("ifExport");
		int dataLength = 20000;
		if("yes".equals(ifExport)){//导出excel
			
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=projectExport.xls"); 
			List<Object[]> dataset = new ArrayList<Object[]>();
			Page page2 = null;
			if(findType!=null && "notin".equals(findType)){
				List<String> controlAndMaintainIdList = new ArrayList<String>(); 
				if(pType!=null && pType.equals("0")){  //内部项目
					controlAndMaintainIdList.addAll(type2IdList);
					controlAndMaintainIdList.addAll(type3IdList);
					controlAndMaintainIdList.add(yg);
					controlAndMaintainIdList.add(edu);
					page2 = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), dataLength,controlAndMaintainIdList);
				}else if(pType!=null && pType.equals("1")){
					controlAndMaintainIdList.addAll(type1IdList);
				}
				typeAllIdList.add("2540");
				page2 = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), dataLength,controlAndMaintainIdList);
			}else{
				if(filter.get("projectType")!=null && filter.get("projectType").equals("5")){
					page2 = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), dataLength,null);
				}else{
					page2 = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), dataLength,typeAllIdList);
				}
			}
			List<Project> list = page2.getResult();
			for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[14];
				params[0] = list.get(i).getProjectName();
				params[1] = list.get(i).getProjectType();
				if (list.get(i).getProjectType() != null) {
					if (list.get(i).getProjectType().equals("1")) {
						params[1] = "外部项目";
					} else if (list.get(i).getProjectType().equals("2")) {
						params[1] = "内部项目资本类";
					} else if (list.get(i).getProjectType().equals("3")) {
						params[1] = "内部项目大修类";
					} else if (list.get(i).getProjectType().equals("4")) {
						params[1] = "内部项目能源类";
					}else if(list.get(i).getProjectType().equals("5")){
						params[1] = "其他类";
					}
				} else {
					params[1] = null;
				}
				params[2] = list.get(i).getProjectNo();
				params[3] = list.get(i).getProjectBudgetAll();
				params[4] = list.get(i).getProjectCompany();
				params[5] = list.get(i).getKeyword();
				params[6] = list.get(i).getProjectAdddept();
				params[7] = list.get(i).getProjectAddperson();
				// params[8] = list.get(i).getProfessionalType();
				if (list.get(i).getProfessionalType() != null) {
					if (list.get(i).getProfessionalType().equals("1")) {
						params[8] = "车辆";
					} else if (list.get(i).getProfessionalType().equals("2")) {
						params[8] = "供电";
					} else if (list.get(i).getProfessionalType().equals("3")) {
						params[8] = "通号";
					} else if (list.get(i).getProfessionalType().equals("4")) {
						params[8] = "工务";
					} else if (list.get(i).getProfessionalType().equals("5")) {
						params[8] = "基地";
					} else if (list.get(i).getProfessionalType().equals("6")) {
						params[8] = "车站机电";
					}
				} else {
					params[8] = null;
				}
				// params[9] = list.get(i).getProjectState();
				if (list.get(i).getProjectState() != null) {
					if (list.get(i).getProjectState().equals("1")) {
						params[9] = "项目准备";
					} else if (list.get(i).getProjectState().equals("2")) {
						params[9] = "项目实施";
					} else if (list.get(i).getProjectState().equals("3")) {
						params[9] = "销项完成";
					} else if (list.get(i).getProjectState().equals("4")) {
						params[9] = "项目采购";
					} else if (list.get(i).getProjectState().equals("5")) {
						params[9] = "合同审批";
					} else if (list.get(i).getProjectState().equals("6")) {
						params[9] = "合同执行";
					} else if (list.get(i).getProjectState().equals("7")) {
						params[9] = "合同结算";
					} else if (list.get(i).getProjectState().equals("8")) {
						params[9] = "项目结算";
					}
				} else {
					params[9] = null;
				}
				params[10] = list.get(i).getProjectStarttimePlanDate();
				params[11] = list.get(i).getProjectEndtimePlanDate();
				params[12] = list.get(i).getDispatchNo();
				// params[13] = list.get(i).getSubstituteConstruction();
				if (list.get(i).getSubstituteConstruction() != null) {
					if (list.get(i).getSubstituteConstruction().equals("1")) {
						params[13] = "是";
					} else if (list.get(i).getSubstituteConstruction().equals(
							"2")) {
						params[13] = "否";
					}
				} else {
					params[13] = null;
				}

				dataset.add(params);
			}
			
			String[] headers = {"项目名称","项目类型","项目编号","投资估算总额(万元)","项目公司","关键字","负责部门","负责人","专业分类","项目状态","计划开始时间","计划完成时间","发文编号","是否代建"};
			short[] width = {12000,4000,8000,6000,8000,4000,8000,3000,3000,3000,4000,4000,6000,3000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}else{
			if(nomodify!=null && !"".equals(nomodify) && "viewOnly".equals(nomodify)){
				return "viewOnly";
			}else{
				return SUCCESS;
			}
		}
	}

	
	/**
	 * 显示新增页面
	 */
	public String showAdd(){
		return "showAdd";
	}

	/**
	 * 保存新增
	 */
	public String saveAdd(){
		
		String attachIds = ServletActionContext.getRequest().getParameter("attachIds");
		
		project.setProjectAttachId(attachIds);
		project.setProjectBudgetAll(getFormattedMoney(project.getProjectBudgetAll()));
		project.setProjectFeasibilityBudget(getFormattedMoney(project.getProjectFeasibilityBudget()));
		project.setPrimaryDesignBudget(getFormattedMoney(project.getPrimaryDesignBudget()));
		project.setPrimaryDesignMoney(getFormattedMoney(project.getPrimaryDesignMoney()));
		
		project.setCreateDate(sdf.format(new Date()));
		project.setOperateDate(sdf.format(new Date()));
		project.setRemoved("0");
		projectService.saveProject(project);
		return SUCCESS;
	}
	
	/**
	 * 显示详细页面
	 * @throws UnsupportedEncodingException 
	 */
	public String showView() throws UnsupportedEncodingException{
		String id = ServletActionContext.getRequest().getParameter("id");
		project = projectService.findProjectById(id);
		
		List<Progress> progressList = progressService.findAllByObjectId(id,"2");
		ServletActionContext.getRequest().setAttribute("progressList", progressList);
		
		if(project!=null && project.getProjectAttachId()!=null && !"".equals(project.getProjectAttachId())){
			String[] ids = project.getProjectAttachId().split(",");
			if(ids!=null && ids.length>0){
				List<Attach> attachList = attachService.findByIds(ids);
				ServletActionContext.getRequest().setAttribute("attachList", attachList);
			}
		}
		
		Cookie[] cookies = servletRequest.getCookies();
		String userId="",userName="",deptId="",deptName="";
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("userId")){
				userId = cookies[i].getValue();
				continue;
			}
			if(cookies[i].getName().equals("userName")){
				userName = URLDecoder.decode(cookies[i].getValue(),"utf-8");
				continue;
			}
			if(cookies[i].getName().equals("deptId")){
				deptId = cookies[i].getValue();
				continue;
			}
			if(cookies[i].getName().equals("deptName")){
				deptName = URLDecoder.decode(cookies[i].getValue(),"utf-8");
				continue;
			}
		}
		servletRequest.setAttribute("userId", userId);
		servletRequest.setAttribute("userName", userName);
		servletRequest.setAttribute("deptId", deptId);
		servletRequest.setAttribute("deptName", deptName);
		
		List<CompanyRoute> routes = contractService.findAllLine();
		
		servletRequest.setAttribute("routes", routes);
		List<ContractStatus> planPay = new ArrayList<ContractStatus>();
		planPay = contractStatusService.findStatusList(id,"4");
		servletRequest.setAttribute("planPay", planPay);
		String tab = ServletActionContext.getRequest().getParameter("tab");
		if(tab!=null){
			servletRequest.setAttribute("tab", tab);
		}
		return "showView";
	}
	
	/**
	 * 显示修改页面
	 */
	public String showEdit(){
		String id = ServletActionContext.getRequest().getParameter("id");
		project = projectService.findProjectById(id);
		
		if(project!=null && project.getProjectAttachId()!=null && !"".equals(project.getProjectAttachId())){
			String[] ids = project.getProjectAttachId().split(",");
			if(ids!=null && ids.length>0){
				List<Attach> attachList = attachService.findByIds(ids);
				ServletActionContext.getRequest().setAttribute("attachList", attachList);
			}
		}
		return "showEdit";
	}
	
	/**
	 * 保存修改
	 */
	public String saveEdit(){
		
//		Project tempProject = projectService.findProjectById(project.getId());
//		BeanUtils.copyProperties(tempProject, project);
		
		String attachIds = ServletActionContext.getRequest().getParameter("attachIds");
		project.setProjectAttachId(attachIds);
		project.setOperateDate(sdf.format(new Date()));
		project.setProjectBudgetAll(getFormattedMoney(project.getProjectBudgetAll()));
		projectService.saveProject(project);
		
		contractService.updateProjectNoAndProjectNameByProjectId(project.getId(),project.getProjectNo(),project.getProjectName());
		
		
		return SUCCESS;
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		
		String id = ServletActionContext.getRequest().getParameter("id");
		Project tempProject = projectService.findProjectById(id);
		if(tempProject!=null){
			tempProject.setRemoved("1");
			projectService.update(tempProject);
		}
		return AJAX;
	}


	/**
	 * ajax查询,根据projectNo查询
	 */
	public String findProjectByProjectNoWithFuzzySearch(){
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		List<Project> list = projectService.findByProjectNoWithFuzzySearch(projectNo);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
		
	}
	
	
	/**
	 * ajax查询,根据projectName查询
	 */
	public String findProjectByProjectNameWithFuzzySearch(){
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		List<Project> list = projectService.findByProjectNameWithFuzzySearch(projectName);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
		
	}
	
	/**
	 * ajax查询,根据dispatchNo查询
	 */
	public String findProjectByDispatchNoWithFuzzySearch(){
		String dispatchNo = ServletActionContext.getRequest().getParameter("dispatchNo");
		List<Project> list = projectService.findByDispatchNoWithFuzzySearch(dispatchNo);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
		
	}
	
	
	public String findOwnerWithFuzzySearch() throws FileNotFoundException, IOException{
		Properties properties = loadProperties("config.properties");
		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String appName = properties.getProperty("appName");
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String secret = properties.getProperty("secret");//config.propertites中读取
		String dataParams = request.getParameter("dataParams");
		

		String serverUrl = properties.getProperty("urlCa").toString() + properties.getProperty("serverPath").toString();
		try {
			url = new URL(serverUrl + "/dataExchange");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			// http.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF-8");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + token
					+ "&method=" + method + "&dataParams=" + dataParams+ "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
			osw.write(param);
			osw.flush();
			osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						http.getInputStream(), "utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
				getServletResponse().setCharacterEncoding("utf-8");
				this.getServletResponse().getWriter().print(textEntity);
				return null;
			} else {
				return "没有通过用户认证";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (http != null)
				http.disconnect();
		}
		
		return AJAX;
	}
	
	
	public String findProjectByNameAndNo(){
		String projectName = servletRequest.getParameter("projectName");
		String projectNo = servletRequest.getParameter("projectNo");
		String projectId = servletRequest.getParameter("projectId");
		boolean nameStatus = projectService.isProjectNameRepeated(projectName,projectId);
		boolean noStatus =projectService.isProjectNoRepeated(projectNo,projectId);
		String feedbackInfo = ""; 
		String jsonData = "";
		
		if(nameStatus && noStatus){	//两个都重复了
			feedbackInfo = "{\"feedbackInfo\":\"项目名称和项目编号已存在，是否保存？\"}";
			jsonData = VOUtils.getJsonData(feedbackInfo);
			createJSonData(jsonData);
			return AJAX;
		}
		if(nameStatus){
			feedbackInfo = "{\"feedbackInfo\":\"项目名称已存在，是否保存？\"}";
			jsonData = VOUtils.getJsonData(feedbackInfo);
			createJSonData(jsonData);
			return AJAX;
		}
		if(noStatus){
			feedbackInfo = "{\"feedbackInfo\":\"项目编号已存在，是否保存？\"}";
			jsonData = VOUtils.getJsonData(feedbackInfo);
			createJSonData(jsonData);
			return AJAX;
		}
		return AJAX;
	}
	
	/**
	 * 显示项目封面
	 * @return
	 */
	public String showProjectCover(){
		//全部
		List<DwProjectCover> tempList = dwProjectCoverService.findByProjectTypeAndCompanyType("0", null);		
		if(tempList!=null && tempList.size()>0){
			servletRequest.setAttribute("all", tempList.get(0).getNumbers().longValue());
		}
		//内部项目-全部
		List<DwProjectCover> temp2List =  dwProjectCoverService.findByProjectTypeAndCompanyType("2", null); 
		if(temp2List!=null && temp2List.size()>0){
			servletRequest.setAttribute("inner", temp2List.get(0).getNumbers().longValue());
		}
		//外部项目-全部
		List<DwProjectCover> temp1List =  dwProjectCoverService.findByProjectTypeAndCompanyType("1", null); 
		if(temp1List!=null && temp1List.size()>0){
			servletRequest.setAttribute("out", temp1List.get(0).getNumbers().longValue());
		}
		//其他项目-全部
		List<DwProjectCover> temp4List =  dwProjectCoverService.findByProjectTypeAndCompanyType("3", null); 
		if(temp4List!=null && temp4List.size()>0){
			servletRequest.setAttribute("other", temp4List.get(0).getNumbers().longValue());
		}
		
		//内部项目-运营公司
		List<DwProjectCover> innerList1 = dwProjectCoverService.findByProjectTypeAndCompanyType("2", "2");
		servletRequest.setAttribute("innerList1", innerList1);
		//内部项目-维保中心
		List<DwProjectCover> innerList2 = dwProjectCoverService.findByProjectTypeAndCompanyType("2", "3");
		servletRequest.setAttribute("innerList2", innerList2);
		//内部项目-其他
		List<DwProjectCover> tempList5 = dwProjectCoverService.findByProjectTypeAndCompanyType("2", "-1");
		if(tempList5!=null && tempList5.size()>0){
			servletRequest.setAttribute("innerOther", tempList5.get(0).getNumbers());
		}
		//内部项目-运管中心,教培中心
		List<DwProjectCover> tempList6 = dwProjectCoverService.findByProjectTypeAndCompanyType("2", "4");
		if(tempList6!=null && tempList6.size()>0){
			servletRequest.setAttribute("ygCompany", tempList6.get(0));
		}
		List<DwProjectCover> eduList = dwProjectCoverService.findByProjectTypeAndCompanyType("2", "5");
		if(eduList!=null && eduList.size()>0){
			servletRequest.setAttribute("eduCompany", eduList.get(0));
		}
		
		//外部项目-项目公司
		List<DwProjectCover> outList = dwProjectCoverService.findByProjectTypeAndCompanyType("1", "1");
		servletRequest.setAttribute("outList", outList);
		//外部项目-其他
		List<DwProjectCover> tempList7 = dwProjectCoverService.findByProjectTypeAndCompanyType("1", "-1");
		if(tempList7!=null && tempList7.size()>0){
			servletRequest.setAttribute("outOther", tempList7.get(0).getNumbers());
		}
			
		return SUCCESS;
	}
	
	//读取配置文件
	public Properties loadProperties(String fileName){
		String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("配置文件没有找到！");
		} catch (IOException e) {
			System.out.println("读取配置文件失败！");
		}
		return props;
	}
	
	
	// 得到MD5加密后的字符串
	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}
	
	
	//得到保留6位小数后的字符串
	public String getFormattedMoney(String money){
		if(money==null || "".equals(money)){
			money = "0";
		}
		Double result = 0d;
		try {
			result = Double.valueOf(money);
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.000000");
		return df.format(result);
	}
	
	//根据项目公司的id找出所对应的线路
	public String getLineByCompanyId(){
		String jsonData = "{\"line\":[";
		String ids = this.servletRequest.getParameter("ids");
		String[] id = ids.split(",");
		String lines = "";
		for(int i=0;i<id.length;i++){
			lines = projectService.getLineByCompanyId(id[i]);
			if(lines==null){
				lines = "none";
			}
			if(i>0){
				jsonData += ",";
			}
			jsonData += "\""+lines+"\"";
		}
		jsonData += "]}";
		createJSonData(jsonData);
		return AJAX;
	}
	
	// 调用ca接口获得用户信息xml格式
	public String getUserInfoThroughCA(String appName, String token, String method, String secret, String dataType, String dataParams) {

		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";

		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();

		Properties properties2 = new Properties();
		try {
			properties2.load(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}

		String serverUrl = properties2.getProperty("urlCa").toString() + properties2.getProperty("serverPath").toString();

		try {
			url = new URL(serverUrl + "/dataExchange");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + token + "&method=" + method + "&dataType=" + dataType + "&dataParams=" + dataParams + "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
			osw.write(param);
			osw.flush();
			osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
			} else {
				return "没有通过用户认证";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (http != null)
				http.disconnect();
		}
		return textEntity;
	}
	
	
	/**
	 * ajax查询,根据projectNo,projectType查询
	 */
	public String findProjectByProjectNoAndTypeWithFuzzySearch(){
		String projectNo = ServletActionContext.getRequest().getParameter("projectNo");
		String type = ServletActionContext.getRequest().getParameter("projectType");
		List<Project> list = projectService.findByProjectNoWithFuzzySearch(projectNo,type);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
		
	}
	
	
	/**
	 * ajax查询,根据projectName,projectType查询
	 */
	public String findProjectByProjectNameAndTypeWithFuzzySearch(){
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String type = ServletActionContext.getRequest().getParameter("projectType");
		List<Project> list = projectService.findByProjectNameWithFuzzySearch(projectName,type);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
		
	}

	/**
	 * 合同管控信息提示
	 * 立项后合同签订超时提示
	 */
	public String showBusinessControlProjectOvertime(){
		Map<String, Object> filter = new HashMap<String, Object>();
		String key = null;
		String value = null;
		JSONObject obj = JSONObject.fromObject(this.project);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.project, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		String pageNo = servletRequest.getParameter("pageNo");
		String projectTypeSet = servletRequest.getParameter("projectTypeSet");
		projectTypeSet="2";
		String subSql = "t.id not in (select c.projectId from Contract c where c.contractType in ('2','2,1','2,2','2,3') and c.removed=0 and c.projectId is not null)";
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		if(StringUtils.isNotEmpty(projectTypeSet)){
			filter.put("projectTypeSet", projectTypeSet);
		}
		filter.put("removed", "0");
		filter.put("subSql", subSql);		//是否有sql条件子句
		Page page = projectService.findByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		return SUCCESS;
	}


	/**
 	* 查询列表页面
 * @throws DocumentException 
 * @throws UnsupportedEncodingException 
 */
	public String findProjectByPageToBeComplemented() throws DocumentException, UnsupportedEncodingException {
		String current = servletRequest.getParameter("current");
		String projectMoneysource = servletRequest.getParameter("projectMoneysource");
		if(projectMoneysource!=null && !"".equals(projectMoneysource)){
			projectMoneysource = URLDecoder.decode(projectMoneysource,"utf-8");
			project.setProjectMoneysource(projectMoneysource);
		}
		
		
		String onLineProjectAdddeptId = "";
		Cookie[] cookies = servletRequest.getCookies();
		String userId="",token="",appName="",secret="";
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("userId")){
				userId = cookies[i].getValue();
				continue;
			}
			if(cookies[i].getName().equals("token")){
				token = cookies[i].getValue();
				continue;
			}
			
		}
		Properties properties = loadProperties("config.properties");
		secret = properties.getProperty("secret");
		appName = properties.getProperty("appName");
		String dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><userId>" + userId + "</userId></params>";
		
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getMatchedDeptUsersById",secret,"xml",dataParams);	//
		if(usePersonXMl!=null && !"".equals(usePersonXMl)){
			Document document = DocumentHelper.parseText(usePersonXMl);
			Element rootElem = document.getRootElement();
			
			Node userIdNode = rootElem.selectSingleNode("//deptId");
			if(userIdNode!=null){
				String dataParams2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + userIdNode.getText() + "</id></params>";
				String pidXml = getUserInfoThroughCA(appName,token,"getNodesInfo",secret,"xml",dataParams2);
				document = DocumentHelper.parseText(pidXml);
				rootElem = document.getRootElement();
				Node deptNode = rootElem.selectSingleNode("//pid");
				String companyId = deptNode.getText();
				String companyName = companyMap.get(companyId);
				if(companyName!=null && !"".equals(companyName)){	//在该公司下
					onLineProjectAdddeptId = companyId;
					servletRequest.setAttribute("onLineProjectAdddeptId", onLineProjectAdddeptId);
				}
			}
		}
		
		String nomodify = servletRequest.getParameter("nomodify");
		servletRequest.setAttribute("nomodify", nomodify);
		
		String projectEndtimePlanDateStart = servletRequest.getParameter("projectEndtimePlanDateStart");
		String projectStarttimePlanDateEnd = servletRequest.getParameter("projectStarttimePlanDateEnd");
		String approvalDateEnd = servletRequest.getParameter("approvalDateEnd");
		servletRequest.setAttribute("approvalDateEnd", approvalDateEnd);
		servletRequest.setAttribute("projectEndtimePlanDateStart", projectEndtimePlanDateStart);
		servletRequest.setAttribute("projectStarttimePlanDateEnd", projectStarttimePlanDateEnd);
		String pageNo = servletRequest.getParameter("pageNo");
		String companyId = ServletActionContext.getRequest().getParameter("companyId");
		servletRequest.setAttribute("companyId", companyId);
		String pType = servletRequest.getParameter("pType");
		servletRequest.setAttribute("pType", pType);
		if(companyId!=null && !"".equals(companyId)){
			if(!"center".equals(companyId)){
				project.setProjectAdddeptId(companyId);
			}
		}
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		
		String companyType = ServletActionContext.getRequest().getParameter("companyType");
		ServletActionContext.getRequest().setAttribute("companyType", companyType);
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> typeAllIdList = new ArrayList<String>();
		
		List<String> type1IdList = new ArrayList<String>();
		List<String> type2IdList= new ArrayList<String>();
		List<String> type3IdList= new ArrayList<String>();
		
		dept1TreeList.add(deptTreeService.findById(type1));
		dept2TreeList.add(deptTreeService.findById(type2));
		dept3TreeList.add(deptTreeService.findById(type3));
		
		//1.先根据父节点得到所有子节点的id(deptTree)
		dept1TreeList.addAll(dept1TreeList.size(), deptTreeService.findByPid(type1));
		dept2TreeList.addAll(dept2TreeList.size(), deptTreeService.findByPid(type2));
		dept3TreeList.addAll(dept3TreeList.size(), deptTreeService.findByPid(type3));
		
		if(pType!=null && !"".equals(pType)){
			List<String> type1List = new ArrayList<String>();
			List<String> type2List = new ArrayList<String>();
			List<String> type3List = new ArrayList<String>();
			/**项目公司数据**/
			if(dept1TreeList!=null && dept1TreeList.size()>0){
				int type1CountAll = 0;
				
				List<String[]> resultList1 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					for(int i=0; i<dept1TreeList.size(); i++){
						type1List.add(dept1TreeList.get(i).getId());
					}
					type1IdList.add(onLineProjectAdddeptId);
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						type1IdList.add(dept1TreeList.get(i).getId());
						type1List.add(dept1TreeList.get(i).getId());
					}
				}
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type1IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept1TreeList.size(); i++){
						if(typeMap.get(dept1TreeList.get(i).getId())!=null){
							resultList1.add(new String[]{dept1TreeList.get(i).getName(),typeMap.get(dept1TreeList.get(i).getId()),dept1TreeList.get(i).getId()});
							type1CountAll += Integer.valueOf(typeMap.get(dept1TreeList.get(i).getId()));
						}else{
							resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						resultList1.add(new String[]{dept1TreeList.get(i).getName(),"0",dept1TreeList.get(i).getId()});
					}
				}
				ServletActionContext.getRequest().setAttribute("type1CountAll", type1CountAll);
				ServletActionContext.getRequest().setAttribute("resultList1", resultList1);
			}
			/**运营公司数据**/
			if(dept2TreeList!=null && dept2TreeList.size()>0){
				int type2CountAll = 0;
				List<String[]> resultList2 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					type2IdList.add(onLineProjectAdddeptId);
					for(int i=0; i<dept2TreeList.size(); i++){
						type2List.add(dept2TreeList.get(i).getId());
					}
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						type2IdList.add(dept2TreeList.get(i).getId());
						type2List.add(dept2TreeList.get(i).getId());
					}
				}
				
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type2IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept2TreeList.size(); i++){
						if(typeMap.get(dept2TreeList.get(i).getId())!=null){
							resultList2.add(new String[]{dept2TreeList.get(i).getName(),typeMap.get(dept2TreeList.get(i).getId()),dept2TreeList.get(i).getId()});
							type2CountAll += Integer.valueOf(typeMap.get(dept2TreeList.get(i).getId()));
						}else{
							resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						resultList2.add(new String[]{dept2TreeList.get(i).getName(),"0",dept2TreeList.get(i).getId()});
					}
				}
				
				ServletActionContext.getRequest().setAttribute("type2CountAll", type2CountAll);
				ServletActionContext.getRequest().setAttribute("resultList2", resultList2);
			}
			/**维保中心数据**/
			if(dept3TreeList!=null && dept3TreeList.size()>0){
				int type3CountAll = 0;
				List<String[]> resultList3 = new ArrayList<String[]>();
				if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
					for(int i=0; i<dept3TreeList.size(); i++){
						type3List.add(dept3TreeList.get(i).getId());
					}
					type3IdList.add(onLineProjectAdddeptId);
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						type3IdList.add(dept3TreeList.get(i).getId());
						type3List.add(dept3TreeList.get(i).getId());
					}
				}
				
				Map<String, String> typeMap = projectService.findCountGroupByAddDeptIds(type3IdList,pType);
				if(typeMap!=null && typeMap.size()>0){
					for(int i=0; i<dept3TreeList.size(); i++){
						if(typeMap.get(dept3TreeList.get(i).getId())!=null){
							resultList3.add(new String[]{dept3TreeList.get(i).getName(),typeMap.get(dept3TreeList.get(i).getId()),dept3TreeList.get(i).getId()});
							type3CountAll += Integer.valueOf(typeMap.get(dept3TreeList.get(i).getId()));
						}else{
							resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
						}
					}
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						resultList3.add(new String[]{dept3TreeList.get(i).getName(),"0",dept3TreeList.get(i).getId()});
					}
				}
				ServletActionContext.getRequest().setAttribute("type3CountAll", type3CountAll);
				ServletActionContext.getRequest().setAttribute("resultList3", resultList3);
			}
			
			Long controlCenterAll = projectService.findSumOfProjectByAddDeptIdAndType("2540","0"); //运营管理中心,id=2540
			String [] controlCenter = {"运管中心",controlCenterAll.toString(),"2540"};
			servletRequest.setAttribute("controlCenter", controlCenter);
			
			//其他项目数据
			Long othterProject = 0l;
			if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
				othterProject = projectService.findSumOfProjectByAddDeptIdAndType(onLineProjectAdddeptId, "5");
			}else{
				othterProject = projectService.findSumOfProjectByAddDeptIdAndType(null, "5");
			}
			servletRequest.setAttribute("othterProject", othterProject);
			
			//内部项目其他数据
			List<String> controlAndMaintainIdList = new ArrayList<String>();
			controlAndMaintainIdList.addAll(type2List);
			controlAndMaintainIdList.addAll(type3List);
			controlAndMaintainIdList.add("2540");	//运营中心
			controlAndMaintainIdList.add("2545");	//维护保障中心
			Long innerOther = 0l;
			if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
				innerOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("0", controlAndMaintainIdList);
			}else{
				innerOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("0", controlAndMaintainIdList);
			}
			servletRequest.setAttribute("innerOther", innerOther );
			//外部项目其他数据
			Long outOther = projectService.findSumOfProjectByTypeWithoutAdddeptId("1", type1List);
			servletRequest.setAttribute("outOther", outOther );
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		
		JSONObject obj = JSONObject.fromObject(this.project);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.project, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(companyId!=null && !"".equals(companyId)){
			if(!"center".equals(companyId)){
				filter.put("projectAdddeptId", companyId);
			}
		}
		filter.put("removed", "0");
		if(pType!=null && !"".equals(pType) && (project.getProjectType()==null || "".equals(project.getProjectType()))){
			filter.put("projectType", pType);
		}
		if(onLineProjectAdddeptId!=null && !"".equals(onLineProjectAdddeptId)){
			filter.put("projectAdddeptId", onLineProjectAdddeptId);
		}
		if(filter.get("projectAdddeptId")!=null && filter.get("projectAdddeptId").equals("2540")){ //点击查询的是运营管理中心
			typeAllIdList = null;
		}
		String findType = servletRequest.getParameter("findType");
		servletRequest.setAttribute("findType", findType);
		if(projectEndtimePlanDateStart!=null && !"".equals(projectEndtimePlanDateStart)){
			filter.put("projectEndtimePlanDateStart", projectEndtimePlanDateStart);
		}
		if(projectStarttimePlanDateEnd!=null && !"".equals(projectStarttimePlanDateEnd)){
			filter.put("projectStarttimePlanDateEnd", projectStarttimePlanDateEnd);
		}
		if(approvalDateEnd!=null && !"".equals(approvalDateEnd)){
			filter.put("approvalDateEnd", approvalDateEnd);
		}
		String complemented = servletRequest.getParameter("complemented");
		if(current!=null && !"".equals(current)){
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf2.parse(current));
			} catch (ParseException e) {
				System.out.println("error");
				e.printStackTrace();
			}
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			filter.put("approvalDate",sdf2.format(c.getTime()));
			filter.put("approvalDateEnd",current);
			
			project.setApprovalDate(sdf2.format(c.getTime()));
			servletRequest.setAttribute("approvalDateEnd", current);
			ServletActionContext.getRequest().setAttribute("showOrHide", "show");
		}else{
			if(complemented==null || !"no".equals(complemented)){
				filter.put("complemented", "yes");
			}
		}
		servletRequest.setAttribute("complemented", complemented);
		
		Page page = null;
		String export = servletRequest.getParameter("export");
		if(export!=null && "yes".equals(export)){
			size = 20000;
			pageNo = "1";
		}
		if(findType!=null && "notin".equals(findType)){
			List<String> controlAndMaintainIdList = new ArrayList<String>(); 
			if(pType!=null && pType.equals("0")){  //内部项目
				controlAndMaintainIdList.addAll(type2IdList);
				controlAndMaintainIdList.addAll(type3IdList);
				controlAndMaintainIdList.add("2540");
				page = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), size,controlAndMaintainIdList);
			}else if(pType!=null && pType.equals("1")){
				controlAndMaintainIdList.addAll(type1IdList);
			}
			typeAllIdList.add("2540");
			page = projectService.findProjectByPageWithoutAdddeptList(filter, Integer.valueOf(pageNo), size,controlAndMaintainIdList);
		}else{
			if(filter.get("projectType")!=null && filter.get("projectType").equals("5")){
				page = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), size,null);
			}else{
				page = projectService.findProjectByPage(filter, Integer.valueOf(pageNo), size,typeAllIdList);
			}
		}
		
		//数据送到前台
		ServletActionContext.getRequest().setAttribute("page", page);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=projectExport.xls"); 
			
			List<Project> list = page.getResult();
			for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[14];
				params[0] = list.get(i).getProjectName();
				params[1] = list.get(i).getProjectType();
				if (list.get(i).getProjectType() != null) {
					if (list.get(i).getProjectType().equals("1")) {
						params[1] = "外部项目";
					} else if (list.get(i).getProjectType().equals("2")) {
						params[1] = "内部项目资本类";
					} else if (list.get(i).getProjectType().equals("3")) {
						params[1] = "内部项目大修类";
					} else if (list.get(i).getProjectType().equals("4")) {
						params[1] = "内部项目能源类";
					}else if(list.get(i).getProjectType().equals("5")){
						params[1] = "其他类";
					}
				} else {
					params[1] = null;
				}
				params[2] = list.get(i).getProjectNo();
				params[3] = list.get(i).getProjectBudgetAll();
				params[4] = list.get(i).getProjectCompany();
				params[5] = list.get(i).getKeyword();
				params[6] = list.get(i).getProjectAdddept();
				params[7] = list.get(i).getProjectAddperson();
				// params[8] = list.get(i).getProfessionalType();
				if (list.get(i).getProfessionalType() != null) {
					if (list.get(i).getProfessionalType().equals("1")) {
						params[8] = "车辆";
					} else if (list.get(i).getProfessionalType().equals("2")) {
						params[8] = "供电";
					} else if (list.get(i).getProfessionalType().equals("3")) {
						params[8] = "通号";
					} else if (list.get(i).getProfessionalType().equals("4")) {
						params[8] = "工务";
					} else if (list.get(i).getProfessionalType().equals("5")) {
						params[8] = "基地";
					} else if (list.get(i).getProfessionalType().equals("6")) {
						params[8] = "车站机电";
					}
				} else {
					params[8] = null;
				}
				// params[9] = list.get(i).getProjectState();
				if (list.get(i).getProjectState() != null) {
					if (list.get(i).getProjectState().equals("1")) {
						params[9] = "项目准备";
					} else if (list.get(i).getProjectState().equals("2")) {
						params[9] = "项目实施";
					} else if (list.get(i).getProjectState().equals("3")) {
						params[9] = "销项完成";
					} else if (list.get(i).getProjectState().equals("4")) {
						params[9] = "项目采购";
					} else if (list.get(i).getProjectState().equals("5")) {
						params[9] = "合同审批";
					} else if (list.get(i).getProjectState().equals("6")) {
						params[9] = "合同执行";
					} else if (list.get(i).getProjectState().equals("7")) {
						params[9] = "合同结算";
					} else if (list.get(i).getProjectState().equals("8")) {
						params[9] = "项目结算";
					}
				} else {
					params[9] = null;
				}
				params[10] = list.get(i).getProjectStarttimePlanDate();
				params[11] = list.get(i).getProjectEndtimePlanDate();
				params[12] = list.get(i).getDispatchNo();
				// params[13] = list.get(i).getSubstituteConstruction();
				if (list.get(i).getSubstituteConstruction() != null) {
					if (list.get(i).getSubstituteConstruction().equals("1")) {
						params[13] = "是";
					} else if (list.get(i).getSubstituteConstruction().equals(
							"2")) {
						params[13] = "否";
					}
				} else {
					params[13] = null;
				}

				dataset.add(params);
			}
			
			String[] headers = {"项目名称","项目类型","项目编号","投资估算总额(万元)","项目公司","关键字","负责部门","负责人","专业分类","项目状态","计划开始时间","计划完成时间","发文编号","是否代建"};
			short[] width = {12000,4000,8000,6000,8000,4000,8000,3000,3000,3000,4000,4000,6000,3000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		
		
		
		
		
		
		
		
		
		
		return SUCCESS;
	}


	/**
	 * 根据条件查询
	 * @return
	 */
	public String findByCondition(){
		//合同类型1是建设类，对应项目类型1外部项目；合同类型2是运维类，对应的项目类型2,3,4内部项目
		String projectType = servletRequest.getParameter("projectType");servletRequest.setAttribute("projectType", projectType);	//接收参数: '1,' ,  '2,'  ,  '3,'
		String companyId = servletRequest.getParameter("companyId");servletRequest.setAttribute("companyId", companyId);
		String condition = servletRequest.getParameter("condition"); servletRequest.setAttribute("condition", condition);
		String pageNo = servletRequest.getParameter("pageNo");
		String showOrHide = servletRequest.getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide)){
			showOrHide = "hide";
		}
		servletRequest.setAttribute("showOrHide", showOrHide);
		String approvalDateEnd = servletRequest.getParameter("approvalDateEnd");
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(StringUtils.isNotEmpty(companyId)){
			if(!"center".equals(companyId)){
				filter.put("projectAdddeptId", companyId);
			}
		}
		if(StringUtils.isNotEmpty(projectType)){
			filter.put("projectType", projectType);
		}
		if(StringUtils.isNotEmpty(project.getProjectNo())){
			filter.put("projectNo",project.getProjectNo());
		}
		if(StringUtils.isNotEmpty(project.getProjectName())){
			filter.put("projectName",project.getProjectName());
		}
		if(StringUtils.isNotEmpty(project.getDispatchNo())){
			filter.put("dispatchNo",project.getDispatchNo());
		}
		if(StringUtils.isNotEmpty(project.getProjectAdddept())){
			filter.put("projectAdddept",project.getProjectAdddept());
		}
		if(StringUtils.isNotEmpty(project.getProfessionalType())){
			filter.put("professionalType",project.getProfessionalType());
		}
		if(StringUtils.isNotEmpty(project.getApprovalDate())){
			filter.put("approvalDate",project.getApprovalDate());
		}
		if(StringUtils.isNotEmpty(approvalDateEnd)){
			filter.put("approvalDateEnd",approvalDateEnd);
			servletRequest.setAttribute("approvalDateEnd", approvalDateEnd);
		}
		
		
		
		//1.根据项目类型查询结果集阶段,
		//List<Project> firstResult = projectService.findByProjectTypeAndProjectAddeptId(projectType,companyId);
		List<Project> firstResult = projectService.findByFilter(filter);
		
		
		List<Project> secondResult  = new ArrayList<Project>();;
		List<Project> finalResult = new ArrayList<Project>();
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		//2.处理condition阶段
		if(condition!=null && !"".equals(condition)){
			if(firstResult!=null && firstResult.size()>0){
				
				if("condition1".equals(condition)){		//立项后合同签订时间
					
					secondResult = projectService.findByCondition(condition,companyId,filter);
				}else if(("condition2").equals(condition)){	//合同变更超额告警提示
					for(int i=0; i<firstResult.size(); i++){
						Project temp = firstResult.get(i);
						if(temp.getProjectType().equals("4") && (temp.getProjectBudgetAll()!=null && Double.valueOf(temp.getProjectBudgetAll())==0.0)){
							continue;
						}else{
							String sql ="select sum(c.contract_price) from C_CONTRACT c where c.removed='0' and c.project_id='"+temp.getId()+"' and c.contract_type in ('2','2,1','2,2','2,3')";
							String contractPriceAll = projectService.findOneData(sql);
							if(contractPriceAll!=null && !"".equals(contractPriceAll) && !"0".equals(condition)){		//该项目下有合同，并且所有的合同价格不为0
								if(Double.valueOf(contractPriceAll) > Double.valueOf(temp.getProjectBudgetAll())){
									secondResult.add(temp);
								}
							}
						}
					}
				}
			}
		}
		if(secondResult!=null && secondResult.size()>0){
			totalSize  = secondResult.size();
			if(secondResult.size() % pSize==0){
				totalPageCount = secondResult.size() / pSize;
			}else{
				totalPageCount = secondResult.size() / pSize + 1;
			}
		}
		
		
		
		//3.分页阶段
		if(pageNo==null || "".equals(pageNo)){	//没有页码，加入1-20条数据
			if(secondResult.size()>pSize){	//超过20条数据
				for(int i=0; i<pSize; i++){
					finalResult.add(secondResult.get(i));
				}
			}else{
				finalResult = secondResult;
			}
		}else{		//有页码
			if(Integer.valueOf(pageNo)!=1){
				begin = Integer.valueOf(pageNo)*pSize-pSize;
			}
			if(Integer.valueOf(pageNo)*pSize <= secondResult.size()){		//结果集包含了要查询的集
				for(int i=begin; i<begin+pSize; i++){
					finalResult.add(secondResult.get(i));
				}
			}else{
				for(int i=begin; i<secondResult.size(); i++){
					finalResult.add(secondResult.get(i));
				}
			}
		}
		
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		
		servletRequest.setAttribute("pSize", pSize);
		servletRequest.setAttribute("pageNo", pageNo);
		servletRequest.setAttribute("totalSize", totalSize);
		servletRequest.setAttribute("totalPageCount", totalPageCount);
		servletRequest.setAttribute("begin", begin);
		servletRequest.setAttribute("list", finalResult);
		
		
		String export = servletRequest.getParameter("export");
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=projectExport.xls"); 
			
			List<Project> list = secondResult;
			for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[14];
				params[0] = list.get(i).getProjectName();
				params[1] = list.get(i).getProjectType();
				if (list.get(i).getProjectType() != null) {
					if (list.get(i).getProjectType().equals("1")) {
						params[1] = "外部项目";
					} else if (list.get(i).getProjectType().equals("2")) {
						params[1] = "内部项目资本类";
					} else if (list.get(i).getProjectType().equals("3")) {
						params[1] = "内部项目大修类";
					} else if (list.get(i).getProjectType().equals("4")) {
						params[1] = "内部项目能源类";
					}else if(list.get(i).getProjectType().equals("5")){
						params[1] = "其他类";
					}
				} else {
					params[1] = null;
				}
				params[2] = list.get(i).getProjectNo();
				params[3] = list.get(i).getProjectBudgetAll();
				params[4] = list.get(i).getProjectCompany();
				params[5] = list.get(i).getKeyword();
				params[6] = list.get(i).getProjectAdddept();
				params[7] = list.get(i).getProjectAddperson();
				// params[8] = list.get(i).getProfessionalType();
				if (list.get(i).getProfessionalType() != null) {
					if (list.get(i).getProfessionalType().equals("1")) {
						params[8] = "车辆";
					} else if (list.get(i).getProfessionalType().equals("2")) {
						params[8] = "供电";
					} else if (list.get(i).getProfessionalType().equals("3")) {
						params[8] = "通号";
					} else if (list.get(i).getProfessionalType().equals("4")) {
						params[8] = "工务";
					} else if (list.get(i).getProfessionalType().equals("5")) {
						params[8] = "基地";
					} else if (list.get(i).getProfessionalType().equals("6")) {
						params[8] = "车站机电";
					}
				} else {
					params[8] = null;
				}
				// params[9] = list.get(i).getProjectState();
				if (list.get(i).getProjectState() != null) {
					if (list.get(i).getProjectState().equals("1")) {
						params[9] = "项目准备";
					} else if (list.get(i).getProjectState().equals("2")) {
						params[9] = "项目实施";
					} else if (list.get(i).getProjectState().equals("3")) {
						params[9] = "销项完成";
					} else if (list.get(i).getProjectState().equals("4")) {
						params[9] = "项目采购";
					} else if (list.get(i).getProjectState().equals("5")) {
						params[9] = "合同审批";
					} else if (list.get(i).getProjectState().equals("6")) {
						params[9] = "合同执行";
					} else if (list.get(i).getProjectState().equals("7")) {
						params[9] = "合同结算";
					} else if (list.get(i).getProjectState().equals("8")) {
						params[9] = "项目结算";
					}
				} else {
					params[9] = null;
				}
				params[10] = list.get(i).getProjectStarttimePlanDate();
				params[11] = list.get(i).getProjectEndtimePlanDate();
				params[12] = list.get(i).getDispatchNo();
				// params[13] = list.get(i).getSubstituteConstruction();
				if (list.get(i).getSubstituteConstruction() != null) {
					if (list.get(i).getSubstituteConstruction().equals("1")) {
						params[13] = "是";
					} else if (list.get(i).getSubstituteConstruction().equals(
							"2")) {
						params[13] = "否";
					}
				} else {
					params[13] = null;
				}

				dataset.add(params);
			}
			
			String[] headers = {"项目名称","项目类型","项目编号","投资估算总额(万元)","项目公司","关键字","负责部门","负责人","专业分类","项目状态","计划开始时间","计划完成时间","发文编号","是否代建"};
			short[] width = {12000,4000,8000,6000,8000,4000,8000,3000,3000,3000,4000,4000,6000,3000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return SUCCESS;
	}
	
	public String findByNewCondition(){
		String condition = servletRequest.getParameter("condition");
		String companyId = servletRequest.getParameter("companyId");
		servletRequest.setAttribute("condition", condition);
		String pageNo = servletRequest.getParameter("pageNo");
		String approvalDateEnd = servletRequest.getParameter("approvalDateEnd");
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		String hql="";
		String sql="";
		String projectFilter="";
		String sqlFilter="";
		
		if(StringUtils.isNotEmpty(companyId)){
			projectFilter += " and p.projectAdddeptId='"+companyId+"'";
			sqlFilter += " and p.project_adddept_id='"+companyId+"'";
		}
		if(StringUtils.isNotEmpty(project.getProjectNo())){
			projectFilter+=" and p.projectNo like '%"+project.getProjectNo()+"%'";
			sqlFilter+=" and p.project_no like '%"+project.getProjectNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProjectName())){
			projectFilter +=" and p.projectName like '%"+project.getProjectName()+"%'";
			sqlFilter +=" and p.project_name like '%"+project.getProjectNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getDispatchNo())){
			projectFilter +=" and p.dispatchNo like '%"+project.getDispatchNo()+"%'";
			sqlFilter +=" and p.dispatch_no like '%"+project.getDispatchNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProjectAdddept())){
			projectFilter +=" and p.projectAdddept like '%"+project.getProjectAdddept()+"%'";
			sqlFilter +=" and p.project_adddept like '%"+project.getProjectAdddept()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProfessionalType())){
			projectFilter +=" and p.professionalType='"+project.getProfessionalType()+"'";
			sqlFilter +=" and p.professional_type='"+project.getProfessionalType()+"'";
		}
		if(StringUtils.isNotEmpty(project.getApprovalDate())){
			projectFilter +=" and p.approvalDate >='"+project.getApprovalDate()+"'";
			sqlFilter +=" and p.approval_date >='"+project.getApprovalDate()+"'";
		}
		if(StringUtils.isNotEmpty(approvalDateEnd)){
			servletRequest.setAttribute("approvalDateEnd", approvalDateEnd);
			projectFilter += " and p.approvalDate <='"+approvalDateEnd+"'";
			sqlFilter += " and p.approval_date <='"+approvalDateEnd+"'";
		}
		
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "1";
		}
		List<Project> list  = null;
		begin = (Integer.valueOf(pageNo)-1)*pSize;
		if("condition1".equals(condition)){
			hql = "from Project p where p.id in (" 
				+"select p.id from Project p,Contract c where p.removed=0"
				+ " and p.id = c.projectId  and c.removed=0"
				+ " and p.approvalDate is not null and c.contractSignedDate is not null"
				+ " and to_date(p.approvalDate,'yyyy-mm-dd') < (to_date(c.contractSignedDate,'yyyy-mm-dd')-120)"
				+ " and p.id not in ("
				+ "select distinct p.id from Project p,Contract c where p.removed=0"
				+ " and p.id = c.projectId and c.removed=0 and c.projectId is not null"
				+ " and p.approvalDate is not null and c.contractSignedDate is not null"
				+ " and to_date(p.approvalDate,'yyyy-mm-dd') >= (to_date(c.contractSignedDate,'yyyy-mm-dd')-120))"
				+ " group by p.id)";
			sql="select count(count(distinct p.id)) from c_project p,c_contract c where p.removed=0"
				+ " and p.id = c.project_id  and c.removed=0"
				+ " and p.approval_date is not null and c.contract_signed_date is not null"
				+ " and to_date(p.approval_date,'yyyy-mm-dd') < (to_date(c.contract_signed_date,'yyyy-mm-dd')-120)"
				+ " and p.id not in ("
				+ "select distinct p.id from c_project p,c_contract c where p.removed=0"
				+ " and p.id = c.project_id and c.removed=0 and c.project_id is not null"
				+ " and p.approval_date is not null and c.contract_signed_date is not null"
				+ " and to_date(p.approval_date,'yyyy-mm-dd') >= (to_date(c.contract_signed_date,'yyyy-mm-dd')-120))";
		}else if("condition2".equals(condition)){
			String sql2="select c.project_id,sum(c.contract_price) from c_contract c where c.removed='0' "
				+ " and c.project_id in (select t.id from c_project t where t.removed =0 and t.project_budget_all!=0) "
				+ " group by c.project_id";
			String hql2="";
			List<Object[]> idPriceList = projectService.findBySQL(sql2);
			if(idPriceList!=null && idPriceList.size()>0) {
				list = new ArrayList<Project>();
				for(int i=0;i<idPriceList.size();i++){
					String id = idPriceList.get(i)[0].toString();
					hql2 ="from Project p where p.id='"+idPriceList.get(i)[0].toString()+"' and to_number(p.projectBudgetAll)<"+idPriceList.get(i)[1].toString();
					if(StringUtils.isNotEmpty(projectFilter)){
						hql2+=projectFilter;
					}
					list.addAll(projectService.findProjectByHql(hql2, 0, 1));
				}
			}
		}
		if(StringUtils.isNotEmpty(projectFilter)){
			hql += projectFilter;
			sql += sqlFilter;
		}
		if("condition1".equals(condition)){
			sql+=" group by p.id";
			list = projectService.findProjectByHql(hql, begin, pSize);
			totalSize = projectService.findCountBySql(sql);
		}else if("condition2".equals(condition)){
			totalSize = list.size();
		}
		
		
		servletRequest.setAttribute("list", list);
		
		
		if(totalSize % pSize==0){
			totalPageCount = totalSize / pSize;
		}else{
			totalPageCount =totalSize / pSize + 1;
		}
		
		
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		
		servletRequest.setAttribute("pSize", pSize);
		servletRequest.setAttribute("pageNo", pageNo);
		servletRequest.setAttribute("totalSize", totalSize);
		servletRequest.setAttribute("totalPageCount", totalPageCount);
		servletRequest.setAttribute("begin", begin);
		servletRequest.setAttribute("list", list);
		
		
		//数据送到前台
		//ServletActionContext.getRequest().setAttribute("page", page);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		
		String export = servletRequest.getParameter("export");
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=projectExport.xls"); 
			
			for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[14];
				params[0] = list.get(i).getProjectName();
				params[1] = list.get(i).getProjectType();
				if (list.get(i).getProjectType() != null) {
					if (list.get(i).getProjectType().equals("1")) {
						params[1] = "外部项目";
					} else if (list.get(i).getProjectType().equals("2")) {
						params[1] = "内部项目资本类";
					} else if (list.get(i).getProjectType().equals("3")) {
						params[1] = "内部项目大修类";
					} else if (list.get(i).getProjectType().equals("4")) {
						params[1] = "内部项目能源类";
					}else if(list.get(i).getProjectType().equals("5")){
						params[1] = "其他类";
					}
				} else {
					params[1] = null;
				}
				params[2] = list.get(i).getProjectNo();
				params[3] = list.get(i).getProjectBudgetAll();
				params[4] = list.get(i).getProjectCompany();
				params[5] = list.get(i).getKeyword();
				params[6] = list.get(i).getProjectAdddept();
				params[7] = list.get(i).getProjectAddperson();
				// params[8] = list.get(i).getProfessionalType();
				if (list.get(i).getProfessionalType() != null) {
					if (list.get(i).getProfessionalType().equals("1")) {
						params[8] = "车辆";
					} else if (list.get(i).getProfessionalType().equals("2")) {
						params[8] = "供电";
					} else if (list.get(i).getProfessionalType().equals("3")) {
						params[8] = "通号";
					} else if (list.get(i).getProfessionalType().equals("4")) {
						params[8] = "工务";
					} else if (list.get(i).getProfessionalType().equals("5")) {
						params[8] = "基地";
					} else if (list.get(i).getProfessionalType().equals("6")) {
						params[8] = "车站机电";
					}
				} else {
					params[8] = null;
				}
				// params[9] = list.get(i).getProjectState();
				if (list.get(i).getProjectState() != null) {
					if (list.get(i).getProjectState().equals("1")) {
						params[9] = "项目准备";
					} else if (list.get(i).getProjectState().equals("2")) {
						params[9] = "项目实施";
					} else if (list.get(i).getProjectState().equals("3")) {
						params[9] = "销项完成";
					} else if (list.get(i).getProjectState().equals("4")) {
						params[9] = "项目采购";
					} else if (list.get(i).getProjectState().equals("5")) {
						params[9] = "合同审批";
					} else if (list.get(i).getProjectState().equals("6")) {
						params[9] = "合同执行";
					} else if (list.get(i).getProjectState().equals("7")) {
						params[9] = "合同结算";
					} else if (list.get(i).getProjectState().equals("8")) {
						params[9] = "项目结算";
					}
				} else {
					params[9] = null;
				}
				params[10] = list.get(i).getProjectStarttimePlanDate();
				params[11] = list.get(i).getProjectEndtimePlanDate();
				params[12] = list.get(i).getDispatchNo();
				// params[13] = list.get(i).getSubstituteConstruction();
				if (list.get(i).getSubstituteConstruction() != null) {
					if (list.get(i).getSubstituteConstruction().equals("1")) {
						params[13] = "是";
					} else if (list.get(i).getSubstituteConstruction().equals(
							"2")) {
						params[13] = "否";
					}
				} else {
					params[13] = null;
				}

				dataset.add(params);
			}
			
			String[] headers = {"项目名称","项目类型","项目编号","投资估算总额(万元)","项目公司","关键字","负责部门","负责人","专业分类","项目状态","计划开始时间","计划完成时间","发文编号","是否代建"};
			short[] width = {12000,4000,8000,6000,8000,4000,8000,3000,3000,3000,4000,4000,6000,3000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return SUCCESS;
	}

	
	public String findNewProjectByPageToBeComplemented(){
		String pageNo = servletRequest.getParameter("pageNo");
		String export = servletRequest.getParameter("export");
		String approvalDateEnd = servletRequest.getParameter("approvalDateEnd");
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		String hql="from Project p where p.removed=0 "
				+ " and (p.dispatchNo is null or  p.approvalDate is null "
				+ " or p.projectMoneysource is null or"
				+ " p.moneySourceType  is null or  p.professionalType is null )";
		String sql="select count(*) from c_project p where p.removed=0 "
				+ " and (p.dispatch_no is null or  p.approval_date is null "
				+ " or p.project_moneysource is null or"
				+ " p.money_source_type  is null or  p.professional_type is null )";
		String projectFilter="";
		String sqlFilter="";
		if(StringUtils.isNotEmpty(project.getProjectNo())){
			projectFilter+=" and p.projectNo like '%"+project.getProjectNo()+"%'";
			sqlFilter+=" and p.project_no like '%"+project.getProjectNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProjectName())){
			projectFilter +=" and p.projectName like '%"+project.getProjectName()+"%'";
			sqlFilter +=" and p.project_name like '%"+project.getProjectNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getDispatchNo())){
			projectFilter +=" and p.dispatchNo like '%"+project.getDispatchNo()+"%'";
			sqlFilter +=" and p.dispatch_no like '%"+project.getDispatchNo()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProjectAdddept())){
			projectFilter +=" and p.projectAdddept like '%"+project.getProjectAdddept()+"%'";
			sqlFilter +=" and p.project_adddept like '%"+project.getProjectAdddept()+"%'";
		}
		if(StringUtils.isNotEmpty(project.getProfessionalType())){
			projectFilter +=" and p.professionalType='"+project.getProfessionalType()+"'";
			sqlFilter +=" and p.professional_type='"+project.getProfessionalType()+"'";
		}
		if(StringUtils.isNotEmpty(project.getApprovalDate())){
			projectFilter +=" and p.approvalDate >='"+project.getApprovalDate()+"'";
			sqlFilter +=" and p.approval_date >='"+project.getApprovalDate()+"'";
		}
		if(StringUtils.isNotEmpty(approvalDateEnd)){
			servletRequest.setAttribute("approvalDateEnd", approvalDateEnd);
			projectFilter += " and p.approvalDate <='"+approvalDateEnd+"'";
			sqlFilter += " and p.approval_date <='"+approvalDateEnd+"'";
		}
		
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "1";
		}
		if(StringUtils.isNotEmpty(projectFilter)){
			hql += projectFilter;
			sql += sqlFilter;
		}
		
		begin = (Integer.valueOf(pageNo)-1)*pSize;
		List<Project> list = projectService.findProjectByHql(hql, begin, pSize);
		totalSize = projectService.findCountBySql(sql);
		servletRequest.setAttribute("list", list);
		
		
		if(totalSize % pSize==0){
			totalPageCount = totalSize / pSize;
		}else{
			totalPageCount =totalSize / pSize + 1;
		}
		
		
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}
		
		servletRequest.setAttribute("pSize", pSize);
		servletRequest.setAttribute("pageNo", pageNo);
		servletRequest.setAttribute("totalSize", totalSize);
		servletRequest.setAttribute("totalPageCount", totalPageCount);
		servletRequest.setAttribute("begin", begin);
		servletRequest.setAttribute("list", list);
		
		
		//数据送到前台
		//ServletActionContext.getRequest().setAttribute("page", page);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=projectExport.xls"); 
			
			for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[14];
				params[0] = list.get(i).getProjectName();
				params[1] = list.get(i).getProjectType();
				if (list.get(i).getProjectType() != null) {
					if (list.get(i).getProjectType().equals("1")) {
						params[1] = "外部项目";
					} else if (list.get(i).getProjectType().equals("2")) {
						params[1] = "内部项目资本类";
					} else if (list.get(i).getProjectType().equals("3")) {
						params[1] = "内部项目大修类";
					} else if (list.get(i).getProjectType().equals("4")) {
						params[1] = "内部项目能源类";
					}else if(list.get(i).getProjectType().equals("5")){
						params[1] = "其他类";
					}
				} else {
					params[1] = null;
				}
				params[2] = list.get(i).getProjectNo();
				params[3] = list.get(i).getProjectBudgetAll();
				params[4] = list.get(i).getProjectCompany();
				params[5] = list.get(i).getKeyword();
				params[6] = list.get(i).getProjectAdddept();
				params[7] = list.get(i).getProjectAddperson();
				// params[8] = list.get(i).getProfessionalType();
				if (list.get(i).getProfessionalType() != null) {
					if (list.get(i).getProfessionalType().equals("1")) {
						params[8] = "车辆";
					} else if (list.get(i).getProfessionalType().equals("2")) {
						params[8] = "供电";
					} else if (list.get(i).getProfessionalType().equals("3")) {
						params[8] = "通号";
					} else if (list.get(i).getProfessionalType().equals("4")) {
						params[8] = "工务";
					} else if (list.get(i).getProfessionalType().equals("5")) {
						params[8] = "基地";
					} else if (list.get(i).getProfessionalType().equals("6")) {
						params[8] = "车站机电";
					}
				} else {
					params[8] = null;
				}
				// params[9] = list.get(i).getProjectState();
				if (list.get(i).getProjectState() != null) {
					if (list.get(i).getProjectState().equals("1")) {
						params[9] = "项目准备";
					} else if (list.get(i).getProjectState().equals("2")) {
						params[9] = "项目实施";
					} else if (list.get(i).getProjectState().equals("3")) {
						params[9] = "销项完成";
					} else if (list.get(i).getProjectState().equals("4")) {
						params[9] = "项目采购";
					} else if (list.get(i).getProjectState().equals("5")) {
						params[9] = "合同审批";
					} else if (list.get(i).getProjectState().equals("6")) {
						params[9] = "合同执行";
					} else if (list.get(i).getProjectState().equals("7")) {
						params[9] = "合同结算";
					} else if (list.get(i).getProjectState().equals("8")) {
						params[9] = "项目结算";
					}
				} else {
					params[9] = null;
				}
				params[10] = list.get(i).getProjectStarttimePlanDate();
				params[11] = list.get(i).getProjectEndtimePlanDate();
				params[12] = list.get(i).getDispatchNo();
				// params[13] = list.get(i).getSubstituteConstruction();
				if (list.get(i).getSubstituteConstruction() != null) {
					if (list.get(i).getSubstituteConstruction().equals("1")) {
						params[13] = "是";
					} else if (list.get(i).getSubstituteConstruction().equals(
							"2")) {
						params[13] = "否";
					}
				} else {
					params[13] = null;
				}

				dataset.add(params);
			}
			
			String[] headers = {"项目名称","项目类型","项目编号","投资估算总额(万元)","项目公司","关键字","负责部门","负责人","专业分类","项目状态","计划开始时间","计划完成时间","发文编号","是否代建"};
			short[] width = {12000,4000,8000,6000,8000,4000,8000,3000,3000,3000,4000,4000,6000,3000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		return SUCCESS;
	}
	
	public String findProjectByPageToBeCancelled(){
		String pageNo = servletRequest.getParameter("pageNo");
		
		int currentPage = (StringUtils.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo));
		int pSize = 20;

		Page page = projectService.getProjects(project,currentPage,pSize);
		
		servletRequest.setAttribute("page", page);

		return SUCCESS;
	}	
}




