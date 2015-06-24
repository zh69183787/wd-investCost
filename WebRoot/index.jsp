<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>欢迎</title>
    <%
        String basePath = request.getContextPath();
    %>
</head>
<body>
<div>
    <ul>
        <li>办公会报表</li>
        <li>开发人：周顺</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>已签订合同情况统计：<a href="<%=basePath %>/jsp/meetEvent/signedContractInfo.jsp?Fdsf" target="_blank">[已签订合同情况统计]</a>
        </li>
        <li>维修合同情况统计：<a href="<%=basePath %>/meetEvent/getFixContractInfo.action" target="_blank">[维修合同情况统计]</a>
        </li>
        <li>评标室情况统计：<a href="<%=basePath %>/jsp/meetEvent/meetEventInfo.jsp?Fdsf" target="_blank">[评标室情况统计]</a>
        </li>
    </ul>
    <ul>
        <li>合同管理</li>
        <li>开发人：姚成龙</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>合同封面：<a href="<%=basePath %>/contract/showContractCover.action" target="_blank">[合同封面]</a><%=basePath %>
            /contract/showContractCover.action
        </li>
        <li>列表页面：<a href="<%=basePath %>/contract/findContractByPage.action" target="_blank">[合同管理]</a><%=basePath %>
            /contract/findContractByPage.action
        </li>
        <li>合同导入-客户定制版，合同编号相同不更新数据：<a href="<%=basePath %>/contract/showBatchUploadCustomized.action" target="_blank">[批量上传合同]</a><%=basePath %>
            /contract/findContractByPage.action
        </li>
        <li>合同实际支付情况批量导入：<a href="<%=basePath %>/contractStatus/showBatchUpload.action"
                            target="_blank">[批量上传实际支付情况]</a><%=basePath %>/contract/findContractByPage.action
        </li>
    </ul>
    <ul>
        <li>工程量条目</li>
        <li>开发人：姚成龙</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>列表页面：<a href="<%=basePath %>/quantitiesSubject/findQuantitiesSubjectByPage.action"
                    target="_blank">[工程量条目]</a><%=basePath %>/quantitiesSubject/findQuantitiesSubjectByPage.action
        </li>
    </ul>

    <ul>
        <li>项目管理</li>
        <li>开发人：姚成龙</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>列表页面：<a href="<%=basePath %>/project/showProjectCover.action" target="_blank">[项目封面]</a><%=basePath %>
            /project/showProjectCover.action
        </li>
        <li>列表页面：<a href="<%=basePath %>/project/findProjectByPage.action" target="_blank">[项目管理]</a><%=basePath %>
            /project/findProjectByPage.action
        </li>
    </ul>
    <ul>
        <li>投资概算科目</li>
        <li>开发人：姚成龙</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>列表页面：<a href="<%=basePath %>/estimateSubject/findInvestEstimateSubjectByPage.action" target="_blank">[投资概算科目]</a><%=basePath %>
            /estimateSubject/findInvestEstimateSubjectByPage.action
        </li>
    </ul>
    <ul>
        <li>投资概算目标控制</li>
        <li>开发人：姚成龙</li>
        <li>版本号：</li>
        <li>发布时间：</li>
        <li>列表页面：<a href="<%=basePath %>/estimateSubject/findInvestEstimateSubjectByPage.action?type=view"
                    target="_blank">[投资概算目标控制]</a><%=basePath %>
            /estimateSubject/findInvestEstimateSubjectByPage.action?type=view
        </li>
    </ul>

    <ul>
        <li>报表</li>
        <li>报表：<a href="<%=basePath %>/dwProject/showReport.action" target="_blank">[报表]</a><%=basePath %>
            /dwProject/showReport.action
        </li>
    </ul>


    <ul>
        <li>合同封面数据录入</li>
        <li>合同封面数据：<a href="<%=basePath %>/dwContract/calculateDwContractCover.action"
                      target="_blank">[合同封面数据录入]</a><%=basePath %>/dwContract/calculateDwContractCover.action
        </li>
    </ul>
    <ul>
        <li>项目封面数据录入</li>
        <li>项目封面数据：<a href="<%=basePath %>/dwProject/calculateDwProjectCover.action"
                      target="_blank">[项目封面数据录入]</a><%=basePath %>/dwProject/calculateDwProjectCover.action
        </li>
    </ul>

    <ul>
        <li>资金计划</li>
        <li>资金计划数据：<a href="<%=basePath %>/fundPlan/findFundPlanByPage.action"
                      target="_blank">[资金计划管理]</a><%=basePath %>/fundPlan/findFundPlanByPage.action
        </li>
    </ul>
    <ul>
        <li>交通委补贴</li>
        <li>交通委补贴数据：<a href="<%=basePath %>/fundAllowance/findFundAllowanceByPage.action"
                       target="_blank">[交通委补贴管理]</a><%=basePath %>/fundAllowance/findFundAllowanceByPage.action
        </li>
    </ul>
    <ul>
        <li>可销项项目查询</li>
        <li>可销项项目查询数据：<a href="<%=basePath %>/project/findProjectByPageToBeCancelled.action"
                         target="_blank">[可销项项目查询]</a><%=basePath %>/project/findProjectByPageToBeCancelled.action
        </li>
    </ul>
</div>
</body>
</html>