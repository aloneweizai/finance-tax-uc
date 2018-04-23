require(["../config"], function () {
    require(["jquery.full"], function ($) {
       $(function (){
            $(".grzx_main_rt_nav>ul>li").click(function(){
                $(this).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
            })

           $(".xtfr").each(function(){
               var content =$(this).attr("content");
               var str =$(this).attr("msg-url");
               var con = content;
               if(str != null && str !=""){
                   var p = new RegExp("<a[^>]*>([^<]*)</a>","gi");
                   var match = p.exec(str);
                   if(match !=null){
                       con = content + match[1];
                   }
               }
               var len = con.match(/[^ -~]/g) == null ? con.length : con.length + con.match(/[^ -~]/g).length ;
               if(len>94){
                   $(this).attr("style","visibility:visible");
               }else{
                   $(this).attr("style","visibility:hidden");
               }

           });
        });

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


        function readPage(){
            var parameter = getChecked();
            if(parameter.length <= 0){
                abc.msg('请勾选需要标记的记录', {icon: 2});
                return;
            }
            var type = $("#pageRead").attr("msg-type");
            parameter += "&type="+type;
            abc.confirm('是否标记当前页消息已读？',{icon:3},function() {
                $.ajax({
                    type:'POST',
                    url:ctx + "/userinfo/message/readPage",
                    data :parameter,
                    beforeSend: function () {
                        layer.load();        //打开一个遮罩层，或者直接禁用你的按钮
                    },
                    complete: function () {
                        layer.close(layer.load());     //取消遮罩层，或者回复按钮
                    },
                    success : function(data){
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg("操作成功", {icon: 1});
                                    counts();
                                    window.location.href = ctx + '/userinfo/user_message.html?index=1&type=' + type;
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            })
        }

        function readAll(){
            abc.confirm('是否标记所有消息已读？', {icon:3},function() {
                var type = $("#allRead").attr("msg-type");
                $.ajax({
                    type: "POST",
                    url: ctx + "/userinfo/message/readAll?type=" + type,
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    beforeSend: function () {
                        layer.load();        //打开一个遮罩层，或者直接禁用你的按钮
                    },
                    complete: function () {
                        layer.close(layer.load());     //取消遮罩层，或者回复按钮
                    },
                    success : function(data){
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg("操作成功", {icon: 1});
                                    counts();
                                    window.location.href = ctx + '/userinfo/user_message.html?index=1&type=' + type;
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
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
        }

        $('#pageRead').click(function(){
            readPage();
        });

        $('#allRead').click(function(){
            readAll();
        });

        $("body").on("click",".js_selectAll",function(){
            var oselectall = $('.js_checkbox');
            var bool=false;
            oselectall.each(function() {
                if (this.checked == false) {
                    bool=true;
                }
            })
            if(bool){
                oselectall.each(function() {
                    this.checked = true;
                    $(this).next().addClass("layui-form-checked");
                })
            }else{
                oselectall.each(function() {
                    this.checked = false;
                    $(this).next().removeClass("layui-form-checked");
                })
            }
        });


       //获取选择的checkbox
        function getChecked(){
            var parameter = "";
            $(".js_checkbox").each(function(){
                if($(this).is(":checked")){
                    parameter += "msgId="+$(this).val()+"&";
                }
            });
            if(parameter.length > 0){
                parameter = parameter.substring(0, parameter.length-1)
            }
            return parameter;
        }


        var pagetools=layui.laypage;
        $(function (){
            var index=$('#demo').attr("index");
            if(index!=0){
                fenye("fanye");
            }
        })

        function fenye(id){
            var count=$('#demo').attr("count");
            var index=$('#demo').attr("index");
            var type=$('#demo').attr("msg-type")
            //分页
            pagetools.render({
                elem: 'demo' //分页容器的id
                ,count: count //总页数
                ,limit:10
                ,curr:index
                ,skin: '#1E9FFF' //自定义选中色值
                //,skip: true //开启跳页
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        window.location.href = ctx + "/userinfo/user_message.html?index="+obj.curr+"&type="+type;
                    }
                }
            });
        }

        //消息标记为已读
        function readMsg(con){
            // debugger;
            var $this = con;
            var message_id = $this.attr("message-id");
            var msgstatus = $this.attr("msgstatus");
            var type = $this.attr("msg-type");
            if (msgstatus == "1") {
                $.ajax({
                    type:"POST",
                    url:ctx+ "/userinfo/bsMessage/" + message_id,
                    async:false,
                    contentType:"application/json",
                    dataType:"JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                // debugger;
                                if (data.data.code == '2000') {
                                    counts();
                                    window.location.href = ctx + '/userinfo/user_message.html?index=1&type=' + type;
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }

                })
            }
        }

        function counts(){
            $.ajax({
                url: ctx+"/message_count_index.html",
                type: "get",
                dataType: 'html',
                success: function(re) {
                    $('#messagecount',parent.document).html(re);
                }
            });
        }
        //UC个人中心 我的消息查看全部
        $(document).on('click','.xtfr',function(){

            if($(this).siblings("h4").hasClass('danhang')){
                $(this).siblings("h4").removeClass('danhang');
                $(this).text('【收起】');
            }else{
                $(this).siblings("h4").addClass('danhang');
                $(this).text('【查看全部】');
            }

        });

        $(".bsMessage").one("click",function(){
            var con = $(this);
            readMsg(con);
        });

        $(".links").one("click",function(){
            var con = $(this);
            readMsg(con);
        });

    })
})