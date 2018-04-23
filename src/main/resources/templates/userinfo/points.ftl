<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!--[if IE 8]>    <html class="ie8"> <![endif]-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<#include "../common/meta.ftl">
    <title>积分兑换记录</title>
    <script type="text/javascript">var ctx = "${ctx}";</script>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/jedate/skin/jedate.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/MyScores.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <style>
        .table tr td,th {
            font-size: 12px;
        }
        .jifen{
            background: #eeeeee;
            padding: 20px 0;
        }
        .jifen li{
            float: left;
            color: #666666;
            width: 20%;
        }
        .jifen li h3{
            color: #ff9000;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="MyScores-box">
    <div class="top-box">
        <h3>
            <span>我的积分</span>
            <a id = "rule" href="javascript:void (0)">【查看积分规则】</a>
        </h3>
        <div class="my-box">
                <ul>
                    <li style="background: #00bcd4">
                        <img src="${ctx}/images/keyongjifen.png">
                        <div class="jifenxinxi">
                            <span>可用积分：</span>
                            <p>${(points.myPoints)!"0"}</p>
                            <button id="chongzhi" class="layui-btn">积分充值</button>
                        </div>
                    </li>
                    <li style="background: #ff7372">
                        <img src="${ctx}/images/jifenshouru.png">
                        <div class="jifenxinxi">
                            <span>收入积分：</span>
                            <p>${(points.income)!"0"}</p>
                            <button id="renwu" class="layui-btn">赚取积分</button>
                        </div>
                    </li>
                    <li style="background: #ad79c3">
                        <img src="${ctx}/images/jifenzhichu.png">
                        <div class="jifenxinxi">
                            <span>支出积分：</span>
                            <p>${(points.outgo)!"0"}</p>
                            <button id="duihuan" class="layui-btn">积分兑换</button>
                        </div>
                    </li>
                </ul>
        </div>
    </div>
    <div >
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li <#if type=='01'>class="layui-this"</#if> type-id="jifen">积分明细</li>
                <li <#if type=='02'>class="layui-this"</#if> type-id="duihuan">兑换记录</li>
            </ul>
            <div class="layui-tab-content">
                <#if type=='01'>
                    <div class="layui-tab-item layui-show">
                        <form class="layui-form date-box">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label style="width: 120px;" class="layui-form-label">请选择日期：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input date"  id="begintime" value="${start!""}" placeholder="开始日期" style="line-height: 28px;text-align: center" readonly>
                                    </div>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input date"  id="endtime" value="${end!""}" placeholder="结束日期" style="line-height: 28px;text-align: center" readonly>
                                    </div>
                                    <button style="background: #14b9d5 " type="button" class="layui-btn" lay-submit lay-filter="formDemo" id="pointQuery">查询</button>
                                </div>
                            </div>
                        </form>
                        <table class="layui-table" lay-size="sm">
                            <thead>
                            <tr>
                                <th>来源/用途</th>
                                <th>积分变化</th>
                                <th>累计</th>
                                <th>日期</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody id="pointsTable">
                                <#if PointsLogs ?? && ( PointsLogs?size>0 )>
                                    <#list PointsLogs as pointLog>
                                    <tr>
                                        <td>${pointLog.channel!""}</td>
                                        <td style="font-size: 18px;">
                                            <#if pointLog.points gt 0>
                                                <div class="grzx_wdjf_dhjl_orange">+${pointLog.points}</div>
                                            <#else>
                                                <div class="grzx_wdjf_dhjl_blue">${pointLog.points}</div>
                                            </#if>
                                        </td>
                                        <td>${pointLog.sums!""}</td>
                                        <td>${pointLog.createTime?string("yyyy年MM月dd日 HH:mm")}</td>
                                        <td><p style="word-break:break-all">${pointLog.remark!""}</p></td>
                                    </tr>
                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                        <!--分页页签-->
                        <div id="pageOne">
                            <#if pagerSpec1?? && pagerSpec1.totalPage gt 0>
                                <input type="hidden" name="curr_link" value="${pagerSpec1.link?replace('[:page]', pagerSpec1.currentPage)}">
                                <nav aria-label="Page navigation" style="text-align: right;">
                                    <ul class="pagination">
                                        <li>
                                            <a href="javascript:void(0)" data-href="${pagerSpec1.link?replace('[:page]', pagerSpec1.currentPage-1)}" aria-label="Previous" <#if pagerSpec1.currentPage gt 1>class="js_page_previous" </#if>>
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <#if pagerSpec1.totalPage gt 0>
                                            <#assign i=1>
                                            <#list pagerSpec1.navOffsest..pagerSpec1.totalPage as index>
                                                <li data-page-index=${index}><a href="${pagerSpec1.link?replace('[:page]', index)}"  <#if index==pagerSpec1.currentPage>style="background: #14B9D5"</#if>  >${index}</a></li>
                                                <#if i==5><#break></#if>
                                                <#assign i=i+1>
                                            </#list>
                                        </#if>
                                        <li>
                                            <a href="javascript:void(0)" data-href="${pagerSpec1.link?replace('[:page]', pagerSpec1.currentPage+1)}" aria-label="Next" <#if pagerSpec1.currentPage lt pagerSpec1.totalPage> class="js_page_next"</#if>>
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </#if>
                        </div>
                    </div>
                <#elseif type=='02'>
                    <div class="layui-tab-item" style="display: block">
                        <form class="layui-form date-box">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label style="width: 120px;" class="layui-form-label">请选择日期：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input date"  id="duiHuanBegin"  placeholder="开始日期" style="line-height: 28px;text-align: center" readonly>
                                    </div>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input date"  id="duiHuanEnd" placeholder="结束日期" style="line-height: 28px;text-align: center" readonly>
                                    </div>
                                    <button style="background: #14b9d5 " type="button" class="layui-btn" lay-submit lay-filter="formDemo" id="orderQuery">查询</button>
                                </div>
                            </div>
                        </form>
                        <div class="layui-tab-item layui-show">
                            <table class="layui-table">
                                <colgroup>
                                    <col width="33%">
                                    <col width="33%">
                                    <col width="33%">
                                </colgroup>
                                <thead style="color: #989898">
                                <tr>
                                    <th>来源/用途</th>
                                    <th>积分变化</th>
                                    <th>日期</th>
                                </tr>
                                </thead>
                                <tbody id="duihuanTable">
                                    <#if orderList?? && (orderList?size >0 )>
                                        <#list orderList as order>
                                            <#if (order.orderProductBOList)?? && (order.orderProductBOList?size>0)>
                                                <#list order.orderProductBOList as orderProduct>
                                                    <#assign  product = orderProduct>
                                                <tr>
                                                    <td>
                                                        <#if product ??>
                                                            <h4>${(product.name)!""}</h4>订单号：${order.orderNo!""}
                                                        <#else>
                                                            <h4></h4><br/>订单号：${order.orderNo!""}
                                                        </#if>
                                                    </td>
                                                    <td style="font-size: 18px;" class="yellow">-${(order.totalPrice)!"0"}</td>
                                                    <td>${order.lastUpdate?string("yyyy年MM月dd日 HH:mm:ss")}</span></td>
                                                </tr>
                                                </#list>
                                            </#if>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>
                            <!--分页页签-->
                            <div id="pageTwo">
                                <#if pagerSpec2?? && pagerSpec2.totalPage gt 0>
                                    <input type="hidden" name="curr_link" value="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage)}">
                                    <nav aria-label="Page navigation" style="text-align: right;">
                                        <ul class="pagination">
                                            <li>
                                                <a href="javascript:void(0)" data-href="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage-1)}" aria-label="Previous" <#if pagerSpec2.currentPage gt 1>class="js_page_previous" </#if>>
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <#if pagerSpec2.totalPage gt 0>
                                                <#assign i=1>
                                                <#list pagerSpec2.navOffsest..pagerSpec2.totalPage as index>
                                                    <li data-page-index=${index}><a href="${pagerSpec2.link?replace('[:page]', index)}"  <#if index==pagerSpec2.currentPage>style="background: #14B9D5"</#if>  >${index}</a></li>
                                                    <#if i==5><#break></#if>
                                                    <#assign i=i+1>
                                                </#list>
                                            </#if>
                                            <li>
                                                <a href="javascript:void(0)" data-href="${pagerSpec2.link?replace('[:page]', pagerSpec2.currentPage+1)}" aria-label="Next" <#if pagerSpec2.currentPage lt pagerSpec2.totalPage> class="js_page_next"</#if>>
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
   <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
   <script data-main="${ctx}/js/abc/points" src="${ctx}/js/require.js"></script>
</html>
</@compress>