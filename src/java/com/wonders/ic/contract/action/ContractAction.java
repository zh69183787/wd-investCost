/** 
// * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.ic.contract.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.wonders.ic.contract.service.ShortMsgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.opensymphony.xwork2.Action;
import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.entity.bo.CurrentLogin;
import com.wonders.ic.contract.entity.bo.KpiClear;
import com.wonders.ic.contract.entity.bo.KpiClearStatus;
import com.wonders.ic.contract.service.CaRestAPIService;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.contractStatus.service.ContractStatusService;
import com.wonders.ic.deptTree.entity.bo.DeptTree;
import com.wonders.ic.deptTree.service.DeptTreeService;
import com.wonders.ic.dwContract.entity.bo.DwContractCover;
import com.wonders.ic.dwContract.service.DwContractCoverService;
import com.wonders.ic.export.ExportExcel;
import com.wonders.ic.investEstimateSubject.service.InvestEstimateSubjectService;
import com.wonders.ic.progress.entity.bo.Progress;
import com.wonders.ic.progress.service.ProgressService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wonders.ic.quantitiesSubject.service.QuantitiesSubjectService;
import com.wonders.webservice.datacenter.utils.DataCenterUtils;
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

public class ContractAction extends BaseAjaxAction {
	private Contract contract = new Contract();
	private ContractService contractService;
	private ProjectService projectService;
	private AttachService attachService;
	private QuantitiesSubjectService quantitiesSubjectService;
	private ContractStatusService contractStatusService;
	private InvestEstimateSubjectService investEstimateSubjectService;
	private DeptTreeService deptTreeService;
	private ProgressService progressService;
	private DwContractCoverService dwContractCoverService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
	private int size = 20;
	private File uploadify;
	private String uploadifyFileName;
	private final String type1 = "99991"; //项目公司
	private final String type2 = "99990"; //运营公司id
	private final String type3 = "2545"; //维保保障中心id
	private final String yg = "2540";	//运管中心
	private final String edu = "2546";	//教育培训中心
	private final int BUFFER_SIZE = 20 * 1024; // 20K
	
	
	private Map<String, String> companyMap = new HashMap<String, String>();
    private Map<String, String> inviteBidTypeMap = new HashMap<String, String>();
    private ShortMsgService shortMsgService;

    public void setShortMsgService(ShortMsgService shortMsgService) {
        this.shortMsgService = shortMsgService;
    }

    public ContractAction() {
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

        inviteBidTypeMap.put("长期合作","4");
        inviteBidTypeMap.put("综合竞价","21");
        inviteBidTypeMap.put("核价竞价","22");
        inviteBidTypeMap.put("公开竞价","23");
        inviteBidTypeMap.put("单一来源公示","24");
        inviteBidTypeMap.put("工程、采购类限价以上","31");
        inviteBidTypeMap.put("工程、采购类限价以下","32");
        inviteBidTypeMap.put("服务类限价以上","33");
        inviteBidTypeMap.put("服务类限价以下","34");
        inviteBidTypeMap.put("无协议（补报）","42");
        inviteBidTypeMap.put("有协议（集团已批准）","41");
        inviteBidTypeMap.put("公开招标","1");
        inviteBidTypeMap.put("采购平台","2");
        inviteBidTypeMap.put("直接委托","3");
	}
	public File getUploadify() {
		return uploadify;
	}
	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}
	public String getUploadifyFileName() {
		return uploadifyFileName;
	}
	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}
	@Override
	public Object getModel() {
		return contract;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public void setDwContractCoverService(
			DwContractCoverService dwContractCoverService) {
		this.dwContractCoverService = dwContractCoverService;
	}
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	public void setAttachService(AttachService attachService) {
		this.attachService = attachService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public void setDeptTreeService(DeptTreeService deptTreeService) {
		this.deptTreeService = deptTreeService;
	}
	public void setInvestEstimateSubjectService(
			InvestEstimateSubjectService investEstimateSubjectService) {
		this.investEstimateSubjectService = investEstimateSubjectService;
	}
	public void setQuantitiesSubjectService(
			QuantitiesSubjectService quantitiesSubjectService) {
		this.quantitiesSubjectService = quantitiesSubjectService;
	}
	public void setContractStatusService(ContractStatusService contractStatusService) {
		this.contractStatusService = contractStatusService;
	}
	public void setProgressService(ProgressService progressService) {
		this.progressService = progressService;
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
	 * @throws UnsupportedEncodingException 
	 * @throws DocumentException 
	 */
	public String findContractByPage() throws UnsupportedEncodingException, DocumentException {
		String current = servletRequest.getParameter("current");
		servletRequest.setAttribute("current", current);
		String onLineContractOwnerExecuteId = "";
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
			
			Node deptIdNode = rootElem.selectSingleNode("//deptId");
			if(deptIdNode!=null){
				String dataParams2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptIdNode.getText() + "</id></params>";
				String pidXml = getUserInfoThroughCA(appName,token,"getNodesInfo",secret,"xml",dataParams2);
				document = DocumentHelper.parseText(pidXml);
				rootElem = document.getRootElement();
				Node pidNode = rootElem.selectSingleNode("//pid");
				String companyId = pidNode.getText();
				String companyName = companyMap.get(companyId);
				if(companyName!=null && !"".equals(companyName)){	//在该公司下
					onLineContractOwnerExecuteId = companyId;
					servletRequest.setAttribute("onLineContractOwnerExecuteId", onLineContractOwnerExecuteId);
				}
			}
		}
		
		
		String nomodify = servletRequest.getParameter("nomodify");
		servletRequest.setAttribute("nomodify", nomodify);
		
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		
		String findType = servletRequest.getParameter("findType");		//notin：不在该executeId中或者为null
		servletRequest.setAttribute("findType", findType);
		String showAll = ServletActionContext.getRequest().getParameter("showAll");
		servletRequest.setAttribute("showAll", showAll);
		String hasQuantities = ServletActionContext.getRequest().getParameter("hasQuantities");		//是否有工程量清单
		ServletActionContext.getRequest().setAttribute("hasQuantities", hasQuantities);
		String pageNo = servletRequest.getParameter("pageNo");
		String companyId = ServletActionContext.getRequest().getParameter("contractOwnerExecuteId");
		if(companyId!=null && !"".equals(companyId)){
			contract.setContractOwnerExecuteId(companyId);
		}
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		
		String companyType = ServletActionContext.getRequest().getParameter("companyType");
		ServletActionContext.getRequest().setAttribute("companyType", companyType);
		String cType = servletRequest.getParameter("cType");
		servletRequest.setAttribute("cType", cType);
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> typeAllIdList = new ArrayList<String>();
		
		List<String> projectCompanyIdList = new ArrayList<String>();  //项目公司idlist
		List<String> controlAndMaintainIdList = new ArrayList<String>(); //运营公司+维保公司idlist
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
		for(int i=0; i<dept1TreeList.size(); i++){
			projectCompanyIdList.add(dept1TreeList.get(i).getId());
		}
		for(int i=0; i<dept2TreeList.size(); i++){
			controlAndMaintainIdList.add(dept2TreeList.get(i).getId());
		}
		for(int i=0; i<dept3TreeList.size(); i++){
			controlAndMaintainIdList.add(dept3TreeList.get(i).getId());
		}
		/*********项目公司,甲方执行数据*********/
		if(cType!=null && !"".equals(cType) && !"3".equals(cType)){
			
			/**项目公司数据**/
			if(dept1TreeList!=null && dept1TreeList.size()>0){
				int type1CountAll = 0;
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type1IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						type1IdList.add(dept1TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company1")){
					typeAllIdList.addAll(type1IdList);
				}
				
				List<String[]> resultList1 = new ArrayList<String[]>();
				Map<String, String> typeMap = typeMap = contractService.findCountGroupByAddDeptIds(type1IdList,cType);
				
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
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type2IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						type2IdList.add(dept2TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company2")){
					typeAllIdList.addAll(type2IdList);
				}
				List<String[]> resultList2 = new ArrayList<String[]>();
				Map<String, String> typeMap = contractService.findCountGroupByAddDeptIds(type2IdList,cType);
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
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type3IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						type3IdList.add(dept3TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company3")){
					typeAllIdList.addAll(type3IdList);
				}
				List<String[]> resultList3 = new ArrayList<String[]>();
				Map<String, String> typeMap = contractService.findCountGroupByAddDeptIds(type3IdList,cType);
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
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.contract);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.contract, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
			filter.put("contractOwnerExecuteId", onLineContractOwnerExecuteId);
		}
		filter.put("removed", "0");
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			filter.put("contractSignedEndDate", contractSignedEndDate);
		}
		if("2".equals(hasQuantities) || "1".equals(hasQuantities)){
			filter.put("hasQuantities", hasQuantities);
		}
		if(cType!=null && !"".equals(cType) && (contract.getContractType()==null || "".equals(contract.getContractType()))){
			filter.put("contractType", cType);
		}
		if(current!=null && !"".equals(current)){
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf3.parse(current));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			filter.put("contractSignedDate",sdf3.format(c.getTime()));
			filter.put("contractSignedEndDate",current);
			contract.setContractSignedDate(sdf3.format(c.getTime()));
