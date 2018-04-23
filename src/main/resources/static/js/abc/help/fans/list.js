/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/layer","../abc/util/pagination"], function ($, abc_layer) {
        //取消关注
        $(".js_cancel_follow").click(function(){
            abc_layer.uc_confirm("POST", $(this).attr("data-href"),'', $("[name='curr_link']").val(),"确认取消关注？");
        });
    })
});