<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>文件上传</title>

<link rel="stylesheet" href="../css/uploadify.css" />
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.uploadify-3.1.js"></script>

<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<script src="../js/html5.js"></script>
<script src="../js/jquery.formalize.js"></script>
<%String basePath = request.getContextPath(); %>

<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");			
});

//关闭窗口
function shut(){
  window.opener=null;
  window.open("","_self");
  window.close();
}
$(function(){
	$("#uploadify").uploadify({
       'auto' : false,
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '../js/uploadify.swf', 
       'uploader' : '<%=basePath%>/fundPlan/batchUpload.action',
       'fileTypeDesc' : '格式:xls',		//描述
       'fileTypeExts' : '*.xls',			//文件类型
       'fileSizeLimit' : '30000KB',			//文件大小
       'buttonText' : '选择文件',			//按钮名称
       'fileObjName'	:'uploadify',
       'successTimeout' : 300,
       'requeueErrors' : false,
       'removeTimeout' : 1,
       'removeCompleted' : true,
       'onUploadStart': function(file){
     		$("#showTR").show();
      		$("#showId").html($('#fileUpload_processing').html());
      },       
       'onSelectError' : function(file,errorCode,errorMsg) {
       		if(errorCode==-110){
       			this.queueData.errorMsg = "文件太大，无法上传！";
       		}
        }, 
       'onUploadSuccess' : function(file, data, response){
       		$("#showId").html(data);
    	}
	});
});

function downloadTemplete(){
	window.location.href='<%=basePath%>/fundPlan/downloadFile.action?fileName=FundPlanImportTemplate.xls';
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
   	<li class="fin">资金计划上传</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>

</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">

<table width="100%" class="table_1">
	<thead>
		<th colspan="5" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" width="200px;">
				上传文件<br/>
				<a href='javascript:void(0);' onclick="downloadTemplete();">下载模板</a>
			</td>
			<td colspan="2">
				<input type="file" name="uploadify" id="uploadify" />
				<input type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
				<!-- <input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');"> -->
			</td>
		</tr>
		 
		<tr id="showTR" style="display: none;">
			<td class="t_r">下载反馈文件</td>
			<td id="showId"></td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
</table>

</div>
<!--Table End-->
<div id="fileUpload_processing" style="width:100%;align:center;line-height:20px;display:none;" class="">
     <p style="width:auto;display:inline;"><img src="<%=basePath%>/images/loading.gif" style="display:inline;"/>
     <b style="color:green;display:inline;">&nbsp;正在处理</b></p>
</div>
</body>
</html>
