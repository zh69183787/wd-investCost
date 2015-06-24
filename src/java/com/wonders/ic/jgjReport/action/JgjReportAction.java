
package com.wonders.ic.jgjReport.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wonders.ic.export.ExportExcel;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajor;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorSeason;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProject;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectProgress;
import com.wonders.ic.jgjReport.service.JgjReportService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;


@SuppressWarnings("serial")
public class JgjReportAction extends BaseAjaxAction {
	
	private JgjReportService jgjReportService;
	public JgjReportService getJgjReportService() {
		return jgjReportService;
	}
	public void setJgjReportService(JgjReportService jgjReportService) {
		this.jgjReportService = jgjReportService;
	}

	//显示报表21
	public String showReportProject() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjProject(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		List<DwJgjProject> list = jgjReportService.findByYearAndTypeJgjProject(year);
		servletRequest.setAttribute("list", list);
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="上海申通地铁集团有限公司"+year+"年度轨道交通运营设施设备大修和更新改造项目资金计划完成情况表(项目详表)";
			
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "资金计划完成情况表(项目详表)";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"专业","立项依据（集团批文号）","项目名称","执行主体","维修类型","批准预算金额","项目实施进度","至本年底开累支付","","项目下合同名称",
					"费用承担单位","费用承担金额","委托方式","","合同价","决算价（若有）","至上年底累计已支付","","本年度累计已支付","","至本年底开累支付",""};
			String[] head2 = {"","","","","","","","总数","其中政府补贴数","","","","指定维修","招标","","","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			short[] width = {2000,10000,10000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,1,0,0});		//{firstRow,lastRow,firstCol,lastCol}
			for(int m=0;m<=6;m++){
				merge.add(new int[]{0,1,m,m});	//合并第0,1行的第m列
			}
			for(int m=9;m<=11;m++){
				merge.add(new int[]{0,1,m,m});	//合并第0,1行的第m列
			}
			for(int m=14;m<=15;m++){
				merge.add(new int[]{0,1,m,m});	//合并第0,1行的第m列
			}
			//merge.add(new int[]{0,1,10,10});
			//merge.add(new int[]{0,1,11,11});
			//merge.add(new int[]{0,1,16,16});
			merge.add(new int[]{0,0,7,8});		
			merge.add(new int[]{0,0,12,13});
			merge.add(new int[]{0,0,16,17});
			merge.add(new int[]{0,0,18,19});
			merge.add(new int[]{0,0,20,21});
			
			header.add(head1);
			header.add(head2);
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					DwJgjProject target = list.get(i);
					Object[] data = new Object[22];
					data[0] = transProfessionalType(target.getProfessionalType());
					data[1] = target.getDispatchNo();
					data[2] = target.getProjectName();
					data[3] = target.getMainExecute();
					String cType = target.getMaintainType();
					if(cType.equals("2")){
						data[4] = "更新改造";
					}else if(cType.equals("3")){
						data[4] = "大修";
					}
					data[5] = target.getApprovalBugget();
					data[6] = target.getProjectProgress();
					data[7] = target.getThisYearPlanCount();
					data[8] = target.getThisYearPlanGov();
					data[9] = target.getContractName();
					data[10] = target.getFeeDepartment();
					data[11] = target.getFeeMoney();
					data[12] = target.getAppointMaintain();
					data[13] = target.getBidInvaiting();
					data[14] = target.getContractPrice();
					data[15] = target.getFinalPrice();
					data[16] = target.getLastYearCount();
					data[17] = target.getLastYearGov();
					data[18] = target.getThisYearPayCount();
					data[19] = target.getThisYearPayGov();
					data[20] = target.getEndYearPayCount();
					data[21] = target.getEndYearPayGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return "showReportProject";
	}
	
