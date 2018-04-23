/**
 * 省 市 区 js
 * Created by liuqi on 2017/5/25.
 */
define(['jquery-3.1.0'],function(){
            //初始化省份数据
            var loadProvince = function () {
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/province/list.html",
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (data) {
                                $.each(data, function (i, item) {
                                    $("#s_province").append("<option value='" + item.provinceId + "'>" + item.province + "</option>");
                                });
                            } else {
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            };

            //根据省份 load...市
            var loadCity = function (pid) {
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/city/list.html?pid=" + pid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (data) {
                                $("#s_city").find("option:not(:first)").remove();
                                $.each(data, function (i, item) {
                                    $("#s_city").append("<option value='" + item.cityId + "'>" + item.city + "</option>");
                                });
                            } else {
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            };

            //根据市区    load...     country
            var loadCounty = function (pid) {
                $.ajax({
                    type: "GET",
                    url: ctx+"/user/region/county/list.html?pid=" + pid,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (data) {
                                $("#s_county").find("option:not(:first)").remove();
                                $.each(data, function (i, item) {
                                    $("#s_county").append("<option value='" + item.areaId + "'>" + item.area + "</option>");
                                });
                            } else {
                            }
                        }else{
                            eval(data.js)
                        }
                    }
                });
            };

            //根据省份 改变 市 地区
            $("#s_province").change(function () {
                var provinceId = $(this).val();
                $("#s_county").find("option:not(:first)").remove();
                if (provinceId == '') {
                    //如果是   --请选择--
                    $("#s_city").find("option:not(:first)").remove();
                } else {
                    //根据省id加载市
                    loadCity(provinceId);
                }
            });

            //根据市区 改变 3级地址
            $("#s_city").change(function () {
                var cityId = $(this).val();
                if (cityId == '') {
                    //如果是   --请选择--
                    $("#s_county").find("option:not(:first)").remove();
                } else {
                    //根据省id加载市
                    loadCounty(cityId);
                }
            });

            loadProvince();

            //如果是修改页面    初始化地址信息
            if($("#initRegion").length > 0){
                var init_province_id = $("#initRegion").attr("data-province-id");
                var init_city_id = $("#initRegion").attr("data-city-id");
                var init_county_id = $("#initRegion").attr("data-county-id");
                if(init_province_id){
                    $("#s_province").find("option").each(function(){
                        if($(this).val() == init_province_id){
                            $(this).attr("selected","selected");
                        }
                    });
                    loadCity(init_province_id);
                }
                if(init_city_id){
                    $("#s_city").find("option").each(function(){
                        if($(this).val() == init_city_id){
                            $(this).attr("selected","selected");
                        }
                    });
                    loadCounty(init_city_id);
                }
                if(init_county_id){
                    $("#s_county").find("option").each(function(){
                        if($(this).val() == init_county_id){
                            $(this).attr("selected","selected");
                        }
                    });
                }
            }
});
