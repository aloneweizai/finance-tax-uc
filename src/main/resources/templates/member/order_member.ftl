<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/privilege.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>

<body style="background: #fff">
<div class="privilege-box">
    <div class="top-box">
        <h3>开通会员</h3>

        <div class="article-box">
        <#if data??&&(data?size>0)>
            <#list data as vip>
                <#if vip.levelCode!='VIP0'>
                <div class="vip_kaitonghuiyuan">
                    <div class="article">

                        <a href="member_rights.html"">
                            <div class="img">
                                     <img src="${picurl!""}${vip.imgUrl!""}" alt="">
                            </div>
                            <div class="price-box">
                                <div class="price">
                                    <h4>
                                        <span>优惠价 ¥ </span> <em>${vip.salePrice?string("0.00")}</em>
                                        <span>元/年</span>
                                    </h4>
                                    <p>
                                        会员价值：
                                        <span>${vip.marketPrice?string("0.00")}</span>
                                    </p>
                                    <p>
                                        VIP 等级：
                                        <span>${vip.level!""}</span>
                                    </p>
                                    <p>
                                        赠送积分：
                                        <span>${vip.sendPoints!""}</span>
                                    </p>
                                </div>

                            </div>
                        <div class="tishi_absolute">
                            <div class="txt">点击查看${vip.level!""}特权</div>
                        </div>
                        </a>
                        <a style=" background: #13bad5" href="javascript:void (0)" class="layui-btn layui-btn-small price-kthy-btn"
                                 palce="zhifu" goods-id="${vip.id!""}">开通会员</a>
                    </div>
                </div>
                </#if>
            </#list>
        </#if>
        </div>
    </div>
</div>
<div class="clear"></div>

<script data-main="${ctx}/js/abc/member/member_order" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>