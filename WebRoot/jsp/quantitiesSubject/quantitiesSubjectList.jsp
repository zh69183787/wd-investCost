<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en" >
<head>
<meta charset="utf-8" />
<title>工程量条目管理</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/flick/jquery-ui-1.8.18.custom.css" />
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
	
	tr td{
		white-space:nowrap;
	}
	#listTitle td{
		background-color: #F0F0F3;
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
<script src="../js/wbs_common.js"></script>
<%String basePath = request.getContextPath(); %>
<script type="text/javascript">
var showAddStatus = false;
var inCalculate = false;
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
		
});

//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name=showOrHide]").val();
	$li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$("#searchArea").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$li.removeClass("selected");
		$("#searchArea").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
}        
</script>
 
<script type="text/javascript">
//删除
function deleteData(id){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统忙，请刷新页面后重新操作！")
			},
			success:function(object){
				queryContract();
			}
		});
	}
}

//找到节点下的最后一个子孙节点
function findLastChild(id){
	//var num = $("#tbody").find("input[id=hide_parentId][value="+id+"]").length;
	var num = $.find("input[id=hide_parentId][value="+id+"]").length;
	var tr;
	if(num!=0){
		id = findLastChild($("input[id=hide_parentId][value="+id+"]:eq("+(num-1)+")").parents("tr").find("#hide_id").val());
	}
	return id;
}

//保存新增
function saveOnPage(object,type){
	
	var wbsId = $("input[name=wbsId_add]").val();
	var subjectNo = $("input[name=subjectNo_add]").val();
	var subjectName = $("input[name=subjectName_add]").val();
	var subjectType = $("#subjectType_add").val();
	var unitPrice = $("input[name=unitPrice_add]").val();
	var amount = $("input[name=amount_add]").val();
	var totalPrice = $("input[name=totalPrice_add]").val();
	var acceptCondition = $("input[name=acceptCondition_add]").val();
	var subjectStatus = $("input[name=subjectStatus_add]").val();
	var ifHasSpecial = $("#ifHasSpecial").val();//是否是特殊行
	var specialOrder = $("#specialOrder").val();
	var $previousTargetTr = $(object).parents("tr").prev();
	var contractPrice = parseFloat($("#contractPrice").val())*10000;		//单位：元
	$("#targetNode").val($previousTargetTr.find("input[name=checkbox]").val());
	
	if($.trim(wbsId)==""){
		alert("WBS编码不能为空！");
		$("input[name=wbsId_add]").focus();
		return false;	
	}
	/*
	if($.trim(subjectNo)==""){
		alert("条目编号不能为空！");
		$("input[name=subjectNo_add]").focus();
		return false;	
	}*/
	if(subjectName=="科目名称"){
		subjectName="";
	}
	if($.trim(subjectName)==""){
		alert("条目名称不能为空！");
		$("input[name=subjectName_add]").focus();
		return false;	
	}
	
	if(unitPrice!="" && !$.isNumeric(unitPrice)){
		alert("请输入正确的数字！");
		$("input[name=unitPrice_add]").focus();
		return false;
	}
	if(amount!="" && !$.isNumeric(amount)){
		alert("请输入正确的数字！");
		$("input[name=amount_add]").focus();
		return false;
	}else{
		if(amount=="") amount="0";
	}
	
	if(totalPrice!="" && !$.isNumeric(totalPrice)){
		alert("请输入正确的数字！");
		$("input[name=totalPrice_add]").focus();
		return false;
	}
	if(parseFloat(totalPrice) > contractPrice){
		if(!confirm("该工程量条目的金额已超过合同金额,是否保存？")){
			$("input[name=totalPrice]").focus();
			return false;
		}
	}
	
	$(object).attr("disabled","disabled");
	$(object).siblings("input").attr("disabled","disabled");

	var parentId,level,order; 
	parentId = $("#parentId_save").val();
	level = $("#level_save").val();
	order = $("#order_save").val();
	if(parentId==null || parentId=="" || parentId=="undefined"){
		parentId = 0;
	}
	if(level==null || level=="" || level=="undefined"){
		level= 1;
	}
	if(order==null || order=="" || order=="undefined"){
		order = 1;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveAddOnPage.action",
		data:{
			wbsId:wbsId,
			subjectNo:subjectNo,
			subjectName:subjectName,
			subjectType:subjectType,
			unitPrice:unitPrice,
			amount:amount,
			totalPrice : totalPrice,
			acceptCondition :acceptCondition,
			subjectStatus :subjectStatus,
			contractId:$("#contractId").val(),
			parentId:parentId,
			level:level,
			order:order,
			dataType:$("#dataType").val(),
			ifHasSpecial:ifHasSpecial,
			specialOrder:specialOrder
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
			},
		success:function(object){
			if(object!=null){
				var html= ""+
				"<tr id='show_common' class='on' style=\"background-color:rgb(226, 231, 243)\">"+
							"<td class='t_c' style='white-space: nowrap;'>"+
								"<input type='hidden' id='hide_id' value="+object.wbs.id+">"+
								"<input type='hidden' id='hide_parentId' value="+object.wbs.parentId+">"+
								"<input type='hidden' id='hide_level' value="+object.wbs.wbsLevel+">"+
								"<input type='hidden' id='hide_order' value="+object.wbs.wbsOrder+">"+
								"<input type='checkbox' name='checkbox' value="+object.quantitiesSubject.id+">&nbsp;"+
							"</td>"+
							"<td class='t_l' style='white-space: nowrap;'>"+
								"<p style='display: inline;'>"+
									"<font style='display: inline;'>";
				if(object.wbs.wbsLevel=="2"){
					html += "&nbsp;&nbsp;";
				}else if(object.wbs.wbsLevel=="3"){
					html += "&nbsp;&nbsp;&nbsp;&nbsp;";
				}else if(object.wbs.wbsLevel=="4"){
					html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}else if(object.wbs.wbsLevel=="5"){
					html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
					html +="&nbsp;&nbsp;&nbsp;&nbsp;";
						html += "</font>"+
									object.wbs.wbsId+
								"</p>"+
							"</td>"+
							"<td class='t_l' style='white-space: nowrap;'>"+object.quantitiesSubject.subjectNo+"</td>"+
							"<td class='t_l' style='white-space: nowrap;'>"+object.quantitiesSubject.subjectName+"</td>";
						if(object.quantitiesSubject.subjectType=="1"){
							html+="<td class='t_l' style='white-space: nowrap;'>总价闭口类</td>";
						}else if(object.quantitiesSubject.subjectType=="2"){
							html+="<td class='t_l' style='white-space: nowrap;'>单价合算类</td>";
						}else if(object.quantitiesSubject.subjectType=="3"){
							html+="<td class='t_l' style='white-space: nowrap;'>里程碑支付类</td>";
						}else{
							html+="<td class='t_l' style='white-space: nowrap;'></td>";
						}
						html +=	"<td class='t_r' style='white-space: nowrap;'>";
						if(parseFloat(object.quantitiesSubject.unitPrice)==0){
							html+= 0;
						}else{
							html+= parseFloat(object.quantitiesSubject.unitPrice).toFixed(2);
						}					
						html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>"+object.quantitiesSubject.amount+"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
						if(parseFloat(object.quantitiesSubject.totalPrice)==0){
							html+= 0;
						}else{
							html+= parseFloat(object.quantitiesSubject.totalPrice).toFixed(2);
						}	
						html+=	"</td>"+
							"<td class='t_c' style='white-space: nowrap;' colspan='2'>"+object.quantitiesSubject.investEstimateName+"</td>"+
							"<td class='t_c'>"+
								"&nbsp;<a href='javascript:void(0);' onclick='showSubjectEdit(this);' style='display:inline;padding-right: 3px;'>编辑</a>&nbsp;"+
								"<a href='javascript:void(0);' onclick='showAdd(1,this);' style='display:inline;padding-right: 3px;'>增(同)</a>&nbsp;"+
								"<a href='javascript:void(0);' onclick='showAdd(2,this);' style='display:inline;padding-right: 3px;'>增(下)</a>&nbsp;"+
								"<a href='javascript:void(0);' onclick='showSubject(this);' style='display:inline;'>关联科目</a>"+
								"<input type='button' value='保存' style='display: none;' onclick='saveEditOnPage(this);' id='save'>&nbsp;"+
								"<input type='button' value='取消' style='display: none;' onclick='hideSubjectEdit(this);' id='cancel'>"+
							"</td>"+
						"</tr>";					
				$previousTargetTr.next().remove();
				
				if(type==1){	//同级新增
					var currentId =	$previousTargetTr.find("#hide_id").val();
					if(currentId!=null && currentId!='' && currentId!='undefined'){
						var _id = findLastChild(currentId);
						$("input[id=hide_id][value="+_id+"]").parents("tr").after(html);
					}else{
						$("#last").before(html);					
					}
				}else{
					$previousTargetTr.after(html);
				}
			}
			//将同级的order+1
			$("input[id=hide_parentId][value="+object.wbs.parentId+"]").each(function(){
				var order = $(this).parents("tr").find("#hide_order").val();
				var id = $(this).parents("tr").find("#hide_id").val();
				if(parseInt(order)>=parseInt(object.wbs.wbsOrder)){
					if(id!=object.wbs.id)
						$(this).parents("tr").find("#hide_order").val(parseInt(order)+1);
				}
				if($("tbody").find($("input:checked[name=checkbox]")).parent().parent().find("#hide_dataType").length==0){
					if(parseInt(order)==parseInt(object.wbs.wbsOrder)-1&&$(this).parents("tr").find("#special_order").length>0){
						$(this).parents("tr").find("#hide_order").val(parseInt(order)+1);
					}
				}else{
					if(parseInt(order)==parseInt(object.wbs.wbsOrder)-1&&$(this).parents("tr").find("#special_order").length>0&&specialOrder<$(this).parents("tr").find("#special_order").val()){
						$(this).parents("tr").find("#hide_order").val(parseInt(order)+1);
					}
				}
			});
			$("input:checked[name=checkbox]").attr("checked",false);
			showAddStatus = false;
			checkbox();
			highLight();
			alert("保存成功！");
		}
	});
}




