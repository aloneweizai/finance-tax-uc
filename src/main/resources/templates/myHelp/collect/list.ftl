<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <#include "../../common/meta.ftl">
    <title>我的收藏</title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<div class="grzx_main_rt">我的收藏</div>
<div class="grzx_main_wdsc">
    <ul>
        <#if collects?? && (collects?size>0)>
            <#list collects as collect>
                <li>
                    <div class="grzx_main_wdgz_sx js_cancel_collect " style="width: 75px" title="取消收藏" data-href="${ctx}/help/my/collect/cancel/${collect.askId}.json">
                        <div class="yuan bg_blue iconfont icon-xingxing"></div>
                        取消收藏
                    </div>
                    <div class="grzx_main_wdgz_tx">
                        <img src="${collect.userPicturePath}">
                    </div>
                    <div class="fl">
                        <h3>${collect.ask}</h3> ${collect.collectTime?string("yyyy-MM-dd mm:ss")} 回答：${collect.answerCount} 点赞：${collect.supportCount} 提问者：${collect.createUserName}
                    </div>
                    <div class="clear"></div>
                </li>
            </#list>
        </#if>
    </ul>
</div>
<#include "../../common/pagination.ftl">
</body>
<script data-main="${ctx}/js/abc/help/collect/list" src="${ctx}/js/require.js"></script>
</html>
