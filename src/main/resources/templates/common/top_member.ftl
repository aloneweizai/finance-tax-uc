<div id="Avatar-box">
    <div class="juzhong">
        <div class="Avatar">
            <div class="img">
                <img src="<#if user??&&user.userPicturePath??&&user.userPicturePath!=''>${picurl}${user.userPicturePath!}<#else>${ctx}/images/mrtx.png</#if>">
            </div>
            <div class="cent">
                <h4>${(user.nickname)!"无名氏"}</h4>
                <p>账户安全：<span>
                    <#if aqcount lte 25>
                        低
                    <#elseif aqcount gte 50&&aqcount  lte 100>
                        中
                    <#else >
                        高
                    </#if>
                </span></p>
                <p>
                <#if vipExpireDate ?? && vipExpireDate gt 0>
                     ${user.vipLevelName!"普通用户"}：
                <#else>
                    <a id="kthy" href="javascript:;" url-zhu="${ctx}/member/member_index.html" url-fu="${ctx}/member/order_member.html">开通会员：</a>
                </#if>
                    <i class="iconfont">
                    <#if user.vipLevel == "VIP0">
                        &#xe745;
                    <#elseif user.vipLevel == "VIP1">
                        &#xe742;
                    <#elseif user.vipLevel == "VIP2">
                        &#xe741;
                    <#elseif user.vipLevel == "VIP3">
                        &#xe743;
                    <#elseif user.vipLevel == "VIP4">
                        &#xe744;
                    </#if>
                </i></p>
                <p id="wans" style="width: 180px">完善资料<span>可获取更多的权限</span></p>
            </div>
            <div class="Certification">
                <ul>
                    <li id="phone">
                        <div class="yuan"></div>
                        <a href="javascript:void (0)">
                            <i class="iconfont">&#xe6ef;</i>
                        </a>
                        <p>手机认证</p>
                    </li>
                    <li id="weChat">
                        <div class="yuan"></div>
                        <a href="javascript:void (0)">
                            <i class="iconfont">&#xe749;</i>
                        </a>
                        <p>微信认证</p>
                    </li>
                    <li id="renz">
                        <div class="yuan"></div>
                        <a href="javascript:void (0)">
                            <i class="iconfont">&#xe748;</i>
                        </a>
                        <p>实名认证</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>