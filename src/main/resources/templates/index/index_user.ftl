<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<li>
    <span>账户安全：</span>
    <dfn>
        <a class="href" href="javascript:void (0)" id="wszl"><em><#if aqcount lte 25>低<#elseif aqcount gte 50&&aqcount  lte 75>中<#else>高</#if></em></a>
    <#if user.phone??&&user.phone!=''>
        <i class="iconfont" style="font-size: 27px; margin: 0;" title="绑定手机">&#xe746;</i>
    </#if>
    <#if usertzxx??&&usertzxx.validStatus??&&usertzxx.validStatus=='2'>
        <i class="iconfont" style="font-size: 26px; margin: 0;" title="实名认证">&#xe748;</i>
    </#if>
    <#if user.wxopenid??&&user.wxopenid!=''>
        <i class="iconfont" style="font-size: 26px; margin: 0;" title="微信绑定">&#xe749;</i>
    </#if>

    </dfn>
</li>
<li>
    <span>VIP 等级：</span>
    <dfn>
        <a style="color:#15c1e0;" href="javascript:void (0)" id="huiyuan">${user.vipLevel!"VIP0"}${(vip.level)!""}</a>
    <#if (user.vipLevel)?? && user.vipLevel == "VIP0">
        <a id="kthy" href="javascript:;" url-zhu="${ctx}/member/member_index.html" url-fu="${ctx}/member/order_member.html"><em>开通会员</em></a>
    </#if>
    </dfn>
</li>

<#if user.vipLevel?? && user.vipLevel != "VIP0">
<li>
    <span>会员到期：</span>
    <dfn>
            ${user.vipExpireDate?string("yyyy-MM-dd")}
            <#if vipExpireDate?? && (vipExpireDate <= 60)>
                <span>
                    <a id="hyxf" href="javascript:;" url-zhu="${ctx}/member/member_index.html" url-fu="${ctx}/member/order_member.html"><em>会员续费</em></a>
                </span>
            </#if>
    </dfn>
</li>
</#if>
<li>
    <span>用户积分：</span>
    <dfn>
        <em>${(points.myPoints)!'0'}</em>积分
        <a class="href"  href="javascript:void(0)"url-zhu="${ctx}/member/member_index.html" url-fu="${ctx}/member/member_integral_uc.html" id="jifen"> 积分兑换</a>
    </dfn>
</li>
<li>
    <span>用户签到：</span>
    <dfn>
        <a href="javascript:void (0)" id="qiandao">
            <i class="iconfont">&#xe6c4;</i>
            <span>累计签到<em>
            ${checks!"0"}
                                    </em>天</span>
        </a>
    </dfn>
</li>
</@compress>