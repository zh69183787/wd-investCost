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
<link rel="stylesheet" href="../../css/formalize.css" />
<link rel="stylesheet" href="../../css/page.css" />
<link rel="stylesheet" href="../../css/default/imgs.css" />
<link rel="stylesheet" href="../../css/reset.css" />
<link type="text/css" href="../../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../../js/jquery-1.7.1.js"></script>
<script src="../../js/jquery.formalize.js"></script>
<script src="../../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../../js/highcharts.js"></script>
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
    </script>

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
	
	
});



//清除查询框
function clearSearch(){
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
			<img src="../../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
		</div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">报表管理</a></li>
				<li class="fin">成本内运营经费统计分析表</li>
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
				<s:form action="showReport" name="dwProject" namespace="/dwProject" method="post" id="form">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
						    <td width="6%" class="t_r" style="white-space:nowrap">报表类型</td>
							<td style="white-space:nowrap">
								<select id="reportType" name="reportType" class="input_slarge" onchange="changeReport();">
								<s:if test="type == 1 || type == null">
										<option value="0">报表0—轨道交通运营设施设备大修、更新改造项目合同进度情况统计表</option>
										<option value="1">报表1—年度线路分专业立项情况统计表</option>
										<option value="4">报表4—合同支付情况表</option>
										<option value="5">报表5—运维合同销号情况统计表</option>
										<option value="6">报表6—合同履约及支付总体情况</option>
										<option value="7">报表7—合同变更总体情况</option>
										<option value="8">报表8—用款计划及执行情况表</option>
										<option value="9" selected="selected">报表9—成本内运营经费统计分析表</option>
								</s:if>
								</select>
							
							</td>
							<td class="t_r">日期</td>
							<td style="white-space:nowrap" >
								<input type="text" name="sDate" id="sDate"  class="input_small" value="2012-01-01" readonly="readonly"/>
								&nbsp;&nbsp;&nbsp;<input type="button" name="sreach" value="查询" onclick=""/>&nbsp<input type="button" value="重置" onclick="clearSearch();">
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
<div id="h"><s:property value="#request.sDate.substring(0,4)"/>2012年成本内运营经费统计分析表</div>
<!--Table-->
        <div class="mb10" id="outter">
        	<table width="100%"  class="table_1" id="table_yusuan">
                              <tbody>
                              <tr class="tit">
                                <td>项目</td>
                                <td width="12%" colspan="3" class="t_c">车辆</td>
                                <td width="12%" colspan="3" class="t_c">通号</td>
                                <td width="12%" colspan="3" class="t_c">供电</td>
                                <td width="12%" colspan="3" class="t_c">工务</td>
                                <td width="12%" colspan="3" class="t_c">物资和后勤</td>
                                <td width="12%" colspan="3" class="t_c">本部</td>
                                <td width="18%" colspan="3" class="t_c">合计</td>
                                </tr>
                              <tr>
                                <td></td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="4%" class="t_c ys">预算</td>
                                <td width="4%" class="t_c ht">合同</td>
                                <td width="4%" class="t_c zf">支付</td>
                                <td width="6%" class="t_c ys">预算</td>
                                <td width="6%" class="t_c ht">合同</td>
                                <td width="6%" class="t_c zf">支付</td>
                                </tr>
                              <tr class="nwarp b_font">
                                <td class="tfoot_1">工资及相关费用</td>
                                <td class="t_r ys">32511</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
								<td class="t_r ys">22129</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
								<td class="t_r ys">32862</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
								<td class="t_r ys">17555</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">8572</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">2292</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">115921</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                              </tr>
                              <tr class="nwarp">
                                <td class="t_r">（1）内部人工经费</td>
                                <td class="t_r ys">32511</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">20489</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">25471</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">16685</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">8572</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">2292</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">106021</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                              </tr>
                              <tr class="nwarp">
                                <td class="t_r">（2）委外人工经费</td>
                                <td class="t_r ys">0</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">1639</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">7391</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">870</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">9900</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                              </tr>
                              <tr class="nwarp b_font">
                                <td class="tfoot_1">维修费用</td>
                                <td class="t_r ys">13290</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">9770</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">6733</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">4208</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">3842</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">37842</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
							  </tr>
                              <tr class="nwarp">
                                <td class="t_r">（1）定额内维修经费</td>
                                <td class="t_r ys">13290</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">9770</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">6733</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">3308</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">33100</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（2）中心统筹经费</td>
                                <td class="t_r ys">0</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">3000</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">3000</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（3）沉降监测费</td>
                                <td class="t_r ys">0</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">900</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">900</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（4）信息维护费</td>
                                <td class="t_r ys">0</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">842</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">842</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp b_font">
                                <td class="tfoot_1">运营经费</td>
                                <td class="t_r ys">1554</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">1162</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">467</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">313</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">11277</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">413</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">15186</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（1）其他直接费</td>
                                <td class="t_r ys">1139</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">900</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">144</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">114</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">124</td> 
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">8</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">2428</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（2）运营间接费</td>
                                <td class="t_r ys">415</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">262</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">323</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">199</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">11154</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">285</td> 
                               <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">12638</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp">
                                <td class="t_r">（3）自购固定资产折旧</td>
                                <td class="t_r ys">0</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">0</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">120</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">120</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp b_font">
                                <td class="tfoot_1">管理费用</td>
                                <td class="t_r ys">&nbsp;</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">886</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">886</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
</tr>
                              <tr class="nwarp b_font">
                                <td class="tfoot_1">应缴增值税及附加</td>
                                <td class="t_r ys">&nbsp;</td>                                
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">&nbsp;</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">4079</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">4079</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                </tr>
                              <tr class="nwarp tfoot b_font">
                                <td>合   计</td>
                                <td class="t_r ys">47355</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">33061</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">40061</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">22076</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">19849</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">11512</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                <td class="t_r ys">173914</td>
                                <td class="t_r ht">&nbsp;</td>
                                <td class="t_r zf">&nbsp;</td>
                                </tr>
                              </tbody>
                            </table>

      </div>
        <!--Table End-->
<!--Table End-->
</div>
</body>
</html>
