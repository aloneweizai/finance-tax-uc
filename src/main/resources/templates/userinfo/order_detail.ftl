<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "../common/meta.ftl">
    <title>订单详情</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr th,td{
            font-size: 12px;
        }
        .col-xs-2{width:20%}
        .grzx_main_tlel{
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="grzx_main_rt pc_zishiying">订单流程
        <a href="${ctx}/userinfo/order.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的订单</a>
    </div>
    <div class="clear"></div>
    <div class="thhlc">
        <div class="step">
            <ul>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>1</i></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">订单提交</p>
                </li>

                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>2</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">用户付款</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>3</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">商品发货</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>4</i></span>
                    <span class="line_bg lbg-l"></span>
                    <span class="line_bg lbg-r"></span>
                    <p class="lbg-txt">用户收货</p>
                </li>
                <li class="col-xs-2 on">
                    <span class="num"><em class="f-r5"></em><i>5</i></span>
                    <span class="line_bg lbg-l"></span>
                    <p class="lbg-txt">订单完成</p>
                </li>
            </ul>
        </div>
    </div>


    <div class="grzx_main_rt grzx_main_tlel  pc_zishiying">订单环节</div>

    <div class="grzx_wdxx_wdxx page_main_tuihuo">
        <ul style="margin-top: 0px;">
        <#if orderlogs ?? && (orderlogs?size > 0)>
            <#list orderlogs as orderlog>
                <li>
                    <div class="grzx_wdxx_wdxx_l ">${orderlog.action!}</div>
                    <div class="grzx_wdxx_wdxx_r">
                        <i></i>&nbsp;&nbsp; ${orderlog.remark!}
                        <span class="tuihuo_shijian"><i class="iconfont icon-dengdai"></i>${orderlog.createTime?string("yyyy-MM-dd HH：mm：ss")}</span>
                    </div>
                    <div class="clear"></div>
                </li>
            </#list>
        <#else>
        </#if>
        </ul>
    </div>
    <#if (orderBO.orderProductBOList)?? && (orderBO.orderProductBOList?size>0)>
        <#list orderBO.orderProductBOList as orderProductBO>
            <#assign productBo = orderProductBO >
                <div class="order_btn">
                    <div class="order_img">
                        <#if (productBo.imageUrl)?? >
                            <img src="${picurl!}${(productBo.imageUrl)!}">
                        <#else>
                            <#if productBo.tradingChannels?? && productBo.tradingChannels=="JFCZ">
                                <img src="${ctx}/images/hyjfcz.jpg">
                            </#if>
                        </#if>
                    </div>
                    <div class="order_xinxi orderdetail" detail-id="${order.orderNo!}">
                        <div class="order_name duohang">
                            ${productBo.name!}
                        </div>
                        <div class="order_zhifufangshi">支付方式：
                            <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                                积分兑换
                            <#elseif orderBO.tradeMethod=="RMB">
                                <#if (orderBO.payMethod)?? && orderBO.payMethod =="ALIPAY">
                                    支付宝
                                <#elseif  (orderBO.payMethod)?? && orderBO.payMethod =="WEIXIN">
                                    微信
                                </#if>
                            </#if>
                        </div>
                        <#--<div class="order_time">下单时间：${order.createTime?string("yyyy-MM-dd")}</div>-->
                        <div class="order_bianhao">订单编号：${orderNo!}</div>
                    </div>
                <#--金额积分-->
                    <div class="order_rmbjifen2">
                        <span style="font-size: 14px">实付款：</span>
                        <#if orderBO.payMethod??>
                            <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                                <span style="color:#ff9000; "><i class="iconfont" title="积分">&#xe75a;</i>${(orderBO.totalPrice)!"0"}</span>
                            <#else>
                                <span style="color:rgba(255, 31, 19, 0.73) ">¥${(orderBO.totalPrice)?string("0.00")}</span>
                            </#if>
                        <#else>
                            <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                                <span style="color:#ff9000; "><i class="iconfont" title="积分">&#xe75a;</i>0</span>
                            <#else>
                                <span style="color:rgba(255, 31, 19, 0.73) ">¥0.00</span>
                            </#if>
                        </#if>
                    </div>
                <#--订单状态-->
                    <div class="order_zhuangtai2">优惠：
                        <#assign  yhPay =0>
                        <#if coupons?? && (coupons?size>0) >
                            <#list  coupons as coupon>
                                <#if coupon.amountAfter??>
                                    <#assign  yhPay =yhPay + coupon.amountAfter >
                                </#if>
                            </#list>
                        </#if>
                        <#if orderBO.payMethod??>
                            <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                                ${(yhPay)!"0"}积分
                            <#else>
                                ¥${(yhPay)?string("0.00")}
                            </#if>
                        <#else>
                            <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                            ${(yhPay)!"0"}积分
                            <#else>
                                ¥${(yhPay)?string("0.00")}
                            </#if>
                        </#if>
                    </div>
                    <div class="order_shifuzone2">原价：
                        <#assign productPay =0>
                        <#if orderBO.orderProductBOList?? && (orderBO.orderProductBOList?size>0)>
                            <#list orderBO.orderProductBOList as product>
                                <#assign productPay = productPay+ product.dealPrice*product.num>
                            </#list>
                        </#if>
                        <#if (orderBO.tradeMethod)?? && orderBO.tradeMethod == "POINTS" >
                            ${productPay!"0"}积分
                        <#else>
                            ¥${(productPay)?string("0.00")}
                        </#if>
                    </div>
                </div>

         </#list>
    </#if>

        <#if (orderBO.orderProductBOList)?? && (orderBO.orderProductBOList?size>0)>
            <#list orderBO.orderProductBOList as orderProductBO>
                <#assign productBo = orderProductBO >
                <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1>
                <div class="grzx_main_rt grzx_main_tlel pc_zishiying">配送信息</div>
                <div class="order_psxx">
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1><p><span>快递公司：</span><#if expressComp??>${(expressComp.compName)!""}</#if></p></#if>
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1><p><span>快递单号：</span>${(orderBO.expressNo)!}</p></#if>
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1>
                        <p><span>运费：</span>
                            <#if (orderBO.deliveryFee)??>
                                ¥${(orderBO.deliveryFee)?string("0.00")}
                            <#else>
                                ¥0.00
                            </#if>
                        </p>
                    </#if>
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1>
                        <p>
                            <span>收货人：</span>${(orderBO.consignee)!}
                        </p>
                    </#if>
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1><p><span>手机号码：</span>${(orderBO.contactNumber)!}</p></#if>
                    <#if (orderBO.isShipping) ?? && orderBO.isShipping == 1><p><span>收货地址：</span><abc>${(orderBO.shippingAddress)!}</abc></p></#if>
                </div>
                </#if>
                <#break >
            </#list>
        </#if>


        <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true>
        <div class="grzx_main_rt grzx_main_tlel pc_zishiying">发票信息</div>
        <div class="order_psxx">
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>发票抬头：</span>
                <#if (orderBO.invoiceBO.name) ?? && orderBO.invoiceBO.name=='1'>
                    个人
                <#else >
                ${(orderBO.invoiceBO.nsrmc)!}
                </#if>
            </p></#if>
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>发票性质：</span>
                <#if (orderBO.invoiceBO.property)??&&orderBO.invoiceBO.property=='1'>
                    纸质发票
                <#else >
                    电子发票
                </#if>
            </p></#if>
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>发票内容：</span>
                <#if invoicecontents?? && (invoicecontents?size >0)>
                    <#list invoicecontents as invoicecontent>
                        <#if (orderBO.invoiceBO.content) ?? && invoicecontent.fieldValue  == orderBO.invoiceBO.content>
                        ${invoicecontent.fieldKey!}
                        </#if>
                    </#list>
                </#if>
            </p></#if>
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>发票状态：</span>
                <#if fqsqstatus??&&(fqsqstatus?size>0)>
                    <#list fqsqstatus as data>
                        <#if (orderBO.invoiceBO.status) ?? && data.fieldValue==orderBO.invoiceBO.status>
                        ${data.fieldKey!""}
                        </#if>
                    </#list>
                </#if>
            </p></#if>
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>发票金额：</span>¥${(orderBO.invoiceBO.amount)?string("0.00")}</p></#if>
            <#if (orderBO.isInvoice)??  && orderBO.isInvoice == true><p><span>纳税人识别号：</span>${(orderBO.invoiceBO.nsrsbh)!}</p></#if>
        </div>
        </#if>
    <#if orderBO?? && orderBO.orderGiftBOList?? &&  (orderBO.orderGiftBOList?size>0)>
    <#assign  ogl = orderBO.orderGiftBOList>
    <div class="grzx_main_rt grzx_main_tlel">优惠赠送</div>
    <table class="layui-table fuwudan" lay-even="" lay-skin="nob" style="margin: 20px 0;border: 1px solid #ddd;">
        <#list ogl as og>
            <tr>
                <th>
                <#if operTypes?? && (operTypes?size>0)>
                    <#list operTypes as ot>
                        <#if ot.fieldValue == og.operType>
                            ${ot.fieldKey!}
                        </#if>
                    </#list>
                </#if></th>
                <td>
                    <#if  og.operType=="POINTS">
                        ${og.operValue!"0"}积分
                    <#elseif  og.operType=="AMOUNT">
                        ¥${og.operValue!"0"}
                    <#elseif  og.operType=="COUPON">
                        ¥${og.operValue!"0"}
                    <#elseif  og.operType=="VIP">
                        <#if og.operValue =="VIP1">
                            银卡会员
                        <#elseif og.operValue =="VIP2">
                            金卡会员
                        <#elseif og.operValue =="VIP3">
                            钻石会员
                        <#elseif og.operValue =="VIP4">
                            超级会员
                        <#else>
                            普通用户
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
    </#if>
<script data-main="${ctx}/js/abc/order_detail" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>