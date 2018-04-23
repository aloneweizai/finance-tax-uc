<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "../common/meta.ftl">
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <#--<link href="${ctx}/css/easyui.css" rel="stylesheet" type="text/css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/jedate/skin/jedate.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
    <div class="grzx_main_rt pc_zishiying">
        我的订单<a href="${ctx}/orderback/back.php" class = "color_b" style="float: right; font-size: 12px; padding-right: 20px;">[退换货申请]</a>
    </div>
    <div class="layui-form pc_zishiying" style="margin-bottom: 58px">
        <div class="layui-form-item fr">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 120px">请选择日期：</label>
                <div class="layui-input-inline" style="width: 150px">
                    <input id="begintime" type="text" placeholder="开始日期" value="${start!}" class="layui-input" readonly>
                </div>
                <div class="layui-input-inline" style="width: 150px">
                    <input id="endtime" type="text" placeholder="结束日期" value="${end!}" class="layui-input" readonly>
                </div>
                <button class="layui-btn" id ="btnQuery" order_type="${type!}">查询</button>
            </div>

        </div>
    </div>

    <div class="layui-tab layui-tab-brief order_shoujizishiying" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title clearfix ">
            <li <#if type="0">class="layui-this"</#if> type="0">全部</li>
            <li <#if type="1">class="layui-this"</#if> type="1">待付款</li>
            <li <#if type="2">class="layui-this"</#if> type="2">待收货</li>
            <li <#if type="3">class="layui-this"</#if> type="3">已完成</li>
            <li <#if type="4">class="layui-this"</#if> type="4">已取消</li>
        </ul>
    <div class="layui-tab-content clearfix">
        <div class="layui-tab-item layui-show">
            <#if type=="0"||type=="1"||type=="2"||type=="3"||type=="4">
            <#if orders ?? && ( orders?size > 0)>
                <#list orders  as order>
                    <div class="order_btn">
                        <div class="order_img">
                        <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                            <#list order.orderProductBOList as orderProductBO>
                                <#assign productBo = orderProductBO >

                                <#if (productBo.imageUrl)?? >
                                    <img class="srcdetail" detail-id="${order.orderNo!}" src="${picurl!}${(productBo.imageUrl)!}">
                                <#else>
                                    <#if productBo.tradingChannels?? && productBo.tradingChannels=="JFCZ">
                                        <img class="srcdetail" detail-id="${order.orderNo!}" src="${ctx}/images/hyjfcz.jpg">
                                    </#if>
                                </#if>
                            </#list>
                          </#if>
                        </div>
                        <div class="order_xinxi">
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
                               &nbsp; X ${(productBo.num)!""}
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
            </#if>
        </div>
    </div>
   <!--modal-->
    <div class="" id="myModal" style="display: none">
        <div class="modal-dialog" role="document" style="width: 400px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label" for="recipient-name">取消原因:</label>
                            <input class="form-control" id="recipient-name" type="text">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="message-text">备注:</label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="reset">重置</button>
                    <button class="btn btn-primary" type="submit">确定</button>
                </div>
            </div>
        </div>
    </div>
<div class="pc_zishiying" id="demo" index="${page}" count="${count}" order-type ="${type!}" style="height: 50px;text-align: center;"></div>
<script data-main="${ctx}/js/abc/order" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>