require(["../../config"], function () {
    require(["jquery.full","jquery.base64.min"], function ($) {




        $("#logout").click(function (){
            abc.confirm('是否退出？',{icon:3}, function(){
                $.ajax({
                    url: ctx+"/logout.html",
                    type: "POST",
                    dataType: 'json',
                    success: function(re) {
                        window.location.href=re.cswurl+'/index.html'
                    }
                });
            });
        });


        $('.nemu-box ul li').click(function (){
            var link=$(this).attr("name");
            var type=$(this).attr("type");
            var url=$(this).attr("link");
            var waibu=$(this).attr("waibu");
            $.ajax({
                type: "POST",
                url: ctx+"/overtime.html",
                dataType: "JSON",
                async: false,
                beforeSend: function () {
                    $.pop();       //打开一个遮罩层，或者直接禁用你的按钮
                },
                complete: function () {
                    $.close();      //取消遮罩层，或者回复按钮
                },
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == "2000") {
                                if(typeof(waibu) == "undefined"){
                                    if (!link) {
                                        return;
                                    }
                                    if ("undefined" == typeof type) {
                                        var iframet = parent.document.getElementById("external-frame");
                                        $(iframet).height(1150);
                                        var iframes = $(".uc_iframe").attr('src', link);
                                        var iframe = document.getElementById("external-frame");
                                    } else {
                                        if("undefined" != typeof top.loc){
                                            top.loc.SwitchHref("#member");
                                            var b = $.base64.btoa(url);
                                            window.location.href = link + "?url=" + b;
                                        }else{
                                            var b = $.base64.btoa(url);
                                            window.location.href = link + "?url=" + b;
                                        }
                                    }
                                } else{
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = link; //url 是你得到的连接
                                    //el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                }
                            } else {
                                if(typeof window.top.loc !=='undefined'){
                                    window.top.loc.GoHome();
                                }else{
                                    if(typeof window.top.loc !=='undefined'){
                                        window.top.loc.GoHome();
                                    }else{
                                        window.parent.layer.open({
                                            type: 1
                                            ,title: false
                                            ,area: '480px'
                                            ,zIndex:9000
                                            ,content:$("#login_layer")
                                            ,closeBtn: 0
                                            ,btnAlign: 'c'
                                        });
                                    }
                                }
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
})