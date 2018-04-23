require(["../../config"], function () {
    require(["jquery.full"], function ($) {

        $("#jfgman").click(function(){
            if($('#syjfgm').is(':checked')){
                $(".scj").hide();
                $(".xsj").hide();
                $(".tj").hide();
                $(".zs").hide();
                $(".point").show();
            }else{
                $(".point").hide();
                $(".scj").show();
                $(".xsj").show();
                $(".tj").show();
                $(".zs").show();
            }
        });

        $('#submit_pay_button').click(function (){
            var goodsid=$(this).attr("goods-id");
            var goodsname=$(this).attr("goods-name");
            var tjr=$('#tjr').val();
            var levelCode = $(this).attr("level-code");
            var ischeck = $("input[type='checkbox']").is(':checked');
            var url = null;
            if(levelCode =="VIP1" && ischeck){
                url = ctx+ '/pointPay/'+goodsid ;
            }else{
                url = ctx+ '/memberOrder/places/'+goodsid;
            }
            var jiage = $(this).attr("jiage");
            var mpoint = $(this).attr("mpoint").replace(",","");
            if(parseInt(mpoint) < parseInt(jiage) && ischeck){
                abc.msg('您的积分不足',{icon:2});
            }else{
                abc.confirm('是否开通'+goodsname+"?",{icon:3}, function(){
                    $.ajax({
                        type: "POST",
                        url: url,
                        data:{'tjr':tjr},
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
                                        var orderId = data.orderId;
                                        var el = document.createElement("a");
                                        document.body.appendChild(el);
                                        el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                        el.target = '_new'; //指定在新窗口打开
                                        el.click();
                                        document.body.removeChild(el);
                                        parent.layer.closeAll();
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
            }
        });

    });
});