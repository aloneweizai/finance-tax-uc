<!DOCTYPE html>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>电子申报缴税企业注册</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css"/>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<style>
    .qy_bg{background:url(images/login1.jpg) #00bcd4 center; height: 100%; width: 100%; position: absolute;}
    .qy_login{background: url(images/bg_w.png); width: 600px; height: 500px; position: absolute; top: 50%; left: 50%; margin: -250px 0 0 -300px;}
    .qy_title{font-size: 24px; font-weight: bold; font-family: "微软雅黑"; text-align: center; color: #333; height: 60px; line-height: 60px; margin: 15px 20px; border-bottom: 1px solid #fff;}
    .qy_form{margin: 30px 80px 0 20px;}
    .qy_tishi{font-size: 12px; color: #666;}
    .qy_tishi span{color:#FD482C;}
    .layui-input-block{margin-left: 120px;}
    .layui-form-label{width:90px;}


    @media screen and (max-width:480px){
        .qy_bg{background:none}
        .qy_login{background:#f2f2f2; width: 340px; height: 520px; position: absolute; top:10px; left: 50%; margin: 30px 0 0 -170px;}
        .qy_title{font-size: 20px; height: 40px; line-height: 40px; margin: 15px 10px; }
        .qy_form{margin: 10px}
        .layui-input-block{margin-left: 0;}
        .layui-form-label{width:90px; text-align: left; float: none; padding:0 0 5px 5px;}
        .layui-form-item{margin-bottom: 10px;}
    }
    .layui-form-radio span{
        font-size: 17px;
    }
</style>
<body>
<div class="qy_bg">
    <div class="qy_login">
        <div class="qy_title">电子申报缴税企业注册</div>
        <div class="qy_form">
            <form class="layui-form" id="qyzc">
                <div class="layui-form-item">
                    <label class="layui-form-label">纳税人识别号</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" id="nsrsbh" placeholder="请输入纳税人识别号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">纳税企业名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" id="nsqymc" placeholder="请输入纳税企业名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" id="dxan">
                    <label class="layui-form-label">用户类型</label>
                    <div class="layui-input-block">
                        <input type="radio" name="type" value="1" title="付费电子申报用户" checked>
                        <div class="qy_tishi">可以申报增值税、消费税、企业所得税、月季度申报表、企业所得税A、B类年度纳税申报表、各类财务报表。由艾博克提供全过程技术支持及业务咨询服务，确保办税无忧，此服务为收费服务。</div>
                    </div>
                    <div class="layui-input-block">
                        <input type="radio" name="type" value="2" title="免费汇算清缴用户">
                        <div class="qy_tishi">可以申报企业所得税A、B类年度纳税申报表，财务报表年报。由艾博克提供技术支持，但不提供业务咨询服务，此服务为免费服务。<br><br><span>咨询服务：QQ4008873133 ， 电话：4008873133</span></div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" type="button" id="submit" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/company/index" src="${ctx}/js/require.js"></script>
</body>
</html>
