<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
<head>
<meta charset="utf-8" />
<title>变更事项</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
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
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>

<style type="text/css">
#contractTypeList ul li a {
	display:inline;
	margin: 5px;
}
#cover,#cover2
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
<%String basePath = request.getContextPath(); %>
<script type="text/javascript">
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

//删除某一条变更事项
function del(id){
		$.ajax({
		url			: 	'<%=basePath%>/contractChangeItem/del.action?id='+id,
		dataType	: 	"json",
		type		:	'post',

		error:function(){
			alert("系统连接失败请稍后再试！");
		},
		success: function( data ) {
			alert("删除成功！");
			//刷新列表
			$("#form").submit();
		}
	});
}

function showEditPage(id){
	sonWindow = window.open('<%=basePath%>/contractChangeItem/showEdit.action?id='+id);
}

//新增变更事项
function showAddPage(){
var url = "<%=basePath%>/contractChangeItem/showAdd.action?contractId=" +$("#contractId").val();

    	
 	sonWindow = window.open(url);
}


</script>

  <head>


	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		<s:form action="findByPage" name="contractChangeItem" namespace="/contractChangeItem" method="post" id="form">
		<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
		<input type="hidden" name="contractId" id="contractId" value="<s:property value='#request.contractId' />"  >
		</s:form>
    		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">
					<span style="float:left">&nbsp;&nbsp;补充协议清单</span>

					<span style="float:right">
						<input type="button" id="addSupplementaryContract" value="新增" onclick="showAddPage();"/>
					</span>
				</th>
			</thead>
			<tbody id="suplementary">
				<tr class="tit">
				
					<td width="5%;" class="t_c">序号</td>
					<td width="20%;" class="t_c" >变更流水号</td>
					<td width="10%;" class="t_c" >变更提出方</td>

					<td width="10%;" class="t_c" colspan='2'>变更事项分类</td>
	
					<td width="10%;" class="t_c" >审批通过时间</td>

					<td width="10%;" class="t_c" colspan="3">操作</td>
				</tr>

				
							<s:iterator value="#request.page.result" id="contractChangeItem" status="st">
							<tr>
								<td class="t_c" style="white-space: nowrap;">
									<input type="checkbox" name="contractCheck" value="<s:property value='#contractChangeItem.id'/>" id="<s:property value='#contract.projectId'/>"/>
									<s:property value="#request.page.start+#st.index"/>
								</td>
								<td class="t_l" style="white-space: nowrap;" title="<s:property value="#contractChangeItem.changeSerialNo"/>">
									<span ><s:property value="#contractChangeItem.changeSerialNo"/></span>
								</td>
								<td class="t_l" title="<s:property value="#contractChangeItem.changePutForward"/>" style="white-space: nowrap;">
									<span ><s:property value="#contractChangeItem.changePutForward"/></span>
								</td>
								
								<td class="t_l" title="<s:property value="#contractChangeItem.changeItemType1"/>" style="white-space: nowrap;">
									<span ><s:property value="#contractChangeItem.changeItemType1"/></span>
								</td>
								<td class="t_l" title="<s:property value="#contractChangeItem.changeItemType2"/>" style="white-space: nowrap;">
									<span ><s:property value="#contractChangeItem.changeItemType2"/></span>
								</td>
								<td class="t_l" title="<s:property value="#contractChangeItem.passTime"/>" style="white-space: nowrap;">
									<span ><s:property value="#contractChangeItem.passTime"/></span>
								</td>
																													

	
								<td class="t_c" style="white-space: nowrap;">
									<a href="showView.action?id=<s:property value="#contractChangeItem.id"/>" style="display:inline;" target="_blank">查看</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="showEditPage('<s:property value="#contractChangeItem.id"/>');" style="display:inline;">编辑</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="del('<s:property value="#contractChangeItem.id"/>');" style="display:inline;">删除</a>&nbsp;&nbsp;
								</td>
							</tr>
							
						</s:iterator>
			</tbody>
					<tr class="tfoot">
					      <td colspan="12"><div class="clearfix">
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

  </body>
</html>
