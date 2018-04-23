<div id="header">
    <div class="ny_head">
        <div class="w_1200 clearfix">
            <div class="ny_logo">
                <a href="${cswurl}/index.html" title="网站首页">
                    <div class="boximg"><span><img style="max-width:none" src="${ctx}/images/logo2.png"></span></div>
                </a>
            </div>
            <div class='ny-480-nav'>
                <div class='ny-480-sg'>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <ul>
                    <#--<li>-->
                        <#--<div class="login_usre_img_name"><img  src="<#if user??&&user.userPicturePath??&&user.userPicturePath!=''>${picurl}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>">-->
                            <#--<#if user?exists>-->
                                <#--<#if user.nickname??>-->
                                    <#--<a href="javascript:window.location.href='${ctx}/index.html'">${user.nickname!}</a>-->
                                <#--<#else >-->
                                    <#--<a href="javascript:window.location.href='${ctx}/index.html'">${user.username!}</a>-->
                                <#--</#if>-->
                            <#--</#if>-->
                        <#--</div>-->
                        <#--<a href="javascript:;"><div class="login_user_tuichu" id="logout">-->
                            <#--<i class="iconfont"> &#xe636;</i>-->
                            <#--<span>退出登录</span>-->
                        <#--</div></a>-->
                        <#--<div class="clear"></div>-->
                    <#--</li>-->
                    <li class='active'><a href='${cswurl}/index.html'>首页</a></li>
                    <li><a href='${cswurl}/products/index.html'>产品</a></li>
                    <li><a href='${cswurl}/services/index.html'>服务</a></li>
                    <li><a href='${cswurl}/news/index.html'>资讯</a></li>
                    <li><a href='${cswurl}/rjxz/index.html'>下载</a></li>
                    <li><a href='${snsurl}/school/index.html'>学堂</a></li>
                    <li><a href='${snsurl}/bangbang/index.html'>帮邦</a></li>
                    <#--<li><a href="${ctx}/index.html">个人中心</a> </li>-->
                    <#--<li><a href="${ctx}/member_index.html">会员中心</a> </li>-->
                    <div class='clear'></div>
                </ul>
                <div class='ny-480-bg'></div>
            </div>
            <div class="fr">
                <div style="" class="ny_nav">
                    <ul>
                        <li><a href="${cswurl}/index.html">首页</a></li>
                        <li><a href="${cswurl}/products/index.html">产品</a></li>
                        <li><a href="${cswurl}/services/index.html">服务</a></li>
                        <li><a href="${cswurl}/news/index.html">资讯</a></li>
                        <li><a href="${cswurl}/rjxz/index.html">下载</a></li>
                        <li><a href="${snsurl}/school/index.html">学堂</a></li>
                        <li><a href="${snsurl}/bangbang/index.html">帮邦</a></li>
                        <div class="clear"></div>
                    </ul>
                </div>
                <div class="ny_login_serach">
                    <a href="${snsurl}/search/index.html" target="_blank"><i class="iconfont">&#xe83b;</i>搜索</a>
                    <a href="${snsurl}/quiz/quizindex.html"><i class="iconfont"> </i>提问</a>
                </div>
            <#if user??>
                <div style="" class="ny_login_nav2">

                    <img src="<#if user??&&user.userPicturePath??&&user.userPicturePath!=''>${picurl}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>">
                    <div class="user_name">
                        <p>
                            <#if user?exists>
                                <#if user.nickname??>
                                    <a style="color: #fff; font-size: 12px;" href="javascript:window.location.href='${ctx}/index.html'">${user.nickname!}</a>
                                <#else >
                                    <a style="color: #fff; font-size: 12px;" href="javascript:window.location.href='${ctx}/index.html'">${user.username!}</a>
                                </#if>
                            </#if>
                        </p>
                        <a href="javascript:;" style="color: #fcff00; font-size: 12px;" >${user.vipLevel!"VIP0"}&nbsp;${user.vipLevelName!"普通用户"}</a>
                        <input type="hidden" id="user_id" value="${user.id!""}">
                    </div>
                    <div class="nemu-box">
                        <div class="center">
                            <a href="javascript:void (0)">${user.level!"LV1"}${user.levelName!"实习生1级"}</a>
                        </div>
                        <ul>
                            <a href="javascript:;"><li  name="${ctx}/index.html" link="${ctx}/userinfo/userinfolist.html" type="waibu">

                                <i class="iconfont"> &#xe764;</i>
                                <span>基本资料</span>
                            <#--<em>1</em>-->

                            </li></a>
                            <a href="javascript:;"><li name="${ctx}/index.html" link="${ctx}/userinfo/userinfolist.html?v=12345678#1_1" type="waibu">

                                <i class="iconfont"> &#xe6ed;</i>
                                <span>实名认证</span>
                            <#--<em>1</em>-->

                            </li></a>
                            <a href="javascript:;"><li name="${ctx}/index.html" link="${ctx}/userinfo/user_message.html" type="waibu">

                                <i class="iconfont"> &#xe603;</i>
                                <span>我的消息</span>
                                <#if sysTotal?? && sysTotal gt 0>
                                    <em  id ="msgTop">${sysTotal!"0"}</em>
                                </#if>

                            </li></a>
                            <a href="javascript:;"><li style="margin-bottom: 10px" name="${ctx}/member/member_index.html" link="${ctx}/member/my_index.html" type="waibu">

                                <i class="iconfont"> &#xe684;</i>
                                <span>会员中心</span>
                            <#--<em>1</em>-->

                            </li></a>
                            <a href="javascript:;"><li class="tuichu" id="logout">

                                <i class="iconfont"> &#xe636;</i>
                                <span>退出登录</span>

                            </li></a>
                        </ul>
                    </div>
                </div>
            <#else >
                <div class="ny_login_nav">
                    <a href="login">登录</a><span>|</span><a href="#">注册</a>
                </div>
            </#if>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/plugin/jquery-3.1.0.js"></script>
<script>
    $(".ny-480-nav").click(function(){
        $(this).children('ul').stop().slideToggle(500);
        if($(".ny-480-bg").css("display")=='none'){
            $(this).find(".ny-480-bg").stop().show();
        }else{
            $(this).find(".ny-480-bg").stop().hide();
        }
    })
    //固定导航条在顶部
    $(document).on('fixe',function() {
        var WIDTH = $(window).width();
        var FIXE = $('.fixe');

        $(window).scroll(function () {
            var HEIGHT = $(window).scrollTop();
            FIXE.css('top', -HEIGHT);
        });
        //IE8兼容
        if (document.all && document.querySelector && !document.addEventListener) {
            if (WIDTH <= 1284) {
                FIXE.addClass('fixes');
            }
        }
    }).trigger('fixe');


</script>