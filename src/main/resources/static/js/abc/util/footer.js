require(["../config"], function () {
    require(["jquery-3.1.0","jquery.base64.min"], function ($) {
        //更换绑定手机
        $("a[name='ghbdsj_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url=" + $.base64.btoa(ctx + "/userinfo/userinfolist.html#ghbdsj"));
        });

        //实名认证
        $("a[name='smrz_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url=" + $.base64.btoa(ctx + "/userinfo/userinfolist.html?v=12345678#1_1"));
        });

        //积分充值
        $("a[name='jfcz_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/member/member_index.html?url=" + $.base64.btoa(ctx + "/pointsExchange/points.php#jfcz"));
        });

        //我的积分
        $("a[name='wdjf_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/member/member_index.html?url=" + $.base64.btoa(ctx + "/pointsExchange/points.php"));
        });

        //索取发票
        $("a[name='sqfp_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url=" + $.base64.btoa(ctx + "/userinfo/userinvoice.html"));
        });

        //忘记申报密码
        $("a[name='wjmm_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url=" + $.base64.btoa(ctx + "/userinfo/enterprise.html#wjmm"));
        });

        //申报服务续费
        $("a[name='sbfwxf_footer']").on("click", function () {
            $.ajax({
                url: ctx + "/pub/toPayServiceCharge?random=" + Math.random(),
                type: "GET",
                success: function (result) {
                    if (result.toUrl != null) {
                        window.open(result.toUrl);
                    } else {
                        window.open("http://pay.abc12366.cn");
                    }
                },
                error: function () {
                    //layer.alert("出错了");
                    window.open("http://pay.abc12366.cn");
                },
                dataType: "json"
            })
            //window.open("http://pay.abc12366.cn");
        });

        //基本资料
        $("a[name='jbzl_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url=" + $.base64.btoa(ctx + "/userinfo/userinfolist.html"));
        });

        //我的消息
        $("a[name='wdxx_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/index.html?url="+$.base64.btoa(ctx+"/userinfo/user_message.html"));
        })

        //会员中心
        $("a[name='hyzx_footer']").on("click", function () {
            validLogin_linkto_footer(ctx + "/member/member_index.html?url="+$.base64.btoa(ctx+"/member/my_index.html"));
        })

        function validLogin_linkto_footer(url) {

                window.location = url;


        }
    });
});