<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单结算页-财税专家</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link type="text/css" rel="stylesheet" media="screen and (min-width:800px)" href="${ctx}/css/settlement.css"/>
    <link type="text/css" rel="stylesheet" media="screen and (max-width:799px)" href="${ctx}/css/settlement-min.css"/>
    <!--[if IE 8]>
    <link type="text/css" rel="stylesheet"  href="${ctx}/css/settlement.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script>
        var ctx="${ctx}";
    </script>
</head>

<body style="background: #fff;">
<!-- head -->
<div class="xxhd-head">
    <div class="clearfix pay_width" >
        <div class="xxhd-logo">
            <a href="${cswurl}/index.html" title="网站首页" target="_blank">
                <div class="boximg"><span><img src="${ctx}/images/logo_jiesuan.png"></span></div>
            </a>
        </div>
        <div class="xxhd-gnny fr">
            <div>
                <a target="_blank" href="${cswurl}/index.html"><i class="iconfont" style="padding-right:5px;">&#xe836;</i>返回财税网首页</a>
            </div>
            <div class="gnny-sg">|</div>
            <div class="xxhd-user-name">
                <a href="${ucurl}/index.html" target="_blank">${currentUser.nickname!"匿名"}</a>
            </div>
            <div class="gnny-sg">|</div>
            <div>
                <a href="${ucurl}/index.html?url=${url}" target="_blank">我的订单</a>
            </div>
            <!-- <div>|</div> -->
            <!-- <div>
                <a>支付帮助</a>
            </div> -->
        </div>
    </div>
