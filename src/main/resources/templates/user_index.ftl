<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
<head>
    <#include "common/meta.ftl">
    <title>专家UC</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="grzx_wdrw_bbrw_t grzx_index_top_bgcolor">
    <ul>
        <li style="background: url('${ctx}/images/ss1.png') right bottom no-repeat #00bcd4">
            <h3>${(taskBo.earnedExp)!"0"}</h3>
            <p>本月获取经验值</p>
        </li >
        <li style="background: url('${ctx}/images/ss2.png') right bottom no-repeat #ffc107">
            <h3>${(taskBo.earnedPoint)!"0"}</h3>
            <p>本月获取积分</p>
        </li >
        <li style="background: url('${ctx}/images/ss3.png') right bottom no-repeat #ff9800">
            <h3>${(taskBo.taskRange)!"0"}</h3>
            <p>本月任务排名</p>
        </li>
        <li style="background: url('${ctx}/images/ss4.png') right bottom no-repeat #66bb6a; margin-right: 0">
            <h3>${(taskBo.completedTaskCount)!"0"}</h3>
            <p>本月完成任务</p>
        </li>
        <div class="clear"></div>
    </ul>
    <div class="clear"></div>
</div>
<#if event??>
<div class="advertising">
    <img src="${picurl}${event.picture!""}" onerror="this.src='${ctx}/images/xitongrenwu.jpg'">
    <div class="huodongneirong">
        <h3>${event.title!""}</h3>
        <#if event.description?length gt 160>
        ${event.description?substring(0,160)}...
        <#else>
        ${event.description!}
        </#if>
        <p>活动时间：${event.begintime?string("yyyy-MM-dd HH:mm")}-${event.endtime?string("yyyy-MM-dd HH:mm")}</p>
        <a href="${snsurl!}/pub/event/detail/${event.eventId}" target="_blank">活动报名</a>
    </div>
    <div class="clear"></div>
    <div class="shutd">
        <a href="javascript:void (0)" id="hide">
            <i class="iconfont"> &#xe687;</i>
        </a>
    </div>
</div>
</#if>
<div class="rela">
    <div class="xitongxx">
        <div style="margin-bottom: 10px" class="mktitle">未读消息</div>
        <div class="grzx_wdxx_bbxx_rr">
            <!-- 3种状态对应的四种图标及颜色 class -->
            <ul id="message_xt_ajax">

            </ul>
        </div>
    </div>
</div>

<div class="shouyetable">
    <div class="mktitle">我的订单 <a href="${ctx}/userinfo/order.php" style="float: right; font-size: 12px; padding-right: 20px;">查看更多订单... </a></div>
    <div class="grzx_wdjf_dhjl" id="order_index">

    </div>
</div>
<div class="shouyetable">
    <div class="mktitle">我的发票</div>
    <div class="grzx_wdjf_dhjl">
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th width="15%">发票金额 </th>
                <th width="20%">发票抬头</th>
                <th width="15%">性质</th>
                <th width="15%">状态</th>
                <th width="20%">时间</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody id="invoice_index">

            </tbody>
        </table>
    </div>
</div>
<script data-main="${ctx}/js/abc/user_index" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>