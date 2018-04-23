<@compress single_line=true>
<#if data??>
<table class="layui-table" lay-size="sm">
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">社会信用代码:</td>
        <td colspan="2">${data.shxydm!}</td>
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
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">税务登记时间:</td>
        <td colspan="2">${(data.djrq?string('yyyy-MM-dd'))!""}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">软件到期日:</td>
        <td colspan="2">${(data.expireTime?string('yyyy-MM-dd'))!""}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">软件延期到期日:</td>
        <td colspan="2">${(data.expandExpireTime?string('yyyy-MM-dd'))!""}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">绑定时间:</td>
        <td colspan="2">${(data.createTime?string('yyyy-MM-dd'))!}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">分组:</td>
        <td colspan="2">${data.bdgroup!""}</td>
    </tr>
    <tr style="line-height: 40px;">
        <td style="text-align: right; padding-right: 10px;">备注:</td>
        <td colspan="2">${data.remark!""}</td>
    </tr>
</table>
</#if>
</@compress>