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
        <div style="height: 40px; text-align: right">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 120px;">请选择日期：</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" id="begintime" placeholder="开始日期" autocomplete="off" class="layui-input" readonly>
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" id="endtime" placeholder="结束日期" autocomplete="off" class="layui-input" readonly>
                    </div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <button type="text" id ="btnQuery" class="layui-btn">查询</button>
                    </div>
                </div>
            </div>
        </div>
        <table class="layui-table" lay-size="sm">
            <thead>
                <tr>
                    <th width="15%"> 来源/用途 </th>
                    <th width="14%">经验值变化</th>
                    <th width="15%">累计</th>
                    <th width="26%">日期</th>
                    <th width="30%">备注</th>
                </tr>
            </thead>
            <tbody id="expTable">
                <#if expList?? && (expList?size >0)>
                 <#list expList as exp>
                    <tr align="center">
                        <td>
                            ${exp.name!}
                        </td>
                        <td>
                            <!-- 负数为grzx_wdjf_dhjl_blue；正数为grzx_wdjf_dhjl_red -->
                            <#if exp.income gt exp.outgo >
                                <div class="grzx_wdjf_dhjl_orange">+${exp.income -exp.outgo}</div>
                            <#else>
                                <div class="grzx_wdjf_dhjl_blue">${exp.income -exp.outgo}</div>
                            </#if>
                        </td>
                        <td>${exp.usableExp!""}</td>
                        <td>${exp.createTime?string("yyyy年MM月dd日 HH:mm:ss")}</td>
                        <td>${exp.remark!""}</td>
                    </tr>
                 </#list>
                <#elseif  expLogList?? && (expLogList?size >0)>
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