<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>交通委补贴新增</title>
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
	
	$("input[name=projectName]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/api/project/projects.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"pageSize" : 3,
					"projectName" : request.term									
				},
				success: function( data ) {
					response( $.map( data.projects, function( item,index ) {
						return {
							label: item.projectName,
							value: item.id,
							dispatchNo:item.dispatchNo,
							contracts: item.contracts
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("input[name=projectId]").val("");
			$("input[name=dispatchNo]").val("");
			
			$('input[name=contractId]').val("");
			$('input[name=contractName]').val("");
			$("#contracts_setting_td").empty();
		},
		select: function( event, ui ) {
			$("input[name=projectName]").val(ui.item.label);
			$("input[name=projectId]").val(ui.item.value);
			$("input[name=dispatchNo]").val(ui.item.dispatchNo);

 			var html = '';
			html += '<select name="contractNo" id="contractNo" onchange="onSelContract(this)"><option value="">---请选择---</option>';
			$.each(ui.item.contracts,function(){
				html += '<option contractId="'+this.id+'" contractName="'+this.contractName+'">' + this.contractNo + '</option>';
			});
			html += '</select>';
			$("#contracts_setting_td").html(html);			
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
function onSelContract(obj){
	var c = $("option:selected",obj);
	$('input[name=contractName]').val(c.attr('contractName'));
	$('input[name=contractId]').val(c.attr('contractId'));
}

//检查表单数据合法性
function checkForm(){
	if($("input[name=projectId]").val()==''){
		alert('请选择项目！');
		$("input[name=dispatchNo]").focus();
		return false;
	}
	if($("input[name=contractId]").val()==''){
		alert('请选择合同！');
		$("select[name=contractNo]").focus();
		return false;
	}
/* 	if($("select[name=line]").val()==''){
		alert('请选择线路！');
		$("select[name=line]").focus();
		return false;
	}	 */
	var moneyBox = $("input[name=money]");
	if(moneyBox.val()==''){
		alert('请输入政府补贴数（万元）！');
		moneyBox.focus();
		return false;
	}
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]{0,6}){0,1}$");  
	if(!reg.test($.trim(moneyBox.val()))){
		alert("政府补贴数（万元）请输入数字，且最多6位小数！");
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
	<li class="fin">交通委补贴新增</li>
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
<s:form action="saveAdd" name="fundAllowance" namespace="/fundAllowance" method="post">
<input type="hidden" name="projectId" value="<s:property value='fundAllowance.projectId'/>">
<input type="hidden" name="contractId" value="<s:property value='fundAllowance.contractId'/>">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit"" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" /> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">基本信息</td><td colspan="3"></td></tr>
			<tr>
				<td class="t_r lableTd" width="15%">项目名称</td>
				<td class="must" width="35%">
					<input type="text" name="projectName" class="input_large" style="width: 320px;" maxlength="200"/>
					&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd" width="15%">项目批文号(自动带入)</td>
				<td width="35%">
					<input type="text" id="" name="dispatchNo" class="input_large" readonly="readonly" maxlength="200" style="border:0px;"/>
				</td>				
			</tr>
			<tr>
				<td class="t_r lableTd">合同自有编号</td>
				<td class="must" id="contracts_setting_td">

				</td>
				<td class="t_r lableTd">合同名称(自动带入)</td>
				<td>
					<input type="text" name="contractName" class="input_large" readonly="readonly" style="width: 350px;border:0px;" maxlength="200"/>
				</td>				
			</tr>			
			<tr>
				<td class="t_r lableTd">补贴年份</td>
				<td>
					<select name="year" id="year">
						<%int curYear = (Integer)request.getAttribute("thisYear");
							for(int i=2014;i<curYear+5;i++){
						%>
						<option <%=i==curYear?"selected":""%>><%=i%></option>
						<%
							}
						%>
					</select>				
				</td>
				<td class="t_r lableTd">线路</td>
				<td>
				<select name="line" id="add_line">
					<option value="">---请选择---</option>
					<s:iterator id="route" value="#request.routes">
						<option value="<s:property value="#route.objectName"/>"><s:property value="#route.objectName"/></option>
					</s:iterator>
				</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">政府补贴数(万元)</td>
				<td colspan="3" class="must"><input type="text" id="" name="money" class="input_large" maxlength="20"/>&nbsp;&nbsp;*</td>			
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset"value="重 置" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
