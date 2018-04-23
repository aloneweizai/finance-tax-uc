require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $(".jifen_jexz a").click(function(){
            $(this).addClass('select').siblings().removeClass('select')
            $('#submit_pay_button').attr("jine",$(this).attr("jine"));
            $('#submit_pay_button').attr("guigeid",$(this).attr("guigeid"));
            var jine=parseInt($(this).attr("jine"));
            if(jine<=0){
                $('#ryje').show();
                //$('#jine').removeAttr("disabled");
            }else{
                $('#ryje').hide();
                //$('#jine').attr("disabled","true");
                $('#jine').val('');
            }
        })

        $(function (){
            $('#jine').val('');
        })

        $('#submit_pay_button').click(function (){
            var bjine=$(this).attr("jine");
            var jine=$('#jine').val();
            if(bjine=='0'){
                if(jine==''||parseFloat(jine)<=0){
                    abc.msg("请输入充值金额!",{icon: 2})
                    return ;
                }
            }
            if(parseFloat(jine)>0){
                var guige=$('#jine').attr("guigeid");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = ctx+"/memberOrder/jf_details/"+guige+"?jine="+jine; //url 是你得到的连接
                el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
                //window.location.href=ctx+"/memberOrder/jf_details/"+guige+"?jine="+jine;
                // var zongjifen=parseFloat(jine).toFixed(2)*1000;
                // abc.confirm("充值 ￥"+jine+"元，兑换积分:"+Math.floor(zongjifen)+"分!",{icon:3}, function(){
                //     window.parent.$.pop();
                //     $.ajax({
                //         type: "POST",
                //         url: ctx+ '/memberOrder/integral_order.html',
                //         data: {jine:jine,guige:guige},
                //         dataType: "JSON",
                //         success: function (data) {
                //             if(typeof(data.errorCode)=='undefined') {
                //                 if (typeof(data.soacode) == "undefined") {
                //                     if (data.data.code == '2000') {
                //                         window.parent.$.close()
                //                         var orderId = data.orderId;
                //                         window.location.href = ctx + "/member/pay/" +  orderId;
                //                     } else {
                //                         window.parent.$.close();
                //                         abc.msg(data.data.message,{icon: 2});
                //                     }
                //                 } else {
                //                     window.parent.$.close()
                //                     abc.msg(data.message,{icon: 2});
                //                 }
                //             }else{
                //                 eval(data.js)
                //             }
                //         }
                //     });
                // });
            }else{
                var jine=$(this).attr("jine");
                var guige=$(this).attr("guigeid");
                var el = document.createElement("a");
                document.body.appendChild(el);
                el.href = ctx+"/memberOrder/jf_details/"+guige+"?jine="+jine; //url 是你得到的连接
                el.target = '_new'; //指定在新窗口打开
                el.click();
                document.body.removeChild(el);
                // var zongjifen=parseFloat(jine).toFixed(2)*1000;
                // abc.confirm("充值 ￥"+jine+"元,积分:"+Math.floor(zongjifen)+"分!",{icon:3}, function(){
                //     $.post(ctx+ '/memberOrder/integral_order.html',{jine:jine,guige:guige},function (data){
                //         if(typeof(data.soacode)=="undefined"){
                //             if(data.data.code=='2000'){
                //                 var orderId=data.orderId;
                //                 window.location.href=ctx+"/member/pay/"+orderId;
                //             }else{
                //                 abc.msg(data.data.message,{icon: 2});
                //             }
                //         }else{
                //             abc.msg(data.message,{icon: 2});
                //         }
                //     });
                // });
            }
        });
    })
})