//			servletRequest.setAttribute("contractSignedDate", sdf3.format(c.getTime()));
			servletRequest.setAttribute("contractSignedEndDate", current);
		}
		Page page = null;
		/*****页面显示list区域的数据*****/
		if(findType!=null && !"".equals(findType) && "notin".equals(findType)){
			typeAllIdList.add(yg);
			typeAllIdList.add(edu);
			page = contractService.findContractByPageWithoutExecuteId(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}else {
			page = contractService.findContractByPage(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		String projectId = this.getServletRequest().getParameter("projectId");
		ServletActionContext.getRequest().setAttribute("projectId", projectId);
		
		/***查询合同分类的数据***/
		if(cType!=null && cType.equals("1")){	//建设类
			List<String> type1List = new ArrayList<String>();
			for(int i=1; i<=8; i++){
				type1List.add("1,"+i);
			}
			List<Object[]> countResultType1  = null;
			if(findType!=null && findType.equals("notin")){
				countResultType1 = contractService.findCountByContractTypeWithoutExecuteId(type1List,filter,typeAllIdList);
			}else{
				countResultType1 = contractService.findCountByContractType(type1List,filter,typeAllIdList);
			}
			
			Map<String, String> map1 = new TreeMap<String, String>();
			int type1All = 0;
			if(countResultType1!=null && countResultType1.size()>0){
				for(int i=0; i<countResultType1.size(); i++){
					map1.put(countResultType1.get(i)[0].toString(), countResultType1.get(i)[1].toString());
					type1All += Integer.valueOf(countResultType1.get(i)[1].toString());
				}
			}
			for(int i=1; i<=8; i++){
				if(map1.get("1,"+i)==null){
					map1.put(("1,"+i), "0");
				}
			}
			servletRequest.setAttribute("map1", map1);
			servletRequest.setAttribute("countResultTypeAll", type1All);
		}else if(cType!=null && cType.equals("2")){	//运维类
			List<String> type2List = new ArrayList<String>();
			for(int i=1; i<=3; i++){
				type2List.add("2,"+i);
			}
			List<Object[]> countResultType2  = null;
			if(findType!=null && "notin".equals(findType)){
				countResultType2 = contractService.findCountByContractTypeWithoutExecuteId(type2List,filter,typeAllIdList);
			}else{
				countResultType2 = contractService.findCountByContractType(type2List,filter,typeAllIdList);
			}
			
			Map<String, String> map2 = new TreeMap<String, String>();
			int type2All = 0;
			if(countResultType2!=null && countResultType2.size()>0){
				for(int i=0; i<countResultType2.size(); i++){
					map2.put(countResultType2.get(i)[0].toString(), countResultType2.get(i)[1].toString());
					type2All += Integer.valueOf(countResultType2.get(i)[1].toString());
				}
			}
			for(int i=1; i<=3; i++){
				if(map2.get("2,"+i)==null){
					map2.put(("2,"+i), "0");
				}
			}
			servletRequest.setAttribute("map2", map2);
			servletRequest.setAttribute("countResultTypeAll", type2All);
		}else if(cType!=null && cType.equals("3")){	//其他类
			List<String> type3List = new ArrayList<String>();
			type3List.add("3,1");
			List<Object[]> countResultType3 = contractService.findCountByContractType(type3List,filter,typeAllIdList);
			Map<String, String> map3 = new TreeMap<String, String>();
			int type3All = 0;
			if(countResultType3!=null && countResultType3.size()>0){
				for(int i=0; i<countResultType3.size(); i++){
					map3.put(countResultType3.get(i)[0].toString(), countResultType3.get(i)[1].toString());
					type3All += Integer.valueOf(countResultType3.get(i)[1].toString());
				}
			}
			map3.put("3","1");
			servletRequest.setAttribute("map3", map3);
			servletRequest.setAttribute("countResultTypeAll", type3All);
		}
		Long otherType1 = contractService.findSumOfContractByTypeWithoutExecuteId("1,",projectCompanyIdList);	 		//建设类-甲方执行-其他
		controlAndMaintainIdList.add(yg);
		controlAndMaintainIdList.add(edu);
		Long otherType2 = contractService.findSumOfContractByTypeWithoutExecuteId("2,",controlAndMaintainIdList);	 		//运维类-甲方执行-其他
		servletRequest.setAttribute("otherType1", otherType1);
		servletRequest.setAttribute("otherType2", otherType2);
		//新增,运管中心，教培中心
		List<DwContractCover> company4List = dwContractCoverService.findByCompanyType("4");
		List<DwContractCover> company5List = dwContractCoverService.findByCompanyType("5");
		if(company4List!=null && company4List.size()>0)
			servletRequest.setAttribute("company4", company4List.get(0));
		if(company5List!=null && company5List.size()>0)
			servletRequest.setAttribute("company5", company5List.get(0));
		
		
		/***查询合同分类的数据end***/
		
		/*********导出开始********/
		String ifExport = this.servletRequest.getParameter("ifExport");
		if("yes".equals(ifExport)){//导出excel
			String contractSignedDate = this.servletRequest.getParameter("contractSignedDate");
			if(contractSignedDate!=null&&!"".equals(contractSignedDate)){
				filter.put("contractSignedDate", contractSignedDate);
			}
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=contractExport.xls"); 
			List<Object[]> dataset = new ArrayList<Object[]>();
//			Page page2 = contractService.findContractByPage(filter, Integer.valueOf(pageNo), page.getTotalSize(),typeAllIdList);
			Page page2 =null ;
			
			if(findType!=null && !"".equals(findType) && "notin".equals(findType)){
				typeAllIdList.add(yg);
				typeAllIdList.add(edu);
				page2 = contractService.findContractByPageWithoutExecuteId(filter, Integer.valueOf(pageNo), 20000,typeAllIdList);
			}else {
				page2 = contractService.findContractByPage(filter, Integer.valueOf(pageNo), 20000,typeAllIdList);
			}
			List<Contract> list = page2.getResult();
			for (int i = 0; i < list.size(); i++) {
				Contract tempContract = list.get(i);
				Object[] params = new Object[22];
				params[0] = tempContract.getContractName();
				params[1] = tempContract.getContractNo();
				params[2] = tempContract.getSelfNo();
				params[3] = tempContract.getContractPrice();
				
				if(params[3]!=null && !"".equals(params[3])){
					params[4] = Double.valueOf(tempContract.getContractPrice()) + contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");					//变更后合同价
				}else{
					params[4] = contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");
				}
				params[5] = tempContract.getFinalPrice();		//投资监理审价
				params[6] = tempContract.getVerifyPrice();		//审价单位审价
				params[7] = tempContract.getNationVerifyPrice();		//国家审计审价
				params[8] = tempContract.getContractOwnerExecuteName();
				params[9] = tempContract.getBuildSupplierName();
				params[10] = tempContract.getPayType();
				
				params[11] = tempContract.getContractPassedDate();	//合同审批通过时间
				params[12] = tempContract.getContractSignedDate();	//合同签约时间
				params[13] = tempContract.getContractStartDate();	//合同开始时间
				params[14] = tempContract.getContractEndDate();		//合同结束时间
				params[15] = tempContract.getContractDestoryDate();	//合同销号日期
				
				if(tempContract.getContractStatus()!=null && !"".equals(tempContract.getContractStatus()!=null)){
					if(tempContract.getContractStatus().equals("0")){
						params[16] = "已备案";
					}else if(tempContract.getContractStatus().equals("1")){
						params[16] = "实施中";
					}else if(tempContract.getContractStatus().equals("2")){
						params[16] = "已竣工";
					}else if(tempContract.getContractStatus().equals("3")){
						params[16] = "已销号";
					}else if(tempContract.getContractStatus().equals("4")){
						params[16] = "过程中";
					}else if(tempContract.getContractStatus().equals("5")){
						params[16] = "已取消";
					}
				}else{
					params[16] = null;
				}
				
				if (list.get(i).getContractType() != null) {
					if (list.get(i).getContractType().equals("1,")) {
						params[17] = "建设类-全部";
					} else if (list.get(i).getContractType().equals("1,1")) {
						params[17] = "建设类-G";
					} else if (list.get(i).getContractType().equals("1,2")) {
						params[17] = "建设类-K";
					} else if (list.get(i).getContractType().equals("1,3")) {
						params[17] = "建设类-S";
					} else if (list.get(i).getContractType().equals("1,4")) {
						params[17] = "建设类-Q";
					} else if (list.get(i).getContractType().equals("1,5")) {
						params[17] = "建设类-q";
					} else if (list.get(i).getContractType().equals("1,6")) {
						params[17] = "建设类-J";
					} else if (list.get(i).getContractType().equals("1,7")) {
						params[17] = "建设类-Y";
					} else if (list.get(i).getContractType().equals("1,8")) {
						params[17] = "建设类-Z";
					} else if (list.get(i).getContractType().equals("2,")) {
						params[17] = "运维类-全部";
					} else if (list.get(i).getContractType().equals("2,1")) {
						params[17] = "运维类-服务";
					} else if (list.get(i).getContractType().equals("2,2")) {
						params[17] = "运维类-工程";
					} else if (list.get(i).getContractType().equals("2,3")) {
						params[17] = "运维类-货物";
					}else if(list.get(i).getContractType().equals("3,3")){
						params[17] = "其他类-其他类";
					}
				} else {
					params[17] = null;
				}
				if (list.get(i).getInviteBidType() != null) {
					if (list.get(i).getInviteBidType().equals("1")) {
						params[18] = "公开招标";
					} else if (list.get(i).getInviteBidType().equals("2")) {
						params[18] = "采购平台";
					} else if (list.get(i).getInviteBidType().equals("3")) {
						params[18] = "直接委托";
					}
				} else{
					params[18] = null;
				}
				params[19] = list.get(i).getProjectNo();
				params[20] = list.get(i).getProjectName();
				
				if(list.get(i).getContractGrouping()!=null && !"".equals(list.get(i).getContractGrouping())){
					if("1".equals(list.get(i).getContractGrouping())){
						params[21] ="成本内";
					}else if("2".equals(list.get(i).getContractGrouping())){
						params[21] ="成本外";
					}
				}else{
					params[21] = "";
				}
				
				dataset.add(params);
			}
			
			String[] headers = {"合同名称","合同编号","自有编号","合同价(万元)","变更后合同价(万元)","投资监理审价","审价单位审价","国家审计审价","甲方（执行）","对方单位","支付方式","合同审批通过时间","合同签约时间","合同开始时间","合同结束时间","合同销号日期","合同状态","合同分类","招标方式","项目编号","项目名称","合同分组"};
			short[] width = {10000,10000,10000,5000,5000,5000,5000,10000,10000,3000,4000,4000,4000,3000,7000,4000,10000,10000,5000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同数据导出", headers, dataset, out,width); 
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
		
		return SUCCESS;
	}

	/**
	 * 保存新增
	 */
	public String saveAdd(){
		
		String attachIds = servletRequest.getParameter("attachIds");
		
		contract.setContractPrice(getFormattedMoney(contract.getContractPrice()));
		
		if(contract.getVerifyPrice()!=null && !"".equals(contract.getVerifyPrice())){
			contract.setVerifyPrice(getFormattedMoney(contract.getVerifyPrice()));
		}
		if(contract.getFinalPrice()!=null && !"".equals(contract.getFinalPrice())){
			contract.setFinalPrice(getFormattedMoney(contract.getFinalPrice()));
		}
		if(contract.getNationVerifyPrice()!=null && !"".equals(contract.getNationVerifyPrice())){
			contract.setNationVerifyPrice(getFormattedMoney(contract.getNationVerifyPrice()));
		}
		contract.setContractAttachment(attachIds);
		contract.setCreateDate(sdf.format(new Date()));
		contract.setUpdateDate(sdf.format(new Date()));
		contract.setRemoved("0");
		
		String projectId = contract.getProjectId();
		String subjectId = ServletActionContext.getRequest().getParameter("estimateSubjectId");
		String subjectName = ServletActionContext.getRequest().getParameter("estimateSubjectName");
		
		contractService.saveContract(contract);
		if(projectId!=null && !"".equals(projectId) && subjectId!=null && !"".equals(subjectId) && subjectName!=null && !"".equals(subjectName)){
			QuantitiesSubject qs = new QuantitiesSubject();
			qs.setContractId(contract.getId());
			qs.setContractNo(contract.getContractNo());
			qs.setInvestEstimateId(subjectId);
			qs.setInvestEstimateName(subjectName);
			qs.setRemoved("0");
			qs.setCreateDate(sdf.format(new Date()));
			qs.setSubjectName(contract.getContractName());
			
			qs.setTotalPrice(getFormattedMoney(String.valueOf(Double.valueOf(contract.getContractPrice())*10000)));
			qs.setUnitPrice(getFormattedMoney(String.valueOf(Double.valueOf(contract.getContractPrice())*10000)));
			qs.setAmount("1");
			qs.setDataType("1");
			quantitiesSubjectService.saveQuantitiesSubject(qs);
			quantitiesSubjectService.saveAddOnPage(qs, "0", "1", "1", "1","no","");
		}
		return SUCCESS;
	}
	
	
	/**
	 * 显示详细页面
	 * @throws UnsupportedEncodingException 
	 */
	public String showView() throws UnsupportedEncodingException{
		String id = ServletActionContext.getRequest().getParameter("id");
		String tab = ServletActionContext.getRequest().getParameter("tab");
		contract = contractService.findContractById(id);
		List<ContractStatus> list_change = new ArrayList<ContractStatus>();
		list_change = contractStatusService.findStatusList(id,"1");
		ServletActionContext.getRequest().setAttribute("list_change", list_change);

		List<ContractStatus> list_plan_pay = new ArrayList<ContractStatus>();
		list_plan_pay = contractStatusService.findStatusList(id,"2");
		ServletActionContext.getRequest().setAttribute("list_plan_pay", list_plan_pay);

		List<ContractStatus> list_actual_pay = new ArrayList<ContractStatus>();
		list_actual_pay = contractStatusService.findStatusList(id,"3");
		ServletActionContext.getRequest().setAttribute("list_actual_pay", list_actual_pay);

		List<Progress> progressList = progressService.findAllByObjectId(id,"1");
		ServletActionContext.getRequest().setAttribute("progressList", progressList);

		List<Map<String,Object>> kpiStatus = contractService.findKpiByContractId(id);
		ServletActionContext.getRequest().setAttribute("kpiStatus", kpiStatus);

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
		servletRequest.setAttribute("id", id);
		if(tab!=null){
			servletRequest.setAttribute("tab", tab);
		}
		List<CompanyRoute> routes = contractService.findAllLine();
		servletRequest.setAttribute("routes", routes);
		if(contract.getContractAttachment()==null || "".equals(contract.getContractAttachment())){
			return "showView";
		}
		List<Attach> attachList = attachService.findByIds(contract.getContractAttachment().split(","));

		ServletActionContext.getRequest().setAttribute("attachList", attachList);
		
		return "showView";
	}
	
	/**
	 * 显示修改页面
	 */
	public String showEdit(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contract = contractService.findContractById(id);
		if(contract!=null && contract.getContractAttachment()!=null && !"".equals(contract.getContractAttachment())){
			String[] ids = contract.getContractAttachment().split(",");
			if(ids!=null && ids.length>0){
				List<Attach> attachList = attachService.findByIds(ids);
				ServletActionContext.getRequest().setAttribute("attachList", attachList);
			}
		}
		
		List<QuantitiesSubject> quantitiesSubjectList = quantitiesSubjectService.findByContractId(contract.getId());
		if(quantitiesSubjectList!=null ){
			QuantitiesSubject qs ;
			if(quantitiesSubjectList.size()==1){
				qs = quantitiesSubjectList.get(0);
				if(qs.getInvestEstimateId()!=null && !"".equals(qs.getInvestEstimateId())){
					//InvestEstimateSubject ies = investEstimateSubjectService.findById(qs.getInvestEstimateId());
					ServletActionContext.getRequest().setAttribute("qs", qs);
				}
			}			
		}else{
			ServletActionContext.getRequest().setAttribute("subjectStatus", false);
		}
		
		
		
		return "showEdit";
	}
	
	/**
	 * 保存修改
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String saveEdit() throws IllegalAccessException, InvocationTargetException{
		
		Contract tempContract = contractService.findContractById(contract.getId());
		String attachIds = servletRequest.getParameter("attachIds");
		if(contract.getContractOwnerExecuteName()==null || "".equals(contract.getContractOwnerExecuteName())){
			contract.setContractOwnerExecuteId(null);
		}
		BeanUtils.copyProperties(tempContract, contract);
		tempContract.setContractAttachment(attachIds);
		tempContract.setUpdateDate(sdf.format(new Date()));
		if(!"".equals(tempContract.getContractPrice())){
			tempContract.setContractPrice(getFormattedMoney(tempContract.getContractPrice()));
		}
		if(!"".equals(tempContract.getVerifyPrice())){
			tempContract.setVerifyPrice(getFormattedMoney(tempContract.getVerifyPrice()));
		}
		if(!"".equals(tempContract.getFinalPrice())){
			tempContract.setFinalPrice(getFormattedMoney(tempContract.getFinalPrice()));
		}
		if(!"".equals(tempContract.getNationVerifyPrice())){
			tempContract.setNationVerifyPrice(getFormattedMoney(tempContract.getNationVerifyPrice()));
		}
		try {
            tempContract.setUpdateDate(sdf.format(new Date()));
			contractService.update(tempContract);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String estimateSubjectId = ServletActionContext.getRequest().getParameter("estimateSubjectId");
		String estimateSubjectName = ServletActionContext.getRequest().getParameter("estimateSubjectName");
		
		List<QuantitiesSubject> list = quantitiesSubjectService.findByContractId(tempContract.getId());
		if(list!=null ){
			QuantitiesSubject qs;
			if(list.size()==1){
				qs = list.get(0);
				qs.setContractId(tempContract.getId());
				qs.setContractNo(tempContract.getContractNo());
				qs.setInvestEstimateId(estimateSubjectId);
				qs.setInvestEstimateName(estimateSubjectName);
				qs.setOperateDate(sdf.format(new Date()));
				qs.setUnitPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice())*10000)));
				qs.setTotalPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice())*10000)));
				qs.setAmount("1");
				qs.setDataType("1");
				quantitiesSubjectService.update(qs);
			}else if(list.size()==0){
				qs = new QuantitiesSubject();
				qs.setContractId(tempContract.getId());
				qs.setContractNo(tempContract.getContractNo());
				qs.setInvestEstimateId(estimateSubjectId);
				qs.setInvestEstimateName(estimateSubjectName);
				qs.setRemoved("0");
				qs.setCreateDate(sdf.format(new Date()));
				qs.setSubjectName(tempContract.getContractName());
				qs.setTotalPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice())*10000)));
				qs.setUnitPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice())*10000)));
				qs.setAmount("1");
				qs.setDataType("1");
				quantitiesSubjectService.saveQuantitiesSubject(qs);
				quantitiesSubjectService.saveAddOnPage(qs, "0", "1", "1", "1","no","");
			}
		}
		
		
		return SUCCESS;
	}


	/**
	 * 删除
	 */
	public String delete(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contractService.deleteById(id);
		return AJAX;
	}
	
	/**
	 * 逻辑删除
	 * @return
	 */
	public String deleteOnlogical(){
		String id = ServletActionContext.getRequest().getParameter("id");
		contractService.deleteContractAndWbsAndQuantitiesByContractId(id);
		return AJAX;
	}
	
	
	/**
	 * 模糊搜索
	 */
	public String findByContractNoWithFuzzySearch(){
		String contractNo = ServletActionContext.getRequest().getParameter("contractNo");
		List<Contract> list = contractService.findByContractNoWithFuzzySearch(contractNo);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 模糊搜索
	 */
	public String findByContractNameWithFuzzySearch(){
		String contractName = ServletActionContext.getRequest().getParameter("contractName");
		List<Contract> list = contractService.findByContractNameWithFuzzySearch(contractName);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	
	/**
	 * 查询业主方名称
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
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

    public String dictionary() throws Exception{
//        String pid = servletRequest.getParameter("pid");
//        String code = servletRequest.getParameter("code");
//        List<Map<String,Object>> list = contractService.getTypeByPid(pid, code);
//        Map map = new HashMap();
//        map.put("result",list);
//
//        this.servletResponse.setCharacterEncoding("UTF-8");
//        this.getServletResponse().getWriter().print(JSONObject.fromObject(map).toString());
        return null;
    }

	public String findCompanyRouteWithFuzzySearch() throws IOException{
		String param = servletRequest.getParameter("param");
		List<CompanyRoute> list = contractService.findCompanyByName(param);
		JSONArray array = JSONArray.fromObject(list);
		this.servletResponse.setCharacterEncoding("UTF-8");
		this.getServletResponse().getWriter().print(array.toString());
		return null;
	}
	
	public String findLineByCompanyId() throws IOException{
		String param = servletRequest.getParameter("param");
		List<CompanyRoute> list = contractService.findLineByCompanyId(param);
		JSONArray array = JSONArray.fromObject(list);
		this.servletResponse.setCharacterEncoding("UTF-8");
		this.getServletResponse().getWriter().print(array.toString());
		return null;
	}
	
	
	
	/**
	 * 绑定概算科目
	 * @return
	 */
	public String bindEstimateSubject(){
		String contractIds = ServletActionContext.getRequest().getParameter("contractIds");
		String estimateSubjectId = ServletActionContext.getRequest().getParameter("estimateSubjectId");
		String estimateSubjectName = ServletActionContext.getRequest().getParameter("estimateSubjectName");
		
		String[] contractIdArray = null;
		List<Contract> resultList = null;
		
		if(contractIds!=null && !"".equals(contractIds)){
			contractIdArray = contractIds.split(",");
		}
		if(contractIdArray!=null && contractIdArray.length>0){
			resultList = contractService.findAllByContractIdArray(contractIdArray);
		}
		
		if(resultList!=null){
			contractService.updateToBindBatchEstimateSubject(resultList, estimateSubjectId, estimateSubjectName);
		}
		
		return AJAX;
	}
	
	
	/**
	 * 将HtXx表中的数据导入C_contract表中，用完之后删除本方法
	 * @return
	 */
	public String importHtXxToContract(){
		contractService.importHtXxToContract();
		return SUCCESS;
	}
	
	//批量修改甲方执行、合同分类
	public String batchEdit(){
		String ids = servletRequest.getParameter("ids");
		if(ids==null || "".equals(ids) || ids.length()<1) return null;
		String idArray[] = ids.split(",");
		String executeName= servletRequest.getParameter("executeName");
		String executeId = servletRequest.getParameter("executeId");
		String type = servletRequest.getParameter("type");
		
		contractService.updateBatchEdit(idArray, executeName, executeId, type);
		
		return AJAX;
	}
	
	/**
	 * 显示合同封面
	 */
	public String showContractCover(){
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		
		List<String> projectCompanyIdList = new ArrayList<String>();  //项目公司idlist
		List<String> controlAndMaintainIdList = new ArrayList<String>(); //运营公司+维保公司idlist
		
		dept1TreeList.add(deptTreeService.findById(type1));
		dept2TreeList.add(deptTreeService.findById(type2));
		dept3TreeList.add(deptTreeService.findById(type3));
		
		//1.先根据父节点得到所有子节点的id(deptTree)
		dept1TreeList.addAll(dept1TreeList.size(), deptTreeService.findByPid(type1));
		dept2TreeList.addAll(dept2TreeList.size(), deptTreeService.findByPid(type2));
		dept3TreeList.addAll(dept3TreeList.size(), deptTreeService.findByPid(type3));
		for(int i=0; i<dept1TreeList.size(); i++){
			projectCompanyIdList.add(dept1TreeList.get(i).getId());
		}
		for(int i=0; i<dept2TreeList.size(); i++){
			controlAndMaintainIdList.add(dept2TreeList.get(i).getId());
		}
		for(int i=0; i<dept3TreeList.size(); i++){
			controlAndMaintainIdList.add(dept3TreeList.get(i).getId());
		}
		
		DwContractCover all = dwContractCoverService.findByContractType("0");	//全部合同的数量
		DwContractCover first = dwContractCoverService.findByContractType("1");	//建设类合同数量
		DwContractCover second = dwContractCoverService.findByContractType("2");	//运维类合同数量
		DwContractCover third = dwContractCoverService.findByContractType("3");	//其他类合同数量
		
		//建设类-项目公司
		List<DwContractCover> company1List = dwContractCoverService.findByCompanyType("1");
		long type1Company1All = 0; 
		if(company1List!=null && company1List.size()>0){
			for(int i=0; i<company1List.size(); i++){
				type1Company1All += company1List.get(i).getNumbers().longValue();
			}
		}
		servletRequest.setAttribute("type1Company1All", type1Company1All);
		//运维类-运营公司
		List<DwContractCover> company2List = dwContractCoverService.findByCompanyType("2");
		//运维类-维保中心
		List<DwContractCover> company3List = dwContractCoverService.findByCompanyType("3");
		long type2Company1All = 0; 
		if(company2List!=null && company2List.size()>0){
			for(int i=0; i<company2List.size(); i++){
				type2Company1All += company2List.get(i).getNumbers().longValue();
			}
		}
		if(company3List!=null && company3List.size()>0){
			for(int i=0; i<company3List.size(); i++){
				type2Company1All += company3List.get(i).getNumbers().longValue();
			}
		}
		servletRequest.setAttribute("type2Company1All", type2Company1All);
		
		
		//新增,运管中心，教培中心
		List<DwContractCover> company4List = dwContractCoverService.findByCompanyType("4");
		List<DwContractCover> company5List = dwContractCoverService.findByCompanyType("5");
		
		//建设类合同
		List<DwContractCover> type1List = dwContractCoverService.findByContractTypeWithFuzzySearch("1,");
		//运维类
		List<DwContractCover> type2List = dwContractCoverService.findByContractTypeWithFuzzySearch("2,");
		
		Long otherType1 = contractService.findSumOfContractByTypeWithoutExecuteId("1,",projectCompanyIdList);	 		//建设类-甲方执行-其他
		
		controlAndMaintainIdList.add(yg);
		controlAndMaintainIdList.add(edu);
		Long otherType2 = contractService.findSumOfContractByTypeWithoutExecuteId("2,",controlAndMaintainIdList);	 		//运维类-甲方执行-其他
		servletRequest.setAttribute("otherType1", otherType1);
		servletRequest.setAttribute("otherType2", otherType2);
		
		
		servletRequest.setAttribute("all", all);
		servletRequest.setAttribute("first", first);
		servletRequest.setAttribute("second", second);
		servletRequest.setAttribute("third", third);
		servletRequest.setAttribute("company1List", company1List);
		servletRequest.setAttribute("company2List", company2List);
		servletRequest.setAttribute("company3List", company3List);
		servletRequest.setAttribute("company4", company4List.get(0));
		servletRequest.setAttribute("company5", company5List.get(0));
		servletRequest.setAttribute("type1List", type1List);
		servletRequest.setAttribute("type2List", type2List);
		
		return SUCCESS;
	}
	
	
	/**
	 * 批量上传合同
	 * @return
	 */
	public String batchUpload(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Properties properties = loadProperties("config.properties");
		String saveDir = properties.getProperty("saveDir");
		
		File savePath = new File(saveDir);
		if(!savePath.exists()){
			savePath.mkdirs();
		}
		String saveFileName = saveDir+File.separator+uploadifyFileName;
		File newFile = new File(saveFileName);
		if(newFile.exists()){
			newFile.delete();
		}
		uploadify.renameTo(newFile);		//将文件移到制定位置并且改名
		
		
		/****************************开始解析******************************/
		HSSFRow row;
		String cellData;
		InputStream is;
		try {
			is = new FileInputStream(saveFileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			int rowsAll = sheet.getPhysicalNumberOfRows();
			if(sheet.getPhysicalNumberOfRows()>0){			//行数大于0
				boolean hasError = false;
				List<Contract> saveList = new ArrayList<Contract>();
				HSSFCell cell ;		
				for(int i=1; i<rowsAll; i++){
					int saveStatus = 0;		//0:可以保存，1：修改成功，2：有错误
					Contract contract = new Contract();
					row = sheet.getRow(i);
					String errorInfo = "";
					if(row==null){
						continue;
					}					
					
					cell = row.getCell(1);				//B集团编号合同编号
					if(cell==null || cell.equals("")){
						errorInfo = "合同编号不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						List<Contract> sameNoList = contractService.findByContractNoLowerCase(cell.toString());
						if(sameNoList!=null && sameNoList.size()>0){
							contract = sameNoList.get(0);
							saveStatus = 1;
						}else{
							contract.setContractNo(cell.toString());
						}
					}
					
					cell = row.getCell(2);				//C.自有编号
					if(cell!=null && !cell.equals("")){
						contract.setSelfNo(cell.toString());
					}
					
					cell = row.getCell(3);			//D.合同名称
					if(cell==null || "".equals(cell)){
						errorInfo+= "合同名称不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						contract.setContractName(cell.toString());
					}
					cell = row.getCell(4);		//E.合同价
					if(cell==null || "".equals(cell)){
						errorInfo+= "合同价不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						contract.setContractPrice(getFormattedMoney(cell.toString()));
					}
					
					cell = row.getCell(5);		//F.投资监理审定价
					if(cell!=null && !"".equals(cell.toString())){
						contract.setFinalPrice(getFormattedMoney(cell.toString()));
					}
					
					cell = row.getCell(6);		//G.审价单位审定价
					if(cell!=null && !"".equals(cell.toString())){
						contract.setVerifyPrice(getFormattedMoney(cell.toString()));
					}
					
					cell = row.getCell(7);		//H.国家审计审定价
					if(cell!=null && !"".equals(cell.toString())){
						contract.setNationVerifyPrice(getFormattedMoney(cell.toString()));
					}
					
					cell = row.getCell(8);		//I.支付方式
					if(cell!=null && !"".equals(cell.toString())){
						contract.setPayType(cell.toString());
					}
					
					cell = row.getCell(9);		//J.采购方式
					if(cell!=null && !"".equals(cell.toString())){
                        if(inviteBidTypeMap.containsKey(cell.toString())){
                            contract.setInviteBidType(inviteBidTypeMap.get(cell.toString()));
                        }
//						if(cell.toString().equals("公开招标")){
//							contract.setInviteBidType("1");
//						}else if(cell.toString().equals("采购平台")){
//							contract.setInviteBidType("2");
//						}else if(cell.toString().equals("直接委托")){
//							contract.setInviteBidType("3");
//						}else if(cell.toString().equals("长期合作")){
//                            contract.setInviteBidType("4");
//                        }
                        else{
							errorInfo+= "采购方式填写错误,";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					cell = row.getCell(10);		//K.合同状态,必填
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString().equals("已备案")){
							contract.setContractStatus("0");
						}else if(cell.toString().equals("实施中")){
							contract.setContractStatus("1");
						}else if(cell.toString().equals("已竣工")){
							contract.setContractStatus("2");
						}else if(cell.toString().equals("已销号")){
							contract.setContractStatus("3");
						}else if(cell.toString().equals("过程中")){
							contract.setContractStatus("4");
						}else if(cell.toString().equals("已取消")){
							contract.setContractStatus("5");
						}else if(cell.toString().equals("已终止")){
                            contract.setContractStatus("6");
                        }else{
							errorInfo+= "合同状态填写错误,";
							saveStatus = 2;
							hasError = true;
						}
					}else{
						errorInfo+= "合同状态不能为空,";
						saveStatus = 2;
						hasError = true;
					}
					
					cell = row.getCell(11);		//L.合同类型，必填
					if(cell!=null && !"".equals(cell.toString())){
						String contractType = cell.toString();
						if(contractType.equals("建设类-工程类")){
							contract.setContractType("1,1");
						}else if(contractType.equals("建设类-勘察类")){
							contract.setContractType("1,2");
						}else if(contractType.equals("建设类-设计类")){
							contract.setContractType("1,3");
						}else if(contractType.equals("建设类-前期类")){
							contract.setContractType("1,4");
						}else if(contractType.equals("建设类-其他类")){
							contract.setContractType("1,5");
						}else if(contractType.equals("建设类-监理类")){
							contract.setContractType("1,6");
						}else if(contractType.equals("建设类-科研类")){
							contract.setContractType("1,7");
						}else if(contractType.equals("建设类-咨询类")){
							contract.setContractType("1,8");
						}else if(contractType.equals("运维类-服务类")){
							contract.setContractType("2,1");
						}else if(contractType.equals("运维类-工程类")){
							contract.setContractType("2,2");
						}else if(contractType.equals("运维类-货物类")){
							contract.setContractType("2,3");
						}else if(contractType.equals("其他类-其他类")){
							contract.setContractType("3,1");
						}else{
							errorInfo+= "合同类型填写错误,";
							saveStatus = 2;
							hasError = true;
						}
					}else{
						errorInfo+= "合同类型不能为空,";
						saveStatus = 2;
						hasError = true;
					}
					
					cell = row.getCell(12);			//M.合同审批通过时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							String date = cell.toString().trim();
							sdf.parse(date);
							contract.setContractPassedDate(date);
						} catch (Exception e) {
							errorInfo+= "合同审批通过时间格式不对(需要文本类型yyyy-MM-dd),";
							saveStatus = 2;
							hasError = true;
						}
						
					}
					
					cell = row.getCell(13);			//N.合同签约时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							String date = cell.toString().trim();
							sdf.parse(date);
							contract.setContractSignedDate(date);
						} catch (Exception e) {
							errorInfo+= "合同签约时间格式不对(必须文本类型yyyy-MM-dd),";
							saveStatus = 2;
							hasError = true;
						}
						
					}
					
					cell = row.getCell(14);			//O.合同开始时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							String date = cell.toString().trim();
							sdf.parse(date);
							contract.setContractStartDate(date);
						} catch (Exception e) {
							errorInfo+= "合同开始时间格式不对(必须文本类型yyyy-MM-dd),";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					cell = row.getCell(15);			//P.合同结束时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							String date = cell.toString().trim();
							sdf.parse(date);
							contract.setContractEndDate(date);
						} catch (Exception e) {
							errorInfo+= "合同结束时间格式不对(必须文本类型yyyy-MM-dd),";
							saveStatus = 2;
							hasError = true;
						}
					}
						
					cell = row.getCell(16);			//Q.合同销号时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							String date = cell.toString().trim();
							sdf.parse(date);
							contract.setContractDestoryDate(date);
						} catch (Exception e) {
							errorInfo+= "合同开始时间格式不对(必须文本类型yyyy-MM-dd),";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					cell = row.getCell(17);			//R.合同分组,必填
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString().trim().equals("成本内")){
							contract.setContractGrouping("1");
						}else if(cell.toString().trim().equals("成本外")){
							contract.setContractGrouping("2");
						}else if(cell.toString().trim().equals("无")){
							contract.setContractGrouping("3");
						}else{
							errorInfo+= "合同分组填写错误,";
							saveStatus = 2;
							hasError = true;
						}
					}else{
						errorInfo+= "合同分组不能为空";
						saveStatus = 2;
						hasError = true;
					}
					
					cell = row.getCell(18);			//S.甲方执行名称,要取id
					if(cell!=null && !"".equals(cell.toString())){
						contract.setContractOwnerExecuteName(cell.toString());
					}
					
					cell = row.getCell(19);			//T.对方单位
					if(cell!=null && !"".equals(cell.toString())){
						contract.setBuildSupplierName(cell.toString());
					}
					cell = row.getCell(20);			//U.项目编号
					if(cell!=null && !"".equals(cell.toString())){
						contract.setProjectNo(cell.toString());
					}
					
					cell = row.getCell(21);			//V.项目名称
					if(cell!=null && !"".equals(cell.toString())){
						contract.setProjectName(cell.toString());
					}
					
					String userName = (String)getSession().get("userName");
					String loginName = (String)getSession().get("loginName");
					if(userName!=null && !"".equals(userName)){
						contract.setRegisterPersonName(userName);
					}
					if(loginName!=null && !"".equals(loginName)){
						contract.setRegisterPersonLoginname(loginName);
					}
					
					if(saveStatus==0){
						contract.setCreateType("excel");
						contract.setRemoved("0");
						contract.setCreateDate(sdf.format(new Date()));
						saveList.add(contract);
					}else if(saveStatus==1){
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);		// 设置背景色为黄色,修改成功
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);
					}else{
						//创建单元格样式
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.RED.index); // 设置背景色为红色,本条数据无效
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);
					}
				}
				if(!hasError){
					contractService.saveAll(saveList);
				}
			}
		
			
			
			//生产反馈文件
			String feedbackFileName = savePath + File.separator + uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf('.'))+"反馈.xls";

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(feedbackFileName);
				hssfWorkbook.write(fos);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String downloadName = uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf('.'))+"反馈.xls";
			createJSonData(downloadName);
		} catch (FileNotFoundException e) {
			uploadify.delete();
		} catch (IOException e) {
			uploadify.delete();
		}
		
		return AJAX;
	}
	
	
	/**
	 * 批量上传合同,客户定制
	 * @return
	 */
	public String batchUploadCustomized(){
		Properties properties = loadProperties("config.properties");
		String saveDir = properties.getProperty("saveDir");
		
		File savePath = new File(saveDir);
		if(!savePath.exists()){
			savePath.mkdirs();
		}
		String saveFileName = saveDir+File.separator+uploadifyFileName;
		File newFile = new File(saveFileName);
		if(newFile.exists()){
			newFile.delete();
		}
		uploadify.renameTo(newFile);		//将文件移到制定位置并且改名
		
		
		/****************************开始解析******************************/
		HSSFRow row;
		String cellData;
		InputStream is;
		try {
			is = new FileInputStream(saveFileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			int rowsAll = sheet.getPhysicalNumberOfRows();
			if(sheet.getPhysicalNumberOfRows()>0){			//行数大于0
				boolean hasError = false;
				List<Contract> saveList = new ArrayList<Contract>();
				HSSFCell cell ;		
				for(int i=1; i<rowsAll; i++){
					int saveStatus = 0;		//0:可以保存，1：修改成功，2：有错误
					Contract contract = new Contract();
					row = sheet.getRow(i);
					String errorInfo = "";
					if(row==null){
						continue;
					}					
					cell = row.getCell(1);				//1,B.客户给的第一列是自有编号
					if(cell==null || cell.equals("")){
						errorInfo = "合同编号不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						List<Contract> sameNoList = contractService.findByContractNoLowerCase(cell.toString());
						if(sameNoList!=null && sameNoList.size()>0){
							contract = sameNoList.get(0);
							saveStatus = 1;
						}else{
							contract.setSelfNo(cell.toString());
							contract.setContractNo(cell.toString());		//暂时将合同编号置为自有编号
						}
					}
					
					cell = row.getCell(2);			//2,C.合同名称
					if(cell==null || "".equals(cell)){
						errorInfo+= "合同名称不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						contract.setContractName(cell.toString());
					}
					cell = row.getCell(3);		//3,D.合同价
					if(cell==null || "".equals(cell)){
						errorInfo+= "合同价不能为空,";
						saveStatus = 2;
						hasError = true;
					}else{
						contract.setContractPrice(getFormattedMoney(cell.toString()));
					}
					
					cell = row.getCell(4);		//4,E.投资监理审定价
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString()!=null && !"".equals(cell.toString())){
							contract.setFinalPrice(getFormattedMoney(cell.toString()));
						}
					}
					
					cell = row.getCell(5);		//5,F.审价单位审定价
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString()!=null && !"".equals(cell.toString())){
							contract.setVerifyPrice(getFormattedMoney(cell.toString()));
						}
					}
					
					cell = row.getCell(6);		//6,G.国家审计审定价
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString()!=null && !"".equals(cell.toString())){
							contract.setNationVerifyPrice(getFormattedMoney(cell.toString()));
						}
					}
					
					cell = row.getCell(7);		//7,H.支付方式
					if(cell!=null && !"".equals(cell.toString())){
						contract.setPayType(cell.toString());
					}
					
					cell = row.getCell(8);		//8,I.采购方式
					if(cell!=null && !"".equals(cell.toString())){
                        if(inviteBidTypeMap.containsKey(cell.toString())){
                            contract.setInviteBidType(inviteBidTypeMap.get(cell.toString()));
                        }else{

                        }
//						if(cell.toString().equals("公开招标")){
//							contract.setInviteBidType("1");
//						}else if(cell.toString().equals("采购平台")){
//							contract.setInviteBidType("2");
//						}else if(cell.toString().equals("直接委托")){
//							contract.setInviteBidType("3");
//						}else if(cell.toString().equals("长期合作")){
//                            contract.setInviteBidType("4");
//                        }else{
//							contract.setInviteBidType(cell.toString());
//						}
					}
					
					cell = row.getCell(9);		//9,J.合同状态
					if(cell!=null && !"".equals(cell.toString())){
						if(cell.toString().equals("已备案")){
							contract.setContractStatus("0");
						}else if(cell.toString().equals("实施中")){
							contract.setContractStatus("1");
						}else if(cell.toString().equals("已竣工")){
							contract.setContractStatus("2");
						}else if(cell.toString().equals("已销号")){
							contract.setContractStatus("3");
						}/*else{
							contract.setContractStatus(cell.toString());
						}*/
					}
					
					cell = row.getCell(10);		//10,K.合同类型
					if(cell!=null && !"".equals(cell.toString())){
						String contractType = cell.toString();
						if(contractType.equals("建设类-工程类")){
							contract.setContractType("1,1");
						}else if(contractType.contains("建设类-勘察")){
							contract.setContractType("1,2");
						}else if(contractType.contains("建设类-设计")){
							contract.setContractType("1,3");
						}else if(contractType.contains("建设类-前期")){
							contract.setContractType("1,4");
						}else if(contractType.contains("建设类-其他")){
							contract.setContractType("1,5");
						}else if(contractType.contains("建设类-监理")){
							contract.setContractType("1,6");
						}else if(contractType.contains("建设类-科研")){
							contract.setContractType("1,7");
						}else if(contractType.contains("建设类-咨询")){
							contract.setContractType("1,8");
						}else if(contractType.contains("运维类-服务")){
							contract.setContractType("2,1");
						}else if(contractType.contains("运维类-工程")){
							contract.setContractType("2,2");
						}else if(contractType.contains("运维类-货物")){
							contract.setContractType("2,3");
						}else if(contractType.contains("其他类-其他")){
							contract.setContractType("3,1");
						}else{
							contract.setContractType(cell.toString());
						}
						
					}
					
					cell = row.getCell(11);			//11,L.合同审批通过时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							sdf3.parse(cell.toString());
							contract.setContractPassedDate(cell.toString());
						} catch (Exception e) {
							errorInfo += "合同审批通过时间格式错误，";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					cell = row.getCell(12);			//12,M.合同签约时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							sdf3.parse(cell.toString());
							contract.setContractSignedDate(cell.toString());
						} catch (ParseException e) {
							errorInfo += "合同签约时间格式错误，";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					cell = row.getCell(13);			//13.N.合同开始时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							sdf3.parse(cell.toString());
							contract.setContractStartDate(cell.toString());
						} catch (ParseException e) {
							errorInfo += "合同开始时间格式错误，";
							saveStatus = 2;
							hasError = true;
						}
						
					}
					
					cell = row.getCell(14);			//14,O.合同结束时间
					if(cell!=null && !"".equals(cell.toString())){
						try {
							sdf3.parse(cell.toString());
							contract.setContractEndDate(cell.toString());
						} catch (ParseException e) {
							errorInfo += "合同结束时间格式错误，";
							saveStatus = 2;
							hasError = true;
						}
					}
						
					cell = row.getCell(15);			//15,P.甲方执行名称,要取id
					if(cell!=null && !"".equals(cell.toString())){
						contract.setContractOwnerExecuteName(cell.toString());
						if(cell.toString().equals("维保中心-车辆公司")){
							contract.setContractOwnerExecuteId("2718");
						}else if(cell.toString().equals("维保中心-通号公司")){
							contract.setContractOwnerExecuteId("2720");
						}else if(cell.toString().equals("维保中心-物资和后勤公司")){
							contract.setContractOwnerExecuteId("2722");
						}else if(cell.toString().equals("维保中心-工务公司")){
							contract.setContractOwnerExecuteId("2721");
						}else if(cell.toString().equals("维保中心-供电公司")){
							contract.setContractOwnerExecuteId("2719");
						}else if(cell.toString().equals("上海地铁第一运营有限公司")){
							contract.setContractOwnerExecuteId("2541");
						}else if(cell.toString().equals("上海地铁第二运营有限公司")){
							contract.setContractOwnerExecuteId("2542");
						}else if(cell.toString().equals("上海地铁第三运营有限公司")){
							contract.setContractOwnerExecuteId("2543");
						}else if(cell.toString().equals("上海地铁第四运营有限公司")){
							contract.setContractOwnerExecuteId("2544");
						}else if(cell.toString().equals("上海轨道交通维护保障中心")){
							contract.setContractOwnerExecuteId("2545");
						}else if(cell.toString().equals("上海轨道交通运营管理中心")){
							contract.setContractOwnerExecuteId("2540");
						}
					}
					
					cell = row.getCell(16);			//16,Q.对方单位
					if(cell!=null && !"".equals(cell.toString())){
						contract.setBuildSupplierName(cell.toString());
					}
					cell = row.getCell(17);			//17,R.项目编号
					if(cell!=null && !"".equals(cell.toString())){
						contract.setProjectNo(cell.toString());
						List<Project> list = projectService.findByProjectName(cell.toString().trim());
						if(list!=null && list.size()==1) contract.setProjectId(list.get(0).getId());
					}
					
					cell = row.getCell(18);			//18,S.项目名称
					if(cell!=null && !"".equals(cell.toString())){
						contract.setProjectName(cell.toString());
					}
					
					String userName = (String)getSession().get("userName");
					String loginName = (String)getSession().get("loginName");
					if(userName!=null && !"".equals(userName)){
						contract.setRegisterPersonName(userName);
					}
					if(loginName!=null && !"".equals(loginName)){
						contract.setRegisterPersonLoginname(loginName);
					}
					
					if(saveStatus==0){
						contract.setCreateType("excel");
						contract.setRemoved("0");
						contract.setCreateDate(sdf.format(new Date()));
						saveList.add(contract);
					}else if(saveStatus==1){
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);		// 设置背景色为黄色,修改成功
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);
						
						contract.setCreateType("excel");
						saveList.add(contract);
					}else{
						// 创建单元格样式
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.RED.index); // 设置背景色为红色,本条数据无效
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);
					}
				}
				if(!hasError){
					contractService.saveAll(saveList);
				}
			}
		
			
			
			//生产反馈文件
			String feedbackFileName = savePath + File.separator + uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf('.'))+"反馈.xls";

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(feedbackFileName);
				hssfWorkbook.write(fos);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String downloadName = uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf('.'))+"反馈.xls";
			createJSonData(downloadName);
		} catch (FileNotFoundException e) {
			uploadify.delete();
		} catch (IOException e) {
			uploadify.delete();
		}
		
		return AJAX;
	}
	
	
	/**
	 * 下载反馈文件
	 * @return
	 */
	public String downloadFile(){
		
		String fileName = ServletActionContext.getRequest().getParameter("fileName");
		Properties properties = loadProperties("config.properties");
		String saveDir = properties.getProperty("saveDir");
		String downloadPath = saveDir;

		String fullPath = downloadPath + File.separator + fileName;
		InputStream is = null;
		try {
			is = new FileInputStream(fullPath);

			int len = 0;
			byte[] buffers = new byte[1024];
			this.getServletResponse().setCharacterEncoding("utf-8");
			this.getServletResponse().reset();
			this.getServletResponse().setContentType("application/x-msdownload");

			this.getServletResponse().addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream os = this.getServletResponse().getOutputStream();
			// 把文件内容通过输出流打印到页面上供下载
			while ((len = is.read(buffers)) != -1) {
				os.write(buffers, 0, len);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	
	
	
	/**
	 * 跳转到批量导入页面
	 * @return
	 */
	public String showBatchUpload(){
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到批量导入页面
	 * @return
	 */
	public String showBatchUploadCustomized(){
		
		return SUCCESS;
	}
	
	/**
	 * 上传附件
	 * @return
	 */
	public String fileUpload(){

		Attach attach = new Attach();
		Properties properties = loadProperties("config.properties");
		String saveDir = properties.getProperty("saveDir");
		
		File savePath = new File(saveDir);
		if(!savePath.exists()){
			savePath.mkdirs();
		}
		
		String saveFileName = saveDir+File.separator+sdf2.format(new Date())+"-"+(new Random().nextInt(10000))+".dat";
		File newFile = new File(saveFileName);
		uploadify.renameTo(newFile);		//将文件移到制定位置并且改名
		
		String fileName = uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf("."));
		String fileExt = uploadifyFileName.substring(uploadifyFileName.lastIndexOf(".")+1,uploadifyFileName.length());
		attach.setFilename(fileName);
		attach.setFileextname(fileExt);
		attach.setFilesize(newFile.length());
		attach.setPath(saveDir);
		//attach.setUploader("");		//上传人
		attach.setUploaddate(sdf.format(new Date()));
		attach.setRemoved(0l);
		//attach.setOperator("");			//操作人
		attach.setSavefilename(saveFileName);
		//attach.setOperateTime(sdf.format(new Date()));		//数据库中设计的字段是number类型的
		attach.setAppname("investCost");
		//attach.setUploaderLoginName("");		//登陆的人
//		attach.setStatus("upload");
		
		attachService.saveAttach(attach);
		
		String jsonData = VOUtils.getJsonData(attach);
		createJSonData(jsonData);
		
		return AJAX;
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
	 * 模糊搜索
	 */
	public String findByContractNoAndTypeWithFuzzySearch(){
		String contractNo = ServletActionContext.getRequest().getParameter("contractNo");
		String type = ServletActionContext.getRequest().getParameter("contractType");
		List<Contract> list = contractService.findByContractNoWithFuzzySearch(contractNo,type);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 模糊搜索
	 */
	public String findByContractNameAndTypeWithFuzzySearch(){
		String contractName = ServletActionContext.getRequest().getParameter("contractName");
		String type = ServletActionContext.getRequest().getParameter("contractType");
		List<Contract> list = contractService.findByContractNameWithFuzzySearch(contractName,type);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}

	/**
	 * 查询列表页面
	 * @throws UnsupportedEncodingException 
	 * @throws DocumentException 
	 */
	public String findContractByPageToBeComplemented() throws UnsupportedEncodingException, DocumentException {
		
		String onLineContractOwnerExecuteId = "";
		String current = servletRequest.getParameter("current");
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
			
			Node deptIdNode = rootElem.selectSingleNode("//deptId");
			if(deptIdNode!=null){
				String dataParams2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptIdNode.getText() + "</id></params>";
				String pidXml = getUserInfoThroughCA(appName,token,"getNodesInfo",secret,"xml",dataParams2);
				document = DocumentHelper.parseText(pidXml);
				rootElem = document.getRootElement();
				Node pidNode = rootElem.selectSingleNode("//pid");
				String companyId = pidNode.getText();
				String companyName = companyMap.get(companyId);
				if(companyName!=null && !"".equals(companyName)){	//在该公司下
					onLineContractOwnerExecuteId = companyId;
					servletRequest.setAttribute("onLineContractOwnerExecuteId", onLineContractOwnerExecuteId);
				}
			}
		}
		
		
		String nomodify = servletRequest.getParameter("nomodify");
		servletRequest.setAttribute("nomodify", nomodify);
		
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		
		String findType = servletRequest.getParameter("findType");		//notin：不在该executeId中或者为null
		servletRequest.setAttribute("findType", findType);
		String showAll = ServletActionContext.getRequest().getParameter("showAll");
		servletRequest.setAttribute("showAll", showAll);
		String hasQuantities = ServletActionContext.getRequest().getParameter("hasQuantities");		//是否有工程量清单
		ServletActionContext.getRequest().setAttribute("hasQuantities", hasQuantities);
		String pageNo = servletRequest.getParameter("pageNo");
		String companyId = ServletActionContext.getRequest().getParameter("companyId");
		servletRequest.setAttribute("companyId", companyId);
		if(companyId!=null && !"".equals(companyId)){
			if(!"center".equals(companyId)){
				contract.setContractOwnerExecuteId(companyId);
			}
		}
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		
		String companyType = ServletActionContext.getRequest().getParameter("companyType");
		ServletActionContext.getRequest().setAttribute("companyType", companyType);
		String cType = servletRequest.getParameter("cType");
		servletRequest.setAttribute("cType", cType);
		
		List<DeptTree> dept1TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept2TreeList = new ArrayList<DeptTree>();
		List<DeptTree> dept3TreeList = new ArrayList<DeptTree>();
		List<String> typeAllIdList = new ArrayList<String>();
		
		List<String> projectCompanyIdList = new ArrayList<String>();  //项目公司idlist
		List<String> controlAndMaintainIdList = new ArrayList<String>(); //运营公司+维保公司idlist
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
		for(int i=0; i<dept1TreeList.size(); i++){
			projectCompanyIdList.add(dept1TreeList.get(i).getId());
		}
		for(int i=0; i<dept2TreeList.size(); i++){
			controlAndMaintainIdList.add(dept2TreeList.get(i).getId());
		}
		for(int i=0; i<dept3TreeList.size(); i++){
			controlAndMaintainIdList.add(dept3TreeList.get(i).getId());
		}
		/*********项目公司,甲方执行数据*********/
		if(cType!=null && !"".equals(cType) && !"3".equals(cType)){
			
			/**项目公司数据**/
			if(dept1TreeList!=null && dept1TreeList.size()>0){
				int type1CountAll = 0;
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type1IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept1TreeList.size(); i++){
						type1IdList.add(dept1TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company1")){
					typeAllIdList.addAll(type1IdList);
				}
				
				List<String[]> resultList1 = new ArrayList<String[]>();
				Map<String, String> typeMap = typeMap = contractService.findCountGroupByAddDeptIds(type1IdList,cType);
				
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
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type2IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept2TreeList.size(); i++){
						type2IdList.add(dept2TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company2")){
					typeAllIdList.addAll(type2IdList);
				}
				List<String[]> resultList2 = new ArrayList<String[]>();
				Map<String, String> typeMap = contractService.findCountGroupByAddDeptIds(type2IdList,cType);
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
				if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
					type3IdList.add(onLineContractOwnerExecuteId);
				}else{
					for(int i=0; i<dept3TreeList.size(); i++){
						type3IdList.add(dept3TreeList.get(i).getId());
					}
				}
				if((companyType==null || "".equals(companyType)) || companyType.equals("company3")){
					typeAllIdList.addAll(type3IdList);
				}
				List<String[]> resultList3 = new ArrayList<String[]>();
				Map<String, String> typeMap = contractService.findCountGroupByAddDeptIds(type3IdList,cType);
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
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.contract);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.contract, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(companyId!=null && !"".equals(companyId)){
			if(!"center".equals(companyId)){
				filter.put("contractOwnerExecuteId", companyId);
			}
		}
		if(onLineContractOwnerExecuteId!=null && !"".equals(onLineContractOwnerExecuteId)){
			filter.put("contractOwnerExecuteId", onLineContractOwnerExecuteId);
		}
		filter.put("removed", "0");
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			filter.put("contractSignedEndDate", contractSignedEndDate);
		}
		if("2".equals(hasQuantities) || "1".equals(hasQuantities)){
			filter.put("hasQuantities", hasQuantities);
		}
		if(cType!=null && !"".equals(cType) && (contract.getContractType()==null || "".equals(contract.getContractType()))){
			filter.put("contractType", cType);
		}
		String complemented = servletRequest.getParameter("complemented");
		if(current!=null && !"".equals(current)){
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf3.parse(current));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
			filter.put("contractSignedDate",sdf3.format(c.getTime()));
			filter.put("contractSignedEndDate",current);
			contract.setContractSignedDate(sdf3.format(c.getTime()));
//			servletRequest.setAttribute("contractSignedDate", sdf3.format(c.getTime()));
			servletRequest.setAttribute("contractSignedEndDate", current);
		}else{
			if(complemented==null || !"no".equals(complemented)){
				filter.put("complemented", "yes");
			}
		}
		servletRequest.setAttribute("complemented", complemented);
		String export = servletRequest.getParameter("export");
		if(export!=null && "yes".equals(export)){
			size = 20000;
			pageNo="1";
		}
		Page page = null;
		/*****页面显示list区域的数据*****/
		if(findType!=null && !"".equals(findType) && "notin".equals(findType)){
			page = contractService.findContractByPageWithoutExecuteId(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}else {
			if(companyId.equals("center")){
				typeAllIdList.add("2718");
				typeAllIdList.add("2719");
				typeAllIdList.add("2720");
				typeAllIdList.add("2721");
				typeAllIdList.add("2722");
				typeAllIdList.add("2545");
			}
			page = contractService.findContractByPage(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		String projectId = this.getServletRequest().getParameter("projectId");
		ServletActionContext.getRequest().setAttribute("projectId", projectId);
		
		/***查询合同分类的数据***/
		if(cType!=null && cType.equals("1")){	//建设类
			List<String> type1List = new ArrayList<String>();
			for(int i=1; i<=8; i++){
				type1List.add("1,"+i);
			}
			List<Object[]> countResultType1  = null;
			if(findType!=null && findType.equals("notin")){
				countResultType1 = contractService.findCountByContractTypeWithoutExecuteId(type1List,filter,typeAllIdList);
			}else{
				countResultType1 = contractService.findCountByContractType(type1List,filter,typeAllIdList);
			}
			
			Map<String, String> map1 = new TreeMap<String, String>();
			int type1All = 0;
			if(countResultType1!=null && countResultType1.size()>0){
				for(int i=0; i<countResultType1.size(); i++){
					map1.put(countResultType1.get(i)[0].toString(), countResultType1.get(i)[1].toString());
					type1All += Integer.valueOf(countResultType1.get(i)[1].toString());
				}
			}
			for(int i=1; i<=8; i++){
				if(map1.get("1,"+i)==null){
					map1.put(("1,"+i), "0");
				}
			}
			servletRequest.setAttribute("map1", map1);
			servletRequest.setAttribute("countResultTypeAll", type1All);
		}else if(cType!=null && cType.equals("2")){	//运维类
			List<String> type2List = new ArrayList<String>();
			for(int i=1; i<=3; i++){
				type2List.add("2,"+i);
			}
			List<Object[]> countResultType2  = null;
			if(findType!=null && "notin".equals(findType)){
				countResultType2 = contractService.findCountByContractTypeWithoutExecuteId(type2List,filter,typeAllIdList);
			}else{
				countResultType2 = contractService.findCountByContractType(type2List,filter,typeAllIdList);
			}
			
			Map<String, String> map2 = new TreeMap<String, String>();
			int type2All = 0;
			if(countResultType2!=null && countResultType2.size()>0){
				for(int i=0; i<countResultType2.size(); i++){
					map2.put(countResultType2.get(i)[0].toString(), countResultType2.get(i)[1].toString());
					type2All += Integer.valueOf(countResultType2.get(i)[1].toString());
				}
			}
			for(int i=1; i<=3; i++){
				if(map2.get("2,"+i)==null){
					map2.put(("2,"+i), "0");
				}
			}
			servletRequest.setAttribute("map2", map2);
			servletRequest.setAttribute("countResultTypeAll", type2All);
		}else if(cType!=null && cType.equals("3")){	//其他类
			List<String> type3List = new ArrayList<String>();
			type3List.add("3,1");
			List<Object[]> countResultType3 = contractService.findCountByContractType(type3List,filter,typeAllIdList);
			Map<String, String> map3 = new TreeMap<String, String>();
			int type3All = 0;
			if(countResultType3!=null && countResultType3.size()>0){
				for(int i=0; i<countResultType3.size(); i++){
					map3.put(countResultType3.get(i)[0].toString(), countResultType3.get(i)[1].toString());
					type3All += Integer.valueOf(countResultType3.get(i)[1].toString());
				}
			}
			map3.put("3","1");
			servletRequest.setAttribute("map3", map3);
			servletRequest.setAttribute("countResultTypeAll", type3All);
		}
		Long otherType1 = contractService.findSumOfContractByTypeWithoutExecuteId("1,",projectCompanyIdList);	 		//建设类-甲方执行-其他
		Long otherType2 = contractService.findSumOfContractByTypeWithoutExecuteId("2,",controlAndMaintainIdList);	 		//运维类-甲方执行-其他
		servletRequest.setAttribute("otherType1", otherType1);
		servletRequest.setAttribute("otherType2", otherType2);
		/***查询合同分类的数据end***/
		
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=contractExport.xls"); 
			List<Contract> list = page.getResult();
			for (int i = 0; i < list.size(); i++) {
				Contract tempContract = list.get(i);
				Object[] params = new Object[21];
				params[0] = tempContract.getContractName();
				params[1] = tempContract.getContractNo();
				params[2] = tempContract.getSelfNo();
				params[3] = tempContract.getContractPrice();
				
				if(params[3]!=null && !"".equals(params[3])){
					params[4] = Double.valueOf(tempContract.getContractPrice()) + contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");					//变更后合同价
				}else{
					params[4] = contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");
				}
				params[5] = tempContract.getFinalPrice();		//投资监理审价
				params[6] = tempContract.getVerifyPrice();		//审价单位审价
				params[7] = tempContract.getNationVerifyPrice();		//国家审计审价
				params[8] = tempContract.getContractOwnerExecuteName();
				params[9] = tempContract.getBuildSupplierName();
				params[10] = tempContract.getPayType();
				
				params[11] = tempContract.getContractPassedDate();	//合同审批通过时间
				params[12] = tempContract.getContractSignedDate();	//合同签约时间
				params[13] = tempContract.getContractStartDate();	//合同开始时间
				params[14] = tempContract.getContractEndDate();		//合同结束时间
				params[15] = tempContract.getContractDestoryDate();	//合同销号日期
				
				if(tempContract.getContractStatus()!=null && !"".equals(tempContract.getContractStatus()!=null)){
					if(tempContract.getContractStatus().equals("0")){
						params[16] = "已备案";
					}else if(tempContract.getContractStatus().equals("1")){
						params[16] = "实施中";
					}else if(tempContract.getContractStatus().equals("2")){
						params[16] = "已竣工";
					}else if(tempContract.getContractStatus().equals("3")){
						params[16] = "已销号";
					}
				}else{
					params[16] = null;
				}
				
				
				if (list.get(i).getContractType() != null) {
					if (list.get(i).getContractType().equals("1,")) {
						params[17] = "建设类-全部";
					} else if (list.get(i).getContractType().equals("1,1")) {
						params[17] = "建设类-G";
					} else if (list.get(i).getContractType().equals("1,2")) {
						params[17] = "建设类-K";
					} else if (list.get(i).getContractType().equals("1,3")) {
						params[17] = "建设类-S";
					} else if (list.get(i).getContractType().equals("1,4")) {
						params[17] = "建设类-Q";
					} else if (list.get(i).getContractType().equals("1,5")) {
						params[17] = "建设类-q";
					} else if (list.get(i).getContractType().equals("1,6")) {
						params[17] = "建设类-J";
					} else if (list.get(i).getContractType().equals("1,7")) {
						params[17] = "建设类-Y";
					} else if (list.get(i).getContractType().equals("1,8")) {
						params[17] = "建设类-Z";
					} else if (list.get(i).getContractType().equals("2,")) {
						params[17] = "运维类-全部";
					} else if (list.get(i).getContractType().equals("2,1")) {
						params[17] = "运维类-服务";
					} else if (list.get(i).getContractType().equals("2,2")) {
						params[17] = "运维类-工程";
					} else if (list.get(i).getContractType().equals("2,3")) {
						params[17] = "运维类-货物";
					}else if(list.get(i).getContractType().equals("3,3")){
						params[17] = "其他类-其他类";
					}
				} else {
					params[17] = null;
				}
				if (list.get(i).getInviteBidType() != null) {
					if (list.get(i).getInviteBidType().equals("1")) {
						params[18] = "公开招标";
					} else if (list.get(i).getInviteBidType().equals("2")) {
						params[18] = "采购平台";
					} else if (list.get(i).getInviteBidType().equals("3")) {
						params[18] = "直接委托";
					}
				} else{
					params[18] = null;
				}
				params[19] = list.get(i).getProjectNo();
				params[20] = list.get(i).getProjectName();
				/*if(list.get(i).getContractGrouping()!=null && !"".equals(list.get(i).getContractGrouping())){
					if("1".equals(list.get(i).getContractGrouping())){
						params[21] ="成本内";
					}else if("2".equals(list.get(i).getContractGrouping())){
						params[21] ="成本外";
					}
				}else{
					params[21] = "";
				}*/
				dataset.add(params);
			}
			
			String[] headers = {"合同名称","合同编号","自有编号","合同价(万元)","变更后合同价(万元)","投资监理审价","审价单位审价","国家审计审价","甲方（执行）","对方单位","支付方式","合同审批通过时间","合同签约时间","合同开始时间","合同结束时间","合同销号日期","合同状态","合同分类","招标方式","项目编号","项目名称"};
			short[] width = {10000,10000,10000,5000,5000,5000,5000,10000,10000,3000,4000,4000,4000,3000,7000,4000,10000,10000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同数据导出", headers, dataset, out,width); 
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
		
		CaRestAPIService caService = new CaRestAPIService();
		CurrentLogin currentLogin = caService.getCurrentLoginInfoFromCa(servletRequest, servletResponse);
		String loginName  = currentLogin.getCookies().get("loginName");
		
		String groupName = loginName.substring(0,loginName.length()-4).trim();
		List<Map<String, Object>> group = contractService.findUserGroupByName(groupName);
		String isShow = "";
		//2508 -- 合约管理部
		if("2508".equals(currentLogin.getCompanyId()) || group != null){
			isShow = "show";
		} else {
			isShow = "hide";
		}
		servletRequest.setAttribute("isShow", isShow);
		
		String contractType = servletRequest.getParameter("contractType");servletRequest.setAttribute("contractType", contractType);		//接收参数: '1,' ,  '2,'  ,  '3,'
		String companyId = servletRequest.getParameter("companyId");servletRequest.setAttribute("companyId", companyId);
		String condition = servletRequest.getParameter("condition"); servletRequest.setAttribute("condition", condition);
		
		String inviteBidType = servletRequest.getParameter("inviteBidType");
		String contractStatus = servletRequest.getParameter("contractStatus");
		//分页搜索条件
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("removed", 0);
		if(contractType!=null && !"".equals(contractType)){
			filter.put("contractType", contractType);
		}
		if(contract.getContractNo()!=null && !"".equals(contract.getContractNo())){
			filter.put("contractNo", contract.getContractNo());
		}
		if(contract.getSelfNo()!=null && !"".equals(contract.getSelfNo())){
			filter.put("selfNo", contract.getSelfNo());
		}
		if(contract.getContractOwnerExecuteName()!=null && !"".equals(contract.getContractOwnerExecuteName())){
			filter.put("contractOwnerExecuteName",contract.getContractOwnerExecuteName());
		}
		if(contract.getContractName()!=null && !"".equals(contract.getContractName())){
			filter.put("contractName", contract.getContractName());
		}
		if(contract.getContractStatus()!=null && !"".equals(contract.getContractStatus())){
			filter.put("contractStatus", contract.getContractStatus());
		}
		if(contract.getContractSignedDate()!=null && !"".equals(contract.getContractSignedDate())){
			filter.put("contractSignedDate", contract.getContractSignedDate());
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
			filter.put("contractSignedEndDate", contractSignedEndDate);
		}
		if(contract.getContractPrice()!=null && !"".equals(contract.getContractPrice())){
			filter.put("contractPrice", contract.getContractPrice());
		}
		if(StringUtils.isNotEmpty(inviteBidType)){
			filter.put("inviteBidType", inviteBidType);
		}
		if(StringUtils.isNotEmpty(contractStatus)){
			filter.put("contractStatus", contractStatus);
		}
		
		String pageNo = servletRequest.getParameter("pageNo");
		String showOrHide = servletRequest.getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide)){
			showOrHide ="hide";
		}
		servletRequest.setAttribute("showOrHide", showOrHide);
		
		//1.根据合同类型查询结果集阶段
		//List<Contract> firstResult = contractService.findByContractType(contractType);
		List<Contract> firstResult = contractService.findByFilter(filter);
		List<Contract> secondResult = null;
		List<Contract> finalResult = new ArrayList<Contract>();
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		
		//2.处理condition阶段
		if(condition!=null && !"".equals(condition)){
			if(firstResult!=null && firstResult.size()>0){
				secondResult = new ArrayList<Contract>();
				for(int i=0; i<firstResult.size(); i++){
					Contract temp = firstResult.get(i);
					if(companyId!=null && !"".equals(companyId)){		//传递的公司id不为空
						if("center".equals(companyId)){
							String executeId = temp.getContractOwnerExecuteId();
							if(!"2718".equals(executeId) && !"2719".equals(executeId) && !"2720".equals(executeId) && !"2721".equals(executeId) && !"2722".equals(executeId) && !"2745".equals(executeId)){
								continue;
							}
						}else{
							if(temp.getContractOwnerExecuteId()==null || "".equals(companyId) || !companyId.equals(temp.getContractOwnerExecuteId())){	//返回值list中公司的id为空，跳出本次循环，继续下次
								continue;
							}
						}
					}
					if("condition5".equals(condition)){		//合同先执行后签订
						if(temp.getContractSignedDate()==null || "".equals(temp.getContractSignedDate()) || temp.getContractStartDate()==null || "".equals(temp.getContractStartDate())){
							continue;
						}else{
							try {
								if(sdf3.parse(temp.getContractSignedDate()).after(sdf3.parse(temp.getContractStartDate()))){
									secondResult.add(temp);
								}
							} catch (ParseException e) {
								//e.printStackTrace();
								continue;
							}
						}
					}else if(("condition6").equals(condition)){		//合同进展风险提示
						String sql ="select t.id from c_progress t where t.object_type ='1' and t.progress_type='1' and t.removed='0' and t.object_id='"+temp.getId()+"'";
						String result = contractService.findOneData(sql); 
						if(result!=null && !"".equals(result) && !"0".equals(result)){
							secondResult.add(temp);
						}else{
							continue;
						}
						
					}else if(("condition7").equals(condition)){	//合同变更超额告警提示
						String sql = "select sum(t.money) from C_CONTRACT_STATUS t where t.removed='0' and  t.type ='1' and t.contract_id='"+temp.getId()+"'";	//该合同的变更金额
						String sumResult = contractService.findOneData(sql);
						if(sumResult!=null && !"".equals(sumResult) && temp.getContractPrice()!=null && !"".equals(temp.getContractPrice()) && sumResult!=null && !"".equals(sumResult)){		//有变更值
							if(Double.valueOf(sumResult) > Double.valueOf(temp.getContractPrice())*0.15){		//变更总金额高于合同价的15%
								secondResult.add(temp);
							}
						}else{
							continue;
						}
					}else if(("condition8").equals(condition)){	 //合同支付超付
						if((temp.getFinalPrice()==null || "".equals(temp.getFinalPrice())) && (temp.getVerifyPrice()==null||"".equals(temp.getVerifyPrice())) 
								&& (temp.getNationVerifyPrice()==null||"".equals(temp.getNationVerifyPrice())) && "单价核算".equals(temp.getPayType())){
							continue;
						}
						if(temp.getContractPrice()==null || "".equals(temp.getContractPrice())){
							continue;
						}
						Double finalPrice=null,verifyPrice=null,nationPrice=null;
						if(temp.getFinalPrice()!=null && !"".equals(temp.getFinalPrice())){
							finalPrice = Double.valueOf(temp.getFinalPrice());
						}
						if(temp.getVerifyPrice()!=null && !"".equals(temp.getVerifyPrice())){
							verifyPrice = Double.valueOf(temp.getVerifyPrice());
						}
						if(temp.getNationVerifyPrice()!=null && !"".equals(temp.getNationVerifyPrice())){
							nationPrice = Double.valueOf(temp.getNationVerifyPrice());
						}
						
						String sql = "select sum(t.money) from C_CONTRACT_STATUS t where t.removed='0' and  t.type ='3' and t.contract_id='"+temp.getId()+"'";	//该合同的实际支付金额
						String actualPay = contractService.findOneData(sql);
						if(actualPay==null || "".equals(actualPay) || "0".equals(actualPay)) continue;		//实际支付为空
						
						Double actual = null,chaned = null;
						if(actualPay!=null && !"".equals(actualPay)){
							actual = Double.valueOf(actualPay);
						}else{
							continue;
						}
						
						if((finalPrice!=null && actual>(0.0001+finalPrice)) || (verifyPrice!=null && actual>(0.0001+verifyPrice)) || (nationPrice!=null && actual>(0.0001+nationPrice))){
							secondResult.add(temp);
						}else{
							String sql2 = "select sum(t.money) from C_CONTRACT_STATUS t where t.removed='0' and  t.type ='1' and t.contract_id='"+temp.getId()+"'";	//该合同变更的金额
							String chanedPay = contractService.findOneData(sql2);
							Double afterChange = 0.0;		//变更后价钱
							if(temp.getContractPrice()!=null && !"".equals(temp.getContractPrice())){
								afterChange = Double.valueOf(temp.getContractPrice());
							}
							  
							if(chanedPay!=null && !"".equals(chanedPay)){
								if(!"0".equals(chanedPay)){
									afterChange = afterChange + Double.valueOf(chanedPay);
								}
							}
							if(afterChange==0.0 && !"单价核算".equals(temp.getPayType()) && actual>(0.0001+afterChange)){
								secondResult.add(temp);
							}
						}
						/*
						if(actualPay==null || "0".equals(actualPay)){
							continue;
						}
						if(chanedPay==null || "".equals(chanedPay) || "0".equals(chanedPay)){		//变更支付为空
							if(Double.valueOf(temp.getContractPrice()) < (Double.valueOf(actualPay)-0.0001)){		//实际支付大于合同价
								secondResult.add(temp);
							}
						}else{
							if(Double.valueOf(temp.getContractPrice()) < (Double.valueOf(chanedPay) - Double.valueOf(actualPay)-0.0001)){
								secondResult.add(temp);
							}
						}*/
						
					}else if("condition10".equals(condition)){		//根据采购方式查询
//						String inviteBidType = servletRequest.getParameter("inviteBidType");	
						String currentYear = servletRequest.getParameter("controlYear");
						servletRequest.setAttribute("inviteBidType", inviteBidType);
						servletRequest.setAttribute("controlYear", currentYear);
//						if(temp.getInviteBidType()!=null && temp.getInviteBidType().equals(inviteBidType)){
							if(temp.getContractSignedDate()!=null && temp.getContractSignedDate().contains(currentYear)){
								secondResult.add(temp);
							}
//						}
					}else if("condition11".equals(condition)){		//根据合同状态查询
						//String contractStatus = servletRequest.getParameter("contractStatus");
						String currentYear = servletRequest.getParameter("controlYear");
						servletRequest.setAttribute("contractStatus", contractStatus);
						servletRequest.setAttribute("controlYear", currentYear);
						//if(temp.getContractStatus()!=null && temp.getContractStatus().equals(contractStatus)){
							if(temp.getContractSignedDate()!=null && temp.getContractSignedDate().contains(currentYear)){
								secondResult.add(temp);
							}
						//}
					}else if("condition12".equals(condition)){		//根据合同变更查询
						String currentYear = servletRequest.getParameter("controlYear");
						servletRequest.setAttribute("controlYear", currentYear);
						if(contractStatusService.isResultExist(currentYear, temp.getId(), "1")){
							secondResult.add(temp);
						}
					}
					
				}	
				
			}
		}
		
		if(secondResult==null){
			secondResult = new ArrayList<Contract>();
		}
		totalSize  = secondResult.size();
		if(secondResult.size() % pSize==0){
			totalPageCount = secondResult.size() / pSize;
		}else{
			totalPageCount = secondResult.size() / pSize + 1;
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
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=contractExport.xls"); 
			List<Contract> list = secondResult;
			for (int i = 0; i < list.size(); i++) {
				Contract tempContract = list.get(i);
				Object[] params = new Object[21];
				params[0] = tempContract.getContractName();
				params[1] = tempContract.getContractNo();
				params[2] = tempContract.getSelfNo();
				params[3] = tempContract.getContractPrice();
				
				if(params[3]!=null && !"".equals(params[3])){
					params[4] = Double.valueOf(tempContract.getContractPrice()) + contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");					//变更后合同价
				}else{
					params[4] = contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");
				}
				params[5] = tempContract.getFinalPrice();		//投资监理审价
				params[6] = tempContract.getVerifyPrice();		//审价单位审价
				params[7] = tempContract.getNationVerifyPrice();		//国家审计审价
				params[8] = tempContract.getContractOwnerExecuteName();
				params[9] = tempContract.getBuildSupplierName();
				params[10] = tempContract.getPayType();
				
				params[11] = tempContract.getContractPassedDate();	//合同审批通过时间
				params[12] = tempContract.getContractSignedDate();	//合同签约时间
				params[13] = tempContract.getContractStartDate();	//合同开始时间
				params[14] = tempContract.getContractEndDate();		//合同结束时间
				params[15] = tempContract.getContractDestoryDate();	//合同销号日期
				
				if(tempContract.getContractStatus()!=null && !"".equals(tempContract.getContractStatus()!=null)){
					if(tempContract.getContractStatus().equals("0")){
						params[16] = "已备案";
					}else if(tempContract.getContractStatus().equals("1")){
						params[16] = "实施中";
					}else if(tempContract.getContractStatus().equals("2")){
						params[16] = "已竣工";
					}else if(tempContract.getContractStatus().equals("3")){
						params[16] = "已销号";
					}
				}else{
					params[16] = null;
				}
				
				
				if (list.get(i).getContractType() != null) {
					if (list.get(i).getContractType().equals("1,")) {
						params[17] = "建设类-全部";
					} else if (list.get(i).getContractType().equals("1,1")) {
						params[17] = "建设类-G";
					} else if (list.get(i).getContractType().equals("1,2")) {
						params[17] = "建设类-K";
					} else if (list.get(i).getContractType().equals("1,3")) {
						params[17] = "建设类-S";
					} else if (list.get(i).getContractType().equals("1,4")) {
						params[17] = "建设类-Q";
					} else if (list.get(i).getContractType().equals("1,5")) {
						params[17] = "建设类-q";
					} else if (list.get(i).getContractType().equals("1,6")) {
						params[17] = "建设类-J";
					} else if (list.get(i).getContractType().equals("1,7")) {
						params[17] = "建设类-Y";
					} else if (list.get(i).getContractType().equals("1,8")) {
						params[17] = "建设类-Z";
					} else if (list.get(i).getContractType().equals("2,")) {
						params[17] = "运维类-全部";
					} else if (list.get(i).getContractType().equals("2,1")) {
						params[17] = "运维类-服务";
					} else if (list.get(i).getContractType().equals("2,2")) {
						params[17] = "运维类-工程";
					} else if (list.get(i).getContractType().equals("2,3")) {
						params[17] = "运维类-货物";
					}else if(list.get(i).getContractType().equals("3,3")){
						params[17] = "其他类-其他类";
					}
				} else {
					params[17] = null;
				}
				if (list.get(i).getInviteBidType() != null) {
					if (list.get(i).getInviteBidType().equals("1")) {
						params[18] = "公开招标";
					} else if (list.get(i).getInviteBidType().equals("2")) {
						params[18] = "采购平台";
					} else if (list.get(i).getInviteBidType().equals("3")) {
						params[18] = "直接委托";
					}
				} else{
					params[18] = null;
				}
				params[19] = list.get(i).getProjectNo();
				params[20] = list.get(i).getProjectName();
				/*if(list.get(i).getContractGrouping()!=null && !"".equals(list.get(i).getContractGrouping())){
					if("1".equals(list.get(i).getContractGrouping())){
						params[21] ="成本内";
					}else if("2".equals(list.get(i).getContractGrouping())){
						params[21] ="成本外";
					}
				}else{
					params[21] = "";
				}*/
				dataset.add(params);
			}
			
			String[] headers = {"合同名称","合同编号","自有编号","合同价(万元)","变更后合同价(万元)","投资监理审价","审价单位审价","国家审计审价","甲方（执行）","对方单位","支付方式","合同审批通过时间","合同签约时间","合同开始时间","合同结束时间","合同销号日期","合同状态","合同分类","招标方式","项目编号","项目名称"};
			short[] width = {10000,10000,10000,5000,5000,5000,5000,10000,10000,3000,4000,4000,4000,3000,7000,4000,10000,10000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同数据导出", headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return SUCCESS;
	}
	
	
	public String findContractBySelfNoWithFuzzySearch(){
		String selfNo = servletRequest.getParameter("selfNo");
		if(selfNo!=null) selfNo = selfNo.trim();
		List<Contract> list = contractService.findContractBySelfNoWithFuzzySearch(selfNo);
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		
		return AJAX;
	}
	

	/**
	 * 新合同首页点击后显示
	 * @return
	 */
	public String findByNewCondition(){
		String condition = servletRequest.getParameter("condition"); servletRequest.setAttribute("condition", condition);
		String companyId = servletRequest.getParameter("companyId");
		
		String contractStatus = servletRequest.getParameter("contractStatus");
		//分页搜索条件
		String contractFilter="";
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("removed", 0);
		
		if(StringUtils.isNotEmpty(companyId)){
			contractFilter +=" and c.contractOwnerExecuteId = '"+companyId+"'";
		}
		if(contract.getContractNo()!=null && !"".equals(contract.getContractNo())){
			contractFilter +=" and c.contractNo like '%"+contract.getContractNo()+"%'";
		}
		if(contract.getSelfNo()!=null && !"".equals(contract.getSelfNo())){
			contractFilter += " and c.selfNo like '%"+contract.getSelfNo()+"%'";
		}
		if(contract.getContractOwnerExecuteName()!=null && !"".equals(contract.getContractOwnerExecuteName())){
			contractFilter +=" and c.contractOwnerExecuteName like '%"+contract.getContractOwnerExecuteName()+"%'";
		}
		if(contract.getContractName()!=null && !"".equals(contract.getContractName())){
			contractFilter +=" and c.contractName like '%"+contract.getContractName()+"%'";
		}
		if(contract.getContractStatus()!=null && !"".equals(contract.getContractStatus())){
			contractFilter += " and c.contractStatus = '"+contract.getContractStatus()+"'";
		}
		if(contract.getContractSignedDate()!=null && !"".equals(contract.getContractSignedDate())){
			contractFilter += " and c.contractSignedDate >= '"+contract.getContractSignedDate()+"'";
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			contractFilter += " and c.contractSignedDate <= '"+contractSignedEndDate+"'";
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		}
		
		String pageNo = servletRequest.getParameter("pageNo");
		String showOrHide = servletRequest.getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide)){
			showOrHide ="hide";
		}
		servletRequest.setAttribute("showOrHide", showOrHide);
		
		//1.根据合同类型查询结果集阶段
		//List<Contract> firstResult = contractService.findByFilter(filter);
		List<Contract> secondResult = null;
		List<Contract> finalResult = new ArrayList<Contract>();
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		
		//2.处理condition阶段
		if(condition!=null && !"".equals(condition)){
				secondResult = new ArrayList<Contract>();
				
				if("condition4".equals(condition)){		//合同先执行后签订风险提示
					String hql="from Contract c where c.removed=0 and c.contractStartDate <  c.contractSignedDate";
					if(!"".equals(contractFilter)) hql+=contractFilter;
					secondResult = contractService.findByHql(hql);
				}else if("condition7".equals(condition)){		//合同进展信息风险提示
					String hql="from Contract c where c.id in(select t.objectId from Progress t,Contract c where t.objectType ='1' and t.progressType='1' and t.removed='0' "
						+ " and t.objectId = c.id and c.removed='0' group by t.objectId)";
					if(!"".equals(contractFilter)) hql+= contractFilter;
					secondResult = contractService.findByHql(hql);
				}else if("condition5".equals(condition)){		//合同变更超额告警提示,变更总金额高于合同价的15%
					String hql="from Contract c where c.removed='0' and c.id in (select t.contractId from ContractStatus t,Contract c where t.type =1 and t.removed='0' " +
						" and t.contractId = c.id and c.removed='0' group by t.contractId)";
					String sql="select t.contract_id,nvl(sum(t.money),0) from C_CONTRACT_STATUS t,C_CONTRACT c where t.type =1 and t.removed='0' " +
							" and t.contract_id = c.id and c.removed='0' group by t.contract_id";
					if(!"".equals(contractFilter)) hql += contractFilter;
					List<Contract> contractList = contractService.findByHql(hql);
					Map<String, Contract> contractMap = new HashMap<String, Contract>();
					if(contractList!=null && contractList.size()>0){
						for(int i=0;i<contractList.size();i++){
							contractMap.put(contractList.get(i).getId(), contractList.get(i));
						}
					}
					List<Object[]> idMoneyList = contractService.findBySQL(sql);
					if(idMoneyList!=null && idMoneyList.size()>0){
						for(int i=0;i<idMoneyList.size();i++){
							String contractId = idMoneyList.get(i)[0].toString();
							double changedMoney = Double.valueOf(idMoneyList.get(i)[1].toString());
							if(contractMap.get(contractId)!=null){
								double contractPrice = Double.valueOf(contractMap.get(contractId).getContractPrice());
								if(changedMoney > contractPrice * 0.15){
									secondResult.add(contractMap.get(contractId));
								}
							}
						}
					}
				}else if("condition6".equals(condition)){
					String hql="from Contract c where c.removed='0' and (c.payType!='单价核算' or c.payType is null)";
					String sql="select t.contract_id,nvl(sum(t.money),0) from C_CONTRACT_STATUS t,c_contract c where t.type ='3' and t.removed='0' " +
						" and t.contract_id=c.id and c.removed='0' group by t.contract_id";		//实际支付
					String sql2="select t.contract_id,nvl(sum(t.money),0) from C_CONTRACT_STATUS t,c_contract c where t.type =1 and t.removed='0' " +
						" and c.removed='0' and t.contract_id=c.id group by t.contract_id";		//变更支付
					if(!"".equals(contractFilter)) hql += contractFilter;
					List<Contract> contractList = contractService.findByHql(hql);
					Map<String, Contract> contractMap = new HashMap<String, Contract>();
					if(contractList!=null && contractList.size()>0){
						for(int i=0;i<contractList.size();i++){
							contractMap.put(contractList.get(i).getId(), contractList.get(i));
						}
					}
					List<Object[]> actualIdMoneyList = contractService.findBySQL(sql);
					List<Object[]> changedIdMoneyList = contractService.findBySQL(sql2);
					Map<String, Double> changedMoneyMap = new HashMap<String, Double>(); 
					if(changedIdMoneyList!=null && changedIdMoneyList.size()>0){
						for(int i=0;i<changedIdMoneyList.size();i++){
							changedMoneyMap.put(changedIdMoneyList.get(i)[0].toString(), Double.valueOf(changedIdMoneyList.get(i)[1].toString()));
						}
					}
					if(actualIdMoneyList!=null && actualIdMoneyList.size()>0){
						for(int i=0;i<actualIdMoneyList.size();i++){
							String id = actualIdMoneyList.get(i)[0].toString();
							double actualMoney = Double.valueOf(actualIdMoneyList.get(i)[1].toString());
							if(actualMoney==0) continue;	//实际支付为空或为0，不计入	
							
							double finalPrice=0d,verifyPrice=0d,nationVerifyPrice=0d;
							Contract temp = contractMap.get(id);
							
							if(temp!=null){
								if((temp.getFinalPrice()==null || "".equals(temp.getFinalPrice())) && (temp.getVerifyPrice()==null || "".equals(temp.getVerifyPrice())) 
										&& (temp.getNationVerifyPrice()==null || "".equals(temp.getNationVerifyPrice())) && (temp.getContractPrice()!=null && "单价核算".equals(temp.getPayType()))){
									continue;
								}
								if(StringUtils.isNotEmpty(temp.getFinalPrice())){
									finalPrice = Double.valueOf(temp.getFinalPrice());
									if(actualMoney>finalPrice+0.0001){
										secondResult.add(temp);
										continue;
									}
								}
								if(StringUtils.isNotEmpty(temp.getVerifyPrice())){
									verifyPrice = Double.valueOf(temp.getVerifyPrice());
									if(actualMoney>verifyPrice+0.0001){
										secondResult.add(temp);
										continue;
									}
								}
								if(StringUtils.isNotEmpty(temp.getNationVerifyPrice())){
									nationVerifyPrice = Double.valueOf(temp.getNationVerifyPrice());
									if(actualMoney>nationVerifyPrice+0.0001){
										secondResult.add(temp);
										continue;
									}
								}
								double afterChangeMoney=0.0;
								double a = changedMoneyMap.get(id)==null?0d:changedMoneyMap.get(id);
								afterChangeMoney = Double.valueOf(temp.getContractPrice()) + a;
								if(actualMoney>(afterChangeMoney+0.0001) && !"单价核算".equals(temp.getPayType())){
									secondResult.add(temp);
									continue;
								}
							}
							
						}
					}
				}
				
			}
		
		if(secondResult==null){
			secondResult = new ArrayList<Contract>();
		}
		totalSize  = secondResult.size();
		if(secondResult.size() % pSize==0){
			totalPageCount = secondResult.size() / pSize;
		}else{
			totalPageCount = secondResult.size() / pSize + 1;
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
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=contractExport.xls"); 
			List<Contract> list = secondResult;
			for (int i = 0; i < list.size(); i++) {
				Contract tempContract = list.get(i);
				Object[] params = new Object[21];
				params[0] = tempContract.getContractName();
				params[1] = tempContract.getContractNo();
				params[2] = tempContract.getSelfNo();
				params[3] = tempContract.getContractPrice();
				
				if(params[3]!=null && !"".equals(params[3])){
					params[4] = Double.valueOf(tempContract.getContractPrice()) + contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");					//变更后合同价
				}else{
					params[4] = contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");
				}
				params[5] = tempContract.getFinalPrice();		//投资监理审价
				params[6] = tempContract.getVerifyPrice();		//审价单位审价
				params[7] = tempContract.getNationVerifyPrice();		//国家审计审价
				params[8] = tempContract.getContractOwnerExecuteName();
				params[9] = tempContract.getBuildSupplierName();
				params[10] = tempContract.getPayType();
				
				params[11] = tempContract.getContractPassedDate();	//合同审批通过时间
				params[12] = tempContract.getContractSignedDate();	//合同签约时间
				params[13] = tempContract.getContractStartDate();	//合同开始时间
				params[14] = tempContract.getContractEndDate();		//合同结束时间
				params[15] = tempContract.getContractDestoryDate();	//合同销号日期
				
				if(tempContract.getContractStatus()!=null && !"".equals(tempContract.getContractStatus()!=null)){
					if(tempContract.getContractStatus().equals("0")){
						params[16] = "已备案";
					}else if(tempContract.getContractStatus().equals("1")){
						params[16] = "实施中";
					}else if(tempContract.getContractStatus().equals("2")){
						params[16] = "已竣工";
					}else if(tempContract.getContractStatus().equals("3")){
						params[16] = "已销号";
					}
				}else{
					params[16] = null;
				}
				
				
				if (list.get(i).getContractType() != null) {
					if (list.get(i).getContractType().equals("1,")) {
						params[17] = "建设类-全部";
					} else if (list.get(i).getContractType().equals("1,1")) {
						params[17] = "建设类-G";
					} else if (list.get(i).getContractType().equals("1,2")) {
						params[17] = "建设类-K";
					} else if (list.get(i).getContractType().equals("1,3")) {
						params[17] = "建设类-S";
					} else if (list.get(i).getContractType().equals("1,4")) {
						params[17] = "建设类-Q";
					} else if (list.get(i).getContractType().equals("1,5")) {
						params[17] = "建设类-q";
					} else if (list.get(i).getContractType().equals("1,6")) {
						params[17] = "建设类-J";
					} else if (list.get(i).getContractType().equals("1,7")) {
						params[17] = "建设类-Y";
					} else if (list.get(i).getContractType().equals("1,8")) {
						params[17] = "建设类-Z";
					} else if (list.get(i).getContractType().equals("2,")) {
						params[17] = "运维类-全部";
					} else if (list.get(i).getContractType().equals("2,1")) {
						params[17] = "运维类-服务";
					} else if (list.get(i).getContractType().equals("2,2")) {
						params[17] = "运维类-工程";
					} else if (list.get(i).getContractType().equals("2,3")) {
						params[17] = "运维类-货物";
					}else if(list.get(i).getContractType().equals("3,3")){
						params[17] = "其他类-其他类";
					}
				} else {
					params[17] = null;
				}
				if (list.get(i).getInviteBidType() != null) {
					if (list.get(i).getInviteBidType().equals("1")) {
						params[18] = "公开招标";
					} else if (list.get(i).getInviteBidType().equals("2")) {
						params[18] = "采购平台";
					} else if (list.get(i).getInviteBidType().equals("3")) {
						params[18] = "直接委托";
					}
				} else{
					params[18] = null;
				}
				params[19] = list.get(i).getProjectNo();
				params[20] = list.get(i).getProjectName();
				/*if(list.get(i).getContractGrouping()!=null && !"".equals(list.get(i).getContractGrouping())){
					if("1".equals(list.get(i).getContractGrouping())){
						params[21] ="成本内";
					}else if("2".equals(list.get(i).getContractGrouping())){
						params[21] ="成本外";
					}
				}else{
					params[21] = "";
				}*/
				dataset.add(params);
			}
			
			String[] headers = {"合同名称","合同编号","自有编号","合同价(万元)","变更后合同价(万元)","投资监理审价","审价单位审价","国家审计审价","甲方（执行）","对方单位","支付方式","合同审批通过时间","合同签约时间","合同开始时间","合同结束时间","合同销号日期","合同状态","合同分类","招标方式","项目编号","项目名称"};
			short[] width = {10000,10000,10000,5000,5000,5000,5000,10000,10000,3000,4000,4000,4000,3000,7000,4000,10000,10000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同数据导出", headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 待补全合同
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public String findNewContractByPageToBeComplemented() throws UnsupportedEncodingException, DocumentException {
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		String pageNo = servletRequest.getParameter("pageNo");
		String export = servletRequest.getParameter("export");
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		String contractFilter="";
		if(contract.getContractNo()!=null && !"".equals(contract.getContractNo())){
			contractFilter +=" and c.contractNo like '%"+contract.getContractNo()+"%'";
		}
		if(contract.getSelfNo()!=null && !"".equals(contract.getSelfNo())){
			contractFilter += " and c.selfNo like '%"+contract.getSelfNo()+"%'";
		}
		if(contract.getContractOwnerExecuteName()!=null && !"".equals(contract.getContractOwnerExecuteName())){
			contractFilter +=" and c.contractOwnerExecuteName like '%"+contract.getContractOwnerExecuteName()+"%'";
		}
		if(contract.getContractName()!=null && !"".equals(contract.getContractName())){
			contractFilter +=" and c.contractName like '%"+contract.getContractName()+"%'";
		}
		if(contract.getContractStatus()!=null && !"".equals(contract.getContractStatus())){
			contractFilter += " and c.contractStatus = '"+contract.getContractStatus()+"'";
		}
		if(contract.getContractSignedDate()!=null && !"".equals(contract.getContractSignedDate())){
			contractFilter += " and c.contractSignedDate >= '"+contract.getContractSignedDate()+"'";
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			contractFilter += " and c.contractSignedDate <= '"+contractSignedEndDate+"'";
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		}
		String hql="from Contract c where c.removed=0 "
				+ " and (c.contractName is null or c.contractNo is null or c.contractPrice is null"
				+ " or c.contractOwnerName is null or c.contractOwnerExecuteName is null or c.buildSupplierName  is null"
				+ " or c.payType is null or c.contractPassedDate is null or c.contractStartDate is null  or c.contractSignedDate is null"
				+ " or c.contractEndDate is null or c.contractStatus is null or c.inviteBidType is null or c.projectName is null)";
		if(StringUtils.isNotEmpty(contractFilter)){
			hql += contractFilter;
		}
		List<Contract> list = contractService.findByHql(hql);
		List<Contract> result = new ArrayList<Contract>();
		if(list==null){
			list = new ArrayList<Contract>();
		}
		totalSize  = list.size();
		if(list.size() % pSize==0){
			totalPageCount = list.size() / pSize;
		}else{
			totalPageCount = list.size() / pSize + 1;
		}
		//3.分页阶段
		if(pageNo==null || "".equals(pageNo)){	//没有页码，加入1-20条数据
			if(list.size()>pSize){	//超过20条数据
				for(int i=0; i<pSize; i++){
					result.add(list.get(i));
				}
			}else{
				result = list;
			}
		}else{		//有页码
			if(Integer.valueOf(pageNo)!=1){
				begin = Integer.valueOf(pageNo)*pSize-pSize;
			}
			if(Integer.valueOf(pageNo)*pSize <= list.size()){		//结果集包含了要查询的集
				for(int i=begin; i<begin+pSize; i++){
					result.add(list.get(i));
				}
			}else{
				for(int i=begin; i<list.size(); i++){
					result.add(list.get(i));
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
		servletRequest.setAttribute("list", result);
		
		
		
		
		/*Page page = null;
		*//*****页面显示list区域的数据*****//*
		if(findType!=null && !"".equals(findType) && "notin".equals(findType)){
			page = contractService.findContractByPageWithoutExecuteId(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}else {
			if(companyId.equals("center")){
				typeAllIdList.add("2718");
				typeAllIdList.add("2719");
				typeAllIdList.add("2720");
				typeAllIdList.add("2721");
				typeAllIdList.add("2722");
				typeAllIdList.add("2545");
			}
			page = contractService.findContractByPage(filter, Integer.valueOf(pageNo), size,typeAllIdList);
		}
		
		ServletActionContext.getRequest().setAttribute("page", page);*/
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		String projectId = this.getServletRequest().getParameter("projectId");
		ServletActionContext.getRequest().setAttribute("projectId", projectId);
		
		
		
		if("yes".equals(export)){//导出excel
			List<Object[]> dataset = new ArrayList<Object[]>();
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=contractExport.xls"); 
			List<Contract> listTemp = list;
			for (int i = 0; i < listTemp.size(); i++) {
				Contract tempContract = listTemp.get(i);
				Object[] params = new Object[21];
				params[0] = tempContract.getContractName();
				params[1] = tempContract.getContractNo();
				params[2] = tempContract.getSelfNo();
				params[3] = tempContract.getContractPrice();
				
				if(params[3]!=null && !"".equals(params[3])){
					params[4] = Double.valueOf(tempContract.getContractPrice()) + contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");					//变更后合同价
				}else{
					params[4] = contractStatusService.findSumOfContractStatusByContractIdAndType(tempContract.getId(), "1");
				}
				params[5] = tempContract.getFinalPrice();		//投资监理审价
				params[6] = tempContract.getVerifyPrice();		//审价单位审价
				params[7] = tempContract.getNationVerifyPrice();		//国家审计审价
				params[8] = tempContract.getContractOwnerExecuteName();
				params[9] = tempContract.getBuildSupplierName();
				params[10] = tempContract.getPayType();
				
				params[11] = tempContract.getContractPassedDate();	//合同审批通过时间
				params[12] = tempContract.getContractSignedDate();	//合同签约时间
				params[13] = tempContract.getContractStartDate();	//合同开始时间
				params[14] = tempContract.getContractEndDate();		//合同结束时间
				params[15] = tempContract.getContractDestoryDate();	//合同销号日期
				
				if(tempContract.getContractStatus()!=null && !"".equals(tempContract.getContractStatus()!=null)){
					if(tempContract.getContractStatus().equals("0")){
						params[16] = "已备案";
					}else if(tempContract.getContractStatus().equals("1")){
						params[16] = "实施中";
					}else if(tempContract.getContractStatus().equals("2")){
						params[16] = "已竣工";
					}else if(tempContract.getContractStatus().equals("3")){
						params[16] = "已销号";
					}
				}else{
					params[16] = null;
				}
				
				
				if (listTemp.get(i).getContractType() != null) {
					if (listTemp.get(i).getContractType().equals("1,")) {
						params[17] = "建设类-全部";
					} else if (listTemp.get(i).getContractType().equals("1,1")) {
						params[17] = "建设类-G";
					} else if (listTemp.get(i).getContractType().equals("1,2")) {
						params[17] = "建设类-K";
					} else if (listTemp.get(i).getContractType().equals("1,3")) {
						params[17] = "建设类-S";
					} else if (listTemp.get(i).getContractType().equals("1,4")) {
						params[17] = "建设类-Q";
					} else if (listTemp.get(i).getContractType().equals("1,5")) {
						params[17] = "建设类-q";
					} else if (listTemp.get(i).getContractType().equals("1,6")) {
						params[17] = "建设类-J";
					} else if (listTemp.get(i).getContractType().equals("1,7")) {
						params[17] = "建设类-Y";
					} else if (listTemp.get(i).getContractType().equals("1,8")) {
						params[17] = "建设类-Z";
					} else if (listTemp.get(i).getContractType().equals("2,")) {
						params[17] = "运维类-全部";
					} else if (listTemp.get(i).getContractType().equals("2,1")) {
						params[17] = "运维类-服务";
					} else if (listTemp.get(i).getContractType().equals("2,2")) {
						params[17] = "运维类-工程";
					} else if (listTemp.get(i).getContractType().equals("2,3")) {
						params[17] = "运维类-货物";
					}else if(listTemp.get(i).getContractType().equals("3,3")){
						params[17] = "其他类-其他类";
					}
				} else {
					params[17] = null;
				}
				if (listTemp.get(i).getInviteBidType() != null) {
					if (listTemp.get(i).getInviteBidType().equals("1")) {
						params[18] = "公开招标";
					} else if (listTemp.get(i).getInviteBidType().equals("2")) {
						params[18] = "采购平台";
					} else if (listTemp.get(i).getInviteBidType().equals("3")) {
						params[18] = "直接委托";
					}
				} else{
					params[18] = null;
				}
				params[19] = listTemp.get(i).getProjectNo();
				params[20] = listTemp.get(i).getProjectName();
				/*if(list.get(i).getContractGrouping()!=null && !"".equals(list.get(i).getContractGrouping())){
					if("1".equals(list.get(i).getContractGrouping())){
						params[21] ="成本内";
					}else if("2".equals(list.get(i).getContractGrouping())){
						params[21] ="成本外";
					}
				}else{
					params[21] = "";
				}*/
				dataset.add(params);
			}
			
			String[] headers = {"合同名称","合同编号","自有编号","合同价(万元)","变更后合同价(万元)","投资监理审价","审价单位审价","国家审计审价","甲方（执行）","对方单位","支付方式","合同审批通过时间","合同签约时间","合同开始时间","合同结束时间","合同销号日期","合同状态","合同分类","招标方式","项目编号","项目名称"};
			short[] width = {10000,10000,10000,5000,5000,5000,5000,10000,10000,3000,4000,4000,4000,3000,7000,4000,10000,10000};
			
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("合同数据导出", headers, dataset, out,width); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return SUCCESS;
	}
	
	
	
	//public static void main(String[] args) throws DocumentException{
	public void updateContractEvaluate() throws DocumentException{
		DataCenterUtils dataCenterUtils = new DataCenterUtils();
//		String xml = dataCenterUtils.getDataInfo("wonder", "wonder2013!", "contractEvaluate");
		Map<String,String> result= dataCenterUtils.getDataInfo("wonder", "wonder2013!", "contractEvaluate");
		
		if(result!=null && result.size()>0){
			for(Map.Entry<String, String> entry : result.entrySet()) {
				String xml = entry.getValue();
				String date="";
				String source="";
				String type="";
				if(StringUtils.isEmpty(xml)) continue;
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();
				Attribute dateAttr = root.attribute("date");
				Attribute sourceAttr = root.attribute("source");
				Attribute typeAttr = root.attribute("type");
				if(dateAttr!=null){
					date = dateAttr.getValue();
				}
				if(sourceAttr!=null){
					source = sourceAttr.getValue();
				}
				if(typeAttr!=null){
					type = typeAttr.getValue();
				}
				if(StringUtils.isNotEmpty(type) && "contractEvaluate".equals(type)){
					Node datas = root.selectSingleNode("datas");
					if(datas==null) continue ;
					List<Element> dataList = datas.selectNodes("data"); 
					if(dataList==null || dataList.size()<1) continue ;
					List<Contract> toBeUpdatedList = new ArrayList<Contract>();
					for(int i=0;i<dataList.size();i++){
						Element contract = dataList.get(i);
						Node idNode = contract.selectSingleNode("id");
						Node contractNo = contract.selectSingleNode("contractNo");
						Node contractName = contract.selectSingleNode("contractName");
						Node evaluateNum = contract.selectSingleNode("evaluateNum");
						
						if(idNode!=null && evaluateNum!=null){
							Contract temp = contractService.findContractById(idNode.getText());
							if(temp!=null){
								temp.setEvaluateNum(Long.valueOf(evaluateNum.getText()));
								toBeUpdatedList.add(temp)
								;
							}
						}
					}
					contractService.updateAllContract(toBeUpdatedList);
					
					String confirmResult = dataCenterUtils.confirmGetDataInfo("wonder", "wonder2013!", "contractEvaluate", entry.getKey());
					System.out.println(confirmResult);
					
				} 
			}	
			
		}
		
		
		
	}
	
	
	
	
	
	public void pushContractToDataCenter() throws ParseException{
		Calendar calendar = Calendar.getInstance();			//当前时间
		String date = sdf3.format(new Date());
		
		calendar.set(Calendar.HOUR,calendar.getMinimum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE,calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND,calendar.getMinimum(Calendar.SECOND));
		
		Date endDate = calendar.getTime();
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
		Date startDate = calendar.getTime(); 
		
		List<Contract> contractList = contractService.findAllContractByUpdateDate(sdf.format(startDate),sdf.format(endDate));
		
		if(contractList!=null && contractList.size()>0){
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("root");
			root.addAttribute("date", date);
			root.addAttribute("source","hetong");
			root.addAttribute("type", "contractBasicInfo");
			Element datas = root.addElement("datas");
			for(int i=0;i<contractList.size();i++){
				Contract contract = contractList.get(i);
				Element data = datas.addElement("data");
				//合同基础信息
				Element basic = data.addElement("basic");
				Element id = basic.addElement("id");
					id.setText(contract.getId());
				Element contractNo = basic.addElement("contractNo");
					contractNo.setText(contract.getContractNo()==null?"":contract.getContractNo());
				Element contractName = basic.addElement("contractName");
					contractName.setText(contract.getContractName()==null?"":contract.getContractName());
				Element contractPrice = basic.addElement("contractPrice");
					contractPrice.setText(contract.getContractPrice()==null?"":contract.getContractPrice());
				Element finalPrice = basic.addElement("finalPrice");
					finalPrice.setText(contract.getFinalPrice()==null?"":contract.getFinalPrice());
				Element verifyPrice = basic.addElement("verifyPrice");
					verifyPrice.setText(contract.getVerifyPrice()==null?"":contract.getVerifyPrice());
				Element nationVerifyPrice = basic.addElement("nationVerifyPrice");
					nationVerifyPrice.setText(contract.getNationVerifyPrice()==null?"":contract.getNationVerifyPrice());
				Element contractOwnerName = basic.addElement("contractOwnerName");
					contractOwnerName.setText(contract.getContractOwnerName()==null?"":contract.getContractOwnerName());
				Element contractOwnerExecuteId = basic.addElement("contractOwnerExecuteId");	
					contractOwnerExecuteId.setText(contract.getContractOwnerExecuteId()==null?"":contract.getContractOwnerExecuteId());
				Element contractOwnerExecuteName = basic.addElement("contractOwnerExecuteName"); 
					contractOwnerExecuteName.setText(contract.getContractOwnerExecuteName()==null?"":contract.getContractOwnerExecuteName());
				Element buildSupplierName = basic.addElement("buildSupplierName");
					buildSupplierName.setText(contract.getBuildSupplierName()==null?"":contract.getBuildSupplierName());
				Element buildSupplierId = basic.addElement("buildSupplierId");
					buildSupplierId.setText(contract.getBuildSupplierId()==null?"":contract.getBuildSupplierId());
				Element payType = basic.addElement("payType");	
					payType.setText(contract.getPayType()==null?"":contract.getPayType());
				Element contractSignedDate = basic.addElement("contractSignedDate");
					contractSignedDate.setText(contract.getContractSignedDate()==null?"":contract.getContractSignedDate());
				Element contractPassedDate = basic.addElement("contractPassedDate");
					contractPassedDate.setText(contract.getContractPassedDate()==null?"":contract.getContractPassedDate());
				Element contractStartDate = basic.addElement("contractStartDate");
					contractStartDate.setText(contract.getContractStartDate()==null?"":contract.getContractStartDate());
				Element contractEndDate = basic.addElement("contractEndDate");
					contractEndDate.setText(contract.getContractEndDate()==null?"":contract.getContractEndDate());
				Element contractStatus = basic.addElement("contractStatus");
					contractStatus.setText(contract.getContractStatus()==null?"":contract.getContractStatus());
				Element contractType = basic.addElement("contractType");
					contractType.setText(contract.getContractType()==null?"":contract.getContractType());
				Element selfNo = basic.addElement("selfNo");
					selfNo.setText(contract.getSelfNo()==null?"":contract.getSelfNo());
				Element contractDestoryDate = basic.addElement("contractDestoryDate");
					contractDestoryDate.setText(contract.getContractDestoryDate()==null?"":contract.getContractDestoryDate());
				Element inviteBidType = basic.addElement("inviteBidType");
					inviteBidType.setText(contract.getInviteBidType()==null?"":contract.getInviteBidType());
				Element contractContent = basic.addElement("contractContent");
					contractContent.setText(contract.getContractContent()==null?"":contract.getContractContent());
				Element projectNo = basic.addElement("projectNo");
					projectNo.setText(contract.getProjectNo()==null?"":contract.getProjectNo());
				Element projectName = basic.addElement("projectName");
					projectName.setText(contract.getProjectName()==null?"":contract.getProjectName());
				Element remark = basic.addElement("remark");
					remark.setText(contract.getRemark()==null?"":contract.getRemark());
				Element removed = basic.addElement("removed");
					removed.setText(contract.getRemoved()==null?"":contract.getRemoved());
				Element createDate = basic.addElement("createDate");
					createDate.setText(contract.getCreateDate()==null?"":contract.getCreateDate());
				
				String attachIdStr = contract.getContractAttachment();
				if(StringUtils.isNotEmpty(attachIdStr)){
					String[] idArray=attachIdStr.split(",");
					List<Attach> attachList = attachService.findByIds(idArray);
					if(attachList!=null && attachList.size()>0){
						//附件信息
						Element attachs = data.addElement("attachs");
						for(int m=0;m<attachList.size();m++){
							Element attachEle = attachs.addElement("attach");
							
							Attach attach = attachList.get(m);
							String filename = attach.getFilename()+"."+attach.getFileextname();
							
							
							String basePath = servletRequest.getScheme()+"://"+servletRequest.getServerName()+":"+servletRequest.getServerPort();  
							String download =basePath+"/investCost/attach/downloadAttach.action?attachId="+attach.getId();
							System.out.println(filename+",download="+download);
							Element filenameEle = attachEle.addElement("fileName");
								filenameEle.setText(filename);
							Element downloadEle = attachEle.addElement("download");
								downloadEle.setText(download);
						}
					}
				}
				
				
			}
			StringWriter out = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
	        
	        format.setEncoding("UTF-8"); // 设置XML文档的编码类型
            format.setIndent(true);      // 设置是否缩进
            format.setIndent("   ");     // 以空格方式实现缩进
            format.setNewlines(true);    // 设置是否换行
			XMLWriter xw = new XMLWriter (out, format);
			try {
				xw.write(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataCenterUtils dataCenterUtils = new DataCenterUtils();
			
			//dataCenterUtils.setDataInfo(list);
			String xmlstring = out.toString();
			List<Map<String,String>> storeList = new  ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("date", date);
			map.put("content", xmlstring);
			storeList.add(map);
			List<String> result = dataCenterUtils.setDataInfo(storeList);
			
			System.out.println(xmlstring);
			
		}
		
	}
	
	/**
	 * KPI消缺
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String kpiClearList(){
		
		
		String contractType = servletRequest.getParameter("contractType");servletRequest.setAttribute("contractType", contractType);		//接收参数: '1,' ,  '2,'  ,  '3,'
		String companyId = servletRequest.getParameter("companyId");servletRequest.setAttribute("companyId", companyId);
		String condition = servletRequest.getParameter("condition"); servletRequest.setAttribute("condition", condition);
		
		String inviteBidType = servletRequest.getParameter("inviteBidType");
		String contractStatus = servletRequest.getParameter("contractStatus");
		//分页搜索条件
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		String clearBeginDate = servletRequest.getParameter("clearBeginDate");
		String clearEndDate = servletRequest.getParameter("clearEndDate");
		
		String kpiStatus = servletRequest.getParameter("kpiStatus");
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		String pageNo = servletRequest.getParameter("pageNo");
		String showOrHide = servletRequest.getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide)){
			showOrHide ="hide";
		}
		servletRequest.setAttribute("showOrHide", showOrHide);
		CaRestAPIService caService = new CaRestAPIService();
		CurrentLogin currentLogin = caService.getCurrentLoginInfoFromCa(servletRequest, servletResponse);
		String loginName  = currentLogin.getCookies().get("loginName");

		String groupName = loginName.substring(0,loginName.length()-4).trim();
		List<Map<String, Object>> group = contractService.findUserGroupByName(groupName);
		String queryFilter = contractService.findQueryFilterByName(groupName);
		
		String type = "";
		//2508 -- 合约管理部
		if("2508".equals(currentLogin.getCompanyId())){
			//合约部业务人员
			for(String str : (currentLogin.getDeptUsers()).keySet()){
				if(loginName!=null && loginName.equals(str)){
					type = "1";
					filter.put("contractOwnerExecuteName", "");
					break;
				}
			}
			//合约部领导
			for(String str : currentLogin.getLeaders().keySet()){
				if(loginName!=null && loginName.equals(str)){
					type = "0";
					filter.put("contractOwnerExecuteName", "");
				}
			}
			
			
		}else{
			type = "23";
			if(kpiStatus==null || "".equals(kpiStatus)){
				kpiStatus = "3";
			}
			if(group == null){
				return Action.SUCCESS;
			}
//			filter.put("mulFilter", group);
			if(queryFilter!=null && !"".equals(queryFilter)){
				filter.put("queryFilter", queryFilter);
			}
		}
		
//		filter.put("removed", 0);
		if(contractType==null || contractType==""){
//			contractType = "2,";
		}
		if(contractType!=null && !"".equals(contractType)){	
			filter.put("contractType", contractType);
		}
		if(contract.getContractNo()!=null && !"".equals(contract.getContractNo())){
			filter.put("contractNo", contract.getContractNo());
		}
		if(contract.getSelfNo()!=null && !"".equals(contract.getSelfNo())){
			filter.put("selfNo", contract.getSelfNo());
		}
		if(contract.getContractOwnerExecuteName()!=null && !"".equals(contract.getContractOwnerExecuteName())){
			filter.put("contractOwnerExecuteName",contract.getContractOwnerExecuteName());
		}
		if(contract.getContractName()!=null && !"".equals(contract.getContractName())){
			filter.put("contractName", contract.getContractName());
		}
		if(contract.getContractStatus()!=null && !"".equals(contract.getContractStatus())){
			filter.put("contractStatus", contract.getContractStatus());
		}
		if(contract.getContractSignedDate()!=null && !"".equals(contract.getContractSignedDate())){
			filter.put("contractSignedDate", contract.getContractSignedDate());
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
			filter.put("contractSignedEndDate", contractSignedEndDate);
		}
		if(contract.getContractPrice()!=null && !"".equals(contract.getContractPrice())){
			filter.put("contractPrice", contract.getContractPrice());
		}
		if(contract.getContractGrouping() != null){
			filter.put("contractGrouping", contract.getContractGrouping());
		}
		if(clearBeginDate!=null && !"".equals(clearBeginDate)){
			servletRequest.setAttribute("clearBeginDate", clearBeginDate);
			filter.put("clearBeginDate", clearBeginDate);
		}
		if(clearEndDate!=null && !"".equals(clearEndDate)){
			servletRequest.setAttribute("clearEndDate", clearEndDate);
			filter.put("clearEndDate", clearEndDate);
		}
		if(kpiStatus!=null && !"".equals(kpiStatus)){
			filter.put("kpiStatus", kpiStatus);
			servletRequest.setAttribute("kpiStatus", kpiStatus);
		}
		if(StringUtils.isNotEmpty(inviteBidType)){
			filter.put("inviteBidType", inviteBidType);
		}
		if(StringUtils.isNotEmpty(contractStatus)){
			filter.put("contractStatus", contractStatus);
		}
		
		List<Object[]> list = contractService.findKpiContractByType(type,filter);
		List<Map<String,Object>> firstResult = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> secondResult = null;
		List<Map<String,Object>> finalResult = new ArrayList<Map<String,Object>>();
		for(Object[] obj : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", (String)obj[0]);
			map.put("contractNo", (String)obj[1]);
			map.put("contractName", (String)obj[2]);
			map.put("executeName", (String)obj[3]);
			map.put("contractGroup", (String)obj[4]);
			map.put("opinion", (String)obj[5]);
			map.put("kpiClearId", (String)obj[6]);
			map.put("contractSignedDate", (String)obj[7]);
			map.put("contractStartDate", (String)obj[8]);
			firstResult.add(map);
		}
		
		int pSize = 20;
		int begin = 0;
		int totalSize = 0;
		int totalPageCount = 0;
		
		totalSize  = firstResult.size();
		if(firstResult.size() % pSize==0){
			totalPageCount = firstResult.size() / pSize;
		}else{
			totalPageCount = firstResult.size() / pSize + 1;
		}
		
		//2.处理condition阶段
		if(firstResult!=null && firstResult.size()>0){
			secondResult = new ArrayList<Map<String,Object>>();
			for(int i=0; i<firstResult.size(); i++){
				Map<String,Object> temp = firstResult.get(i);
				//合同先执行后签订
				if(temp.get("contractSignedDate")==null || "".equals(temp.get("contractSignedDate")) || temp.get("contractStartDate")==null || "".equals(temp.get("contractStartDate"))){
					continue;
				}else{
					try {
						if(sdf3.parse((String)temp.get("contractSignedDate")).after(sdf3.parse((String)temp.get("contractStartDate")))){
							secondResult.add(temp);
						}
					} catch (ParseException e) {
						//e.printStackTrace();
						continue;
					}
				}
			}	
			
		}
		
		if(secondResult==null){
			secondResult = new ArrayList<Map<String,Object>>();
		}
		totalSize  = secondResult.size();
		if(secondResult.size() % pSize==0){
			totalPageCount = secondResult.size() / pSize;
		}else{
			totalPageCount = secondResult.size() / pSize + 1;
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
		
		
		
		servletRequest.setAttribute("type", type);
		if("0".equals(type)){
			servletRequest.setAttribute("result", finalResult);
		}else{
			servletRequest.setAttribute("result", finalResult);
		}
		return Action.SUCCESS;
	}
	
	public String viewKpiClear(){
		String id = servletRequest.getParameter("id");
		List<KpiClear> list = contractService.findKpiClearByContractId(id);
		JSONArray array = new JSONArray();
		String tmpAttachIds = "";
		for(KpiClear tmp : list){
			JSONObject obj = new JSONObject();
			obj.put("opinion", tmp.getOpinion());
			obj.put("date", tmp.getCreateTime().toString().substring(0, 19));
			obj.put("userName", tmp.getCreator());
			if(tmp.getContractAttachId() != null){
				tmpAttachIds += tmp.getContractAttachId()+",";
			}
			array.add(obj);
		}
		String[] attachIds = tmpAttachIds.split(",");
		List<Attach> attachs = attachService.findByIds(attachIds);
		JSONObject object = new JSONObject();
		object.put("attachs", attachs);
		array.add(object);
		try {
			servletResponse.setCharacterEncoding("utf-8");
			servletResponse.getWriter().print(array.toString());
			servletResponse.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Action.NONE;
	}
	
	public void saveKpiClear(){
		CaRestAPIService caService = new CaRestAPIService();
		CurrentLogin currentLogin = caService.getCurrentLoginInfoFromCa(servletRequest, servletResponse);
		String option = servletRequest.getParameter("option");		//判断操作为"消缺","退回"或者"提交"
		String role = servletRequest.getParameter("roleType");		//判断操作人员的角色类型,0为集团领导,1为集团合约部业务人员,2为下属单位合同管理人员
		String contractId = servletRequest.getParameter("contractId");
		String opinion = servletRequest.getParameter("opinion");
		String mode = servletRequest.getParameter("mode");
		String contractIds = servletRequest.getParameter("contractIds");
		String attachIds = servletRequest.getParameter("attachIds");
		
		if(mode!=null && "batch".equals(mode)){
			if("return".equals(option)){
				if(contractIds!=null && !"".equals(contractIds)){
					String[] ids = contractIds.split(",");
					for(int i=0; i<ids.length; i++){
						List<KpiClearStatus> batchList = contractService.findKpiClearStatusByContractId(ids[i]);
						KpiClearStatus tmpStatus = null;
						if(batchList!=null && batchList.size()>0){
							tmpStatus = batchList.get(0);
						} else {
							tmpStatus = new KpiClearStatus();
							tmpStatus.setContractId(ids[i]);
							tmpStatus.setKpiType("1");
							tmpStatus.setUpdateTime(new Date());
							contractService.saveKpiClearStatus(tmpStatus);
						}
						tmpStatus.setUpdateTime(new Date());
						tmpStatus.setKpiStatus("3");
						contractService.updateKpiClearStatus(tmpStatus);
						
						KpiClear kpiClear = new KpiClear();
						Contract con = new Contract();
						con.setId(ids[i]);
						kpiClear.setContractId(con);
						kpiClear.setOpinion(opinion);
						kpiClear.setCreateTime(new Date());
						kpiClear.setCreator(currentLogin.getUserName());
						contractService.saveKpiClear(kpiClear);
						
						//发送短信
						Contract contract = contractService.findContractById(ids[i]);
						String receiverId = contractService.findNameByGroup(contract.getContractOwnerExecuteName(), contract.getContractGrouping());
						shortMsgService.sendMessage(receiverId,"合同销缺退回");
					}
				}
			}
			if("agree".equals(option)){
				if(contractIds!=null && !"".equals(contractIds)){
					String[] ids = contractIds.split(",");
					for(int i=0; i<ids.length; i++){
						List<KpiClearStatus> batchList = contractService.findKpiClearStatusByContractId(ids[i]);
						KpiClearStatus tmpStatus = null;
						if(batchList!=null && batchList.size()>0){
							tmpStatus = batchList.get(0);
						} else {
							tmpStatus = new KpiClearStatus();
							tmpStatus.setContractId(ids[i]);
							tmpStatus.setKpiType("1");
							tmpStatus.setUpdateTime(new Date());
							contractService.saveKpiClearStatus(tmpStatus);
						}
						tmpStatus.setKpiStatus("0");
						tmpStatus.setUpdateTime(new Date());
						contractService.updateKpiClearStatus(tmpStatus);
						
						KpiClear kpiClear = new KpiClear();
						Contract con = new Contract();
						con.setId(ids[i]);
						kpiClear.setContractId(con);
						kpiClear.setCreateTime(new Date());
						kpiClear.setCreator(currentLogin.getUserName());
						contractService.saveKpiClear(kpiClear);
					}
				}
			}
			return ;
		}
		
		
		Contract contract = new Contract();
		contract.setId(contractId);
		
		KpiClear kpiClear = new KpiClear();
		kpiClear.setContractId(contract);
		kpiClear.setOpinion(opinion);
		kpiClear.setCreateTime(new Date());
		kpiClear.setCreator(currentLogin.getUserName());
		
		List<KpiClearStatus> kpiStatusList = contractService.findKpiClearStatusByContractId(contractId);
		KpiClearStatus kpiStatus = null;
		if(kpiStatusList!=null && kpiStatusList.size()>0){
			kpiStatus = kpiStatusList.get(0);
		} else {
			kpiStatus = new KpiClearStatus();
			kpiStatus.setContractId(contractId);
			kpiStatus.setKpiType("1");
			kpiStatus.setUpdateTime(new Date());
			contractService.saveKpiClearStatus(kpiStatus);
		}
			
		if("0".equals(role)){
			kpiStatus.setKpiStatus("3");
			kpiStatus.setUpdateTime(new Date());
			contractService.updateKpiClearStatus(kpiStatus);
			
			contractService.saveKpiClear(kpiClear);
		}else if("1".equals(role)){
			if("return".equals(option)){
				kpiStatus.setKpiStatus("3");
				kpiStatus.setUpdateTime(new Date());
				contractService.updateKpiClearStatus(kpiStatus);
				contractService.saveKpiClear(kpiClear);
				
				List<String> receivers = contractService.getNameByContractIdInGroup(contractId);
				if(receivers != null){
					for(String receiverId : receivers){
						shortMsgService.sendMessage(receiverId,"合同销缺退回");
					}
				}
			}
			if("agree".equals(option)){
				kpiStatus.setKpiStatus("0");
				kpiStatus.setUpdateTime(new Date());
				contractService.updateKpiClearStatus(kpiStatus);
				
				contractService.saveKpiClear(kpiClear);
			}
		}else if("23".equals(role)){
			kpiStatus.setKpiStatus("1");
			kpiStatus.setUpdateTime(new Date());
			contractService.updateKpiClearStatus(kpiStatus);
			
			kpiClear.setContractAttachId(attachIds);
			contractService.saveKpiClear(kpiClear);
		}
		
	}


}

