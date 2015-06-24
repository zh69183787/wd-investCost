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
<title>年轨道交通运营设施设备大修和更新改造项目资金计划情况完成表</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<style type="text/css">
.table_1 td{
	border: 1px solid #D0D0D3;
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

 $(document).ready(function () {
          var $tbInfo = $(".filter .query input:text");
          $tbInfo.each(function () {
              $(this).focus(function () {
                  $(this).attr("placeholder", "");
              });
          });
	
	var $tblAlterRow = $(".table_1 tbody tr:even");
	if ($tblAlterRow.length > 0)
		$tblAlterRow.css("background","#fafafa");			
		
		$("#outter").css("overflowX","auto");
		var w = $(window).width();
		if(w>1000){
			$("#outter").width($(window).width());
		}else{
			$("#outter").width(1000);
		}
      });

$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	var height = 0;
	var screenHeight = window.document.body.clientHeight;
	$('#main').children().each(function(index){
		if(index!=3){
			height += $(this).height();
		}else{
			var style = $(this).attr("style")+";height:"+(screenHeight-height-15)+"px;";
			$(this).attr("style",style);
		}
	})
	countAll();
	showReportName();
	
	$(window).resize(function(){
		$("#outter").css("overflowX","auto");
		var w = $(window).width();
		var h = $(window).height();
		if(w>1000){
			$("#outter").width($(window).width());
		}else{
			$("#outter").width(1000);
		}
			
		var h2 =0; 
		$("#main").children("div:lt(3)").each(function(){
			h2+= $(this).height();
		});
		$("#outter").height($(window).height()-h2-15);
	});
});

function changeReport(type){
	if(type==11){
		window.location.href='showReportProjectCount.action';
	}else if(type==12){
		window.location.href='showReportMajorCountPlan.action';
	}else if(type==13){
		window.location.href='showReportMajorPlan.action';
	}else if(type==21){
		window.location.href='showReportProject.action';
	}else if(type==22){
		window.location.href='showReportMajorCount.action';
	}else if(type==23){
		window.location.href='showReportMajor.action';
	}else if(type==31){
		window.location.href='showReportMajorSeason.action';
	}else if(type==32){
		window.location.href='showReportProjectProgress.action';
	}
}

function refresh(){
	window.location.href='showReportMajorCountPlan.action?year='+$('#year').val()+'&type='+$('#type').val();
}

function getNumberCount(number,text){
	if(text==null || $.trim(text)==''){
		return number;
	}else{
		return number+parseFloat($.trim(text));
	}
}

