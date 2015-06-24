<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String basePath = request.getContextPath();
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>维修合同情况统计</title>
    <link rel="stylesheet" href="<%=basePath%>/css/formalize.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/page.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/imgs.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/reset.css"/>
    <link type="text/css" href="<%=basePath%>/js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script src="<%=basePath%>/js/jquery-1.7.1.js"></script>
    <script src="<%=basePath%>/js/jquery.formalize.js"></script>
    <script src="<%=basePath%>/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="<%=basePath%>/js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
    <script src="<%=basePath%>/js/highcharts.js"></script>
    <style type="text/css">
        tr td {
            white-space: nowrap;
        }

        #h {
            font-size: 20px;
            text-align: center;
        }

        #h1 {
            font-size: 20px;
            text-align: right;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $(".odd tr:odd").css("background", "#fafafa");

            //日期控件
            $("#startTime").datepicker({
                inline: true,
                changeYear: true,
                changeMonth: true,
                showOtherMonths: true,
                selectOtherMonths: true,
                onSelect: function (selectedDate) {
                    $("#endTime").datepicker("option", "minDate", selectedDate);
                }
            });
            $("#endTime").datepicker({
                inline: true,
                changeYear: true,
                changeMonth: true,
                showOtherMonths: true,
                selectOtherMonths: true,
                onSelect: function (selectedDate) {
                    $("#startTime").datepicker("option", "maxDate", selectedDate);
                }
            });

            $("#go").click(function () {
                $("#fixForm").submit();
            });

            var v = 0;
            $("#total").children("td:gt(1)").each(function(i,n){
                v = 0;
                var num = i+3;
                $(".show").find("td:nth-child("+num+")").each(function(i,n){
                    //console.log($(n).text());
                    //console.log(getNum($(n).text()));
                    v += getNum($(n).text());
                });
                $(n).html(v);
            });

        });

        function getSName(name) {
            var result = "";
            switch (name) {
                case "上海申通地铁集团有限公司":
                    result = "集团公司";
                    break;
                case "上海轨道交通运营管理中心":
                    result = "运管中心";
                    break;
                case "上海地铁第一运营有限公司":
                    result = "运一公司";
                    break;
                case "上海地铁第二运营有限公司":
                    result = "运二公司";
                    break;
                case "上海地铁第三运营有限公司":
                    result = "运三公司";
                    break;
                case "上海地铁第四运营有限公司":
                    result = "运四公司";
                    break;
                case "上海地铁维护保障有限公司":
                    result = "维保公司";
                    break;
                case "上海轨道交通教育培训中心":
                    result = "教培中心";
                    break;
                case "上海轨道交通信息管理中心":
                    result = "信息中心";
                    break;
                case "上海地铁维护保障有限公司车辆分公司":
                    result = "车辆分公司";
                    break;
                case "上海地铁维护保障有限公司供电分公司":
                    result = "供电分公司";
                    break;
                case "上海地铁维护保障有限公司通号分公司":
                    result = "通号分公司";
                    break;
                case "上海地铁维护保障有限公司工务分公司":
                    result = "工务分公司";
                    break;
                case "上海地铁维护保障有限公司物资和后勤分公司":
                    result = "物资和后勤分公司";
                    break;
                case "上海申通地铁集团股份有限公司":
                    result = "股份公司";
                    break;
                default:
                    result = "";
                    break;
            }
            return result;
        }

        function getNum(v) {
            if (typeof(v) == "undefined" || v == null || !v) {
                return 0;
            } else {
                return parseFloat(v);
            }
        }

        //清除查询框
        function clearSearch() {
            $("#startTime").val("");
            $("#endTime").val("");
        }

    </script>

</head>

<body>
<div class="main">
    <div class="ctrl clearfix">
        <div class="fl">
            <img src="<%=basePath%>/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起">
        </div>
        <div class="posi fl">
            <ul>
                <li><a href="#">办公会报表</a></li>
                <li class="fin">维修合同情况统计</li>
            </ul>
        </div>
        <div class="fr lit_nav">
            <ul>
                <li><a class="print" href="#" onclick="window.print();">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
            </ul>
        </div>
    </div>

    <div class="pt45">
        <div class="filter">
            <div class="query p8" id="searchArea">
                <s:form namespace="/meetEvent" action="getFixContractInfo" id="fixForm" name="fixForm" theme="simple">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="t_r">签约日期</td>
                            <td>
                                <input readonly="readonly" type="text" id="startTime" name="startTime" value="<s:property value="#request.startTime"/>"/>
                                至
                                <input readonly="readonly" type="text" id="endTime" name="endTime" value="<s:property value="#request.endTime"/>"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="t_c">
                                <input id="gggo" type="submit" value="检 索"/>&nbsp;&nbsp;
                                <input type="button" value="重 置" onclick="clearSearch();"/></td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>

        <!-- 图表区域 -->
        <div>
            <div id="chartArea" style="display: block;"></div>
        </div>


    </div>
    <!--Tabs_2 End-->
    <div id="h">维修合同情况统计</div>
    <div id="h1">单位：万元</div>
    <div class="mb10">
        <table width="100%" class="table_1">
            <tbody>
            <tr class="tit">
                <td>序号</td>
                <td>单位</td>
                <td>已签约总数</td>
                <td>其中维修费合同数</td>
                <td>已签约总金额（万元）</td>
                <td>其中维修费合同金额（万元）</td>
            </tr>
            <s:iterator value="#request.result" id="obj" status="s">
                <tr class="show">
                    <td class="t_c"><s:property value="#s.index+1"/></td>
                    <td class="t_l"><s:property value="#obj.company"/></td>
                    <td class="t_l"><s:property value="#obj.count"/></td>
                    <td class="t_l"><s:property value="#obj.fixCount"/></td>
                    <td class="t_l"><s:property value="#obj.price"/></td>
                    <td class="t_l"><s:property value="#obj.fixPrice"/></td>
                </tr>
            </s:iterator>
            <tr id="total">
                <td class="t_c"></td>
                <td class="t_l">合计</td>
                <td class="t_l"></td>
                <td class="t_l"></td>
                <td class="t_l"></td>
                <td class="t_l"></td>
            </tr>
            </tbody>
            <tr class="tfoot">
                <td colspan="12">
                    <div class="clearfix"></div>
                </td>
            </tr>
        </table>
    </div>
    <!--Table End-->
</div>
</body>
</html>
