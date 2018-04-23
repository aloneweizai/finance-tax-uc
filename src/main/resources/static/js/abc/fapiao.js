require(["../config"], function () {
    require(["jquery.full"], function ($) {

        $("#hide").click(function(){
            $(".advertising").hide();
        });




        $("#checkall").click(
            function(){
                var bool=this.checked;
                var f=document.getElementsByName("checkname");
                for(var i=0;i<f.length;i++)
                {
                    f[i].checked=bool;
                }
            }
        );


        $(".grzx_main_rt_nav>ul>li").click(function(){
            //debugger;
            $(this).addClass('hover').siblings('.hover').removeClass('hover');
            $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
            $.iframeHeight();
        })



        $('#savefp').click(function (){

            var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }

            var checkname=$('input[name="checkname"]');
            var bool=false;
            var ids="";
            var je=0;
            var jeList=[];
            var i=0;
            checkname.each(function() {
                if (this.checked == true) {
                    ids+=this.value+",";
                    var jes=$(this).attr("data-je");
                    je+=parseFloat(jes);
                    bool=true;
                    jeList[i]=jes;
                    i++;
                }
            })
            if(bool){
                window.location.href=ctx+"/userinfo/addinvice.html?ids="+ids+"&je="+je+"&jes="+jeList;
            }else{
                abc.msg("请选择需要开发票的订单!",{icon: 2});
            }
        });

        $('.fptt').click(function (){
            if($(this).find('input[name="name"]').val()==1){
                $('tr[name="gs"]').each(function (){
                    $(this).hide();
                })
                $('#nsrsbh').val('')
                $('#compName').val('')
                $('#address').val('')
                $('#phone').val('')
                $('#bank').val('')
            }else{
                $('tr[name="gs"]').each(function (){
                    $(this).show();
                })
            }
            $.iframeHeight();
        });


        $('label[class="fpxz"]').click(function(){
            if($(this).find('input[name="property"]').val()=='1'){
                $("#email").hide();
                $("#emails").val('');
                $('#fapiaodizhi').show();
            }else{
                $("#email").show();
                $('#fapiaodizhi').hide();
            }
            $.iframeHeight();
        })

        var $validatorWsysVoFrom = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
            },
            fields: {
                // "type": {
                //     rule:"发票类型:required"
                // },
                "name": {
                    rule:"发票抬头:required;"
                },
                "content": {
                    rule:"发票内容:required"
                },
                "property":{
                    rule:"发票性质:required"
                },
                "addressId":{
                    rule:"发票地址:required"
                }
            }
        }

        var $validatorWsysVoFrom2 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                email:[ /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,"邮箱不正确"]
            },
            fields: {
                // "type": {
                //     rule:"发票类型:required"
                // },
                "name": {
                    rule:"发票抬头:required;"
                },
                "content": {
                    rule:"发票内容:required"
                },
                "property":{
                    rule:"发票性质:required"
                },
                "addressId":{
                    rule:"发票地址:required"
                },
                "email":{
                    rule:"邮箱:email;required"
                }
            }
        }

        var $validatorWsysVoFrom3 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                email:[ /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,"邮箱不正确"],
                yanzhengphone:function (element){
                    var v1=/^1(3|4|5|7|8)\d{9}$/;
                    var v2=/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
                    if(v1.test(element.value)||v2.test(element.value)){
                        return true;
                    }else{
                        return {"error":"手机号或者固定电话错误"};
                    }
                }
            },
            fields: {
                // "type": {
                //     rule:"发票类型:required"
                // },
                "name": {
                    rule:"发票抬头:required;"
                },
                "content": {
                    rule:"发票内容:required"
                },
                "property":{
                    rule:"发票性质:required"
                },
                "addressId":{
                    rule:"发票地址:required"
                },
                "nsrsbh":{
                    rule:"纳税人识别号:required;length(15~20)"
                },
                "nsrmc":{
                    rule:"公司名称:required;length(5~25)"
                },
                "address":{
                    rule:"注册地址:length(5~50)"
                },
                "phone":{
                    rule:"注册电话:yanzhengphone"
                },
                "bank":{
                    rule:"开户银行:length(5~25)"
                },
                "email":{
                    rule:"邮箱:email;required"
                }
            }
        }

        var $validatorWsysVoFrom1 = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                yanzhengphone:function (element){
                    var v1=/^1(3|4|5|7|8)\d{9}$/;
                    var v2=/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
                    if(v1.test(element.value)||v2.test(element.value)){
                        return true;
                    }else{
                        return {"error":"手机号或者固定电话错误"};
                    }
                }
            },
            fields: {
                // "type": {
                //     rule:"发票类型:required"
                // },
                "name": {
                    rule:"发票抬头:required;"
                },
                "content": {
                    rule:"发票内容:required"
                },
                "property":{
                    rule:"发票性质:required"
                },
                "addressId":{
                    rule:"发票地址:required"
                },
                "nsrsbh":{
                    rule:"纳税人识别号:required;length(15~20)"
                },
                "nsrmc":{
                    rule:"公司名称:required;length(5~25)"
                },
                "address":{
                    rule:"注册地址:length(5~50)"
                },
                "phone":{
                    rule:"注册电话:yanzhengphone"
                },
                "bank":{
                    rule:"开户银行:length(5~25)"
                }
            }
        }


        $('#fapiaotijiao').click(function (){
            var val=$('input:radio[name="name"]:checked').val();
            var email=$('input:radio[name="property"]:checked').val();
            var $form1=null;
            if(email == 1){//选择纸质发票时
                var jeList = $('input[name="jesList"]').val();
                var b = false;
                var jes = jeList.split(",");
                for(var j=0;j<jes.length;j++){
                    if(jes[j] < 100){//当订单中金额小于100元的怎不能索取发票
                        b = true;
                        break;
                    }
                }
                if(b){
                    layer.msg('纸质发票申请金额不能低于100元',
                        {  time: 3000,
                            icon:5,
                        });
                    return;
                }
            }

            if(val==2&&email==1){
                $('#formfapiao').validator('destroy');
                $form1=$('#formfapiao').validator($validatorWsysVoFrom1);
                var address=$('input:radio[name="addressId"]');
                if(address.length==0){
                    abc.msg('请先维护发票地址!',{icon: 2});
                    return ;
                }
                var addressid=$('input:radio[name="addressId"]:checked').val();
                if(typeof(addressid)=="undefined"){
                    abc.msg('请选择发票地址!',{icon: 2});
                    return ;
                }
            }else if(val==1&&email==1){
                $('#formfapiao').validator('destroy');
                $form1=$('#formfapiao').validator($validatorWsysVoFrom);
                var address=$('input:radio[name="addressId"]');
                if(address.length==0){
                    abc.msg('请先维护发票地址!',{icon: 2});
                    return ;
                }
                var addressid=$('input:radio[name="addressId"]:checked').val();
                if(typeof(addressid)=="undefined"){
                    abc.msg('请选择发票地址!',{icon: 2});
                    return ;
                }
            }else if(val==2&&email==2){
                $('#formfapiao').validator('destroy');
                $form1=$('#formfapiao').validator($validatorWsysVoFrom3);
            }else if(val==1&&email==2){
                $('#formfapiao').validator('destroy');
                $form1=$('#formfapiao').validator($validatorWsysVoFrom2);
            }
            if($form1.isValid()){
                var data=$('#formfapiao').serializeJson();
                abc.confirm('是否提交发票申请？',{icon:3}, function(){
                    parent.$.pop();
                    $.ajax({
                        type: "POST",
                        url: ctx+"/userinfo/invoice/save.html",
                        data:data,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            parent.$.close();
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.data.code == '2000') {
                                        abc.msg('索要发票提交成功!',{icon: 1});
                                        window.location.href = ctx+"/userinfo/userinvoice.html";
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
            }


        });

        $('a[fapiao="fp"]').click(function (){
            var id=$(this).attr("fapiao-id");
            $.ajax({
                type: "POST",
                url: ctx+"/userinfo/invoice/"+id,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            window.open(data.url);
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        });

        $('#fanhui').click(function (){
            window.location.href=ctx+"/userinfo/userinvoice.html";
        });

        $('#upddizhis').click(function (){
            abc.confirm('进入地址维护，当前页面填写数据会丢失？',{icon:3}, function(){
                window.location.href=ctx+"/userinfo/useraddr.html";
                parent.layer.closeAll();
            });
        });

        $(".queren").click(function(){
            var id=$(this).attr("fapiao-id");
            abc.confirm("是否确认收货?",{icon:3},
            function(){
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/invoice/shouhuo/"+id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('收货成功',{icon: 1});
                                    window.location.href = ctx + "/userinfo/userinvoice.html"  ;
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
            })
        });

    })
})