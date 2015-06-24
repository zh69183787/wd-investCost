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
<title>项目编辑</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/uploadify.css" />
<style>
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
<script src="../js/jquery.uploadify-3.1.js"></script>
<script src="../js/addDetail.js"></script>
<%String basePath = request.getContextPath(); %>
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
	$("select[name=payType]").val('<s:property value="project.payType" />');
});
</script>




<script type="text/javascript">
//日期控件
$(function(){
	$("#projectDestoryDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("#projectStarttimePlanDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			//$("#projectStarttimePlanDate").val(selectedDate.substring(0,7));
			$("#projectEndtimePlanDate").datepicker("option","minDate",selectedDate);
		}
	});
	$("#projectEndtimePlanDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			//$("#projectEndtimePlanDate").val(selectedDate.substring(0,7));
			$("#projectStarttimePlanDate").datepicker("option","maxDate",selectedDate);
		}
	});
	$("#approvalDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
	$("#projectFeasibilityDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
	$("#primaryDesignDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
});

$(function(){
	loadEditPage($("#projectMoneysource"));
});

//检查表单数据合法性
function checkForm(){
	
	var value ;
	//项目名称
	value = $("input[name=projectName]").val();
	if(value==null || value==""){
		$("input[name=projectName]").focus();
		alert("请输入项目名称！");
		return false;
	}

	value = $("input[name=projectNo]").val();
	if(value==null || value==""){
		$("input[name=projectNo]").focus();
		alert("请输入集团项目编号！");
		return false;
	}
	
	value = $("input[name=projectBudgetAll]").val();
	if(value==null || value==""){
		$("input[name=projectBudgetAll]").focus();
		alert("请输入"+$("#budget").html()+"！");
		return false;
	}else{
		if(!$.isNumeric(value)){
			$("input[name=projectBudgetAll]").focus();
			alert($("#budget").html()+"只能填数字！");
			return false;
		}
	}
	
	var mStatus = true;
	$("#display_moneysource").nextAll("p").each(function(){
		if(!$.isNumeric($(this).find("#detailInput").val())){
			alert("请输入数字");
			$(this).find("#detailInput").focus();
			mStatus = false;
			return false
		}
		if($(this).find("select").val()!="undefined" && $(this).find("select").val()==""){
			alert("请选择甲方(出资)的线路！");
			$(this).find("select").focus();
			mStatus = false;
			return false;
		}
		
	});
	if(!mStatus){
		return false;
	}
	
	//项目公司
	var projectCompany = $("#projectCompany").val();
	if(projectCompany==null || projectCompany==""){
		$("#projectCompany").focus();
		alert("请填写项目公司！");
		return false;
	}else{
		var projectCompanyId = $("#projectCompanyId").val();
		if(projectCompanyId==null || projectCompanyId==""){
			$("#projectCompany").focus();
			alert("该项目公司不存在，请重新填写！");
			return false;		
		}
	}
	
	//负责部门
	var projectAdddept = $("#projectAdddept").val();
	if(projectAdddept==null || projectAdddept==""){
		$("#projectAdddept").focus();
		alert("请填写负责部门！");
		return false;
	}else{
		var projectAdddeptId = $("#projectAdddeptId").val();
		if(projectAdddeptId==null || projectAdddeptId ==""){
			$("#projectAdddept").focus();
			alert("该负责部门不存在，请重新填写！");
			return false;
		}
	}
	//负责人
	var projectAddperson = $("input[name=projectAddperson]").val();
	if(projectAddperson==null || projectAddperson==""){
		$("#projectAddperson").focus();
		alert("请填写负责人");
		return false;
	}else{
		var projecttAddpersonId = $("input[name=projectAddpersonId]").val();
		if(projecttAddpersonId==null || projecttAddpersonId==""){
			$("#projectAddperson").focus();
			alert("该负责人不存在，请重新填写！");
			return false;
		}
	}
	
	//决算价
	value = $("input[name=settlementPrice]").val();
	if(value==null || value==""){

	}else{
		if(!$.isNumeric(value)){
			$("input[name=settlementPrice]").focus();
			alert("决算价只能填数字！");
			return false;
		}
	}
	//成本属性
	value = $("select[name=payType]").val();
	if(value==null || value==""){
		$("select[name=payType]").focus();
		alert("请选择成本属性！");
		return false;
	}
	

	var attachIds ="";
	$("#fileBody").find("a[name=attach_id]").each(function(){
		attachIds += $(this).attr("id")+",";
	});
	
	if(attachIds!=null && attachIds!="" && attachIds.length>1){
		attachIds = attachIds.substring(0,attachIds.length-1);
	}
	$("#attachIds").val(attachIds);
	saveDetail($("#projectMoneysource"));
	
	$.ajax({
		url			: 	"findProjectByNameAndNo.action?random="+Math.random(),
		dataType	: 	"json",
		type		:	'post',
		data: {
			projectName:$("#projectName").val(),
			projectNo:$("#projectNo").val(),
			projectId:$("#projectId").val()
		},
		error:function(){
			alert("系统连接失败请稍后再试！");
		},
		success: function( data ) {
			if(data!=null){
				if(confirm(data.feedbackInfo)){
					alert("保存成功！");
					$("#form").submit();
				}			
			}else{
				alert("保存成功！");
				$("#form").submit();
			}
		}
	});
	
	return false;
}

function deleteById(){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:$("#projectId").val()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				alert("删除成功");
				window.close();
			}
		});	
	}
}


