<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>财税专家UC</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .fonsize{
            font-size: 12px;
            text-align: center;
        }
    </style>
</head>

<body >
<div class="grzx_main_rt">我的发票</div>
<!-- 右侧导航 -->
<div class="grzx_main_rt_nav">
    <ul>
        <li class="hover">索取发票 </li>
        <li>历史发票</li>
        <div class="clear"></div>
    </ul>
</div>
<!-- 我的关注 -->
<div class="grzx_wdjf_dhjl page_main_xxk">
    <table class="layui-table" lay-size="sm">
        <thead>
        <tr>
            <th width="10%" class="fonsize">
             <input class="js_checkbox" type="checkbox" id="checkall" title="">
               <#-- <input class="js_checkbox" type="checkbox" name="1" value="" lay-skin="primary" id="checkall" title="">-->
            </th>
            <th width="25%" class="fonsize">订单编号</th>
            <th width="20%" class="fonsize">订单时间</th>
            <th width="25%" class="fonsize">产品名称</th>
            <th width="20%" class="fonsize">金额</th>
        </tr>
        </thead>
        <tbody class="cent">
        <#if orderlist??&&(orderlist?size>0)>
            <#list orderlist as list>
                <tr>
                    <td class="fonsize"><input class="js_checkbox" type="checkbox" lay-skin="primary" title="" name="checkname" value="${list.orderNo!}" data-je="${list.totalPrice!}"></td>
                    <td class="fonsize">${list.orderNo!}</td>
                    <td class="fonsize">${(list.createTime?string("yyyy-MM-dd"))!}</td>
                    <td class="fonsize">
                    <#if list.orderProductBOList??&&(list.orderProductBOList?size>0)>
                        <#list list.orderProductBOList as data>
                            <#if data_index==0  && data.productBO ??>
                                ${data.productBO.goodsName!""}
                            <#else>
                                 ${data.name!""}
                            </#if>
                        </#list>
                        <#if (list.orderProductBOList?size>1)>
                            ...
                        </#if>
                    </#if>
                    </td>
                    <td class="fonsize">¥${list.totalPrice?string("0.00")}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
<#if orderlist??&&(orderlist?size>0)>
    <div class="" id="savefp"
         <#if usertzxx??>
            smrz="${usertzxx.validStatus!""}"
         <#else>
            smrz=""
         </#if>
         style="margin: 20px 0 0 20px; float:left;"><a href="javascript:;" class="layui-btn">索要发票</a>
    </div>
</#if>
</div>
<div class="grzx_wdjf_dhjl page_main_xxk" style="display: none;">
    <table class="layui-table" lay-size="sm">
        <thead>
        <tr>
            <th width="15%" class="fonsize">发票订单号 </th>
            <th width="10%" class="fonsize">发票金额 </th>
            <th width="20%" class="fonsize">发票抬头</th>
            <th width="10%" class="fonsize">性质</th>
            <th width="10%" class="fonsize">状态</th>
            <th width="15%" class="fonsize">时间</th>
            <th width="25%" class="fonsize">操作</th>
        </tr>
        </thead>
        <tbody class="cent">
        <#if invoicelist??&&(invoicelist?size>0)>
            <#list invoicelist as list>
                <tr class="order_btn">
                    <td class="fonsize">${list.id!}</td>
                    <td class="fonsize">¥${list.amount?string("0.00")}</td>
                    <td class="fonsize">
                    <#if list.name??&&list.name=="2">
                        ${list.nsrmc!}
                        <#else >
                        个人
                    </#if>
                    </td>
                    <td class="fonsize">
                    <#if list.property??&&list.property=='1'>
                        纸质发票
                    <#else >
                        电子发票
                    </#if></td>
                    <td class="fonsize">
                        <#if fqsqstatus??&&(fqsqstatus?size>0)>
                            <#list fqsqstatus as data>
                                <#if data.fieldValue==list.status>
                                    ${data.fieldKey!""}
                                </#if>
                            </#list>
                        </#if>
                    </td>
                    <td class="fonsize">${(list.createTime?string("yyyy-MM-dd"))!}</td>
                    <#--<td  class="fonsize">-->
                        <#--${list.remark!}-->
                    <#--</td>-->
                    <td><a href="invoice/${list.id!}" class="quxiao color_b" >详情</a>
                    <#if list.status?? && list.status=="4">
                        <a href="javascript:;" fapiao-id="${list.id!}" class="queren color_b" >确认收货</a>
                    </#if>
                    <#--<#if list.property??&&list.property=='2'&&list.status??&&list.status=='5'>
                        |<a href="invoice/${list.id!}" >发票下载</a>
                        &lt;#&ndash;|<a href="javascript:;" class="quxiao" fapiao-id="${list.invoiceNo!}" fapiao="fp" >发票下载</a>&ndash;&gt;
                    </#if>-->
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>


<script data-main="${ctx}/js/abc/fapiao" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>