<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <title>个人资料</title>
    <#include "../common/meta.ftl">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">


    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <!--上传头像-->
    <link href="${ctx}/css/head/cropper.min.css" rel="stylesheet">
    <link href="${ctx}/css/head/sitelogo.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <!--[if lt IE 9]>
    <script type="text/javascript"  src="${ctx}/js/abc/util/html5shiv.js"></script>
    <![endif]-->

    <script type="text/javascript">
        var ctx = "${ctx}";
        var snsUrl = "${snsurl}";
    </script>
    <style>

    </style>
</head>
<body>
    <div class="m_zishiying"><#include "../common/top.ftl"></div>
    <div class="grzl_tx m_zishiying">
        <div class="grzx_grxx_l">
            <div class="grzx_grxx_limg">
                <img src="<#if user.userPicturePath??&&user.userPicturePath!=''>${path}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>" style="border-radius: 100px;width: 80px;height: 80px;">
            </div>
        </div>
        <div class="grzx_grxx_c ">
            <table class="grzx_jbxx">
                <tr>
                    <td colspan="2"><span>用户资料</span></td>
                </tr>
                <tr>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <th>昵称：</th>
                    <td> ${(user.nickname)!""}</td>
                </tr>
                <tr>
                    <th>真实姓名：</th>
                    <td>
                        <#if (userextend.realName)??&&userextend.validStatus=='2'>
                                ${(userextend.realName)!""}
                            </#if>
                    </td>
                </tr>
                <tr>
                    <th>出生年月：</th>
                    <td>
                        <#if (userextend.birthday)??>
                                    ${(userextend.birthday)?string("yyyy年MM月dd日")}
                                </#if>
                    </td>
                </tr>
                <tr>
                    <th>居住地区：</th>
                    <td colspan="4"><#if dizhi.province??>${dizhi.province.province!""}</#if ><#if dizhi.city??>${dizhi.city.city!""}</#if> <#if dizhi.area??>${dizhi.area.area!""}</#if>  ${(userextend.postAddress)!""}</td>
                </tr>
            </table>
        </div>
        <div class="clear"></div>
    </div>
    <div class="grzx_main_rt pc_zishiying">基本资料
        <a href="userinfo/userinfolist.html"></a>
    </div>
    <!-- 右侧导航 -->
    <div class="grzx_main_rt_nav">
        <ul>
            <li class="hover">个人资料 </li>
            <li id="aqszTag">安全设置</li>
            <div class="clear"></div>
        </ul>
    </div>
    <!-- 我的关注 -->
    <div class="grzx_main_grxx page_main_xxk">
        <div class="grzl_tx pc_zishiying">
            <div class="grzx_grxx_l">
                <div class="grzx_grxx_limg">
                    <img src="<#if user.userPicturePath??&&user.userPicturePath!=''>${path}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>" style="border-radius: 100px;width: 80px;height: 80px;">
                </div><a href="${ctx}/userinfo/imageeditshow.html" class="layui-btn  layui-btn-primary layui-btn-small "> 修改头像 </a>
            </div>
            <div class="grzx_grxx_c ">
                <table class="grzx_jbxx">
                    <tr>
                        <th width="80px"><p>昵称：</p></th>
                        <td> ${(user.nickname)!""}</td>
                        <th width="80px"><p>真实姓名：</p></th>
                        <td>
                        <#if (userextend.realName)??&&userextend.validStatus=='2'>
                            ${(userextend.realName)!""}
                        </#if>
                        </td>
                        <th width="80px"><p>性别：</p></th>
                        <td>
                        <#if (userextend.sex)??>
                            <#if userextend.sex == "0">
                                女
                            <#else>
                                男
                            </#if>
                        </#if>
                        </td>
                    </tr>
                    <tr style="width: 30%">
                        <th><p>出生年月：</p></th>
                        <td>
                            <#if (userextend.birthday)??>
                                ${(userextend.birthday)?string("yyyy年MM月dd日")}
                            </#if>
                        </td>
                        <th><p>最高学历：</p></th>
                        <td>${(userextend.education)!""} </td>
                        <th><p>从业时间：</th>
                        <td>${(userextend.careerDuration)!""} </td>
                    </tr>
                    <tr>
                        <th><p>QQ：</p></th>
                        <td>${(userextend.qq)!""} </td>
                        <th><p>所属行业：</p></th>
                        <td colspan="3">
                        <#if industrys?? && (industrys?size>0)>
                            <#list industrys as industry>
                                <#if (userextend.occupation)?? && industry.fieldValue== userextend.occupation>
                                    ${industry.fieldKey!}
                                </#if>
                            </#list>
                        </#if>
                        </td>
                    </tr>
                    <tr>
                        <th><p>email：</p></th>
                        <td>${(user.regMail)!""} </td>
                        <th><p>兴趣爱好：</p></th>
                        <td colspan="3">
                        <#if goodats?? && (goodats?size>0)>
                            <#list goodats as goodat>
                                <#if (userextend.tags)?? && goodat.fieldValue== userextend.tags>
                                     ${goodat.fieldKey!}
                                </#if>
                            </#list>
                        </#if>
                        </td>
                    </tr>
                    <tr>
                        <th><p>居住地区：</p></th>
                        <td colspan="4"><#if dizhi.province??>${dizhi.province.province!""}</#if ><#if dizhi.city??>${dizhi.city.city!""}</#if> <#if dizhi.area??>${dizhi.area.area!""}</#if>  ${(userextend.postAddress)!""}</td>
                        <td><a href="infoeditshow.html" class="layui-btn  layui-btn-small">修改完善</a></td>
                    </tr>
                </table>
            </div>
            <div class="clear"></div>
        </div>
        <div class="grzx_grxx_b">
            <div class="bottom-box">
                <ul>

                    <li>
                        <a href="javascript:void (0)" class="color_b" id="xiangqin">
                            <div class="icon-box">
                                <div class="icon">
                                    <i style="font-size: 40px" class="iconfont">&#xe7f1;</i>
                                </div>
                                <div class="text">
                                    <h4>绑定纳税人</h4>
                                    <p>获取更多权限</p>
                                </div>
                            </div>
                            <div class="conn">
                                <i class="iconfont">&#xe7e2;</i>
                            </div>
                        </a>
                    </li>
                    <li><#if user.phone??>
                        <a href="javascript:void (0)" class="color_b" id="phonebangdingup">
                            <div class="icon-box">
                                <div class="icon">
                                    <i style="font-size: 44px" class="iconfont">&#xe7f2;</i>
                                </div>
                                <div class="text">
                                    <h4>绑定手机</h4>
                                    <p>${user.phone!} [点击修改]</p>
                                </div>
                            </div>
                            <div class="conn">
                                <i class="iconfont">&#xe7e2;</i>
                            </div>
                        </a>
                        <#else>
                            <a href="javascript:void (0)" class="color_b" class="color_b" id="phonebangdingnr">
                                <div class="icon-box">
                                    <div class="icon">
                                        <i style="font-size: 44px" class="iconfont">&#xe7f2;</i>
                                    </div>
                                    <div class="text">
                                        <h4>绑定手机</h4>
                                        <p>[立即绑定]</p>
                                    </div>
                                </div>
                                <div class="conn">
                                    <i class="iconfont">&#xe7e2;</i>
                                </div>
                            </a>
                        </#if>

                    </li>
                    <li>
                        <#if user.wxopenid??&& user.wxopenid!=''>
                        <a href="javascript:void (0)" class="color_b" id="weixinbangdingnr">
                            <div class="icon-box">
                                <div class="icon">
                                    <i class="iconfont">&#xe7f3;</i>
                                </div>
                                <div class="text">
                                    <h4>绑定微信</h4>
                                    <p>${user.wxnickname!""}[点击解绑]
                                    </p>
                                </div>
                            </div>
                            <div class="conn">
                                <i class="iconfont">&#xe7e2;</i>
                            </div>
                        </a>
                        <#else>
                            <a href="javascript:void (0)" class="color_b" id="weixinbangdingnr">
                                <div class="icon-box">
                                    <div class="icon">
                                        <i class="iconfont">&#xe7f3;</i>
                                    </div>
                                    <div class="text">
                                        <h4>绑定微信</h4>
                                        <p>[立即绑定]
                                        </p>
                                    </div>
                                </div>
                                <div class="conn">
                                    <i class="iconfont">&#xe7e2;</i>
                                </div>
                            </a>
                        </#if>
                    </li>
                    <li>
                        <a href="javascript:void (0)" class="color_b" id="updatePassword">
                            <div class="icon-box">
                                <div class="icon">
                                    <i class="iconfont">&#xe61e;</i>
                                </div>
                                <div class="text">
                                    <h4>修改密码</h4>
                                    <p>[点击修改]
                                    </p>
                                </div>
                            </div>
                            <div class="conn">
                                <i class="iconfont">&#xe7e2;</i>
                            </div>
                        </a>
                    </li>
                    <li>
                        <div class="icon-box">
                            <div class="icon">
                                <i style="line-height: 50px" class="iconfont">&#xe684;</i>
                            </div>
                            <div class="text">
                                <h4>会员状态</h4>
                                <p><#if vip??>
                                ${vip.level!"普通会员"}
                                <#else>
                                    普通用戶
                                </#if></p>
                            </div>
                        </div>
                    <#--<div class="conn">-->
                    <#--<i class="iconfont">&#xe7e2;</i>-->
                    <#--</div>-->
                    </li>
                    <li>
                            <div class="icon-box">
                                <div class="icon">
                                    <i style="font-size: 40px" class="iconfont">&#xe795;</i>
                                </div>
                                <div class="text">
                                    <h4>上次登录时间</h4>
                                    <p>
                                    <#if (user.lastUpdate)??>
								                        ${(user.lastUpdate)?string("yyyy-MM-dd HH:mm:ss")}
								                    </#if>
                                    </p>
                                </div>
                            </div>
                    </li>
                    <li>
                            <div class="icon-box">
                                <div class="icon">
                                    <i style="font-size: 40px" class="iconfont">&#xe7f4;</i>
                                </div>
                                <div class="text">
                                    <h4>上次登录IP</h4>
                                    <p>${ip!""}</p>
                                </div>
                            </div>
                    </li>
                    <li>
                            <div class="icon-box">
                                <div class="icon">
                                    <i style="font-size: 38px" class="iconfont">&#xe7f5;</i>
                                </div>
                                <div class="text">
                                    <h4>注册时间</h4>
                                    <p><#if (user.createTime)??>
						                            ${(user.createTime)?string("yyyy-MM-dd HH:mm:ss")}
						                        </#if></p>
                                </div>
                            </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 安全设置 -->
    <div class="grzx_main_mmxg page_main_xxk" style="display: none;">
        <#if (user.phone)??&&user.phone!=''>
            <!-- 修改绑定 -->
            <div class="anquan_title">
                <div class="anquan_name"><i class="iconfont">&#xe6ef;</i>绑定手机</div>
                <div id="bangdingphones" class="anquan_click">
                    <span><i class="iconfont" style="color: #19C475;">&#xe73d;</i></span>
                    <span>修改绑定</span>
                    <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
                </div>
                <div class="clear"></div>
            </div>
            <div class="bangdingnr" style="display: block;">
            <div class="grzx_main_bjgrzl">
                <form id="updPhoneFrom" class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前手机号</label>
                        <div class="layui-input-inline">
                            <div class="layui-form-mid layui-word-aux">&nbsp;&nbsp;${user.phone!}</div>
                            <input name="disabledInputs" id="disabledInputs" type="hidden" value="${user.phone!}" placeholder="请输入手机号码" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前手机号</label>
                        <div class="layui-input-inline">
                            <input id="querenphone" name="querenphone" placeholder="请输入手机号码" class="layui-input">
                            <input type="hidden" size="30" value="手机更改" name="type" class="layui-input"/>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">新手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text" size="30" id="xphone" name="xphone" placeholder="请输入新手机号码" class="layui-input" />
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" id="code" name="code" maxlength="6" size="30" class="layui-input" />
                        </div>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input class="layui-input"  sms-id="yzm" type-name="手机更改" value="发送验证码" sms-phone-id="xphone" sms-verification="updPhoneFrom" sms-yz="1" type="button">
                        </div>
                    </div>

                    <button class="layui-btn" id="updpass" type="button">确定修改</button>
                </form>
            </div>
        </div>
        <#else>
            <!-- 绑定手机 -->
            <div class="anquan_title">
                <div class="anquan_name"><i class="iconfont">&#xe6ef;</i>绑定手机</div>
                <div id="bangdingphones" class="anquan_click">
                    <span><i class="iconfont" style="color:#999;">&#xe73d;</i></span>
                    <span>立即绑定</span>
                    <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
                </div>
                <div class="clear"></div>
            </div>
            <div class="bangdingnr" style="display: block;">
                <div class="grzx_main_bjgrzl">
                    <form id="xinphonebangding" class="layui-form layui-form-pane">
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机号码</label>
                            <div class="layui-input-inline">
                                <input type="text" size="30" id="phonexin" name="xphone" placeholder="请输入手机号码" class="layui-input" />
                                <input type="hidden" size="30" value="手机绑定" name="type" />
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">验证码</label>
                            <div class="layui-input-inline" style="width: 120px;">
                                <input type="text" name="code" maxlength="6" size="30" class="layui-input" />
                            </div>
                            <div class="layui-input-inline" style="width: 120px;">
                                <input class="layui-input" sms-id="yzm" sms-phone-id="phonexin" sms-verification="xinphonebangding" type-name="手机绑定" value="发送验证码" type="button">
                            </div>
                        </div>

                        <button class="layui-btn" id="bangdingphone" type="button">确定绑定</button>
                    </form>




                </div>
            </div>
        </#if>
        <!-- 实名认证 -->
        <div class="anquan_title">
            <div class="anquan_name"><i class="iconfont">&#xe6d7;</i>实名认证</div>
            <div id="smrzClickDiv" class="anquan_click">
                <span><i class="iconfont" style="color: <#if userextend??&&userextend.validStatus=='2'> #19C475;<#else>#999;</#if>">&#xe73d;</i></span>
                <span>立即实名</span>
                <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
            </div>
            <div class="clear"></div>
        </div>
        <div class="bangdingnr">
        <#if user.phone??&&user.phone!=''>
            <div id="savesmrz" data-status="${(userextend.validStatus)!""}">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>上传身份证示例</legend>
            </fieldset>
            <div class="shiming_shili">
                <img src="${ctx}/images/shiming.jpg">
                <div class="shiming_jieshao">
                    <p>1、手持身份证保持本人头像清晰完整，手持身份证信息清晰无遮挡。</p>
                    <p>2、身份证反面单独拍照，保持证件清晰。</p>
                    <p>3、请使用证件原件拍摄，不支持复印件。</p>
                    <p>4、拍摄照片中无logo、水印、反光、遮挡等情况。</p>
                    <p>5、身份证上传仅支持jpg、jpeg、png、gif格式图片</p>
                </div>
                <div class="clear"></div>
            </div>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>上传身份证手持单张正面和反面</legend>
            </fieldset>
            <div>
                <div class="site-demo-upload" style="float:left">
                    <form id="zmfrom" enctype="multipart/form-data">
                    <img id="LAY_demo_upload1" src="${ctx}/images/sfz.png" style="width: 200px;">
                    <div class="site-demo-upbar">

                            <input type="file" name="zm" class="layui-upload-file" id="zm"  style="width: 200px; height: 130px;">

                    </div>
                    </form>
                </div>
                <div class="site-demo-upload" style="float:left; margin-left:20px;">
                    <img id="LAY_demo_upload2" src="${ctx}/images/sfzfm.png" style="width: 200px;">
                    <div class="site-demo-upbar">
                        <form id="zmfrom" enctype="multipart/form-data">
                            <input type="file" name="zm" class="layui-upload-file" id="fm"  style="width: 200px; height: 130px;">
                        </form>

                    </div>
                </div>
                <div class="clear"></div>
            </div>

            <fieldset class="layui-elem-field layui-field-title">
                <legend>填写真实有效的姓名和身份证号</legend>
            </fieldset>
                <form id="smrz" class="layui-form layui-form-pane">

                    <div class="layui-form-item">
                        <label class="layui-form-label">真实姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" size="10" name="zsxm" id="zsxm" placeholder="请输入真实姓名" class="layui-input"/>
                            <input type="hidden" name="fmbase64" class="layui-upload-file" id="fmbase64" style="">
                            <input type="hidden" name="zmbase64" class="layui-upload-file" id="zmbase64" style="">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">身份证号码</label>
                        <div class="layui-input-inline">
                            <input type="text" size="30" name="sfzhm" id="szfhm"  placeholder="请输入身份证号码" class="layui-input" />
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input id="disabledInputt" type="hidden" value="${user.phone!}"  placeholder="请输入手机号码" class="layui-input">
                            <div class="layui-form-mid layui-word-aux">&nbsp;&nbsp;${user.phone!}</div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" id="smrzcode" name="code" maxlength="6" size="30" class="layui-input"/>
                        </div>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input class="layui-input" sms-id="yzm" type-name="实名认证" value="发送验证码" sms-phone-id="disabledInputt" sms-verification="smrz" type="button">
                        </div>
                    </div>
                    <button class="layui-btn" id="smrztijiao" type="button">确定修改</button>

                </form>
            </div>
            <#else>
                <blockquote class="layui-elem-quote">尊敬的用户你好，实名认证需要，<a href="javascript:;" id="bdphone">绑定手机</a></blockquote>
            </#if>
            <div id="drz" style="display: none;">
                姓名：${(userextend.realName)!""}     <br>
                身份证号:${(userextend.idcard)!}<br>
                处理状态:<span<#if userextend??&&userextend.validStatus=='1'> style="color: #D58512;">处理中</#if>
            <#if userextend??&&userextend.validStatus=='2'> style="color:#00ee00;">已认证</#if>
            <#if userextend??&&userextend.validStatus=='3'> style="color:red;">认证拒绝</#if>
            <#if userextend??&&userextend.validStatus=='0'> style="color:#01AAED;">未认证</#if></span><br>
                备注:<#if userextend??&&userextend.validStatus!='2'>${(userextend.remark)!""}</#if><br>
                <#if userextend??&&userextend.validStatus=="3">
                    <button class="btn btn-info layui-btn" id="cxrz" type="button">重新认证</button>
                </#if>
                <#--<button class="btn btn-info layui-btn" id="cxrz" type="button">重新认证</button>-->
            </div>
        </div>

        <div class="anquan_title">
            <div class="anquan_name"><i class="iconfont">&#xe6af;</i>密码修改</div>
       <#--     <button class="btn btn-info layui-btn" id="cxrz" type="button">重新认证</button>-->
            <div class="anquan_click">
                <span><i class="iconfont" style="color: #19C475;">&#xe73d;</i></span>
                <span>立即修改</span>
                <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
            </div>
            <div class="clear"></div>
        </div>
        <div class="bangdingnr">
            <div class="grzx_main_bjgrzl">
                <form id="updform" class="layui-form layui-form-pane">
                    <#if user.phone??&&user.phone!=''>
                    <#if userextend??&&userextend.validStatus!='2'>
                    <blockquote class="layui-elem-quote">尊敬的用户您好，您还未实名认证，不能修改密码&nbsp;&nbsp;<a id="shim" href="javascript:;" style="color:red;">去实名认证</a></blockquote>
                    <#else>
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前手机号</label>
                        <div class="layui-input-inline">

                            <div class="layui-form-mid layui-word-aux">&nbsp;&nbsp;${user.phone!}</div>
                            <input id="disabledInputp" type="hidden" value="${user.phone!}" placeholder="请输入手机号码" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-inline">
                            <input type="password" size="30" maxlength="16" id="xpass" name="password" placeholder="请输入新密码" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">确认密码</label>
                        <div class="layui-input-inline">
                            <input  type="password" size="30" maxlength="16" id="qpass" name="qpass" placeholder="请输入确认密码" class="layui-input" />
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text"  maxlength="6" id="passcode" name="passcode" size="30" class="layui-input" />
                        </div>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input class="layui-input"  sms-id="yzm" type-name="修改密码" value="发送验证码" sms-phone-id="disabledInputp" sms-verification="updform" type="button">
                            <input class="layui-input" type="hidden" name="phone" id="updphone" value="${user.phone!}">
                        </div>
                    </div>
                    <button class="layui-btn" id="updpassword" type="button">确定修改</button>
                    </#if>
                    <#else>
                        <blockquote class="layui-elem-quote">尊敬的用户你好，修改密码需要，<a href="javascript:;" id="bdphone1">绑定手机</a></blockquote>
                    </#if>
                </form>
            </div>
        </div>
        <div class="anquan_title">
            <div class="anquan_name"><i class="iconfont">&#xe7f0;</i>微信绑定</div>
            <div class="anquan_click">
                <span><i class="iconfont"
                         <#if user.wxopenid??&&user.wxopenid!=''>
                         style="color: #19C475;"
                         <#else>
                         style="color: #999;"
                         </#if>
                >&#xe73d;</i></span>
                <span>立即绑定</span>
                <i class="iconfont icon-zuoyoujiantou-copy-copy"></i>
            </div>
            <div class="clear"></div>
        </div>
        <div class="bangdingnr">
            <#if user.wxopenid??&&user.wxopenid!=''>
                <div class="weixinbangding">
                    <img src="${user.wxheadimg!""}" style="    border-radius: 75px;">
                    <div class="wx_xinxi">
                        <p style="padding-top: 40px;">微信昵称:&nbsp;&nbsp;&nbsp;${user.wxnickname!""}</p>
                        <#--<p>微信OPENID:&nbsp;&nbsp;&nbsp;${user.wxopenid!""}</p>-->
                        <p style="padding-top: 15px;"><a href="javascript:;" wxjb="jb" wx-openid="${user.wxopenid!""}">解除绑定</a></p>
                    </div>
                </div>
                <input type="hidden" id="openid" value="${user.wxopenid!""}">
            <#else>
                <div class="weixinbangding">
                    <img src="${ctx}/images/code.jpg" id="weixin_ewm">
                    <div class="wx_xinxi">
                        <p>您还未绑定微信，扫一扫绑定微信</p>
                        <span>绑定微信可及时获取最新财税信息和涉税业务办理结果提醒通知</span>
                    </div>
                </div>
                <input type="hidden" id="openid" value="">
            </#if>

        </div>
    </div>
    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
    <script data-main="${ctx}/js/abc/user_info" src="${ctx}/js/require.js"></script>
</body>

</html>
</@compress>