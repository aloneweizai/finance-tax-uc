<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<!--[if IE 9]>    <html class="ie9"> <![endif]-->
<html>
<head>
<#include "../common/meta.ftl">
    <title>会员中心</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/MeCe.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
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
<body style="background: #ececec;">
<!-- 遮罩层DIV -->
<div id="overlay" class="overlay"></div>
<!-- Loading提示 DIV -->
<div id="loadingTip" class="loading-tip">
    <img src="${ctx}/images/loading.gif" />
</div>
<!-- head -->
<#include "../common/top.ftl">
<!-- head end -->
<#--<#include "../common/top_member.ftl">-->

<!-- main -->
<!-- 个人中心-头部 -->

<!--会员内容-->
<#include "../member/member_content.ftl">

<!-- main end -->
<!-- footer  -->
<#include "../common/bottom.ftl">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog" role="document" style="height: 100%;">
        <div class="modal-content" style="width: 400px; left: 50%; top: 50%; margin: -100px 0 0 -200px;">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">是否支付成功!</h4>
            </div>
            <div class="modal-footer" style="text-align: center">
                <button type="button" class="btn btn-info" id="chenggong">支付成功</button>
                <button type="button" class="btn btn-warning" id="shibai">支付失败</button>
            </div>
        </div>
    </div>
</div>

<#include "../common/layer_login.ftl" />
<!-- footer end -->
<script data-main="${ctx}/js/abc/member/member_index" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>