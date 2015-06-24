<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<title>合同详细</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/uploadify.css" />
<style>
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
<script src="../js/addDetail.js"></script>
<script src="../js/jquery.uploadify-3.1.js"></script>
<script src="http://10.1.48.16:8080/workflow/contract/js/purchase.js"></script>
<style type="text/css">
tr td{
	/*white-space: nowrap;*/
}
</style>
<%String basePath = request.getContextPath(); %>
<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});

function showDatepicker(){
	$("input[id=add_operateDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
}

function showDatepickerEdit(){
	$("input[name=operateDate_]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
}

$(function(){
	loadViewPage($("#contractOwnerName"));
    $("#inviteBidTypeDiv").wrapSelect({
        url:"http://10.1.48.16:8080/workflow/contract-reviewUtil/dictionary.action",
        param:{"random":Math.random(),"code":"purchase"},
        selectVal:"<s:property value="contract.inviteBidType"/>",//默认选中对象
        name:"inviteBidType",
        label:"NAME",
        value:"ID"
    });

    $("#inviteBidTypeDiv > select").bind("completed",function(){
        if($(this).index() == 0){
            if($(this).val() != "")
                $("#inviteBidTypeName").prepend($(this).find("option:selected").text())
        }
        if($(this).index() == 1){
            if($(this).val() != "")
                $("#inviteBidTypeName").append("-"+$(this).find("option:selected").text())
        }
    })
});

//显示工程量条目
function showQuantitiesList(){
	var url = "<%=basePath%>/quantitiesSubject/findQuantitiesSubjectByPage.action?contractId="+$("#contractId").val();
	window.open(url);
}

function addContractStatus(type){
	$("[id=addButton]").attr("disabled",true);
	var add_html = "<tr><td><input type='hidden' id='add_type' value='"+type+"'/></td>"+
				"<td><input type='text' id='add_reason' style='width:100%'/></td>"+
				"<td>"+$("#routeArea").html()+"</td>"+
				"<td><input type='text' id='add_money' style='width:100%'/></td>"+
				"<td><input type='text' id='add_persent' style='width:100%'/></td>"+
				"<td><input type='text' id='add_operateDate' style='width:100%' readonly='readonly'/></td>"+
				"<td><input type='text' id='add_remark' style='width:100%'/></td>"+
				"<td class='t_c'><input type='button' value='保存' onclick='saveAdd(this,"+type+");'/>&nbsp;&nbsp;<input type='button' value='取消' onclick='cancelAdd(this);'/></td></tr>";
	if(type=='1'){
		add_html ="<tr><td>"+
					"<input type='hidden' id='add_type' value='"+type+"'/>"+
					"<input type='text' id='add_flowNo' style='width:100%'/></td>"+
				"<td><select id='add_raisedCompany'>"+
				"<option value=''>---请选择---</option>"+
				"<option value='项目公司'>项目公司</option>"+
				"<option value='设计单位'>设计单位</option>"+
				"<option value='施工单位'>施工单位</option>"+
				"</select>"+
				"<td><input type='text' id='add_reason' style='width:100%'/></td>"+
				"<td><input type='text' id='add_money' style='width:100%'/></td>"+
				"<td><input type='text' id='add_persent' style='width:100%'/></td>"+
				"<td><input type='text' id='add_operateDate' style='width:100%'/></td>"+
				"<td><input type='text' id='add_remark' style='width:100%'/></td>"+
				"<td id='fileArea'><input type='file' name='uploadify' id='uploadify' />"+
					"<input type='button' value='上传' onclick='$(\"#uploadify\").uploadify(\"upload\",\"*\");'>"+
					"<input type='button' value='取消' onclick='$(\"#uploadify\").uploadify(\"stop\");'></td>"+
				"<td class='t_c'><input type='button' value='保存' onclick='saveAdd(this,"+type+");'/>&nbsp;&nbsp;<input type='button' value='取消' onclick='cancelAdd(this);'/></td></tr>";
		$("#tbodyChange").find("#sumTr").before(add_html);
		
		uploadFile();
	}else if(type=='2'){
		$("#tbodyPalnPay").find("#sumTr").before(add_html);
	}else if(type=='3'){
		$("#tbodyActualPay").find("#sumTr").before(add_html);
	}
	showDatepicker();
}



function saveAdd(obj,type){
	var tr = $(obj).parent().parent();
	var add_type = tr.find("#add_type").val();
	var add_reason = tr.find("#add_reason").val();
	var add_line = tr.find("#add_line").val();
	var add_money = tr.find("#add_money").val();
	if(add_money!=""&&add_money!="0"){
		add_money = parseFloat(add_money).toFixed(6);
	}
	var add_persent = tr.find("#add_persent").val();
	var add_operateDate = tr.find("#add_operateDate").val();
	var add_remark = tr.find("#add_remark").val();
	var add_flowNo = tr.find("#add_flowNo").val();
	var add_raisedCompany = tr.find("#add_raisedCompany").val();
	var attach = tr.find("input[name='attach']").val();
	
	if(add_money!=''&&!$.isNumeric(add_money)){
		alert("金额只能填数字！");
		$("#add_money").focus();
		return false;
	}
	
	if(add_persent!=''&&!$.isNumeric(add_persent)){
		alert("百分比只能填数字！");
		$("#add_persent").focus();
		return false;
	}
	
	var id = "<s:property value='#request.id'/>";
	var tab;
	if(type==1){
		tab = 1;
	}else if(type==2||type==3){
		tab = 2;
	}else{
		tab = 0;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/investCost/contractStatus/saveAdd.action",
		data:{
			type:add_type,
			reason:add_reason,
			line : add_line,
			money:add_money,
			persent:add_persent,
			operateDate:add_operateDate,
			remark:add_remark,
			contractId:$("#contractId").val(),
			add_flowNo:add_flowNo,
			add_raisedCompany:add_raisedCompany,
			attach:attach
		},
		error:function(){
				alert("系统连接失败，请联系管理员！");
			},
		success:function(object){
			alert("保存成功！");
			//window.top.location.reload()
			window.location.href = "/investCost/contract/showView.action?id="+id+"&tab="+tab;
		}
	});
}

function cancelAdd(obj){
	$(obj).parent().parent().remove();
	$("[id=addButton]").attr("disabled",false);
}

function showEdit(obj){
	$("input[id='addButton']").each(function(index){
		$(this).attr("disabled",true);
	});
	var tr = $(obj).parent().parent();
	var dateValue ="";
	if($(obj).parents("div").attr("id")=="changeArea"){
		$("tr[name='editArea']").each(function(){
			$(this).find("input[value='取消']").click();
			$(this).attr("name","");
		});
		tr.attr("name","editArea");
		for(var i=0;i<8;i++){
			var $targetTd =  tr.children("td:eq("+i+")");
			if(i==1){
				var addHtml = "<select id='"+$.trim($targetTd.attr('title'))+"' name='editChangeName'>"+
				"<option value=''>---请选择---</option>"+
				"<option value='项目公司'>项目公司</option>"+
				"<option value='设计单位'>设计单位</option>"+
				"<option value='施工单位'>施工单位</option></select>";
				$targetTd.html(addHtml);
				$("select[name='editChangeName']").children("option[value='"+$targetTd.attr("title")+"']").attr("selected",true);
			}else if(i==5){
				dateValue = $.trim(tr.children("td:eq("+i+")").html());
				tr.children("td:eq("+i+")").html("<input type='text' style='width:100%' name='operateDate_' id='"+$.trim(tr.children("td:eq("+i+")").html())+"' value='"+$.trim(tr.children("td:eq("+i+")").html())+"'/>");
			}else if(i==7){
				if($.trim($targetTd.html())=='无'){
					var addHtml = "<input type='file' name='uploadify' id='uploadify' />"+
					"<input type='button' value='上传' onclick='$(\"#uploadify\").uploadify(\"upload\",\"*\");'>"+
					"<input type='button' value='取消' onclick='$(\"#uploadify\").uploadify(\"stop\");'>";
					$targetTd.attr("id","fileArea");
					$targetTd.html(addHtml);
					uploadFile();
				}else{
					var addHtml = "<input type='hidden' name='upload' value='"+$targetTd.find("input[type='hidden']").val()+"'><input type='button' value='删除' onclick='removeFile(this)'>";
					$targetTd.html(addHtml);
				}
			}else{
				if($targetTd.attr("title")!=null && $targetTd.attr("title")!=""){		//流水号，变更事由、备注
					$targetTd.html("<input type='text' style='width:100%' id='"+$.trim($targetTd.attr("title"))+"' value='"+$.trim($targetTd.attr("title"))+"'/>");
				}else{
					tr.children("td:eq("+i+")").html("<input type='text' style='width:100%' id='"+$.trim(tr.children("td:eq("+i+")").html())+"' value='"+$.trim(tr.children("td:eq("+i+")").html())+"'/>");
				}
			}
		}
	}else{
		for(var i=1;i<7;i++){
			if(i==2){
				var $selectedObj;
				var $addTd = tr.children("td:eq("+i+")");
				var addLineHtml ="";
				var lineValue = $.trim(tr.children("td:eq("+i+")").html());
				$("#routeArea").find("select").find("option").each(function(){
					if($(this).val()==lineValue){
						$selectedObj = $(this);
						$(this).attr("selected",true);
					}
				});
				$addTd.html($("#routeArea").html());
				$selectedObj.attr("selected",false);
				$addTd.find("select").attr("id",lineValue);
			}else if(i==5){
				dateValue = $.trim(tr.children("td:eq("+i+")").html());
				tr.children("td:eq("+i+")").html("<input type='text' style='width:100%' name='operateDate_' id='"+$.trim(tr.children("td:eq("+i+")").html())+"' value='"+$.trim(tr.children("td:eq("+i+")").html())+"'/>");
			}else{
				var $targetTd =  tr.children("td:eq("+i+")");
				if($targetTd.attr("title")!=null && $targetTd.attr("title")!=""){		//变更事由、备注
					$targetTd.html("<input type='text' style='width:100%' id='"+$.trim($targetTd.attr("title"))+"' value='"+$.trim($targetTd.attr("title"))+"'/>");
				}else{
					tr.children("td:eq("+i+")").html("<input type='text' style='width:100%' id='"+$.trim(tr.children("td:eq("+i+")").html())+"' value='"+$.trim(tr.children("td:eq("+i+")").html())+"'/>");
				}
			}
		}
		$("input[name=operateDate_]").datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
		$("input[name=operateDate_]").attr("alias",$("input[name=operateDate_]").val());
	}
	showDatepickerEdit();
	//$("input[name=operateDate_]").attr("id",dateValue); //编辑之后，日期控件出错
	$(obj).parent().children("a").attr("style","display:none");
	$(obj).parent().children("input[type=button]").attr("style","display:inline");
}

function hideEdit(obj){
	$("input[id='addButton']").each(function(){
		$(this).attr("disabled",false);
	});
	var $tr = $(obj).parent().parent();
	var maxInputLength = $tr.find("input").length;
	if($(obj).parents("div").attr("id")=="changeArea"){
		if($tr.find("select").attr("id")==null || $tr.find("select").attr("id")=="undefined"){
			$tr.find("select").parent("td").html("");		
		}else{
			$tr.find("select").parent("td").html($tr.find("select").attr("id"));
		}
		$tr.find("input[type!=hidden]").each(function(index){
			if(index<(maxInputLength-2)){
				if(index==4){
					if($(this).attr("id").indexOf("-")<1){
						$tr.children("td:eq("+(index+1)+")").html("");
					}else{
						$tr.children("td:eq("+(index+1)+")").html($(this).attr("id"));
					}
				}else if(index==6){
						if($(this).siblings("input[type='hidden'][name='upload']").length<1){
							$tr.children("td:eq("+(index+1)+")").html("无");				
						}else{
							var addHtml = "<input type='hidden' value='"+$(this).siblings("input[type='hidden']").val()+"'>"+
								"<input type='button' value='下载' onclick='downloadFile("+$(this).siblings("input[type='hidden']").val()+")'>";
							$tr.children("td:eq("+(index+1)+")").html(addHtml);		
						}					
				}else if(index==7){
				
				}else{
						
					if(index>0){
						index = index+1;
					}
					if($(this).attr("id")!=null && $(this).attr("id")!="" && $(this).attr("id")!="undefined"){
						$tr.children("td:eq("+(index)+")").html($(this).attr("id"));
					}else{	
						$tr.children("td:eq("+(index)+")").html("");
					}
				}		
			}
		});
	}else{
		$tr.find("select").parent().html($tr.find("select").attr("id"));
		$tr.find("input").each(function(index){
			if(index<(maxInputLength-2)){
				if(index==0){
					if($(this).attr("id")!=null && $(this).attr("id")!="" && $(this).attr("id")!="undefined"){
						$tr.children("td:eq(1)").html($(this).attr("id"));
					}else{	
						$tr.children("td:eq(1)").html("");
					}
				}else if(index==3){
					if($(this).attr("alias").indexOf("-")<1){
						$tr.children("td:eq("+(index+2)+")").html("");
					}else{
						$tr.children("td:eq("+(index+2)+")").html($(this).attr("alias"));
					}
				}else{
					if($(this).attr("id")!=null && $(this).attr("id")!="" && $(this).attr("id")!="undefined"){
						$tr.children("td:eq("+(index+2)+")").html($(this).attr("id"));
					}else{	
						$tr.children("td:eq("+(index+2)+")").html("");
					}
					
				}		
			}
		});
	}
	
	$(obj).parent().children("a").attr("style","display:inline");
	$(obj).parent().children("input[type=button]").attr("style","display:none");
	enableShowChangeInShort();
	enableShowPayInShort();
}

function saveEdit(obj,id,type){
	var tr = $(obj).parent().parent();
	var reason ;
	var money;
	var persent;
	var operateDate;
	var remark ;
	var changeFlowNo="";
	var changeRaisedCompany="";
	var attach ="";
	if($(obj).parents("div").attr("id")=="changeArea"){
		attach = tr.children("td:eq(7)").find("input[type='hidden'][name!='remove']").val();
		changeFlowNo = tr.children("td:eq(0)").children("input").val();
		changeRaisedCompany = tr.children("td:eq(1)").children("select").val();
		reason = tr.children("td:eq(2)").children("input").val();
		money = tr.children("td:eq(3)").children("input").val();
		if(money!=""&&money!="0"){
			money = parseFloat(money).toFixed(6);
		}
		persent = tr.children("td:eq(4)").children("input").val();
		operateDate = tr.children("td:eq(5)").children("input").val();
		remark = tr.children("td:eq(6)").children("input").val();
		if(money!=''&&!$.isNumeric(money)){
			alert("金额只能填数字！");
			$("#add_money").focus();
			return false;
		}
		if(persent!=''&&!$.isNumeric(persent)){
			alert("百分比只能填数字！");
			$("#add_persent").focus();
			return false;
		}
	}else{
		reason = tr.children("td:eq(1)").children("input").val();
		var line = tr.children("td:eq(2)").children("select").val();
		money = tr.children("td:eq(3)").children("input").val();
		if(money!=""&&money!="0"){
			money = parseFloat(money).toFixed(6);
		}
		persent = tr.children("td:eq(4)").children("input").val();
		operateDate = tr.children("td:eq(5)").children("input").val();
		remark = tr.children("td:eq(6)").children("input").val();
		
		if(money!=''&&!$.isNumeric(money)){
			alert("金额只能填数字！");
			tr.children("td:eq(3)").children("input").focus();
			return false;
		}
		if(persent!=''&&!$.isNumeric(persent)){
			alert("百分比只能填数字！");
			tr.children("td:eq(4)").children("input").focus();
			return false;
		}
	}
	var tid = "<s:property value='#request.id'/>";
	var tab;
	if(type==1){
		tab = 1;
	}else if(type==2||type==3){
		tab = 2;
	}else{
		tab = 0;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/investCost/contractStatus/saveEdit.action",
		data:{
			reason:reason,
			line:line,
			money:money,
			persent:persent,
			operateDate:operateDate,
			remark:remark,
			id:id,
			changeFlowNo:changeFlowNo,
			changeRaisedCompany:changeRaisedCompany,
			attach:attach
		},
		error:function(){
				alert("系统连接失败，请联系管理员！");
			},
		success:function(object){
			alert("保存成功！");
			window.location.href = "/investCost/contract/showView.action?id="+tid+"&tab="+tab;
		}
	});
}

