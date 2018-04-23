<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "../../common/meta.ftl">
    <title>我的团队</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
</head>
<body>
<div class="grzx_main_rt">我的团队</div>
<!-- 我的团队 -->
<div class="grzx_main_wdtd">
    <ul>
        <#if teams?? && (teams?size>0)>
            <#list teams as team>
                <li>
                    <a href=""><img src="${team.maxPicturePath!}">
                        <h3>${team.teamName!}</h3>团员：${team.members!} 采纳：${team.acceptedCount!}
                        <#if team.status! =='1'>
                            <div class="grzx_wdtd_sta1"></div>
                        </#if>
                        <#if team.status! =='3'>
                            <div class="grzx_wdtd_sta2"></div>
                        </#if>
                        <!-- 团队申请状态，审核中：grzx_wdtd_sta1；审核失败：grzx_wdtd_sta2； -->
                    </a>
                </li>
                <#if (team_index+1)%5 ==0>
                    <div class="clear"></div>
                </#if>
            </#list>
        </#if>
        <li class="grzx_wdtd_add">
            <a href="">
                <div class="iconfont icon-tianjia"></div>点击加入新团队
            </a>
        </li>
        <div class="clear"></div>
    </ul>
</div>
<div class="grzx_main_rt">相关团队</div>
<div class="grzx_main_xgtd">
    <ul>
        <li>
            <a href="">
                <div class="grzx_xgtd_tx"><img src="images/touxiang1.png">
                    <div class="grzx_xgtd_txt">成就<b>188</b></div>
                </div>
                <div class="grzx_xgtd_r">
                    <h3>帮邦团名字</h3>
                    <i class="iconfont icon-wodezhanghu3"></i> 我爱帮邦团
                    <div><span>税收政策    </span><span>涉税实务</span></div>
                    <button class="grzx_btn">申请加入</button>
                </div>
                <div class="clear"></div>
            </a>
        </li>
        <li>
            <a href="">
                <div class="grzx_xgtd_tx"><img src="images/touxiang1.png">
                    <div class="grzx_xgtd_txt">成就<b>188</b></div>
                </div>
                <div class="grzx_xgtd_r">
                    <h3>帮邦团名字</h3>
                    <i class="iconfont icon-wodezhanghu3"></i> 我爱帮邦团
                    <div><span>税收政策    </span><span>涉税实务</span></div>
                    <button class="grzx_btn">申请加入</button>
                </div>
                <div class="clear"></div>
            </a>
        </li>
        <li>
            <a href="">
                <div class="grzx_xgtd_tx"><img src="images/touxiang1.png">
                    <div class="grzx_xgtd_txt">成就<b>188</b></div>
                </div>
                <div class="grzx_xgtd_r">
                    <h3>帮邦团名字</h3>
                    <i class="iconfont icon-wodezhanghu3"></i> 我爱帮邦团
                    <div><span>税收政策    </span><span>涉税实务</span></div>
                    <button class="grzx_btn">申请加入</button>
                </div>
                <div class="clear"></div>
            </a>
        </li>
    </ul>
</div>
</body>
</html>