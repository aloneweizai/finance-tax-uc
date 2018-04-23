require(["../config"],function($){
    require(["jquery.full","jquery.jedate"],function($){

       /* $(".wddd_quxiao").click(function(){
            layer.open({
                title: '在线调试'
                ,offset: '100px'
                ,content: '可以填写任意的layer代码'
            });
        })*/

        var start = {
            format: 'YYYY-MM-DD',
            maxDate: $.nowDate({DD:0}), //最大日期
            choosefun: function(elem, val, date){
                end.minDate = date; //开始日选好后，重置结束日的最小日期
                endDates();
            }
        };
        var end = {
            format: 'YYYY-MM-DD',
            minDate: $.nowDate({DD:0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            choosefun: function(elem, val, date){
                start.maxDate = date; //将结束日的初始值设定为开始日的最大日期
            }
        };

        function endDates() {
            //将结束日期的事件改成 false 即可
            end.trigger = false;
            $("#endtime").jeDate(end);
        }
        $('#begintime').jeDate(start);
        $('#endtime').jeDate(end);




        $(".quxiao").click(function(){
            $("#myModal").slideToggle();
            var iframet = parent.document.getElementById("external-frame");
            $(iframet).height(700);
        });

        $(".grzx_main_rt_nav>ul>li").click(function(){
            this.addClass("hover").siblings(".hover").removeClass("hover");
            $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
        });

        $('#fanhui').click(function (){
            window.location.href=ctx + "/userinfo/order.php";
        });

        function pay_order(kj){
            var orderId=$(kj).attr("order-id");
            var goodsid=$(kj).attr("goods-id");
            var payMethod = $(kj).attr("pay-method");
            //如果是课程，先查询次课程是否购买
            var channels = $(kj).attr("channels");
            if(channels =="CSKT"){
                $.ajax({
                    type: "GET",
                    url: ctx + "/userinfo/ketang" +"?goodsId=" +goodsid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                //debugger;
                                if (data.kechen == false) {
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                    el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                } else {
                                    abc.alert("该课程已购买！",{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else{
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }
        }

        function cancel_order(kj){
            var order_id=$(kj).attr('order-id');
            var order_status=$(kj).attr('order-status');
            var cause =$('#recipient-name option:selected').text();
            var remark =$('message-text').text();
            var cancelCause={orderNo:order_id,orderStatus:order_status,cancelId:cause,remark:remark};
            abc.confirm('是否取消订单？',{icon:3,offset:['40%']}, function(){
                $.ajax({
                    type: "POST",
                    url: "../../orderList/cancel",
                    data: JSON.stringify(cancelCause),
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('取消订单成功',{icon: 1});
                                    window.location.href = ctx+"/userinfo/order.php";
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

        function delete_order(kj){
            var delete_id=$(kj).attr('delete-id');
            abc.confirm('是否删除订单？',{icon:3,offset:['40%']}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/orderList/delete/" + delete_id,
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
                                    window.location.href = ctx+"/userinfo/order.php";
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

        function delivery_order(kj){
            var confirm_id =$(kj).attr('confirm-id');
            abc.confirm('确认收货？',{icon:3,offset:['40%']}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/userinfo/orderList/confirm/" + confirm_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('确认收货成功',{icon: 1},function (){
                                        window.location.href = ctx+"/userinfo/order.php";
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
        }

        /**订单付款*/
        $('a[class="dd_zhifu"]').click(function (){
            pay_order(this);
        });

        /**订单详情*/
        $(".orderdetail").click(function (){
            var orderId=$(this).attr("detail-id");
            window.location.href=ctx+ "/orderDetail/"+orderId;
            layer.load();
        });
        /**取消订单*/
        $("#queding").click(function (){
            cancel_order(this);
        });
        /**删除订单*/
        $("a[class='deleteorder']").click(function (){
            delete_order(this);
        });
        /**确认收货*/
        $("a[class='confirmorder']").click(function (){
            delivery_order(this);
        });


        /**根据日期查询订单*/
        $("#btnQuery").click(function () {
            var startDate = $("#begintime").val();
            var endDate = $("#endtime").val();
            var order_type = $(this).attr("order_type");
            window.location.href = ctx + "/userinfo/orderList/date?start=" + startDate + "&end=" + endDate + "&type="+ order_type;
        });

        $(".layui-tab-title li").click(function() {
            var type = $(this).attr("type");
            window.location.href = ctx + "/userinfo/order.php?type=" + type;
        });

        var pagetools=layui.laypage;
        $(function (){
            var index=$('#demo').attr("index");
            if(index!=0){
                fenye("fanye");
            }
        })

        function fenye(id){
            var count=$('#demo').attr("count");
            var index=$('#demo').attr("index");
            var type=$('#demo').attr("order-type");
            //分页
            pagetools.render({
                elem: 'demo' //分页容器的id
                ,count: count //总页数
                ,limit:7
                ,curr:index
                ,skin: '#1E9FFF' //自定义选中色值
                //,skip: true //开启跳页
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        window.location.href = ctx + "/userinfo/order.php?index="+obj.curr+"&type="+type;
                    }
                }
            });
        }
    });
});
