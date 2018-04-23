<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <#include "../common/meta.ftl">

    <title>新增/修改地址</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- head -->
<div class="grzx_main_rt pc_zishiying"><span id="title">${data!"新增地址"}</span>
    <a href="${ctx}/userinfo/useraddr.html" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的地址</a>
</div>
<div class="clear"></div>
<form name="form1" id="dizhiform" method="post" action="save.html">
    <div class="tableBorder">
        <p><span>收货人姓名</span><input class="layui-input" type="text" size="30" name="name"  id="xingming" value="${address.name!}"/>
            <input type="hidden" size="30" name="id" value="${address.id!}"/></p>

        <p><span>手机号码</span><input class="layui-input" type="text" size="30" name="phone" id="phone" value="${address.phone!}"/></p>

        <p><span style="display: block">收货地址</span>
            <select id="s_province" name="province" data-value="${address.province!}" data-target="#addmsg"><option value="">请选择</option>
            </select>
            <select id="s_city" name="city" data-value="${address.city!}" data-target="#addmsg"><option value="">请选择</option>
            </select>
            <select id="s_county" name="area" data-value="${address.area!}" data-target="#addmsg"><option value="">请选择</option>
            </select>
            <span id="addmsg"></span>
            <div id="show"></div>
        </p>

        <p><span>详细地址</span><textarea placeholder="请输入详细地址" rows="5" class="layui-textarea" id="detail" name="detail">${address.detail!}</textarea></p>

        <div class="modal-footer">
            <button type="button" id="tijiao" class="layui-btn">确定</button>
        <#--<button type="button" id="fanhui" class="btn btn-primary">返回</button>-->
        </div>

    </div>

    </form>
    <script data-main="${ctx}/js/abc/savedizhi" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>