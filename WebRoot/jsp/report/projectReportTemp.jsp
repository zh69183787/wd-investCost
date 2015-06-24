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
<title>年度线路分专业立项情况统计表</title>
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
#h1{
    font-size:20px;
    text-align:right;
} 

.input_slarge
{
  width: 210px;
}

#cover
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
    width:70%;
    height:500px;
    border:2px #6699CC solid;
    z-index:2;
    background:#FFFFFF;
    margin:20px auto;
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
	var type1All = 0,type2All = 0,type3All = 0,type4All = 0,type5All = 0,type6All = 0;
	var rowTotal = 0,totalAll = 0;
	$trList.each(function(index,object){
		rowTotal = 0;
		if(index!=$trList.length-1){
			//遍历list合计每一列
			type1All += getNumber($.trim($(this).find("td:eq(2)").text()));
			type2All += getNumber($.trim($(this).find("td:eq(3)").text()))
			type3All += getNumber($.trim($(this).find("td:eq(4)").text()));
			type4All += getNumber($.trim($(this).find("td:eq(5)").text()));
			type5All += getNumber($.trim($(this).find("td:eq(6)").text()));
			type6All += getNumber($.trim($(this).find("td:eq(7)").text()));
			//合计一行
			rowTotal = getNumber($.trim($(this).find("td:eq(2)").text())) 
			           + getNumber($.trim($(this).find("td:eq(3)").text()))
			           + getNumber($.trim($(this).find("td:eq(4)").text())) 
			           + getNumber($.trim($(this).find("td:eq(5)").text())) 
			           + getNumber($.trim($(this).find("td:eq(6)").text()))
			           + getNumber($.trim($(this).find("td:eq(7)").text()));
			$(this).find("td:eq(8)").html(rowTotal.toFixed(4));
		}
	});
	//<a href="project/findProjectByPage.action?professionalType=1">
	totalAll = type1All + type2All + type3All 
				+ type4All + type5All + type6All;
	$totalTr.find("td:eq(2)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=1&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type1All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(3)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=2&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type2All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(4)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=3&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type3All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(5)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=4&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type4All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(6)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=5&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type5All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(7)").html("<a href=\'<%=basePath%>/project/findProjectByPage.action?professionalType=6&nomodify=viewOnly&projectNo="+$('#currentYear').val()+"' target='_blank'>"+type6All.toFixed(4)+"</a>");
	$totalTr.find("td:eq(8)").html(totalAll.toFixed(4));
});

//传入文本,得到数字
function getNumber(text){
	if(text==null || text=="") return 0;
	var number = parseFloat(text);
	if(number!="NaN") return number ;
	else return null;
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

function showList(){
	$("#form").submit();
}


//清除查询框
function clearSearch(){
	$("#projectId").val("");
	$("#projectNo").val("");
	$("#projectName").val("");
	$("#sDate").val("");
	$("#eDate").val("");
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
	}else if(typeValue==100){
		window.location.href='<%=basePath%>/dwProject/showReportTemp.action';
	}else if(typeValue==9){
		window.location.href='<%=basePath%>/jsp/report/projectReport9.jsp';
	}
}

$(function(){
	drawChart();
});

