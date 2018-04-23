require(["../../config"], function () {
    require(["jquery.full"], function ($) {

        $('#submit_pay_button').click(function (){
            var jine=$(this).attr("jine");
            var guige=$(this).attr("guigeid");
            var zongjifen=parseFloat(jine).toFixed(2)*1000;
            abc.confirm("充值 ￥"+jine+"元，兑换积分:"+Math.floor(zongjifen)+"分!",{icon:3}, function(){
                window.parent.$.pop();
                $.ajax({
                    type: "POST",
                    url: ctx+ '/memberOrder/integral_order.html',
                    data: {jine:jine,guige:guige},
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    window.parent.$.close()
                                    var orderId = data.orderId;
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                    el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                } else {
                                    window.parent.$.close();
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                window.parent.$.close()
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