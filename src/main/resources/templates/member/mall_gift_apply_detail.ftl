<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>礼包详情</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr th,td{
            font-size: 12px;
        }
        .col-xs-2{width:20%}

    </style>
</head>
<body>
<div class="huiyuan_title">领取流程
    <a style="margin-bottom: 10px" href="${ctx}/member/member_mall.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>会员礼包</a>
</div>
<div class="clear"></div>
<div class="thhlc">
    <div class="step">
        <ul>
            <li class="col-xs-2 on">
                <span class="num"><em class="f-r5"></em><i>1</i></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">申请提交</p>
            </li>

            <li class="col-xs-2 on">
                <span class="num"><em class="f-r5"></em><i>2</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">用户付款</p>
            </li>
            <li class="col-xs-2 on">
                <span class="num"><em class="f-r5"></em><i>3</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">商家发货</p>
            </li>
            <li class="col-xs-2 on">
                <span class="num"><em class="f-r5"></em><i>4</i></span>
                <span class="line_bg lbg-l"></span>
                <span class="line_bg lbg-r"></span>
                <p class="lbg-txt">用户收货</p>
            </li>
            <li class="col-xs-2 on">
                <span class="num"><em class="f-r5"></em><i>5</i></span>
                <span class="line_bg lbg-l"></span>
                <p class="lbg-txt">领取完成</p>
            </li>
        </ul>
    </div>
</div>

<div class="huiyuan_title" style="margin-top: 10px">领取环节</div>

<div class="grzx_wdxx_wdxx page_main_tuihuo">
    <ul style="margin-top: 0px;">
    <#if logs ?? && (logs?size > 0)>
        <#list logs as log>
            <li>
                <div class="grzx_wdxx_wdxx_l ">${log.action!}</div>
                <div class="grzx_wdxx_wdxx_r">
                    <i></i>&nbsp;&nbsp; ${log.remark!}
                    <span class="tuihuo_shijian"><i class="iconfont icon-dengdai"></i>${log.createTime?string("yyyy-MM-dd HH：mm：ss")}</span>
                </div>
                <div class="clear"></div>
            </li>
        </#list>
    <#else>
    </#if>
    </ul>
</div>

<table class="layui-table fuwudan" style="margin-top: 20px">

    <tr>
        <th style="font:normal;font-size: 16px;text-align:left;color: #747474;" colspan="2">礼物信息</th>
        <th style="font:normal;font-size: 16px;color: #747474;text-align: left" colspan="2">
            配送信息
        </th>
    </tr>

    <tr>
        <th>礼物图片：</th>
        <td>
            <img style="width: 200px;height: 100px" src="${picurl!}${giftImg!}">
        </td>
        <th>快递公司:</th>
        <td>
           ${apply.expressComp!""}
        </td>
    </tr>
    <tr>

        <th>礼物编号：</th>
        <td>${apply.applyId!}</td>
        <th>快递单号：</th>
        <td>${apply.expressNo!}</td>
    </tr>

    <tr>
        <th>礼物金额：</th>
        <td>
        <#if gifts?? && (gifts?size>0)>
            <#assign cost = 0>
            <#list gifts as gift>
            <#assign  applyGift = gift>
            <#assign cost = cost + gift.giftAmount>
                ¥${cost?string("0.00")}
            </#list>
        </#if>
        </td>
        <th>收货人：</th>
        <td>${(apply.name)!}</td>

    </tr>
    <tr>
        <th>礼物名称：</th>
        <td>
        <#if applyGift??>${applyGift.giftName!}</#if>
        </td>
        <th>手机号码：</th>
        <td>${(apply.phone)!}</td>
    </tr>
    <tr>
        <th>实付金额：</th>
        <td>
            <#if cost??>¥${cost?string("0.00")}</#if>
        </td>
        <th>收件地址：</th>
        <td>
        ${(apply.address)!""}
        </td>

    </tr>
</table>
<script data-main="${ctx}/js/abc/member/member_mall" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>