function deleteData(id,type){
	if(confirm("是否删除？")){
		var tid = "<s:property value='#request.id'/>";
		var tab;
		if(type==1){
			tab = 1;
		}else if(type==2||type==3){
			tab = 2;
		}else{
			tab = 0;
		}
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/investCost/contractStatus/deleteData.action",
			data:{
				id:id
			},
			error:function(){
					alert("系统连接失败，请联系管理员！");
				},
			success:function(object){
				alert("删除成功！");
				//window.top.location.reload()
				window.location.href = "/investCost/contract/showView.action?id="+tid+"&tab="+tab;
			}
		});
	}
}

function countMoney(obj){
	var moneyAll = 0;
	var persentAll = 0;
	$(obj).find("#moneyTd").each(function(){
		if($.trim($(this).html())!=""){
			moneyAll += parseFloat($(this).html());
		}
		if($.trim($(this).siblings("#persentTd").html())!=""){
			persentAll += parseFloat($(this).siblings("#persentTd").html());
		}
	});
	moneyAll = moneyAll.toFixed(6);
	persentAll = persentAll.toFixed(2);
	if(moneyAll==0.0000){
		moneyAll = 0;
	}
	if(persentAll==0.00){
		persentAll = 0;
	}
	$(obj).find("#moneyAll").html(moneyAll);
	$(obj).find("#persentAll").html(persentAll);
}

