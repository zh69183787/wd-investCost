<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
if(cookies !=null){
	for(int i=0;i<cookies.length;i++){
		Cookie cookie = cookies[i];
		cookieMap.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
		if("token".equals(cookie.getName())){
			try{
				token = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目查看</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../js/addDetail.js"></script>


<%String basePath = request.getContextPath(); %>
<style type="text/css">
.must{
	color:red;
}

.ui-autocomplete {
	max-height: 150px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
	/* add padding to account for vertical scrollbar */
	padding-right: 20px;
	max-width: 300px;
	width: 300px;
}
/* IE 6 doesn't support max-height
 * we use height instead, but this forces the menu to always be this tall
 */
* html .ui-autocomplete {
	height: 150px;
}
</style>



<script type="text/javascript">
function countMoney(obj){
	var moneyAll = 0;
	var persentAll = 0;
	$(obj).find("#moneyTd").each(function(){
		if($.trim($(this).html())!=""){
			moneyAll += parseFloat($(this).html());
		}
		if($.trim($(this).siblings("#persentTd").html())!=""){
			persentAll += parseFloat($(this).siblings("#persentTd").html());
		}
	});
	moneyAll = moneyAll.toFixed(4);
	persentAll = persentAll.toFixed(2);
	if(moneyAll==0.0000){
		moneyAll = 0;
	}
	if(persentAll==0.00){
		persentAll = 0;
	}
	$(obj).find("#moneyAll").html(moneyAll);
	$(obj).find("#persentAll").html(persentAll);
}
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});

$(function(){
	loadViewPage($("#projectMoneysource"));	
	
	showShortWord();
	var tab = $("#tab").val();
	if(tab!=""){
		$("#pageFoot").find("a:eq("+tab+")").click();
	}
	
	countMoney("#tbodyPlanPay");
});


function showShortWord(){
	$("#progress").find("tr:gt(0)").each(function(){
		var $targetTd = $(this).find("td:eq(5)");
		if($.trim($targetTd.text()).length>20){
			$targetTd.html($targetTd.text().substring(0,30)+"...");
		}
	});
	
	$("#planPayArea").find("tr:gt(0)").each(function(){
		var $targetTd = $(this).find("td:eq(1)");
		if($.trim($targetTd.text()).length>20){
			$targetTd.html($targetTd.text().substring(0,20)+"...");
		}
		$targetTd = $(this).find("td:eq(5)");
		if($.trim($targetTd.text()).length>30){
			$targetTd.html($targetTd.text().substring(0,30)+"...");
		}
	});
	
} 

function showEstimateList(){
	var url = "/investCost/estimateSubject/findInvestEstimateSubjectByPage.action?projectId="+$("#projectId").val();
	window.open(url);
}


function showControl(){
	var url = "/investCost/estimateSubject/findInvestEstimateSubjectByPage.action?type=view&projectId="+$("#projectId").val();
	window.open(url);
}



//合同进展情况，显示
function showProgressAdd(){
	$("#addProgressButton").attr("disabled",true);
	var html ="<tr>"+
		"<td></td>"+
		"<td id='addDept'><input style='width:100%' type='text' id='reportDeptName' maxlength='50' readonly='readonly' value='<%=request.getAttribute("deptName")%>'><input type='hidden' id='reportDeptId' value='<%=request.getAttribute("deptId")%>'></td>"+
		"<td id='addPerson'><input style='width:100%' type='text' id='reportPersonName' maxlength='50' readonly='readonly' value='<%=request.getAttribute("userName")%>'><input type='hidden' id='reportPersonId' value='<%=request.getAttribute("userId")%>'></td>"+
		"<td><input style='width:100%' type='text' id='progressType' maxlength='500'></td>"+		
		"<td><input style='width:100%' type='text' id='time' readonly='readonly'></td>"+
		"<td><input style='width:100%' type='text' id='describe' maxlength='500'></td>"+
		"<td class='t_c'>"+
			"<a href='javascript:void(0);' onclick='showProgressEdit(this);' style='display:none;'>编辑</a>&nbsp;&nbsp;"+
			"<a href='javascript:void(0);' onclick='deleteProgress(this);' style='display:none;'>删除</a>"+
			"<input type='button' value='保存' style='display: inline;' onclick='saveProgress(this);'>&nbsp;&nbsp;"+
			"<input type='button' value='取消' style='display: inline;' onclick='hideProgressAdd(this);'>"+
		"</td></tr>";
	$("#progress").append(html);
	enableDatepicker("time");
}

//合同进展情况，显示编辑
function showProgressEdit(object){
	$("#addProgressButton").attr("disabled",true);
	var $targetTr = $(object).parent().parent();
	$(object).parent().find("input").attr("style","display:inline;");
	$(object).parent().find("a").hide();
	
	//填报部门
	var $targetTd = $targetTr.find("td:eq(1)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	var htmlValue = "<input style='width:100%' type='text' id='reportDeptName' readonly='readonly' maxlength='50' value='"+$.trim($targetTd.text())+"'/>";
	var objectValue = $targetTd.find("input:hidden");
	$targetTd.html(htmlValue);
	$targetTd.append(objectValue);
	
	//填报人
	$targetTd = $targetTr.find("td:eq(2)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	htmlValue = "<input type='text' style='width:100%' id='reportDeptName' readonly='readonly' value='"+$.trim($targetTd.text())+"'/>";
	objectValue = $targetTd.find("input:hidden");
	$targetTd.html(htmlValue);
	$targetTd.append(objectValue);
	
	//进展类型
	$targetTd = $targetTr.find("td:eq(3)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	htmlValue = "<input type='text' style='width:100%' id='progressType' value='"+$.trim($targetTd.text())+"'/>";
	$targetTd.html(htmlValue);
	//时间
	$targetTd = $targetTr.find("td:eq(4)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	htmlValue = "<input type='text' style='width:100%' id='timeAdd' value='"+$.trim($targetTd.text())+"'/>";
	$targetTd.html(htmlValue);
	//描述
	$targetTd = $targetTr.find("td:eq(5)");
	$targetTd.attr("id",$.trim($targetTd.attr("title")));
	htmlValue = "<input type='text' style='width:100%' id='describe' value='"+$.trim($targetTd.attr("title"))+"'/>";
	$targetTd.html(htmlValue);
	
	enableDatepicker("timeAdd");
}

function hideProgressEdit(object){
	$("#addProgressButton").attr("disabled",false);
	$targetTr = $(object).parent().parent();
	$(object).parent().find("input").hide();
	$(object).parent().find("a").attr("style","display:inline;");
	
	//填报部门
	var $targetTd = $targetTr.find("td:eq(1)");
	var $hideDeptId = $targetTd.find("input:hidden"); 
	$targetTd.html($targetTd.attr("id"));
	$targetTd.append($hideDeptId);
	//填报人
	$targetTd = $targetTr.find("td:eq(2)");
	$hideDeptId = $targetTd.find("input:hidden"); 
	$targetTd.html($targetTd.attr("id"));
	$targetTd.append($hideDeptId);
	//进展类型
	$targetTd = $targetTr.find("td:eq(3)");
	if($targetTd.attr("id")==null || $targetTd.attr("id")=="undefined"){
		$targetTd.html("");
	}else{
		$targetTd.html($targetTd.attr("id"));
	}
	
	//时间
	$targetTd = $targetTr.find("td:eq(4)");
	if($targetTd.attr("id")==null || $targetTd.attr("id")=="undefined"){
		$targetTd.html("");
	}else{
		$targetTd.html($targetTd.attr("id"));
	}
	//描述
	$targetTd = $targetTr.find("td:eq(5)");
	$targetTd.html($targetTd.attr("id"));
	
	showShortWord();
}


//合同进展情况，保存新增
function saveProgress(object){
	var $targetTr = $(object).parent().parent();
	var $deptName = $targetTr.find("td:eq(1)").find("input:visible");
	var $deptId = $targetTr.find("td:eq(1)").find("input:hidden");
	var $personName = $targetTr.find("td:eq(2)").find("input:visible");
	var $personId = $targetTr.find("td:eq(2)").find("input:hidden");
	
	$.ajax({
		url:"<%=basePath%>/progress/saveAddProgress.action?random="+Math.random(),
		dataType:"json",
		data:{
			reportDeptName:$.trim($deptName.val()),
			reportDeptId:$.trim($deptId.val()),
			reportPersonName:$.trim($personName.val()),
			reportPersonId:$.trim($personId.val()),
			progressType:$.trim($targetTr.find("td:eq(3)").find("input").val()),
			time:$.trim($targetTr.find("td:eq(4)").find("input").val()),
			describe:$.trim($targetTr.find("td:eq(5)").find("input").val()),
			objectType:'2',
			objectId:$("#projectId").val()
		},
		error:function(){
			alert("系统忙，请刷新后再试！");
		},
		success:function(){
			alert("保存成功！");
			window.top.location.reload()
		}
	});
}

function saveProgressEdit(object,id){
	var $targetTr = $(object).parent().parent();
	var $deptName = $targetTr.find("td:eq(1)").find("input:visible");
	var $deptId = $targetTr.find("td:eq(1)").find("input:hidden");
	var $personName = $targetTr.find("td:eq(2)").find("input:visible");
	var $personId = $targetTr.find("td:eq(2)").find("input:hidden");
	$.ajax({
		url:"<%=basePath%>/progress/saveEditProgress.action?random="+Math.random(),
		dataType:"json",
		data:{
			id:id,
			reportDeptName:$.trim($deptName.val()),
			reportDeptId:$.trim($deptId.val()),
			reportPersonName:$.trim($personName.val()),
			reportPersonId:$.trim($personId.val()),
			progressType:$.trim($targetTr.find("td:eq(3)").find("input").val()),
			time:$.trim($targetTr.find("td:eq(4)").find("input").val()),
			describe:$.trim($targetTr.find("td:eq(5)").find("input").val()),
			objectType:'2',
			objectId:$("#projectId").val()
		},
		error:function(){
			alert("系统忙，请刷新后再试！");
		},
		success:function(){
			alert("保存成功！");
			window.top.location.reload()
		}
	});
}

//合同进展情况，删除
function deleteProgress(id){
	if(confirm("是否删除？")){
		$.ajax({
			url:"<%=basePath%>/progress/deleteProgress.action?random="+Math.random(),
			dataType:"json",
			data:{
				id:id
			},
			error:function(){
				alert("系统忙，请刷新后再试！");
			},
			success:function(){
				alert("删除成功！");
				window.top.location.reload()
			}
		});	
	}
}


//合同进展情况，隐藏
function hideProgressAdd(object){
	$("#addProgressButton").attr("disabled",false);
	$(object).parent().parent().remove();
}

//使日期控件生效,参数，控件id
function enableDatepicker(id){
	var $target = $("#"+id);
	if(!$.isEmptyObject($target)){
		$target.datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
	}
}

</script>





</head>

<body>
<input type="hidden" value="<s:property value='#request.tab'/>" id="tab"/>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">工程投资造价管理</a></li>
	<li><a href="#">项目封面</a></li>
	<li><a href="#">项目管理</a></li>
	<li class="fin">项目查看</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#" onclick="window.print();">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">
<input type="hidden" id="projectId" value="<s:property value='project.id'/>">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp;
				<input type="button" value="投资概算" onclick="showEstimateList();"/>
				<input type="button" value="目标控制" onclick="showControl();"/>
			</th>
		</thead>
		<tbody id="formBody">
		<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">项目名称</td>
				<td>
					<s:property value="project.projectName" />
				</td>
				<td class="t_r lableTd">项目类型</td>
				<td>
					<s:if test="project.projectType==1">外部项目</s:if>
					<s:elseif test="project.projectType==2">内部项目资本类</s:elseif>
					<s:elseif test="project.projectType==3">内部项目大修类</s:elseif>
					<s:elseif test="project.projectType==4">内部项目能源类</s:elseif>
					<s:elseif test="project.projectType==5">其他类</s:elseif>
				</td>
			</tr>
			<tr>
				
				<td class="t_r lableTd" width="20%;">关键字</td>
				<td width="30%;"><s:property value="project.keyword" /></td>
				<td class="t_r lableTd" width="20%;">是否代建</td>
				<td width="30%;">
					<s:if test="project.substituteConstruction==1">是</s:if>
					<s:elseif test="project.substituteConstruction==2">否</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd" width="20%;"><strong>集团项目编号</strong></td>
				<td width="30%;">
					<s:property value="project.projectNo" />
				</td>
				<td class="t_r lableTd" width="20%;"><strong id="budget">投资估算总额(万元)</strong></td>
				<td width="30%;">
					<s:if test="project.projectBudgetAll==0">0</s:if>
					<s:else>
						<s:property value="project.projectBudgetAll" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">集团发文编号</td>
				<td>
					<s:property value='project.dispatchNo'/>	
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<s:property value='project.approvalDate'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">计划开始时间</td>
				<td><s:property value="project.projectStarttimePlanDate" /></td>
				<td class="t_r lableTd">计划完成时间</td>
				<td><s:property value="project.projectEndtimePlanDate" /></td>
			</tr>
			<tr>
				<td class="t_r lableTd">资金来源（万元）</td>
				<td colspan="3">
					<input type="hidden" name="projectMoneysource" id="projectMoneysource" value="<s:property value="project.projectMoneysource" />"/>
					<!-- 
					<textarea rows="2" cols="2" name="projectMoneysource"><s:property value="project.projectMoneysource" /></textarea>
					 -->
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">资金渠道性质</td>
				<td>
					<s:if test="project.moneySourceType==1">建设资金</s:if>
					<s:elseif test="project.moneySourceType==2">自有资金</s:elseif>
					<s:elseif test="project.moneySourceType==3">财政资金</s:elseif>
				</td>
				<td class="t_r lableTd">专业分类</td>
				<td>
					<s:if test="project.professionalType==1">车辆</s:if>
					<s:elseif test="project.professionalType==2">供电</s:elseif>
					<s:elseif test="project.professionalType==3">通号</s:elseif>
					<s:elseif test="project.professionalType==4">工务</s:elseif>
					<s:elseif test="project.professionalType==5">基地</s:elseif>
					<s:elseif test="project.professionalType==6">车站机电</s:elseif>
				</td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">项目公司</td>
				<td >
					<s:property value="project.projectCompany" />
				</td>
				<td class="t_r lableTd">项目状态</td>
				<td>	
					<input type="hidden" value="<s:property value='project.projectState'/>" id="hide_projectState">
					<s:if test="project.projectState==1">项目准备</s:if>
					<s:elseif test="project.projectState==2">项目实施</s:elseif>
					<s:elseif test="project.projectState==3">销项完成</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">负责部门</td>
				<td >
					<s:property value="project.projectAdddept" />
				</td>
				<td class="t_r lableTd">负责人</td>
				<td >
					<s:property value="project.projectAddperson" />
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">项目销项时间</td>
				<td>
					<s:property value="project.projectDestoryDate"/>
				</td>
				<td class="t_r lableTd">市拨补贴资金</td>
				<td>
					<s:if test="project.cityAllowance==1">
							是
					</s:if>
					<s:elseif test="project.cityAllowance==2">
						否
					</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">决算价(万元)</td>
				<td>
					<s:property value="project.settlementPrice"/>	
				</td>
				<td class="t_r lableTd">成本属性</td>
				<td>
					<s:if test="project.payType==1">
						成本性
					</s:if>
					<s:elseif test="project.payType==2">
						费用性
					</s:elseif>				
				</td>
			</tr>				
			<tr>
				<td class="t_r lableTd">附件</td>
				<td colspan="3">
					<s:if test="#request.attachList==null || #request.attachList.size==0">
						无
					</s:if>
					<s:else>
						<table id="fileTable">
							<tbody style="width: 550px;border: solid;border-color: #D0D0D3;" id="fileBody">
								<tr style="border: solid;border: #D0D0D3;">
									<td width="200px;" class="t_c" >文件名</td>
									<td width="100px;" class="t_c">大小(KB)</td>
									<td width="150px;" class="t_c">上传时间</td>
									<td width="50px;" class="t_c">操作</td>
								</tr>
								<s:iterator id="attach" value="#request.attachList">
									<s:if test="#attach.status!=null && #attach.status=='project'">
										<tr>
											<td class="t_l">
												<a href="javascript:void(0);" onclick="window.open('<s:property value="#attach.path"/>')">
													<s:property value="#attach.filename"/>.<s:property value="#attach.fileextname"/>
												</a>
											</td>
											<td class="t_r">
												<s:if test="(#attach.filesize/1024.0+'').substring((#attach.filesize/1024.0+'').indexOf('.'),(#attach.filesize/1024.0+'').length()).length()<=3">
													<s:property value="#attach.filesize/1024.0" />
												</s:if>
												<s:else>
													<s:property value="(#attach.filesize/1024.0+'').substring(0,(#attach.filesize/1024.0+'').indexOf('.')+3)"/>
												</s:else>
											</td>
											<td class="t_c"><s:property value="#attach.uploaddate"/></td>
											<td class="t_c">
												<a href="javascript:void(0);" onclick="window.open('<s:property value="#attach.path"/>')">下载</a>
											</td>
										</tr>
									</s:if>
									<s:else>
										<tr style="word-break:break-all; word-wrap:break-word;">
											<td class="t_l" width="200px;">
												<a href="<%=basePath%>/attach/downloadAttach.action?attachId=<s:property value='#attach.id'/>">
													<s:property value="#attach.filename"/>.<s:property value="#attach.fileextname"/>
												</a>
											</td>
											<td class="t_r">
												<s:if test="(#attach.filesize/1024.0+'').substring((#attach.filesize/1024.0+'').indexOf('.'),(#attach.filesize/1024.0+'').length()).length()<=3">
													<s:property value="#attach.filesize/1024.0" />
												</s:if>
												<s:else>
													<s:property value="(#attach.filesize/1024.0+'').substring(0,(#attach.filesize/1024.0+'').indexOf('.')+3)"/>
												</s:else>
												<!--<s:property value="(#attach.filesize/1024.0+'').substring(0,(#attach.filesize/1024.0+'').indexOf('.')+3)"/>-->
											</td>
											<td class="t_c"><s:property value="#attach.uploaddate"/></td>
											<td class="t_c">
												<a href="<%=basePath%>/attach/downloadAttach.action?attachId=<s:property value='#attach.id'/>">下载</a>
											</td>
										</tr>
									</s:else>								
								</s:iterator>
							</tbody>
						</table>					
					</s:else>
				</td>								
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<s:property value="project.remark" />			
				</td>
			</tr>
			
			<tr><td colspan="4" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">工可信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">工可项目批复文号</td>
				<td>
					<s:property value='project.projectFeasibilityNo'/>
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<s:property value='project.projectFeasibilityDate'/>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">投资估算总额(万元)</td>
				<td colspan="3">
					<s:if test="project.projectFeasibilityBudget==0">0</s:if>
					<s:else>
						<s:property value="project.projectFeasibilityBudget"/>
					</s:else>
				</td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<s:property value='project.projectFeasibilityRemark'/>					
				</td>
			</tr>
			
			<tr><td colspan="4" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">初步设计信息</td><td colspan="3"></td></tr>
			
			<tr>
				<td class="t_r lableTd">初步设计批复文号</td>
				<td>
					<s:property value='project.primaryDesignNo'/>
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<s:property value='project.primaryDesignDate'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">批复概算(万元)</td>
				<td>
					<s:if test="project.primaryDesignBudget==0">0</s:if>
					<s:else>
						<s:property value="project.primaryDesignBudget"/>
					</s:else>
				</td>
				<td class="t_r lableTd">调整概算(万元)</td>
				<td>
					<s:if test="project.primaryDesignMoney==0">0</s:if>
					<s:else>
						<s:property value="project.primaryDesignMoney"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<s:property value='project.primaryDesignRemark'/>					
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp;
				<input type="button" value="投资概算" onclick="showEstimateList();"/> 
				<input type="button" value="目标控制" onclick="showControl();"/>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
function showDetail(target,type){
	$(target).parent().siblings().removeClass("selected");
	$(target).parent().addClass("selected");
	$("#pageFoot").nextAll().hide();
	if(type=='1'){
		$("#progressArea").show();
	}else if(type=='2'){
		$("#planPayArea").show();
	}
}

function saveAdd(obj,type){
	var tr = $(obj).parent().parent();
	var add_type = tr.find("#add_type").val();
	var add_reason = tr.find("#add_reason").val();
	var add_line = tr.find("#add_line").val();	
	var add_money = tr.find("#add_money").val();
	if(add_money!=""&&add_money!="0"){
		add_money = parseFloat(add_money).toFixed(4);
	}
	var add_persent = tr.find("#add_persent").val();
	var add_operateDate = tr.find("#add_operateDate").val();
	var add_remark = tr.find("#add_remark").val();
	
	if(add_money!=''&&!$.isNumeric(add_money)){
		alert("金额只能填数字！");
		$("#add_money").focus();
		return false;
	}
	if(add_persent!=''&&!$.isNumeric(add_persent)){
		alert("百分比只能填数字！");
		$("#add_persent").focus();
		return false;
	}
	var id = "<s:property value='#request.id'/>";
	var tab;
	if(type==4){
		tab = 1;
	}else{
		tab = 0;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/investCost/contractStatus/saveAdd.action",
		data:{
			type:add_type,
			reason:add_reason,
			line:add_line,
			money:add_money,
			persent:add_persent,
			operateDate:add_operateDate,
			remark:add_remark,			
			contractId:$("#projectId").val()
		},
		error:function(){
				alert("系统连接失败，请联系管理员！");
			},
		success:function(object){
			alert("保存成功！");
			window.location.href = "<%=basePath%>/project/showView.action?id="+id+"&tab="+tab;
		}
	});
}
function cancelAdd(obj){
	$(obj).parent().parent().remove();
	$("[id=addButton]").attr("disabled",false);
}

function showPlanAdd(type){
	$("[id=addButton]").attr("disabled",true);
	var add_html = "<tr><td><input type='hidden' id='add_type' value='"+type+"'/></td>"+
				"<td><input type='text' id='add_reason' style='width:100%'/></td>"+
				"<td>"+$("#routeArea").html()+"</td>"+
				"<td><input type='text' id='add_money' style='width:100%'/></td>"+
				"<td><input type='text' id='add_persent' style='width:100%'/></td>"+
				"<td><input type='text' id='add_operateDate' style='width:100%' readonly='readonly'/></td>"+
				"<td><input type='text' id='add_remark' style='width:100%'/></td>"+
				"<td class='t_c'><input type='button' value='保存' onclick='saveAdd(this,"+type+");'/>&nbsp;&nbsp;<input type='button' value='取消' onclick='cancelAdd(this);'/></td></tr>";
	$("#tbodyPlanPay").find("#sumTr").before(add_html);
	enableDatepicker("add_operateDate");
}
function showEdit(obj){
	$("[id=addButton]").attr("disabled",true);
	var tr = $(obj).parent().parent();
	
	for(var i=1;i<7;i++){
		if(i==2){
			var $selectedObj;
			var $addTd = tr.children("td:eq("+i+")");
			var addLineHtml ="";
			var lineValue = $.trim(tr.children("td:eq("+i+")").html());
			$("#routeArea").find("select").find("option").each(function(){
				if($(this).val()==lineValue){
					$selectedObj = $(this);
					$(this).attr("selected",true);
				}
			});
			$addTd.html($("#routeArea").html());
			$selectedObj.attr("selected",false);
			$addTd.find("select").attr("id",lineValue);
		}else if(i==5){
			var dateValue = $.trim(tr.children("td:eq("+i+")").html());
			var eqHtml = "<input type='text' style='width:100%' name='operateDate_' id='' value='"+dateValue+"'/>";
			tr.children("td:eq(5)").html(eqHtml);
		}else{
			var index = i;
			var $targetTd =  tr.children("td:eq("+index+")");
			if($targetTd.attr("title")!=null && $targetTd.attr("title")!=""){		//变更事由、备注
				$targetTd.html("<input type='text' style='width:100%' id='"+$.trim($targetTd.attr("title"))+"' value='"+$.trim($targetTd.attr("title"))+"'/>");
			}else{
				tr.children("td:eq("+i+")").html("<input type='text' style='width:100%' id='"+$.trim(tr.children("td:eq("+index+")").html())+"' value='"+$.trim(tr.children("td:eq("+index+")").html())+"'/>");
			}
		}
	}
	$("input[name=operateDate_]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=operateDate_]").attr("alias",$("input[name=operateDate_]").val());
	$(obj).parent().children("a").attr("style","display:none");
	$(obj).parent().children("input[type=button]").attr("style","display:inline");
}

function saveEdit(obj,id,type){
	var tr = $(obj).parent().parent();
	var reason = tr.children("td:eq(1)").children("input").val();
	var line = tr.children("td:eq(2)").children("select").val();
	var money = tr.children("td:eq(3)").children("input").val();
	if(money!=""&&money!="0"){
		money = parseFloat(money).toFixed(4);
	}
	var persent = tr.children("td:eq(4)").children("input").val();
	var operateDate = tr.children("td:eq(5)").children("input").val();
	var remark = tr.children("td:eq(6)").children("input").val();
	
	if(money!=''&&!$.isNumeric(money)){
		alert("金额只能填数字！");
		tr.children("td:eq(3)").children("input").focus();
		return false;
	}
	
	if(persent!=''&&!$.isNumeric(persent)){
		alert("百分比只能填数字！");
		tr.children("td:eq(4)").children("input").focus();
		return false;
	}
	
	var tid = "<s:property value='#request.id'/>";
	var tab;
	if(type==4){
		tab = 1;
	}else{
		tab = 0;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"<%=basePath%>/contractStatus/saveEdit.action",
		data:{
			reason:reason,
			line:line,
			money:money,
			persent:persent,
			operateDate:operateDate,
			remark:remark,
			id:id
		},
		error:function(){
				alert("系统连接失败，请联系管理员！");
			},
		success:function(object){
			alert("保存成功！");
			window.location.href = "<%=basePath%>/project/showView.action?id="+tid+"&tab="+tab;
		}
	});
}

function hideEdit(obj){
	$("[id=addButton]").attr("disabled",false);
	var tr = $(obj).parent().parent();
	var maxInputLength = tr.find("input").length;
	tr.find("select").parent().html(tr.find("select").attr("id"));
	tr.find("input").each(function(index){
		if(index<(maxInputLength-2)){
			if(index==0){
				if($(this).attr("id")!=null && $(this).attr("id")!="" && $(this).attr("id")!="undefined"){
					tr.children("td:eq("+(index+1)+")").html($(this).attr("id"));
				}else{	
					tr.children("td:eq("+(index+1)+")").html("");
				}
			}else{
				if(index==3){
					if($(this).attr("alias").indexOf("-")<1){
						tr.children("td:eq("+(index+2)+")").html("");
					}else{
						tr.children("td:eq("+(index+2)+")").html($(this).attr("alias"));
					}
				}else{
					if($(this).attr("id")!=null && $(this).attr("id")!="" && $(this).attr("id")!="undefined"){
						tr.children("td:eq("+(index+2)+")").html($(this).attr("id"));
					}else{	
						tr.children("td:eq("+(index+2)+")").html("");
					}
				}
			}
					
		}
	});
	$(obj).parent().children("a").attr("style","display:inline");
	$(obj).parent().children("input[type=button]").attr("style","display:none");
}
function deleteData(id,type){
	if(confirm("是否删除？")){
		var tid = "<s:property value='#request.id'/>";
		var tab;
		if(type==4){
			tab = 1;
		}else{
			tab = 0;
		}
		$.ajax({
			type:"post",
			dataType:"json",
			url:"<%=basePath%>/contractStatus/deleteData.action",
			data:{
				id:id
			},
			error:function(){
					alert("系统连接失败，请联系管理员！");
				},
			success:function(object){
				alert("删除成功！");
				//window.top.location.reload()
				window.location.href = "<%=basePath%>/project/showView.action?id="+tid+"&tab="+tab;
			}
		});
	}
}

</script>
<div class="tabs_2" id="pageFoot">
	<ul>
		<li class="selected"><a href="javascript:void(0);" onclick="showDetail(this,'1');"><span>项目进展情况</span></a></li>
		<li><a href="javascript:void(0);" onclick="showDetail(this,'2');"><span>项目用款计划</span></a></li>
	</ul>
</div>
<div style="display: none;" id="routeArea">
	<select name="line" id="add_line">
		<option value="">---请选择---</option>
		<s:iterator id="route" value="#request.routes">
			<option value="<s:property value="#route.objectName"/>"><s:property value="#route.objectName"/></option>
		</s:iterator>
	</select>
</div>
<div class="mb10" id="progressArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;项目进展情况</span>
					<span style="float:right">
						<input type="button" id="addProgressButton" value="新增" onclick="showProgressAdd();"/>
					</span>
				</th>
			</thead>
			<tbody id="progress">
				<tr class="tit">
					<td width="5%;">序号</td>
					<td width="20%;" class="t_r" >填报部门</td>
					<td width="10%;" class="t_r" >填报人</td>
					<td width="10%;" class="t_r" >进展类型</td>
					<td width="10%;" class="t_r" >时间</td>
					<td width="35%;" class="t_r" >描述</td>
					<td width="10%;" class="t_r" colspan="3">操作</td>
				</tr>
				<s:iterator value="#request.progressList" id="list" status="st">
					<tr>
						<td class="t_c"><s:property value="#st.index+1"/></td>
						<td class="t_l">
							<input type="hidden" value="<s:property value='#list.reportDeptId'/>" id="reportDeptId">
							<s:property value="#list.reportDeptName"/>
						</td>
						<td class="t_l">
							<input type="hidden" value="<s:property value='#list.reportDeptName'/>" id="reportDeptName">
							<s:property value="#list.reportPersonName"/>
						</td>
						<td class="t_l"><s:property value="#list.progressType"/></td>						
						<td class="t_c"><s:property value="#list.time"/></td>
						<td class="t_l" title="<s:property value="#list.describe"/>"><s:property value="#list.describe"/></td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showProgressEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteProgress('<s:property value='#list.id'/>')" style="display:inline;">删除</a>
							<input type="button" value="保存" style="display: none;" onclick="saveProgressEdit(this,'<s:property value="#list.id"/>');">
							<input type="button" value="取消" style="display: none;" onclick="hideProgressEdit(this);">
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>

<div class="mb10" style="display: none;" id="planPayArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;项目用款计划支付情况</span>
					<span style="float:right">
						<input type="button" id="addButton" value="新增" onclick="showPlanAdd(4);"/>
					</span>
				</th>
			</thead>
			<tbody id="tbodyPlanPay">
				<tr class="tit">
					<td width="5%;" style="white-space:nowrap">序号</td>
					<td width="20%;" class="t_c" style="white-space:nowrap">事由</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">线路</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">金额(万元)</td>
					<td width="5%;" class="t_c" style="white-space:nowrap">百分比(%)</td>
					<td width="15%;" class="t_c" style="white-space:nowrap">支付时间</td>
					<td width="25%;" class="t_c" style="white-space:nowrap" >备注</td>
					<td width="10%;" class="t_c" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.planPay" id="list" status="st">
					<tr>
						<td class="t_c" style="white-space: nowrap;"><s:property value="#st.index+1"/></td>
						<td class="t_l" style="white-space: nowrap;" id="payReason" title="<s:property value="#list.reason"/>">
							<s:property value="#list.reason"/>
						</td>
						<td class="t_c" style="white-space: nowrap;" id="lineTd">
							<s:property value="#list.line"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="moneyTd">
							<s:property value="#list.money"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="persentTd">
							<s:property value="#list.persent"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<s:property value="#list.operateDate"/>
						</td>
						<td class="t_l" style="white-space: nowrap;" id="payRemark" title="<s:property value="#list.remark"/>">
							<s:property value="#list.remark"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<a href="javascript:void(0);" onclick="showEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteData('<s:property value="#list.id"/>','4');" style="display:inline;">删除</a>&nbsp;&nbsp;
							<input type="button" value="保存" style="display: none;" onclick="saveEdit(this,'<s:property value="#list.id"/>','4');">
							<input type="button" value="取消" style="display: none;" onclick="hideEdit(this);">
						</td>
					</tr>
				</s:iterator>
				<tr id="sumTr">
					<td></td>
					<td></td>
					<td class="t_c" style="white-space: nowrap;font-weight: bold;">用款计划累计支付</td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="moneyAll"></td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="persentAll"></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>
<!--Table End--></div>
</body>
</html>
