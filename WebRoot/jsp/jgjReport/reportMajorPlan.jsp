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
		//alert("all="+$(window).height()+",top="+h2+",outter="+$("#outter").height());
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
	window.location.href='showReportMajorPlan.action?year='+$('#year').val()+'&type='+$('#type').val();
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
			eleActualGov = getNumberCount(eleActualGov,$.trim($(this).find('td:eq(9)').html()));
			
			numPlanCount = getNumberCount(numPlanCount,$.trim($(this).find('td:eq(10)').html()));
			numPlanGov = getNumberCount(numPlanGov,$.trim($(this).find('td:eq(11)').html()));
			numActualCount = getNumberCount(numActualCount,$.trim($(this).find('td:eq(12)').html()));
			numActualGov = getNumberCount(numActualGov,$.trim($(this).find('td:eq(13)').html()));
			
			workPlanCount = getNumberCount(workPlanCount,$.trim($(this).find('td:eq(14)').html()));
			workPlanGov = getNumberCount(workPlanGov,$.trim($(this).find('td:eq(15)').html()));
			workActualCount = getNumberCount(workActualCount,$.trim($(this).find('td:eq(16)').html()));
			workActualGov = getNumberCount(workActualGov,$.trim($(this).find('td:eq(17)').html()));
			
			stationPlanCount = getNumberCount(stationPlanCount,$.trim($(this).find('td:eq(18)').html()));
			stationPlanGov = getNumberCount(stationPlanGov,$.trim($(this).find('td:eq(19)').html()));
			stationActualCount = getNumberCount(stationActualCount,$.trim($(this).find('td:eq(20)').html()));
			stationActualGov = getNumberCount(stationActualGov,$.trim($(this).find('td:eq(21)').html()));
			
			basePlanCount = getNumberCount(basePlanCount,$.trim($(this).find('td:eq(22)').html()));
			basePlanGov = getNumberCount(basePlanGov,$.trim($(this).find('td:eq(23)').html()));
			baseActualCount = getNumberCount(baseActualCount,$.trim($(this).find('td:eq(24)').html()));
			baseActualGov = getNumberCount(baseActualGov,$.trim($(this).find('td:eq(25)').html()));
			
			otherPlanCount = getNumberCount(otherPlanCount,$.trim($(this).find('td:eq(26)').html()));
			otherPlanGov = getNumberCount(otherPlanGov,$.trim($(this).find('td:eq(27)').html()));
			otherActualCount = getNumberCount(otherActualCount,$.trim($(this).find('td:eq(28)').html()));
			otherActualGov = getNumberCount(otherActualGov,$.trim($(this).find('td:eq(29)').html()));
			
			allPlanCount = getNumberCount(allPlanCount,$.trim($(this).find('td:eq(30)').html()));
			allPlanGov = getNumberCount(allPlanGov,$.trim($(this).find('td:eq(31)').html()));
			allActualCount = getNumberCount(allActualCount,$.trim($(this).find('td:eq(32)').html()));
			allActualGov = getNumberCount(allActualGov,$.trim($(this).find('td:eq(33)').html()));
		}
	})
	$('#dataArea').find('tr:last').find('td:eq(1)').html(carPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(2)').html(carPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(3)').html(carActualCount);
	$('#dataArea').find('tr:last').find('td:eq(4)').html(carActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(5)').html(elePlanCount);
	$('#dataArea').find('tr:last').find('td:eq(6)').html(elePlanGov);
	$('#dataArea').find('tr:last').find('td:eq(7)').html(eleActualCount);
	$('#dataArea').find('tr:last').find('td:eq(8)').html(eleActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(9)').html(numPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(10)').html(numPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(11)').html(numActualCount);
	$('#dataArea').find('tr:last').find('td:eq(12)').html(numActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(13)').html(workPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(14)').html(workPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(15)').html(workActualCount);
	$('#dataArea').find('tr:last').find('td:eq(16)').html(workActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(17)').html(stationPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(18)').html(stationPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(19)').html(stationActualCount);
	$('#dataArea').find('tr:last').find('td:eq(20)').html(stationActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(21)').html(basePlanCount);
	$('#dataArea').find('tr:last').find('td:eq(22)').html(basePlanGov);
	$('#dataArea').find('tr:last').find('td:eq(23)').html(baseActualCount);
	$('#dataArea').find('tr:last').find('td:eq(24)').html(baseActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(25)').html(otherPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(26)').html(otherPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(27)').html(otherActualCount);
	$('#dataArea').find('tr:last').find('td:eq(28)').html(otherActualGov);
	
	$('#dataArea').find('tr:last').find('td:eq(29)').html(allPlanCount);
	$('#dataArea').find('tr:last').find('td:eq(30)').html(allPlanGov);
	$('#dataArea').find('tr:last').find('td:eq(31)').html(allActualCount);
	$('#dataArea').find('tr:last').find('td:eq(32)').html(allActualGov);
}

function showReportName(){
	var html = $('#h').html().replace('xxxx',$('#year').val());
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
				<li class="fin">年度资金计划申请表(分专业汇总)</li>
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
											<option value="12">报表1.2汇总表</option>
											<option value="13" selected="selected">报表1.3,1.4年度资金计划申请表(分专业汇总)</option>
											<option value="21">报表2.1年度资金计划完成情况表(项目详表)</option>
											<option value="22">报表2.2汇总表</option>
											<option value="23">报表2.3,2.4年度资金计划完成情况表(分专业汇总)</option>
											<option value="31">报表3.1汇总表季度总结</option>
											<option value="32">报表3.2实施进展情况表</option>
										</s:if>
								</select>
							</td>
							
							<td class="t_r" style="white-space:nowrap;" width="5%;">年份</td>
							<td style="white-space:nowrap" width="10%;">
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
							<td class="t_r" style="white-space:nowrap;" width="5%;">项目类型</td>
							<td style="white-space:nowrap" width="100%;">
								<select name="type" id="type" onchange="refresh();">
									<s:if test="#request.type==1">
										<option value="3" selected="selected">大修项目</option>
										<option value="2">更新改造项目</option>
									</s:if>
									<s:elseif test="#request.type==2">
										<option value="3">大修项目</option>
										<option value="2" selected="selected">更新改造项目</option>
									</s:elseif>
									<s:else>
										<option value="3">大修项目</option>
										<option value="2">更新改造项目</option>
									</s:else>
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
		<s:if test="#request.type==3">
			申通地铁集团xxxx年轨道交通运营设施设备大修项目资金计划申请表(大修，分专业汇总）
		</s:if>
		<s:elseif test="#request.type==2">
			申通地铁集团xxxx年轨道交通运营设施设备更新改造项目资金计划申请表(更新改造，分专业汇总)
		</s:elseif>
	  	<span style="float:right;">单位(万元)</span>
</div>
<!--Tabs_2 End-->
<div class="mb10" id="outter">
	<table width="100%"  class="table_1">
		<thead style="background: white;">
			<tr class="nwarp" >
                <td class="t_c" rowspan="3" style="vertical-align:middle" width="5%;" align="center" >序号</td>
                <td class="t_c" rowspan="3" style="vertical-align:middle" width="5%">线路\专业</td>
                <td class="t_c" colspan="2" style="" width="15%;">车辆</td>
                <td class="t_c" colspan="2" style="" width="15%;">供电</td>
                <td class="t_c" colspan="2" style="" width="15%;">通号</td>
                <td class="t_c" colspan="2" style="" width="15%;">工务</td>
                <td class="t_c" colspan="2" style="" width="15%;">车站机电</td>
                <td class="t_c" colspan="2" style="" width="15%;">段场</td>
                <td class="t_c" colspan="2" style="" width="15%;">其他</td>
                <td class="t_c" colspan="2" style="" width="15%;">合计</td>
            </tr>
            <tr class="nwarp" >
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
                <td class="t_c" colspan="2" >计划用款</td>
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
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
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
	                <td class="t_r"><s:property value="#major.carPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.carPlanGov"/></td>
	                <td class="t_r"><s:property value="#major.elePlanCount"/></td>
	                <td class="t_r"><s:property value="#major.elePlanGov"/></td>
	                <td class="t_r"><s:property value="#major.numPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.numPlanGov"/></td>
	                <td class="t_r"><s:property value="#major.workPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.workPlanGov"/></td>
	                <td class="t_r"><s:property value="#major.stationPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.stationPlanGov"/></td>
	                <td class="t_r"><s:property value="#major.basePlanCount"/></td>
	                <td class="t_r"><s:property value="#major.basePlanGov"/></td>
	                <td class="t_r"><s:property value="#major.otherPlanCount"/></td>
	                <td class="t_r"><s:property value="#major.otherPlanGov"/></td>
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
	                <td class="t_r"></td>
	                <td class="t_r"></td>
	                <td class="t_r"></td>
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