//显示工程编辑
function showQuantities(target){
	$targetTr = $(target).parent().parent();
	var $count = $targetTr.children("td:eq(6)");
	var count_input = "<input name='countAll' class='input_small' type='text' value='"+$.trim($count.text())+"' id='"+$.trim($count.text())+"' style='text-align:right'>";
	$count.html(count_input);
	$(target).hide();
	$(target).siblings("input:first").attr("style","display:inline;");
	$(target).siblings("input:last").attr("style","display:inline;");
}


//隐藏工程编辑
function hideQuantitiesListEdit(target){
	$targetTr = $(target).parent().parent();
	$count = $targetTr.find("td:eq(6)");
	var id = $count.children("input").attr("id");
	if(id==null || id=="" || id=="undefined"){
		$count.html("");
	}else{
		$count.html(id);
	}
	$(target).hide();
	$(target).siblings("input").hide();
	$(target).siblings("a").each(function(){
		$(this).attr("style","display:inline;");
	});
}

//保存列表的第一行、最后一行，工程
function saveQuantitiesList(target){
	var id = $(target).parent().parent().find("input:hidden").val();
	//var projectAmount = $(target).parent().parent().find("input:visible[type=text]:last").val();
	var countAll = $(target).parent().parent().find("input[name='countAll']").val();
	if(!$.isNumeric(countAll)){
		alert("数量字段必须输入数字！");
		return false;
	}
	if($.isNumeric(id)){
		id = "'"+id+"'";
	}
	$.ajax({
		type : "post",
		url : "<%=basePath%>/quantitiesList/saveOrUpdateOnPage.action?random="+Math.random(),
		dataType:"json",
		cache : false,
		data:{
			id:id,
			contractId:$("#contractId").val(),
			contractNo:$("#contractNo").val(),
			projectAmount:countAll
		},
		error:function(){
			alert("系统忙，请刷新页面后重新操作！")
		},
		success:function(object){
			alert("保存成功！");
			queryContract();
		}
	});
}

