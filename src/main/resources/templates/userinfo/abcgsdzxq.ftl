<@compress single_line=true>
<#if data??>
<table class="layui-table" lay-size="sm">
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">办税人员类型:</td>
        <td colspan="2">
            ${data.bsy!""}
        </td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">纳税号:</td>
        <td colspan="2">${data.nsrsbh!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">纳税人名称:</td>
        <td colspan="2">${data.nsrmc!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">所属税务机关:</td>
        <td colspan="2">${data.swjgMc!}</td>
    </tr>
    <#--<tr style="line-height: 40px;">-->
        <#--<td style="text-align: right; padding-right: 10px;">实名办税核验状态:</td>-->
        <#--<td colspan="2">${data.smrzzt!}</td>-->
    <#--</tr>-->
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">绑定时间:</td>
        <td colspan="2">${(data.createTime?string('yyyy-MM-dd'))!}</td>
    </tr>
</table>
</#if>
</@compress>