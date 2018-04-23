<@compress single_line=true>
<table class="layui-table" lay-size="sm">
    <tr>
        <th width="6%">序号</th>
        <th width="14%">纳税人识别号 </th>
        <th width="14%">用户名 </th>
        <th width="14%">子用户</th>
        <th width="14%">纳税人名称</th>
        <#--<th width="14%">所属税务机关</th>-->
        <#--<th width="14%">绑定时间</th>-->
        <th width="10%">操作</th>
    </tr>
<#if userhnds.dataList??&&(userhnds.dataList?size>0)>
    <#list userhnds.dataList as data>
        <tr>
            <td><#if page2!=0>${15*(page2-1)+data_index+1}<#else>${data_index+1}</#if></td>
            <td>${data.shxydm!}</td>
            <td>${data.username!""}</td>
            <td>${data.subuser!}</td>
            <td><a href="javascript:;" type="dswt" dswt-id="${data.id!""}">${data.nsrmc!}</a></td>
            <#--<td>${data.swjgmc!}</td>-->
            <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
            <td><a href="javascript:;" data-type="hnds" data-id="${data.id!}" name="del"   data-msg="地税网上办税厅"><i class="iconfont"></i> 解绑</a></td>
        </tr>
    </#list>
</#if>
</table>
<div id="demo2" index="${page2}" count="${count2}" style="text-align: right;"></div>
</@compress>