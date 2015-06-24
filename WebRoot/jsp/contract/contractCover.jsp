<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<title>合同封面</title>
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
<script src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<% String basePath = request.getContextPath();  %>
<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.40.201:8088/portal/portal.jsp?random=Math.random()");
}  
</script>

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
	$("dd").width(h1-h2-100);
	$(window).resize(function() {
    	var h1 = $("dl").width();
		var h2 = $("dt").width();
		$("dd").width(h1-h2-100);
	});
	var $tblAlterRow = $(".table_1 tbody tr:even");
	if ($tblAlterRow.length > 0)
	$tblAlterRow.css("background","#fafafa");
});

function refreshData(){
	window.location = "<%=basePath%>/dwContract/calculateDwContractCover.action";
}
    
</script>
 
 <style type="text/css">
#contractTypeList ul li a {
	display:inline;
	margin: 5px;
}
</style>
 
</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>

	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
			<div class="posi fl">
				<ul>
					<li><a href="javascript:void(0);">工程投资造价管理</a></li>
					<li class="fin">合同封面</li>
				</ul>
			</div>
			<div class="fr lit_nav">
				<ul>
					<li><a class="print" href="#" onclick="window.print();">打印</a></li>
					<li><a class="storage" href="#">网络硬盘</a></li>
					<li><a class="rss" href="#">订阅</a></li>
					<li><a class="chat" href="#">聊天</a></li>
					<li><a class="query" href="findContractByPage.action?showOrHide=show">查询</a></li>
				</ul>
			</div>
		</div>
		<div class="filter">
			<div class="query" id="quickSearch">
				<div class="p8 stat_3">
				
					<dl class="clearfix" id="dl1">
						<dt style="width:100px;">全部合同：</dt>
						<dd>
							<ul id ="ul">
								<li><a href="findContractByPage.action" >全部(<span><s:property value="#request.all.numbers"/></span>)</a></li>
								<!-- <li style="float: right;"><input type="button" value="刷新数据" onclick="refreshData();"></li> -->
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="width:100px;">建设类合同：</dt>
						<dd>
							<ul>
								<li><a href="findContractByPage.action?contractType=1," >全部(<span><s:property value="#request.first.numbers"/></span>)</a></li>
							</ul>
						</dd>
					</dl>
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="font-weight: normal;">甲方(执行)：</dt>
						<dd>
							<a href="findContractByPage.action?cType=1" style="color: #1d4668;">全部(<span style="display:inline;padding-left:5px;padding-right:5px;color:#ed6d00;"><s:property value="#request.type1Company1All"/></span>)</a>
							<ul>
								<dl class="clearfix" style="border-bottom: none; ">
									<dt style="text-align: left;" class="L_20">项目公司：</dt>
									<dd>
										<ul>
											<s:iterator value="#request.company1List" id="list">
												<li>
													<s:if test="#list.numbers==0">
														<a href="javascript:void(0);"><s:property value="#list.companyName"/>(<span><s:property value="#list.numbers"/></span>)</a>													
													</s:if>
													<s:else>
														<a href="findContractByPage.action?cType=1&contractOwnerExecuteId=<s:property value='#list.companyId'/>"><s:property value="#list.companyName"/>(<span><s:property value="#list.numbers"/></span>)</a>
													</s:else>
												</li>
											</s:iterator>
										</ul>
									</dd>
								</dl>
							</ul>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">其他：</dt>
									<dd>
										<ul>
											<li><a href="findContractByPage.action?cType=1&companyType=company1&findType=notin" >其他
											(<span>
												<s:property value="#request.otherType1"/>
											</span>)</a></li>
										</ul>
									</dd>
								</dl>
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" >
						<dt style="font-weight: normal;">合同分类：</dt>
						<dd>
							<ul>
								<s:iterator value="#request.type1List" id="list">
									<li><a href="findContractByPage.action?contractType=<s:property value='#list.contractType'/>" >
									建设类-<s:if test="#list.contractType=='1,1'">工程类</s:if>
									<s:elseif test="#list.contractType=='1,2'">勘察类</s:elseif>
									<s:elseif test="#list.contractType=='1,3'">设计类</s:elseif>
									<s:elseif test="#list.contractType=='1,4'">前期类</s:elseif>
									<s:elseif test="#list.contractType=='1,5'">其他类</s:elseif>
									<s:elseif test="#list.contractType=='1,6'">监理类</s:elseif>
									<s:elseif test="#list.contractType=='1,7'">科研类</s:elseif>
									<s:elseif test="#list.contractType=='1,8'">咨询类</s:elseif>
									(<span><s:property value='#list.numbers'/></span>)</a></li>
								</s:iterator>
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="width:100px;">运维类合同：</dt>
						<dd>
							<ul>
								<li>
									<a href="findContractByPage.action?contractType=2," >全部(<span><s:property value="#request.second.numbers"/></span>)</a>
								</li>
							 </ul>
						</dd>
					</dl>
					
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="font-weight: normal;">甲方(执行)：</dt>
						<dd>
						<a href="findContractByPage.action?cType=2" style="color: #1d4668;">全部(<span style="display:inline;padding-left:5px;padding-right:5px;color:#ed6d00;"><s:property value="#request.type2Company1All"/></span>)</a>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">运营公司：</dt>
									<dd>
										<ul>
											<s:iterator value="#request.company2List" id="list">
												<s:if test="#list.numbers==0">
													<li><a href="javascript:void(0);"><s:property value="#list.companyName"/>(<span><s:property value="#list.numbers"/></span>)</a></li>
												</s:if>
												<s:else>
													<li><a href="findContractByPage.action?cType=2&contractOwnerExecuteId=<s:property value="#list.companyId"/>"><s:property value="#list.companyName"/>(<span><s:property value="#list.numbers"/></span>)</a></li>
												</s:else>
											</s:iterator>
										</ul>
									</dd>
								</dl>
							</ul>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">维保中心：</dt>
									<dd>
										<ul>
											<s:iterator value="#request.company3List" id="list">
												<s:if test="#list.numbers==0">
													<li><a href="javascript:void(0);"><s:property value='#list.companyName'/>(<span>0</span>)</a></li>
												</s:if>
												<s:else>
													<li><a href="findContractByPage.action?cType=2&contractOwnerExecuteId=<s:property value='#list.companyId'/>"><s:property value='#list.companyName'/>(<span><s:property value='#list.numbers'/></span>)</a></li>
												</s:else>
											</s:iterator>
										</ul>
									</dd>
								</dl>
							</ul>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">其他：</dt>
									<dd>
										<ul>
											<li><a href="findContractByPage.action?cType=2&contractOwnerExecuteId=<s:property value='#request.company4.companyId'/>" ><s:property value="#request.company4.companyName"/>(<span><s:property value="#request.company4.numbers"/></span>)</a></li>
											<li><a href="findContractByPage.action?cType=2&contractOwnerExecuteId=<s:property value='#request.company5.companyId'/>" ><s:property value="#request.company5.companyName"/>(<span><s:property value="#request.company5.numbers"/></span>)</a></li>
											<li><a href="findContractByPage.action?cType=2&findType=notin" >其他(<span><s:property value="#request.otherType2"/></span>)</a></li>
										</ul>
									</dd>
								</dl>
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" >
						<dt style="font-weight: normal;">合同分类：</dt>
						<dd>
							<ul>
								<s:iterator value="#request.type2List" id="list">
									<s:if test="#list.numbers==0">
										<li><a href="javascript:void(0);" >
										运维类-
										<s:if test="#list.contractType=='2,1'">工程类</s:if>
										<s:elseif test="#list.contractType=='2,2'">货物类</s:elseif>
										<s:elseif test="#list.contractType=='2,3'">服务类</s:elseif>
										(<span>0</span>)</a></li>
									</s:if>
									<s:else>
										<li><a href="findContractByPage.action?contractType=<s:property value='#list.contractType'/>" >
										运维类-
										<s:if test="#list.contractType=='2,1'">工程类</s:if>
										<s:elseif test="#list.contractType=='2,2'">货物类</s:elseif>
										<s:elseif test="#list.contractType=='2,3'">服务类</s:elseif>
										(<span><s:property value="#list.numbers"/></span>)</a></li>
									</s:else>
								</s:iterator>
							</ul>
						</dd>
					</dl>
					
					
					
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="width:100px;">其他类合同：</dt>
						<dd>
							<ul>
								<li><a href="findContractByPage.action?cType=3&contractType=3,1">全部(<span><s:property value="#request.third.numbers"/></span>)</a></li>
							</ul>
						</dd>
					</dl>
					
				</div>
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<!--Table End-->
	</div>
</body>
</html>