//显示新增区域,1：同级新增，2：下级新增
function showAdd(type,target){
	if(showAddStatus){
		alert("请先保存或取消新增行！");
		return false;
	}
	if($("#contractId").val()==null || $("#contractId").val()==""){
		$("input[name=contractNo_search]").focus();
		alert("请先查询合同！");
		return false;
	}
	var ifHasSpecial;
	var specialOrder = 0;
	if($("tbody").find($("input:checked[name=checkbox]")).parent().parent().find("#hide_dataType").length==1){//是否是特殊行
		ifHasSpecial = "yes";
		specialOrder = $("tbody").find($("input:checked[name=checkbox]")).parent().parent().find("#hide_specialOrder").val();
		if(type==2){
			alert("特殊行不能新增下级！");
			return false;
		}
	}else{
		ifHasSpecial = "no";
	}
	var wbsId,parentId,level,order,$beforeTargetTr;
	if(!$.isEmptyObject(target)){		//操作栏里面点 增（同），增（下）
		wbsId = $.trim($(target).parent().parent().find("td:eq(1)").text().replace("-","").replace("+",""));
		level = $(target).parent().parent().find("#hide_level").val();
		order = $(target).parent().parent().find("#hide_order").val();
		if(type==1 || type==3){		//同级新增,新增特殊行
			parentId = $(target).parent().parent().find("#hide_parentId").val();
		}else{			//下级新增
			parentId = $(target).parent().parent().find("#hide_id").val();
		}
	}else{		//点击button："同级新增"，"下级新增"
		//勾选了checkbox
		if($("input:checked[name=checkbox]:last").val()!=null && $("input:checked[name=checkbox]:last").val()!=""){
			if($("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").length==0){
				wbsId = $.trim($("input:checked[name=checkbox]:last").parent().next().text().replace("-","").replace("+",""));
			}else{
				wbsId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").val();
			}
			level = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_level]").val();
			order = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_order]").val();
			if(type==1 || type==3){	//同级新增,新增特殊行
				parentId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_parentId]").val();	
			}else{		//下级新增
				parentId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_id]").val();
			}
		}else{	//没有勾选checkbox,在最后新增
			var largestOrder = 0,index = -1;
			$("input[id=hide_level][value=1]:hidden").each(function(i){
				var order = $(this).parents("tr").find("#hide_order").val();
				if(parseInt(order) > largestOrder){
					largestOrder = parseInt(order);
					index = i;
				}
			});	
			if(largestOrder!=0){	//level=1,有数据
				$beforeTargetTr = $("input[id=hide_level][value=1]:hidden:eq("+index+")").parents("tr");
				wbsId = $.trim($beforeTargetTr.find("td:eq(1)").text());
				level = $beforeTargetTr.find("input[id=hide_level]").val();
				order = $beforeTargetTr.find("input[id=hide_order]").val();
				parentIdObj = $beforeTargetTr.find("input[id=hide_parentId]");
				if(type==1){	//同级新增,新增特殊行
					parentId = $beforeTargetTr.find("input[id=hide_parentId]").val();
				}else{			//下级新增
					parentId = $beforeTargetTr.find("input[id=hide_id]").val();
				}
			}else{					//level=1,无数据
				$beforeTargetTr = $("#first");
				wbsId = $.trim($("#last").prev().find("td:eq(1)").text());
				level = $("#last").prev().find("input[id=hide_level]").val();
				order = $("#last").prev().find("input[id=hide_order]").val();
				parentIdObj = $("#last").prev().find("input[id=hide_parentId]");
				if(type==1){	//同级新增,新增特殊行
					parentId = $("#last").prev().find("input[id=hide_parentId]").val();
				}else{			//下级新增
					parentId = $("#last").prev().find("input[id=hide_id]").val();
				}
			}
		}
	}
	
	if(wbsId==null || wbsId==""){
		wbsId = 1;	
	}else{
		if(type==1 || type==3){	//同级新增,level不变
			if(wbsId.indexOf(".")==-1){
				wbsId = parseInt(wbsId)+1; 
			}else{
				var prefix = wbsId.substring(0,wbsId.lastIndexOf("."));
				var suffix = wbsId.substring(wbsId.lastIndexOf(".")+1,wbsId.length);
				wbsId = prefix+"."+(parseInt(suffix)+1); 
			}
		
			if(order!=null && order!=""){ 
				order = parseInt(order)+1;
			}else{
				order = 1;
			}
		}else{		//下级新增,order =1,level+1
			if(level!=null && level!=""){
				level = parseInt(level)+1;
			}else{
				level = 1;
			}
			order = 1;
			wbsId = wbsId+".1";
		}
	}
	var input_html="<tr>"+
			"<input id='parentId_save' value='"+parentId+"' type='hidden'>"+
			"<input id='level_save' value='"+level+"' type='hidden'>"+
			"<input id='order_save' value='"+order+"' type='hidden'>"+
			"<input id='dataType' value='1' type='hidden'>"+
			"<input id='ifHasSpecial' value='"+ifHasSpecial+"' type='hidden'>"+
			"<input id='specialOrder' value='"+specialOrder+"' type='hidden'>"+
			"<td class='t_c' colspan='2'><input name='wbsId_add' class='input_tiny' placeholder='WBS编号' value='"+wbsId+"' readonly='readonly' style='text-align:left;'></td>"+
			"<td class='t_c'><input name='subjectNo_add' class='input_small' placeholder='条目编码'></td>"+
			"<td class='t_c'><input name='subjectName_add' class='input_large' placeholder='条目名称'></td>"+
			"<td class='t_c'>"+
				"<select name='subjectType_add' id='subjectType_add'><option value=''>---请选择---</option><option value='1'>总价闭口类</option><option value='2'>单价核算类</option><option value='3'>里程碑支付类</option></select>"+
			"<td class='t_c'><input name='unitPrice_add' class='input_small' placeholder='单价' style='text-align:right;'></td>"+
			"<td class='t_c'><input name='amount_add' class='input_small' placeholder='数量' style='text-align:right;'></td>"+
			"<td class='t_c'><input name='totalPrice_add' class='input_small' placeholder='金额' onfocus='setPrice(this)' style='text-align:right;'></td>"+
			"<td class='t_c' colspan='2'></td>"+
			"<td class='t_c'>";
			if(type==1){		//同级新增
				input_html += "<input type='button' value='保存' onclick='saveOnPage(this,1);'>";
			}else{
				input_html += "<input type='button' value='保存' onclick='saveOnPage(this);'>";
			}
		input_html +="<input type='button' value='取消' onclick='hideAdd(this)'>"+
			"</td>"+
		"</tr>";
		
	if(!$.isEmptyObject(target)){
		$(target).parent().parent().after(input_html);
	}else{
		if($("input:checked[name=checkbox]:last").val()!=null && $("input:checked[name=checkbox]:last").val()!=""){
			$("input:checked[name=checkbox]:last").parent().parent().after(input_html);
		}else{	//没有勾选checkbox,在最后新增
			if($("#tbody").find($("tr[id=show_common]")).length<1){
				$("#last").before(input_html);			
			}else{
				$beforeTargetTr.after(input_html);
			}
		}	
	}
	showAddStatus = true;
}

//隐藏新增区域
function hideAdd(target){
	$(target).parent().parent().remove();
	showAddStatus = false;
}


