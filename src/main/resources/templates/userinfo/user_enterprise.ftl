<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<head>
    <title>个人资料</title>
    <#include "../common/meta.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <!--上传头像-->
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        table tr{
            line-height: 30px;
        }
        table tr td,th{
            font-size: 12px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="grzx_main_rt">我的纳税企业</div>
<!-- 右侧导航 -->

<div class="grzx_main_rt_nav">
    <ul>
        <li class="hover">ABC电子申报企业绑定 </li>
        <li>国税电子税局企业绑定</li>
        <li>地税网上办税厅企业绑定</li>
        <div class="clear"></div>
    </ul>
</div>
<!-- 我的关注 -->
<div class="grzx_wdjf_dhjl page_main_xxk">
    <span style="float: left; margin-top: 5px; margin-bottom: 10px" class="layui-form">
        企业名称：<input type="text" id="dszbname">
        纳税人识别号：<input type="text" id="dszbnsrsbh">
        分组<a href="javascript:;" id="fz"><i class="iconfont">&#xe695;</i></a>：<input type="text" id="bdgroup">
        <a href="javascript:;" style="margin-top: 0px;" id="dzsbquery" class="nsqy_xzbd layui-btn">查询</a>
    </span>
    <a href="javascript:;" id="addmodal" class="nsqy_xzbd layui-btn" phone="${data.phone!}">新增绑定</a>
    <#--<a href="user_swsbadd.html" class="nsqy_xzbd" >新增绑定</a>-->
    <div id="abcdzsb">
        <table class="layui-table" lay-size="sm">
            <tr>
                <th width="6%">序号</th>
                <th width="12%">社会信用代码</th>
                <th width="12%">纳税号</th>
                <th width="12%">纳税人名称</th>
                <#--<th width="12%">所属税务机关</th>-->
                <#--<th width="12%">税务登记时间</th>-->
                <th width="11%">软件到期日</th>
                <th width="14%">软件延期到期日</th>
                <th width="14%">分组</th>
                <#--<th width="11%">绑定时间</th>-->
                <th width="12%">操作</th>
            </tr>
        <#if userdzsb??&&userdzsb.dataList??&&(userdzsb.dataList?size>0)>
            <#list userdzsb.dataList as data>
                <tr>
                    <td><#if page!=0>${15*(page-1)+data_index+1}<#else>${data_index+1}</#if></td>
                    <td>${data.shxydm!}</td>
                    <td>${data.nsrsbh!}</td>
                    <td><a href="javascript:;" type="dzsb" dzsb-id="${data.id!""}">${data.nsrmc!}</a></td>
                    <#--<td>${data.swjgMc!}</td>-->
                    <#--<td>${(data.expireTime?string('yyyy-MM-dd'))!""}</td>-->
                    <td>
                        <#if (data.expireTime?date)?? &&((data.expireTime?date) gt (.now?date))>
                            ${(data.expireTime?string('yyyy-MM-dd'))!""}
                        <#else>
                            <span style="color: red;">${(data.expireTime?string('yyyy-MM-dd'))!""}</span>
                        </#if>
                    </td>
                    <td>${(data.expandExpireTime?string('yyyy-MM-dd'))!""}</td>
                    <td>${data.bdgroup!""}</td>
                    <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
                    <td><a href="javascript:;" data-type="dzsb" data-id="${data.id!}" name="del" data-msg="ABC电子申报"><i class="iconfont"></i>解绑</a>|
                        <a href="javascript:;" type="update" nsrsbh="${data.nsrsbh!}"><i class="iconfont"></i> 更新</a>|
                        <a href="javascript:;" id="qyxf" nsrsbh="${data.nsrsbh!}"><i class="iconfont"></i> 续费</a>|
                        <a href="javascript:;" group="bd" nsrsbh="${data.id!}" fz-value="${data.bdgroup!""}"><i class="iconfont"></i> 分组</a></td>
                </tr>
            </#list>
        </#if>
        </table>
        <div id="demo0" index="${page}" count="${count}" style="text-align: right;"></div>
    </div>

</div>
<div class="grzx_wdjf_dhjl page_main_xxk" style="display: none;">
    <span style="float: left; margin-top: 5px; margin-bottom: 10px">
        企业名称：<input type="text" id="gsname">
        纳税人识别号：<input type="text" id="gsnsrsbh">
        <a href="javascript:;" style="margin-top: 0px;" id="gsquery" class="nsqy_xzbd layui-btn">查询</a>
    </span>
    <a href="javascript:;" id="addmodal1" class="nsqy_xzbd layui-btn" phone="${data.phone!}">新增绑定</a>
    <#--<a href="user_swwsadd.html" class="nsqy_xzbd" >新增绑定</a>-->
    <div id="abcgs" style="width: 100%;text-align: right;">
        <table class="layui-table" lay-size="sm">
            <tr>
                <th width="6%">序号</th>
                <th width="12%">办税人员类型 </th>
                <th width="12%">纳税号</th>
                <th width="12%">纳税人名称</th>
                <th width="11%">所属税务机关</th>
                <#--<th width="14%">实名办税核验状态</th>-->
                <#--<th width="11%">绑定时间</th>-->
                <th width="10%">操作</th>
            </tr>
        <#if userhngs??&&userhngs.dataList??&&(userhngs.dataList?size>0)>
            <#list userhngs.dataList as data>
                <tr>
                    <td><#if page1!=0>${15*(page1-1)+data_index+1}<#else>${data_index+1}</#if></td>
                    <td>
                        <#if data.roleId??&&data.roleId=='R0001'>
                            办税员01
                        <#elseif data.roleId??&&data.roleId=='R0002'>
                            办税员02
                        <#elseif data.roleId??&&data.roleId=='R0003'>
                            办税员03
                        </#if>
                    </td>
                    <td><a>${data.nsrsbh!}</a></td>
                    <td><a href="javascript:;" type="gsdz" gsdz-id="${data.id!""}">${data.nsrmc!}</a></td>
                    <td>${data.swjgMc!}</td>
                    <#--<td>${data.smrzzt!}</td>-->
                    <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
                    <td><a href="javascript:;" data-type="hngs" data-id="${data.id!}" name="del"  data-msg="国税电子税局"><i class="iconfont"></i>解绑</a></td>
                </tr>
            </#list>
        </#if>
        </table>
        <div id="demo1" index="${page1}" count="${count1}" style="text-align: right;"></div>
    </div>
</div>
<div class="grzx_wdjf_dhjl page_main_xxk" style="display: none;">
    <span style="float: left; margin-top: 5px;  margin-bottom: 10px">
        企业名称：<input type="text" id="dsname">
        纳税人识别号：<input type="text" id="dsnsrsbh">
        <a href="javascript:;" style="margin-top: 0px;" id="dsquery" class="nsqy_xzbd layui-btn">查询</a>
    </span>
    <#--<a href="user_swdsadd.html" class="nsqy_xzbd" >新增绑定</a>-->
    <a href="javascript:;" class="nsqy_xzbd layui-btn" id-name="myModal2" from-id="form2">新增绑定</a>
        <div id="abcds" style="width: 100%;text-align: right;">
            <table class="layui-table" lay-size="sm">
                <tr>
                    <th width="6%">序号</th>
                    <th width="14%">纳税人识别号 </th>
                    <th width="14%">用户名 </th>
                    <th width="14%">子用户</th>
                    <th width="14%">纳税人名称</th>
                    <#--<th width="14%">所属税务机关</th>-->
                    <#--<th width="14%">绑定时间</th>-->
                    <th width="10%">操作</th>
                </tr>
            <#if userhnds??&&userhnds.dataList??&&(userhnds.dataList?size>0)>
                <#list userhnds.dataList as data>
                    <tr>
                        <td><#if page2!=0>${15*(page2-1)+data_index+1}<#else>${data_index+1}</#if></td>
                        <td>${data.shxydm!}</td>
                        <td>${data.username!""}</td>
                        <td>${data.subuser!}</td>
                        <td><a href="javascript:;" type="dswt" dswt-id="${data.id!""}">${data.nsrmc!}</a></td>
                        <#--<td>${data.swjgMc!}</td>-->
                        <#--<td>${(data.createTime?string('yyyy-MM-dd'))!}</td>-->
                        <td><a href="javascript:;" data-type="hnds" data-id="${data.id!}" name="del"   data-msg="地税网上办税厅"><i class="iconfont"></i>解绑</a></td>
                    </tr>
                </#list>
            </#if>
            </table>
            <div id="demo2" index="${page2}" count="${count2}" style="text-align: right;"></div>
        </div>
</div>

<#--新增国税电子申报用户绑定-->
<div class="modal" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增国税电子申报企业绑定</h4>
            </div>
            <div class="modal-body">
                <div class="grzx_main_bjgrzl">
                    <form name="form1" id="form3">
                        <table class="layui-table" lay-size="sm">
                            <tr>
                                <th style="text-align: right; padding-right: 10px;">纳税号/社会信用代码</th>
                                <td style="text-align: left">
                                    <input type="text" name="bsy"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: right; padding-right: 10px;">密码 </th>
                                <td style="text-align: left">
                                    <input type="password" name="password"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: right; padding-right: 10px;">办税角色 </th>
                                <td style="text-align: left">
                                    <select style="width: 150px;" name="role">
                                        <option value="R0001">办税员01</option>
                                        <option value="R0002">办税员02</option>
                                        <option value="R0003">办税员03</option>
                                    </select>
                                </td>
                            </tr>
                            <tr style="line-height: 40px;">
                                <td width="100" style="text-align: right; padding-right: 10px;">手机号码</td>
                                <td style="text-align: left;">
                                ${data.phone!}
                                    <input id="disabledInputt" type="hidden" value="${data.phone!}" >
                                </td>
                            </tr>
                            <tr style="line-height: 40px;">
                                <td style="text-align: right; padding-right: 10px;">当前手机号 </td>
                                <#--<td>-->
                                    <#--<input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/>-->
                                    <#--<input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" type-name="添加纳税企业" value="发送验证码" sms-phone-id="disabledInputt" sms-verification="form3"  type="button">-->
                                <#--</td>-->
                                <td>
                                    <input type="text" id="smrzcode" name="code" maxlength="11" size="30" style="width: 140px; margin-top: 3px; float: left;"/>
                                </td>
                            </tr>
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
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModal1">关闭</button>
                <button type="button" class="btn btn-primary" id="tijiaogs" id-name="myModal1">提交</button>
            </div>
        </div>
    </div>
</div>
<#--新增地税网上办税厅用户绑定-->
<div class="modal" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增地税网上办税厅企业绑定</h4>
            </div>
            <div class="modal-body">
                <div class="grzx_main_bjgrzl">
                    <form name="form1" id="form2">
                        <table class="layui-table" lay-size="sm">
                            <tr>
                                <td style="text-align: right; padding-right: 10px;">纳税人名称</td>
                                <td style="text-align: left">
                                    <select name="nsrmc">
                                        <#if dzsblist??&&dzsblist.dataList?? &&(dzsblist.dataList?size>0)>
                                            <#list dzsblist.dataList as data>
                                                <option value="${data.nsrmc!""}">${data.nsrmc!""}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; padding-right: 10px;">用户名</td>
                                <td style="text-align: left">
                                    <input type="text" name="subuser"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; padding-right: 10px;">子用户账号</td>
                                <td style="text-align: left">
                                    <input type="text" name="username"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right; padding-right: 10px;">子用户密码 </td>
                                <td style="text-align: left">
                                    <input type="password" name="password"/>
                                </td>
                            </tr>
                            <tr style="line-height: 40px;">
                                <td width="100" style="text-align: right; padding-right: 10px;">手机号码</td>
                                <td style="text-align: left;">
                                ${data.phone!}
                                    <input id="disabledInputt" type="hidden" value="${data.phone!}" >
                                </td>
                            </tr>
                            <tr style="line-height: 40px;">
                                <td style="text-align: right; padding-right: 10px;">当前手机号 </td>
                                <#--<td>-->
                                    <#--<input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/>-->
                                    <#--<input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" type-name="添加纳税企业" value="发送验证码" sms-phone-id="disabledInputt" sms-verification="form2" type="button">-->
                                <#--</td>-->
                                <td>
                                    <input type="text" id="smrzcode" name="code" maxlength="11" size="30" style="width: 140px; margin-top: 3px; float: left;"/>
                                </td>
                            </tr>
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
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModal2">关闭</button>
                <button type="button" class="btn btn-primary" id="tijiao" id-name="myModal2">提交</button>
            </div>
        </div>
    </div>
</div>
<#--新增ABC电子申报用户绑定-->
<div class="modal" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增ABC电子申报企业绑定</h4>
            </div>
            <div class="modal-body">
                <form name="form1" id="form1" >
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
                        <tr>
                            <th style="text-align: right; padding-right: 10px;">分组</th>
                            <td style="text-align: left;height: 20px;" valign="middle">
                                <select id="selectgroup" style="height: 28px;width: 153px;">
                                    <option value=""></option>
                                </select>
                                <input type="text" name="bdgroup" style="display: none;"/>
                                <input type="checkbox" id="addgroup" style="height: 13px">新增分组
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: right; padding-right: 10px;">备注</th>
                            <td style="text-align: left">
                                <input type="text" name="remark"/>
                            </td>
                        </tr>
                        <tr style="line-height: 40px;">
                            <td width="100" style="text-align: right; padding-right: 10px;">手机号码</td>
                            <td style="text-align: left;">
                            ${data.phone!}
                                <input id="disabledInputt" type="hidden" value="${data.phone!}" >
                            </td>
                        </tr>
                        <tr style="line-height: 40px;">
                            <td style="text-align: right; padding-right: 10px;">当前手机号 </td>
                        <#--<td>-->
                        <#--<input type="text" id="smrzcode" name="code" maxlength="6" size="30" style="width: 140px; margin-top: 3px; float: left;"/>-->
                        <#--<input class="btn btn-default" sms-id="yzm"  style="height: 30px; line-height: 30px; margin-left: 10px; padding: 0 10px; float: left;" value="发送验证码" type-name="添加纳税企业" sms-phone-id="disabledInputt" sms-verification="form1" type="button">-->
                        <#--</td>-->
                            <td>
                                <input type="text" id="smrzcode" name="code" maxlength="11" size="30" style="width: 140px; margin-top: 3px; float: left;"/>
                            </td>
                        </tr>
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModal3">关闭</button>
                <button type="button" class="btn btn-primary" id="tijiaodzsb" id-name="myModal3">提交</button>
            </div>
        </div>
    </div>
</div>

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
                            <td colspan="2"><input style="width: 90%" name="frmc" value=""></td>
                        </tr>
                        <tr style="line-height: 40px;">
                            <td style="text-align: right; padding-right: 10px;">法人代表证件号码:</td>
                            <td colspan="2"><input style="width: 90%" name="frzjh" value=""></td>
                        </tr>
                        <tr style="line-height: 40px;">
                            <td style="text-align: right; padding-right: 10px;">您的手机号码:</td>
                            <td colspan="2"> ${data.phone!}
                                <input id="disabledInputt" type="hidden" value="${data.phone!}" >
                            </td>
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
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModal">关闭</button>
                <button id = "dzsbforgot" class="btn btn-primary" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
<div tabindex="-1" class="modal fade" id="myModal123" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">纳税企业详情</h4>
            </div>
            <div class="modal-body" id="nsqyxq">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModal123">关闭</button>
            </div>
        </div>
    </div>
</div>

<div tabindex="-1" class="modal fade" id="myModalgroup" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">修改分组</h4>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <tr style="line-height: 40px;">
                        <td style="text-align: right; padding-right: 10px;">分组:</td>
                        <td colspan="2">
                            <select id="updateselectgroup" style="height: 28px;width: 153px;">
                                <option value=""></option>
                            </select>
                            <input type="text" name="updatebdgroup" style="display: none;"/>
                            <input type="checkbox" id="updateaddgroup" style="height: 13px">新增分组
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" name="guanbi" id-name="myModalgroup">关闭</button>
                <button id = "updategroup" class="btn btn-primary" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/user_info_sw" src="${ctx}/js/require.js"></script>
</body>

</html>
</@compress>