require(["../config"], function () {
    require(["jquery.full","jquery.md5","cebianlan","footer","jquery.base64.min","lock"], function ($) {

        $(function (){
            $('#overlay').remove();
            $('#loadingTip').remove();
        });

        $(function (){
            $("#login_layer").find("input[name='password']").eq(0).capsLockTip();
        })

        $('.skillbar').each(function(){
            $(this).find('.skillbar-bar').animate({
                width:$(this).attr('data-percent')
            },3000);
        });
        $("#hide").click(function(){
            $(".advertising").hide();
        });

        //固定导航条在顶部
        $(document).on('fixe',function() {
            var WIDTH = $(window).width();
            var FIXE = $('.fixe');

            $(window).scroll(function () {
                var HEIGHT = $(window).scrollTop();
                FIXE.css('top', -HEIGHT);
            });
            //IE8兼容
            if (document.all && document.querySelector && !document.addEventListener) {
                if (WIDTH <= 1284) {
                    FIXE.addClass('fixes');
                }
            }
        }).trigger('fixe');

        $(".grzx_main_rt_nav>ul>li").click(function(){
            $(this).addClass('hover').siblings('.hover').removeClass('hover');
            var test="#"+$(this).attr("test");
            var length=$(this).length+1;
            for(var i=1;i<=length;i++){
                if("#test"+i==test){
                    $("#test"+i).show();
                }else{
                    $("#test"+i).hide();
                }
            }
        })

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
        $(function (){
            var iframet = $('#external-frame');
            $(".uc_iframe").attr('src', iframet.attr("url"));
        });


        $(function (){
            //加载用户基本信息
            $.ajax({
                url: ctx+"/index_userinfo.html?v="+new Date().getTime(),
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('.index_userinfo').html(re);
                    // $.iframeHeight();
                }
            });


            //加载消息角标
            $.ajax({
                url: ctx+"/message_count_index.html?v="+new Date().getTime(),
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('#messagecount').html(re);
                    // $.iframeHeight();
                }
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
                                        $(iframet).height(1150);
                                        var iframes = $(".uc_iframe").attr('src', link);
                                        var iframe = document.getElementById("external-frame");
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
                                //$.close();
                                try{
                                    if(typeof window.top.loc !=='undefined'){
                                        window.top.loc.GoHome();
                                    }else{
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


        //关闭uc首页广告
        $(document).on('click','#hide',function(){
            $(this).parent().parent().hide();
        });



//个人中心切换iframe src地址

        $("#dh_left li,dd").click(function(){
            var index=$(this).index();
            $('#dh_left li').each(function (){
                if($(this).index()==index){
                    $(this).addClass("dq_hover");
                }else{
                    $(this).removeClass("dq_hover");
                }
            });

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
                                        //$.close();
                                        return;
                                    }
                                    if ("undefined" == typeof type) {
                                        $(".uc_iframe").attr('src', link);
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
                                    el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                }
                                //$.close();
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
                                //$.close();
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

        $(document).on('click','#wszl',function (){
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
                                $.close();
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
                            }
                        } else {
                            abc.msg("服务器繁忙请稍后再试...",{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });



        $("#wdbb").click(function(){
            var link = $(this).attr("name");
            window.parent.location.href = link;
        })
        $("#bangbang>dd").click(function(){
            var bangbang=$(this).attr("name");
            $(".uc_iframe").attr('src',bangbang)
        })



        //个人中心/我的订单
        $("#orderCancel").click(function(){
            var order = $(this).attr("href");
            $(".uc_iframe").attr("src",order);
        });



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
                                        window.location.href = ctx + "/index.html";
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


        /**订单付款*/
        $('a[class="dd_zhifu"]').click(function (){
            var orderId=$(this).attr("order-id");
            var goodsid=$(this).attr("goods-id");
            var payMethod = $(this).attr("pay-method");
            //如果是课程，先查询次课程是否购买
            var channels = $(this).attr("channels");
            if(channels =="CSKT"){
                $.ajax({
                    type: "GET",
                    url: ctx + "/userinfo/ketang" +"?goodsId=" +goodsid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                //debugger;
                                if (data.kechen == false) {
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                    el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                } else {
                                    abc.alert("该课程已购买！",{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else {
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }
        });

        /**订单详情*/
        $('a[class="orderdetail"]').click(function (){
            var orderId=$(this).attr("detail-id");
            window.location.href=ctx+ "/orderDetail/"+orderId;
            layer.load();
        });

        /**删除订单*/
        $("a[class='deleteorder']").click(function (){
            var delete_id=$(this).attr('delete-id');
            abc.confirm('是否删除订单？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/orderList/delete/" + delete_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    beforeSend: function () {
                        layer.load();        //打开一个遮罩层，或者直接禁用你的按钮
                    },
                    complete: function () {
                        layer.close(layer.load());     //取消遮罩层，或者回复按钮
                    },
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('删除订单成功',{icon:1},function (){
                                        window.location.href = ctx+"/user_index.html";
                                    });
                                } else {
                                    abc.alert(data.data.message,{icon: 2});
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
        })

        /**确认收货*/
        $("a[class='confirmorder']").click(function (){
            var confirm_id =$(this).attr('confirm-id');
            abc.confirm('确认收货？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/userinfo/orderList/confirm/" + confirm_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('确认收货成功',{icon:1},function (){
                                        window.location.href = ctx+"/user_index.html";
                                    });
                                } else {
                                    abc.alert(data.data.message,{icon: 2});
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
        })

        //会员等级
        $(document).on('click','#huiyuan',function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx+'/member/member_rights.html');
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
        $(document).on('click','#jifen',function (){
            if("undefined" != typeof top.loc){
                var zhu=ctx+"/member/external_member_index.html";
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }else{
                var zhu=$(this).attr("url-zhu");
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
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

        //签到
        $(document).on('click','#qiandao',function (){
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

        //头像
        $('#touxiangimag').click(function (){
            var iframes=$(".uc_iframe");
            iframes.attr('src',ctx + '/userinfo/imageeditshow.html');
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


        $(document).on('click','#kthy',function (){
            if("undefined" != typeof top.loc){
                var zhu=ctx+"/member/external_member_index.html";
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }else{
                var zhu=$(this).attr("url-zhu");
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }
        });

        $(document).on('click','#hyxf',function (){
            if("undefined" != typeof top.loc){
                var zhu=ctx+"/member/external_member_index.html";
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }else{
                var zhu=$(this).attr("url-zhu");
                var fu=$(this).attr("url-fu");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = zhu+"?url="+$.base64.btoa(fu); //url 是你得到的连接
                // el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }
        });


        $(function (){
            if(sessionStorage.getItem("count")==null){
                //var urlgg=ctx+'/member/member_rights.html'
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
                    if(IsPC()) {
                        //会员中心、个人中心的广告弹出
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