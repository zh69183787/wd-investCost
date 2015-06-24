<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="IE=8">
    <title>合同变更协议</title>
    <link rel="stylesheet" href="../css/formalize.css"/>
    <link rel="stylesheet" href="../css/page.css"/>
    <link rel="stylesheet" href="../css/imgs.css"/>
    <link rel="stylesheet" href="../css/reset.css"/>
    <link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
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
    <script src="../js/html5.js"></script>
    <script src="../js/jquery-1.7.1.js"></script>
    <script src="../js/jquery.formalize.js"></script>
    <script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>

    <style type="text/css">
        .must {
            color: red;
            display: inline;
        }
    </style>
    <script type="text/javascript">
        //日期控件
        $(function () {
            var selectedChangeItemList = [];
            <s:if test="%{#parameters.mode[0]=='view'}">
            $(".must ").css("display", "none");
            $(".input_xxlarge,textarea,").css("border-color", "white");
            $(":text,textarea").prop("readonly", true);
            </s:if>
            <s:else>
            $("input[name^=execPeriod],input[name$=Time]").datepicker({
                inline: true,
                changeYear: true,
                changeMonth: true,
                showOtherMonths: true,
                selectOtherMonths: true
            });
            </s:else>


            getAttachList("<s:property value="attach"/>", <s:if test="%{#parameters.mode[0]=='view'}">false</s:if><s:else>true</s:else>);
            renderSelectedChangeItemList();

            $("form").submit(function () {
                var attachIds = new Array();
                var changeItemIds = new Array();
                $.each(selectedChangeItemList, function (i, value) {
                    changeItemIds.push(value.id);
                });
                $("#fileBody").find("a[name='attach_id']").each(function () {
                    attachIds.push($(this).attr("id"));
                });

                $(":hidden[name=attach]").val(attachIds.join(","));
                $(":hidden[name=changeItemIds]").val(changeItemIds.join(","));
            });

            $(":text[name=oppositeCompany]").autocomplete({
                autoFouces: true,
                source: function (request, response) {
                    $.ajax({
                        url: "<s:url action="findPsCorporationByCompNameWithFuzzySearch" namespace="/corporation"/>?random=" + Math.random(),
                        dataType: "json",
                        data: {
                            "type": 'post',
                            "compName": request.term
                        },
                        success: function (data) {
                            response($.map(data, function (item, index) {
                                return {
                                    label: item.companyNameChn,
                                    buildSupplierId: item.compId
                                }
                            }));
                        }
                    });
                },
                minLength: 1,
                change: function (event, ui) {
                },
                search: function () {
                    $(":text[name=corporationId]").val("");
                },
                select: function (event, ui) {
                    $(":text[name=oppositeCompany]").val(ui.item.label);
                    $(":text[name=corporationId]").val(ui.item.buildSupplierId);
                    return false;
                },
                open: function () {
                    $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
                },
                close: function () {
                    $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
                },
                focus: function (event, ui) {
                    return false;
                }
            });

            $.ajax({
                url: "<s:url action="changeItems" namespace="/contractChangeItem" includeParams="none"></s:url>",
                dataType: "json",
                data: {
                    "contractId": '<s:property value="contract.id"/>',
                    "notSelected": '1',
                    "contractChangeProtocolId": '<s:property value="id"/>',
                    "format" : 'json'
                },
                success: function (data) {
                    var $tbody = $("#changeItemList>table>tbody");
                    $("#changeItemList>table>tbody>tr").remove();

                    $.each(data.result, function (i, value) {
                        var $tr = $('<tr><td style="display: none;"></td><td></td><td></td><td></td><td class="t_r"></td><td class="t_c"></td><td></td></tr>');
                        $tr.find("td:eq(0)").html("<input type='checkbox' name='changeItemIdChk' value='" + value.id + "'>");
                        $tr.find("td:eq(1)").text(value.changeSerialNo || "");
                        $tr.find("td:eq(2)").text(value.contract.contractName || "");
                        $tr.find("td:eq(3)").text(value.contract.contractNo || "");
                        $tr.find("td:eq(4)").text((value.changePutForword || "") == "1" ? "合同甲方单位" : "合同乙方单位");
                        $tr.find("td:eq(5)").text(value.changePrice || "");
                        $tr.find("td:eq(6)").html('<a class="fl mr5" href="javascript:void(0)" name="addItemBtn">添加</a>');
                        $.each(selectedChangeItemList, function (i, v) {
                            if (value.id == v.id) {
                                $tr.find(":checkbox").prop("checked",true);
                                $tr.find("td:eq(6)").html("<span style='font-weight:bold;color:green;'>已添加</span>");
                            }
                        });
                        $tbody.append($tr);

                    });
                    $(".table_1>tbody>tr:even").css("background","#fafafa");
                    $("a[name=addItemBtn]").click(function () {
                        var id = $(this).parents("tr").find(":checkbox").val();
                        var flag = true;
                        $.each(selectedChangeItemList, function (i, value) {
                            if (value.id == id) {
                                flag = false;
                            }
                        });
                        if (flag) {
                            var result = data.result[$(this).parents("tr").index()];
                            result.contract.contractName = result.contract.contractName || "";
                            result.contract.contractNo = result.contract.contractNo || "";
                            result.changePutForword = result.changePutForword || "";
                            result.changePrice = result.changePrice || "";
                            result.changeSerialNo = result.changeSerialNo || "";
                            selectedChangeItemList.push(result);
                        }
                        renderSelectedChangeItemList();
                        $(this).parents("tr").find(":checkbox").prop("checked", true);
                        $(this).html("<span style='font-weight:bold;color:green;'>已添加</span>");
                        $(this).unbind("click");
                    });
                }
            });
            <s:if test="id!=null">
            $.ajax({
                url: "<s:url action="changeItems" namespace="/contractChangeItem" includeParams="none"></s:url>",
                dataType: "json",
                data: {
                    "contractId": '<s:property value="contract.id"/>',
                    "contractChangeProtocolId": '<s:property value="id"/>',
                    "format" : 'json'
                },
                success: function (data) {

                    $.each(data.result, function (i, result) {
                        result.contract.contractName = result.contract.contractName || "";
                        result.contract.contractNo = result.contract.contractNo || "";
                        result.changePutForword = result.changePutForword || "";
                        result.changePrice = result.changePrice || "";
                        result.changeSerialNo = result.changeSerialNo || "";
                        selectedChangeItemList.push(result);
                    });
                    renderSelectedChangeItemList();

                }
            });
            </s:if>

            $("#addChangeItemBtn").click(function(){
                $("#changeItemList").dialog({
                    modal: true,
                    title: "合同变更协议列表",
                    width: 900,
                    height: 400,
                    buttons: {
                        '确定': function () {
                            $(this).dialog('close');
                        },
                        '取消': function () {
                            $(this).dialog('close');
                        }
                    }
                });
            });

            function renderSelectedChangeItemList() {
                var $tbody = $("#selectedChangeItemList>tbody");
                $("#selectedChangeItemList>tbody>tr:gt(0)").remove();
                $.each(selectedChangeItemList, function (index, value) {
                    var $tr = $('<tr><td style="display: none;"></td><td></td><td colspan="2" class="t_c op"></td></tr>');
                    $tr.find("td:eq(0)").html("<input type='checkbox' name='selectedChangeItemIdChk' value='" + value.id + "'>");
                    $tr.find("td:eq(1)").text(value.changeSerialNo || "");
                    $tr.find("td:eq(2)").html('<a class="fl mr5" href="javascript:void(0)" name="deleteItemBtn">删除</a>');
                    $tbody.append($tr);
                });


                $("a[name=deleteItemBtn]").click(function () {
                    if (window.confirm("确认删除？")) {
                        var id = $(this).parents("tr:eq(0)").find(":hidden[name$=selectedChangeItemIdChk]").val();
                        for (var i = selectedChangeItemList.length - 1; i > -1; i--) {
                            if (selectedChangeItemList[i].id == id) {
                                selectedChangeItemList.splice(i,1);
                            }
                        }
                        renderSelectedChangeItemList();

                        $(":checkbox[name=changeItemIdChk][value="+id+"]").each(function(i, o){
                            $(o).parents("tr").find("td:eq(6)>a").html('<a class="fl mr5" href="javascript:void(0)" name="addItemBtn">添加</a>');
                        });
                    }

                });

            }

        });





        function clearInput() {
            $("form")[0].reset();
            resetAllFile();
        }


    </script>


