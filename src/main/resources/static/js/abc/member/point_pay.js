require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $('#upddizhis').click(function (){
            abc.confirm('是否修改地址？',{icon:3}, function(){
                window.location.href=ctx+"/userinfo/useraddr.html";
                parent.layer.closeAll();
            });
        });

        $('#pointPay').click(function (){
            var tradeNo=$(this).attr("tradeNo");
            var member_jifen=$(this).attr("member-jifen").replace(",","");
            var goodsjiage=$(this).attr("goods-jiage").replace(",","");
            var dizhi = $(".dizhi");
            var addressid = null;
            if(dizhi != null && dizhi.length != 0){
                var address=$('input:radio[name="addressId"]');
                if(address.length==0){
                    abc.msg('请先维护发票地址!',{icon: 2});
                    return ;
                }
                addressid=$('input:radio[name="addressId"]:checked').val();
                if(typeof(addressid)=="undefined"){
                    abc.msg('请选择发票地址!',{icon: 2});
                    return ;
                }
            }
            var data =JSON.stringify({tradeNo:tradeNo,addressId:addressid});
            if(parseInt(member_jifen)>=parseInt(goodsjiage)){
                //加锁，防止重复支付
                abc.confirm("是否立即支付",{icon:3}, function(){
                        $.ajax({
                            type: "POST",
                            url: ctx + '/pointPay/toPay',
                            data:data,
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
                                            abc.msg('支付成功!',{icon: 1},function (){
                                                window.location.href = ctx + "/userinfo/order.php";
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
    })
})