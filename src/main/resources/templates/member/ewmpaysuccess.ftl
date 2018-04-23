<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <#include "../common/meta.ftl">
    <title>专家UC</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<#--<body>-->
<#--<div class="ny_head">-->
    <#--<div class="w_1200">-->
        <#--<div class="ny_logo">-->
            <#--<a href="#" title="网站首页">-->
                <#--<div class="boximg"><span><img src="${ctx}/images/ny_logo.jpg"></span></div>-->
            <#--</a>-->
        <#--</div>-->
        <#--<div class="fr">-->
            <#--<div class="ny_nav">-->

            <#--</div>-->
            <#--<div class="ny_login_nav">-->

            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
<div class="reg-box m30 mt10" id="verifyCheck" style="  display: flex; justify-content: center;  align-items: center; height: 100%;">
    <div class="part3 tc h300" style="padding-left: 0px">
        <div>
											<span class="dpib w80 h80 lh80  b_2fbd68 c_f bdr100">
												<i class="iconfont fs42">&#xe732;</i>
											</span>
            <span class="dpib ml10">
												<p class="fs20 fb c_2fbd68 lh80">恭喜您支付成功！</p>

											</span>
        </div>
        <#--<p class=" fs12 c_9 mt20">页面将在 <strong id="times" class="c_f29941">5</strong> 秒钟后，自动关闭</p>-->
        <div class=" mt20">
            <button class="btn btn-info mr15" onclick="window.location.href='${ctx}/member/my_index.html'">返回首页</button>
            <button class="btn btn-success" onclick="window.location.href='${ctx}/userinfo/order.php'">查看订单</button>
        </div>
    </div>
</div>

<#--<#include "../common/bottom.ftl">-->
<script data-main="${ctx}/js/abc/member/ewmpaysuccess" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>