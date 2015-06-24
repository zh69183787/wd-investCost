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
<title>合同新增</title>
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

<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../js/jquery.uploadify-3.1.js"></script>
<script src="../js/addDetail.js"></script>
<script src="http://10.1.48.16:8080/workflow/contract/js/purchase.js"></script>

<script src="../js/datepicker/js/jquery.tree.core.min.js"></script>
<script src="../js/datepicker/js/contract_type_tree.js"></script>
<link rel="stylesheet" href="../css/tree/style.css" type="text/css">


<%String basePath = request.getContextPath(); %>
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});
</script>




<script type="text/javascript">
//日期控件
$(function(){
	
		
	$("input[name=contractDestoryDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=contractPassedDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=contractSignedDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
	
	$("input[name=contractStartDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "input[name=contractEndDate]" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name=contractEndDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "input[name=contractStartDate]" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
	
});

//自动填写，对方单位
$(function(){
	$("#buildSupplierName" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/corporation/findPsCorporationByCompNameWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',	
					"compName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.companyNameChn,
							buildSupplierId:item.compId
						}
					}));
				}
			});
		},
		minLength: 1,
		change : function(event,ui){  
		},
		search:function(){
			$("#buildSupplierId").val("");
		},
		select: function( event, ui ) {
			$("#buildSupplierName").val(ui.item.label);
			$("#buildSupplierId").val(ui.item.buildSupplierId);
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ){
				return false;
		}
	});
});

//检查表单数据合法性
function checkForm(){
	
	var value ;
	value = $("input[name=contractName]").val();	//合同名称
	if(value==null || value==""){
		$("input[name=contractName]").focus();
		alert("请输入合同名称！");
		return false;
	}
	
	value = $("input[name=contractNo]").val();	//合同编号
	if(value==null || value==""){
		$("input[name=contractNo]").focus();
		alert("请输入合同编号！");
		return false;
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
	
	var value1 = $("input[name=buildSupplierId]").val();
	value = $("input[name=buildSupplierName]").val();	//对方单位
	if((value!=null && value!="") && value1==""){
		$("input[name=buildSupplierName]").focus();
		alert("对方单位不存在！");
		return false;
	}
	
	value = $("input[name=contractPrice]").val();	//合同价
	if(value==null || value==""){
		$("input[name=contractPrice]").focus();
		alert("请输入合同价！");
		return false;
	}else{
		if(!$.isNumeric(value)){
			$("input[name=contractPrice]").focus();
			alert("请输入数字！");
			return false;
		}
	}

    if($("#inviteBidType").val() != ""){
        $("#inviteBidType").val($("#inviteBidTypeId").val())
    }
	
	/*
	var subjectMoney = $("#subjectMoney").val()==""?0:parseFloat($("#subjectMoney").val());
	if(parseFloat(value) < subjectMoney){
		alert("合同价(万元)高于绑定的概算科目的概算批复值，保存失败！");
		$("input[name=estimateSubjectName]").focus();
		return false;
	}*/
	
	value = $("#verifyPrice").val();	//审价单位审价
	if(value!=null && value!=""){
		if(!$.isNumeric(value)){
			$("#verifyPrice").focus();
			alert("请输入数字！");
			return false;
		}
	}
	
	value = $("#finalPrice").val();	//投资监理审价
	if(value!=null && value!=""){
		if(!$.isNumeric(value)){
			$("#finalPrice").focus();
			alert("请输入数字！");
			return false;
		}
	}
	
	value = $("#nationVerifyPrice").val();	//国家审计审价
	if(value!=null && value!=""){
		if(!$.isNumeric(value)){
			$("#nationVerifyPrice").focus();
			alert("请输入数字！");
			return false;
		}
	}
	
	var contractOwnerExecuteName = $("#contractOwnerExecuteName").val();
	var contractOwnerExecuteId = $("#contractOwnerExecuteId").val();
	if(contractOwnerExecuteName!=null && contractOwnerExecuteName!=""){
		if(contractOwnerExecuteId==null || contractOwnerExecuteId==""){
			$("#contractOwnerExecuteName").focus();
			alert("该甲方(执行)不存在,请重新填写！");
			return false;
		}
	}
	
	value = $("select[name=contractType1]").val();	//合同分类
	if(value!=null && value!=""){
		value = $("select[name=contractType2]").val();
		if(value==null || value==''){
			alert("请选择合同分类");
			$("select[name=contractType2]").focus();
			return false;		
		}
	}
	value = $("select[name='contractGrouping']").val();
	if(value==null || value==""){
		alert("请选择合同分组！");
		$("select[name='contractGrouping']").focus();
		return false;
	}

	value = $("input[name=projectId]").val();	//项目id
	if(value==null || value==""){
		//alert("项目编号或项目名称不存在");
		//$("input[name=projectNo]").focus();
		//return false;
	}
	var subjectName = $("input[name=estimateSubjectName]").val();
	var subjectId = $("input[name=estimateSubjectId]").val();
	if(subjectName!=null && subjectName!=""){
		if(subjectId==null || subjectId==""){
			alert("该概算科目不存在，请重新填写！");
			$("input[name=estimateSubjectName]").focus();
			return false;
		}
	}
	
	var attachIds ="";
	$("#fileBody").find("a[name='attach_id']").each(function(){
		attachIds += $(this).attr("id")+",";
	});
	
	if(attachIds!=null && attachIds!="" && attachIds.length>1){
		attachIds = attachIds.substring(0,attachIds.length-1);
	}
	$("#attachIds").val(attachIds);
	
	//拼接合同分类
	var type =  $("select[name=contractType1]").val()+","+ $("select[name=contractType2]").val()
	if(type!=","){
		$("input[name=contractType]").val(type);
	}
	 
	
	saveDetail($("#contractOwnerName"));
	alert("保存成功！");
	return true;
}


//自动提示模糊搜索,项目编号
$(function(){
	var saveStatus;
	$("input[name=projectNo]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByProjectNoWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectNo" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.projectNo,
							value: item.id,
							projectName:item.projectName
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			//saveStatus = false;
			$("input[name=projectId]").val("");
			$("input[name=projectName]").val("");
			$("input[name=estimateSubjectName]").val("");
			$("input[name=estimateSubjectId]").val("");
			//$("#subjectMoney").val("");
			$("input[name=estimateSubjectName]").attr("disabled","true");
		},
		select: function( event, ui ) {
			$("input[name=projectNo]").val(ui.item.label);
			$("input[name=projectName]").val(ui.item.projectName);
			$("input[name=projectId]").val(ui.item.value);
			$("input[name=estimateSubjectName]").removeAttr("disabled");
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				return false;
		},
		change : function(event,ui){
			//$("input[name=estimateSubjectName]").val("");
			//$("input[name=estimateSubjectName]").attr("disabled","true");
		}
	});
});

