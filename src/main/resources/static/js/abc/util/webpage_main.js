// 弹窗

function popup(){ 

var oPopup="<div class='grzx_popup_bj'><div class='przx_popup'><div class='przx_popup_t'>微信扫一扫<span onclick='popclose()'>X</span></div><div class='przx_popup_tit'><img src='images/code.jpg' style='width:100%'/></div></div></div>"
$("body").append(oPopup);
var oheight=document.body.clientHeight



}

function popclose(){
$('.grzx_popup_bj').css({
	display: 'none'
});
}


$(function(){
$(".grzx_main_rt_nav>ul>li").click(function(){
	$(this).addClass('hover').siblings('.hover').removeClass('hover');  
    $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
    
    

    //评价弹窗
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
            li.find("img").eq(i).attr("src", "images/" + stars[index][i]);//切换每个星星
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
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","images/x1.png");
        for (var i=0;i<index;i++){

            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","images/x2.png");
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
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","images/x1.png");
        for (var i=0;i<index;i++){
            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","images/x2.png");
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
            alert("感谢您的热情，您的输入已超出最大限度！" );
    	}else{
            $("#textCount").text(140-$("#TextArea1").val().length);
        }
    })
    
    
    
    
    
        
    
    
	
    
    
})


//个人中心切换iframe src地址

$(document).on('click','#dh_left>li',function(){
	var test=$(this).attr("name");
    	$(".uc_iframe").attr('src',test) 
})
$("#wdbb").click(function(){
	$("#bangbang").slideToggle();
})
$("#bangbang>dd").click(function(){
	var bangbang=$(this).attr("name");
	$(".uc_iframe").attr('src',bangbang)
})



//个人中心我的发票全选/取消全选
$("#checkall").click( 
  function(){ 
    if(this.checked){
        $("input[name='checkname']").attr('checked', true);
    }else{ 
        $("input[name='checkname']").attr('checked', false);
    } 
  } 
);


$(".anquan_click").click(function(){
	$(".bangdingnr").hide()
	$(this).parent().next().show()
})



/*订单支付*/

$(".clearfix a").click(function(){
	$(this).addClass('select').siblings().removeClass('select')
})


$('.pay_ways .way_alipay').on('click',function(){
    $('.pay_ways .way_weixin').css('background-position','0px -145px');
    $(this).css('background-position','-320px 0px');
})
$('.pay_ways .way_weixin').on('click',function(){
    $('.pay_ways .way_alipay').css('background-position','0px 0px');
    $(this).css('background-position','-191px -145px');
});
			

//积分金额选择

$(".jifen_jexz a").click(function(){
	$(this).addClass('select').siblings().removeClass('select')
})


$(".guige span").click(function(){
   		$(this).addClass('hover').siblings().removeClass('hover')
   })
 
    $(".grzx-btns button").click(function(){ 
    	 $(".kclbwg").eq($(this).index()).show().siblings(".kclbwg").hide(); 
   
    })
    $(".grzx_gridnav span").click(function(){
    	$(this).addClass('active').siblings().removeClass('active') 
    	$(".kclbwg1").eq($(this).index()).show().siblings(".kclbwg1").hide(); 
    	$(".kclbwg2").eq($(this).index()).show().siblings(".kclbwg2").hide(); 
    })
})

//活动抽奖
$(function(){
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

window.onload=function(){
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
};
})
//会员活动左侧上下轮播
$(function(){
    var i=0
    var size=$(".activity-content-left ul li").size()
    var clone=$(".activity-content-left ul li").clone()
    $(".activity-content-left ul").append(clone)

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
   })
   var t=setInterval(function(){
        topmove()
    },1500);



    
})

//iframe 高度
// 定义一个函数，定时调用并刷新iframe高度  
function reinitIframe(){  
var iframe = document.getElementById("external-frame");  
try{  
    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    var height = Math.max(bHeight, dHeight);  
    iframe.height = height;  
}catch (ex){
	
}
}  
  
var timer1 = window.setInterval("reinitIframe()", 500); //定时调用开始  