//画柱状图
function drawChart(){
	var Property="";
	$.ajax({
			type : 'post',
			url : '<%=basePath%>/dwProject/findAllDataTemp.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				sDate : $("#sDate").val()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				var categories = [];
				var type1List = [],type2List = [],type3List = [],type4List = [],type5List = [],type6List = [];
				if(object!=null && object.length>0){
					for(var i=0; i<object.length; i++){
						categories.push(object[i].lineName);
						type1List.push(getNumber(object[i].type1));
						type2List.push(getNumber(object[i].type2));
						type3List.push(getNumber(object[i].type3));
						type4List.push(getNumber(object[i].type4));
						type5List.push(getNumber(object[i].type5));
						type6List.push(getNumber(object[i].type6));
					}
				}
				var chartOption ={
			        chart: {
			            renderTo: 'chartArea',
			            type: 'column',
			            backgroundColor:'#F0F0F2'
			        },
			        title: {
			            text: $.trim($("#h").text())
			        },
			        xAxis: {
			            categories: categories,
			            labels:	{
						    formatter: function(){
						    	return '<a href="javascript:showChart(\''+this.value+'\',0)">'+this.value+'</a>';	
						    }
					    }	
			        },
			        yAxis: {
			            title: {
			                text: null
			            }
			        },
			        credits:{					
			 			enabled:false
			 		},
			        legend: {
			        	enabled: true,
			            align: 'right',
			            x: -100,
			            verticalAlign: 'top',
			            y: 20,
			            floating: true,
			            borderColor: '#CCC',
			            borderWidth: 1,
			            shadow: false
			        },
			        tooltip: {
			            formatter: function() {
			                //return '<b>'+ this.x +'</b>'+ this.series.name +': '+ this.y +'<br/>'+'总计: '+ parseFloat(this.point.stackTotal).toFixed(4);
			                return '<b>'+ this.x +'</b>'+ this.series.name +': '+ this.y ;
			            }
			        },
			        plotOptions: {
			            column: {
			                //stacking: 'normal'
			            },
			          // 点击柱状图后触发	
			            series: {
			                cursor: 'pointer',
			                events: {
			                    click: function(event) {
			                    //alert(event.point.x);
			                    if(event.point.x==0){
									showPic();
									}
			                    }
			                }
			            }
			        },
			        series: [{
		                name: '车辆',
		                data: type1List
		            },{
		                name: '供电',
		                data: type2List
		            },{
		                name: '通号',
		                data: type3List
		            },{
		                name: '工务',
		                data: type4List
		            },{
		                name: '基地',
		                data: type5List
		            },{
		                name: '车站机电',
		                data: type6List
		            }]
			    }
			    new Highcharts.Chart(chartOption); 
			}
		});
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
</script>

<script type="text/javascript">
//显示饼图区域
function showChart(obj,type){
	if(type==0){
		showChart($("td[id='"+obj+"']").find("a"));
	}else{
		var height = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
		$("#cover").css("height",height);
		$("#cover").css("margin-top",getScrollTop());
		$("#title").html($(obj).html());
		$("#cover").show();
		drawPie(obj);
	}
}

function showPic(){
		var height = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
		$("#title").html("");
		$("#cover").css("height",height);
		$("#cover").css("width",900);
		$("#cover").css("margin-top",getScrollTop());
		$("#pieArea").html("<img src='/investCost/jsp/report/img/line1.jpeg'/>");
		$("#cover").show();
}


//隐藏饼图区域
function hideChart(){
	$("#cover").hide();
}
function getScrollTop() {
    if ('pageYOffset' in window) {
        return window.pageYOffset;
    } else if (document.compatMode === "BackCompat") {
        return document.body.scrollTop;
    } else {
        return document.documentElement.scrollTop;
    }
}

function drawPie(obj){
	var $targetTr = $(obj).parent().parent();
	var data1 = getNumber($.trim($targetTr.find("#type1").text()));
	var data2 = getNumber($.trim($targetTr.find("#type2").text()));
	var data3 = getNumber($.trim($targetTr.find("#type3").text()));
	var data4 = getNumber($.trim($targetTr.find("#type4").text()));
	var data5 = getNumber($.trim($targetTr.find("#type5").text()));
	var data6 = getNumber($.trim($targetTr.find("#type6").text()));
	var dataAll = getNumber($.trim($targetTr.find("#dataAll").text()));
	var chartOption ={
        chart: {
            renderTo: 'pieArea',
            type: 'pie',
            backgroundColor:'#ffffff',
            width:$("#tb_window").width()-20
        },
        title: {
            text: $.trim($(obj).html())
        },
        xAxis: {
            categories: ['车辆','供电','通号','工务','基地','车站机电']
        },
        yAxis: {
            title: {
                text: null
            }
        },
        credits:{					
 			enabled:false
 		},
        legend: {
        	enabled: true,
            align: 'right',
            x: -100,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return this.point.name +': '+ this.y +'<br/>'+'总计: '+ dataAll;
                //return this.point.name+'：'+this.percentage.toFixed(2)+'%';
            }
        },
        plotOptions: {
            pie: {
                stacking: 'normal',
                dataLabels: {
					enabled: true,			
					//color: 'black',			
					connectorColor: '#333',
					//distance: -20,
					style: {		
                        fontSize:'16px'
                    },
					formatter: function() {
						return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
				}
            }
        },
        series: [{
        	data:[
        		['车辆',data1],
        		['供电',data2],
        		['通号',data3],
        		['工务',data4],
        		['基地',data5],
        		['车站机电',data6]
        	]       
        }]
    }
    new Highcharts.Chart(chartOption); 
}

