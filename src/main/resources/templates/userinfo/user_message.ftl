<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<head>
    <title>我的消息</title>
    <#include "../common/meta.ftl">
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>

<body>
<div class="grzx_main_rt">我的消息</div>
<div class="grzx_main_rt_nav">
    <ul>
        <li <#if type=="4">class="hover"</#if> msg-id="wd">未读消息</li>
        <li <#if type=="1">class="hover"</#if> msg-id="xt">系统通知 </li>
        <li <#if type=="2">class="hover"</#if> msg-id="yh">用户提醒</li>
        <li <#if type=="3">class="hover"</#if> msg-id="ss">涉税提醒</li>
        <li <#if type=="5">class="hover"</#if> msg-id="dy">消息订阅</li>
        <div class="clear"></div>
    </ul>
</div>
<!-- 个人中心内容右侧 -->
<#if type =="4">
    <div id="fanye" class="grzx_wdxx_xtxx page_main_xxk layui-form">
        <!-- 3种状态对应的四种图标及颜色 class -->
        <table class="layui-table"  lay-skin="line">
        <#if messages?? && (messages?size > 0)>
        <#list messages as message>
            <tr>
                <td><input class="js_checkbox" type="checkbox" name="xz" value="${message.id!}" lay-skin="primary"  title="" ></td>
                <td><div class="iconfont icon-laba color_b grzx_wdxx_bbxx_l "></div></td>
                <td><div class="grzx_wdxx_bbxx_r" style="width:700px">
                    <#assign  bt="">
                    <#if busiTypes??>
                        <#list  busiTypes as busi>
                            <#if message.busiType?? && message.busiType == busi.fieldValue>
                                <#assign  bt=busi.fieldKey!>
                            <#else>
                            </#if>
                        </#list>
                    </#if>
                    <#if bt =="">
                        <#if msgTypes??>
                            <#list  msgTypes as msgType>
                                <#if message.type == msgType.fieldValue>
                                    <#assign  bt=msgType.fieldKey!>
                                </#if>
                            </#list>
                        </#if>
                    </#if>
                    ${bt!}:

                    <#if message.createTime??>
                       <samp>${message.createTime!""}</samp>
                    <#else>
                        <samp>${message.lastUpdate!""}</samp>
                    </#if>
                <#if message.status =="1"><#--未读-->
                    <span class="news">新</span>
                </#if>
                <h4 <#if !message.url??||message.url=="">class="danhang bsMessage " message-id="${message.id!}"  msgstatus ="${message.status!}" msg-type="${message.type!""}" style="cursor: pointer;"<#else>class="danhang"</#if>>
                 ${message.content!""}
                    <span <#if message.url??>class="links" message-id="${message.id!}"  msgstatus ="${message.status!}" msg-type="${message.type!""}" style="cursor: pointer;"</#if>>${message.url!""}</span>
                </h4>
                <div  class="xtfr fr" content="${message.content!}" <#if message.url??>msg-url="${message.url?replace("\"","\'")}"<#else>msg-url="${message.url!}"</#if> style="visibility: hidden">【查看全部】</div>
                </div></td>
            </tr>
        </#list>
        </#if>
        </table>
    </div>
  <#else>
    <div id="fanye" class="grzx_wdxx_xtxx page_main_xxk layui-form">
        <!-- 3种状态对应的四种图标及颜色 class -->
        <table class="layui-table"  lay-skin="line">
            <#if messages?? && (messages?size > 0)>
                <#list messages as message>
                    <tr>
                        <td><div class="iconfont icon-laba color_b grzx_wdxx_bbxx_l "></div></td>
                        <td><div class="grzx_wdxx_bbxx_r" style="width:700px">
                            <#assign  bt="">
                            <#if busiTypes??>
                                <#list  busiTypes as busi>
                                    <#if message.busiType?? && message.busiType == busi.fieldValue>
                                        <#assign  bt=busi.fieldKey!>
                                    <#else>
                                    </#if>
                                </#list>
                            </#if>
                            <#if bt =="">
                                <#if msgTypes??>
                                    <#list  msgTypes as msgType>
                                        <#if message.type == msgType.fieldValue>
                                            <#assign  bt=msgType.fieldKey!>
                                        </#if>
                                    </#list>
                                </#if>
                            </#if>
                            ${bt!}:

                            <#if message.createTime??>
                                <samp>${message.createTime!""}</samp>
                            <#else>
                                <samp>${message.lastUpdate!""}</samp>
                            </#if>
                            <#if message.status =="1"><#--未读-->
                                <span class="news">新</span>
                            </#if>
                            <h4 <#if !message.url??||message.url=="">class="danhang bsMessage " message-id="${message.id!}"  msgstatus ="${message.status!}" msg-type="${message.type!""}" style="cursor: pointer;"<#else>class="danhang"</#if>>
                            ${message.content!""}
                                <span <#if message.url??>class="links" message-id="${message.id!}"  msgstatus ="${message.status!}" msg-type="${message.type!""}" style="cursor: pointer;"</#if>>${message.url!""}</span>
                            </h4>
                            <div  class="xtfr fr" content="${message.content!}" <#if message.url??>msg-url="${message.url?replace("\"","\'")}"<#else>msg-url="${message.url!}"</#if> style="visibility: hidden">【查看全部】</div>
                        </div></td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</#if>

<div type ="${type!}" class="layui-input-block" style="margin-left: 0px">
    <#if type=="4">
        <div class="js_selectAll layui-btn">全选</div>
        <button id ="pageRead" index="${page}" msg-type ="${type!}" class="layui-btn layui-btn-sm">标记已读</button>
    <#-- <button id ="allRead"type ="${type!}" class="layui-btn layui-btn-sm">全部已读</button>-->
    <#else>
       <#-- <button id ="delete" type ="${type!}" class="layui-btn layui-btn-sm">删除</button>-->
    </#if>
</div>
<div id="demo" index="${page}" count="${count}" msg-type ="${type!}" style="height: 50px;text-align: center;"></div>

<script data-main="${ctx}/js/abc/user_info_mess" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>
