<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<div class="layui-tab-item layui-show">
    <ul>
    <#if data?? && (data?size>0)>
        <#list data as gift>
            <li class ="giftImg"
                <#if usertzxx??>
                smrz="${usertzxx.validStatus!""}"
                <#else>
                smrz=""
                </#if>
                gift-id="${gift.id}" gift-name="${gift.name!}" kucun="${gift.stock!}">
                <div class="hysc-img">
                    <img src="${picurl}${gift.imageUrl!}" alt="" />
                </div>
                <div class="content-div clearfix">
                    <p>
                            <span>
                                <#if (gift.name?length>12)>
                                    ${gift.name?substring(0,12)}…
                                <#else>
                                    ${gift.name}
                                </#if>
                            </span>
                    </p>
                    <div class="jiage_lingqu">
                        ¥<span> ${gift.sellingPrice!}</span> 元
                        <a href="javascript:;"
                           class ="gift fr" gift-id="${gift.id}" gift-name="${gift.name!}" kucun="${gift.stock!}">
                            我要领取
                        </a>
                        <div class="clear"></div>
                    </div>
                </div>
            </li>
        </#list>
    </#if>
    </ul>
</div>

</@compress>