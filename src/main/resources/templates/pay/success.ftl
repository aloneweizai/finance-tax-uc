<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>支付成功-财税专家</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">
   <#-- <link type="text/css" rel="stylesheet" media="screen and (min-width:481px) and (max-width:1200px)" href="${ctx}/css/webpage_main-1024.css"/>-->
  <#--  <link type="text/css" rel="stylesheet" media="screen and (min-width:1201px)" href="${ctx}/css/webpage_main.css"/>-->
    <link type="text/css" rel="stylesheet" href="${ctx}/css/webpage_main.css"/>
    <!--[if IE 8]>
    <link type="text/css" rel="stylesheet"  href="${ctx}/css/webpage_main-1024.css"/>
    <![endif]-->
    <script>
        var csw='${cswurl}';
        var ctx='${ctx}';
    </script>

</head>

<body style="background: #ececec;">
<!-- head -->
<#include "../common/top.ftl">
<!-- head end -->
<!-- main -->
<div class="w_1200">
    <div class="b_f pt10 pl15 pr15 pb10" style="min-height: 630px;margin-top: 25px;">
        <div class="grzx_main mt20">
            <div class="login-box f-mt10 f-pb50">
                <div class="main bgf">
                    <div class="reg-box-pan display-inline">
                        <div class="reg-box m30 mt10" id="verifyCheck">
                            <div class="part3 tc h300" style=" margin-top:145px; padding-left: 0;">
                                <div class="zhifu_tishi" style="padding-left: 350px;">
											<span class="dpb w70 h70 lh70 tc b_2fbd68 c_f bdr100 fl">
												<i class="iconfont fs42">&#xe732;</i>
											</span>
                                    <span class="dpib h70 lh70 fs20 fb c_2fbd68  pl10 fl">恭喜您支付成功！</span>
                                    <div class="clear"></div>
                                </div>

                                <p class=" fs14 c_9 mt20">订单号： <strong id="times" class="c_f29941" style="width:100px;display: inline-block;text-align: left;">${orderid!""}</strong> </p>
                                <p class=" fs14 c_9 mt20">订单流水号： <strong id="times" class="c_f29941" style="width:100px;display: inline-block;text-align: left;">${no!""}</strong> </p>
                                <#if type=='RMB'>
                                    <p class=" fs14 c_9 ">订单金额： <strong id="times" class="c_f29941" style="width:100px;display: inline-block;text-align: left;">&yen;${zje!0.00}</strong> </p>
                                <#else>
                                    <p class=" fs14 c_9 ">订单积分： <strong id="times" class="c_f29941" style="width:100px;display: inline-block;text-align: left;"><i class="iconfont">&#xe814;</i>${zje!0.00}</strong> </p>
                                </#if>
                                <div class=" mt30">
                                    <button class="btn btn-info mr15" id="cswindex">返回财税网首页</button>
                                    <button class="btn btn-success" id="ckddxq">查看订单信息</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!-- main end -->
<!-- footer  -->
<#include "../common/bottom.ftl">

<#include "../common/layer_login.ftl" />
<!-- footer end -->
<script data-main="${ctx}/js/abc/pay/success" src="${ctx}/js/require.js"></script>
</body>

</html>