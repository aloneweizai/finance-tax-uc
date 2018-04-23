require(["../../config"], function () {
    require(["jquery.full","jquery.md5","cebianlan","footer","lock"], function ($) {

        $(function (){
            $('#overlay').remove();
            $('#loadingTip').remove();
        });

        $(function (){
            $("#login_layer").find("input[name='password']").eq(0).capsLockTip();
        })

        //会员功能连接事件
        $(document).on("click", "a[data-lj='1']", function () {
            var url = $(this).data('url');
            $('#external-frame').attr('src', url);
            $('#myModal').modal('hide');
        })


        var formusername={
            theme: 'yellow_top',
            stopOnError:true,
            timely: 1,
            rules: {
            },
            fields: {
                "username": {
                    rule:"手机号码:required;phone;"
                },
                "password":{
                    rule:"密码:required;"
                }
            }
        };

        $('#ljLogin').click(function (){
            $('#modalLogin').validator('destroy');
            var $form2 = $("#modalLogin").validator(formusername);
            if($form2.isValid()){
                $.pop();
                var data=$("#modalLogin").serializeJson();
                var da=JSON.parse(data);
                da.password=$.md5(da.password);
                data=JSON.stringify(da);
                $.ajax({
                    type:'POST',
                    url: ctx+"/login_v2.html" ,
                    data:data,
                    contentType:"application/json",
                    success: function (data){
                        $.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    $.hideLogin();
                                    var id = $('#user_id').val();
                                    if (id != data.id) {
                                        //window.location.href = ctx + "/index.html";
                                        location.reload();
                                    }
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    } ,
                    dataType: "JSON"
                });
            }
        });


        $("#logout").click(function (){
            abc.confirm('是否退出？',{icon:3}, function(){
                $.ajax({
                    url: ctx+"/logout.html",
                    type: "POST",
                    dataType: 'json',
                    success: function(re) {
                        window.location.href=re.cswurl+'/index.html'
                    }
                });
            });
        });

        $('.nemu-box ul li').click(function (){
            var link=$(this).attr("name");
            var type=$(this).attr("type");
            var url=$(this).attr("link");
            var waibu=$(this).attr("waibu");
            $.ajax({
                type: "POST",
                url: ctx+"/overtime.html",
                dataType: "JSON",
                async: false,
                beforeSend: function () {
                    $.pop();       //打开一个遮罩层，或者直接禁用你的按钮
                },
                complete: function () {
                    $.close();      //取消遮罩层，或者回复按钮
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == "2000") {
                                if(typeof(waibu) == "undefined"){
                                    if (!link) {
                                        return;
                                    }
                                    if ("undefined" == typeof type) {
                                        var iframet = parent.document.getElementById("external-frame");
                                        var iframes = $(".uc_iframe").attr('src', link);
                                        var iframe = document.getElementById("external-frame");
                                        // if (iframe.attachEvent) {
                                        //     iframe.attachEvent("onreadystatechange", function () {
                                        //         if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                                        //             $.close();
                                        //         }
                                        //     });
                                        // } else {
                                        //     iframe.addEventListener("load", function () {
                                        //         $.close();
                                        //     }, false);
                                        // }
                                    } else {
                                        if("undefined" != typeof top.loc){
                                            try{
                                                top.loc.SwitchHref("#member");
                                                var b = $.base64.btoa(url);
                                                window.location.href = link + "?url=" + b;
                                            }catch (e){
                                                var b = $.base64.btoa(url);
                                                window.location.href = link + "?url=" + b;
                                            }
                                        }else{
                                            var b = $.base64.btoa(url);
                                            window.location.href = link + "?url=" + b;
                                        }
                                    }
                                } else{
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = link; //url 是你得到的连接
                                    //el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                }
                            } else {
                                try{
                                    if(typeof window.top.loc !=='undefined'){
                                        window.top.loc.GoHome();
                                    }else{
                                        window.parent.layer.open({
                                            type: 1
                                            ,title: false
                                            ,area: '480px'
                                            ,zIndex:9000
                                            ,content:$("#login_layer")
                                            ,closeBtn: 0
                                            ,btnAlign: 'c'
                                        });
                                    }
                                }catch (e){
                                    window.parent.layer.open({
                                        type: 1
                                        ,title: false
                                        ,area: '480px'
                                        ,zIndex:9000
                                        ,content:$("#login_layer")
                                        ,closeBtn: 0
                                        ,btnAlign: 'c'
                                    });
                                }
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });

//个人中心切换iframe src地址

        $("#hy_left>li").click(function(){
            var test=$(this).attr("name");
            var waibu=$(this).attr("waibu");
            $.ajax({
                type: "POST",
                url: ctx+"/overtime.html",
                dataType: "JSON",
                async: false,
                beforeSend: function () {
                    $.pop();       //打开一个遮罩层，或者直接禁用你的按钮
                },
                complete: function () {
                    $.close();      //取消遮罩层，或者回复按钮
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == "2000") {
                                if (typeof(waibu) == "undefined") {
                                    $(".uc_iframe").attr('src', test);
                                } else {
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = test; //url 是你得到的连接
                                    //el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                }
                            } else {
                                try{
                                    if(typeof window.top.loc !=='undefined'){
                                        window.top.loc.GoHome();
                                    }else{
                                        window.parent.layer.open({
                                            type: 1
                                            ,title: false
                                            ,area: '480px'
                                            ,zIndex:9000
                                            ,content:$("#login_layer")
                                            ,closeBtn: 0
                                            ,btnAlign: 'c'
                                        });
                                    }
                                }catch (e){
                                    window.parent.layer.open({
                                        type: 1
                                        ,title: false
                                        ,area: '480px'
                                        ,zIndex:9000
                                        ,content:$("#login_layer")
                                        ,closeBtn: 0
                                        ,btnAlign: 'c'
                                    });
                                }

                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        })
        $('#wszl').click(function (){
            $.ajax({
                type: "POST",
                url: ctx+"/overtime.html",
                dataType: "JSON",
                async: false,
                beforeSend: function () {
                    $.pop();       //打开一个遮罩层，或者直接禁用你的按钮
                },
                complete: function () {
                    $.close();      //取消遮罩层，或者回复按钮
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == "2000") {
                                var iframes = $(".uc_iframe");
                                iframes.attr('src', ctx+'/userinfo/userinfolist.html');
                                var iframe = document.getElementById("external-frame");
                                if (iframe.attachEvent) {
                                    iframe.attachEvent("onreadystatechange", function () {
                                        if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                                            //$.close();
                                        }
                                    });
                                } else {
                                    iframe.addEventListener("load", function () {
                                        //$.close();
                                    }, false);
                                }
                            } else {
                                try{
                                    if(typeof window.top.loc !=='undefined'){
                                        window.top.loc.GoHome();
                                    }else{
                                        window.parent.layer.open({
                                            type: 1
                                            ,title: false
                                            ,area: '480px'
                                            ,zIndex:9000
                                            ,content:$("#login_layer")
                                            ,closeBtn: 0
                                            ,btnAlign: 'c'
                                        });
                                    }
                                }catch (e){
                                    window.parent.layer.open({
                                        type: 1
                                        ,title: false
                                        ,area: '480px'
                                        ,zIndex:9000
                                        ,content:$("#login_layer")
                                        ,closeBtn: 0
                                        ,btnAlign: 'c'
                                    });
                                }
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });



        $(function (){
            var iframe = document.getElementById("external-frame");
            try{
                var bHeight = iframe.contentWindow.document.body.scrollHeight;
                var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
                var height = Math.max(bHeight, dHeight);
                iframe.height = height;
            }catch (ex){}
        });

        //iframe 高度
// 定义一个函数，定时调用并刷新iframe高度

        var initHelp = function(){
            var arr = ['collect','fans','follow','team'];
            for (var i=0;i<arr.length;i++)
            {
                var js_cnt_class = "js_"+arr[i]+"_cnt";
                $.ajax({
                    type: "GET",
                    url: ctx+"/help/my/"+arr[i]+"/ajaxList.json",
                    data: "",
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (data && data.code == "2000") {
                                $("." + js_cnt_class).text(data.total);
                            } else {
                                abc.msg(data.message,{icon: 2})
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }
        };
        //initHelp();
        $('#chenggong').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx+'/member/my_index.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        $('#shibai').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/member/my_index.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //开通会员
        $('#kthy').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src', ctx+'/member/order_member.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //会员等级
        $('#huiyuan').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src', ctx+'/member/member_rights.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //勋章
        $('#xunz').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/userinfo/user_level_rule.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //我的积分
        $('#points').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/pointsExchange/points.php');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        $('.mypoint').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/pointsExchange/points.php');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //积分商城
        $('#jifen').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx+'/member/member_integral.html');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //经验值
        $('#jingyan').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/userinfo/expLog.php');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        //签到
        $('#qiandao').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/member/checkIn.php');
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        $(".vip>a").click(function(){
            var url = $(this).attr("data-url");
            var iframes=$(".uc_iframe");
            iframes.attr('src',url);
            $('#myModal').modal('hide');
            $.pop();
            var iframe=document.getElementById("external-frame");
            if(iframe.attachEvent){
                iframe.attachEvent("onreadystatechange", function() {
                    if (iframe.readyState === "complete" || iframe.readyState == "loaded") {
                        $.close();
                    }
                });
            }else{
                iframe.addEventListener("load", function() {
                    $.close();
                }, false);
            }
        });

        $(function (){
            if(sessionStorage.getItem("count")==null){
                // var urlgg=ctx+'/member/member_rights.html'
                var urlgg=ctx+'/member/member_mall.html'
                var b = $.base64.btoa(urlgg);
                if(typeof top.loc!="undefined"){
                    //会员中心、个人中心的广告弹出
                    if(IsPC()){
                        layer.open({
                            title: false
                            ,type:1
                            ,area: ['820px','550']
                            ,shade: [0.7, '#000']
                            ,content:'<a href="'+ctx+'/member/external_member_index.html?url='+b+'"><img src="'+ctx+'/images/banjiaqianggou.png" height="550" width="820"></a>'
                        });
                    }
                }else{
                    //会员中心、个人中心的广告弹出
                    if(IsPC()) {
                        layer.open({
                            title: false
                            ,type: 1
                            ,area: ['820px', '550']
                            ,shade: [0.7, '#000']
                            ,content: '<a href="' + ctx + '/member/member_index.html?url=' + b + '"><img src="' + ctx + '/images/banjiaqianggou.png" height="550" width="820"></a>'
                        });
                    }
                }
                $('.layui-layer').css('background','none');
                $('.layui-layer').css('box-shadow','none')
                sessionStorage.setItem("count",1);
            }
        })

        function IsPC(){
            var userAgentInfo = navigator.userAgent;
            var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
            var flag = true;
            for (var v = 0; v < Agents.length; v++) {
                if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }
            }
            return flag;
        }

    });
});