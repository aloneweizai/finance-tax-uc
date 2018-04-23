require(["../../config"], function () {
    require(["jquery.full","qrcode"], function ($,qrCode) {
            var orderid=$('#orderid').val();
            $.ajax({
                type: "POST",
                url: ctx+ '/memberOrder/'+orderid,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                var qrcode = new qrCode($("#zhifubao").get(0), {
                                    text: data.zhifubao,
                                    width: 100,
                                    height: 100,
                                    correctLevel: qrCode.CorrectLevel.H
                                });
                                var qrcode1 = new qrCode($("#weixin").get(0), {
                                    text: data.weixin,
                                    width: 100,
                                    height: 100,
                                    correctLevel: qrCode.CorrectLevel.H
                                });
                            } else {
                                abc.msg(data.data.message,{icon: 2});
                            }
                        } else {
                            abc.msg(data.massage,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
        })
    })
})