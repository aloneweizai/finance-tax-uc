<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<#if invoicelist??&&(invoicelist?size>0)>
    <#list invoicelist as list>
    <tr>
        <td class="fonsize">¥${list.amount?string("0.00")}</td>
        <td class="fonsize">
            <#if list.name??&&list.name=='1'>
                个人
            <#else >
            ${list.nsrmc!}
            </#if>
        </td>
        <td class="fonsize">
            <#if list.property??&&list.property=='1'>
                纸质发票
            <#else >
                电子发票
            </#if></td>
        <td class="fonsize">
            <#if fqsqstatus??&&(fqsqstatus?size>0)>
                            <#list fqsqstatus as data>
                <#if data.fieldValue==list.status>
                ${data.fieldKey!""}
                </#if>
            </#list>
                        </#if>
        </td>
        <td class="fonsize">${(list.createTime?string("yyyy-MM-dd"))!}</td>
        <td class="fonsize"><a href="${ctx}/userinfo/invoice/${list.id!}"  class="orderdetail">详情</a></td>
    </tr>
    </#list>
</#if>
</@compress>