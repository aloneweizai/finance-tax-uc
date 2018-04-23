<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="UTF-8">
    <#include "../common/meta.ftl">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title></title>
    <script type="text/javascript">var ctx ="${ctx}"</script>
    <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">


    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link media="screen and (min-width:481px)" href="${ctx}/css/CheckIn.css" rel="stylesheet" type="text/css">

    <style>
        .layui-table td,th{padding: 7px 15px;}
        .content_box{ overflow: hidden;}
    </style>
</head>
<body style="background:#00b5d4;">
<!--签到日历-->
<div id="check-calendar-tmpl">
    <div class="home-box">
        <div class="content_box">
            <div class="home calendar_left">

                <img src="${ctx}/images/rili.png">
                <a href="javascript:void (0)" id="qiandao">
                    <i class="iconfont rilibiaoqian">&#xe840;</i>
                </a>
                <div class="ym-box">

                    <div class="left">
                        <i class="iconfont" data-index="0"></i>
                    </div>
                    <div class="cent">
                        <span class="year">${year!}</span>
                        <span class="month">${month!}</span>
                        <span class="date">${day!}</span>
                    </div>
                    <div class="right">
                        <i class="iconfont" data-index="1"></i>
                    </div>
                </div>
                <div class="ymd">
                    <table>
                        <thead id="thead">
                        <tr>
                            <td>周一</td>
                            <td>周二</td>
                            <td>周三</td>
                            <td>周四</td>
                            <td>周五</td>
                            <td>周六</td>
                            <td>周日</td>
                        </tr>
                        </thead>
                        <tbody class="tbody" precheck="${precheck!"0"}">
       <#--                 <tr>
                            <td></td>
                            <td><p class="gray">01</p></td>
                            <td><p class="gray">02</p></td>
                            <td><p class="gray">03</p></td>
                            <td><p class="gray">04</p></td>
                            <td><p class="gray">05</p></td>
                            <td><p class="gray">06</p></td>
                        </tr>
                        <tr>
                            <td>
                                <p class="gray">07</p>
                            </td>
                            <td>
                                <p class="gray">08</p>
                            </td>
                            <td>
                                <p class="gray">09</p>
                            </td>
                            <td>
                                <p class="gray">10</p>
                            </td>
                            <td>
                                <p class="gray">11</p>
                            </td>
                            <td>
                                <p class="gray">12</p>
                            </td>
                            <td>
                                <p class="gray">13</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="gray">14</p>
                            </td>
                            <td>
                                <p class="gray">15</p>
                            </td>
                            <td><p class="gray">16</p></td>
                            <td><p class="gray">17</p></td>
                            <td><p class="gray">18</p></td>
                            <td><p class="gray">19</p></td>
                            <td><p class="gray">20</p></td>
                        </tr>
                        <tr>
                            <td><p class="gray">21</p></td>
                            <td><p class="color">22</p></td>
                            <td><p>23</p></td>
                            <td><p>24</p></td>
                            <td><p>25</p></td>
                            <td><p>26</p></td>
                            <td><p>27</p></td>
                        </tr>
                        <tr>
                            <td><p>28</p></td>
                            <td><p>29</p></td>
                            <td><p>30</p></td>
                            <td><p>31</p></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>-->
              <#--          <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>-->
                        </tbody>
                    </table>
                </div>
                <div class="jifen_bottom">
                    <div class="jifen_anniu">


                            <button class="button btn signin" type="button" >立即签到</button>

                        <a href="${ctx}/member/member_integral_uc.html" class="pointsfor">
                            积分兑换
                        </a>
                    </div>
                    <img src="${ctx}/images/guize.png" style="clear: both;display: block;">
                    <div class="rule">
                        <ol>
                            <li>1，第一天签到5分，第二天签到10分，第三天签到15分，第四天签到20分。</li>
                            <li>2，连续签到4天以上，以后每天可领20分。</li>
                            <li>3，若中断签到，将重新从5分开始。</li>
                            <li>4，每月补签最多三次，补签一次扣用户${precheck!"0"}积分。</li>
                        </ol>
                    </div>
                   <#-- <p id="jifen"></p>-->
                </div>
            </div>
            <div class="home2 calendar_right">
                <img src="${ctx}/images/paiming.png">
         <#--       <div class="rule">
                    <ol>
                        <li>1，第一天签到5分，第二天签到10分，第三天签到15分，第四天签到20分。</li>
                        <li>2，连续签到4天以上，以后每天可领20分。</li>
                        <li>3，若中断签到，将重新从5分开始。</li>
                        <li>4，每月补签最多三次，补签一次扣用户${precheck!"0"}积分。</li>
                    </ol>
                </div>-->
                <div class="nosignin">
                <#--    <table class="layui-table" style="width:305px;">
                        <thead>
                        <tr>
                            <th width="22%">排名</th> 22 45 33
                            <th width="45%">昵称</th>
                            <th width="33%">累计天数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if ranks ?? && (ranks?size>0)>
                            <#list ranks as rank>
                                <#assign order = rank_index + 1>
                            <tr>
                                <td>${order}</td>
                                <td>${rank.nickname!}</td>
                                <td>${rank.count!}</td>
                            </tr>
                               <#if order == 8>
                                   <#break>
                               </#if>
                            </#list>
                        </#if>
                        <tr> <td class="tbody_one">1</td> <td>王五</td> <td>12</td> </tr> <tr> <td>2</td> <td>财税专家大头</td> <td>6</td> </tr> <tr> <td>3</td> <td>财税706367</td> <td>6</td> </tr> <tr> <td>4</td> <td>财税018839</td> <td>5</td> </tr> <tr> <td>5</td> <td>财税072937</td> <td>5</td> </tr> <tr> <td>6</td> <td>(ಥ_ಥ)</td> <td>5</td> </tr> <tr> <td>7</td> <td>财税597750</td> <td>3</td> </tr> <tr> <td>8</td> <td>财税084042</td> <td>3</td> </tr>
                        </tbody>
                    </table>-->
                    <div class="nosignin-table">
                         <div class="nosignin-top">
                              <span class="nosignin-p">排名</span>
                              <span class="nosignin-n">昵称</span>
                              <span class="nosignin-r">累计天数</span>
                         </div>
                        <#if ranks ?? && (ranks?size>0)>
                            <#list ranks as rank>
                                <#assign order = rank_index + 1>
                                <div class="nosignin-content">
                                  <span class="nosignin-content_p">
                                    <#if order==1>
                                        <small class="on_1">${order}</small>
                                    <#elseif order==2>
                                        <small class="on_2">${order}</small>
                                    <#elseif order==3>
                                        <small class="on_3">${order}</small>
                                    <#else>
                                        ${order}
                                    </#if>
                                  </span>
                                  <span class="nosignin-content_n">${rank.nickname!}</span>
                                  <span class="nosignin-content_r">${rank.count!}</span>
                            </div>
                                <#if order == 15>

                                    <#break>
                                </#if>
                            </#list>
                        </#if>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/member/member_checkIn" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>