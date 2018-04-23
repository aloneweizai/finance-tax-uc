<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body>
<div class="huiyuan_title">积分支付
    <a href="${ctx}/userinfo/order.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的订单</a>
</div>
<div class="clear"></div>
<div class="content_box">
    <div class="content clearfix ">

    <div style="font-size: 16px;  ">商品图片:
        <#if order?? && order.orderProductBOList??&&(order.orderProductBOList?size>0)>
            <#list order.orderProductBOList as da>
                <img style="width: 200px;height: 100px" src="${picurl!}${(da.imageUrl)!}">
                <#break>
            </#list>
        </#if>
    </div>
    <div style="font-size: 16px;   ">订单编号:<span style="padding-left: 20px;">${(order.orderNo)!""}</span></div>
    <div style="font-size: 16px;  ">商品名称:
        <span style="padding-left: 16px;">
        <#if order?? && order.orderProductBOList??&&(order.orderProductBOList?size>0)>
            <#list order.orderProductBOList as da>
                  ${(da.name)!""}
                <#break>
            </#list>
        </#if>
        </span>
    </div>
    <div style="font-size: 16px; ">我的积分:<span style="color: #F34948;padding-left: 20px;"><b>${(user.points)!0}分</b></span></div>
    <div style="font-size: 16px; ">订单金额:<span style="color: #F34948;padding-left: 20px;"><b>${(order.totalPrice)!0}(积分)</b></span></div>
    <#--<div style="font-size: 16px; ">赠送积分：<span style="color: #F34948;padding-left: 10px;"><b>${(order.giftPoints)!0}</b></span></div>-->

    <div class="jifen_duihuan">
        <a href="javascript:;"  id="pointPay" tradeNo="${tradeNo!""}" member-jifen="${(user.points)?c!0}" goods-jiage="${(order.totalPrice)!0}">立即支付</a>
    </div>
</div>
    <script data-main="${ctx}/js/abc/member/point_pay" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>