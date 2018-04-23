/**
 * 分页js
 * Created by liuqi on 2017/5/22.
 */
(function($){
        $('#_goPage').click(function(){
            var page = $('#_goPs').val();
            var url = $(this).attr("data-url");
            if(page.match("^[1-9][0-9]*$")){
                window.location.href = url.replace("[:page]",page);
            }else{
                return;
            }
        });
})(jQuery);