<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>

<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>变更事项查看</title>
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
	$("input[name=passTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
});

$(function() {
	$("select[name=changeItemType1]>option[value=<s:property value="contractChangeItem.changeItemType1"/>]").prop("selected",true);
	showChangeItemType($("select[name=changeItemType1]"));
	$("select[name=changeItemType2]>option[value=<s:property value="contractChangeItem.changeItemType2"/>]").prop("selected",true);


});

$(function(){
	$("select").each(function(){
		$(this).hide();
			if ($(this).val()!="")
			{
				var addHtml = $(this).find("option:selected").text();
	 		  	$(this).parent().append(" "+addHtml+" ");
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


//检查表单数据合法性
function checkForm(){
	

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


$(function(){
	if($("#hide_contractType2").val()!=""){
		$("select[name=contractType2]").children("option").each(function(){
			if($(this).val()==$("#hide_contractType2").val()){
				$(this).attr("selected",true);
			}
		});	
	}
});

function showChangeItemType(paramObj){

	var addHtml;
	if($(paramObj).val()=="1"){
// 	地基加固
		addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>主体围护</option>"+
					"<option value='2'>支撑及开挖方式</option>"+
					"<option value='3'>地基加固</option>"+
					"<option value='4'>主体结构</option>"+
					"<option value='5'>附属零星</option>"+
					"<option value='6'>封堵墙</option>"+
					"<option value='7'>地下障碍物</option>";
	}else if($(paramObj).val()=="2"){
// 	高架车站
		addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>主体围护</option>"+
					"<option value='2'>支撑及开挖方式</option>"+
					"<option value='3'>地基加固</option>"+
					"<option value='4'>主体结构</option>"+
					"<option value='5'>附属零星</option>"+
					"<option value='6'>地下障碍物</option>"
					;
	}else if($(paramObj).val()=="3"){
// 	地下区间
		addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>主体围护</option>"+
					"<option value='2'>支撑及开挖方式</option>"+
					"<option value='3'>地基加固</option>"+
					"<option value='4'>主体结构</option>"+
					"<option value='5'>附属零星</option>"+
					"<option value='6'>封堵墙</option>"+
					"<option value='7'>地下障碍物</option>";
	}else if($(paramObj).val()=="4"){
// 	高架区间
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>桩基</option>"+
					"<option value='2'>承台盖梁</option>"+
					"<option value='3'>支座</option>"+
					"<option value='4'>箱梁</option>"+
					"<option value='5'>地下障碍物</option>"
					;
	}else if($(paramObj).val()=="5"){
//	敞开段或明挖段	
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>主体围护</option>"+
					"<option value='2'>支撑及开挖方式</option>"+
					"<option value='3'>地基加固</option>"+
					"<option value='4'>主体结构</option>"+
					"<option value='5'>地下障碍物</option>"
			;
	}else if($(paramObj).val()=="6"){
// 	铺轨	
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>道床</option>"+
					"<option value='2'>扣件</option>"+
					"<option value='3'>轨枕</option>"+
					"<option value='4'>道岔</option>"+
					"<option value='5'>车挡</option>"+
					"<option value='6'>检查坑</option>"+
					"<option value='7'>铺轨基地</option>";
	}else if($(paramObj).val()=="7"){
// 	装修		
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>灯具变更</option>"+
					"<option value='2'>墙面变更</option>"+
					"<option value='3'>地面变更</option>"+
					"<option value='4'>顶面变更</option>"+
					"<option value='5'>附属设施</option>"+
					"<option value='6'>新增装修内容</option>"
					;
	}else if($(paramObj).val()=="8"){
// 	停车场市政	
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>站场路基</option>"+
					"<option value='2'>站场轨道</option>"+
					"<option value='3'>站场道路</option>"+
					"<option value='4'>站场下水道</option>"+
					"<option value='5'>绿化</option>"
				;
	}else if($(paramObj).val()=="9"){
// 	停车场房建
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>土建</option>"+
					"<option value='2'>装修</option>"+
					"<option value='3'>安装</option>"+
					"<option value='4'>附属零星</option>"+
					"<option value='5'>设备</option>";
	}else if($(paramObj).val()=="a"){
	//区间变电所
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>主体围护</option>"+
					"<option value='2'>支撑及开挖方式</option>"+
					"<option value='3'>地基加固</option>"+
					"<option value='4'>主体结构</option>"+
					"<option value='5'>附属零星</option>"+
					"<option value='6'>封堵墙</option>"+
					"<option value='7'>地下障碍物</option>";
	}else {
// 	无线系统	
			addHtml = "<option value=''>---请选择---</option>"+
					"<option value='1'>系统改造</option>"+
					"<option value='2'>新增设备 </option>"+
					"<option value='3'>规格型号调整</option>"+
					"<option value='4'>数量调整</option>";
	}
	$(paramObj).next().html(addHtml);
	
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
			<li class="fin">变更事项查看</li>
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
<s:form action="saveEdit" name="contractChangeItem" namespace="/contractChangeItem" method="post">



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
			    <td class="t_r lableTd" width="20%;"> 原合同名称 </td>
				<td  width="30%;">
					<s:property value='#request.contract.contractName'/>
				
				</td>
				<td class="t_r lableTd" width="19%;">原合同编号</td>
				<td  width="31%;">
					<s:property value='#request.contract.contractNo'/>
				</td>

			</tr>

			<tr>
				<td class="t_r lableTd">合同分类</td>

				<td>
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">
						<select disabled="disabled" name="contractType1" class="input_large" onchange="showContractType(this);">
							<option value="">---请选择---</option>
							<option value="1">建设类</option>
							<option value="2">运维类</option>
							<option value="3">其他类</option>
						</select>
						<select disabled="disabled" name="contractType2" class="input_large">
							<option value="">---请选择---</option>
						</select>
					</s:if>
					<s:else>
						<input type="hidden" value="<s:property value='#request.contract.contractType.substring(2,3)'/>" id="hide_contractType2">
						<input type="hidden" value="<s:property value='#request.contract.contractType.substring(0,1)'/>" id="hide_contractType1">
						<s:if test="#request.contract.contractType.substring(0,1)==1">
							<select disabled="disabled" name="contractType1" class="input_large" onchange="showContractType(this);">
								<option value="">---请选择---</option>
								<option value="1" selected="selected">建设类</option>
								<option value="2">运维类</option>
								<option value="3">其他类</option>
							</select>
							<select disabled="disabled" name="contractType2" class="input_large">
								<option value='1'>工程类</option>
								<option value='2'>勘察类</option>
								<option value='3'>设计类</option>
								<option value='4'>前期类</option>
								<option value='5'>其他类</option>
								<option value='6'>监理类</option>
								<option value='7'>科研类</option>
								<option value='8'>咨询类</option>
							</select>						
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2">

								<select disabled="disabled" name="contractType1" class="input_large" onchange="showContractType(this);">
									<option value="">---请选择---</option>
									<option value="1">建设类</option>
									<option value="2" selected="selected">运维类</option>
									<option value="3">其他类</option>
								</select>
							<select disabled="disabled" name="contractType2" class="input_large">
								<option value='1'>服务</option>
								<option value='2'>工程</option>
								<option value='3'>货物</option>
							</select>
						</s:elseif>
						<s:elseif test="#request.contract.contractType.substring(0,1)==3">
							<select disabled="disabled" name="contractType1" class="input_large" onchange="showContractType(this);">
								<option value="">---请选择---</option>
								<option value="1">建设类</option>
								<option value="2">运维类</option>
								<option value="3" selected="selected">其他类</option>
							</select>
							<select disabled="disabled" name="contractType2" class="input_large">
								<option value='1'>其他类</option>
								
							</select>
						</s:elseif>
						<s:else>
							<select disabled="disabled" name="contractType1" class="input_large" onchange="showContractType(this);">
								<option value="">---请选择---</option>
								<option value="1">建设类</option>
								<option value="2">运维类</option>
								<option value="3">其他类</option>
							</select>
							<select disabled="disabled" name="contractType2" class="input_large">
								<option value="">---请选择---</option>
							</select>
						</s:else>
					</s:else>
				</td>
			</tr>
			

			
			<tr>
				<td class="t_r lableTd" width="19%;">变更提出方</td>
					<td>
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">
						<select name="changePutForword" class="input_large" >
							<option value="">---请选择---</option>

						</select>

					</s:if>
					<s:else>

						<s:if test="#request.contract.contractType.substring(0,1)==1">
							<select hidden="hidden" name="changePutForword" class="input_large" >
									<option value="">---请选择---</option>			
									<s:if test="#request.contractChangeItem.changePutForword==1"><option value="1" selected="selected">项目公司</option></s:if>
									<s:else > <option value="1" >项目公司</option></s:else>
									<s:if test="#request.contractChangeItem.changePutForword==2"><option value="2" selected="selected">设计单位</option></s:if>
									<s:else > <option value="2" >设计单位</option></s:else>
									<s:if test="#request.contractChangeItem.changePutForword==3"><option value="3" selected="selected">业主</option></s:if>
									<s:else > <option value="3" >业主</option></s:else>									
							</select>
				
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">
							<select hidden="hidden" name="contractType1" class="input_large" onchange="showContractType(this);">
									<option value="">---请选择---</option>
								
									<s:if test="#request.contractChangeItem.changePutForword==1"><option value="1" selected="selected">我方</option></s:if>
									<s:else > <option value="1" >我方</option></s:else>
									<s:if test="#request.contractChangeItem.changePutForword==2"><option value="2" selected="selected">对方</option></s:if>
									<s:else > <option value="2" >对方</option></s:else>
									<s:if test="#request.contractChangeItem.changePutForword==3"><option value="3" selected="selected">双方</option></s:if>
									<s:else > <option value="3" >双方</option></s:else>	
							</select>

						</s:elseif>

						<s:else>
							<select name="changePutForword" class="input_large" onchange="showContractType(this);">
									<option value="">---请选择---</option>

							</select>
						</s:else>
					</s:else>
											
					</td>				

				<td class="t_r lableTd" width="20%;"> 变更事项流水号 </td>
				<td class="must" width="30%;">
					<input type="text"  name="changeSerialNo" class="input_xxlarge"  maxlength="100" value="<s:property value='contractChangeItem.changeSerialNo'/>" />&nbsp;&nbsp;*
				</td>
				
				

			</tr>
			
			<tr>
				<td class="t_r lableTd">变更事项</td>

				<td>
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">

					</s:if>
					<s:else>
						<s:if test="#request.contract.contractType.substring(0,1)==1">
					
							<select name="changeItemType1" class="input_large" onchange="showChangeItemType(this);" >
									<option value="">---请选择---</option>			
								    <option value="1" >地基加固</option>
 									<option value="2" >高架车站</option>	
									<option value="3" >地下区间</option>
									<option value="4" >高架区间</option>					
									<option value="5" >敞开段或明挖段</option>
								    <option value="6" >铺轨</option>	
									<option value="7" >装修</option>
								    <option value="8" >停车场市政</option>
								    <option value="9" >停车场房建</option>
									<option value="a"  >区间变电所</option>
									 <option value="b" >无线系统</option>
									 <option value="c" >通信系统</option>
									 <option value="d" >信号系统</option>
									 <option value="e" >信号安装</option>
									 <option value="f" >供电系统设备</option>
									 <option value="g" >牵降变安装</option>	
									<option value="h" >接触网安装</option>
									 <option value="i" >通风、空调设备</option>
									<option value="j" >风水电安装</option>	
									 <option value="k" >屏蔽门系统</option>
									 <option value="l" >气体灭火系统设备</option>
									<option value="m" >防灾报警系统</option>	
									<option value="n" >综合监控系统</option>
									<option value="o" >气体灭火系统</option>
									 <option value="p" >自动售检票采购安装</option>
									 <option value="q" >自动扶梯采购安装</option>	
									 <option value="r" >垂直电梯采购安装</option>
									<option value="s" >人防防护门、防淹们制作安装</option>
																		
							</select>
							<select name="changeItemType2" class="input_large" >

							</select>						
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 || #request.contract.contractType.substring(0,1)==3">

								<select name="changeItemType1" class="input_large">
									<option value="">---请选择---</option>			
									<s:if test="#request.contractChangeItem.changeItemType1==1"><option value="1" selected="selected">合同金额</option></s:if>
									<s:else > <option value="1" >合同金额</option></s:else>
									<s:if test="#request.contractChangeItem.changeItemType1==2"><option value="2" selected="selected">履行期限</option></s:if>
									<s:else > <option value="2" >履行期限</option></s:else>	
									<s:if test="#request.contractChangeItem.changeItemType1==3"><option value="3" selected="selected">终止</option></s:if>
									<s:else > <option value="3" >终止</option></s:else>
									<s:if test="#request.contractChangeItem.changeItemType1==4"><option value="4" selected="selected">其它</option></s:if>
									<s:else > <option value="4" >其它</option></s:else>
								</select>

						</s:elseif>
						<s:else>

						</s:else>
					</s:else>
				</td>			
			</tr>
			
			<tr>
				<td class="t_r lableTd">变更事项描述</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="changeItemDescription" style="width: 360px;"><s:property value='#request.contractChangeItem.changeItemDescription"'/></textarea>
				</td>
			</tr>
			
			<tr>
			

				
				

				
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">

					</s:if>
					<s:else>

						<s:if test="#request.contract.contractType.substring(0,1)==1">
							<td class="t_r lableTd" width="19%;">变更原因分类</td>
							<td class="must" width="31%;">
							<select name="changeReasonType" class="input_large" >
							
									<option value="">---请选择---</option>			
									<s:if test="#request.contractChangeItem.changeReasonType==1"><option value="1" selected="selected">第三方引起的技术方案变更</option></s:if>
									<s:else > <option value="1" >第三方引起的技术方案变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeReasonType==2"><option value="2" selected="selected">进度要求改变引起的技术方案变更</option></s:if>
									<s:else > <option value="2" >进度要求改变引起的技术方案变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeReasonType==3"><option value="3" selected="selected">质量安全等级改变引起的技术方案变更</option></s:if>
									<s:else > <option value="3" >质量安全等级改变引起的技术方案变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeReasonType==4"><option value="4" selected="selected">规划调整引起的技术方案变更</option></s:if>
									<s:else > <option value="4" >规划调整引起的技术方案变更</option></s:else>	
									<s:if test="#request.contractChangeItem.changeReasonType==5"><option value="5" selected="selected">材料价格变化引起的合同价变更</option></s:if>
									<s:else > <option value="5" >材料价格变化引起的合同价变更</option></s:else>	
									<s:if test="#request.contractChangeItem.changeReasonType==6"><option value="6" selected="selected">质量安全等级改变引起的技术方案变更</option></s:if>
									<s:else > <option value="6" >人工成本价格变化引起的合同价变更</option></s:else>	
									<s:if test="#request.contractChangeItem.changeReasonType==7"><option value="7" selected="selected">人工成本价格变化引起的合同价变更</option></s:if>
									<s:else > <option value="7" >工程前期条件不确定引起的技术方案变更</option></s:else>	
											<s:if test="#request.contractChangeItem.changeReasonType==8"><option value="8" selected="selected">维稳需要引起的合同价变更</option></s:if>
									<s:else > <option value="8" >维稳需要引起的合同价变更</option></s:else>	
											<s:if test="#request.contractChangeItem.changeReasonType==9"><option value="9" selected="selected">政府法律法规修订引起的技术方案变更</option></s:if>
									<s:else > <option value="9" >政府法律法规修订引起的技术方案变更</option></s:else>	
																		
									</select>
								</td>
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">


						</s:elseif>

						<s:else>

						</s:else>
					</s:else>
				

			</tr>
			
			<tr>

				<td class="t_r lableTd">变更原因描述</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="changeReasonDescription" style="width: 360px;"><s:property value='contractChangeItem.changeReasonDescription'/></textarea>
				</td>
			</tr>	
			
			<tr>
				<td class="t_r lableTd">变更内容</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="changeContent" style="width: 360px;"><s:property value='contractChangeItem.changeContent'/></textarea>
				</td>
			</tr>	
			
			<tr>
				<td class="t_r lableTd" width="19%;">拟变金额</td>
				<td class="must" width="31%;">
					<input type="text"  name="changePrice" class="input_xxlarge" maxlength="90" value="<s:property value='contractChangeItem.changePrice'/>"/>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd" width="20%;">通过时间 </td>
				<td class="must" width="30%;">
					<input type="text"  name="passTime" class="input_xxlarge"  maxlength="100" value="<s:property value='contractChangeItem.passTime'/>"/>&nbsp;&nbsp;*
				</td>
	
		
				
			
			</tr>	

			<tr>
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">
					</s:if>
					<s:else>

						<s:if test="#request.contract.contractType.substring(0,1)==1">
						<td class="t_r lableTd" width="20%;">变更类型 </td>
						<td class="" width="30%;">
							<select name="changeType" class="input_large" >
							
									<option value="">---请选择---</option>			
									<s:if test="#request.contractChangeItem.changeType==1"><option value="1" selected="selected">方案变更</option></s:if>
									<s:else > <option value="1" >方案变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeType==2"><option value="2" selected="selected">合同变更</option></s:if>
									<s:else > <option value="2" >合同变更</option></s:else>
												
							</select>
						</td>
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">

						</s:elseif>
						<s:else>

						</s:else>
					</s:else>
					
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">

					</s:if>
					<s:else>
						<s:if test="#request.contract.contractType.substring(0,1)==1">
						<td class="t_r lableTd" width="20%;">变更性质 </td>
						<td class="must" width="30%;">
							<select name="changeNature" class="input_large" >
							
									<option value="">---请选择---</option>											
									<s:if test="#request.contractChangeItem.changeNature==1"><option value="1" selected="selected">措施变更</option></s:if>
									<s:else > <option value="1" >措施变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeNature==2"><option value="2" selected="selected">政策性变更</option></s:if>
									<s:else > <option value="2" >政策性变更</option></s:else>
									<s:if test="#request.contractChangeItem.changeNature==3"><option value="3" selected="selected">主体变更</option></s:if>
									<s:else > <option value="3" >主体变更</option></s:else>												
							</select>
						</td>
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">


						</s:elseif>

						<s:else>

						</s:else>
					</s:else>	
				

								
			</tr>	
			
			<tr>
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">
					</s:if>
					<s:else>
						<s:if test="#request.contract.contractType.substring(0,1)==1">
						<td class="t_r lableTd" width="20%;">工作量增减 </td>
						<td class="must" width="30%;">
							<select name="changeWorkload" class="input_large" >
							
									<option value="">---请选择---</option>											
									<s:if test="#request.contractChangeItem.changeWorkload==1"><option value="1" selected="selected">不变</option></s:if>
									<s:else > <option value="1" >不变</option></s:else>
									<s:if test="#request.contractChangeItem.changeWorkload==2"><option value="2" selected="selected">减少</option></s:if>
									<s:else > <option value="2" >减少</option></s:else>
									<s:if test="#request.contractChangeItem.changeWorkload==3"><option value="3" selected="selected">增加</option></s:if>
									<s:else > <option value="3" >增加</option></s:else>												
							</select>
						</td>
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">


						</s:elseif>

						<s:else>

						</s:else>
					</s:else>
					
					<s:if test="#request.contract.contractType==null || #request.contract.contractType==''">

					</s:if>
					<s:else>
						<s:if test="#request.contract.contractType.substring(0,1)==1">
						<td class="t_r lableTd" width="20%;">是否出图 </td>
						<td class="must" width="30%;">
							<select name="isPicture" class="input_large" >
							
									<option value="">---请选择---</option>											
									<s:if test="#request.contractChangeItem.isPicture==1"><option value="1" selected="selected">已出图</option></s:if>
									<s:else > <option value="1" >已出图</option></s:else>
									<s:if test="#request.contractChangeItem.isPicture==2"><option value="2" selected="selected">未出图</option></s:if>
									<s:else > <option value="2" >未出图</option></s:else>
												
							</select>
						</td>
						</s:if>
						<s:elseif test="#request.contract.contractType.substring(0,1)==2 ||#request.contract.contractType.substring(0,1)==3">
						</s:elseif>

						<s:else>

						</s:else>
					</s:else>

		
					
			</tr>	
			
			

			<tr>
				<td class="t_r lableTd">附件<br/>(单个文件上传最大为10Mb)</td>
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
				<td class="t_r lableTd" width="19%;">登记人</td>
				<td class="must" width="31%;">
					<input type="text"  name="regPerson" class="input_xxlarge" maxlength="90"/>&nbsp;&nbsp;*
				</td>

			</tr>	
			
			<tr>

				<td class="t_r lableTd" width="20%;"> 经办人 </td>
				<td class="must" width="30%;">
					<input type="text"  name="dealPerson" class="input_xxlarge"  maxlength="100"/>&nbsp;&nbsp;*
				</td>
			</tr>	
			
			<tr>
				<td class="t_r lableTd">经办部门意见</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="dealDeptSuggest" style="width: 360px;"><s:property value='contractChangeItem.dealDeptSuggest'/></textarea>
				</td>
				

			</tr>	
			
			
			<div>
					<input hidden="hidden" type="text"  name="contract.id" class="input_xxlarge" value="<s:property value='#request.contract.id'/>" maxlength="90"/>
					<input hidden="hidden" type="text"  name="id" class="input_xxlarge" value="<s:property value='#request.contractChangeItem.id'/>" maxlength="90"/>
				
					
			</div>	
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
</body>
</html>
