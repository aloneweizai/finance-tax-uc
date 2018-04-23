define(['jquery-3.1.0'],function(){
    var widH = $(window).height();
    var html ='';
    html+='<div class="top-div">';
    html+='<ul class="top-div-ul">';
    html+='<li class="top-div-top"><a href=""></a><i class="iconfont">&#xeb88;</i><p>返回顶部</p></li>';
    html+='<li >';
    html+='<a target="_blank" href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA2MzIwM180NzY5ODJfNDAwODg3MzEzM18yXw">';
    html+='<i class="iconfont">&#xe80a;</i>';
    html+='<p>在线客服</p>';
    html+='</a>';
    html+='</li>';
    html+='<li class="top-div-ewm2"><a href=""></a><i class="iconfont">&#xe80b;</i>';
    html+='<div class="top-div-ewm">';
    html+='<img src="'+ctx+'/images/code.jpg" alt="">';
    html+='</div>';
    html+='</li>';
    html+='<li >';
    html+='<a target="_blank" id="cbl_fwxf_a">';
    html+='<i class="iconfont">&#xe7e1;</i>';
    html+='<p>服务续费</p>';
    html+='</a>';
    html+='</li>';
    html+='<li class="clearfix"><a href="'+snsUrl+'/help/knowledge/index.html" target="_blank"><i class="iconfont icon-dengpao"></i><p>帮助中心</p></a></li>';
    html+='<li class="clearfix"><a href="javascript:void(0);" id="cbl_yjfk_a"><i class="iconfont icon-xiugai"></i><p>意见反馈</p></a></li>';
    html+='<li class="clearfix top-div-ss"><a ><i class="iconfont icon-guanbi2"></i></a></li>';
    html+='</ul>';
    html+='</div>';
    $('body').append(html)
    //财税网首页侧边栏
    $('.top-div-ewm2').hover(function(){
        $('.top-div-ewm').show();
    },function(){
        $('.top-div-ewm').hide();
    });
    $('.top-div ul li').hover(function(){

        $(this).find('p').css("left","-60px");

    },function(){
        $(this).find('p').css("left","50px");
    });
    $(document).scroll(function(){
        var WinST = $(this).scrollTop();
        if(WinST > widH){
            $('.top-div-top').css({opacity:1});
        }
        else{
            $('.top-div-top').css({opacity:0});
        }
    });
    var LSiz = $('.top-div-ul li').size();
    var LHig = $('.top-div ul li').outerHeight();
    var SSdiv = $('.top-div-ss').outerHeight();
    $('.top-div-ul').css({height:LSiz*LHig});
    $('.top-div-ss').click(function(){
        var ParH = $(this).parent('ul').outerHeight();
        if(ParH == (LSiz*LHig)){
            $(this).parent('.top-div-ul').css('height',SSdiv);
            $(this).find('i').addClass('icon-zuidahua1').removeClass(' icon-guanbi2')
        }else{
            $(this).parent('.top-div-ul').css('height',LSiz*LHig);
            $(this).find('i').addClass('icon-guanbi2').removeClass('icon-zuidahua1')
        }
        // $(this).siblings('li').animate({top:(LSiz - Ind) * Lhg})
    });
    $('.top-div-ewm').hover(function(){
        $(this).show();
    },function(){
        $(this).hide();
    })

    $('.top-div-top').click(function(){
        $(window).scrollTop(0)
    })


    $(document).scroll(function(){
        var WinST = $(this).scrollTop();
        if(WinST > widH){
            $('.top-div-top').css({opacity:1});
        }
        else{
            $('.top-div-top').css({opacity:0});
        }
    })
    //意见反馈
    $('#cbl_yjfk_a').click(function(){
        $.ajax({
            type: "POST",
            url: ctx+"/overtime.html",
            dataType: "JSON",
            async: false,
            success: function (data) {
                if(typeof(data.errorCode)=='undefined') {
                    if (typeof(data.soacode) == "undefined") {
                        if (data.data.code == "2000") {
                            window.open(snsUrl +"/help/feedback/toAdd.html");
                        } else {
                            $.close();
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
                    } else {
                        abc.msg(data.message,{icon: 2});
                    }
                }else{
                    eval(data.js)
                }
            }
        });


    });

    $('#cbl_fwxf_a').click(function(){
        $.ajax({
            url: ctx + "/toPayServiceCharge?random=" + Math.random(),
            type: "GET",
            success: function (result) {
                if (result.toUrl != null) {
                    window.open(result.toUrl);
                } else {
                    window.open("http://pay.abc12366.cn");
                }
            },
            error: function () {
                //layer.alert("出错了");
                window.open("http://pay.abc12366.cn");
            },
            dataType: "json"
        })
    });
})