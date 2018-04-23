require(["../../config"], function () {
    require(["jquery.full"], function ($) {

        // $(".clearfix a").click(function(){
        //     $(this).addClass('select').siblings().removeClass('select')
        //     var id=$(this).attr("id");
        //     $("#submit_pay_button").attr("goods-id",id);
        // })


        $('.pay_ways .way_alipay').on('click',function(){
            $('.pay_ways .way_weixin').css('background-position','0px -145px');
            $(this).css('background-position','-320px 0px');
            $("#submit_pay_button").attr("payType",$(this).attr("payMethod"));
        })
        $('.pay_ways .way_weixin').on('click',function(){
            $('.pay_ways .way_alipay').css('background-position','0px 0px');
            $(this).css('background-position','-191px -145px');
            $("#submit_pay_button").attr("payType",$(this).attr("payMethod"));
        });

        var zhifudiv='<div class="zf_zhifubao" test="zhifu" id="alipay_div" style="display:block;padding: 20px;"><h4><div class="zf_logo"><i class="iconfont" style="color:#009fe8;">&#xe750;</i></div></h4><div class="zf_erweima"><span id="alipay_img"><img src="'+ctx+'/images/loading.gif" id="zhifubaoewm"></span><p id="paysaoma">请使用支付宝扫一扫支付</p></div>' +
            '<button id="fanhuizhifu" class="grzx_btn" button-type="1">取消支付</button><button id="wanchengzhifu" button-type="2" class="grzx_btn">完成支付</button></div>';
        var weixindiv='<div class="zf_weixin" test="zhifu" id="weixin_div" style="display: block"><h4><div class="zf_logo" ><i class="iconfont" style="color: #09bb07;">&#xe751;</i></div></h4><div class="zf_erweima"><span id="weixin_img"><img src="'+ctx+'/images/loading.gif" id="weixingewm"></span><p>请使用微信扫一扫支付</p></div><button id="fanhuizhifu" button-type="1" class="grzx_btn">取消支付</button><button id="wanchengzhifu" button-type="2" class="grzx_btn">完成支付</button></div>';


        var diguistatus=false;

        $('body').on('click','#fanhuizhifu',function (){
            if(boolstatus){
                window.location.href=ctx+"/member/my_index.html";
            }else{
                diguistatus=true;
                $.unblockUI();
            }
        })
        $('body').on('click','#wanchengzhifu',function (){
            /*var orderid=$('#submit_pay_button').attr("order-id");*/
            var tradeNo=$('#submit_pay_button').attr("tradeNo");
            $.ajax({
                type: "post",
                url: ctx+"/memberOrder/orderpay/" + tradeNo,
                contentType: "application/json",
                dataType: "JSON",
                data:{tjr:$('#tjr').val()},
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                window.location.href = ctx + "/memberOrder/orderpay/ewmpaysuccess.html?orderid=" + tradeNo;
                            } else {
                                window.location.href = ctx + "/member/my_index.html";
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


        var zhifubao=false;
        var weixin=false;
        var status=2;
        $('#submit_pay_button').click(function (){
            var payType=$(this).attr("payType");
            //var tradeNo=$(this).attr("order-id");
             var tradeNo=$('#submit_pay_button').attr("tradeNo");
            if(payType=='ALIPAY'){
                if(status==1){
                    var el = document.createElement("a");
                    document.body.appendChild(el);
                    el.href = ctx+"/memberOrder/"+tradeNo; //url 是你得到的连接
                    el.target = '_new'; //指定在新窗口打开
                    el.click();
                    document.body.removeChild(el);
                    parent.$('#myModal').modal({backdrop: 'static', keyboard: false});
                }else{
                    $.blockUI({message:zhifudiv,css:{
                        top:'30%',
                        cursor: 'auto'
                    }});
                    zhifubao=true;
                    payQrCode("alipay_img",tradeNo);
                }
            }else if(payType=='WEIXIN'){
                $.blockUI({message:weixindiv});
                weixin=true;
                payQrCode("weixin_img",tradeNo);
            }
        });

        var count=0;
        function payQrCode(imgID,orderid){
            $.ajax({
                type: "get",
                url: ctx+"/memberOrder/payewm/"+orderid,
                dataType: "JSON",
                data:{tjr:$('#tjr').val()},
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                $('#zhifubaoewm').attr("src", "data:image/jpg;base64," + data.ewm);
                                if ((weixin || zhifubao) && count == 0) {
                                    count = 1;
                                    timeoutorder(orderid);
                                }
                            } else {
                                setTimeout(function () {
                                        timeoutorder(orderid)
                                    }
                                    , 5000)
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });


        }

        var boolstatus=false;
        var s='<div id="success"><i class="iconfont">&#xe740;</i><span>扫码成功</span></div>';

        function timeoutorder(orderid){
            if(diguistatus){
                diguistatus=false;
                return ;
            }
            $.ajax({
                type: "POST",
                url: ctx+"/memberOrder/orderpay/orderstatus/" + orderid,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                abc.msg('订单支付成功！',{icon: 1},function (){
                                    window.location.href = ctx + "/memberOrder/orderpay/ewmpaysuccess.html?orderid=" + orderid;
                                });
                            } else {
                                if (data.data.code == '6666' && !boolstatus) {
                                    boolstatus = true;
                                    $('#paysaoma').html(s);
                                }
                                setTimeout(function () {
                                        timeoutorder(orderid)
                                    }
                                    , 5000)
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });

        }
    });
});