$(function(){
	countMoney($("#tbodyChange"));
	countMoney($("#tbodyPalnPay"));
	countMoney($("#tbodyActualPay"));
	var tab = $("#tab").val();
	if(tab!=""){
		$("#pageFoot").find("a:eq("+tab+")").click();
	}
	
	enableShowProgressInShort();
	enableShowPayInShort();
});


//合同进展情况，显示
function showProgressAdd(){
	$("#addProgressButton").attr("disabled",true);
	var html ="<tr>"+
		"<td></td>"+
		"<td id='addDept'><input style='width:100%' type='text' id='reportDeptName' value='<%=request.getAttribute("deptName")%>' maxlength='50' readonly='readonly'><input type='hidden' id='reportDeptId' value='<%=request.getAttribute("deptId")%>'></td>"+
		"<td id='addPerson'><input style='width:100%' type='text' id='reportPersonName' value='<%=request.getAttribute("userName")%>' maxlength='50' readonly='readonly'><input type='hidden' id='reportPersonId' value='<%=request.getAttribute("userId")%>'></td>"+
		//"<td><input style='width:100%' type='text' id='progressType' maxlength='500'></td>"+
		"<td><select name='progressType'><option value=''>--请选择---</option><option value='1'>风险</option></select></td>"+		
		"<td><input style='width:100%' type='text' id='time' readonly='readonly'></td>"+
		"<td><input style='width:100%' type='text' id='describe' maxlength='500'></td>"+
		"<td class='t_c'>"+
			"<a href='javascript:void(0);' onclick='showProgressEdit(this);' style='display:none;'>编辑</a>&nbsp;&nbsp;"+
			"<a href='javascript:void(0);' onclick='deleteProgress(this);' style='display:none;'>删除</a>&nbsp;&nbsp;"+
			"<input type='button' value='保存' style='display: inline;' onclick='saveProgress(this);'>&nbsp;&nbsp;"+
			"<input type='button' value='取消' style='display: inline;' onclick='hideProgressAdd(this);'>"+
		"</td></tr>";
	$("#progress").append(html);
	enableDatepicker("time");
}

