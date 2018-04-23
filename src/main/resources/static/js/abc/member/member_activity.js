require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $(function () {
        //
        //    $("body").on("click",".js_close_modal",function(){
        //        $(".close").click();
        //    });
        //
        //    /* 初始化中奖名单 */
        //    var init_winners = function(){
        //        $.ajax({
        //            type: "get",
        //            url: ctx+"/member/lottery/winners.php",
        //            data: '',
        //            async: true,
        //            contentType: "application/json",
        //            dataType: "JSON",
        //            success: function (data) {
        //                if(typeof(data.errorCode)=='undefined') {
        //                    if (data.code == '2000') {
        //                        var html = '';
        //                        $.each(data.dataList, function (i, item) {
        //                            var image = item.userPicturePath;
        //                            if (!image) {
        //                                image = ctx + '/images/icon_zx_03.png';
        //                            } else {
        //                                image = imgPth + image;
        //                            }
        //                            html = html + '<li style="top: -406.86px;">' +
        //                                '<span><img alt="" onerror="this.src="' + ctx + '"/images/icon_zx_03.png"  src="' + image + '">' + item.username + '</span>' +
        //                                '<span>' + item.lotteryName + '</span>' +
        //                                '</li>';
        //                        });
        //                        $("#winners_ul").html(html);
        //                    }
        //                }else{
        //                    eval(data.js)
        //                }
        //            }
        //        });
        //    };
        //    init_winners();
        //
        //    var lottery={
        //        index:-1,	//当前转动到哪个位置，起点位置
        //        count:0,	//总共有多少个位置
        //        timer:0,	//setTimeout的ID，用clearTimeout清除
        //        speed:20,	//初始转动速度
        //        times:0,	//转动次数
        //        cycle:20,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
        //        prize:-1,	//中奖位置
        //        notluck:1,
        //        init:function(id){
        //            if ($("#"+id).find(".lottery-unit").length>0) {
        //                $lottery = $("#"+id);
        //                $units = $lottery.find(".lottery-unit");
        //                this.obj = $lottery;
        //                this.count = $units.length;
        //                $lottery.find(".lottery-unit-"+this.index).addClass("active");
        //            };
        //        },
        //        roll:function(){
        //            var index = this.index;
        //            var count = this.count;
        //            var lottery = this.obj;
        //            $(lottery).find(".lottery-unit-"+index).removeClass("active");
        //            index += 1;
        //            if (index>count-1) {
        //                index = 0;
        //            };
        //            $(lottery).find(".lottery-unit-"+index).addClass("active");
        //            this.index=index;
        //            return false;
        //        },
        //        stop:function(index){
        //            this.prize=index;
        //            return false;
        //        }
        //    };
        //    var award_index = -1;
        //    var award_index = -1;
        //    $(function (){
        //        lottery.init('lottery');
        //        $("#lottery a").click(function(){
        //            if (click) {
        //                return false;
        //            }else{
        //                $.ajax({
        //                    type: "post",
        //                    url: ctx+"/member/lottery/start.php",
        //                    data: '',
        //                    async: true,
        //                    contentType: "application/json",
        //                    dataType: "JSON",
        //                    success: function (data) {
        //                        if(typeof(data.errorCode)=='undefined') {
        //                            var award = data.data;
        //                            award_index = (award.orderId - 1);
        //                            lottery.speed = 100;
        //                            lottery.notluck = award.notluck;
        //                            roll();
        //                            click = true;
        //                            return false;
        //                        }else{
        //                            eval(data.js)
        //                        }
        //                    }
        //                });
        //            }
        //        });
        //    });
        //    function roll(){
        //        lottery.times += 1;
        //        lottery.roll();
        //        lottery.prize= award_index;
        //        if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
        //            clearTimeout(lottery.timer);
        //            lottery.prize=-1;
        //            lottery.times=0;
        //            click=false;
        //            /* 如果中奖 */
        //            if(lottery.notluck == 0){
        //                lottery.notluck == 1;
        //                $("#winner_modal").modal("show");
        //            }
        //        }else{
        //            if (lottery.times<lottery.cycle) {
        //                lottery.speed -= 10;
        //            }else if(lottery.times==lottery.cycle) {
        //                var index = Math.random()*(lottery.count)|0;
        //                lottery.prize = index;
        //            }else{
        //                if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
        //                    lottery.speed += 110;
        //                }else{
        //                    lottery.speed += 20;
        //                }
        //            }
        //            if (lottery.speed<40) {
        //                lottery.speed=40;
        //            };
        //            lottery.timer = setTimeout(roll,lottery.speed);
        //        }
        //        return false;
        //    }
        //    var click=false;
        //

            //活动抽奖
            var lottery={
                index:-1,	//当前转动到哪个位置，起点位置
                count:0,	//总共有多少个位置
                timer:0,	//setTimeout的ID，用clearTimeout清除
                speed:20,	//初始转动速度
                times:0,	//转动次数
                cycle:20,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
                prize:-1,	//中奖位置
                init:function(id){
                    if ($("#"+id).find(".lottery-unit").length>0) {
                        $lottery = $("#"+id);
                        $units = $lottery.find(".lottery-unit");
                        this.obj = $lottery;
                        this.count = $units.length;
                        $lottery.find(".lottery-unit-"+this.index).addClass("active");
                    };
                },
                roll:function(){
                    var index = this.index;
                    var count = this.count;
                    var lottery = this.obj;
                    $(lottery).find(".lottery-unit-"+index).removeClass("active");
                    index += 1;
                    if (index>count-1) {
                        index = 0;
                    };
                    $(lottery).find(".lottery-unit-"+index).addClass("active");
                    this.index=index;
                    return false;
                },
                stop:function(index){
                    this.prize=index;
                    return false;
                }
            };
            var probability=1;
            function roll(){
                lottery.times += 1;
                lottery.roll();
                lottery.prize=probability;
                if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
                    clearTimeout(lottery.timer);
                    //console.log(lottery.prize)
                    lottery.prize=-1;
                    lottery.times=0;
                    click=false;
                }else{
                    if (lottery.times<lottery.cycle) {
                        lottery.speed -= 10;
                    }else if(lottery.times==lottery.cycle) {
                        var index = Math.random()*(lottery.count)|0;
                        lottery.prize = index;
                    }else{
                        if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
                            lottery.speed += 110;
                        }else{
                            lottery.speed += 20;
                        }
                    }
                    if (lottery.speed<40) {
                        lottery.speed=40;
                    };
                    //console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
                    lottery.timer = setTimeout(roll,lottery.speed);
                }
                return false;
            }

            var click=false;


                lottery.init('lottery');
                $("#lottery a").click(function(){
                    if (click) {
                        return false;
                    }else{
                        lottery.speed=100;
                        roll();
                        click=true;
                        return false;
                    }
                });
        });

        //会员活动左侧上下轮播
        $(function(){
            var i=0;
            var size=$(".activity-content-left ul li").length;
            var clone=$(".activity-content-left ul li").clone();
            $(".activity-content-left ul").append(clone);

            function topmove(){
                i++;
                var index= $(".activity-content-left ul li").index()
                if(i==size+1){
                    $(".activity-content-left ul li").css({top:0})
                    i=1
                }
                $(".activity-content-left ul li").stop().animate({top:i*-51},1500)
            }
            $(".activity-content-left ul").hover(function(){
                clearInterval(t)
            },function(){
                var t=setInterval(function(){
                    topmove()
                },1500);
            });
            var t=setInterval(function(){
                topmove()
            },1500);
        });
    })
});