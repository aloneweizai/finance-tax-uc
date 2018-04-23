<@compress single_line=true>
<#if data??>
<table class="layui-table" lay-size="sm">
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">社会信用代码:</td>
        <td colspan="2">${data.shxydm!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">用户名:</td>
        <td colspan="2">${data.username!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">子用户:</td>
        <td colspan="2">${data.subuser!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">纳税人名称:</td>
        <td colspan="2">${data.nsrmc!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">所属税务机关:</td>
        <td colspan="2">${data.swjgmc!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">绑定时间:</td>
        <td colspan="2">${(data.createTime?string('yyyy-MM-dd'))!}</td>
    </tr>
</table>
</#if>
</@compress>