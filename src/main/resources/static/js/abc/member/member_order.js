require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        //鼠标悬停事件
        $(".dg_hy li img").hover(function(){
            $(this).addClass("active");
        },function(){
            $(this).removeClass("active");
        });

        $('a[palce="zhifu"]').click(function (){

            var goodsid=$(this).attr("goods-id");
            //window.location.href=ctx+"/memberOrder/details/"+goodsid;
            var el = document.createElement("a");
            document.body.appendChild(el);
            el.href = ctx+"/memberOrder/details/"+goodsid; //url 是你得到的连接
            el.target = '_new'; //指定在新窗口打开
            el.click();
            document.body.removeChild(el);
            // var goodsname=$(this).attr("goods-name");
            // var goodskucun=$(this).attr("goods-kucun");
            // if(parseFloat(goodskucun)<=0){
            //     layer.msg("库存不足!")
            //     return ;
            // }
            // layer.confirm('是否开通'+goodsname+"?", {
            //     btn: ['是','否'] //按钮
            // }, function(){
            //     $.ajax({
            //         type: "POST",
            //         url: ctx+ '/memberOrder/place/'+goodsid,
            //         contentType: "application/json",
            //         dataType: "JSON",
            //         success: function (data) {
            //             if(typeof(data.errorCode)=='undefined') {
            //                 if (typeof(data.soacode) == "undefined") {
            //                     if (data.data.code == '2000') {
            //                         var orderId = data.orderId;
            //                         window.location.href = ctx+"/member/pay/"+ orderId;
            //                     } else {
            //                         layer.msg(data.data.message);
            //                     }
            //                 } else {
            //                     layer.msg(data.message);
            //                 }
            //             }else{
            //                 eval(data.js)
            //             }
            //         }
            //     });
            // }, function(){
            // });
        });

    });
});