//合同进展情况，显示编辑
function showProgressEdit(object){
	$("#addProgressButton").attr("disabled",true);
	var $targetTr = $(object).parent().parent();
	$(object).parent().find("input").attr("style","display:inline;");
	$(object).parent().find("a").hide();
	
	//填报部门
	var $targetTd = $targetTr.find("td:eq(1)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	var htmlValue = "<input style='width:100%' type='text' id='reportDeptName' readonly='readonly' maxlength='50' value='"+$.trim($targetTd.text())+"'/>";
	var objectValue = $targetTd.find("input:hidden");
	$targetTd.html(htmlValue);
	$targetTd.append(objectValue);
	//enableReportDept($targetTd);
	
	//填报人
	$targetTd = $targetTr.find("td:eq(2)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	htmlValue = "<input type='text' style='width:100%' id='reportDeptName' readonly='readonly' value='"+$.trim($targetTd.text())+"'/>";
	objectValue = $targetTd.find("input:hidden");
	$targetTd.html(htmlValue);
	$targetTd.append(objectValue);
	//enableReportPerson($targetTd);
	
	//进展类型
	$targetTd = $targetTr.find("td:eq(3)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	//htmlValue = "<input type='text' style='width:100%' id='progressType' value='"+$.trim($targetTd.text())+"'/>";
	if($.trim($targetTd.text())=='风险'){
		htmlValue = "<select name='progressType'><option value=''>--请选择---</option><option value='1' selected='selected'>风险</option></select>";
	}else{
		htmlValue = "<select name='progressType'><option value=''>--请选择---</option><option value='1'>风险</option></select>";
	}
	$targetTd.html(htmlValue);
	
	//时间
	$targetTd = $targetTr.find("td:eq(4)");
	$targetTd.attr("id",$.trim($targetTd.text()));
	htmlValue = "<input type='text' style='width:100%' id='timeAdd' value='"+$.trim($targetTd.text())+"'/>";
	$targetTd.html(htmlValue);
	//描述
	$targetTd = $targetTr.find("td:eq(5)");
	$targetTd.attr("id",$.trim($targetTd.attr("title")));
	htmlValue = "<input type='text' style='width:100%' id='describe' value='"+$.trim($targetTd.attr("id"))+"'/>";
	$targetTd.html(htmlValue);
	
	enableShowProgressInShort();
	
	enableDatepicker("timeAdd");
}

function hideProgressEdit(object){
	$("#addProgressButton").attr("disabled",false);
	$targetTr = $(object).parent().parent();
	$(object).parent().find("input").hide();
	$(object).parent().find("a").attr("style","display:inline;");
	
	//填报部门
	var $targetTd = $targetTr.find("td:eq(1)");
	var $hideDeptId = $targetTd.find("input:hidden"); 
	$targetTd.html($targetTd.attr("id"));
	$targetTd.append($hideDeptId);
	//填报人
	$targetTd = $targetTr.find("td:eq(2)");
	$hideDeptId = $targetTd.find("input:hidden"); 
	$targetTd.html($targetTd.attr("id"));
	$targetTd.append($hideDeptId);
	//进展类型
	$targetTd = $targetTr.find("td:eq(3)");
	if($targetTd.attr("id")==null || $targetTd.attr("id")=="" || $targetTd.attr("id")=="undefined"){ 
		$targetTd.html("");
	}else{
		$targetTd.html($targetTd.attr("id"));
	}
	
	//时间
	$targetTd = $targetTr.find("td:eq(4)");
	if($targetTd.attr("id")!=null && $targetTd.attr("id")!="undefined"){
		$targetTd.html($targetTd.attr("id"));
	}else{
		$targetTd.html("");
	}
	
	//描述
	$targetTd = $targetTr.find("td:eq(5)");
	if($targetTd.attr("id")==null || $targetTd.attr("id")=="" || $targetTd.attr("id")=="undefined"){ 
		$targetTd.html("");
	}else{
		$targetTd.html($targetTd.attr("id"));
	}
	enableShowProgressInShort();
}

//合同进展情况，保存新增
function saveProgress(object){
	var $targetTr = $(object).parent().parent();
	var $deptName = $targetTr.find("td:eq(1)").find("input:visible");
	var $deptId = $targetTr.find("td:eq(1)").find("input:hidden");
	var $personName = $targetTr.find("td:eq(2)").find("input:visible");
	var $personId = $targetTr.find("td:eq(2)").find("input:hidden");
	var tid = "<s:property value='#request.id'/>";
	$.ajax({
		url:"<%=basePath%>/progress/saveAddProgress.action?random="+Math.random(),
		dataType:"json",
		data:{
			reportDeptName:$.trim($deptName.val()),
			reportDeptId:$.trim($deptId.val()),
			reportPersonName:$.trim($personName.val()),
			reportPersonId:$.trim($personId.val()),
			progressType:$.trim($targetTr.find("td:eq(3)").find("select").val()),
			time:$.trim($targetTr.find("td:eq(4)").find("input").val()),
			describe:$.trim($targetTr.find("td:eq(5)").find("input").val()),
			objectType:'1',
			objectId:$("#contractId").val()
		},
		error:function(){
			alert("系统忙，请刷新后再试！");
		},
		success:function(){
			alert("保存成功！");
			//window.top.location.reload()
			window.location.href = "/investCost/contract/showView.action?id="+tid;
		}
	});
}

function saveProgressEdit(object,id){
	var $targetTr = $(object).parent().parent();
	var $deptName = $targetTr.find("td:eq(1)").find("input:visible");
	var $deptId = $targetTr.find("td:eq(1)").find("input:hidden");
	var $personName = $targetTr.find("td:eq(2)").find("input:visible");
	var $personId = $targetTr.find("td:eq(2)").find("input:hidden");
	var tid = "<s:property value='#request.id'/>";
	$.ajax({
		url:"<%=basePath%>/progress/saveEditProgress.action?random="+Math.random(),
		dataType:"json",
		data:{
			id:id,
			reportDeptName:$.trim($deptName.val()),
			reportDeptId:$.trim($deptId.val()),
			reportPersonName:$.trim($personName.val()),
			reportPersonId:$.trim($personId.val()),
			progressType:$.trim($targetTr.find("td:eq(3)").find("select").val()),
			time:$.trim($targetTr.find("td:eq(4)").find("input").val()),
			describe:$.trim($targetTr.find("td:eq(5)").find("input").val()),
			objectType:'1',
			objectId:$("#contractId").val()
		},
		error:function(){
			alert("系统忙，请刷新后再试！");
		},
		success:function(){
			alert("保存成功！");
			//window.top.location.reload()
			window.location.href = "/investCost/contract/showView.action?id="+tid;
		}
	});
}

//合同进展情况，删除
function deleteProgress(id){
	if(confirm("是否删除？")){
		var tid = "<s:property value='#request.id'/>";
		$.ajax({
			url:"<%=basePath%>/progress/deleteProgress.action?random="+Math.random(),
			dataType:"json",
			data:{
				id:id
			},
			error:function(){
				alert("系统忙，请刷新后再试！");
			},
			success:function(){
				alert("删除成功！");
				//window.top.location.reload()
				window.location.href = "/investCost/contract/showView.action?id="+tid;
			}
		});	
	}
}


//合同进展情况，隐藏
function hideProgressAdd(object){
	$("#addProgressButton").attr("disabled",false);
	$(object).parent().parent().remove();
}

//使日期控件生效,参数，控件id
function enableDatepicker(id){
	var $target = $("#"+id);
	if(!$.isEmptyObject($target)){
		$target.datepicker({
			inline: true,
			changeYear:true,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
	}
}

$(function(){
	var price = $.trim($("#originalPrice").text()).replace("万元","");
	if(price==null || price==""){
		price=0.0;
	}
	var changePrice = 0.0;
	$("#tbodyChange").find("#moneyTd").each(function(){
		changePrice += parseFloat($.trim($(this).text()));
	});
	var finalPrice = parseFloat(price)+changePrice;
	if(finalPrice==0) finalPrice = "0";
	else  finalPrice = parseFloat(finalPrice).toFixed(6);
	var addHtml = "<img src='"+getRootPath()+"/images/arrow_right.jpg' width='20px' height='15px' style='display:inline'/>&nbsp;"+finalPrice+"&nbsp;万元";
	$("#originalPrice").append(addHtml);
	
	enableShowChangeInShort();
});

//进展情况，缩略显示
function enableShowProgressInShort(){
	$("#progress").find("tr:gt(0)").each(function(){
		var $targetTd = $(this).find("td:eq(5)");
		if($.trim($targetTd.text()).length>20){
			$targetTd.html($targetTd.text().substring(0,30)+"...");
		}
	});
}

//变更情况，缩略显示
function enableShowChangeInShort(){
	$("td[id=changeReason]").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),20));
	});
	$("td[id=changeRemark]").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),30));
	});
}

//支付情况，缩略显示
function enableShowPayInShort(){
	$("#tbodyPalnPay").find("td[id='payReason']").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),20));
	});
	$("#tbodyPalnPay").find("td[id='payRemark']").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),30));
	});
	
	$("#tbodyActualPay").find("td[id='payReason']").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),20));
	});
	$("#tbodyActualPay").find("td[id='payRemark']").each(function(){
		$(this).text(thumbnailDisplay($(this).text(),30));
	});
}


//缩略显示,截取字符串并在最后加上"..."
function thumbnailDisplay(data,length){
	if(data==null || "undefined"==data || ""==data ){
		return data;
	}
	if(length==null || ""==length || parseInt(length)=="undefined"){
		length = 20;
	}
	if(data.length>length){
		data = data.substring(0,length)+"...";
	}
	return data;
}




//文件上传

function uploadFile(){
	$("#uploadify").uploadify({
       'auto' : false,
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '../js/uploadify.swf', 
       'uploader' : '<%=basePath%>/contractStatus/fileUpload.action',
       'fileTypeDesc' : '格式:txt,xls，xlsx,doc,docx,rar,zip,jpg,png,pdf',		//描述
       'fileTypeExts' : '*.txt;*.xls;*.xlsx;*.doc;*.docx;*.rar;*.zip;*.jpg;*.png;*.pdf',			//文件类型
       'fileSizeLimit' : '30000KB',			//文件大小
       'buttonText' : '选择文件',			//按钮名称
       'fileObjName'	:'uploadify',
       'successTimeout' : 5,
       'requeueErrors' : false,
       'removeTimeout' : 1,
       'removeCompleted' : true,
       'onSelectError' : function(file,errorCode,errorMsg) {
       		if(errorCode==-110){
       			this.queueData.errorMsg = "文件太大，无法上传！";
       		}
        }, 
       'onUploadSuccess' : function(file, data, response){
       		var attach = eval('(' + data + ')');
       		var addHtml = "<input type='hidden' name='attach' value='"+attach.id+"'><input type='button' value='下载' onclick='downloadFile("+attach.id+")'><input type='button' value='删除' onclick='removeFile(this)'>";
       		$("#uploadify").uploadify("destroy");
       		$("#fileArea").html(addHtml)
    	}
	});
}

function removeFile(obj){
	var $targetTd = $(obj).parent();
	var id = $targetTd.find("input[type='hidden']").val();
	$.ajax({
		url			: 	"<%=basePath%>/attach/deleteAttach.action?random="+Math.random(),
		dataType	: 	"json",
		type		:	'post',
		data: {
			id:id
		},
		error:function(){
			alert("系统连接失败请稍后再试！");
		},
		success: function( data ) {
			alert("删除成功！");
			var addHtml = "<input type='hidden' value='"+id+"' name='remove'><input type='file' name='uploadify' id='uploadify' />"+
					"<input type='button' value='上传' onclick='$(\"#uploadify\").uploadify(\"upload\",\"*\");'>"+
					"<input type='button' value='取消' onclick='$(\"#uploadify\").uploadify(\"stop\");'></td>";
			$targetTd.html(addHtml);
			$targetTd.attr("id","fileArea");
			uploadFile();
			/*	
			$target.parent().parent().remove();
			var trLength = $("#fileTable").find("tr").length;
			if(trLength==1){
				$("#fileTable").hide();
			}*/
		}
	});
}

function downloadFile(id){
	window.location = "<%=basePath%>/attach/downloadAttach.action?attachId="+id;
}

