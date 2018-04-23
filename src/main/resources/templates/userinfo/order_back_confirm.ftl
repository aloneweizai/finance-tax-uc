<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>退换货记录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="grzx_main_rt">退换订单信息
    <a href="${ctx}/orderback/back.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>退换货申请</a>
</div>
<div class="clear"></div>
<table class="layui-table table table-bordered" >
        <tr>
            <th>订单编号</th>
            <th>商品名称</th>
            <th>购买数量</th>
            <th>退单原因</th>
        </tr>
        <tr>
            <td id="orderNo">${exchange.orderNo!}</td>
            <td>
                <#--<img src="${path}${exchange.imageUrl!""}">-->
            ${exchange.name!}
            </td>
            <td>${exchange.num!"1"}</td>
            <td>
                <#if exchangeReason?? && (exchangeReason?size>0)>
                    <#list exchangeReason as reason>
                        <#if reason.fieldValue == exchange.reason>
                            ${reason.fieldKey!""}
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
</table>

    <div>
        <div class="grzx_main_rt">退换信息：</div>
        <form id="confirmInfo" name="confirmForm"  method="post">
            <label class="control-label" style="color: #F34948;">(*发票如果为纸质发票，请和商品一起邮寄！)</label><br/>
            <#--<div class="form-group" style="margin-bottom: 5px">
                <label class="control-label" for="recipient-name">发票类型:</label>
                <select name="pingju" style="width: 400px;height: 30px">
                    <option value="zhizi">纸质发票</option>
                    <option value="dianzi">电子发票</option>
                </select>
            </div>-->
            <div class="form-group">
                   <span>
                        <label class="control-label" style="margin-bottom: 5px">退换方式:</label>
                        <label class="control-label" >快递到卖家</label>
                   </span>
            </div>

            <div class="form-group">
                <label class="control-label" style="margin-bottom: 5px">收件人:</label>
                <label style="width: 90%;height: 30px">
                <#if receiveInfos?? && (receiveInfos?size> 0)>
                    <#list receiveInfos as receiveInfo>
                        <#if receiveInfo.fieldValue == "1">
                            ${receiveInfo.fieldKey!""}
                        </#if>
                    </#list>
                </#if>
                </label>
            </div>

            <div class="form-group">
                <label class="control-label" style="margin-bottom: 5px">收件人号码:</label>
                <label style="width: 90%;height: 30px">
                <#if receiveInfos?? && (receiveInfos?size> 0)>
                    <#list receiveInfos as receiveInfo>
                    <#if receiveInfo.fieldValue == "2">
                        ${receiveInfo.fieldKey!""}
                    </#if>
                </#list>
                </#if>
                </label>
            </div>

            <div class="form-group">
                <label class="control-label" style="margin-bottom: 5px">收件地址:</label>
                <label style="width: 90%;height: 30px">
                <#if receiveInfos?? && (receiveInfos?size> 0)>
                    <#list receiveInfos as receiveInfo>
                    <#if receiveInfo.fieldValue == "3">
                        ${receiveInfo.fieldKey!""}
                    </#if>
                </#list>
                </#if>
                </label>
            </div>
         <#if exchange.type=="1" ><#--只有换货时才要提供收货地址-->
         <#--<div class="grzx_main_rt">用户发货：</div>
            <div class="form-group" style="margin-bottom: 5px">
                <label class="control-label" for="recipient-name">快递公司:</label>
                <select style="width: 240px">
                    <option>顺丰</option>
                    <option>申通</option>
                    <option>中通</option>
                    <option>圆通</option>
                </select>
            </div>
            <div class="form-group" style="margin-bottom: 5px">
                <label class="control-label" for="recipient-name">快递单号:</label>
                <input type="text" style="width:240px"></input>
            </div>
         <button class="grzx_btn" style="float: right;margin-right: 40px">提交</button>-->
         </#if>
        </form>
    </div>

</body>
<script data-main="${ctx}/js/abc/order_back" src="${ctx}/js/require.js"></script>
</html>
</@compress>