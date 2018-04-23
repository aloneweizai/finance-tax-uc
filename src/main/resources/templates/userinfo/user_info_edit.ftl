<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>编辑个人资料</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/jedate/skin/jedate.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr td,th{
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="grzx_main_rt">完善资料
    <a href="${ctx}/userinfo/userinfolist.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>基本资料</a>
</div>
<div class="clear"></div>
<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="grzx_main_bjgrzl">
                    <form id="infoedit" name="form1">
                        <table class="layui-table" lay-size="sm">
                            <tr style="line-height: 50px;">
                                <td>用户名 </td>
                                <td>
                                    <#if user.usernameModifiedTimes??&&user.usernameModifiedTimes<=0>
                                        <input type="text" size="30" id="username_" name="username" value="${(user.username)!""}" oninput="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'');"   required/><span style="color: #F34948;">用户名只能修改一次</span>
                                        <#else>
                                        ${(user.username)!""}
                                    </#if>

                                </td>
                            </tr>
                            <tr style="line-height: 50px;">
                                <td>昵称 </td>
                                <td>
                                    <input type="text" size="30" name="nickname" value="${(user.nickname)!""}" required/><span style="color: red">*</span>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;" class="layui-form">
                                <td>性别 </td>
                                <td class="gender">
                                    <#if userextend??&&userextend.validStatus!='2'>
                                        <#if (userextend.sex)??>
                                            <#if (userextend.sex) == "1" >
                                                <label>
                                                    <input style="margin: 4px 4px 0 0;" type="radio" title="男" name="sex" checked="checked" value="1"/></label>
                                                <label>
                                                    <input style="margin: 4px 4px 0 0;" type="radio" title="女" name="sex" style="margin-left: 10px;" value="0"></label>
                                            <#else>
                                                <label>
                                                    <input style="margin: 2px 4px 0 0;" type="radio" title="男" name="sex" style="margin-left: 10px;" value="1"/></label>
                                                <label>
                                                    <input style="margin: 2px 4px 0 0;" type="radio" title="女" name="sex" checked="checked"  value="0"></label>
                                            </#if>
                                        <#else>
                                            <label>
                                                <input type="radio" name="sex" checked="checked" title="男" value="1"/>
                                            </label>
                                            <label>
                                                <input type="radio" name="sex" style="margin-left: 10px;" title="女" value="0">
                                            </label>
                                        </#if>
                                        <span style="color: red">*</span>
                                    <#else>
                                        <#if (userextend.sex)??&&(userextend.sex) == "1" >
                                            男
                                        <#else>
                                            女
                                        </#if>
                                    </#if>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;">
                                <td>出生年月</td>
                                <td>
                                    <#if userextend??&&userextend.validStatus!='2'>
                                        <input id="begintime" type="text" placeholder="请选择出生年月" value="${(userextend.birthday?string("yyyy-MM-dd"))!}"  data-target="#startMsg" name="birthday"  readonly><span style="color: red">*</span>
                                        <span id="startMsg"></span>
                                    <#else>
                                        ${(userextend.birthday?string("yyyy-MM-dd"))!}
                                    </#if>
                                </td>
                                </td>
                            </tr>

                            <tr style="line-height: 50px;" class="layui-form layui-Modifythe">
                                <td>所属行业</td>
                                <td>
                                    <select name="industry">
                                        <#if industrys?? && (industrys?size>0)>
                                            <#list industrys as industry>
                                                <#if industry?? && userextend?? && userextend.occupation?? && industry.fieldValue==userextend.occupation>
                                                    <option value="${industry.fieldValue!}" selected>${industry.fieldKey!}</option>
                                                <#else>
                                                    <option value="${industry.fieldValue!}">${industry.fieldKey!}</option>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </select>
                                    <span style="color: red">*</span>
                                </td>
                            </tr>

                            <tr style="line-height: 50px;">
                                <td>从业时间</td>
                                <td>
                                    <#--<input id ="workYear" type="text" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" name="careerDuration" value="${(userextend.careerDuration)!""}"/>年份-->
                                        <input id="workYear" type="text" placeholder="请选择从业时间" value="${(userextend.careerDuration)!""}" name="careerDuration"  readonly><span style="color: red">*</span>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;" class="layui-form layui-Modifythe">
                                <td>最高学历</td>
                                <td>
                                    <select name="education">
                                        <#if userextend??&&userextend.education??&&userextend.education=='小学'>
                                            <option value="小学" selected>小学</option>
                                        <#else >
                                            <option value="小学" >小学</option>
                                        </#if>
                                        <#if userextend??&&userextend.education??&&userextend.education=='中学'>
                                            <option value="中学" selected>中学</option>
                                        <#else >
                                            <option value="中学" >中学</option>
                                        </#if>
                                        <#if userextend??&&userextend.education??&&userextend.education=='高中'>
                                            <option value="高中" selected>高中</option>
                                        <#else >
                                            <option value="高中" >高中</option>
                                        </#if>
                                        <#if userextend??&&userextend.education??&&userextend.education=='专科'>
                                            <option value="专科" selected>专科</option>
                                        <#else >
                                            <option value="专科" >专科</option>
                                        </#if>
                                        <#if userextend??&&userextend.education??&&userextend.education=='本科'>
                                            <option value="本科" selected>本科</option>
                                        <#else >
                                            <option value="本科" >本科</option>
                                        </#if>
                                        <#--<#if userextend??&&userextend.education??&&userextend.education=='研究生'>-->
                                            <#--<option value="研究生" selected>研究生</option>-->
                                        <#--<#else >-->
                                            <#--<option value="研究生" >研究生</option>-->
                                        <#--</#if>-->
                                        <#if userextend??&&userextend.education??&&userextend.education=='硕士'>
                                            <option value="硕士" selected>硕士</option>
                                        <#else >
                                            <option value="硕士" >硕士</option>
                                        </#if>
                                        <#if userextend??&&userextend.education??&&userextend.education=='博士'>
                                            <option value="博士" selected>博士</option>
                                        <#else >
                                            <option value="博士" >博士</option>
                                        </#if>
                                    </select>
                                    <span style="color: red">*</span>
                                </td>
                            </tr>

                            <tr style="line-height: 50px;">
                                <td>QQ</td>
                                <td>
                                    <input type="text" maxlength="20"  name="qq" value="${(userextend.qq)!""}"/><span style="color: red">*</span>
                                </td>
                            </tr>

                            <tr style="line-height: 50px;">
                                <td>邮箱</td>
                                <td>
                                    <input type="text" maxlength="30"  name="email" value="${(user.regMail)!""}"/><span style="color: red">*</span>
                                </td>
                            </tr>

                            <tr style="line-height: 50px;">
                                <td>居住地区</td>
                                <td>
                                    <select id="s_province" name="province" data-id="${(userextend.province)!""}" data-target="#show" style="width: 120px;"><option value="">请选择</option></select>  
                                    <select id="s_city" name="city" data-id="${(userextend.city)!""}" data-target="#show" style="width: 120px;"><option value="">请选择</option></select>  
                                    <select id="s_county" name="area" data-id="${(userextend.area)!""}" data-target="#show" style="width: 120px;"><option value="">请选择</option></select>
                                    <span style="color: red">*</span>
                                    <div id="show"></div>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;">
                                <td>详细地址</td>
                                <td>
                                    <input type="text" size="40" name="postAddress" value="${(userextend.postAddress)!""}"/><span style="color: red">*</span>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;" class="layui-form layui-Modifythe">
                                <td>兴趣爱好</td>
                                <td>
                                    <select name="tags" style="width:120px;">
                                    <#if goodats?? && (goodats?size>0)>
                                        <#list goodats as goodat>
                                            <#if goodat?? &&userextend?? && userextend.tags?? && goodat.fieldValue==userextend.tags>
                                                <option value="${goodat.fieldValue!}" selected>${goodat.fieldKey!}</option>
                                            <#else>
                                                <option value="${goodat.fieldValue!}">${goodat.fieldKey!}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                    </select>
                                    <span style="color: red">*</span>
                                </td>
                            </tr>
                            <tr style="line-height: 50px;">
                                <td></td>
                                <td>
                                    <input type="button" name="" value="保存修改" class="grzx_btn layui-btn" id="userupdsave">
                                    <input type="reset" name="" value="返回" class="grzx_btn layui-btn" id="fanhui" url="/userinfo/userinfolist.html">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/user_info" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>