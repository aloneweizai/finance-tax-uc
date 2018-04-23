<@compress single_line=true>
<table class="layui-table" lay-size="sm">
    <tr>
        <th width="6%">序号</th>
        <th width="12%">社会信用代码</th>
        <th width="12%">纳税号</th>
        <th width="12%">纳税人名称</th>
        <#--<th width="12%">所属税务机关</th>-->
        <#--<th width="12%">税务登记时间</th>-->
        <th width="11%">软件到期日</th>
        <th width="14%">软件延期到期日</th>
        <th width="14%">分组</th>
        <#--<th width="11%">绑定时间</th>-->
        <th width="12%">操作</th>
    </tr>
<#if userdzsb.dataList??&&(userdzsb.dataList?size>0)>
    <#list userdzsb.dataList as data>
        <tr>
            <td><#if page!=0>${15*(page-1)+data_index+1}<#else>${data_index+1}</#if></td>
            <td>${data.shxydm!}</td>
            <td>${data.nsrsbh!}</td>
            <td><a href="javascript:;" type="dzsb" dzsb-id="${data.id!""}">${data.nsrmc!}</a></td>
            <#--<td>${data.swjgMc!}</td>-->
            <#--<td>-->
                <#--${(data.expireTime?string('yyyy-MM-dd'))!""}-->
            <#--</td>-->
            <td>
                <#if (data.expireTime?date)?? &&((data.expireTime?date) gt (.now?date))>
                    ${(data.expireTime?string('yyyy-MM-dd'))!""}
                <#else>
                    <span style="color: red;">${(data.expireTime?string('yyyy-MM-dd'))!""}</span>
                </#if>
            </td>
            <td>${(data.expandExpireTime?string('yyyy-MM-dd'))!""}</td>
            <td>${data.bdgroup!""}</td>
            <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
            <td><a href="javascript:;" data-type="dzsb" data-id="${data.id!}" name="del" data-msg="ABC电子申报"><i class="iconfont"></i> 解绑</a>|
                <a href="javascript:;" type="update" nsrsbh="${data.nsrsbh!}"><i class="iconfont"></i> 更新</a>|
                <a href="javascript:;"  id="qyxf" nsrsbh="${data.nsrsbh!}"><i class="iconfont"></i> 续费</a>|
            <a href="javascript:;" group="bd" nsrsbh="${data.id!}" fz-value="${data.bdgroup!""}"><i class="iconfont"></i> 分组</a></td>
        </tr>
    </#list>
</#if>
</table>
<div id="demo0" index="${page}" count="${count}" style="text-align: right;"></div>
</@compress>