	//显示报表2.3和2.4
	public String showReportMajor() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjMajor(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		String type = servletRequest.getParameter("type");
		if(StringUtils.isEmpty(type)){
			type="3";
		}
		servletRequest.setAttribute("year", year);
		servletRequest.setAttribute("type", type);
		List<DwJgjMajor> result = jgjReportService.findByYearAndTypeJgjMajor(year, type);
		servletRequest.setAttribute("result", result);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			String typename = "";
			String type1name="";
			if(type.equals("3")){
				typename="(大修，分专业汇总)";
				type1name="大修项目";
			}else if("2".equals(type)) {
				typename="(更新改造，分专业汇总)";
				type1name="更新改造项目";
			}
			this.servletResponse.setContentType("octets/stream");  
			String filename="申通地铁集团"+year+"年轨道交通运营设施设备"+type1name+"资金计划完成情况表("+typename+").xls";
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "汇总表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"线路/专业","车辆","","","","供电","","","","通号","","","","工务","","","","车站机电","","","","段场","","","","其他","","","","合计","","",""};
			String[] head2 = {"","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款",""};
			String[] head3 = {"","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			header.add(head1);
			header.add(head2);
			header.add(head3);
			
			short[] width = {4000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,2,0,0});
			merge.add(new int[]{0,0,1,4});
			merge.add(new int[]{0,0,5,8});
			merge.add(new int[]{0,0,9,12});
			merge.add(new int[]{0,0,13,16});
			merge.add(new int[]{0,0,17,20});
			merge.add(new int[]{0,0,21,24});
			merge.add(new int[]{0,0,25,28});
			merge.add(new int[]{0,0,29,32});
			
			merge.add(new int[]{1,1,1,2});
			merge.add(new int[]{1,1,3,4});
			merge.add(new int[]{1,1,5,6});
			merge.add(new int[]{1,1,7,8});
			merge.add(new int[]{1,1,9,10});
			merge.add(new int[]{1,1,11,12});
			merge.add(new int[]{1,1,13,14});
			merge.add(new int[]{1,1,15,16});
			
			merge.add(new int[]{1,1,17,18});
			merge.add(new int[]{1,1,19,20});
			merge.add(new int[]{1,1,21,22});
			merge.add(new int[]{1,1,23,24});
			merge.add(new int[]{1,1,25,26});
			merge.add(new int[]{1,1,27,28});
			merge.add(new int[]{1,1,29,30});
			merge.add(new int[]{1,1,31,32});
			
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					DwJgjMajor target = result.get(i);
					Object[] data = new Object[33];
					data[0] = target.getLineName();
					data[1] = target.getCarPlanCount();
					data[2] = target.getCarPlanGov();
					data[3] = target.getCarActualCount();
					data[4] = target.getCarActualGov();
					data[5] = target.getElePlanCount();
					data[6] = target.getElePlanGov();
					data[7] = target.getEleActualCount();
					data[8] = target.getEleActualGov();
					data[9] = target.getNumPlanCount();
					data[10] = target.getNumPlanGov();
					data[11] = target.getNumActualCount();
					data[12] = target.getNumActualGov();
					data[13] = target.getWorkPlanCount();
					data[14] = target.getWorkPlanGov();
					data[15] = target.getWorkActualCount();
					data[16] = target.getWorkActualGov();
					data[17] = target.getStationPlanCount();
					data[18] = target.getStationPlanGov();
					data[19] = target.getStationActualCount();
					data[20] = target.getStationActualGov();
					data[21] = target.getBasePlanCount();
					data[22] = target.getBasePlanGov();
					data[23] = target.getBaseActualCount();
					data[24] = target.getBaseActualGov();
					data[25] = target.getOtherPlanCount();
					data[26] = target.getOtherPlanGov();
					data[27] = target.getOtherActualCount();
					data[28] = target.getOtherActualGov();
					data[29] = target.getAllPlanCount();
					data[30] = target.getAllPlanGov();
					data[31] = target.getAllActualCount();
					data[32] = target.getAllActualGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
			
		}
		return "showReportMajor";
	}
	
	//得到保留4位小数后的字符串
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
		DecimalFormat df = new DecimalFormat("#0.0000");
		return df.format(result);
	}
	
	public void calculateDwJgjProject() throws Exception{
		String year = servletRequest.getParameter("year");
		String table = servletRequest.getParameter("table");
		if(StringUtils.isEmpty(year)){
			year = Calendar.getInstance().get(Calendar.YEAR)+"";
		}
		if(StringUtils.isEmpty(table)){
			jgjReportService.deleteAllDwJgjProjectByYear(year);
			jgjReportService.calculateDwJgjProject(year);
			
			jgjReportService.deleteAllDwJgjProjectCountByYear(year);
			jgjReportService.calculateDwJgjProjectCount(year);
			
			jgjReportService.deleteAllDwJgjProjectProgressByYear(year);
			jgjReportService.calculateDwJgjProjectProgress(year);
			
			jgjReportService.deleteAllDwJgjMajorCountByYear(year);
			jgjReportService.calculateDwJgjMajorCount(year);
			
			jgjReportService.deleteAllDwJgjMajorSeasonByYear(year);
			jgjReportService.calculateDwJgjMajorSeason(year);
		}
		if("1".equals(table)){
			jgjReportService.deleteAllDwJgjProjectByYear(year);
			jgjReportService.calculateDwJgjProject(year);
		}
		if("2".equals(table)){
			jgjReportService.deleteAllDwJgjProjectCountByYear(year);
			jgjReportService.calculateDwJgjProjectCount(year);
		}
		if("3".equals(table)){
			jgjReportService.deleteAllDwJgjProjectProgressByYear(year);
			jgjReportService.calculateDwJgjProjectProgress(year);
		}
		if("4".equals(table)){
			jgjReportService.deleteAllDwJgjMajorCountByYear(year);
			jgjReportService.calculateDwJgjMajorCount(year);
		}
		if("5".equals(table)){
			jgjReportService.deleteAllDwJgjMajorSeasonByYear(year);
			jgjReportService.calculateDwJgjMajorSeason(year);
		}
	}
	
	public void calculateDwJgjMajor(){
		String year = servletRequest.getParameter("year");
		if(StringUtils.isEmpty(year)){
			year = Calendar.getInstance().get(Calendar.YEAR)+"";
		}
		jgjReportService.deleteAllDwJgjMajorByYear(year);
		jgjReportService.calculateDwJgjMajor(year);
	}
	
	public String showReportProjectCount() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjProjectCount(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		servletRequest.setAttribute("year", year);
		List<DwJgjProjectCount> list = jgjReportService.findByYearAndTypeJgjProjectCount(year);
		servletRequest.setAttribute("list", list);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="上海申通地铁集团有限公司"+year+"年度轨道交通运营设施设备大修和更新改造项目资金计划申请表(项目详表)";
						    
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "资金计划完成情况表(项目详表)";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"专业","立项依据（集团批文号）","项目名称","执行主体","费用承担单位","费用承担金额","批准预算金额","维修类型","合同价总计","决算价（若有）","至上年底累计已支付","","本年度用款计划",""};
			String[] head2 = {"","","","","","","","","","","总数","其中政府补贴数","总数","其中政府补贴数"};
			short[] width = {2000,10000,10000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,1,0,0});		//{firstRow,lastRow,firstCol,lastCol}
			for(int m=0;m<=9;m++){
				merge.add(new int[]{0,1,m,m});	//合并第0,1行的第m列
			}
			merge.add(new int[]{0,0,10,11});			//合并第0行的9,10列
			merge.add(new int[]{0,0,12,13});			//合并第0行的11,12列
			
			header.add(head1);
			header.add(head2);
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					DwJgjProjectCount target = list.get(i);
					Object[] data = new Object[14];
					data[0] = transProfessionalType(target.getProfessionalType());
					data[1] = target.getDispatchNo();
					data[2] = target.getProjectName();
					data[3] = target.getMainExecute();
					data[4] = target.getFeeDepartment();
					data[5] = target.getFeeMoney();
					
					data[6] = target.getApprovalBugget();
					
					data[7] = target.getMaintainType();
					if(data[7]!=null && "2".equals(data[7].toString())){
						data[7] = "更新改造";
					}else if(data[7]!=null && "3".equals(data[7].toString())){
						data[7] = "大修";
					}else {
						data[7] ="";
					}
					data[8] = target.getContractPrice();
					data[9] = target.getFinalPrice();
					data[10] = target.getLastYearPayCount();
					data[11] = target.getLastYearPayGov();
					data[12] = target.getThisYearPlanCount();
					data[13] = target.getThisYearPlanGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		return "success";
	}
	
	
	public String showReportProjectProgress() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		String season = servletRequest.getParameter("season");
		List<String> allYear = jgjReportService.findAllYearJgjProjectProgress(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		if(StringUtils.isBlank(season)){
			season = "1";
		}
		
		servletRequest.setAttribute("year", year);
		servletRequest.setAttribute("season", season);
		List<DwJgjProjectProgress> list = jgjReportService.findByYearJgjProjectProgress(year,season);
		servletRequest.setAttribute("list", list);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="上海申通地铁集团有限公司"+year+"年度第"+season+"季度轨道交通运营设施设备大修和更新改造项目资金计划完成情况季度表(实施进展情况表)";
			
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "实施进展情况表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"专业","立项依据（集团批文号）","项目名称","执行主体","费用承担单位","费用承担金额","批准预算金额","维修类型","合同价总计","决算价（若有）","本年度用款计划","","项目实施进度","本年度累计已支付",""};
			String[] head2 = {"","","","","","","","","","","总数","其中政府补贴数","","总数","其中政府补贴数"};
			short[] width = {2000,10000,10000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,1,0,0});		//{firstRow,lastRow,firstCol,lastCol}
			for(int m=0;m<=9;m++){
				merge.add(new int[]{0,1,m,m});	//合并第0,1行的第m列
			}
			merge.add(new int[]{0,0,10,11});			
			merge.add(new int[]{0,0,13,14});			
			
			header.add(head1);
			header.add(head2);
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					DwJgjProjectProgress target = list.get(i);
					Object[] data = new Object[15];
					data[0] = transProfessionalType(target.getProfessionalType());
					data[1] = target.getDispatchNo();
					data[2] = target.getProjectName();
					data[3] = target.getMainExecute();
					data[4] = target.getFeeDepartment();
					data[5] = target.getFeeMoney();
					
					
					data[6] = target.getApprovalBugget();
					data[7] = target.getMaintainType();
					if(data[7]!=null && "2".equals(data[7].toString())){
						data[7] = "更新改造";
					}else if(data[6]!=null && "3".equals(data[7].toString())){
						data[7] = "大修";
					}else {
						data[7] ="";
					}
					data[8] = target.getContractPrice();
					data[9] = target.getFinalPrice();
					data[10] = target.getThisYearPlanCount();
					data[11] = target.getThisYearPlanGov();
					data[12] = target.getProjectProgress();
					data[13] = target.getThisYearPayCount();
					data[14] = target.getThisYearPayGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		return "success";
	}
	
	
	public String showReportMajorCount() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjMajorCount(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		servletRequest.setAttribute("year", year);
		List<DwJgjMajorCount> result = jgjReportService.findByYearJgjMajorCount(year);
		servletRequest.setAttribute("result", result);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="申通地铁集团"+year+"年轨道交通运营设施设备大修项目资金计划完成情况表(汇总表）";
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "汇总表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"线路/专业","大修","","","","更新改造","","","","总计","","","",};
			String[] head2 = {"","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款",""};
			String[] head3 = {"","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			header.add(head1);
			header.add(head2);
			header.add(head3);
			
			short[] width = {4000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,2,0,0});
			merge.add(new int[]{0,0,1,4});
			merge.add(new int[]{0,0,5,8});
			merge.add(new int[]{0,0,9,12});
			
			merge.add(new int[]{1,1,1,2});
			merge.add(new int[]{1,1,3,4});
			merge.add(new int[]{1,1,5,6});
			merge.add(new int[]{1,1,7,8});
			merge.add(new int[]{1,1,9,10});
			merge.add(new int[]{1,1,11,12});
			
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					DwJgjMajorCount target = result.get(i);
					Object[] data = new Object[13];
					data[0] = target.getLineName();
					data[1] = target.getOverhaulPlanCount();
					data[2] = target.getOverhaulPlanGov();
					data[3] = target.getOverhaulActualCount();
					data[4] = target.getOverhaulActualGov();
					data[5] = target.getUpdatePlanCount();
					data[6] = target.getUpdatePlanGov();
					data[7] = target.getUpdateActualCount();
					data[8] = target.getUpdateActualGov();
					data[9] = target.getAllPlanCount();
					data[10] = target.getAllPlanGov();
					data[11] = target.getAllActualCount();
					data[12] = target.getAllActualGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		return "success";
	}
	
	public String showReportMajorCountPlan() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjMajorCount(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		servletRequest.setAttribute("year", year);
		List<DwJgjMajorCount> result = jgjReportService.findByYearJgjMajorCount(year);
		servletRequest.setAttribute("result", result);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="申通地铁集团"+year+"年轨道交通运营设施设备大修项目资金计划完成情况表(汇总表）";
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "汇总表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"线路/专业","大修","","更新改造","","总计",""};
			String[] head2 = {"","计划用款","","计划用款","","计划用款",""};
			String[] head3 = {"","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			header.add(head1);
			header.add(head2);
			header.add(head3);
			
			short[] width = {4000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,2,0,0});
			merge.add(new int[]{0,0,1,2});
			merge.add(new int[]{0,0,3,4});
			merge.add(new int[]{0,0,5,6});
			merge.add(new int[]{1,1,1,2});
			merge.add(new int[]{1,1,3,4});
			merge.add(new int[]{1,1,5,6});
			
			
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					DwJgjMajorCount target = result.get(i);
					Object[] data = new Object[7];
					data[0] = target.getLineName();
					data[1] = target.getOverhaulPlanCount();
					data[2] = target.getOverhaulPlanGov();
					data[3] = target.getUpdatePlanCount();
					data[4] = target.getUpdatePlanGov();
					data[5] = target.getAllPlanCount();
					data[6] = target.getAllPlanGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		return "success";
	}
	
	
	public String showReportMajorSeason() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		String season = servletRequest.getParameter("season");
		List<String> allYear = jgjReportService.findAllYearJgjMajorSeason(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		if(StringUtils.isBlank(season)){
			season ="1";
		}
		servletRequest.setAttribute("year", year);
		servletRequest.setAttribute("season", season);
		List<DwJgjMajorSeason> result = jgjReportService.findByYearJgjMajorSeason(year,season);
		servletRequest.setAttribute("result", result);
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			this.servletResponse.setContentType("octets/stream");  
			String filename="申通地铁集团"+year+"年第"+season+"季度轨道交通运营设施设备大修项目资金计划完成情况表(汇总表）";
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "汇总表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename+".xls"); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"线路/专业","大修","","","","更新改造","","","","总计","","","",};
			String[] head2 = {"","计划用款","","实际用款","","计划用款","","实际用款","","计划用款","","实际用款",""};
			String[] head3 = {"","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			header.add(head1);
			header.add(head2);
			header.add(head3);
			
			short[] width = {4000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,2,0,0});
			merge.add(new int[]{0,0,1,4});
			merge.add(new int[]{0,0,5,8});
			merge.add(new int[]{0,0,9,12});
			
			merge.add(new int[]{1,1,1,2});
			merge.add(new int[]{1,1,3,4});
			merge.add(new int[]{1,1,5,6});
			merge.add(new int[]{1,1,7,8});
			merge.add(new int[]{1,1,9,10});
			merge.add(new int[]{1,1,11,12});
			
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					DwJgjMajorSeason target = result.get(i);
					Object[] data = new Object[13];
					data[0] = target.getLineName();
					data[1] = target.getOverhaulPlanCount();
					data[2] = target.getOverhaulPlanGov();
					data[3] = target.getOverhaulActualCount();
					data[4] = target.getOverhaulActualGov();
					data[5] = target.getUpdatePlanCount();
					data[6] = target.getUpdatePlanGov();
					data[7] = target.getUpdateActualCount();
					data[8] = target.getUpdateActualGov();
					data[9] = target.getAllPlanCount();
					data[10] = target.getAllPlanGov();
					data[11] = target.getAllActualCount();
					data[12] = target.getAllActualGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		
		
		return "success";
	}
	
	
	//显示报表1.3和1.4
	public String showReportMajorPlan() throws UnsupportedEncodingException{
		String year = servletRequest.getParameter("year");
		List<String> allYear = jgjReportService.findAllYearJgjMajor(); 
		servletRequest.setAttribute("allYear", allYear);
		if(StringUtils.isEmpty(year)){
			if(allYear!=null && allYear.size()>0){
				year = allYear.get(0);
			}else{
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
		}
		String type = servletRequest.getParameter("type");
		if(StringUtils.isEmpty(type)){
			type="3";
		}
		servletRequest.setAttribute("year", year);
		servletRequest.setAttribute("type", type);
		
		List<DwJgjMajor> result = jgjReportService.findByYearAndTypeJgjMajor(year, type);
		servletRequest.setAttribute("result", result);
		
		String export = servletRequest.getParameter("export");
		if(StringUtils.isNotBlank(export) && "yes".equals(export)){
			String typename = "";
			if(type.equals("3")){
				typename="(大修，分专业汇总)";
			}else if("2".equals(type)) {
				typename="(更新改造，分专业汇总)";
			}
			this.servletResponse.setContentType("octets/stream");  
			String filename="申通地铁集团"+year+"年轨道交通运营设施设备大修项目资金计划申请表("+typename+").xls";
			filename = new String(filename.getBytes("gb2312"),"iso8859-1");
			String sheettitle = "汇总表";
			
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+filename); 
			List<String[]> header = new ArrayList<String[]>();
			String[] head1 = {"线路/专业","车辆","","供电","","通号","","工务","","车站机电","","段场","","其他","","合计",""};
			String[] head2 = {"","计划用款","","计划用款","","计划用款","","计划用款","","计划用款","","计划用款","","计划用款","","计划用款",""};
			String[] head3 = {"","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数","总数","其中政府补贴数"};
			header.add(head1);
			header.add(head2);
			header.add(head3);
			
			short[] width = {4000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
			List<int[]> merge = new ArrayList<int[]>();			
			merge.add(new int[]{0,2,0,0});
			merge.add(new int[]{0,0,1,2});
			merge.add(new int[]{0,0,3,4});
			merge.add(new int[]{0,0,5,6});
			merge.add(new int[]{0,0,7,8});
			merge.add(new int[]{0,0,9,10});
			merge.add(new int[]{0,0,11,12});
			merge.add(new int[]{0,0,13,14});
			merge.add(new int[]{0,0,15,16});
			
			
			merge.add(new int[]{1,1,1,2});
			merge.add(new int[]{1,1,3,4});
			merge.add(new int[]{1,1,5,6});
			merge.add(new int[]{1,1,7,8});
			merge.add(new int[]{1,1,9,10});
			merge.add(new int[]{1,1,11,12});
			merge.add(new int[]{1,1,13,14});
			merge.add(new int[]{1,1,15,16});
			
			
			List<Object[]> dataset = new ArrayList<Object[]>();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					DwJgjMajor target = result.get(i);
					Object[] data = new Object[17];
					data[0] = target.getLineName();
					data[1] = target.getCarPlanCount();
					data[2] = target.getCarPlanGov();
					data[3] = target.getElePlanCount();
					data[4] = target.getElePlanGov();
					data[5] = target.getNumPlanCount();
					data[6] = target.getNumPlanGov();
					data[7] = target.getWorkPlanCount();
					data[8] = target.getWorkPlanGov();
					data[9] = target.getStationPlanCount();
					data[10] = target.getStationPlanGov();
					data[11] = target.getBasePlanCount();
					data[12] = target.getBasePlanGov();
					data[13] = target.getOtherPlanCount();
					data[14] = target.getOtherPlanGov();
					data[15] = target.getAllPlanCount();
					data[16] = target.getAllPlanGov();
					dataset.add(data);
				}
			}
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportJgjExcel(sheettitle, header, dataset, out,width,merge); 
				out.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return null;
			
		}
		return "showReportMajor";
	}
	
	public String transProfessionalType(String type){
		if(StringUtils.isBlank(type)) return "";
		if("1".equals(type)){
			return "车辆";
		}else if("2".equals(type)){
			return "供电";
		}else if("3".equals(type)){
			return "通号";
		}else if("4".equals(type)){
			return "工务";
		}else if("5".equals(type)){
			return "基地";
		}else if("6".equals(type)){
			return "车站机电";
		}
		return "";
	}
	
}