//显示清单编辑
function showSubjectEdit(target){
	//隐藏 “查看”和“编辑”按钮，显示保存和取消
	$(target).parent().children("input:first").attr("style","display:inline;")
	$(target).parent().children("input:last").attr("style","display:inline;");
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:none");
	});
	var value ="";			//初始值
	var addHtml = "";		//要添加的html
	var $targetObject;
	
	//1.条目编码
	$targetObject = $(target).parent().siblings("td:eq(2)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='subjectNo' value='"+value+"' id='"+value+"' class='input_small' type='text'>";
	$targetObject.html(addHtml);
	
	//2.条目名称
	$targetObject = $(target).parent().siblings("td:eq(3)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='subjectName' value='"+value+"' id='"+value+"' class='input_large' type='text'>";
	$targetObject.html(addHtml);
	
	//3.条目类型
	$targetObject = $(target).parent().siblings("td:eq(4)");
	value = $.trim($targetObject.text());
	addHtml = "<select name='subjectType_add' id='subjectType_add' class='"+value+"'><option value=''>---请选择---</option>";
	var op1,op2,op3;
	if(value=="总价闭口类"){
		op1 = "<option value='1' selected='selected'>总价闭口类</option>"
	}else{
		op1 = "<option value='1'>总价闭口类</option>"
	}
	if(value=="单价核算类"){
		op2 = "<option value='2' selected='selected'>单价核算类</option>"
	}else{
		op2 = "<option value='2'>单价核算类</option>"
	}
	if(value=="里程碑支付类"){
		op3 = "<option value='3' selected='selected'>里程碑支付类</option>"
	}else{
		op3 = "<option value='3'>里程碑支付类</option>"
	}
	addHtml += op1+op2+op3+"</select>";
	$targetObject.html(addHtml);
	
	//4.单价
	$targetObject = $(target).parent().siblings("td:eq(5)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='unitPrice' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);
	
	//5.数量
	$targetObject = $(target).parent().siblings("td:eq(6)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='amount' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);

	//6.总价
	$targetObject = $(target).parent().siblings("td:eq(7)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='totalPrice' value='"+value+"' id='"+value+"' class='input_small' type='text' onfocus='setPrice(this);' style='text-align:right;'>";
	$targetObject.html(addHtml);
}

//隐藏编辑科目
function hideSubjectEdit(target){

	//显示 “查看”和“编辑”按钮，隐藏保存和取消
	$(target).parent().children("input").attr("style","display:none;");
	
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:inline;padding-right:3px;");
	});
	
	$targetObject = $(target).parent().siblings("td:eq(1)");
	//1.目录
	//$targetObject = $(target).parent().siblings("td:eq(1)");
	/*
	var $checkbox = $targetObject.children("input[type=checkbox]");
	$checkbox.attr("style","display:inline");
	$targetObject.html($targetObject.children("input[type=text]").attr("id"));
	*/
	//$targetObject.prepend("&nbsp;");
	//$targetObject.prepend($checkbox);
	
	//1.条目编码
	$targetObject = $(target).parent().siblings("td:eq(2)");
	if($targetObject.children("input").attr("id")==null && $targetObject.children("input").attr("id")=="undefined"){
		$targetObject.html($targetObject.children("input").attr("id")+"");
	}else{
		$targetObject.html("");
	}
	
	
	//2.条目名称
	$targetObject = $(target).parent().siblings("td:eq(3)");
	$targetObject.html($targetObject.children("input").attr("id"));
	
	//3.条目类型
	$targetObject = $(target).parent().siblings("td:eq(4)");
	$targetObject.html($targetObject.children("select").attr("class"));
	
	//4.单价
	$targetObject = $(target).parent().siblings("td:eq(5)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//5.数量
	$targetObject = $(target).parent().siblings("td:eq(6)");
	var id = $targetObject.children("input").attr("id");
	if(id==null || id==""){
		$targetObject.html("");
	}else{
		$targetObject.html(id);
	}
	
	//6.金额
	$targetObject = $(target).parent().siblings("td:eq(7)");
	$targetObject.html($targetObject.children("input").attr("id"));
	
	//7.关联概算科目
	$targetObject = $(target).parent().prev();
	$targetObject.html($targetObject.children("input").attr("id"));
	
}

//保存编辑
function saveEditOnPage(target){
	//var wbsId = $.trim($(target).parent().siblings("td:eq(1)").children("input[name=wbsId]").val());
	var subjectNo = $(target).parent().siblings("td:eq(2)").children("input").val();
	var subjectName = $(target).parent().siblings("td:eq(3)").children("input").val();
	var subjectType = $(target).parent().siblings("td:eq(4)").children("select").val();
	var unitPrice = $(target).parent().siblings("td:eq(5)").children("input").val();
	var amount = $.trim($(target).parent().siblings("td:eq(6)").children("input").val());
	var totalPrice = $(target).parent().siblings("td:eq(7)").children("input").val();
	var $targetTr = $(target).parents("tr");
	var contractPrice = parseFloat($("#contractPrice").val())*10000;
	$("#targetNode").val($targetTr.find("input[name=checkbox]").val());
	/*
	if($.trim(subjectNo)==""){
		alert("条目编码不能为空！");
		$("input[name=subjectNo]").focus();
		return false;	
	}*/
	if($.trim(subjectName)==""){
		alert("条目名称不能为空！");
		$("input[name=subjectName]").focus();
		return false;	
	}
	if(unitPrice!="" && !$.isNumeric(unitPrice)){
		alert("请输入正确的数字！");
		$("input[name=unitPrice]").focus();
		return false;
	}else{
		if(unitPrice=="") unitPrice="0";
	}
	if(amount!="" && !$.isNumeric(amount)){
		alert("请输入正确的数字！");
		$("input[name=amount]").focus();
		return false;
	}else{
		if(amount=="") amount="0";
	}
	
	if(totalPrice!="" && !$.isNumeric(totalPrice)){
		alert("请输入正确的数字！");
		$("input[name=totalPrice]").focus();
		return false;
	}else{
		if(totalPrice=="") totalPrice="0";
	}
	if(parseFloat(totalPrice) > contractPrice){
		if(!confirm("该工程量条目的金额已超过合同金额,是否保存？")){
			$("input[name=totalPrice]").focus();
			return false;
		}
	}
	
	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveEditOnPage.action",
		data:{
			id:$(target).parents("tr").find("td:first").find("input[name=checkbox]").val(),
			subjectNo:subjectNo,
			subjectName:subjectName,
			subjectType:subjectType,
			unitPrice:unitPrice,
			amount:amount,
			totalPrice :totalPrice,
			contractId:$("#contractId").val()
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
		},
		success:function(object){
			alert("保存成功！");
			
			$targetTr.find("td:eq(2)").html(object.subjectNo);
			$targetTr.find("td:eq(3)").html(object.subjectName);
			var type = object.subjectType;
			var typeName="" ;
			if(type!=null){
				if(type=='1'){
					typeName='总价闭口类';
				}else if(type=='2'){
					typeName='单价核算类';
				}else if(type=='3'){
					typeName='里程碑支付类';
				}
			}
			$targetTr.find("td:eq(4)").html(typeName);
			
			$targetTr.find("td:eq(5)").html(parseFloat(unitPrice)==0?0:parseFloat(unitPrice).toFixed(2));
			$targetTr.find("td:eq(6)").html(object.amount);
			$targetTr.find("td:eq(7)").html(parseFloat(totalPrice)==0?0:parseFloat(totalPrice).toFixed(2));
			$targetTr.find("td:eq(9)").children("input").attr("style","display:none;");
			$targetTr.find("td:eq(9)").children("a").each(function(){
				$(this).attr("style","display:inline;padding-right:3px;");
			});
		}
	});
}

//删除所有选中的
function deleteAllChecked(){
	if($("input:checked").length<1){
		alert("请先勾选要删除的数据！");
		return false;	
	}else if($("input:checked").length>1){
		alert("只能勾选一条数据进行删除！");
		return false;	
	}
	
	$targetTr = $("input:checked").parent().parent();
	var id = $targetTr.find("#hide_id").val(); 
	var pro_id = $("input:checked").val();
	if($targetTr.find("#hide_dataType").length>0){//删除特殊行
		if(!confirm("是否删除？"))
				return false;
		$.ajax({
			type : 'post',
			url : 'deleteSpecial.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id,
				pro_id:pro_id
			},
			error:function(){
				alert("系统忙，请刷新页面后重新操作！")
			},
			success:function(object){
			alert("删除成功！");
				queryContract();
			}
		});
	}else{
		var hasChild = false;
	
		$("input[id=hide_parentId]").each(function(){
			if($(this).val() == id){
				hasChild = true;
			}
		});
		if(hasChild){
			if(!confirm("如要删除该数据，则该目录下的子目录将全部删除！是否删除？"))
				return false;
		}else{
			if(!confirm("是否删除？"))
				return false;
		}
		var id,parentId,contractId;
		id = $targetTr.find("#hide_id").val();
		parentId = $targetTr.find("#hide_parentId").val();
		contractId = $("#contractId").val();
		$.ajax({
			type : 'post',
			url : 'deleteAll.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id,
				parentId:parentId,
				contractId:contractId
			},
			error:function(){
				alert("系统忙，请刷新页面后重新操作！")
			},
			success:function(object){
			alert("删除成功！");
				queryContract();
			}
		});
	}
}

//自动提示,合同编号
$(function(){
	//var cache = {};
	$("#contractNo_search" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/contract/findByContractNoWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"contractNo" : request.term
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.contractNo,
							value: item.id,
							projectId:item.projectId
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
		},
		select: function( event, ui ) {
			$("#contractNo_search").val(ui.item.label);
			$("#contractId_search").val(ui.item.value);
			$("#projectId").val(ui.item.projectId);
			return false;
		},
		open: function() {
		},
		close: function() {
		},
		focus: function( event, ui ) {
				return false;
		},
		change : function(event,ui){
			$("input[name=contractId_search]").val("");
		}
	});

});

//自动提示，合同名称
$(function(){
	$("#contractName_search" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/contract/findByContractNameWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"contractName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.contractName,
							value: item.id,
							projectId:item.projectId
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
		},
		select: function( event, ui ) {
			$("#contractName_search").val(ui.item.label);
			$("#contractId_search").val(ui.item.value);
			$("#projectId").val(ui.item.projectId);
			return false;
		},
		open: function() {
		},
		close: function() {
		},
		focus: function( event, ui ) {
				return false;
		},
		change : function(event,ui){
			$("input[name=contractId_search]").val("");
		}
	});
});


