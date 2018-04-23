<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx=request.getContextPath()>
<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单积分结算页-财税专家</title>
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link type="text/css" rel="stylesheet" media="screen and (min-width:800px)" href="${ctx}/css/settlement.css"/>
    <link type="text/css" rel="stylesheet" media="screen and (max-width:799px)" href="${ctx}/css/settlement-min.css"/>
    <!--[if IE 8]>
    <link type="text/css" rel="stylesheet"  href="${ctx}/css/settlement.css"/>
    <![endif]-->
    <script>
        var ctx="${ctx}";
    </script>
</head>

<body style="background: #fff;position:relative;">
<!-- head -->
<div class="xxhd-head">
    <div class="clearfix pay_width">
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
<div class="ddzf-xxhd-main ddzf-zfgm-main pay_width" >
    <div class="xxhd-tsxx clearfix "><i class="iconfont">&#xe638;</i><span class="fl">温馨提示：积分支付，不能退换货，7个工作日内完成订单，如有疑问请咨询:400-887-3133</span></div>
    <div class="xxhd-ddxx clearfix">
        <div class="xxhd-ddxx-bt">
            填写并核对订单信息
        </div>
        <div class="xxhd-ddxx-content">
            <#if data.orderBoReq.isShipping==1>
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
                    <div >更多地址<i class="iconfont icon-xiangxia"></i></div>
                </div>
            </div>
    </#if>
            <div class="xxhd-ddxx-shqd">
                <div class="xxhd-ddxx-title">
                    商品清单
                </div>
                <div class="xxhd-shqd-content">
                    <ul class="xxhd-shqd-spxx">
                        <#if data.orderBoReq.orderProductBOList??&&(data.orderBoReq.orderProductBOList?size>0)>
                            <#list data.orderBoReq.orderProductBOList as list>
                                <li class="clearfix">
                                    <div class="shqd-spxx-img fl">
                                        <img src="${picurl!""}${list.imageUrl!""}" alt="">
                                    </div>
                                    <div class="shqd-spxx-name fl">
                                        <div>${list.name!""} [规格:${list.specInfo!""}]</div>
                                    </div>
                                    <div class="shqd-spxx-num">
                                        &Chi;${list.num!1}
                                    </div>
                                    <div class="shqd-spxx-price fr">
                                        <span><i class="iconfont">&#xe814;</i>${list.dealPrice?string("0.00")}</span>
                                    </div>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                    <div class="xxhd-shqd-bz">
                        <div class="shqd-bz-title">添加订单备注</div>
                        <div class="shqd-bz-xxxx">
                            <input class="shqd-bz-sm" type="text" id="bz" placeholder="限45个字(定制商品，请将购买需求在备注中做详细说明)">
                            <span class="shqd-bz-ts">
										提示:请勿填写有关支付、收货、发票方面的信息
									</span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="xxhd-ddxx-jejs clearfix">
            <div class="ddxx-jejs-lm fr" >
                <div class="jejs-lm-zje"><span id="zje"><i class="iconfont">&#xe814;</i>${data.orderBoReq.totalPrice?string("0.00")}</span></div>
            </div>
            <div class="ddxx-jejs-price fr">
            <#if data.orderBoReq.orderProductBOList??&&(data.orderBoReq.orderProductBOList?size>0)>
                <div class="ddxx-jejs-zje"><span>${data.orderBoReq.orderProductBOList?size}</span>件商品，总商品积分：</div>
            <#else>
                <div class="ddxx-jejs-zje"><span>0</span>件商品，总商品积分：</div>
            </#if>
            </div>

        </div>
        <div class="xxhd-ddxx-tjdd clearfix">
            <div class="ddxx-jejs-price">
                <div class="ddxx-jejs-zje2">应付总积分：<span id="zyfje"><i class="iconfont">&#xe814;</i>${data.orderBoReq.totalPrice?string("0.00")}</span></div>
            </div>
        <div class="ddxx-jejs-shrxx clearfix" id="shdz">
        <#if data.orderBoReq.isShipping==1>
            <#if addresslist??&&(addresslist?size>0)>
                <#list addresslist as list>
                    <#if list_index==0>
                        <div class="jejs-shrxx-num">
                            收 货 人：<span>${list.name!""}</span><br>
                            联系电话：<span>${list.phone!""}</span><br>
                            收货地址：
                            <span>${list.provinceName!""}</span>
                            <span>${list.cityName!""}${list.areaName!""}</span>
                            <span>${list.detail!""}</span>
                        </div>
                    </#if>
                </#list>
            </#if>
        </#if>
        </div>
        </div>
        <div class="ddxx-jejs-tjdd">
            <button type="button" lshid="${data.lsh!""}" id="orderdd" isShipping="${data.orderBoReq.isShipping!2}" zje="${data.orderBoReq.totalPrice?string("0.00")}">提交订单</button>
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
<!-- footer end -->
<!-- Modal -->
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
                <select name="add_shen" id="add_shen" data-target="#addmsg">
                    <option value="">请选择</option>
                </select>
            </div>
            <div>
                <select name="add_shi" id="add_shi" data-target="#addmsg">
                    <option value="">请选择</option>
                </select>
            </div>
            <div>
                <select name="add_qu" id="add_qu" data-target="#addmsg">
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
                <select name="updaddress_shen" id="updaddress_shen">
                    <option value="">湖南省</option>
                </select>
            </div>
            <div>
                <select name="updaddress_shi" id="updaddress_shi">
                    <option value="">长沙市</option>
                </select>
            </div>
            <div>
                <select name="updaddress_qu" id="updaddress_qu">
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
<!-- Modal end-->
<script data-main="${ctx}/js/abc/pay/integral_settlement" src="${ctx}/js/require.js"></script>
</body>

</html>