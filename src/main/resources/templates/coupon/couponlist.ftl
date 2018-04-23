<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta content="text/html; charset=utf-8" />
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>优惠券列表-财税专家</title>
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <script>
        var ctx='${ctx}';
    </script>
</head>

<body style="background: #ececec;">
<!-- head -->
<#include "../common/top.ftl">
<!-- head end -->
<!-- main -->

<div class="w_1200" style="margin-top:15px">
    <#if data.dataList??&&(data.dataList?size>0)>
        <#list data.dataList as list>
            <div class="ghd clearfix" style="background:#fff;">
                <div class="ghd-left fl">
                    <img src="${picurl}${list.imageUrl!""}" alt="">
                </div>
                <div class="ghd-right fl">
                    <p>${list.activityName!""}</p>
                    <p><i class="iconfont">&#xe735;</i>${list.activityStartTime?string('yyyy-MM-dd')}~${list.activityEndTime?string('yyyy-MM-dd')}</p>
                    <p><i class="iconfont">&#xe697;</i>数量:${list.couponNum!0}张</p>
                </div>
                <div class="ghd-logo"></div>
                <#if list.activityEndTime?datetime gte time?datetime>
                    <#if list.activityStartTime?datetime gt .now?datetime>
                        <a href="javascript:;" target="_blank">活动未开始</a>
                    <#else>
                        <a href="${ctx}/coupon/details/${list.couponId!""}/${list.id!""}" target="_blank">马上领取</a>
                    </#if>
                <#else>
                    <a href="javascript:;" target="_blank">活动已过期</a>
                </#if>
            </div>
        </#list>
    <#else>
        <div style=" margin-top: 150px; margin-bottom: 150px; text-align: center;">
           <img src="${ctx}/images/zwyhj.png" alt=""/>
        </div>
    </#if>
</div>
<!-- main end -->
<!-- footer  -->
<#include "../common/bottom.ftl">
<!-- footer end -->
<#include "../common/layer_login.ftl" />

<script data-main="${ctx}/js/abc/coupon/couponlist" src="${ctx}/js/require.js"></script>
</body>

</html>
