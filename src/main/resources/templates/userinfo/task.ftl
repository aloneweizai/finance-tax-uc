<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>我的任务</title>
    <#include "../common/meta.ftl">
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
<div class="grzx_main_rt">我的任务</div>

<div class="grzx_wdrw_bbrw_tl">
    <ul class="tasks">
        <li style="background: url('../images/rw1.jpg') right bottom no-repeat #f5c051">
            <h3>${(taskBo.currentPoints)!"0"}</h3>
            <p>我获取的积分</p>
        </li>
        <li style="background: url('../images/rw2.jpg') right bottom no-repeat #19b0bb">
            <h3>${(taskBo.unReceivePoints)!"0"}</h3>
            <p>未领取任务积分</p>
        </li>
        <li style="background: url('../images/rw3.jpg') right bottom no-repeat #15ba80">
            <h3>${(taskBo.taskRange)!"0"}</h3>
            <p>本月任务排名</p>
        </li>
        <li style="background: url('../images/rw4.jpg') right bottom no-repeat #bd4ff5;margin-right: 0">
            <h3>${(taskBo.finishedTaskCount)!"0"}</h3>
            <p>本月完成任务</p>
        </li>
        <div class="clear"></div>
    </ul>
    <div class="clear"></div>
</div>



<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title clearfix">
        <li class="layui-this" taskType="2">日常任务</li>
        <li taskType="1">新手任务</li>
        <li taskType="3">特殊任务</li>
    </ul>
    <div class="layui-tab-content clearfix">
    <div class="layui-tab-item layui-show">
     <#if taskType =="2">
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
         <#elseif  taskType =="1">
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
         <#elseif  taskType =="3">
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
     </#if>
     </div>
    </div>
</div>

<!--ESC排序用-->
<!--<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>-->
<script data-main="${ctx}/js/abc/task" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>