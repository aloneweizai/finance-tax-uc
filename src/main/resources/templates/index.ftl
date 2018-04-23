<@compress single_line=true>
    <#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "common/meta.ftl">
    <title>财税专家UC</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/modules/layer/default/layer.css">

    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <!--[if IE 8]><link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css"><![endif]-->
    <script type="text/javascript">
        var ctx = "${ctx}";
        var snsUrl = "${snsurl}";
        var cswurl = "${cswurl}";
    </script>
</head>
<body style="background: #ececec;">
<!-- 遮罩层DIV -->
<div id="overlay" class="overlay"></div>
<!-- Loading提示 DIV -->
<div id="loadingTip" class="loading-tip">
    <img src="${ctx}/images/loading.gif" />
</div>
<!-- head -->
<#include "common/top.ftl">
<!-- head end -->
<!-- main -->
<div class="m_zishiying">
    <#include "common/content_m.ftl">
</div>
<div class="pc_zishiying">
    <div class="grzx_main_bg">
        <!-- 个人中心内容 -->
        <div class="w_1200 grzx_main">
            <#include "common/content.ftl">
            <#include "common/left.ftl">
            <div class="clear"></div>
        </div>
    </div>
    <!-- main end -->

    <!-- footer  -->
    <#include "common/bottom.ftl">
    <!-- footer end -->
</div>

<script data-main="${ctx}/js/abc/index" src="${ctx}/js/require.js"></script>

</body>

<#include "common/layer_login.ftl" />
</html>
</@compress>