//点击查询工程量条目
function queryContract(){
	var contractId = $("input[name=contractId_search]").val();
	$("#contractId").val(contractId);
	//var hideNodes = $("#hideNodes").val();
	//var targetNode = $("#targetNode").val();
	if(contractId==null || contractId==""){
		alert("该合同不存在，请重新查询！");
		return false;
	}
	var hideNodes = "";
	$("[id=minus]").each(function(){
		//alert($(this).parents("tr").attr("style"));
		if($(this).parents("tr").attr("style")==null||$(this).parents("tr").attr("style").indexOf("display")==-1){
			if(hideNodes.length>0){
				hideNodes += ",";
			}
			hideNodes += $(this).parents("tr").find("#hide_id").val();
		}
	});
	$("#hideNodes").val(hideNodes);
	$("#form").submit();
	//window.location.href = "findQuantitiesSubjectByPage.action?contractId="+$("#contractId_search").val()+"&hideNodes="+hideNodes+"&targetNode="+targetNode;
}


//显示下级,并且保留原来的层级显示和隐藏
function showH(id){
	$("#tbody").find("input[id=hide_parentId][value="+id+"]").each(function(){
		$(this).parents("tr").show();
		if($(this).parents("tr").attr("class")=="on"){
			showH($(this).siblings("#hide_id").val());
		}
	});
}

function hideH(id){		//隐藏下级所有
	$("#tbody").find("input[id=hide_parentId][value="+id+"]").each(function(){
		$(this).parents("tr").hide();
		hideH($(this).siblings("#hide_id").val());
	});
}

//显示关联概算科目
function showSubject(target){
	if($("input[name=subjectId]").length>=1){
		alert("请先保存或取消已显示的关联概算科目！");
		return false;
	}
	var $targetTd = $(target).parent().prev();
	var addHtml = "<input type='text' name='ieSubjectName' id='"+$.trim($targetTd.html())+"' value='"+$.trim($targetTd.html())+"'>";
	var addHtml2 = "<input type='hidden' name='ieSubjectId'>";
	var $saveButton1 = $(target).siblings("#save");
	$saveButton1.remove();
	var saveButton2 = "<input type='button' value='保存' onclick='saveSubject(this)' id='save'>"
	$(target).parent().find("#cancel").before(saveButton2);
	
	var $cancelButton1 = $(target).siblings("#cancel");
	$cancelButton1.remove();
	var cancelButton2 = "<input type='button' value='取消' onclick='hideSubject(this)' id='cancel'>"
	$(target).parent().find("#save").after(cancelButton2);
	
	//隐藏 “查看”和“编辑”按钮，显示保存和取消
	$(target).parent().children("input:first").attr("style","display:inline;");
	$(target).parent().children("input:last").attr("style","display:inline;");
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:none");
	});
	$targetTd.html(addHtml+addHtml2);
	autoCompleteSubject();
}

//影藏关联概算科目
function hideSubject(target){
	var $saveButton2 = $(target).siblings("#save");
	$saveButton2.remove();
	var saveButton1 = "<input type='button' value='保存' onclick='saveEditOnPage(this)' id='save' style='display:none;'>"
	$(target).parent().find("#cancel").before(saveButton1);
	
	var $cancelButton2 = $(target);
	var cancelButton1 = "<input type='button' value='取消' onclick='hideSubjectEdit(this)' id='cancel' style='display:none;'>"
	$(target).parent().find("#save").after(cancelButton1);
	
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:inline;padding-left:3px;");
	});
	var $td = $(target).parent().prev();
	var idValue = $td.children("input[name=ieSubjectName]").attr("id");
	if(idValue==null || idValue==""){
		$td.html("");	
	}else{
		$td.html("idValue");
	}
	
	$cancelButton2.remove();
}

//保存关联的概算科目
function saveSubject(target){
	var $dataTd = $(target).parent().prev();
	var ieId = $dataTd.children("input[name=ieSubjectId]").val();
	var ieName = $dataTd.children("input[name=ieSubjectName]").val(); 
	if(ieId==null || ieId==""){
		alert("该概算科目不存在！");
		$dataTd.children("input[name=ieSubjectName]").focus();
		return false;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveSubjectOnPage.action",
		data:{
			id:$(target).parents("tr").find("td:first").find("input[name=checkbox]").val(),
			ieId:ieId,
			ieName:ieName
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
			},
		success:function(object){
			alert("保存成功！");
			queryContract();
		}
	});
}


//自动提示,关联概算科目
function autoCompleteSubject(){
	var saveStatus = false;
	$("input[name=ieSubjectName]").autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				/*url: "findSubject.action?random="+Math.random(),*/
				url: "<%=basePath%>/quantitiesSubject/findSubjectByProjectId.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",
					//"contractId" : $("#contractId").val(),
					"projectId" : $("input[name=projectId]").val(),
					"subjectName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						if(item!=null){
							return {
								label: item.wbsId + "-" + item.subjectName + "-" + item.sum+"万元",
								value: item.subjectId,
								name : item.subjectName
							}						
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("input[name=ieSubjectName]").val($.trim(ui.item.label));
			$("input[name=ieSubjectId]").val($.trim(ui.item.value));
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
			$("input[name=ieSubjectId]").val("");
			return false;
		},
		change : function(event,ui){
			if(!saveStatus){
				$("input[name=ieSubjectId]").val("");
			}
		}
	});
}

//新增特殊行
function showAddSpecial(){
	if($("input:checked[name=checkbox]").length==0){
		alert("请先勾选要添加特殊行的位置！");
		return false;
	}
	if(showAddStatus){
		alert("请先保存或取消新增行！");
		return false;
	}
	if($("#contractId").val()==null || $("#contractId").val()==""){
		$("input[name=contractNo_search]").focus();
		alert("请先查询合同！");
		return false;
	}
	var parentId,level,order,wbsId,specialOrder;
	if($("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_specialOrder]").length>0){
		specialOrder = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_specialOrder]").val();
	}else{
		specialOrder = 0;
	}
	if($("input:checked[name=checkbox]:last").val()!=null && $("input:checked[name=checkbox]:last").val()!=""){
		if($("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").length==0){
			wbsId = $.trim($("input:checked[name=checkbox]:last").parent().next().text().replace("-","").replace("+",""));
		}else{
			wbsId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").val();
		}
		level = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_level]").val();
		order = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_order]").val();
		parentId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_parentId]").val();	
	}
	var input_html="<tr>"+
			"<input id='parentId_save' value='"+parentId+"' type='hidden'>"+
			"<input id='level_save' value='"+level+"' type='hidden'>"+
			"<input id='order_save' value='"+order+"' type='hidden'>"+
			"<input id='wbsId_save' value='"+wbsId+"' type='hidden'>"+
			"<input id='specialOrder_save' value='"+specialOrder+"' type='hidden'>"+
			"<td colspan='3'><select onchange='showSpecial(this);'><option value=''>--请选择特殊行类型--</option><option value='2'>注释行</option><option value='3'>求和行</option></select></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak' colspan='2'></td>"+
			"<td class='t_c'>"+
				"<input type='button' value='保存' onclick='saveOnPageSpecial(this);'>"+
				"<input type='button' value='取消' onclick='hideAdd(this)'>"+
			"</td>"+
		"</tr>";	
	$("input:checked[name=checkbox]:last").parent().parent().after(input_html);
	showAddStatus = true;
}

