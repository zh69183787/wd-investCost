﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>交通委补贴管理</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>

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
			
			$('#year').val('<s:property value="fundAllowance.year"/>')
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
function showOrHideControl(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide"){
		$("#searchArea").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
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
		window.location.href = "findFundAllowanceByPage.action";
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

//跳转到导入页面
function showImportPage(){
	sonWindow = window.open('showBatchUpload.action');
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
	$("#search td input:lt(4)").val("");
	$("#pageNo").val(1);
	$("#year").val('');
}

function search(){
	$("#pageNo").val(1);
	$("#form").submit();
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
				$("#form").submit();
			}
		});
	}
}
//导出Excel
function doExport(){
	$("#formExport").submit();
	hideExport();
}
//显示批量修改
function showExport(){
	$("#cover2").show();
}
function hideExport(){
	$("#cover2").hide();
}
</script>
 
<style>
#cover2
{
    position:absolute;
    right:0px;
    bottom:0px;
    width:100%;
    height:100%;
    display:none;
    z-index:99;
}
#tb_window
{
    width:450px;
    height:220px;
    border:2px #6699CC solid;
    z-index:2;
    background:#FFFFFF;
    margin:200px auto;
}
</style>
 
 
</head>

<body>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li class="fin">交通委补贴管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#" onclick="window.print();">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl();">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8" id="searchArea">
			<s:form action="findFundAllowanceByPage" name="fundAllowance" namespace="/fundAllowance" method="post" id="form">
			<input type="hidden" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
			
				<table id="search" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="t_r" style="white-space:nowrap">补贴年份</td>
						<td style="white-space:nowrap">
							<select name="year" id="year">
								<option value="">全部</option>
								<%int curYear = (Integer)request.getAttribute("curYear");
									for(int i=2014;i<curYear+5;i++){
								%>
								<option><%=i%></option>
								<%
									}
								%>								
							</select>
						</td>
						<td class="t_r" style="white-space:nowrap">项目批文号</td>
						<td style="white-space:nowrap">
							<input type="text" name="dispatchNo" id="textfield" class="input_large" value="<s:property value='fundAllowance.dispatchNo'/>" >
						</td>
						<td class="t_r" style="white-space:nowrap">线路</td>
						<td style="white-space:nowrap">
							<input type="text" name="line" id="textfield" class="input_large" value="<s:property value='fundAllowance.line'/>" >
						</td>					
					</tr>
					<tr>
						<td class="t_r" style="white-space:nowrap">合同编号</td>
						<td style="white-space:nowrap">
							<input type="text" name="contractNo" id="textfield" class="input_large" value="<s:property value='fundAllowance.contractNo'/>" >
						</td>
						<td class="t_r" style="white-space:nowrap">合同名称</td>
						<td style="white-space:nowrap" colspan="3">
							<input type="text" name="contractName" id="textfield" class="input_large" value="<s:property value='fundAllowance.contractName'/>" >
						</td>					
					</tr>					
					<tr>
						<td colspan="6" class="t_c">
							<input type="button" value="检 索" onclick="search();"/>&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<div class="fn">
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
				<input type="button"" name="button2" id="button2" value="批量导入" class="new" onclick="showImportPage();">
				<input type="button" value="导出" class="new" onclick="showExport();">
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="11">&nbsp;&nbsp;交通委补贴列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td width="3%;" style="white-space:nowrap">序号</td>
					<td width="5%;" class="t_r" style="white-space:nowrap">补贴年份</td>
					<td width="12%;" class="t_r" style="white-space:nowrap">项目批文号</td>
					<td width="15%;" class="t_r" style="white-space:nowrap">项目名称</td>
					<td width="15%;" class="t_r" style="white-space:nowrap">合同名称</td>
					<td width="15%;" class="t_r" style="white-space:nowrap">合同自有编号</td>
					<td width="10%;" class="t_r" style="white-space:nowrap">线路</td>
					<td width="10%;" class="t_r" style="white-space:nowrap">政府补贴数(万元)</td>
					<td width="10%;" class="t_r" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.page.result" id="fundAllowance" status="st">
					<tr>
						<td class="t_c" style="word-break:break-all"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.year"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.dispatchNo"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.projectName"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.contractName"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.contractNo"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.line"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#fundAllowance.money"/></td>
						<td class="t_c">
							<a href="showView.action?id=<s:property value="#fundAllowance.id"/>" style="float:inline;" target="_blank">查看</a>
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showEditPage('<s:property value="#fundAllowance.id"/>');" style="float:inline;">编辑</a>
						</td>
						<td class="t_c">
							<a href="#" style="float:inline;" onclick="deleteData('<s:property value="#fundAllowance.id"/>');">删除</a>
						</td>
					</tr>
					
				</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="11"><div class="clearfix">
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
		<!--Table End-->
	</div>
<div id="cover2" style="left: 0px; top: 0px;">
	<div id="tb_window" style="margin-left:30%;">
		<div style="background:#6699CC; text-align:right; height:28px; padding:5px;">
		    <label id="title" style="float:left; font-size:15px; color:blue;">导出合同支付清单</label>
		    <input type="button" onclick="hideExport();" value="关闭" style=" border-style:none;" />
		</div>
		<div style="padding-left:6px;padding-top: 6px;">
		<s:form action="export" name="fundAllowance" namespace="/fundAllowance" method="post" id="formExport">
			<table width="100%" >
				<tbody>
					<tr >
						<td class="lableTd">支付时间范围：</td>
						<td>
							<select name="year_s">
								<%int curYear = (Integer)request.getAttribute("curYear");
									for(int i=0;i<5;i++){
								%>
								<option <%=i==1?"selected":""%>><%=curYear-i%></option>
								<%
									}
								%>
							</select>
							<select name="month_s">
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11" selected>11</option>
								<option value="12">12</option>
							</select>							
							&nbsp;-&nbsp;
							<select name="year_e">
								<%
									for(int i=0;i<5;i++){
								%>
								<option <%=i==0?"selected":""%>><%=curYear-i%></option>
								<%
									}
								%>
							</select>
							<select name="month_e">
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12" selected>12</option>
							</select>								
						</td>
					</tr>
					<tr>
						<td class="t_c" colspan="2">
							<input type="button" value="导出" onclick="doExport();">
							<input type="reset" value="重置">
						</td>
					</tr>
				</tbody>
			</table>
			</s:form>
		</div>
	</div>
</div>	
</body>
</html>
