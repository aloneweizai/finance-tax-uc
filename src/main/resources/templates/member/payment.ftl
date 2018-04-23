<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head>

<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body style="">
<div class="huiyuan_title">订单支付</div>
<div class="content_box">
    <div class="content clearfix ">
<#if order??>
    <div style="font-size: 16px;   ">订单编号:<span style="padding-left: 20px;">${order.orderNo!""}</span></div>
        <div style="font-size: 16px;  ">商品描述:
        <#if order.orderProductBOList??&&(order.orderProductBOList?size>0)>
            <span style="padding-left: 16px;"><#list order.orderProductBOList as da>
                <#if da_index==0>
                    ${da.name!""}
                </#if>
            </#list>
            <#if (order.orderProductBOList?size>1)>
                ...
            </#if>
            </span>
        </#if>
        </div>
    <div style="font-size: 16px; ">订单金额:<span style="color: #F34948;padding-left: 20px;"><b>${order.totalPrice?string("0.00")}</b></span>元</div>
    <div style="font-size: 16px; ">赠送积分:<span style="color: #F34948;padding-left: 20px;"><b>${order.giftPoints!0}</b></span>分</div>
    <#--<div style="font-size: 16px; ">&nbsp;&nbsp;&nbsp;推荐人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="tjr" type="text"> <span style="color: #F34948;">注:(请输入推荐人工号或手机号)</span></div>-->
    </#if>

        <#--<#if order??>-->
                <#--<a class="pay_type fl" href="javascript:void(0);" id="${order.id!""}" goods-id="${order.id!""}"-->
                   <#--<#if order.orderProductBOList??&&(order.orderProductBOList?size>0)>-->
                       <#--<#list order.orderProductBOList as da>-->
                            <#--<#if da_index==0>-->
                                <#--<#assign data_jifen=da>-->
                                <#--goods-name="${da.productBO.goodsName!""}" guige-id="${da.productBO.goodsId!""}"-->
                            <#--</#if>-->
                       <#--</#list>-->
                   <#--</#if>-->

                    <#--goods-jiage="${order.totalPrice!0}"><span>${order.totalPrice!0}元</span>-->
                <#--${data_jifen.productBO.goodsName!""}-->
                <#--</a>-->
        <#--</#if>-->
    <#--<#if data??&&(data?size>0)>-->
        <#--<#list data as goods>-->
            <#--<#if goods.id==goodsid>-->
            <#--<a class="pay_type fl <#if goods.id==goodsid>select</#if>" href="javascript:void(0);" id="${goods.id!""}" goods-id="${goods.id!""}" goods-name="${goods.name!""}"-->
            <#--<#if goods.productBOList??&&(goods.productBOList?size>0)>-->
                <#--<#if goods.productBOList?size==1>-->
                    <#--<#list goods.productBOList as da>-->
                        <#--<#assign data_jifen=da>-->
                            <#--guige-id="${da.id!""}"-->
                    <#--</#list>-->
               <#--</#if>-->
               <#--</#if>-->
               <#--goods-jiage="${goods.sellingPrice!""}"><span>${goods.sellingPrice!""}元</span>${goods.name!""}</a>-->
            <#--</#if>-->
        <#--</#list>-->
    <#--</#if>-->
    </div>
    <div style="border: 1px solid #E3E3E3; margin-top: 10px; margin-bottom: 10px;"></div>
    <div class="pay">
        <p class="zan">选择支付方式</p>
        <div class="zz_way">
            <div class="pay_ways">
                <a class="pay_way_item way_alipay" href="javascript:;" payway="ali" payMethod="ALIPAY"></a>
                <#--<a class="pay_way_item way_weixin" href="javascript:;" payway="weixin" payMethod="WEIXIN"></a>-->
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="zhu">
        <input name="pay-submit" type="button" id="submit_pay_button" goods-id="${goodsid!""}" order-id="${orderid!""}"  tradeNo = "${tradeNo!""}" class="btn-info" payType="ALIPAY" value="立即支付"></div>
</div>
</div>
<#--<div class="zf_zhifubao" test="zhifu" id="alipay_div" style="display: none">-->
    <#--<!--支付宝支付&ndash;&gt;-->
    <#--<h4>-->
        <#--<div class="zf_logo"><i class="iconfont" style="color:#009fe8;">&#xe750;</i></div>-->
    <#--</h4>-->
    <#--<div class="zf_erweima">-->
        <#--<span id="alipay_img"><img src="${ctx}/images/loading.gif" id="zhifubaoewm">-->
            <#--&lt;#&ndash;<i class="iconfont">&#xe621;</i> 刷新&ndash;&gt;-->
        <#--</span>-->
        <#--<p>请使用支付宝扫一扫支付</p></div>-->
<#--</div>-->
<#--<div class="zf_weixin" test="zhifu" id="weixin_div" style="display: none">-->
    <#--<h4>-->
        <#--<div class="zf_logo" ><i class="iconfont" style="color: #09bb07;">&#xe751;</i></div>-->
    <#--</h4>-->
    <#--<div class="zf_erweima">-->
        <#--<span id="weixin_img"><img src="${ctx}/images/loading.gif"></span>-->
        <#--<p>请使用微信扫一扫支付</p></div>-->
<#--</div>-->

<!-- Modal -->

<script data-main="${ctx}/js/abc/member/payment" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>