<@compress single_line=true>
<#assign ctx =request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>我是会员</title>
    <#include "../common/meta.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">var ctx ="${ctx}"</script>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <style>
        td{font-size: 12px;}
        td:first-child{text-align: center;}
        td:last-child{text-align: center;}
        span:first-child{width: 50%;text-align: right}
        span:last-child{width: 50%;text-align: left}
        img{width: 80px;height: 80px}
    </style>
</head>
<body>
<div class="huiyuan_title">用户等级规则
    <a href="${ctx}/user_index.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>个人中心</a>
</div>
<div class="clear"></div>
<p style='line-height: 22px;
margin-bottom: 10px' class="color_b">*您在财税平台的等级取决于经验值的多少，随着经验值的增加，等级会随之提升，各等级都会有对应的头衔，等级越高享有的等级特权越大，同时表明您的财税水平越高。以下为各等级所需经验值及对应头衔:</p>
<table class="layui-table table table-bordered">
    <tbody>
    <!--实习生-->
    <tr class="tr_tdtitle">
        <td>等级</td>
        <td align="center">名称</td>
        <td>经验值</td>
    </tr>
    <#assign colum = 1>
    <#assign medal = "medal_0">
    <#if levelList?? && (levelList?size>0)>
        <#list levelList as level>
        <tr>
            <td>${level.name!""}</td>
            <#if level.medal != medal>
                <#assign medal = level.medal >
                <#assign num = 1>
                <#if medals ?? && (medals?size>0)>
                    <#list medals as map>
                        <#list map?keys as itemKey>
                            <#if itemKey== level.medal && num == 1>
                                <td rowspan="${map[itemKey]}"><span><img  src="${ctx}/images/${level.medalIcon!"medal_1"}.png"></span><span>${level.medal!"实习生1级"}</span></td>
                                <#assign num = num + 1>
                            </#if>
                        </#list>
                    </#list>
                </#if>
            </#if>
            <#--<td><span><img  src="${ctx}/images/${level.medalIcon!"medal_1"}.png"></span><span>${level.medal!"实习生1级"}</span></td>-->
            <td>${level.minValue}~${level.maxValue}</td>
        </tr>
        </#list>
    </#if>

    </tbody>
</table>
<script data-main="${ctx}/js/abc/level_rule" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>