require(["../config"], function () {
    require(["jquery.full","jquery.md5"], function ($) {
        $(function(){
            //第一页的确定按钮
            $("#btn_part1").click(function(){
                // if(!verifyCheck._click()) return;
                var phone= $('#phone').val();
                var randCode=$('#randCode').val()
                var reg=/^1[0-9]{10}$/;
                if(phone==''){
                    layer.tips("手机号码不能为空","#phone",{tips: [1, '#14b9d5']});
                    return ;
                }else if(randCode==''){
                    layer.tips("验证码不能为空","#randCode",{tips: [1, '#14b9d5']});
                    return ;
                }else if(!reg.test(phone)){
                    layer.tips("手机号码不正确","#phone",{tips: [1, '#14b9d5']});
                    return ;
                }
                $.ajax({
                    url: ctx+"/forgot.html",
                    data: {phone:phone,code:randCode},
                    type: "POST",
                    dataType: 'json',
                    success: function(re) {
                        if(typeof(re.errorCode)=='undefined') {
                            if (typeof(re.soacode) == "undefined") {
                                if (re.result.code == '4018') {
                                    layer.tips("手机号码不存在","#phone",{tips: [1, '#14b9d5']});
                                    var imgSrc = $("#yzmimg");
                                    var timestamp = (new Date()).valueOf();
                                    var src = ctx+"/validatecode.html?timestamp=" + timestamp;
                                    imgSrc.attr("src", src);
                                } else if (re.result.code == '2000') {
                                    document.getElementById('shoujihao').innerHTML = phone;
                                    $(".part1").hide();
                                    $(".part2").show();
                                    $(".step li").eq(1).addClass("on");
                                    $('#phones').html(phone);
                                } else {
                                    layer.tips(re.result.message,"#phone",{tips: [1, '#14b9d5']});
                                    var imgSrc = $("#yzmimg");
                                    var timestamp = (new Date()).valueOf();
                                    var src = ctx+"/validatecode.html?timestamp=" + timestamp;
                                    imgSrc.attr("src", src);
                                }
                            } else {
                                abc.msg(re.message,{icon:2})
                            }
                        }else{
                            eval(re.js)
                        }
                    }
                });
            });

            $('#yzm').click(function (){
                var shoujihao=$('#shoujihao').html();
                var type=$(this).attr("type-name");
                settime();
                $.ajax({
                    type:'POST',
                    url: ctx+"/sms.html" ,
                    data:{phone:shoujihao,type:type},
                    success: function (data){
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.result.code == '2000') {
                                    abc.msg("发送成功，有效期5分钟!",{icon:1})
                                } else {
                                    abc.msg( data.result.message,{icon:2});
                                }
                            } else {
                                abc.msg(data.message,{icon:2})
                            }
                        }else{
                            eval(data.js)
                        }

                    } ,
                    dataType: "JSON"
                });
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

            //第二页的确定按钮
            $("#btn_part2").click(function(){
                var shoujihao=$('#shoujihao').html();
                var verifyNo=$('#verifyNo').val();
                if(verifyNo==''){
                    layer.tips("验证码不能为空","#verifyNo",{tips: [1, '#14b9d5']});
                    return;
                }
                if(verifyNo.length<6){
                    layer.tips("验证码必须为6位","#verifyNo",{tips: [1, '#14b9d5']});
                    return;
                }
                $.ajax({
                    url: ctx+"/smsforgot.html",
                    data: {phone:shoujihao,code:verifyNo},
                    type: "POST",
                    dataType: 'json',
                    success: function(re) {
                        if(typeof(re.errorCode)=='undefined') {
                            if (typeof(re.soacode) == "undefined") {
                                if (re.result.code == '2000') {
                                    $(".part2").hide();
                                    $(".part3").show();
                                } else {
                                    layer.tips(re.result.message,"#verifyNo",{tips: [1, '#14b9d5']});
                                }
                            } else {
                                abc.msg(re.message,{icon:2})
                            }
                        }else{
                            eval(re.js)
                        }
                    }
                });
            });
            //第三页的确定按钮
            $("#btn_part3").click(function(){
                var rePassword=$('#password').val();
                var password=$('#rePassword').val();
                if(rePassword==''){
                    layer.tips("密码不能为空","#password",{tips: [1, '#14b9d5']});
                    return;
                }
                if(rePassword.length<8&&rePassword.length>16){
                    layer.tips("密码长度不能小于8位却不能大于16位","#password",{tips: [1, '#14b9d5']});
                    return;
                }
                var mima=/((?=.*[a-zA-Z])(?=.*\d)|(?=[a-zA-Z])(?=.*[_])|(?=.*\d)(?=.*[_]))[a-zA-Z\d_]{8,16}/;
                if (!mima.test(rePassword)) {
                    layer.tips("密码必须由8-16位数字、字母、下划线中至少两种组成","#password",{tips: [1, '#14b9d5']});
                    return;
                }
                if(password==''){
                    layer.tips("第二次密码不能为空","#rePassword",{tips: [1, '#14b9d5']});
                    return;
                }
                if(rePassword!=password){
                    layer.tips("两次密码不一致","#rePassword",{tips: [1, '#14b9d5']});
                    return;
                }
                var shoujihao=$('#shoujihao').html();
                parent.$.pop();
                $.ajax({
                    url: ctx+"/saveforgot_v2.html",
                    data: {phone:shoujihao,password:$.md5(rePassword)},
                    type: "POST",
                    dataType: 'json',
                    success: function(re) {
                        parent.$.close();
                        if(typeof(re.errorCode)=='undefined') {
                            if (typeof(re.soacode) == "undefined") {
                                if (re.result.code == '2000') {
                                    $(".part3").hide();
                                    $(".part4").show();
                                    $(".step li").eq(2).addClass("on");
                                    time();
                                } else {
                                    layer.tips(re.result.message,"#password",{tips: [1, '#14b9d5']});
                                    // document.getElementById('password2').innerHTML = re.result.message;
                                }
                            } else {
                                abc.msg(re.message,{icon:2})
                            }
                        }else{
                            eval(re.js)
                        }

                    }
                });
            });

            var count=5;
            function time(){
                if (count == 0) {
                    window.location.href=ctx+"/login";
                    return;
                } else {
                    $('#times').html(count);
                    count--;
                }
                setTimeout(function() {
                        time() }
                    ,1000)
            }

            $('#verifyYz').click(function (){
                //alert('发送短信');
            });
        });

        $('#tk').click(function (){
            if($(this).is(':checked')){
                $('#btn_part1').attr("DISABLED",false);
            }else{
                $('#btn_part1').attr("DISABLED",true);
            }
        });


        $('#yzmimg').click(function (){
            var imgSrc = $("#yzmimg");
            var timestamp = (new Date()).valueOf();
            var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
            imgSrc.attr("src", src);
        });

        $('.c-blue').click(function (){
            var imgSrc = $("#yzmimg");
            var timestamp = (new Date()).valueOf();
            var src =  ctx+"/validatecode.html?timestamp=" + timestamp;
            imgSrc.attr("src", src);
        });

        function showoutc(){$(".m-sPopBg,.m-sPopCon").show();}
        function closeClause(){
            $(".m-sPopBg,.m-sPopCon").hide();
        }
    });
})
