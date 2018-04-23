require(["../../config"], function () {
    require(["jquery.full"], function ($) {
       /* $(function(){
            var keyname=$(".sp_type").attr("keyname");
            var key=$('a[keyname="'+keyname+'"]');
            key.each(function(index,item){
                if(index == 0){
                    $(this).attr("class","sp_type select");
                }else{
                    $(this).attr("class","sp_type");
                }
            });
        });*/

        $('#jifengoods').click(function (){
            var goodsid=$(this).attr("goods-id");
            var member_jifen=$(this).attr("member-jifen").replace(",","");
            var goodsjiage=$(this).attr("goods-jiage");
            var guigeid=$(this).attr("guige-id");
            var goodsname=$(this).attr("goods-name");
            //debugger;
            var hyjia = $(".hyjia").attr("jiage");
            var jiage = null
            if(hyjia != null && hyjia.length >0){
                jiage = hyjia;
            }else{
                jiage = goodsjiage;
            }
            //实物订单添加地址
            var addressid = null;
            var isShipping =$(this).attr("isShipping");
            if(isShipping=="1"){
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
            }
            jiage = jiage.replace(",","");
            if(parseInt(member_jifen)>=parseInt(jiage)){
                abc.confirm("您是否花费："+jiage+"积分,兑换"+goodsname+"?",{icon:3}, function(){
                    $.ajax({
                        type: "POST",
                        url: ctx+'/integral/'+goodsid+"/"+guigeid + "?addressid="+ addressid,
                        //data:addressid,
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
                                        abc.msg(goodsname + '下单成功!',{icon: 1},function (){
                                            window.location.href = ctx + "/pointPay/" + data.data.data.orderNo;
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
                abc.msg("您的积分不足!",{icon: 2});
            }
        });


        $(".sp_type").click(function (){
            var keyname=$(this).attr("keyname");
            var key=$('a[keyname="'+keyname+'"]');
            key.each(function(){
                $(this).attr("class","sp_type");
            });
            $(this).attr("class","sp_type select");
            var value=$(this).attr("keyvalue");
            var size=$('#jifenduihuan').attr("guige-size");
            if(size==1){
                var goodsid=$('#jifenduihuan').attr("goods-id")
                $.ajax({
                    type: "GET",
                    url: ctx+'/integral/guige/'+goodsid+"/"+value,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                //debugger;
                                $('.jifenshu').html(data.jiage);
                                if(typeof(data.vipPrice) == "undefined"){
                                    $('.hy').hide();
                                    $('.hyjia').attr("jiage",data.jiage);
                                    $('.hyjia').html(data.jiage);
                                }else{
                                    $('.hy').show();
                                    $('.hyjia').attr("jiage",data.vipPrice);
                                    $('.hyjia').html(data.vipPrice);
                                }
                                $('#jifenduihuan').attr("goods-jiage", data.jiage)
                                $('#jifenduihuan').attr("guige-id", data.guigeid)
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else if(size>1){
                var count=0;
                var str="";
                $('.sp_type').each(function (){
                    if($(this).attr("class")=='sp_type select'){
                        if(count==0){
                            str=$(this).attr("keyvalue");
                            count++;
                        }else{
                            str=str+","+$(this).attr("keyvalue");
                            count++;
                        }
                    }
                });
                if(count==size){
                    var goodsid=$('#jifenduihuan').attr("goods-id")
                    $.ajax({
                        type: "GET",
                        url: ctx+'/integral/guige/'+goodsid+"/"+str,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    $('.jifenshu').html(data.jiage);
                                    if(typeof(data.vipPrice) == "undefined"){
                                        $('.hy').hide();
                                        $('.hyjia').attr("jiage",data.jiage);
                                        $('.hyjia').html(data.jiage);
                                    }else{
                                        $('.hy').show();
                                        $('.hyjia').attr("jiage",data.vipPrice);
                                        $('.hyjia').html(data.vipPrice);
                                    }
                                    $('#jifenduihuan').attr("goods-jiage", data.jiage)
                                    $('#jifenduihuan').attr("guige-id", data.guigeid)
                                } else {
                                    abc.msg(data.message,{icon: 2});
                                }
                            }else{
                                eval(data.js)
                            }
                        }
                    });
                }
            }
        });


        $('#jifenduihuan').click(function (){
            var goodsid=$(this).attr("goods-id");
            var member_jifen=$(this).attr("member-jifen").replace(",","");
            var goodsjiage=$(this).attr("goods-jiage");
            var guigeid=$(this).attr("guige-id");
            var goodsname=$(this).attr("goods-name");
            //debugger;
            var hyjia = $(".hyjia").attr("jiage");
            var jiage = null;
            if(hyjia != null && hyjia.length >0){
                jiage = hyjia;
            }else{
                jiage = goodsjiage;
            }
            //实物订单添加地址
            var addressid = null;
            var isShipping =$(this).attr("isShipping");
            if(isShipping=="1"){
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
            }

            jiage = jiage.replace(",","");
            if(guigeid!=''&&goodsjiage!=''){
                if(parseInt(member_jifen)>=parseInt(jiage)){
                    abc.confirm("您是否花费："+jiage+"积分,兑换"+goodsname+"?",{icon:3}, function(){
                        $.ajax({
                            type: "POST",
                            url: ctx+'/integral/'+goodsid+"/"+guigeid + "?addressid="+ addressid,
                            //data:addressid,
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
                                            abc.msg(goodsname + '下单成功!',{icon: 1});
                                            window.location.href = ctx + "/pointPay/" + data.data.data.orderNo;
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
                    abc.msg("您的积分不足!",{icon: 2});
                }
            }else{
                abc.msg("请选择商品规格!",{icon: 2});
            }
        });

        $('#upddizhis').click(function (){
            abc.confirm('是否修改地址？',{icon:3}, function(){
                window.location.href=ctx+"/userinfo/useraddr.html";
                parent.layer.closeAll();
            });
        });


        $('#ljdh').click(function (){
            var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }

            var goods_id=$(this).attr("goods-id");
            var count=0;
            var member_jifen=$(this).attr("member-jifen").replace(",","");
            var goodsjiage=$(this).attr("goods-jiage");
            //debugger;
            var hyjia = $(".hyjia").attr("jiage");
            var jiage = null;
            if(hyjia != null && hyjia.length >0){
                jiage = hyjia;
            }else{
                jiage = goodsjiage;
            }
            var str="";
            if($('.sp_type').length!=0){
                $('.sp_type').each(function (){
                    if($(this).attr("class")=='sp_type select'){
                        if(count==0){
                            str=$(this).attr("keyvalue");
                            count++;
                        }else{
                            str=str+","+$(this).attr("keyvalue");
                            count++;
                        }
                    }
                });
                var value = str.replace(",","");
                if(value==''){
                    abc.msg("请选择规格!",{icon:2});
                    return;
                }
            }

            jiage = jiage.replace(",","");
            if(parseInt(member_jifen)>=parseInt(jiage)) {
                $.ajax({
                    type: "POST",
                    url: ctx + '/integral/' + goods_id + "?guigeid=" + str,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (typeof(data.errorCode) == 'undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = ctx + "/order_settlement/integral/" + data.lsh; //url 是你得到的连接
                                    el.target = '_blank'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                } else {
                                    abc.msg(data.data.message, {icon: 2});
                                }
                            } else {
                                abc.msg(data.message, {icon: 2});
                            }
                        } else {
                            eval(data.js)
                        }
                    }
                });
            }else{
                abc.msg("您的积分不足!",{icon: 2});
            }
        })

    });
});