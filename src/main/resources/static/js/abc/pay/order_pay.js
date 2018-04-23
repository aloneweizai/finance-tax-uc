require(["../../config"], function () {
    require(["jquery.full","jquery.base64.min"], function ($) {
        //订单支付页面 支付方式选择
        $('.main-bottom-content>div').click(function(){
            $(this).addClass('active').siblings().removeAttr("active")
            $(this).addClass('active').siblings().removeClass('active');
            $(this).attr("active","true");
        })

        $('#lj-pay-jf').click(function (){
            var jifen=$(this).attr("jifen");
            var spname=$(this).attr("spname");
            var tradeNo=$(this).attr("order-id");
            var order_lsh=$(this).attr("order_lsh");
            abc.confirms("是否支付"+jifen+"积分,购买"+spname+"?",{icon:3}, function(){
                $.ajax({
                    type: "get",
                    url: ctx+"/order_settlement/integral_zhifu/" + tradeNo,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('订单支付成功！',{icon: 1},function (){
                                        window.location.replace(ctx+"/order_settlement/integral_success/JF/"+tradeNo)
                                    });
                                }else{
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
            },function (){
            });
        });



        var zhifudiv='<div class="zf_zhifubao" test="zhifu" id="alipay_div" style="display:block;padding: 20px;"><h4><div class="zf_logo"><i class="iconfont" style="color:#009fe8;">&#xe750;</i></div></h4><div class="zf_erweima"><span id="alipay_img"><img src="'+ctx+'/images/loading.gif" id="zhifubaoewm"></span><p id="paysaoma">请使用支付宝扫一扫支付</p></div>' +
            '<button id="fanhuizhifu" class="grzx_btn" button-type="1">取消支付</button><button id="wanchengzhifu" button-type="2" class="grzx_btn">完成支付</button></div>';
        //var weixindiv='<div class="zf_weixin" test="zhifu" id="weixin_div" style="display: block"><h4><div class="zf_logo" ><i class="iconfont" style="color: #09bb07;">&#xe751;</i></div></h4><div class="zf_erweima"><span id="weixin_img"><img src="'+ctx+'/images/loading.gif" id="zhifubaoewm"></span><p>请使用微信扫一扫支付</p></div><button id="fanhuizhifu" button-type="1" class="grzx_btn">取消支付</button><button id="wanchengzhifu" button-type="2" class="grzx_btn">完成支付</button></div>';
        var weixindiv='<div class="zf_zhifubao" test="zhifu" id="weixin_div" style="display:block;padding: 20px;"><h4><div class="zf_logo"><i class="iconfont" style="color:#09bb07;">&#xe751;</i></div></h4><div class="zf_erweima"><span id="weixin_img"><img src="'+ctx+'/images/loading.gif" id="zhifubaoewm" style="width: 130px;"></span><p id="paysaoma">请使用微信扫一扫支付</p></div>' +
            '<button id="fanhuizhifu" class="grzx_btn" button-type="1">取消支付</button><button id="wanchengzhifu" button-type="2" class="grzx_btn">完成支付</button></div>';

        $('body').on('click','#fanhuizhifu',function (){
            if(boolstatus){
                var link=ctx+"/member/member_index.html";
                var url=ctx+"/member/my_index.html";
                var b = $.base64.btoa(url);
                window.location.replace(link+"?url="+b)
            }else{
                diguistatus=true;
                $.unblockUI();
            }
        })
        $('body').on('click','#wanchengzhifu',function (){
            var tradeNo=$('#lj-pay').attr("order-id");
            var order_lsh=$('#lj-pay').attr("order_lsh");
            var type_pay=$('div[active="true"]').attr("type-pay");
            if('wx'==type_pay){
                $.ajax({
                    type: "post",
                    url: ctx+"/memberOrder/orderpay/weixin/" + tradeNo,
                    contentType: "application/json",
                    dataType: "JSON",
                    data:{tjr:''},
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    window.location.replace(ctx+"/order_settlement/integral_success/RMB/"+tradeNo)
                                } else {
                                    var link=ctx+"/member/member_index.html";
                                    var url=ctx+"/member/my_index.html";
                                    var b = $.base64.btoa(url);
                                    window.location.replace(link+"?url="+b)
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else if(type_pay=='zfb'){
                $.ajax({
                    type: "post",
                    url: ctx+"/memberOrder/orderpay/" + tradeNo,
                    contentType: "application/json",
                    dataType: "JSON",
                    data:{tjr:''},
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    window.location.replace(ctx+"/order_settlement/integral_success/RMB/"+tradeNo)
                                } else {
                                    var link=ctx+"/member/member_index.html";
                                    var url=ctx+"/member/my_index.html";
                                    var b = $.base64.btoa(url);
                                    window.location.replace(link+"?url="+b)
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

        })


        var diguistatus=false;
        var zhifubao=false;
        var weixin=false;
        var status=2;
        $('#lj-pay').click(function (){
            var type_pay=$('div[active="true"]').attr("type-pay");
            var tradeNo=$(this).attr("order-id");
            if(type_pay=='wx'){
                if(!IsPC()){
                    if(isWeiXin()){
                        abc.msg("暂时不能微信支付",{icon: 2});
                    }else{
                        wxzf(tradeNo,type_pay);
                    }
                }else{
                    payQrCode("alipay_img",tradeNo,type_pay);
                }

            }else if(type_pay=='zfb'){
                if(status==1){
                    var el = document.createElement("a");
                    document.body.appendChild(el);
                    el.href = ctx+"/memberOrder/"+tradeNo; //url 是你得到的连接
                    el.target = '_new'; //指定在新窗口打开
                    el.click();
                    document.body.removeChild(el);
                    parent.$('#myModal').modal({backdrop: 'static', keyboard: false});
                }else{
                    payQrCode("alipay_img",tradeNo,type_pay);
                }
            }
        });

        var count=0;
        var zhifubao_status=false;
        var weixin_status=false;
        function payQrCode(imgID,orderid,type_pay){
            $.ajax({
                type: "get",
                url: ctx+"/memberOrder/payewm/"+orderid,
                dataType: "JSON",
                data:{tjr:$('#tjr').val(),type_pay:type_pay},
                beforeSend:function (){
                    parent.$.pop();
                },
                complete:function (){
                    //parent.$.close();
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if(data.data.code=="0000"){
                                abc.msg(data.data.message,{icon: 2},function (){
                                    window.location.replace(ctx+"/index.html");
                                });
                                return;
                            }
                            var html=zhifudiv;
                            if(type_pay=='wx'){
                                html=weixindiv;
                                weixin_status=true;
                            }else{
                                zhifubao_status=true;
                            }
                            $.blockUI({message:html,css:{
                                top:'50%',
                                left:'50%',
                                width:'250px',
                                height:'290px',
                                margin:'-145px 0 0 -125px',
                                cursor: 'auto'
                            }});

                            if (data.data.code == '2000') {
                                $('#zhifubaoewm').attr("src", "data:image/jpg;base64," + data.ewm);
                                // if ((weixin_status || zhifubao_status) && count == 0) {
                                //     count = 1;
                                    timeoutorder(orderid);
                                // }
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
                                var order_lsh=$('#lj-pay').attr("order_lsh");
                                abc.msg('订单支付成功！',{icon: 1},function (){
                                    window.location.replace(ctx+"/order_settlement/integral_success/RMB/"+orderid)
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


        function IsPC() {
            var userAgentInfo = navigator.userAgent;
            var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
            var flag = true;
            for (var v = 0; v < Agents.length; v++) {
                if (userAgentInfo.indexOf(Agents[v]) > 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        function isWeiXin() {
            var ua = window.navigator.userAgent.toLowerCase();
            console.log(ua);//mozilla/5.0 (iphone; cpu iphone os 9_1 like mac os x) applewebkit/601.1.46 (khtml, like gecko)version/9.0 mobile/13b143 safari/601.1
            if (ua.match(/MicroMessenger/i) == 'micromessenger') {
                return true;
            } else {
                return false;
            }
        }

        function wxzf(tradeNo,type_pay){
            $.ajax({
                type: "get",
                url: ctx+"/memberOrder/payewm/"+tradeNo,
                dataType: "JSON",
                data:{tjr:$('#tjr').val(),type_pay:type_pay,wxtype:"phone"},
                beforeSend:function (){
                    parent.$.pop();
                },
                complete:function (){
                    parent.$.close();
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            window.location.href=data.datas.mweb_url;
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        }

        function wxneizf(tradeNo,type_pay){
            $.ajax({
                type: "get",
                url: ctx+"/memberOrder/payewm/"+tradeNo,
                dataType: "JSON",
                data:{tjr:$('#tjr').val(),type_pay:type_pay,wxtype:"jsapi"},
                beforeSend:function (){
                    parent.$.pop();
                },
                complete:function (){
                    parent.$.close();
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            try {
                                WeixinJSBridge.invoke(
                                    'getBrandWCPayRequest', {
                                        "appId": data.datas.appid,   //公众号名称，由商户传入
                                        "timeStamp": data.datas.timeStamp,     //时间戳，自1970年以来的秒数
                                        "nonceStr": data.datas.nonce_str, //随机串
                                        "package": "prepay_id=" + data.datas.prepay_id,   //统一订单号
                                        "signType": data.datas.sign_type,     //微信签名方式：
                                        "paySign": data.datas.sign //支付签名
                                    },
                                    function (res) {
                                        alert(res.err_msg);
                                        if (res.err_msg == "get_brand_wcpay_request：ok") {

                                        }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回  ok，但并不保证它绝对可靠。
                                    }
                                );
                            } catch (e) {
                                WeixinJSBridge_abc.invoke(
                                    'getBrandWCPayRequest', {
                                        "appId": data.datas.appid,   //公众号名称，由商户传入
                                        "timeStamp": data.datas.timeStamp,     //时间戳，自1970年以来的秒数
                                        "nonceStr": data.datas.nonce_str, //随机串
                                        "package": "prepay_id=" + data.datas.prepay_id,   //统一订单号
                                        "signType": "MD5",     //微信签名方式：
                                        "paySign": data.datas.paySign //支付签名
                                    },
                                    function (res) {
                                        alert(res.err_msg);
                                        if (res.err_msg == "get_brand_wcpay_request：ok") {

                                        }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回  ok，但并不保证它绝对可靠。
                                    }
                                );
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


    })
})