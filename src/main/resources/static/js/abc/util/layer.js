/**
 * Created by LiuQi
 */
define(['jquery-3.1.0',"layer"],function($){

    var winscrollTop = function (offset){
        return window.parent?($(window.parent.document).scrollTop()+offset)+'px':($(document).scrollTop()+offset)+'px';
    };

    var uc_alert = function(success, msg, go_url){
        if(success){
            if(go_url){
                parent.layer.alert(msg, {offset: winscrollTop(200),icon: 1,closeBtn: 0}, function(){
                    window.location.href = go_url;
                });
            }else{
                parent.layer.alert(msg, {offset: winscrollTop(200),icon: 1,closeBtn: 0});
            }
        }else{
            parent.layer.alert(msg, {offset: winscrollTop(200),icon: 5,closeBtn: 0});
        }
    };

    var uc_confirm = function(req_type, req_url, req_json, back_url, title){
        parent.layer.confirm(!title?'确认操作？':title, {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: winscrollTop(200),closeBtn: 0},
            function(){
                $.ajax({
                    type: req_type,
                    url: req_url,
                    data: req_json,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data && data.code == "2000") {
                            if(back_url){
                                parent.layer.msg("操作成功", {offset: winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href = back_url;
                                });
                            }else{
                                parent.layer.msg("操作成功", {offset: winscrollTop(200),shade:0.3,icon: 1,time:1000});
                            }
                        } else {
                            uc_alert(false, '操作失败: '+data.message);
                        }
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
                        alert(XMLHttpRequest.responseText);
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus); // parser error;
                    }
                });
            }
        );
    };

    return{
        uc_alert: uc_alert,
        uc_confirm: uc_confirm
    }
});