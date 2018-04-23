;!function(window, $){

    window.abc={
        msg:function (msg,icon,callback){
            if (typeof callback === "function"){
                parent.layer.msg(msg,icon,callback);
            }else{
                parent.layer.msg(msg,icon);
            }
        },
        alert:function (msg,icon,callback){
            if(typeof callback === "function"){
                parent.layer.alert(msg,icon,callback);
            }else{
                parent.layer.alert(msg,icon);
            }
        },
        confirms:function (msg,icon,callback,qcallback){
            if(typeof callback === "function"){
                parent.layer.confirm(msg,icon,function (index){
                    callback();
                    setTimeout(function (){
                        parent.layer.close(index);
                    },1000);
                },function (){
                    qcallback();
                });
            }
        },
        confirm:function (msg,icon,callback){
            if(typeof callback === "function"){
                parent.layer.confirm(msg,icon,function (index){
                    callback();
                    setTimeout(function (){
                        parent.layer.close(index);
                    },1000);
                });
            }
        }
    }

}(window, jQuery);