//文件上传
$(function() {
    $("#uploadify").uploadify({
       'auto' : false,
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '<%=basePath%>/js/uploadify.swf', 
       'uploader' : '<%=basePath%>/contract/fileUpload.action',
      'fileTypeDesc' : '格式:txt,xls，xlsx,doc,docx,rar,zip,jpg,png,pdf',		//描述
       'fileTypeExts' : '*.txt;*.xls;*.xlsx;*.doc;*.docx;*.rar;*.zip;*.jpg;*.png;*.pdf',			//文件类型
       'fileSizeLimit' : '30000KB',			//文件大小
       'buttonText' : '选择文件',			//按钮名称
       'fileObjName'	:'uploadify',
       'successTimeout' : 5,
       'requeueErrors' : false,
       'removeTimeout' : 1,
       'removeCompleted' : true,
       'onSelectError' : function(file,errorCode,errorMsg) {
       		if(errorCode==-110){
       			this.queueData.errorMsg = "文件太大，无法上传！";
       		}
        }, 
       'onUploadSuccess' : function(file, data, response){
       		var attach = eval('(' + data + ')');
       		$("#fileTable").show();
       		var addHtml = "<tr style='word-break:break-all; word-wrap:break-word;'>"+
       						"<td class='t_l'  style='width:200px;'>"+
       							"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+attach.filename+"."+attach.fileextname+"</a>"+
       						"</td>"+
       						"<td class='t_r'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
       						"<td class='t_c'>"+attach.uploaddate+"</td>"+
       						"<td class='t_c'>"+
       						"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>下载</a></td>"+
       						"<td class='t_c'>"+
       						"<a href='#' onclick='removeFile(this)' id='"+attach.id+"' name='attach_id'>删除</a></td>"+
       					  "</tr>";
       		$("#fileBody").find("tr").each(function(){
       			if($.trim($(this).find("td:first").text())==(attach.filename+"."+attach.fileextname)){
       				var $target = $(this).find("td:last a");
					$.ajax({
						url			: 	"<%=basePath%>/attach/deleteAttach.action?random="+Math.random(),
						dataType	: 	"json",
						type		:	'post',
						data: {
							id:$target.attr("id")
						},
						error:function(){
							alert("系统连接失败请稍后再试！");
						},
						success: function( data ) {
							$target.parent().parent().remove();
							var trLength = $("#fileTable").find("tr").length;
							if(trLength==1){
								$("#fileTable").hide();
							}
						}
					});
       			}
       		});
       		$("#fileBody").append(addHtml);
       		$("#fileBody").show();
    	}
	});
});

