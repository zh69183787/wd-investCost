﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
String path = request.getContextPath();
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
<meta http-equiv="x-ua-compatible" content="IE=8">
<title>KPI销缺列表</title>
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
<script src="../js/jquery.uploadify-3.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/addDetail.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<%String basePath = request.getContextPath(); %>
<script type="text/javascript">

$(document).ready(function () {
	
	var $tbInfo = $(".filter .query input:text");
	$tbInfo.each(function () {
	    $(this).focus(function () {
	        $(this).attr("placeholder", "");
	    });
	});
	var h1 = $("dl").width();
	var h2 = $("dt").width();
	$("dd").width(h1-h2-10);

	$(window).resize(function() {
    	var h1 = $("dl").width();
		var h2 = $("dt").width();
		$("dd").width(h1-h2-10);
	});
	var $tblAlterRow = $(".table_1 tbody tr:even");
	if ($tblAlterRow.length > 0)
	$tblAlterRow.css("background","#fafafa");


    $("select[name=contractStatus] > option[value='<s:property value="contract.contractStatus"/>']").attr("selected",true);
});


//日期控件        
$(function(){
	
	$("input[name='contractSignedEndDate']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='contractSignedDate']").datepicker( "option", "maxDate", selectedDate );
		}
	});
	$("input[name='contractSignedDate']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='contractSignedEndDate']").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name='clearBeginDate']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='clearBeginDate']").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name='clearEndDate']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='clearEndDate']").datepicker( "option", "minDate", selectedDate );
		}
	});
});
    
//页面初始化时隐藏搜索条件
$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide"){
		$("#searchArea").css("display","none");
		//$("#quickSearch").show();
	}else{
		$("#searchArea").css("display","block");
		//$("#quickSearch").hide();
	}
});

//控制显示或隐藏查询条件
function showOrHideControl(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide"){
		$("#searchArea").slideDown();
		$("input[name=showOrHide]").val("show");
		//$("#quickSearch").slideUp();
	}else{
		$("#searchArea").slideUp();
		$("input[name=showOrHide]").val("hide");
		//$("#quickSearch").slideDown();
	}
}        
</script>
 
 
<script type="text/javascript">
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		//window.location.href = "findContractByPageToBeComplemented.action";
		$("form").submit();
		clearInterval(refresh);
	}
}

//跳转到新增页面
function showAddPage(){
	sonWindow = window.open("showAdd.action");
}

//跳转到编辑页面
function showEditPage(id){
	sonWindow = window.open('showEdit.action?id='+id);
}

function goPage(pageNo,type){
	//type=0,直接跳转到制定页
	if(type=="0"){
  		var pageCount = $("#totalPageCount").val();
  		var number = $("#number").val();
  		if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
  		}
  		if(parseInt(number)>parseInt(pageCount)){
  			$("#number").val(pageCount);
  			$("#pageNo").val(pageCount);
  		}else{
  			$("#pageNo").val(number);
  		}
  	}
	//type=1,跳转到上一页	       
    if(type=="1"){
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }
	//type=2,跳转到下一页	       
    if(type=="2"){
   		$("#pageNo").val(parseInt($("#number").val())+1);
    }
     //type=3,跳转到最后一页,或第一页
    if(type=="3"){
    	$("#pageNo").val(pageNo);
    }
   	$("#form").submit();
} 

//清除表单数据
function clearSearch(){
	
	$("input[name='contractSignedDate']").val("");
	$("input[name='contractSignedEndDate']").val("");
	
	$("input[name='clearBeginDate']").val("");
	$("input[name='clearEndDate']").val("");
	
	$("#contractOwnerExecuteId").val("");
	$("#companyType").val("");
	
	$("#search1 td input:lt(3)").val("");
	$("#search2 td input:lt(3)").val("");
	$("select").each(function(){
		$(this).children("option:first").attr("selected",true);
	});
	//$("input[name=contractType]").val("");
	$("#pageNo").val(1);
	$("#contractPrice").val("");
}

</script> 
 
 
 
<script type="text/javascript">
//删除
function deleteData(id){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("form").submit();
			}
		});
	}
}

//页面截取字符串
$(function(){
	$("span[id=limitLength1]").each(function(){
		var html = $(this).html();
		if(html.length>25){
			$(this).html(html.substring(0,25)+"...");
		}
	});
	$("span[id=limitLength2]").each(function(){
		var html = $(this).html();
		if(html.length>30){
			$(this).html(html.substring(0,30)+"...");
		}
	});
	$("span[id=limitLength3]").each(function(){
		var html = $(this).html();
		if(html.length>15){
			$(this).html(html.substring(0,15)+"...");
		}
	});
	$("span[id=limitLength4]").each(function(){
		var html = $(this).html();
		if(html.length>10){
			//$(this).html(html.substring(0,20)+"...");		
		}
	});
	$("span[id=limitLength5_1]").each(function(){
		var text = $(this).text();
		if(text.length>20){
			$(this).html(text.substring(0,20)+"...");		
		}
	});
	$("span[id=limitLength5_2]").each(function(){
		var text = $(this).text();
		if(text.length>40){
			$(this).html(text.substring(0,40)+"...");		
		}
	});
})

