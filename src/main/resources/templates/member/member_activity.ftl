<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>

<head>
<#include "../common/meta.ftl">
    <title>会员活动</title>
</head><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style>
    img{
        max-width: none;
    }
</style>

<body >
<div class="huiyuan_title-activity"></div>
<div class="activity-content clearfix" style="background:#29DCE7" style="width:870px;">
    <div class="activity-content-left">
        <p>幸运者名单</p>
        <div class="activity-content-left-top">
            <p><span>用户名</span><span>获得奖品</span></p>
            <ul>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">海宝</span><span>耳机一只</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">财税17</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">财税专家</span><span>耐克球鞋一只</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">艾奥</span><span>手机一台</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">潇潇</span><span>耐克球鞋一只</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">小米</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">财税大侠</span><span>耐克球鞋一只</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">小明</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">王敏</span><span>耐克球鞋一只</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">芳芳</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">廊坊</span><span>平板一台</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">小贝</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">希尔</span><span>玩偶一个</span></li>
                <li><span><img src="${ctx}/images/icon_zx_03.png" alt="">露露</span><span>玩偶一个</span></li>
            </ul>
        </div>

        <div class="activity-content-left-bottom">
            <table class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th>抽奖规则</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1、每次抽奖需要扣除200个积分，扣除的积分不退还，每天参与抽奖次数不限；</td>
                </tr>
                <tr>
                    <td>2、本活动奖品为：商品类奖品和积分奖品；</td>
                </tr>
                <tr>
                    <td>3、中奖用户兑换商品奖品需要自行承担运费</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div id="lottery" class="activity-content-right">
        <img style="width:478px; height:488px;" src="../../images/activity_bg.jpg" alt="">
        <table class="kjt layui-table" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="lottery-unit lottery-unit-0"><img src="${ctx}/images/1.png"></td>
                <td class="lottery-unit lottery-unit-1"><img src="${ctx}/images/3.png"></td>
                <td class="lottery-unit lottery-unit-2"><img src="${ctx}/images/4.png"></td>
                <td class="lottery-unit lottery-unit-3"><img src="${ctx}/images/3.png"></td>
            </tr>
            <tr>
                <td class="lottery-unit lottery-unit-11"><img src="${ctx}/images/7.png"></td>
                <td colspan="2" rowspan="2"><a style="width: 200px;height: 200px;display: block;background: url('../images/activity_bg.jpg')" href="#"></a></td>
                <td class="lottery-unit lottery-unit-4"><img src="${ctx}/images/5.png"></td>
            </tr>
            <tr>
                <td class="lottery-unit lottery-unit-10"><img src="${ctx}/images/1.png"></td>
                <td class="lottery-unit lottery-unit-5"><img src="${ctx}/images/3.png"></td>
            </tr>
            <tr>
                <td class="lottery-unit lottery-unit-9"><img src="${ctx}/images/3.png"></td>
                <td class="lottery-unit lottery-unit-8"><img src="${ctx}/images/6.png"></td>
                <td class="lottery-unit lottery-unit-7"><img src="${ctx}/images/8.png"></td>
                <td class="lottery-unit lottery-unit-6"><img src="${ctx}/images/3.png"></td>
            </tr>
        </table>
    </div>
    <div class="activity-foot" >
        <span><img src="${ctx}/images/activity-foot1.png" alt=""></span><span><img src="${ctx}/images/activity-foot2.png" alt=""></span>
    </div>
