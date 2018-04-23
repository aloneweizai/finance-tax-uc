<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "../../common/meta.ftl">
    <title>我的粉丝</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
</head>
<body>
<div class="grzx_main_rt">我的粉丝</div>
<div class="grzx_main_wdgz">
    <ul>
        <#if fans?? && (fans?size>0)>
            <#list fans as fan>
                <li> <div class="grzx_main_wdgz_gz"><a href="#"><div class="yuan bg_blue iconfont icon-xinxi1"></div>私信</a>
                </div>
                    <#if fan.isFollowed>
                        <div class="grzx_main_wdgz_sx js_cancel_follow" title="取消关注" style="width:75px" data-href="/help/my/follow/cancel/${fan.followerUserId}.json">
                            <div class="yuan bg_orange iconfont icon-xingxing"></div>已关注
                        </div>
                    </#if>
                    <div class="grzx_main_wdgz_tx"><img src="${fan.userPicturePath!}"></div>
                    <div class="grzx_main_wdgz_text">
                        <h3>${fan.followerName!}</h3>回答 ${fan.followerAnswerCount!} | 提问${fan.followerAskCount!} | 粉丝 ${fan.followerFansCount!}</div>
                    <div class="clear"></div>
                </li>
                <#if (fan_index+1)%2 ==0>
                    <div class="clear"></div>
                </#if>
            </#list>
        </#if>
    </ul>
</div>
<div class="clear"></div>
<#include "../../common/pagination.ftl">
</body>
<script data-main="${ctx}/js/abc/help/fans/list" src="${ctx}/js/require.js"></script>
</html>
