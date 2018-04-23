require(["../config"],function($){
    require(["jquery.full"],function($) {
        $(".grzx_main_rt_nav>ul>li").click(function(){
            $(this).addClass('hover').siblings('.hover').removeClass('hover');
            $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
        });

        /**订单详情*/
        $('a[class="orderdetail"]').click(function (){
            var orderId=$(this).attr("detail-id");
            window.location.href=ctx+ "/orderDetail/"+orderId;
            layer.load();
            var iframet = parent.document.getElementById("external-frame");
            $(iframet).height(700);
        });

        /**退换货申请*/
        $('a[class="dd_zhifu"]').click(function (){
            var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                layer.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }

            var orderId=$(this).attr("order-id");
            window.location.href=ctx+ "/orderback/back/"+orderId;
            var iframet = parent.document.getElementById("external-frame");
            $(iframet).height(700);
        });

        /**查看*/
        $('a[class="chakan"]').click(function (){
            var orderId=$(this).attr("order-id");
            var exchangeId=$(this).attr("exchange-id");
            window.location.href=ctx+ "/orderback/exchange/"+ exchangeId + "/" +orderId;
            var iframet = parent.document.getElementById("external-frame");
            $(iframet).height(700);
        });

        /**退换信息*/
        $('a[class="exchangeOrder"]').click(function (){
            var exchangeId=$(this).attr("exchange-id");
            window.location.href=ctx+ "/orderback/exchangeConfirm/"+exchangeId;
            var iframet = parent.document.getElementById("external-frame");
            $(iframet).height(700);
        });

        /**确认收货*/
        $("a[class='shouhuo']").click(function (){
            var exchangeId =$(this).attr('exchange-id');
            abc.confirm('确认收货？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/orderback/receive/" + exchangeId,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('确认收货成功',{icon: 1},function (){
                                        window.location.href = ctx + "/orderback/back.php";
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

    })
})