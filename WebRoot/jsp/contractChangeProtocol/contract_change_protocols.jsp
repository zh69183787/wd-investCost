﻿
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>合同变更协议列表</title>
    <link rel="stylesheet" href="../css/formalize.css"/>
    <link rel="stylesheet" href="../css/page.css"/>
    <link rel="stylesheet" href="../css/imgs.css"/>
    <link rel="stylesheet" href="../css/reset.css"/>
    <link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>

    <!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="../js/jquery-1.7.1.js"></script>
    <script src="../js/jquery.formalize.js"></script>
    <script src="../js/jquery-ui-1.8.18.custom.min.js"></script>

</head>
<script>
    $(function(){
        $("#addProtocolBtn").click(function(){
            window.open("<s:url action="protocol" namespace="/contractChangeProtocol"></s:url>");
        })
    })
</script>
<body>

<div class="main"><!--Ctrl-->

    <div class="pt45">

        <%--<div class="filter">--%>
            <%--<div class="query" id="quickSearch"  style="display:none;">--%>
                <%--<div class="p8 stat_3">--%>
                    <%--<dl class="clearfix">--%>
                    <%--</dl>--%>
                <%--</div>--%>
            <%--</div>--%>


            <%--<div class="fn clearfix">--%>
	        		<%--<span style="float:left;">--%>
	        			<%--<input type="button" id="addProtocolBtn" value="新增"/>--%>
	        		<%--</span>--%>
            <%--</div>--%>

        <%--</div>--%>


        <!--Table-->
        <div class="mb10">

            <table width="100%" class="table_1">
                <thead>
                <th colspan="7">&nbsp;&nbsp;合同变更协议列表<span style="float:right;"><input type="button" id="addProtocolBtn" value="新增"/></span></th>
                </thead>
                <tbody>
                <tr class="tit">
                    <td width="5%;" style="white-space:nowrap">序号</td>
                    <td width="15%;" class="t_r" style="white-space:nowrap">合同名称</td>
                    <td width="15%;" class="t_r" style="white-space:nowrap">合同编号</td>
                    <td width="8%;" class="t_r" style="white-space:nowrap">合同金额</td>
                    <td width="8%;" class="t_r" style="white-space:nowrap">拟变金额</td>
                    <td width="39%;" class="t_r" style="white-space:nowrap">变更事项</td>
                    <td width="10%;" class="t_r" colspan="3" style="white-space:nowrap">操作</td>
                </tr>
                <s:iterator value="page.result" id="protocol" status="st">
                    <tr>
                        <td class="t_c" style="white-space: nowrap;"><s:property value="page.start+#st.index"/></td>
                        <td class="t_l" style="white-space: nowrap;" title="<s:property value="contractName"/>">
                            <span><s:property value="contractName"/></span>
                        </td>
                        <td class="t_l" style="white-space: nowrap;" title="<s:property value="contractNo"/>">
                            <span><s:property value="contractNo"/></span>
                        </td>
                        <td class="t_r" style="white-space: nowrap;" title="<s:property value="contractPrice"/>">
                            <span><s:property value="contractPrice"/></span>
                        </td>
                        <td class="t_r" style="white-space: nowrap;" title="<s:property value="changePrice"/>">
                            <span><s:property value="changePrice"/></span>
                        </td>
                        <td class="t_c" style="white-space: nowrap;">
                            <s:iterator value="contractChangeItemList" status="i">
                                <a style="display:inline;cursor:pointer;" target="_blank" href="<s:url action="changeItem" namespace="/contractChangeItem" includeParams="none"><s:param name="id" value="id"></s:param></s:url>"><s:property value="changeSerialNo"/>;</a>
                            </s:iterator>
                        </td>
                        <td class="t_c" style="white-space: nowrap;">
                            <a href="<s:url action="protocol" namespace="/contractChangeProtocol"><s:param name="id" value="id"></s:param><s:param name="mode" value="'view'"></s:param></s:url>" style="display:inline;" target="_blank">查看</a>&nbsp;&nbsp;
                            <a href="<s:url action="protocol" namespace="/contractChangeProtocol"><s:param name="id" value="id"></s:param></s:url>" style="display:inline;">编辑</a>&nbsp;&nbsp;
                            <a href="<s:url action="delete" namespace="/contractChangeProtocol"><s:param name="id" value="id"></s:param></s:url>" style="display:inline;">删除</a>
                        </td>
                    </tr>
                </s:iterator>

                </tbody>

                <tr class="tfoot">
                    <td colspan="7">
                        <div class="clearfix">
                            <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
                            <s:else>
					      	<span class="fl">
						      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property
                                    value="#request.page.start"/> -
							    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
                                    <s:property value="#request.page.totalSize"/>条
                                </s:if>
							    <s:else>
                                    <s:property value="#request.page.start+#request.page.pageSize-1"/>条
                                </s:else>
						    </span>
                                <ul class="fr clearfix pager">
                                    <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property
                                            value="#request.page.totalPageCount"/>
                                        <input type="hidden" value="<s:property value='#request.page.totalPageCount'/>"
                                               id="totalPageCount">
                                        <input type="text" id="number" name="pageNumber" min="0" max="999" step="1"
                                               class="input_tiny"
                                               value="<s:property value='#request.page.currentPageNo'/>"/>
                                        <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
                                    </li>

                                    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
                                        <li><a href="javascript:void(0)">&gt;&gt;</a></li>
                                    </s:if>
                                    <s:else>
                                        <li><a href="javascript:void(0)"
                                               onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">
                                            &gt;&gt;</a></li>
                                    </s:else>

                                    <li>
                                        <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
                                            <a href="javascript:void(0)">下一页</a>
                                        </s:if>
                                        <s:else>
                                            <a href="javascript:void(0)" onclick="goPage(<s:property
                                                    value='#request.page.currentPageNo'/>,2)">下一页</a>
                                        </s:else>
                                    </li>

                                    <li>
                                        <s:if test="#request.page.currentPageNo==1">
                                            <a href="javascript:void(0)">上一页</a>
                                        </s:if>
                                        <s:else>
                                            <a href="javascript:void(0)" onclick="goPage(<s:property
                                                    value='#request.page.currentPageNo'/>,1)">上一页</a>
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
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!--Table End-->
    </div>
</div>
<div>
</div>
</body>
</html>
