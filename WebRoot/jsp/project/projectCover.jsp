<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<title>项目封面</title>
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
	window.location = "<%=basePath%>/dwProject/calculateDwProjectCover.action";
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
					<li class="fin">项目封面</li>
				</ul>
			</div>
			<div class="fr lit_nav">
				<ul>
					<li><a class="print" href="#" onclick="window.print();">打印</a></li>
					<li><a class="storage" href="#">网络硬盘</a></li>
					<li><a class="rss" href="#">订阅</a></li>
					<li><a class="chat" href="#">聊天</a></li>
					<li><a class="query" href="findProjectByPage.action?showOrHide=show">查询</a></li>
				</ul>
			</div>
		</div>
		<div class="filter">
			<div class="query" id="quickSearch">
				<div class="p8 stat_3">
				
					<dl class="clearfix" id="dl1">
						<dt style="width:100px;">全部项目：</dt>
						<dd>
							<ul id ="ul">
								<li><a href="findProjectByPage.action" >全部(<span><s:property value="#request.all"/></span>)</a></li>
								<!-- <li style="float: right;"><input type="button" value="刷新数据" onclick="refreshData();"></li> -->
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="width:100px;">内部项目：</dt>
						<dd>
							<ul id ="ul">
								<li>
									<a href="findProjectByPage.action?pType=0" onclick="" >全部(<span><s:property value="#request.inner"/></span>)</a>
								</li>
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" >
						<dt style="font-weight: normal;">负责部门：</dt>
						<dd>
						<a href="findProjectByPage.action?pType=0" style="color: #1d4668;">全部(<span style="display:inline;padding-left:5px;padding-right:5px;color:#ed6d00;"><s:property value="#request.inner-#request.innerOther-#request.ygCompany.numbers-#request.eduCompany.numbers"/></span>)</a>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">运营公司：</dt>
									<dd>
										<ul>
											<s:iterator value="#request.innerList1" id="list">
												<li><a href="findProjectByPage.action?pType=0&projectAdddeptId=<s:property value='#list.companyId'/>"><s:property value="#list.companyName"/>(<span><s:property value='#list.numbers'/></span>)</a></li>
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
											<s:iterator value="#request.innerList2" id="list">
												<li><a href="findProjectByPage.action?pType=0&projectAdddeptId=<s:property value='#list.companyId'/>"><s:property value="#list.companyName"/>(<span><s:property value='#list.numbers'/></span>)</a></li>
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
											<s:if test="#request.ygCompany.numbers==0">
												<li><a href="javascript:void(0);" ><s:property value="#request.ygCompany.companyName"/>(<span>0</span>)</a></li>
											</s:if>
											<s:else>
												<li><a href="findProjectByPage.action?pType=0&projectAdddeptId=<s:property value='#request.ygCompany.companyId'/>" ><s:property value="#request.ygCompany.companyName"/>(<span><s:property value="#request.ygCompany.numbers"/></span>)</a></li>
											</s:else>
											
											<s:if test="#request.eduCompany.numbers==0">
												<li><a href="javascript:void(0);" ><s:property value="#request.eduCompany.companyName"/>(<span>0</span>)</a></li>
											</s:if>
											<s:else>
												<li><a href="findProjectByPage.action?pType=0&projectAdddeptId=<s:property value='#request.eduCompany.companyId'/>" ><s:property value="#request.eduCompany.companyName"/>(<span><s:property value="#request.eduCompany.numbers"/></span>)</a></li>
											</s:else>
										
										
										
											<li><a href="findProjectByPage.action?pType=0&findType=notin&projectAdddeptId=-1" >其他(<span><s:property value="#request.innerOther"/></span>)</a></li>
										</ul>
									</dd>
								</dl>
							</ul>
						</dd>
					</dl>
					<dl class="clearfix" style="border-bottom: none;">
						<dt style="width:100px;">外部项目：</dt>
						<dd>
							<ul id ="ul">
								<li>
									<a href="findProjectByPage.action?pType=1">全部(<span><s:property value="#request.out"/></span>)</a>
								</li>
							</ul>
						</dd>
					</dl>
					
					<dl class="clearfix" >
						<dt style="font-weight: normal;">负责部门：</dt>
						<dd>
						<a href="findProjectByPage.action?pType=1" style="color: #1d4668;">全部(<span style="display:inline;padding-left:5px;padding-right:5px;color:#ed6d00;"><s:property value="#request.out-#request.outOther"/></span>)</a>
							<ul>
								<dl class="clearfix" style="border-bottom: none;">
									<dt style="text-align: left;" class="L_20">项目公司：</dt>
									<dd>
										<ul>
											<s:iterator value="#request.outList" id="list">
												<li ><a href="findProjectByPage.action?pType=1&projectAdddeptId=<s:property value='#list.companyId'/>"><s:property value='#list.companyName'/>(<span><s:property value='#list.numbers'/></span>)</a></li>
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
											<li><a href="findProjectByPage.action?pType=1&projectAdddeptId=-1&findType=notin" >其他(<span><s:property value='#request.outOther'/></span>)</a></li>
										</ul>
									</dd>
								</dl>
							</ul>								
						</dd>
					</dl>
					
					<dl class="clearfix" >
						<dt style="width:100px;">其他项目：</dt>
						<dd>
							<ul>
								<li><a href="findProjectByPage.action?pType=5">全部(<span><s:property value="#request.other"/></span>)</a></li>
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
