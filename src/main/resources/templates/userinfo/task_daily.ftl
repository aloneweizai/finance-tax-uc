<@compress single_line=true>
<#assign ctx=request.getContextPath()>
     <ul class="rcrw">
         <#if dailyTask ?? && ( dailyTask?size > 0) >
             <#list dailyTask  as task >
                 <li class="daliyTask" status-id="${task.status!""}" skip-id="${task.skipUrl!""}" >
                     <img class="renwuimg" src="${path!""}${task.imageUrl!""}">

                     <div class="rwtxt">
                         <h3>${task.name!""}</h3>
                         <div class="rws_jl">
                             <dd>任务完成数：<span class="color_o">${task.finishedCount!"0"}</span>/${task.allCount!"0"}</dd>

                             <em> 奖励：${task.award!"0"}</em>
                             <#if task.awardType =="0">
                                 经验值
                             <#elseif  task.awardType =="1">
                                 积分
                             </#if>
                         </div>
                         <p class="rule">${task.rule!""}</p>
                     </div>
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