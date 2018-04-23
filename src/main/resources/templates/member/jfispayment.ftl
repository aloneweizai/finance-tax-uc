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
<body>
<div class="huiyuan_title">积分充值订单
    <a href="${ctx}/integral/integral_payment.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>积分充值</a>
</div>
<div class="clear"></div>
<div class="content_box">
    <div class="content clearfix ">
        <div style="font-size: 16px;  "><img src="${ctx}/images/hyjfcz.jpg"></div>
        <div>
            <div>
                <table border="0" width="300">
                    <tr>
                        <td style="text-align:right; width:90px;">商品描述：</td>
                        <td>积分充值</td>
                    </tr>
                    <tr class="scj">
                        <td style="text-align:right; width:90px;">原价：</td>
                        <td><span style="color: #F34948;text-decoration:line-through;"><b >${jine?string("0.00")}</b></span>元</td>
                    </tr>
                    <tr class="xsj">
                        <td style="text-align:right; width:90px;">优惠价：</td>
                        <td><span style="color: #F34948;"><b>${jine?string("0.00")}</b></span>元</td>
                    </tr>
                    <tr class="xsj">
                        <td style="text-align:right; width:90px;">优惠：</td>
                        <td><span style="color: #F34948;"><b>${yhje?string("0.00")}</b></span>元</td>
                    </tr>
                    <tr class="xsj">
                        <td style="text-align:right; width:90px;">实付金额：</td>
                        <td><span style="color: #F34948;"><b>${sfje?string("0.00")}</b></span>元</td>
                    </tr>
                    <#if coupon??&&(coupon?size>0)>
                        <tr class="xsj">
                            <td style="text-align:right; width:90px;">优惠券：</td>
                            <td><select>
                                    <#list coupon as list>
                                        <option value="${list.couponId!""}">${list.couponName!""}</option>
                                    </#list>
                            </select></td>
                        </tr>
                    </#if>
                    <tr class="zs">
                        <td style="text-align:right; width:90px;">赠送积分：</td>
                        <td><span style="color: #F34948;"><b>${zsjf!0}</b></span>分</td>
                    </tr>
                    <tr class="tj">
                        <td style="text-align:right; width:90px;">推荐人：</td>
                        <td><input id="tjr" type="text"> <span style="color: #F34948;">注:(请输入推荐人工号或手机号)</span></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div style="border: 1px solid #E3E3E3; margin-top: 10px; margin-bottom: 10px;"></div>
    <div class="zhu">
        <input name="pay-submit" type="button" id="submit_pay_button" guigeid="${goodsid!""}" jine="${jine!0}" class="btn-info"  value="下一步"></div>
</div>
</div>

<script data-main="${ctx}/js/abc/member/jfispayment" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>