<@compress single_line=true>
<#assign ctx=request.getContextPath()>
     <ul class="xsrw">
         <#if noviceTask ?? && ( noviceTask?size > 0) >
             <#list noviceTask  as task >
                 <li class="xinshou" status-id="${task.status!""}" skip-id="${task.skipUrl!""}" >
                     <div style="margin-top: 20px">
                         <img class="iconfont" src="${path!""}${task.imageUrl!""}"></img>
                     </div>
                     <h3>${task.name!""}</h3>
                     <p>
                         任务完成加+
                         <em> ${task.award!"0"}</em>
                         <#if task.awardType =="0">
                             经验值
                         <#elseif  task.awardType =="1">
                             积分
                         </#if>
                     </p>
                     <#if task.status == "1">
                         <img class="oks" src="../images/ok.png" alt="">
                     <#else>
                         <img class="oks" src="../images/noOk.png" alt="">
                     </#if>
                 </li>
             </#list>
         </#if>
     </ul>
</@compress>