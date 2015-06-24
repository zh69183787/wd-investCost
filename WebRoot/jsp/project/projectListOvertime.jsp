<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目管理</title>
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
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>

<script type="text/javascript">
$(document).ready(function () {
            var $tbInfo = $(".filter .query input:text");
            $tbInfo.each(function () {
                $(this).focus(function () {
                    $(this).attr("placeholder", "");
                });
            });
            var h1 = $("dl").width();
			var h2 = $("dt").width();
			$("dd").width(h1-h2-10);
		
			$(window).resize(function() {
		    	var h1 = $("dl").width();
				var h2 = $("dt").width();
				$("dd").width(h1-h2-10);
			});
			
			var $tblAlterRow = $(".table_1 tbody tr:even");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background","#fafafa");			
        });
//日期控件        
$(function(){
	
	$("input[name=approvalDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='approvalDateEnd']").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name='approvalDateEnd']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='approvalDate']").datepicker( "option", "maxDate", selectedDate );
		}
	});
	

	$("input[name=projectStarttimePlanDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='projectStarttimePlanDateEnd']").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name='projectStarttimePlanDateEnd']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='projectStarttimePlanDate']").datepicker( "option", "maxDate", selectedDate );
		}
	});

	$("input[name=projectEndtimePlanDateStart]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='projectEndtimePlanDate']").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("input[name='projectEndtimePlanDate']").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("input[name='projectEndtimePlanDateStart']").datepicker( "option", "maxDate", selectedDate );
		}
	});
});
	

</script>
 
 
<script type="text/javascript">
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		$("#form").submit();
		clearInterval(refresh);
	}
}

//跳转到新增页面
function showAddPage(){
	sonWindow = window.open('showAdd.action');
}

//跳转到编辑页面
function showEditPage(id){
	sonWindow = window.open('showEdit.action?id='+id);
}

function goPage(pageNo,type){
	//type=0,直接跳转到制定页
	if(type=="0"){
  		var pageCount = $("#totalPageCount").val();
  		var number = $("#number").val();
  		if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
  		}
  		if(parseInt(number)>parseInt(pageCount)){
  			$("#number").val(pageCount);
  			$("#pageNo").val(pageCount);
  		}else{
  			$("#pageNo").val(number);
  		}
  	}
	//type=1,跳转到上一页	       
    if(type=="1"){
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }
	//type=2,跳转到下一页	       
    if(type=="2"){
   		$("#pageNo").val(parseInt($("#number").val())+1);
    }
     //type=3,跳转到最后一页,或第一页
    if(type=="3"){
    	$("#pageNo").val(pageNo);
    }
   	$("#form").submit();
} 

//清除表单数据
function clearSearch(){
	$("#search1 td input").val("");
	$("#search1 td select option:first").attr("selected",true);
	$("#search3 td select option:first").attr("selected",true);
	$("#search2 td input").val("");
	$("#search3 td input").val("");
	$("#pageNo").val(1);
	//$("select[name='professionalType'] option:first").attr("selected",true);
	$("select[name=projectType]").find("option:first").attr("selected",true);
	//$("#pType").val("0");
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
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("form").submit();
			}
		});
	}
}
//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name=showOrHide]").val();
	var $li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$("#searchArea").slideDown();
		$("input[name=showOrHide]").val("show");
		$("#quickSearch").slideUp();
	}else{
		$li.removeClass("selected");
		$("#searchArea").slideUp();
		$("input[name=showOrHide]").val("hide");
		$("#quickSearch").slideDown();
	}
}

//页面初始化时隐藏搜索条件
$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide"){
		$("#searchArea").css("display","none");
		$("#quickSearch").css("display","block");
	}else{
		$("#searchArea").css("display","block");
		$("#quickSearch").css("display","none");
	}		
});


