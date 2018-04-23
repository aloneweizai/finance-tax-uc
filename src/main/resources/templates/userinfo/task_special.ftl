<@compress single_line=true>
<#assign ctx=request.getContextPath()>
     <ul class="grzx_wdrw_bbrw">
         <#if specialTask ?? && ( specialTask?size > 0) >
             <#list specialTask  as task >
                 <li style="background:#f2f2f2; margin-top: 10px;">
                     <div class="grzx_wdrw_bbrw_img fl">
                         <#if (task.imageUrl)?? && (task.imageUrl)!= "">
                             <img src="${path!""}${task.imageUrl!""}">
                         <#else>
                             <img src="${ctx}/images/xitongrenwu.jpg">
                         </#if>
                     </div>
                     <div class="grzx_wdrw_bbrw_top fl">
                         <h4>${task.name!""}</h4>
                         <p>${task.rule!""}</p>
                         <p>活动时间：${task.startTime?string("yyyy-MM-dd")} ~ ${task.endTime?string("yyyy-MM-dd")}</p>
                         <p class="color_o">活动奖励：
                             <#if task.awardType =="0">
                                 <i class="iconfont" style="font-size: 25px">&#xe7d0;</i>
                             <#elseif  task.awardType =="1">
                                 <i class="iconfont icon-jinbi1" style="font-size: 25px"></i>
                             </#if>
                         ${task.award!""}
                             <#if task.awardType =="0">
                                 经验值
                             <#elseif  task.awardType =="1">
                                 积分
                             </#if>
                         </p>
                         <p>
                             <a status-id="${task.status!""}" skip-id ="${(task.skipUrl)!}" class="layui-btn wanc">
                                 <#if task.status != "1">
                                     完成任务
                                 <#else>
                                     已完成
                                 </#if>
                             </a>
                         </p>
                     </div>
                     <div class="clear"></div>
                 </li>
             </#list>
         </#if>
     </ul>
</@compress>