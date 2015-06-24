<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String basePath = request.getContextPath();
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>已签订合同总体情况</title>
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

            $("#submit").click(function () {
                drawChart();
            });

            $("#submit").click();

        });

        function getSName(name){
            var result = "";
            switch(name){
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
                return v;
            }
        }

        //清除查询框
        function clearSearch() {
            $("#startTime").val("");
            $("#endTime").val("");
        }

        //画柱状图
        function drawChart() {
            $.ajax({
                type: 'post',
                url: '<%=basePath%>/meetEvent/getSignedContractInfo.action?random=' + Math.random(),
                dataType: 'json',
                cache: false,
                data: {
                    startTime: $("#startTime").val(),
                    endTime: $("#endTime").val()
                },
                error: function () {
                    alert("系统连接失败，请稍后再试！")
                },
                success: function (object) {
                    var categories = [] , type1List = [], type2List = [], type3List = [];
                    $("#show").children("tr:gt(0)").html("");
                    if (object != null && object.result.length > 0) {
                        var rows = "";
                        for (var i = 0; i < object.result.length; i++) {
                            var obj = object.result[i];
                            var price = obj.price.toFixed(0);
                            var company = obj.company;
                            var count = obj.count;
                            var type1 = getNum(count["2,2"]);
                            var type2 = getNum(count["2,1"]);
                            var type3 = getNum(count["2,3"]);

                            categories.push(getSName(company) + "<br>（" + price + "万元）");
                            type1List.push(type1);
                            type2List.push(type2);
                            type3List.push(type3);
                            rows += "<tr>";
                            rows += "<td class=\"t_c\">"+(i+1)+"</td>";
                            rows += "<td>"+company+"</td>";
                            rows += "<td>"+price+"</td>";
                            rows += "<td>"+type1+"</td>";
                            rows += "<td>"+type2+"</td>";
                            rows += "<td>"+type3+"</td>";
                            rows += "</tr>";
                        }
                        $("#show").append(rows);
                    }
                    var chartOption = {
                        chart: {
                            renderTo: 'chartArea',
                            type: 'column',
                            backgroundColor: '#F0F0F2'
                        },
                        title: {
                            text: '已签合同总体情况'
                        },
                        xAxis: {
                            categories: categories
                        },
                        yAxis: {
                            min: 0,
                            //opposite:true,
                            title: {
                                text: null
                            },
                            stackLabels: {
                                enabled: true,
                                style: {
                                    fontWeight: 'bold',
                                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                                }
                            }
                        },
                        credits: {
                            enabled: false
                        },
                        legend: {
                            enabled: true,
                            borderColor: '#CCC',
                            borderWidth: 1,
                            shadow: false
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.x + '</b><br/>' +
                                        this.series.name + ': ' + this.y + '<br/>' +
                                        '总数: ' + this.point.stackTotal;
                            }
                        },
                        plotOptions: {
                            column: {
                               // colorByPoint:true,
                                pointPadding: 0.2,
                                stacking: 'normal',
                                dataLabels: {
                                    enabled: true,
                                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                    style: {
                                        textShadow: '0 0 3px black'
                                    }
                                }
                            },
                            // 点击柱状图后触发
                            series: {
                                cursor: 'pointer',
                                events: {
                                    click: function (event) {
                                       // alert(event.point.category);
                                    }
                                }
                            }
                        },
                        series: [
                            {
                                name: '服务',
                                data: type1List
                            },
                            {
                                name: '工程',
                                data: type2List
                            },
                            {
                                name: '货物',
                                data: type3List
                            }
                        ]
                    }
                    new Highcharts.Chart(chartOption);
                }
            });
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
                <li class="fin">已签合同总体情况</li>
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
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="t_r">签约日期</td>
                        <td>
                            <input readonly="readonly" type="text" id="startTime" name="startTime" value=""/>
                            至
                            <input readonly="readonly" type="text" id="endTime" name="endTime" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="t_c">
                            <input id="submit" type="button" value="检 索"/>&nbsp;&nbsp;
                            <input type="button" value="重 置" onclick="clearSearch();"/></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 图表区域 -->
        <div>
            <div id="chartArea" style="display: block;"></div>
        </div>


    </div>
    <!--Tabs_2 End-->
    <div id="h">已签合同总体情况</div>
    <div id="h1">单位：万元</div>
    <div class="mb10">
        <table width="100%" class="table_1">
            <tbody id="show">
            <tr class="tit">
                <td>序号</td>
                <td>甲方执行</td>
                <td>合同总金额（万元）</td>
                <td>服务（个）</td>
                <td>工程（个）</td>
                <td>货物（个）</td>
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
