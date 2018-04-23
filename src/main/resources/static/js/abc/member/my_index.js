require(["../../config"],function($){
    require(["jquery.full"],function($){
    /**订单付款*/
    $('a[class="dd_zhifu"]').click(function (){
        var orderId=$(this).attr("order-id");
        var goodsid=$(this).attr("goods-id");
        var payMethod = $(this).attr("pay-method");
        var el = document.createElement("a");
        document.body.appendChild(el);
        el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
        el.target = '_new'; //指定在新窗口打开
        el.click();
        document.body.removeChild(el);
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
                url: ctx + "/userinfo/orderList/delete/" + delete_id,
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
                                abc.msg('删除订单成功',{icon: 1});
                                window.location.href = ctx+"/member/my_index.html";
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
                                    abc.msg('确认收货成功',{icon: 1});
                                    window.location.href = ctx+"/member/my_index.html";
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
        })

   });
});