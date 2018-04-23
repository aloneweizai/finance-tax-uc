<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>VC积分商城</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/Redeem.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>

<body style="background: #fff">
<div class="Redeem-box">
    <div class="top-box">
        <h3>积分兑换</h3>
        <div class="article-box">
        <#if data??&&(data?size>0)>
            <#list data as goods>
            <div class="article">
                <div class="img">
                    <img src="${picurl!""}${goods.imageUrl!""}" alt="">
                </div>
                <div class="price-box">
                    <div class="price">
                        <h4>${goods.name!""}
                            <span style="float: right">
                                 剩余<em>${goods.totalStock!0}</em>份
                             </span>
                        </h4>
                        <p>
                            <i class="iconfont">&#xe75e;</i>
                            <em>${goods.sellingPrice!0}</em>
                        </p>
                    </div>
                    <a style="width: 90px; background: #13bad5" href="javascript:void (0)" goods="jifen"
                    <#if usertzxx??>
                       smrz="${usertzxx.validStatus!""}"
                    <#else>
                       smrz=""
                    </#if>
                       goods-id="${goods.id!''}" kucun="${goods.totalStock!0}" class="layui-btn layui-btn-small">兑换</a>
                </div>
            </div>
            </#list>
          </#if>
        </div>
    </div>
</div>
<div class="clear"></div>

<script data-main="${ctx}/js/abc/member/member_integral" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>