//跳转到变更事项的编辑页面
function showEditPage(id){
	sonWindow = window.open('<%=basePath%>/contractChangeItem/showEdit.action?id='+id);
}




</script>




</head>

<body>
<input type="hidden" value="<s:property value='#request.tab'/>" id="tab"/>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">工程投资造价管理</a></li>
	<li><a href="#">合同封面</a></li>
	<li><a href="#">合同管理</a></li>
	<li class="fin">合同详细</li>
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
<input type="hidden" value="<s:property value='contractId' />" id="contractId" >
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="工程量清单" onclick="showQuantitiesList();"/>
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd" width="20%;">合同名称</td>
				<td width="30%;" >
					<s:property value="contract.contractName"/>
				</td>
				<td class="t_r lableTd" width="20%;"><strong>合同编号</strong></td>
				<td width="30%;">
					<s:property value="contract.contractNo"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd" width="20%;"><strong>合同价(万元)</strong></td>
				<td width="30%;" id="originalPrice">
					<s:if test="contract.contractPrice==0">0&nbsp;万元</s:if>
					<s:elseif test="contract.contractPrice==''">0&nbsp;万元</s:elseif>
					<s:else>
						<s:if test="contract.contractPrice.indexOf('.')>=0">
							<s:if test="contract.contractPrice.indexOf('.')+7<=contract.contractPrice.length()">
								<s:property value='contract.contractPrice.substring(0,contract.contractPrice.indexOf(".")+7)'/>&nbsp;万元
							</s:if>
							<s:else>
								<s:if test="contract.contractPrice.length()-contract.contractPrice.indexOf('.')==2">
									<s:property value="contract.contractPrice"/>00000
								</s:if>
								<s:elseif test="contract.contractPrice.length()-contract.contractPrice.indexOf('.')==3">
									<s:property value="contract.contractPrice"/>0000
								</s:elseif>
								<s:elseif test="contract.contractPrice.length()-contract.contractPrice.indexOf('.')==4">
									<s:property value="contract.contractPrice"/>000
								</s:elseif>
								<s:elseif test="contract.contractPrice.length()-contract.contractPrice.indexOf('.')==5">
									<s:property value="contract.contractPrice"/>00
								</s:elseif>
								<s:elseif test="contract.contractPrice.length()-contract.contractPrice.indexOf('.')==6">
									<s:property value="contract.contractPrice"/>0
								</s:elseif>
							</s:else>
						</s:if>
						<s:else>
							<s:property value="contract.contractPrice"/>.000000						
						</s:else>
					</s:else>
				</td>
				<td class="t_r lableTd">审定价(投资监理)(万元)</td>
				<td>
					<s:property value="contract.finalPrice"/>
					<!-- 
					<s:if test="contract.finalPrice==0">0&nbsp;万元</s:if>
					<s:elseif test="contract.finalPrice==''">0&nbsp;万元</s:elseif>
					<s:else>
						<s:if test="contract.finalPrice.indexOf('.')>=0">
							<s:if test="contract.finalPrice.indexOf('.')+7<=contract.finalPrice.length()">
								<s:property value='contract.finalPrice.substring(0,contract.finalPrice.indexOf(".")+7)'/>&nbsp;万元
							</s:if>
							<s:else>
								<s:if test="contract..length()-contract.finalPrice.indexOf('.')==2">
									<s:property value="contract.finalPrice"/>00000
								</s:if>
								<s:elseif test="contract.finalPrice.length()-contract.finalPrice.indexOf('.')==3">
									<s:property value="contract.finalPrice"/>0000
								</s:elseif>
								<s:elseif test="contract.finalPrice.length()-contract.finalPrice.indexOf('.')==4">
									<s:property value="contract.finalPrice"/>000
								</s:elseif>
								<s:elseif test="contract.finalPrice.length()-contract.finalPrice.indexOf('.')==5">
									<s:property value="contract.finalPrice"/>00
								</s:elseif>
								<s:elseif test="contract.finalPrice.length()-contract.finalPrice.indexOf('.')==6">
									<s:property value="contract.finalPrice"/>0
								</s:elseif>
							</s:else>
						</s:if>
						<s:else>
							<s:property value="contract.finalPrice"/>.000000						
						</s:else>
					</s:else>
					 -->
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">审定价(审价单位)(万元)</td>
				<td>
					<s:property value="contract.verifyPrice"/>
					<!-- 
					<s:if test="contract.verifyPrice==0">0&nbsp;万元</s:if>
					<s:elseif test="contract.verifyPrice==''">0&nbsp;万元</s:elseif>
					<s:else>
						<s:if test="contract.verifyPrice.indexOf('.')>=0">
							<s:if test="contract.verifyPrice.indexOf('.')+7<=contract.verifyPrice.length()">
								<s:property value='contract.verifyPrice.substring(0,contract.verifyPrice.indexOf(".")+7)'/>&nbsp;万元
							</s:if>
							<s:else>
								<s:if test="contract.verifyPrice.length()-contract.verifyPrice.indexOf('.')==2">
									<s:property value="contract.verifyPrice"/>00000
								</s:if>
								<s:elseif test="contract.verifyPrice.length()-contract.verifyPrice.indexOf('.')==3">
									<s:property value="contract.verifyPrice"/>0000
								</s:elseif>
								<s:elseif test="contract.verifyPrice.length()-contract.verifyPrice.indexOf('.')==4">
									<s:property value="contract.verifyPrice"/>000
								</s:elseif>
								<s:elseif test="contract.verifyPrice.length()-contract.verifyPrice.indexOf('.')==5">
									<s:property value="contract.verifyPrice"/>00
								</s:elseif>
								<s:elseif test="contract.verifyPrice.length()-contract.verifyPrice.indexOf('.')==6">
									<s:property value="contract.verifyPrice"/>0
								</s:elseif>
							</s:else>
						</s:if>
						<s:else>
							<s:property value="contract.contractPrice"/>.000000						
						</s:else>
					</s:else>
					 -->
				</td>				
				<td class="t_r lableTd">审定价(国家审计)(万元)</td>
				<td>
					<s:property value="contract.nationVerifyPrice"/>
					<!-- 
					<s:if test="contract.nationVerifyPrice==0">0&nbsp;万元</s:if>
					<s:elseif test="contract.nationVerifyPrice==''">0&nbsp;万元</s:elseif>
					<s:else>
						<s:if test="contract.nationVerifyPrice.indexOf('.')>=0">
							<s:if test="contract.nationVerifyPrice.indexOf('.')+7<=contract.nationVerifyPrice.length()">
								<s:property value='contract.nationVerifyPrice.substring(0,contract.nationVerifyPrice.indexOf(".")+7)'/>&nbsp;万元
							</s:if>
							<s:else>
								<s:if test="contract.nationVerifyPrice.length()-contract.nationVerifyPrice.indexOf('.')==2">
									<s:property value="contract.nationVerifyPrice"/>00000
								</s:if>
								<s:elseif test="contract.nationVerifyPrice.length()-contract.nationVerifyPrice.indexOf('.')==3">
									<s:property value="contract.nationVerifyPrice"/>0000
								</s:elseif>
								<s:elseif test="contract.nationVerifyPrice.length()-contract.nationVerifyPrice.indexOf('.')==4">
									<s:property value="contract.nationVerifyPrice"/>000
								</s:elseif>
								<s:elseif test="contract.nationVerifyPrice.length()-contract.nationVerifyPrice.indexOf('.')==5">
									<s:property value="contract.nationVerifyPrice"/>00
								</s:elseif>
								<s:elseif test="contract.nationVerifyPrice.length()-contract.nationVerifyPrice.indexOf('.')==6">
									<s:property value="contract.nationVerifyPrice"/>0
								</s:elseif>
							</s:else>
						</s:if>
						<s:else>
							<s:property value="contract.nationVerifyPrice"/>.000000						
						</s:else>
					</s:else>
					 -->
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">甲方(出资)(万元)</td>
				<td colspan="3">
					<input type="hidden" id="contractOwnerName" value="<s:property value="contract.contractOwnerName"/>"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">甲方(执行)</td>
				<td>
					<s:property value="contract.contractOwnerExecuteName"/>
				</td>
				<td class="t_r lableTd">对方单位</td>
				<td>
					<s:property value="contract.buildSupplierName"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">支付方式</td>
				<td>
					<s:property value="contract.payType"/>
				</td>
				<td class="t_r lableTd">合同签约时间</td>
				<td colspan="3">
					<s:property value='contract.contractSignedDate'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同审批通过时间</td>
				<td>
					<s:property value='contract.contractPassedDate'/>
				</td>
				<td class="t_r lableTd">合同期限</td>
				<td>
					<s:property value='contract.contractStartDate'/>
					<s:if test="contract.contractStartDate!=null && contract.contractEndDate!=null">
						&nbsp;&nbsp;至&nbsp;&nbsp;
					</s:if>
					<s:property value='contract.contractEndDate'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同状态</td>
				<td>
					<s:if test="contract.contractStatus==0">已备案</s:if>
					<s:elseif test="contract.contractStatus==1">实施中</s:elseif>
					<s:elseif test="contract.contractStatus==2">已竣工</s:elseif>
					<s:elseif test="contract.contractStatus==3">已销号</s:elseif>
					<s:elseif test="contract.contractStatus==4">过程中</s:elseif>
					<s:elseif test="contract.contractStatus==5">已取消</s:elseif>
                    <s:elseif test="contract.contractStatus==6">已终止</s:elseif>
				</td>
				<td class="t_r lableTd">合同分组</td>
				<td colspan="3">
					<s:if test="contract.contractGrouping==1">
						成本内
					</s:if>
					<s:elseif test="contract.contractGrouping==2">
						成本外
					</s:elseif>
					<s:elseif test="contract.contractGrouping==3">
						无
					</s:elseif>
				</td> 
			</tr>
			<tr>
				<td class="t_r lableTd">自有编号</td>
				<td><s:property value="contract.selfNo"/></td>
				<td class="t_r lableTd">合同销号时间</td>
				<td><s:property value="contract.contractDestoryDate"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">
					采购方式
				</td>
				<td>
                    <div id="inviteBidTypeDiv" style="display:none;">

                    </div>
                    <div id="inviteBidTypeName">

                    </div>
					<%--<s:if test="contract.inviteBidType==1">--%>
						<%--公开招标--%>
					<%--</s:if>--%>
					<%--<s:elseif test="contract.inviteBidType==2">--%>
						<%--采购平台--%>
					<%--</s:elseif>--%>
					<%--<s:elseif test="contract.inviteBidType==3">--%>
						<%--直接委托--%>
					<%--</s:elseif>--%>
				</td>
								<td class="t_r lableTd">合同登记人</td>
				<td>
					<s:property value='contract.registerPersonName'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同分类</td>
				<td>
					<s:if test="contract.contractType!=null && contract.contractType!=''">	
						<s:if test="contract.contractType.substring(0,1)==1">建设类
							-
							<s:if test="contract.contractType.substring(2,3)==1">工程类</s:if>
							<s:elseif test="contract.contractType.substring(2,3)==2">勘察类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==3">设计类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==4">前期类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==5">其他类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==6">监理类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==7">科研类</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==8">咨询类</s:elseif>
						</s:if>
						<s:elseif test="contract.contractType.substring(0,1)==2">运维类
							-
							<s:if test="contract.contractType.substring(2,3)==1">服务</s:if>
							<s:elseif test="contract.contractType.substring(2,3)==2">工程</s:elseif>
							<s:elseif test="contract.contractType.substring(2,3)==3">货物</s:elseif>
						</s:elseif>
						<s:elseif test="contract.contractType.substring(0,1)==3">其他类-其他类</s:elseif>
					</s:if>
				</td>
				<td class="t_r lableTd">合同属性(建设类)</td>
				<td>
				<span name="contractAttributeDescription" style="display: none;"><s:property value='#request.contract.contractAttributeId'/>-<s:property value='#request.contract.contractAttribute'/></span>

				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">合同附件</td>
				<td colspan="3">
					<s:if test="#request.attachList==null || #request.attachList.size()==0">
						无
					</s:if>
					<s:else>
						<table id="fileTable">
							<tbody style="width: 550px;border: solid;border-color: #D0D0D3;" id="fileBody">
								<tr style="border: solid;border: #D0D0D3;">
									<td width="200px;" class="t_c">文件名</td>
									<td width="100px;" class="t_c">大小(KB)</td>
									<td width="150px;" class="t_c">上传时间</td>
									<td width="50px;" class="t_c">操作</td>
								</tr>
								<s:iterator id="attach" value="#request.attachList">
									<s:if test="#attach.status!=null && #attach.status=='eam'">
										<tr>
											<td class="t_l">
												<a href="javascript:void(0);" onclick="window.open('<s:property value="#attach.path"/>')">
													<s:property value="#attach.filename"/>.<s:property value="#attach.fileextname"/>
												</a>
											</td>
											<td class="t_r">
												<s:if test="(#attach.filesize/1024.0+'').substring((#attach.filesize/1024.0+'').indexOf('.'),(#attach.filesize/1024.0+'').length()).length()<=3">
													<s:property value="#attach.filesize/1024.0" />
												</s:if>
												<s:else>
													<s:property value="(#attach.filesize/1024.0+'').substring(0,(#attach.filesize/1024.0+'').indexOf('.')+3)"/>
												</s:else>
											</td>
											<td class="t_c"><s:property value="#attach.uploaddate"/></td>
											<td class="t_c">
												<a href="javascript:void(0);" onclick="window.open('<s:property value="#attach.path"/>')">下载</a>
											</td>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<td class="t_l">
												<a href="<%=basePath%>/attach/downloadAttach.action?attachId=<s:property value='#attach.id'/>">
													<s:property value="#attach.filename"/>.<s:property value="#attach.fileextname"/>
												</a>
											</td>
											<td class="t_r">
												<s:if test="(#attach.filesize/1024.0+'').substring((#attach.filesize/1024.0+'').indexOf('.'),(#attach.filesize/1024.0+'').length()).length()<=3">
													<s:property value="#attach.filesize/1024.0" />
												</s:if>
												<s:else>
													<s:property value="(#attach.filesize/1024.0+'').substring(0,(#attach.filesize/1024.0+'').indexOf('.')+3)"/>
												</s:else>
											</td>
											<td class="t_c"><s:property value="#attach.uploaddate"/></td>
											<td class="t_c">
												<a href="<%=basePath%>/attach/downloadAttach.action?attachId=<s:property value='#attach.id'/>">下载</a>
											</td>
										</tr>
									</s:else>
								</s:iterator>
							</tbody>
						</table>					
					</s:else>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">合同内容</td>
				<td colspan="3" style="word-break:break-all;white-space: normal;">
					<s:property value='contract.contractContent'/>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">项目编号</td>
				<td>
					<s:property value='#request.contract.projectNo'/>
				</td>
				<td class="t_r lableTd">项目名称</td>
				<td>
					<s:property value='#request.contract.projectName'/>
				</td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3" style="word-break:break-all;white-space: normal;">
					<s:property value='contract.remark'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">评估次数</td>
				<td colspan="3" style="word-break:break-all;white-space: normal;">
					<s:property value='contract.evaluateNum'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">预算类型</td>
				<td>
					<s:property value='#request.contract.budgetType'/>
				</td>
				<td class="t_r lableTd">预算类型编码</td>
				<td>
					<s:property value='#request.contract.budgetTypeCode'/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">统计类型</td>
				<td>
					<!-- <s:property value='#request.contract.statType'/> -->
					<s:if test="#request.contract.statType==1">大修更新</s:if>
					<s:if test="#request.contract.statType==2">维修费</s:if>
					<s:if test="#request.contract.statType==3">管理费</s:if>
					<s:if test="#request.contract.statType==4">运营间接费</s:if>
					<s:if test="#request.contract.statType==5">内部人工费</s:if>
					<s:if test="#request.contract.statType==6">委外人工经费</s:if>
					<s:if test="#request.contract.statType==7">收入</s:if>
					<s:if test="#request.contract.statType==8">其他直接费</s:if>
					<s:if test="#request.contract.statType==9">监测支出</s:if>
					<s:if test="#request.contract.statType==10">其他</s:if>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="工程量清单" onclick="showQuantitiesList();"/>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
