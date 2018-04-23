require(["../../config"], function () {
    require(["jquery.full","layui"], function ($) {
        $(".layui-tab-title li").click(function(){
            var category = $(this).attr("category");
            $.ajax({
                type: "GET",
                url: ctx + "/gift/category?category="+category,
                dataType:"html",
                success: function (data) {
                    try {
                        var json = eval("(" + data + ")");
                        if(typeof(json.errorCode)=='undefined') {
                            if (typeof(json.soacode) == "undefined") {
                                $('.layui-show').html(data);
                            } else {
                                abc.msg(json.message,{icon: 2});
                            }
                        }else{
                            eval(json.js)
                        }
                    }catch (e){
                        $('.layui-show').html(data);
                    }

                    $('a[class="gift"]').click(function () {
                       /* var smrz=$(this).attr("smrz");
                        if(smrz!='2'){
                            abc.alert("您未实名认证,立即认证",{icon:2},function(){
                                window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                                parent.layer.closeAll();
                            });
                            return;
                        }*/
                        var giftId = $(this).attr("gift-id");
                        window.location.href = ctx +"/gift/"+ giftId;
                    });

                    $('.giftImg').click(function () {
                       /* var smrz=$(this).attr("smrz");
                        if(smrz!='2'){
                            abc.alert("您未实名认证,立即认证",{icon:2},function(){
                                window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                                parent.layer.closeAll();
                            });
                            return;
                        }*/
                        var giftId = $(this).attr("gift-id");
                        window.location.href = ctx +"/gift/"+ giftId;
                    });
                }
            });
        });


        $('a[class="gift"]').click(function () {
            /*var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }*/
            var giftId = $(this).attr("gift-id");
            window.location.href = ctx +"/gift/"+ giftId;
        });

        $("textarea[name='remark']").keydown(function(){
            var curLength=$(this).val().length;
            if(curLength>400){
                var num=$(this).val().substr(0,400);
                $(this).val(num);
                alert("感谢您的热情，您的输入已超出最大限度！" );
            }
        })

        $('.giftImg').click(function () {
            /*var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }*/
            var giftId = $(this).attr("gift-id");
            window.location.href = ctx +"/gift/"+ giftId;
        });

        $('#upddizhis').click(function (){
            abc.confirm('进入地址维护，当前页面填写数据会丢失？',{icon:3}, function(){
                window.location.href=ctx+"/userinfo/useraddr.html";
                parent.layer.closeAll();
            });
        });

        $('#dizhi').click(function (){
           /* var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }*/
             var kucun=$(this).attr("kucun");
             var giftAmount=$(this).attr("gift-amount").replace(",","");
             var userAmount=$(this).attr("user-amount").replace(",","");
             if(kucun <= 0){
                 abc.msg('礼物已经被领取完了，您可以选择其他礼物!',{icon: 2});
             }
             if(parseInt(giftAmount) > parseInt(userAmount)){
                 abc.msg('您的礼物金额不足!',{icon: 2});
             }

        });

        $('button[class="confirm"]').click(function (){
            var gift_id=$(this).attr("gift-id");
            var kucun=$(this).attr("kucun");
            var goodsname=$(this).attr("gift-name");
            var remark=$("textarea[name='remark']").val();
            var giftAmount =$(this).attr("gift-amount").replace(",","");
            var userAmount =$(this).attr("user-amount").replace(",","");
            //实物订单添加地址
            var addressid = null;
            var address=$('input:radio[name="addressId"]');
            if(address.length==0){
                abc.msg('请先维护收货地址!',{icon: 2});
                return ;
            }
            addressid=$('input:radio[name="addressId"]:checked').val();
            if(typeof(addressid)=="undefined"){
                abc.msg('请选择收货地址!',{icon: 2});
                return ;
            }
            if(parseFloat(userAmount)>=parseFloat(giftAmount)){
                if(parseInt(kucun)>0){
                    abc.confirm('确认领取？',{icon:3}, function(){
                        $.ajax({
                            type: "POST",
                            url: ctx+"/gift/apply?giftId="+gift_id +"&addressId="+ addressid,
                            data:JSON.stringify(remark),
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
                                            abc.msg(goodsname + '兑换成功!',{icon: 1},function (){
                                                window.location.href = ctx + "/member/member_mall.html";
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
                }else{
                    abc.msg("礼物已经被领完了，您可以领取其他礼物!",{icon: 2});
                }
            }else{
                abc.msg("您的礼物金额不足!",{icon: 2});
            }
        });


        $(".detail").on("click",function(){
            var applyId  =$(this).attr("apply-id");
            window.location.href = ctx + "/gift/apply/"+ applyId;
        });


        $(".receive").on("click",function(){
            var applyId  =$(this).attr("apply-id");
            abc.confirm('确认收货？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/gift/receive/"+ applyId,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('确认收货成功',{icon: 1},function (){
                                        window.location.href = ctx+"/member/member_mall.html";
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
        });
    });
});