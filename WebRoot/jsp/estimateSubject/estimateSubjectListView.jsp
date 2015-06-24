<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en" >
<head>
<meta charset="utf-8" />
<title>投资概算管理-目标控制</title>
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
	
	.highLight{
		background-color: #FFF554; 
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
<!--<script src="../js/html5.js"></script>-->
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/wbs_common.js"></script>
<%String basePath = request.getContextPath(); %>
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
        });
//页面初始化时隐藏搜索条件
$(function(){
	var status = $("input[name='showOrHide']").val();
	if(status=="hide")
		$("#searchArea").css("display","none");
	else
		$("#searchArea").css("display","block");
});

//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name='showOrHide']").val();
	$li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$("#searchArea").slideDown();
		$("input[name='showOrHide']").val("show");
	}else{
		$li.removeClass("selected");
		$("#searchArea").slideUp();
		$("input[name='showOrHide']").val("hide");
	}
}        
</script>
 
 
<script type="text/javascript">

//显示科目编辑
function showSubjectEdit(target){
	
	//隐藏 “查看”和“编辑”按钮，显示保存和取消
	$(target).parent().children("input:first").attr("style","display:inline;");
	$(target).parent().children("input:last").attr("style","display:inline;");
	
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:none");
	});
	
	var value ="";			//初始值
	var addHtml = "";		//要添加的html
	var $targetObject;
	
	$targetObject = $(target).parent().siblings("td:eq(6)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='objectAdjustValue_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	$targetObject.html(addHtml);
	
	$targetObject = $(target).parent().siblings("td:eq(7)");
	value = $.trim($targetObject.text());
	addHtml = "<input name='objectControlValue_' value='"+value+"' id='"+value+"' class='input_small' type='text' style='text-align:right;'>";
	
	$targetObject.html(addHtml);

}