//页面缩略显示
$(function(){
	$("span[id=limitLength1]").each(function(){
		var html = $(this).html();
		if(html.length>20){
			$(this).html(html.substring(0,20)+"...");
		}
	});
	$("span[id=limitLength2]").each(function(){
		var html = $(this).html();
		if(html.length>18){
			$(this).html(html.substring(0,18)+"...");
		}
	});
})

//点击查询按钮，清空页码值
function clearPageNo(){
	$("#pageNo").val("");
	//设置项目类型
	if($("#pType").val()!=null && $("#pType").val()!=""){
		if($("select[name=projectType]").val()=='1'){
			$("#pType").val("1");
		}else if($("select[name=projectType]").val()=='2' || $("select[name=projectType]").val()=='3' || $("select[name=projectType]").val()=='4'){
			$("#pType").val("2");
		}
	}
	return true;
}

//点击项目公司查询
function showList(addDeptId,addDeptName){
	
	$("#projectAdddeptId").val("");
	$("#projectAdddept").val("");
	if(addDeptId=="company1"){
		$("input[name=companyType]").val(1);
	}else if(addDeptId=="company2"){
		$("input[name=companyType]").val(2);
	}else if(addDeptId=="company3"){
		$("input[name=companyType]").val(3);
	}else{
		$("#projectAdddeptId").val(addDeptId);
		$("#projectAdddept").val(addDeptName);
	}
	clearPageNo();
	$("#form").submit();
}

	
//导出Excel
function exportExcel(){
	$("#ifExport").val("yes");
	$("#form").submit();
	$("#ifExport").val("");
}

//点击项目公司、运营公司、维保中心查询
//type=0查询指定的公司,type=1查项目，type=2查运营，type=3查维保
//findType='notin',查询其他
function queryByCompany(type,id,findType){
	$("#projectAdddeptId").val("");
	if(type=='0'){
		$("#projectAdddeptId").val(id);
		$("#companyType").val("");
	}else if(type=='1'){
		$("#companyType").val("company1");
	}else if(type=='2'){
		$("#companyType").val("company2");
	}else if(type=='3'){
		$("#companyType").val("company3");
	}
	$("#findType").val(findType);
	clearPageNo();
	$("#form").submit();

}

function queryContract(projectId,projectNo,projectName){
	$("#projectId").val(projectId);
	$("#projectNo").val(projectNo);
	$("#projectName").val(projectName);
	$("#queryContractForm").submit();
}
</script>
 
 
</head>

