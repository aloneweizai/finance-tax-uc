<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>财税专家UC</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
            <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .grzx_main_bjgrzl table tr td,th{
            font-size: 14px;
            line-height: 30px;
        }
        .grzx_wdjf_dhjl table tr td,th{
            text-align: center;
            font-size: 14px;
        }
        .mktitle{
            margin-top: 20px;
        }
        .thduiqi{
            text-align: right;
        }
    </style>
</head>

<body >
<div class="grzx_main_rt">历史发票详情
    <a href="${ctx}/userinfo/userinvoice.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的发票</a>
</div>
<div class="clear"></div>
        <div class="grzx_main_bjgrzl">
            <#if ewm??>
                <div class="mktitle" style="margin-bottom: 20px;">二维码抽奖</div>
                <div style="border-bottom: 1px dashed #ddd; padding-bottom: 20px;">
                    <img src="data:image/jpg;base64,${ewm!""}" height="140" width="140" style="float: left; margin-right: 20px;">
                    <span style="line-height: 32px;">
                        <h4 style="margin-bottom: 10px;">扫码抽奖步骤：</h4>
                        1、微信关注“财税专家”微信公众号<br>
                        2、直接使用“微信扫一扫”参加微信红包抽奖<br>
                        3、中奖后在“财税专家”公众号领取微信红包
                    </span>
                    <div class="clear"></div>
                </div>
            </#if>
            <div class="mktitle">开票明细</div>
            <div style="border-bottom: 1px dashed #ddd; padding-bottom: 20px;">
                <table>
                    <tr>
                        <th class="thduiqi">发票订单号：&nbsp;</th>
                        <td colspan="2">${orderinvoice.id!""}</td>
                        <th>&nbsp;</th>
                    </tr>
                    <tr>
                        <th class="thduiqi">发票金额：&nbsp;</th>
                        <td>¥${orderinvoice.amount?string("0.00")}</td>
                        <th class="thduiqi">发票抬头：&nbsp;</th>
                        <td>
                        <#if orderinvoice.name??&&orderinvoice.name=='1'>
                            个人
                            <#else >
                            ${orderinvoice.nsrmc!}
                        </#if>

                        </td>
                    </tr>
                    <tr>
                        <th class="thduiqi">发票状态：&nbsp;</th>
                        <td>
                        <#if fqsqstatus??&&(fqsqstatus?size>0)>
                            <#list fqsqstatus as data>
                            <#if data.fieldValue==orderinvoice.status>
                            ${data.fieldKey!""}
                            </#if>
                        </#list>
                        </#if>
                        </td>
                        <th class="thduiqi">发票内容：&nbsp;</th>
                        <td>
                        <#if fpnr??&&(fpnr?size>0)>
                            <#list fpnr as data>
                                <#if data.fieldValue==orderinvoice.content>
                                ${data.fieldKey!}
                                </#if>
                            </#list>
                        </#if>
                        </td>
                    </tr>
                    <#if orderinvoice.status=='4'||orderinvoice.status=='5'||orderinvoice.status=='6'||orderinvoice.status=='7'>
                        <tr>
                            <th class="thduiqi">发票类型：&nbsp;</th>
                            <td>
                            <#if fplx??&&(fplx?size>0)>
                            <#list fplx as data>
                                <#if (orderinvoice.type)?? && data.fieldValue==orderinvoice.type>
                                ${data.fieldKey!"普通发票"}
                                </#if>
                            </#list>
                            </#if>
                                <#--<#if orderinvoice.property??&&orderinvoice.property=='1'>-->
                                    <#--增值税普通发票-->
                                <#--<#else >-->
                                    <#--电子增值税普通发票-->
                                <#--</#if>-->

                            </td>
                            <th class="thduiqi">发票代码：&nbsp;</th>
                            <td>${orderinvoice.invoiceCode!}</td>
                        </tr>
                    </#if>
                    <tr>
                        <th class="thduiqi">发票性质：&nbsp;</th>
                        <td>
                        <#if orderinvoice.property??&&orderinvoice.property=='1'>
                            纸质发票
                        <#else >
                            电子发票
                        </#if>
                      </td>
                        <th class="thduiqi">申请时间：&nbsp;</th>
                        <td>${(orderinvoice.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    </tr>
                    <#if orderinvoice.property??&&orderinvoice.property=='1'>
                    <tr>
                        <th class="thduiqi">收件人：&nbsp;</th>
                        <td>
                           ${(orderinvoice.consignee)!""}
                           <#-- <#if orderinvoice.userAddressBO??>
                                ${orderinvoice.userAddressBO.name!""}
                            </#if>-->
                        </td>
                        <th class="thduiqi">联系电话：&nbsp;</th>
                        <td>
                        ${(orderinvoice.contactNumber)!""}
                        <#--<#if orderinvoice.userAddressBO??>
                            ${orderinvoice.userAddressBO.provinceName!} ${orderinvoice.userAddressBO.cityName!} ${orderinvoice.userAddressBO.areaName!} ${orderinvoice.userAddressBO.detail!}
                        </#if>-->
                        </td>
                    </tr>
                        <tr>
                            <th class="thduiqi">地址：&nbsp;</th>
                            <td colspan="3">
                            ${(orderinvoice.shippingAddress)!""}
                        <#--<#if orderinvoice.userAddressBO??>
                            ${orderinvoice.userAddressBO.provinceName!} ${orderinvoice.userAddressBO.cityName!} ${orderinvoice.userAddressBO.areaName!} ${orderinvoice.userAddressBO.detail!}
                        </#if>-->
                            </td>
                        </tr>
                    <tr>
                        <th class="thduiqi">物流公司：&nbsp;</th>
                        <td>${(expressComp.compName)!""}</td>
                        <th class="thduiqi">物流编号：&nbsp;</th>
                        <td>${orderinvoice.waybillNum!""}</td>
                    </tr>
                    <#else>
                        <tr>
                            <th class="thduiqi">邮箱：&nbsp;</th>
                            <td>${orderinvoice.email!""}</td>
                            <th>&nbsp;</th>
                            <td>&nbsp;</td>
                        </tr>
                    </#if>
                    <tr>
                        <th class="thduiqi" width="100">备注：&nbsp;</th>
                        <td colspan="3">${orderinvoice.remark!""}</td>
                    </tr>
                </table>

            </div>
            <div class="mktitle">关联订单/月账单</div>
            <div class="grzx_wdjf_dhjl">
                <table>
                    <tr>
                        <th width="25%">订单编号</th>
                        <th width="25%">订单时间</th>
                        <th width="25%">产品名称</th>
                        <th width="25%">金额</th>
                    </tr>
                    <#if orderinvoice.orderBOList??&&(orderinvoice.orderBOList?size>0)>
                        <#list orderinvoice.orderBOList as order>
                            <tr>
                                <td>${order.orderNo!}</td>
                                <td>${(order.createTime?string('yyyy-MM-dd HH:mm'))!}</td>
                                <td>
                                    <#if order.orderProductBOList??&&(order.orderProductBOList?size>0)>
                                        <#list order.orderProductBOList as goods>
                                            <#if goods_index==0>
                                                ${goods.name!''}
                                            </#if>
                                        </#list>
                                    </#if>

                                </td>
                                <td style="font-weight: bold; color: #D58512;">¥${order.totalPrice?string("0.00")}</td>
                            </tr>
                        </#list>
                    </#if>

                </table>
            </div>
        </div>
    <#if url?? && orderinvoice.status!="8">
        <a href="javascript:;" class="layui-btn" style="float: right;margin-right: 30px" fapiao-id="${orderinvoice.invoiceNo!}" fapiao="fp" >发票下载</a>
    </#if>
    </div>
<script data-main="${ctx}/js/abc/fapiao" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>