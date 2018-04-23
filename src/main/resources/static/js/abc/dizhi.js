require(["../config"], function () {
    require(["jquery.full"], function ($) {



        // $('#jssptest').click(function (){
        //     var el = document.createElement("a");
        //     document.body.appendChild(el);
        //     el.href = ctx+"/memberOrder/goods_js/fce87408b94946298bc5d64bace9eccf"; //url 是你得到的连接
        //     el.target = '_new'; //指定在新窗口打开
        //     el.click();
        //     document.body.removeChild(el);
        // });


        $('a[del="delete"]').click(function (){
            var del_id=$(this).attr('del-id');
            abc.confirm('是否删除收货地址？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/"+del_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('删除收货地址成功',{icon: 1});
                                    window.location.href = ctx+"/userinfo/useraddr.html";
                                } else {
                                    abc.alert(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            });
        })
        $('a[default-type="default"]').click(function (){
            var default_id=$(this).attr('default-id');
            abc.confirm('是否设为默认收货地址？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/default/"+default_id,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('默认收货地址设置成功',{icon: 1});
                                    window.location.href = ctx+"/userinfo/useraddr.html";
                                } else {
                                    abc.alert(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            });
        });



    })
})