//自动提示模糊搜索
$(function(){
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
			$("input[name=projectId]").val("");
			$("input[name=projectName]").val("");
		},
		select: function( event, ui ) {
			$("input[name=projectNo]").val(ui.item.label);
			$("input[name=projectName]").val(ui.item.projectName);
			$("input[name=projectId]").val(ui.item.value);
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
			$("input[name=projectId]").val("");
			$("input[name=projectNo]").val("");
		},
		select: function( event, ui ) {
			$("input[name=projectNo]").val(ui.item.label);
			$("input[name=projectName]").val(ui.item.projectName);
			$("input[name=projectId]").val(ui.item.value);
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


//点击查询，清空页码
function clearPageNo(){
	var type1 = $("select[name=contractType1]").val();
	var type2 = $("select[name=contractType2]").val();
	$("#pageNo").val("");
	//拼接合同分类
	if(type1!=null && type1!=""){
		var type =  type1+","+ type2;
		$("input[name=contractType]").val(type);
	}else{
		$("input[name=contractType]").val("");
	}
	
	<%--
	if($("#contractPrice").val()!=""){
		if(!$.isNumeric($("#contractPrice").val())){
			alert("合同价只能输入数字");
			$("#contractPrice").focus();
			return false;
		}
	}
	--%>
	
	return true;
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

//显示合同分类
$(function(){
	if($("input[name=contractType]").val()!=""){
		//搜索条件
		var type1 = $("input[name=contractType]").val().substring(0,1);
		var type2 = $("input[name=contractType]").val().substring(2,3);
		$("select[name=contractType2]").children("option").each(function(){
			if($(this).val()==type2){
				$(this).attr("selected",true);
			}
		});
		//快速搜索
		$("#"+type1+type2+"").addClass("selected");
		$("#00").removeClass("selected");
	}else{
		//快速搜索，没有选择,显示全部
		$("#00").addClass("selected");
	}
});


//点击项目公司查询
function showList(contractOwnerExecuteId){
	$("#contractOwnerExecuteId").val("");
	
	$("select[name=contractType1]").find("option:selected").attr("selected",false);
	$("select[name=contractType2]").find("option:selected").attr("selected",false);
	if(contractOwnerExecuteId=="company1"){
		$("input[name=companyType]").val(1);
	}else if(contractOwnerExecuteId=="company2"){
		$("input[name=companyType]").val(2);
	}else if(contractOwnerExecuteId=="company3"){
		$("input[name=companyType]").val(3);
	}else{
		$("#contractOwnerExecuteId").val(contractOwnerExecuteId);
	}
	clearPageNo();
	$("#form").submit();
}
//快速搜索合同分类
function queryContractType(contractType1,contractType2){
	if(contractType1==""){
		$("select[name=contractType1]").find("option:selected").attr("selected",false);
		$("select[name=contractType1]").val("");
	}else{
		$("select[name=contractType1]").find("option[value="+contractType1+"]").attr("selected",true);
	}
	showContractType($("select[name=contractType1]"));
	if(contractType2==""){
		$("select[name=contractType2]").find("option:selected").attr("selected",false);
		$("select[name=contractType2]").val("");
	}else{
		$("select[name=contractType2]").find("option[value="+contractType2+"]").attr("selected",true);	
	}
	clearPageNo();
	$("#form").submit();
}



//影藏绑定概算科目区域
function hiddenBindSubject(){
	$("#cover").hide();
}
//影藏批量修改区域
function hideBatchEdit(){
	$("#cover2").hide();
}


//自动提示模糊搜索,概算科名称
$(function(){
	var saveStatus;
	$("#subjectName" ).autocomplete({
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
							name : item.subjectName
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("#subjectId").val("");
		},
		select: function( event, ui ) {
			$("#subjectName").val(ui.item.label);
			$("#subjectId").val(ui.item.value); 
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
			$("#estimateSubjectId").val("");
		}
	});
});

function bindAction(){
	var contractIds = "";
	$("input[type=checkbox][name=contractCheck]:checked").each(function(){
		contractIds += $(this).val()+",";
	});
	if(contractIds=="") return;
	if($("#subjectId").val()==""){
		alert("该概算科目不存在！");
		$("#subjectName").focus();
		return ;
	}
	else contractIds=contractIds.substring(0,contractIds.length-1);
	$.ajax({
		type:'post',
		url : 'bindEstimateSubject.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			contractIds:contractIds,
			estimateSubjectId:$("#subjectId").val(),
			estimateSubjectName:$("#subjectName").val()
		},
		error:function(){
			alert("系统忙，请刷新后再试！");
		},
		success : function(){
			alert("绑定成功！");
			$("#cover").hide();
			$("#subjectId").val("");
			$("#subjectName").val("");
			$("#form").submit();
		}
	});
}
	
//导出Excel
function exportExcel(){
	$("#export").val("yes");
	$("#form").submit();
	$("#export").val("");
}


//点击项目公司、运营公司、维保中心查询
//type=0查询指定的公司,type=1查项目，type=2查运营，type=3查维保
//findType:notin
function queryByCompany(type,id,findType){
	$("#contractOwnerExecuteId").val("");
	$("input[name=contractType]").val("");
	$("select[name=contractType1]").find("option:selected").attr("selected",false);
	$("select[name=contractType1]").val("");
	$("select[name=contractType2]").find("option:selected").attr("selected",false);
	$("select[name=contractType2]").val("");
	if(type=='0'){
		$("#contractOwnerExecuteId").val(id);
		$("#companyType").val("");
	}else if(type=='1'){
		$("#companyType").val("company1");
	}else if(type=='2'){
		$("#companyType").val("company2");
	}else if(type=='3'){
		$("#companyType").val("company3");
	}
	$("#findType").val(findType);
	clearPageNo();
	$("#form").submit();
}

//显示批量修改
function showBatchEdit(){
	if($("input[type=checkbox][name=contractCheck]:checked").length<1){
		alert("请先勾选合同！");
		return false;	
	}
	$("#cover2").show();
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

//甲方(执行)，批量修改
$(function(){
	var saveStatus = false;
	$("#executeName" ).autocomplete({
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
			$("#executeId").val(ui.item.value);
			$("#executeName").val(ui.item.label);
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
				$("#executeId").val("");
			}
		}
	});
});

