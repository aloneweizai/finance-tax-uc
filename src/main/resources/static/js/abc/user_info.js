require(["../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","jquery.md5","jquery.jedate","jquery.base64.min","../abc/util/tools"], function ($) {
        $(function (){

            $("#shim").click(function (){
                $("#smrzClickDiv").trigger("click");
            });

            $('#bdphone').click(function (){
                $("#bangdingphones").trigger("click");
            });

            $('#bdphone1').click(function (){
                $("#bangdingphones").trigger("click");
            });

            $.jeDate("#begintime",{
                format:"YYYY-MM-DD",
                isTime:false
            })

            $("#workYear").jeDate({
                isinitVal:true,
                format:"YYYY"
            });

            $(function (){
                if(document.getElementById("openid")==null||document.getElementById("openid").value==''){
                    $.ajax({
                        type: "GET",
                        url: ctx+"/weixin/weixin.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            $('#weixin_ewm').attr("src","data:image/jpg;base64,"+data.data);
                            longPolling();
                        }
                    })

                    function longPolling() {
                        // $.ajax({
                        //     url: ctx+"/weixin/wx.html",
                        //     data: {"timed": new Date().getTime()},
                        //     dataType: "text",
                        //     timeout: 5000,
                        //     error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //         if (textStatus == "timeout") { // 请求超时
                        //             setTimeout(function () {
                        //                     timeoutorder(orderid)
                        //                 }
                        //                 , 5000); // 递归调用
                        //         } else {
                        //             setTimeout(function () {
                        //                     longPolling()
                        //                 }
                        //                 , 5000);
                        //         }
                        //     },
                        //     success: function (data, textStatus) {
                        //         if (textStatus == "success") { // 请求成功
                        //             var obj = eval('(' + data + ')');
                        //             if(obj.data=="1"){
                        //                 layer.msg("绑定成功!",function (){
                        //                     window.parent.location.href=ctx+"/index.html";
                        //                 })
                        //             }else{
                        //                 setTimeout(function () {
                        //                         longPolling()
                        //                     }
                        //                     , 5000);
                        //             }
                        //         }
                        //     }
                        // });
                    }
                }
            })



            $('a[wxjb="jb"]').click(function (){
                abc.confirm('是否解除微信绑定？',{icon:3}, function(){
                    $.ajax({
                        type: "get",
                        url: ctx+"/userinfo/wxjb.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.data.code == '2000') {
                                        abc.msg('微信解绑成功',{icon:1},function  (){
                                            if(typeof window.top.loc!="undefined"){
                                                window.parent.location.href = ctx+"/external_index.html";
                                            }else{
                                                window.parent.location.href = ctx+"/index.html";
                                            }
                                        });
                                    } else {
                                        abc.msg(data.data.message,{icon: 2});
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
            });




            $(function (){
                var url=window.location.href;
                var urls=url.split("#");
                if(urls.length>1){
                    var indexs=urls[1].split("_");
                    $(".grzx_main_rt_nav>ul>li").eq(parseInt(indexs[0])).addClass('hover').siblings('.hover').removeClass('hover');
                    $(".page_main_xxk").eq(parseInt(indexs[0])).show().siblings(".page_main_xxk").hide();
                    $(".bangdingnr").eq(parseInt(indexs[1])).show().siblings(".bangdingnr").hide();
                    $.iframeHeight();
                }
            });


            $(".grzx_main_rt_nav>ul>li").click(function(){
                $(this).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
                $(".bangdingnr").eq(0).show().siblings(".bangdingnr").hide();
            })

            $(".anquan_click").click(function(){
                $(".bangdingnr").hide();
                $(this).parent().next().show(function(){
                    $.iframeHeight();
                });
            })




            $("#phonebangdingnr").click(function(){
                $(".grzx_main_rt_nav>ul>li").eq(1).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq(1).show().siblings(".page_main_xxk").hide();
                $(".bangdingnr").eq(0).show().siblings(".bangdingnr").hide();
            })

            $("#phonebangdingup").click(function(){
                $(".grzx_main_rt_nav>ul>li").eq(1).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq(1).show().siblings(".page_main_xxk").hide();
                $(".bangdingnr").eq(0).show().siblings(".bangdingnr").hide();
            })

            $("#weixinbangdingnr").click(function(){
                $(".grzx_main_rt_nav>ul>li").eq(1).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq(1).show().siblings(".page_main_xxk").hide();
                $(".bangdingnr").eq(3).show().siblings(".bangdingnr").hide();
            })

            $("#updatePassword").click(function(){
                $(".grzx_main_rt_nav>ul>li").eq(1).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq(1).show().siblings(".page_main_xxk").hide();
                $(".bangdingnr").eq(2).show().siblings(".bangdingnr").hide();
            })

            $('#xiangqin').click(function (){
                window.location.href=ctx + "/userinfo/enterprise.html";
            });

            if(window.location.hash=='#ghbdsj'){
                $('#aqszTag').trigger("click");
            }else if(window.location.hash=='#smrz'){
                $('#aqszTag').trigger("click");
                $("#smrzClickDiv").trigger("click");
            }

            $(function (){
                var s_province=$("#s_province").attr("data-id");
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/province/list.html",
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#s_province").append("<option value='" + item.provinceId + "'>" + item.province + "</option>");
                                    });
                                    $('#s_province').val(s_province)
                                } else {
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

            $('#s_province').change(function (){
                var pid=$(this).val();
                if(pid!=''){
                    $.ajax({
                        type: "GET",
                        url: ctx+"/user/region/city/list.html?pid="+pid,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $("#s_county").html("<option value=''>请选择</option>");
                                    $("#s_city").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#s_city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                        });
                                    }
                                } else {
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    });
                }else{
                    $("#s_city").html("<option value=''>请选择</option>");
                    $("#s_county").html("<option value=''>请选择</option>");
                }
            });

            $('#s_city').change(function (){
                var pid=$(this).val();
                if(pid!=''){
                    $.ajax({
                        type: "GET",
                        url: ctx+"/user/region/county/list.html?pid="+pid,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $("#s_county").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#s_county").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                        });
                                    } else {
                                    }
                                } else {
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    });
                }else{
                    $("#s_county").html("<option value=''>请选择</option>");
                }
            });

            var $validatorWsysVoFrom ={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/phone_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot_.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "querenphone":{
                        rule:"确认手机号:required;phone;myRemote2;"
                    },
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote1;"
                    },
                    "code":{
                        rule:"验证码:required;"
                    }
                }
            };
              //  $("#updPhoneFrom").validator();

            var $validatorWsysVoFrom1 = {
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/phone_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot_.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "querenphone":{
                        rule:"确认手机号:required;phone;myRemote2;"
                    },
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote1;"
                    }
                }
            };

            $('#updpass').click(function (){
                $('#updPhoneFrom').validator('destroy');
                var $form2 = $("#updPhoneFrom").validator($validatorWsysVoFrom);
                $form2.isValid(function (v) {
                    if (v) {
                        var data=$('#updPhoneFrom').serializeJson();
                        parent.$.pop();
                        $.ajax({
                            type: "POST",
                            url: ctx+"/userinfo/updpassword.html",
                            data:data,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                parent.$.close();
                                if(typeof(data.errorCode)=='undefined') {
                                    if (typeof(data.soacode) == "undefined") {
                                        if (data.data.code == '2000') {
                                            abc.msg('修改手机号码成功',{icon:1},function (){
                                                window.location.href = ctx+"/userinfo/userinfolist.html";
                                            });
                                        } else {
                                            abc.msg(data.data.message,{icon:2});
                                        }
                                    } else {
                                        abc.msg(data.message,{icon:2});
                                    }
                                }else{
                                    eval(data.js)
                                }
                            }
                        });
                    }
                })
            });

            var v1={
                theme: 'simple_right',
                stopOnError: true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    sfz:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,"身份证不正确"],
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "zsxm": {
                        rule: "真实姓名:required;length(2~6)"
                    },
                    "sfzhm": {
                        rule: "身份证号码:required;sfz;"
                    },
                    "phone":{
                        rule:"手机号:required;phone;myRemote1;"
                    },
                    "code":{
                        rule: "验证码:required;"
                    }
                }
            };
            var v2={
                theme: 'simple_right',
                stopOnError: true,
                timely: 1,
                rules: {
                    sfz:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,"身份证不正确"]
                },
                fields: {
                    "zsxm": {
                        rule: "真实姓名:required;length(2~6)"
                    },
                    "sfzhm": {
                        rule: "身份证号码:required;sfz;"
                    },
                    "code":{
                        rule: "验证码:required;"
                    }
                }
            };

            var v22={
                theme: 'simple_right',
                stopOnError: true,
                timely: 1,
                rules: {
                    sfz:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,"身份证不正确"]
                },
                fields: {
                    "zsxm": {
                        rule: "真实姓名:required;length(2~6)"
                    },
                    "sfzhm": {
                        rule: "身份证号码:required;sfz;"
                    }
                }
            };




            $('#smrztijiao').click(function (){
                var zm=$('#zm').val();
                var fm=$('#fm').val();
                if(zm==''){
                    abc.msg('请选择身份证正反面图片上传',{icon:2});
                    return ;
                }
                if(fm==''){
                    abc.msg('请选择身份证正反面图片上传',{icon:2});
                    return ;
                }
                $('#smrz').validator('destroy');
                var $form2 ;
                if($('#disabledInputt').val().indexOf()>-1){
                    $form2 = $("#smrz").validator(v2);
                }else{
                    $form2 = $("#smrz").validator(v1);
                }
                var datas=$('#smrz').serializeJson();
                $form2.isValid(function (v) {
                    if (v) {
                        parent.$.pop();
                        $.ajax({
                            type: "POST",
                            url: ctx+"/userinfo/upload.html?path=sfz",
                            data:datas,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                parent.$.close();
                                if(typeof(data.errorCode)=='undefined') {
                                    if (typeof(data.soacode) == "undefined") {
                                        if (data.data.code == '2000') {
                                            abc.msg('实名认证上传成功，请耐心等待审核',{icon:1},function (){
                                                window.location.href = ctx+"/userinfo/userinfolist.html";
                                            });
                                        } else {
                                            abc.msg(data.data.message,{icon:2});
                                        }
                                    } else {
                                        abc.msg(data.message,{icon:2});
                                    }
                                }else{
                                    eval(res.js)
                                }
                            }

                        });
                    }
                });

            });

            $(function (){
                var status=$('#savesmrz').attr("data-status");
                if(status!=''&&status=='1'){
                    $('#drz').show();
                    $('#savesmrz').hide();
                }else if(status!=''&&status=='2'){
                    $('#drz').show();
                    $('#savesmrz').hide();
                }else if(status!=''&&status=='3'){
                    $('#drz').show();
                    $('#savesmrz').hide();
                }
            });

            $('#cxrz').click(function (){
                $('#drz').hide();
                $('#savesmrz').show();
                $.iframeHeight();
            });

            var $updform1 = {
                theme: 'simple_right',
                stopOnError: true,
                timely: 1,
                rules: {
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
                    passwords:function (element){
                        var v2=element.value;
                        var v1=$('#xpass').val();
                        if(v1==v2){
                            return true;
                        }else{
                            return "两次密码不一致";
                        }
                    }
                },
                fields: {
                    "password": {
                        rule: "新密码:required;mimaqd;mimayz"
                    },
                    "qpass": {
                        rule: "确认密码:required;passwords;"
                    }
                }
            }

            var $updform = {
                theme: 'simple_right',
                stopOnError: true,
                timely: 1,
                rules: {
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
                    passwords:function (element){
                        var v2=element.value;
                        var v1=$('#xpass').val();
                        if(v1==v2){
                            return true;
                        }else{
                            return "两次密码不一致";
                        }
                    }
                },
                fields: {
                    "passcode": {
                        rule: "验证码:required"
                    },
                    "password": {
                        rule: "新密码:required;mimaqd;mimayz"
                    },
                    "qpass": {
                        rule: "确认密码:required;passwords;"
                    }
                }
            };
                //$("#updform").validator();
            $('#updpassword').click(function (){
                var disabledInputp=$('#disabledInputp').val();
                if(disabledInputp==''){
                    abc.msg('请先绑定手机才能帮您修改密码',{icon:2});
                    return ;
                }
                $('#updform').validator('destroy');
                var $form2 = $("#updform").validator($updform);
                if($form2.isValid()){
                    var data=$('#updform').serializeJson();
                    var jsons=$.parseJSON( data );
                    jsons.password=$.md5(jsons.password);
                    data=JSON.stringify(jsons);
                    parent.$.pop();
                    $.ajax({
                        type: "POST",
                        url: ctx+"/userinfo/updpass_v2.html",
                        data:data,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            parent.$.close();
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.data.code == '2000') {
                                        if(typeof window.top.loc !=='undefined'){
                                            window.top.loc.GoHome();
                                        }else{
                                            abc.msg('修改密码成功,请重新登录！',{icon:1},function (){
                                                $.ajax({
                                                    url: ctx+"/logout.html",
                                                    type: "POST",
                                                    dataType: 'json',
                                                    success: function(re) {
                                                        window.parent.location.href=re.cswurl+'/index.html'
                                                    }
                                                });
                                            });
                                        }
                                        //if("undefined" != typeof loc){
                                        //    $.ajax({
                                        //        type: "POST",
                                        //        url: ctx+"/logout.html",
                                        //        data: data,
                                        //        contentType: "application/json",
                                        //        dataType: "JSON",
                                        //        success: function (data) {
                                        //            window.loc.Logout();
                                        //        }
                                        //    })
                                        //}else{
                                        //    setTimeout(function () {
                                        //        window.parent.location.href = ctx + "/login";
                                        //    }, 1000)
                                        //}
                                    } else {
                                        abc.msg(data.data.message,{icon:2});
                                    }
                                } else {
                                    abc.msg(data.message,{icon:2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }

                    });
                }
            });


            $(function (){
                var s_province=$("#s_province").attr("data-id");
                var s_city=$("#s_city").attr("data-id");
                var s_county=$("#s_county").attr("data-id");
                if(s_province!=''&&typeof(s_province)!="undefined"){
                    $("#s_province").val(s_province);
                    $.ajax({
                        type: "GET",
                        url: ctx+"/user/region/city/list.html?pid="+s_province,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $("#s_city").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#s_city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                        });
                                        if (s_city != ''&&typeof(s_city)!="undefined") {
                                            $("#s_city").val(s_city);
                                            $.ajax({
                                                type: "GET",
                                                url: ctx+"/user/region/county/list.html?pid=" + s_city,
                                                contentType: "application/json",
                                                dataType: "JSON",
                                                success: function (data) {
                                                    if(typeof(data.errorCode)=='undefined') {
                                                        if (typeof(data.soacode) == "undefined") {
                                                            $("#s_county").html("<option value=''>请选择</option>");
                                                            if (data.list) {
                                                                $.each(data.list, function (i, item) {
                                                                    $("#s_county").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                                                });
                                                                $("#s_county").val(s_county);
                                                            } else {
                                                            }
                                                        } else {
                                                            abc.msg(data.message,{icon:2});
                                                        }
                                                    }else{
                                                        eval(data.js)
                                                    }
                                                }
                                            });
                                        }
                                    }
                                } else {
                                    abc.msg(data.message,{icon:2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    });
                }


            });



            var formyzt={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote1;"
                    }
                }
            };


            var formyzt2={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/phone_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "querenphone":{
                        rule:"确认手机号:required;phone;myRemote1;"
                    },
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote2;"
                    }
                }
            };

            $("#updform").validator($updform1);

            var values="手机更改,手机绑定";

            $('input[sms-id="yzm"]').click(function (){
                var verification=$(this).attr("sms-verification");
                var id=$(this).attr("sms-phone-id");
                var zcphone=$('#'+id).val();
                if(typeof(verification)=="undefined"){
                    var op=$(this);
                    var type=$(this).attr("type-name");
                    var url='/xinsms.html';
                    if(values.indexOf(type)!=-1){
                        url="/sms.html";
                    }
                    settimes2(op);
                    $.ajax({
                        type:'POST',
                        url: ctx+url ,
                        data:{phone:zcphone,type:type},
                        success: function (data){
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if(data.result.code=='2000'){
                                        abc.msg("发送成功，有效期5分钟!",{icon:1})
                                    }else{
                                        abc.layerAlert(false,data.result.message);
                                    }
                                } else {
                                    abc.msg(data.message,{icon:2});
                                }
                            }else{
                                eval(data.js)
                            }
                        } ,
                        dataType: "JSON"
                    });
                }else{
                    $('#'+verification).validator('destroy');
                    var sms_yz=$(this).attr('sms-yz');
                    var $form2 ;
                    if("updform"==verification){
                        $form2 = $("#"+verification).validator($updform1);
                    }else if("updPhoneFrom"==verification){
                        $form2 = $("#"+verification).validator($validatorWsysVoFrom1);
                    }else if("xinphonebangding"==verification){
                        $form2 = $("#xinphonebangding").validator(formyzs1);
                    }else if("smrz"==verification){
                        var zm=$('#zm').val();
                        var fm=$('#fm').val();
                        if(zm==''){
                            abc.msg('请选择身份证正反面图片上传',{icon:2});
                            return ;
                        }
                        if(fm==''){
                            abc.msg('请选择身份证正反面图片上传',{icon:2});
                            return ;
                        }
                        $form2 = $("#"+verification).validator(v22);
                    }

                    var op=$(this);
                    var type=$(this).attr("type-name");
                    var url='/xinsms.html';
                    if(values.indexOf(type)!=-1){
                        url="/sms.html";
                    }
                    $form2.isValid(function (v) {
                        if (v) {
                            settimes2(op);
                            $.ajax({
                                type:'POST',
                                url: ctx+url ,
                                data:{phone:zcphone,type:type},
                                success: function (data){
                                    if(typeof(data.errorCode)=='undefined') {
                                        if (typeof(data.soacode) == "undefined") {
                                            if(data.result.code=='2000'){
                                                abc.msg("发送成功，有效期5分钟!",{icon:1})
                                            }else{
                                                abc.msg(data.result.message,{icon:2});
                                            }
                                        } else {
                                            abc.msg(data.message,{icon:2});
                                        }
                                    }else{
                                        eval(data.js)
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    })
                }

            });

            var countdowns2=90;
            function settimes2(op) { //发送验证码倒计时
                var yzm=$(op);
                if (countdowns2 == 0) {
                    yzm.attr('disabled', false);
                    //obj.removeattr("disabled");
                    yzm.val("发送验证码");
                    countdowns2 = 90;
                    return;
                } else {
                    yzm.attr('disabled', true);
                    yzm.val("重新发送(" + countdowns2 + ")");
                    countdowns2--;
                }
                setTimeout(function() {
                        settimes2(op) }
                    ,1000)
            }


            var formyzs={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot_.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote2;"
                    },
                    "code":{
                        rule:"验证码:required;"
                    }
                }
            };

            var formyzs1={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    phone:[/^1[0-9]{10}$/,"手机号不正确"],
                    myRemote2: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/user_forgot_.html',
                            type: 'post',
                            data: element.name +'='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    }
                },
                fields: {
                    "xphone": {
                        rule:"手机号码:required;phone;myRemote2;"
                    }
                }
            };

            $('#bangdingphone').click(function (){
                $('#xinphonebangding').validator('destroy');
                var $form2 = $("#xinphonebangding").validator(formyzs);
                $form2.isValid(function (v){
                    if(v){
                        var data=$('#xinphonebangding').serializeJson();
                        parent.$.pop();
                        $.ajax({
                            type: "POST",
                            url: ctx+"/userinfo/updpassword.html",
                            data:data,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                parent.$.close();
                                if(typeof(data.errorCode)=='undefined') {
                                    if (typeof(data.soacode) == "undefined") {
                                        if(data.data.code=='2000'){
                                            abc.msg('修改手机号码成功',{icon:1},function (){
                                                window.location.href=ctx+"/userinfo/userinfolist.html";
                                            });
                                        }else{
                                            abc.msg(data.data.message,{icon:2});
                                        }
                                    } else {
                                        abc.msg(data.message,{icon:2});
                                    }
                                }else{
                                    eval(data.js)
                                }
                            }
                        });
                    }
                });
            });

            var userinfo={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    year:[/^\d{4}$/,"从业年份"],
                    yearboolean:function (element){
                        var myDate = new Date();
                        var year=myDate.getFullYear();
                        if(parseInt(element.value)>year){
                            return {"error":"从业年份不能超过当前年份!"};
                        }else{
                            return true;
                        }
                    },

                    industryyear:function(element){
                        var birthYear = new Date($("#begintime").val()).getFullYear();
                        var workYear = parseInt(element.value) ;
                        if(workYear -birthYear < 18){
                            return {"error":"从业时间必须要满18周岁"};
                        }else{
                            return true;
                        }
                    },

                    csrq:function (element){
                        var myDate = new Date();
                        var year=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
                        var yue=myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
                        var ri=myDate.getDate(); //获取当前日(1-31)
                        var timestamp2 = Date.parse(new Date(element.value));
                        var timestamp1 = Date.parse(new Date((year-18)+"-"+yue+"-"+ri));
                        if(timestamp1<timestamp2){
                            return {"error":"出生日期必须满18周岁"}
                        }else{
                            return true;
                        }
                    },
                    qq:[/^\d{5,18}$/, "QQ号格式不正确"],
                    email:[/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/ , "邮箱格式不正确"],
                    nicknamelength:function(element){
                        var str=element.value;
                        var len=str.replace(/[\u0391-\uFFE5]/g,"**").length;
                        if(len>10){
                            return {"error":"昵称必须2到5个汉字或者4到10个字符"};
                        }else if(len<4){
                            return {"error":"昵称必须2到5个汉字或者4到10个字符"};
                        }else{
                            return true;
                        }
                    }
                },
                fields: {
                    "nickname": {
                        rule:"昵称:required;nicknamelength;"
                    },
                    "sex":{
                        rule:"性别:required;"
                    },
                    "birthday":{
                        rule:"出生日期:required;csrq"
                    },
                    "industry":{
                        rule:"所属行业:required"
                    },
                    "careerDuration":{
                        rule:"从业时长:required;year;yearboolean,industryyear"
                    },
                    "education":{
                        rule:"学历:required;"
                    },
                    "qq":{
                        rule:"QQ:required;qq"
                    },
                    "email":{
                        rule:"eMail:required;email"
                    },
                    "province":{
                        rule:"省份:required;"
                    },
                    "city":{
                        rule:"市:required;"
                    },
                    "area":{
                        rule:"区县:required;"
                    },
                    "postAddress":{
                        rule:"详细地址:required;"
                    },
                    "tags":{
                        rule:"兴趣爱好:required;"
                    }
                }
            };


            var userinfo1={
                theme: 'simple_right',
                stopOnError:true,
                timely: 1,
                rules: {
                    year:[/^\d{4}$/,"从业年份"],
                    yearboolean:function (element){
                        var myDate = new Date();
                        var year=myDate.getFullYear();
                        if(parseInt(element.value)>year){
                            return {"error":"从业年份不能超过当前年份!"};
                        }else{
                            return true;
                        }
                    },

                    industryyear:function(element){
                        var birthYear = new Date($("#begintime").val()).getFullYear();
                        var workYear = parseInt(element.value) ;
                        if(workYear -birthYear < 18){
                            return {"error":"从业时间必须要满18周岁"};
                        }else{
                            return true;
                        }
                    },

                    csrq:function (element){
                        var myDate = new Date();
                        var year=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
                        var yue=myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
                        var ri=myDate.getDate(); //获取当前日(1-31)
                        var timestamp2 = Date.parse(new Date(element.value));
                        var timestamp1 = Date.parse(new Date((year-18)+"-"+yue+"-"+ri));
                        if(timestamp1<timestamp2){
                            return {"error":"出生日期必须满18周岁"}
                        }else{
                            return true;
                        }
                    },
                    qq:[/^\d{5,18}$/, "QQ号格式不正确"],
                    email:[/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/ , "邮箱格式不正确"],
                    myRemote1: function(element){
                        return $.ajax({
                            url: ctx+'/userinfo/username_forgot.html',
                            type: 'post',
                            data: 'phone='+ $.base64.btoa(element.value),
                            dataType: 'json'
                        });
                    },
                    zhongwen:function (element){
                        var reg=/^[0-9A-Za-z_]$/;
                        if(reg.test(element.value)){
                            return {"error":"用户名只能是数字字母下划线"};
                        }else{
                            return true;
                        }
                    },
                    nicknamelength:function(element){
                        var str=element.value;
                        var len=str.replace(/[\u0391-\uFFE5]/g,"**").length;
                        if(len>10){
                            return {"error":"昵称必须2到5个汉字或者4到10个字符"};
                        }else if(len<4){
                            return {"error":"昵称必须2到5个汉字或者4到10个字符"};
                        }else{
                            return true;
                        }
                    }
                },
                fields: {
                    "username": {
                        rule:"用户名:required;length(6~15);myRemote1;zhongwen;"
                    },
                    "nickname": {
                        rule:"昵称:required;nicknamelength;"
                    },
                    "sex":{
                        rule:"性别:required;"
                    },
                    "birthday":{
                        rule:"出生日期:required;csrq"
                    },
                    "industry":{
                        rule:"所属行业:required"
                    },
                    "careerDuration":{
                        rule:"从业时长:required;year;yearboolean,industryyear"
                    },
                    "education":{
                        rule:"学历:required;"
                    },
                    "qq":{
                        rule:"QQ:required;qq"
                    },
                    "email":{
                        rule:"eMail:required;email"
                    },
                    "province":{
                        rule:"省份:required;"
                    },
                    "city":{
                        rule:"市:required;"
                    },
                    "area":{
                        rule:"区县:required;"
                    },
                    "postAddress":{
                        rule:"详细地址:required;"
                    },
                    "tags":{
                        rule:"兴趣爱好:required;"
                    }
                }
            };


            $('#userupdsave').click(function (){
                $('#infoedit').validator('destroy');
                var $form1;
                if($('#username_').length>0){
                    $form1=$('#infoedit').validator(userinfo1);
                }else{
                    $form1=$('#infoedit').validator(userinfo);
                }
                $form1.isValid(function (v) {
                    if (v) {
                        abc.confirm('是否保存基本资料？',{icon:3}, function(){
                            parent.$.pop();
                            var data=$('#infoedit').serializeJson();
                            $.ajax({
                                type: "POST",
                                url: ctx+"/userinfo/infoedit.html",
                                data:data,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    parent.$.close();
                                    if(typeof(data.errorCode)=='undefined') {
                                        if (typeof(data.soacode) == "undefined") {
                                            if(data.data.code=='2000'){
                                                abc.msg('基本资料修改成功',{icon:1},function (){
                                                    if(typeof top.loc!="undefined"){
                                                        window.parent.location.href = ctx+"/external_index.html";
                                                    }else{
                                                        window.parent.location.href = ctx+"/index.html";
                                                    }
                                                });
                                            }else{
                                                abc.msg(data.data.message,{icon:2});
                                            }
                                        } else {
                                            abc.msg(data.message,{icon:2});
                                        }
                                    }else{
                                        eval(data.js)
                                    }
                                }
                            });
                        });
                    }
                })
            });

            $('#fanhui').click(function (){
                var url=$(this).attr("url");
                window.location.href=ctx+url;
            });


            $('#zm').change(function (){
                if($(this).length==0){
                    abc.msg("请选择图片！",{icon:2});
                    return ;
                }
                var _upFile=document.getElementById("zm");

                if(!checkFileExt(_upFile.value)){
                    _upFile.value='';
                    abc.msg("图片上传：文件类型必须是JPG、JPEG、PNG、GIF",{icon:2});
                    return;
                }


                if(myBrowser()=='IE'){
                    // var filePath = _upFile.value;
                    // var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                    // var file = fileSystem.GetFile (filePath);
                    // fileSize = file.Size;
                    // if(fileSize>1024*5*1024){
                    //     layer.msg("图片大小不能超过5M");
                    //     return ;
                    // }
                    //ietupianyasuo(_upFile,"zmbase64","LAY_demo_upload1");
                    $.ajaxFileUpload({
                        url: ctx+"/userinfo/base64upload.html?filename=zm",
                        secureuri: false,
                        fileElementId: 'zm',//file标签的id
                        dataType: 'json',
                        data: { },
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $('#zmbase64').val(data.base64);
                                    $('#LAY_demo_upload1').attr("src","data:image/png;base64,"+data.base64)
                                } else {
                                    abc.msg(res.message,{icon:2});
                                }
                            }else{
                                eval(res.js)
                            }
                        }
                    });
                }else{
                    var fileSize = this.files[0].size;
                    if(fileSize>1024*1024){
                        tupianyasuo("zmbase64","LAY_demo_upload1",this.files[0]);
                    }else{
                        var reader = new FileReader();
                        reader.onload = function(e) {
                            var base64Img= e.target.result;
                            base64Img=base64Img.replace(/^.*?,/,'')
                            $('#zmbase64').val(base64Img);
                            document.querySelector('#LAY_demo_upload1').src = "data:image/png;base64,"+base64Img;
                        }
                        reader.readAsDataURL(this.files[0]);
                    }
                }

            });


            function checkFileExt(filename)
            {
                var flag = false; //状态
                var arr = ["jpg","png","gif","jpeg","JPG","PNG","GIF","JPEG"];
                //取出上传文件的扩展名
                var index = filename.lastIndexOf(".");
                var ext = filename.substr(index+1);
                //循环比较
                for(var i=0;i<arr.length;i++)
                {
                    if(ext == arr[i])
                    {
                        flag = true; //一旦找到合适的，立即退出循环
                        break;
                    }
                }
                //条件判断
                return flag;
            }

            Array.prototype.lastIndexOf=function(item){
                var len=this.length;
                for(var i=len;i>=0;i--){
                    if(this[i]===item){
                        return len-i;
                    }
                }
                return -1;
            }


            $('#fm').change(function (){
                if($(this).length==0){
                    abc.msg("请选择图片！",{icon:2});
                    return ;
                }
                var _upFile=document.getElementById("fm");

                if(!checkFileExt(_upFile.value)){
                    _upFile.value='';
                    abc.msg("图片上传：文件类型必须是JPG、JPEG、PNG、GIF",{icon:2});
                    return;
                }

                if(myBrowser()=='IE'){
                    // var filePath = _upFile.value;
                    // var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                    // var file = fileSystem.GetFile(filePath);
                    // fileSize = file.Size;
                    // if(fileSize>1024*5*1024){
                    //     layer.msg("图片大小不能超过5M");
                    //     return ;
                    // }
                    //ietupianyasuo(_upFile,"fmbase64","LAY_demo_upload2");
                    $.ajaxFileUpload({
                        url: ctx+"/userinfo/base64upload.html?filename=fm",
                        secureuri: false,
                        fileElementId: 'fm',//file标签的id
                        dataType: 'json',
                        data: { },
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $('#fmbase64').val(data.base64);
                                    $('#LAY_demo_upload2').attr("src","data:image/png;base64,"+data.base64)
                                } else {
                                    abc.msg(res.message,{icon:2});
                                }
                            }else{
                                eval(res.js)
                            }
                        }
                    });
                }else{
                    var fileSize = this.files[0].size;
                    if(fileSize>1024*1024){
                        tupianyasuo("fmbase64","LAY_demo_upload2",this.files[0]);
                    }else{
                        var reader = new FileReader();
                        reader.onload = function(e) {
                            var base64Img= e.target.result;
                            base64Img=base64Img.replace(/^.*?,/,'')
                            $('#fmbase64').val(base64Img);
                            document.querySelector('#LAY_demo_upload2').src = "data:image/png;base64,"+base64Img;
                        }
                        reader.readAsDataURL(this.files[0]);
                    }
                }
            });


            /**
             * 非IE处理图片压缩
             * @param base64id
             * @param imageid
             */
            function tupianyasuo(base64id,imageid,op){
                var reader = new FileReader();
                reader.onload = function(e) {
                    var base64Img= e.target.result;
                    var _ir=ImageResizer({
                        resizeMode:"auto"
                        ,dataSource:base64Img
                        ,dataSourceType:"base64"
                        ,maxWidth:2400 //允许的最大宽度
                        ,maxHeight:2000 //允许的最大高度。
                        ,onTmpImgGenerate:function(img){
                        }
                        ,success:function(resizeImgBase64,canvas){
                            // //压缩后预览
                            // $("#nextview").attr("src",resizeImgBase64);
                            // //赋值到隐藏域传给后台
                            $('#'+base64id).val(resizeImgBase64.substr(23));
                            document.querySelector('#'+imageid).src = resizeImgBase64;
                        }
                        ,debug:true
                    });

                }
                reader.readAsDataURL(op);
            }

            function ietupianyasuo(obj,base64id,imageid){
                obj.select();
                //obj.blur();
                window.parent.document.body.focus();
                //获取文本内容值，在IE中input type=file 选择文件之后input显示的是文件在本地的路径
                var imgSrc = document.selection.createRange().text;
                alert(imgSrc);
                var xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");
                xmlHttp.open("POST",imgSrc, false);
                xmlHttp.send("");
                var xml_dom = new ActiveXObject("MSXML2.DOMDocument");
                var tmpNode = xml_dom.createElement("tmpNode");
                tmpNode.dataType = "bin.base64";
                tmpNode.nodeTypedValue = xmlHttp.responseBody;
                var imgBase64Data = tmpNode.text;
                //$("#LAY_demo_upload1").attr("src",'data:image/png;base64,'+imgBase64Data.replace(/\n/g,''));
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/imagecompress.html",
                    data:{'imgbase64':imgBase64Data},
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                $('#'+base64id).val(data.image);
                                $("#"+imageid).attr("src",'data:image/png;base64,'+data.image);
                            } else {
                                abc.msg(data.message,{icon:2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }

            /**
             * 判断浏览器类型
             * @returns {*}
             */
            function myBrowser(){
                var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
                var isOpera = userAgent.indexOf("Opera") > -1;
                if (isOpera) {
                    return "Opera"
                }; //判断是否Opera浏览器
                if (userAgent.indexOf("Firefox") > -1) {
                    return "FF";
                } //判断是否Firefox浏览器
                if (userAgent.indexOf("Chrome") > -1){
                    return "Chrome";
                }
                if (userAgent.indexOf("Safari") > -1) {
                    return "Safari";
                } //判断是否Safari浏览器
                if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
                    return "IE";
                }; //判断是否IE浏览器
            }
        })


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
                                    abc.msg("该课程已购买！",{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else{
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
                                    abc.msg('删除订单成功',{icon: 1},function (){
                                        window.location.href = ctx+"/userinfo/order.php";
                                    });
                                } else {
                                    abc.mag(data.data.message,{icon: 2});
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
                                    abc.msg('确认收货成功',{icon: 1},function (){
                                        window.location.href = ctx+"/userinfo/order.php";
                                    });
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
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
        });

    });
});