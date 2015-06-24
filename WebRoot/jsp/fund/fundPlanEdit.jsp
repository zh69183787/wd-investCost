<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资金计划编辑</title>
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
</style>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
	$('#year').val('<s:property value="fundPlan.year"/>');
	$('#line').val('<s:property value="fundPlan.line"/>');
	
	$("input[name=dispatchNo]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByDispatchNoWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"dispatchNo" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.dispatchNo,
							value: item.id,
							projectName:item.projectName
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("input[name=projectId]").val("");
			//$("input[name=projectName]").val("");
		},
		select: function( event, ui ) {
			$("input[name=dispatchNo]").val(ui.item.label);
			//$("input[name=projectName]").val(ui.item.projectName);
			$("input[name=projectId]").val(ui.item.value);
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
			//$("input[name=estimateSubjectName]").val("");
			//$("input[name=estimateSubjectName]").attr("disabled","true");
		}
	});
});
</script>




<script type="text/javascript">
$(function(){

	
});


//检查表单数据合法性
function checkForm(){
	if($("input[name=projectId]").val()==''){
		alert('请选择项目！');
		$("input[name=dispatchNo]").focus();
		return false;
	}
	if($("select[name=year]").val()==''||$("select[name=year]").val()==null){
		alert('请选择计划年度！');
		$("select[name=year]").focus();
		return false;
	}	
	if($("select[name=line]").val()==''){
		alert('请选择线路！');
		$("select[name=line]").focus();
		return false;
	}	
	var moneyBox = $("input[name=money]");
	if(moneyBox.val()==''){
		alert('请输入用款计划（万元）！');
		moneyBox.focus();
		return false;
	}
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]{0,6}){0,1}$");  
	if(!reg.test($.trim(moneyBox.val()))){
		alert("用款计划（万元）请输入数字，且最多6位小数！");
		moneyBox.focus();
		return false;
	}	
	
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
	<li class="fin">资金计划编辑</li>
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
<s:form action="saveEdit" name="fundPlan" namespace="/fundPlan" method="post">
<input type="hidden" name="id" value="<s:property value='fundPlan.id'/>">
<input type="hidden" name="projectId" value="<s:property value='fundPlan.projectId'/>">
<input type="hidden" name="createDate" value="<s:property value='fundPlan.createDate'/>">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_c">
				<span style="display:inline;" class="must"><s:property value='#request.browserMessage'/></span>
			</th>
		</thead>
		<tbody id="formBody">
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd" width="15%">项目批文号</td>
				<td colspan="3" class="must">
					<input type="text" id="" name="dispatchNo" value="<s:property value='fundPlan.dispatchNo'/>" class="input_large" style="width: 300px;" maxlength="30"/>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd" width="15%">计划年度</td>
				<td width="35%" class="must">
					<select name="year" id="year">
						<%int curYear = (Integer)request.getAttribute("thisYear");
							for(int i=2014;i<curYear+5;i++){
						%>
						<option><%=i%></option>
						<%
							}
						%>
					</select>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd" width="15%">线路</td>
				<td width="35%">
				<input type="hidden" name="line" value="<s:property value='fundPlan.line'/>"/>
				<s:property value='fundPlan.line'/>				
<%-- 				<select name="line" id="line">
					<option value="">---请选择---</option>
					<s:iterator id="route" value="#request.routes">
						<option value="<s:property value="#route.objectName"/>"><s:property value="#route.objectName"/></option>
					</s:iterator>
				</select>&nbsp;&nbsp;* --%>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">用款计划(万元)</td>
				<td colspan="3" class="must"><input type="text" id="" name="money" value="<s:property value='fundPlan.money'/>" class="input_large" maxlength="20"/>&nbsp;&nbsp;*</td>			
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