//重置批量修改
function clearBatchEdit(){
	$("#executeName").val("");
	$("#executeId").val("");
	$("#type1").val("");
	$("#type2").val("");
}
//保存批量修改
function saveBatchEdit(){
	var executeName = $("#executeName").val();
	var executeId = $("#executeId").val();
	if(executeName!=null && executeName!=""){	
		if(executeId==null || executeId==""){
			alert("该甲方(执行)不存在，请重新填写！");
			$("#executeName").focus();
			return false;		
		}
	}else{
		executeName ="";
		executeId ="";
	}
	var type1 = $("#type1").val();
	var type2 = $("#type2").val();
	var type = type1+","+type2;
	if(type.length==2){
		alert("请选择合同分类子类！");
		return false;
	}else if(type.length==1){
		type="";
	}
	if(executeId=="" && type==""){
		alert("请填写需要修改的数据！");
		return false;
	}
	var ids ="";
	$("input[type='checkbox']:checked").each(function(){
		ids += $(this).val()+",";
	});
	if(ids.length>0){
		ids = ids.substring(0,ids.length-1);			//要修改的id
	}
	$.ajax({
		url			: 	"batchEdit.action?random="+Math.random(),
		dataType	: 	"json",
		type		:	'post',
		data: {
			ids:ids,
			executeName:executeName,
			executeId:executeId,
			type:type
		},
		error:function(){
			alert("系统连接失败请稍后再试！");
		},
		success: function( data ) {
			alert("修改保存成功！");
			$("#cover2").hide();
			$("#form").submit();
		}
	});
}

function showBatchUpload(){
	window.open("showBatchUpload.action");
}
</script>
 
<script type="text/javascript">
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
       						"<td class='t_l' style='width:150px;'>"+
       							"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+attach.filename+"."+attach.fileextname+"</a>"+
       						"</td>"+
       						"<td class='t_r'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
       						"<td class='t_c'>"+attach.uploaddate+"</td>"+
       						"<td class='t_c'><a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"' id='"+attach.id+"'>下载</a></td>"+
       						"<td class='t_c'><a href='#' onclick='removeFile(this)' id='"+attach.id+"' name='attach_id'>删除</a></td>"+
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
</script>
 
<script type="text/javascript">

function batchClear(){
	if($("input:checked").length<=0){
		alert("请至少选择一项!");
		return ;
	}
	var ids = "";
	$("input:checked").each(function(){
		var tmp = $(this).val()+",";
		ids += tmp;
	});
	$("#checkDialog").text("确定要销缺吗？");
	$("#checkDialog").dialog({
		modal: true,
		buttons: {
			'确定': function(){
				$.ajax({
					url: '<%=basePath%>/contract/saveKpiClear.action',
					data: {
						contractIds: ids,
						roleType: $("#roleType").val(),
						option: 'agree',
						mode: 'batch'
					},
					type: 'post',
					success: function(){
					    location.href = '<%=basePath%>/contract/kpiClearList.action?'+$("form").serialize();
					}
				});
				$("#checkDialog").dialog('close');
			},
			'取消': function(){
				$(this).dialog('close');
			}
		}
	});
	
}