//保存特殊行
function saveOnPageSpecial(obj){
	$(obj).attr("disabled","disabled");
	$(obj).siblings("input").attr("disabled","disabled");
	var type = $(obj).parent().parent().find("select").val();
	if(type==''){
		alert("请选择特殊行类型！");
	}else{
		var parentId = $(obj).parent().parent().find("input[id=parentId_save]").val();
		var level = $(obj).parent().parent().find("input[id=level_save]").val();
		var order = $(obj).parent().parent().find("input[id=order_save]").val();
		var wbsId = $(obj).parent().parent().find("input[id=wbsId_save]").val();
		var specialOrder = $(obj).parent().parent().find("input[id=specialOrder_save]").val();
		var contractId = $("input[name=contractId]").val();
		var $previousTargetTr = $(obj).parents("tr").prev();
		$("#targetNode").val($previousTargetTr.find("input[name=checkbox]").val());
		if(type=="2"){
			var subjectName = $(obj).parent().parent().find("input[id=subjectName]").val();
			if($.trim(subjectName)==""){
				alert("请填写注释行！");
				$("input[id=subjectName]").focus();
				return false;	
			}
			if(subjectName.length>80){
				alert("注释不能超过80个字！");
				$(obj).parent().parent().find("input[id='subjectName']").focus();
				$(obj).attr("disabled",false);
				$(obj).siblings("input").attr("disabled",false);
				return false;
			}
			
			$.ajax({
				type:"post",
				dataType:"json",
				url:"saveAddOnPageSpecial.action",
				data:{
					contractId:contractId,
					dataType:type,
					parentId:parentId,
					subjectName:subjectName,
					level:level,
					order:order,
					specialOrder:specialOrder,
					wbsId:wbsId
				},
				error:function(){
						alert("系统忙，请刷新页面后重新操作！");
					},
				success:function(object){
					alert("保存成功！");
					queryContract();
				}
			});
		}else if(type=="3"){
			var subjectName = $.trim($(obj).parent().parent().find("#subjectName").html());
			var amount = $.trim($(obj).parent().parent().find("#amount").html());
			var totalPrice = $.trim($(obj).parent().parent().find("#totalPrice").html());
			
			$.ajax({
				type:"post",
				dataType:"json",
				url:"saveAddOnPageSpecial.action",
				data:{
					contractId:contractId,
					dataType:type,
					parentId:parentId,
					subjectName:subjectName,
					level:level,
					order:order,
					specialOrder:specialOrder,
					wbsId:wbsId,
					amount:amount,
					totalPrice:totalPrice
				},
				error:function(){
						alert("系统忙，请刷新页面后重新操作！");
					},
				success:function(object){
					alert("保存成功！");
					queryContract();
				}
			});
		}
	}
}

function showSpecial(obj){
	if($(obj).val()=='2'){//注释行
		$(obj).parent().parent().find(".bak").remove();
		$(obj).parent().after("<td class='bak' colspan='7'><input type='text' style='width:100%' id='subjectName'/></td>");
		$("#subjectName").focus();
	}else if($(obj).val()=='3'){
		var arraySum = rootSum();
		$(obj).parent().parent().find(".bak").remove();
		var addHtml = "<td colspan='3' class='bak'  style='text-align:right' id='subjectName'>以上各项合计：</td>"+
					"<td class='bak' style='text-align:right' id='amount'>"+arraySum[0]+"</td>"+
					"<td class='bak' style='text-align:right' id='totalPrice'>"+arraySum[1]+"</td>"+
					"<td class='bak'  colspan='2'></td>";
		$(obj).parent().after(addHtml);
	}
}

function rootSum(){
	var num;
	var sum1 =0;
	var sum2 =0;
	var arraySum = new Array();
	var tr;
	$("#tbody").find("input[type=checkbox]").each(function(index){
		if($(this).attr("checked")=="checked"){
			num =index+2;
		}
	});
	for(var i=2;i<=num;i++){
		tr = $("#tbody").children("tr:eq("+i+")");
		if(tr.find("input[id=hide_level]").val()==1&&tr.find("input[id=hide_dataType]").length==0){
			sum1 += parseFloat(tr.children("td:eq(6)").html().replace("","0"));
			sum2 += parseFloat(tr.children("td:eq(7)").html());
		}
	}
	arraySum[0] = sum1;
	arraySum[1] = sum2.toFixed(2);
	return arraySum;
}


$(function(){
	if($("#hideNodes").val()==""&&$("#targetNode").val()==""){//首次打开页面
		openNodesByLevel(1);//控制展开层数，修改此参数
	}else{//页面刷新
		openNodesByLevel(1);//此参数不可修改
		shrinkHideNodes();
	}
	
	//特殊行高亮
	$("[id=special_line]").each(function(){
		$(this).attr("style","background-color:rgb(226, 231, 243);");
	});
})

function showSubjectEditSpecial(obj){
	var tr = $(obj).parent().parent();
	if(tr.find("#hide_dataType").val()=='2'){
		tr.children("td:eq(1)").html("<input type='text' style='width:100%' name='subjectName_' id='"+$.trim(tr.children("td:eq(1)").html())+"' value='"+$.trim(tr.children("td:eq(1)").html())+"'/>");
		$(obj).attr("style","display:none");
		$(obj).parent().children("input[type=button]").attr("style","display:inline");
	}
}

function hideSubjectEditSpecial(obj){
	var tr = $(obj).parent().parent();
	if(tr.find("#hide_dataType").val()=='2'){
		tr.children("td:eq(1)").html(tr.find("input[name=subjectName_]").attr("id"));
		$(obj).parent().children("a").attr("style","display:inline");
		$(obj).parent().children("input[type=button]").attr("style","display:none");
	}
}

function saveEditOnPageSpecial(obj){
	var tr = $(obj).parent().parent();
	var subjectName = $.trim(tr.find("input[name=subjectName_]").val());
	$("#targetNode").val(tr.find("input[name=checkbox]").val());
	if(subjectName==""){
		alert("请填写注释行！");
		tr.find("input[name=subjectName_]").focus();
		return false;
	}
	if(subjectName.length>80){
		alert("注释不能超过80个字！");
		tr.find("input[name=subjectName_]").focus();
		return false;
	}
	if(tr.find("#hide_dataType").val()=='2'){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"saveEditOnPageSpecial.action",
			data:{
				id:tr.find("input[type='checkbox']").val(),
				subjectName:subjectName
			},
			error:function(){
					alert("系统忙，请刷新页面后重新操作！");
				},
			success:function(object){
				alert("保存成功！");
				queryContract();
			}
		});
	}
}

//重新计算功能
function compute(){
	if(!inCalculate){
		if(confirm("是否重新计算？")){
			inCalculate = true;
			var ids="",number="",money="";
			var $targetTr;
			$("input[id=hide_dataType][value=3]:hidden").each(function(){
				$targetTr = $(this).parents();
				ids += $targetTr.find("input[name=checkbox]").val()+",";
				number += $.trim($targetTr.find("td:eq(4)").html())+",";
				money += $.trim($targetTr.find("td:eq(5)").html())+",";
			});
	
			if(ids!="")
				ids = ids.substring(0,ids.length-1);
			if(number!="")
				number = number.substring(0,number.length-1);
			if(money!="")
				money = money.substring(0,money.length-1);
			$.ajax({
				type : 'post',
				url : 'compute.action?random='+Math.random(),
				dataType:'json',
				cache : false,
				data:{
					contractId:$("#contractId").val(),
					specilDataIds:ids,
					number:number,
					money:money
				},
				error:function(){
					alert("系统忙，请刷新页面后重新操作！")
				},
				success:function(object){
					alert("计算成功！");
					queryContract();
				}
			});
		}
	}else{
		alert("计算中，请耐心等待！");
	}
}


//双击某一行第三个td修改，单击某一行还原样式
function highLight(){
	$("#tbody").find("#show_common").each(function(){
		if($(this).find("#hide_dataType").length==0){
			var $target = $(this).children("td:eq(2)");
			$target.dblclick(function(obj){
				if($target.html().indexOf("subjectNo")<0 && $target.html().indexOf("text")<0){
					$(this).siblings("td:last").children("a:eq(0)").click();
				}
			});
		}
	});
}


