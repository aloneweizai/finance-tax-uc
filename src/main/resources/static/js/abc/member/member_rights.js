require(["../../config"], function () {
    require(["jquery.full"], function ($) {

        $('a[palce="zhifu"]').click(function (){

            var goodsid=$(this).attr("goods-id");
            //window.location.href=ctx+"/memberOrder/details/"+goodsid
            var el = document.createElement("a");
            document.body.appendChild(el);
            el.href = ctx+"/memberOrder/details/"+goodsid; //url 是你得到的连接
            el.target = '_new'; //指定在新窗口打开
            el.click();
            document.body.removeChild(el);
            // var smrz=$(this).attr("smrz");
            // if(smrz!='2'){
            //     layer.alert("请先实名认证,<a style='color: red;' href='"+ctx+"/userinfo/userinfolist.html#1_1'>去认证</a>");
            //     return;
            // }
            //
            // var goodsname=$(this).attr("goods-name");
            // var goodsid=$(this).attr("goods-id");
            // var goodsjine=$(this).attr("goods-jiage");
            // var goodskucun=$(this).attr("goods-kucun");
            // var guigeid=$(this).attr("guige-id");
            // if(parseFloat(goodskucun)<=0){
            //     layer.msg("库存不足!")
            //     return ;
            // }
            // layer.confirm('是否开通'+goodsname+",金额： "+goodsjine+" 元?", {
            //     btn: ['是','否'] //按钮
            // }, function(){
            //     $.ajax({
            //         type: "POST",
            //         //url: ctx+ '/memberOrder/'+goodsid+"/"+guigeid,
            //         url: ctx+ '/memberOrder/place/'+goodsid,
            //         contentType: "application/json",
            //         dataType: "JSON",
            //         success: function (data) {
            //             if(typeof(data.errorCode)=='undefined') {
            //                 if (typeof(data.soacode) == "undefined") {
            //                     if (data.data.code == '2000') {
            //                         var orderId = data.orderId;
            //                         window.location.href = goodsid + "/" + orderId;
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