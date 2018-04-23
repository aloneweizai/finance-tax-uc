require(["../config"], function () {
    require(["jquery.full",'jquery.base64.min',"jquery.md5","lock"], function ($) {

        $('.skillbar').each(function(){
            $(this).find('.skillbar-bar').animate({
                width:$(this).attr('data-percent')
            },3000);
        });
        $("#hide").click(function(){
            $(".advertising").hide();
        });

        $(function (){
            $("#login_layer").find("input[name='password']").eq(0).capsLockTip();
        })

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
            //加载未读消息
            $.ajax({
                url: ctx+"/message_wd.html?v="+new Date().getTime(),
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('#message_xt_ajax').html(re);
                    $.iframeHeight();

                    $(".bsMessage").one("click",function(){
                        window.location.href=ctx+'/userinfo/user_message.html?index=1&type=4';
                    });
                }
            });

            //加载订单列表
            $.ajax({
                url: ctx+"/order_index.html?v="+new Date().getTime(),
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('#order_index').html(re);
                    $.iframeHeight();
                }
            });

            //加载发票列表
            $.ajax({
                url: ctx+"/invoice_index.html?v="+new Date().getTime(),
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('#invoice_index').html(re);
                    $.iframeHeight();
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
                                        var iframes = $(".uc_iframe").attr('src', link);
                                        var iframe = document.getElementById("external-frame");
                                    } else {
                                        if("undefined" != typeof top.loc){
                                            top.loc.SwitchHref("#member");
                                            var b = $.base64.btoa(url);
                                            window.location.href = link + "?url=" + b;
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
                            layer.msg(data.message,{icon: 2});
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
                                        return;
                                    }
                                    if ("undefined" == typeof type) {
                                        var iframet = parent.document.getElementById("external-frame");
                                        var iframes = $(".uc_iframe").attr('src', link);
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
                                        if("undefined" != typeof top.loc){
                                            top.loc.SwitchHref("#member");
                                            var b = $.base64.btoa(url);
                                            window.location.href = link + "?url=" + b;
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
                            } else {
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
                            layer.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });

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
                            layer.msg("服务器繁忙请稍后再试...",{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });

        $('#tanchu').click(function (){
            $.showLogin();
        });



        $("#wdbb").click(function(){
            var link = $(this).attr("name");
            window.parent.location.href = link;
        })
        $("#bangbang>dd").click(function(){
            var bangbang=$(this).attr("name");
            $(".uc_iframe").attr('src',bangbang)
        })



//个人中心我的发票全选/取消全选
        $("#checkall").click(
            function(){
                if(this.checked){
                    $("input[name='checkname']").attr('checked', true);
                }else{

                    $("input[name='checkname']").attr('checked', false);
                }
            }
        );

        //个人中心/我的订单
        $("#orderCancel").click(function(){
            var order = $(this).attr("href");
            $(".uc_iframe").attr("src",order);
        });

//上传头像。。。做个下简易的验证  大小 格式
        $('#avatarInput').on('change', function(e) {
            var filemaxsize = 1024 * 5;//5M
            var target = $(e.target);
            var Size = target[0].files[0].size / 1024;
            if(Size > filemaxsize) {
                alert('图片过大，请重新选择!');
                $(".avatar-wrapper").childre().remove;
                return false;
            }
            if(!this.files[0].type.match(/image.*/)) {
                alert('请选择正确的图片!')
            } else {
                var filename = document.querySelector("#avatar-name");
                var texts = document.querySelector("#avatarInput").value;
                var teststr = texts; //你这里的路径写错了
                testend = teststr.match(/[^\\]+\.[^\(]+/i); //直接完整文件名的
                filename.innerHTML = testend;
            }

        });

        $(".avatar-save").on("click", function() {
            var img_lg = document.getElementById('imageHead');
            // 截图小的显示框内的内容
            html2canvas(img_lg, {
                allowTaint: true,
                taintTest: false,
                onrendered: function(canvas) {
                    canvas.id = "mycanvas";
                    //生成base64图片数据
                    var dataUrl = canvas.toDataURL("image/jpeg");
                    var newImg = document.createElement("img");
                    newImg.src = dataUrl;
                    imagesAjax(dataUrl)
                }
            });
        })

        function imagesAjax(src) {
            var data = {};
            data.img = src;
            data.jid = $('#jid').val();
            $.ajax({
                url: "upload-logo.php",
                data: data,
                type: "POST",
                dataType: 'json',
                success: function(re) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (re.status == '1') {
                            $('.user_pic img').attr('src', src);
                        }
                    }else{
                        eval(rs.js)
                    }
                }
            });
        }

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
                                    layer.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                layer.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    } ,
                    dataType: "JSON"
                });
            }
        });


        //iframe 高度
// 定义一个函数，定时调用并刷新iframe高度

        // var initHelp = function(){
        //     var arr = ['collect','fans','follow','team'];
        //     for (var i=0;i<arr.length;i++)
        //     {
        //         var js_cnt_class = "js_"+arr[i]+"_cnt";
        //         $.ajax({
        //             type: "GET",
        //             url: ctx+"/help/my/"+arr[i]+"/ajaxList.json",
        //             data: "",
        //             contentType: "application/json",
        //             dataType: "JSON",
        //             success: function (data) {
        //                 if(typeof(data.errorCode)=='undefined') {
        //                     if (typeof(data.soacode) == "undefined") {
        //                         if (data && data.code == "2000") {
        //                             $("." + js_cnt_class).text(data.total);
        //                         } else {
        //                             layer.msg(data.message,{icon: 2})
        //                         }
        //                     } else {
        //                         layer.msg(data.message,{icon: 2});
        //                     }
        //                 }else{
        //                     eval(data.js)
        //                 }
        //             }
        //         });
        //     }
        // };
        // initHelp();


        /**订单付款*/
        $(document).on("click",'a[class="dd_zhifu"]',function (){
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
                                    layer.alert("该课程已购买！",{icon: 2});
                                }
                            } else {
                                layer.msg(data.message,{icon: 2});
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
        $(document).on("click",'a[class="orderdetail"]',function (){
            var orderId=$(this).attr("detail-id");
            window.location.href=ctx+ "/orderDetail/"+orderId;
            layer.load();
        });

        /**删除订单*/
        $(document).on("click","a[class='deleteorder']",function (){
            var delete_id=$(this).attr('delete-id');
            layer.confirm('是否删除订单？',{icon:3}, function(){
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
                                    layer.msg('删除订单成功',{icon:1},function (){
                                        window.location.href =ctx + "/user_index.html";
                                    });
                                } else {
                                    layer.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                layer.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            });
        })

        /**确认收货*/
        $(document).on("click",'a[class="confirmorder"]',function (){
            var confirm_id =$(this).attr('confirm-id');
            layer.confirm('确认收货？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/userinfo/orderList/confirm/" + confirm_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    layer.msg('确认收货成功',{icon:1},function (){
                                        window.location.href = ctx+"/user_index.html";
                                    });
                                } else {
                                    layer.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                layer.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            });
        })

        //会员等级
        $('#huiyuan').click(function (){
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
        $('#jifen').click(function (){
            var iframes=$(".uc_iframe");
            //iframes.attr('src',ctx + '/shopBop/member_shopBop.html');
            iframes.attr('src',ctx + '/member/member_integral_uc.html');
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


        $('#kthy').click(function (){
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

    });
});