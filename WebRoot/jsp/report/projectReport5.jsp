<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getContextPath();
%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运维合同销号情况统计表</title>
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
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
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
tr td{
	white-space: nowrap;
}

#h{
    font-size:20px;
    text-align:center;
}  

.input_ssmall
{
  width: 122px;
}

.input_slarge
{
  width: 210px;
}
</style>

<script type="text/javascript">

var $tblAlterRow = $(".table_1 tbody tr:even");
if ($tblAlterRow.length > 0)
	$tblAlterRow.css("background","#fafafa");			
		

$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	//日期控件
	$("#contractSignedStart").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("#contractSignedEnd").datepicker("option","minDate",selectedDate);
		}
	});	
	$("#contractSignedEnd").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("#contractSignedStart").datepicker("option","maxDate",selectedDate);
		}
	});	
});

//页面初始化时隐藏搜索条件
$(function(){
	var status = $("#showOrHide").val();
	status = "hide";
	if(status=="hide"){
		$("#searchArea").css("display","block");
	}else{
		$("#searchArea").css("display","none");
	}		
});

//控制显示或隐藏查询条件
function showOrHideControl(){
	var status = $("#showOrHide").val();
	if(status=="hide"){
	    $("#searchArea").slideUp();
		$("#showOrHide").val("show");
	}else{
		$("#searchArea").slideDown();
		$("#showOrHide").val("hide");
	}
}

