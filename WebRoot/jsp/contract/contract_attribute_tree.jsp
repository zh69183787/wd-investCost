<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/2/10
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="../js/tree/jquery.tree.core.min.js"></script>
	<script src="../js/tree/contract_type_tree.js"></script>
	<link rel="stylesheet" href="../css/tree/style.css" type="text/css">
	
    <style>
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }

        .ztree span {
            display: inline;
        }
    </style>
    <script>
        $(function () {
            $.fn.zTree.init($("#contractAttributeTree"), {}, contractAttributeData);
            $("span[name=contractAttributeDescription]").each(function (i, o) {
                var txt = $(o).text().split("-");
                var v = txt[0];
                    $(o).parent().text(getSelectedNodeName(v));
                
            }); 
            $("#contractAttributeBtn").click(function () {
                $("#contractAttributeTreeDiv").dialog({
                    resizable: true,
                    height: 550,
                    width: 400,
                    modal: true,
                    open: function () {
                    },
                    title: '建设类合同分类',
                    buttons: {
                        '确定': function () {
                             var nodes = getSelectedNodes();
                            $.each(nodes, function (i, n) {
                                $(":hidden[name=contractAttributeId]").val(n.id);
                                $(":text[name=contractAttribute]").val(getSelectedNodeName(n.id));
                                $(":text[name=contractAttribute]").prop("readonly",true);
                            });
                            $(this).dialog('close');
                        },
                        '取消': function () {
                            $(this).dialog('close');
                        }
                    }
                });
            });
        });
        function getSelectedNodes() {
            return   $.fn.zTree.getZTreeObj("contractAttributeTree").getSelectedNodes();
        }
        function getSelectedNodeName(nodeId) {
            var names = [];
            for (var i = 0; i <= nodeId.length - 1; i++) {
                var c = nodeId.substring(0, (i + 1));
                getNode(c, contractAttributeData, names);
            }
            return names.join("-");

        }

        function getNode(nodeId, nodeList, names) {
            $.each(nodeList,function(i,node){
                if (node.id == nodeId) {
                    names.push(node.name);
                } else {
                    if(node.children)
                        getNode(nodeId, node.children, names);
                }
            });
        }
  
    </script>
</head>
<body>
<div id="contractAttributeTreeDiv" style="display: none;width:100px;height:100px;">
    <ul id="contractAttributeTree" class="ztree" style="background-color:white;"></ul>
</div>
</body>
</html>
