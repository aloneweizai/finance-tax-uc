define(['jquery-3.1.0','jquery.base64.min'],function(){
    $("#phone").click(function(){
        window.location.href = ctx + "/member/member_index.html?url="  +$.base64.btoa("../userinfo/userinfolist.html#1_0");
    });

    $("#renz").click(function(){
        window.location.href = ctx + "/member/member_index.html?url="  +$.base64.btoa("../userinfo/userinfolist.html#1_1");
    });

    $("#weChat").click(function(){
        window.location.href = ctx + "/member/member_index.html?url="  +$.base64.btoa("../userinfo/userinfolist.html#1_3");
    });

    $("#wans").click(function() {
        window.location.href = ctx + "/member/member_index.html?url=" +$.base64.btoa("../userinfo/userinfolist.html");
    });
})