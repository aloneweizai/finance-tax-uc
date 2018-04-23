require(["../config"], function ($) {
    require(["jquery.full",'jquery.base64.min', "jquery.jedate"], function ($) {
        function smrzDo(){
            var smrz = $("#smrz").val();
            if (smrz != '2') {
                abc.alert("您未实名认证,立即认证",{icon:2},function(){
                    window.location.href=ctx+"/userinfo/userinfolist.html#1_1";
                    parent.layer.closeAll();
                });
                return false;
            }
            return true;
        }
        $(function () {
            smrzDo();
        });

        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#test29'
            });
            laydate.render({
                elem: '#test30'
            });
        })
        function funLingqu(lotteryLogId,addressId,address,sendName,phone){
            if(lotteryLogId){

                $.ajax({
                    type: "POST",
                    data:  {lotteryLogId:lotteryLogId
                        ,addressId:addressId
                        ,address:address
                        ,phone:phone
                        ,sendName:sendName},
                    url: ctx+"/member/getLottery.html",
                    async: false,
                    dataType: 'JSON',
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                var result = data.result;
                                if (result.code == '2000') {
                                    abc.alert("领取成功，请到我的中奖记录查询！", { shade: 0.3, icon: 1, time: 2000});
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 2000);
                                } else {
                                    abc.msg( result.message,{icon:2});
                                }
                            } else {
                                abc.msg(data.message,{icon:2})
                            }
                        }else{
                            eval(data.js)
                        }

                    }
                });
            }

        }
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        $("#chaxun").on('click', function () {

          var datel =  $("#test29").val();
            var dater =  $("#test30").val();
            var url = ctx+"/member/prize.html"       ;
            if(datel){
                if(dater){
                    url =url + "?datel="  + datel  + "&dater=" +dater;

                }
            }
            window.location.href = url;
        });
            $('.layui-btn-mini').on('click', function () {
                if (!smrzDo())return ;
               var method = $(this).attr("data-method");
               var lotteryLogId = $(this).parent().attr("data-id");
               if (method=="abc"){
                   layer.open({
                       type: 2
                       , area:  ['500px', '380px']
                       , shade: 0//不显示遮罩
                       , title: '查看奖品信息'
                      // , offset: 1 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                       , id: 'layerDemo' + 1 //防止重复弹出
                       , content: ctx + "/member/prizeEdit.html?lotteryLogId=" + lotteryLogId
                       , btn: ['关闭']
                       , btnAlign: 'r' //按钮居中
                   });

               }else if (method=="virtual"){
                   funLingqu(lotteryLogId);

               }else if (method=="lingqu"){
                     $.get(ctx + "/member/prizeAddress.html?lotteryLogId=" + lotteryLogId,function (con){
                         layer.open({
                             type: 1
                             , area:['500px', '380px']
                             , shade: 0//不显示遮罩
                             , title: '查看奖品信息'
                             // , offset: 1 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                             , id: 'layerDemo' + 1 //防止重复弹出
                             , content: con
                             , btn: ['确定','关闭']
                             , btnAlign: 'r' //按钮居中
                             ,yes:function( ){
                                    var input = $('input:radio:checked');

                                 var tmp1 =input.val();
                                 var address =input.attr("addr");
                                 var name =input.attr("data-name");//收货人
                                 var phone = input.attr("phone");//收件人号码
                                 if (typeof(address) == "undefined") {
                                     abc.msg("请添加收货地址以便寄送您的奖品！",{icon:2});
                                     return;
                                 }
                                 funLingqu(lotteryLogId,tmp1,address,name,phone);
                                 layer.closeAll();
                             }

                         });
                     });

               }
            });
        });
    });
});
