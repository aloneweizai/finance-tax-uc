<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<table class="layui-table">
    <colgroup>
        <col width="16%">
        <col width="16%">
        <col width="16%">
        <col width="20%">
        <col>
    </colgroup>
    <thead style="color: #989898">
    <tr>
        <th>来源/用途</th>
        <th>积分变化</th>
        <th>累计</th>
        <th>日期</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody id="pointsTable">
    <#if PointsLogs ?? && ( PointsLogs?size>0 )>
        <#list PointsLogs as pointLog>
        <tr>
            <td>${pointLog.channel!""}</td>
            <td style="font-size: 18px;">
                <#if pointLog.points gt 0>
                    <div class="grzx_wdjf_dhjl_orange">+${pointLog.points}</div>
                <#else>
                    <div class="grzx_wdjf_dhjl_blue">${pointLog.points}</div>
                </#if>
            </td>
            <td>${pointLog.sums!""}</td>
            <td>${pointLog.createTime?string("yyyy年MM月dd日 HH:mm")}</td>
            <td><p style="word-break:break-all">${pointLog.remark!""}</p></td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<!--分页页签-->
<div id="pageOne">
<#if pagerSpec?? && pagerSpec.totalPage gt 0>
    <input type="hidden" name="curr_link" value="${pagerSpec.link?replace('[:page]', pagerSpec.currentPage)}">
    <nav aria-label="Page navigation" style="text-align: right;">
        <ul class="pagination">
            <li>
                <a href="javascript:void(0)" data-href="${pagerSpec.link?replace('[:page]', pagerSpec.currentPage-1)}" aria-label="Previous" <#if pagerSpec.currentPage gt 1>class="js_page_previous" </#if>>
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <#if pagerSpec.totalPage gt 0>
                <#assign i=1>
                <#list pagerSpec.navOffsest..pagerSpec.totalPage as index>
                    <li data-page-index=${index}><a href="${pagerSpec.link?replace('[:page]', index)}"  <#if index==pagerSpec.currentPage>style="background: #14B9D5"</#if>  >${index}</a></li>
                    <#if i==5><#break></#if>
                    <#assign i=i+1>
                </#list>
            </#if>
            <li>
                <a href="javascript:void(0)" data-href="${pagerSpec.link?replace('[:page]', pagerSpec.currentPage+1)}" aria-label="Next" <#if pagerSpec.currentPage lt pagerSpec.totalPage> class="js_page_next"</#if>>
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</#if>
</div>
</@compress>