require(["../config"], function () {
    require(["jquery.full","layui"], function ($) {
        $(".layui-tab-title li").click(function(){
            var status = $(this).attr("status");
            window.location.href = ctx + "/coupons/list.html?status="+status;
        });


        $('a[class="coupon"]').click(function () {
            var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }
            var giftId = $(this).attr("gift-id");
            window.location.href = ctx +"/gift/"+ giftId;
        });


        function immediateuse(kj){
            var category = $(kj).attr("category-id");
            if(category.indexOf("HYCZ")>=0){
                window.location.href=ctx + "/member/order_member.html";
            }else if(category.indexOf("JFCZ")>=0){
                window.location.href=ctx + "/integral/integral_payment.html";
            }else if(category.indexOf("CSKT")>=0){
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = snsurl +"/school/index.html"; //url 是你得到的连接
                el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
            }else if(category.indexOf("UCSC")>=0){
                window.location.href=ctx + "/member/member_integral_uc.html";
            }else{
                window.location.href=ctx + "/member/order_member.html";
            }
        }

        $('a[class="immediateuse"]').click(function (){
            immediateuse(this);
        });

        $('div[class="triangle"]').click(function (){
            deleteCoupon(this);
        });

        function deleteCoupon(kj){
            var couponId = $(kj).attr("coupon-id");
            var couponStatus =$(kj).attr("coupon-status");
            if(couponStatus=="4"){
                abc.msg('优惠券已删除！',{icon: 2});
            }else{
                abc.confirm('是否删除优惠券？删除后不能再领取！',{icon:3}, function(){
                    $.ajax({
                        type: "GET",
                        url: ctx + "/coupons/deleteCoupon?couponId="+couponId,
                        contentType: "application/json",
                        dataType:"json",
                        success: function (data) {
                            if(typeof(data.errorCode)=='undefined') {
                                if (typeof(data.soacode) == "undefined") {
                                    if (data.data.code == '2000') {
                                        abc.msg('删除成功',{icon: 1});
                                        window.location.href = ctx+"/coupons/list.html";
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
                })
            }
        }

        var pagetools=layui.laypage;
        $(function (){
            var index=$('#demo').attr("index");
            if(index!=0){
                fenye("fanye");
            }
        })

        function fenye(id) {
            var count = $('#demo').attr("count");
            var index = $('#demo').attr("index");
            var status = $('#demo').attr("coupon-status")
            //分页
            pagetools.render({
                elem: 'demo' //分页容器的id
                , count: count //总页数
                , limit: 6
                , curr: index
                , skin: '#1E9FFF' //自定义选中色值
                //,skip: true //开启跳页
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                        window.location.href = ctx + "/coupons/list.html?index=" + obj.curr + "&status=" + status;
                    }
                }
            });
        }
    });
});