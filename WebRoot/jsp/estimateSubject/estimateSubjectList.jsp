<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en" >
<head>
<meta charset="utf-8" />
<title>投资概算管理</title>
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

//ie下实现placeHolder功能
$(document).ready(
	function(){
		var doc=document,
		inputs=doc.getElementsByTagName('input'),
		supportPlaceholder = 'placeholder'in doc.createElement('input'),
		placeholder = function(input){
			var text = input.getAttribute('placeholder'),
			defaultValue=input.defaultValue;
			if(defaultValue==''){
				input.value=text
			}
			input.onfocus=function(){
				if(input.value===text){
					this.value=''}
			};
			input.onblur=function(){
				if(input.value===''){
					this.value=text
				}
			}
		};
		if(!supportPlaceholder){
			for(var i=0,len=inputs.length;i<len;i++){
				var input=inputs[i],text=input.getAttribute('placeholder');
				if(input.type==='text'&&text){
					placeholder(input)
				}
			}
		}
	}
);

function enablePlaceHolder(){
	var doc=document,
	inputs=doc.getElementsByTagName('input'),
	supportPlaceholder = 'placeholder'in doc.createElement('input'),
	placeholder = function(input){
		var text = input.getAttribute('placeholder'),
		defaultValue=input.defaultValue;
		if(defaultValue==''){
			input.value=text
		}
		input.onfocus=function(){
			if(input.value===text){
				this.value=''}
		};
		input.onblur=function(){
			if(input.value===''){
				this.value=text
			}
		}
	};
	if(!supportPlaceholder){
		for(var i=0,len=inputs.length;i<len;i++){
			var input=inputs[i],
			text = input.getAttribute('placeholder');
			if(input.type==='text'&&text){
				placeholder(input)
			}
		}
	}
}


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
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		window.location.href = "findInvestEstimateSubjectByPage.action?projectId="+$("#projectId").val();
		clearInterval(refresh);
	}
}


</script> 
 
 
 
<script type="text/javascript">

var showAddStatus = false;
var inCalculate = false;


//显示概算编辑
function showEstimateEdit(target){

	var $unit = $(target).parent().parent().children("td:eq(3)");
	var $count = $(target).parent().parent().children("td:eq(4)");

	var unit_input ="<input name='unit_all' class='input_small' type='text' value='"+$unit.text()+"' id='"+$unit.text()+"'>"
	var amount_input="<input name='amount_all' class='input_small' type='text' style='text-align:right;' value='"+$count.text()+"' id='"+$count.text()+"'>"
	$unit.html(unit_input);
	$count.html(amount_input);
	
	$(target).hide();
	$(target).siblings("a").hide();
	
	$(target).siblings("input:first").attr("style","display:inline;");
	$(target).siblings("input:last").attr("style","display:inline;");
}


//隐藏概算编辑
function hideEstimateEdit(object){
	var $unit = $(object).parent().parent().children("td:eq(3)");
	var $count = $(object).parent().parent().children("td:eq(4)");
	var id = $unit.children().attr("id");
	if(id==null || id==""){
		$unit.html("");
	}else{
		$unit.html(id);
	}
	
	id = $count.children().attr("id");
	if(id==null || id==""){
		$count.html("");
	}else{
		$count.html(id);
	}
	
	$(object).hide();
	$(object).siblings("input").hide();
	$(object).siblings("a").attr("style","display:inline;");
	
}

//保存列表的第一行、最后一行，概算
function saveEstimate(target){
	var id = $(target).parent().parent().find("input:hidden").val();
	if($.isNumeric(id)){
		id = "'"+id+"'";
	}
	var projectCount = $(target).parent().parent().find("input:visible[type=text]:last").val();
	if(!$.isNumeric(projectCount)){
		alert("数量字段必须输入数字！");
		$(target).parent().parent().find("input:visible[type=text]:last").focus();
		return false;
	}
	$.ajax({
		type : "post",
		url : "<%=basePath%>/investEstimate/saveOrUpdateOnPage.action?random="+Math.random(),
		dataType:"json",
		cache : false,
		data:{
			id:id,
			projectId:$("#projectId").val(),
			projectName:$("#projectName").val(),
			projectUnit:$(target).parent().parent().find("input:visible[type=text]:first").val(),
			projectCount:projectCount
		},
		error:function(){
			alert("系统忙，请刷新页面后重新操作！")
		},
		success:function(object){
			alert("保存成功！");
			queryProject();
		}
	});
}



