<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>投资概算科目详细</title>
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


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});
</script>



</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">工程投资造价管理</a></li>
	<li><a href="#">投资概算科目管理</a></li>
	<li class="fin">投资概算科目详细</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#" onclick="window.print();">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">
<s:form action="saveAdd" name="estimateSubject" namespace="/estimateSubject" method="post">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody id="formBody">
			<!-- <tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr> -->
			<tr>
				<td class="t_r lableTd" style="width: 15%;">科目名称</td>
				<td style="width: 35%;">
					<s:property value="investEstimateSubject.subjectName"/>
				</td>
				<td class="t_r lableTd" style="width: 15%;">科目状态</td>
				<td style="width: 35%;">
					<s:property value="investEstimateSubject.subjectStatus"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">工程量数据</td>
				<td><s:property value="investEstimateSubject.projectAmount"/></td>
				<td class="t_r lableTd">工程量单位</td>
				<td><s:property value="investEstimateSubject.projectAmountUnit"/></td>
			</tr>
			<tr>
				
				<td class="t_r lableTd">建筑工程批复（万元）</td>
				<td>
					<s:if test="investEstimateSubject.estimateDecideFirst==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.estimateDecideFirst.substring(0,investEstimateSubject.estimateDecideFirst.indexOf('.')+5)"/>					
					</s:else>
				</td>
				<td class="t_r lableTd">安装工程批复（万元）</td>
				<td>
					<s:if test="investEstimateSubject.estimateDecideSecond==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.estimateDecideSecond.substring(0,investEstimateSubject.estimateDecideSecond.indexOf('.')+5)"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">设备工器具批复（万元）</td>
				<td>
					<s:if test="investEstimateSubject.estimateDecideThird==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.estimateDecideThird.substring(0,investEstimateSubject.estimateDecideThird.indexOf('.')+5)"/>
					</s:else>
					
				</td>
				<td class="t_r lableTd">其他费用批复（万元）</td>
				<td>
					<s:if test="investEstimateSubject.estimateDecideOther==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.estimateDecideOther.substring(0,investEstimateSubject.estimateDecideOther.indexOf('.')+5)"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">概算批复值（合计）（万元）</td>
				<td>
					<s:if test="investEstimateSubject.estimateDecideSum==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.estimateDecideSum.substring(0,investEstimateSubject.estimateDecideSum.indexOf('.')+5)"/>						
					</s:else>
				</td>
				<td class="t_r lableTd">经济指标（单价）（万元）</td>
				<td>
					<s:if test="investEstimateSubject.economicIndicator==0">0</s:if>
					<s:else>
						<s:property value="investEstimateSubject.economicIndicator.substring(0,investEstimateSubject.economicIndicator.indexOf('.')+5)"/>						
					</s:else>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<s:property value="investEstimateSubject.remark"/>					
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</td>
		</tr>
		
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