function showDetail(target,type){
	$(target).parent().siblings().removeClass("selected");
	$(target).parent().addClass("selected");
	$("#pageFoot").nextAll().hide();
	if(type=='1'){
		$("#progressArea").show();
	}else if(type=='2'){
		$("#changeArea").show();
	}else if(type=='3'){
		$("#planPayArea").show();
		$("#actualPayArea").show();
	}else if(type=='4'){
		$("#kpiStatus").show();
	}else if(type=='5'){
		$("#supplementaryContract").show();
	}else if(type=='6'){
		$("#changeContract").show();
	}
}

function viewDetail(id){
	$.ajax({
		url: '<%=basePath%>/contract/viewKpiClear.action',
		data: {'id':id},
		type: 'post',
		dataType: 'json',
		async: true,
		success: function(data){
			var tmpHtml = "";
			for(var i=0; i<data.length; i++){
				if(!data[i].opinion){
					continue;
				}
				tmpHtml += "<span>"+data[i].userName+"--"+data[i].date+"<br/>"
					+"<font>&nbsp;&nbsp;"+data[i].opinion+"</font></span>";
			}
			$("#opinionText").html(tmpHtml);
			
			var attachs = data[data.length-1].attachs;
			$("#opinionFileBody").empty();
			for(var i=0; i<attachs.length; i++){
				var attach = attachs[i];
				var filename = attach.filename;
				if(filename.length >= 10){
					filename = filename.substr(0,8)+"...";
				}
				var addHtml = "<tr style='border: solid 1px #D0D0D3;'>"+
					"<td class='t_l' style='width:150px;'>"+
						"<a href='<%=basePath%>/attach/downloadAttach.action?attachId="+attach.id+"'>"+filename+"</a>"+
					"</td>"+
					"<td class='t_c' style='width:50px;'>"+attach.fileextname+
					"<td class='t_r' style='width:50px;'>"+(parseFloat(attach.filesize)/1024+'').substring(0,(parseFloat(attach.filesize)/1024+'').indexOf('.')+3)+"</td>"+
					"<td class='t_c' style='width:150px;'>"+attach.uploaddate+"</td>"+
				  "</tr>";
				$("#opinionFileBody").append(addHtml);
			}
		}
	});
	var dialogOpts = {
	    bgiframe: true,
	    resizable: true,
	    width:500,
	    height:450,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        '取消': function(){
	            $(this).dialog('close');
	        }
	    }
	};
	$("#opinionDialog").dialog(dialogOpts);
}