//显示新增区域,1：同级新增，2：下级新增
function showAdd(type,target){
	if(showAddStatus){
		alert("请先保存或取消新增行！");
		return false;
	}
	if($("#projectId").val()==null || $("#projectId").val()==""){
		$("input[name=projectNo_]").focus();
		alert("请先查询项目！");
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
	if(!$.isEmptyObject(target)){	
		wbsId = $.trim($(target).parent().parent().find("td:eq(1)").text().replace("-","").replace("+",""));
		level = $(target).parent().parent().find("#hide_level").val(); 
		order = $(target).parent().parent().find("#hide_order").val();
		if(type==1){	//同级新增,新增特殊行
			parentId = 	$(target).parent().parent().find("#hide_parentId").val();
		}else{		//下级新增
			parentId = $(target).parent().parent().find("#hide_id").val();
		}
	}else{								
		//勾选了checkbox
		if($("input:checked[name=checkbox]:last").val()!=null && $("input:checked[name=checkbox]:last").val()!=""){
			if($("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").length==0){
				wbsId = $.trim($("input:checked[name=checkbox]:last").parent().next().text().replace("-","").replace("+",""));
			}else{
				wbsId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_wbsId]").val();
			}
			level = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_level]").val();
			order = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_order]").val();
			if(type==1){	//同级新增
				parentId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_parentId]").val();	
			}else{		//下级新增
				parentId = $("input:checked[name=checkbox]:last").parent().parent().find("input[id=hide_id]").val();
			}
		}else{	//没有勾选checkbox,在最后新增

			
			var largestOrder = 0,index=-1;
			$("input[id=hide_level][value=1]:hidden").each(function(i){
				var order = $(this).siblings("#hide_order").val();
				if(parseInt(order) > largestOrder){
					largestOrder = parseInt(order);
					index = i;
				}
			});
			if(largestOrder!=0){
				$beforeTargetTr = $("input[id=hide_level][value=1]:hidden:eq("+index+")").parents("tr");
				wbsId = $.trim($beforeTargetTr.find("td:eq(1)").text());
				level = $beforeTargetTr.find("input[id=hide_level]").val();
				order = $beforeTargetTr.find("input[id=hide_order]").val();
				if(type==1){	//同级新增,新增特殊行
					parentId = $beforeTargetTr.find("input[id=hide_parentId]").val();
				}else{			//下级新增
					parentId = $beforeTargetTr.find("input[id=hide_id]").val();
				}
			}else{
				$beforeTargetTr = $("#first");
				wbsId = $.trim($("#last").prev().find("td:eq(1)").text());
				level = $("#last").prev().find("input[id=hide_level]").val();
				order = $("#last").prev().find("input[id=hide_order]").val();
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
			"<td colspan='2'><input name='wbsId' class='input_tiny' placeholder='WBS编号' value='"+wbsId+"' readonly='readonly' style='text-align:right;'>"+
			"<input id='parentId_save' value='"+parentId+"' type='hidden'>"+
			"<input id='level_save' value='"+level+"' type='hidden'>"+
			"<input id='order_save' value='"+order+"' type='hidden'>"+
			"<input id='type_save' value='1' type='hidden'>"+
			"<input id='ifHasSpecial' value='"+ifHasSpecial+"' type='hidden'>"+
			"<input id='specialOrder' value='"+specialOrder+"' type='hidden'>"+
			"</td>"+
			"<td><input name='subjectName' class='input_small' placeholder='科目名称'></td>"+
			"<td><input name='unit' class='input_small' placeholder='单位'></td>"+
			"<td><input name='amount' class='input_small' placeholder='数量'></td>"+
			"<td><input name='decide1' class='input_small' placeholder='建筑工程批复' style='text-align:right;'></td>"+
			"<td><input name='decide2' class='input_small' placeholder='安装工程批复' style='text-align:right;'></td>"+
			"<td><input name='decide3' class='input_small' placeholder='设备工器具批复' style='text-align:right;'></td>"+
			"<td><input name='decide4' class='input_small' placeholder='其他费用批复' style='text-align:right;'></td>"+
			"<td></td>"+
			"<td></td>"+
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
	
	enablePlaceHolder();
}


//隐藏新增区域
function hideAdd(target){
	$(target).parent().parent().remove();
	showAddStatus = false;
}

//保存新增
function saveOnPage(object,type){
	
	var wbsId = $("input[name=wbsId]").val();
	var subjectName = $("input[name=subjectName]").val();
	var unit = $("input[name=unit]").val();		//单位
	var projectAmount = $("input[name=amount]").val();		//数量
	var estimateDecideFirst = $("input[name=decide1]").val();
	var estimateDecideSecond = $("input[name=decide2]").val();
	var estimateDecideThird = $("input[name=decide3]").val();
	var estimateDecideOther = $("input[name=decide4]").val();
	var dataType = $("#type_save").val();
	var ifHasSpecial = $("#ifHasSpecial").val();//是否是特殊行
	var specialOrder = $("#specialOrder").val();
	var $previousTargetTr = $(object).parents("tr").prev();
	$("#targetNode").val($previousTargetTr.find("input[name=checkbox]").val());
	
	if($.trim(wbsId)==""){
		alert("目录不能为空！");
		$("input[name=wbsId]").focus();
		return false;	
	}
	if(subjectName=="科目名称"){
		subjectName="";
	}
	if($.trim(subjectName)==""){
		alert("科目名称不能为空！");
		$("input[name=subjectName]").focus();
		return false;	
	}
	if(unit=="单位"){
		unit="";
	}
	if($.trim(unit)==""){
		alert("单位不能为空！");
		$("input[name=unit]").focus();
		return false;
	}
	if(projectAmount==""){
		alert("数量不能为空！");
		$("input[name=amount]").focus();
		return false;
	}
	
	if(projectAmount!="" && !$.isNumeric(projectAmount)){
		alert("请输入正确的数字！");
		$("input[name=amount]").focus();
		return false;
	}
	
	if(estimateDecideFirst!="" && !$.isNumeric(estimateDecideFirst)){
		alert("请输入正确的数字！");
		$("input[name=decide1]").focus();
		return false;
	}
	if(estimateDecideSecond!="" && !$.isNumeric(estimateDecideSecond)){
		alert("请输入正确的数字！");
		$("input[name=decide2]").focus();
		return false;
	}
	if(estimateDecideThird!="" && !$.isNumeric(estimateDecideThird)){
		alert("请输入正确的数字！");
		$("input[name=decide3]").focus();
		return false;
	}
	if(estimateDecideOther!="" && !$.isNumeric(estimateDecideOther)){
		alert("请输入正确的数字！");
		$("input[name=decide4]").focus();
		return false;
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
			wbsId:$("input[name=wbsId]").val(),
			subjectName:$("input[name=subjectName]").val(),
			projectAmountUnit:$("input[name=unit]").val(),
			projectAmount:$("input[name=amount]").val(),
			estimateDecideFirst :$("input[name=decide1]").val(),
			estimateDecideSecond :$("input[name=decide2]").val(),
			estimateDecideThird :$("input[name=decide3]").val(),
			estimateDecideOther :$("input[name=decide4]").val(),
			projectId:$("input[name=projectId]").val(),
			projectName:$("input[name=projectName]").val(),
			dataType:dataType,
			parentId:parentId,
			level:level,
			order:order,
			ifHasSpecial:ifHasSpecial,
			specialOrder:specialOrder
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
			},
		success:function(object){
			if(object!=null && object.investEstimateSubject!=null){
				var html= ""+
				"<tr id='show_common' class='on' style=\"background-color:rgb(226, 231, 243)\">"+
							"<td class='t_c' style='white-space: nowrap;'>"+
								"<input type='checkbox' name='checkbox' value="+object.investEstimateSubject.id+">&nbsp;"+
								"<input type='hidden' id='hide_id' value="+object.wbs.id+">"+
								"<input type='hidden' id='hide_parentId' value="+object.wbs.parentId+">"+
								"<input type='hidden' id='hide_level' value="+object.wbs.wbsLevel+">"+
								"<input type='hidden' id='hide_order' value="+object.wbs.wbsOrder+">"+
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
							"<td class='t_c' style='white-space: nowrap;'>"+object.investEstimateSubject.subjectName+"</td>"+
							"<td class='t_c' style='white-space: nowrap;'>"+object.investEstimateSubject.projectAmountUnit+"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>"+object.investEstimateSubject.projectAmount+"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.estimateDecideFirst)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.estimateDecideFirst).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.estimateDecideSecond)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.estimateDecideSecond).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.estimateDecideThird)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.estimateDecideThird).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.estimateDecideOther)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.estimateDecideOther).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.estimateDecideSum)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.estimateDecideSum).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_r' style='white-space: nowrap;'>";
							if(parseFloat(object.investEstimateSubject.economicIndicator)==0){
								html+= 0;
							}else{
								html+= parseFloat(object.investEstimateSubject.economicIndicator).toFixed(4);
							}	
							html+=	"</td>"+
							"<td class='t_c'>"+
								//"&nbsp;<a href='showView.action?id="+object.investEstimateSubject.id+"' target='_blank' style='display:inline;'>查</a>&nbsp;&nbsp;&nbsp;"+
								"<a href='javascript:void(0);' onclick='showSubjectEdit(this);' style='display:inline;'>编辑</a>&nbsp;"+
								"<a href='javascript:void(0);' onclick='showAdd(1,this);' style='display:inline;'>增(同)</a>&nbsp;"+
								"<a href='javascript:void(0);' onclick='showAdd(2,this);' style='display:inline;'>增(下)</a>"+
								"<input type='button' value='保存' style='display: none;' onclick='saveEditOnPage(this);'>&nbsp;"+
								"<input type='button' value='取消' style='display: none;' onclick='hideSubjectEdit(this);'>"+
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

//保存概算编辑
function saveEditOnPage(target){

	var subjectName = $(target).parent().siblings("td:eq(2)").children("input").val();
	var projectAmountUnit = $(target).parent().siblings("td:eq(3)").children("input").val();
	var projectAmount = $.trim($(target).parent().siblings("td:eq(4)").children("input").val());
	var estimateDecideFirst = $(target).parent().siblings("td:eq(5)").children("input").val();
	var estimateDecideSecond = $(target).parent().siblings("td:eq(6)").children("input").val();
	var estimateDecideThird = $(target).parent().siblings("td:eq(7)").children("input").val();
	var estimateDecideOther = $(target).parent().siblings("td:eq(8)").children("input").val();
	var $targetTr = $(target).parents("tr");
	$("#targetNode").val($targetTr.find("input[name=checkbox]").val());

	if($.trim(subjectName)==""){
		alert("科目名称不能为空！");
		$(target).parent().siblings("td:eq(2)").children("input").focus();
		return false;	
	}
	
	if($.trim(projectAmountUnit)==""){
		alert("单位不能为空！");
		$(target).parent().siblings("td:eq(3)").children("input").focus();
		return false;
	}
	
	if($.trim(projectAmount)==""){
		alert("数量不能为空！");
		$(target).parent().siblings("td:eq(4)").children("input").focus();
		return false;
	}
	
	if(projectAmount!="" && !$.isNumeric(projectAmount)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(4)").children("input").focus();
		return false;
	}
	
	if(estimateDecideFirst!="" && !$.isNumeric(estimateDecideFirst)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(5)").children("input").focus();
		return false;
	}else{
		if(estimateDecideFirst=="") estimateDecideFirst="0";
	}
	if(estimateDecideSecond!="" && !$.isNumeric(estimateDecideSecond)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(6)").children("input").focus();
		return false;
	}else{
		if(estimateDecideSecond=="") estimateDecideSecond="0";
	}
	if(estimateDecideThird!="" && !$.isNumeric(estimateDecideThird)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(7)").children("input").focus();
		return false;
	}else{
		if(estimateDecideThird=="") estimateDecideThird="0";
	}
	if(estimateDecideOther!="" && !$.isNumeric(estimateDecideOther)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(8)").children("input").focus();
		return false;
	}else{
		if(estimateDecideOther=="") estimateDecideOther = "0";
	}


	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveEditOnPage.action",
		data:{
			id:$(target).parent().parent().children("td:first").children("input").val(),
			subjectName:subjectName,
			projectAmountUnit:projectAmountUnit,
			projectAmount:projectAmount,
			estimateDecideFirst :estimateDecideFirst,
			estimateDecideSecond :estimateDecideSecond,
			estimateDecideThird :estimateDecideThird,
			estimateDecideOther :estimateDecideOther,
			projectId:$("input[name=projectId]").val(),
			projectName:$("input[name=projectName]").val()
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
			},
		success:function(object){
			alert("保存成功！");
			var decide1,decide2,decide3,decide4,decideAll,decideEconomy ;
			decide1 = parseFloat(estimateDecideFirst)==0?0:parseFloat(estimateDecideFirst).toFixed(4);
			decide2 = parseFloat(estimateDecideSecond)==0?0:parseFloat(estimateDecideSecond).toFixed(4);
			decide3 = parseFloat(estimateDecideThird)==0?0:parseFloat(estimateDecideThird).toFixed(4);
			decide4 = parseFloat(estimateDecideOther)==0?0:parseFloat(estimateDecideOther).toFixed(4);
			decideAll = parseFloat(decide1)+ parseFloat(decide2)+ parseFloat(decide3)+ parseFloat(decide4);
			if(decideAll!=0){
				decideAll = decideAll.toFixed(4);
				if(parseFloat(projectAmount)!=0){
					decideEconomy = parseFloat(decideAll) / parseFloat(projectAmount);
					decideEconomy = decideEconomy.toFixed(4);			
				}else{
					decideEconomy = decideAll;
				}
			}else{
				decideEconomy = 0;
			}
			
			$targetTr.find("td:eq(2)").html(subjectName);
			$targetTr.find("td:eq(3)").html(projectAmountUnit);
			$targetTr.find("td:eq(4)").html(projectAmount);
			$targetTr.find("td:eq(5)").html(decide1);
			$targetTr.find("td:eq(6)").html(decide2);
			$targetTr.find("td:eq(7)").html(decide3);
			$targetTr.find("td:eq(8)").html(decide4);
			$targetTr.find("td:eq(9)").html(decideAll);
			$targetTr.find("td:eq(10)").html(decideEconomy);
			
			$targetTr.find("td:eq(11)").children("input").attr("style","display:none;");
			$targetTr.find("td:eq(11)").children("a").attr("style","display:inline;");
			/*
			$targetTr.find("td:eq(11)").children("a").each(function(){
				$(this).attr("style","display:inline;");
			});
			*/
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
				queryProject();
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
		var id,parentId,projectId;
		id = $targetTr.find("#hide_id").val();
		parentId = $targetTr.find("#hide_parentId").val();
		projectId = $("input[name=projectId]").val();
		
		$.ajax({
			type : 'post',
			url : 'deleteAll.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:$targetTr.find("#hide_id").val(),
				parentId:$targetTr.find("#hide_id").val(),
				projectId:$("input[name=projectId]").val()
			},
			error:function(){
				alert("系统忙，请刷新页面后重新操作！")
			},
			success:function(object){
			alert("删除成功！");
				queryProject();
			}
		});
	}

}

//显示科目编辑
function showSubjectEdit(target){
	
	//隐藏 “查看”和“编辑”按钮，显示保存和取消
	$(target).parent().children("input:first").attr("style","display:inline;");
	$(target).parent().children("input:last").attr("style","display:inline;");
	$(target).parent().children("a").attr("style","display:none");
	/*
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:none");
	});
	*/
	var value ="";			//初始值
	var addHtml = "";		//要添加的html
	var $targetObject;
	
	//2.科目名称
	$targetObject = $(target).parent().siblings("td:eq(2)");
	value = $targetObject.text();
	addHtml = "<input name='subjectName_' value='"+value+"' id='"+value+"' class='input_small' type='text'>";
	$targetObject.html(addHtml);
	
	//3.单位
	$targetObject = $(target).parent().siblings("td:eq(3)");
	value = $targetObject.text();
	addHtml = "<input name='unit_' value='"+value+"' id='"+value+"' class='input_tiny' type='text'>";
	$targetObject.html(addHtml);
	
	//4.数量
	$targetObject = $(target).parent().siblings("td:eq(4)");
	value = $targetObject.text();
	addHtml = "<input name='amount_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);

	//5.建筑工程批复
	$targetObject = $(target).parent().siblings("td:eq(5)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='decide1_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);
	
	//6.安装工程批复
	$targetObject = $(target).parent().siblings("td:eq(6)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='decide2_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);
	
	//7.设备工器具批复
	$targetObject = $(target).parent().siblings("td:eq(7)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='descide3_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);
	
	//8.其他费用批复
	$targetObject = $(target).parent().siblings("td:eq(8)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='decide4_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);

}

//隐藏编辑科目
function hideSubjectEdit(target){
	//显示 “查看”和“编辑”按钮，隐藏保存和取消
	$(target).parent().children("input").attr("style","display:none;");
	$(target).parent().children("a").attr("style","display:inline");
	/*
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:inline");
	});
	*/
	
	//2.科目名称
	$targetObject = $(target).parent().siblings("td:eq(2)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//3.单位
	$targetObject = $(target).parent().siblings("td:eq(3)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//4.数量
	$targetObject = $(target).parent().siblings("td:eq(4)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//5.建筑工程批复
	$targetObject = $(target).parent().siblings("td:eq(5)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//6.安装工程批复
	$targetObject = $(target).parent().siblings("td:eq(6)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//7.设备工器具批复
	$targetObject = $(target).parent().siblings("td:eq(7)");
	$targetObject.html($targetObject.children("input").attr("id"));
	//8.其他费用批复
	$targetObject = $(target).parent().siblings("td:eq(8)");
	$targetObject.html($targetObject.children("input").attr("id"));
}


//自动提示,项目编号
$(function(){
	$("#projectNo_" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByProjectNoWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectNo" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.projectNo,
							value: item.id
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
		},
		select: function( event, ui ) {
			
			$("#projectNo_").val(ui.item.label);
			$("#projectId_").val(ui.item.value);
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
			$("#projectId_").val("");
		}
	});

});

//自动提示,项目名称
$(function(){
	$("#projectName_" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "<%=basePath%>/project/findProjectByProjectNameWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"projectName" : request.term									
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.projectName,
							value: item.id
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
		},
		select: function( event, ui ) {
			$("#projectName_").val(ui.item.label);
			$("#projectId_").val(ui.item.value);
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
			$("#projectId_").val("");
		}
	});

});

//点击查询项目
function queryProject(){
	var projectId = $("#projectId_").val();
	$("#projectId").val(projectId);
	if(projectId==null || projectId==""){
		alert("该项目不存在，请重新查询！");
		return false;
	}
	var hideNodes = "";
	$("[id=minus]").each(function(){
		if($(this).parents("tr").attr("style")==null||$(this).parents("tr").attr("style").indexOf("display")==-1){
			if(hideNodes.length>0){
				hideNodes += ",";
			}
			hideNodes += $(this).parents("tr").find("#hide_id").val();
		}
	});
	$("#hideNodes").val(hideNodes);
	$("#form").submit();
}



//重新计算功能
function compute(){
	if(!inCalculate){
		if(confirm("是否重新计算？")){
			inCalculate = true;
			var ids="",decides="";
			var $targetTr;
			$("input[id=hide_dataType][value=3]:hidden").each(function(){
				$targetTr = $(this).parents("tr");
				ids += $targetTr.find("input[name=checkbox]").val()+",";
				decides += $.trim($targetTr.find("td:eq(4)").html())+","+$.trim($targetTr.find("td:eq(5)").html())+","+
				$.trim($targetTr.find("td:eq(6)").html())+","+$.trim($targetTr.find("td:eq(7)").html())+","+$.trim($targetTr.find("td:eq(8)").html())+"-";
			});
			if(ids!="")
				ids = ids.substring(0,ids.length-1);
			if(decides!="")
				decides = decides.substring(0,decides.length-1);
			$.ajax({
				type : 'post',
				url : 'compute.action?random='+Math.random(),
				dataType:'json',
				cache : false,
				data:{
					projectId:$("#projectId").val(),
					specilDataIds:ids,
					decides:decides
				},
				error:function(){
					alert("系统忙，请刷新页面后重新操作！")
				},
				success:function(object){
					alert("计算成功！");
					queryProject();
				}
			});
		}
	}else{
		alert("计算中，请耐心等待！");
	}
	
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


//跳转到目标控制
function showControl(){
	var url = "/investCost/estimateSubject/findInvestEstimateSubjectByPage.action?type=view&projectId="+$("#projectId").val();
	window.open(url);
}
function showAddSpecial(){
	if($("input:checked[name=checkbox]").length==0){
		alert("请先勾选要添加特殊行的位置！");
		return false;
	}
	if(showAddStatus){
		alert("请先保存或取消新增行！");
		return false;
	}
	if($("#projectId").val()==null || $("#projectId").val()==""){
		$("input[name=projectNo_]").focus();
		alert("请先查询项目！");
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
			"<td colspan='2' class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='bak'></td>"+
			"<td class='t_c'>"+
				"<input type='button' value='保存' onclick='saveOnPageSpecial(this);'>"+
				"<input type='button' value='取消' onclick='hideAdd(this)'>"+
			"</td>"+
		"</tr>";	
	$("input:checked[name=checkbox]:last").parent().parent().after(input_html);
	showAddStatus = true;
}

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
		var projectId = $("input[name=projectId]").val();
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
				$(obj).parent().parent().find("input[id=subjectName]").focus();
				$(obj).attr("disabled",false);
				$(obj).siblings("input").attr("disabled",false);
				return false;
			}
			
			$.ajax({
				type:"post",
				dataType:"json",
				url:"saveAddOnPageSpecial.action",
				data:{
					projectId:projectId,
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
					queryProject();
				}
			});
		}else if(type=="3"){
			var subjectName = $.trim($(obj).parent().parent().find("#subjectName").html());
			var estimateDecideFirst = $.trim($(obj).parent().parent().find("#estimateDecideFirst").html());
			var estimateDecideSecond = $.trim($(obj).parent().parent().find("#estimateDecideSecond").html());
			var estimateDecideThird = $.trim($(obj).parent().parent().find("#estimateDecideThird").html());
			var estimateDecideOther = $.trim($(obj).parent().parent().find("#estimateDecideOther").html());
			var estimateDecideSum = $.trim($(obj).parent().parent().find("#estimateDecideSum").html());
			
			$.ajax({
				type:"post",
				dataType:"json",
				url:"saveAddOnPageSpecial.action",
				data:{
					projectId:projectId,
					dataType:type,
					parentId:parentId,
					subjectName:subjectName,
					level:level,
					order:order,
					specialOrder:specialOrder,
					wbsId:wbsId,
					estimateDecideFirst:estimateDecideFirst,
					estimateDecideSecond:estimateDecideSecond,
					estimateDecideThird:estimateDecideThird,
					estimateDecideOther:estimateDecideOther,
					estimateDecideSum:estimateDecideSum
				},
				error:function(){
						alert("系统忙，请刷新页面后重新操作！");
					},
				success:function(object){
					alert("保存成功！");
					queryProject();
				}
			});
		}
		
	}
}

function showSpecial(obj){
	if($(obj).val()=='2'){//注释行
		$(obj).parent().parent().find(".bak").remove();
		$(obj).parent().after("<td class='bak' colspan='8'><input type='text' style='width:100%' id='subjectName'/></td>");
		$("#subjectName").focus();
	}else if($(obj).val()=='3'){
		var arraySum = rootSum();
		$(obj).parent().parent().find(".bak").remove();
		var addHtml = "<td colspan='2' class='bak'  style='text-align:right' id='subjectName'>以上各项合计：</td>"+
					"<td class='bak' style='text-align:right' id='estimateDecideFirst'>"+arraySum[0]+"</td>"+
					"<td class='bak' style='text-align:right' id='estimateDecideSecond'>"+arraySum[1]+"</td>"+
					"<td class='bak' style='text-align:right' id='estimateDecideThird'>"+arraySum[2]+"</td>"+
					"<td class='bak' style='text-align:right' id='estimateDecideOther'>"+arraySum[3]+"</td>"+
					"<td class='bak' style='text-align:right' id='estimateDecideSum'>"+arraySum[4]+"</td>"+
					"<td class='bak'></td>";
		$(obj).parent().after(addHtml);
	}
}

function rootSum(){
	var num;
	var sum1 =0;
	var sum2 =0;
	var sum3 =0;
	var sum4 =0;
	var sum5 =0;
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
			sum1 += parseFloat(tr.children("td:eq(5)").html());
			sum2 += parseFloat(tr.children("td:eq(6)").html());
			sum3 += parseFloat(tr.children("td:eq(7)").html());
			sum4 += parseFloat(tr.children("td:eq(8)").html());
			sum5 += parseFloat(tr.children("td:eq(9)").html());
		}
	}
	arraySum[0] = sum1.toFixed(4);
	arraySum[1] = sum2.toFixed(4);
	arraySum[2] = sum3.toFixed(4);
	arraySum[3] = sum4.toFixed(4);
	arraySum[4] = sum5.toFixed(4);
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
	}else{
		if(subjectName.length>80){
			alert("注释不能超过80个字！");
			tr.find("input[name=subjectName_]").focus();
			return false;
		}
	}
	
	if(tr.find("#hide_dataType").val()=='2'){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"saveEditOnPageSpecial.action",
			data:{
				id:tr.children("td:first").children("input").val(),
				subjectName:subjectName
			},
			error:function(){
					alert("系统忙，请刷新页面后重新操作！");
				},
			success:function(object){
				alert("保存成功！");
				queryProject();
			}
		});
	}
}

//导出Excel
function exportExcel(){
	$("#ifExport").val("yes");
	$("#form").submit();
	$("#ifExport").val("");
}



//双击某一行第三个td修改，单击某一行还原样式
function highLight(){
	$("#tbody").find("#show_common").each(function(){
		if($(this).find("#hide_dataType").length==0){
			$(this).children("td:eq(2)").dblclick(function(){
				$(this).siblings("td:last").children("a:eq(1)").click();
			});
			//$(this).click(function(){
			//	$(this).attr("style","");
			//});
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

$.fn.smartFloat = function() {
    var position = function(element) {
        var top = element.position().top, pos = element.css("position");
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

</script>


 
 
</head>

<body>
<input name="projectName" value="<s:property value='#request.project.projectName' />" type="hidden" id="projectName">
<input name="showOrHide" type="hidden">
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li class="fin">投资概算管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#" onclick="window.print();">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl(this);">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8" id="searchArea">
			<s:form action="findInvestEstimateSubjectByPage" name="estimateSubject" namespace="/estimateSubject" method="post" id="form">
			    <input type="hidden" id="projectId" name="projectId" value="<s:property value='#request.project.id'/>"/>
				<input type="hidden" id="hideNodes" name="hideNodes" value="<s:property value='#request.hideNodes'/>"/>
				<input type="hidden" id="targetNode" name="targetNode" value="<s:property value='#request.targetNode'/>"/>
				<input type="hidden" name="ifExport" id="ifExport"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%;" class="t_r" >项目编号：</td>
						<td width="25%;"  >
							<input type="text" name="projectNo_" id="projectNo_" class="input_large" value="<s:property value='#request.project.projectNo'/>" />
							<input type="hidden" name="projectId_" id="projectId_" value="<s:property value='#request.project.id'/>" />
							<input type="button" value="查询" onclick="queryProject();">
						</td>
						<td width="10%;" class="t_r" >项目名称：</td>
						<td width="35%;" >
							<input type="text" name="projectName_" id="projectName_" class="input_large" value="<s:property value='#request.project.projectName'/>" />
							<input type="button" value="查询" onclick="queryProject();">
						</td>
						<td class="t_r" >负责部门：</td>
						<td >
							<s:property value="#request.project.projectAdddept"/>
						</td>
					</tr>
					<tr >
						<td class="t_r" >计划时间：</td>
						<td >
							<s:property value="#request.project.projectStarttimePlanDate"/>&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;
							<s:property value="#request.project.projectEndtimePlanDate"/>
						</td>
						<td class="t_r" >负责人：</td>
						<td >
							<s:property value="#request.project.projectAddperson"/>
						</td>
						<td class="t_r" >投资估算总额(万元)：</td>
						<td >
							<s:if test="#request.project.projectBudgetAll==null || #request.project.projectBudgetAll==0">0</s:if>
							<s:else>
								<s:property value="#request.project.projectBudgetAll"/>&nbsp;&nbsp;
							</s:else>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			
			<div class="fn">
				<input type="button" value="同级新增" class="fl mr5" onclick="showAdd(1);" >
				<input type="button" value="下级新增" class="fl mr5" onclick="showAdd(2);" >
				<input type="button" value="新增特殊行" class="fl mr5" onclick="showAddSpecial();" >
				<!-- <input type="button" value="导入" class="fl mr5" > -->
				
				
				<input type="button" value="删除" class="mr5" style="float: right;" onclick="deleteAllChecked();">
				 <input type="button" value="导出" class="mr5" style="float: right;" onclick="exportExcel()">
				<input type="button" value="重新计算" class="mr5" style="float: right;" onclick="compute();">
				<input type="button" value="目标控制" class="mr5" style="float: right;" onclick="showControl();">
				
			</div>
			
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			
			<!--投资概算科目列表  -->			
			<thead>
				<th colspan="14">&nbsp;&nbsp;投资概算科目列表</th>
			</thead>
			
			<tbody id="tbody">
				<tr class="tit" id="listTitle">
					<td width="2%;" >
						<!-- <input type="checkbox" id="checkAll" value="1"> -->
					</td>
					<td width="6%;" >WBS编码</td>
					<td width="10%;" class="t_r" >科目名称</td>
					<td width="5%;" class="t_r" >单位</td>
					<td width="7%;" class="t_r" >数量</td>
					<td width="10%;" class="t_r">
						<span>建筑工程批复<br/>(万元)</span>
					</td>
					<td width="10%;" class="t_r"  >安装工程批复<br/>(万元)</td>
					<td width="10%;" class="t_r"  >设备工器具批复<br/>(万元)</td>
					<td width="10%;" class="t_r"  >其他费用批复<br/>(万元)</td>
					<td width="10%;" class="t_r"  >概算批复值<br/>(合计)(万元)</td>
					<td width="8%;" class="t_r"  >经济指标<br/>(单价)(万元)</td>
					<td width="17%;" class="t_r" colspan="3" >
						操作&nbsp;&nbsp;&nbsp;
						<a href="#" style="display: inline;"><img title="刷新" alt="刷新" src="../images/refresh.png" onclick="queryProject();" style="display: inline;width: 20px;height: 20px;"></a>
						<a href="#" onclick="showAddSpecial();">新增特殊行</a>
					</td>
				</tr>
				<tr id="first">
					<td width="2%;" class="t_c" >
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td width="6%;" class="t_c" ></td>
					<td width="10%;" class="t_c" style="font-weight: bold;">总计</td>
					<td width="5%;" id="unit_1" class="t_c" style="font-weight: bold;"><s:property value='#request.investEstimate.projectUnit'/></td>
					<td width="7%;" id="amount_1" class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.projectCount'/></td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideFirst==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideFirst.substring(0,#request.investEstimate.estimateDecideFirst.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideSecond==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideSecond.substring(0,#request.investEstimate.estimateDecideSecond.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideThird==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideThird.substring(0,#request.investEstimate.estimateDecideThird.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideOther==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideOther.substring(0,#request.investEstimate.estimateDecideOther.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" style="font-weight: bold;" >
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td width="8%;" class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td width="17%;" class="t_c">
						<a href="#" onclick="showEstimateEdit(this);" style="display:inline;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveEstimate(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideEstimateEdit(this);">
					</td>
				</tr>
				
				
				<s:iterator value="#request.InvestEstimateSubjectList" id="list" status="st">
					<!-- 普通行 -->
					<s:if test="#request.listWBS.get(#st.index).type==1">
					<tr id="show_common" class="off" style="display:none">
						<td class="t_l" >
							<input type="checkbox" name="checkbox" value="<s:property value="#list.id"/>" >
							<input type="hidden" id="hide_id" value="<s:property value='#request.listWBS.get(#st.index).id'/>" />
							<input type="hidden" id="hide_parentId" value="<s:property value='#request.listWBS.get(#st.index).parentId'/>" />
							<input type="hidden" id="hide_level" value="<s:property value='#request.listWBS.get(#st.index).wbsLevel'/>">
							<input type="hidden" id="hide_order" value="<s:property value='#request.listWBS.get(#st.index).wbsOrder'/>">
						</td>
						<td class="t_l" >
							<p style="display: inline;">
								<font style="display: inline;">
								<s:if test="#request.listWBS.get(#st.index).wbsLevel==2">&nbsp;</s:if>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==3">&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:elseif test="#request.listWBS.get(#st.index).wbsLevel==5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:elseif>
								<s:if test="#request.listWBS.get(#st.index+1).parentId==#request.listWBS.get(#st.index).id||#request.listWBS.get(#st.index+2).parentId==#request.listWBS.get(#st.index).id||#request.listWBS.get(#st.index+3).parentId==#request.listWBS.get(#st.index).id">
									<!-- 
									<a href="#" style="display: inline;background-image:url('../images/tree_1.png') right -23px repeat-x;" onclick="showHierarchy(this);">-</a>
									 -->
									<img src="../images/plus.png" onclick="showHierarchy(this);" style="display:inline" id="plus"/>
								</s:if>
								<s:else>&nbsp;&nbsp;&nbsp;</s:else>
								</font>
								<s:property value="#request.listWBS.get(#st.index).wbsId"/>
							</p>
						</td>
						<td class="t_c" ><s:property value="#list.subjectName"/></td>
						<td class="t_c" ><s:property value="#list.projectAmountUnit"/></td>
						<td class="t_r" ><s:property value="#list.projectAmount"/></td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideFirst==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideFirst.substring(0,#list.estimateDecideFirst.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideSecond==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideSecond.substring(0,#list.estimateDecideSecond.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideThird==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideThird.substring(0,#list.estimateDecideThird.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideOther==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideOther.substring(0,#list.estimateDecideOther.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideSum==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideSum.substring(0,#list.estimateDecideSum.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.economicIndicator==0">0</s:if>
							<s:else><s:property value="#list.economicIndicator.substring(0,#list.economicIndicator.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showSubjectEdit(this);" style="display:inline;" name="<s:property value="#list.id"/>">编辑</a>
							<a href="javascript:void(0);" onclick="showAdd(1,this)" style="display:inline;">增(同)</a>
							<a href="javascript:void(0);" onclick="showAdd(2,this)" style="display:inline;">增(下)</a>
							<input type="button" value="保存" style="display: none;" onclick="saveEditOnPage(this);">
							<input type="button" value="取消" style="display: none;" onclick="hideSubjectEdit(this);">
						</td>
					</tr>
					</s:if>
					<!-- 特殊行 -->
					<s:else>
					<tr id="special_line">
						<td class="t_l" >
							<input type="checkbox" name="checkbox" value="<s:property value="#list.id"/>" >
							<input type="hidden" id="hide_id" value="<s:property value='#request.listWBS.get(#st.index).id'/>" />
							<input type="hidden" id="hide_parentId" value="<s:property value='#request.listWBS.get(#st.index).parentId'/>" />
							<input type="hidden" id="hide_level" value="<s:property value='#request.listWBS.get(#st.index).wbsLevel'/>">
							<input type="hidden" id="hide_order" value="<s:property value='#request.listWBS.get(#st.index).wbsOrder'/>">
							<input type="hidden" id="hide_specialOrder" value="<s:property value='#request.listWBS.get(#st.index).specialOrder'/>">
							<input type="hidden" id="hide_wbsId" value="<s:property value='#request.listWBS.get(#st.index).wbsId'/>">
							<input type="hidden" id="hide_dataType" value="<s:property value='#list.dataType'/>">
						</td>
						<s:if test="#list.dataType==2">
							<td class="t_l"  colspan="10"><s:property value="#list.subjectName"/></td>
						</s:if>
						<s:elseif test="#list.dataType==3">
							<td></td>
							<td></td>
							<td colspan="2" class="t_r" ><s:property value="#list.subjectName"/></td>
							<td class="t_r" >
								<s:if test="#list.estimateDecideFirst==0">0</s:if>
								<s:else><s:property value="#list.estimateDecideFirst.substring(0,#list.estimateDecideFirst.indexOf('.')+5)"/></s:else>
							</td>
							<td class="t_r" >
								<s:if test="#list.estimateDecideSecond==0">0</s:if>
								<s:else><s:property value="#list.estimateDecideSecond.substring(0,#list.estimateDecideSecond.indexOf('.')+5)"/></s:else>
							</td>
							<td class="t_r" >
								<s:if test="#list.estimateDecideThird==0">0</s:if>
								<s:else><s:property value="#list.estimateDecideThird.substring(0,#list.estimateDecideThird.indexOf('.')+5)"/></s:else>
							</td>
							<td class="t_r" >
								<s:if test="#list.estimateDecideOther==0">0</s:if>
								<s:else><s:property value="#list.estimateDecideOther.substring(0,#list.estimateDecideOther.indexOf('.')+5)"/></s:else>
							</td>
							<td class="t_r" >
								<s:if test="#list.estimateDecideSum==0">0</s:if>
								<s:else><s:property value="#list.estimateDecideSum.substring(0,#list.estimateDecideSum.indexOf('.')+5)"/></s:else>
							</td>
							<td></td>
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
				<tr id="last" >
					
					<td class="t_c" >
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" ></td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td id="unit_1" class="t_c" style="font-weight: bold;"><s:property value='#request.investEstimate.projectUnit'/></td>
					<td id="amount_1" class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.projectCount'/></td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideFirst==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideFirst.substring(0,#request.investEstimate.estimateDecideFirst.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideSecond==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideSecond.substring(0,#request.investEstimate.estimateDecideSecond.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideThird==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideThird.substring(0,#request.investEstimate.estimateDecideThird.indexOf('.')+5)"/></s:else>
					</td>
					<td width="10%;" class="t_r" >
						<s:if test="#request.investEstimate.estimateDecideOther==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideOther.substring(0,#request.investEstimate.estimateDecideOther.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td class="t_c">
						<a href="#" onclick="showEstimateEdit(this);" style="display:inline;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveEstimate(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideEstimateEdit(this);">
					</td>
				</tr>
				
			</tbody>
			<tr class="tfoot">
				<td colspan="14"></td>
		   </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
	
<script type="text/javascript">
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



