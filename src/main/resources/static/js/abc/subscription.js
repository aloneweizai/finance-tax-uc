require(["../config"],function(){
    require(["jquery.full"],function($){

        $(function (){
            $(".grzx_main_rt_nav>ul>li").click(function(){
                $(this).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
            });
        });

        $(".tip").hover(function(){
            var remark = $(this).attr("remark");
            layer.tips(remark,this,{
                tips: [1, '#3595CC'],
                time: 4000
            });
        },function(){
            layer.closeAll();
        })

        $('li[msg-id="xt"]').click(function (){
            window.location.href=ctx+'/userinfo/user_message.html?index=1&type=1';
        });

        $('li[msg-id="yh"]').click(function (){
            window.location.href=ctx+'/userinfo/user_message.html?index=1&type=2';
        });

        $('li[msg-id="ss"]').click(function (){
            window.location.href=ctx+'/userinfo/user_message.html?index=1&type=3';
        });

        $('li[msg-id="wd"]').click(function (){
            window.location.href=ctx+'/userinfo/user_message.html?index=1&type=4';
        });

        $('li[msg-id="dy"]').click(function (){
            window.location.href=ctx+'/subscription/subscription.html';
        });


        $(".dy_dalei").click(function(){
            var zilei=$(this).parent().next();
            zilei.toggle();
            if(zilei.css("display")=="table-row"){
                $(this).find("i").removeClass().addClass("iconfont icon-zuoyoujiantou-copy-copy");
            }else{
                $(this).find("i").removeClass().addClass("iconfont icon-zuoyoujiantou1");
            }
        });


        var dataList = new Array();
        $(function(){
            $(".rowSub").each(function(){
                var suId = $(this).attr("suid");
                var userId = $(this).attr("userid");
                var setId = $(this).attr("settingid");
                var web = $(this).attr("web");
                var wechat = $(this).attr("wechat");
                var message = $(this).attr("message");

                var userSub = {
                    id:suId ,
                    userId:userId ,
                    settingId:setId ,
                    web:web ,
                    wechat:wechat ,
                    message:message
                };

                dataList.push(userSub);
            });
        });

        //站内信、微信、短信
        $(".web>div").click(function(){
            var bool=$(this).hasClass("layui-form-checked");
            if(bool){
                this.checked = true;
                $(this).next().addClass("layui-form-checked");
            }else{
                this.checked = false;
                $(this).next().removeClass("layui-form-checked");
            }
            var setId = $(this).parent().parent().attr("settingid");
            for(var index = 0; index<dataList.length;index++){
                if(setId != null && setId == dataList[index].settingId){
                    dataList[index].web = bool;
                }
            }
        });

        $(".wechat>div").click(function(){
            var bool=$(this).hasClass("layui-form-checked");
            if(bool){
                this.checked = true;
                $(this).next().addClass("layui-form-checked");
            }else{
                this.checked = false;
                $(this).next().removeClass("layui-form-checked");
            }
            var setId = $(this).parent().parent().attr("settingid");
            for(var index = 0; index<dataList.length;index++){
                if(setId != null && setId == dataList[index].settingId){
                    dataList[index].wechat = bool;
                }
            }
        });

        $(".msg>div").click(function(){
            var bool = $(this).hasClass("layui-form-checked");
            if(bool){
                this.checked = true;
                $(this).next().addClass("layui-form-checked");
            }else{
                this.checked = false;
                $(this).next().removeClass("layui-form-checked");
            }
            var setId = $(this).parent().parent().attr("settingid");
            for(var index = 0; index<dataList.length;index++){
                if(setId != null && setId == dataList[index].settingId){
                    dataList[index].message = bool;
                }
            }
        });

        $(".reset").click(function(){
            $.ajax({
                type: "POST",
                url: ctx + "/subscription/init",
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                abc.msg('消息订阅重置成功!',{icon: 1});
                                window.location.href = ctx+"/subscription/subscription.html";
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
            })
        })

        $(".confirm").click(function(){
            if(dataList != null && dataList.length >0){
                $.ajax({
                    type: "POST",
                    url: ctx + "/subscription/setSub",
                    data:JSON.stringify(dataList),
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg('消息订阅设置成功!',{icon: 1});
                                    window.location.href = ctx+"/subscription/subscription.html";
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
                })
            }else{
                abc.msg("请先标记订阅消息！",{icon:2});
            }
        });
    })
})
