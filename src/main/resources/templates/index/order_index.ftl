<@compress single_line=true>
<#assign ctx=request.getContextPath()>
    <#if orders ?? && ( orders?size > 0)>
        <#list orders  as order>
        <div class="order_btn">
            <div class="order_img">
                <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                    <#list order.orderProductBOList as orderProductBO>
                        <#assign productBo = orderProductBO >

                        <#if (productBo.imageUrl)?? >
                            <img src="${picurl!}${(productBo.imageUrl)!}">
                        <#else>
                            <#if productBo.tradingChannels?? && productBo.tradingChannels=="JFCZ">
                                <img src="${ctx}/images/hyjfcz.jpg">
                            </#if>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="order_xinxi ">
                <div class="order_name duohang">
                    <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                                    <#list order.orderProductBOList as orderProduct>
                        <#assign  product = orderProduct>
                        <#if orderProduct_index == 0 && orderProduct.productBO??>
                        ${orderProduct.productBO.goodsName!""}
                        <#else>
                        ${orderProduct.name!""}
                        </#if>
                    </#list>
                                    <#else>
                    </#if>
                </div>
                <div class="order_zhifufangshi">支付方式：
                    <#if (order.tradeMethod)?? && order.tradeMethod=="POINTS">
                        <span>积分兑换</span>
                    <#elseif order.tradeMethod=="RMB">
                        <#if (order.payMethod)?? && order.payMethod =="ALIPAY">
                            <span>支付宝</span>
                        <#elseif  (order.payMethod)?? && order.payMethod =="WEIXIN">
                            <span>微信</span>
                        <#else>
                            <span></span>
                        </#if>
                    <#else>
                    </#if>
                </div>
                <div class="order_time">下单时间：${order.createTime?string("yyyy-MM-dd")}</div>
                <div class="order_bianhao">订单编号：${(order.orderNo)!""}</div>
            </div>
        <#--金额积分-->
            <div class="order_rmbjifen">
                <#if (order.tradeMethod)?? && order.tradeMethod=="POINTS">
                    <span style="color:#ff9000; "><i class="iconfont">&#xe75a;</i>${(order.totalPrice)!"0"}</span>
                <#else>
                    <span style="color:rgba(255, 31, 19, 0.73) ">¥${(order.totalPrice)?string("0.00")}</span>
                </#if>
            </div>
        <#--订单状态-->
            <div class="order_zhuangtai">
                <#if orderStatus?? && ( orderStatus?size gt 0 )>
                    <#list orderStatus as orderStatu>
                        <#if orderStatu.fieldValue!="0">
                            <#if order.orderStatus ?? && (order.orderStatus == orderStatu.fieldValue)>
                                <td>${orderStatu.fieldKey}</td>
                            </#if>
                        </#if>
                    </#list>
                </#if>
            </div>
        <#--订单操作-->
            <div class="order_caozuo">
                <#if order.orderStatus =="2" || order.orderStatus =="8">
                    <#if (product.productBO)??>
                        <a class="dd_zhifu" href="javascript:;" pay-method ="${(order.tradeMethod)!}" order-id = "${order.orderNo!}" goods-id="${product.goodsId!}" channels = "${product.tradingChannels!""}">立即付款</a>
                    <#else>
                        <a class="dd_zhifu" href="javascript:;" order-id = "${order.orderNo!}" pay-method ="${(order.tradeMethod)!}" goods-id="${product.goodsId!}" channels = "${product.tradingChannels!""}">立即付款</a>
                    </#if>
                    <a  class="wddd_quxiao"  href="${ctx}/userinfo/orderList/${order.orderNo!}/${order.orderStatus}">取消订单</a>
                <#--<a  class="wddd_quxiao"  href="javascript:;">取消订单</a>-->

                <#elseif order.orderStatus =="5">
                    <a href="javascript:void(0)" class="confirmorder" confirm-id="${order.orderNo!}">确认收货</a>

                <#elseif order.orderStatus =="7">
                    <a href="javascript:void(0)" class="deleteorder"  delete-id="${order.orderNo!}">删除订单</a>
                </#if>
                <a href="javascript:void(0)" class="orderdetail"  detail-id="${order.orderNo!}">查看详情</a>
            </div>

        </div>
        </#list>
</#if>
</@compress>