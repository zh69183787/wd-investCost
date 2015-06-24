<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015-03-09
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="../js/jquery.uploadify-3.1.js"></script>
<link rel="stylesheet" href="../css/uploadify.css"/>

<%String basePath = request.getContextPath(); %>
<script>
    //文件上传
    var getAttachList = function(attachIdList,isEdited){
        if(isEdited)
        $("#uploadify").uploadify({
            'auto': false,
            'method': "post",
            'height': 20,
            'width': 100,
            'swf': '../js/uploadify.swf',
            'uploader': '<%=basePath%>/contract/fileUpload.action',
            'fileTypeDesc': '格式:txt,xls，xlsx,doc,docx,rar,zip,jpg,png,pdf',		//描述
            'fileTypeExts': '*.txt;*.xls;*.xlsx;*.doc;*.docx;*.rar;*.zip;*.jpg;*.png;*.pdf',			//文件类型
            'fileSizeLimit': '30000KB',			//文件大小
            'buttonText': '选择文件',			//按钮名称
            'fileObjName': 'uploadify',
            'successTimeout': 5,
            'requeueErrors': false,
            'removeTimeout': 1,
            'removeCompleted': true,
            'onSelectError': function (file, errorCode, errorMsg) {
                if (errorCode == -110) {
                    this.queueData.errorMsg = "文件太大，无法上传！";
                }
            },
            'onUploadSuccess': function (file, data, response) {
                var attach = eval('(' + data + ')');
                renderFile(attach);
            }
        });
        $.ajax({
            url: "<s:url action="getAttachList" namespace="/attach"/>?random="+Math.random(),
            dataType: "json",
            data: {
                "type" : 'post',
                "attachIdList" :attachIdList
            },
            success: function( data ) {
                if(data){
                    $.each(data,function(i,o){
                        renderFile(o);
                    })
                }


                $(".op").css("display",isEdited ? "" : "none");
            }
        });
    };

    function renderFile(attach){

        $("#fileTable").show();
        var addHtml = "<tr style='word-break:break-all; word-wrap:break-word;'>" +
                "<td class='t_l' style='width:200px;'>" +
                "<a href='<%=basePath%>/attach/downloadAttach.action?attachId=" + attach.id + "'>" + attach.filename + "." + attach.fileextname + "</a>" +
                "</td>" +
                "<td class='t_r'>" + (parseFloat(attach.filesize) / 1024 + '').substring(0, (parseFloat(attach.filesize) / 1024 + '').indexOf('.') + 3) + "</td>" +
                "<td class='t_c'>" + attach.uploaddate + "</td>" +
                "<td class='t_c op'><a href='<%=basePath%>/attach/downloadAttach.action?attachId=" + attach.id + "' id='" + attach.id + "'>下载</a></td>" +
                "<td class='t_c op'><a href='#' id='" + attach.id + "' name='attach_id'>取消</a></td>" +
                "</tr>";
        $("#fileBody").find("tr").each(function () {
            if ($.trim($(this).find("td:first").text()) == (attach.filename + "." + attach.fileextname)) {
                var $target = $(this).find("td:last a");
                $.ajax({
                    url: "<%=basePath%>/attach/deleteAttach.action?random=" + Math.random(),
                    dataType: "json",
                    type: 'post',
                    data: {
                        id: $target.attr("id")
                    },
                    error: function () {
                        alert("系统连接失败请稍后再试！");
                    },
                    success: function (data) {
                        $target.parent().parent().remove();
                        var trLength = $("#fileTable").find("tr").length;
                        if (trLength == 1) {
                            $("#fileTable").hide();
                        }
                    }
                });
            }
        });
        $("#fileBody").append(addHtml);
        $("#fileBody").find("a[name=attach_id]").click(removeFile)
    }



    //删除上传的附件
    function removeFile() {
        if (confirm("是否删除?")) {
            var $target = $(this);
            $.ajax({
                url: "<%=basePath%>/attach/deleteAttach.action?random=" + Math.random(),
                dataType: "json",
                type: 'post',
                data: {
                    id: $target.attr("id")
                },
                error: function () {
                    alert("系统连接失败请稍后再试！");
                },
                success: function (data) {
                    $target.parent().parent().remove();
                    var trLength = $("#fileTable").find("tr").length;
                    if (trLength == 1) {
                        $("#fileTable").hide();
                    }
                }
            });
        }
    }

    //点击重置后清空附件，提示，是否删除删除所有附件
    function resetAllFile() {
        if ($("#fileBody").find("a[name='attach_id']").length > 0) {
            if (confirm("是否删除所有附件？")) {
                $("#fileBody").find("a[name='attach_id']").each(function () {
                    var $target = $(this);
                    $.ajax({
                        url: "<%=basePath%>/attach/deleteAttach.action?random=" + Math.random(),
                        dataType: "json",
                        type: 'post',
                        data: {
                            id: $target.attr("id")
                        },
                        error: function () {
                            alert("系统连接失败请稍后再试！");
                        },
                        success: function (data) {
                            $target.parent().parent().remove();
                            var trLength = $("#fileTable").find("tr").length;
                            if (trLength == 1) {
                                $("#fileTable").hide();
                            }
                        }
                    });
                });
            }
        }
        if ($("div[class='cancel']").length > 0) {
            $("div[class='cancel']").each(function () {
                window.location.href = $(this).find("a").attr("href");
            });
        }
    }
</script>
<input class="op" type="file" name="uploadify" id="uploadify" />
<input class="op" type="button" value="上传" onclick="$('#uploadify').uploadify('upload','*');">
<input class="op" type="button" value="取消" onclick="$('#uploadify').uploadify('stop');">

    <table id="fileTable" style="display: none;">
        <tbody style="width: 550px;border: solid;border-color: #D0D0D3;"   id="fileBody">
        <tr style="border: solid;border: #D0D0D3;">
            <td width="200px;" class="t_c">文件名</td>
            <td width="100px;" class="t_c">大小(KB)</td>
            <td width="150px;" class="t_c">上传时间</td>
            <td width="50px;" class="t_c op" colspan="2">操作</td>
        </tr>
        </tbody>
    </table>