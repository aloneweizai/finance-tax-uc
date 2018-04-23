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
    .hysc-txxx{
        font-size:12px;
        color:#999;
    }
    .hylb_img{
        width: 312px;
        height: 312px;
    }
    .jifenshu{
        background: #;
    }
</style>
<body style="">
<div class="huiyuan_title">会员礼包
    <a href="${ctx}/member/member_mall.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>会员礼包</a>
</div>
<div class="clear"></div>
<p class="jifen_jilu">我的会员礼物金额：<span class="jifen">${userAmount!0}元</span></p>
<div class="jifen_shangpin">
    <div>
        <img class="hylb_img" src="${picurl}${data.imageUrl!}" alt="" />
    </div>
    <div class="jifen_xinxi">
        <div class="jifen_title">${data.name!""}</div>
        <div class="jifen_dingdanxinxi">
            <p>价格：<span class="jifenshu">${data.sellingPrice!0}</span> 元</p>
            <p>数量：1</p>
        </div>

        <div tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" style="width: 400px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="myModalLabel">会员礼物领取申请</h4>
                    </div>
                    <div class="modal-body">
                        <div>礼物名称：<span class="jifen">${data.name!""}</span></div>
                        <div>礼物金额：<span class="jifen">${data.sellingPrice!0}元</span></div>
                        <div class="hysc-xzshr">请选择我的收货联系人信息</div>
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
                        <div  class="hysc-txxx">
                            <p>备注：<span id="xianzhi" style="color:#EF4B7F">最多输入400字</span></p>
                            <textarea  style="line-height: 30px;width: 100%" type="text" name="remark" value ="" placeholder="请参考礼包简介填写您需要的礼包规格颜色等信息。"></textarea>
                        </div>
                        <div class="hysc-txxx">提醒：请确认收货信息无误，小艾客服在2个工作日之内安排快递送给您。</div>
                    </div>
                    <div class="modal-footer">
                        <button style="width: 50px"  class ="confirm" gift-id="${data.id}" gift-amount="${data.sellingPrice!0}" user-amount=${userAmount!0}" gift-name="${data.name!}" kucun="${data.stock!}">确定</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="jifen_duihuan">
            <a href="javascript:;"
                <#if usertzxx??>
               smrz="${usertzxx.validStatus!""}"
                <#else>
               smrz=""
                </#if>
               id="dizhi"  style="cursor: pointer;" kucun="${data.stock!}" gift-amount="${data.sellingPrice!0}" user-amount="${userAmount!0}"
                <#if data.stock gt 0 && userAmount gte data.sellingPrice  && usertzxx.validStatus=="2"> data-toggle="modal" data-target="#myModal"</#if>>立即领取</a>
        </div>

    </div>
</div>
<p class="jifen_jilu">礼包简介：${data.introduction!""}</p>
<p class="jifen_jilu">礼包详细</p>
<div class="sp_jieshao">
${data.details!""}
</div>
<script data-main="${ctx}/js/abc/member/member_mall" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>