</div>
<!-- head end -->
<!-- main -->
<div class="ddzf-xxhd-main pay_width">
    <div class="xxhd-tsxx clearfix "><i class="iconfont">&#xe638;</i><span class="fl">温馨提示：订单中商品如果是会员、积分充值、课程视频，购买后不能退款，请确认商品信息后提交订单，半小时内未提交订单，订单自动失效。</span></div>
    <div class="xxhd-ddxx clearfix">
        <div class="xxhd-ddxx-bt">
            填写并核对订单信息
        </div>
        <div class="xxhd-ddxx-content">
            <#if data.newOrderBo.isShipping==1>
                <div class="xxhd-ddxx-shrxx">
                    <div class="xxhd-content-title">
                        <div class="xxhd-title-shr">收货人信息</div>
                        <div class="xxhd-title-xzshr">新增收货地址</div>
                    </div>
                    <ul class="xxhd-content-dz">
                        <#if addresslist??&&(addresslist?size>0)>
                            <#list addresslist as list>
                                <#if list_index==0>
                                    <li class="clearfix active" addressid="${list.id!""}" address_name="${list.name!""}" address_phone="${list.phone!""}"
                                        address_province="${list.provinceName!""}" address_city="${list.cityName!""}" address_area="${list.areaName!""}" address_detail="${list.detail!""}">
                                        <div class="xxhd-dz-name fl">
                                            <span key="${list.id!""}_name">${list.name!""}</span>
                                            <s class="dz-name-jb"></s>
                                        </div>
                                        <div class="xxhd-dz-xxxx fl" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;" title="${list.provinceName!""}${list.cityName!""}${list.areaName!""}${list.detail!""}">
                                            <span key="${list.id!""}_shen">${list.provinceName!""}</span>
                                            <span key="${list.id!""}_shi">${list.cityName!""}</span>
                                            <span key="${list.id!""}_qu">${list.areaName!""}</span>
                                            <span key="${list.id!""}_xxdz">${list.detail!""}</span>
                                        </div>
                                        <div class="xxhd-dz-num fl" key="${list.id!""}_phone">
                                        ${list.phone!""}
                                        </div>
                                        <div class="xxhd-btn">
                                            <a href="javascript:;" class="xxhd-btn-bj" address_id="${list.id!""}" address_name="${list.name!""}" address_phone="${list.phone!""}"
                                               address_province="${list.province!""}" address_city="${list.city!""}" address_area="${list.area!""}" address_detail="${list.detail!""}">编辑</a>
                                        </div>
                                    </li>
                                <#else>
                                    <li class="clearfix" addressid="${list.id!""}" address_name="${list.name!""}" address_phone="${list.phone!""}"
                                        address_province="${list.provinceName!""}" address_city="${list.cityName!""}" address_area="${list.areaName!""}" address_detail="${list.detail!""}">
                                        <div class="xxhd-dz-name fl">
                                            <span key="${list.id!""}_name">${list.name!""}</span>
                                            <s class="dz-name-jb"></s>
                                        </div>
                                        <div class="xxhd-dz-xxxx fl" style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;  ">
                                            <span  key="${list.id!""}_shen">${list.provinceName!""}</span>
                                            <span  key="${list.id!""}_shi">${list.cityName!""}</span>
                                            <span  key="${list.id!""}_qu">${list.areaName!""}</span>
                                            <span  key="${list.id!""}_xxdz">${list.detail!""}</span>
                                        </div>
                                        <div class="xxhd-dz-num fl"  key="${list.id!""}_phone">
                                        ${list.phone!""}
                                        </div>
                                        <div class="xxhd-btn">
                                            <a href="javascript:;" class="xxhd-btn-bj" address_id="${list.id!""}" address_name="${list.name!""}" address_phone="${list.phone!""}"
                                               address_province="${list.province!""}" address_city="${list.city!""}" address_area="${list.area!""}" address_detail="${list.detail!""}">编辑</a>
                                        </div>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>

                    <div class="xxhd-more-dz">
                        <div>更多地址<i class="iconfont icon-xiangxia"></i></div>
                    </div>
                </div>
            </#if>
            <div class="xxhd-ddxx-shqd">
                <div class="xxhd-ddxx-title">
                    商品清单
                </div>
                <div class="xxhd-shqd-content">
                    <ul class="xxhd-shqd-spxx">
                        <#if data.newOrderBo.orderProductBOList??&&(data.newOrderBo.orderProductBOList?size>0)>
                            <#list data.newOrderBo.orderProductBOList as list>
                                <li class="clearfix">
                                    <div class="shqd-spxx-img fl">
                                        <#if list.tradingChannels!='JFCZ'>
                                            <img src="${list.browseUrl!""}" alt="">
                                        <#else>
                                            <img src="${ctx}/images/jfdh.jpg" alt="">
                                        </#if>
                                    </div>
                                    <div class="shqd-spxx-name fl">
                                        <div>${list.name!""}</div>
                                    </div>
                                    <div class="shqd-spxx-num">
                                        &Chi;${list.num!1}
                                    </div>
                                    <div class="shqd-spxx-price">
                                        <div id="rmbjiage">&yen;<span>${list.dealPrice!0}</span></div>
                                        <#if data.newOrderBo.pointsPrice??&&data.newOrderBo.pointsPrice!='0'>
                                            <div id="jifenjiage" style="display: none;" class="zyfje"><i class="iconfont">&#xe814;</i><span>${data.newOrderBo.pointsPrice!0}</span></div>
                                        </#if>
                                    </div>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                    <div class="xxhd-shqd-bz">
                        <div class="shqd-bz-title">添加订单备注</div>
                        <div class="shqd-bz-xxxx">
                            <input class="shqd-bz-sm" type="text" id="bz" placeholder="限45个字(请将购买需求在备注中做详细说明)">
                        </div>
                    </div>
                </div>
            </div>
                <div id="rmbtype">
            <div class="xxhd-ddxx-fpxx">
                <div class="ddxx-fpxx-title">
                    发票信息
                </div>
                <div class="ddxx-fpxx-content">
                    <span>完成订单后，可以在个人中心【我的发票】模块中索取发票。如有疑问请咨询:400-887-3133</span>
                </div>
            </div>
            <div class="xxhd-ddxx-yh">
                <#--<div class="xxhd-ddxx-title">-->
                    <#--使用优惠券<i class="iconfont icon-zhiding1"></i>-->
                <#--</div>-->
                <#--<div class="ddxx-yh-content">-->
                <#if data.couponDataListBo??&&data.couponDataListBo.dataList??&&(data.couponDataListBo.dataList?size>0)>
                    <div class="xxhd-ddxx-title">
                        使用优惠券<i class="iconfont icon-zhiding1"></i>
                    </div>
                    <div class="ddxx-yh-content">
                    <div class="ddxx-yh-nav">
                        <ul class="clearfix">
                            <li class="active fl">
                                <div>优惠券</div>
                            </li>
                            <#--<li class="fl"><div>礼品卡</div></li>-->
                            <#--<li class="fl"><div>余额</div></li>-->
                        </ul>
                    </div>
                    <div class="ddxx-nav-content">
                        <div class="nav-view">
                            <ul class="ddxx-yh-yhj clearfix">

                                    <#list data.couponDataListBo.dataList as list>
                                        <li class="yh-yhj-li
                                         <#if list.couponType=='MANJIAN'>
                                        <#if list.param1 gt data.newOrderBo.totalPrice>
                                                invalid  fl" style="cursor: not-allowed;"
                                                             <#else>
                                                             fl" yhj="yhjlist"
                                        </#if>
                                    <#elseif list.couponType=='LIJIAN'>
                                        <#if list.param2 gte data.newOrderBo.totalPrice>
                                                invalid  fl" style="cursor: not-allowed;"
                                                <#else>
                                                 fl" yhj="yhjlist"
                                        </#if>
                                    <#elseif list.couponType=='ZHEKOU'>
                                        <#if list.param1??&&list.param1 gt 0>
                                            <#if list.param1 gt data.newOrderBo.totalPrice>
                                                invalid fl" style="cursor: not-allowed;"
                                            <#else>
                                                fl" yhj="yhjlist"
                                            </#if>
                                         <#else>
                                            fl" yhj="yhjlist"
                                        </#if>
                                    </#if>
                                          yhjid="${list.id!""}">
                                            <div class="yh-yhj-content">
                                                <div class="yh-yhj-top">
                                                    <div class="yhj-top-xq">
                                                        <#if list.couponType=='MANJIAN'>
                                                            <div class="top-xq-price">
                                                                &yen;<span>${list.param2!0}</span>
                                                            </div>
                                                            <div class="top-xq-cl">
                                                                <span>满${list.param1!0}</span>
                                                            </div>
                                                        <#elseif list.couponType=='LIJIAN'>
                                                            <div class="top-xq-price">
                                                                &yen;<span>${list.param2!0}</span>
                                                            </div>
                                                        <#elseif list.couponType=='ZHEKOU'>
                                                            <#if list.param1??&&list.param1 gt 0>
                                                                <div class="top-xq-price">
                                                                    <span>${list.param2*10!0}</span>折
                                                                </div>
                                                                <div class="top-xq-cl">
                                                                    <span>满${list.param1!0}</span>
                                                                </div>
                                                            <#else>
                                                                <div class="top-xq-price">
                                                                    <span>${list.param2*10!0}</span>折
                                                                </div>
                                                            </#if>
                                                        </#if>

                                                    </div>
                                                    <div class="yhj-top-yxq">
                                                        有效期至${list.validEndTime?string("yyyy-MM-dd")}
                                                    </div>
                                                </div>
                                                <div class="yh-yhj-bottom">
                                                    <div>&nbsp;</div>
                                                </div>
                                                <s class="yh-yhj-jb"></s>
                                                <#if list.couponType=='MANJIAN'>
                                                    <u class="yh-yhj-mj"></u>
                                                <#elseif list.couponType=='LIJIAN'>
                                                    <u class="yh-yhj-lj"></u>
                                                <#elseif list.couponType=='ZHEKOU'>
                                                    <u class="yh-yhj-zk"></u>
                                                </#if>
                                            </div>
                                        </li>
                                    </#list>

                            </ul>
                        </div>
                        <i class="iconfont nav-left">&#xe700;</i>
                        <i class="iconfont nav-right">&#xe702;</i>
                    </div>
                    <div class="ddxx-yh-syqk">
                        <div class="yh-syqk-left">
                            金额抵用<span id="yhjdyje">&yen;0.00</span>
                        </div>
                        <div class="yh-syqk-right">
                            优惠券<span id="yhjcount">0</span>张，
                            优惠<span id="yhje">0.00</span>元
                        </div>
                    </div>
                <#else>
                    <div class="xxhd-ddxx-title">
                        使用优惠券<i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
                    </div>
                    <div class="ddxx-yh-content" style="display: none;">
                    <div class="ddxx-yh-zwyh">
                        <div>
                            <i class="iconfont">&#xe841;</i>暂无可用优惠券~
                        </div>
                    </div>
                </#if>
                </div>

            </div>
            <#if data.curriculumGiftBoList??&&(data.curriculumGiftBoList?size>0)>
                <div class="xxhd-ddxx-fpxx" style="margin-bottom: 15px;">
            <#else>
                <div class="xxhd-ddxx-fpxx" style="border: 0">
            </#if>
                <div class="ddxx-fpxx-title">
                    推荐人
                </div>
                <div class="ddxx-fpxx-content">
                        <div class="shqd-bz-xxxx">
                            <input class="shqd-bz-sm2" type="text" id="tjr" value="${data.newOrderBo.recommendUser!""}" placeholder="请填写推荐人工号或手机号" maxlength="15"
                                <#if data.newOrderBo.recommendUser??&&data.newOrderBo.recommendUser!="">
                                   disabled="disabled"
                                </#if>
                            > <span>注:(请输入推荐人工号或手机号)</span>
                            <#--<p class="shqd-bz-ts">-->
                                <#--提示:可以删除此提示-->
                            <#--</p>-->
                        </div>
                </div>
            </div>
            <#if data.curriculumGiftBoList??&&(data.curriculumGiftBoList?size>0)>
                <div class="xxhd-ddxx-fpxx" style="border: 0">
                    <div class="ddxx-fpxx-title">
                        购买优惠赠送
                    </div>
                    <div class="ddxx-fpxx-content" >
                        <#list data.curriculumGiftBoList as datas>
                            <div class="" style="margin-bottom: 8px;">
                                <#if datas.operType=="POINTS">
                                    <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}"/>
                                    <span style="margin-left: 10px;">赠送积分：${datas.operValue!0}分</span>
                                <#elseif datas.operType=="COUPON">
                                    <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}"/>
                                    <span style="margin-left: 10px;">赠送优惠券：${datas.msg!""}</span>
                                <#elseif datas.operType=="VIP">
                                    <#if !(currentUser.vipLevel??)>
                                        <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}"/>
                                    <#else>
                                        <#if (currentUser.vipLevel?substring(3,4))?number <= (datas.operValue?substring(3,4))?number>
                                            <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}"/>
                                        <#else>
                                            <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}" disabled="disabled"/>
                                        </#if>
                                    </#if>
                                    <#if datas.operValue=="VIP1">
                                        <span style="margin-left: 10px;">赠送会员：银卡会员</span>
                                    <#elseif datas.operValue=="VIP2">
                                        <span style="margin-left: 10px;">赠送会员：金卡会员</span>
                                    <#elseif datas.operValue=="VIP3">
                                        <span style="margin-left: 10px;">赠送会员：钻石会员</span>
                                    <#elseif datas.operValue=="VIP4">
                                        <span style="margin-left: 10px;">赠送会员：超级会员</span>
                                    </#if>
                                <#elseif datas.operType=="AMOUNT">
                                    <input type="checkbox" value="${datas.giftId}" operSymbol="${datas.operSymbol!""}" operid="${datas.id}"/>
                                    <span style="margin-left: 10px;">赠送礼包金额：${datas.operValue!0}元</span>
                                </#if>
                            </div>
                        </#list>
                    </div>
                </div>
            </#if>
        </div>
                    <div id="zsmsg">
            <#if data.curriculumGiftBoList??&&(data.curriculumGiftBoList?size>0)>
                                <div class="xxhd-tsxx clearfix " >
                                    <i class="iconfont">&#xe638;</i>
                                    <span class="fl">赠送会员：当前用户会员等级高于赠送会员等级平台不予赠送。</span><br>
                                    <span class="fl">赠送礼金：赠送的礼包金额可以在会员中心【会员礼包】模块中进行消费。<a href="${ctx}/member/member_index.html?url=bWVtYmVyX21hbGwuaHRtbA==" target="_blank" style="color: #01AAED;">马上去消费</a></span><br>
                                    <span class="fl">赠送积分：赠送的积分可以在会员中心【积分兑换】模块中进行兑换。<a href="${ctx}/member/member_index.html?url=bWVtYmVyX2ludGVncmFsX3VjLmh0bWw=" target="_blank" style="color: #01AAED;">马上去兑换</a></span>
                                </div>
            </#if>
                    </div>
            </div>
