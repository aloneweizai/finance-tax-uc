<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<!--[if IE 9]>    <html class="ie9"> <![endif]-->
<html>
<head>
    <meta charset="utf-8">
<#include "common/meta.ftl">
    <title>财税专家UC</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <link rel="stylesheet" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layer/skin/default/layer.css">
    <!--[if IE 8]><link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css"><![endif]-->
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body >
<#include "common/top_login.ftl">
<div class="ht_login_m">
    <div id="user_denglu">
    <#--<form id="user_denglu" action="login.do" method="post">-->
        <div class="ht_login_b">
        <#--<div class="ht_login_t "><img src="${ctx}/images/logo.png"></div>-->
        <#--<div class="sys_tishi">←扫一扫，微信二维码登陆</div>-->
            <div id="login_user">
                <form id="usernamelogin" class="form-lo" method="post">
                    <div class="login_duanxin">短信验证码登陆 &gt;&gt;</div>
                    <div class="username-con p_rel"><i class="iconfont p_abs">&#xe6d6;</i>
                        <input class="login_username" name="username" maxlength="100" placeholder="用户名/手机号" value=""
                               type="text">
                    </div>
                    <div class="password-con p_rel"><i class="iconfont p_abs">&#xe6af;</i>
                        <input class="login_password" name="password" maxlength="32" placeholder="密码" value=""
                               type="password" id="login_password">
                        <input class="login_password" name="type" type="hidden" value="username">
                        <input type="hidden" name="recallurl" value="${recallurl!}">
                    </div>

                    <div class="style-clor-480" style="display: block; text-align: right;">              <#--pc显示481以下-->
                        <span style="margin-left: 60px; color: #F34948;">${erro!}</span>
                        <a href="forgotpassword.html" target="_blank" style="">忘记密码</a>
                    </div>
                    <div class="denglu"><input type="button" id="userlogin" class="btn btn-info login_btn" value="登录">
                    </div>
                </form>
            </div>

            <div id="login_phone" style="display: none;">
                <form class="form-lo" id="phone_login" method="post">
                    <div class="login_mima">账户密码登陆 &gt;&gt;</div>
                    <div class="username-con p_rel"><i class="iconfont p_abs">&#xe73c;</i>
                        <input class="login_username" name="phone" id="login_phones" maxlength="100" placeholder="手机号码"
                               type="text">
                    </div>
                    <div class="password-con p_rel yanz"><i class="iconfont p_abs">&#xe73d;</i>
                        <input type="text" class="login_password" name="tpcode" id="tpyzms" data-target="#yzmmsgs"
                               maxlength="4"
                               placeholder="验证码" style="width: 65%;">
                        <img src="" id="yzmimgs">
                        <span id="yzmmsgs"></span>
                    </div>
                    <div class="password-con p_rel"><i class="iconfont p_abs">&#xe73d;</i>
                        <input class="login_password" style="width:68%; margin-right: 2%;" name="code" maxlength="6"
                               placeholder="验证码" data-target="#loginphone_msg" type="text" required>
                        <input type="button" class="btn btn-default fsyzm" id="phoneyzm" style="width: 103px;"
                               type-name="手机登录" value="发送验证码">
                        <input class="login_password" name="type" type="hidden" value="phone">
                        <span id="loginphone_msg"></span>
                    </div>

                    <div class="login_jz"></div>

                    <div class=""><input type="button" id="phonelogin" class="btn btn-info login_btn" value="登录">
                    </div>
                </form>
            </div>
            <div class="login_zhuce login">立即注册 &gt;&gt;</div>
            <div class="login_jz style-480" style="display: none!important;">   <#--手机显示480以下-->
                <span style=" color: #F34948;">${erro!}</span>
                <a href="forgotpassword.html" target="_blank">忘记密码</a>
            </div>
        </div>
    </div>


<#--<img class="ewm_none" src="${ctx}/images/login_ewm_lt.png">-->
<#--<div class="login_ewm_lt">-->
<#--<img src="${ctx}/images/login_ewm.jpg">-->
<#--<p><i class="iconfont" style="font-size:20px; color: #fff;">&#xe73e;</i></p>-->
<#--<p>扫一扫微信二维码登陆</p>-->
<#--</div>-->

<#--</form>-->


    <form id="user_zhuce" class="form-lo" action="${ctx}/zhuce.html" method="post">
        <div class="ht_login_b ht_login_bs style-481-470" style="display: none;">
            <div class="ht_login_t "></div>
        <#--       <div class="login_denglu login">&lt;&lt;直接登陆</div>-->
            <div id="login_user">
                <div class="username-con p_rel"><i class="iconfont p_abs">&#xe73c;</i>
                    <input class="login_username" name="username" id="zcphone" maxlength="100" placeholder="手机号码"
                           type="text">
                </div>
                <div class="password-con p_rel"><i class="iconfont p_abs">&#xe6af;</i>
                    <input class="login_password" name="password" id="pass" maxlength="32" placeholder="密码"
                           type="password">
                </div>
                <div class="password-con p_rel"><i class="iconfont p_abs">&#xe6af;</i>
                    <input type="password" class="login_password" name="quepassword" id="quepass" maxlength="32"
                           placeholder="确认密码">
                </div>
                <div class="password-con p_rel yanzs"><i class="iconfont p_abs">&#xe73d;</i>
                    <input type="text" class="login_password" name="tpcode" id="tpyzm" data-target="#yzmmsg"
                           maxlength="4"
                           placeholder="验证码" style="width: 65%;">
                    <img src="" id="yzmimg">
                    <span id="yzmmsg"></span>
                </div>
                <div class="password-con p_rel yanz"><i class="iconfont p_abs">&#xe73d;</i>
                    <input class="login_password" style="width:65%; margin-right: 2%;" id="zhuce_code" name="code"
                           maxlength="6" data-target="#dxyzmmsg" placeholder="短信验证码" type="text">
                    <input type="button" class="btn btn-default fsyzm" id="yzm" style="width: 112px;" type-name="用户注册"
                           value="发送验证码">

                    <span id="dxyzmmsg" style="position: relative;z-index: 1; "></span>
                </div>
                <div id="ok" class="layui-form">
                    <label for="tongyi">
                      <#--    <input style="float: left;margin-right: 6px; margin-top: -1px" id="tongyi" type="checkbox" name="1">-->
                        <input class="js_checkbox" type="checkbox" name="1" value="" lay-skin="primary" id="tongyia" title="">
                        我已阅读并同意
                    </label>
                    <a href="${yhxyUrl!""}" target="_blank">用户协议和隐私条款</a>
                </div>
                <div class=""><input type="button"  class="btn btn-info login_btn" id="zhucetijiao" value="注册">
                </div>
            </div>
            <div class="login_denglu login" style="display:none;">&lt;&lt;直接登陆</div>
        </div>
    </form>
</div>



<#--<#include "common/bottom.ftl">-->
<script data-main="${ctx}/js/abc/login" src="${ctx}/js/require.js"></script>
<script data-main="${ctx}/js/abc/login" src="${ctx}/js/cssrem.js"></script>
</body>
</html>
</@compress>