<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<link href="${ctx}/js/lib/layui/css/layui.css" rel="stylesheet" type="text/css">

<html>
<#include "../common/meta.ftl">
<body>
<div id="layuitable" style="padding:10px;">
<table class="layui-table">
    <tr>
        <th>活动名称：</th>
        <td>${(lotteryLogBo.activityName)!}</td>
    </tr>
    <tr>
        <th>活动开始时间：</th>
        <td>${(lotteryLogBo.activityStartTime?string("yyyy-MM-dd"))!}</td>
    </tr>
    <tr>
        <th>活动结束时间：</th>
        <td>${(lotteryLogBo.activityEndTime?string("yyyy-MM-dd"))!}</td>
    </tr>
    <tr>
        <th>奖品名称：</th>
        <td>${(lotteryLogBo.lotteryName)!}</td>
    </tr>
<#if lotteryLogBo.lotterySend?? && lotteryLogBo.lotterySend>
    <tr>
        <th>快递公司：</th>
        <td>${(lotteryLogBo.kuaidiGS)!}</td>
    </tr>
    <tr>
        <th>快递单号：</th>
        <td>${(lotteryLogBo.kuaididanhao)!}</td>
    </tr>
    <tr>
        <th>收货人：</th>
        <td>${(lotteryLogBo.sendName)!}</td>
    </tr>
    <tr>
        <th>收货人号码：</th>
        <td>${(lotteryLogBo.phone)!}</td>
    </tr>
    <tr>
        <th>收件地址：</th>
        <td>${(lotteryLogBo.address)!} </td>
    </tr>
<#else>
    <tr>
        <th>奖品信息：</th>
        <td>${(lotteryLogBo.sendRemake)!} </td>
    </tr>
</#if>
</table>
</div>
</body>
</html>
</@compress>