//删除上传的附件
function removeFile(paramObj){
	if(confirm("是否删除?")){
		var $target = $(paramObj);
		$.ajax({
			url			: 	"<%=basePath%>/attach/deleteAttach.action?random="+Math.random(),
			dataType	: 	"json",
			type		:	'post',
			data: {
				id:$target.attr("id")
			},
			error:function(){
				alert("系统连接失败请稍后再试！");
			},
			success: function( data ) {
				$target.parent().parent().remove();
				var trLength = $("#fileTable").find("tr").length;
				if(trLength==1){
					$("#fileTable").hide();
				}
			}
		});
	}
}

//清空输入框
function clearInput(){
	$("#formBody").find("input[type=text]").val("");
	$("#formBody").find("textarea").val("");
	$("#formBody").find("select").each(function(){
		$(this).find("option:first").attr("selected",true);
	});
	
	$("#projectCompanyId").val("");
	$("#projectAdddeptId").val("");
	removeAll($("#projectMoneysource"));
	$("#hidden_company_id").val("");
	
}

//项目状态
$(function(){
	var statusValue = $("#hide_projectState").val();
	$("select[name=projectState]").children().each(function(){
		if($(this).val()==statusValue) 
			$(this).attr("selected",true);
	});
	var proValue = $("#hide_professionalType").val();
	$("select[name=professionalType]").children().each(function(){
		if($(this).val()==proValue) 
			$(this).attr("selected",true);
	});
});

//自动提示，项目公司
$(function(){
	var saveStatus = false;
	$("#projectCompany" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><deptName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</deptName><typeId>0</typeId></dataParams>";
			$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"token" : "<%=token%>",	
					"method" : "getMatchedDepts",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( data ) {
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.description,
							value: item.id
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#projectCompanyId").val(ui.item.value);
			$("#projectCompany").val(ui.item.label);
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$( "#projectCompanyId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#projectCompanyId").val("");
			}
		}
	});
});

//自动提示，负责部门
$(function(){
	var saveStatus = false;
	$("#projectAdddept" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/deptTree/findDeptByName.action?random="+Math.random(),
				dataType: "json",
				data: {
					"deptName":request.term
				},
				success: function( data ) {
					if(data!=null){
						response( $.map( data, function(item) {
							return {
								label: item.description,
								id: item.id,
								name:item.name
								//canChoose: item.name==item.description ? false : true
							}
						}));					
					}
				}
			});
		},
		minLength: 1,
		search: function() {
			saveStatus = false;
		},
		select: function( event, ui ) {
			if(ui.item.id=="99990" || ui.item.id=="99991"){		//维护保障中心的id
				alert("顶级科目，无法选择！");
				return false;
			}
			$("#projectAdddeptId").val(ui.item.id);
			$("#projectAdddept").val(ui.item.name);
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$( "#projectAdddeptId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#projectAdddeptId").val("");
			}
		}
	});
});

//自动提示，负责人
$(function(){
	var saveStatus = false;
	$("input[name=projectAddperson]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><userName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</userName></dataParams>";
			$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"token" : "<%=token%>",	
					"method" : "getMatchedDeptUsers",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( data ) {
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.userName+"("+item.deptName+")",
							value: item.userId,
							show : item.userName
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("input[name=projectAddpersonId]").val(ui.item.value);
			$("input[name=projectAddperson]").val(ui.item.show);
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$("input[name=projectAddpersonId]" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("input[name=projectAddpersonId]").val("");
			}
		}
	});
});

//自动提示，资金来源
$(function(){
	//var saveStatus = false;
	$("#display_moneysource" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/contract/findCompanyRouteWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"param":request.term.replace(/(^\s*)|(\s*$)/g,'')
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.description,
							value: item.id
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			$("#hidden_company_id").val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#display_moneysource").val(ui.item.label);
			$("#hidden_company_id").val(ui.item.value);
			//alert($("#hidden_company_id").val());
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$("#hidden_company_id").val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#hidden_company_id").val("");
			}
		}
	});
});

