<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta content="text/html; charset=utf-8" />
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>优惠券-财税专家</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <!--[if IE 8]>
    <link type="text/css" rel="stylesheet"  href="${ctx}/css/webpage_main-1024.css"/>
    <![endif]-->
    <script>
        var ctx='${ctx}';
    </script>
</head>

<body style="background: #FF473D;">
<!-- head -->
<#include "../common/top.ftl">
<!-- head end -->
<!-- main -->
<div class="yhj">
    <div class="yhj-content clearfix">
        <div class="yhj-content-left fl">
            <div class="yhj-left-img "><img src="${picurl}${image!""}" alt=""></div>
        </div>
        <div class="yhj-content-right fl">
            <div class="yhj-right-title danhang" title="${data.couponName!""}">${data.couponName!""}</div>
            <#if data??&&data.couponType=='MANJIAN'>
                <#if data.couponMode=='FIXED'>
                    <div class="yhj-right-price">满&nbsp;&yen;<span>${data.param1!0}</span>减&nbsp;&yen;<span>${data.param2!0}</span></div>
                <#elseif data.couponMode=='FLOAT'>
                    <div class="yhj-right-price">满&nbsp;&yen;<span>${data.param1!0}</span>最高可减&nbsp;&yen;<span>${data.param3!0}</span></div>
                </#if>
            <#elseif data??&&data.couponType=='LIJIAN'>
                <#if data.couponMode=='FIXED'>
                    <div class="yhj-right-price">&yen;<span>${data.param2!0}</span></div>
                <#elseif data.couponMode=='FLOAT'>
                    <div class="yhj-right-price">随机金额,最高可领 &yen;<span>${data.param3!0}</span></div>
                </#if>
            <#elseif data??&&data.couponType=='ZHEKOU'>
                <#if data.param1??&&data.param1 gt 0>
                    <div class="yhj-right-price">满&yen;<span>${data.param1!0}</span>可享<span>${data.param2*10!0}</span>折</div>
                <#else>
                    <div class="yhj-right-price">任意金额,可享<span>${data.param2*10!0}</span>折</div>
                </#if>

            </#if>
            <div class="yhj-right-sy">限品类：<span>${lx!""}</span></div>
            <div class="yhj-right-js">领取截止时间：<span>${endtime?string('yyyy-MM-dd')}</span></div>
            <#if time==-1>
                <a class="yhj-right-lq" href="javascript:;">已结束</a>
            <#else>
                <#if sysl==0>
                    <a class="yhj-right-lq" href="javascript:;">已领完</a>
                <#else>
                    <button class="yhj-right-lq" id="yhjlq" couponid="${hdid!""}" style="border: 0px;" >立即领取</button>
                </#if>
            </#if>

        </div>
    </div>
    <div class="yhj-hdjs">
        <h2>优惠券介绍</h2>
        <div style="margin-top: 5px;">&nbsp;</div>
        <div>${data.description!""}</div>
    </div>
</div>
<!-- main end -->
<!-- footer  -->
<#include "../common/bottom.ftl">

<#include "../common/layer_login.ftl" />
<!-- footer end -->
<script data-main="${ctx}/js/abc/coupon/coupon" src="${ctx}/js/require.js"></script>
</body>

</html>