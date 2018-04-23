<@compress single_line=true>
<#assign ctx= request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <#include "../common/meta.ftl">
        <title>我的经验值</title>
        <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <#--<link href="${ctx}/css/easyui.css" rel="stylesheet" type="text/css">-->
        <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/jedate/skin/jedate.css">
        <script type="text/javascript">var ctx ="${ctx}"</script>
    </head>
    <body>

<div class="grzx_main_rt">我的经验值</div>

<!-- 个人中心内容右侧 -->
<div class="grzx_wdjf_dhjl">

    <div class="grzx_wdjf_dhjl_t" style="margin-top:0;">
        <ul class="experience">
        <#if userExp??>
            <li>
                <div class="title">
                    <h3>${userExp.exp!}</h3>
                    <p>我的经验值</p>
                </div>
                <div class="icon">
                    <i class="iconfont">&#xe7fb;</i>
                </div>
            </li>
            <li>
                <div class="title">
                    <h3>${(userExp.incomeMonth!0) - (userExp.outgoMonth!0)}</h3>
                    <p>本月增长</p>
                </div>
                <div class="icon">
                    <i class="iconfont">&#xe804;</i>
                </div>
            </li>
            <li style="margin-right: 0">
                <div class="title">
                    <h3>${(userExp.incomeYear!0) - (userExp.outgoYear!0)}</h3>
                    <p>本年增长</p>
                </div>
                <div class="icon">
                    <i style="font-size: 80px" class="iconfont">&#xe802;</i>
                </div>
            </li>
        <#else>
            <li>我的经验值
                <h3>0</h3>
            </li>
            <li>本月增长
                <h3>0</h3>
            </li>
            <li>本年增长
                <h3>0</h3>
            </li>
        </#if>
            <div class="clear"></div>
        </ul>
    </div>

        <div style="text-align: left;"><i class="grzx_wdjf_dhjl_icon"></i>经验值累加不过期
            <a id= "rule" class="color_b" href="javascript:;">[查看经验值规则]</a></div>
        <div style="height: 40px; float: right;">
            <div style="width: 100px; text-align: right; line-height: 32px; float: left;">请选择日期：</div>
            <input id="begintime" type="text" placeholder="开始日期" value="${start!}" style="line-height: 28px;width: 90px;height: 32px" readonly>
            <#--<input type="text"  class="form-control" data-options="editable:false" data-target="#startMsg" data-type="datebox" id="begintime" value="${start!}" style="width: 100px;float: left;">-->
            <div style="position: relative ;display:inline-block;width: 20px; line-height: 32px; text-align: center;">-</div>
            <input id="endtime" type="text" placeholder="结束日期" value="${end!}" style="line-height: 28px;width: 90px;height: 32px" readonly>
            <#--<input type="text"  class="form-control" data-options="editable:false" data-target="#endMsg" data-type="datebox" id="endtime" value="${end!}" style="float: left;width: 100px;">-->
            <button class="btn btn-info button" id ="btnQuery" style="float: right;margin-left: 10px;">查询</button>

        </div>

        <table class="layui-table" lay-size="sm">
            <thead>
                <tr>
                    <th width="15%"> 来源/用途 </th>
                    <th width="10%">经验值变化</th>
                    <th width="15%">累计</th>
                    <th width="30%">日期</th>
                    <th width="30%">备注</th>
                </tr>
            </thead>
            <tbody id="expTable">
                <#if  expLogList?? && (expLogList?size >0)>
                    <#list expLogList as expLog>
                    <tr align="center">
                        <td>
                        ${expLog.channel!}
                        </td>
                        <td>
                            <!-- 负数为grzx_wdjf_dhjl_blue；正数为grzx_wdjf_dhjl_red -->
                            <#if expLog.exp gt 0 >
                                <div class="grzx_wdjf_dhjl_orange">+${expLog.exp}</div>
                            <#else>
                                <div class="grzx_wdjf_dhjl_blue">${expLog.exp}</div>
                            </#if>
                        </td>
                        <td>${expLog.sums}</td>
                        <td>${expLog.createTime?string("yyyy年MM月dd日 HH:mm:ss")}</td>
                        <td>${expLog.remark!}</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#include "../common/pagination.ftl">
 </body>
    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
    <script data-main="${ctx}/js/abc/expLog" src="${ctx}/js/require.js"></script>
</html>
</@compress>