//项目名称
$(function(){
	$("input[name=projectName]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByProjectNameWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.projectName,
							value: item.id,
							projectName:item.projectName,
							projectNo:item.projectNo
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("input[name=projectId]").val("");
			$("input[name=projectNo]").val("");
			$("input[name=estimateSubjectName]").val("");
			$("input[name=estimateSubjectId]").val("");
			//$("#subjectMoney").val("");
			$("input[name=estimateSubjectName]").attr("disabled","true");
		},
		select: function( event, ui ) {
			$("input[name=projectNo]").val(ui.item.projectNo);
			$("input[name=projectName]").val(ui.item.projectName);
			$("input[name=projectId]").val(ui.item.value);
			$("input[name=estimateSubjectName]").removeAttr("disabled");
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				return false;
		},
		change : function(event,ui){
		}
	});
});


//自动提示模糊搜索,概算科目名称
$(function(){


    $("#inviteBidTypeDiv").wrapSelect({
        url:"http://10.1.48.16:8080/workflow/contract-reviewUtil/dictionary.action",
        param:{"random":Math.random(),"code":"purchase"},
        // selectVal:"24",//默认选中对象
        name:"inviteBidType",
        label:"NAME",
        value:"ID"
    });







	var saveStatus;
	$("input[name=estimateSubjectName]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/quantitiesSubject/findSubjectByProjectId.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectId" : $("input[name=projectId]").val(),
					"subjectName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.wbsId + "-" + item.subjectName + "-" + item.sum+"万元",
							value: item.subjectId,
							name : item.subjectName,
							sum  : item.sum 
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("input[name=estimateSubjectId]").val("");
			//$("#subjectMoney").val("");
		},
		select: function( event, ui ) {
			$("input[name=estimateSubjectName]").val(ui.item.label);
			$("input[name=estimateSubjectId]").val(ui.item.value); 
			//$("#subjectMoney").val(ui.item.sum);
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				return false;
		},
		change : function(event,ui){
			//$("input[name=estimateSubjectName]").val("");
			//$("input[name=estimateSubjectName]").attr("disabled","true");
		}
	});
});

