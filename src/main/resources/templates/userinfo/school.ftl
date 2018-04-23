<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <#include "../common/meta.ftl">
    <title>财税专家UC</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
        var snsurl="${snsurl}";
    </script>
    <style>
        table tr td{
            font-size: 14px;
            line-height: 40px;
            text-align: center;
        }
        select{
            width: 120px;
        }
        input,textarea{
            width:100%;
        }
        table tr th{
            font-size: 12px;
        }
    </style>
</head>

<body>
<div class="grzx_main_rt pc_zishiying">
    我的课程
</div>
<div class="grzx_wdrw_bbrw_kc pc_zishiying">
    <ul>
        <li>
            <div class="title">
                <h3><#if currMyStudyNumRes??>${currMyStudyNumRes.studyNumMonth!"0"}<#else>0</#if></h3>
                <p>本月学习课程数</p>
            </div>
            <div class="icon">
                <i class="iconfont">&#xe808;</i>
            </div>
        </li>
        <li style="color:#38c486">
            <div class="title">
                <h3><#if currMyStudyNumRes??>${currMyStudyNumRes.studyNumYear!"0"}<#else>0</#if></h3>
                <p>本年学习课程数</p>
            </div>
            <div class="icon">
                <i class="iconfont">&#xe806;</i>
            </div>
        </li>
        <li style="margin-right: 0;color:#2ecde6">
            <div class="title">
                <h3><#if currMyStudyNumRes??>${currMyStudyNumRes.studyQfb!"0"}<#else>0</#if></h3>
                <p>学习勤奋榜排名</p>
            </div>
            <div class="icon">
                <i style="font-size: 68px" class="iconfont">&#xe807;</i>
            </div>
        </li>
        <div class="clear"></div>
    </ul>
    <div class="clear"></div>
</div>
<div>
    <div style=" width: 100%" class="grzx_gridnav">
        <span class="active">学习历史</span>
        <span>收藏</span>
        <span>关注讲师</span>
        <div style="margin-bottom: 10px;" class="grzx-btns pc_zishiying">
            <button class="layui-btn layui-btn-sm rt">网格模式</button>
            <button class="layui-btn layui-btn-sm rt">列表模式</button>
        </div>
    </div>
    <div class="clear"></div>
</div>

<!-- 个人中心内容右侧 -->
<div class="grzx_wdjf_dhjl">

    <div class="kclbwg grzx_grid">

        <div class="kclbwg1 grzx_gridcontent">
            <ul class="clearfix">
                <#if currMyStudyRes??&&(currMyStudyRes?size>0)>
                    <#list currMyStudyRes as data>
                        <li>
                            <img src="${picurl!""}${data.cover!""}" onerror="this.src='${ctx}/images/noimg_0.jpg'" alt="">
                            <h5 class="duohang">${data.title!""}</h5>
                            <#--<p>购买时间：${(data.orderTime?string("yyyy-MM-dd"))!""}</p>-->
                            <p>学习时间：${(data.studyTime?string("yyyy-MM-dd"))!""}</p>
                            <p>
                               <#if data.evaluateNum??&&data.evaluateNum==0><span class="active" data-pj="pj" kc-id="${data.curriculumId!""}">评价</span></#if>
                                <span class="active xuexi" kcxx="kcxx" kc-id="${data.curriculumId!""}" kj-id="${data.coursewareId!""}" teachingMethod="${data.teachingMethod!""}">继续学习</span>
                            </p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>


        <div class="kclbwg1 grzx_gridcontent" style="display: none;">
            <ul class="clearfix">
                <#if curriculumListsyRes??&&(curriculumListsyRes?size>0)>
                    <#list curriculumListsyRes as data>
                        <li>
                            <img src="${picurl!""}${data.cover!""}" onerror="this.src='${ctx}/images/noimg_0.jpg'" alt="">
                            <h5>${data.title!""}</h5>
                            <p>日期：${(data.collectTime?string("yyyy-MM-dd"))!""}</p>
                            <p><span class="active xuexi2" kcxx="kcxx" kc-id="${data.curriculumId!""}" kj-id="${data.coursewareId!""}" teachingMethod="${data.teachingMethod!""}">开始学习</span></p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="kclbwg1 grzx_gridcontent" style="display: none;">
            <ul class="clearfix">
                <#if lecturerRes??&& (lecturerRes?size>0)>
                    <#list lecturerRes as lecturer>
                     <#if lecturer.status ==true>
                       <#assign  lect = lecturer.curriculumLecturer>
                        <li>
                            <div class="js_bannar">
                                <div class="js_tx">
                                <#if lect??>
                                    <img src="${picUrl!}${lect.lecturerPicture!}" onerror="this.src='${ctx}/images/icon_zx_03.png'">
                                    <p>${lect.lecturerName!}</p>
                                </#if>
                                </div>
                                <div class="js_xx">
                                    <p>好评度：
                                       <#if  lect.evaluateNum==0>
                                             0
                                        <#else >
                                            ${(lect.praiseNum/lect.evaluateNum*100)?string("###.0")}
                                        </#if>
                                        %</p>
                                    <a href="" target="_blank"><p>课程数：${lect.currNum!0}</p></a>
                                    <p>学生数：${lect.studentNum!0}</p>
                                </div>
                            </div>

                            <div class="js_js" lect-id="${lecturer.id!}" <#if lect??>intro="${lect.intro!}"</#if>>
                                <#--<#assign intro ="<p>谢老师具备八年以上财务会计从业经验，曾在多家大型企业工作，具备丰富的行业财务实操和管理经验。</p><p><br></p>">
                                <#if lect??>
                                    <#if lect.intro?? && (lect.intro?length>56)>
                                        ${lect.intro?substring(0,56)!}..
                                    <#else>
                                        ${lect.intro!}
                                    </#if>
                                </#if>-->
                            </div>
                            <p><span class="active unsubscribe" lect-id="${lecturer.lecturerId!}">取消关注</span></p>
                        </li>
                     </#if>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>



    <div class="kclbwg" style="display: none;">
        <table class="layui-table kclbwg2 grzx_list">
            <tr>
                <th>序号</th>
                <th>课程</th>
                <#--<th>购买时间</th>-->
                <th>学习时间</th>
                <th>操作</th>
            </tr>
