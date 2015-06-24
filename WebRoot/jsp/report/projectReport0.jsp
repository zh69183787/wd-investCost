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
<title>轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</title>
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
<script src="../js/highcharts.js"></script>
<style type="text/css">
tr td{
	white-space: nowrap;
}
#h{
    font-size:20px;
    text-align:center;
}   
.input_slarge
{
  width: 210px;
}
</style>

<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	
	$("#contractCreateDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
	//“说明”点段列字段截取
	$("td[id='explan']").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),10));
	});
	
});

//缩略显示,截取字符串并在最后加上"..."
function thumbnailDisplay(data,length){
	if(data==null || "undefined"==data || ""==data ){
		return data;
	}
	if(length==null || ""==length || parseInt(length)=="undefined"){
		length = 20;
	}
	if(data.length>length){
		data = data.substring(0,length)+"...";
	}
	return data;
}

//页面初始化时隐藏搜索条件
$(function(){
	var status = $("#showOrHide").val();
	if(status=="hide"){
		$("#searchArea").css("display","block");
	}else{
		$("#searchArea").css("display","none");
	}
});

function changeReport(){
	var typeValue = $("#reportType").val();
	if(typeValue==1){
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
//重置搜索条件
function clearSearch(){
	$("#contractCreateDate").val("");
}



</script>




</head>

<body>
<div class="main">
	<div class="ctrl clearfix">
		<div class="fl">
			<img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
		</div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">报表管理</a></li>
				<li class="fin">轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</li>
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
				<s:form action="showReport0" name="dwContract" namespace="/dwContract" method="post" id="form">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
						    <td width="6%" class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" onchange="changeReport();">
									<option value="0" selected="selected">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
									<option value="1">报表1—年度线路分专业立项情况统计表</option>
									<option value="4">报表4—合同支付情况表</option>
									<option value="5">报表5—运维合同销号情况统计表</option>
									<option value="6">报表6—合同履约及支付总体情况</option>
									<option value="7">报表7—合同变更总体情况</option>
									<option value="8">报表8—用款计划及执行情况表</option>
									<option value="9">报表9—成本内运营经费统计分析表</option>
								</select>
							
							</td>
							<td class="t_r">日期</td>
							<td style="white-space:nowrap" >
								<input type="text" name="contractCreateDate" id="contractCreateDate"  class="input_small" value="<s:property value="#request.contractCreateDate"/>" readonly="readonly"/>
								&nbsp;&nbsp;&nbsp;<input type="button" name="sreach" value="查询" onclick="$('#form').submit();"/>
								&nbsp;<input type="button" value="重置" onclick="clearSearch();">
								<input type="hidden" name="showOrHide" id="showOrHide" value="<s:property value="#request.showOrHide"/>">
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
		
		<!-- 图表区域 -->
		<div id="chartArea" style="display: block;"></div>
		
	</div>
<!--Tabs_2 End-->
<div id="h">
	<span style="text-align:center;">上海申通地铁集团有限公司 <s:property value="#request.contractCreateDate.substring(0,4)"/>年轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</span>
	<span style="display:inline-block;float:left;font-size:15px;">填报单位:XXXX</span>
	<span style="display:inline-block;text-align:right;float:right;font-size:15px;">单位：万元</span>
</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<tbody>
			<tr class="tit">
				<td width="5%;" >序号</td>
				<td width="6%;" >项目分类</td>
				<td width="10%;" >集团批文号</td>
				<td width="6%;" >费用性质</td>
				<td width="10%;" >项目合同名称</td>
				<td width="10%;" >费用承担单位</td>
				<td width="6%;" >合同价/决算价</td>
				<td width="6%;" >至前一年底开累支付</td>
				<td width="6%;" >XXXX年实际支付</td>
				<td width="6%;" >第一季度支付</td>
				<td width="6%;" >第二季度支付</td>
				<td width="6%;" >第三季度支付</td>
				<td width="6%;" >第四季度支付</td>
				<td width="11%;" >说明</td>
			</tr>
			<s:iterator value="#request.resultList" id="progress" status="st">
				<tr>
					<td><s:property value="#st.index+1"/></td>
					<td><s:property value="#progress.projectType"/></td>
					<td id="approvalNo" title="<s:property value="#progress.groupCompanyApprovalNo"/>"><s:property value="#progress.groupCompanyApprovalNo"/></td>
					<td><s:property value="#progress.costType"/></td>
					<td class="t_l" id="projectContractName" title="<s:property value="#progress.projectContractName"/>"><s:property value="#progress.projectContractName"/></td>
					<td class="t_l" id="paidCompany" title="<s:property value="#progress.paidCompany"/>"><s:property value="#progress.paidCompany"/></td>
					<td class="t_r">
						<s:property value="#progress.contractPrice"/>
					</td>
					<td class="t_r"><s:property value="#progress.lastAccumulativeTotal"/></td>
					<td class="t_r"><s:property value="#progress.actualPay"/></td>
					<td class="t_r"><s:property value="#progress.firstSeasonPay"/></td>
					<td class="t_r"><s:property value="#progress.secondSeasonPay"/></td>
					<td class="t_r"><s:property value="#progress.thirdSeasonPay"/></td>
					<td class="t_r"><s:property value="#progress.fourthSeasonPay"/></td>
					<td class="t_l" title="<s:property value="#progress.explanation"/>" id="explan"><s:property value="#progress.explanation"/></td>
				</tr>
			</s:iterator>
		</tbody>
		<tr class="tfoot">
	      <td colspan="14">
	      	<div class="clearfix"></div>
          </td>
        </tr>
	</table>
</div>
<!--Table End-->
</div>
</body>
</html>