</script>

<div class="tabs_2" id="pageFoot">
	<ul>
		<li class="selected"><a href="javascript:void(0)" onclick="showDetail(this,'1');"><span>进展情况</span></a></li>
		<li><a href="javascript:void(0)" onclick="showDetail(this,'2');"><span>变更情况</span></a></li>
		<li><a href="javascript:void(0)" onclick="showDetail(this,'3');"><span>支付情况</span></a></li>
		<li><a href="javascript:void(0)" onclick="showDetail(this,'4');"><span>KPI状态</span></a></li>
		<li><a href="javascript:void(0)" onclick="showDetail(this,'5');"><span>变更事项</span></a></li>
		<li><a href="javascript:void(0)" onclick="showDetail(this,'6');"><span>变更协议</span></a></li>
	</ul>
</div>
<div style="display: none;" id="routeArea">
	<select name="line" id="add_line">
		<option value="">---请选择---</option>
		<s:iterator id="route" value="#request.routes">
			<option value="<s:property value="#route.objectName"/>"><s:property value="#route.objectName"/></option>
		</s:iterator>
	</select>
</div>
<div class="mb10" id="progressArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;合同进展情况</span>
					<span style="float:right">
						<input type="button" id="addProgressButton" value="新增" onclick="showProgressAdd();"/>
					</span>
				</th>
			</thead>
			<tbody id="progress">
				<tr class="tit">
					<td width="5%;" class="t_c">序号</td>
					<td width="20%;" class="t_c" >填报部门</td>
					<td width="10%;" class="t_c" >填报人</td>
					<td width="10%;" class="t_c" >进展类型</td>
					<td width="10%;" class="t_c" >时间</td>
					<td width="35%;" class="t_c" >描述</td>
					<td width="10%;" class="t_c" colspan="3">操作</td>
				</tr>
				<s:iterator value="#request.progressList" id="list" status="st">
					<tr>
						<td class="t_c"><s:property value="#st.index+1"/></td>
						<td class="t_c">
							<input type="hidden" value="<s:property value='#list.reportDeptId'/>" id="reportDeptId">
							<s:property value="#list.reportDeptName"/>
						</td>
						<td class="t_l">
							<input type="hidden" value="<s:property value='#list.reportDeptName'/>" id="reportDeptName">
							<s:property value="#list.reportPersonName"/>
						</td>
						<td class="t_l">
							<s:if test="#list.progressType==1">风险</s:if>
							<s:else><s:property value="#list.progressType"/></s:else>
						</td>						
						<td class="t_c"><s:property value="#list.time"/></td>
						<td class="t_l" title="<s:property value="#list.describe"/>"><s:property value="#list.describe"/></td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showProgressEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteProgress('<s:property value='#list.id'/>')" style="display:inline;">删除</a>&nbsp;&nbsp;
							<input type="button" value="保存" style="display: none;" onclick="saveProgressEdit(this,'<s:property value="#list.id"/>');">
							<input type="button" value="取消" style="display: none;" onclick="hideProgressEdit(this);">
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>

<div class="mb10" style="display: none;" id="changeArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="11">
					<span style="float:left">&nbsp;&nbsp;合同变更情况</span>
					<span style="float:right">
						<input type="button" id="addButton" value="新增" onclick="addContractStatus('1');"/>
					</span>
				</th>
			</thead>
			<tbody id="tbodyChange">
				<tr class="tit">
					<td width="10%;" class="t_c" style="white-space:nowrap">变更流水号</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">变更提出方</td>
					<td width="15%;" class="t_c" style="white-space:nowrap">变更原因</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">变更金额(万元)</td>
					<td width="5%;" class="t_c" style="white-space:nowrap">百分比(%)</td>
					<td width="15%;" class="t_c" style="white-space:nowrap">变更审批时间</td>
					<td width="15%;" class="t_c" style="white-space:nowrap" >备注</td>
					<td width="10%;" class="t_c" style="white-space:nowrap" >附件</td>
					<td width="10%;" class="t_c" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.list_change" id="list" status="st">
					<tr>
						<td class="t_c" style="white-space: nowrap;" id="changeFlowNo" title="<s:property value="#list.changeFlowNo"/>"><s:property value="#list.changeFlowNo"/></td>
						<td class="t_l" style="white-space: nowrap;" id="changeRaisedCompany" title="<s:property value="#list.changeRaisedCompany"/>">
							<s:property value="#list.changeRaisedCompany"/>
						</td>
						<td class="t_l" style="white-space: nowrap;" id="reasonTd">
							<s:property value="#list.reason"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="moneyTd">
							<s:property value="#list.money"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="persentTd">
							<s:property value="#list.persent"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<s:property value="#list.operateDate"/>
						</td>
						<td class="t_l" style="white-space: nowrap;" id="changeRemark" title="<s:property value="#list.remark"/>">
							<s:property value="#list.remark"/>
						</td>
						<td class="t_c" style="white-space: nowrap;" title="<s:property value="#list.remark"/>">
							<s:if test="#list.attach!=null && #list.attach!=''">
								<input type="hidden" value="<s:property value='#list.attach'/>">
								<input type="button" value="下载" onclick="downloadFile(<s:property value='#list.attach'/>)">
							</s:if>
							<s:else>无</s:else>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<a href="javascript:void(0);" onclick="showEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteData('<s:property value="#list.id"/>','1');" style="display:inline;">删除</a>&nbsp;&nbsp;
							<input type="button" value="保存" style="display: none;" onclick="saveEdit(this,'<s:property value="#list.id"/>','1');">
							<input type="button" value="取消" style="display: none;" onclick="hideEdit(this);">
						</td>
					</tr>
				</s:iterator>
				<tr id="sumTr">
					<td colspan="2"></td>
					<td class="t_c" style="white-space: nowrap;font-weight: bold;">总计</td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="moneyAll"></td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="persentAll"></td>
					<td colspan="4"></td>
				</tr>
			</tbody>
			<tr class="tfoot">
			      <td colspan="11">
			      </td>
		     </tr>
		</table>
		
		</div>
		
