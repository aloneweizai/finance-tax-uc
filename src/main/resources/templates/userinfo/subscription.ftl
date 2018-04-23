<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8" />
    <title>消息订阅</title>
    <#include "../common/meta.ftl">
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style>
    .style1 .layui-unselect i{
        border: 1px solid #00bcd4 !important;
    }
    .style2 .layui-unselect i{
        border: 1px solid #ccc !important;
    }
    .style3 .layui-unselect i{
        border: 1px solid #00bcd4 !important;
    }
    .style4 .layui-unselect i{
        border: 1px solid #ccc !important;
        background: #ccc;
    }
    .tip{
        cursor: pointer;
        color: #999;
        margin-left: 10px;
    }
</style>

<body>
<div class="grzx_main_rt">我的消息</div>
<div class="grzx_main_rt_nav">
    <ul>
        <li msg-id="wd">未读消息</li>
        <li msg-id="xt">系统通知 </li>
        <li msg-id="yh">用户提醒</li>
        <li msg-id="ss">涉税提醒</li>
        <li class="hover" msg-id="dy">消息订阅</li>
        <div class="clear"></div>
    </ul>
</div>

<div class="layui-form">
    <div style="float: right;margin:0 0px 10px 0">
        <button class="layui-btn layui-btn-primary layui-btn-sm reset" >重置</button>
        <button class="layui-btn layui-btn-sm confirm">确认</button>
    </div>
    <p style="margin-top: 20px;">注：有些消息是需会员才能订阅的！</p>
    <table class="layui-table layui-form" lay-skin="line" >
        <colgroup>
            <col width="31%">
            <col width="23%">
            <col width="23%">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>消息类型</th>
            <th>站内信</th>
            <th>微信</th>
            <th>短信</th>
        </tr>
        </thead>

        <#if subes??>
            <#list subes?keys as key>
                <#assign sub = subes[key]>
                    <tr class="dy_dalei_title">
                        <td class="dy_dalei" colspan="4">
                            <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
                            <#if msgTypes??>
                                <#list msgTypes as msgType>
                                     <#if key == msgType.fieldValue>
                                        ${msgType.fieldKey!}
                                     </#if>
                                </#list>
                            </#if>
                        </td>
                    </tr>
                    <tr class="dy_zilei_tr">
                        <td colspan="4" class="dy_zilei">
                            <table class="layui-table" lay-skin="line" style="margin: 0; border: 0;">
                                <colgroup>
                                    <col width="31%">
                                    <col width="23%">
                                    <col width="23%">
                                    <col>
                                </colgroup>
                                <#list sub as su>
                                    <tr class="rowSub" suid="${su.id!}" userid ="${su.userId!}" settingid="${su.settingId!}" web="${su.web?string}" wechat="${su.wechat?string}" message="${su.message?string}">
                                        <td>&nbsp; &nbsp; &nbsp;
                                            <#if busiTypes??>
                                                <#list busiTypes as busiType>
                                                    <#if su.busiType == busiType.fieldValue>
                                                        ${busiType.fieldKey!}
                                                            <#if su.remark?? &&  su.remark != "">
                                                              <i class="iconfont tip" remark="${su.remark}">&#xe813;</i>
                                                            </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </td>
                                    <td
                                        <#if su.web == false>
                                        <#assign webCheck = false>
                                            <#if su.hasWeb == true && su.webForce==false>
                                                <#assign webDisable = false>
                                                class="web style1"
                                            <#else>
                                                <#assign webDisable = true>
                                                class="web style2"
                                            </#if>
                                        <#else>
                                            <#assign webCheck = true>
                                            <#if su.hasWeb == true && su.webForce==false>
                                                <#assign webDisable = false>
                                                class="web style3"
                                            <#else>
                                                <#assign webDisable = true>
                                                class="web style4"
                                            </#if>
                                        </#if>>
                                            <input type="checkbox" name="" title="" lay-skin="primary" <#if webCheck>checked</#if> <#if webDisable>disabled</#if>>
                                        </td>

                                    <td
                                        <#if su.wechat == false>
                                            <#assign wechatCheck = false>
                                            <#if su.hasWechat == true && su.wechatForce==false>
                                                <#assign wechatDisable = false>
                                                    class="wechat style1"
                                            <#else>
                                                <#assign wechatDisable = true>
                                                    class="wechat style2"
                                            </#if>
                                        <#else>
                                            <#assign wechatCheck = true>
                                            <#if su.hasWechat == true && su.wechatForce==false>
                                                <#assign wechatDisable = false>
                                                    class="wechat style3"
                                            <#else>
                                                <#assign wechatDisable = true>
                                                    class="wechat style4"
                                            </#if>
                                        </#if>>
                                        <input type="checkbox" name="" title="" lay-skin="primary" <#if wechatCheck>checked</#if> <#if wechatDisable>disabled</#if>>
                                    </td>


                                        <td
                                            <#if su.message == false>
                                                <#assign messageCheck = false>
                                                <#if su.hasMessage == true && su.messageForce==false>
                                                    <#assign messageDisable = false>
                                                        class="msg style1"
                                                <#else>
                                                    <#assign messageDisable = true>
                                                        class="msg style2"
                                                </#if>
                                            <#else>
                                                <#assign messageCheck = true>
                                                <#if su.hasMessage == true && su.messageForce==false>
                                                    <#assign messageDisable = false>
                                                        class="msg style3"
                                                <#else>
                                                    <#assign messageDisable = true>
                                                        class="msg style4"
                                                </#if>
                                            </#if>>
                                            <input type="checkbox" name="" title="" lay-skin="primary" <#if messageCheck>checked</#if> <#if messageDisable>disabled</#if>>
                                        </td>
                                       <#-- <td class ="web">
                                            <input type="checkbox" name="" title="" lay-skin="primary" <#if su.web==true>checked</#if> <#if su.webForce==true || su.hasWeb == false>disabled="" </#if>>
                                        </td>
                                        <td class="wechat"><input type="checkbox" name="" title="" lay-skin="primary" <#if su.wechat==true>checked</#if> <#if su.wechatForce==true || su.hasWechat == false>disabled="" </#if>></td>
                                        <td class ="msg"><input type="checkbox" name="" title="" lay-skin="primary" <#if su.message==true>checked</#if> <#if su.messageForce==true|| su.hasMessage == false>disabled="" </#if>></td>-->
                                    </tr>
                                </#list>
                            </table>
                        </td>
                    </tr>
            </#list>
        </#if>
    </table>
</div>
<script data-main="${ctx}/js/abc/subscription" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>