//隐藏编辑科目
function hideSubjectEdit(target){
	//显示 “查看”和“编辑”按钮，隐藏保存和取消
	$(target).parent().children("input").attr("style","display:none;");
	
	$(target).parent().children("a").each(function(){
		$(this).attr("style","display:inline");
	});
	
	var id;
	$targetObject = $(target).parent().siblings("td:eq(7)");
	id = $targetObject.children("input").attr("id");
	if(id==null || id==""){
		$targetObject.html("");		
	}else{
		$targetObject.html(id);
	}
	
	$targetObject = $(target).parent().siblings("td:eq(6)");
	id = $targetObject.children("input").attr("id");
	if(id==null || id==""){
		$targetObject.html("");		
	}else{
		$targetObject.html(id);
	}
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
	$("[id='minus']").each(function(){
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




//显示下级,并且保留原来的层级显示和隐藏
function showH(id){
	$("#tbody").find("input[id=hide_parentId][value='"+id+"']").each(function(){
		$(this).parents("tr").show();
		if($(this).parents("tr").attr("class")=="on"){
			showH($(this).siblings("#hide_id").val());
		}
	});
}

function hideH(id){		//隐藏下级所有
	$("#tbody").find("input[id=hide_parentId][value='"+id+"']").each(function(){
		$(this).parents("tr").hide();
		hideH($(this).siblings("#hide_id").val());
	});
}

//保存概算编辑
function saveEditOnPage(target){

	var objectControlValue = $(target).parent().siblings("td:eq(7)").children("input").val();
	if(objectControlValue!="" && !$.isNumeric(objectControlValue)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(7)").children("input").focus();
		return false;
	}
	
	var objectAdjustValue = $(target).parent().siblings("td:eq(6)").children("input").val();
	if(objectAdjustValue!="" && !$.isNumeric(objectAdjustValue)){
		alert("请输入正确的数字！");
		$(target).parent().siblings("td:eq(6)").children("input").focus();
		return false;
	}
	var adjustValue = $(target).parent().siblings("td:eq(5)").text();
	if(objectAdjustValue!=null && objectAdjustValue!=""){
		if(parseFloat(objectControlValue) > parseFloat(objectAdjustValue)){
			if(!confirm("目标控制值大于调整概算值是否确认保存？")){
				return false;
			}
		}
	}else{
		if(parseFloat(objectControlValue) > parseFloat(adjustValue)){
			if(!confirm("目标控制值大于概算批复值是否确认保存？")){
				return false;
			}
		}
	}
	
	
	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveObjectControlValue.action",
		data:{
			id:$(target).parent().parent().children("td:first").children("input").val(),
			objectControlValue :objectControlValue,
			objectAdjustValue : objectAdjustValue,
			projectId:$("input[name='projectId']").val(),
			projectName:$("input[name='projectName']").val()
		},
		error:function(){
				alert("系统忙，请刷新页面后重新操作！");
			},
		success:function(object){
			alert("保存成功！");
			$(target).parent().siblings("td:eq(7)").html(objectControlValue);
			$(target).parent().siblings("td:eq(6)").html(objectAdjustValue);
			$(target).parent().children("input").attr("style","display:none;");
			$(target).parent().children("a").each(function(){
				$(this).attr("style","display:inline;");
			});
		}
	});
}
//导出Excel
function exportExcel(){
	$("#ifExport").val("yes");
	$("#form").submit();
	$("#ifExport").val("");
}


$(function(){
	if($("#hideNodes").val()==""&&$("#targetNode").val()==""){//首次打开页面
		openNodesByLevel(1);//控制展开层数，修改此参数
	}else{//页面刷新
		openNodesByLevel(1);//此参数不可修改
		shrinkHideNodes();
	}
});

function showContracts(obj){
	var number = parseFloat($.trim($(obj).text()));
	if(number != 0){
		$(obj).text(number.toFixed(4));
	}
	var id = $(obj).parent("tr").find("#hide_id").val();
	var length = $("input:hidden[id=hide_parentId][value='"+id+"']").length;  //下层的数量
	var type = $(obj).attr("class").substring(0,1);		//type=1,点击后显示,type=2,点击后隐藏 
	if(length<1 && type!=0){
		var clickId = $(obj).parent("tr").find("td input:first").val();
		if(type==1){
			var $beforeTarget = $(obj);
			$beforeTarget.addClass("highLight");
			$.ajax({
				type : "post",
				url : "<%=basePath%>/estimateSubject/showAllContract.action?random="+Math.random(),
				dataType:"json",
				cache : false,
				data:{
					estimateSubjectId:clickId,
					projectId:$("#projectId").val()
				},
				error:function(){
					alert("系统忙，请刷新页面后重新操作！")
				},
				success:function(object){
					if(object==null || object.length<1 || object==""){
						alert("尚无数据！");
					}else{
						var html ="<tr class='t_r highLight' style='white-space: nowrap;' id='"+clickId+"'><td colspan='1'><td class='t_r'>合同编号</td><td class='t_l' colspan='5'>合同名称</td></td>"+
						"<td class='t_r'>合同价</td><td class='t_r'>计入已签价格</td><td class='t_l' colspan='3'>计划未签价格</td></tr>";
						for(var m=0; m<object.length; m++){
							html += "<tr class='t_r highLight' style='white-space: nowrap;' id='"+clickId+"'>"+
										"<td colspan='1'></td>"+
										"<td class='t_r'>"+object[m][1]+"</td>"+
										"<td class='t_l' colspan='5'>"+object[m][2]+"</td>"+
										"<td class='t_r'>";
							if(parseFloat(object[m][3])==0)
								html += 0;
							else
								html += parseFloat(object[m][3]).toFixed(4);
							html +="</td><td class='t_r'>";
							if(parseFloat(object[m][4])==0)
								html += 0;
							else
								html +=parseFloat(object[m][4]).toFixed(4);
							html += "</td><td colspan='3'></td></tr>"; 
										
						}
						$beforeTarget.parents("tr").after(html);
					}
					$(obj).attr("class","2 highLight");
				}
			});
		}else{
			$(obj).attr("style","text-align: right;");
			$("tr[id='"+clickId+"']").each(function(){
				$(this).hide();
			});	
			$(obj).attr("class","1");
		}	
	}
}


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
				<li class="fin">投资概算管理-目标控制</li>
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
			<div class="query p8"  id="searchArea">
			<s:form action="findInvestEstimateSubjectByPage" name="estimateSubject" namespace="/estimateSubject" method="post" id="form">
				<input type="hidden" id="hideNodes" name="hideNodes" value="<s:property value='#request.hideNodes'/>"/>
				<input type="hidden" id="projectId" name="projectId" value="<s:property value='#request.project.id'/>"/>
				<input type="hidden" name="type" value="view"/>
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
				<!-- <input type="button" value="同级新增" class="fl mr5" > -->
				<!--  <input type="button" value="下级新增" class="fl mr5"  > -->
				<!--  <input type="button" value="新增特殊行" class="fl mr5"> -->
				<!-- <input type="button" value="导入" class="fl mr5" > -->
				
				
				<!-- <input type="button" value="删除" class="mr5" style="float: right;"> -->
				 <input type="button" value="导出" class="mr5" style="float: right;" onclick="exportExcel()" >
				<!--  <input type="button" value="重新计算" class="mr5" style="float: right;"> -->
				<!-- <input type="button" value="目标控制" class="mr5" style="float: right;"> -->
				
			</div>
			
			
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			
			<!--投资概算科目列表  -->			
			<thead>
				<th colspan="18">&nbsp;&nbsp;投资概算科目列表</th>
			</thead>
			<tbody id="tbody">
				<tr class="tit" id="listTitle">
					<td width="2%;" style="display:none">
						<!-- <input type="checkbox" id="checkAll" value="1"> -->
					</td>
					<td width="10%;" >WBS编码</td>
					<td width="20%;" class="t_r" >科目名称</td>
					<td width="8%;" class="t_r" >单位</td>
					<td width="3%;" class="t_r" >数量</td>
					<td width="7%;" class="t_r"  >概算批复值(1)<br/>(合计)(万元)</td>
					<td width="7%;" class="t_r"  >调整概算值(2)<br/>(合计)(万元)</td>
					<td width="7%;" class="t_r"  >目标控制值(3)<br/>(万元)</td>
					<td width="7%;" class="t_r"  >执行值(4)=(5)+(6)<br/>(万元)</td>
					<td width="7%;" class="t_r"  >已签合同(5)<br/>(万元)</td>
					<td width="7%;" class="t_r"  >未签合同(6)<br/>(万元)</td>
					<td width="7%;" class="t_r"  >经济指标<br/>(单价)(万元)</td>
					<td width="12%;" class="t_r" colspan="3" >操作</td>
				</tr>
				<tr id="first">
					<td class="t_c" style="display:none">
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" ></td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td id="unit_1" class="t_c" style="font-weight: bold;"><s:property value='#request.investEstimate.projectUnit'/></td>
					<td id="amount_1" class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.projectCount'/></td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td class="t_c" >
						<!-- 
						<a href="#" onclick="showEstimateEdit(this);" style="display:none;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveEstimate(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideEstimateEdit(this);">
						 -->
					</td>
				</tr>
				<s:iterator value="#request.InvestEstimateSubjectList" id="list" status="st">
					<s:if test="#request.listWBS.get(#st.index).type==1">
					<tr id="show_common" class="off" style="display:none">
						<td class="t_l" style="display:none">
							<input type="checkbox" name="checkbox" value="<s:property value="#list.id"/>" style="display:none">
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
							<s:if test="#list.estimateDecideSum==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideSum.substring(0,#list.estimateDecideSum.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.objectAdjustValue==0">0</s:if>
							<s:else><s:property value="#list.objectAdjustValue.substring(0,#list.objectAdjustValue.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" >
							<s:if test="#list.objectControlValue==0">0</s:if>
							<s:else><s:property value="#list.objectControlValue.substring(0,#list.objectControlValue.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r" ></td>
						<td class="1"  id="signedContract" style="text-align: right;" onclick="showContracts(this);">
							<s:if test="#request.signedContractList.get(#st.index)==0">0</s:if>
							<s:else><s:property value="#request.signedContractList.get(#st.index)"/></s:else>
						</td>
						<td class="t_r" ></td>
						<td class="t_r" >
							<s:if test="#list.economicIndicator==0">0</s:if>
							<s:else><s:property value="#list.economicIndicator.substring(0,#list.economicIndicator.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showSubjectEdit(this);" style="display:inline;">编辑</a>
							<input type="button" value="保存" style="display: none;" onclick="saveEditOnPage(this);">
							<input type="button" value="取消" style="display: none;" onclick="hideSubjectEdit(this);">
						</td>
					</tr>
					</s:if>
				</s:iterator>
				<tr id="last">
					
					<td class="t_c" style="display:none">
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" ></td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td id="unit_1" class="t_c" style="font-weight: bold;"><s:property value='#request.investEstimate.projectUnit'/></td>
					<td id="amount_1" class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.projectCount'/></td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_c" ></td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
					<td class="t_c">
						<!-- 
						<a href="#" onclick="showEstimateEdit(this);" style="display:none;" >编辑</a>
						<input type="button" value="保存" style="display: none;" onclick="saveEstimate(this);">
						<input type="button" value="取消" style="display: none;" onclick="hideEstimateEdit(this);">
						 -->
					</td>
				</tr>
				
			</tbody>
			<tr class="tfoot">
				<td colspan="18"></td>
		   </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
	
<script type="text/javascript">





//直接从第5层开始算起
$(function(){
	for(var i=5;i>1;i--){
		countAllSignedContracts(i);
	}
});

function countAllSignedContracts(i){
	if(i>1 && i<6){
		var dataCounts = $("input[type=hidden][id=hide_level][value='"+i+"']").length;
		var prevParentId = "";
		if(dataCounts>0){
			$("input[type=hidden][id=hide_level][value='"+i+"']").each(function(){			//所有level相同
				var $hideValueTd = $(this).parent();		//父节点、id等存放隐藏信息的td
				var parentId = $hideValueTd.find("#hide_parentId").val();
				if(parentId!=prevParentId){		//跟上一个是不同的parentId
					prevParentId = parentId;
					var result = 0.0;
					$("input[type=hidden][id=hide_parentId][value='"+parentId+"']").each(function(index,object){
						result += parseFloat($.trim($(object).parent().parent().find("#signedContract").text()));
					});
					if(result!=0){
						result = result.toFixed("4");
					}else{
						result = 0;
					}
					$("input[type=hidden][id=hide_id][value='"+parentId+"']").parent().parent().find("#signedContract").text(result);
				} 
			});
		}
	}
}
	
</script>	
</body>
</html>



