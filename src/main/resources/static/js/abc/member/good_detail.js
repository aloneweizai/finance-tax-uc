require(["../../config"], function () {
    require(["jquery.full"], function ($) {
    $('#jifengoods').click(function () {
        var goodsid = $(this).attr("goods-id");
        var guigeid = $(this).attr("guige-id");
        var dizhi = $(".dizhi");
        var addressid = null;
        if(dizhi != null && dizhi.length != 0){
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
        abc.confirm("是否立即购买",{icon:3}, function () {
            $.ajax({
                type: "POST",
                url: ctx+'/shopBop/' + goodsid + "/" + guigeid,
                data:addressid,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (typeof(data.errorCode) == 'undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                var orderId = data.data.data.orderNo;
                                var el = document.createElement("a");
                                document.body.appendChild(el);
                                el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                el.target = '_new'; //指定在新窗口打开
                                el.click();
                                document.body.removeChild(el);
                            } else {
                                abc.msg(data.data.message,{icon: 2});
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    } else {
                        eval(data.js)
                    }
                }
            });
        });
        });

        $('#jifenduihuan').click(function () {
            var goodsid = $(this).attr("goods-id");
            var guigeid = $(this).attr("guige-id");
            var dizhi = $(".dizhi");
            var addressid = null;
            if(dizhi != null && dizhi.length != 0){
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
            abc.confirm("是否立即购买",{icon:3}, function () {
                $.ajax({
                    type: "POST",
                    url: ctx+'/shopBop/' + goodsid + "/" + guigeid,
                    data:addressid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (typeof(data.errorCode) == 'undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    var orderId = data.data.data.orderNo;
                                    var el = document.createElement("a");
                                    document.body.appendChild(el);
                                    el.href = ctx+ "/order_settlement/order_pay/"+orderId; //url 是你得到的连接
                                    el.target = '_new'; //指定在新窗口打开
                                    el.click();
                                    document.body.removeChild(el);
                                } else {
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        } else {
                            eval(data.js)
                        }
                    }
                });
            });
        });
    })
})