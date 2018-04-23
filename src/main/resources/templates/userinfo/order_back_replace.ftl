<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <#include "../common/meta.ftl">
    <title>退换货申请</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="grzx_main_rt">
    退换货信息
    <a href="${ctx}/orderback/back.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>退换货申请</a>
</div>
<div class="clear"></div>
<blockquote class="layui-elem-quote"><a href="javascript:;" data-toggle="modal" data-target="#myModal">退换货须知</a></blockquote>
<table class="layui-table table table-bordered hy_dgxq">
    <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>赠送清单</th>
        <th>购买数量</th>
    </tr>
    <tr>
        <th id="orderNo">${orderNo!}</th>
    <#if productList?? && (productList?size>0)>
        <#list productList as product>
            <th>${product.name!}</th>
            <th>${product.name!"无"}</th>
            <th>${product.num!"1"}</th>
        </#list>
    </#if>
    </tr>
</table>
<div class="tuiHuo">
    <form id="shengqin" enctype="multipart/form-data" class="layui-form" >
        <fieldset class="layui-elem-field layui-field-title">
            <legend>填写退换货信息</legend>
        </fieldset>
       <div hidden="hidden">
            <label class="control-label" for="message-text">订单号：</label>
            <input type="text" name="orderNo" value="${orderNo!}"></input>
        </div>

        <div class="form-group" style="margin-bottom: 5px">
            <label class="control-label" for="recipient-name">服务类型：</label>
            <#if goodsBO?? && goodsBO.isExchange == "0">
                <label class="fwxz">
                    <input type="radio" name="exchangeType" value="1" title="换货"></input>
                </label>
            </#if>
            <#if goodsBO?? && goodsBO.isReturn =="0">
                <label class="fwxz">
                    <input type="radio" name="exchangeType" value="2" checked="true" title="退货"></input>
                </label>
            </#if>
        </div>

        <div style="margin-bottom: 10px">
            <i></i>提交原因：
            <span style="display: inline-block">
                <select style="width:400px;" class="form-control" id="backReason" name ="reason">
                <#if exchangeReason ?? && (exchangeReason?size>0)>
                    <#list exchangeReason as reason>
                        <#if reason_index ==0>
                            <option value="${(reason.fieldValue)!""}" selected>${(reason.fieldKey)!""}</option>
                        <#else>
                            <option value="${(reason.fieldValue)!""}">${(reason.fieldKey)!""}</option>
                        </#if>
                    </#list>
                </#if>
                </select>
            </span>
        </div>
        <div>
            <label class="control-label" for="message-text">详细描述：</label>
            <textarea  name ="userRemark" id="backDescribe" style="width: 405px;vertical-align:top" rows="4"></textarea>
        </div>

        <#--<fieldset>
            <legend>上传要退换的货物图片</legend>
        </fieldset>
        <div>
            <div class="site-demo-upload" style="float:left">
                <img id="LAY_demo_upload" src="${ctx}/images/sfz.png" style="width: 200px;">
                <div class="site-demo-upbar">
                    <input type="file" name="first" class="layui-upload-file" id="first" style="width: 100px;">
                </div>
            </div>
            <div class="site-demo-upload" style="float:left; margin-left:20px;">
                <img id="LAY_demo_upload" src="${ctx}/images/sfz.png" style="width: 200px;">
                <div class="site-demo-upbar">
                    <input type="file" name="second" class="layui-upload-file" id="second" style="width: 100px;">
                </div>
            </div>
            <div class="site-demo-upload" style="float:left; margin-left:20px;">
                <img id="LAY_demo_upload" src="${ctx}/images/sfz.png" style="width: 200px;">
                <div class="site-demo-upbar">
                    <input type="file" name="third" class="layui-upload-file" id="third" style="width: 100px;">
                </div>
            </div>
            <div class="clear"></div>
        </div>-->

        <#if goodsBO?? && goodsBO.isExchange =="0">
        <div class="dizhi" style="margin-top: 10px;display: none" >
            <label></label>收件地址：
                <div>
                <#if addresslist??&&(addresslist?size>0)>
                    <#list addresslist as list>
                        <#if list_index!=0>
                            <br>
                        </#if>
                        <label style="font-size: 14px; ">
                            <input type="radio" name="addressId" value="${list.id!}"
                                <#if list.isDefault??&&list.isDefault>
                                   checked="checked"
                                </#if>
                                   title="${list.name!}<#if list.dizhi??>${list.dizhi.province.province!""} </#if ><#if list.dizhi??>${list.dizhi.city.city!""} </#if> <#if list.dizhi??>${list.dizhi.area.area!""} </#if>${list.detail!}"/> </label>
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
        </#if>

        <div class="modal-footer" style="margin-top: 20px">
            <#--<button type="button" id="fanhui" class="btn btn-default">返回</button>-->
            <button id ="backReplace"  class="layui-btn" order-id="${orderNo!}"  type="button">提交</button>
        </div>
    </form>
</div>

<!--modal-->
<div tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel" align="center" style="color: deepskyblue">退换货规则</h4>
            </div>
            <div class="modal-body">
                <textarea rows="26" style="width: 100%">
    1. 外包装及附件赠品，退换货时请一并退回，如有破损或丢失，请自行寻找相似包装代替。
    2. 关于物流损：请您在收货时务必仔细验货，如发现商品外包装或商品本身外观存在异常，需当场向配送人员指出，并拒收整个包裹；如您在收货后发现外观异常，请在收货24小时内提交退换货申请。如超时未申请，将无法受理。
    3. 关于商品实物与网站描述不符：我司保证所出售的商品均为正品行货，并与时下市场上同样主流新品一致。但因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，所以我们无法确保您收到的货物与商城图片、产地、附件说明完全一致。
    4. 如果您在使用时对商品质量表示置疑，您可出具相关书面鉴定，会按照国家法律规定予以处理。
    5、如果您符合以上退换服务规格，那么在下方填写退换货原因，填写退换地址等即可，填写完成后，点击底部的“提交”即可。
                </textarea>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/order_back_replace" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>