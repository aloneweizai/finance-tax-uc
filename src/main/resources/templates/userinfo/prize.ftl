<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#include "../common/meta.ftl">
    <title>我的中奖纪录</title>
    <link href="${ctx}/js/lib/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <#--<link href="${ctx}/css/easyui.css" rel="stylesheet" type="text/css">-->

    <script type="text/javascript">
        var ctx = "${ctx}";


    </script>
<style>
    .jinDanUl{
        display: none;
    }

</style>
</head>

<body>
<input id = "smrz" type="hidden" value="${(usertzxx.validStatus)!}">
    <div class="grzx_main_rt">
        我的中奖纪录
    </div>
    <form>
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline fr">
                    <label class="layui-form-label" style="width:100px;">选择日期${(lotteryLogRq.startTime?string("yyyy-MM-dd"))!}</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test29" value="${datel!}"  >
                    </div>
                    <div class="layui-input-inline" style="width: 10px; margin-right: 5px;">-</div>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test30"  value="${dater!}"   >
                    </div>
                    <a  href="javascript:void(0)" class="layui-btn" id="chaxun">查询</a>
                </div>
            </div>
        </div>
    </form>

    <div class="grzx_wdjf_dhjl page_main_xxk">
        <table class="layui-table" lay-size="sm">
            <thead>
            <tr>
                <th>奖品名称</th>
                <th>奖品等级</th>
                <th>活动名称</th>
                <th>中奖时间</th>
                <th>领奖截止时间</th>
                <th>奖品状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            <#if logRs?? && ( logRs?size gt 0 )>
                <#list logRs as list>
                <tr>
                    <td>${(list.lotteryName)!}</td>
                    <td>${(list.lotteryLevel)!} </td>
                    <td>${(list.activityName)!} </td>
                    <td> ${(list.createTime?string("yyyy-MM-dd"))!}</td>
                    <td> ${(list.endlqDate?string("yyyy-MM-dd"))!}</td>
                    <td> ${(list.state)!}</td>
                    <td data-id = "${(list.id)!}">
                        <#if list.state?? >
                            <#if   list.state=="未领取">
                                <#if list.lotterySend?? && list.lotterySend >

                                    <button  phone ="${list.phone!""}" data-method="lingqu" class="layui-btn layui-btn-mini">领取</button>
                                <#else >
                                    <button  phone ="${list.phone!""}" data-method="virtual" class="layui-btn layui-btn-mini">领取</button>
                                </#if>

                            <#elseif list.state="已过期">
                                ---
                            <#else >
                                <button id="layerDemo" phone ="${list.phone!""}" data-method="abc" class="layui-btn layui-btn-primary layui-btn-mini">查看</button>
                            </#if>
                        <#else >
                            <button id="layerDemo"  phone ="${list.phone!""}" data-method="abc" class="layui-btn layui-btn-primary layui-btn-mini">查看</button>
                        </#if>
                    </td>
                </tr>


                </#list>
            </#if>

            </tbody>
        </table>
    </div>


    <script data-main="${ctx}/js/abc/myLottery" src="${ctx}/js/require.js"></script>

</body>
</html>
</@compress>