function countAll(){
	var carPlanCount = null, carPlanGov = null,carActualCount = null,carActualGov =null;
	var elePlanCount =null,elePlanGov=null,eleActualCount=null,eleActualGov=null;
	var numPlanCount =null,numPlanGov=null,numActualCount=null,numActualGov=null;
	var workPlanCount =null,workPlanGov=null,workActualCount=null,workActualGov=null;
	var stationPlanCount =null,stationPlanGov=null,stationActualCount=null,stationActualGov=null;
	var basePlanCount =null,basePlanGov=null,baseActualCount=null,baseActualGov=null;
	var otherPlanCount =null,otherPlanGov=null,otherActualCount=null,otherActualGov=null;
	var allPlanCount =null,allPlanGov=null,allActualCount=null,allActualGov=null;
	$('#dataArea').find('tr').each(function(index){
		if(index!=$('#dataArea').find('tr').length-1){
			carPlanCount = getNumberCount(carPlanCount,$.trim($(this).find('td:eq(2)').html()));
			carPlanGov = getNumberCount(carPlanGov,$(this).find('td:eq(3)').html());
			carActualCount = getNumberCount(carActualCount,$.trim($(this).find('td:eq(4)').html()));
			carActualGov = getNumberCount(carActualGov,$.trim($(this).find('td:eq(5)').html()));
			
			elePlanCount = getNumberCount(elePlanCount,$.trim($(this).find('td:eq(6)').html()));
			elePlanGov = getNumberCount(elePlanGov,$.trim($(this).find('td:eq(7)').html()));
			eleActualCount = getNumberCount(eleActualCount,$.trim($(this).find('td:eq(8)').html()));
			
		}
	})
	$('#dataArea').find('tr:last').find('td:eq(1)').html(carPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(2)').html(carPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(3)').html(carActualCount);
	$('#dataArea').find('tr:last').find('td:eq(4)').html(carActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(5)').html(elePlanCount);
	$('#dataArea').find('tr:last').find('td:eq(6)').html(elePlanGov);
	$('#dataArea').find('tr:last').find('td:eq(7)').html(eleActualCount);
}

function showReportName(){
	var html = '申通地铁集团'+$('#year').val()+'年轨道交通运营设施设备大修项目资金计划完成情况表(汇总表）<span style="float:right;">单位(万元)</span>';
	$('#h').html(html);
}
function exportExcel(){
	var currentUrl = window.location.href ;
	if(currentUrl.indexOf('?')!=-1){
		window.location.href = currentUrl + '&export=yes';
	}else{
		window.location.href = currentUrl + '?export=yes';
	}
}

</script>




</head>

<body style="overflow-x: hidden;">
<div class="main" id="main">
	<div class="ctrl clearfix">
		<div class="fl">
			<img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
		</div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">交港局报表</a></li>
				<li class="fin">年度资金计划完成情况表(汇总表)</li>
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
				<s:form action="showReport4" name="dwProject" namespace="/dwProject" method="post" id="form">
					<input type="hidden" id="showOrHide" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
							<td class="t_r" style="white-space:nowrap" width="5%;">报表类型</td>
							<td style="white-space:nowrap" width="15%;">
								<select id="reportType" name="reportType" class="input_slarge" class="input_large" onchange="changeReport(this.value);">
										<s:if test="type == 4 || type == null">
											<option value="11">报表1.1年度资金计划完成情况表(项目详表)</option>
											<option value="12" selected="selected">报表1.2计划申请表(汇总表)</option>
											<option value="13">报表1.3,1.4季度汇总表</option>
											<option value="21">报表2.1年度资金计划完成情况表(项目详表)</option>
											<option value="22">报表2.2汇总表</option>
											<option value="23">报表2.3,2.4年度资金计划完成情况表(分专业汇总)</option>
											<option value="31">报表3.1汇总表季度总结</option>
											<option value="32">报表3.2实施进展情况表</option>
										</s:if>
								</select>
							</td>
							
							<td class="t_r" style="white-space:nowrap;" width="5%;">年份</td>
							<td style="white-space:nowrap" width="100%;">
								<select name="year" id="year" onchange="refresh();">
									<s:iterator id="year" value="#request.allYear" status="st">
										<s:if test="#request.year==#year">
											<option value="<s:property value="#year"/>" selected="selected"><s:property value="#year"/></option>
										</s:if>
										<s:else>
											<option value="<s:property value="#year"/>"><s:property value="#year"/></option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td class="t_r" style="white-space:nowrap;" width="5%;">
								<input type="button" value="导出" onclick="exportExcel();" style="float:right;"/>
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
	
<div align="center" id = "h">
	申通地铁集团20  年轨道交通运营设施设备大修和更新改造项目资金计划申请表(汇总表)<span style="float:right;">单位(万元)</span>
</div>
<!--Tabs_2 End-->
<div class="mb10" id="outter">
	<table width="100%"  class="table_1">
		<thead style="background: white;">
			<tr class="nwarp" >
                <td class="t_c" rowspan="3" style="vertical-align:middle" width="5%;" align="center" >序号</td>
                <td class="t_c" rowspan="3" style="vertical-align:middle" width="5%">线路\维修类型</td>
                <td class="t_c" colspan="2" style="" width="15%;">大修</td>
                <td class="t_c" colspan="2" style="" width="15%;">更新改造</td>
                <td class="t_c" colspan="2" style="" width="15%;">小计</td>
            </tr>
            <tr class="nwarp" >
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
            </tr>
            <tr class="nwarp" >
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
            </tr>
		</thead>
		<tbody id="dataArea">
			<s:iterator id="major" value="#request.result" status="st">
				<tr>
	                <td class="t_c"><s:property value="#st.index+1"/></td>
	                <td class="t_c"><s:property value="#major.lineName"/></td>
	                <td class="t_r"><s:property value="#major.overhaulPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.overhaulPlanGov"/></td>
	                <td class="t_r"><s:property value="#major.updatePlanCount"/></td>
	                <td class="t_r"><s:property value="#major.updatePlanGov"/></td>
	                <td class="t_r"><s:property value="#major.allPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.allPlanGov"/></td>
	            </tr>
			</s:iterator>
				<tr>
	                <td class="t_c" colspan="2">合计</td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	            </tr>
		</tbody>
	</table>
</div>
<!--Table End-->
</div>
</body>
</html>
