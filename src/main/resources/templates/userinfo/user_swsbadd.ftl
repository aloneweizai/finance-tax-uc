<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<head>
    <title>个人资料</title>
    <#include "../common/meta.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <!--上传头像-->
    <link href="${ctx}/css/head/cropper.min.css" rel="stylesheet">
    <link href="${ctx}/css/head/sitelogo.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr{
            line-height: 40px;
        }
        table tr td,th{
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="grzx_main_rt">
    新增ABC电子申报用户绑定
    <a href="${ctx}/userinfo/enterprise.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的纳税企业</a>
</div>
<div class="clear"></div>
<div class="modal-body">
    <div class="grzx_main_bjgrzl">
        <form name="form1" id="form1">
            <table class="layui-table" lay-size="sm">
                <tr>
                    <td style="text-align: right; padding-right: 10px;">纳税人识别号/社会信用代码</td>
                    <td style="text-align: left">
                        <input type="text" name="nsrsbhOrShxydm" value=""/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; padding-right: 10px;">申报服务密码 </td>
                    <td style="text-align: left">
                        <input type="password" name="fwmm"/>
                        <a href="" class="color_b" id="forgotpwd" style="cursor: pointer;" data-toggle="modal" data-target="#myModal">[忘记密码]</a>
                    </td>
                </tr>
            <#if data.xinphone??&&data.xinphone!=''>
                <tr style="line-height: 40px;">
                    <td width="100" style="text-align: right; padding-right: 10px;">手机号码</td>
                    <td>
                        ${data.phone!}
                        <input id="disabledInputt" type="hidden" value="${data.phone!}" >
                    </td>
                </tr>
                <tr style="line-height: 40px;">
                    <td style="text-align: right; padding-right: 10px;">验证码 </td>
                    <td>
                        <input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/>
                        <input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" type-name="添加纳税企业" value="发送验证码" sms-phone-id="disabledInputt"  type="button">
                    </td>
                </tr>
            <#else>
                <tr style="line-height: 40px;">
                    <td width="100" style="text-align: right; padding-right: 10px;"></td>
                    <td>
                        <a href="${ctx}/userinfo/userinfolist.html#1_0">绑定手机</a>
                        <#--<input class="form-control" style="width: 245px;" name="xphone" id="disabledInputt"  type="text"  >-->
                    </td>
                </tr>
                <#--<tr style="line-height: 40px;">-->
                    <#--<td style="text-align: right; padding-right: 10px;">验证码 </td>-->
                    <#--<td>-->
                        <#--<input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/>-->
                        <#--<input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" value="发送验证码" type-name="添加纳税企业" sms-phone-id="disabledInputt" sms-verification="form1" type="button">-->
                    <#--</td>-->
                <#--</tr>-->
            </#if>
                <input type="hidden" id="smrzstatus" value="<#if tzxx??&&tzxx.validStatus??>${tzxx.validStatus!0}<#else>0</#if>">
            <#if !(tzxx??&&tzxx.validStatus??&&tzxx.validStatus=='2')>
                <tr>
                    <td style="text-align: center; color: red;" colspan="2">
                        温馨提示：如果您的个人信息不符，请先进行 <a href="${ctx}/userinfo/userinfolist.html" style="color: #F34948; font-weight: bold">实名认证</a>
                    </td>
                </tr>
            </#if>
            </table>
        </form>
        <div class="modal-footer">
            <#--<button type="button" class="btn btn-default" id="fanhui" url="/userinfo/enterprise.html?index=2">返回</button>-->
            <button type="button" class="btn btn-primary" id="tijiaodzsb">提交</button>
        </div>
    </div>
</div>

<!--modal-->
<div tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">找回密码</h4>
            </div>
            <div class="modal-body">
                <form name ="dzfgForm" id="dzfgForm">
                    <table class="layui-table" lay-size="sm">
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">纳税人识别号:</td>
                        <td colspan="2"><input style="width: 90%" name="nsrsbh" value=""></td>
                    </tr>
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">法人代表名称:</td>
                        <td colspan="2"><input style="width: 90%" ></td>
                    </tr>
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">法人代表证件号码:</td>
                        <td colspan="2"><input style="width: 90%" ></td>
                    </tr>
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">您的手机号码:</td>
                        <td colspan="2"><input style="width: 90%" ></td>
                    </tr>
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">短信验证码： </td>
                        <td><input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/></td>
                        <td><input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" value="发送验证码" type-name="添加纳税企业" sms-phone-id="disabledInputt" sms-verification="dzfgForm" type="button"></td>
                    </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <#--<button type="button" class="btn btn-default" id="return" url="/userinfo/user_swsbadd.html">返回</button>-->
                <button id = "dzsbforgot" class="btn btn-primary" type="submit">确定</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/user_swsave" src="${ctx}/js/require.js"></script>
</body>

</html>
</@compress>