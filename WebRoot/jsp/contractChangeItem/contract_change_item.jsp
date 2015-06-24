<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="IE=8">
    <title>变更事项申请单</title>
    <link rel="stylesheet" href="../css/formalize.css"/>
    <link rel="stylesheet" href="../css/page.css"/>
    <link rel="stylesheet" href="../css/imgs.css"/>
    <link rel="stylesheet" href="../css/reset.css"/>
    <link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <style>
        ul span {
            display: inline;
        }

        ul input {
            margin: 5px;
        }

        .content li {
            line-height: 30px;
            float: left;
            margin-right: 20px;
            width: 120px;
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
    <script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript">
        var changeItemTypeData = [

            {label: "技术变更", id: "1", items: [{label: "一类", id: "1"}, {label: "二类", id: "2"}]},
            {label: "设计变更", id: "2", items: [{label: "一般", id: "1"}, {label: "重大", id: "2"}]},
            {label: "转报变更", id: "3", items: [{label: "一般", id: "1"}, {label: "重大", id: "2"}]}

        ]

        $(function () {


            initCheckboxGroup($(":checkbox[name=changeContentType]"), "<s:property value="changeContentType"/>");
            initCheckboxGroup($(":checkbox[name=changePriceType]"), "<s:property value="changePriceType"/>");

            var changeItemType = "<s:property value="changeItemType"/>".split(",");
            var renderChangeItemType = function (data, $object, val) {
                $object.find("option:gt(0)").remove();
                val = val || "";

                var options = "";
                $.each(data, function (i, o) {
                    options += "<option value='" + o.id + "'>" + o.label + "</option>";
                });
                $(options).insertAfter($object.find("option:eq(0)"));
                $object.find("option[value=" + val + "]").prop("selected", true);
            }

            $($("select[name=changeItemType]")[0]).change(function () {
                var data = [];
                var $this = $(this);
                $.each(changeItemTypeData, function (i, o) {
                    if (o.id == $this.val())
                        data = o.items;
                });
                renderChangeItemType(data, $($("select[name=changeItemType]")[1]), changeItemType[1]);
            });


            renderChangeItemType(changeItemTypeData, $($("select[name=changeItemType]")[0]), changeItemType[0]);
            $($("select[name=changeItemType]")[0]).trigger("change");

            getAttachList("<s:property value="attach"/>", <s:if test="%{#parameters.mode[0]=='view'}">false</s:if><s:else>true</s:else>);


            $("form").submit(function () {
                var attachIds = new Array();
                $("#fileBody").find("a[name='attach_id']").each(function () {
                    attachIds.push($(this).attr("id"));
                });
                $(":hidden[name=attach]").val(attachIds.join(","));

            });

            function initCheckboxGroup($object, val) {
                var array = val.split(",");

                <s:if test="%{#parameters.mode[0]=='view'}">
                $object.css("display", "none");
                $object.next().css("display", "none");
                </s:if>
                $object.each(function (i, o) {
                    $.each(array,function(ai,ao){

                        if (ao==$(o).val()) {
                            $(o).prop("checked", true);
                            <s:if test="%{#parameters.mode[0]=='view'}">
                            $(o).next().css("display", "");
                            </s:if>
                        }
                    })
                })
            }



            <s:if test="%{#parameters.mode[0]=='view'}">
            $(".must").css("display", "none");
            $(".input_xxlarge,textarea,").css("border-color", "white");
            $(":text,textarea").prop("readonly", true);
            $("select").each(function (i, o) {
                $(o).parent().html($(o).find("option:selected").text());
            });
            $(":text[name=changeSerialNo]").blur(function () {
                var $this = $(this);
                $.ajax({
                    url: "<s:url action="uniqueNo" namespace="/contractChangeItem"/>",
                    dataType: "json",
                    data: {
                        "changeSerialNo": $(this).val()
                    },
                    success: function (data) {

                        if (data == "1")
                            $this.parent().find(".error_label").remove();
                        if (data == "0")
                            $("<span class='error_label'>流水号重复</span>").insertAfter($this.next());

                    }
                });
            });
            </s:if>
            <s:else>

            $("input[name=regTime],input[name=passTime]").datepicker({
                inline: true,
                changeYear:true,
                changeMonth:true,
                showOtherMonths: true,
                selectOtherMonths: true
            });
            </s:else>

        });

        function clearInput() {
            $("form")[0].reset();
            resetAllFile();
        }


    </script>

    <style type="text/css">
        .must {
            color: red;
            display: inline;
        }
        .error_label{
            color: red;
        }
    </style>


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
                <li class="fin">合同事项申请</li>
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
        <s:form action="save" name="contractChangeItem" namespace="/contractChangeItem" method="post">
            <s:hidden name="id"/>
            <s:hidden name="contract.id"/>
            <s:hidden name="attach"/>

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
                    <td class="t_r lableTd" width="19%;">变更的原合同名称</td>
                    <td colspan="3" width="100%;">
                        <s:property value="contract.contractName"/>
                        <%--<s:textfield name="contract.contractName" cssClass="input_xxlarge"--%>
                                     <%--cssStyle="width:985px;"/><span--%>
                            <%--class="must">&nbsp;&nbsp;*</span>--%>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd" width="19%;">变更的原合同编号</td>
                    <td width="31%;">
                        <s:property value="contract.contractNo"/>
                        <%--<s:textfield name="contract.contractNo" cssClass="input_xxlarge"/><span--%>
                            <%--class="must">&nbsp;&nbsp;*</span>--%>
                    </td>
                    <td class="t_r lableTd" width="20%;">变更事项流水号</td>
                    <td width="30%;">
                        <s:textfield name="changeSerialNo" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd" width="19%;">变更提出方</td>
                    <td width="31%;">
                        <s:select list="#{'1':'合同甲方单位','2':'合同乙方单位'}" headerValue="请选择" headerKey=""
                                  name="changePutForword"/>
                        <span class="must">&nbsp;&nbsp;*</span>
                    </td>

                    <td class="t_r lableTd" width="20%;">拟变金额</td>
                    <td width="30%;">
                        <s:textfield name="changePrice" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd" width="19%;">主体内容变更情形</td>
                    <td width="100%;" colspan="3">
                        <ul class="content">
                            <li>
                                <input name="changeContentType" value="1" type="checkbox"/><span>合同当事人</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="2" type="checkbox"/><span>标段</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="3" type="checkbox"/><span>数量</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="4" type="checkbox"/><span>质量</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="5" type="checkbox"/><span>价款</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="6" type="checkbox"/><span>履行期限</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="7" type="checkbox"/><span>地点和方式</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="8" type="checkbox"/><span>违约责任</span>
                            </li>
                            <li>
                                <input name="changeContentType" value="9" type="checkbox"/><span>解决争议方法</span>
                            </li>
                        </ul>

                        <!-- <span class="must">&nbsp;&nbsp;*</span> -->
                    </td>

                <tr>
                    <td class="t_r lableTd" width="19%;">合同价款变更情形</td>
                    <td colspan="3" width="31%;">

                        <ul class="content">
                            <li>
                                <input name="changePriceType" value="1" type="checkbox"/><span>法律法规变化</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="2" type="checkbox"/><span>工程变更</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="3" type="checkbox"/><span>项目特征不符</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="4" type="checkbox"/><span>清单缺项</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="5" type="checkbox"/><span>清单偏差</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="6" type="checkbox"/><span>记日工</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="7" type="checkbox"/><span>物价变化</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="8" type="checkbox"/><span>暂估价</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="9" type="checkbox"/><span>不可抗力</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="10" type="checkbox"/><span>赴工补偿</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="11" type="checkbox"/><span>误期赔偿</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="12" type="checkbox"/><span>索赔</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="13" type="checkbox"/><span>现场签证</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="14" type="checkbox"/><span>暂列金额</span>
                            </li>
                            <li>
                                <input name="changePriceType" value="15" type="checkbox"/><span>约定的其它事项</span>
                            </li>
                        </ul>

                    </td>
                </tr>

                <td class="t_r lableTd" width="20%;">变更事项情形</td>
                <td width="30%;" colspan="3">
                    <span style="display:inline;"><select name="changeItemType">
                        <option value="">请选择一级分类</option>
                    </select></span>
                    <span style="display:inline;"><select name="changeItemType">
                        <option value="">请选择二级分类</option>
                    </select></span>

                    <span class="must">&nbsp;&nbsp;*</span>
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
                    <td colspan="3">
                        <s:date name="passTime" format="yyyy-MM-dd" id="passTime"/>
                        <s:textfield name="passTime" cssClass="input_xxlarge"/><span
                            class="must">&nbsp;&nbsp;*</span>
                    </td>
                    <%--<td class="t_r lableTd">合同签约时间</td>--%>
                    <%--<td >--%>
                        <%--<s:date name="regTime" format="yyyy-MM-dd" id="regTime"/>--%>
                        <%--<s:textfield name="regTime" cssClass="input_xxlarge"/><span--%>
                            <%--class="must">&nbsp;&nbsp;*</span>--%>
                    <%--</td>--%>
                </tr>

                <tr>
                    <td class="t_r lableTd" width="20%;">主体内容变更情形描述</td>
                    <td colspan="3">
                        <s:textarea rows="5" cols="5" name="changeContentDescription"/>
                    </td>
                </tr>


                <tr>
                    <td class="t_r lableTd" width="20%;">合同价款变更情形描述</td>
                    <td colspan="3">
                        <s:textarea rows="5" cols="5" name="changePriceDescription"/>
                    </td>
                </tr>


                <tr>
                    <td class="t_r lableTd">投资监理审核意见</td>
                    <td colspan="3">
                        <s:textarea rows="5" cols="5" name="supervisionSuggest"/>
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
                        <s:textarea rows="5" cols="5" name="changeMemo"/>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">附件<br/>(单个文件上传最大为10Mb)</td>
                    <td colspan="3">
                        <%@include file="../inc/attach.jsp" %>
                    </td>
                </tr>

                <!-- 						 发起部门ID 合同ID 		流程类型																																			 -->
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
</body>
</html>




