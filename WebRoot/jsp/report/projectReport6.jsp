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
<title>合同履约及支付总体情况</title>
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
<style type="text/css">
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
#h1{
    font-size:20px;
    text-align:right;
} 

.input_slarge
{
  width: 210px;
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
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	
	//日期控件
	$("#sDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("#eDate").datepicker("option","minDate",selectedDate);
		}
	});	
	$("#eDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("#sDate").datepicker("option","maxDate",selectedDate);
		}
	});	

	//行、列合计
	var $totalTr = $("#total");
	if($totalTr.prevAll("tr").length==1){
		$totalTr.find("td:eq(0)").html("合计");
	}else{
		$totalTr.find("td:eq(0)").html("合计");
	}
	
	
	var $trList = $("#total").prevAll();
	var type1All = 0,type2All = 0,type3All = 0;
	var rowTotal = 0,totalAll = 0;
	$trList.each(function(index,object){
		rowTotal = 0;
		if(index!=$trList.length-1){
			//遍历list合计每一列
			type1All += parseFloat($.trim($(this).find("td:eq(4)").html()));
			type3All += parseFloat($.trim($(this).find("td:eq(5)").html()));
			//合计一行
			rowTotal = parseFloat($.trim($(this).find("td:eq(4)").html())) 
			           + parseFloat($.trim($(this).find("td:eq(5)").html()));
		}
	});
	totalAll = type3All/type1All;
	$totalTr.find("td:eq(4)").html(type1All.toFixed(4));
	$totalTr.find("td:eq(5)").html(type3All.toFixed(4));
	$totalTr.find("td:eq(7)").html(totalAll.toFixed(2) * 100);
	
});

//页面初始化时隐藏搜索条件
$(function(){
	var status = $("#showOrHide").val();
	if(status=="hide"){
		$("#filter").css("display","block");
	}else{
		$("#filter").css("display","none");
	}		
});

//自动提示，项目名称
$(function(){
	$("#projectName" ).autocomplete({
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
							id: item.id,
							projectNo:item.projectNo
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("#projectId").val("");
		},
		select: function( event, ui ) {
			$("#projectName").val(ui.item.label);
			$("#projectId").val(ui.item.id);
			$("#projectNo").val(ui.item.projectNo);
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

//自动提示模糊搜索,项目编号
$(function(){
	var saveStatus;
	$("#projectNo" ).autocomplete({
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
							id: item.id,
							projectName:item.projectName,
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("#projectId").val("");
			$("#projectName").val("");
		},
		select: function( event, ui ) {
			$("#projectNo").val(ui.item.label);
			$("#projectName").val(ui.item.projectName);
			$("#projectId").val(ui.item.id);
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
		},
		change : function(event,ui){
		}
	});
});

//清除查询框
function clearSearch(){
	$("#projectId").val("");
	$("#projectNo").val("");
	$("#projectName").val("");
	$("#sDate").val("");
	$("#eDate").val("");
}

//提交查询表单
function showList(){
$("#form").submit();
}


//控制显示或隐藏查询条件
function showOrHideControl(){
	var status = $("#showOrHide").val();
	if(status=="hide"){
	    $("#filter").slideUp();
		$("#showOrHide").val("show");
	}else{
		$("#filter").slideDown();
		$("#showOrHide").val("hide");
	}
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
				<li class="fin">合同履约及支付总体情况</li>
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
	<div class="filter" style="display: none;" id="filter">
			<div class="query p8" id="searchArea">
				<s:form action="showReport6" name="dwContract" namespace="/dwContract" method="post" id="form">
				<input type="hidden" id="showOrHide" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
							<td class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" class="input_large" onchange="changeReport();">
								<s:if test="type == 6 || type == null" >
										<option value="0">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
										<option value="1" >报表1—年度线路分专业立项情况统计表</option>
										<option value="4">报表4—合同支付情况表</option>
										<option value="5">报表5—运维合同销号情况统计表</option>
										<option value="6" selected="selected">报表6—合同履约及支付总体情况</option>
										<option value="7">报表7—合同变更总体情况</option>
										<option value="8">报表8—用款计划及执行情况表</option>
										<option value="9">报表9—成本内运营经费统计分析表</option>
								</s:if>
								</select>
					
							</td>
							<td class="t_r" style="white-space:nowrap">
								<input type="hidden" name="projectId" id="projectId" value=""/>
								项目编号
							</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectNo" id="projectNo"  class="input_xlarge" value="<s:property value="#request.projectNo"/>"/>
							</td>
							<td class="t_r" style="white-space:nowrap">项目名称</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectName" id="projectName"  class="input_xlarge" value="<s:property value="#request.projectName"/>"/>
							</td>
							</tr>
							<tr>
							<td class="t_r" >项目类型</td>
							<td >
								<input type="radio" name="funcType" id="funcType1"  value="全部"   checked="checked"/>全部 &nbsp;&nbsp;       
                                <input type="radio" name="funcType"  id="funcType2" value="外部项目" />外部项目&nbsp;&nbsp; 
                                <input type="radio" name="funcType"  id="funcType3" value="内部项目" />内部项目
							</td>
							
							<td class="t_r" style="white-space:nowrap">日期</td>
							<td style="white-space:nowrap">
								<input type="text" name="sDate" id="sDate"  class="input_ssmall" value="<s:property value="#request.sDate"/>" readonly="readonly"/>-<input type="text" name="sDate" id="eDate"  class="input_ssmall" value="<s:property value="#request.eDate"/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="t_c">
								<input type="submit" name="sreach" value="查询" onclick="showList();">
								<input type="button" value="重置" onclick="clearSearch();">
							</td>
						</tr>
					</table>
				</s:form>
				</div>
		</div>
		
		<!-- 图表区域 -->
		<div id="chart" style="display: none;">
			图表区域
		</div>
	</div>
<!--Tabs_2 End-->
<div id="h">合同履约及支付总体情况</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<tbody>
			<tr class="tit">
				<td width="5%;" >序号</td>
				<td width="20%;" >项目名称</td>
				<td width="20%;" >合同名称</td>
				<td width="15%;" >乙方单位</td>
				<td width="10%;" >变更后合同金额(万元)</td>
				<td width="10%;" >累计支付(万元)</td>
				<td width="10%;" >履约进展</td>
				<td width="10%;" >累计支付比例（%）</td>
			</tr>
			<s:iterator value="#request.list" id="list" status="st">
				<tr>
					<td class="t_c"><s:property value="#st.index+1"/></td>
					<td class="t_l"><s:property value="#list.projectName"/></td>
					<td class="t_l"><s:property value="#list.contractName"/></td>
					<td class="t_l"><s:property value="#list.partyBUnit"/></td>
					<td class="t_r"><s:property value="#list.contractPrice"/></td>
					<td class="t_r"><s:property value="#list.totalPay"/></td>
					<td class="t_l"><s:property value="#list.progressInfo"/></td>
					<td class="t_r"><s:property value="#list.totalPayRate"/></td>
				</tr>
			</s:iterator>
			<tr id="total">
				<td class="t_c"></td>
				<td class="t_c"></td>
				<td class="t_c"></td>
				<td class="t_l"></td>
				<td class="t_r"></td>
				<td class="t_r"></td>
				<td class="t_r"></td>
				<td class="t_r"></td>
			</tr>
			
		</tbody>
		<tr class="tfoot">
	      <td colspan="12">
	      	<div class="clearfix"></div>
          </td>
        </tr>
	</table>
</div>
<!--Table End-->
</div>
</body>
</html>
