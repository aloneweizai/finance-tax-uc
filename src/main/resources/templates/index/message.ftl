<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<#if businessMessages?? && (businessMessages?size > 0)>
    <#list businessMessages as bMessage>
    <li class="bsMessage " <#if !bMessage.url??||bMessage.url=="">message-id="${bMessage.id!}"  msgstatus ="${bMessage.status!}" msg-type="${bMessage.type!""}" style="cursor: pointer;"</#if>>
        <div class="iconfont icon-laba color_b grzx_wdxx_bbxx_l "></div>
        <div class="grzx_wdxx_bbxx_r">
        <#if msgTypes??>
        <#list  msgTypes as msgType>
            <#if bMessage.type == msgType.fieldValue>
                ${msgType.fieldKey!}-
            </#if>
            </#list>
        </#if>
            <#if bMessage.createTime??>
                <samp>${bMessage.createTime!""}</samp>
            <#else>
                <samp>${bMessage.lastUpdate!""}</samp>
            </#if>
            <#if bMessage.status =="1"><#--未读-->
                <span class="news">新</span>
            </#if>
            <h4> ${bMessage.content!""}
                <span style="color: #00BFFF"<#if bMessage.url??> message-id="${bMessage.id!}"  msgstatus ="${bMessage.status!}" msg-type="${bMessage.type!""}" style="cursor: pointer;"</#if>>
                    <#if bMessage.url??>
                        <#if bMessage.url?index_of("href")!=-1 && bMessage.url?index_of(">")!=-1 && bMessage.url?index_of("</")!=-1 >
                            <#assign  from = bMessage.url?index_of(">")+ 1>
                            <#assign  to = bMessage.url?index_of("</")>
                            ${bMessage.url?substring(from,to)}
                        <#else>
                            ${bMessage.url!""}
                        </#if>
                    </#if>
                </span>
            </h4>
        </div>
        <div class="clear"></div>
    </li>
    </#list>
</#if>
</@compress>