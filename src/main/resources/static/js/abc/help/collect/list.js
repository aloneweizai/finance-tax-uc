/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/layer","../abc/util/pagination"], function ($, abc_layer) {
        //取消收藏
        $(".js_cancel_collect").click(function(){
            abc_layer.uc_confirm("POST", $(this).attr("data-href"),'', $("[name='curr_link']").val());
        });
    })
});