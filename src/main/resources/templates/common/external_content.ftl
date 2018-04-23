
<div class="grzx_main_l scoll-boder">
    <div class="grzx_main_l_b">
        <div class="mine-box">
            <div class="img-box">
                <div class="img">
                    <img src="<#if user??&&user.userPicturePath??&&user.userPicturePath!=''>${picurl}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>">
                </div>
                <div class="mine">
                    <h3>${(user.nickname)!"无名氏"}</h3>
                    <div class="Safety">
                        <div class="xunzhang">
                            <img class="xz" src="${ctx}/images/${(userExp.medalIcon)!"medal_1"}.png" alt="">
                            <span style="color: #fff;">${(userExp.expLevel)!"LV1"}</span>
                        </div>
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

                            <a id="xunz" class="href"  href="javascript:void(0)">${(userExp.medal)!"实习生1级"}</a>
                        </div>
                    </div>
                </div>
                <div class="Basic">
                    <ul class="index_userinfo">

                    </ul>
                </div>
            </div>
        </div>
        <ul id="dh_left">
            <li name="user_index.html" class="dq_hover">
                <span class=" iconfont icon-iconfontgerenzhongxin"></span>个人中心
            <#--<span class="fr yuan bg_blue">12</span>-->
                <div class="clear"></div>
            </li>
            <li name="userinfo/userinfolist.html">
                <span class=" iconfont icon-xiangmushenbao"></span>基本资料
            <#--<span class="fr yuan bg_blue">12</span>-->
                <div class="clear"></div>
            </li>
            <li name="userinfo/enterprise.html">
                <span class="iconfont icon-cop"></span>我的纳税企业
            </li>
            <li name="userinfo/user_message.html">
                <span class="iconfont icon-xiaoxi"></span>我的消息
                <span id="messagecount"></span>
                <div class="clear"></div>
            </li>

            <li name="coupons/list.html">
                <span class="iconfont icon-fapiao"></span>我的优惠券
            </li>
        <#--<li name="userinfo/order.php">-->
            <li name="userinfo/order.php">
                <span class="iconfont icon-jifen5"></span>我的订单
            <#--<span class="fr yuan bg_orange">12</span>-->
                <div class="clear"></div>
            </li>
            <li name="userinfo/task.php">
                <span class="iconfont icon-privilege"></span>我的任务
            <#--<span class="fr yuan bg_blue">12</span>-->
                <div class="clear"></div>
            </li>

        <#--<li name="userinfo/points.php">-->
            <li name="member/external_member_index.html" link="../pointsExchange/points.php" type="waibu">
                <span class="iconfont icon-jifen"></span>我的积分
            </li>
            <li name="userinfo/expLog.php">
                <span class="iconfont icon-wujiaoxing"></span>我的经验值
            </li>
            <li name="userinfo/useraddr.html">
                <span class="iconfont icon-shouhuodizhi"></span>我的地址
            </li>
            <li name="userinfo/userinvoice.html">
                <span class="iconfont icon-fapiao"></span>我的发票
            </li>

        <#--退换货管理-->
            <li name="orderback/back.php">
                <span class="iconfont icon-02huilv"></span>退换货申请
            </li>

            <li name="school/list.html">
                <span class="iconfont icon-class"></span>我的课程
            </li>
            <li name="member/prize.html">
                <span class="iconfont icon-jifen3"></span>我的中奖纪录
            </li>
            <#--<li name="userinfo/user_vip.php">-->
            <#--<span class="iconfont icon-huiyuan"></span>我的VIP特权-->
            <#--</li>-->

            <li name="${snsurl}/userindex/index.html" waibu="_new">
                <span class="iconfont icon-da"></span>我的帮邦
            </li>
        </ul>
    </div>
</div>
