require(["../config"], function () {
    require(["jquery.full"], function ($) {
        //数据校验
        var $validatorWsysVoFrom = $("form").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                phone:[/^1[0-9]{10}$/,"手机号不正确"]
            },
            fields: {
                "name": {
                    rule:"姓名:required;length(2~5)"
                },
                "phone": {
                    rule:"手机号:required;phone;"
                },
                "province": {
                    rule:"省份:required"
                },
                "city":{
                    rule:"市:required"
                },
                "area":{
                    rule:"区县:required"
                },
                "detail": {
                    rule:"详细地址:required;length(1~30)"
                }
            }
        });

        $('#tijiao').click(function (){
            if($validatorWsysVoFrom.isValid()){
                parent.$.pop();
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/save.html",
                    data:$('#dizhiform').serializeJson(),
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    var title=$('#title').text();
                                    abc.msg(title+'成功',{icon: 1},function (){
                                        window.location.href = ctx+"/userinfo/useraddr.html";
                                    });
                                } else {
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
            }
        });
        $(function (){
            var s_province=$("#s_province").attr("data-value");
            $.ajax({
                type: "GET",
                url: ctx+"/user/region/province/list.html",
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.list) {
                                $.each(data.list, function (i, item) {
                                    $("#s_province").append("<option value='" + item.provinceId + "'>" + item.province + "</option>");
                                });
                                $('#s_province').val(s_province);
                            } else {
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            });
        })

        $('#s_province').change(function (){
            var pid=$(this).val();
            if(pid!=''){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/city/list.html?pid="+pid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                $("#s_city").html("<option value=''>请选择</option>");
                                $("#s_county").html("<option value=''>请选择</option>");
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#s_city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                    });
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else{
                $("#s_city").html("<option value=''>请选择</option>");
                $("#s_county").html("<option value=''>请选择</option>");
            }
        });

        $('#s_city').change(function (){
            var pid=$(this).val();
            if(pid!=''){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/county/list.html?pid="+pid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                $("#s_county").html("<option value=''>请选择</option>");
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#s_county").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                    });
                                } else {
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }else{
                $("#s_county").html("<option value=''>请选择</option>");
            }
        });


        $('#fanhui').click(function (){
            window.location.href= ctx + "/userinfo/useraddr.html";
        });

        $(function (){
            var s_province=$("#s_province").attr("data-value");
            var s_city=$("#s_city").attr("data-value");
            var s_county=$("#s_county").attr("data-value");
            if(s_province!=''){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/city/list.html?pid="+s_province,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#s_city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                    });
                                    $("#s_city").val(s_city);
                                    $.ajax({
                                        type: "GET",
                                        url: ctx+"/user/region/county/list.html?pid=" + s_city,
                                        contentType: "application/json",
                                        dataType: "JSON",
                                        success: function (data) {
                                            if(typeof(data.errorCode)=='undefined') {
                                                if (typeof(data.soacode) == "undefined") {
                                                    if (data.list) {
                                                        $.each(data.list, function (i, item) {
                                                            $("#s_county").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                                        });
                                                        $("#s_county").val(s_county);
                                                    } else {
                                                    }
                                                } else {
                                                    abc.msg(data.message,{icon: 2});
                                                }
                                            }else{
                                                eval(data.js)
                                            }
                                        }
                                    });
                                } else {
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            }

        })


    })
})