function batchReturn(){
	if($("input:checked").length<=0){
		alert("请至少选择一项!");
		return ;
	}
	var ids = "";
	$("input:checked").each(function(){
		var tmp = $(this).val()+",";
		ids += tmp;
	});
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:350,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '确定': function() {
		    	if(checkOpinionLength()){
		    		$("#checkDialog").text("确定要退回吗？");
    				$("#checkDialog").dialog({
	    				modal: true,
	    				buttons: {
    					'确定': function(){
    						$.ajax({
								url: '<%=basePath%>/contract/saveKpiClear.action',
								data: {
									contractIds: ids,
									opinion: $.trim($("#returnOpinion").val()),
									roleType: $("#roleType").val(),
									option: 'return',
									mode: 'batch'
								},
								type: 'post',
								success: function(){
									location.href = '<%=basePath%>/contract/kpiClearList.action?'+$("form").serialize();
								}
							});
    						$("#checkDialog").dialog('close');
							$("#returnDialog").dialog('close');
    					},
    					'取消': function(){
    						$(this).dialog('close');
    					}
    				}
    			});
					
		    	}
	        },
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#returnDialog").dialog(dialogOpts);
}

function viewExplain(id){
	$.ajax({
		url: '<%=basePath%>/contract/viewKpiClear.action',
		data: {'id':id},
		type: 'post',
		dataType: 'json',
		async: true,
		success: function(data){
			var tmpHtml = "";
			for(var i=0; i<data.length; i++){
				if(!data[i].opinion){
					continue;
				}
				tmpHtml += "<span>"+data[i].userName+"--"+data[i].date+"<br/>"
					+"<font>&nbsp;&nbsp;"+data[i].opinion+"</font></span>";
			}
			$("#opinionText").html(tmpHtml);
			
			var attachs = data[data.length-1].attachs;
			$("#opinionFileBody").empty();
			if(attachs){
				for(var i=0; i<attachs.length; i++){
					var attach = attachs[i];
					var filename = attach.filename;
					if(filename.length >= 10){
						filename = filename.substr(0,8)+"...";
					}
					var addHtml = "<tr style='border: solid 1px #D0D0D3;'>"+
						"<td class='t_l' style='width:150px;'>"+
							"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+filename+"</a>"+
						"</td>"+
						"<td class='t_c' style='width:50px;'>"+attach.fileextname+
						"<td class='t_r' style='width:50px;'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
						"<td class='t_c' style='width:150px;'>"+attach.uploaddate+"</td>"+
					  "</tr>";
					$("#opinionFileBody").append(addHtml);
				}
			}
		}
	});
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:450,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '同意销缺': function() {
	    		$("#checkDialog").text("确定要销缺吗？");
    			$("#checkDialog").dialog({
    				modal: true,
    				buttons: {
    					'确定': function(){
    						$.ajax({
								url: '<%=basePath%>/contract/saveKpiClear.action',
								data: {
									contractId: id,
									roleType: $("#roleType").val(),
									option: 'agree'
								},
								type: 'post',
								success: function(){
						            location.href = '<%=basePath%>/contract/kpiClearList.action?'+$("form").serialize();
								}
							});
    						$("#checkDialog").dialog('close');
							$("#opinionDialog").dialog('close');
    					},
    					'取消': function(){
    						$(this).dialog('close');
    					}
    				}
    			});
	            
	        },
	        '退回': function() {
				returnBack(id);
	        },
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#opinionDialog").dialog(dialogOpts);
}

function leaderView(id){
	$.ajax({
		url: '<%=basePath%>/contract/viewKpiClear.action',
		data: {'id':id},
		type: 'post',
		dataType: 'json',
		async: true,
		success: function(data){
			var tmpHtml = "";
			for(var i=0; i<data.length; i++){
				if(!data[i].opinion){
					continue;
				}
				tmpHtml += "<span>"+data[i].userName+"--"+data[i].date+"<br/>"
					+"<font>&nbsp;&nbsp;"+data[i].opinion+"</font></span>";
			}
			$("#leaderViewOpinion").html(tmpHtml);
			
			var attachs = data[data.length-1].attachs;
			$("#leaderFileBody").empty();
			if(attachs){
				for(var i=0; i<attachs.length; i++){
					var attach = attachs[i];
					var filename = attach.filename;
					if(filename.length >= 10){
						filename = filename.substr(0,8)+"...";
					}
					var addHtml = "<tr style='border: solid 1px #D0D0D3;'>"+
						"<td class='t_l' style='width:150px;'>"+
							"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+filename+"</a>"+
						"</td>"+
						"<td class='t_c' style='width:50px;'>"+attach.fileextname+
						"<td class='t_r' style='width:50px;'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
						"<td class='t_c' style='width:150px;'>"+attach.uploaddate+"</td>"+
					  "</tr>";
					$("#leaderFileBody").append(addHtml);
				}
			}
		}
	});
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:400,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '退回': function() {
				returnBack(id);
	        },
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#leaderViewDialog").dialog(dialogOpts);
}

function checkOpinionLength (){
	if($.trim($("#returnOpinion").val()).length > 100){
		alert("填写内容不得大于100字!");
		return ;
	}
	return true;
}

