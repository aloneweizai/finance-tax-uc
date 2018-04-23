require(["../config"], function () {
    require(["jquery.full"], function ($) {
        // var snsurl="http://testsns.abc12366.com/ABCSNS";
        // var snsurl="http://118.118.116.132:8087/ABCSNS";
        $(".grzx_gridnav span").click(function(){
            $(this).addClass('active').siblings().removeClass('active')
            $(".kclbwg1").eq($(this).index()).show().siblings(".kclbwg1").hide();
            $(".kclbwg2").eq($(this).index()).show().siblings(".kclbwg2").hide();
        })

        $(".grzx-btns button").click(function(){
            $(".kclbwg").eq($(this).index()).show().siblings(".kclbwg").hide();
        })

        $('a[kcxx="kcxx"]').click(function (){
            var kcid=$(this).attr("kc-id");
            var kjid=$(this).attr("kj-id");
            var teachingMethod=$(this).attr("teachingMethod");
            if(teachingMethod=='recording'){
                var a=document.createElement('a');
                document.body.appendChild(a);
                a.href = snsurl+"/school/video/"+kcid+"/"+kjid; //url 是你得到的连接
                a.target="_new";
                a.click();
                document.body.removeChild(a);
            }else{
                var a=document.createElement('a');
                document.body.appendChild(a);
                a.href = snsurl+"/school/details/"+kcid; //url 是你得到的连接
                a.target="_new";
                a.click();
                document.body.removeChild(a);
            }
        });

        $('span[kcxx="kcxx"]').click(function (){
            var kcid=$(this).attr("kc-id");
            var kjid=$(this).attr("kj-id");
            var teachingMethod=$(this).attr("teachingMethod");
            if(teachingMethod=='recording'){
                var a=document.createElement('a');
                document.body.appendChild(a);
                a.href = snsurl+"/school/video/"+kcid+"/"+kjid; //url 是你得到的连接
                a.target="_new";
                a.click();
                document.body.removeChild(a);
            }else{
                var a=document.createElement('a');
                document.body.appendChild(a);
                a.href = snsurl+"/school/details/"+kcid; //url 是你得到的连接
                a.target="_new";
                a.click();
                document.body.removeChild(a);
            }
        });

        var width_1 =document.documentElement.clientWidth-100;
        var height_1 =document.documentElement.clientHeight-50;

        $('#addpj').click(function (){
            var index=$('#xping').attr("data-default-index");
            if(parseInt(index)==0){
                abc.msg("请给出星评!",{offset:[(height_1/2)+'px',(width_1/2)+'px'],icon: 2});
                return ;
            }
            var kcid=$('#kcid').val();
            var TextArea1=$('#TextArea1').val();
            if(TextArea1==''){
                abc.msg("请填写评价信息!",{offset:[(height_1/2)+'px',(width_1/2)+'px'],icon: 2})
                return;
            }
            $.ajax({
                type: "POST",
                url: ctx + '/school/pj/userpj.html',
                dataType: "JSON",
                data:{curriculumId:kcid,grade:index,studyFeel:TextArea1},
                success: function (data) {
                    if(data.data.code=='2000'){
                        $('#myModal').modal('hide');
                        abc.msg("评价成功!",{offset:[(height_1/2)+'px',(width_1/2)+'px'],icon:1})
                        location.reload()
                    }else{
                        if(data.data.code!='002'){
                            abc.msg(data.data.message,{offset:[(height_1/2)+'px',(width_1/2)+'px'],icon:2})
                        }
                    }
                }
            });
        });


        $('a[data-pj="pj"]').click(function (){
            var kcid=$(this).attr("kc-id");
            $('#kcid').val(kcid);
            $('#myModal').modal({backdrop: 'static', keyboard: false});
        });
        $('span[data-pj="pj"]').click(function (){
            var kcid=$(this).attr("kc-id");
            $('#kcid').val(kcid);
            $('#myModal').modal({backdrop: 'static', keyboard: false});
        });



        function byIndexLeve(index){
            var str ="";
            switch (index)
            {
                case 0:
                    str="差评";
                    break;
                case 1:
                    str="较差";
                    break;
                case 2:
                    str="中等";
                    break;
                case 3:
                    str="一般";
                    break;
                case 4:
                    str="好评";
                    break;
            }
            return str;
        }
        //  星星数量
        var stars = [
            ['x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
            ['x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png'],
            ['x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png'],
            ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png'],
            ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png'],
        ];
        $(".block li").find("img").hover(function(e) {
            var obj = $(this);
            var index = obj.index();
            if(index < (parseInt($(".block li").attr("data-default-index")) -1)){
                return ;
            }
            var li = obj.closest("li");
            var star_area_index = li.index();
            for (var i = 0; i < 5; i++) {
                li.find("img").eq(i).attr("src", ctx+"/images/" + stars[index][i]);//切换每个星星
            }
            $(".level").html(byIndexLeve(index));
        }, function() {
        })

        $(".block li").hover(function(e) {
        }, function() {
            var index = $(this).attr("data-default-index");//点击后的索引
            index = parseInt(index);
            //console.log("index",index);
            $(".level").html(byIndexLeve(index-1));
            //console.log(byIndexLeve(index-1));
            $(".order-evaluation ul li:eq(0)").find("img").attr("src",ctx+"/images/x1.png");
            for (var i=0;i<index;i++){

                $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src",ctx+"/images/x2.png");
            }
        })
        $(".block li").find("img").click(function() {
            var obj = $(this);
            var li = obj.closest("li");
            var star_area_index = li.index();
            var index1 = obj.index();
            li.attr("data-default-index", (parseInt(index1)+1));
            var index = $(".block li").attr("data-default-index");//点击后的索引
            index = parseInt(index);
            //console.log("index",index);
            $(".level").html(byIndexLeve(index-1));
            //console.log(byIndexLeve(index-1));
            $(".order-evaluation ul li:eq(0)").find("img").attr("src",ctx+"/images/x1.png");
            for (var i=0;i<index;i++){
                $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src",ctx+"/images/x2.png");
            }

        });
        //印象
        $(".order-evaluation-check").click(function(){
            if($(this).hasClass('checked')){
                //当前为选中状态，需要取消
                $(this).removeClass('checked');
            }else{
                //当前未选中，需要增加选中
                $(this).addClass('checked');
            }
        });
        //评价字数限制
        $("#TextArea1").keydown(function(){
            var curLength=$(this).val().length;
            if(curLength>140){
                var num=$(this).val().substr(0,140);
                $("#TextArea1").val(num);
                abc.msg("感谢您的热情，您的输入已超出最大限度！",{icon:2})
                // alert("感谢您的热情，您的输入已超出最大限度！" );
            }else{
                $("#textCount").text(140-$("#TextArea1").val().length);
            }
        })

        $(".unsubscribe").click(function(){
            var ltId = $(this).attr("lect-id");
            $.ajax({
                type: "POST",
                url: ctx + '/lecturer/unsubscribe?id='+ltId,
                dataType: "JSON",
                contentType: "application/json",
                success: function (data) {
                    if(data.data.code=='2000'){
                        abc.msg("取消关注成功!",{icon:1});
                        window.location.href=ctx + "/school/list.html"
                    }else{
                        if(data.data.code!='002'){
                            abc.msg(data.data.message,{icon:2});
                        }
                    }
                }
            });
        })

        $(".js_js").hover(function(){
            var intro = $(this).attr("intro");
            if(intro != null && intro.length >50){
                layer.tips(intro,this,{
                    tips: [1, '#00bcd4'],
                    time: 4000
                });
            }
        },function(){
            layer.closeAll();
        })

        //过滤老师介绍含有的标签
        $(function(){
            $(".js_js").each(function(){
                var intro = $(this).attr("intro");
                var p = new RegExp("<[^>]*>","gi");
                var textStr = intro.replace(p,"");
                if(typeof (textStr)!= "undefined" && textStr != null && textStr.length >56 ){
                    $(this).text(textStr.substring(0,56)+"..");
                }else{
                    $(this).text(textStr);
                }
            })
        })

    })
})
