<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>

<%
String path = request.getContextPath();
String basePath = request.getContextPath();
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
<title>用款计划及执行情况表</title>
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

.input_ssmall
{
  width: 122px;
}

#td2{
    font-size:12px;
    margin-top:20px;
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
		maxDate:$("#eDate").val(),
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
		minDate:$("#sDate").val(),
		onSelect:function(selectedDate){
			$("#sDate").datepicker("option","maxDate",selectedDate);
		}
	});	
	
	//页面初始化时隐藏搜索条件	
	var status = $("#showOrHide").val();
	if(status=="hide"){
		$("#searchArea").css("display","block");
	}else{
		$("#searchArea").css("display","none");
	}	
	
	
	/*
	//行、列合计
	var $totalTr = $("#total");
	if($totalTr.prevAll("tr").length==2){
		$totalTr.find("td:eq(0)").html("合计");
	}else{
		$totalTr.find("td:eq(0)").html("合计");
	}
	var $trList = $("#total").prevAll();
	var type1All = 0,type2All = 0,type3All=0;
	
	$trList.each(function(index,object){
		if((index!=$trList.length-2) &&(index!=$trList.length-1)){
			//遍历list合计每一列
			type1All += parseFloat($.trim($(this).find("td:eq(3)").html()));
			type2All += parseFloat($.trim($(this).find("td:eq(5)").html()));
			type3All += parseFloat($.trim($(this).find("td:eq(7)").html()));
		}
	});
	
	$totalTr.find("td:eq(3)").html(type1All.toFixed(4));
	$totalTr.find("td:eq(5)").html(type2All.toFixed(4));
	$totalTr.find("td:eq(7)").html(type3All.toFixed(4));
	*/
});



