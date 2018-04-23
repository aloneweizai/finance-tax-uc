<@compress single_line=true>
<div class="MeCe-box">
    <div class="left-box khd-g">
        <img width="260" src="${ctx}/images/left-t.jpg" alt="">
        <div class="left">
            <div class="top">
                <img src="${ctx}/images/left-top.png" alt="">
            </div>
            <div class="cent-box">
                <div class="cent">
                    <div class="img">
                        <#if user.vipLevel == "VIP1">
                            <img width="80px" src="${ctx}/images/vip-silver.png" alt="">
                        <#elseif user.vipLevel == "VIP2">
                            <img width="80px" src="${ctx}/images/vip-gold.png" alt="">
                        <#elseif user.vipLevel == "VIP3">
                            <img width="80px" src="${ctx}/images/vip-diamond.png" alt="">
                        <#elseif user.vipLevel == "VIP4">
                            <img width="80px" src="${ctx}/images/vip-super.png" alt="">
                        <#else>
                            <img width="80px" src="${ctx}/images/vip-common.png" alt="">
                        </#if>
                    </div>
                    <div class="vip">
                        <p>${user.vipLevel!"VIP0"}${(vip.level)!""}</p>
                        <#if (user.vipLevel)?? && user.vipLevel == "VIP0">
                              <a data-lj="1" data-url="${ctx}/member/order_member.html" href="javascript:void (0)">开通会员</a>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <ul id="hy_left">
                    <li name="my_index.html">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe684;</i>
                        </a>
                        <p>我是会员</p>
                    </li>
                    <li name="order_member.html">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe7e3;</i>
                        </a>
                        <p>订购会员</p>
                    </li>
                    <li name="lottery/view.php">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe7f8;</i>
                        </a>
                        <p>会员活动</p>
                    </li>
                    <li name="../pointsExchange/points.php">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe7e5;</i>
                        </a>
                        <p>我的积分</p>
                    </li>
                    <li name="member_integral.html">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe7e6;</i>
                        </a>
                        <p>积分兑换</p>
                    </li>
                    <li name="member_mall.html">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe7e4;</i>
                        </a>
                        <p>会员礼包</p>
                    </li>
                    <li name="../integral/integral_payment.html">
                        <a href="javascript:void (0)" >
                            <i class="iconfont" style="margin-left: 0px">&#xe7e8;</i>
                        </a>
                        <p>积分充值</p>
                    </li>
                    <li name="../external_index.html" waibu="_new">
                        <a  href="javascript:void (0)">
                            <i class="iconfont" style="margin-left: 0px">&#xe60c;</i>
                        </a>
                        <p>我的信息</p>
                    </li>
                </ul>
            </div>
        </div>
        <div id="bjs"></div>
    </div>

    <div class="right-box">
        <iframe  id="external-frame" class="uc_iframe" frameborder="no" style="width: 100%;"  scrolling="no"  marginheight="0px" marginwidth="0px" src="${url!""}">

        </iframe>
    </div>
    <div class="clear"></div>
</div>
</@compress>