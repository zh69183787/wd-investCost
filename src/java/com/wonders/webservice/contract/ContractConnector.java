package com.wonders.webservice.contract;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wonders.ic.attach.entity.bo.Attach;
import com.wonders.ic.attach.service.AttachService;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.project.entity.bo.Project;
import com.wonders.ic.project.service.ProjectService;

public class ContractConnector {

	private static ContractService contractServiceForWS;
	private static ProjectService projectServiceForWS;
	private static AttachService attachServiceForWs;

	public ContractService getContractServiceForWS() {
		return contractServiceForWS;
	}

	public void setContractServiceForWS(ContractService contractServiceForWS) {
		ContractConnector.contractServiceForWS = contractServiceForWS;
	}
	public ProjectService getProjectServiceForWS() {
		return projectServiceForWS;
	}

	public void setProjectServiceForWS(ProjectService projectServiceForWS) {
		ContractConnector.projectServiceForWS = projectServiceForWS;
	}
	
	public AttachService getAttachServiceForWs() {
		return attachServiceForWs;
	}

	public void setAttachServiceForWs(AttachService attachServiceForWs) {
		ContractConnector.attachServiceForWs = attachServiceForWs;
	}

	public String saveContract(String contractXML,String secret){
		String errorInfo="";
		if(secret==null || !"71ED833FF46E404C84F2C480A645E149".equals(secret)){
			errorInfo += "验证码错误！";
			return errorInfo;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Contract contract = new Contract();		//存到临时表中去
			
			Document document = DocumentHelper.parseText(contractXML);
			Element root = document.getRootElement();
			if(root==null){
				return "文件格式不正确！";
			}
				
			
			String type =""; 
			if(root.attribute("type")!=null){
				type = root.attribute("type").getText();
			}else{
				return "没有数据类型！root/type字段不能为空！";
			}
			
			Element basicData = (Element)root.selectSingleNode("//BasicData");
			Element htxx = (Element) basicData.selectSingleNode("//Htxx");
			Element htba = (Element) basicData.selectSingleNode("//Htba");
			Element attachFileList = (Element) root.selectSingleNode("//AttachFileList");
			/**必填区域**/
			
			//1.自有编号
			Node selfNum = htxx.selectSingleNode("SelfNum");
			if(selfNum!=null){
				if(!isEmpty(selfNum.getText())){
					if(!contractServiceForWS.isSelfNoExistIgnoreCase(selfNum.getText().trim())){
						contract.setSelfNo(selfNum.getText().trim());
					}else{
						errorInfo += "SelfNum(自有编号)已存在，请检查数据是否正确！";
					}
				}
			}else{
				errorInfo += "SelfNum(自有编号)不能为空！";
			}
			
			//2.合同名称
			Node contractNameNode = htxx.selectSingleNode("//ContractName");
			if(contractNameNode!=null){
				String contractName = contractNameNode.getText().trim();
				if(!isEmpty(contractName)){
					contract.setContractName(contractName);
				}else{
					errorInfo += "ContractName(合同名称)不能为空。";
				}
			}else{
				errorInfo += "ContractName(合同名称)不能为空。";
			}
			
			//3.合同价
			Node contractPrice = htxx.selectSingleNode("ContractMoney");
			if(contractPrice!=null){
				if(!isEmpty(contractPrice.getText())){
					try {
						contract.setContractPrice(getFormattedMoney(contractPrice.getText().trim()));
					} catch (Exception e) {
						errorInfo +=  "ContractMoney(合同价)只能输入数字。";
					}
				}else{
					errorInfo += "ContractMoney(合同价)不能为空。";
				}
			}else{
				errorInfo +=  "ContractMoney(合同价)不能为空。";
			}
			
			//4.项目公司
			Node projectCOName = htxx.selectSingleNode("ProjectCOName");
			if(projectCOName!=null){
				if(!isEmpty(projectCOName.getText())){
					contract.setContractOwnerExecuteName(projectCOName.getText().trim());
				}else{
					errorInfo += "ProjectCOName(项目公司)不能为空。";
				}
			}else{
				errorInfo += "ProjectCOName(项目公司)不能为空。";
			}
			
			//5.登记人
			Node addPerson = htxx.selectSingleNode("AddPerson");
			if(addPerson!=null){
				if(!isEmpty(addPerson.getText())){
					contract.setRegisterPersonName(addPerson.getText().trim());
				}else{
					errorInfo += "AddPerson(登记人)不能为空";
				}
			}else{
				errorInfo += "AddPerson(登记人)不能为空";
			}
			
			//6.对方单位
			Node SignCop = htxx.selectSingleNode("SignCop");
			if(SignCop!=null){
				if(!isEmpty(SignCop.getText())){
					contract.setBuildSupplierName(SignCop.getText().trim());
				}else{
					errorInfo +="SignCop(对方公司)不能为空！";
				}
			}else{
				errorInfo +="SignCop(对方公司)不能为空！";
			}
			
			//7.合同金额分类---》支付方式
			Node ContractMoneyType = htxx.selectSingleNode("ContractMoneyType");
			if(ContractMoneyType!=null){
				if(!isEmpty(ContractMoneyType.getText())){
					contract.setPayType(ContractMoneyType.getText().trim());
				}else{
					errorInfo+="ContractMoneyType(合同金额分类不能为空！)";
				}
			}else{
				errorInfo+="ContractMoneyType(合同金额分类不能为空！)";
			}
			
			//8.合同签约时间
			Node contractSignTime = htba.selectSingleNode("contractSignTime");
			if(contractSignTime!=null){
				if(!isEmpty(contractSignTime.getText())){
					try {
						sdf.parse(contractSignTime.getText().trim());
						contract.setContractSignedDate(contractSignTime.getText().trim());
					} catch (ParseException e) {
						errorInfo += "合同签约时间格式错误！";
					}
				}else{
					errorInfo += "contractSignTime(合同签约时间不能为空！)";
				}
			}else{
				errorInfo += "contractSignTime(合同签约时间不能为空！)";
			}
			
			//9.合同审批通过时间
			Node examinePassTime = htba.selectSingleNode("examinePassTime");
			if(examinePassTime!=null){
				if(!isEmpty(examinePassTime.getText())){
					try {
						sdf.parse(examinePassTime.getText().trim());
						contract.setContractPassedDate(examinePassTime.getText().trim());
					} catch (ParseException e) {
						errorInfo +="examinePassTime(合同审批通过时间)不能为空！";
					}
				}else{
					errorInfo+= "examinePassTime(合同审批通过时间)不能为空！";
				}
			}else{
				errorInfo+= "examinePassTime(合同审批通过时间)不能为空！";
			}
			
			//10.项目名称
			Node projectName = htxx.selectSingleNode("projectName");
			if(projectName!=null){
				if(!isEmpty(projectName.getText())){
					contract.setProjectName(projectName.getText().trim());
					List<Project> list = projectServiceForWS.findByProjectName(projectName.getText().trim());
					if(list!=null && list.size()==1){		//只有当返回的项目只有一个的时候设置pojectId值
						contract.setProjectId(list.get(0).getId());
					}
				}else{
					errorInfo += "projectName(项目名称)不能为空！";
				}
			}else{
				errorInfo += "projectName(项目名称)不能为空！";
			}
			
			//11合同开始时间
			Node ContractStartDate = htxx.selectSingleNode("ContractStartDate");
			if(ContractStartDate!=null){
				if(!isEmpty(ContractStartDate.getText())){
					try {
						sdf.parse(ContractStartDate.getText().trim());
						contract.setContractStartDate(ContractStartDate.getText().trim());
					} catch (ParseException e) {
						errorInfo += "ContractStartDate(合同开始时间)不能为空!";
					}
				}
			}else{
				errorInfo += "ContractStartDate(合同开始时间)不能为空!";
			}
			//12.合同结束时间
			Node ContractEndDate = htxx.selectSingleNode("ContractEndDate");
			if(ContractEndDate!=null){
				if(!isEmpty(ContractEndDate.getText())){
					try {
						sdf.parse(ContractEndDate.getText().trim());
						contract.setContractEndDate(ContractEndDate.getText().trim());
					} catch (ParseException e) {
						errorInfo +="ContractEndDate(合同结束时间)格式错误！";
					}
				}else{
					errorInfo +="ContractEndDate(合同结束时间)不能为空！";
				}
			}else{
				errorInfo +="ContractEndDate(合同结束时间)不能为空！";
			}
			
			/**非必填**/
			//1.项目编号
			Node projectNo = htxx.selectSingleNode("ProjectNum");
			if(projectNo!=null){
				if(!isEmpty(projectNo.getText())){
					contract.setProjectNo(projectNo.getText().trim());
				}
			}
			
			//2.合同编号,传的数据可能没有值,非必填，如果重复不能入库
			Node contractNoNode = htxx.selectSingleNode("//ContractNum");
			if(contractNoNode!=null){
				String contractNo = contractNoNode.getText().trim();
				if(!isEmpty(contractNo)){
					List<Contract> contractList = contractServiceForWS.findByContractNoLowerCase(contractNo.trim());
					if(contractList!=null && contractList.size()>0){
						errorInfo +=  "ContractNum(合同编号)已存在,请检查数据是否正确。";
					}else{
						contract.setContractNo(contractNo);
					}
				}
			}
			
			//3.审定价(投资监理)(万元) ,非必填
			Node finalPrice = htxx.selectSingleNode("finalPrice");
			if(finalPrice!=null){
				if(!isEmpty(finalPrice.getText())){
					try {
						contract.setFinalPrice(getFormattedMoney(finalPrice.getText().trim()));
					} catch (Exception e) {
						errorInfo +=  "finalPrice(审定价(投资监理)只能输入数字)。";
					}
				}
			}
			
			//4.审定价(审价单位),非必填
			Node auditUnitPrice = htxx.selectSingleNode("auditUnitPrice");
			if(auditUnitPrice!=null){
				if(!isEmpty(auditUnitPrice.getText())){
					try {
						contract.setVerifyPrice(getFormattedMoney(auditUnitPrice.getText().trim()));
					} catch (Exception e) {
						errorInfo +=  "auditUnitPrice(审定价(审价单位))只能输入数字。";
					}
				}
			}
			
			//5.审定价(国家审计)
			Node countryAuditPrice = htxx.selectSingleNode("countryAuditPrice");
			if(countryAuditPrice!=null){
				if(!isEmpty(countryAuditPrice.getText())){
					try {
						contract.setNationVerifyPrice(getFormattedMoney(countryAuditPrice.getText().trim()));
					} catch (Exception e) {
						errorInfo +=  "countryAuditPrice(审定价(国家审计))只能输入数字。";
					}
				}
			}
			
			//6.采购方式
			Node inviteBidType = htxx.selectSingleNode("inviteBidType");
			if(inviteBidType!=null){
				if(!isEmpty(inviteBidType.getText())){
					try {
						int inviteBid = Integer.valueOf(inviteBidType.getText().trim());
						if((inviteBid>=1 && inviteBid<=4)||(inviteBid>=21 && inviteBid<=24)||(inviteBid>=31 && inviteBid<=34)||(inviteBid>=41 && inviteBid<=42)){
							contract.setInviteBidType(inviteBidType.getText().trim());
						}else{
							errorInfo +=  "inviteBidType(采购方式)数据错误。";
						}
					} catch (NumberFormatException e) {
						errorInfo +=  "inviteBidType(采购方式)数据错误。";
					}
				}
			}
			
			
			//7.合同状态,设置为0，已备案
			//contract.setContractStatus("0");
			Node contractStatus = htxx.selectSingleNode("contractStatus");
			if(contractStatus!=null){
				if(!isEmpty(contractStatus.getText())){
					String status = contractStatus.getText().trim();
					if("1".equals(status)){			//过程中，系统存5
						contract.setContractStatus("4");
					}else if("2".equals(status)){
						contract.setContractStatus("0");		//已备案，系统存0
					}else if("3".equals(status)){
						contract.setContractStatus("1");	//实施中，系统存1
					}else if("4".equals(status)){
						contract.setContractStatus("2");	//已竣工，系统存2
					}else if("5".equals(status)){
						contract.setContractStatus("3");	//已销号，系统3
					}else if("6".equals(status)){
						contract.setContractStatus("5");	//已取消，系统存5
					}else if("7".equals(status)){
                        contract.setContractStatus("6");	//已终止，系统存6
                    }else{
						errorInfo +=  "contractStatus(合同状态)数据错误。";
					}
				}
			}

			//8.合同类型
			Node contractType = htxx.selectSingleNode("contractType");
			if(contractType!=null){
				if(!isEmpty(contractType.getText())){
					if("1".equals(contractType.getText().trim())){			//运维类-服务，根据文档来设置
						contract.setContractType("2,1");
					}else if("2".equals(contractType.getText().trim())){		//运维类-工程
						contract.setContractType("2,2");
					}else if("3".equals(contractType.getText().trim())){		//运维类-货物
						contract.setContractType("2,3");
					}else{
						errorInfo +=  "contractType合同类型数据错误。";
					}
				}
			}
			//9.备注信息
			Node remark = htxx.selectSingleNode("Remark");
			if (remark!=null) {
				if(!isEmpty(remark.getText())){
					contract.setRemark(remark.getText().trim());
				}
			}
			

			//10.成本内，成本外
			Node isInside = htxx.selectSingleNode("isInside");
			if(isInside!=null){
				if(!isEmpty(isInside.getText())){
					contract.setContractGrouping(isInside.getText());
				}
			}
			
			//11.甲方出资
			Node moneySource = htxx.selectSingleNode("moneySource");
			if(moneySource!=null){
				if(!isEmpty(moneySource.getText())){
					contract.setContractOwnerName(moneySource.getText());
				}
			}
			
			if(attachFileList!=null){
				List<Node> fileList = attachFileList.selectNodes("AttachFile");
				if(fileList!=null && fileList.size()>0){
					String attachId = "";
					for(int i=0,len = fileList.size(); i<len ; i++){
						Node file = fileList.get(i);
						Attach attach = new Attach();
						attach.setFilename(file.selectSingleNode("fileName")==null?"":file.selectSingleNode("fileName").getText().trim());
						attach.setFileextname(file.selectSingleNode("fileExtName")==null?"":file.selectSingleNode("fileExtName").getText().trim());
						attach.setPath(file.selectSingleNode("path")==null?"":file.selectSingleNode("path").getText().trim());
						attach.setFilesize(file.selectSingleNode("fileSize")==null?null:Long.valueOf(file.selectSingleNode("fileSize").getText()));
						attach.setUploaddate(sdf.format(new Date()));
						attach.setRemoved(0l);
						attach.setSavefilename(file.selectSingleNode("path")==null?"":file.selectSingleNode("path").getText().trim());
						attach.setAppname("investCost");
						attach.setStatus("eam");
						attachServiceForWs.saveAttach(attach);
						attachId +=attach.getId()+",";
					}
					if(!"".equals(attachId)){
						attachId = attachId.substring(0,attachId.length()-1);
					}
					contract.setContractAttachment(attachId);
				}
			}
			
			if(errorInfo!=null && !"".equals(errorInfo)){
				return errorInfo;
			}
			contract.setRemoved("0");
			contract.setCreateDate(sdf.format(new Date()));
            contract.setUpdateDate(contract.getCreateDate());//合同同步nc系统时需要根据updatetime做处理
			contract.setCreateType(type);
			contractServiceForWS.saveContract(contract);
		} catch (DocumentException e) {
			e.printStackTrace();
			return "解析错误！";
		}
		return "保存成功";
	}
	
	
	
	public boolean isEmpty(String str){
		if(str ==null || "".equals(str)) return true;
		return false;
	}
	
	//得到保留6位小数后的字符串
	public String getFormattedMoney(String money){
		if(money==null || "".equals(money)){
			money = "0";
		}
		money = money.trim();
		Double result = 0d;
		try {
			result = Double.valueOf(money);
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.000000");
		return df.format(result);
	}
}
