<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>支付页-财税专家</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link type="text/css" rel="stylesheet" media="screen and (min-width:800px)" href="${ctx}/css/ddzf-zfym.css"/>
    <link type="text/css" rel="stylesheet" media="screen and (max-width:799px)" href="${ctx}/css/ddzf-zfym-min.css"/>
    <!--[if IE 8]>
    <link type="text/css" rel="stylesheet"  href="${ctx}/css/ddzf-zfym.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <script>
        var ctx='${ctx}';
    </script>
</head>

<body style="background: #fff;">
<!-- head -->
<div class="xxhd-head">
    <div class=" clearfix pay_width" >
        <div class="xxhd-logo">
            <a href="${cswurl}/index.html" title="网站首页" target="_blank">
                <div class="boximg"><span><img src="${ctx}/images/logo_shouying.png"></span></div>
            </a>
        </div>
        <div class="xxhd-gnny fr">
            <div>
                <a target="_blank" href="${cswurl}/index.html"><i class="iconfont" style="padding-right:5px;">&#xe836;</i>返回财税网首页</a>
            </div>
            <div class="gnny-sg">|</div>
            <div class="xxhd-user-name">
                <a href="${ucurl}/index.html" target="_blank">${currentUser.nickname!"匿名"}</a>
            </div>
            <div class="gnny-sg">|</div>
            <div>
                <a href="${ucurl}/index.html?url=${url}" target="_blank">我的订单</a>
            </div>
            <!-- <div>|</div> -->
            <!-- <div>
                <a>支付帮助</a>
            </div> -->
        </div>
    </div>
</div>
<!-- head end -->
<!-- main -->
<div class="ddzf-zfym-main pay_width" >
    <div class="zfym-main-top">
        <div class="ddzf-zfym-ddcg clearfix">
            订单提交成功，请尽快付款！订单号：<span>${data.orderNo!""}</span>
            <div class="zfym-ddcg-yfje fr">
                <#if data.tradeMethod=='RMB'>
                        应付金额<span>${data.totalPrice?string("0.00")!"0.00"}</span>
                    <#else>
                        应付积分<span>${data.totalPrice?string("0.00")!"0.00"}</span>
                </#if>
            </div>
        </div>
        <#if data.tradeMethod=='RMB'>
            <div class="ddzf-zfym-tj">
                推荐使用
            <#--<i class="iconfont zfym-tj-wx ">&#xe751;</i>-->
                <i class="iconfont zfym-tj-zfb ">&#xe750;</i>
                扫码支付&nbsp;|&nbsp;请您在<span>2小时</span>内完成支付，否则订单会被自动取消
            </div>
        </#if>
    </div>
    <div class="zfym-main-middle">
        <#if data.isShipping==1>
            <div class="ddzf-zfym-shdz">
                收货地址：<span>${data.shippingAddress!""}</span>收货人：<span>${data.consignee!""}</span><span>${data.contactNumber!""}</span>
            </div>
        </#if>
        <div class="ddzf-zfym-spmc">
            商品名称：<span>${name!""}</span>
        </div>
    </div>
    <#if data.tradeMethod=='RMB'>
        <div class="zfym-main-bottom">
            <div class="main-bottom-title">支付方式</div>
            <div class="main-bottom-content">

                <div class="bottom-content-zfb active clearfix" active="true" type-pay="zfb">
                    <div class="fl content-gx">
                        <i class="iconfont">&#xe740;</i>
                    </div>
                    <div class="fl content-zfb-zfb">
                        <img src="${ctx}/images/zfbzf.png" alt="">
                    </div>
                </div>
                <div class="bottom-content-wx  clearfix"  type-pay="wx">
                    <div class="fl content-gx">
                        <i class="iconfont">&#xe740;</i>
                    </div>
                    <div class="fl content-wx-wx">
                        <img src="${ctx}/images/wxzf.png" alt="">
                    </div>
                </div>
            </div>
            <div class="main-bottom-btn">
                <a href="javascript:;" id="lj-pay"
                    <#if data.tradeBOList?? && (data.tradeBOList?size>0)>
                        <#list data.tradeBOList as list>
                            <#if list_index==0>
                   order-id="${list.tradeNo!""}"
                            </#if>
                        </#list>
                    </#if>
                   order_lsh="${lsh!""}"

                >立即支付</a>
            </div>
        </div>
    <#else>
        <div class="main-bottom-btn">
            <a href="javascript:;" id="lj-pay-jf"
                <#if data.tradeBOList?? && (data.tradeBOList?size>0)>
                    <#list data.tradeBOList as list>
                        <#if list_index==0>
               order-id="${list.tradeNo!""}"
                        </#if>
                    </#list>
                </#if>
               order_lsh="${lsh!""}" jifen="${data.totalPrice}" spname="${name!""}"

            >立即支付</a>
        </div>
    </#if>

</div>
<!-- main end -->
<!-- footer  -->
<div class="xxhd-footer">

    <div class="xxhd-foot w_1200 clearfix">
        <div class="">Copyright © 2005-2015 ABC12366 All Rights Reserved 粤ICP备08111004号-3</div>
    </div>
</div>
<!-- footer end -->
<script data-main="${ctx}/js/abc/pay/order_pay" src="${ctx}/js/require.js"></script>
</body>

</html>