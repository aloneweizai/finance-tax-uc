<@compress single_line=true>
<#assign ctx=request.getContextPath()>

<div class="layui-tab-item layui-show">
    <table class="layui-table">
        <colgroup>
            <col width="33%">
            <col width="33%">
            <col width="33%">
        </colgroup>
        <thead style="color: #989898">
        <tr>
            <th>来源/用途</th>
            <th>积分变化</th>
            <th>日期</th>
        </tr>
        </thead>
        <tbody id="duihuanTable">
        <#if orderList?? && (orderList?size >0 )>
            <#list orderList as order>
                <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                    <#list order.orderProductBOList as orderProduct>
                        <#assign  product = orderProduct>
                    <tr>
                        <td>
                            <#if product ??>
                                <h3>${(product.name)!""}</h3>订单号：${order.orderNo!""}
                            <#else>
                                <h3></h3><br/>订单号：${order.orderNo!""}
                            </#if>
                        </td>
                        <td style="font-size: 18px;" class="yellow">-${(order.totalPrice)!"0"}</td>
                        <td>${order.lastUpdate?string("yyyy年MM月dd日 HH:mm:ss")}</span></td>
                    </tr>
                    </#list>
                </#if>
            </#list>
        </#if>
        </tbody>
    </table>
    <!--分页页签-->
    <div id="pageTwo">
    <#if pagerSpec2?? && pagerSpec2.totalPage gt 0>
        <input type="hidden" name="curr_link" value="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage)}">
        <nav aria-label="Page navigation" style="text-align: right;">
            <ul class="pagination">
                <li>
                    <a href="javascript:void(0)" data-href="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage-1)}" aria-label="Previous" <#if pagerSpec2.currentPage gt 1>class="js_page_previous" </#if>>
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <#if pagerSpec2.totalPage gt 0>
                    <#assign i=1>
                    <#list pagerSpec2.navOffsest..pagerSpec2.totalPage as index>
                        <li data-page-index=${index}><a href="${pagerSpec2.link?replace('[:page]', index)}"  <#if index==pagerSpec2.currentPage>style="background: #14B9D5"</#if>  >${index}</a></li>
                        <#if i==5><#break></#if>
                        <#assign i=i+1>
                    </#list>
                </#if>
                <li>
                    <a href="javascript:void(0)" data-href="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage+1)}" aria-label="Next" <#if pagerSpec2.currentPage lt pagerSpec2.totalPage> class="js_page_next"</#if>>
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </#if>
    </div>
</div>
</@compress>