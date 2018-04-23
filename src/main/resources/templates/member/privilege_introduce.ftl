<@compress single_line=true>
<#assign ctx =request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "../common/meta.ftl">
    <title>我是会员</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">var ctx="${ctx}"</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/MeCes.css">
</head>
<body>
<div class="MeCes-box">
    <div class="top-box">
        <h3>会员专属特权信息
            <a href="${ctx}/member/my_index.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我是会员</a>
        </h3>
        <ul>
            <#if privilegeList?? && (privilegeList?size>0)>
                <#list privilegeList as privilege>
                    <#assign flag = false>
                    <li>
                        <#if levels?? && (levels?size > 0)>
                            <#list levels as level>
                                <#if level.privilegeId?? && level.privilegeId == privilege.code>
                                    <#assign flag = true>
                                    <#if vipLevel.levelCode != "VIP0" && (vipExpireDate < 0)>
                                        <#assign flag = false>
                                        <#if ordinarys ?? && (ordinarys?size>0)>
                                            <#list ordinarys as ordinary>
                                                <#if ordinary.privilegeId == level.privilegeId >
                                                    <#assign flag = true>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
                        <a href="${(privilege.id)!""}">
                            <div>
                                <#if flag>
                                    <i class="iconfont ${privilege.icon!""}"></i>
                                <#else>
                                    <i class="iconfont ${privilege.icon!""}" style="color: #808080"></i>
                                </#if>
                                <p>${privilege.name!""}</p>
                            </div>
                        </a>
                    </li>
            </#list>
        </#if>
        </ul>
    </div>
    <div class="bottom-box">
    <#if vipPrivilege??>
        <div class="zhuanshutequan">
            <p id="vipName">${(vipPrivilege.name)!""}</p>
            <p id="vipDesc">${(vipPrivilege.description)!""}</p>
        </div>
    </#if >
    </div>
   <p>注：会员特权最终解释权归财税专家软件服务商所有</p>
</div>
</body>

<script data-main="${ctx}/js/abc/member/my_index" src="${ctx}/js/require.js"></script>
</html>
</@compress>