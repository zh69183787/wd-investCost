<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>合同计划新增</title>
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
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});
</script>




<script type="text/javascript">
//日期控件
$(function(){
	$("input[name=contractEndDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
});


//检查表单数据合法性
function checkForm(){
	return true;
}

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
	<li><a href="#">合同计划管理</a></li>
	<li class="fin">合同计划新增</li>
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
<s:form action="saveAdd" name="contractPlan" namespace="/contractPlan" method="post">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit"" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="取 消" /> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">合同计划编号</td>
				<td colspan="3" class="must">
					<input type="text" id="" name="contractNo" class="input_large" style="width: 300px;" maxlength="30"/>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同计划名称</td>
				<td><input type="text" id="" name="contractName" class="input_large" maxlength="30"/></td>
				<td class="t_r lableTd">合同计划状态</td>
				<td><input type="text" id="" name="contractStatus" class="input_large" maxlength="1"/></td>
			</tr>
			<tr>
				
				<td class="t_r lableTd">合同价(万元)</td>
				<td><input type="text" id="" name="contractPrice" class="input_large" maxlength="20"/></td>
				<td class="t_r lableTd">合同计划类型</td>
				<td><input type="text" id="" name="contractType" class="input_large" maxlength="1"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">支付方式</td>
				<td><input type="text" id="" name="payType" class="input_large" maxlength="10"/></td>
				<td class="t_r lableTd">合同计划结束时间</td>
				<td><input type="text" id="" name="contractEndDate" class="input_large" maxlength="10" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同附件</td>
				<td colspan="3"><input type="text" id="" name="contractAttachment" class="input_large" maxlength="20" /></td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同内容</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="contractContent" style="width: 360px;"></textarea>
				</td>
			</tr>
			
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">权属信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">合同业主方名称</td>
				<td><input type="text" id="" name="contractOwnerName" class="input_large" maxlength="10"/></td>
				<td class="t_r lableTd">承建供应商名称</td>
				<td><input type="text" id="" name="buildSupplierName" class="input_large" maxlength="20"/></td>
			</tr>
			
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">关联信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd">项目编号</td>
				<td><input type="text" id="" name="projectId" class="input_large" maxlength="10"/></td>
				<td class="t_r lableTd">项目名称</td>
				<td><input type="text" id="" name="projectName" class="input_large" maxlength="20"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="5" cols="5" name="remark" ></textarea>					
				</td>
			</tr>
			
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset"value="取 消" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
