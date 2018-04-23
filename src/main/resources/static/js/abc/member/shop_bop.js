require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $('button[goods="jifen"]').click(function (){
            var goods_id=$(this).attr("goods-id");
            var kucun=$(this).attr("kucun");
            if(parseInt(kucun)>0){
                window.location.href=ctx +"/shopBop/"+ goods_id;
            }else{
                abc.msg("库存不足,购买失败!",{icon: 2});
            }
        });

    })
})