//转盘抽奖

//获取用户今天抽奖次数
function drawFree(){
	$.get("userCount.php",function (iii){
		if(iii){
			var userFreeCount = $("#userFreeCount").val();
			if(userFreeCount){
               var oldii =  parseInt(userFreeCount);
               var newii = parseInt(iii);
                if(oldii > newii){
               	//当免费次数 大于 已经抽奖的次数
                   $("#userdayCount").html(  (oldii -newii)  );
			    }else{
                    $("#userMsg").html("");
                    $.get("getjf.php",function (jf) {
                        $("#userMsg").html("您当前可用积分：" + jf + "积分");
                    });
				}
			}

        }
    });
}

function closeJP1(){
    $('.jiangPinResult1').css("display","none");
}
$(function(){
    drawFree();
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
var probability=2;
var lotteryName = "";
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
        drawFree();
        if(lotteryName != ""){

            var div = $(".jiangPinResult");

            div.children("p").html(lotteryName);
            var td = $("." + "lottery-unit-" + probability);
            var src = td.children("img").attr("src");
            $(".imgJiangPin").attr("src",src);
            div.css("visibility","visible");
       }else{
            var div = $(".jiangPinResult1");
           // div.css("visibility","visible");
             div.fadeIn("slow");
             setTimeout(closeJP1,4444);
        }

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
function gogogo(){
    $.ajax({
        type: "post",
        url: "start.php",
        data: '',
        async: true,
        contentType: "application/json",
        dataType: "JSON",
        beforeSend: function () {
            layer.load();        //打开一个遮罩层，或者直接禁用你的按钮
        },
        complete: function () {
            layer.close(layer.load());     //取消遮罩层，或者回复按钮
        },
        success: function (data) {
            if (typeof(data.errorCode) == 'undefined') {
                if (data.code == '2000') {
                    var award = data.data;
                    probability = (award.index);
                    lottery.speed = 100;
                    if (award.luck) {
                        lotteryName = award.lotteryName;
                    } else {
                        lotteryName = "";
                    }
                    roll();
                    click = true;

                    return false;
                } else {
                    var msg =  data.message;
                    abc.msg(msg, {shade: 0.3, icon: 2, time: 2000});

                    //console.log("err:" + data.message);
                }
            } else {
                eval(data.js)
            }
        }
    });
}
window.onload=function(){
	lottery.init('lottery');
    $('.btn_Get1').click(fun1);
	$("#lottery a").click(fun1);
    function fun1(){
        if (click) {
            return false;
        }else{

            var div = $("#userdayCount").html();
            if (div){

                gogogo();
            }else{
                // 询问框
                abc.confirm('每次抽奖需要扣除200积分，请确认', {
                    btn: ['确认','取消'] //按钮
                    ,closeBtn: 0
                    , offset: '500px'
                }, function(){
                    //setTimeout(gogogo,50);
                    //layui-layer

                    gogogo();

                    abc.msg("开始");
                });

//                     layer.confirm('每次抽奖需要扣除200积分，请确认', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '500px', closeBtn: 0},
//                     function () {
//
// return true;
//                        // gogogo();
//
//                     });
            }

        }
    }
};
})
//转盘抽奖左侧上下轮播
$(function(){
    var i=0;
    var liH=$(".activity-content-left ul li").height()+11;
    var size=$(".activity-content-left ul li").size();
    var clone=$(".activity-content-left ul li").clone();
    $(".activity-content-left ul").append(clone);
    function topmove(){
        i++;
        if(i==size+1){
        $(".activity-content-left ul li").css({top:0})
            i=1
        }
        $(".activity-content-left ul li").stop().animate({top:i*-liH},1500)
    }
   $(".activity-content-left ul").hover(function(){
       clearInterval(t)
   },function(){
       var t=setInterval(function(){
        topmove()
    },1500);
   })
   var t=setInterval(function(){
        topmove()
    },1500);
    $('.jiangPinResult1').click(function(){
        closeJP1();
    })
    $('.jiangPinResult1 .colseJiangPin').click(function(){
        closeJP1();
    })



})