<body>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">工程投资造价管理</a></li>
				<li><a href="#">项目封面</a></li>
				<li class="fin">项目管理</li>
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
		
	<div class="pt45">
		<!-- 
		<div class="tabs_2 nwarp" >
       		<ul>
       			<s:if test="#request.pType==null || #request.pType==''">
       				<li class="selected"><a href="findProjectByPage.action"><span>全部</span></a></li>
       				<li><a href="findProjectByPage.action?pType=0"><span>内部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=1"><span>外部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=5"><span>其他项目</span></a></li>
       			</s:if>
       			<s:elseif test="#request.pType==1">
       				<li><a href="findProjectByPage.action"><span>全部</span></a></li>
       				<li><a href="findProjectByPage.action?pType=0"><span>内部项目</span></a></li>
	           		<li class="selected"><a href="findProjectByPage.action?pType=1"><span>外部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=5"><span>其他项目</span></a></li>
       			</s:elseif>
       			<s:elseif test="#request.pType==0">
       				<li><a href="findProjectByPage.action"><span>全部</span></a></li>
					<li class="selected"><a href="findProjectByPage.action?pType=0"><span>内部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=1"><span>外部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=5"><span>其他项目</span></a></li>
       			</s:elseif>
       			<s:else>
       				<li><a href="findProjectByPage.action"><span>全部</span></a></li>
					<li><a href="findProjectByPage.action?pType=0"><span>内部项目</span></a></li>
	           		<li><a href="findProjectByPage.action?pType=1"><span>外部项目</span></a></li>
	           		<li class="selected"><a href="findProjectByPage.action?pType=5"><span>其他项目</span></a></li>
       			</s:else>
           </ul>
       </div>
		 -->
		<div class="filter">
			<s:if test="#request.pType!=null && #request.pType!=''">
			<div class="query p8" id="quickSearch">
				<div class="p8 stat_3">
                	<dl class="clearfix">
                		<s:if test="#request.pType==0">
                			<dt>运营公司：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="((project.projectAdddeptId==null || project.projectAdddeptId=='') && (#request.companyType==null || #request.companyType=='')) || #request.companyType=='company2'">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('2','');">全部(<span><s:property value='#request.type2CountAll'/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('2','');">全部(<span><s:property value='#request.type2CountAll'/></span>)</a></li>
	                            	</s:else>
	                                <s:iterator value="#request.resultList2" id="result">
	                                	<s:if test="#result[1]!=0">
	                                		<s:if test="#result[2]==project.projectAdddeptId">
	                                			<li class="selected"><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0]=='第一运营公司'">运一公司</s:if>
	                                			<s:elseif test="#result[0]=='第二运营公司'">运二公司</s:elseif>
	                                			<s:elseif test="#result[0]=='第三运营公司'">运三公司</s:elseif>
	                                			<s:elseif test="#result[0]=='第四运营公司'">运四公司</s:elseif>
	                                			<s:else><s:property value="#result[0]"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:if>
	                                		<s:else>
	                                			<li><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0]=='第一运营公司'">运一公司</s:if>
	                                			<s:elseif test="#result[0]=='第二运营公司'">运二公司</s:elseif>
	                                			<s:elseif test="#result[0]=='第三运营公司'">运三公司</s:elseif>
	                                			<s:elseif test="#result[0]=='第四运营公司'">运四公司</s:elseif>
	                                			<s:else><s:property value="#result[0]"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:else>
	                                	</s:if>
	                                </s:iterator>
	                            </ul>
	                        </dd>
	                        
	                        <dt>维保中心：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="((project.projectAdddeptId==null || project.projectAdddeptId=='') && (#request.companyType==null || #request.companyType=='')) || #request.companyType=='company3'">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('3','');">全部(<span><s:property value='#request.type3CountAll'/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('3','');">全部(<span><s:property value='#request.type3CountAll'/></span>)</a></li>
	                            	</s:else>
	                                <s:iterator value="#request.resultList3" id="result">
	                                	<s:if test="#result[1]!=0">
	                                		<s:if test="#result[2]==project.projectAdddeptId">
	                                			<li class="selected"><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0]=='维保中心供电分公司'">供电公司</s:if>
	                                			<s:elseif test="#result[0]=='维保中心工务分公司'">工务公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心通号分公司'">通号公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心物资后勤分公司'">物资公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心车辆分公司'">车辆公司</s:elseif>
	                                			<s:else><s:property value="#result[0]"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:if>
	                                		<s:else>
	                                			<li><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0]=='维保中心供电分公司'">供电公司</s:if>
	                                			<s:elseif test="#result[0]=='维保中心工务分公司'">工务公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心通号分公司'">通号公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心物资后勤分公司'">物资公司</s:elseif>
	                                			<s:elseif test="#result[0]=='维保中心车辆分公司'">车辆公司</s:elseif>
	                                			<s:else><s:property value="#result[0]"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:else>
	                                	</s:if>
	                                </s:iterator>
	                            </ul>
	                        </dd>
	                        
	                        <dt>其他：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="-1==project.projectAdddeptId">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('0','-1','notin');">其他(<span><s:property value="#request.innerOther"/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('0','-1','notin');">其他(<span><s:property value="#request.innerOther"/></span>)</a></li>
	                            	</s:else>
	                            	<s:if test="#request.controlCenter[2]==project.projectAdddeptId">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('0','<s:property value="#request.controlCenter[2]"/>');"><s:property value="#request.controlCenter[0]"/>(<span><s:property value="#request.controlCenter[1]"/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                                	<li><a href="#" onclick="queryByCompany('0','<s:property value="#request.controlCenter[2]"/>');"><s:property value="#request.controlCenter[0]"/>(<span><s:property value="#request.controlCenter[1]"/></span>)</a></li>
	                                </s:else>
	                            </ul>
	                        </dd>
                		</s:if>
                		<s:elseif test="#request.pType==1">
                			<dt>项目公司：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="((project.projectAdddeptId==null || project.projectAdddeptId=='') && (#request.companyType==null || #request.companyType=='')) || #request.companyType=='company1'">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('1','');">全部(<span><s:property value='#request.type1CountAll'/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('1','');">全部(<span><s:property value='#request.type1CountAll'/></span>)</a></li>
	                            	</s:else>
	                                <s:iterator value="#request.resultList1" id="result">
	                                	<s:if test="#result[1]!=0">
	                                		<s:if test="#result[2]==project.projectAdddeptId">
	                                			<li class="selected"><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0].indexOf('项目公司')<1"><s:property value="#result[0]"/></s:if>
		                                		<s:else><s:property value="#result[0].replace('项目公司','')"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:if>
	                                		<s:else>
	                                			<li><a href="#" onclick="queryByCompany('0','<s:property value="#result[2]"/>');">
	                                			<s:if test="#result[0].indexOf('项目公司')<1"><s:property value="#result[0]"/></s:if>
		                                		<s:else><s:property value="#result[0].replace('项目公司','')"/></s:else>
	                                			(<span><s:property value='#result[1]'/></span>)</a></li>
	                                		</s:else>
	                                	</s:if>
	                                </s:iterator>
	                            </ul>
	                        </dd>
	                        <dt>其他：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="-1==project.projectAdddeptId">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('0','-1','notin');">其他(<span><s:property value="#request.outOther"/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('0','-1','notin');">其他(<span><s:property value="#request.outOther"/></span>)</a></li>
	                            	</s:else>
	                            </ul>
	                        </dd>
                		</s:elseif>
                		<s:else>
                			<dt>其他：</dt>
	                        <dd >
	                            <ul>
	                            	<s:if test="((project.projectAdddeptId==null || project.projectAdddeptId=='') && (#request.companyType==null || #request.companyType=='')) || #request.companyType=='company1'">
	                            		<li class="selected"><a href="#" onclick="queryByCompany('1','');">全部(<span><s:property value='#request.othterProject'/></span>)</a></li>
	                            	</s:if>
	                            	<s:else>
	                            		<li><a href="#" onclick="queryByCompany('1','');">全部(<span><s:property value='#request.othterProject'/></span>)</a></li>
	                            	</s:else>
	                            </ul>
	                        </dd>
                		</s:else>
                    </dl>
           		</div>
			</div>
			</s:if>
			
			<div class="query p8" id="searchArea">
				<s:form action="showBusinessControlProjectOvertime" name="project" namespace="/project" method="post" id="form">
				<input type="hidden" name="findType" id="findType" value="<s:property value='#request.findType'/>"/>
				<input type="hidden" name="ifExport" id="ifExport"/>
				<input type="hidden" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
				<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
				<input type="hidden" name="companyType" id="companyType" value="<s:property value='#request.companyType'/>">
				<input type="hidden" name="pType" id="pType" value="<s:property value="#request.pType"/>"/>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr id="search1">
							<td class="t_r" style="white-space:nowrap">项目编号</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectNo"  class="input_large" value="<s:property value='project.projectNo'/>" />
							</td>
							<td class="t_r" style="white-space:nowrap">项目名称</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectName"  class="input_large" value="<s:property value='project.projectName'/>" >
							</td>
							
							<td class="t_r" style="white-space:nowrap">计划开始时间</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectStarttimePlanDate"  class="input_medium" value="<s:property value='project.projectStarttimePlanDate'/>" readonly="readonly">~
								<input type="text" name="projectStarttimePlanDateEnd"  class="input_medium" value="<s:property value='#request.projectStarttimePlanDateEnd'/>" readonly="readonly">
							</td>
						</tr>
						<tr id="search2">
							<td class="t_r" style="white-space:nowrap">负责部门</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectAdddept" id="projectAdddept" class="input_large" value="<s:property value='project.projectAdddept'/>" />
								<input type="hidden" name="projectAdddeptId" id="projectAdddeptId" value="<s:property value='project.projectAdddeptId'/>" />
							</td>
							<td class="t_r" style="white-space:nowrap">项目类型</td>
							<td style="white-space:nowrap">
								<select name="projectType" class="input_large">
									<s:if test="project.projectType==1">
										<option value="">---请选择---</option>
										<option value="1" selected="selected">外部项目</option>
										<option value="2">内部项目资本类</option>
										<option value="3">内部项目大修类</option>
										<option value="4">内部项目能源类</option>
										<option value="5">其他类</option>
									</s:if>
									<s:elseif test="project.projectType==2">
										<option value="">---请选择---</option>
										<option value="1">外部项目</option>
										<option value="2" selected="selected">内部项目资本类</option>
										<option value="3">内部项目大修类</option>
										<option value="4">内部项目能源类</option>
										<option value="5">其他类</option>
									</s:elseif>
									<s:elseif test="project.projectType==3">
										<option value="">---请选择---</option>
										<option value="1">外部项目</option>
										<option value="2">内部项目资本类</option>
										<option value="3" selected="selected">内部项目大修类</option>
										<option value="4">内部项目能源类</option>
										<option value="5">其他类</option>
									</s:elseif>
									<s:elseif test="project.projectType==4">
										<option value="">---请选择---</option>
										<option value="1">外部项目</option>
										<option value="2">内部项目资本类</option>
										<option value="3">内部项目大修类</option>
										<option value="4" selected="selected">内部项目能源类</option>
										<option value="5">其他类</option>
									</s:elseif>
									<s:elseif test="project.projectType==5">
										<option value="">---请选择---</option>
										<option value="1">外部项目</option>
										<option value="2">内部项目资本类</option>
										<option value="3">内部项目大修类</option>
										<option value="4">内部项目能源类</option>
										<option value="5" selected="selected">其他类</option>
									</s:elseif>
									<s:else>
										<option value="">---请选择---</option>
										<option value="1">外部项目</option>
										<option value="2">内部项目资本类</option>
										<option value="3">内部项目大修类</option>
										<option value="4">内部项目能源类</option>
										<option value="5">其他类</option>
									</s:else>
								</select>
							</td>
							<td class="t_r" style="white-space:nowrap">计划完成时间</td>
							<td style="white-space:nowrap">
								<input type="text" name="projectEndtimePlanDateStart"  class="input_medium" value="<s:property value='#request.projectEndtimePlanDateStart'/>" readonly="readonly">~
								<input type="text" name="projectEndtimePlanDate"  class="input_medium" value="<s:property value='project.projectEndtimePlanDate'/>" readonly="readonly">
							</td>
						</tr>
						<tr id="search3">
							<td colspan="2"></td>
							<td class="t_r" style="white-space:nowrap">专业分类</td>
							<td>
								<select name="professionalType" class="input_large">
									<s:if test="project.professionalType==1">
										<option value="">---请选择---</option>
										<option value="1" selected="selected">车辆</option>
										<option value="2">供电</option>
										<option value="3">通号</option>
										<option value="4">工务</option>
										<option value="5">基地</option>
										<option value="6">车站机电</option>
									</s:if>
									<s:elseif test="project.professionalType==2">
										<option value="">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2" selected="selected">供电</option>
										<option value="3">通号</option>
										<option value="4">工务</option>
										<option value="5">基地</option>
										<option value="6">车站机电</option>
									</s:elseif>
									<s:elseif test="project.professionalType==3">
										<option value="">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2">供电</option>
										<option value="3" selected="selected">通号</option>
										<option value="4">工务</option>
										<option value="5">基地</option>
										<option value="6">车站机电</option>
									</s:elseif>
									<s:elseif test="project.professionalType==4">
										<option value="">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2">供电</option>
										<option value="3">通号</option>
										<option value="4" selected="selected">工务</option>
										<option value="5">基地</option>
										<option value="6">车站机电</option>
									</s:elseif>
									<s:elseif test="project.professionalType==5">
										<option value="">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2">供电</option>
										<option value="3">通号</option>
										<option value="4">工务</option>
										<option value="5" selected="selected">基地</option>
										<option value="6">车站机电</option>
									</s:elseif>
									<s:elseif test="project.professionalType==6">
										<option value="">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2">供电</option>
										<option value="3">通号</option>
										<option value="4">工务</option>
										<option value="5">基地</option>
										<option value="6" selected="selected">车站机电</option>
									</s:elseif>
									<s:else>
										<option value="" selected="selected">---请选择---</option>
										<option value="1">车辆</option>
										<option value="2">供电</option>
										<option value="3">通号</option>
										<option value="4">工务</option>
										<option value="5">基地</option>
										<option value="6">车站机电</option>
									</s:else>
								</select>
							</td>
							<td class="t_r" style="white-space:nowrap">批文时间</td>
							<td style="white-space:nowrap">
								<input type="text" name="approvalDate"  class="input_medium" value="<s:property value='project.approvalDate'/>" readonly="readonly">~
								<input type="text" name="approvalDateEnd"  class="input_medium" value="<s:property value='#request.approvalDateEnd'/>" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td colspan="6" class="t_c">
								<input type="submit" value="检 索" onclick="return clearPageNo();"/>&nbsp;&nbsp; 
								<input type="button" value="重 置" onclick="clearSearch();"/>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
			<!-- 
			<div class="fn">
			
				<span style="float:left;">
        			<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
        		</span>
				<span style="float: right;">
					<input type="button" value="导出" style="float: right;" onclick="exportExcel();"/>
				</span>
				
			</div>
			 -->
		</div>
		<!--Filter End--> <!--Table-->
		
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="8">&nbsp;&nbsp;项目列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td width="5%;" style="white-space:nowrap;">序号</td>
					<td width="20%;" class="t_r" style="white-space:nowrap;">项目编号</td>
					<td width="1%;"  class="t_r" style="white-space:nowrap;">项目名称</td>
					<td width="10%;"  class="t_r" style="white-space:nowrap;">项目类型</td>
					<td width="10%;" class="t_r" style="white-space:nowrap;">负责部门</td>
					<td width="10%;"  class="t_r" style="white-space:nowrap;">负责人</td>
					<!-- 
					<td width="10%;"  class="t_r" style="white-space:nowrap;">计划开工时间</td>
					<td width="10%;"  class="t_r" style="white-space:nowrap;">计划完成时间</td>
					 -->
					<td width="10%;"  class="t_r" style="white-space:nowrap;">投资估算总额(万元)</td>
					<td width="20%;" class="t_r" style="white-space:nowrap;">操作</td>
				</tr>
				<s:iterator value="#request.page.result" id="project" status="st">
					<tr>
						<!--<td class="t_c" style="word-break:break-all"><s:property value="#request.page.start+#st.index"/></td>-->
						<td class="t_c" style="white-space: nowrap;">
							<s:property value="#st.index+#request.page.start"/>
						</td>
                        <td class="t_l" style="white-space: nowrap;" title="<s:property value="#project.projectNo"/>">
                        	<span id="limitLength1"><s:property value="#project.projectNo"/></span>
                        </td>
                        <td class="t_l" style="white-space: nowrap;" title="<s:property value="#project.projectName"/>">
                        	<span id="limitLength2"><s:property value="#project.projectName"/></span>
                        </td>
                        <td class="t_c" style="white-space: nowrap;">
                        	<s:if test="#project.projectType==1">外部项目</s:if>
                        	<s:elseif test="#project.projectType==2">内部项目-资本类</s:elseif>
                        	<s:elseif test="#project.projectType==3">内部项目-大修类</s:elseif>
                        	<s:elseif test="#project.projectType==4">内部项目-能源类</s:elseif>
                        	<s:elseif test="#project.projectType==5">其他类</s:elseif>
                        </td>
                        <td class="t_c" style="white-space: nowrap;"><s:property value="#project.projectAdddept"/></td>
                        <td class="t_c" style="white-space: nowrap;"><s:property value="#project.projectAddperson"/></td>
                        <!-- 
                        <td class="t_c" style="white-space: nowrap;"><s:property value="#project.projectStarttimePlanDate"/></td>
						<td class="t_c" style="white-space: nowrap;"><s:property value="#project.projectEndtimePlanDate"/></td>
						 -->
						<td class="t_r" style="white-space: nowrap;">
							<s:if test="#project.projectBudgetAll==0">0</s:if>
							<s:else>
								<s:property value="#project.projectBudgetAll.substring(0,#project.projectBudgetAll.indexOf('.')+5)"/>
							</s:else>
						</td>
						<td class="t_c" style="white-space: nowrap;">
							<a href="showView.action?id=<s:property value="#project.id"/>" style="display:inline;" target="_blank">查看</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="showEditPage( '<s:property value="#project.id"/>');" style="display:inline;">编辑</a>&nbsp;&nbsp;&nbsp;
							<a href="/investCost/estimateSubject/findInvestEstimateSubjectByPage.action?projectId=<s:property value="#project.id"/>" target="_blank" style="display:inline;">投资概算</a>
							<a href="/investCost/estimateSubject/findInvestEstimateSubjectByPage.action?type=view&projectId=<s:property value="#project.id"/>" target="_blank" style="display:inline;">目标控制</a>
							<a href="javascript:void(0);" onclick="queryContract('<s:property value="#project.id"/>','<s:property value="#project.projectNo"/>','<s:property value="#project.projectName"/>');" style="display:inline;">合同列表</a>
						</td>
					</tr>
				</s:iterator>
				<tr style="display: none">
					<td>
						<s:form action="findContractByPage" name="contract" namespace="/contract" method="post" id="queryContractForm" target="_blank">
							<input type="hidden" name="projectId" id="projectId">
							<input type="hidden" name="projectNo" id="projectNo">
							<input type="hidden" name="projectName" id="projectName">
							<input type="hidden" name="showAll" value="hide">
						</s:form>
					</td>
				</tr>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="8"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
			      <s:else>
			      	<span class="fl">
				      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
					    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
					      	<s:property value="#request.page.totalSize"/>条
					    </s:if>
					    <s:else>
					    	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
						</s:else>
				    </span>
			        <ul class="fr clearfix pager">
			          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
			          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
		           	  </li>
		           	  
		               <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		               </s:if>
		               <s:else>
		                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		               </s:else>
		                
		              <li>
		              	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
		              		<a href="javascript:void(0)">下一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
		              	</s:else>
		              </li>
		              
		              <li>
		              	<s:if test="#request.page.currentPageNo==1">
		              		<a href="javascript:void(0)">上一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
		              	</s:else>
		              </li>
		              
		              <s:if test="#request.page.currentPageNo==1">
		              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
		              </s:if>
		              <s:else>
		              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
		              </s:else>
		         </ul>
		       </s:else>
		       </div></td>
		     </tr>
		</table>
		
		</div>
	</div>
		<!--Table End-->
	</div>
</body>
</html>
