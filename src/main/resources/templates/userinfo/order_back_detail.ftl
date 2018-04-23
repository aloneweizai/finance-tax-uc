<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#include "../common/meta.ftl">
    <title>退换货详情</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr th,td{
            font-size: 12px;
        }
        .col-xs-3{width:20%}
    </style>
</head>

<body>

<body>
<#if (exchange.type) ?? && exchange.type =="1" >
    <div class="grzx_main_rt">换货流程
        <a href="${ctx}/orderback/back.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>退换货申请</a>
    </div>

<#elseif (exchange.type) ?? && exchange.type =="2">
    <div class="grzx_main_rt">退货流程
        <a href="${ctx}/orderback/back.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>退换货申请</a>
    </div>
</#if>
<div class="clear"></div>
<div class="clear"></div>
<div class="thhlc">
    <div class="step">
        <ul>
            <#--换货流程-->
            <#if (exchange.type) ?? && exchange.type =="2" >
            <li class="col-xs-3 on">
                <span class="num"><em class="f-r5"></em><i>1</i></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">提交申请</p>
            </li>
            <li class="col-xs-3 on">
                <span class="num"><em class="f-r5"></em><i>2</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">商家审核</p>
            </li>
            <li class="col-xs-3 on">
                <span class="num"><em class="f-r5"></em><i>3</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">商家收货</p>
            </li>
            <li class="col-xs-3 on">
                <span class="num"><em class="f-r5"></em><i>4</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">确认退单</p>
            </li>
            <li class="col-xs-3 on">
                <span class="num"><em class="f-r5"></em><i>5</i></span>
                <span class="line_bg lbg-l"></span>
                <p class="lbg-txt">退单完成</p>
            </li>
            <#elseif (exchange.type) ?? && exchange.type =="1">
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>1</i></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">提交申请</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>2</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">商家审核</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>3</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">商家收货</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>4</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">商家发货</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>5</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">用户收货</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>6</i></span>
                    <span class="line_bg lbg-l"></span>
                    <p class="lbg-txt">换货完成</p>
                </li>
            </#if>
        </ul>
    </div>
</div>
<div class="grzx_main_rt">退换货环节</div>

<div class="grzx_wdxx_wdxx page_main_tuihuo">
    <ul style="margin-top: 0px;">
        <#if orderlogs ?? && (orderlogs?size > 0)>
            <#list orderlogs as orderlog>
                <li>
                    <div class="grzx_wdxx_wdxx_l ">${orderlog.action!}</div>
                    <div class="grzx_wdxx_wdxx_r">
                        <i></i> 操作人：${orderlog.createUserName!} &nbsp;&nbsp; | &nbsp;&nbsp; ${orderlog.remark!}
                        <span class="tuihuo_shijian"><i class="iconfont icon-dengdai"></i>${orderlog.createTime?string("yyyy-MM-dd HH：mm：ss")}</span>
                    </div>
                    <div class="clear"></div>
                </li>
            </#list>
        <#else>
        </#if>
    </ul>
</div>

<div class="grzx_main_rt">服务单信息</div>
<table class="layui-table fuwudan">
    <tbody><tr>
        <th>商品处理方式：</th>
        <td>
        <#if (exchange.type) ?? && exchange.type =="1" >
            换货
        <#elseif (exchange.type) ?? && exchange.type =="2">
            退货
        </#if>
        </td>
        <#if (exchange.type) ?? && exchange.type =="1">
            <th>物流信息:</th>
            <td>快递</td>
        </#if>
    </tr>
    <tr>
        <th>当前流程：</th>
        <#--退换货状态-->
        <#if exchangeStatus?? && ( exchangeStatus?size gt 0 )>
            <#list exchangeStatus as exchangeStatu>
                <#if exchangeStatu.fieldValue!="0">
                    <#if exchange.status ?? && (exchange.status == exchangeStatu.fieldValue)>
                        <td >${exchangeStatu.fieldKey}</td>
                    </#if>
                </#if>
            </#list>
        </#if>
        <#if (exchange.type) ?? && exchange.type =="1">
            <th>快递单号：</th>
            <td>${exchange.toExpressNo!""}</td>
        </#if>
    </tr>
    <tr>
    <th>问题描述：</th>
    <#if exchangeReason?? && ( exchangeReason?size gt 0 )>
        <#list exchangeReason as reason>
            <#if reason.fieldValue!="0">
                <#if exchange.reason ?? && (exchange.reason == reason.fieldValue)>
                    <td >${reason.fieldKey}</td>
                </#if>
            </#if>
        </#list>
    </#if>
    <#if (exchange.type) ?? && exchange.type =="1" >
        <th>快递公司：</th>
        <td>${exchange.toExpressCompName!""}</td>
    </#if>
    </tr>

    <#if (exchange.type) ?? && exchange.type =="1" >
    <tr>
        <th>收件地址：</th>
        <td>
            <#if (exchange.type)?? && exchange.type == "1">
                 ${(exchange.shippingAddress)!""}
            <#else>
            ${(orderBO.shippingAddress)!""}
            </#if>
        </td>
    </tr>
    <tr>
        <th>收件人：</th>
        <td>
            <#if (exchange.type)?? && exchange.type == "1">
                ${(exchange.consignee)!""}
            <#else>
            ${(orderBO.consignee)!""}
            </#if>
        </td>
    </tr>
    <tr>
        <th>联系电话：</th>
        <td>
            <#if (exchange.type)?? && exchange.type == "1">
                ${(exchange.contactNumber)!""}
            <#else>
            ${(orderBO.contactNumber)!""}
            </#if>
        </td>
    </tr>
    </#if>
    </tbody></table>
</body>
<script data-main="${ctx}/js/abc/order_detail" src="${ctx}/js/require.js"></script>
</html>
</@compress>