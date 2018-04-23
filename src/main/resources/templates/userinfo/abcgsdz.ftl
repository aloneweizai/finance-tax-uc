<@compress single_line=true>
<table class="layui-table" lay-size="sm">
    <tr>
        <th width="6%">序号</th>
        <th width="12%">办税人员类型 </th>
        <th width="12%">纳税号</th>
        <th width="12%">纳税人名称</th>
        <th width="11%">所属税务机关</th>
        <#--<th width="14%">实名办税核验状态</th>-->
        <#--<th width="11%">绑定时间</th>-->
        <th width="10%">操作</th>
    </tr>
<#if userhngs.dataList??&&(userhngs.dataList?size>0)>
    <#list userhngs.dataList as data>
        <tr>
            <td><#if page1!=0>${15*(page1-1)+data_index+1}<#else>${data_index+1}</#if></td>
            <td>
                <#if data.roleId??&&data.roleId=='R0001'>
                    办税员01
                <#elseif data.roleId??&&data.roleId=='R0002'>
                    办税员02
                <#elseif data.roleId??&&data.roleId=='R0003'>
                    办税员03
                </#if>
            </td>
            <td>${data.nsrsbh!}</td>
            <td><a href="javascript:;" type="gsdz" gsdz-id="${data.id!""}">${data.nsrmc!}</a></td>
            <td>${data.swjgMc!}</td>
            <#--<td>${data.smrzzt!}</td>-->
            <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
            <td><a href="javascript:;" data-type="hngs" data-id="${data.id!}" name="del"  data-msg="国税电子税局"><i class="iconfont"></i> 解绑</a></td>
        </tr>
    </#list>
</#if>
</table>
<div id="demo1" index="${page1}" count="${count1}" style="text-align: right;"></div>
</@compress>