<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "../../common/meta.ftl">
    <title>我的关注</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
</head>
<body>
<div class="grzx_main_rt">我的关注</div>
<div class="grzx_main_wdgz">
    <ul>
        <#if follows?? && (follows?size>0)>
            <#list follows as follow>
                <li>
                    <div class="grzx_main_wdgz_gz js_cancel_follow" style="width:75px" title="取消关注" data-href="${ctx}/help/my/follow/cancel/${follow.followedUserId}.json">
                        <div class="yuan bg_orange iconfont icon-xingxing"></div>已关注
                    </div>
                    <div class="grzx_main_wdgz_sx" style="width:55px"><a href="#"><div class="yuan bg_blue iconfont icon-xinxi1"></div>私信</a></div>
                    <div class="grzx_main_wdgz_tx"><img src="${follow.userPicturePath}"></div>
                    <div class="grzx_main_wdgz_text">
                        <h3>${follow.followName}</h3>回答 ${follow.answerCount} | 提问${follow.askCount} | 粉丝 ${follow.fansCount}</div>
                    <div class="clear"></div>
                </li>
                <#if (follow_index+1)%2 ==0>
                    <div class="clear"></div>
                </#if>
            </#list>
        </#if>
    </ul>
</div>
<div class="clear"></div>
<#include "../../common/pagination.ftl">
</body>
<script data-main="${ctx}/js/abc/help/follow/list" src="${ctx}/js/require.js"></script>
</html>
