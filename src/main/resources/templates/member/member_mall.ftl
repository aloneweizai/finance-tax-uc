<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>VC积分商城</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/Redeem.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/mall_libao.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body>
<div class='hysc-zslb clearfix'>
    <div class='hysc-zslb-top'>会员专属礼包
       <#-- <a href="javascript:">我领取的礼物</a>-->
    </div>
    <ul class='hysc-zslb-middle'>
        <li><s></s>VIP会员可免费挑选个人心仪礼物，小艾客服在2个工作日之内安排快递寄送给您。</li>
        <li><s></s>可领取您会员礼物金额内的任意礼物，多个会员订购赠送的礼物金额可累计，以便您挑选更好的会员礼物。</li>
    </ul>
    <div class='hysc-zslb-bottom'>我的会员礼物金额：<span>${userAmount!0}</span>元</div>
</div>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title clearfix">
        <li class="layui-this" category="">全部礼物</li>
        <li category="VIP1">银卡会员</li>
        <li category="VIP2">金卡会员</li>
        <li category="VIP3">钻石会员</li>
        <li category="VIP4">超级会员</li>
    </ul>
    <div class="layui-tab-content clearfix">

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
    </div>
</div>
<div>
    <div class="wlqd-lw" style="margin-top: 20px">
        我领取的会员礼物
    </div>
    <table class="layui-table" lay-size="sm">
        <colgroup>
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th width="8%">序号</th>
            <th width="20%">会员礼物</th>
            <th width="10%">金额</th>
            <th width="10%">申请时间</th>
            <th width="10%">申请状态</th>
            <th width="10%">处理时间</th>
            <th width="14%">快递信息</th>
            <th width="18%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#if applyList?? && (applyList?size>0)>
           <#list applyList as apply>
           <tr>
               <td>${apply_index+1}</td>
               <#assign amount = 0>
               <#if apply.giftApplyBOList??&& (apply.giftApplyBOList?size>0)>
                   <#list  apply.giftApplyBOList as gift>
                       <#assign amount = amount + gift.giftAmount>
                       <#assign applyGift = gift>
                       <#if gift_index == 0>
                           <td>${gift.giftName!}</td>
                       </#if>
                   </#list>
               </#if>
               <td>${amount!}元</td>
               <td>${apply.createTime?string("yyyy-MM-dd HH:mm")}</td>

               <#if apply.status=="0">
                   <td>已拒绝</td>
               <#elseif apply.status=="1">
                   <td>待处理</td>
               <#elseif apply.status=="2">
                   <td>已审核</td>
               <#elseif apply.status=="3">
                   <td>已发货</td>
               <#elseif apply.status=="4">
                   <td>已完成</td>
               </#if>
               <td>${apply.lastUpdate?string("yyyy-MM-dd HH:mm")}</td>
               <td>
                   <#if apply.expressNo?? && apply.expressComp??>
                     ${apply.expressComp!}：${apply.expressNo!}
                   </#if>
               </td>
               <td>
                   <a href="javascript:void(0)" class="caozuo  detail" apply-id="${apply.applyId!}">详情</a>
                   <#if apply.status=="3">
                    | <a href="javascript:void(0)" class="caozuo receive" apply-id="${apply.applyId!}">确认收货</a>
                   </#if>
               </td>
           </tr>
           </#list>
        </#if>
        </tbody>
    </table>
</div>

<script data-main="${ctx}/js/abc/member/member_mall" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>