<#if data.newOrderBo.pointsPrice??&&data.newOrderBo.pointsPrice!='0'>
    <div class="bb1 p5" style="text-align: right;margin-top: 15px;font-size: 16px;">
        <label class="checkbox-inline ml15">
            <input type="checkbox" id="points" style="width: 17px;height: 17px;">使用积分购买 (您的可用积分: <span class="zyfje"><i class="iconfont">&#xe814;</i>${currentUser.points!"0"}</span>)
        </label>
    </div>
</#if>
                    <div id="RMBtype">
        <div class="xxhd-ddxx-jejs clearfix">
            <div class="ddxx-jejs-lm fr">
                <div class="jejs-lm-zje">&yen;<span id="zje">${data.newOrderBo.totalPrice?string("0.00")}</span></div>
                <#--<div class="jejs-lm-fx">-&nbsp;&yen;<span>0.00</span></div>-->
                <#--<div class="jejs-lm-yf"><i class="iconfont">&#xe6a1;</i>&yen;<span>0.00</span></div>-->
                <#--<div class="jejs-lm-fwf">&yen;<span>0.00</span></div>-->
                <#--<div class="jejs-lm-thwy">&yen;<span>0.00</span></div>-->
                <div class="jejs-lm-spyh">-&nbsp;&yen;<span id="zyhje">${data.yhje?string("0.00")}</span></div>
            </div>
            <div class="ddxx-jejs-price fr">
                <#if data.newOrderBo.orderProductBOList??&&(data.newOrderBo.orderProductBOList?size>0)>
                    <div class="ddxx-jejs-zje"><span>${data.newOrderBo.orderProductBOList?size}</span>件商品，总商品金额：</div>
                <#else>
                    <div class="ddxx-jejs-zje"><span>0</span>件商品，总商品金额：</div>
                </#if>

                <#--<div  class="ddxx-jejs-fx">返现：</div>-->
                <#--<div  class="ddxx-jejs-yf">运费：</div>-->
                <div  class="ddxx-jejs-spyh">商品优惠：</div>
            </div>

        </div>

        <div class="xxhd-ddxx-tjdd clearfix">
            <div class="ddxx-jejs-lm fr">
                <div class="jejs-lm-zje">&yen;<span id="zyfje">${data.newOrderBo.totalPrice?string("0.00")}</span></div>
            </div>
            <div class="ddxx-jejs-price fr">
                <div class="ddxx-jejs-zje">应付总额：</div>
            </div>
            <#if data.newOrderBo.isShipping==1>
                <#if addresslist??&&(addresslist?size>0)>
                    <#list addresslist as list>
                        <#if list_index==0>
                            <div class="ddxx-jejs-shrxx clearfix fr" id="shdz">
                                <div class="jejs-shrxx-num fr">
                                    收货人：<span>${list.name!""}</span>
                                    <span>${list.phone!""}</span>
                                </div>
                                <div class="jejs-shrxx-xxxx fr">
                                    寄送至：
                                    <span>${list.provinceName!""}</span>
                                    <span>${list.cityName!""}${list.areaName!""}</span>
                                    <span>${list.detail!""}</span>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </#if>
        </div>
                    </div>
                    <div id="Pointstype" style="display: none;">
                        <div class="xxhd-ddxx-jejs clearfix">
                            <div class="ddxx-jejs-lm fr">
                                <div class="jejs-lm-zje"><i class="iconfont">&#xe814;</i><span >${data.newOrderBo.pointsPrice!0}</span></div>
                            <#--<div class="jejs-lm-fx">-&nbsp;&yen;<span>0.00</span></div>-->
                            <#--<div class="jejs-lm-yf"><i class="iconfont">&#xe6a1;</i>&yen;<span>0.00</span></div>-->
                            <#--<div class="jejs-lm-fwf">&yen;<span>0.00</span></div>-->
                            <#--<div class="jejs-lm-thwy">&yen;<span>0.00</span></div>-->
                                <#--<div class="jejs-lm-spyh">-&nbsp;<i class="iconfont">&#xe814;</i><span id="zyhje">${data.yhje?string("0.00")}</span></div>-->
                            </div>
                            <div class="ddxx-jejs-price fr">
                            <#if data.newOrderBo.orderProductBOList??&&(data.newOrderBo.orderProductBOList?size>0)>
                                <div class="ddxx-jejs-zje"><span>${data.newOrderBo.orderProductBOList?size}</span>件商品，总商品积分：</div>
                            <#else>
                                <div class="ddxx-jejs-zje"><span>0</span>件商品，总商品积分：</div>
                            </#if>

                            <#--<div  class="ddxx-jejs-fx">返现：</div>-->
                            <#--<div  class="ddxx-jejs-yf">运费：</div>-->
                                <#--<div  class="ddxx-jejs-spyh">商品优惠积分：</div>-->
                            </div>

                        </div>

                        <div class="xxhd-ddxx-tjdd clearfix">
                            <div class="ddxx-jejs-lm fr">
                                <div class="jfjs-lm-zje"><i class="iconfont">&#xe814;</i><span>${data.newOrderBo.pointsPrice!0}</span></div>
                            </div>
                            <div class="ddxx-jejs-price fr">
                                <div class="ddxx-jejs-zje">应付积分：</div>
                            </div>
                        <#if data.newOrderBo.isShipping==1>
                            <#if addresslist??&&(addresslist?size>0)>
                                <#list addresslist as list>
                                    <#if list_index==0>
                                        <div class="ddxx-jejs-shrxx clearfix fr" id="shdz">
                                            <div class="jejs-shrxx-num fr">
                                                收货人：<span>${list.name!""}</span>
                                                <span>${list.phone!""}</span>
                                            </div>
                                            <div class="jejs-shrxx-xxxx fr">
                                                寄送至：
                                                <span>${list.provinceName!""}</span>
                                                <span>${list.cityName!""}${list.areaName!""}</span>
                                                <span>${list.detail!""}</span>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </#if>
                        </div>
                    </div>
        <div class="ddxx-jejs-tjdd">
            <button type="button" lshid="${data.lsh!""}" yhjid="" isShipping="${data.newOrderBo.isShipping!2}" id="orderdd">提交订单</button>
        </div>
    </div>
</div>
<!-- main end -->
<!-- footer  -->
<div class="xxhd-footer">

    <div class="xxhd-foot w_1200 clearfix">
        <div class="">Copyright © 2005-2015 ABC12366 All Rights Reserved 粤ICP备08111004号-3</div>
    </div>
</div>

<form id="myform">
    <div class="xzdz-modal">
        <div class="xzdz-modal-content">
            <div class="xzdz-modal-close"><i class="iconfont icon-guanbi2">
            </i></div>
            <div class="modal-content-title">新增收货地址</div>

            <div class="xzdz-modal-sjr"><div>收件人：</div><div><input type="text" id="add_name" name="add_name"></div></div>
            <div class="xzdz-modal-dz">
                <div>地址：</div>
                <div>
                    <select name="add_shen" id="add_shen" data-target="#addmsg"  style="width: 150px;">
                        <option value="">请选择</option>
                    </select>
                </div>
                <div>
                    <select name="add_shi" id="add_shi" data-target="#addmsg"  style="width: 150px;">
                        <option value="">请选择</option>
                    </select>
                </div>
                <div>
                    <select name="add_qu" id="add_qu" data-target="#addmsg"  style="width: 150px;">
                        <option value="">请选择</option>
                    </select>
                </div>
                <div>
                    <span id="addmsg"></span>
                </div>
            </div>
            <div class="xzdz-modal-xxdz"><div>详细地址：</div><div><textarea id="add_xxdz" name="add_xxdz"></textarea></div></div>
            <div class="xzdz-modal-dh"><div>电话：</div><div><input type="text" id="add_phone" name="add_phone"></div></div>

            <div class="xzdz-modal-btn"><a href="javascript:;" class="modal-btn-qr" id="savedizhi">确认</a><a href="javascript:;" class="modal-btn-gb">关闭</a></div>
        </div>
    </div>
</form>
<div class="xzdz-modal-bg"></div>
<!-- Modal end-->
<!-- Modal -->
<form id="bj_dizhi">
    <div class="bj-modal">
        <div class="bj-modal-content">

            <div class="bj-modal-close"><i class="iconfont icon-guanbi2">
            </i></div>
            <div class="modal-content-title">编辑收货地址</div>

            <div class="bj-modal-sjr"><div>收件人：</div><div><input type="text" id="updaddress_name" name="updaddress_name"></div></div>
            <div class="bj-modal-dz">
                <div>地址：</div>
                <div>
                    <select name="updaddress_shen" id="updaddress_shen" style="width: 150px;">
                        <option value="">湖南省</option>
                    </select>
                </div>
                <div>
                    <select name="updaddress_shi" id="updaddress_shi" style="width: 150px;">
                        <option value="">长沙市</option>
                    </select>
                </div>
                <div>
                    <select name="updaddress_qu" id="updaddress_qu" style="width: 150px;">
                        <option value="">天心区</option>
                    </select>
                    <input type="hidden" id="updaddress_id">
                </div>
            </div>
            <div class="bj-modal-xxdz"><div>详细地址：</div><div><textarea id="updaddress_xxdz" name="updaddress_xxdz"></textarea></div></div>
            <div class="bj-modal-dh"><div>电话：</div><div><input type="text" id="updaddress_phone" name="updaddress_phone"></div></div>

            <div class="bj-modal-btn"><a href="javascript:;" class="modal-btn-qr" id="tijiao">确认</a><a href="javascript:;" class="modal-btn-gb">关闭</a></div>

        </div>
    </div>
</form>
<div class="bj-modal-bg"></div>

<!-- footer end -->
<script data-main="${ctx}/js/abc/pay/settlement" src="${ctx}/js/require.js"></script>
</body>

</html>