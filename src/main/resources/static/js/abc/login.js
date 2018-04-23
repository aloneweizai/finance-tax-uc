require(["../config"], function () {
    require(["jquery.full","jquery.md5","jquery.base64.min","lock"], function ($) {

        $(function (){
            var ie=IEVersion();
            if(ie==6||ie==7||ie==8){
                if(sessionStorage.getItem("ieEdition")==null){
                    alert("尊敬的用户您好：为保证财税专家操作体验，请使用IE9及以上版本或其他浏览器！");
                    sessionStorage.setItem("ieEdition",1);
                }
            }
        })

        $(function (){
            $("#login_user").find("input[name='password']").eq(0).capsLockTip();
        })


        function IEVersion() {
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
            var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
            var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
            if(isIE) {
                var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                reIE.test(userAgent);
                var fIEVersion = parseFloat(RegExp["$1"]);
                if(fIEVersion == 7) {
                    return 7;
                } else if(fIEVersion == 8) {
                    return 8;
                } else if(fIEVersion == 9) {
                    return 9;
                } else if(fIEVersion == 10) {
                    return 10;
                } else {
                    return 6;//IE版本<=7
                }
            } else if(isEdge) {
                return 'edge';//edge
            } else if(isIE11) {
                return 11; //IE11
            }else{
                return -1;//不是ie浏览器
            }
        }


        $(function (){
            var typelogin=0;
            $(".ewm_none").click(function(){
                $(".login_ewm_lt").show(200);
            })
            $(".login_ewm_lt").mouseleave(function(){
                $(this).hide(200);
            })

            $(".ny-480-nav").click(function(){
                $(this).children('ul').stop().slideToggle(500);
                if($(".ny-480-bg").css("display")=='none'){
                    $(this).find(".ny-480-bg").stop().show();
                }else{
                    $(this).find(".ny-480-bg").stop().hide();
                }
            })


            // $('#tongyi').click(function (){
            //     if($(this).is(':checked')){
            //         $('#zhucetijiao').attr("DISABLED",false);
            //     }else{
            //         $('#zhucetijiao').attr("DISABLED",true);
            //     }
            // });


            $(".login_duanxin").click(function(){
                typelogin=1;
                $("#login_user").hide();
                $("#login_phone").show();
            })
            $(".login_mima").click(function(){
                typelogin=0;
                $("#login_user").show();
                $("#login_phone").hide();
            })

            $(document).on('click','.login',function(){
                $(':input','.form-lo').not(':button,:submit,:reset,:hidden')  //清空表单
                    .val('')
                    .removeAttr('checked')
                    .removeAttr('checked');

                var navigatorName = "Microsoft Internet Explorer";
                var reg = /10\.0/;
                var str = navigator.userAgent;
                if(reg.test(str)){                                 //区分IE10
                    $(this).parent().addClass('ht_login_bs');
                    $(this).parent().parent().siblings().find('.ht_login_b').removeClass('ht_login_bs');
                }else if(navigator.appName === navigatorName){     //区分ie9以下浏览器
                    $(this).parent().hide();
                    $(this).parent().parent().siblings().find('.ht_login_b').show().removeClass('ht_login_bs');
                }else{                                             //其他浏览器
                    $(this).parent().addClass('ht_login_bs');
                    $(this).parent().parent().siblings().find('.ht_login_b').removeClass('ht_login_bs');
                }
                $('#user_zhuce').validator('destroy');
                $('#user_zhuce').validator(phoneyzs);
                $('.ht_login_b').css("display","block");
                $('.login_denglu').show();
            });

            var phoneyz={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    password:function (element){
                        var v2=element.value;
                        var v1=$('#pass').val();
                        if(v1==v2){
                            return true;
                        }else{
                            return "两次密码不一致";
                        }
                    },
                    mimaqd:function (element){
                        var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
                        var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
                        if (strongRegex.test(element.value)) {
                            return {"ok":"强"};
                        }
                        else if (mediumRegex.test(element.value)) {
                            return {"ok":"中"};
                        }
                        else {
                            return {"ok":"弱"};
                        }
                    },
                    mimayz:function (element){
                        var mima=/((?=.*[a-zA-Z])(?=.*\d)|(?=[a-zA-Z])(?=.*[_])|(?=.*\d)(?=.*[_]))[a-zA-Z\d_]{8,16}/;
                        if (!mima.test(element.value)) {
                            return "密码必须由8-16位数字、字母、下划线中至少两种组成";
                        }else{
                            return true;
                        }
                    },
                    myRemote: function(element){
                        return $.ajax({
                            url: ctx+'/forgot_phone_new_fangxiang.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/tp_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "username": {
                        rule:"手机号码:required;phone;myRemote;"
                    },
                    "password":{
                        rule:"密码:required;length(8~16),mimaqd;mimayz"
                    },
                    "quepassword":{
                        rule:"确认密码:required;password;mimaqd"
                    },
                    "tpcode":{
                        rule:"验证码:required;myRemote1;"
                    }
                }
            };


            var phoneyzs={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    password:function (element){
                        var v2=element.value;
                        var v1=$('#pass').val();
                        if(v1==v2){
                            return true;
                        }else{
                            return "两次密码不一致";
                        }
                    },
                    mimaqd:function (element){
                        var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
                        var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
                        if (strongRegex.test(element.value)) {
                            return {"ok":"强"};
                        }
                        else if (mediumRegex.test(element.value)) {
                            return {"ok":"中"};
                        }
                        else {
                            return {"ok":"弱"};
                        }
                    },
                    mimayz:function (element){
                        var mima=/((?=.*[a-zA-Z])(?=.*\d)|(?=[a-zA-Z])(?=.*[_])|(?=.*\d)(?=.*[_]))[a-zA-Z\d_]{8,16}/;
                        if (!mima.test(element.value)) {
                            return "密码必须由8-16位数字、字母、下划线中至少两种组成";
                        }else{
                            return true;
                        }
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/forgot_phone_new_fangxiang.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "username": {
                        rule:"手机号码:required;phone;"
                    },
                    "password":{
                        rule:"密码:required;length(8~16);mimaqd;mimayz"
                    },
                    "quepassword":{
                        rule:"确认密码:required;password;mimaqd"
                    }
                }
            };

            $('#user_zhuce').validator(phoneyzs);


            $('#yzm').click(function (){
                $('#user_zhuce').validator('destroy');
                var $form1=$('#user_zhuce').validator(phoneyzs);
                var zcphone=$('#zcphone').val();
                var type=$(this).attr("type-name");
                $form1.isValid(function (v) {
                    if (v) {
                        var imgSrc = $("#yzmimg");
                        if(imgSrc.attr("src")==''){
                            var timestamp = (new Date()).valueOf();
                            var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                            imgSrc.attr("src", src);
                            $('#tpyzm').val();
                        }else{
                            $('#user_zhuce').validator('destroy');
                            $form1=$('#user_zhuce').validator(phoneyz);
                            $form1.isValid(function (v){
                                if(v){
                                    settime();
                                    $.ajax({
                                        type:'POST',
                                        url: ctx+"/sms.html" ,
                                        data:{phone:zcphone,type:type},
                                        success: function (data){
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.result.code == '2000') {
                                                        abc.msg("发送成功，有效期5分钟!",{icon: 1})
                                                    } else {
                                                        abc.msg( data.result.message,{icon:2});
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
                                }else{
                                    var timestamp = (new Date()).valueOf();
                                    var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                                    imgSrc.attr("src", src);
                                    $('#tpyzm').val();
                                }
                            });
                        }
                    }
                });

            });


            var phoneyzspy={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/phone_forgot_login_phone.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "phone": {
                        rule:"手机号码:required;phone;myRemote2;"
                    }
                }
            };



            $('#tpyzms').click(function (){
                $('#phone_login').validator('destroy');
                var $form1=$('#phone_login').validator(phoneyzspy);
                $form1.isValid(function (v) {
                    if (v) {
                        var imgSrc = $("#yzmimgs");
                        if(imgSrc.attr("src")==''){
                            var timestamp = (new Date()).valueOf();
                            var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                            imgSrc.attr("src", src);
                            $('#tpyzms').val();
                        }
                    }
                });
            });

            $('#tpyzm').click(function (){
                $('#user_zhuce').validator('destroy');
                var $form1=$('#user_zhuce').validator(phoneyzs);
                $form1.isValid(function (v) {
                    if (v) {
                        var imgSrc = $("#yzmimg");
                        if(imgSrc.attr("src")==''){
                            var timestamp = (new Date()).valueOf();
                            var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                            imgSrc.attr("src", src);
                            $('#tpyzm').val();
                        }
                    }
                });
            });

            $('#yzmimg').click(function (){
                var imgSrc = $("#yzmimg");
                var timestamp = (new Date()).valueOf();
                var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                imgSrc.attr("src", src);
                $('#tpyzm').val();
            });

            $('#yzmimgs').click(function (){
                var imgSrc = $("#yzmimgs");
                var timestamp = (new Date()).valueOf();
                var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                imgSrc.attr("src", src);
                $('#tpyzms').val();
            });


            var countdown=90;
            function settime() { //发送验证码倒计时
                var yzm=$('#yzm');
                if (countdown == 0) {
                    yzm.attr('disabled', false);
                    //obj.removeattr("disabled");
                    yzm.val("发送验证码");
                    countdown = 90;
                    return;
                } else {
                    yzm.attr('disabled', true);
                    yzm.val("重新发送(" + countdown + ")");
                    countdown--;
                }
                setTimeout(function() {
                        settime() }
                    ,1000)
            }

            var formyz={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    password:function (element){
                        var v2=element.value;
                        var v1=$('#pass').val();
                        if(v1==v2){
                            return true;
                        }else{
                            return "两次密码不一致";
                        }
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/phone_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    mimayz:function (element){
                        var mima=/((?=.*[a-zA-Z])(?=.*\d)|(?=[a-zA-Z])(?=.*[_])|(?=.*\d)(?=.*[_]))[a-zA-Z\d_]{8,16}/;
                        if (!mima.test(element.value)) {
                            return "密码必须由8-16位数字、字母、下划线中至少两种组成";
                        }else{
                            return true;
                        }
                    },
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/tp_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "username": {
                        rule:"手机号码:required;phone;myRemote1;"
                    },
                    "password":{
                        rule:"密码:required;length(8~);mimayz"
                    },
                    "quepassword":{
                        rule:"确认密码:required;password;"
                    },
                    "tpcode":{
                        rule:"required;myRemote2;"
                    },
                    "code":{
                        rule:"required;"
                    }
                }
            };


            $('#zhucetijiao').click(function (){

                if(!$("#tongyia").prop('checked')){
                    abc.msg("请同意用户协议条款!",{icon: 2});
                    return ;
                }
                $('#user_zhuce').validator('destroy');
                var $form1=$('#user_zhuce').validator(phoneyzs);
                $form1.isValid(function (v) {
                    if (v) {
                        var imgSrc = $("#yzmimg");
                        if (imgSrc.attr("src") == '') {
                            var timestamp = (new Date()).valueOf();
                            var src = ctx+"/validatecode.html?timestamp=" + timestamp;
                            imgSrc.attr("src", src);
                            $('#tpyzm').val();
                        } else {
                            $('#user_zhuce').validator('destroy');
                            var $form2 = $("#user_zhuce").validator(formyz);
                            $form2.isValid(function (v){
                                if(v){
                                    var zcphone=$('#zcphone').val();
                                    var zhuce_code=$('#zhuce_code').val();
                                    var pass=$('#pass').val();
                                    var tpyzm=$('#tpyzm').val();
                                    $.pop();
                                    $.ajax({
                                        type:'POST',
                                        url: ctx+"/zhuce.html" ,
                                        data:{username:zcphone,code:zhuce_code,password:$.md5(pass),tpcode:tpyzm},
                                        success: function (data){
                                            $.close();
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.result.code == '2000') {
                                                        abc.msg("注册成功!",{icon: 1},function (){
                                                            window.location.reload();
                                                        });
                                                    } else {
                                                        abc.msg(data.result.message,{icon: 2});
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
                                }else{
                                    var imgSrc = $("#yzmimg");
                                    var timestamp = (new Date()).valueOf();
                                    var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
                                    imgSrc.attr("src", src);
                                    $('#tpyzm').val();
                                }
                            });
                        }
                    }
                });
            });

            var formusername={
                theme: 'simple_right',
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

            $('#userlogin').click(function (){
                $('#usernamelogin').validator('destroy');
                var $form2 = $("#usernamelogin").validator(formusername);
                if($form2.isValid()){
                    $.pop();
                    var data=$("#usernamelogin").serializeJson();
                    var da=JSON.parse(data);
                    da.password=$.md5(da.password);
                    data=JSON.stringify(da);
                    $.ajax({
                        type:'POST',
                        url: ctx+"/login_v2.html",
                        data:data,
                        contentType:"application/json",
                        success: function (data){
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.data.code == '2000') {
                                        if (data.redirect != null && data.redirect != "") {
                                            top.location = "http://" + data.redirect;
                                        } else {
                                            window.location.replace(ctx + "/index.html");
                                        }
                                    } else {
                                        $.close();
                                        abc.msg(data.data.message,{icon: 2});
                                    }
                                } else {
                                    $.close();
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else {
                                $.close();
                                eval(data.js)
                            }
                        } ,
                        dataType: "JSON"
                    });
                }
            });


            var countdown1=90;
            function settime1() { //发送验证码倒计时
                var yzm=$('#phoneyzm');
                if (countdown1 == 0) {
                    yzm.attr('disabled', false);
                    //obj.removeattr("disabled");
                    yzm.val("发送验证码");
                    countdown1 = 90;
                    return;
                } else {
                    yzm.attr('disabled', true);
                    yzm.val("重新发送(" + countdown1 + ")");
                    countdown1--;
                }
                setTimeout(function() {
                        settime1() }
                    ,1000)
            }

            var phoneyzsp={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/phone_forgot_login_phone.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/tp_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "phone": {
                        rule:"手机号码:required;phone;myRemote2;"
                    },
                    "tpcode":{
                        rule:"验证码:required;myRemote1;"
                    }
                }
            };



            $('#phoneyzm').click(function (){
                $('#phone_login').validator('destroy');
                var $form1=$('#phone_login').validator(phoneyzsp);
                var zcphone=$('#login_phones').val();
                var type=$(this).attr("type-name");
                $form1.isValid(function (v) {
                    if (v) {
                        var imgSrc = $("#yzmimgs");
                        if (imgSrc.attr("src") == '') {
                            var timestamp = (new Date()).valueOf();
                            var src = ctx+"/validatecode.html?timestamp=" + timestamp;
                            imgSrc.attr("src", src);
                            $('#tpyzms').val();
                        } else {
                            $('#phone_login').validator('destroy');
                            var $form1 = $("#phone_login").validator(phoneyzsp);
                            $form1.isValid(function (v) {
                                $.ajax({
                                    type: 'POST',
                                    url: ctx + "/phonelongsms.html",
                                    data: {phone: zcphone, type: type},
                                    success: function (data) {
                                        if (typeof(data.errorCode) == 'undefined') {
                                            if (typeof(data.soacode) == "undefined") {
                                                if (data.result.code == '2000') {
                                                    abc.msg("发送成功，有效期5分钟!",{icon: 1},function (){
                                                        settime1();
                                                    })
                                                } else {
                                                    abc.msg(data.result.message,{icon: 2});
                                                }
                                            } else {
                                                abc.msg("服务器繁忙请稍后再试...",{icon: 2});
                                            }
                                        } else {
                                            eval(data.js)
                                        }
                                    },
                                    dataType: "JSON"
                                });
                            })
                        }
                    }
                });
            });


            var formusernames={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/phone_forgot_login_phone.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "phone": {
                        rule:"手机号码:required;phone;myRemote2"
                    },
                    "code":{
                        rule:"验证码:required;"
                    }
                }
            };


            $('#phonelogin').click(function (){
                $('#phone_login').validator('destroy');
                var $form2 = $("#phone_login").validator(formusernames);
                $form2.isValid(function (v) {
                    if (v) {
                        $.pop();
                        var data=$("#phone_login").serializeJson();
                        $.ajax({
                            type:'POST',
                            url: ctx+"/login_v2.html" ,
                            data:data,
                            contentType:"application/json",
                            success: function (data){
                                if(typeof(data.errorCode)=='undefined') {
                                    if (typeof(data.soacode) == "undefined") {
                                        if (data.data.code == '2000') {
                                            window.location.replace(ctx + "/index.html");
                                        } else {
                                            $.close();
                                            abc.msg(data.data.message,{icon: 2});
                                        }
                                    } else {
                                        $.close();
                                        abc.msg(data.message,{icon: 2});
                                    }
                                }else{
                                    $.close();
                                    eval(data.js)
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                })
            });

            $(document).keyup(function(event){
                console.log(event.keyCode)
                if(event.keyCode ==13){
                    if(typelogin==0){
                        $('#usernamelogin').validator('destroy');
                        var $form2 = $("#usernamelogin").validator(formusername);
                        if($form2.isValid()){
                            $.pop();
                            var data=$("#usernamelogin").serializeJson();
                            var da=JSON.parse(data);
                            da.password=$.md5(da.password);
                            data=JSON.stringify(da);
                            $.ajax({
                                type:'POST',
                                url: ctx+"/login_v2.html" ,
                                data:data,
                                contentType:"application/json",
                                success: function (data){
                                    if(typeof(data.errorCode)=='undefined') {
                                        if (typeof(data.soacode) == "undefined") {
                                            if (data.data.code == '2000') {
                                                if (data.redirect != null && data.redirect != "") {
                                                    top.location = "http://" + data.redirect;
                                                } else {
                                                    window.location.replace(ctx + "/index.html");
                                                }
                                            } else {
                                                $.close();
                                                abc.msg(data.data.message,{icon: 2});
                                            }
                                        } else {
                                            $.close();
                                            abc.msg(data.message,{icon: 2});
                                        }
                                    }else{
                                        $.close();
                                        eval(data.js)
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    }else{
                        $('#phone_login').validator('destroy');
                        var $form2 = $("#phone_login").validator(formusernames);
                        $form2.isValid(function (v) {
                            if (v) {
                                $.pop();
                                var data=$("#phone_login").serializeJson();
                                $.ajax({
                                    type:'POST',
                                    url: ctx+"/login_v2.html" ,
                                    data:data,
                                    contentType:"application/json",
                                    success: function (data){
                                        if(typeof(data.errorCode)=='undefined') {
                                            if (typeof(data.soacode) == "undefined") {
                                                if (data.data.code == '2000') {
                                                    window.location.replace(ctx + "/index.html");
                                                } else {
                                                    $.close();
                                                    abc.msg(data.data.message,{icon: 2});
                                                }
                                            } else {
                                                $.close();
                                                abc.msg(data.message,{icon: 2});
                                            }
                                        }else{
                                            $.close();
                                            eval(data.js)
                                        }
                                    } ,
                                    dataType: "JSON"
                                });
                            }
                        })
                    }
                }
            });

            //跳转到注册页面
            if(window.location.hash=='#zhuce'){
                $("#user_denglu").find('.login_zhuce').trigger("click");
            }

        })
    });
});