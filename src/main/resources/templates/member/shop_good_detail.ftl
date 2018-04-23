<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style>

</style>
<body style="">
<div class="huiyuan_title">购买商品</div>
<div class="jifen_shangpin">
    <div class="jifen_img" style="background: url(${picurl!""}${data.imageUrl!"${ctx}/images/noimg_0.jpg"}) no-repeat center;"></div>
    <div class="jifen_xinxi">
        <div class="jifen_title">${data.name!""}</div>
        <#--<div class="jifen_jieshao">${data.details!""}</div>-->
        <div class="jifen_dingdanxinxi">
            <#if data.productBOList??&&(data.productBOList?size>0)>
                <#if data.productBOList?size==1>
                    <#list data.productBOList as da>
                        <#if da_index==0>
                            <#assign data_jifen=da>
                            <p>价格：<span class="jifenshu">¥${da.sellingPrice?string("0.00")}</span></p>
                            <p>数量：<input type="number" value="1" style="width: 100px;" disabled="disabled"/></p>
                        </#if>
                    </#list>
                <#else>
                    <p>价格：<span class="jifenshu">¥${(xiao!0-da!0)?string("0.00")}</span></p>
                    <p>数量：<input type="number" value="1" style="width: 100px;" disabled="disabled"/></p>
                </#if>
            </#if>

        </div>
        <div style="margin-left: 20px; margin-top: 10px;">
            <#if map??&&(map?size>0)>
                <#list map?keys as key>
                    <p style="margin-top: 10px;">${key}:
                        <#if map[key]??&&(map[key]?size>0)>
                            <#list map[key] as maps>
                                <a keyname="${maps['keyname']}" class="sp_type" href="javascript:void(0);" keyvalue="${maps["value"]}" keytext="${maps["text"]}">${maps["text"]}</a>
                            </#list>
                        </#if>
                        </p>
                </#list>
            </#if>
        </div>

    <#if data?? && data.isShipping ==1><#--实物添加收货地址-->
        <div style="border-top: 1px solid #e5e5e5;margin-top: 20px;margin-bottom: 20px;"/>

        <div class="dizhi" style="font-size: 12px; ">收件地址 </div>
        <div>
            <#if addresslist??&&(addresslist?size>0)>
                <#list addresslist as list>
                    <#if list_index!=0>
                        <br>
                    </#if>
                    <label style="font-size: 14px; ">
                        <input type="radio" name="addressId" value="${list.id!}"
                            <#if list.isDefault??&&list.isDefault>
                               checked="checked"
                            </#if>
                                />${list.name!} <#if list.dizhi??>${list.dizhi.province.province!""} </#if ><#if list.dizhi??>${list.dizhi.city.city!""} </#if> <#if list.dizhi??>${list.dizhi.area.area!""} </#if>${list.detail!}</label>
                </#list>
            </#if>
            <a href="javascript:;" id="upddizhis" style="color: blue;">
            <#if addresslist??&&(addresslist?size>0)>
                修改地址
            <#else>
                新增地址
            </#if>
            </a>
        </div>
    </#if>

    <#if map??&&(map?size>0)>
        <div class="jifen_duihuan">
            <a href="javascript:;" goods-name="${data.name!""}" id="jifenduihuan" guige-size="${map?size}" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">立即购买</a>
        </div>
    <#else>
        <div class="jifen_duihuan">
            <a href="javascript:;" goods-name="${data.name!""}" id="jifengoods" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">立即购买</a>
        </div>
    </#if>
    </div>
</div>
<p class="jifen_jilu">商品介绍</p>
<div class="sp_jieshao">
    ${data.introduction!""}
</div>
<script data-main="${ctx}/js/abc/member/good_detail" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>