//甲方（执行）
$(function(){
	var saveStatus = false;
	$("#contractOwnerExecuteName" ).autocomplete({
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
			$("#contractOwnerExecuteId").val(ui.item.id);
			$("#contractOwnerExecuteName").val(ui.item.name);
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
				$( "#contractOwnerExecuteId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#contractOwnerExecuteId").val("");
			}
		}
	});
});

//自动提示，资金来源,甲方(出资)
$(function(){
	var saveStatus = false;
	$("#display_moneysource" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "findCompanyRouteWithFuzzySearch.action?random="+Math.random(),
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
			$("#hidden_contract_owner_id").val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#display_moneysource").val(ui.item.label);
			$("#hidden_contract_owner_id").val(ui.item.value);
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
				$("#hidden_contract_owner_id").val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#hidden_contract_owner_id").val("");
			}
		}
	});
});

//文件上传
$(function() {
    $("#uploadify").uploadify({
       'auto' : false,
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '../js/uploadify.swf', 
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
       						"<td class='t_l' style='width:200px;'>"+
       							"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+attach.filename+"."+attach.fileextname+"</a>"+
       						"</td>"+
       						"<td class='t_r'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
       						"<td class='t_c'>"+attach.uploaddate+"</td>"+
       						"<td class='t_c'><a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"' id='"+attach.id+"'>下载</a></td>"+
       						"<td class='t_c'><a href='#' onclick='removeFile(this)' id='"+attach.id+"' name='attach_id'>取消</a></td>"+
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


//显示合同分类
function showContractType(paramObj){
	var addHtml;
	if($(paramObj).val()=="1"){
		addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>工程类</option>"+
					"<option value='2'>勘察类</option>"+
					"<option value='3'>设计类</option>"+
					"<option value='4'>前期类</option>"+
					"<option value='5'>其他类</option>"+
					"<option value='6'>监理类</option>"+
					"<option value='7'>科研类</option>"+
					"<option value='8'>咨询类</option>";
	}else if($(paramObj).val()=="2"){
		addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>服务</option>"+
					"<option value='2'>工程</option>"+
					"<option value='3'>货物</option>";
	}else if($(paramObj).val()=="3"){
		addHtml = "<option value='1'>其他类</option>";
	}else{
		addHtml = "<option value=''>---请选择---</option>";
	}
	$(paramObj).next().html(addHtml);

}

function addDetail(obj){
	addDetailFunc(obj,$("#hidden_contract_owner_id").val());
}

function clearInput(){
	removeAll($("#contractOwnerName"));
	$("#hidden_contract_owner_id").val("");
	resetAllFile();
}


//点击重置后清空附件，提示，是否删除删除所有附件
function resetAllFile(){
	if($("#fileBody").find("a[name='attach_id']").length>0){
		if(confirm("是否删除所有附件？")){
			$("#fileBody").find("a[name='attach_id']").each(function(){
				var $target = $(this);
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
			});	
		}
	}
	if($("div[class='cancel']").length>0){
		$("div[class='cancel']").each(function(){
			window.location.href=$(this).find("a").attr("href");
		});
	}
}
function clearAttribute(){
	$("#contractAttributeId").val("");
	$("#contractAttribute").val("");
	
}
</script>




</head>

<body>
<div class="main">
	<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
		width="46" height="30" alt="收起"></div>
		<div class="posi fl">
		<ul>
			<li><a href="#">工程投资造价管理</a></li>
			<li><a href="#">合同封面</a></li>
			<li><a href="#">合同管理</a></li>
			<li class="fin">合同新增</li>
		</ul>
		</div>
		<div class="fr lit_nav">
		<ul>
			<li><a class="print" href="#">打印</a></li>
			<li><a class="storage" href="#">网络硬盘</a></li>
			<li><a class="rss" href="#">订阅</a></li>
			<li><a class="chat" href="#">聊天</a></li>
			<li><a class="query" href="#">查询</a></li>
		</ul>
		</div>
	</div>


<div class="mb10">
<!-- 
<input type="hidden" id="subjectMoney">
 -->
<s:form action="saveAdd" name="contract" namespace="/contract" method="post">
<input type="hidden" name="attachIds" id="attachIds">
<input type="hidden" name="contractType" id="contractType">
<input type="hidden" id="hidden_contract_owner_id"/>

	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit"" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" onclick="clearInput()"/> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd" width="19%;">合同名称</td>
				<td class="must" width="31%;">
					<input type="text"  name="contractName" class="input_xxlarge" maxlength="90"/>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd" width="20%;"><strong>合同编号</strong></td>
				<td class="must" width="30%;">
					<input type="text"  name="contractNo" class="input_xxlarge"  maxlength="100"/>&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd" width="20%;"><strong>合同价(万元)</strong></td>
				<td class="must" width="30%;">
					<input type="text"  name="contractPrice" class="input_large" maxlength="20"/>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd">审定价(投资监理)(万元)</td>
				<td>
					<input type="text" name="finalPrice" id="finalPrice" class="input_large" maxlength="50"/>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">审定价(审价单位)(万元)</td>
				<td>
					<input type="text" name="verifyPrice" id="verifyPrice" class="input_large" maxlength="50"/>
				</td>
				<td class="t_r lableTd">审定价(国家审计)(万元)</td>
				<td>
					<input type="text" name="nationVerifyPrice" id="nationVerifyPrice" class="input_large" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">甲方(出资)(万元)</td>
				<td colspan="3">
					<input type="text" class="input_xxlarge" id="display_moneysource"/>
					<input type="button" value="添加" onclick="addDetail(this)"/>
					<input type="button" value="删除" onclick="deleteDetail(this)"/>
					<input type="hidden" name="contractOwnerName" id="contractOwnerName" class="input_xxlarge" maxlength="30"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">甲方(执行)</td>
				<td>
					<input type="text" name="contractOwnerExecuteName" id="contractOwnerExecuteName" class="input_xxlarge" maxlength="30"/>
					<input type="hidden" name="contractOwnerExecuteId" id="contractOwnerExecuteId"/>
				</td>
				<td class="t_r lableTd">对方单位</td>
				<td>
					<input type="text" id="buildSupplierName" name="buildSupplierName" class="input_xxlarge" maxlength="30"/>
					<input type="hidden" name="buildSupplierId" id="buildSupplierId" value="<s:property value='contract.buildSupplierId'/>">
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">支付方式</td>
				<td>
					<input type="text" name="payType" class="input_xxlarge" maxlength="40">
				</td>
				<td class="t_r lableTd">合同签约时间</td>
				<td><input type="text"  name="contractSignedDate" class="input_large" maxlength="20" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同审批通过时间</td>
				<td><input type="text"  name="contractPassedDate" class="input_large" maxlength="10" readonly="readonly"/></td>
				<td class="t_r lableTd">合同期限</td>
				<td>
					<input type="text"  name="contractStartDate" class="input_large" readonly="readonly"/>&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text"  name="contractEndDate" class="input_large" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同状态</td>
				<td>
					<select name="contractStatus" class="input_large">
						<option value="">---请选择---</option>
						<option value="0">已备案</option>
						<option value="1">实施中</option>
						<option value="2">已竣工</option>
						<option value="3">已销号</option>
						<option value="4">过程中</option>
						<option value="5">已取消</option>
                        <option value="6">已终止</option>
					</select>
				</td>
				<td class="t_r lableTd">合同分组</td>
				<td colspan="3" class="must">
					<select name="contractGrouping" class="input_large">
						<option value="">---请选择---</option>
						<option value="1">成本内</option>
						<option value="2">成本外</option>
						<option value="3">无</option>
					</select>
					&nbsp;&nbsp;*
				</td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">自有编号</td>
				<td><input type="text"  name="selfNo" class="input_xxlarge" maxlength="100"/></td>
				<td class="t_r lableTd">合同销号时间</td>
				<td><input type="text"  name="contractDestoryDate" class="input_large" maxlength="10" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">采购方式</td>
				<td>
                    <div id="inviteBidTypeDiv">

                    </div>
					<%--<select name="inviteBidType" class="input_large">--%>
						<%--<option value="">---请选择---</option>--%>
						<%--<option value="1">公开招标</option>--%>
						<%--<option value="2">采购平台</option>--%>
						<%--<option value="3">直接委托</option>--%>
					<%--</select>--%>
				</td>
				<td class="t_r lableTd">合同登记人</td>
				<td >
					<input name="registerPersonName" readonly="readonly" value="<%=session.getAttribute("userName")==null?"":session.getAttribute("userName")%>">
					<input type="hidden" name="registerPersonLoginname" value="<%=session.getAttribute("loginName") %>">			
				</td>
			</tr>
			<tr>		
				<td class="t_r lableTd">合同分类</td>
				<td>
					<select name="contractType1" class="input_large" onchange="showContractType(this);">
						<option value="">---请选择---</option>
						<option value="1">建设类</option>
						<option value="2">运维类</option>
						<option value="3">其他类</option>
					</select>
					&nbsp;&nbsp;-&nbsp;&nbsp;
					<select name="contractType2" class="input_large">
						<option value="">---请选择---</option>
					</select>
				</td>
				<td class="t_r lableTd">合同属性（建设类）</td>
				<td>
					<input type="text"  readonly="readonly"  name="contractAttribute" id="contractAttribute" class="input_large" maxlength="100"/>
					<input type="button" value="选择" id="contractAttributeBtn"/>
					<input type="button" value="清除" onclick="clearAttribute();"/>
					<input type="hidden" name="contractAttributeId" id="contractAttributeId" value=""/>
				</td> 
			</tr>	
			<tr>
				<td class="t_r lableTd">合同附件<br/>(单个文件上传最大为10Mb)</td>
				<td colspan="3">
					<input type="file" name="uploadify" id="uploadify" />
					<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
					<input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">
					<table style="display: none;" id="fileTable">
						<tbody style="width: 550px;border: solid;border-color: #D0D0D3;" id="fileBody">
							<tr style="border: solid;border: #D0D0D3;">
								<td width="200px;" class="t_c">文件名</td>
								<td width="100px;" class="t_c">大小(KB)</td>
								<td width="150px;" class="t_c">上传时间</td>
								<td width="100px;" class="t_c" colspan="2">操作</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同内容</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="contractContent" style="width: 360px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<input type="hidden" name="projectId" value="<s:property value='contract.projectId'/>">
				<td class="t_r lableTd">项目编号</td>
				<td class="must">
					<s:if test="#request.project!=null">
						<input type="text"  name="projectNo" class="input_xxlarge" maxlength="50" value="<s:property value='#request.project.projectNo'/>" readonly="readonly"/>
					</s:if>
					<s:else>
						<input type="text"  name="projectNo" class="input_xxlarge" maxlength="50"/>
					</s:else>
					&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd">项目名称</td>
				<td class="must">
					<s:if test="#request.project!=null">
						<input type="text"  name="projectName" class="input_xxlarge" maxlength="20" value="<s:property value='#request.project.projectName'/>" readonly="readonly"/>
					</s:if>
					<s:else>
						<input type="text"  name="projectName" class="input_xxlarge" maxlength="20" value="<s:property value='#request.project.projectName'/>"/>
					</s:else>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">绑定概算科目</td>
				<td colspan="3">
					<input type="hidden" name="estimateSubjectId"/>
					<input type="text" class="input_xxlarge" maxlength="20" disabled="disabled" name="estimateSubjectName"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="5" cols="5" name="remark" ></textarea>					
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">预算类型</td>
				<td><input type="text" class="input_xxlarge" name="budgetType" maxlength="20"/></td>
				<td class="t_r lableTd">预算类型编码</td>
				<td><input type="text" class="input_xxlarge" name="budgetTypeCode" maxlength="20"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">统计类型</td>
				<td colspan="3" class="must">
					<select name="statType" class="input_large">
						<option value="">---请选择---</option>
						<option value="1">大修更新</option>
						<option value="2">维修费</option>
						<option value="3">管理费</option>
						<option value="4">运营间接费</option>
						<option value="5">内部人工费</option>
						<option value="6">委外人工经费</option>
						<option value="7">收入</option>
						<option value="8">其他直接费</option>
						<option value="9">监测支出</option>
						<option value="10">其他</option>
					</select>
					&nbsp;&nbsp;*
				</td>
			</tr>
			
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" onclick="clearInput()"/>&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
</div>


<div>
    <jsp:include page="contract_attribute_tree.jsp"></jsp:include>
</div>
</body>
</html>
