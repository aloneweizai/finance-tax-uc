require(["../config"], function () {
    require(["jquery.full","../abc/util/security","jquery.base64.min"], function ($) {

        $(function (){
            if(window.location.hash=='#wjsbmm'){
                $('#forgotpwd').trigger("click");
            }
        });
        var $validatorWsysVoFrom = $("#form2").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"用户许可证不正确"],
                shuzizimu1:[/^[A-Za-z0-9]+$/,"子用户账号不正确"],
                myRemote2: function(element){
                    return $.ajax({
                        url: ctx+'/userinfo/phone_forgot.html',
                        type: 'post',
                        data: 'phone='+ $.base64.btoa(element.value),
                        dataType: 'json'
                    });
                }
            },
            fields: {
                "subuser": {
                    rule:"用户许可证:required;shuzizimu"
                },
                "username": {
                    rule:"子用户账号:required;shuzizimu1"
                },
                "password": {
                    rule:"子用户密码:required"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2"
                }
            }
        });

        $('#fanhui').click(function (){
            var url=$(this).attr("url");
            window.location.href= ctx + url;
        });

        $('#return').click(function (){
            var url=$(this).attr("url");
            window.location.href= ctx + url;
        });


        $('#tijiao').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            $validatorWsysVoFrom.isValid(function(v) {
                if (v) {
                    var datas=$('#form2').serializeJson();
                    var jsons=$.parseJSON( datas );
                    //jsons.password=$.md5(jsons.password);
                    //data=JSON.stringify(jsons);
                    window.parent.$.pop();
                    $.ajax({
                        type: "POST",
                        url: ctx+"/rsa_v2.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    var publicKey = RSAUtils.getKeyPair(data.exponent,"",data.modulus);
                                    var password=RSAUtils.encryptedString(publicKey,jsons.password);
                                    jsons.password=$.base64.btoa(password);
                                    datas=JSON.stringify(jsons);
                                    $.ajax({
                                        type: "POST",
                                        url: ctx+"/userinfo/bdhnds.html",
                                        data:data,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            window.parent.$.close();
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.data.code == '2000') {
                                                        abc.msg('绑定成功',{icon: 1});
                                                        window.location.href = ctx+"/userinfo/enterprise.html";
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
                                }else{
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    })
                }
            })
        });

        var $form3 = $("#form3").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                myRemote2: function(element){
                    return $.ajax({
                        url: ctx+'/userinfo/phone_forgot.html',
                        type: 'post',
                        data: element.name +'='+ $.base64.btoa(element.value),
                        dataType: 'json'
                    });
                }
            },
            fields: {
                "bsy": {
                    rule:"办税员:required"
                },
                "password": {
                    rule:"密码:required"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2"
                }
            }
        });

        $('#tijiaogs').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            $form3.isValid(function(v) {
                if (v) {
                    window.parent.$.pop();
                    var datas=$('#form3').serializeJson();
                    var jsons=$.parseJSON( datas );
                    $.ajax({
                        type: "POST",
                        url: ctx+"/rsa_v2.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    var publicKey = RSAUtils.getKeyPair(data.exponent,"",data.modulus);
                                    var password=RSAUtils.encryptedString(publicKey,jsons.password);
                                    jsons.password=$.base64.btoa(password);
                                    datas=JSON.stringify(jsons);
                                    $.ajax({
                                        type: "POST",
                                        url: ctx+"/userinfo/bdhngs.html",
                                        data:datas,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            window.parent.$.close();
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.data.code == '2000') {
                                                        abc.msg('绑定成功',{icon: 1});
                                                        window.location.href = ctx+"/userinfo/enterprise.html";
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
                                }else{
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    })
                }
            })
        });

        var $form1 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"纳税人识别号不正确"],
                myRemote2: function(element){
                    return $.ajax({
                        url: ctx+'/userinfo/phone_forgot.html',
                        type: 'post',
                        data: 'phone='+ $.base64.btoa(element.value),
                        dataType: 'json'
                    });
                }
            },
            fields: {
                "nsrsbhOrShxydm": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "fwmm": {
                    rule:"密码:required"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2"
                }
            }
        };

        $('#tijiaodzsb').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            $('#form1').validator('destroy');
            var $form2 = $("#form1").validator($form1);
            $form2.isValid(function(v) {
                if (v) {
                    window.parent.$.pop();
                    var datas=$('#form1').serializeJson();
                    var jsons=$.parseJSON( datas );
                    $.ajax({
                        type: "POST",
                        url: ctx+"/rsa_v2.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    var publicKey = RSAUtils.getKeyPair(data.exponent,"",data.modulus);
                                    var password=RSAUtils.encryptedString(publicKey,jsons.fwmm);
                                    jsons.fwmm=$.base64.btoa(password);
                                    datas=JSON.stringify(jsons);
                                    $.ajax({
                                        type: "POST",
                                        url: ctx+"/userinfo/bddzsb.html",
                                        data:datas,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            window.parent.$.close();
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.data.code == '2000') {
                                                        abc.msg('绑定成功',{icon: 1});
                                                        window.location.href = ctx+"/userinfo/enterprise.html";
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
                                }else{
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    })
                }
            });
        });

        var formyzt={
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                phone:[/^1[0-9]{10}$/,"手机号不正确"],
                myRemote2: function(element){
                    return $.ajax({
                        url: ctx+'/userinfo/user_forgot.html',
                        type: 'post',
                        data: 'phone='+ $.base64.btoa(element.value),
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

        $('input[sms-id="yzm"]').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            var verification=$(this).attr("sms-verification");
            var id=$(this).attr("sms-phone-id");
            var zcphone=$('#'+id).val();
            if(typeof(verification)=="undefined"){
                settimes2($(this));
                var type=$(this).attr("type-name");
                // $.ajax({
                //     type:'POST',
                //     url: ctx+"/xinsms.html" ,
                //     data:{phone:zcphone,type:type},
                //     success: function (data){
                //         if(typeof(data.errorCode)=='undefined') {
                //             if (typeof(data.soacode) == "undefined") {
                //                 if (data.result.code == '2000') {
                //                     layer.msg("发送成功，有效期5分钟!")
                //                 } else {
                //                     abc.layerAlert(false, data.result.message);
                //                 }
                //             } else {
                //                 layer.msg(data.message);
                //             }
                //         }else{
                //             eval(data.js)
                //         }
                //     } ,
                //     dataType: "JSON"
                // });
            }else{
                $('#'+verification).validator('destroy');
                var $form=null;
                if(verification=='form1'){
                    $form= $("#"+verification).validator($form1);
                }else if(verification=='form2'){
                    $form= $("#"+verification).validator($validatorWsysVoFrom);
                }else if(verification=='form3'){
                    $form= $("#"+verification).validator($form3);
                }

                var op=$(this);
                var type=$(this).attr("type-name");
                $form.isValid(function(v) {
                    if (v) {
                        settimes2(op);
                        // $.ajax({
                        //     type:'POST',
                        //     url: ctx+"/xinsms.html" ,
                        //     data:{phone:zcphone,type:type},
                        //     success: function (data){
                        //         if(typeof(data.errorCode)=='undefined') {
                        //             if (typeof(data.soacode) == "undefined") {
                        //                 if (data.result.code == '2000') {
                        //                     layer.msg("发送成功，有效期5分钟!")
                        //                 } else {
                        //                     abc.layerAlert(false, data.result.message);
                        //                 }
                        //             } else {
                        //                 layer.msg(data.message);
                        //             }
                        //         }else{
                        //             eval(data.js)
                        //         }
                        //     } ,
                        //     dataType: "JSON"
                        // });
                    }
                });
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

        var $dzfgForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"纳税人识别号不正确"]
            },
            fields: {
                "nsrsbh": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "code":{
                    rule:"验证码:required"
                }
            }
        };

        $('#dzsbforgot').click(function (){
            var validStatus=$('#smrzstatus').val();
            if(validStatus!='2'){
                abc.msg("未实名认证，不能绑定！",{icon: 2});
                return ;
            }
            $('#dzfgForm').validator('destroy');
            var $dzfg= $("#dzfgForm").validator($dzfgForm);
            if($dzfg.isValid()){
                window.parent.$.pop();
                var data=$('#dzfgForm').serializeJson();
                $.ajax({
                    type: "POST",
                    url: ctx +"/userinfo/user_swdsadd_forgot.html",
                    data:data,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        window.parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg(data.data.message,{icon: 1});
                                    window.location.href = ctx+"/userinfo/enterprise.html";
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
            }
        });

    })
})