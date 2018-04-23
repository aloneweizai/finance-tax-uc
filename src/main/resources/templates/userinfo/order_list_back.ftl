<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#include "../common/meta.ftl">
    <title>退换货管理</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr th{
            font-size: 12px;
        }
    </style>
</head>

<body>
<div class="grzx_main_rt">退换货管理
    <a href="${ctx}/userinfo/order.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的订单</a></div>
<div class="grzx_main_rt_nav">
    <ul>
        <li class="hover">退换货申请</li>
        <li>退换货记录</li>
        <div class="clear"></div>
    </ul>
</div>

<div class="grzx_wdjf_dhjl page_main_xxk">
    <table class="layui-table" lay-size="sm">
        <thead>
        <tr>
            <th width="20%">订单编号</th>
            <th width="20%">商品名称</th>
            <th width="12%">下单日期</th>
            <th width="12%">金额</th>
            <th width="12%">状态</th>
            <th width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
    <#if orders ?? && ( orders?size > 0)>
        <#list orders  as order>
            <tr class="order_btn">
                <td>${(order.orderNo)!""}</td>
                <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                    <#list order.orderProductBOList as orderProduct>
                        <#assign  product = orderProduct>
                        <#if orderProduct_index = 0 && orderProduct.productBO??>
                            <td>${orderProduct.productBO.goodsName!""}</td>
                        <#else>
                            <td>${orderProduct.name!""}</td>
                        </#if>
                        <#break>
                    </#list>
                <#else>
                    <td></td>
                </#if>
                <td >${order.createTime?string("yyyy-MM-dd")}</td>
                <#if (order.tradeMethod)?? && order.tradeMethod=="POINTS">
                    <td style="color: #ff9000; font-weight: bold;">${(order.totalPrice)!"0"}(积分)</td>
                <#else>
                    <td style="color: #ff9000; font-weight: bold;">¥${(order.totalPrice)?string("0.00")}</td>
                </#if>
            <#--订单状态-->
                <#if orderStatus?? && ( orderStatus?size gt 0 )>
                    <#list orderStatus as orderStatu>
                        <#if orderStatu.fieldValue!="0">
                            <#if order.orderStatus ?? && (order.orderStatus == orderStatu.fieldValue)>
                                <td>${orderStatu.fieldKey}</td>
                            </#if>
                        </#if>
                    </#list>
                </#if>
            <#--订单操作-->
                <td ><a class="dd_zhifu"
                        <#if usertzxx??>
                            smrz="${usertzxx.validStatus!""}"
                        <#else>
                            smrz=""
                        </#if>
                        href="javascript:;"  order-id="${order.orderNo!""}" >申请</a>
                    <a href="javascript:void(0);" class="orderdetail"  detail-id="${order.orderNo!}">详情</a>
                </td>
            </tr>
        </#list>
    </#if>
        </tbody>
    </table>
</div>

<div class="grzx_wdjf_dhjl page_main_xxk" style="display: none;">
    <div class="clear"></div>
    <div class="grzx_wdjf_dhjl page_main_xxk">
        <table class="layui-table" lay-size="sm">
            <thead>
            <tr>
                <th>订单编号</th>
                <th style="text-align: left">商品名称</th>
                <th>申请日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="cent">
        <#if exchanges ?? && ( exchanges?size > 0)>
            <#list exchanges  as exchange>
                <tr align="center">
                    <td>${(exchange.orderNo)!""}</td>
                    <td>${(exchange.name)!""}</td>
                   <#-- <td style="text-align: left"><img style="width: 80px;height: 50px;margin-right: 6px;" src="${picurl}${exchange.imageUrl!""}">${exchange.name!""}</td>-->

                    <td >${exchange.createTime?string("yyyy-MM-dd")}</td>

                   <#--退换货状态-->
                    <#if exchangeStatus?? && ( exchangeStatus?size gt 0 )>
                        <#list exchangeStatus as exchangeStatu>
                            <#if exchangeStatu.fieldValue!="0">
                                <#if exchange.status ?? && (exchange.status == exchangeStatu.fieldValue)>
                                    <td>${exchangeStatu.fieldKey}</td>
                                </#if>
                            </#if>
                        </#list>
                    </#if>

                    <td >
                        <a class="chakan" style="color: #14B9D5" order-id ="${(exchange.orderNo)!""}" exchange-id ="${(exchange.id)!""}"  href="javascript:;">查看</a>
                        <#if exchange.status=="2"><#--审核通过需再次确认信息-->
                            | <a class = "exchangeOrder" style="color: #14B9D5"  exchange-id ="${(exchange.id)!""}" href="javascript:;">退换信息</a>
                        <#elseif exchange.status=="3">
                            | <a class = "shouhuo" style="color: #14B9D5"  exchange-id ="${(exchange.id)!""}" href="javascript:;">确认收货</a>
                        </#if>
                    </td>
                </tr>
            </#list>
        </#if>
            </tbody>
        </table>
    </div>
</div>

<script data-main="${ctx}/js/abc/order_back_list" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>