<#if currMyStudyRes??&&(currMyStudyRes?size>0)>
    <#list currMyStudyRes as data>
            <tr>
                <td>${data_index+1}</td>
                <td>${data.title!""}</td>
                <#--<td>${(data.orderTime?string("yyyy-MM-dd"))!""}</td>-->
                <td>${(data.studyTime?string("yyyy-MM-dd"))!""}</td>
                <td>
                <#if data.evaluateNum??&&data.evaluateNum==0><a href="javascript:;" class="dd_zhifu" data-pj="pj" kc-id="${data.curriculumId!""}">评价</a></#if>
                    <a href="javascript:;" class="dd_zhifu" kcxx="kcxx" kc-id="${data.curriculumId!""}" kj-id="${data.coursewareId!""}" teachingMethod="${data.teachingMethod!""}">继续学习</a>
                </td>
            </tr>
    </#list></#if>
        </table>

        <table class="layui-table kclbwg2 grzx_list" style="display:none;">
            <tr>
                <th>序号</th>
                <th>课程</th>
                <th>日期</th>
                <th>操作</th>
            </tr>
<#if curriculumListsyRes??&&(curriculumListsyRes?size>0)>
    <#list curriculumListsyRes as data>
            <tr>
                <td>${data_index+1}</td>
                <td>${data.title!""}</td>
                <td>${(data.issueTime?string("yyyy-MM-dd"))!""}</td>
                <td><a href="javascript:;" class="dd_zhifu" kcxx="kcxx" kc-id="${data.curriculumId!""}" kj-id="${data.coursewareId!""}" teachingMethod="${data.teachingMethod!""}">开始学习</a>
                </td>
            </tr>
    </#list></#if>
        </table>
        <table class="layui-table kclbwg2 grzx_list" style="display:none;">
            <tr>
                <th width="60">序号</th>
                <th width="100">讲师名称</th>
                <th>讲师介绍</th>
                <th width="100">好评度</th>
                <th width="80">课程数</th>
                <th width="80">学生数</th>
                <th width="120">操作</th>
            </tr>
    <#if lecturerRes??&& (lecturerRes?size>0)>
        <#list lecturerRes as lecturer>
            <#if lecturer.status ==true>
            <#assign  lect = lecturer.curriculumLecturer>
            <tr>
                <td>${lecturer_index+1}</td>
                <td><#if lect??>${lect.lecturerName!}</#if></td>
                <td><#if lect??>${lect.intro!}</#if></td>
                <td>
                     <#if lect.evaluateNum==0>
                        0
                    <#else >
                        ${(lect.praiseNum/lect.evaluateNum*100)?string("###.0")}
                    </#if>%
                </td>
                <td>${lect.currNum!0}</td>
                <td>${lect.studentNum!0}</td>
                <td><a href="javascript:;" class="dd_zhifu unsubscribe" lect-id="${lecturer.lecturerId!}">取消关注</a>
                </td>
            </tr>
            </#if>
        </#list>
    </#if>
        </table>
    </div>









</div>








<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">请对本次课程进行评价</h5>
            </div>
            <div class="modal-body">
                <div class="order-evaluation clearfix">
                    <p>请认真对待此次评价哦！您的评价对我们真的非常重要！</p>
                    <div class="block">
                        <ul>
                            <li id="xping" data-default-index="0">
			                        <span>
			                            <img src="http://7xnlea.com2.z0.glb.qiniucdn.com/x1.png">
			                            <img src="http://7xnlea.com2.z0.glb.qiniucdn.com/x1.png">
			                            <img src="http://7xnlea.com2.z0.glb.qiniucdn.com/x1.png">
			                            <img src="http://7xnlea.com2.z0.glb.qiniucdn.com/x1.png">
			                            <img src="http://7xnlea.com2.z0.glb.qiniucdn.com/x1.png">
			                        </span>
                                <em class="level"></em>
                            </li>
                        </ul>
                    </div>
                    <#--<div class="order-evaluation-text">-->
                        <input type="hidden" id="kcid" value="">
                        <#--本次学习过程中，给您的印象是什么？-->
                    <#--</div>-->
                    <#--<div class="order-evaluation-checkbox">-->
                        <#--<ul class="clearfix">-->
                            <#--<li class="order-evaluation-check" data-impression="1">老师水平高<i></i></li>-->
                            <#--<li class="order-evaluation-check" data-impression="2">容易理解<i></i></li>-->
                            <#--<li class="order-evaluation-check" data-impression="3">内容丰富<i></i></li>-->
                            <#--<li class="order-evaluation-check" data-impression="4">细节不到位<i></i></li>-->
                            <#--<li class="order-evaluation-check" data-impression="5">能力待提高<i></i></li>-->
                            <#--<li class="order-evaluation-check" data-impression="6">发音不标准<i></i></li>-->
                        <#--</ul>-->
                    <#--</div>-->
                    <div class="order-evaluation-textarea">
                        <textarea name="content" id="TextArea1" ></textarea>
                        <span>还可以输入<em id="textCount">140</em>个字</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消评价</button>
                <button type="button" class="btn btn-info" id="addpj">提交评价</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/school" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>