</head>

<body>
<div class="main">
    <div class="ctrl clearfix">
        <div class="fl"><img src="../images/sideBar_arrow_left.jpg"
                             width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="#">工程投资造价管理</a></li>
                <li><a href="#">合同封面</a></li>
                <li><a href="#">合同管理</a></li>
                <li class="fin">合同变更协议</li>
            </ul>
        </div>
        <div class="fr lit_nav">
            <ul>
                <li><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
                <li><a class="query" href="#">查询</a></li>
            </ul>
        </div>
    </div>


    <div class="mb10">
        <!--
        <input type="hidden" id="subjectMoney">
         -->
        <s:form action="save" name="contractChangeProtocol" namespace="/contractChangeProtocol" method="post">
            <s:hidden name="id"/>
            <s:hidden name="contract.id"/>
            <s:hidden name="attach"/>
            <s:hidden name="changeItemIds"/>

            <table width="100%" class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    <s:if test="%{#parameters.mode[0]=='view'}">
                    </s:if><s:else>
                    <input type="submit" value="确 认"/>&nbsp;
                </s:else>
                    <input type="button" value="关 闭" onclick="window.close();"/> &nbsp;
                    <input type="reset" value="重 置" onclick="clearInput()"/> &nbsp;
                </th>
                </thead>
                <tbody id="formBody">

                <tr>
                    <td class="t_r lableTd" width="19%;">原合同名称</td>
                    <td colspan="3" width="100%;">
                        <s:textfield name="originalContractName" cssClass="input_xxlarge" cssStyle="width:985px;"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd" width="19%;">合同编号</td>
                    <td width="31%;">
                        <s:textfield name="contractNo" cssClass="input_xxlarge"/><span class="must">&nbsp;&nbsp;*</span>
                    </td>
                    <td class="t_r lableTd" width="20%;">自有编号</td>
                    <td width="30%;">
                        <s:textfield name="contractSelfNum" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd" width="19%;">合同名称</td>
                    <td colspan="3" width="100%;">
                        <s:textfield name="contractName" cssClass="input_xxlarge" cssStyle="width:985px;"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd" width="19%;">合同金额</td>
                    <td width="31%;">
                        <s:textfield name="contractPrice" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>

                    <td class="t_r lableTd" width="20%;">拟变金额</td>
                    <td width="30%;">
                        <s:textfield name="changePrice" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd" width="19%;">履行开始期限</td>
                    <td width="31%;">
                        <s:date name="execPeriodStart" format="yyyy-MM-dd" id="execPeriodStart"/>
                        <s:textfield name="execPeriodStart" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                    <td class="t_r lableTd" width="20%;">履行结束期限</td>
                    <td width="30%;">
                        <s:date name="execPeriodEnd" format="yyyy-MM-dd" id="execPeriodEnd"/>
                        <s:textfield name="execPeriodEnd" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>

                <tr>

                    <td class="t_r lableTd" width="20%;">对方公司</td>
                    <td  colspan="3">
                        <s:hidden name="corporationId"/>
                        <s:textfield name="oppositeCompany" cssStyle="width:985px;" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd" width="19%;">变更事项</td>
                    <td colspan="3" width="31%;">
                        <s:if test="%{#parameters.mode[0]=='view'}">
                        </s:if><s:else><input
                            type="button" id="addChangeItemBtn" value="添加合同变更事项"/>
                    </s:else>
                        <table id="selectedChangeItemList" style="">
                            <tbody style="width: 350px;border: solid;border-color: #D0D0D3;">
                            <tr style="border: solid;border: #D0D0D3;">
                                <td width="20px;" class="t_c" style="display:none;"></td>
                                <td width="150px;" class="t_c">变更流水号</td>
                                <td width="50px;" class="t_c op" colspan="2">操作</td>
                            </tr>

                            </tbody>
                        </table>
                    </td>
                </tr>


                <tr>
                    <td class="t_r lableTd">登记人</td>
                    <td >
                        <s:textfield name="regPerson" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                    <td class="t_r lableTd">经办人</td>
                    <td >
                        <s:textfield name="dealPerson" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">审批通过日期</td>
                    <td >
                        <s:date name="passTime" format="yyyy-MM-dd" id="passTime"/>
                        <s:textfield name="passTime" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                    <td class="t_r lableTd">合同签约时间</td>
                    <td >
                        <s:date name="signTime" format="yyyy-MM-dd" id="regTime"/>
                        <s:textfield name="signTime" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">经办部门领导意见</td>
                    <td colspan="3">
                        <s:textarea rows="5" cols="5" name="dealDeptSuggest"/>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">备注</td>
                    <td colspan="3">
                        <s:textarea rows="5" cols="5" name="memo"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">附件<br/>(单个文件上传最大为10Mb)</td>
                    <td colspan="3">
                        <%@include file="../inc/attach.jsp" %>
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r">
                        <s:if test="%{#parameters.mode[0]=='view'}">
                        </s:if><s:else>
                        <input type="submit" value="确 认"/>&nbsp;
                    </s:else>
                        <input type="button" value="关 闭" onclick="window.close();"/> &nbsp;
                        <input type="reset" value="重 置" onclick="clearInput()"/>&nbsp;
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
</div>

<div id="changeItemList" style="display: none;">
    <table width="100%" class="table_1">
        <thead>
        <tr>
            <th width="5%"  style="white-space:nowrap;display: none;"></th>
            <th width="15%" style="white-space:nowrap">变更流水号</th>
            <th width="25%"  style="white-space:nowrap">变更的原合同名称</th>
            <th width="25%"  style="white-space:nowrap">变更的原合同编号</th>
            <th width="10%"  style="white-space:nowrap">变更提出方</th>
            <th width="10%" class="t_r" style="white-space:nowrap">拟变金额</th>
            <th width="10%" class="t_c" colspan="3" style="white-space:nowrap">操作</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

</body>
</html>




