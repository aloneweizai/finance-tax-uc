require(["../../config"], function () {
    require(["jquery.full","jquery.md5","jquery.base64.min"], function ($) {

        $('#submit').click(function (){
            $(this).attr("disabled","disabled");
            var nsrsbh=$('#nsrsbh').val();
            var nsqymc=$('#nsqymc').val();
            if(nsrsbh==''){
                layer.msg('请输入纳税人识别号！',{icon:2});
                $(this).removeAttr("disabled");
                return;
            }
            if(nsqymc==''){
                layer.msg('请输入纳税企业名称！',{icon:2});
                $(this).removeAttr("disabled");
                return;
            }
            var this_=$(this);
            var value=$('input[name="type"]:checked').val();
            if(value==1){
                $.ajax({
                    //url: ctx + "/toPayServiceCharge?random=" + Math.random()+"&nsrsbh="+nsrsbh,
                    url: ctx + "/company/toPayServiceCharge?random=" + Math.random()+"&nsrsbh="+nsrsbh,
                    type: "GET",
                    success: function (result) {
                        if (result.toUrl != null) {
                            //window.open(result.toUrl);
                            var el = document.createElement("a");
                            document.body.appendChild(el);
                            el.href = result.toUrl; //url 是你得到的连接
                            el.target = '_new'; //指定在新窗口打开
                            el.click();
                            document.body.removeChild(el);
                            this_.removeAttr("disabled");
                        } else {
                            //window.open("http://pay.abc12366.cn");
                            var el = document.createElement("a");
                            document.body.appendChild(el);
                            el.href = "http://pay.abc12366.cn"; //url 是你得到的连接
                            el.target = '_new'; //指定在新窗口打开
                            el.click();
                            document.body.removeChild(el);
                            this_.removeAttr("disabled");
                        }
                    },
                    error: function () {
                        //layer.alert("出错了");
                        //window.open("http://pay.abc12366.cn");
                        var el = document.createElement("a");
                        document.body.appendChild(el);
                        el.href = "http://pay.abc12366.cn"; //url 是你得到的连接
                        el.target = '_new'; //指定在新窗口打开
                        el.click();
                        document.body.removeChild(el);
                        this_.removeAttr("disabled");
                    },
                    dataType: "json"
                })
            }else if(value==2){
                var json={nsrsbh:nsrsbh,nsrqymc:nsqymc};
                var data=JSON.stringify(json);
                $.ajax({
                    type:'POST',
                    url: ctx+"/company/save.html" ,
                    data:data,
                    contentType: "application/json",
                    success: function (data){
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg( data.data.message,{icon:1},function (){
                                        window.loc.CloseBrowser();
                                    });
                                } else {
                                    abc.msg( data.data.message,{icon:2});
                                    this_.removeAttr("disabled");
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                                this_.removeAttr("disabled");
                            }
                        }else{
                            this_.removeAttr("disabled");
                            eval(data.js)
                        }
                    } ,
                    dataType: "JSON"
                });
            }
        });
        $('#dxan i').click(function (){
            var index=$('#dxan i').index(this);
            if(index==0){
                $('input[name="type"]').eq(1).removeAttr("checked");
                $('input[name="type"]').eq(0).attr("checked","true");
            }else if(index==1){
                $('input[name="type"]').eq(0).removeAttr("checked");
                $('input[name="type"]').eq(1).attr("checked","true");
            }
        });

    });
});