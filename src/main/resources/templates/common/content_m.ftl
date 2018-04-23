<div class="grzx_main_l">
    <div class="grzx_main_l_b">
        <div class="mine-box">
            <div class="img-box">
                <div class="img">
                    <a href="javascript:;" id="touxiangimag"><img src="<#if user??&&user.userPicturePath??&&user.userPicturePath!=''>${picurl}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>"></a>
                </div>
                <div class="mine">
                    <h3>${(user.nickname)!"无名氏"}</h3>
                    <div class="progress-box">

                        <div class="skillbar clearfix " >
                        <#if userExp??>
                            <div class="skillbar clearfix " data-percent="${(userExp.exp/(userExp.nextLevelExp?number)!userExp.exp)?string.percent}">
                                <div class="skillbar-bar" style="background: #FF8800;"></div>
                                <div class="skill-bar-percent">${userExp.exp!""}/${userExp.nextLevelExp!userExp.exp}</div>
                            </div>
                        <#else>
                            <div class="skillbar clearfix " data-percent="1">
                                <div class="skillbar-bar" style="background: #FF8800;"></div>
                                <div class="skill-bar-percent">0/0</div>
                            </div>
                        </#if>
                        </div><!-- End Skill Bar -->

                    <#--<script>
                        $(document).ready(function(){
                            $('.skillbar').each(function(){
                                $(this).find('.skillbar-bar').animate({
                                    width:$(this).attr('data-percent')
                                },3000);
                            });
                        });
                    </script>-->
                    </div>
                    <div class="Safety">
                        <div class="xunzhang">
                            <a id="xunz" class="href"  href="javascript:void(0)"><span style="color: #999;">${(userExp.expLevel)!"LV1"}.${(userExp.medal)!"实习生1级"}</span></a>
                        </div>
                    </div>
                </div>

            </div>
            <div class="Basic">
                <ul class="index_userinfo">

                </ul>
            </div>
        </div>
        <ul id="dh_left">
            <#--<li name="user_index.html" class="dq_hover">-->
                <#--<span class=" iconfont icon-iconfontgerenzhongxin"></span>个人中心-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--&lt;#&ndash;<span class="fr yuan bg_blue">12</span>&ndash;&gt;-->
                <#--<div class="clear"></div>-->
            <#--</li>-->
            <a href="${ctx}/userinfo/userinfolist.html">
                <li>
                    <span class=" iconfont icon-xiangmushenbao"></span>基本资料
                    <i class="iconfont">&#xe7d9;</i>
                <#--<span class="fr yuan bg_blue">12</span>-->
                    <div class="clear"></div>
                </li>
            </a>
            <#--<li name="userinfo/enterprise.html">-->
                <#--<span class="iconfont icon-cop"></span>我的纳税企业-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
            <#--<li name="userinfo/user_message.html">-->
                <#--<span class="iconfont icon-xiaoxi"></span>我的消息-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
                <#--<div id="messagecount"></div>-->
                <#--<div class="clear"></div>-->
            <#--</li>-->
            <a href="${ctx}/coupons/list.html">
                <li>
                    <span class="iconfont icon-fapiao"></span>我的优惠券
                    <i class="iconfont">&#xe7d9;</i>
                </li>
            </a>
        <#--<li name="userinfo/order.php">-->
            <a href="${ctx}/userinfo/order.php">
                <li>
                    <span class="iconfont icon-jifen5"></span>我的订单
                    <i class="iconfont">&#xe7d9;</i>
                <#--<span class="fr yuan bg_orange">12</span>-->
                    <div class="clear"></div>
                </li>
            </a>
            <#--<li name="userinfo/task.php">-->
                <#--<span class="iconfont icon-privilege"></span>我的任务-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--<#--<span class="fr yuan bg_blue">12</span>-->
                <#--<div class="clear"></div>-->
            <#--</li>-->

        <#--<li name="userinfo/points.php">-->
            <#--<li name="member/member_index.html" link="../pointsExchange/points.php" type="waibu">-->
                <#--<span class="iconfont icon-jifen"></span>我的积分-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
            <#--<li name="userinfo/expLog.php">-->
                <#--<span class="iconfont icon-wujiaoxing"></span>我的经验值-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
            <a href="${ctx}/userinfo/useraddr.html">
                <li>
                    <span class="iconfont icon-shouhuodizhi"></span>我的地址
                    <i class="iconfont">&#xe7d9;</i>
                </li>
            </a>
            <#--<li name="userinfo/userinvoice.html">-->
                <#--<span class="iconfont icon-fapiao"></span>我的发票-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
        <#--退换货管理-->
            <#--<li name="orderback/back.php">-->
                <#--<span class="iconfont icon-02huilv"></span>退换货申请-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
            <a href="${ctx}/school/list.html">
                <li>
                    <span class="iconfont icon-class"></span>我的课程
                    <i class="iconfont">&#xe7d9;</i>
                </li>
            </a>
            <#--<li name="member/prize.html">-->
                <#--<span class="iconfont icon-jifen3"></span>我的中奖纪录-->
                <#--<i class="iconfont">&#xe7d9;</i>-->
            <#--</li>-->
            <#--<li name="userinfo/user_vip.php">-->
            <#--<span class="iconfont icon-huiyuan"></span>我的VIP特权-->
            <#--</li>-->
            <a href="${snsurl}/userindex/index.html" target="_blank">
                <li>
                    <span class="iconfont icon-da"></span>我的帮邦
                    <i class="iconfont">&#xe7d9;</i>
                </li>
            </a>
        </ul>
    </div>
</div>