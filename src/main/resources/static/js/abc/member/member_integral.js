require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $('a[goods="jifen"]').click(function (){
            // debugger;
            /*var smrz=$(this).attr("smrz");
            if(smrz!='2'){
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return;
            }*/

            var goods_id=$(this).attr("goods-id");
            var kucun=$(this).attr("kucun");
            if(parseInt(kucun)>0){
                window.location.href=ctx+"/integral/"+goods_id;
            }else{
                abc.msg("库存不足,兑换失败!",{icon: 2});
            }
        });
    });
});