require(["../config"],function($){
    require(["jquery.full"],function($) {

        $('#upddizhis').click(function (){
            abc.confirm('是否修改地址？',{icon:3}, function(){
                window.location.href=ctx+"/userinfo/useraddr.html";
                parent.layer.closeAll();
            });
        });

        $('.fwxz').click(function(){
            var val = $(this).find('input[name="exchangeType"]').val();
            if(val == "1"){
                $('div[class="dizhi"]').show();
            }else{
                $('div[class="dizhi"]').hide();
            }

        });

        /**退换货申请*/
        $("#backReplace").click(function (){
            var orderNo = $('#orderNo').text();
            var type = $("input[name='exchangeType']:checked").val();
            var reason = $('#backReason option:selected').val();
            var remark = $('#backDescribe').val();
            //debugger;
            var addressid = null;
            if(type =="1"){
                var address=$('input:radio[name="addressId"]');
                if(address.length==0){
                    abc.msg('请先维护收货地址!',{icon: 2});
                    return ;
                }
                addressid=$('input:radio[name="addressId"]:checked').val();
                if(typeof(addressid)=="undefined"){
                    abc.msg('请选择收货地址!',{icon: 2});
                    return ;
                }
            }
            var orderBack = {type:type,orderNo:orderNo,reason:reason,addressId:addressid,userRemark:remark}
            abc.confirm('是否申请退换货？',{icon:3}, function(){
                $.ajax({
                    type: "POST",
                    url: ctx + "/orderback/back_replace/submit",
                    data: JSON.stringify(orderBack),
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if(data.data.code=='2000'){
                                    abc.msg('申请成功',{icon: 1},function (){
                                        window.location.href=ctx + "/orderback/back.php";
                                    });
                                }else{
                                    abc.alert(data.data.message,{icon: 2});
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
        });

        var v1={
            theme: 'simple_right',
            stopOnError: true,
            timely: 1,
            fields: {
                "orderNo": {
                    rule: "订单号:required"
                },
                "exchangeType": {
                    rule: "类型:required"
                },
                "reason":{
                    rule:"退换货原因:required"
                },
                "userRemark":{
                    rule: "备注:"
                }
            }
        };

        $('#fanhui').click(function (){
            window.location.href=ctx + "/orderback/back.php";
        });

        //换货申请
        /* $('#backReplace').click(function (){
            debugger;
            var first=$('#first').val();
            var second=$('#second').val();
            var third=$('#third').val();
            if(first==''){
                layer.msg('请选择上传要退换的第一张货物图片');
                return ;
            }
            if(second==''){
                layer.msg('请选择上传要退换的第二张货物图片');
                return ;
            }
            if(third==''){
                layer.msg('请选择上传要退换的第三张货物图片');
                return ;
            }
            $('#shengqin').validator('destroy');
            var $form2 = $('#shengqin').validator(v1);

            $form2.isValid(function (v) {
                if (v) {
                    parent.$.pop();
                    $.ajax({
                        url: ctx+"/orderback/upload.html?path=replace",
                        type: 'POST',
                        cache: false,
                        data: new FormData($('#shengqin')[0]),
                        processData: false,
                        contentType: false
                    }).done(function(res) {
                        parent.$.close();
                        if(typeof(res.errorCode)=='undefined') {
                            if (typeof(res.soacode) == "undefined") {
                                if (res.data.code == '2000') {
                                    layer.msg('申请成功，请耐心等待审核');
                                    setTimeout(function () {
                                            window.location.href = ctx + "/orderback/back.php";
                                        }
                                        , 1000)
                                } else {
                                    layer.msg(res.data.message);
                                }
                            } else {
                                layer.msg(res.message);
                            }
                        }else{
                            eval(res.js)
                        }
                    }).fail(function(res) {});
                }
            });

        });*/
    })
})