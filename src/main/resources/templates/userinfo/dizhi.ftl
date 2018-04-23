<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "../common/meta.ftl">
    <title>财税专家UC</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body >
<!-- head -->
<div class="grzx_main_rt pc_zishiying">
    我的地址
</div>

<!-- 个人中心内容右侧 -->
<div class="grzx_wdjf_dhjl">
        <#if addresslist??&&(addresslist?size > 0)>
            <#list addresslist as list>
                <div class="dizhi_btn">
                    <div class="dizhi_name">${list.name!} <span>${list.phone!}</span></div>
                    <div class="dizhi_dizhi">
                        <#if list.dizhi??>${list.dizhi.province.province!""} </#if ><#if list.dizhi??>${list.dizhi.city.city!""} </#if> <#if list.dizhi??>${list.dizhi.area.area!""} </#if>${list.detail!""}
                    </div>
                    <div class="dizhi_caozuo">
                        <div class="dizhi_morendizhi">
                            <a href="javascript:;"  class="quxiao"
                                <#if list.isDefault??&&list.isDefault>
                                    style="color:#00bcd4;"><i class="iconfont" style="color:#00bcd4;">&#xe711;</i> 默认地址
                                <#else >
                                    default-id="${list.id!}" default-type="default" > <i class="iconfont">&#xe711;</i> 设为默认
                                </#if>
                            </a>
                        </div>
                        <div class="dizhi_xgsc">
                            <span><i class="iconfont">&#xe70f;</i><a href="${list.id!}" class="quxiao">修改</a></span>
                            <span><i class="iconfont">&#xe675;</i><a href="javascript:;" del-id="${list.id!}" del="delete"  class="quxiao">删除</a></span>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
</div>
<a href="addaddr.html" class="layui-btn dizhi_xzdz">添加新收货地址</a>
    <#--<a href="javascript:;" id="jssptest" class="layui-btn">测试寄送商品</a>-->


<script data-main="${ctx}/js/abc/dizhi" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>

