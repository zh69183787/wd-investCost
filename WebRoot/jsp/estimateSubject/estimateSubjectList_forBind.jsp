<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en" >
<head>
<meta charset="utf-8" />
<title>投资概算管理-绑定</title>
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
<script src="../js/html5.js"></script>
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
	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$("#searchArea").css("display","none");
	else
		$("#searchArea").css("display","block");
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
	//alert($("#hideNodes").val());
	$("#form").submit();
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


//多选框实现单选
$(function(){
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
});


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
	
	$.ajax({
		type:"post",
		dataType:"json",
		url:"saveObjectControlValue.action",
		data:{
			id:$(target).parent().parent().children("td:first").children("input").val(),
			objectControlValue :objectControlValue,
			objectAdjustValue : objectAdjustValue,
			projectId:$("input[name=projectId]").val(),
			projectName:$("input[name=projectName]").val()
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


$(function(){
	if($("#hideNodes").val()==""&&$("#targetNode").val()==""){//首次打开页面
		openNodesByLevel(1);//控制展开层数，修改此参数
	}else{//页面刷新
		openNodesByLevel(1);//此参数不可修改
		shrinkHideNodes();
	}
})

//绑定概算科目
function bind(){
	if($("input[type=checkbox]:checked").length<1){
		alert("请先勾选要绑定的概算科目！");
		return ;
	}else{
		var parentId = $("input[type=checkbox]:checked").siblings("#hide_id").val();
		var wbsId = $.trim($("input[type=checkbox]:checked").parent().next().find("p").text());
		var name = $.trim($("input[type=checkbox]:checked").parent().siblings(":eq(1)").text());
		var money = $.trim($("input[type=checkbox]:checked").parent().siblings(":eq(2)").text());
//alert("wbsId="+wbsId+",name="+name+",money="+money);
		var label = wbsId+"-"+name+"-"+money+"万元";	
		if($("input:hidden[id=hide_parentId][value="+parentId+"]").length>0){
			alert("不能勾选有下级科目的概算科目！请重新勾选！");
			return false;
		}else{
			window.parent.document.getElementById("estimateSubjectId").value = parentId;
			window.parent.document.getElementById("estimateSubjectName").value = label;
			window.parent.document.getElementById("cover").style.display = 'none';
		}
	}
	
}
</script>
 
 
 
 
</head>

<body>
<input name="projectName" value="<s:property value='#request.project.projectName' />" type="hidden" id="projectName">
<input name="showOrHide" type="hidden">
	<div class="main"><!--Ctrl-->
		<div class="filter">
			<div class="query p8"  id="searchArea">
			<s:form action="findInvestEstimateSubjectByPageForBind" name="estimateSubject" namespace="/estimateSubject" method="post" id="form">
				<input type="hidden" id="hideNodes" name="hideNodes" value="<s:property value='#request.hideNodes'/>"/>
				<input type="hidden" id="projectId" name="projectId" value="<s:property value='#request.project.id'/>"/>
				<input type="hidden" name="type" value="view"/>
				<input type="hidden" name="ifExport" id="ifExport"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%;" class="t_r" >项目编号：</td>
						<td width="25%;"  >
							<!--<s:property value='#request.project.projectNo'/>-->
							<input type="text" name="projectNo_" id="projectNo_" class="input_xlarge" value="<s:property value='#request.project.projectNo'/>" disabled="disabled"/>
							<input type="hidden" name="projectId_" id="projectId_" value="<s:property value='#request.project.id'/>" />
							<!-- <input type="button" value="查询" onclick="queryProject();"> -->
						</td>
						<td width="10%;" class="t_r" >项目名称：</td>
						<td width="35%;" >
							<!--<s:property value='#request.project.projectName'/>-->
							<input type="text" name="projectName_" id="projectName_" class="input_xlarge" value="<s:property value='#request.project.projectName'/>" disabled="disabled"/>
							<!-- <input type="button" value="查询" onclick="queryProject();"> -->
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<div class="fn">
				<span style="float: left;padding-left:15px;">
					 绑定概算科目：<input type="text" class="input_xlarge">&nbsp;&nbsp;<input type="button" value="查询">
				</span>
				 <input type="button" value="绑定" class="mr5" style="float: right;" onclick="bind();" >
			</div>
			
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			
			<!--投资概算科目列表  -->			
			<thead>
				<th colspan="5">&nbsp;&nbsp;投资概算科目列表</th>
			</thead>
			<tbody id="tbody">
				<tr class="tit" id="listTitle">
					<td width="10%;" ></td>
					<td width="20%;" >WBS编码</td>
					<td width="30%;" class="t_r" >科目名称</td>
					<td width="20%;" class="t_r"  >概算批复值(合计)(万元)</td>
					<td width="20%;" class="t_r"  >已签合同(万元)</td>
				</tr>
				<tr id="first">
					<td class="t_c" >
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" ></td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
				</tr>
				<s:iterator value="#request.InvestEstimateSubjectList" id="list" status="st">
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
								<s:if test="#request.listWBS.get(#st.index+1).wbsId.contains(#request.listWBS.get(#st.index).wbsId)">
									<img src="../images/plus.png" onclick="showHierarchy(this);" style="display:inline" id="plus"/>
								</s:if>
								<s:else>&nbsp;&nbsp;&nbsp;</s:else>
								</font>
								<s:property value="#request.listWBS.get(#st.index).wbsId"/>
							</p>
						</td>
						<td class="t_c" ><s:property value="#list.subjectName"/></td>
						<td class="t_r" >
							<s:if test="#list.estimateDecideSum==0">0</s:if>
							<s:else><s:property value="#list.estimateDecideSum.substring(0,#list.estimateDecideSum.indexOf('.')+5)"/></s:else>
						</td>
						<td class="t_r"  id="signedContract">
							<s:if test="#request.signedContractList.get(#st.index)==0">0</s:if>
							<s:else><s:property value="#request.signedContractList.get(#st.index)"/></s:else>
						</td>
					</tr>
					</s:if>
				</s:iterator>
				<tr id="last">
					<!-- 
					<td class="t_c" >
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					 -->
					<td class="t_c" >
						<input type="hidden" value="<s:property value='#request.investEstimate.id'/>" id="estimateId">
					</td>
					<td class="t_c" ></td>
					<td class="t_c" style="font-weight: bold;">总计</td>
					<td class="t_r" style="font-weight: bold;">
						<s:if test="#request.investEstimate.estimateDecideAmount==0">0</s:if>
						<s:else><s:property value="#request.investEstimate.estimateDecideAmount.substring(0,#request.investEstimate.estimateDecideAmount.indexOf('.')+5)"/></s:else>
					</td>
					<td class="t_r" style="font-weight: bold;"><s:property value='#request.investEstimate.'/></td>
				</tr>
				
			</tbody>
			<tr class="tfoot">
				<td colspan="5"></td>
		   </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
	
<script type="text/javascript">
	$(function(){
		$("td[id=signedContract]").each(function(){
			var number = parseFloat($.trim($(this).text()));
			if(number != 0){
				$(this).text(number.toFixed(4));
			}
			//点击后查询已签合同的价钱先隐藏
			/*
			var clickId = $(this).parent("tr").find("td input:first").val()
			$(this).toggle(
				function(){
					var $beforeTarget = $(this); 
					$beforeTarget.addClass("highLight");	
					$.ajax({
						type : "post",
						url : "</%=basePath%>/estimateSubject/showAllContract.action?random="+Math.random(),
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
								var html ="";
								for(var m=0; m<object.length; m++){
									html += "<tr class='t_r highLight' style='white-space: nowrap;' id='"+clickId+"'>"+
												"<td colspan='2'></td>"+
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
						}
					});
				},
				function(){
					$(this).removeClass("highLight");
					$("tr[id="+clickId+"]").each(function(){
						$(this).hide();
					});	
				}
			);	*/		
		});
	})
	
	
</script>	
</body>
</html>



