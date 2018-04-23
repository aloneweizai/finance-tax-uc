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
