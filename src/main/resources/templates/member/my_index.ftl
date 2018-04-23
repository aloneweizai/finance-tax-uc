<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head>

<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/MeCes.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body>

<body>
<div class="MeCes-box">
    <div class="top-box">
        <h3>我的会员专属特权<a href="${ctx}/member/privilege_introduce/all">【查看更多特权】</a></h3>
        <ul>
        <#if privilegeList?? && (privilegeList?size> 0) >
            <#list privilegeList as privilege>
                <#if levels?? && (levels?size > 0)>
                    <#list levels as level>
                        <#if level.privilegeId?? && level.privilegeId == privilege.code>
                        <li>
                            <a href="${ctx}/member/privilege_introduce/${privilege.id!""}">
                                <div>
                                    <i class="iconfont ${privilege.icon!""}"></i>
                                    <p>${privilege.name}</p>
                                </div>
                            </a>
                        </li>
                        </#if>
                    </#list>
                </#if>
            </#list>
        </#if>
        </ul>
    </div>
    <div class="bottom-box">
        <h3>会员订购信息<a href="../userinfo/order.php" style="float: right; font-size: 12px; padding-right: 20px;">查看更多订单... </a></h3>
        <#if orders??&&(orders?size>0)>
            <table class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th>订单编号</th>
                    <th>商品名称</th>
                    <th>金额/积分</th>
                    <th>下单日期</th>
                <#--<th width="10%">付款日期</th>-->
                    <th>支付方式</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list orders as order>
                    <tr class="order_btn">
                        <td>${order.orderNo!}</td>
                        <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                            <#list order.orderProductBOList as orderProduct>
                                <#assign  product = orderProduct>
                                <#if orderProduct_index == 0 && orderProduct.productBO??>
                                    <td>${orderProduct.productBO.goodsName!""}</td>
                                <#else>
                                    <td>${orderProduct.name!""}</td>
                                </#if>
                            </#list>
                        <#else>
                            <td ></td>
                        </#if>
                        <#if (order.tradeMethod)?? && order.tradeMethod=="POINTS">
                            <td style="color: #ff9000; font-weight: bold;">${(order.totalPrice)!"0"}(积分)</td>
                        <#else>
                            <td style="color: #ff9000; font-weight: bold;">¥${(order.totalPrice)?string("0.00")}</td>
                        </#if>
                        <td>${order.createTime?string("yyyy-MM-dd")}</td>
                    <#--<td>${order.createTime?string("yyyy-MM-dd")}</td>-->
                        <#if (order.tradeMethod)?? && order.tradeMethod=="POINTS">
                            <td>积分兑换</td>
                        <#elseif order.tradeMethod=="RMB">
                            <#if (order.payMethod)?? && order.payMethod =="ALIPAY">
                                <td>支付宝</td>
                            <#elseif  (order.payMethod)?? && order.payMethod =="WEIXIN">
                                <td>微信</td>
                            <#else>
                                <td></td>
                            </#if>
                        <#else>
                            <td></td>
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

                        <#if order.orderStatus =="2"|| order.orderStatus =="8">
                            <td>
                                <#if (product.productBo)?? >
                                    <a class="dd_zhifu" href="javascript:;" pay-method ="${(order.tradeMethod)!}" order-id = "${order.orderNo!}"  goods-id="${product.goodsId!}" channels = "${product.tradingChannels!""}">立即付款</a>
                                <#else>
                                    <a class="dd_zhifu" href="javascript:;" pay-method ="${(order.tradeMethod)!}" order-id = "${order.orderNo!}"  goods-id="${product.goodsId!}" channels = "${product.tradingChannels!""}">立即付款</a>
                                </#if>
                                <a class="quxiao" href="${ctx}/userinfo/orderList/${order.orderNo!}/${order.orderStatus}">取消订单</a>
                            </td>
                        <#elseif order.orderStatus =="5">
                            <td style="text-align: left"><a class="orderdetail"  detail-id="${order.orderNo!}">详情</a>
                                <a href="javascript:void(0)"  class="confirmorder" confirm-id="${order.orderNo!}">确认收货</a>
                            </td>
                        <#elseif order.orderStatus =="7">
                            <td style="text-align: left"><a class="orderdetail"  detail-id="${order.orderNo!}">详情</a>
                                <a href="javascript:void(0)" class="deleteorder" delete-id="${order.orderNo!}">删除订单</a>
                            </td>
                        <#else>
                            <td style="text-align: left"><a href="javascript:void(0)" class="orderdetail"  detail-id="${order.orderNo!}">详情</a></td>
                        </#if>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <div class="img">
                <img src="${ctx}/images/asd.png" alt="">
            </div>
        </#if>
    </div>
</div>
</body>

<script data-main="${ctx}/js/abc/member/my_index" src="${ctx}/js/require.js"></script>
</html>
</@compress>