<div class="mb10" style="display: none;" id="planPayArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;合同计划支付情况</span>
					<span style="float:right">
						<input type="button" id="addButton" value="新增" onclick="addContractStatus('2');"/>
					</span>
				</th>
			</thead>
			<tbody id="tbodyPalnPay">
				<tr class="tit">
					<td width="5%;" style="white-space:nowrap">序号</td>
					<td width="20%;" class="t_c" style="white-space:nowrap">事由</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">线路</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">金额(万元)</td>
					<td width="5%;" class="t_c" style="white-space:nowrap">百分比(%)</td>
					<td width="15%;" class="t_c" style="white-space:nowrap">支付时间</td>
					<td width="25%;" class="t_c" style="white-space:nowrap" >备注</td>
					<td width="10%;" class="t_c" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.list_plan_pay" id="list" status="st">
					<tr>
						<td class="t_c" style="white-space: nowrap;"><s:property value="#st.index+1"/></td>
						<td class="t_l" style="white-space: nowrap;" id="payReason" title="<s:property value="#list.reason"/>">
							<s:property value="#list.reason"/>
						</td>
						<td class="t_c" style="white-space: nowrap;" id="lineTd">
							<s:property value="#list.line"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="moneyTd">
							<s:property value="#list.money"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="persentTd">
							<s:property value="#list.persent"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<s:property value="#list.operateDate"/>
						</td>
						<td class="t_l" style="white-space: nowrap;" id="payRemark" title="<s:property value="#list.remark"/>">
							<s:property value="#list.remark"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<a href="javascript:void(0);" onclick="showEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteData('<s:property value="#list.id"/>','2');" style="display:inline;">删除</a>&nbsp;&nbsp;
							<input type="button" value="保存" style="display: none;" onclick="saveEdit(this,'<s:property value="#list.id"/>','2');">
							<input type="button" value="取消" style="display: none;" onclick="hideEdit(this);">
						</td>
					</tr>
					
				</s:iterator>
				<tr id="sumTr">
					<td></td>
					<td></td>
					<td class="t_c" style="white-space: nowrap;font-weight: bold;">计划累计支付</td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="moneyAll"></td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="persentAll"></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>
<div class="mb10" style="display: none;" id="actualPayArea">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;合同实际支付情况</span>
					<span style="float:right">
						<input type="button" id="addButton" value="新增" onclick="addContractStatus('3');"/>
					</span>
				</th>
			</thead>
			<tbody id="tbodyActualPay">
				<tr class="tit">
					<td width="5%;" style="white-space:nowrap">序号</td>
					<td width="20%;" class="t_c" style="white-space:nowrap">事由</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">线路</td>
					<td width="10%;" class="t_c" style="white-space:nowrap">金额(万元)</td>
					<td width="5%;" class="t_c" style="white-space:nowrap">百分比(%)</td>
					<td width="15%;" class="t_c" style="white-space:nowrap">支付时间</td>
					<td width="25%;" class="t_c" style="white-space:nowrap" >备注</td>
					<td width="10%;" class="t_c" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.list_actual_pay" id="list" status="st">
					<tr>
						<td class="t_c" style="white-space: nowrap;"><s:property value="#st.index+1"/></td>
						<td class="t_l" style="white-space: nowrap;" id="payReason" title="<s:property value="#list.reason"/>">
							<s:property value="#list.reason"/>
						</td>
						<td class="t_c" style="white-space: nowrap;" id="lineTd">
							<s:property value="#list.line"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="moneyTd">
							<s:property value="#list.money"/>
						</td>
						<td class="t_r" style="white-space: nowrap;" id="persentTd">
							<s:property value="#list.persent"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<s:property value="#list.operateDate"/>
						</td>
						<td class="t_l" style="white-space: nowrap;" id="payRemark" title="<s:property value="#list.remark"/>">
							<s:property value="#list.remark"/>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<a href="javascript:void(0);" onclick="showEdit(this);" style="display:inline;">编辑</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteData('<s:property value="#list.id"/>','3');" style="display:inline;">删除</a>&nbsp;&nbsp;
							<input type="button" value="保存" style="display: none;" onclick="saveEdit(this,'<s:property value="#list.id"/>','3');">
							<input type="button" value="取消" style="display: none;" onclick="hideEdit(this);">
						</td>
					</tr>
				</s:iterator>
				<tr id="sumTr">
					<td></td>
					<td></td>
					<td class="t_c" style="white-space: nowrap;font-weight: bold;">实际累计支付</td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="moneyAll"></td>
					<td class="t_r" style="white-space: nowrap;font-weight: bold;" id="persentAll"></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>
<div class="mb10" style="display: none;" id="kpiStatus">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;KPI状态</span>
				</th>
			</thead>
			<tbody id="progress">
				<tr class="tit">
					<td width="30%;" class="t_c" >KPI风险</td>
					<td width="30%;" class="t_c" >状态</td>
					<td width="30%;" class="t_c" colspan="3">操作</td>
				</tr>
				<s:if test="#request.kpiStatus.size==0">
					<tr>
						<td class="t_l">
							先执行后签订
						</td>
						<td class="t_l">
							待处理
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="viewDetail('<s:property value='contract.id'/>')" style="display:inline;">查看详情</a>&nbsp;&nbsp;
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="#request.kpiStatus" id="kpi">
						<tr>
							<td class="t_l">
								先执行后签订
							</td>
							<td class="t_l">
								<s:if test="#kpi.status=='0'">已销缺</s:if>
								<s:if test="#kpi.status=='1'">待处理</s:if>
								<s:if test="#kpi.status=='3'">已退回</s:if>
							</td>
							<td class="t_c">
								<a href="javascript:void(0);" onclick="viewDetail('<s:property value='contract.id'/>')" style="display:inline;">查看详情</a>&nbsp;&nbsp;
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
			<tr class="tfoot">
			      <td colspan="10">
			      </td>
		     </tr>
		</table>
</div>


<div class="mb10" style="display: none;" id="supplementaryContract">
    <iframe height="600"  frameborder="0" border="0" marginwidth="0" marginheight="0" scrolling="no"  width="100%"
    src="<%=basePath%>/contractChangeItem/changeItems.action?contractId=<s:property value='contract.id' />">
    
    </iframe>
		
</div>

<div class="mb10" style="display: none;" id="changeContract">
    <iframe height="600"  frameborder="0" border="0" marginwidth="0" marginheight="0" scrolling="no"  width="100%"
  	  src="<%=basePath%>/contractChangeProtocol/protocols.action?contractId=<s:property value='contract.id' />">
    
    </iframe>
		
</div>

<div id="opinionDialog" style="display: none;">
   	<div>
   		双方交流意见：
   		<div id="opinionText" style="width:460px;height:200px;border:2px solid #D0D0D3; overflow: auto;">
   		</div>
   	</div>
  	<br/>
   	<div>
   		附件列表：
		<table id="opinionFileTable" style="border: solid 2px;border: #D0D0D3;">
			<thead>
				<tr style="border: solid 2px;border: #D0D0D3;">
					<td width="150px;" class="t_c">附件名称</td>
					<td width="100px;" class="t_c">附件类型</td>
					<td width="60px;" class="t_c">大小(KB)</td>
					<td width="100px;" class="t_c">上传时间</td>
				</tr>
			</thead>
			<tbody style="border: solid 2px;border-color: #D0D0D3;" id="opinionFileBody">
			</tbody>
		</table>
	</div>
</div>

<div>
    <jsp:include page="contract_attribute_tree.jsp"></jsp:include>
</div>
<!--Table End--></div>
</body>
</html>
