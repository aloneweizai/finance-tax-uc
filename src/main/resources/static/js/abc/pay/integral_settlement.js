require(["../../config"], function () {
    require(["jquery.full"], function ($) {

        //收货人信息上下收缩功能
        $('.xxhd-more-dz').click(function(){
            var ulLiH = $('.xxhd-content-dz li').outerHeight();
            var ulLiSize = $('.xxhd-content-dz li').size();
            var ulH = ulLiH * ulLiSize;
            var TiH = $(this).siblings('.xxhd-content-dz').outerHeight()
           // console.log(TiH)
            if(TiH != ulH){
                $(this).siblings('.xxhd-content-dz').stop().animate({height:ulH})
                $(this).children().children().removeClass('icon-xiangxia').addClass('icon-zhiding1')

            }else{
                $(this).siblings('.xxhd-content-dz').stop().animate({height:ulLiH})
                $(this).children().children().removeClass('icon-zhiding1').addClass('icon-xiangxia ')
            }
        })

        //收货人选中功能
        $(document).on('click','.xxhd-content-dz>li',function(){
            $(this).addClass('active').siblings().removeClass('active');
            var address_name=$(this).attr("address_name");
            var address_phone=$(this).attr("address_phone");
            var address_detail=$(this).attr("address_detail");
            var address_province=$(this).attr("address_province");
            var address_city=$(this).attr("address_city");
            var address_area=$(this).attr("address_area");
            var html='';
            html+='<div class="jejs-shrxx-num">';
            html+='收货人：<span>'+address_name+'</span><br>';
            html+='联系电话：<span>'+address_phone+'</span><br>';
            html+='收货地址：';
            html+='<span>'+address_province+'</span>';
            html+='<span>'+address_city+address_area+'</span>';
            html+='<span>'+address_detail+'</span>';
            html+='</div>';



            $('#shdz').html(html);
        })




        $(function(){
            //新增收货地址弹出框

            $('.xxhd-title-xzshr').click(function(){
                $('.xzdz-modal').show();
                $('.xzdz-modal-bg').show();
                $('body').css({"overflow-x":"hidden","overflow-y":"hidden"});
                $('#myform')[0].reset();
                $("#add_shi").html("<option value=''>请选择</option>");
                $("#add_qu").html("<option value=''>请选择</option>");
                shen_chushihua();
            })
            $('.xzdz-modal-close').click(function(){
                $('.xzdz-modal').hide();
                $('.xzdz-modal-bg').hide();
                $('body').css({"overflow-x":"auto","overflow-y":"auto"});
            })
            $('.modal-btn-gb').click(function(){
                $('.xzdz-modal').hide();
                $('.xzdz-modal-bg').hide();
                $('body').css({"overflow-x":"auto","overflow-y":"auto"});
            })

            //编辑地址弹出框
            $(document).on('click','.xxhd-btn-bj',function(){
                console.log($(this).index());
                $('.bj-modal').show();
                $('.bj-modal-bg').show();
                $('body').css({"overflow-x":"hidden","overflow-y":"hidden"});
                var address_id=$(this).attr("address_id")
                var address_name=$(this).attr("address_name")
                var address_province=$(this).attr("address_province")
                var address_city=$(this).attr("address_city")
                var address_area=$(this).attr("address_area")
                var address_detail=$(this).attr("address_detail")
                var address_phone=$(this).attr("address_phone")
                $('#updaddress_name').val(address_name);
                $('#updaddress_phone').val(address_phone);
                $('#updaddress_xxdz').val(address_detail);
                $('#updaddress_id').val(address_id);
                shen(address_province);
                shi(address_province,address_city);
                qu(address_city,address_area);
            })

            function shen_chushihua(){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/province/list.html",
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.list) {
                                    $("#add_shen").append("<option value=''>请选择</option>");
                                    $.each(data.list, function (i, item) {
                                        $("#add_shen").append("<option value='" + item.provinceId + "'>" + item.province + "</option>");
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



            function shen(address_province){
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
                                        $("#updaddress_shen").append("<option value='" + item.provinceId + "'>" + item.province + "</option>");
                                    });
                                    $('#updaddress_shen').val(address_province);
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

            function shi(address_province,address_city){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/city/list.html?pid="+address_province,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#updaddress_shi").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                    });
                                    $("#updaddress_shi").val(address_city);
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

            function qu(address_city,address_area){
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/county/list.html?pid=" + address_city,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.list) {
                                    $.each(data.list, function (i, item) {
                                        $("#updaddress_qu").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                    });
                                    $("#updaddress_qu").val(address_area);
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



            $('#updaddress_shen').change(function (){
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
                                    $("#updaddress_shi").html("<option value=''>请选择</option>");
                                    $("#updaddress_qu").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#updaddress_shi").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
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
                    $("#updaddress_shi").html("<option value=''>请选择</option>");
                    $("#updaddress_qu").html("<option value=''>请选择</option>");
                }
            });

            $('#updaddress_shi').change(function (){
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
                                    $("#updaddress_qu").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#updaddress_qu").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
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
                    $("#updaddress_qu").html("<option value=''>请选择</option>");
                }
            });


            $('#add_shen').change(function (){
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
                                    $("#add_shi").html("<option value=''>请选择</option>");
                                    $("#add_qu").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#add_shi").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
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
                    $("#add_shi").html("<option value=''>请选择</option>");
                    $("#add_qu").html("<option value=''>请选择</option>");
                }
            });

            $('#add_shi').change(function (){
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
                                    $("#add_qu").html("<option value=''>请选择</option>");
                                    if (data.list) {
                                        $.each(data.list, function (i, item) {
                                            $("#add_qu").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
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
                    $("#add_qu").html("<option value=''>请选择</option>");
                }
            });



            $('.bj-modal-close').click(function(){
                $('.bj-modal').hide();
                $('.bj-modal-bg').hide();
                $('body').css({"overflow-x":"auto","overflow-y":"auto"});
            })
            $('.modal-btn-gb').click(function(){
                $('.bj-modal').hide();
                $('.bj-modal-bg').hide();
                $('body').css({"overflow-x":"auto","overflow-y":"auto"});
            })

        })


        //数据校验
        var $validatorWsysVoFrom = $("#bj_dizhi").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                phone:[/^1[0-9]{10}$/,"手机号不正确"]
            },
            fields: {
                "updaddress_name": {
                    rule:"姓名:required;length(2~5)"
                },
                "updaddress_phone": {
                    rule:"手机号:required;phone;"
                },
                "updaddress_shen": {
                    rule:"省份:required"
                },
                "updaddress_shi":{
                    rule:"市:required"
                },
                "updaddress_qu":{
                    rule:"区县:required"
                },
                "updaddress_xxdz": {
                    rule:"详细地址:required;length(1~30)"
                }
            }
        });

        $('#tijiao').click(function (){
            console.log($(this).index());
            if($validatorWsysVoFrom.isValid()){
                parent.$.pop();
                var name=$('#updaddress_name').val();
                var phone=$('#updaddress_phone').val();
                var detail=$('#updaddress_xxdz').val();
                var id=$('#updaddress_id').val();
                var province=$('#updaddress_shen').val();
                var city=$('#updaddress_shi').val();
                var area=$('#updaddress_qu').val();
                var json={name:name,phone:phone,detail:detail,id:id,province:province,city:city,area:area};
                var data=JSON.stringify(json);
                var shen_text=$("#updaddress_shen").find("option:selected").text();
                var shi_text=$("#updaddress_shi").find("option:selected").text();
                var qu_text=$("#updaddress_qu").find("option:selected").text();
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/save.html",
                    data:data,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    $('span[key="'+id+'_name"]').html(name);
                                    $('span[key="'+id+'_xxdz"]').html(detail);
                                    $('span[key="'+id+'_shen"]').html(shen_text);
                                    $('span[key="'+id+'_shi"]').html(shi_text);
                                    $('span[key="'+id+'_qu"]').html(qu_text);
                                    $('div[key="'+id+'_phone"]').html(phone);
                                    var li=$('li[addressid="'+id+'"]');
                                    li.attr("address_name",name);
                                    li.attr("address_phone",phone);
                                    li.attr("address_province",shen_text);
                                    li.attr("address_city",shi_text);
                                    li.attr("address_area",qu_text);
                                    li.attr("address_detail",detail);
                                    var li_a=li.find('a').eq(0);
                                    li_a.attr("address_name",name);
                                    li_a.attr("address_phone",phone);
                                    li_a.attr("address_province",province);
                                    li_a.attr("address_city",city);
                                    li_a.attr("address_area",area);
                                    li_a.attr("address_detail",detail);
                                    var html='';
                                    html+='<div class="jejs-shrxx-num">';
                                    html+='收货人：<span>'+name+'</span><br>';
                                    html+='联系电话：<span>'+phone+'</span><br>';
                                    html+='收货地址：';
                                    html+='<span>'+shen_text+'</span>';
                                    html+='<span>'+shi_text+qu_text+'</span>';
                                    html+='<span>'+detail+'</span>';
                                    html+='</div>';
                                    $('#shdz').html(html);
                                    abc.msg("修改成功!",{icon:1});
                                    $('.bj-modal').hide();
                                    $('.bj-modal-bg').hide();
                                    $('body').css({"overflow-x":"auto","overflow-y":"auto"});
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

        //数据校验
        var $validatorWsysVoFrom1 = $("#myform").validator({
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {
                phone:[/^1[0-9]{10}$/,"手机号不正确"]
            },
            fields: {
                "add_name": {
                    rule:"姓名:required;length(2~5)"
                },
                "add_phone": {
                    rule:"手机号:required;phone;"
                },
                "add_shen": {
                    rule:"省份:required"
                },
                "add_shi":{
                    rule:"市:required"
                },
                "add_qu":{
                    rule:"区县:required"
                },
                "add_xxdz": {
                    rule:"详细地址:required;length(1~30)"
                }
            }
        });


        $('#savedizhi').click(function (){
            console.log($validatorWsysVoFrom1.isValid())
            if($validatorWsysVoFrom1.isValid()){
                parent.$.pop();
                var name=$('#add_name').val();
                var phone=$('#add_phone').val();
                var detail=$('#add_xxdz').val();
                var province=$('#add_shen').val();
                var city=$('#add_shi').val();
                var area=$('#add_qu').val();
                var json={name:name,phone:phone,detail:detail,province:province,city:city,area:area};
                var data=JSON.stringify(json);
                var shen_text=$("#add_shen").find("option:selected").text();
                var shi_text=$("#add_shi").find("option:selected").text();
                var qu_text=$("#add_qu").find("option:selected").text();
                $.ajax({
                    type: "POST",
                    url: ctx+"/userinfo/save.html",
                    data:data,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        parent.$.close();
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.code == '2000') {
                                    abc.msg("新增成功!",{icon:1});
                                    var html='';
                                    var size=$('.xxhd-content-dz').find("li").length;
                                    if(size==0){
                                        html+='<li class="clearfix active" addressid="'+data.data.data.id+'" address_name="'+name+'" address_phone="'+phone+'"';
                                    }else{
                                        html+='<li class="clearfix" addressid="'+data.data.data.id+'" address_name="'+name+'" address_phone="'+phone+'"';
                                    }
                                    html+='address_province="'+shen_text+'" address_city="'+shi_text+'" address_area="'+qu_text+'" address_detail="'+detail+'">';
                                    html+='<div class="xxhd-dz-name fl">';
                                    html+='<span key="'+data.data.data.id+'_name">'+name+'</span>';
                                    html+='<s class="dz-name-jb"></s>';
                                    html+='</div>';
                                    html+='<div class="xxhd-dz-xxxx fl">';
                                    html+='<span  key="'+data.data.data.id+'_shen">'+shen_text+'</span>';
                                    html+='<span key="'+data.data.data.id+'_shi">'+shi_text+'</span>';
                                    html+='<span key="'+data.data.data.id+'_qu">'+qu_text+'</span>';
                                    html+='<span key="'+data.data.data.id+'_xxdz">'+detail+'</span>';
                                    html+='</div>';
                                    html+='<div class="xxhd-dz-num fl"  key="'+data.data.data.id+'_phone">'+phone;
                                    html+='</div>';
                                    html+='<div class="xxhd-btn">';
                                    html+='<a href="javascript:;" class="xxhd-btn-bj" address_id="'+data.data.data.id+'" address_name="'+name+'" address_phone="'+phone+'"';
                                    html+='address_province="'+province+'" address_city="'+city+'" address_area="'+area+'" address_detail="'+detail+'">编辑</a>';
                                    html+='</div>';
                                    html+='</li>';
                                    $('.xxhd-content-dz').append(html);
                                    $('.xzdz-modal').hide();
                                    $('.xzdz-modal-bg').hide();

                                    if(size==0){
                                        html='';
                                        html+='<div class="jejs-shrxx-num">';
                                        html+='收货人：<span>'+name+'</span><br>';
                                        html+='联系电话：<span>'+phone+'</span><br>';
                                        html+='收货地址：';
                                        html+='<span>'+shen_text+'</span>';
                                        html+='<span>'+shi_text+qu_text+'</span>';
                                        html+='<span>'+detail+'</span>';
                                        html+='</div>';
                                        $('#shdz').html(html);
                                    }
                                    $('body').css({"overflow-x":"auto","overflow-y":"auto"});
                                    var ulLiH = $('.xxhd-content-dz li').outerHeight();
                                    var ulLiSize = $('.xxhd-content-dz li').size();
                                    var ulH = ulLiH * ulLiSize;
                                    var TiH = $('.xxhd-content-dz').outerHeight()
                                    //console.log(TiH)
                                    $('.xxhd-content-dz').stop().animate({height:ulH})
                                    $('.xxhd-more-dz').children().children().removeClass('icon-xiangxia').addClass('icon-zhiding1')
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






        $('#orderdd').click(function (){
            var lshid=$(this).attr("lshid");
            var zje=$(this).attr("zje");
            var isShipping=$(this).attr("isShipping");
            if(isShipping==1){
                var addressid='';
                $('.xxhd-content-dz').find("li").each(function (){
                    var class_=$(this).attr("class");
                    if(class_=='clearfix active'){
                        addressid=$(this).attr("addressid");
                    }
                })
                //console.log(addressid);
                if(addressid==''){
                    abc.msg("请选择收货地址!",{icon:2});
                    return;
                }
                //console.log(addressid);
            }
            //return;
            $(this).attr("disabled",true);
            var this_=$(this);
            var bz=$('#bz').val();
            var json={bz:bz,lshid:lshid,addreId:addressid};
            var data=JSON.stringify(json);
            abc.confirms("您是否花费："+zje+"积分,兑换清单商品?",{icon:3}, function(){
                $.pop();
                $.ajax({
                    type: "POST",
                    url: ctx+"/order_settlement/integral_lsh/"+lshid,
                    data:data,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if(data.data.code=='2000'){
                                    abc.msg("兑换成功!",{icon:1},function (){
                                         window.location.replace(ctx+"/order_settlement/integral_success/JF/"+data.no)
                                    });
                                }else{
                                    this_.removeAttr("disabled");
                                    $.close();
                                    abc.msg(data.data.message,{icon: 2});
                                }
                            } else {
                                this_.removeAttr("disabled");
                                $.close();
                                abc.msg(data.message,{icon: 2});
                            }
                        }else{
                            this_.removeAttr("disabled");
                            $.close();
                            eval(data.js)
                        }
                    }
                });
            },function (){
                this_.removeAttr("disabled");
            });


        });
    })
})