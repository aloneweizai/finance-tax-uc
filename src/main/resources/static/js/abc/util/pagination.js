/**
 * Created by LiuQi
 * 分页
 */
define(['jquery-3.1.0'],function(){
    //点击下一页
    $('body').on('click', '.js_page_next', function(){
        window.location.href = $(this).attr("data-href");
    });

    //点击上一页
    $('body').on('click', '.js_page_previous', function(){
        window.location.href = $(this).attr("data-href");
    });

});