//多选框实现单选功能
function checkbox(){
	$("input[name=checkbox]").each(function(){
		$target = $(this); 
		$(this).click(function(){
			var status = $(this).attr("checked");
			if(status=="checked"){
				$("input:checked").attr("checked",false);
				$(this).attr("checked",true);
			}else{
				$(this).attr("checked",false);
			}
		});
	});
}

$.fn.smartFloat = function() {
    var position = function(element) {
        var top = element.position().top, pos = element.css("position");
        var browser=navigator.appName;
        var browser2=navigator.userAgent;      
            $(window).scroll(function() {
            var scrolls = $(this).scrollTop();
            if (scrolls > 120) {
                if (window.XMLHttpRequest) {
                    if(browser2.indexOf("Chrome") > -1 || browser2.indexOf("MSIE")> -1 || browser2.indexOf("Mozilla")> -1){
                        element.css({position: "fixed",top: 0});//更改这里的0可以设定在哪个Y位置固定
                    }else{
                        element.css({position: "fixed",top: scrolls}); 
                    }
                } else {
                    element.css({top: scrolls});   
                }
            }else {
                element.css({position: pos,top: top}); 
            }
        });
    };
    return $(this).each(function() {
        position($(this));                      
    });
};

//设置金额
function setPrice(object){
	var price = parseFloat($(object).parent().prev().prev().find("input").val());
	var number = parseFloat($(object).parent().prev().find("input").val());
	var totalPrice = price * number;
	if($.isNumeric(totalPrice))
		$(object).val(totalPrice.toFixed(2));
}
//导出Excel
function exportExcel(){
	$("#ifExport").val("yes");
	$("#form").submit();
	$("#ifExport").val("");
}
</script>
 
 
 
 
</head>