function returnBack(id){
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:350,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '确定': function() {
	    		if(checkOpinionLength ()){
	    			$("#checkDialog").text("确定要退回吗？");
	    			$("#checkDialog").dialog({
	    				modal: true,
	    				buttons: {
	    					'确定': function(){
	    						$.ajax({
									url: '<%=basePath%>/contract/saveKpiClear.action',
									data: {
										contractId: id,
										opinion: $.trim($("#returnOpinion").val()),
										roleType: $("#roleType").val(),
										option: 'return'
									},
									type: 'post',
									success: function(){
										location.href = '<%=basePath%>/contract/kpiClearList.action?'+$("form").serialize();
									}
								});
	    						$("#checkDialog").dialog('close');
								$("#returnDialog").dialog('close');
								$("#opinionDialog").dialog('close');
								$("#leaderViewDialog").dialog('close');
	    					},
	    					'取消': function(){
	    						$(this).dialog('close');
	    					}
	    				}
	    			});
					
	    		}
	        },
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#returnDialog").dialog(dialogOpts);
}

function submitOpinion(id){
	
	$.ajax({
		url: '<%=basePath%>/contract/viewKpiClear.action',
		data: {'id':id},
		type: 'post',
		dataType: 'json',
		async: true,
		success: function(data){
			var tmpHtml = "";
			var latest = "";
			var maxIndex ;
			for(var i=0; i<data.length; i++){
				if(data[i].date>latest){
					latest = data[i].date;
					maxIndex = i;
				}
			}
			if(!data[maxIndex].opinion){
				data[maxIndex].opinion = "无" ;
			}
			tmpHtml += "<span>"+data[maxIndex].userName+"--"+data[maxIndex].date+"<br/>"
					+"<font>&nbsp;&nbsp;"+data[maxIndex].opinion+"</font></span>";
			$("#companyOpinionText").html(tmpHtml);
		}
	});
	
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:450,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '提交': function() {
	    		if (checkSubmitOpinion()){
	    			$("#checkDialog").text("确定要提交吗？");
	    			$("#checkDialog").dialog({
	    				modal: true,
	    				buttons: {
	    					'确定': function(){
	    						$.ajax({
									url: '<%=basePath%>/contract/saveKpiClear.action',
									data: {
										contractId: id,
										opinion: $.trim($("#submitOpinionText").val()),
										roleType: $("#roleType").val(),
										attachIds: $("#attachIds").val(),
										option : 'submit'
									},
									type: 'post',
									success: function(){
										location.href = '<%=basePath%>/contract/kpiClearList.action?'+$("form").serialize();
									}
								});
	    						$("#checkDialog").dialog('close');
								$("#submitOpinionDialog").dialog('close');
	    					},
	    					'取消': function(){
	    						$(this).dialog('close');
	    					}
	    				}
	    			});
				}
	        },
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#submitOpinionDialog").dialog(dialogOpts);
}

function checkSubmitOpinion(){
	var content = $.trim($("#submitOpinionText").val());
	if(content.length==0){
		alert("请输入说明内容!");
		return ;
	}
	if(content.length > 100){
		alert("填写内容不得大于100字!");
		return ;
	}
	var attachIds ="";
	$("#fileBody").find("a[name='attach_id']").each(function(){
		attachIds += $(this).attr("id")+",";
	});
	
	if(attachIds!=null && attachIds!="" && attachIds.length>1){
		attachIds = attachIds.substring(0,attachIds.length-1);
	}
	$("#attachIds").val(attachIds);
	return true;
}
</script>
 

<style type="text/css">
#contractTypeList ul li a {
	display:inline;
	margin: 5px;
}
#cover,#cover2
{
    position:absolute;
    right:0px;
    bottom:0px;
    width:100%;
    height:100%;
    display:none;
    z-index:99;
}
#tb_window
{
    width:450px;
    height:220px;
    border:2px #6699CC solid;
    z-index:2;
    background:#FFFFFF;
    margin:200px auto;
}
</style>
 
</head>

