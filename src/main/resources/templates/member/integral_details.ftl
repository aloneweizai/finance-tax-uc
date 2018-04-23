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
<div class="huiyuan_title">商品兑换
    <a href="${ctx}/member/member_integral.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>积分兑换</a>
</div>
<div class="clear"></div>
<p class="jifen_jilu">我的积分：<span class="jifen">${user.points!0}分 <a class="color_b"  href="integral_payment.html">[充值积分]</a></span></p>
<div class="jifen_shangpin">
    <div class="jifen_img" style="background: url(${picurl!""}${data.imageUrl!"${ctx}/images/noimg_0.jpg"}) no-repeat center;"></div>
    <div class="jifen_xinxi">
        <div class="jifen_title">${data.name!""}</div>
        <#--<div class="jifen_jieshao">${data.details!""}</div>-->
        <div class="jifen_dingdanxinxi">
            <#if data.productBOList??&&(data.productBOList?size>0)>
                <#list data.productBOList as da>
                    <#if da_index==0>
                        <#assign data_jifen=da>
                        <p>价格：<span class="jifenshu">${da.sellingPrice!0}</span>积分</p>
                        <#if user.vipLevel !="VIP0">
                             <#if vipPrices?? && (vipPrices?size > 0)>
                                <#list vipPrices as vipPrice>
                                  <#list vipPrice as vp>
                                      <#if (vp.vipLevel== user.vipLevel ||vp.vipLevel== user.vipLevelName) && (vp.productId == da.id)>
                                          <p class="hy">会员价格：<span class="hyjia" jiage="${(vp.tradePrice)!0}">${(vp.tradePrice)!0}</span>积分</p>
                                      </#if>
                                  </#list>
                                </#list>
                             </#if>
                        </#if>
                        <p>数量：<input type="number" value="1" style="width: 100px;" disabled="disabled"/></p>
                       <#-- <p>赠送积分：<span class="jifenshu">${data.giftPoints!0}</span></p>-->
                    </#if>
                </#list>
            </#if>

        </div>
        <div style="margin-left: 20px; margin-top: 10px;">
            <#if map??&&(map?size>0)>
                <#list map?keys as key>
                    <p style="margin-top: 10px;">${key}:
                        <#if map[key]??&&(map[key]?size>0)>
                            <#list map[key] as maps>
                                <a keyname="${maps['keyname']}"
                                 <#assign flag = false>
                                 <#if defaults ?? && (defaults?size>0)>
                                     <#list defaults as df>
                                         <#if df.value == maps.value && df.text == maps.text >
                                             <#assign flag = true>
                                         </#if>
                                     </#list>
                                 </#if>
                                <#if flag>
                                   class="sp_type select"
                                <#else>
                                   class="sp_type"
                                </#if>
                                 href="javascript:void(0);" keyvalue="${maps["value"]}" keytext="${maps["text"]}">${maps["text"]}</a>
                            </#list>
                        </#if>
                        </p>
                </#list>
            </#if>
        </div>

    <#if data?? && data.isShipping == 1><#--实物添加收货地址-->
        <div tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" style="width: 400px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="myModalLabel">我的地址</h4>
                    </div>
                    <div class="modal-body">
                        <div>
                            <#if addresslist??&&(addresslist?size>0)>
                                <#list addresslist as list>
                                    <#if list_index!=0>
                                        <br>
                                    </#if>
                                    <label style="font-size: 14px; ">
                                        <input style="float: left;margin: 2px 0 0 4px;" type="radio" name="addressId" value="${list.id!}"
                                            <#if list.isDefault??&&list.isDefault>
                                               checked="checked"
                                            </#if>
                                                />${list.name!}&nbsp;&nbsp;   <#if list.dizhi??>${list.dizhi.province.province!""}</#if ><#if list.dizhi??>${list.dizhi.city.city!""}</#if><#if list.dizhi??>${list.dizhi.area.area!""}</#if>${list.detail!}
                                    </label>
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
                    </div>
                    <div class="modal-footer">
                        <#if map??&&(map?size>0)>
                            <button style="width: 50px"  goods-name="${data.name!""}" isShipping ="${(data.isShipping)!""}" id="jifenduihuan" guige-size="${map?size}" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">确定</button>
                        <#else>
                            <button style="width: 50px"  goods-name="${data.name!""}" isShipping ="${(data.isShipping)!""}" id="jifengoods" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">确定</button>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </#if>

    <div class="jifen_duihuan">
        <#--<#if data?? && data.isShipping ==1>&lt;#&ndash;实物添加收货地址&ndash;&gt;-->
            <#--<a href=""  id="dizhi"  isShipping ="${(data.isShipping)!""}" style="cursor: pointer;" data-toggle="modal" data-target="#myModal">立即兑换</a>-->
        <#--<#else>-->
            <#--<#if map??&&(map?size>0)>-->
                <#--<a href="javascript:;" goods-name="${data.name!""}" isShipping ="${(data.isShipping)!""}" id="jifenduihuan" guige-size="${map?size}" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">立即兑换</a>-->
            <#--<#else>-->
                <#--<a href="javascript:;" goods-name="${data.name!""}" isShipping ="${(data.isShipping)!""}" id="jifengoods" goods-id="${data.id!""}" member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>" guige-id="<#if data_jifen??>${data_jifen.id!""}</#if>">立即兑换</a>-->
            <#--</#if>-->
        <#--</#if>-->
            <a href="javascript:;"  id="ljdh" <#if usertzxx??>
               smrz="${usertzxx.validStatus!""}"
            <#else>
               smrz=""
            </#if>  style="cursor: pointer;" goods-id="${data.id!""}"member-jifen="${user.points?c!0}" goods-jiage="<#if data_jifen??>${data_jifen.sellingPrice?c!0}</#if>">立即兑换</a>
    </div>

    </div>
</div>
<p class="jifen_jilu" style="margin-top: 30px">商品介绍</p>
<div class="sp_jieshao">
    ${data.details!""}
</div>
<script data-main="${ctx}/js/abc/member/integral_pay" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>