function clearSearch(){
	$("#projectId").val("");
	$("#projectNo").val("");
	$("#projectName").val("");
	$("#contractSignedStart").val("");
	$("#contractSignedEnd").val("");
	$("#contractStatus").children("option:first").attr("selected",true);
}
function changeReport(){
	var typeValue = $("#reportType").val();
	if(typeValue==0){
		window.location.href='<%=basePath%>/dwContract/showReport0.action';
	}else if(typeValue==1){
		window.location.href='<%=basePath%>/dwProject/showReport.action';
	}else if(typeValue==4){
		window.location.href='<%=basePath%>/dwProject/showReport4.action';
	}else if(typeValue==5){
		window.location.href='<%=basePath%>/dwContract/showReport5.action';
	}else if(typeValue==6){
		window.location.href='<%=basePath%>/dwContract/showReport6.action';
	}else if(typeValue==7){
		window.location.href='<%=basePath%>/dwContract/showReport7.action';
	}else if(typeValue==8){
		window.location.href='<%=basePath%>/dwProject/showReport8.action';
	}else if(typeValue==9){
		window.location.href='<%=basePath%>/jsp/report/projectReport9.jsp';
	}
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
function queruForm(){
	if($("#projectName").val()!="" || $("#projectNo").val()!=""){
		if($("#projectId").val()==""){
			alert("请重新选择项目！");
			return false;
		}	
	}
	$("#form").submit();
}

//导出Excel
function exportExcel(){
	$("#export").val("yes");
	$("#form").submit();
	$("#export").val("");
}

function strlen(str) {  
    var s = 0;  
    for(var i = 0; i < str.length; i++) {  
        if(str.charAt(i).match(/[\u0391-\uFFE5]/)) {  
            s += 2;     
        } else {  
            s++;  
        }  
    }  
    return parseInt(s/2);  
} 


$(function(){
	$("td[name='wordbreak']").each(function(){
		if(strlen($.trim($(this).text())) > 20){
			$(this).text($(this).text().substring(0,20)+"...");
		}
	});

});




</script>




</head>

<body style="overflow-x: hidden;">
<div class="main">
	<div class="ctrl clearfix">
		<div class="fl">
			<img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
		</div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">报表管理</a></li>
				<li class="fin">运维合同销号情况统计表</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#" onclick="window.print();">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" id="query" onclick="showOrHideControl();">查询</a></li>
			</ul>
		</div>
	</div>

	<div class="pt45">
		<div class="filter">
			<div class="query p8" id="searchArea">
				<s:form action="showReport5" name="dwContract" namespace="/dwContract" method="post" id="form">
					<input type="hidden" name="export" id="export"/>
					<input type="hidden" id="showOrHide" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
						<td class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" class="input_large" onchange="changeReport();">
								<s:if test="type == 5 || type == null">
										<option value="0">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
										<option value="1" >报表1—年度线路分专业立项情况统计表</option>
										<option value="4">报表4—合同支付情况表</option>
										<option value="5" selected="selected">报表5—运维合同销号情况统计表</option>
										<option value="6">报表6—合同履约及支付总体情况</option>
										<option value="7">报表7—合同变更总体情况</option>
										<option value="8">报表8—用款计划及执行情况表</option>
										<option value="9">报表9—成本内运营经费统计分析表</option>
								</s:if>
								</select>
							
							</td>
							<td class="t_r" style="white-space:nowrap">
								<input type="hidden" name="projectId" id="projectId" value="<s:property value='#request.projectId'/>" />项目编号
							</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectNo" id="projectNo"  class="input_xlarge" value="<s:property value='#request.projectNo'/>"/>
							</td>
							<td class="t_r" style="white-space:nowrap">项目名称</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectName" id="projectName"  class="input_xlarge" value="<s:property value='#request.projectName'/>"/>
							</td>
								</tr>
							<tr>
							<td class="t_r" style="white-space:nowrap">日期</td>
							<td style="white-space:nowrap">
								<input type="text" name="contractSignedStart" id="contractSignedStart"  class="input_small" value="<s:property value='#request.contractSignedStart'/>" readonly="readonly"/>
								-<input type="text" name="contractSignedEnd" id="contractSignedEnd"  class="input_small" value="<s:property value='#request.contractSignedEnd'/>" readonly="readonly"/>
							</td>
							<td class="t_r" style="white-space:nowrap">合同状态</td>
							<td style="white-space:nowrap">
								<select name="contractStatus" id="contractStatus">
									<s:if test="#request.contractStatus==1">
										<option value="">---请选择---</option>
										<option value="1" selected="selected">实施中</option>
										<option value="3">已销号</option>
									</s:if>
									<s:elseif test="#request.contractStatus==3">
										<option value="">---请选择---</option>
										<option value="1">实施中</option>
										<option value="3" selected="selected">已销号</option>
									</s:elseif>
									<s:else>
										<option value="">---请选择---</option>
										<option value="1">实施中</option>
										<option value="3">已销号</option>
									</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="t_c">
								<input type="button" value="查询" onclick="queruForm();">
								<input type="button" value="重置" onclick="clearSearch();">
							</td>
						</tr>
					</table>
				</s:form>
			</div>
			<div class="fn clearfix">
				<span style="float: right;">
					<input type="button" value="导出" onclick="exportExcel();" style="float:right;"/>
				</span>
			</div>
		</div>
		
		<!-- 图表区域 -->
		<div id="chart" style="display: none;">
			图表区域
		</div>
	</div>
	
<div align="center" id = "h">
	  	运维合同销号情况统计表
</div>
<!--Tabs_2 End-->
<div class="mb10" id="outter">
	<table width="100%"  class="table_1">
		<tbody>
			<tr class="tit nwarp">
                <td class=" t_c">序号</td>
                <td>合同编号</td>
                <td>合同名称</td>
                <td>承包商/供应商</td>
                <td>合同价(万元)</td>
                <td>结算价(万元)</td>
                <td>合同签约时间</td>
                <td>合同状态</td>
			</tr>
			<s:iterator value="#request.destoryNumberList" id="list" status="st">
				<tr>
	                <td class="t_c" style="width: 5%;"><s:property value="#st.index+1"/></td>
	                <td style="width: 20%;" name="wordbreak" title="<s:property value="#list.contractNo"/>"><s:property value="#list.contractNo"/></td>
	                <td style="width: 20%;" name="wordbreak" title="<s:property value="#list.contractName"/>"><s:property value="#list.contractName"/></td>
	                <td style="width: 10%;" name="wordbreak"><s:property value="#list.supplier"/></td>
	                <td style="width: 10%;" class="t_r">
	                	<s:property value="%{getFormattedMoney(#list.contractPrice)}"/>
	                </td>
	                <td style="width: 10%;" class="t_r">
	                	<s:if test="#list.finalPrice!=null">
	                		<s:property value="%{getFormattedMoney(#list.finalPrice)}"/>
	                	</s:if>
	                </td>
	                 <td style="width: 10%;" class="t_c">
	                	<s:property value="#list.contractSignedDate"/>
	                </td>
					<td style="width: 10%;" class="t_c">
						<s:if test="#list.contractStatus==3">已销号</s:if>
						<s:elseif test="#list.contractStatus==1">实施中</s:elseif>
					</td>
	        	</tr>
			</s:iterator>
		</tbody>
		<tr class="tfoot">
	      <td colspan="8">
	      	<div class="clearfix"></div>
          </td>
        </tr>
	</table>
</div>
<!--Table End-->
</div>
</body>
</html>
