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
<title>年度轨道交通运营设施设备大修和更新改造项目资金计划情况完成表(项目详表)</title>
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

function showTitle(){
	var type1array = [];		//专业id的array
	var type2array = [];		//集团批文号id的array
	var type3array = [];		//项目名称id的array
	
	$('#dataArea').find('tr').each(function(index){
		var professionalType = $(this).find('td:eq(1)').attr('alias1');
		var professionalTypeStatus = true;
		for(var i=0;i<type1array.length;i++){			
			if(type1array[i]==professionalType){
				professionalTypeStatus = false;
				break;
			}
		}
		if(professionalTypeStatus)type1array.push(professionalType);
		
		var dispatchNo = $(this).find('td:eq(2)').attr('alias2');
		var dispatchNoStatus = true;
		for(var i=0;i<type2array.length;i++){
			if(type2array[i]==dispatchNo){
				dispatchNoStatus = false;
				break;
			}
		}
		if(dispatchNoStatus)type2array.push(dispatchNo);
		
		var projectName = $(this).find('td:eq(3)').attr('alias3');
		var projectNameStatus = true;
		for(var i=0;i<type3array.length;i++){
			if(type3array[i]==projectName){
				projectNameStatus = false;
				break;
			}
		}
		if(projectNameStatus)type3array.push(projectName);
	});
	
	
	for(var i=0;i<type1array.length;i++){
		var currentId = type1array[i];
		$('#dataArea').find('td[alias1="'+currentId+'"]').each(function(index){
			if(index==0){
				var $target = $('#dataArea').find('td[alias1="'+currentId+'"]:eq(0)');
				$target.attr('rowspan',$('#dataArea').find('td[alias1="'+currentId+'"]').length);
				$target.attr('style','vertical-align:middle;');
			}else{
				$(this).remove();
			}
		});
	}
	
	for(var i=0;i<type2array.length;i++){
		var currentId = type2array[i];
		$('#dataArea').find('td[alias2="'+currentId+'"]').each(function(index){
			if(index==0){
				var $target = $('#dataArea').find('td[alias2="'+currentId+'"]:eq(0)');
				$target.attr('rowspan',$('#dataArea').find('td[alias2="'+currentId+'"]').length);
				$target.attr('style','vertical-align:middle;');
			}else{
				$(this).remove();
			}
		});
	}
	
	for(var i=0;i<type3array.length;i++){
		var currentId = type3array[i];
		$('#dataArea').find('td[alias3="'+currentId+'"]').each(function(index){
			if(index==0){
				var $target = $('#dataArea').find('td[alias3="'+currentId+'"]:eq(0)');
				$target.attr('rowspan',$('#dataArea').find('td[alias3="'+currentId+'"]').length);
				$target.attr('style','vertical-align:middle;');
			}else{
				$(this).remove();
			}
		});
	}
}

function getNumberCount(number,text){
	if(text==null || $.trim(text)==''){
		return number;
	}else{
		if(number==null) return parseFloat($.trim(text));
		return number+parseFloat($.trim(text));
		//return (number+parseFloat($.trim(text))).toFixed(6);
	}
}
String.prototype.replaceAll = stringReplaceAll;
function stringReplaceAll(AFindText,ARepText){
raRegExp = new RegExp(AFindText,"g");
return this.replace(raRegExp,ARepText)
}
function countAll(){
	var feeMoney=null,budget=null,contractPrice=null,finalPrice=null,td10=null,td11=null,td13=null,td14=null;
	$('#dataArea').find("tr").each(function(index){
		if(index!=$('#dataArea').find("tr").length-1){
			feeMoney = getNumberCount(feeMoney,$(this).find('td:eq(6)').html());
			budget = getNumberCount(budget,$(this).find('td:eq(7)').html());
			
			contractPrice = getNumberCount(contractPrice,$(this).find('td:eq(9)').html());
			finalPrice = getNumberCount(finalPrice,$(this).find('td:eq(10)').html());
			td10 = getNumberCount(td10,$(this).find('td:eq(11)').html());
			td11 = getNumberCount(td11,$(this).find('td:eq(12)').html());
			td13 = getNumberCount(td13,$(this).find('td:eq(13)').html());
			td14 = getNumberCount(td14,$(this).find('td:eq(14)').html());
		}
	});
	
	$('#countAll').find("td:eq(5)").html(feeMoney!=null?feeMoney.toFixed(6):feeMoney);
	$('#countAll').find("td:eq(6)").html(budget!=null?budget.toFixed(6):budget);
	$('#countAll').find("td:eq(8)").html(contractPrice!=null?contractPrice.toFixed(6):contractPrice);
	$('#countAll').find("td:eq(9)").html(finalPrice!=null?finalPrice.toFixed(6):finalPrice);
	
	$('#countAll').find("td:eq(10)").html(td10!=null?td10.toFixed(6):td10);
	$('#countAll').find("td:eq(11)").html(td11!=null?td11.toFixed(6):td11);
	$('#countAll').find("td:eq(12)").html(td13!=null?td13.toFixed(6):td13);
	$('#countAll').find("td:eq(13)").html(td14!=null?td14.toFixed(6):td14);
}

