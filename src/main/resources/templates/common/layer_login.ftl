<div id="login_layer" style="display: none;">
    <div class="lo-img">
        <img src="${ctx}/images/csdl.png" alt="">
    </div>
    <div class="lo-userpass" style="padding:35px 50px 20px">
        <form  id="modalLogin" class="layui-form">

            <div class="layui-form-item">
                <input type="text"  name="username"  placeholder="请输入手机号或用户名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="hidden" class="form-control" name="type" value="username">
                <input type="password" name="password"  placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-big fl" style="width: 245px;" id="ljLogin">立刻登录</button>
                <a href="${cswurl}/index.html" title="网站首页" class="layui-btn layui-btn-primary layui-btn-big fr" id="ljLogins" style="margin: 0;">暂不登录</a>
                <div class="clear"></div>
            </div>
        </form>
    </div>
</div>

