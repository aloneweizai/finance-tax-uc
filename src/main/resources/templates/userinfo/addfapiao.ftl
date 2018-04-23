<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>财税专家UC</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr td,th{
            line-height: 40px;
            font-size: 12px;
        }
        .fonsize{
            text-align: right;
        }
    </style>
</head>

<body >
<div class="grzx_main_rt">索取发票收件地址
    <a href="${ctx}/userinfo/userinvoice.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的发票</a>
</div>
<div class="advertising" style="height: 40px;margin-left: 20px;margin-right: 20px">
    <i style="margin-left:20px;color: #00BFFF">开电子发票，微信扫一扫，可以领取2~5元微信红包哦</i>
    <div class="shutd">
        <a href="javascript:void (0)" id="hide">
            <i class="iconfont"> &#xe687;</i>
        </a>
    </div>
</div>

<div class="grzx_main_bjgrzl">
    <form name="form1" class="layui-form" id="formfapiao" >
        <table class="layui-table tables">
        <#--<tr>-->
        <#--<td class="fonsize">发票类型 </td>-->
        <#--<td>-->
        <#--<#if fplx??&&(fplx?size>0)>-->
        <#--<#list fplx as data>-->
        <#--<label>-->
        <#--<input type="radio" name="type" checked="checked" value="${data.fieldValue!}" />${data.fieldKey!}</label>-->
        <#--</#list>-->
        <#--</#if>-->
        <#--</td>-->
        <#--</tr>-->
            <tr>
                <td class="fonsize">发票抬头 </td>
                <td>
                <#if fptt??&&(fptt?size>0)>
                    <#list fptt as data>
                        <label class="fptt">
                            <input type="radio" name="name" checked="checked" value="${data.fieldValue!}"  title="${data.fieldKey!}"/></label>
                        <#if data_index!=0&&data_index%2==0>
                            <br>
                        </#if>
                    </#list>
                </#if>
                    <span style="color: red;font-size: 14px;">*</span>
                </td>
            </tr>
            <tr name="gs">
                <td width="100" class="fonsize">纳税人识别号</td>
                <td>
                    <input type="text" size="30" name="nsrsbh" id="nsrsbh" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'')" /><span style="color: red;font-size: 14px;">*</span>
                </td>
            </tr>
            <tr name="gs">
                <td class="fonsize">公司名称 </td>
                <td>
                    <input type="text" size="30" name="nsrmc" id="compName" /><span style="color: red;font-size: 14px;">*</span>
                    <input type="hidden" size="30" name="orderstrNos" value="${ids}" />
                    <input type="hidden" size="30" name="amount" value="${je}" />
                    <input type="hidden" size="30" name="jesList" value="${jes}" />
                </td>
            </tr>
            <tr name="gs">
                <td width="100" class="fonsize">注册地址</td>
                <td>
                    <input type="text" size="30" name="address" id="address" />
                </td>
            </tr>
            <tr name="gs">
                <td class="fonsize">注册电话 </td>
                <td>
                    <input type="text" size="30" name="phone" id="phone" />
                </td>
            </tr>
            <tr name="gs">
                <td width="100" class="fonsize">开户银行</td>
                <td>
                    <input type="text" size="30" name="bank" id="bank" />
                </td>
            </tr>
            <tr>
                <td class="fonsize">发票内容 </td>
                <td>
                <#if fpnr??&&(fpnr?size>0)>
                    <#list fpnr as data>
                        <label>
                            <input type="radio" name="content" checked="checked" value="${data.fieldValue!}" title="${data.fieldKey!}"/></label>
                        <#if data_index!=0&&data_index%2==0>
                            <br>
                        </#if>
                    </#list>
                </#if>
                    <span style="color: red;font-size: 14px;">*</span>
                </td>
            </tr>
            <tr>
                <td class="fonsize">发票性质 </td>
                <td>
                    <label class="fpxz">
                        <input type="radio" name="property" checked="checked" value="2" title="电子发票"/></label>
                    <label class="fpxz">
                        <input type="radio" name="property"  value="1"  title="纸质发票"/></label>
                    <span style="color: red;font-size: 14px;">*</span>
                </td>
            </tr>
            <tr id="email">
                <td class="fonsize">邮箱 </td>
                <td>
                    <input type="text" size="30" name="email" id="emails" /><span style="color: red;">历史发票提供下载</span>
                </td>
            </tr>
            <tr id="fapiaodizhi" style="display: none">
                <td class="fonsize">发票地址 </td>
                <td style="position: relative">
                <#if addresslist??&&(addresslist?size>0)>
                    <#list addresslist as list>
                        <#if list_index!=0>
                            <br>
                        </#if>
                        <label class="fonsize">
                            <input type="radio" name="addressId" value="${list.id!}"
                                <#if list.isDefault??&&list.isDefault>
                                   checked="checked"
                                </#if>
                                   title="${list.name!}<#if list.dizhi??>${list.dizhi.province.province!""} </#if ><#if list.dizhi??>${list.dizhi.city.city!""} </#if> <#if list.dizhi??>${list.dizhi.area.area!""} </#if>${list.detail!}"/> </label>
                    </#list>
                </#if>
                    <a href="javascript:;" id="upddizhis" style="color: blue; position: absolute; top: 10px; right: 10px;  display: block;" class="layui-btn  layui-btn-primary layui-btn-mini">
                    <#if addresslist??&&(addresslist?size>0)>
                        修改地址
                    <#else>
                        新增地址
                    </#if>
                    </a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="layui-btn" id="fapiaotijiao">提交</button>
</div>



<script data-main="${ctx}/js/abc/fapiao" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>