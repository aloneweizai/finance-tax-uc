<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "../../common/meta.ftl">
    <title>我的私信</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
</head>
<body>
<div class="grzx_main_rt">我的私信</div>
<!-- 我的关注 -->
<div class="grzx_main_wdsx">
    <ul>
        <#if privateMsgs?? && (privateMsgs?size>0)>
            <#list privateMsgs as privateMsg>
                <li>
                    <div class="grzx_main_wdsx_tx">
                        <img src="${privateMsg.senderPicturePath}">
                    </div>
                    <div class="grzx_main_wdsx_nametime">
                        <span>${privateMsg.senderName}</span>${privateMsg.createTime?string("yyyy-MM-dd HH:mm")}<a class="news">新</a>
                    </div>
                    <div class="clear"></div>
                    <div class="grzx_main_wdsx_xx">${privateMsg.content}</div>
                    <div class="grzx_main_wdsx_hf" data-toggle="modal" data-target="#myModal">回 复</div>
                </li>
            </#list>
        </#if>
    </ul>
</div>
<div class="clear"></div>
<#include "../../common/pagination.ftl">

<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">回复</h4>
            </div>
            <div class="modal-body">
                <div class="grzx_main_bjgrzl">
                    <textarea rows="3" cols="73"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/help/follow/list" src="${ctx}/js/require.js"></script>
</html>
