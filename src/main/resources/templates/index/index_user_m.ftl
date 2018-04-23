<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<div class="vip_dengji">
    <span><i class="iconfont">&#xe846;</i></span>
    <dfn>
        <a style="color:#15c1e0;" href="javascript:void (0)" id="huiyuan">${user.vipLevel!"VIP0"}${(vip.level)!""}</a>
        <#if (user.vipLevel)?? && user.vipLevel == "VIP0">
            <a id="kthy" href="javascript:;" url-zhu="${ctx}/member/member_index.html" url-fu="${ctx}/member/order_member.html"><em>开通会员</em></a>
        </#if>
    </dfn>
    <i class="iconfont">&#xe7d9;</i>
    <#if user.vipLevel?? && user.vipLevel != "VIP0">
    <div class="huiyuandaoqi">
        <span>会员到期：</span>
        <dfn>
            <#if vipExpireDate?? && (vipExpireDate > 0)>
            ${user.vipExpireDate?string("yyyy-MM-dd")}
            <#elseif vipExpireDate?? && (vipExpireDate <= 0)>
                即将到期
            </#if>
        </dfn>
    </div>
    </#if>
    <div class="clear"></div>
</div>
<div class="user_xinxi">
    <div class="anquan_jifen">
        <div class="anquan">
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
        </div>
        <div class="jifen">
            <span>用户积分：</span>
            <dfn>
                <em>${(points.myPoints)!'0'}</em>积分
            </dfn>
        </div>
    </div>
    <a href="${ctx}/member/checkIn.php">
    <div class="user_qiandao">
        <img src="${ctx}/images/qiandaoimg.jpg">
        <div class="yhqd">
            <span>用户签到</span>
            <div><span>累计签到<em>${checks!"0"}</em>天</span></div>
        </div>
    </div>
    </a>
    <div class="clear"></div>
</div>
</@compress>