function getNumber(data){
	if(data==null || data=="" || data=="undefined") return 0;
	if(parseFloat(data)=="NaN" || parseFloat(data)=="undefined") return 0;
	return parseFloat(parseFloat(data).toFixed(2));
}

function openNewWindow(url,moneysource){
	var money = encodeURI(moneysource);
	url += "&projectMoneysource="+money;
	window.open(url);
}
</script>


</head>

<body>
<div id="cover" style="left: 0px; top: 0px;">
	<div id="tb_window" style="margin-left:15%;">
		<div style="background:#6699CC; text-align:right; height:28px; padding:5px;">
		    <label id="title" style="float:left; font-size:15px; color:blue;"></label>
		    <input type="button" onclick="hideChart();" value="关闭" style=" border-style:none;" />
		</div>
		<div style="padding-left:6px;padding-top: 6px;" id="pieArea">
		</div>
	</div>
</div>


<div class="main">
	<div class="ctrl clearfix">
		<div class="fl">
			<img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
		</div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">报表管理</a></li>
				<li class="fin">年度线路分专业立项情况统计表</li>
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
				<s:form action="showReportTemp" name="dwProject" namespace="/dwProject" method="post" id="form">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
						    <td width="6%" class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" onchange="changeReport();">
								<s:if test="type == 1 || type == null">
										<option value="0">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
										<option value="1">报表1—年度线路分专业立项情况统计表</option>
										<option value="100" selected="selected">报表1'—年度线路分专业立项情况统计表</option>
										<option value="4">报表4—合同支付情况表</option>
										<option value="5">报表5—运维合同销号情况统计表</option>
										<option value="6">报表6—合同履约及支付总体情况</option>
										<option value="7">报表7—合同变更总体情况</option>
										<option value="8">报表8—用款计划及执行情况表</option>
										<option value="9">报表9—成本内运营经费统计分析表</option>
								</s:if>
								</select>
							
							</td>
							<td class="t_r">日期</td>
							<td style="white-space:nowrap" >
								<input type="text" name="sDate" id="sDate"  class="input_small" value="<s:property value="#request.sDate"/>" readonly="readonly"/>
								&nbsp;&nbsp;&nbsp;<input type="button" name="sreach" value="查询" onclick="showList();"/>&nbsp<input type="button" value="重置" onclick="clearSearch();">
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
<div id="h"><s:property value="#request.sDate.substring(0,4)"/>年度线路分专业立项情况统计表
	<input type="hidden" value="<s:property value="#request.sDate.substring(0,4)"/>" id="currentYear">
</div>
<div id="h1">单位：万元</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<tbody>
			<tr class="tit">
				<td width="5%;" >序号</td>
				<td width="18%;" >线路/专业</td>
				<td width="11%;" >车辆</td>
				<td width="11%;" >供电</td>
				<td width="11%;" >通号</td>
				<td width="11%;" >工务</td>
				<td width="11%;" >基地</td>
				<td width="11%;" >车站机电</td>
				<td width="11%;">合计</td>
			</tr>
			<s:iterator value="#request.list" id="list" status="st">
				<tr>
					<td class="t_c"><s:property value="#st.index+1"/></td>
					<td class="t_l" id="<s:property value="#list.lineName"/>">
						<a href="javascript:void(0);" onclick="showChart(this);"><s:property value="#list.lineName"/></a>
                    </td>
					<td class="t_r" id="type1">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=1&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type1"/></a>
					</td>
					<td class="t_r" id="type2">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=2&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type2"/></a>
					</td>
					<td class="t_r" id="type3">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=3&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type3"/></a>
					</td>
					<td class="t_r" id="type4">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=4&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type4"/></a>
					</td>
					<td class="t_r" id="type5">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=5&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type5"/></a>
					</td>
					<td class="t_r" id="type6">
						<a href="javascript:void(0);" onclick="openNewWindow('<%=basePath%>/project/findProjectByPage.action?professionalType=6&nomodify=viewOnly&projectNo=<s:property value='#request.sDate.substring(0,4)'/>','<s:property value="#list.lineName"/>')"><s:property value="#list.type6"/></a>
					</td>
					<td class="t_r" id="dataAll"></td>
				</tr>
			</s:iterator>
			<tr id="total">
				<td class="t_c"></td>
				<td class="t_l"></td>
				<td class="t_r"></td>
				<td class="t_r"></td>
				<td class="t_r"></td>
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
