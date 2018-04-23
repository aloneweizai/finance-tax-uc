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
<div class="huiyuan_title">积分充值</div>

<img src="${ctx}/images/hyjfcz.jpg" style="margin-top: 20px;">
<p class="jifen_jilu">我的积分：<span class="jifen">${user.points!0}分 </span></p>
<div class="jifen_dingdanxinxi">
    <p>积分单价：<span style="color: #F34948;">1元=1000积分 </span></p>
    <#--<p>充值积分：<span class="jifen">33000积分</span><span style="margin-left: 10px; color: #999;">包含赠送积分：3000</span></p>-->
    <p class="jifen_jexz">
        选择金额：
        <#if data??&&(data?size>0)>
            <#list data as da>
                <a
                        <#if da_index==0>
                                class="select"
                        </#if>
                        href="javascript:void(0);" jine="${da.fee!0}" guigeid="${da.integralId!""}">
                    <#if da.integralId=='JF0'>
                        任意金额
                    <#else>
                        ${da.fee!0}元
                    </#if>
                    <#if da_index==0>
                        <#assign data_jifen=da.fee>
                        <#assign guige=da.integralId>
                        <em class="rec-icon"></em>
                    </#if>
                </a>
            </#list>
        </#if>
        <input id="ryjeid" value="" type="hidden" />
        <#--<a class="select" href="javascript:void(0);" jine="30">30元<em class="rec-icon"></em></a>-->
        <#--<a href="javascript:void(0);" jine="50">50元</a>-->
        <#--<a href="javascript:void(0);" jine="100">100元</a>-->
        <#--<a href="javascript:void(0);" jine="300">300元</a>-->
    </p>
    <p id="ryje" style="display: none;">任意金额：<input type="text" style="width: 100px;" id="jine" onkeyup="this.value=this.value.replace(/\D/g,'')" guigeid="JF0" placeholder="请输入金额"></p>
    <p style="color: #F34948;">*备注：充值后不能退换!</p>
</div>
<div class="content_box">
    <div class="zhu">
        <input name="pay-submit" type="button" id="submit_pay_button" class="btn-info" jine="${data_jifen!0}" guigeid="${guige!""}" value="下一步"></div>
</div>
</div>
<script data-main="${ctx}/js/abc/member/integral_payment" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>