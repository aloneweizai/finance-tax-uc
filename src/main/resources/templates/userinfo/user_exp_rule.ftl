<@compress single_line=true>
<#assign ctx =request.getContextPath()>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>我是会员</title>
    <#include "../common/meta.ftl">
    <script type="text/javascript">var ctx ="${ctx}"</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="huiyuan_title">经验值明细规则
    <a href="${ctx}/userinfo/expLog.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的经验值</a>
</div>
<div class="clear"></div>
<table class="layui-table table table-bordered jx_mxgz">
    <tbody><tr class="tr_tdtitle">
        <td>行为</td>
        <td>经验值</td>
        <td>规则</td>
        <td>上限次数</td>
    </tr>
    <#if expRules?? && (expRules?size>0)>
        <#list expRules as rule>
        <tr>
            <td>${rule.name!""}</td>
            <td>${rule.exp!""}</td>
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

    <tr>
        <td>第1天</td>
        <td>3</td>
        <td rowspan="4">第1天3经验值、第2天5经验值、第3天8经验值，第4天及以上10经验值</td>
        <td rowspan="4">1次/日</td>
    </tr>

    <tr>
        <td>第2天</td>
        <td>5</td>
    </tr>

    <tr>
        <td>第3天</td>
        <td>8</td>
    </tr>

    <tr>
        <td>第4天及以上</td>
        <td>10</td>
    </tr>

    <tr>
        <td>每日回答问题</td>
        <td>10</td>
        <td>在帮邦上解答问题被采纳即可完成任务</td>
        <td>2次/日</td>
    </tr>
    <tr>
        <td>每日疑难提问</td>
        <td>4</td>
        <td>在帮邦上提出问题即可完成任务</td>
        <td>2次/日</td>
    </tr>
    <tr>
        <td>每日文章分享</td>
        <td>4</td>
        <td>浏览文章并分享即可完成任务</td>
        <td>3次/日</td>
    </tr>
    <tr>
        <td>每日文章收藏</td>
        <td>2</td>
        <td>浏览文章并收藏即可完成任务</td>
        <td>3次/日</td>
    </tr>
    <tr>
        <td>每日浏览资讯</td>
        <td>5</td>
        <td>打开艾博克官网站浏览任何资讯内容即可获取经验值</td>
        <td>3次/日</td>
    </tr>

    <tr>
        <td>每日课程学习</td>
        <td>5</td>
        <td>学习课程即可获得相应奖励</td>
        <td>1次/日</td>
    </tr>

    <tr>
        <td>绑定税号</td>
        <td>5</td>
        <td>使用ABC4000绑定税号即可获取经验值</td>
        <td  rowspan="15">1次/月</td>
    </tr>
    <tr>
        <td>系统修复</td>
        <td>5</td>
        <td>在ABC4000上使用系统修复功能即可获取经验值</td>
    </tr>
    <tr>
        <td>实名认证</td>
        <td>20</td>
        <td>在ABC4000上完成实名认证即可获取经验值</td>
    </tr>
    <tr>
        <td>介质申报</td>
        <td>5</td>
        <td>在ABC4000上完成介质申报即可获取经验值</td>

    </tr>
    <tr>
        <td>预缴税款</td>
        <td>5</td>
        <td>在ABC4000上预缴税款即可获取经验值</td>
    </tr>
    <tr>
        <td>作废报表</td>
        <td>5</td>
        <td>在ABC4000上作废报表即可获取经验值</td>
    </tr>
    <tr>
        <td>分支机构设置</td>
        <td>10</td>
        <td>在ABC4000上设立分支机构即可获取经验值</td>
    </tr>
    <tr>
        <td>下载企业信息</td>
        <td>5</td>
        <td>在ABC4000上下载企业信息即可获取经验值</td>
    </tr>
    <tr>
        <td>获取申报结果</td>
        <td>15</td>
        <td>在ABC4000上获取企业信息即可获取经验值</td>
    </tr>
    <tr>
        <td>打印完税凭证</td>
        <td>5</td>
        <td>在ABC4000上打印完税凭证即可获取经验值</td>
    </tr>
    <tr>
        <td>查询服务器数据</td>
        <td>10</td>
        <td>在ABC4000上查询服务器数据即可获取经验值</td>
    </tr>
    <tr>
        <td>海关完税凭证采集</td>
        <td>10</td>
        <td>在ABC4000上使用海关完税凭证采集即可获取经验值</td>
    </tr>
    <tr>
        <td>固定资产折旧管理</td>
        <td>10</td>
        <td>在ABC4000上使用固定资产折旧管理即可获取经验值</td>
    </tr>
    <tr>
        <td>网上缴税</td>
        <td>20</td>
        <td>在ABC4000上完成网上缴税即可获取经验值</td>
    </tr>
    <tr>
        <td>网上零申报</td>
        <td>15</td>
        <td>在ABC4000上完成网上零申报即可获取经验值</td>
    </tr>
    <tr>
        <td>网上申报</td>
        <td>20</td>
        <td>在ABC4000上完成每类申报都可获取经验值</td>
        <td>不限</td>
    </tr>-->
    </tbody>
</table>
</body>
</html>
</@compress>