function addDetail(obj){
	addDetailFunc(obj,$("#hidden_company_id").val());
}

$(function(){
	if($("select[name=projectType]").val()=='1'){
		$("#budget").html("批复概算总额(万元)");
	}else{
		$("#budget").html("投资估算总额(万元)");
	}
});
//项目类型，下拉选择，改变投资估算总额名称
function changeType(target){
	var choosedValue = $(target).val();
	if(choosedValue=='1'){
		$("#budget").html("批复概算总额(万元)");
	}else{
		$("#budget").html("投资估算总额(万元)");
	}
}
</script>




</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">工程投资造价管理</a></li>
	<li><a href="#">项目封面</a></li>
	<li><a href="#">项目管理</a></li>
	<li class="fin">项目编辑</li>
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

<s:form action="saveEdit" name="project" namespace="/project" method="post" id="form">
<input type="hidden" value="<s:property value='project.createDate'/>" name="project.createDate"/>
<input type="hidden" value="<s:property value='project.removed'/>" name="project.removed"/>
<input type="hidden" value="<s:property value='project.id'/>" name="project.id"/>
<input type="hidden" name="attachIds" id="attachIds">
<input type="hidden" id="hidden_company_id">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/> &nbsp;
				<input type="button" value="删 除" onclick="deleteById();"/>
			</th>
		</thead>
		<tbody id="formBody">
		<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">项目名称</td>
				<td class="must">
					<input type="text" id="projectName" name="projectName" value="<s:property value="project.projectName" />" style="width: 300px;" maxlength="100"/>
					&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd">项目类型</td>
				<td>
					<select name="projectType" class="input_large" onchange="changeType(this);">
						<s:if test="project.projectType==1">
							<option value="">---请选择---</option>
							<option value="1" selected="selected">外部项目</option>
							<option value="2">内部项目资本类</option>
							<option value="3">内部项目大修类</option>
							<option value="4">内部项目能源类</option>
							<option value="5">其他类</option>
						</s:if>
						<s:elseif test="project.projectType==2">
							<option value="">---请选择---</option>
							<option value="1">外部项目</option>
							<option value="2" selected="selected">内部项目资本类</option>
							<option value="3">内部项目大修类</option>
							<option value="4">内部项目能源类</option>
							<option value="5">其他类</option>
						</s:elseif>
						<s:elseif test="project.projectType==3">
							<option value="">---请选择---</option>
							<option value="1">外部项目</option>
							<option value="2">内部项目资本类</option>
							<option value="3" selected="selected">内部项目大修类</option>
							<option value="4">内部项目能源类</option>
							<option value="5">其他类</option>
						</s:elseif>
						<s:elseif test="project.projectType==4">
							<option value="">---请选择---</option>
							<option value="1">外部项目</option>
							<option value="2">内部项目资本类</option>
							<option value="3">内部项目大修类</option>
							<option value="4" selected="selected">内部项目能源类</option>
							<option value="5">其他类</option>
						</s:elseif>
						<s:elseif test="project.projectType==5">
							<option value="">---请选择---</option>
							<option value="1">外部项目</option>
							<option value="2">内部项目资本类</option>
							<option value="3">内部项目大修类</option>
							<option value="4">内部项目能源类</option>
							<option value="5" selected="selected">其他类</option>
						</s:elseif>
						<s:else>
							<option value="">---请选择---</option>
							<option value="1">外部项目</option>
							<option value="2">内部项目资本类</option>
							<option value="3">内部项目大修类</option>
							<option value="4">内部项目能源类</option>
							<option value="5">其他类</option>
						</s:else>
					</select>
				</td>
			</tr>
			<tr>
				
				<td class="t_r lableTd" width="20%;">关键字</td>
				<td width="30%;"><input type="text"  name="keyword" class="input_xxlarge" maxlength="20" value="<s:property value="project.keyword" />"/></td>
				<td class="t_r lableTd" width="20%;">是否代建</td>
				<td width="30%;">
					<select name="substituteConstruction" class="input_large">
						<s:if test="project.substituteConstruction==1">
							<option value="">---请选择---</option>
							<option value="1" selected="selected">是</option>
							<option value="2">否</option>
						</s:if>
						<s:elseif test="project.substituteConstruction==2">
							<option value="">---请选择---</option>
							<option value="1">是</option>
							<option value="2" selected="selected">否</option>
						</s:elseif>
						<s:else>
							<option value="">---请选择---</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</s:else>
						
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd" width="20%;"><strong>集团项目编号</strong></td>
				<td class="must" width="30%;">
					<input type="text" id="projectNo" name="projectNo" value="<s:property value="project.projectNo" />" style="width: 300px;" maxlength="100"/>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd" width="20%;"><strong id="budget">投资估算总额(万元)</strong></td>
				<td class="must" width="30%;">
					<s:if test="project.projectBudgetAll==0">
						<input type="text"  name="projectBudgetAll" class="input_large" maxlength="10" value="0"/>&nbsp;&nbsp;*
					</s:if>
					<s:else>
						<input type="text"  name="projectBudgetAll" class="input_large" maxlength="10" value="<s:property value="project.projectBudgetAll"/>"/>&nbsp;&nbsp;*
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">集团发文编号</td>
				<td>
					<input type="text" id="" name="dispatchNo" class="input_xxlarge" value="<s:property value='project.dispatchNo'/>">	
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<input type="text"  class="input_large" name="approvalDate" id="approvalDate" value="<s:property value='project.approvalDate'/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">计划开始时间</td>
				<td><input type="text" id="projectStarttimePlanDate" name="projectStarttimePlanDate" class="input_large" maxlength="10" readonly="readonly" value="<s:property value="project.projectStarttimePlanDate" />"/></td>
				<td class="t_r lableTd">计划完成时间</td>
				<td><input type="text" id="projectEndtimePlanDate" name="projectEndtimePlanDate" class="input_large" maxlength="20" readonly="readonly" value="<s:property value="project.projectEndtimePlanDate" />"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">资金来源（万元）</td>
				<td colspan="3">
					<input type="text" class="input_xxlarge" id="display_moneysource"/>
					<input type="button" value="添加" onclick="addDetail(this)"/>
					<input type="button" value="删除" onclick="deleteDetail(this)"/>
					<input type="hidden" name="projectMoneysource" id="projectMoneysource" value="<s:property value="project.projectMoneysource" />"/>
					<!-- 
					<textarea rows="2" cols="2" name="projectMoneysource"><s:property value="project.projectMoneysource" /></textarea>
					 -->
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">资金渠道性质</td>
				<td>
					<select class="input_large" name="moneySourceType">
						<s:if test="project.moneySourceType==1">
							<option value="">---请选择---</option>
							<option value="1" selected="selected">建设资金</option>
							<option value="2">自有资金</option>
							<option value="3">财政资金</option>
						</s:if>
						<s:elseif test="project.moneySourceType==2">
							<option value="">---请选择---</option>
							<option value="1">建设资金</option>
							<option value="2" selected="selected">自有资金</option>
							<option value="3">财政资金</option>
						</s:elseif>
						<s:elseif test="project.moneySourceType==3">
							<option value="">---请选择---</option>
							<option value="1">建设资金</option>
							<option value="2">自有资金</option>
							<option value="3" selected="selected">财政资金</option>
						</s:elseif>
						<s:else>
							<option value="">---请选择---</option>
							<option value="1">建设资金</option>
							<option value="2">自有资金</option>
							<option value="3">财政资金</option>
						</s:else>
					</select>
				</td>
				<td class="t_r lableTd">专业分类</td>
				<td>
					<input type="hidden" value="<s:property value='project.professionalType'/>" id="hide_professionalType">
					<select name="professionalType" class="input_large">
						<option value="">---请选择---</option>
						<option value="1">车辆</option>
						<option value="2">供电</option>
						<option value="3">通号</option>
						<option value="4">工务</option>
						<option value="5">基地</option>
						<option value="6">车站机电</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">项目公司</td>
				<td class="must">
					<input type="text"  name="projectCompany" id="projectCompany" class="input_xxlarge" maxlength="20" value="<s:property value="project.projectCompany" />"/>&nbsp;&nbsp;*
					<input type="hidden" name="projectCompanyId" id="projectCompanyId" value="<s:property value='project.projectCompanyId'/>">
				</td>
				<td class="t_r lableTd">项目状态</td>
				<td>	
					<input type="hidden" value="<s:property value='project.projectState'/>" id="hide_projectState">
					<select name="projectState" class="input_large">
						<option value="">---请选择---</option>
						<option value="1">项目准备</option>
						<option value="2">项目实施</option>
						<option value="3">销项完成</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">负责部门</td>
				<td class="must">
					<input type="text"  name="projectAdddept" id="projectAdddept" class="input_xxlarge" maxlength="10" value="<s:property value="project.projectAdddept" />"/>&nbsp;&nbsp;*
					<input type="hidden" name="projectAdddeptId" id="projectAdddeptId" value="<s:property value='project.projectAdddeptId'/>">
				</td>
				<td class="t_r lableTd">负责人</td>
				<td class="must">
					<input type="text"  name="projectAddperson" class="input_large" maxlength="10" value="<s:property value="project.projectAddperson" />"/>&nbsp;&nbsp;*
					<input type="hidden" name="projectAddpersonId" value="<s:property value='project.projectAddpersonId'/>">
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">项目销项时间</td>
				<td>
					<input type="text"  class="input_large" name="projectDestoryDate" id="projectDestoryDate" readonly="readonly" value="<s:property value='project.projectDestoryDate'/>">	
				</td>
				<td class="t_r lableTd">市拨补贴资金</td>
				<td>
					<select name="cityAllowance" class="input_large">
						<s:if test="project.cityAllowance==1">
							<option value="">---请选择---</option>
							<option value="1" selected="selected">是</option>
							<option value="2">否</option>					
						</s:if>
						<s:elseif test="project.cityAllowance==2">
							<option value="">---请选择---</option>
							<option value="1">是</option>
							<option value="2" selected="selected">否</option>
						</s:elseif>
						<s:else>
							<option value="">---请选择---</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</s:else>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">决算价(万元)</td>
				<td>
					<input type="text" class="input_large" name="settlementPrice" id="settlementPrice" maxlength="20" value="<s:property value="project.settlementPrice" />"/>	
				</td>
				<td class="t_r lableTd">成本属性</td>
				<td class="must">
					<select name="payType" class="input_large">
						<option value="">---请选择---</option>
						<option value="1">成本性</option>
						<option value="2">费用性</option>
					</select>&nbsp;&nbsp;*
				</td>
			</tr>				
			
			<tr>
				<td class="t_r lableTd">上传附件<br/>(单个文件上传最大为10Mb)</td>
				<td colspan="3">
					<input type="file" name="uploadify" id="uploadify" />
					<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
					<input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">
					
					<s:if test="#request.attachList!=null && #request.attachList.size()>0">
						<table id="fileTable">
							<tbody style="width: 550px;border: solid;border-color: #D0D0D3;" id="fileBody">
								<tr style="border: solid;border: #D0D0D3;">
									<td width="200px;" class="t_c" >文件名</td>
									<td width="100px;" class="t_c">大小(KB)</td>
									<td width="150px;" class="t_c">上传时间</td>
									<td width="100px;" class="t_c" colspan="2">操作</td>
								</tr>
								<s:iterator id="attach" value="#request.attachList">
									<s:if test="#attach.status!=null && #attach.status=='project'">
										<tr style="word-break:break-all; word-wrap:break-word;">
											<td class="t_l" style="width:200px;">
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
											<td class="t_c">
												<a href="#" onclick="removeFile(this)" id="<s:property value='#attach.id'/>" name="attach_id">删除</a>
											</td>
										</tr>
									</s:if>
									<s:else>
										<tr style="word-break:break-all; word-wrap:break-word;">
											<td width="200px;" class="t_l" style="word-break:break-all; word-wrap:break-word;">
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
											</td>
											<td class="t_c"><s:property value="#attach.uploaddate"/></td>
											<td class="t_c">
												<a href="<%=basePath%>/attach/downloadAttach.action?attachId=<s:property value='#attach.id'/>">下载</a>
											</td>
											<td class="t_c">
												<a href="#" onclick="removeFile(this)" id="<s:property value='#attach.id'/>" name="attach_id">删除</a>
											</td>
										</tr>
									</s:else>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
					<s:else>
						<table id="fileTable">
							<tbody style="width: 550px;border: solid;border-color: #D0D0D3;display: none" id="fileBody">
								<tr style="border: solid;border: #D0D0D3;" style="word-break:break-all; word-wrap:break-word;">
									<td width="200px;" class="t_c" >文件名</td>
									<td width="100px;" class="t_c">大小(KB)</td>
									<td width="150px;" class="t_c">上传时间</td>
									<td width="100px;" class="t_c" colspan="2">操作</td>
								</tr>
							</tbody>
						</table>
					</s:else>
				</td>								
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="2" cols="5" name="remark" ><s:property value="project.remark" /></textarea>					
				</td>
			</tr>
			
			<tr><td colspan="4" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">工可信息</td><td colspan="3"></td></tr>
			
			<tr>
				<td class="t_r lableTd">工可项目批复文号</td>
				<td>
					<input type="text"  class="input_xxlarge" maxlength="100" name="projectFeasibilityNo" id="projectFeasibilityNo" value="<s:property value='project.projectFeasibilityNo'/>">	
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<input type="text" class="input_large" maxlength="100" name="projectFeasibilityDate" id="projectFeasibilityDate" readonly="readonly" value="<s:property value='project.projectFeasibilityDate'/>">	
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">投资估算总额(万元)</td>
				<td colspan="3">
					<s:if test="project.projectFeasibilityBudget==0">
						<input type="text"  class="input_xxlarge" maxlength="20" name="projectFeasibilityBudget" id="projectFeasibilityBudget" value="0">
					</s:if>
					<s:else>
						<input type="text"  class="input_xxlarge" maxlength="20" name="projectFeasibilityBudget" id="projectFeasibilityBudget" value="<s:property value="project.projectFeasibilityBudget"/>">
					</s:else>
				</td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="2" cols="5" name="projectFeasibilityRemark"><s:property value='project.projectFeasibilityRemark'/></textarea>					
				</td>
			</tr>
			
			<tr><td colspan="4" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">初步设计信息</td><td colspan="3"></td></tr>
			
			<tr>
				<td class="t_r lableTd">初步设计批复文号</td>
				<td>
					<input type="text"  class="input_xxlarge" maxlength="100" name="primaryDesignNo" value="<s:property value='project.primaryDesignNo'/>">	
				</td>
				<td class="t_r lableTd">批文时间</td>
				<td>
					<input type="text" class="input_large" name="primaryDesignDate" id="primaryDesignDate" readonly="readonly" value="<s:property value='project.primaryDesignDate'/>">	
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">批复概算(万元)</td>
				<td>
					<s:if test="project.primaryDesignBudget==0">
						<input type="text"  class="input_xxlarge" name="primaryDesignBudget" value="<s:property value='0'/>">	
					</s:if>
					<s:else>
						<input type="text"  class="input_xxlarge" name="primaryDesignBudget" value="<s:property value="project.primaryDesignBudget"/>">
					</s:else>
				</td>
				<td class="t_r lableTd">调整概算(万元)</td>
				<td>
					<s:if test="project.primaryDesignMoney==0">
						<input type="text" class="input_xxlarge" maxlength="20" name="primaryDesignMoney" value="<s:property value='0'/>">
					</s:if>
					<s:else>
						<input type="text" class="input_xxlarge" maxlength="20" name="primaryDesignMoney" value="<s:property value="project.primaryDesignMoney"/>">
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="2" cols="5" name="primaryDesignRemark"><s:property value='project.primaryDesignRemark'/></textarea>					
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/>&nbsp;
				<input type="button" value="删 除" onclick="deleteById();"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
