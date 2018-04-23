<@compress single_line=true>
<#assign ctx =request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>我是会员</title>
    <#include "../common/meta.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">var ctx ="${ctx}"</script>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="huiyuan_title">积分获取规则
    <a href="${ctx}/pointsExchange/points.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的积分</a>
</div>
<div class="clear"></div>
<table class="layui-table table table-bordered jx_mxgz">
    <tbody>
    <tr class="tr_tdtitle">
        <td>行为</td>
        <td>积分</td>
        <td>规则</td>
        <td>上限次数</td>
    </tr>
    <#if pointRules?? && (pointRules?size>0)>
        <#list pointRules as rule>
        <tr>
            <td>${rule.name!""}</td>
            <td>${rule.points!""}</td>
            <td>${rule.description!""}</td>
            <#if rule.period =="D">
                <td>${rule.degree!""}次/日</td>
            <#elseif rule.period =="M">
                <td>${rule.degree!""}次/月</td>
            <#elseif rule.period =="Y">
                <td>${rule.degree!""}次/年</td>
            <#elseif rule.period =="A">
                <td>无限制</td>
            <#elseif rule.period =="O">
                <td>一次性</td>
            </#if>
        </tr>
        </#list>
    </#if>
    <#--<tr>
        <td>每日登录</td>
        <td></td>
        <td>登录即可完成任务</td>
        <td>1次/日</td>
    </tr>
    <#--<tr>
        <td>消费</td>
        <td>1：1兑换</td>
        <td>消费多少金额送多少积分</td>
        <td>不限</td>
    </tr>
    <tr>
        <td>连续签到</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>第1天</td>
        <td>5</td>
        <td  rowspan="4">若签到中断，签到日期重第1天开始计算；每月用户有3次补签的机会.</td>
        <td  rowspan="4">1次/日</td>
    </tr>
    <tr>
        <td>第2天</td>
        <td>10</td>
    </tr>
    <tr>
        <td>第3天</td>
        <td>15</td>
    </tr>
    <tr>
        <td>第4天</td>
        <td>20</td>
    </tr>
    <tr>
        <td>点赞</td>
        <td>2</td>
        <td>对文章、问答点赞即可完成任务</td>
        <td>3次/日</td>
    </tr>
    <tr>
        <td>评论</td>
        <td>4</td>
        <td>对文章发表评论即可完成任务</td>
        <td>3次/日</td>
    </tr>
    <tr>
        <td>用户等级升级奖励</td>
        <td>10</td>
        <td>用户等级提升1级</td>
        <td>不限</td>
    </tr>
    <tr>
        <td>首次手机认证</td>
        <td>100</td>
        <td>账号绑定手机即可获取积分</td>
        <td rowspan="9">一次性</td>
    </tr>
   &lt;#&ndash; <tr>
        <td>首次邮箱认证</td>
        <td>50</td>
        <td>账号绑定邮箱即可获取积分</td>
    </tr>&ndash;&gt;
    <tr>
        <td>首次实名认证</td>
        <td>200</td>
        <td>完成实名认证即可获取积分</td>
    </tr>
    <tr>
        <td>首次修改交易密码</td>
        <td>50</td>
        <td>修改交易密码即可获取积分</td>
    </tr>
    <tr>
        <td>首次上传头像</td>
        <td>50</td>
        <td>上传头像即可获取积分</td>
    </tr>
    <tr>
        <td>首次完善个人信息</td>
        <td>300</td>
        <td>完善个人信息即可获取积分</td>
    </tr>
    <tr>
        <td>首次消费</td>
        <td>500</td>
        <td>购买会员、课程、服务、软件；充值即可获取积分</td>
    </tr>
    <tr>
        <td>首次申报缴税</td>
        <td>200</td>
        <td>申报并成功缴税即可获取积分</td>
    </tr>

    <tr>
        <td>关注财税专家公众号</td>
        <td>200</td>
        <td>扫描关注财税专家公众号即可获取积分</td>
    </tr>
    <tr>
        <td>首次下载安装ABC4000</td>
        <td>200</td>
        <td>下载ABC4000并安装、登录即可获取积分</td>
    </tr>-->
    </tbody>
</table>
<div class="huiyuan_title" style="color: #F34948;">备注：消费积分及任务积分为变量积分，根据实际行为累积！</div>
<script data-main="${ctx}/js/abc/user_point_rule" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>