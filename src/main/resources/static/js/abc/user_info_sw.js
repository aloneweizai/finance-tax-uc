require(["../config"], function () {
    require(["jquery.full","../abc/util/security","jquery.base64.min","jquery.md5"], function ($) {

        var laypage;
        layui.use(['laypage'], function() {
            laypage = layui.laypage;
            demo("demo0");
            demo("demo1");
            demo("demo2");
            $.iframeHeight();
        });



        var abcdzsb_index;

        $('#addmodal').click(function (){
            var phone=$(this).attr("phone");
            if(phone!=''){
                $.ajax({
                    type: "GET",
                    url: ctx + '/userinfo/bdgroup.html',
                    data:{},
                    dataType:"json",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                $('#selectgroup').html("")
                                $('#selectgroup').html("<option value=''></option>")
                                $.each(data.data, function(i, item){
                                    $('#selectgroup').append("<option value='"+item.bdgroup+"'>"+item.bdgroup+"</option>")
                                });
                                $('#myModal3').modal({ show:true, backdrop:'static' });
                            } else {
                                abc.msg(json.message,{icon: 2});
                            }
                        }else{
                            eval(json.js)
                        }
                    }
                });

            }else{
                layer.alert("您还未绑定手机号码，请先进行手机绑定",{icon:2},function (){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_0";
                });
            }
        });


        $('#addgroup').click(function (){
            var bool=$(this).is(':checked');
            if(bool){
                $('input[name="bdgroup"]').show();
                $('#selectgroup').hide();
            }else{
                $('input[name="bdgroup"]').hide();
                $('#selectgroup').show();
            }
        });

        $('#fz').mouseover(function (){
            var index=layer.tips('<span style="color: #DB7C22;">分组说明：帮助绑定税号较多的用户，分类备注绑定信息列表。如：天心区企业（4301xxxxxxx7165、4301xxxxxxx8549）、雨花区企业（4301xxxxxxx2256、4301xxxxxxx3342）。</span>', '#fz',{
                tips: [3,'#FFFCEF'],
                time:0
            });
            $(this).mouseout(function (){
                layer.close(index);
            })
        });





        $('#addmodal1').click(function (){
            var phone=$(this).attr("phone");
            if(phone!=''){
                $('#myModal1').modal({ show:true, backdrop:'static' });
            }else{
                layer.alert("您还未绑定手机号码，请先进行手机绑定",{icon:2},function (){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_0";
                });
            }
        });



        $('#dzsbquery').click(function (){
            var dszbname=$('#dszbname').val();
            var dszbnsrsbh=$('#dszbnsrsbh').val();
            var bdgroup=$('#bdgroup').val();
            $.ajax({
                type: "GET",
                url: ctx + '/userinfo/enterprise_page.html',
                data:{index:1,name:dszbname,nsrsbh:dszbnsrsbh,bdgroup:bdgroup},
                dataType:"html",
                success: function (data) {
                    try {
                        var json = eval("(" + data + ")");
                        if(typeof(json.errorCode)=='undefined') {
                            if (typeof(json.soacode) == "undefined") {
                                $('#abcdzsb').html(data);
                                demo("demo0");
                            } else {
                                abc.msg(json.message,{icon: 2});
                            }
                        }else{
                            eval(json.js)
                        }
                    }catch (e){
                        $('#abcdzsb').html(data);
                        demo("demo0");
                    }
                    $.iframeHeight();
                }
            });
        });

        $('#gsquery').click(function (){
            var gsname=$('#gsname').val();
            var gsnsrsbh=$('#gsnsrsbh').val();
            $.ajax({
                type: "GET",
                url: ctx + '/userinfo/enterprise_page1.html',
                data:{index:1,name:gsname,nsrsbh:gsnsrsbh},
                dataType:"html",
                success: function (data) {
                    try {
                        var json = eval("(" + data + ")");
                        if(typeof(json.errorCode)=='undefined') {
                            if (typeof(json.soacode) == "undefined") {
                                $('#abcgs').html(data);
                                demo('demo1');
                            } else {
                                abc.msg(json.message,{icon: 2});
                            }
                        }else{
                            eval(json.js)
                        }
                    }catch (e){
                        $('#abcgs').html(data);
                        demo('demo1');
                    }
                    $.iframeHeight();
                }
            });
        });

        $('#dsquery').click(function (){
            var dsname=$('#dsname').val();
            var dsnsrsbh=$('#dsnsrsbh').val();
            $.ajax({
                type: "GET",
                url: ctx + '/userinfo/enterprise_page2.html',
                data:{index:1,name:dsname,nsrsbh:dsnsrsbh},
                dataType:"html",
                success: function (data) {
                    try {
                        var json = eval("(" + data + ")");
                        if(typeof(json.errorCode)=='undefined') {
                            if (typeof(json.soacode) == "undefined") {
                                $('#abcds').html(data);
                                demo('demo2');
                            } else {
                                abc.msg(json.message,{icon: 2});
                            }
                        }else{
                            eval(json.js)
                        }
                    }catch (e){
                        $('#abcds').html(data);
                        demo('demo2');
                    }
                    $.iframeHeight();
                }
            });
        });


        function demo(id){
            var page=$('#'+id).attr("index");
            var count=$('#'+id).attr("count");
            laypage.render({
                elem:id
                ,count: count //数据总数
                ,limit:15
                ,curr:page
                ,layout: ["count",'prev', 'page', 'next']
                ,jump: function(obj, first){
                    //首次不执行
                    if(!first){
                        if(id=='demo0'){
                            var dszbname=$('#dszbname').val();
                            var dszbnsrsbh=$('#dszbnsrsbh').val();
                            var bdgroup=$('#bdgroup').val();
                            $.ajax({
                                type: "GET",
                                url: ctx + '/userinfo/enterprise_page.html',
                                data:{index:obj.curr,name:dszbname,nsrsbh:dszbnsrsbh,bdgroup:bdgroup},
                                dataType:"html",
                                success: function (data) {
                                    try {
                                        var json = eval("(" + data + ")");
                                        if(typeof(json.errorCode)=='undefined') {
                                            if (typeof(json.soacode) == "undefined") {
                                                $('#abcdzsb').html(data);
                                                demo(id);
                                            } else {
                                                abc.msg(json.message,{icon: 2});
                                            }
                                        }else{
                                            eval(json.js)
                                        }
                                    }catch (e){
                                        $('#abcdzsb').html(data);
                                        demo(id);
                                    }
                                    $.iframeHeight();
                                }
                            });
                        }else if(id=='demo1'){
                            var gsname=$('#gsname').val();
                            var gsnsrsbh=$('#gsnsrsbh').val();
                            $.ajax({
                                type: "GET",
                                url: ctx + '/userinfo/enterprise_page1.html',
                                data:{index:obj.curr,name:gsname,nsrsbh:gsnsrsbh},
                                dataType:"html",
                                success: function (data) {
                                    try {
                                        var json = eval("(" + data + ")");
                                        if(typeof(json.errorCode)=='undefined') {
                                            if (typeof(json.soacode) == "undefined") {
                                                $('#abcgs').html(data);
                                                demo(id);
                                            } else {
                                                abc.msg(json.message,{icon: 2});
                                            }
                                        }else{
                                            eval(json.js)
                                        }
                                    }catch (e){
                                        $('#abcgs').html(data);
                                        demo(id);
                                    }
                                    $.iframeHeight();
                                }
                            });
                        }else if(id=='demo2'){
                            var dsname=$('#dsname').val();
                            var dsnsrsbh=$('#dsnsrsbh').val();
                            $.ajax({
                                type: "GET",
                                url: ctx + '/userinfo/enterprise_page2.html',
                                data:{index:obj.curr,name:dsname,nsrsbh:dsnsrsbh},
                                dataType:"html",
                                success: function (data) {
                                    try {
                                        var json = eval("(" + data + ")");
                                        if(typeof(json.errorCode)=='undefined') {
                                            if (typeof(json.soacode) == "undefined") {
                                                $('#abcds').html(data);
                                                demo(id);
                                            } else {
                                                abc.msg(json.message,{icon: 2});
                                            }
                                        }else{
                                            eval(json.js)
                                        }
                                    }catch (e){
                                        $('#abcds').html(data);
                                        demo(id);
                                    }
                                    $.iframeHeight();
                                }
                            });
                        }

                    }
                }
            });
        }







        $(function (){
            if(window.location.hash=='#wjmm'){
                $('#forgotpwd').click();
            }


            $(".grzx_main_rt_nav>ul>li").click(function(){
                $(this).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
            })

            $(".anquan_click").click(function(){
                $(".bangdingnr").slideUp()
                $(this).parent().next().slideDown()
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
                $(".bangdingnr").eq(4).show().siblings(".bangdingnr").hide();
            })
        })

        $(document).on("click","a[name='del']",function (){
            var type=$(this).attr("data-type");
            var id=$(this).attr("data-id");
            var msg=$(this).attr("data-msg");
            abc.confirm('是否删除绑定的'+msg+'信息?',{icon:3}, function(){
                parent.$.pop();
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/"+type+"/"+id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('解绑成功',{icon:1},function (){
                                        if(type=='dzsb'){
                                            var dszbname=$('#dszbname').val();
                                            var dszbnsrsbh=$('#dszbnsrsbh').val();
                                            var page=$('#demo0').attr("index");
                                            $.ajax({
                                                type: "GET",
                                                url: ctx + '/userinfo/enterprise_page.html',
                                                data:{index:page,name:dszbname,nsrsbh:dszbnsrsbh},
                                                dataType:"html",
                                                success: function (data) {
                                                    try {
                                                        var json = eval("(" + data + ")");
                                                        if(typeof(json.errorCode)=='undefined') {
                                                            if (typeof(json.soacode) == "undefined") {
                                                                $('#abcdzsb').html(data);
                                                                demo("demo0");
                                                            } else {
                                                                abc.msg(json.message,{icon: 2});
                                                            }
                                                        }else{
                                                            eval(json.js)
                                                        }
                                                    }catch (e){
                                                        $('#abcdzsb').html(data);
                                                        demo("demo0");
                                                    }
                                                    $.iframeHeight();
                                                }
                                            });
                                        }else if(type=='hngs'){
                                            var gsname=$('#gsname').val();
                                            var gsnsrsbh=$('#gsnsrsbh').val();
                                            var page=$('#demo1').attr("index");
                                            $.ajax({
                                                type: "GET",
                                                url: ctx + '/userinfo/enterprise_page1.html',
                                                data:{index:page,name:gsname,nsrsbh:gsnsrsbh},
                                                dataType:"html",
                                                success: function (data) {
                                                    try {
                                                        var json = eval("(" + data + ")");
                                                        if(typeof(json.errorCode)=='undefined') {
                                                            if (typeof(json.soacode) == "undefined") {
                                                                $('#abcgs').html(data);
                                                                demo("demo1");
                                                            } else {
                                                                abc.msg(json.message,{icon: 2});
                                                            }
                                                        }else{
                                                            eval(json.js)
                                                        }
                                                    }catch (e){
                                                        $('#abcgs').html(data);
                                                        demo("demo1");
                                                    }
                                                    $.iframeHeight();
                                                }
                                            });
                                        }else if(type=='hnds'){
                                            var dsname=$('#dsname').val();
                                            var dsnsrsbh=$('#dsnsrsbh').val();
                                            var page=$('#demo2').attr("index");
                                            $.ajax({
                                                type: "GET",
                                                url: ctx + '/userinfo/enterprise_page2.html',
                                                data:{index:page,name:dsname,nsrsbh:dsnsrsbh},
                                                dataType:"html",
                                                success: function (data) {
                                                    try {
                                                        var json = eval("(" + data + ")");
                                                        if(typeof(json.errorCode)=='undefined') {
                                                            if (typeof(json.soacode) == "undefined") {
                                                                $('#abcds').html(data);
                                                                demo("demo2");
                                                            } else {
                                                                abc.msg(json.message,{icon: 2});
                                                            }
                                                        }else{
                                                            eval(json.js)
                                                        }
                                                    }catch (e){
                                                        $('#abcds').html(data);
                                                        demo("demo2");
                                                    }
                                                    $.iframeHeight();
                                                }
                                            });
                                        }
                                        //window.location.href = ctx+"/userinfo/enterprise.html";
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


        $('.nsqy_xzbd').click(function (){
            var id=$(this).attr("from-id");
            try{
                document.getElementById(id).reset();
                var id=$(this).attr("id-name");
                $('#'+id).modal({backdrop: 'static', keyboard: false});
            }catch (e){
            }
        });

        $('button[name="guanbi"]').click(function (){
            var id=$(this).attr("id-name");
            $('#'+id).modal('hide');
        });






        var $validatorWsysVoFrom = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"用户许可证不正确"],
                shuzizimu1:[/^[A-Za-z0-9]+$/,"子用户账号不正确"],
                myRemote2: function(element){
                    return $.ajax({
                        url: 'phone_forgot.html',
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
                    rule:"子用户账号:shuzizimu1"
                },
                "password": {
                    rule:"子用户密码:required;"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2"
                }
            }
        };

        var $validatorWsysVoFrom1 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"用户许可证不正确"],
                shuzizimu1:[/^[A-Za-z0-9]+$/,"子用户账号不正确"]
            },
            fields: {
                "subuser": {
                    rule:"用户许可证:required;shuzizimu"
                },
                "username": {
                    rule:"子用户账号:shuzizimu1"
                },
                "password": {
                    rule:"子用户密码:required"
                }
            }
        };

        // $('#fanhui').click(function (){
        //     var url=$(this).attr("url");
        //     window.location.href= ctx + url;
        // });
        //
        // $('#return').click(function (){
        //     var url=$(this).attr("url");
        //     window.location.href= ctx + url;
        // });


        $('#tijiao').click(function (){
            var iddiv=$(this).attr("id-name");
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            var $form2 = $("#form2").validator($validatorWsysVoFrom);
            $form2.isValid(function(v) {
            // $validatorWsysVoFrom.isValid(function(v) {
                if (v) {
                    var datas=$('#form2').serializeJson();
                    var jsons=$.parseJSON( datas );
                    jsons.password=$.md5(jsons.password);
                    var data=JSON.stringify(jsons);
                    window.parent.$.pop();
                    // $.ajax({
                    //     type: "POST",
                    //     url: ctx+"/rsa.html",
                    //     contentType: "application/json",
                    //     dataType: "JSON",
                    //     success: function (data) {
                    //         if(typeof(data.errorCode)=='undefined') {
                    //             if (typeof(data.soacode) == "undefined") {
                    //                 var publicKey = RSAUtils.getKeyPair(data.exponent,"",data.modulus);
                    //                 var password=RSAUtils.encryptedString(publicKey,jsons.password);
                    //                 jsons.password=$.base64.btoa(password);
                    //                 datas=JSON.stringify(jsons);
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
                                                        abc.msg('绑定成功',{icon:1},function (){
                                                            window.location.href = ctx+"/userinfo/enterprise.html";
                                                            $('#'+iddiv).modal('hide');
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
                        //         }else{
                        //             abc.msg(data.message,{icon: 2});
                        //         }
                        //     }else{
                        //         eval(data.js)
                        //     }
                        // }
                    // })
                }
            });
        });


        $(document).on('click','#qyxf',function (){
            var nsrsbh=$(this).attr("nsrsbh");
            $.ajax({
                url: ctx + "/toPayServiceCharge?random=" + Math.random()+"&nsrsbh="+nsrsbh,
                type: "GET",
                success: function (result) {
                    if (result.toUrl != null) {
                        //window.open(result.toUrl);
                        var el = document.createElement("a");
                        document.body.appendChild(el);
                        el.href = result.toUrl; //url 是你得到的连接
                        el.target = '_new'; //指定在新窗口打开
                        el.click();
                        document.body.removeChild(el);
                    } else {
                        //window.open("http://pay.abc12366.cn");
                        var el = document.createElement("a");
                        document.body.appendChild(el);
                        el.href = "http://pay.abc12366.cn"; //url 是你得到的连接
                        el.target = '_new'; //指定在新窗口打开
                        el.click();
                        document.body.removeChild(el);
                    }
                },
                error: function () {
                    //layer.alert("出错了");
                    //window.open("http://pay.abc12366.cn");
                    var el = document.createElement("a");
                    document.body.appendChild(el);
                    el.href = "http://pay.abc12366.cn"; //url 是你得到的连接
                    el.target = '_new'; //指定在新窗口打开
                    el.click();
                    document.body.removeChild(el);
                },
                dataType: "json"
            })
        })

        var $form3 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
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
                "bsy": {
                    rule:"办税员:required"
                },
                "password": {
                    rule:"密码:required"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2;"
                }
            }
        };

        var $form33 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
            },
            fields: {
                "bsy": {
                    rule:"办税员:required"
                },
                "password": {
                    rule:"密码:required"
                }
            }
        };

        $('#tijiaogs').click(function (){
            var iddiv=$(this).attr("id-name");
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            $('#form3').validator('destroy');
            var $form2 = $("#form3").validator($form3);
            $form2.isValid(function(v) {
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
                                        url: ctx+"/userinfo/bdhngs_v2.html",
                                        data:datas,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            window.parent.$.close();
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.data.code == '2000') {
                                                        abc.msg('绑定成功',{icon:1},function (){
                                                            window.location.href = ctx+"/userinfo/enterprise.html";
                                                            $('#'+iddiv).modal('hide');
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
                                }else{
                                    abc.msg(data.message,{icon:2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    })
                }
            });
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
                },
                fenzu:function (element){
                    var str=element.value;
                    if(str!=''){
                        var len=str.replace(/[\u0391-\uFFE5]/g,"**").length;
                        if(len<2){
                            return {"error":"分组名称必须2到4个汉字或者4到8个字符"};
                        }else if(len>8){
                            return {"error":"分组名称必须2到4个汉字或者4到8个字符"};
                        }else{
                            return true;
                        }
                    }else{
                        return true;
                    }

                }
            },
            fields: {
                "nsrsbhOrShxydm": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "fwmm": {
                    rule:"密码:required"
                },
                "bdgroup":{
                    rule:"分组:fenzu;"
                },
                "code":{
                    rule:"当前手机号:required;myRemote2;"
                }
            }
        };

        var $form11 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"纳税人识别号不正确"]
            },
            fields: {
                "nsrsbhOrShxydm": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "fwmm": {
                    rule:"密码:required"
                }
            }
        };

        $('#tijiaodzsb').click(function (){
            var iddiv=$(this).attr("id-name");
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
                    var datas = $('#form1').serializeJson();
                    var jsons = $.parseJSON(datas);
                    $.ajax({
                        type: "POST",
                        url: ctx + "/rsa_v2.html",
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (typeof(data.errorCode) == 'undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    var bool=$("#addgroup").is(':checked');
                                    var publicKey = RSAUtils.getKeyPair(data.exponent, "", data.modulus);
                                    var password = RSAUtils.encryptedString(publicKey, jsons.fwmm);
                                    jsons.fwmm = $.base64.btoa(password);
                                    if(!bool){
                                        jsons.bdgroup=$('#selectgroup').val();
                                    }

                                    datas = JSON.stringify(jsons);
                                    $.ajax({
                                        type: "POST",
                                        url: ctx+"/userinfo/bddzsb_v2.html",
                                        data: datas,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            window.parent.$.close();
                                            if (typeof(data.errorCode) == 'undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.data.code == '2000') {
                                                        abc.msg('绑定成功',{icon:1},function (){
                                                            window.location.href = ctx+"/userinfo/enterprise.html";
                                                            //$('#'+iddiv).modal('hide');
                                                        });

                                                    } else {
                                                        abc.msg(data.data.message,{icon:2});
                                                    }
                                                } else {
                                                    abc.msg(data.message,{icon:2});
                                                }
                                            } else {
                                                eval(data.js)
                                            }
                                        }
                                    });
                                } else {
                                    abc.msg(data.message,{icon:2});
                                }
                            } else {
                                eval(data.js)
                            }
                        }
                    })
                }
            })
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

        $('input[sms-id="yzm"]').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            //debugger;
            var verification=$(this).attr("sms-verification");
            var id=$(this).attr("sms-phone-id");
            var zcphone=$('#'+id).val();
            if(typeof(verification)=="undefined"){
                settimes2($(this));
                var type=$(this).attr("type-name");
                $.ajax({
                    type:'POST',
                    url: ctx+"/xinsms.html" ,
                    data:{phone:zcphone,type:type},
                    success: function (data){
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.result.code == '2000') {
                                    abc.msg("发送成功，有效期5分钟!",{icon:1})
                                } else {
                                    abc.msg( data.result.message,{icon:2});
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
                var $form=null;
                if(verification=='form1'){
                    $form= $("#"+verification).validator($form11);
                }else if(verification=='form2'){
                    $form= $("#"+verification).validator($validatorWsysVoFrom1);
                }else if(verification=='form3'){
                    $form= $("#"+verification).validator($form33);
                }else if(verification=='dzfgForm'){
                    $form =  $("#"+verification).validator($dzfgForm11);
                }

                var op=$(this);
                var type=$(this).attr("type-name");
                if($form.isValid()){
                    settimes2(op);
                    $.ajax({
                        type:'POST',
                        url: ctx+"/xinsms.html" ,
                        data:{phone:zcphone,type:type},
                        success: function (data){
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.result.code == '2000') {
                                        abc.msg("发送成功，有效期5分钟!",{icon:1})
                                    } else {
                                        abc.layerAlert(false, data.result.message);
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
        var $dzfgForm11 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"纳税人识别号不正确"],
                sfz:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,"身份证不正确"],
                phone:[/^1[0-9]{10}$/,"手机号不正确"],
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
                "nsrsbh": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "frmc": {
                    rule:"法人代表名称:required;"
                },
                "frzjh": {
                    rule:"法人代表证件号码:required;sfz"
                },
                "xphone": {
                    rule:"手机号码:required;phone;myRemote2;"
                }
            }
        };

        var $dzfgForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                shuzizimu:[/^[A-Za-z0-9]+$/,"纳税人识别号不正确"],
                sfz:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,"身份证不正确"],
                phone:[/^1[0-9]{10}$/,"手机号不正确"],
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
                "nsrsbh": {
                    rule:"纳税人识别号:required;shuzizimu"
                },
                "frmc": {
                    rule:"法人代表名称:required;"
                },
                "frzjh": {
                    rule:"法人代表证件号码:required;sfz"
                },
                "xphone": {
                    rule:"手机号码:required;phone;myRemote2;"
                },
                "code":{
                    rule:"验证码:required"
                }
            }
        };

        $('#dzsbforgot').click(function (){
            var validStatus=$('#smrzstatus').val();
            // if(validStatus!='2'){
            //     layer.msg("未实名认证，不能绑定！");
            //     return ;
            // }
            $('#dzfgForm').validator('destroy');
            var $dzfg= $("#dzfgForm").validator($dzfgForm);
            if($dzfg.isValid()){
                window.parent.$.pop();
                var data=$('#dzfgForm').serializeJson();
                $.ajax({
                    type: "POST",
                    url: ctx + "/userinfo/user_swdsadd_forgot.html",
                    data:data,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        window.parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg(data.data.message,{icon:1},function (){
                                        window.location.href = ctx+"/userinfo/enterprise.html";
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
        });

        $(document).on("click",'a[type="dzsb"]',function (){
            var dzsb=$(this).attr("dzsb-id");
            window.parent.$.pop();
            $.ajax({
                type: "get",
                url: ctx + "/userinfo/nsqyxq.html?id="+dzsb+"&type=dzsb",
                dataType: "html",
                success: function (data) {
                    window.parent.$.close();
                    $('#nsqyxq').html(data);
                    $('#myModal123').modal({backdrop: 'static', keyboard: false});
                }
            });
        })

        $(document).on("click",'a[type="update"]',function (){
            var dzsb=$(this).attr("nsrsbh");
            window.parent.$.pop();
            $.ajax({
                type: "POST",
                url: ctx + "/userinfo/update/nsqyxq.html?nsrsbh="+dzsb,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    window.parent.$.close();
                    if(data.data.code=='2000'){
                        abc.msg("更新成功",{icon:1},function (){
                            window.location.href = ctx+"/userinfo/enterprise.html";
                        });
                    }else{
                        abc.msg(data.data.message,{icon:2});
                    }
                }
            });
        })



        $(document).on("click",'a[type="gsdz"]',function (){
            var dzsb=$(this).attr("gsdz-id");
            window.parent.$.pop();
            $.ajax({
                type: "get",
                url: ctx + "/userinfo/nsqyxq.html?id="+dzsb+"&type=gsdz",
                dataType: "html",
                success: function (data) {
                    window.parent.$.close();
                    $('#nsqyxq').html(data);
                    $('#myModal123').modal({backdrop: 'static', keyboard: false});
                }
            });
        })

        $(document).on("click",'a[type="dswt"]',function (){
            var dzsb=$(this).attr("dswt-id");
            window.parent.$.pop();
            $.ajax({
                type: "get",
                url: ctx + "/userinfo/nsqyxq.html?id="+dzsb+"&type=dswt",
                dataType: "html",
                success: function (data) {
                    window.parent.$.close();
                    $('#nsqyxq').html(data);
                    $('#myModal123').modal({backdrop: 'static', keyboard: false});
                }
            });
        })

       $(document).on("click",'a[group="bd"]',function (){
           $('input[name="updatebdgroup"]').hide();
           $('#updateselectgroup').show();
           $('#updateaddgroup').attr("checked", false);
           var value=$(this).attr("fz-value");
           var nsrsbh=$(this).attr("nsrsbh");
           $.ajax({
               type: "GET",
               url: ctx + '/userinfo/bdgroup.html',
               data:{},
               dataType:"json",
               success: function (data) {
                   if(typeof(data.errorCode)=='undefined') {
                       if (typeof(data.soacode) == "undefined") {
                           $('#updateselectgroup').html("")
                           $('#updateselectgroup').html("<option value=''></option>")
                           $.each(data.data, function(i, item){
                               if(item.bdgroup==value){
                                   $('#updateselectgroup').append("<option value='"+item.bdgroup+"' selected>"+item.bdgroup+"</option>")
                               }else{
                                   $('#updateselectgroup').append("<option value='"+item.bdgroup+"'>"+item.bdgroup+"</option>")
                               }
                           });
                           $('#updategroup').attr("groupid",nsrsbh);
                           $('#myModalgroup').modal({ show:true, backdrop:'static' });
                       } else {
                           abc.msg(data.message,{icon: 2});
                       }
                   }else{
                       eval(data.js)
                   }
               }
           });
       })


        $('#updateaddgroup').click(function (){
            var bool=$(this).is(':checked');
            if(bool){
                $('input[name="updatebdgroup"]').show();
                $('#updateselectgroup').hide();
            }else{
                $('input[name="updatebdgroup"]').hide();
                $('#updateselectgroup').show();
            }
        });

        $('#updategroup').click(function (){
            var id=$(this).attr("groupid");
            var bdgroup='';
            var bool=$('#updateaddgroup').is(':checked');
            if(bool){
                bdgroup=$('input[name="updatebdgroup"]').val();
                if(bdgroup!=''){
                    var len=bdgroup.replace(/[\u0391-\uFFE5]/g,"**").length;
                    if(len<2){
                        abc.msg("分组名称必须2到4个汉字或者4到8个字符",{icon:2});
                        return;
                    }else if(len>8){
                        abc.msg("分组名称必须2到4个汉字或者4到8个字符",{icon:2});
                        return;
                    }
                }
            }else{
                bdgroup=$('#updateselectgroup').val();
            }
            $.ajax({
                type: "POST",
                url: ctx + '/userinfo/update/bdgroup.html',
                data:{id:id,bdgroup:bdgroup},
                dataType:"json",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if(data.data.code=='2000'){
                                abc.msg("分组成功",{icon: 1},function (){
                                    window.location.href = ctx+"/userinfo/enterprise.html";
                                });
                            }else{
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
        })

    })
})