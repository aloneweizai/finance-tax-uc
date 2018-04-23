requirejs.config({
    baseUrl : ctx+"/js/lib",
    paths : {
        "jquery-3.1.0" : "../plugin/jquery-3.1.0",
        "owl.carousel" : "../plugin/owl.carousel",
        "bootstrap" : "../plugin/bootstrap",
        "jquery.validate" : "../plugin/jquery.validate",
        "cropper" : "../plugin/cropper",
        "json.array":"json.array",
        "page":"page",
        "layer":"layer/layer",
        "nicevalidator":"nicevalidator/jquery.validator",
        "nicevalidator.zh_CN":"nicevalidator/zh_CN",
        "abc.ajaxfileupload":"../abc/util/ajaxfileupload",
        "indexheight":"../abc/util/indexHeight",
        "jquery.blockUI":"../plugin/jquery.blockUI",
        "loading":"../abc/util/blockLoad",
        "jquery.Jcrop":"../abc/util/jquery.Jcrop",
        "jquery.base64.min":"../abc/util/jquery.base64.min",
        "jquery.md5":"../abc/util/jquery.md5",
        "jquery.jedate":"jedate/jquery.jedate",
        "layui":"layui/layui.all",
        "cebianlan":"../abc/util/cebianlan",
        "abc.layer":"../abc/util/abclayer",
        "footer":"../abc/util/footer",
        "lock":"../abc/util/lock"
    },
    shim : {
        "owl.carousel" : {
            deps:["jquery-3.1.0"]
        },
        "bootstrap" : {
            deps:["jquery-3.1.0"]
        },
        "json.array" : {
            deps:["jquery-3.1.0"]
        },
        "page" : {
            deps:["jquery-3.1.0"]
        },
        "nicevalidator": {
            deps: ["jquery-3.1.0"]
        },
        "abc.layer": {
            deps: ["jquery-3.1.0","layui"]
        },
        "nicevalidator.zh_CN": {
            deps: ["jquery-3.1.0", "nicevalidator"]
        },
        "layer": {
            deps: ["jquery-3.1.0"]
        },
        "abc.ajaxfileupload": {
            deps: ["jquery-3.1.0"]
        },
        "jquery.blockUI":{
            deps:["jquery-3.1.0"]
        },
        "loading":{
            deps:["jquery-3.1.0","jquery.blockUI"]
        },
        "jquery.Jcrop":{
            deps:["jquery-3.1.0"]
        },
        "jquery.base64.min":{
            deps:["jquery-3.1.0"]
        },
        "jquery.md5":{
            deps:["jquery-3.1.0"]
        },
        "jquery.jedate":{
            deps:["jquery-3.1.0"]
        },
        "lock":{
            deps:["jquery-3.1.0"]
        }

    },
    urlArgs:"v="+(new Date()).getTime()
});
define("jquery.full",["jquery-3.1.0","bootstrap","nicevalidator","nicevalidator.zh_CN","loading","indexheight","layui","abc.layer"],function($){
    return $;
});
define("ajaxfileupload.full", ["abc.ajaxfileupload"], function ($) {
    //return $.noConflict(true);
    return $;
});
define("loading.full", ["loading"], function ($) {
    //return $.noConflict(true);
    return $;
});
