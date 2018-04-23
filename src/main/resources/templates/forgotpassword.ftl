<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <#include "common/meta.ftl">
    <title>财税专家UC</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link href="${ctx}/css/gloab.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body style="min-width: 0px;">
<!-- head -->
<#include "common/top.ftl">
<!-- head end -->
<!-- main -->
<div class="w_1200 grzx_main" style="height:600px; margin-top:15px;">
    <div class="login-box f-mt10 f-pb50">
        <div class="main bgf">
            <div class="reg-box-pan display-inline">
                <div class="step">
                    <ul>
                        <li class="col-xs-4 on">
                            <span class="num"><em class="f-r5"></em><i>1</i></span>
                            <span class="line_bg lbg-r"></span>
                            <p class="lbg-txt">填写账户信息</p>
                        </li>
                        <li class="col-xs-4">
                            <span class="num"><em class="f-r5"></em><i>2</i></span>
                            <span class="line_bg lbg-l"></span>
                            <span class="line_bg lbg-r"></span>
                            <p class="lbg-txt">验证账户信息</p>
                        </li>
                        <li class="col-xs-4">
                            <span class="num"><em class="f-r5"></em><i>3</i></span>
                            <span class="line_bg lbg-l"></span>
                            <p class="lbg-txt">密码修改成功</p>
                        </li>
                    </ul>
                </div>
                <div class="reg-box" id="verifyCheck" style="margin-top:20px;">
                    <div class="part1">
                        <div class="item">
                            <span class="intelligent-label f-fl" style="float: left"><b class="ftx04">*</b>手机号：</span>
                            <div class="f-fl item-ifo" style="float: left">
                                <input type="text" class="txt03 f-r3 required" style="height: 35px;" keycodes="tel" tabindex="2" data-valid="isNonEmpty||isPhone" data-error="手机号码不能为空||手机号码格式不正确" maxlength="11" id="phone" />
                                <label class="focus valid error" id="errorphonets"></label>
                            </div>
                        </div>

                        <div class="item">
                            <span class="intelligent-label f-fl" style="float: left"><b class="ftx04">*</b>验证码：</span>
                            <div class="f-fl item-ifo" style="float: left">
                                <input type="text" maxlength="4" class="txt03 f-r3 f-fl required"  tabindex="4" style="width:60%;height: 35px;" id="randCode" data-valid="isNonEmpty" data-error="验证码不能为空" />
                                <span class="ie8 icon-close close hide"></span>
                                <label class="f-size12 c-999 f-fl f-pl10 f-m001">
                                    <img src="validatecode.html" id="yzmimg" />
                                </label>
                                <label class="icon-sucessfill blank hide" style="left:380px"></label>
                                <label class="focusa style-480-tupian">看不清？<a href="javascript:;" class="c-blue">换一张</a></label>
                                <label class="focus valid" style="left:370px"></label>
                                <label class="focus valid error" id="errorts" style="left:370px"></label>
                            </div>
                        </div>

                        <div class="item">
                            <span class="intelligent-label f-fl"></span>
                            <div class="f-fl item-ifo">
                                <button class="btn btn-blue f-r3" id="btn_part1">下一步</button>
                            </div>
                        </div>
                    </div>
                    <div class="part2" style="display:none">
                        <div class="alert alert-info alert-481">请输入短信中的验证码，确保您的手机号真实有效。</div>
                        <div class="item col-xs-12 f-mb10" style="height:auto">
                            <span class="intelligent-label f-fl" style="float: left;">手机号：</span>
                            <div class="f-fl item-ifo c-blue" id="shoujihao">
                                15824450934
                            </div>
                        </div>
                        <div class="item col-xs-12">
                            <span class="intelligent-label f-fl" style="float: left;"><b class="ftx04">*</b>验证码：</span>
                            <div class="f-fl item-ifo"style="float: left;">
                                <input type="text" maxlength="6" id="verifyNo" class="txt03 f-r3 f-fl required style-480-yanzheng" tabindex="4" style="width:167px; float: left;height: 38px;" />
                                <input type="button" class="btn btn-default fsyzm" id="yzm" type-name="忘记密码" value="发送验证码" style="margin-left: 10px; width: 100px;">
                                <div id="erroryzms" style="left:370px; color: #F34948;"></div>
                                <span class="focus"><span>请查收手机短信，并填写短信中的验证码（此验证码5分钟内有效）</span></span>
                            </div>

                        </div>
                        <div class="item col-xs-12">
                            <span class="intelligent-label f-fl">&nbsp;</span>
                            <div class="f-fl item-ifo">
                                <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part2">下一步</a>
                            </div>
                        </div>
                    </div>
                    <div class="part3" style="display:none">
                        <div class="item col-xs-12" style="height:80px;">
                            <span class="intelligent-label f-fl" style="float: left"><b class="ftx04">*</b>新密码：</span>
                            <div class="f-fl item-ifo" style="float: left">
                                <input type="password" id="password" maxlength="16" class="txt03 f-r3 required" tabindex="3" style="ime-mode:disabled;height: 35px;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||level:2" data-error="密码不能为空||密码长度6-20位||该密码太简单，有被盗风险，建议字母+数字的组合" />
                                <div id="password1" style="color:red;"></div>
                                <span class="focus style-480-focus">密码必须由8-16位数字、字母、下划线中至少两种组成</span>
                                <span class="clearfix"></span>
                            </div>

                        </div>
                        <div class="item col-xs-12">
                            <span class="intelligent-label f-fl" style="float: left"><b class="ftx04">*</b>确认新密码：</span>
                            <div class="f-fl item-ifo" style="float: left">
                                <input type="password" maxlength="16" class="txt03 f-r3 required" tabindex="4" style="ime-mode:disabled; height: 35px;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-16||isRepeat:password" data-error="密码不能为空||密码长度6-16位||两次密码输入不一致" id="rePassword" />
                                <div id="password2" style="color:red;"></div>
                                <span class="focus style-480-focus style-480-left">请再输入一遍上面的密码</span>
                            </div>

                        </div>
                        <div class="item col-xs-12">
                            <span class="intelligent-label f-fl">&nbsp;</span>
                            <div class="f-fl item-ifo">
                                <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part3">修改密码</a>
                            </div>
                        </div>
                    </div>
                    <div class="part4 text-center" style="display:none; margin-top:100px;">
                        <h3>恭喜<span id="phones"></span>，您已成功修改密码<br><br><span style="color:#C9302C">现在开始您的财税之旅吧！</span></h3>
                        <p class="c-666 f-mt30 f-mb50">页面将在 <strong id="times" class="f-size18">5</strong> 秒钟后，跳转到 <a href="${ctx}/login" class="c-blue">用户登陆</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="m-sPopBg" style="z-index:998;"></div>
    <div class="m-sPopCon regcon">
        <div class="m-sPopTitle"><strong>服务协议条款</strong><b id="sPopClose" class="m-sPopClose" onClick="closeClause()">×</b></div>
        <div class="apply_up_content">
    	<pre class="f-r0">
		<strong>同意以下服务条款，提交注册信息</strong>
        </pre>
        </div>
        <center><a class="btn btn-blue btn-lg f-size12 b-b0 b-l0 b-t0 b-r0 f-pl50 f-pr50 f-r3" href="javascript:closeClause();">已阅读并同意此条款</a></center>
    </div>

</div>
<!-- main end -->
<!-- footer  -->
<#include "common/bottom.ftl">
<script data-main="${ctx}/js/abc/forgot" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>