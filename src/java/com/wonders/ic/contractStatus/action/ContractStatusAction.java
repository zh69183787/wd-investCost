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

package com.wonders.ic.contractStatus.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.BeanUtils;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wonders.ic.contractStatus.entity.vo.ContractStatusVO;
import com.wonders.ic.contractStatus.service.ContractStatusService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractStatusAction extends BaseAjaxAction {
	private ContractStatus contractStatus = new ContractStatus();
	private ContractStatusVO contractStatusVO = new ContractStatusVO();
	private ContractStatusService contractStatusService;
	private ContractService contractService;
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
	private File uploadify;
	private String uploadifyFileName;
	private AttachService attachService;
	
	
	
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public AttachService getAttachService() {
		return attachService;
	}

	public void setAttachService(AttachService attachService) {
		this.attachService = attachService;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	@Override
	public ValueObject getValueObject() {
		return this.contractStatusVO;
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

	public String findContractStatusById() {
		String id = super.getServletRequest().getParameter("id");

		ContractStatus bo = contractStatusService.findContractStatusById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findContractStatusByPage() {
		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.contractStatusVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.contractStatusVO,
						key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = contractStatusService.findContractStatusByPage(filter,
				start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page, ContractStatus.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteContractStatus() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			ContractStatus bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (ContractStatus) JSONObject.toBean(obj,
						ContractStatus.class);
				if (bean != null) {
					contractStatusService.deleteContractStatus(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addContractStatus() {
		ContractStatus contractStatus = convertVOToBO(contractStatusVO);
		if (contractStatus != null) {
			contractStatusService.addContractStatus(contractStatus);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateContractStatus() {
		String[] modifiedData = (String[]) super.getParameters().get(
				"modifiedData");
		if (modifiedData != null) {
			JSONArray modifiedArr = JSONArray.fromObject("[" + modifiedData[0]
					+ "]");
			JSONObject obj = null;
			for (int i = 0; i < modifiedArr.size(); i++) {
				obj = (JSONObject) modifiedArr.get(i);
				Object isNew = obj.get("isNew");
				if (isNew != null && ((Boolean) isNew).booleanValue()) {
					ContractStatusVO bean = (ContractStatusVO) JSONObject
							.toBean(obj, ContractStatusVO.class);
					contractStatusService.addContractStatus(this
							.convertVOToBO(bean));
				} else {
					contractStatusService
							.updateContractStatus((ContractStatus) JSONObject
									.toBean(obj, ContractStatus.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private ContractStatus convertVOToBO(ContractStatusVO contractStatusVO) {
		ContractStatus contractStatus = new ContractStatus();
		BeanUtils.copyProperties(contractStatusVO, contractStatus,
				new String[] { "id" });
		return contractStatus;
	}

	private ContractStatusVO convertBOToVO(ContractStatus contractStatus) {
		ContractStatusVO contractStatusVO = new ContractStatusVO();
		BeanUtils.copyProperties(contractStatus, contractStatusVO);
		return contractStatusVO;
	}

	public ContractStatusVO getContractStatusVO() {
		return contractStatusVO;
	}

	public void setContractStatusVO(ContractStatusVO contractStatusVO) {
		this.contractStatusVO = contractStatusVO;
	}

	public void setContractStatusService(
			ContractStatusService contractStatusService) {
		this.contractStatusService = contractStatusService;
	}
	
	public void saveAdd(){
		String type = this.servletRequest.getParameter("type");
		String reason = this.servletRequest.getParameter("reason");
		String line = this.servletRequest.getParameter("line");
		String money = this.servletRequest.getParameter("money");
		String persent = this.servletRequest.getParameter("persent");
		String operateDate = this.servletRequest.getParameter("operateDate");
		String remark = this.servletRequest.getParameter("remark");
		String contractId = this.servletRequest.getParameter("contractId");
		String attach = this.servletRequest.getParameter("attach");
		
		
		
		ContractStatus bo = new ContractStatus();
		if(type!=null && "1".equals(type)){
			String add_flowNo = this.servletRequest.getParameter("add_flowNo");
			String add_raisedCompany = this.servletRequest.getParameter("add_raisedCompany");
			bo.setChangeFlowNo(add_flowNo);
			bo.setChangeRaisedCompany(add_raisedCompany);
		}
		bo.setAttach(attach);
		bo.setContractId(contractId);
		bo.setType(type);
		bo.setReason(reason);
		bo.setLine(line);
		bo.setMoney(money);
		bo.setPersent(persent);
		bo.setOperateDate(operateDate);
		bo.setRemark(remark);
		bo.setRemoved("0");
		bo.setUpdateDate(new Date());
		contractStatusService.addContractStatus(bo);
	}
	
	public void saveEdit(){
		String reason = this.servletRequest.getParameter("reason");
		String line = this.servletRequest.getParameter("line");
		String money = this.servletRequest.getParameter("money");
		String persent = this.servletRequest.getParameter("persent");
		String operateDate = this.servletRequest.getParameter("operateDate");
		String remark = this.servletRequest.getParameter("remark");
		String id = this.servletRequest.getParameter("id");
		String changeFlowNo = this.servletRequest.getParameter("changeFlowNo");
		String changeRaisedCompany = this.servletRequest.getParameter("changeRaisedCompany");
		String attach = this.servletRequest.getParameter("attach");
		
		ContractStatus bo = contractStatusService.findContractStatusById(id);
		bo.setAttach(attach);
		bo.setChangeFlowNo(changeFlowNo);
		bo.setChangeRaisedCompany(changeRaisedCompany);
		bo.setReason(reason);
		bo.setLine(line);
		bo.setMoney(money);
		bo.setPersent(persent);
		bo.setOperateDate(operateDate);
		bo.setRemark(remark);
		bo.setUpdateDate(new Date());
		contractStatusService.updateContractStatus(bo);
	}
	
	public void deleteData(){
		String id = this.servletRequest.getParameter("id");
		
		ContractStatus bo = contractStatusService.findContractStatusById(id);
		bo.setRemoved("1");
		bo.setUpdateDate(new Date());
		contractStatusService.updateContractStatus(bo);
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

	public String showBatchUpload(){
		
		return SUCCESS;
	}

	public String bacthUploadContractStatus(){
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
		try {
			is = new FileInputStream(saveFileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			int rowsAll = sheet.getPhysicalNumberOfRows();
			if(sheet.getPhysicalNumberOfRows()>0){			//行数大于0
				boolean hasError = false;
				List<ContractStatus> saveList = new ArrayList<ContractStatus>();
				HSSFCell cell ;		
				for(int i=1; i<rowsAll; i++){
					Contract contract =null;
					int saveStatus = 0;		//0:可以保存，1：修改成功，2：有错误
					ContractStatus contractStatus = new ContractStatus();
					contractStatus.setType("3");
					row = sheet.getRow(i);
					String errorInfo = "";
					if(row==null){
						continue;
					}					
					cell = row.getCell((short)1);				//1,B.合同编号
					if(cell!=null && !"".equals(cell.toString())){
						List<Contract> contractList = contractService.findByContractNoLowerCase(cell.toString().toLowerCase().trim());
						if(contractList!=null && contractList.size()>0){
							if(contractList.size()==1){
								contractStatus.setContractId(contractList.get(0).getId());
								contract = contractList.get(0);
							}else{
								errorInfo +="该合同编号有多个合同，无法导入";
								saveStatus = 2;
								hasError = true;
							}
						}else{
							errorInfo +="合同编号没有对应的合同，";
							saveStatus = 2;
							hasError = true;
						}
					}
					cell = row.getCell((short)2);		//2.C.合同名称，暂时不用
					
					cell = row.getCell((short)3);		//3.D.事由
					contractStatus.setReason(cell.toString().trim());
					
					cell = row.getCell((short)4);		//4.E.金额
					if(cell!=null && !"".equals(cell.toString())){
						String money = getFormattedValue(cell.toString(), 4);
						if("error".equals(money)){
							errorInfo += "金额格式错误，";
							saveStatus = 2;
							hasError = true;
						}else{
							contractStatus.setMoney(money);
							if(contract!=null && contract.getContractPrice()!=null && !"".equals(contract.getContractPrice())){
								Double d = Double.valueOf(cell.toString()) / Double.valueOf(contract.getContractPrice()) * 100;
								contractStatus.setPersent(getFormattedValue(d.toString(), 1));		//百分比设置
							}
						}
					}
					
					cell = row.getCell((short)5);		//5.F.备注
					contractStatus.setRemark(cell.toString().trim());
					
					cell = row.getCell((short)6);		//6.G.支付时间
					if(cell!=null && !"".equals(cell.toString().trim())){
						try {
							sdf3.parse(cell.toString().trim());
							contractStatus.setOperateDate(cell.toString());
						} catch (ParseException e) {
							errorInfo += "支付时间格式错误，";
							saveStatus = 2;
							hasError = true;
						}
					}
					
					if(saveStatus==0){
						contractStatus.setRemoved("0");
						contractStatus.setUpdateDate(new Date());
						saveList.add(contractStatus);
					}else{
						// 创建单元格样式
						HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
						cellStyle.setFillForegroundColor(HSSFColor.RED.index); // 设置背景色为红色,本条数据无效
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						HSSFCell cell0 = row.createCell((short)0);
						cell0.setCellValue(errorInfo);
						cell0.setCellStyle(cellStyle);
					}
				}
				if(!hasError){
					contractStatusService.saveAll(saveList);
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



	public String getFormattedValue(String value,int decimal){
		if(value==null || "".equals(value)) return value;
		try {
			DecimalFormat df = new DecimalFormat("#.0000");
			Double result = Double.valueOf(value);
			if(result==0) return "0";
			else return df.format(result)+"";
		} catch (NumberFormatException e) {
			return "error";			//如果转型错误,返回error
		}
	}
	
}
