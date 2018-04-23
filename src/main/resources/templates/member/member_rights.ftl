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
<body style="">
<div class="huiyuan_title">会员等级与权益
    <i style="color: #F34948;">(*会员购买后不能退换!)</i>
    <a href="${ctx}/member/order_member.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>订购会员</a>
</div>
<div class="clear"></div>

<table class="layui-table table table-bordered hy_dgxq">
    <tr>
        <td> </td>
        <#if data??&&(data?size>0)>
        <#list data as vip>
            <td>
                <img src="${picurl!""}${vip.imgUrl!""}" alt="${vip.level!""}">
            </td>
        </#list>
        </#if>
    </tr>

    <#if privilegeList?? && (privilegeList?size>0)>
    <#list privilegeList as privilege>
      <tr>
        <td style="text-align: left;">${privilege.name!} </td>
        <#if data??&&(data?size>0)>
            <#list data as vip>
              <td class="yes"><i class="iconfont">
                <#assign  desc = "--">
                <#if vipDatasList?? && (vipDatasList?size>0)>
                  <#list vipDatasList as vipDatas>
                      <#if vipDatas?? && (vipDatas?size>0)>
                          <#list vipDatas as vipData>
                              <#if (vip.levelCode== vipData.levelId) && (privilege.code == vipData.privilegeId)>
                                  <#assign desc =vipData.description>
                              </#if>
                          </#list>
                      </#if>
                  </#list>
                </#if>
                <#if !desc?? ||desc=="">
                    --
                <#else>
                    ${desc!"--"}
                </#if>
              </i>
              </td>
          </#list>
        </#if>
      </tr>
      </#list>
    </#if>

    <tr>
    <td style="text-align: left;">会员年费价值</td>
    <#if data??&&(data?size>0)>
        <#list data as vip>
            <#if  !(vip.marketPrice)?? ||vip.marketPrice ==0 >
                <td class="nf_rmb" style="font-size: 16px;color: #000000;font-weight:normal ;">--</td>
            <#else>
                <td class="nf_rmb" style="font-size: 16px;color: #333;font-weight:normal ; text-decoration:line-through">¥${vip.marketPrice?string("0.00")}</td>
            </#if>
        </#list>
    </#if>
    </tr>
    <tr>
        <td style="text-align: left;">会员年费优惠价</td>
        <#if data??&&(data?size>0)>
            <#list data as vip>
                <#if  !(vip.salePrice)?? ||vip.salePrice ==0 >
                    <td class="nf_rmb" style="font-size: 16px">--</td>
                <#else>
                    <td class="nf_rmb" style="font-size: 24px; color: #ffaf37;"><span style="color: #00bcd4; font-size: 14px;">¥ </span>${vip.salePrice?string("0.00")}<span style="color: #00bcd4; font-size: 14px;"> 元/年</span></td>
                </#if>
            </#list>
        </#if>
    </tr>
    <tr>
        <td> </td>
        <td></td>
        <#if data??&&(data?size>0)>
            <#list data as goods>
                <td><a href="javascript:;"
                <#if goods.levelCode!="VIP0">
                       palce="zhifu"  goods-kucun="1"   goods-id="${goods.id!""}" ><button class="btn btn-info">开通会员</button></a></td>
                </#if>
            </#list>
        </#if>
    </tr>
</table>
<p>注：会员特权最终解释权归财税专家软件服务商所有</p>

<script data-main="${ctx}/js/abc/member/member_rights" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>