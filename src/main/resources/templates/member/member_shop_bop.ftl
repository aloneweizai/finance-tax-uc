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
<div class="huiyuan_title">Shop Bop</div>
<ul class="jifenduihuan">
<#if data??&&(data?size>0)>
    <#list data as goods>
        <li>
            <a href="javascript:;">
                <img src="${picurl!""}${goods.imageUrl!""}">
                <p>${goods.name!""}</p>
                <div class="jf_size">
                    <span class="sxjf">¥${goods.sellingPrice?string("0.00")}</span>
                    <span class="syjf">剩余${goods.totalStock!0}份</span>
                </div>
            </a>
            <button class="btn btn-duihuan" goods="jifen" goods-id="${goods.id!''}" kucun="${goods.totalStock!0}">购买</button>
        </li>
    </#list>
    </#if>
    <div class="clear"></div>
</ul>
<script data-main="${ctx}/js/abc/member/shop_bop" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>