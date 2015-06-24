<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String basePath = request.getContextPath();
%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8"/>
<title>评标室使用情况统计</title>
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

    //画柱状图
    function drawChart() {
        $.ajax({
            type: 'post',
            url: '<%=basePath%>/meetEvent/getEvaluationInfo.action?random=' + Math.random(),
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
                var categories = [] , countList = [];
                if (object != null && object.result.length > 0) {
                    var rows = "";
                    for (var i = 0; i < object.result.length; i++) {
                        var obj = object.result[i];
                        var month = obj.month;
                        var count = obj.count;
                        var type1 = getNum(count["2,2"]);

                        categories.push(month);
                        countList.push(count);
                    }
                }
                var chartOption = {
                    chart: {
                        renderTo: 'chartArea',
                        type: 'column',
                        backgroundColor: '#F0F0F2'
                    },
                    title: {
                        text: '评标室使用情况统计'
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
                            name: '汇总',
                            data: countList
                        }
                    ]
                }
                new Highcharts.Chart(chartOption);
            }
        });
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
                <li class="fin">评标室使用情况统计</li>
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
                        <td class="t_r">会议日期</td>
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
</div>
</body>
</html>
