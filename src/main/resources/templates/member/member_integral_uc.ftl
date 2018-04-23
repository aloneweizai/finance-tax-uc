<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>UC积分商城</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body style="">
<div class="huiyuan_title">积分兑换</div>
<p style="color: #F34948;">*备注：积分兑换的商品不能退换!</p>
<p class="jifen_jilu">我的积分：${user.points!0}分 <a href="../pointsExchange/points.php">查看我的兑换记录...</a></p>
<ul class="jifenduihuan">
<#if data??&&(data?size>0)>
    <#list data as goods>
        <li>
            <a href="javascript:;" goods="jifen"
                <#if usertzxx??>
               smrz="${usertzxx.validStatus!""}"
                <#else>
               smrz=""
                </#if>
               goods-id="${goods.id!''}" kucun="${goods.totalStock!0}">
                <img src="${picurl!""}${goods.imageUrl!""}">
                <p>${goods.name!""}</p>
                <div class="jf_size">
                    <span class="sxjf"><i class="iconfont">&#xe733;</i>${goods.sellingPrice!0}</span>
                    <span class="syjf">剩余${goods.totalStock!0}份</span>
                </div>
            </a>
            <button class="btn btn-duihuan" goods="jifen"
                <#if usertzxx??>
                    smrz="${usertzxx.validStatus!""}"
                <#else>
                    smrz=""
                </#if>
                    goods-id="${goods.id!''}" kucun="${goods.totalStock!0}">兑换</button>
        </li>
    </#list>
    </#if>
    <div class="clear"></div>
</ul>
<script data-main="${ctx}/js/abc/member/member_integral_uc" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>