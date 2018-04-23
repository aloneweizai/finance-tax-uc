<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>修改头像</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <!--上传头像-->
    <link href="${ctx}/css/jquery.Jcrop.css" rel="stylesheet">
    <style>
        .grzx_btn {
            border: none;
            border-radius: 3px;
            font-size: 12px;
            line-height: 1.5;
            padding: 5px 10px;
            cursor: pointer;
            color: #fff;
            background: #14B9D5;
            margin-right: 12px;
            margin-top: 10px;
            text-align: right;
        }
    </style>

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
    <div class="grzx_main_rt">修改头像
        <a href="${ctx}/userinfo/userinfolist.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>基本资料</a>
    </div>
<div class="clear"></div>

<div style="width: 200px; height: 50px;" class="layui-form">
    <input type="button" name="" value="选择头像" class="layui-btn" id="xztp" style="float: left;margin-right: 10px;">
    <form id="tx" enctype="multipart/form-data">
        <input type="file" id="filepath" name="file" style="display: none;">
        <input type="hidden" name="width" id="width">
        <input type="hidden" name="height" id="height">
        <input type="hidden" name="x" id="x">
        <input type="hidden" name="y" id="y">
        <input type="button" name="" value="上传头像" class="layui-btn" id="sctx">
    </form>
</div>
<div style="width: 100%">
    <img src="${ctx}/images/txscmrt.jpg" id="imgcj" width="300" height="300">
</div>



    <script data-main="${ctx}/js/abc/user_info_imge" src="${ctx}/js/require.js"></script>

</html>
</@compress>