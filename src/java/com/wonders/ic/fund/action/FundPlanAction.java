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

package com.wonders.ic.fund.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.fund.entity.bo.FundPlan;
import com.wonders.ic.fund.entity.vo.FundPlanVo;
import com.wonders.ic.fund.service.FundPlanService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
/**
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class FundPlanAction extends BaseAjaxAction {
	private FundPlan fundPlan = new FundPlan();
	private FundPlanVo fundPlanVo = new FundPlanVo();
	private FundPlanService fundPlanService;
	private ContractService contractService;
	private ProjectService projectService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private File uploadify;
	private String uploadifyFileName;
	
	public String findFundPlanByPage() {
		String pageNo = servletRequest.getParameter("pageNo");
		String pageSize = servletRequest.getParameter("pageSize");
		
		Page page = fundPlanService.findFundPlanByPage(fundPlan,pageNo,pageSize);
		ServletActionContext.getRequest().setAttribute("page", page);
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		servletRequest.setAttribute("thisYear", thisYear);
		
		return SUCCESS;
	}
	
	/**
	 * 显示新增页面
	 */
	public String showAdd(){
		List<CompanyRoute> routes = contractService.findAllLine();
		servletRequest.setAttribute("routes", routes);
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		servletRequest.setAttribute("thisYear", thisYear);
		return SUCCESS;
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){

		fundPlan.setCreateDate(sdf.format(new Date()));			
		fundPlan.setUpdateDate(sdf.format(new Date()));
		fundPlan.setRemoved("0");
		
		if(fundPlanService.exists(fundPlan)){
			servletRequest.setAttribute("browserMessage", "相同项目，同线路，同年份的资金计划已存在，请修改后再提交");
			
			List<CompanyRoute> routes = contractService.findAllLine();
			servletRequest.setAttribute("routes", routes);
			
			int thisYear = Calendar.getInstance().get(Calendar.YEAR);
			servletRequest.setAttribute("thisYear", thisYear);
			
			return INPUT;
		}
		
		fundPlanService.saveFundPlan(fundPlan);

		return SUCCESS;
	}
	
	/**
	 * 显示修改页面
	 */
	public String showEdit(){
		String id = servletRequest.getParameter("id");
		fundPlan = fundPlanService.findFundPlanById(id);		
		
		String saveStatus = servletRequest.getParameter("saveStatus");
		if("1".equals(saveStatus)){
			servletRequest.setAttribute("browserMessage", "资金计划保存成功！");
		}
		
		List<CompanyRoute> routes = contractService.findAllLine();
		servletRequest.setAttribute("routes", routes);
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		servletRequest.setAttribute("thisYear", thisYear);
		
		return "showEdit";
	}
	
	/**
	 * 保存新增
	 */
	public String saveEdit(){

		fundPlan.setUpdateDate(sdf.format(new Date()));
		fundPlan.setRemoved("0");
		
		if(fundPlanService.exists(fundPlan)){
			servletRequest.setAttribute("browserMessage", "相同项目，同线路，同年份的资金计划已存在，请修改后再提交");
			
			List<CompanyRoute> routes = contractService.findAllLine();
			servletRequest.setAttribute("routes", routes);
			
			return INPUT;
		}
		
		fundPlanService.saveFundPlan(fundPlan);

		return SUCCESS;
	}
	
	/**
	 * 显示查看页面
	 */
	public String showView(){
		String id = servletRequest.getParameter("id");
		fundPlan = fundPlanService.findFundPlanById(id);		
		
		return SUCCESS;
	}
	
	public String showBatchUpload(){
		return SUCCESS;
	}
	
	/**
	 * 逻辑删除
	 */
	public String delete(){
		String id = ServletActionContext.getRequest().getParameter("id");
		fundPlanService.deleteByIdOnLogical(id);
		
		return AJAX;
	}
	
	public String batchUpload(){
		String statusType = servletRequest.getParameter("statusType");
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
		InputStream is;
		Map<String,String> rec = new HashMap<String,String>(); 
		String responseData = "";
		try {
			is = new FileInputStream(saveFileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			int rowsAll = sheet.getPhysicalNumberOfRows();
			if(sheet.getPhysicalNumberOfRows()>0){			//行数大于0
				boolean hasError = false;
				List<FundPlan> saveList = new ArrayList<FundPlan>();
				HSSFCell cell ;		
				for(int i=1; i<rowsAll; i++){
					Project project =null;
					int saveStatus = 0;		//0:可以保存，1：修改成功，2：有错误
					FundPlan fundPlan = new FundPlan();
					row = sheet.getRow(i);
					String errorInfo = "";
					if(row==null){
						continue;
					}					
					
					cell = row.getCell(0);		//0.A.年度
					if(cell!=null && !"".equals(cell.toString())){
						Object inputValue = null;
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							double doubleVal = cell.getNumericCellValue();
						    long longVal = Math.round(cell.getNumericCellValue());
						    if(Double.parseDouble(longVal + ".0") == doubleVal){
						    	inputValue = longVal;  
						    }else{
						    	inputValue = doubleVal;
						    }
						}
						if(inputValue != null && String.valueOf(inputValue).trim().length() == 4){
							fundPlan.setYear(String.valueOf(inputValue));	
						}else{
							errorInfo +="计划年度需为4位数字，";
							saveStatus = 2;
							hasError = true;							
						}
					}else{
						errorInfo +="计划年度不能为空，";
						saveStatus = 2;
						hasError = true;						
					}
					
					cell = row.getCell(1);				//1,B.项目批文号
					if(cell!=null && !"".equals(cell.toString())){
						List<Project> projectList = projectService.findByDispatchNo(cell.toString().toLowerCase().trim());
						if(projectList!=null && projectList.size()>0){
							if(projectList.size()==1){
								fundPlan.setDispatchNo(projectList.get(0).getDispatchNo());
								fundPlan.setProjectId(projectList.get(0).getId());
								project = projectList.get(0);
							}else{
								errorInfo +="项目批文号有多个项目";
								saveStatus = 2;
								hasError = true;
							}
						}else{
							errorInfo +="项目批文号没有对应的项目，";
							saveStatus = 2;
							hasError = true;
						}
					}else{
						errorInfo +="项目批文号不能为空，";
						saveStatus = 2;
						hasError = true;						
					}

					cell = row.getCell(2);		//2.C.线路
					if(cell!=null && !"".equals(cell.toString())){
						fundPlan.setLine(cell.toString().trim());	
					}else{
						errorInfo +="线路不能为空，";
						saveStatus = 2;
						hasError = true;						
					}
					
					
					cell = row.getCell(3);		//3.D.用款计划（万元）
					if(cell!=null && !"".equals(cell.toString())){
						String money = getFormattedValue(cell.toString(), 6);
						if("error".equals(money)){
							errorInfo += "用款计划（万元）金额格式错误，";
							saveStatus = 2;
							hasError = true;
						}else{
							fundPlan.setMoney(BigDecimal.valueOf(Double.valueOf(money)));
						}
					}else{
						errorInfo +="用款计划（万元）不能为空，";
						saveStatus = 2;
						hasError = true;						
					}
					
					String pKey = fundPlan.getProjectId() + "|" + fundPlan.getLine() + "|" + fundPlan.getYear();
					if(saveStatus==0){			//检查唯一性
						if(rec.containsKey(pKey) || this.fundPlanService.exists(fundPlan)){
							errorInfo +="相同项目，同线路，同年份的资金计划已存在，";
							saveStatus = 2;
							hasError = true;		
						}						
					}
					
					if(saveStatus==0){
						fundPlan.setRemoved("0");
						fundPlan.setCreateDate(sdf.format(new Date()));
						fundPlan.setUpdateDate(sdf.format(new Date()));
						saveList.add(fundPlan);
						rec.put(pKey, null);
						
/*						// 创建单元格样式
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index); // 设置背景色为绿色,本条数据有效
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue("导入成功");
						cell0.setCellStyle(cellStyle);*/
					}else{
						responseData += "第"+(i+1)+"行的"+errorInfo+"<br>";
/*						// 创建单元格样式
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.RED.index); // 设置背景色为红色,本条数据无效
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);*/
					}
				}
				if(!hasError){
					responseData = "导入成功";
					fundPlanService.saveAll(saveList);
				}else{
					responseData = "导入失败<br>" + responseData;
				}
			}
			
/*			//生产反馈文件
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
			createJSonData(downloadName);*/
			createJSonData(responseData);
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
	
	private String getFormattedMoney(String money){
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
	
	private String getFormattedValue(String value,int decimal){
		if(value==null || "".equals(value)) return value;
		try {
			DecimalFormat df = new DecimalFormat("#.000000");
			Double result = Double.valueOf(value);
			if(result==0) return "0";
			else return df.format(result)+"";
		} catch (NumberFormatException e) {
			return "error";			//如果转型错误,返回error
		}
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

	@Override
	public ValueObject getValueObject() {
		return this.fundPlanVo;
	}
	
	@Override
	public Object getModel() {
		return fundPlan;
	}
	
	public FundPlan getFundPlan() {
		return fundPlan;
	}

	public void setFundPlan(FundPlan fundPlan) {
		this.fundPlan = fundPlan;
	}

	public FundPlanService getFundPlanService() {
		return fundPlanService;
	}

	public void setFundPlanService(FundPlanService fundPlanService) {
		this.fundPlanService = fundPlanService;
	}

	public FundPlanVo getFundPlanVo() {
		return fundPlanVo;
	}

	public void setFundPlanVo(FundPlanVo fundPlanVo) {
		this.fundPlanVo = fundPlanVo;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
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

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
}