function refresh(){
	window.location.href='showReportProjectCount.action?year='+$('#year').val();
}

function showReportName(){
	var html = '上海申通地铁集团有限公司'+$('#year').val()+'年度轨道交通运营设施设备大修和更新改造项目资金计划申请表(项目详表)<span style="float:right;">单位(万元)</span>'
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
				<li class="fin">年度资金计划申请表(项目详表)</li>
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
							<td class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" class="input_large" onchange="changeReport(this.value);">
									<s:if test="type == 4 || type == null">
											<option value="11" selected="selected">报表1.1年度资金计划申请表(项目详表)</option>
											<option value="12">报表1.2汇总表</option>
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
	  	上海申通地铁集团有限公司年度轨道交通运营设施设备大修和更新改造项目资金计划申请表(项目详表)<span style="float:right;">单位(万元)</span>
</div>
<!--Tabs_2 End-->
<div class="mb10" id="outter">
	<table width="100%"  class="table_1" style="overflow: scroll;">
		<thead style="background: white;">
			<tr class="nwarp" >
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="5%;" align="center" >序号</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">专业</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">立项依据（集团批文号）</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">项目名称</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">执行主体</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">费用承担单位</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" width="15%;">费用承担金额</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" >批准预算金额</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" >维修类型</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" >合同价总计</td>
                <td class="t_c" rowspan="2" style="vertical-align:middle" >决算价<br>（若有）</td>
                <td class="t_c" rowspan="1" colspan="2"  >至上年底累计已支付</td>
                <td class="t_c" rowspan="1" colspan="2"  >本年度用款计划</td>
            </tr>
            <tr class="nwarp" >
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
                <td class="t_c" >总数</td>
                <td class="t_c" >其中政府<br>补贴数</td>
            </tr>
		</thead>
		<tbody id="dataArea">
			<s:iterator id="vo" value="#request.list" status="st">
				<tr>
	                <td><s:property value="#st.index+1"/></td>
	                <td alias1="<s:property value="#vo.professionalType"/>" class="t_c">
	                	<s:if test="#vo.professionalType==1">车辆</s:if>
						<s:elseif test="#vo.professionalType==2">供电</s:elseif>
						<s:elseif test="#vo.professionalType==3">通号</s:elseif>
						<s:elseif test="#vo.professionalType==4">工务</s:elseif>
						<s:elseif test="#vo.professionalType==5">基地</s:elseif>
						<s:elseif test="#vo.professionalType==6">车站机电</s:elseif>
	                </td>
	                <td alias2="<s:property value="#vo.dispatchNo"/>"><s:property value="#vo.dispatchNo"/></td>
	                <td alias3="<s:property value="#vo.projectName"/>"><s:property value="#vo.projectName"/></td>
	                <td><s:property value="#vo.mainExecute"/></td>
	                <td><s:property value="#vo.feeDepartment"/></td>
	                <td class="t_r"><s:property value="#vo.feeMoney"/></td>
	                <td class="t_r"><s:property value="#vo.approvalBugget"/></td>
	                <td class="t_c">
	                	<s:if test="#vo.maintainType==2">更新改造</s:if>
	                	<s:elseif test="#vo.maintainType==3">大修</s:elseif>
	                </td>
	                <td class="t_r"><s:property value="#vo.contractPrice"/></td>
	                <td class="t_r"><s:property value="#vo.finalPrice"/></td>
	                <td class="t_r"><s:property value="#vo.lastYearPayCount"/></td>
	                <td class="t_r"><s:property value="#vo.lastYearPayGov"/></td>
	                <td class="t_r"><s:property value="#vo.thisYearPlanCount"/></td>
	                <td class="t_r"><s:property value="#vo.thisYearPlanGov"/></td>
	            </tr>
			</s:iterator>
				<tr id="countAll">
	                <td colspan="2" class="t_c">合计</td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td></td>
	               	<td class="t_r"></td>
	               	<td></td>
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