</div>
<script data-main="${ctx}/js/abc/member/member_activity.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
<#--<body>-->
<#--<div class="huiyuan_title-activity"></div>-->
<#--<div class="activity-content clearfix" style="background: rgb(41, 220, 231);">-->
    <#--<div class="activity-content-left">-->
        <#--<p>幸运者名单</p>-->
        <#--<div class="activity-content-left-top">-->
            <#--<p><span>用户名</span><span>获得奖品</span></p>-->
            <#--<ul id="winners_ul">-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝1</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝2</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝3</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝4</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝5</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝6</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝7</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝8</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝9</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝10</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝11</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝12</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝13</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝14</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝1</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝2</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝3</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝4</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝5</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝6</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝7</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝8</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝9</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝10</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝11</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝12</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝13</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
                <#--&lt;#&ndash;<li style="top: -406.86px;"><span><img alt="" src="${ctx}/images/icon_zx_03.png">海绵宝宝14</span><span>耐克球鞋一只</span></li>&ndash;&gt;-->
            <#--</ul>-->
        <#--</div>-->

        <#--<div class="activity-content-left-bottom">-->
            <#--<table>-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th>抽奖规则</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody>-->
                <#--<tr>-->
                    <#--<td>1、每次抽奖需要扣除100个万表积分，扣除的积分不退还，每天参与抽奖次数不限；</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td>2、本活动奖品为：商品类奖品和万表积分奖品；</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td>3、中奖用户兑换商品奖品需要自行承担运费</td>-->
                <#--</tr>-->
                <#--</tbody>-->
            <#--</table>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="activity-content-right" id="lottery">-->
        <#--<table border="0" cellspacing="0" cellpadding="0">-->
            <#--<tbody>-->
                <#--<tr>-->
                    <#--<#list awards as award>-->
                        <#--<td class="lottery-unit lottery-unit-${award_index}">-->
                            <#--<img src="${imgPth}${award.image}">-->
                        <#--</td>-->
                        <#--<#if award_index ==3><#break></#if>-->
                    <#--</#list>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td class="lottery-unit lottery-unit-11">-->
                        <#--<img src="${imgPth}${awards[11].image}" >-->
                    <#--</td>-->
                    <#--<td rowspan="2" colspan="2"><a href="#"></a></td>-->
                    <#--<td class="lottery-unit lottery-unit-4">-->
                        <#--<img src="${imgPth}${awards[4].image}" ">-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td class="lottery-unit lottery-unit-10">-->
                        <#--<img src="${imgPth}${awards[10].image}">-->
                    <#--</td>-->
                    <#--<td class="lottery-unit lottery-unit-5">-->
                        <#--<img src="${imgPth}${awards[5].image}">-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td class="lottery-unit lottery-unit-9">-->
                        <#--<img src="${imgPth}${awards[9].image}">-->
                    <#--</td>-->
                    <#--<td class="lottery-unit lottery-unit-8">-->
                        <#--<img src="${imgPth}${awards[8].image}">-->
                    <#--</td>-->
                    <#--<td class="lottery-unit lottery-unit-7">-->
                        <#--<img src="${imgPth}${awards[7].image}">-->
                    <#--</td>-->
                    <#--<td class="lottery-unit lottery-unit-6">-->
                        <#--<img src="${imgPth}${awards[6].image}">-->
                    <#--</td>-->
                <#--</tr>-->
            <#--</tbody>-->
        <#--</table>-->
    <#--</div>-->
    <#--<div class="activity-foot">-->
        <#--<span>-->
            <#--<img alt="" src="${ctx}/images/activity-foot1.png">-->
        <#--</span>-->
        <#--<span>-->
            <#--<img alt="" src="${ctx}/images/activity-foot2.png">-->
        <#--</span>-->
    <#--</div>-->
<#--</div>-->

<#--<!--modal&ndash;&gt;-->
<#--<div id="winner_modal" tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">-->
    <#--<div class="modal-dialog" role="document" style="width: 400px;">-->
        <#--<div class="modal-content">-->
            <#--<div class="modal-header">-->
                <#--<button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>-->
                <#--<h4 class="modal-title" id="myModalLabel">恭喜中奖</h4>-->
            <#--</div>-->
            <#--<#if defaultAddress??>-->
                <#--<div class="modal-body">-->
                    <#--<form name ="dzfgForm" id="dzfgForm">-->
                        <#--<table>-->
                            <#--<tr style="line-height: 40px;">-->
                                <#--<td style="text-align: right; padding-right: 10px;">收货人姓名:</td>-->
                                <#--<td colspan="2">${(defaultAddress.name)!}</td>-->
                            <#--</tr>-->
                            <#--<tr style="line-height: 40px;">-->
                                <#--<td style="text-align: right; padding-right: 10px;">手机号码:</td>-->
                                <#--<td colspan="2">${(defaultAddress.phone)!}</td>-->
                            <#--</tr>-->
                            <#--<tr style="line-height: 40px;">-->
                                <#--<td style="text-align: right; padding-right: 10px;">收货地址:</td>-->
                                <#--<td colspan="2">${(defaultAddress.provinceName+defaultAddress.cityName+defaultAddress.areaName)!}</td>-->
                            <#--</tr>-->
                            <#--<tr style="line-height: 40px;">-->
                                <#--<td style="text-align: right; padding-right: 10px;">详细地址:</td>-->
                                <#--<td colspan="2">${(defaultAddress.detail)!}</td>-->
                            <#--</tr>-->
                        <#--</table>-->
                    <#--</form>-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                    <#--<button type="button" class="btn btn-default js_close_modal">确定</button>-->
                    <#--<a href="${ctx}/userinfo/addaddr.html" class="btn btn-primary" type="submit">修改</a>-->
                <#--</div>-->
            <#--<#else>-->
                <#--<div class="modal-footer">-->
                    <#--<a href="${ctx}/userinfo/addaddr.html" class="btn btn-primary" type="submit">新增收货地址</a>-->
                <#--</div>-->
            <#--</#if>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->


<#--</body>-->
<html>
</@compress>