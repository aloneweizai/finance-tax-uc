<@compress single_line=true>
    <#assign ctx=request.getContextPath()>
<html>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<head>
    <meta content="text/html; charset=utf-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <#include "../common/meta.ftl">
    <title>我的优惠券</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css?time=20170117">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:480px)" href="${ctx}/css/coupon.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/coupon-480.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
        var snsurl="${snsurl}";
    </script>
</head>

<body>
<div class="grzx_main_rt pc_zishiying">我的优惠券
    <a href='${ctx}/coupon/activities/list.html' target="_blank"><b class="coupon_a">领取更多优惠券</b></a>
</div>

<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title clearfix">
        <li <#if status="1">class="layui-this"</#if> status="1">可用优惠劵</li>
        <li <#if status="2">class="layui-this"</#if> status="2">已用优惠劵</li>
        <li <#if status="5">class="layui-this"</#if> status="5">过期优惠劵</li>
        <li <#if status="4">class="layui-this"</#if> status="4">已删除优惠劵</li>
    </ul>
    <!-- 可用优惠劵 -->
    <div class="layui-tab-content clearfix">
        <div id="fanye" class="layui-tab-item layui-show yhj_padding">
            <#if status="1"||status="2"||status="4"||status="5">
                <#if coupons?? && (coupons?size>0)>
                    <#list coupons as coupon>
                    <#if coupon.status==status>
                        <div class="coupon">
                            <div class="coupon_asingle">
                                <div <#if coupon.status=="1">class="coupon_img_ok"<#else>class="coupon_img"</#if>>
                                    <div class="couponimg_top"><sup>￥</sup>
                                        <h3>
                                            <#if coupon.couponType=="ZHEKOU">
                                            ${coupon.param2 * 10}折
                                            <#else>
                                            ${coupon.param2!}
                                            </#if>
                                        </h3>
                                    </div>
                                    <p class="coupon_inthemiddle">
                                        <#if coupon.param1?? && coupon.param1 gt 0>
                                            [满${coupon.param1!}可用]
                                        </#if>
                                    </p>
                                    <p class="coupon_bottom">${coupon.validStartTime?string("yyyy-MM-dd")}-${coupon.validEndTime?string("yyyy-MM-dd")}</p>
                                    <div class="triangle" coupon-id="${coupon.id!}" coupon-status="${coupon.status!}">
                                        <i class="iconfont">&#xe687;</i>
                                    </div>
                                </div>
                                <div class="coupon_content">
                                    <p>限品类 : <small>
                                        <#assign cname = "">
                                    <#if coupon.categoryIds??>
                                        <#if coupon.categoryIds?contains(",")>
                                            <#list coupon.categoryIds?split(",")as cate>
                                                <#if cate=="ALL">
                                                    <#assign cname = cname +"全品类">
                                                    <#break>
                                                <#elseif cate=="HYCZ">
                                                    <#assign cname = cname +"会员充值">
                                                <#elseif cate=="JFCZ">
                                                    <#assign cname = cname +"积分充值">
                                                <#elseif cate=="CSKT">
                                                    <#assign cname = cname +"财税课堂">
                                                <#elseif cate=="UCSC">
                                                    <#assign cname = cname +"积分兑换">
                                                </#if>
                                                <#if cate_has_next>
                                                    <#assign cname = cname +"，">
                                                </#if>
                                            </#list>
                                        <#else>
                                            <#if coupon.categoryIds?contains("ALL")>
                                                <#assign cname = cname +"全品类">
                                            <#elseif coupon.categoryIds?contains("HYCZ")>
                                                <#assign cname = cname +"会员充值">
                                            <#elseif coupon.categoryIds?contains("JFCZ")>
                                                <#assign cname = cname +"积分充值">
                                            <#elseif coupon.categoryIds?contains("CSKT")>
                                                <#assign cname = cname +"财税课堂">
                                            <#elseif coupon.categoryIds?contains("UCSC")>
                                                <#assign cname = cname +"积分兑换">
                                            </#if>
                                        </#if>
                                    </#if>
                                    ${cname!}
                                    </small></p>
                                    <p>优惠类型 : <small>
                                        <#if couponTypes?? && ( couponTypes?size gt 0 )>
                                        <#list couponTypes as couponType>
                                            <#if couponType.fieldValue==coupon.couponType>
                                            ${couponType.fieldKey}
                                            </#if>
                                        </#list>
                                    </#if>
                                    </small></p>
                                    <p>计算金额类型 : <small>
                                        <#if coupon.amountType=="ORDER">
                                            订单金额
                                        <#elseif coupon.amountType=="POSTAGE">
                                            邮费金额
                                        </#if>
                                    </small></p>
                                </div>
                                <#if coupon.status=="2">
                                    <img src="${ctx}/images/use.png">
                                <#elseif coupon.status=="5">
                                    <img src="${ctx}/images/overdue.jpg">
                                <#elseif coupon.status=="4">
                                    <img src="${ctx}/images/delete.jpg">
                                </#if>

                                <div class="coupon_immediateuse" <#if coupon.status!="1">style="visibility: hidden"</#if>>
                                    <a href="javascript:void(0);" class="immediateuse" category-id="${coupon.categoryIds!}">
                                        立即使用
                                    </a>
                                </div>
                            </div>
                        </div>
                    </#if>
                    </#list>
                </#if>
            </#if>
        </div>
    </div>
    <div class="m_zishiying"><a href='${ctx}/coupon/activities/list.html' target="_blank"><b class="coupon_a">领取更多优惠券</b></a></div>
</div>
<div class="pc_zishiying" id="demo" index="${page}" count="${count}" coupon-status ="${status!}" style="height: 50px;text-align: center;position: absolute;bottom:0px;left: 20%;"></div>

<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
<script data-main="${ctx}/js/abc/coupon" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>