<body>
<input name="contractNo" value="<s:property value='#request.contract.contractNo' />" type="hidden" id="contractNo">
<input name="projectId" value="<s:property value='#request.contract.projectId' />" type="hidden" id="projectId">
<input name="showOrHide" type="hidden">
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li class="fin">工程量条目管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl(this);">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8" id="searchArea">
			<input id="contractPrice" type="hidden" value="<s:property value='#request.contract.contractPrice'/>">
			<s:form action="findQuantitiesSubjectByPage" name="quantitiesSubject" namespace="/quantitiesSubject" method="post" id="form">
				<input type="hidden" name="ifExport" id="ifExport"/>
				<input type="hidden" id="hideNodes" name="hideNodes" value="<s:property value='#request.hideNodes'/>"/>
				<input type="hidden" id="targetNode" name="targetNode" value="<s:property value='#request.targetNode'/>"/>
				<input name="contractId" value="<s:property value='#request.contract.id' />" type="hidden" id="contractId">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%;" class="t_r">合同编号：</td>
						<td width="25%;"  >
							<input type="text" name="contractNo_search" id="contractNo_search" class="input_xxlarge" value="<s:property value='#request.contract.contractNo'/>" />
							<input type="hidden" name="contractId_search" id="contractId_search" value="<s:property value='#request.contract.id'/>"/>
							<input type="button" value="查询" onclick="queryContract();">
						</td>
						<td width="10%;" class="t_r" >合同名称：</td>
						<td width="35%;" >
							<input type="text" name="contracName_search" id="contractName_search" class="input_xxlarge" value="<s:property value='#request.contract.contractName'/>" />
							<input type="button" value="查询" onclick="queryContract();">
						</td>
						<td class="t_r" >合同类型：</td>
						<td >
							<s:if test="#request.contract.contractType!=null && #request.contract.contractType!=''">
								<s:if test="#request.contract.contractType.substring(0,1)==1">
									建筑类
									-
									<s:if test="#request.contract.contractType.substring(2,3)==1">工程类</s:if>
									<s:elseif test="#request.contract.contractType.substring(2,3)==2">勘察类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==3">设计类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==4">前期类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==5">其他类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==6">监理类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==7">科研类</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==8">咨询类</s:elseif>
								</s:if>
								<s:elseif test="#request.contract.contractType.substring(0,1)==2">
									运维类
									-
									<s:if test="#request.contract.contractType.substring(2,3)==1">服务</s:if>
									<s:elseif test="#request.contract.contractType.substring(2,3)==2">工程</s:elseif>
									<s:elseif test="#request.contract.contractType.substring(2,3)==3">货物</s:elseif>
								</s:elseif>
								<s:else>
									其他类-其他类
								</s:else>
							</s:if>
						</td>
					</tr>
					<tr >
						<td class="t_r" >合同有效时间：</td>
						<td >
							<s:property value="#request.contract.contractPassedDate"/>&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;
							<s:property value="#request.contract.contractEndDate"/>
						</td>
						<!-- 
						<td class="t_r" >业主方：</td>
						<td >
							<s:property value="#request.contract.contractOwnerName"/>
						</td>
						 -->
						<td class="t_r" >对方单位：</td>
						<td >
							<s:property value="#request.contract.buildSupplierName"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			
			<div class="fn" style="white-space: nowrap;">
				<input type="button" value="同级新增" class="fl mr5" onclick="showAdd(1);" >
				<input type="button" value="下级新增" class="fl mr5" onclick="showAdd(2);" >
				<input type="button" value="新增特殊行" class="fl mr5" onclick="showAddSpecial();" >
				<!-- <input type="button" value="导入" class="fl mr5" > -->
				
				<input type="button" value="删除" class="mr5" style="float: right;" onclick="deleteAllChecked();">
				<input type="button" value="导出" class="mr5" style="float: right;" onclick="exportExcel();">
				<input type="button" value="重新计算" class="mr5" style="float: right;" onclick="compute();">
			</div>
			
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			
			<!--投资概算科目列表  -->			
			<thead>
				<th colspan="11">&nbsp;&nbsp;工程量条目列表</th>
			</thead>
			<tbody id="tbody">
				
				<tr class="tit" id="listTitle">
					<td width="2%;" >
						<!-- <input type="checkbox" id="checkAll" value="1"> -->
					</td>
					<td width="8%;" >
						WBS编码
					</td>
					<td width="5%;" class="t_r" >条目编码</td>
					<td width="18%;" class="t_r" >条目名称</td>
					<td width="7%;" class="t_r" >条目类型</td>
					<td width="10%;" class="t_r" >单价<br>(元)</td>
					<td width="8%;" class="t_r" >数量</td>
					<td width="10%;" class="t_r" >金额<br>(元)</td>
					<!-- <td width="12%;" class="t_r"  >验收条件</td>
					<td width="10%;" class="t_r"  >条目状态</td> -->
					<td width="17%;" class="t_r"  colspan="2">关联概算科目</td>
					<td width="15%;" class="t_r" >
						操作&nbsp;&nbsp;&nbsp;
						<a href="#" style="display: inline;"><img title="刷新" alt="刷新" src="../images/refresh.png" onclick="queryContract();" style="display: inline;width: 20px;height: 20px;"></a>
						<a href="#" onclick="showAddSpecial();" >新增特殊行</a>
					</td>
				</tr>
				<tr id="first">
					<td width="2%;" class="t_l" >
						<input type="hidden" value="<s:property value='#request.quantitiesList.id'/>" id="estimateId">
					</td>
					<td width="8%;" class="t_l" ></td>
					<td width="5%;" class="t_l" ></td>
					<td width="18%;" class="t_c" style="white-space: nowrap;font-weight: bold;">总计</td>
					<td width="7%;"></td>
					<td width="10%;" id="unit_1" class="t_r" style="white-space: nowrap;font-weight: bold;"></td>
					<td width="8%;" id="amount_1" class="t_r" style="white-space: nowrap;font-weight: bold;"><s:property value='#request.quantitiesList.projectAmount'/></td>
					<td width="10%;" class="t_r" ><s:property value='#request.quantitiesList.totalPrice'/></td>
					<td width="17%;" class="t_c"  colspan="2"></td>
					<td width="15%;" class="t_c">
						<a href="#" onclick="showQuantities(this);" style="display:inline;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveQuantitiesList(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideQuantitiesListEdit(this);">
					</td>
				</tr>
				<s:iterator value="#request.quantitiesSubjectList" id="list" status="st">
					<s:if test="#request.listWBS.get(#st.index).type==1">
					<tr id="show_common" class="off" style="display:none">
						<td class="t_c" >
							<input type="hidden" id="hide_id" value="<s:property value='#request.listWBS.get(#st.index).id'/>" />
							<input type="hidden" id="hide_parentId" value="<s:property value='#request.listWBS.get(#st.index).parentId'/>" />
							<input type="hidden" id="hide_level" value="<s:property value='#request.listWBS.get(#st.index).wbsLevel'/>">
							<input type="hidden" id="hide_order" value="<s:property value='#request.listWBS.get(#st.index).wbsOrder'/>">
							<input type="checkbox" name="checkbox" value="<s:property value="#list.id"/>">&nbsp;
						</td>
						<td class="t_l" >
							<p style="display: inline;">
								<font style="display: inline;">
								<s:if test="#request.listWBS.get(#st.index).wbsLevel==2">&nbsp;</s:if>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==3">&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:if test="#request.listWBS.get(#st.index+1).parentId==#request.listWBS.get(#st.index).id||#request.listWBS.get(#st.index+2).parentId==#request.listWBS.get(#st.index).id||#request.listWBS.get(#st.index+3).parentId==#request.listWBS.get(#st.index).id">
									<img src="../images/plus.png" onclick="showHierarchy(this);" style="display:inline" id="plus"/>
								</s:if>
								<s:else>&nbsp;&nbsp;&nbsp;</s:else>
								</font>
								<s:property value="#request.listWBS.get(#st.index).wbsId"/>
							</p>
						</td>
						<td class="t_l" ><s:property value="#list.subjectNo"/></td>
						<td class="t_l" ><s:property value="#list.subjectName"/></td>
						<td class="t_l" >
							<s:if test="#list.subjectType==1">总价闭口类</s:if>
							<s:elseif test="#list.subjectType==2">单价核算类</s:elseif>
							<s:elseif test="#list.subjectType==3">里程碑支付类</s:elseif>
							<s:else></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.unitPrice==0">0</s:if>
							<s:elseif test="#list.unitPrice.indexOf('.')==-1">
								<s:property value="#list.unitPrice"/>.00
							</s:elseif>
							<s:else>
								<s:property value="#list.unitPrice.substring(0,#list.unitPrice.indexOf('.')+3)"/>
							</s:else>
						</td>
						<td class="t_r" ><s:property value="#list.amount"/></td>
						<td class="t_r" >
							<s:if test="#list.totalPrice==0">0</s:if>
							<s:elseif test="#list.totalPrice.indexOf('.')==-1">
								<s:property value="#list.totalPrice"/>.00
							</s:elseif>
							<s:else>
								<s:property value="#list.totalPrice.substring(0,#list.totalPrice.indexOf('.')+3)"/>
							</s:else>
						</td>
						<td class="t_c"  colspan="2">
							<s:property value="#list.investEstimateName" />
						</td>
						<td class="t_c" >
							<a href="javascript:void(0);" onclick="showSubjectEdit(this);" style="display:inline;padding-right: 3px;" name="<s:property value="#list.id"/>">编辑</a>
							<a href="javascript:void(0);" onclick="showAdd(1,this);" style="display:inline;padding-right: 3px;">增(同)</a>
							<a href="javascript:void(0);" onclick="showAdd(2,this);" style="display:inline;padding-right: 3px;">增(下)</a>
							<a href="javascript:void(0);" onclick="showSubject(this);" style="display:inline;">关联科目</a>
							<input type="button" value="保存" style="display: none;" onclick="saveEditOnPage(this);" id="save">
							<input type="button" value="取消" style="display: none;" onclick="hideSubjectEdit(this);" id="cancel">
						</td>
					</tr>
					</s:if>
					<!-- 特殊行 -->
					<s:else>
					<tr id="special_line">
						<td class="t_l" >
							<input type="hidden" id="hide_id" value="<s:property value='#request.listWBS.get(#st.index).id'/>" />
							<input type="hidden" id="hide_parentId" value="<s:property value='#request.listWBS.get(#st.index).parentId'/>" />
							<input type="hidden" id="hide_level" value="<s:property value='#request.listWBS.get(#st.index).wbsLevel'/>">
							<input type="hidden" id="hide_order" value="<s:property value='#request.listWBS.get(#st.index).wbsOrder'/>">
							<input type="hidden" id="hide_specialOrder" value="<s:property value='#request.listWBS.get(#st.index).specialOrder'/>">
							<input type="hidden" id="hide_wbsId" value="<s:property value='#request.listWBS.get(#st.index).wbsId'/>">
							<input type="hidden" id="hide_dataType" value="<s:property value='#list.dataType'/>">
							<input type="checkbox" name="checkbox" value="<s:property value="#list.id"/>" >
						</td>
						<s:if test="#list.dataType==2">
							<td class="t_l"  colspan="9"><s:property value="#list.subjectName"/></td>
						</s:if>
						<s:elseif test="#list.dataType==3">
							<td></td>
							<td></td>
							<td colspan="3" class="t_r" ><s:property value="#list.subjectName"/></td>
							<td class="t_r" ><s:property value="#list.amount"/></td>
							<td class="t_r" ><s:property value="#list.totalPrice"/></td>
							<td colspan="2"></td>
						</s:elseif>
						
						<td class="t_c">
							<s:if test="#list.dataType==2">
							<a href="javascript:void(0);" onclick="showSubjectEditSpecial(this);" style="display:inline;" name="<s:property value="#list.id"/>">修改</a>
							</s:if>
							<input type="button" value="保存" style="display: none;" onclick="saveEditOnPageSpecial(this);">
							<input type="button" value="取消" style="display: none;" onclick="hideSubjectEditSpecial(this);">
						</td>
					</tr>
					</s:else>
				</s:iterator>
				<tr id="last">
					
					<td class="t_l" >
						<input type="hidden" value="<s:property value='#request.quantitiesList.id'/>" id="estimateId">
					</td>
					<td class="t_l" ></td>
					<td class="t_l" ></td>
					<td class="t_c" style="white-space: nowrap;font-weight: bold;">总计</td>
					<td></td>
					<td id="unit_2" class="t_r" style="white-space: nowrap;font-weight: bold;"></td>
					<td id="amount_2" class="t_r" style="white-space: nowrap;font-weight: bold;"><s:property value='#request.quantitiesList.projectAmount'/></td>
					<td class="t_r" ><s:property value='#request.quantitiesList.totalPrice'/></td>
					<td class="t_c"  colspan="2"></td>
					<td class="t_c">
						<a href="#" onclick="showQuantities(this);" style="display:inline;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveQuantitiesList(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideQuantitiesListEdit(this);">
					</td>
				</tr>
			</tbody>
			<tr class="tfoot">
			      <td colspan="11">
		       </td>
		     </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
	<script>
	//多选框实现单选
$(function(){
	checkbox();
	highLight();
	$("#listTitle").smartFloat();
	$("#listTitle").find("td").each(function(i,n){
		$(this).width($("#first").find("td").eq(i).width()+10);
	});
});
	</script>
</body>
</html>