//自动提示，项目名称
$(function(){
	$("#projectName").autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByProjectNameAndTypeWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : "post",
					"dataType" : "json",	
					"projectName" : request.term,
					"projectType" : $("[name=funcType]:checked").val()									
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
			$("#sDate").val("");
			$("#eDate").val("");
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
				url: "<%=basePath%>/project/findProjectByProjectNoAndTypeWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectNo" : request.term,				//request.term是取到的值			
					"projectType" : $("[name=funcType]:checked").val()		
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.projectNo,
							id: item.id,
							projectName:item.projectName
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
			$("#sDate").val("");
			$("#eDate").val("");
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


//自动提示，合同名称
$(function(){
	$("#contractName").autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/contract/findByContractNameAndTypeWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : "post",
					"dataType" : "json",	
					"contractName" : request.term,
					"contractType" : $("[name=contractType]:checked").val()									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.contractName,
							id: item.id,
							contractNo:item.contractNo
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			//$("#projectId").val("");
		},
		select: function( event, ui ) {
			$("#contractName").val(ui.item.label);
			//$("#projectId").val(ui.item.id);
			$("#contractNo").val(ui.item.contractNo);
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

//自动提示模糊搜索,合同编号
$(function(){
	var saveStatus;
	$("#contractNo" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/contract/findByContractNoAndTypeWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"contractNo" : request.term,				//request.term是取到的值			
					"contractType" : $("[name=contractType]:checked").val()		
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.contractNo,
							id: item.id,
							contractName:item.contractName
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			//$("#projectId").val("");
			$("#contractName").val("");
		},
		select: function( event, ui ) {
			$("#contractNo").val(ui.item.label);
			$("#contractName").val(ui.item.contractName);
			//$("#projectId").val(ui.item.id);
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

//甲方（执行）
$(function(){
	var saveStatus = false;
	$("#contractOwnerExecuteName" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><deptName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</deptName><typeId>0</typeId></dataParams>";
			$.ajax({
				url: "<%=basePath%>/contract/findOwnerWithFuzzySearch.action?random="+Math.random(),
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
			$("#contractOwnerExecuteId").val(ui.item.value);
			$("#contractOwnerExecuteName").val(ui.item.label);
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
				$("#contractOwnerExecuteId").val("");
			}
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
	if($("[id=calculateObject]:checked").val()!="2"){
		$("[id=projectData]").hide();
		$("#chartArea").hide();
		return false;
	}
	if($("#funcType1").attr("checked")=="checked"){
		return false;
	}else{
		return true;
	}
	//$("#form").submit();
}

//画柱状图
function drawChart(){
	var funcType = "";
	if($("#funcType1").attr("checked")=="checked"){
		funcType = "0";
	}else if($("#funcType2").attr("checked")=="checked"){
		funcType = "1";
	}else if($("#funcType3").attr("checked")=="checked"){
		funcType = "2";
	}
	$.ajax({
			type : 'post',
			url : '<%=basePath%>/dwProject/findAllData8.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				projectName:$("#projectName").val(),
				projectNo:$("#projectNo").val(),
				sDate : $("#sDate").val(),
				eDate : $("#eDate").val(),
				funcType : funcType
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				var categories = [];
				var type1List = [],type2List = [],type3List = [],type4List = [],type5List = [],type6List = [];
				var sum1 = 0,sum2 = 0,sum3 = 0;
				var year = "",month = "",day = "";
				var innerValue = [];
				if(object!=null && object.length>0){
					for(var i=0; i<object.length; i++){
						if(object[i].planPayDate!=null){
							sum2 = parseFloat(object[i].planPay);
							year = parseInt(object[i].planPayDate.substring(0,4));
							if(object[i].planPayDate.substring(5,6)=='0'){
								month = parseInt(object[i].planPayDate.substring(6,7))-1;
							}else{
								month = parseInt(object[i].planPayDate.substring(5,7))-1;
							}
							day = parseInt(object[i].planPayDate.substring(8,10));
							innerValue.push(Date.UTC(year,month,day));
							innerValue.push(parseFloat(sum2.toFixed(2)));
							type2List.push(innerValue);
							innerValue = [];
						}
						
						if(object[i].actualPayDate!=null){
							sum3 = parseFloat(object[i].actualPay);
							year = parseInt(object[i].actualPayDate.substring(0,4));
							if(object[i].actualPayDate.substring(5,6)=='0'){
								month = parseInt(object[i].actualPayDate.substring(6,7))-1;
							}else{
								month = parseInt(object[i].actualPayDate.substring(5,7))-1;
							}
							day = parseInt(object[i].actualPayDate.substring(8,10));
							innerValue.push(Date.UTC(year,month,day));
							innerValue.push(parseFloat(sum3.toFixed(2)));
							type3List.push(innerValue);
							innerValue = [];
						}
					}
				}
				
				var chartOption ={
			        chart: {
			            renderTo: 'chartArea',
			            type: 'line',
			            backgroundColor:'#F0F0F2'
			        },
			        title: {
			            text: $.trim($("#h").text())
			        },
			        xAxis: {
			            type: 'datetime',
			             labels: {
			                
			                //staggerLines: 2,
			                formatter: function() {
			                       
			                               return  Highcharts.dateFormat('%Y-%m-%d', this.value);
			                }
			                
			                }
			        },
			        yAxis: {
               		 	title: {
                    		text: null
                		},
                		plotLines: [{
                    		value: 0,
                   			width: 1
               			}]
           			 },
			        legend: {
			        	enabled: true,
			            align: 'right',
			            x: -100,
			            verticalAlign: 'top',
			            y: 0,
			            floating: true,
			            borderColor: '#CCC',
			            borderWidth: 1,
			            shadow: false
			        },
			        tooltip: {
			            shared : true,
			            xDateFormat: '%Y-%m-%d'//鼠标移动到趋势线上时显示的日期格式  
			            //formatter: function() {
			            //    return '<b>'+ this.x +'</b>'+ this.series.name +': '+ this.y +'<br/>';
			            //}
            			
			        },credits:{					
			 			enabled:false
			 		},
			        series: [{
		                name: '计划支付',
		                data: type2List
		            },{
		                name: '实际支付',
		                data:  type3List
		            }]
			    };
			    
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
$(function(){
	drawChart();
})

function clearDate(obj){
	var value = "<s:property value='#request.funcType'/>";
	if(value!=$(obj).val()){
		$("#sDate").val("");
		$("#eDate").val("");
	}
}

function changeObject(obj){
	var value = $(obj).attr("value");
	//alert(value);
	$("[id^=changeTr_]").hide();
	$("#changeTr_"+value).show();
	clearDate();
	
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
				<li class="fin">用款计划及执行情况表</li>
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
				<s:form action="showReport8" name="dwProject" namespace="/dwProject" method="post" id="form">
				<input type="hidden" id="showOrHide" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
						<td class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" class="input_large" onchange="changeReport();">
								<s:if test="type == 8 || type == null">
										<option value="0">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
										<option value="1" >报表1—年度线路分专业立项情况统计表</option>
										<option value="4">报表4—合同支付情况表</option>
										<option value="5">报表5—运维合同销号情况统计表</option>
										<option value="6">报表6—合同履约及支付总体情况</option>
										<option value="7">报表7—合同变更总体情况</option>
										<option value="8" selected="selected">报表8—用款计划及执行情况表</option>
										<option value="9">报表9—成本内运营经费统计分析表</option>
								</s:if>
								</select>
							
							</td>
							<td class="t_r" style="white-space:nowrap">
								统计对象
							</td>
							<td style="white-space:nowrap">
								<input type="radio" value="1" id="calculateObject" name="calculateObject" onclick="changeObject(this)"/> 合同
								<input type="radio" value="2" checked="checked" id="calculateObject" name="calculateObject" onclick="changeObject(this)"/> 项目
								<input type="radio" value="3" id="calculateObject" name="calculateObject" onclick="changeObject(this)"/> 执行单位
								<input type="radio" value="4" id="calculateObject" name="calculateObject" onclick="changeObject(this)"/> 全部
							</td>
							<td class="t_r" style="white-space:nowrap">日期</td>
							<td style="white-space:nowrap">
								<input type="text" name="sDate" id="sDate"  class="input_ssmall" value="<s:property value="#request.sDate"/>" readonly="readonly"/>-
								<input type="text" name="eDate" id="eDate"  class="input_ssmall" value="<s:property value="#request.eDate"/>" readonly="readonly"/>
							</td>
							</tr>
							
							
							<tr id="changeTr_1" style="display:none">
							<td class="t_r" >合同类型</td>
							<td >
								<input type="radio" name="contractType" checked="checked" value="0"/>  全部
								<input type="radio" name="contractType" value="1"/>  建设类
								<input type="radio" name="contractType" value="2"/>  运维类
							</td>
							<td class="t_r" style="white-space:nowrap">
								合同编号
							</td>
							<td style="white-space:nowrap">
								<input type="text" class="input_xlarge" id="contractNo"/>
							</td>
							
							<td class="t_r" style="white-space:nowrap">合同名称</td>
							<td style="white-space:nowrap">
								<input type="text" class="input_xlarge" id="contractName"/>
							</td>
						</tr>
							
							<tr id="changeTr_2">
							<td class="t_r" >项目类型</td>
							<td >
								<s:if test="#request.funcType==0">
									<input type="radio" name="funcType" id="funcType1"  value="0" checked="checked" onclick="clearDate(this)"/>全部 &nbsp;&nbsp;  
								</s:if>
								<s:else>
									<input type="radio" name="funcType" id="funcType1"  value="0" onclick="clearDate(this)"/>全部 &nbsp;&nbsp; 
								</s:else>   
								<s:if test="#request.funcType==1">
									<input type="radio" name="funcType"  id="funcType2" value="1" checked="checked" onclick="clearDate(this)"/>外部项目&nbsp;&nbsp; 
								</s:if>
								<s:else>
									<input type="radio" name="funcType"  id="funcType2" value="1"  onclick="clearDate(this)"/>外部项目&nbsp;&nbsp; 
								</s:else>   
								<s:if test="#request.funcType==2">
									<input type="radio" name="funcType"  id="funcType3" value="2" checked="checked" onclick="clearDate(this)"/>内部项目
								</s:if>
								<s:else>
									<input type="radio" name="funcType"  id="funcType3" value="2" onclick="clearDate(this)"/>内部项目
								</s:else>   
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
						
						<tr id="changeTr_3" style="display:none">
							
							<td class="t_r" style="white-space:nowrap">单位名称</td>
							<td style="white-space:nowrap">
								<input type="hidden" name="contractOwnerExecuteId" id="contractOwnerExecuteId"/>
								<input type="text" class="input_slarge" name="contractOwnerExecuteName" id="contractOwnerExecuteName"/>
							</td>
						</tr>
						
						<tr id="changeTr_4" style="display:none">
						</tr>
						
						<tr>
							<td colspan="8" class="t_c">
								<input type="submit" value="查询" onclick="return showList();">
								<input type="button" value="重置" onclick="clearSearch();">
							</td>
						</tr>
					</table>
				</s:form>
				</div>
		</div>
		
		<!-- 图表区域 -->
		<div id="chartArea" style="display:block;">
		</div>
	</div>
<!--Tabs_2 End-->
<div id="h">用款计划及执行情况表</div>
<div class="mb10">
	<table width="100%" class="table_1" border="1">
		<tbody>
			<tr class="tit">
				<td width="5%;" id="td2">序号</td>
				<td width="35%;" >项目名称</td>
				<td width="30%;" colspan="2">计划支付</td>
				<td width="30%;" colspan="2">实际支付</td>
			</tr>
			<tr class="tit">
				<td width="40%;" colspan="2"></td>
				<td width="15%;" >时间</td>
				<td width="15%;" >数额(万元)</td>
				<td width="15%;" >时间</td>
				<td width="15%;" >数额(万元)</td>
			</tr>
			<s:iterator value="#request.list" id="list" status="st">
				<tr id="projectData">
					<td class="t_c"><s:property value="#st.index+1"/></td>
					<td class="t_l"><s:property value="#list.projectName"/></td>
					<td class="t_c"><s:date name="#list.planPayDate" format="yyyy-MM"/></td>
					<td class="t_r">
						<s:if test="#list.planMonthPay.length()-#list.planMonthPay.indexOf('.')>=3">
							<s:property value="#list.planMonthPay.substring(0,#list.planMonthPay.indexOf('.')+3)"/>	
						</s:if>
						<s:else>
							<s:property value="#list.planMonthPay"/>
						</s:else>
					</td>
					<td class="t_c"><s:date name="#list.actualPayDate" format="yyyy-MM"/></td>
					<td class="t_r">
						<s:if test="#list.monthPay.length()-#list.monthPay.indexOf('.')>=3">
							<s:property value="#list.monthPay.substring(0,#list.monthPay.indexOf('.')+3)"/>	
						</s:if>
						<s:else>
							<s:property value="#list.monthPay"/>0
						</s:else>
					</td>
				</tr>
			</s:iterator>
			<!-- 
			<tr id="total">
				<td class="t_c"></td>
				<td class="t_l"></td>
				<td class="t_c"></td>
				<td class="t_r"></td>
				<td class="t_c"></td>
				<td class="t_r"></td>
				<td class="t_c"></td>
				<td class="t_r"></td>
			</tr>
			 -->
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