<body>

	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix nwarp">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<!-- <li><a href="#">合同封面</a></li> -->
				<li class="fin">KPI销缺列表</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#" onclick="window.print();">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl();">查询</a></li>
			</ul>
		</div>
		</div>
		
		<div class="pt45">
		
	        <div class="filter" id="quickSearch">
	        	<div class="query p8" id="searchArea" style="display: none;">
					<s:form action="kpiClearList" name="contract" namespace="/contract" method="post" id="form">
					
						<input type="hidden" name="contractOwnerExecuteId" id="contractOwnerExecuteId" value="<s:property value='contract.contractOwnerExecuteId'/>"/>
						<input type="hidden" name="showOrHide" value="<s:property value="#request.showOrHide"/>"/>
						<input type="hidden" name="controlYear" id="controlYear" value="<s:property value='#request.controlYear'/>"/>
						<input type="hidden" name="contractType" id="contractType" value="<s:property value='#request.contractType'/>"/>
						<input type="hidden" name="companyId" id="companyId" value="<s:property value='#request.companyId'/>"/>
						<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.pageNo'/>"/>
						<input type="hidden" name="condition" id="condition" value="<s:property value='#request.condition'/>"/>
						<input type="hidden" name="inviteBidType" id="inviteBidType" value="<s:property value='#request.inviteBidType'/>"/>
						
						<input type="hidden" name="export" id="export" />
	
						
						<input type="hidden" name="type" value="
						<s:if test="#request.type==null || #request.type==''">1</s:if>
						<s:else><s:property value='#request.type'/></s:else>"/>
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr id="search1">
								<td class="t_r" style="white-space:nowrap">合同编号</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractNo"  class="input_xlarge" value="<s:property value='contract.contractNo'/>" />
								</td>
								<td class="t_r" style="white-space:nowrap">自有编号</td>
								<td style="white-space:nowrap">
									<input type="text" name="selfNo"  class="input_xlarge" value="<s:property value='contract.selfNo'/>" />
								</td>
								<s:if test="#request.type!=23">
									<td class="t_r" style="white-space:nowrap">甲方(执行)</td>
									<td style="white-space:nowrap">
										<input type="text" name="contractOwnerExecuteName"  class="input_large" value="<s:property value='contract.contractOwnerExecuteName'/>" >
									</td>
								</s:if>
							</tr>
							<tr id="search2">
								<td class="t_r" style="white-space:nowrap">合同名称</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractName"  class="input_xlarge" value="<s:property value='#request.contractName'/>" >
								</td>
								<td class="t_r" style="white-space:nowrap">合同状态</td>
								<td>
									<select name="contractStatus" class="input_large">
                                        <option value="" selected="selected">---请选择---</option>
                                        <option value="0">已备案</option>
                                        <option value="1">实施中</option>
                                        <option value="2">已竣工</option>
                                        <option value="3">已销号</option>
                                        <option value="4">过程中</option>
                                        <option value="5">已取消</option>
                                        <option value="6">已终止</option>
                                        </select>
								</td>
								<td class="t_r" style="white-space:nowrap">合同签约时间</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractSignedDate"  style="width: 118px;" value="<s:property value='contract.contractSignedDate'/>" readonly="readonly">~
									<input type="text" name="contractSignedEndDate"  style="width: 118px;" value="<s:property value='#request.contractSignedEndDate'/>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<s:if test="#request.type!=23">
								<td class="t_r" style="white-space:nowrap">合同分组</td>
								<td style="white-space:nowrap">
									<select name="contractGrouping" class="input_large">
                                        <option value="-1" selected="selected">---请选择---</option>
                                        <option value="1" <s:if test="contract.contractGrouping==1">selected</s:if>>成本内</option>
                                        <option value="2" <s:if test="contract.contractGrouping==2">selected</s:if>>成本外</option>
                                        <option value="3" <s:if test="contract.contractGrouping==3">selected</s:if>>无</option>
                                    </select>
								</td>
								</s:if>
								<s:if test="#request.type==0">
									<td class="t_r" style="white-space:nowrap">销缺时间</td>
									<td style="white-space:nowrap">
										<input type="text" name="clearBeginDate"  style="width: 118px;" value="<s:property value='#request.clearBeginDate'/>" readonly="readonly">~
										<input type="text" name="clearEndDate"  style="width: 118px;" value="<s:property value='#request.clearEndDate'/>" readonly="readonly">
									</td>
								</s:if>
								<s:if test="#request.type!=0">
									<td class="t_r" style="white-space:nowrap">KPI消缺状态</td>
									<td style="white-space:nowrap">
										<select name="kpiStatus" class="input_large">
	                                        <option value="-1" selected="selected">---请选择---</option>
	                                        <option value="0" <s:if test="#request.kpiStatus==0">selected</s:if>>已销缺</option>
	                                        <option value="3" <s:if test="#request.kpiStatus==3">selected</s:if>>已退回</option>
	                                    </select>
									</td>
								</s:if>
							</tr>
							<!-- 
							<tr>
								<td class="t_r" style="white-space:nowrap">合同价(万元)</td>
								<td style="white-space:nowrap">
									<input type="text" name="contractPrice" id="contractPrice" style="width: 118px;" value="<s:property value='contract.contractPrice'/>" >
								</td>
							</tr>
							 -->
							<tr>
								<td colspan="6" class="t_c">
									<input type="submit" value="检 索" onclick="return clearPageNo();"/>&nbsp;&nbsp; 
									<input type="button" value="重 置" onclick="clearSearch();"/>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
	        	<s:if test="#request.type==1 && #request.kpiStatus!=0 && #request.kpiStatus!=3">
	        	<div class="fn clearfix">
					<span style="float: right;">
						<input type="button" value="退回" onclick="batchReturn();" style="float:right;"/>
						<input type="button" value="销缺" onclick="batchClear();" style="float:right;margin-right:10px;"/>
					</span>
				</div>
				</s:if>
				
	        </div>
	        
	        
	        
	        <!--Table-->
			<div class="mb10">
				<input id="roleType" type="hidden" value="<s:property value="#request.type"/>"/>
				<input type="hidden" name="attachIds" id="attachIds">
				<table width="100%" class="table_1">
					<thead>
						<s:if test="#request.type==0"><th colspan="6">&nbsp;&nbsp;KPI销缺列表</th></s:if>
						<s:elseif test="#request.type==1"><th colspan="8">&nbsp;&nbsp;KPI销缺列表</th></s:elseif>
						<s:elseif test="#request.type==23"><th colspan="5">&nbsp;&nbsp;KPI销缺列表</th></s:elseif>
						<s:else><th colspan="5">&nbsp;&nbsp;KPI销缺列表</th></s:else>
					</thead>
					<tbody>
						<tr class="tit">
							<s:if test="#request.type==1">
								<td width="3%;" style="white-space:nowrap"></td>
							</s:if>
							<td width="5%;" style="white-space:nowrap">序号</td>
							<td width="15%;" class="t_r" style="white-space:nowrap">合同编号</td>
							<td width="20%;" class="t_r" style="white-space:nowrap">合同名称</td>
							<s:if test="#request.type==0 || #request.type==1">
								<td width="13%;" class="t_r" style="white-space:nowrap">甲方(执行)</td>
								<td width="5%;" class="t_r" style="white-space:nowrap" >合同分组</td>
								<s:if test="#request.type!=0">
									<td width="10%;" class="t_r" style="white-space:nowrap">解释说明</td>
								</s:if>
							</s:if>
							<s:else>
								<td width="10%;" class="t_r" style="white-space:nowrap">集团合约部意见</td>
							</s:else>
							<td width="12%;" class="t_r" style="white-space:nowrap">操作</td>
						</tr>
						<s:iterator value="#request.result" id="contract" status="st">
							<tr>
								<s:if test="#request.type==1">
									<td class="t_c">
										<input type="checkbox" value="<s:property value="#contract.id"/>"/>
										<input type="hidden" value="<s:property value="#contract.id"/>"/>
									</td>
								</s:if>
								<td class="t_c" style="white-space: nowrap;">
									<s:property value="#request.begin+#st.index+1"/>
								</td>
								<td class="t_l" style="white-space: nowrap;" title="<s:property value="#contract.contractNo"/>">
									<span id="limitLength1"><s:property value="#contract.contractNo"/></span>
								</td>
								<td class="t_l" title="<s:property value="#contract.contractName"/>" style="white-space: nowrap;">
									<span id="limitLength2"><s:property value="#contract.contractName"/></span>
								</td>
								<s:if test="#request.type==0 || #request.type==1">
									<td class="t_l" title="<s:property value="#contract.executeName"/>" style="white-space: nowrap;">
										<span id="limitLength3"><s:property value="#contract.executeName"/></span>
									</td>
									<td class="t_l" title="<s:property value="#contract.contractGroup"/>" style="white-space: nowrap;">
										<span id="limitLength4">
											<s:if test="#contract.contractGroup==1">
												成本内
											</s:if>
											<s:elseif test="#contract.contractGroup==2">
												成本外
											</s:elseif>
											<s:elseif test="#contract.contractGroup==3 || #contract.contractGroup==null">
												无
											</s:elseif>
										</span>
									</td>
								</s:if>
								<s:if test="#request.type==23">
								<td class="t_l" title="" style="white-space: nowrap;">
									<span id="limitLength5_2"><s:if test="#contract.opinion==null || #contract.opinion==''">无</s:if><s:else><s:property value="#contract.opinion"/></s:else></span>
								</td>
								</s:if>
								<s:else>
									<s:if test="#request.type!=0">
									<td class="t_l" title="" style="white-space: nowrap;">
										<span id="limitLength5_1"><s:if test="#contract.opinion==null || #contract.opinion==''">无</s:if><s:else><s:property value="#contract.opinion"/></s:else></span>
									</td>
									</s:if>
								</s:else>
								<td class="t_c" style="white-space: nowrap;">
									<s:if test="#request.type==0">
										<a href="javascript:void(0);" onclick="leaderView('<s:property value='#contract.id'/>');" style="display:inline;" >查看说明</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</s:if>
									<s:if test="#request.type==1">
										<s:if test="#request.kpiStatus!=0 && #request.kpiStatus!=3">
											<a href="javascript:void(0);" onclick="viewExplain('<s:property value='#contract.id'/>');" style="display:inline;" >查看说明</a>&nbsp;&nbsp;&nbsp;&nbsp;
										</s:if>
									</s:if>
									<s:if test="#request.type==23">
										<s:if test="#request.kpiStatus!=0">
											<a href="javascript:void(0);" onclick="submitOpinion('<s:property value='#contract.id'/>');" style="display:inline;" >提交说明</a>&nbsp;&nbsp;&nbsp;&nbsp;
										</s:if>
									</s:if>
									<a href="showView.action?id=<s:property value="#contract.id"/>"  target="_blank" onclick="" style="display:inline;">查看合同信息</a>&nbsp;&nbsp;
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tr class="tfoot">
					      <s:if test="#request.type==0"><td colspan="6"></s:if>
						<s:elseif test="#request.type==1"><td colspan="8"></s:elseif>
						<s:elseif test="#request.type==23"><td colspan="5"></s:elseif>
						  <s:else><td colspan="5"></s:else>
					      <div class="clearfix">
					      <s:if test="#request.totalSize==0"><span>无相关数据</span></s:if>
					      <s:else>
					      	<span class="fl">
						      	<s:property value="#request.totalSize"/>条记录，当前显示<s:if test="#request.totalSize!=0"><s:property value="#request.begin+1"/></s:if><s:else>0</s:else> -
						      	<s:if test="#request.totalSize!=0">
						      		<s:if test="#request.pageNo==#request.totalPageCount">
								      	<s:property value="#request.totalSize"/>条
								    </s:if>
								    <s:else>
								    	<s:property value="#request.begin+#request.pSize"/>条
									</s:else>
						      	</s:if>
						      	<s:else>0条</s:else>
						    </span>
					        <ul class="fr clearfix pager">
					          <li>Pages:<s:property value="#request.pageNo"/>/<s:property value="#request.totalPageCount"/>
					          	<input type="hidden" value="<s:property value='#request.totalPageCount'/>" id="totalPageCount">
					            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.pageNo'/>" />
					            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
				           	  </li>
				           	  
				               <s:if test="#request.pageNo==#request.totalPageCount">
				               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
				               </s:if>
				               <s:else>
				                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.totalPageCount'/>,3)">&gt;&gt;</a></li>
				               </s:else>
				                
				              <li>
				              	<s:if test="#request.pageNo==#request.totalPageCount">	
				              		<a href="javascript:void(0)">下一页</a>
				              	</s:if>
				              	<s:else>
				              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageNo'/>,2)">下一页</a>
				              	</s:else>
				              </li>
				              
				              <li>
				              	<s:if test="#request.pageNo==1">
				              		<a href="javascript:void(0)">上一页</a>
				              	</s:if>
				              	<s:else>
				              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.pageNo'/>,1)">上一页</a>
				              	</s:else>
				              </li>
				              
				              <s:if test="#request.pageNo==1">
				              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
				              </s:if>
				              <s:else>
				              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
				              </s:else>
				         </ul>
				       </s:else>
				       </div></td>
				     </tr>
				</table>
			</div>
			<!--Table End-->
	        
	        
			
	        <div id="opinionDialog" style="display: none;">
	        	<div>
	        		双方交流意见：
	        		<div id="opinionText" style="width:460px;height:200px;border:2px solid #D0D0D3; overflow: auto;">
	        		</div>
	        	</div>
        		<br/>
	        	<div>
	        		附件列表：
					<table id="opinionFileTable" style="border: solid 2px;border: #D0D0D3;">
						<thead>
							<tr style="border: solid 2px;border: #D0D0D3;">
								<td width="150px;" class="t_c">附件名称</td>
								<td width="100px;" class="t_c">附件类型</td>
								<td width="60px;" class="t_c">大小(KB)</td>
								<td width="100px;" class="t_c">上传时间</td>
							</tr>
						</thead>
						<tbody style="border: solid 2px;border-color: #D0D0D3;" id="opinionFileBody">
						</tbody>
					</table>
				</div>
			</div>
	        
	        <!--  -->
	        <div id="submitOpinionDialog" style="display: none">
	        	<div>
	        		集团合约部意见：
	        		<div id="companyOpinionText" style="width:460px;height:60px;border:2px solid #D0D0D3; overflow: auto;">
	        		</div>
	        	</div>
        		<br/>
	        	<div>
	        		下属单位说明：
	        		<textarea id="submitOpinionText" style="width:460px;height:100px;">
	        		</textarea>
	        	</div>
	        	<br/>
	        	<div>
	        		<input type="file" name="uploadify" id="uploadify" />
					<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
					<input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">
					<br/><br/>
					<table style="display: none;border: solid;border-color: #D0D0D3;" id="fileTable">
						<tbody style="width: 550px;border: solid;border-color: #D0D0D3;" id="fileBody">
							<tr style="border: solid;border: #D0D0D3;">
								<td width="150px;" class="t_c">文件名</td>
								<td width="60px;" class="t_c">大小(KB)</td>
								<td width="150px;" class="t_c">上传时间</td>
								<td width="100px;" class="t_c" colspan="2">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
	        
	        
	        <div id="returnDialog" style="display: none;">
	        	<div>
	        		意见说明：<br/><br/>
	        		<textarea id="returnOpinion" style="width:470px;height:200px;">
	        		</textarea>
	        	</div>
	        </div>
	        
	        <div id="leaderViewDialog" style="display: none">
	        	<div>
	        		双方意见：
	        		<div id="leaderViewOpinion" style="width:460px;height:200px;border:2px solid #D0D0D3;overflow:auto">
	        		</div>
	        	</div>
	        	<br/>
	        	<div>
	        		附件列表：
					<table id="leaderFileTable">
						<thead>
							<tr style="border: solid 2px;border: #D0D0D3;">
								<td width="150px;" class="t_c">附件名称</td>
								<td width="100px;" class="t_c">附件类型</td>
								<td width="60px;" class="t_c">大小(KB)</td>
								<td width="100px;" class="t_c">上传时间</td>
							</tr>
						</thead>
						<tbody style="border: solid 2px;border-color: #D0D0D3;" id="leaderFileBody">
						</tbody>
					</table>
				</div>
			</div>
	        
	        <div id="checkDialog"  style="display: none">
	        	
	        </div>
        </div>
		
		
	</div>
</body>
</html>
