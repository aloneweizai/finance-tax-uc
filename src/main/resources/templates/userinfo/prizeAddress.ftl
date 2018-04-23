<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<link href="${ctx}/js/lib/layui/css/layui.css" rel="stylesheet" type="text/css">

<html>
<#include "../common/meta.ftl">
<body>
<div id="linjiang" style="padding: 10px;">
    <form class="layui-form" action="">
        <table class="layui-table">
            <tr>
                <th>活动名称：</th>
                <td>${(lotteryLogBo.activityName)!}</td>
            </tr>
            <tr>
                <th>活动开始时间：</th>
                <td>${(lotteryLogBo.activityStartTime?string("yyyy-MM-dd"))!}</td>
            </tr>
            <tr>
                <th>活动结束时间：</th>
                <td>${(lotteryLogBo.activityEndTime?string("yyyy-MM-dd"))!}</td>
            </tr>
        </table>
        <a href="${ctx}/userinfo/useraddr.html">添加地址</a>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 88px">收货地址</label>
            <div class="layui-input-block">
                <#if addressRes?? && ( addressRes?size gt 0 )>
                    <#list addressRes as userAddressBO>
                            <input id="radio1" type="radio" name="sex" <#if userAddressBO.isDefault?? && userAddressBO.isDefault>checked</#if> value="${userAddressBO.id!}" phone ="${userAddressBO.phone!""}" data-name="${userAddressBO.name!}" title="${userAddressBO.name!}&nbsp;&nbsp;&nbsp;&nbsp;${userAddressBO.provinceName!}${userAddressBO.cityName!}${userAddressBO.areaName!}${userAddressBO.detail!}" addr="${userAddressBO.provinceName!}${userAddressBO.cityName!}${userAddressBO.areaName!}${userAddressBO.detail!}">
                    </#list>
                </#if>

            </div>
        </div>
    </form>
</div>


</body>

<script  src="${ctx}/js/lib/layui/layui.all.js"></script>


</html>
</@compress>