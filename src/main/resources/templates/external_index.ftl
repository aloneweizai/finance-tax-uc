<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>专家UC</title>
<#include "common/meta.ftl">
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
    var snsUrl = "${snsurl}";
</script>
<style>
    .overlay {
        position: fixed;
        top: 0px;
        left: 0px;
        z-index: 10001;
        display:block;
        filter:alpha(opacity=60);
        background-color: #777;
        opacity: 0.5;
        -moz-opacity: 0.5;
        width: 100%;
        height:100%;
    }
    .loading-tip {
        z-index: 10002;
        position: fixed;
        display:block;
        top:50%;
        left:50%;
    }
    .loading-tip img {
        width:100px;
        height:100px;
    }
</style>
<body style="background: #fff;position: relative;overflow: hidden;">
<!-- 遮罩层DIV -->
<div id="overlay" class="overlay"></div>
<!-- Loading提示 DIV -->
<div id="loadingTip" class="loading-tip">
    <img src="${ctx}/images/loading.gif" />
</div>
<#include "common/external_content.ftl">

<div class="cszj_main_r">
    <iframe class="uc_iframe" frameborder="no"   id="external-frame" scrolling="no" marginheight="0px" marginwidth="0px" url="${url!""}" height="1180"></iframe>
</div>
<div class="clear"></div>
<script data-main="${ctx}/js/abc/index" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>